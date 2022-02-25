package p110z1;

/* renamed from: z1.bjo */
/* loaded from: classes3.dex */
public final class ObservableDetach<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableDetach(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4433a(assVar));
    }

    /* compiled from: ObservableDetach.java */
    /* renamed from: z1.bjo$a */
    /* loaded from: classes3.dex */
    static final class C4433a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        Observer<? super T> f19029a;

        /* renamed from: b */
        Disposable f19030b;

        C4433a(Observer<? super T> assVar) {
            this.f19029a = assVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable atrVar = this.f19030b;
            this.f19030b = EmptyComponent.INSTANCE;
            this.f19029a = EmptyComponent.asObserver();
            atrVar.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19030b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19030b, atrVar)) {
                this.f19030b = atrVar;
                this.f19029a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19029a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            Observer<? super T> assVar = this.f19029a;
            this.f19030b = EmptyComponent.INSTANCE;
            this.f19029a = EmptyComponent.asObserver();
            assVar.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            Observer<? super T> assVar = this.f19029a;
            this.f19030b = EmptyComponent.INSTANCE;
            this.f19029a = EmptyComponent.asObserver();
            assVar.onComplete();
        }
    }
}
