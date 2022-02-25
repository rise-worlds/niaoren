package p110z1;

/* renamed from: z1.brg */
/* loaded from: classes3.dex */
public abstract class BasicFuseableSubscriber<T, R> implements FlowableSubscriber<T>, QueueSubscription<R> {

    /* renamed from: j */
    protected final Subscriber<? super R> f19991j;

    /* renamed from: k */
    protected dby f19992k;

    /* renamed from: l */
    protected QueueSubscription<T> f19993l;

    /* renamed from: m */
    protected boolean f19994m;

    /* renamed from: n */
    protected int f19995n;

    /* renamed from: a */
    protected boolean m9472a() {
        return true;
    }

    /* renamed from: b */
    protected void m9469b() {
    }

    public BasicFuseableSubscriber(Subscriber<? super R> dbxVar) {
        this.f19991j = dbxVar;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.f19992k, dbyVar)) {
            this.f19992k = dbyVar;
            if (dbyVar instanceof QueueSubscription) {
                this.f19993l = (QueueSubscription) dbyVar;
            }
            if (m9472a()) {
                this.f19991j.onSubscribe(this);
                m9469b();
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f19994m) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f19994m = true;
        this.f19991j.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m9470a(Throwable th) {
        Exceptions.m9983b(th);
        this.f19992k.cancel();
        onError(th);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f19994m) {
            this.f19994m = true;
            this.f19991j.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final int m9471a(int i) {
        QueueSubscription<T> avtVar = this.f19993l;
        if (avtVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = avtVar.requestFusion(i);
        if (requestFusion != 0) {
            this.f19995n = requestFusion;
        }
        return requestFusion;
    }

    @Override // p110z1.dby
    public void request(long j) {
        this.f19992k.request(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        this.f19992k.cancel();
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return this.f19993l.isEmpty();
    }

    public void clear() {
        this.f19993l.clear();
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
