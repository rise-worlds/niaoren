package p110z1;

/* renamed from: z1.bey */
/* loaded from: classes3.dex */
public final class MaybeContains<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    final MaybeSource<T> f18545a;

    /* renamed from: b */
    final Object f18546b;

    public MaybeContains(MaybeSource<T> asiVar, Object obj) {
        this.f18545a = asiVar;
        this.f18546b = obj;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public MaybeSource<T> mo9687s_() {
        return this.f18545a;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f18545a.mo10646a(new C4266a(asxVar, this.f18546b));
    }

    /* compiled from: MaybeContains.java */
    /* renamed from: z1.bey$a */
    /* loaded from: classes3.dex */
    static final class C4266a implements MaybeObserver<Object>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f18547a;

        /* renamed from: b */
        final Object f18548b;

        /* renamed from: c */
        Disposable f18549c;

        C4266a(SingleObserver<? super Boolean> asxVar, Object obj) {
            this.f18547a = asxVar;
            this.f18548b = obj;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18549c.dispose();
            this.f18549c = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18549c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18549c, atrVar)) {
                this.f18549c = atrVar;
                this.f18547a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(Object obj) {
            this.f18549c = DisposableHelper.DISPOSED;
            this.f18547a.onSuccess(Boolean.valueOf(ObjectHelper.m9874a(obj, this.f18548b)));
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18549c = DisposableHelper.DISPOSED;
            this.f18547a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18549c = DisposableHelper.DISPOSED;
            this.f18547a.onSuccess(false);
        }
    }
}
