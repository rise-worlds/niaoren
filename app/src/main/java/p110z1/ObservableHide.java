package p110z1;

/* renamed from: z1.bkq */
/* loaded from: classes3.dex */
public final class ObservableHide<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableHide(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4466a(assVar));
    }

    /* compiled from: ObservableHide.java */
    /* renamed from: z1.bkq$a */
    /* loaded from: classes3.dex */
    static final class C4466a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19165a;

        /* renamed from: b */
        Disposable f19166b;

        C4466a(Observer<? super T> assVar) {
            this.f19165a = assVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19166b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19166b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19166b, atrVar)) {
                this.f19166b = atrVar;
                this.f19165a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19165a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19165a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19165a.onComplete();
        }
    }
}
