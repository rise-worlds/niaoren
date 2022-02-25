package p110z1;

/* renamed from: z1.bpc */
/* loaded from: classes3.dex */
public final class SingleDoOnSuccess<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19759a;

    /* renamed from: b */
    final Consumer<? super T> f19760b;

    public SingleDoOnSuccess(SingleSource<T> ataVar, Consumer<? super T> aumVar) {
        this.f19759a = ataVar;
        this.f19760b = aumVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19759a.mo10018a(new C4673a(asxVar));
    }

    /* compiled from: SingleDoOnSuccess.java */
    /* renamed from: z1.bpc$a */
    /* loaded from: classes3.dex */
    final class C4673a implements SingleObserver<T> {

        /* renamed from: a */
        final SingleObserver<? super T> f19761a;

        C4673a(SingleObserver<? super T> asxVar) {
            this.f19761a = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19761a.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleDoOnSuccess.this.f19760b.accept(t);
                this.f19761a.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19761a.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19761a.onError(th);
        }
    }
}
