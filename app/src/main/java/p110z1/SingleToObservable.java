package p110z1;

/* renamed from: z1.bqe */
/* loaded from: classes3.dex */
public final class SingleToObservable<T> extends Observable<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19837a;

    public SingleToObservable(SingleSource<? extends T> ataVar) {
        this.f19837a = ataVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f19837a.mo10018a(m9539b((Observer) assVar));
    }

    /* renamed from: b */
    public static <T> SingleObserver<T> m9539b(Observer<? super T> assVar) {
        return new C4703a(assVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleToObservable.java */
    /* renamed from: z1.bqe$a */
    /* loaded from: classes3.dex */
    public static final class C4703a<T> extends DeferredScalarDisposable<T> implements SingleObserver<T> {
        private static final long serialVersionUID = 3786543492451018833L;
        Disposable upstream;

        C4703a(Observer<? super T> assVar) {
            super(assVar);
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            error(th);
        }

        @Override // p110z1.DeferredScalarDisposable, p110z1.Disposable
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
