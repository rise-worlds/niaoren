package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bib */
/* loaded from: classes3.dex */
public final class ObservableSwitchMapMaybe<T, R> extends Observable<R> {

    /* renamed from: a */
    final Observable<T> f18799a;

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends R>> f18800b;

    /* renamed from: c */
    final boolean f18801c;

    public ObservableSwitchMapMaybe(Observable<T> aslVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
        this.f18799a = aslVar;
        this.f18800b = aunVar;
        this.f18801c = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        if (!ScalarXMapZHelper.m9671a(this.f18799a, this.f18800b, assVar)) {
            this.f18799a.subscribe(new C4372a(assVar, this.f18800b, this.f18801c));
        }
    }

    /* compiled from: ObservableSwitchMapMaybe.java */
    /* renamed from: z1.bib$a */
    /* loaded from: classes3.dex */
    static final class C4372a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final C4373a<Object> INNER_DISPOSED = new C4373a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicReference<C4373a<R>> inner = new AtomicReference<>();
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        Disposable upstream;

        C4372a(Observer<? super R> assVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
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
            C4373a<R> aVar;
            C4373a<R> aVar2 = this.inner.get();
            if (aVar2 != null) {
                aVar2.dispose();
            }
            try {
                MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                C4373a<R> aVar3 = new C4373a<>(this);
                do {
                    aVar = this.inner.get();
                    if (aVar == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(aVar, aVar3));
                asiVar.mo10646a(aVar3);
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
            C4373a<Object> aVar = (C4373a) this.inner.getAndSet(INNER_DISPOSED);
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

        void innerError(C4373a<R> aVar, Throwable th) {
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

        void innerComplete(C4373a<R> aVar) {
            if (this.inner.compareAndSet(aVar, null)) {
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> assVar = this.downstream;
                AtomicThrowable bsnVar = this.errors;
                AtomicReference<C4373a<R>> atomicReference = this.inner;
                int i = 1;
                while (!this.cancelled) {
                    if (bsnVar.get() == null || this.delayErrors) {
                        boolean z = this.done;
                        C4373a<R> aVar = atomicReference.get();
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
        /* compiled from: ObservableSwitchMapMaybe.java */
        /* renamed from: z1.bib$a$a */
        /* loaded from: classes3.dex */
        public static final class C4373a<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final C4372a<?, R> parent;

            C4373a(C4372a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete(this);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
