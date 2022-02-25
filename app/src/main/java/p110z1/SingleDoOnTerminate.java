package p110z1;

/* renamed from: z1.bpd */
/* loaded from: classes3.dex */
public final class SingleDoOnTerminate<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19763a;

    /* renamed from: b */
    final Action f19764b;

    public SingleDoOnTerminate(SingleSource<T> ataVar, Action augVar) {
        this.f19763a = ataVar;
        this.f19764b = augVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19763a.mo10018a(new C4674a(asxVar));
    }

    /* compiled from: SingleDoOnTerminate.java */
    /* renamed from: z1.bpd$a */
    /* loaded from: classes3.dex */
    final class C4674a implements SingleObserver<T> {

        /* renamed from: a */
        final SingleObserver<? super T> f19765a;

        C4674a(SingleObserver<? super T> asxVar) {
            this.f19765a = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19765a.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleDoOnTerminate.this.f19764b.mo9442a();
                this.f19765a.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19765a.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnTerminate.this.f19764b.mo9442a();
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f19765a.onError(th);
        }
    }
}
