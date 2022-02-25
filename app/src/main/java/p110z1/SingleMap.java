package p110z1;

/* renamed from: z1.bpt */
/* loaded from: classes3.dex */
public final class SingleMap<T, R> extends Single<R> {

    /* renamed from: a */
    final SingleSource<? extends T> f19809a;

    /* renamed from: b */
    final Function<? super T, ? extends R> f19810b;

    public SingleMap(SingleSource<? extends T> ataVar, Function<? super T, ? extends R> aunVar) {
        this.f19809a = ataVar;
        this.f19810b = aunVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        this.f19809a.mo10018a(new C4691a(asxVar, this.f19810b));
    }

    /* compiled from: SingleMap.java */
    /* renamed from: z1.bpt$a */
    /* loaded from: classes3.dex */
    static final class C4691a<T, R> implements SingleObserver<T> {

        /* renamed from: a */
        final SingleObserver<? super R> f19811a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f19812b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4691a(SingleObserver<? super R> asxVar, Function<? super T, ? extends R> aunVar) {
            this.f19811a = asxVar;
            this.f19812b = aunVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19811a.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                this.f19811a.onSuccess(ObjectHelper.m9873a(this.f19812b.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19811a.onError(th);
        }
    }
}
