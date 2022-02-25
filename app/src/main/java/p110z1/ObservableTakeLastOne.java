package p110z1;

/* renamed from: z1.bmu */
/* loaded from: classes3.dex */
public final class ObservableTakeLastOne<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableTakeLastOne(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4569a(assVar));
    }

    /* compiled from: ObservableTakeLastOne.java */
    /* renamed from: z1.bmu$a */
    /* loaded from: classes3.dex */
    static final class C4569a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19419a;

        /* renamed from: b */
        Disposable f19420b;

        /* renamed from: c */
        T f19421c;

        C4569a(Observer<? super T> assVar) {
            this.f19419a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19420b, atrVar)) {
                this.f19420b = atrVar;
                this.f19419a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19421c = t;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19421c = null;
            this.f19419a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            m9574a();
        }

        /* renamed from: a */
        void m9574a() {
            T t = this.f19421c;
            if (t != null) {
                this.f19421c = null;
                this.f19419a.onNext(t);
            }
            this.f19419a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19421c = null;
            this.f19420b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19420b.isDisposed();
        }
    }
}
