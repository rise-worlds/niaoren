package p110z1;

/* renamed from: z1.bmk */
/* loaded from: classes3.dex */
public final class ObservableSkip<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19376b;

    public ObservableSkip(ObservableSource<T> asqVar, long j) {
        super(asqVar);
        this.f19376b = j;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4556a(assVar, this.f19376b));
    }

    /* compiled from: ObservableSkip.java */
    /* renamed from: z1.bmk$a */
    /* loaded from: classes3.dex */
    static final class C4556a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19377a;

        /* renamed from: b */
        long f19378b;

        /* renamed from: c */
        Disposable f19379c;

        C4556a(Observer<? super T> assVar, long j) {
            this.f19377a = assVar;
            this.f19378b = j;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19379c, atrVar)) {
                this.f19379c = atrVar;
                this.f19377a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = this.f19378b;
            if (j != 0) {
                this.f19378b = j - 1;
            } else {
                this.f19377a.onNext(t);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19377a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19377a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19379c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19379c.isDisposed();
        }
    }
}
