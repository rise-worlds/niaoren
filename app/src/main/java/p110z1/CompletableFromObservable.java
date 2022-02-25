package p110z1;

/* renamed from: z1.axq */
/* loaded from: classes3.dex */
public final class CompletableFromObservable<T> extends Completable {

    /* renamed from: a */
    final ObservableSource<T> f17724a;

    public CompletableFromObservable(ObservableSource<T> asqVar) {
        this.f17724a = asqVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17724a.subscribe(new C3954a(arpVar));
    }

    /* compiled from: CompletableFromObservable.java */
    /* renamed from: z1.axq$a */
    /* loaded from: classes3.dex */
    static final class C3954a<T> implements Observer<T> {

        /* renamed from: a */
        final CompletableObserver f17725a;

        @Override // p110z1.Observer
        public void onNext(T t) {
        }

        C3954a(CompletableObserver arpVar) {
            this.f17725a = arpVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f17725a.onSubscribe(atrVar);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f17725a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f17725a.onComplete();
        }
    }
}
