package p110z1;

/* renamed from: z1.bpp */
/* loaded from: classes3.dex */
public final class SingleHide<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19801a;

    public SingleHide(SingleSource<? extends T> ataVar) {
        this.f19801a = ataVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19801a.mo10018a(new C4685a(asxVar));
    }

    /* compiled from: SingleHide.java */
    /* renamed from: z1.bpp$a */
    /* loaded from: classes3.dex */
    static final class C4685a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19802a;

        /* renamed from: b */
        Disposable f19803b;

        C4685a(SingleObserver<? super T> asxVar) {
            this.f19802a = asxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19803b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19803b.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19803b, atrVar)) {
                this.f19803b = atrVar;
                this.f19802a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19802a.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19802a.onError(th);
        }
    }
}
