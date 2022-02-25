package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhy */
/* loaded from: classes3.dex */
public final class ObservableConcatMapMaybe<T, R> extends Observable<R> {

    /* renamed from: a */
    final Observable<T> f18778a;

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends R>> f18779b;

    /* renamed from: c */
    final ErrorMode f18780c;

    /* renamed from: d */
    final int f18781d;

    public ObservableConcatMapMaybe(Observable<T> aslVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, ErrorMode bsuVar, int i) {
        this.f18778a = aslVar;
        this.f18779b = aunVar;
        this.f18780c = bsuVar;
        this.f18781d = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        if (!ScalarXMapZHelper.m9671a(this.f18778a, this.f18779b, assVar)) {
            this.f18778a.subscribe(new C4365a(assVar, this.f18779b, this.f18781d, this.f18780c));
        }
    }

    /* compiled from: ObservableConcatMapMaybe.java */
    /* renamed from: z1.bhy$a */
    /* loaded from: classes3.dex */
    static final class C4365a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final C4366a<R> inner = new C4366a<>(this);
        R item;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        final SimplePlainQueue<T> queue;
        volatile int state;
        Disposable upstream;

        C4365a(Observer<? super R> assVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, int i, ErrorMode bsuVar) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.errorMode = bsuVar;
            this.queue = new SpscLinkedArrayQueue(i);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        void innerComplete() {
            this.state = 0;
            drain();
        }

        void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.dispose();
                }
                this.state = 0;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> assVar = this.downstream;
                ErrorMode bsuVar = this.errorMode;
                SimplePlainQueue<T> avvVar = this.queue;
                AtomicThrowable bsnVar = this.errors;
                int i = 1;
                while (true) {
                    if (this.cancelled) {
                        avvVar.clear();
                        this.item = null;
                    } else {
                        int i2 = this.state;
                        if (bsnVar.get() == null || !(bsuVar == ErrorMode.IMMEDIATE || (bsuVar == ErrorMode.BOUNDARY && i2 == 0))) {
                            boolean z = false;
                            if (i2 == 0) {
                                boolean z2 = this.done;
                                T poll = avvVar.poll();
                                if (poll == null) {
                                    z = true;
                                }
                                if (z2 && z) {
                                    Throwable terminate = bsnVar.terminate();
                                    if (terminate == null) {
                                        assVar.onComplete();
                                        return;
                                    } else {
                                        assVar.onError(terminate);
                                        return;
                                    }
                                } else if (!z) {
                                    try {
                                        MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null MaybeSource");
                                        this.state = 1;
                                        asiVar.mo10646a(this.inner);
                                    } catch (Throwable th) {
                                        Exceptions.m9983b(th);
                                        this.upstream.dispose();
                                        avvVar.clear();
                                        bsnVar.addThrowable(th);
                                        assVar.onError(bsnVar.terminate());
                                        return;
                                    }
                                }
                            } else if (i2 == 2) {
                                this.item = null;
                                assVar.onNext((R) this.item);
                                this.state = 0;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
                avvVar.clear();
                this.item = null;
                assVar.onError(bsnVar.terminate());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableConcatMapMaybe.java */
        /* renamed from: z1.bhy$a$a */
        /* loaded from: classes3.dex */
        public static final class C4366a<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final C4365a<?, R> parent;

            C4366a(C4365a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(R r) {
                this.parent.innerSuccess(r);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
