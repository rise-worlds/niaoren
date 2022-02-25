package p110z1;

/* renamed from: z1.ayn */
/* loaded from: classes3.dex */
public final class CompletableToObservable<T> extends Observable<T> {

    /* renamed from: a */
    final CompletableSource f17800a;

    public CompletableToObservable(CompletableSource arsVar) {
        this.f17800a = arsVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f17800a.mo11309a(new C3975a(assVar));
    }

    /* compiled from: CompletableToObservable.java */
    /* renamed from: z1.ayn$a */
    /* loaded from: classes3.dex */
    static final class C3975a extends BasicQueueDisposable<Void> implements CompletableObserver {

        /* renamed from: f */
        final Observer<?> f17801f;

        /* renamed from: g */
        Disposable f17802g;

        /* renamed from: a */
        public Void poll() throws Exception {
            return null;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return true;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return i & 2;
        }

        C3975a(Observer<?> assVar) {
            this.f17801f = assVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f17801f.onComplete();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17801f.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f17802g, atrVar)) {
                this.f17802g = atrVar;
                this.f17801f.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17802g.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17802g.isDisposed();
        }
    }
}
