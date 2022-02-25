package p110z1;

/* renamed from: z1.bez */
/* loaded from: classes3.dex */
public final class MaybeCount<T> extends Single<Long> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18550a;

    public MaybeCount(MaybeSource<T> asiVar) {
        this.f18550a = asiVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18550a;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Long> asxVar) {
        this.f18550a.mo10646a(new C4267a(asxVar));
    }

    /* compiled from: MaybeCount.java */
    /* renamed from: z1.bez$a */
    /* loaded from: classes3.dex */
    static final class C4267a implements MaybeObserver<Object>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Long> f18551a;

        /* renamed from: b */
        Disposable f18552b;

        C4267a(SingleObserver<? super Long> asxVar) {
            this.f18551a = asxVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18552b, atrVar)) {
                this.f18552b = atrVar;
                this.f18551a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(Object obj) {
            this.f18552b = DisposableHelper.DISPOSED;
            this.f18551a.onSuccess(1L);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18552b = DisposableHelper.DISPOSED;
            this.f18551a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18552b = DisposableHelper.DISPOSED;
            this.f18551a.onSuccess(0L);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18552b.isDisposed();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18552b.dispose();
            this.f18552b = DisposableHelper.DISPOSED;
        }
    }
}
