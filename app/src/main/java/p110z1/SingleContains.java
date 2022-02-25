package p110z1;

/* renamed from: z1.bol */
/* loaded from: classes3.dex */
public final class SingleContains<T> extends Single<Boolean> {

    /* renamed from: a */
    final SingleSource<T> f19697a;

    /* renamed from: b */
    final Object f19698b;

    /* renamed from: c */
    final BiPredicate<Object, Object> f19699c;

    public SingleContains(SingleSource<T> ataVar, Object obj, BiPredicate<Object, Object> aujVar) {
        this.f19697a = ataVar;
        this.f19698b = obj;
        this.f19699c = aujVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f19697a.mo10018a(new C4654a(asxVar));
    }

    /* compiled from: SingleContains.java */
    /* renamed from: z1.bol$a */
    /* loaded from: classes3.dex */
    final class C4654a implements SingleObserver<T> {

        /* renamed from: b */
        private final SingleObserver<? super Boolean> f19701b;

        C4654a(SingleObserver<? super Boolean> asxVar) {
            this.f19701b = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19701b.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                this.f19701b.onSuccess(Boolean.valueOf(SingleContains.this.f19699c.mo9871a(t, SingleContains.this.f19698b)));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19701b.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19701b.onError(th);
        }
    }
}
