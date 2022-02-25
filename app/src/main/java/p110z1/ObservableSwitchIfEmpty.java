package p110z1;

/* renamed from: z1.bmq */
/* loaded from: classes3.dex */
public final class ObservableSwitchIfEmpty<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final ObservableSource<? extends T> f19405b;

    public ObservableSwitchIfEmpty(ObservableSource<T> asqVar, ObservableSource<? extends T> asqVar2) {
        super(asqVar);
        this.f19405b = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        C4564a aVar = new C4564a(assVar, this.f19405b);
        assVar.onSubscribe(aVar.f19408c);
        this.f18807a.subscribe(aVar);
    }

    /* compiled from: ObservableSwitchIfEmpty.java */
    /* renamed from: z1.bmq$a */
    /* loaded from: classes3.dex */
    static final class C4564a<T> implements Observer<T> {

        /* renamed from: a */
        final Observer<? super T> f19406a;

        /* renamed from: b */
        final ObservableSource<? extends T> f19407b;

        /* renamed from: d */
        boolean f19409d = true;

        /* renamed from: c */
        final SequentialDisposable f19408c = new SequentialDisposable();

        C4564a(Observer<? super T> assVar, ObservableSource<? extends T> asqVar) {
            this.f19406a = assVar;
            this.f19407b = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19408c.update(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.f19409d) {
                this.f19409d = false;
            }
            this.f19406a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19406a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.f19409d) {
                this.f19409d = false;
                this.f19407b.subscribe(this);
                return;
            }
            this.f19406a.onComplete();
        }
    }
}
