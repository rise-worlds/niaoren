package p110z1;

/* renamed from: z1.bpa */
/* loaded from: classes3.dex */
public final class SingleDoOnEvent<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19750a;

    /* renamed from: b */
    final BiConsumer<? super T, ? super Throwable> f19751b;

    public SingleDoOnEvent(SingleSource<T> ataVar, BiConsumer<? super T, ? super Throwable> auhVar) {
        this.f19750a = ataVar;
        this.f19751b = auhVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19750a.mo10018a(new C4671a(asxVar));
    }

    /* compiled from: SingleDoOnEvent.java */
    /* renamed from: z1.bpa$a */
    /* loaded from: classes3.dex */
    final class C4671a implements SingleObserver<T> {

        /* renamed from: b */
        private final SingleObserver<? super T> f19753b;

        C4671a(SingleObserver<? super T> asxVar) {
            this.f19753b = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19753b.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleDoOnEvent.this.f19751b.mo9895a(t, null);
                this.f19753b.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19753b.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnEvent.this.f19751b.mo9895a(null, th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f19753b.onError(th);
        }
    }
}
