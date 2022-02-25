package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bic */
/* loaded from: classes3.dex */
public final class ObservableSwitchMapSingle<T, R> extends Observable<R> {

    /* renamed from: a */
    final Observable<T> f18802a;

    /* renamed from: b */
    final Function<? super T, ? extends SingleSource<? extends R>> f18803b;

    /* renamed from: c */
    final boolean f18804c;

    public ObservableSwitchMapSingle(Observable<T> aslVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        this.f18802a = aslVar;
        this.f18803b = aunVar;
        this.f18804c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        if (!ScalarXMapZHelper.m9670b(this.f18802a, this.f18803b, assVar)) {
            this.f18802a.subscribe(new C4374a(assVar, this.f18803b, this.f18804c));
        }
    }

    /* compiled from: ObservableSwitchMapSingle.java */
    /* renamed from: z1.bic$a */
    /* loaded from: classes3.dex */
    static final class C4374a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final C4375a<Object> INNER_DISPOSED = new C4375a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicReference<C4375a<R>> inner = new AtomicReference<>();
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        Disposable upstream;

        C4374a(Observer<? super R> assVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
            this.downstream = assVar;
            this.mapper = aunVar;
            this.delayErrors = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Observer
        public void onNext(T t) {
            C4375a<R> aVar;
            C4375a<R> aVar2 = this.inner.get();
            if (aVar2 != null) {
                aVar2.dispose();
            }
            try {
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                C4375a<R> aVar3 = new C4375a<>(this);
                do {
                    aVar = this.inner.get();
                    if (aVar == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(aVar, aVar3));
                ataVar.mo10018a(aVar3);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.dispose();
                this.inner.getAndSet(INNER_DISPOSED);
                onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
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

        /* JADX WARN: Multi-variable type inference failed */
        void disposeInner() {
            C4375a<Object> aVar = (C4375a) this.inner.getAndSet(INNER_DISPOSED);
            if (aVar != null && aVar != INNER_DISPOSED) {
                aVar.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            disposeInner();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerError(C4375a<R> aVar, Throwable th) {
            if (!this.inner.compareAndSet(aVar, null) || !this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!this.delayErrors) {
                this.upstream.dispose();
                disposeInner();
            }
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> assVar = this.downstream;
                AtomicThrowable bsnVar = this.errors;
                AtomicReference<C4375a<R>> atomicReference = this.inner;
                int i = 1;
                while (!this.cancelled) {
                    if (bsnVar.get() == null || this.delayErrors) {
                        boolean z = this.done;
                        C4375a<R> aVar = atomicReference.get();
                        boolean z2 = aVar == null;
                        if (z && z2) {
                            Throwable terminate = bsnVar.terminate();
                            if (terminate != null) {
                                assVar.onError(terminate);
                                return;
                            } else {
                                assVar.onComplete();
                                return;
                            }
                        } else if (z2 || aVar.item == null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            atomicReference.compareAndSet(aVar, null);
                            assVar.onNext((R) aVar.item);
                        }
                    } else {
                        assVar.onError(bsnVar.terminate());
                        return;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableSwitchMapSingle.java */
        /* renamed from: z1.bic$a$a */
        /* loaded from: classes3.dex */
        public static final class C4375a<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final C4374a<?, R> parent;

            C4375a(C4374a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
