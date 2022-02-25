package p110z1;

/* renamed from: z1.bjf */
/* loaded from: classes3.dex */
public final class ObservableCount<T> extends AbstractObservableWithUpstream<T, Long> {
    public ObservableCount(ObservableSource<T> asqVar) {
        super(asqVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Long> assVar) {
        this.f18807a.subscribe(new C4418a(assVar));
    }

    /* compiled from: ObservableCount.java */
    /* renamed from: z1.bjf$a */
    /* loaded from: classes3.dex */
    static final class C4418a implements Observer<Object>, Disposable {

        /* renamed from: a */
        final Observer<? super Long> f18970a;

        /* renamed from: b */
        Disposable f18971b;

        /* renamed from: c */
        long f18972c;

        C4418a(Observer<? super Long> assVar) {
            this.f18970a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18971b, atrVar)) {
                this.f18971b = atrVar;
                this.f18970a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18971b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18971b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            this.f18972c++;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18970a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18970a.onNext(Long.valueOf(this.f18972c));
            this.f18970a.onComplete();
        }
    }
}
