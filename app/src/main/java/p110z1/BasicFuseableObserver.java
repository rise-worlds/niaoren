package p110z1;

/* renamed from: z1.avx */
/* loaded from: classes3.dex */
public abstract class BasicFuseableObserver<T, R> implements Observer<T>, QueueDisposable<R> {

    /* renamed from: f */
    protected final Observer<? super R> f17602f;

    /* renamed from: g */
    protected Disposable f17603g;

    /* renamed from: h */
    protected QueueDisposable<T> f17604h;

    /* renamed from: i */
    protected boolean f17605i;

    /* renamed from: j */
    protected int f17606j;

    /* renamed from: a */
    protected boolean m9870a() {
        return true;
    }

    /* renamed from: b */
    protected void m9867b() {
    }

    public BasicFuseableObserver(Observer<? super R> assVar) {
        this.f17602f = assVar;
    }

    @Override // p110z1.Observer
    public final void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.validate(this.f17603g, atrVar)) {
            this.f17603g = atrVar;
            if (atrVar instanceof QueueDisposable) {
                this.f17604h = (QueueDisposable) atrVar;
            }
            if (m9870a()) {
                this.f17602f.onSubscribe(this);
                m9867b();
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        if (this.f17605i) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f17605i = true;
        this.f17602f.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m9868a(Throwable th) {
        Exceptions.m9983b(th);
        this.f17603g.dispose();
        onError(th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f17605i) {
            this.f17605i = true;
            this.f17602f.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final int m9869a(int i) {
        QueueDisposable<T> avrVar = this.f17604h;
        if (avrVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = avrVar.requestFusion(i);
        if (requestFusion != 0) {
            this.f17606j = requestFusion;
        }
        return requestFusion;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        this.f17603g.dispose();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f17603g.isDisposed();
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return this.f17604h.isEmpty();
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        this.f17604h.clear();
    }

    @Override // p110z1.SimpleQueue
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p110z1.SimpleQueue
    public final boolean offer(R r, R r2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
