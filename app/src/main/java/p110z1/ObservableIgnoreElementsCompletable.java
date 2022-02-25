package p110z1;

/* renamed from: z1.bks */
/* loaded from: classes3.dex */
public final class ObservableIgnoreElementsCompletable<T> extends Completable implements FuseToObservable<T> {

    /* renamed from: a */
    final ObservableSource<T> f19169a;

    public ObservableIgnoreElementsCompletable(ObservableSource<T> asqVar) {
        this.f19169a = asqVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        this.f19169a.subscribe(new C4468a(arpVar));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<T> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableIgnoreElements(this.f19169a));
    }

    /* compiled from: ObservableIgnoreElementsCompletable.java */
    /* renamed from: z1.bks$a */
    /* loaded from: classes3.dex */
    static final class C4468a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final CompletableObserver f19170a;

        /* renamed from: b */
        Disposable f19171b;

        @Override // p110z1.Observer
        public void onNext(T t) {
        }

        C4468a(CompletableObserver arpVar) {
            this.f19170a = arpVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19171b = atrVar;
            this.f19170a.onSubscribe(this);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19170a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19170a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19171b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19171b.isDisposed();
        }
    }
}
