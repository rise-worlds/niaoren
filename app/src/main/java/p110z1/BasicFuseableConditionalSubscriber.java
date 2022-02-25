package p110z1;

/* renamed from: z1.brf */
/* loaded from: classes3.dex */
public abstract class BasicFuseableConditionalSubscriber<T, R> implements ConditionalSubscriber<T>, QueueSubscription<R> {

    /* renamed from: j */
    protected final ConditionalSubscriber<? super R> f19986j;

    /* renamed from: k */
    protected dby f19987k;

    /* renamed from: l */
    protected QueueSubscription<T> f19988l;

    /* renamed from: m */
    protected boolean f19989m;

    /* renamed from: n */
    protected int f19990n;

    /* renamed from: a */
    protected boolean m9476a() {
        return true;
    }

    /* renamed from: b */
    protected void m9473b() {
    }

    public BasicFuseableConditionalSubscriber(ConditionalSubscriber<? super R> aviVar) {
        this.f19986j = aviVar;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.f19987k, dbyVar)) {
            this.f19987k = dbyVar;
            if (dbyVar instanceof QueueSubscription) {
                this.f19988l = (QueueSubscription) dbyVar;
            }
            if (m9476a()) {
                this.f19986j.onSubscribe(this);
                m9473b();
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f19989m) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f19989m = true;
        this.f19986j.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m9474a(Throwable th) {
        Exceptions.m9983b(th);
        this.f19987k.cancel();
        onError(th);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f19989m) {
            this.f19989m = true;
            this.f19986j.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final int m9475a(int i) {
        QueueSubscription<T> avtVar = this.f19988l;
        if (avtVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = avtVar.requestFusion(i);
        if (requestFusion != 0) {
            this.f19990n = requestFusion;
        }
        return requestFusion;
    }

    @Override // p110z1.dby
    public void request(long j) {
        this.f19987k.request(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        this.f19987k.cancel();
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return this.f19988l.isEmpty();
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        this.f19988l.clear();
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
