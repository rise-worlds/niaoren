package p110z1;

/* renamed from: z1.boz */
/* loaded from: classes3.dex */
public final class SingleDoOnError<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19746a;

    /* renamed from: b */
    final Consumer<? super Throwable> f19747b;

    public SingleDoOnError(SingleSource<T> ataVar, Consumer<? super Throwable> aumVar) {
        this.f19746a = ataVar;
        this.f19747b = aumVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19746a.mo10018a(new C4669a(asxVar));
    }

    /* compiled from: SingleDoOnError.java */
    /* renamed from: z1.boz$a */
    /* loaded from: classes3.dex */
    final class C4669a implements SingleObserver<T> {

        /* renamed from: b */
        private final SingleObserver<? super T> f19749b;

        C4669a(SingleObserver<? super T> asxVar) {
            this.f19749b = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19749b.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19749b.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnError.this.f19747b.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f19749b.onError(th);
        }
    }
}
