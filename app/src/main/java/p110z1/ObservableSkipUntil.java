package p110z1;

/* renamed from: z1.bmn */
/* loaded from: classes3.dex */
public final class ObservableSkipUntil<T, U> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final ObservableSource<U> f19386b;

    public ObservableSkipUntil(ObservableSource<T> asqVar, ObservableSource<U> asqVar2) {
        super(asqVar);
        this.f19386b = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SerializedObserver btyVar = new SerializedObserver(assVar);
        ArrayCompositeDisposable auyVar = new ArrayCompositeDisposable(2);
        btyVar.onSubscribe(auyVar);
        C4560b bVar = new C4560b(btyVar, auyVar);
        this.f19386b.subscribe(new C4559a(auyVar, bVar, btyVar));
        this.f18807a.subscribe(bVar);
    }

    /* compiled from: ObservableSkipUntil.java */
    /* renamed from: z1.bmn$b */
    /* loaded from: classes3.dex */
    static final class C4560b<T> implements Observer<T> {

        /* renamed from: a */
        final Observer<? super T> f19392a;

        /* renamed from: b */
        final ArrayCompositeDisposable f19393b;

        /* renamed from: c */
        Disposable f19394c;

        /* renamed from: d */
        volatile boolean f19395d;

        /* renamed from: e */
        boolean f19396e;

        C4560b(Observer<? super T> assVar, ArrayCompositeDisposable auyVar) {
            this.f19392a = assVar;
            this.f19393b = auyVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19394c, atrVar)) {
                this.f19394c = atrVar;
                this.f19393b.setResource(0, atrVar);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.f19396e) {
                this.f19392a.onNext(t);
            } else if (this.f19395d) {
                this.f19396e = true;
                this.f19392a.onNext(t);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19393b.dispose();
            this.f19392a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19393b.dispose();
            this.f19392a.onComplete();
        }
    }

    /* compiled from: ObservableSkipUntil.java */
    /* renamed from: z1.bmn$a */
    /* loaded from: classes3.dex */
    final class C4559a implements Observer<U> {

        /* renamed from: a */
        final ArrayCompositeDisposable f19387a;

        /* renamed from: b */
        final C4560b<T> f19388b;

        /* renamed from: c */
        final SerializedObserver<T> f19389c;

        /* renamed from: d */
        Disposable f19390d;

        C4559a(ArrayCompositeDisposable auyVar, C4560b<T> bVar, SerializedObserver<T> btyVar) {
            this.f19387a = auyVar;
            this.f19388b = bVar;
            this.f19389c = btyVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19390d, atrVar)) {
                this.f19390d = atrVar;
                this.f19387a.setResource(1, atrVar);
            }
        }

        @Override // p110z1.Observer
        public void onNext(U u) {
            this.f19390d.dispose();
            this.f19388b.f19395d = true;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19387a.dispose();
            this.f19389c.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19388b.f19395d = true;
        }
    }
}
