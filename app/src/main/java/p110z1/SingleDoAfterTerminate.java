package p110z1;

/* renamed from: z1.bow */
/* loaded from: classes3.dex */
public final class SingleDoAfterTerminate<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19737a;

    /* renamed from: b */
    final Action f19738b;

    public SingleDoAfterTerminate(SingleSource<T> ataVar, Action augVar) {
        this.f19737a = ataVar;
        this.f19738b = augVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19737a.mo10018a(new C4666a(asxVar, this.f19738b));
    }

    /* compiled from: SingleDoAfterTerminate.java */
    /* renamed from: z1.bow$a */
    /* loaded from: classes3.dex */
    static final class C4666a<T> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19739a;

        /* renamed from: b */
        final Action f19740b;

        /* renamed from: c */
        Disposable f19741c;

        C4666a(SingleObserver<? super T> asxVar, Action augVar) {
            this.f19739a = asxVar;
            this.f19740b = augVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19741c, atrVar)) {
                this.f19741c = atrVar;
                this.f19739a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19739a.onSuccess(t);
            m9549a();
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19739a.onError(th);
            m9549a();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19741c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19741c.isDisposed();
        }

        /* renamed from: a */
        private void m9549a() {
            try {
                this.f19740b.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
