package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhx */
/* loaded from: classes3.dex */
public final class ObservableConcatMapCompletable<T> extends Completable {

    /* renamed from: a */
    final Observable<T> f18774a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18775b;

    /* renamed from: c */
    final ErrorMode f18776c;

    /* renamed from: d */
    final int f18777d;

    public ObservableConcatMapCompletable(Observable<T> aslVar, Function<? super T, ? extends CompletableSource> aunVar, ErrorMode bsuVar, int i) {
        this.f18774a = aslVar;
        this.f18775b = aunVar;
        this.f18776c = bsuVar;
        this.f18777d = i;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        if (!ScalarXMapZHelper.m9672a(this.f18774a, this.f18775b, arpVar)) {
            this.f18774a.subscribe(new C4363a(arpVar, this.f18775b, this.f18776c, this.f18777d));
        }
    }

    /* compiled from: ObservableConcatMapCompletable.java */
    /* renamed from: z1.bhx$a */
    /* loaded from: classes3.dex */
    static final class C4363a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 3610901111000061034L;
        volatile boolean active;
        volatile boolean disposed;
        volatile boolean done;
        final CompletableObserver downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final C4364a inner = new C4364a(this);
        final Function<? super T, ? extends CompletableSource> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        Disposable upstream;

        C4363a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, ErrorMode bsuVar, int i) {
            this.downstream = arpVar;
            this.mapper = aunVar;
            this.errorMode = bsuVar;
            this.prefetch = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                if (atrVar instanceof QueueDisposable) {
                    QueueDisposable avrVar = (QueueDisposable) atrVar;
                    int requestFusion = avrVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.queue = avrVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = avrVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (t != null) {
                this.queue.offer(t);
            }
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.disposed = true;
                this.inner.dispose();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.downstream.onError(terminate);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.done = true;
                drain();
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            this.upstream.dispose();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        void innerError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.disposed = true;
                this.upstream.dispose();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.downstream.onError(terminate);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.active = false;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        void drain() {
            boolean z;
            if (getAndIncrement() == 0) {
                AtomicThrowable bsnVar = this.errors;
                ErrorMode bsuVar = this.errorMode;
                while (!this.disposed) {
                    if (!this.active) {
                        if (bsuVar != ErrorMode.BOUNDARY || bsnVar.get() == null) {
                            boolean z2 = this.done;
                            CompletableSource arsVar = null;
                            try {
                                T poll = this.queue.poll();
                                if (poll != null) {
                                    arsVar = (CompletableSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z2 && z) {
                                    this.disposed = true;
                                    Throwable terminate = bsnVar.terminate();
                                    if (terminate != null) {
                                        this.downstream.onError(terminate);
                                        return;
                                    } else {
                                        this.downstream.onComplete();
                                        return;
                                    }
                                } else if (!z) {
                                    this.active = true;
                                    arsVar.mo11309a(this.inner);
                                }
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                this.disposed = true;
                                this.queue.clear();
                                this.upstream.dispose();
                                bsnVar.addThrowable(th);
                                this.downstream.onError(bsnVar.terminate());
                                return;
                            }
                        } else {
                            this.disposed = true;
                            this.queue.clear();
                            this.downstream.onError(bsnVar.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableConcatMapCompletable.java */
        /* renamed from: z1.bhx$a$a */
        /* loaded from: classes3.dex */
        public static final class C4364a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = 5638352172918776687L;
            final C4363a<?> parent;

            C4364a(C4363a<?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
