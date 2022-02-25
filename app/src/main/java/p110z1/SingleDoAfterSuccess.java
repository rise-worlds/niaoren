package p110z1;

/* renamed from: z1.bov */
/* loaded from: classes3.dex */
public final class SingleDoAfterSuccess<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19732a;

    /* renamed from: b */
    final Consumer<? super T> f19733b;

    public SingleDoAfterSuccess(SingleSource<T> ataVar, Consumer<? super T> aumVar) {
        this.f19732a = ataVar;
        this.f19733b = aumVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19732a.mo10018a(new C4665a(asxVar, this.f19733b));
    }

    /* compiled from: SingleDoAfterSuccess.java */
    /* renamed from: z1.bov$a */
    /* loaded from: classes3.dex */
    static final class C4665a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19734a;

        /* renamed from: b */
        final Consumer<? super T> f19735b;

        /* renamed from: c */
        Disposable f19736c;

        C4665a(SingleObserver<? super T> asxVar, Consumer<? super T> aumVar) {
            this.f19734a = asxVar;
            this.f19735b = aumVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19736c, atrVar)) {
                this.f19736c = atrVar;
                this.f19734a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19734a.onSuccess(t);
            try {
                this.f19735b.accept(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19734a.onError(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19736c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19736c.isDisposed();
        }
    }
}
