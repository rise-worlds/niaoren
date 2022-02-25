package p110z1;

/* renamed from: z1.bgj */
/* loaded from: classes3.dex */
public final class MaybeIsEmptySingle<T> extends Single<Boolean> implements FuseToMaybe<Boolean>, HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18661a;

    public MaybeIsEmptySingle(MaybeSource<T> asiVar) {
        this.f18661a = asiVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18661a;
    }

    @Override // p110z1.FuseToMaybe
    /* renamed from: v_ */
    public Maybe<Boolean> mo9694v_() {
        return RxJavaPlugins.m9205a(new MaybeIsEmpty(this.f18661a));
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f18661a.mo10646a(new C4308a(asxVar));
    }

    /* compiled from: MaybeIsEmptySingle.java */
    /* renamed from: z1.bgj$a */
    /* loaded from: classes3.dex */
    static final class C4308a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f18662a;

        /* renamed from: b */
        Disposable f18663b;

        C4308a(SingleObserver<? super Boolean> asxVar) {
            this.f18662a = asxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18663b.dispose();
            this.f18663b = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18663b.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18663b, atrVar)) {
                this.f18663b = atrVar;
                this.f18662a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18663b = DisposableHelper.DISPOSED;
            this.f18662a.onSuccess(false);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18663b = DisposableHelper.DISPOSED;
            this.f18662a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18663b = DisposableHelper.DISPOSED;
            this.f18662a.onSuccess(true);
        }
    }
}
