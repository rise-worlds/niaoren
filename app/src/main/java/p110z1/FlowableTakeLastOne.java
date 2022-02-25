package p110z1;

/* renamed from: z1.bds */
/* loaded from: classes3.dex */
public final class FlowableTakeLastOne<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableTakeLastOne(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4210a(dbxVar));
    }

    /* compiled from: FlowableTakeLastOne.java */
    /* renamed from: z1.bds$a */
    /* loaded from: classes3.dex */
    static final class C4210a<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -5467847744262967226L;
        dby upstream;

        C4210a(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.value = t;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.value = null;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            T t = this.value;
            if (t != null) {
                complete(t);
            } else {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
