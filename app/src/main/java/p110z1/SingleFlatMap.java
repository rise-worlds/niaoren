package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpg */
/* loaded from: classes3.dex */
public final class SingleFlatMap<T, R> extends Single<R> {

    /* renamed from: a */
    final SingleSource<? extends T> f19775a;

    /* renamed from: b */
    final Function<? super T, ? extends SingleSource<? extends R>> f19776b;

    public SingleFlatMap(SingleSource<? extends T> ataVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        this.f19776b = aunVar;
        this.f19775a = ataVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        this.f19775a.mo10018a(new C4676a(asxVar, this.f19776b));
    }

    /* compiled from: SingleFlatMap.java */
    /* renamed from: z1.bpg$a */
    /* loaded from: classes3.dex */
    static final class C4676a<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 3258103020495908596L;
        final SingleObserver<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        C4676a(SingleObserver<? super R> asxVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
            this.downstream = asxVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The single returned by the mapper is null");
                if (!isDisposed()) {
                    ataVar.mo10018a(new C4677a(this, this.downstream));
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        /* compiled from: SingleFlatMap.java */
        /* renamed from: z1.bpg$a$a */
        /* loaded from: classes3.dex */
        static final class C4677a<R> implements SingleObserver<R> {

            /* renamed from: a */
            final AtomicReference<Disposable> f19777a;

            /* renamed from: b */
            final SingleObserver<? super R> f19778b;

            C4677a(AtomicReference<Disposable> atomicReference, SingleObserver<? super R> asxVar) {
                this.f19777a = atomicReference;
                this.f19778b = asxVar;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this.f19777a, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                this.f19778b.onSuccess(r);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.f19778b.onError(th);
            }
        }
    }
}
