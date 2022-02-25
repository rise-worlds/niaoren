package p110z1;

/* renamed from: z1.bkr */
/* loaded from: classes3.dex */
public final class ObservableIgnoreElements<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableIgnoreElements(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4467a(assVar));
    }

    /* compiled from: ObservableIgnoreElements.java */
    /* renamed from: z1.bkr$a */
    /* loaded from: classes3.dex */
    static final class C4467a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19167a;

        /* renamed from: b */
        Disposable f19168b;

        @Override // p110z1.Observer
        public void onNext(T t) {
        }

        C4467a(Observer<? super T> assVar) {
            this.f19167a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19168b = atrVar;
            this.f19167a.onSubscribe(this);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19167a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19167a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19168b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19168b.isDisposed();
        }
    }
}
