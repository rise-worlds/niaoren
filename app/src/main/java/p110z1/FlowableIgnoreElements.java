package p110z1;

/* renamed from: z1.bbg */
/* loaded from: classes3.dex */
public final class FlowableIgnoreElements<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableIgnoreElements(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4093a(dbxVar));
    }

    /* compiled from: FlowableIgnoreElements.java */
    /* renamed from: z1.bbg$a */
    /* loaded from: classes3.dex */
    static final class C4093a<T> implements FlowableSubscriber<T>, QueueSubscription<T> {

        /* renamed from: f */
        final Subscriber<? super T> f18133f;

        /* renamed from: g */
        dby f18134g;

        @Override // p110z1.SimpleQueue
        public void clear() {
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return true;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() {
            return null;
        }

        @Override // p110z1.dby
        public void request(long j) {
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return i & 2;
        }

        C4093a(Subscriber<? super T> dbxVar) {
            this.f18133f = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18134g, dbyVar)) {
                this.f18134g = dbyVar;
                this.f18133f.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18133f.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18133f.onComplete();
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // p110z1.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18134g.cancel();
        }
    }
}
