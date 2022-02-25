package p110z1;

/* renamed from: z1.bjs */
/* loaded from: classes3.dex */
public final class ObservableDoFinally<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Action f19043b;

    public ObservableDoFinally(ObservableSource<T> asqVar, Action augVar) {
        super(asqVar);
        this.f19043b = augVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4437a(assVar, this.f19043b));
    }

    /* compiled from: ObservableDoFinally.java */
    /* renamed from: z1.bjs$a */
    /* loaded from: classes3.dex */
    static final class C4437a<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Observer<? super T> downstream;
        final Action onFinally;

        /* renamed from: qd */
        QueueDisposable<T> f19044qd;
        boolean syncFused;
        Disposable upstream;

        C4437a(Observer<? super T> assVar, Action augVar) {
            this.downstream = assVar;
            this.onFinally = augVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                if (atrVar instanceof QueueDisposable) {
                    this.f19044qd = (QueueDisposable) atrVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            QueueDisposable<T> avrVar = this.f19044qd;
            if (avrVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = avrVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f19044qd.clear();
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f19044qd.isEmpty();
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll = this.f19044qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
