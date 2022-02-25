package p110z1;

/* renamed from: z1.azt */
/* loaded from: classes3.dex */
public final class FlowableCount<T> extends AbstractFlowableWithUpstream<T, Long> {
    public FlowableCount(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Long> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4024a(dbxVar));
    }

    /* compiled from: FlowableCount.java */
    /* renamed from: z1.azt$a */
    /* loaded from: classes3.dex */
    static final class C4024a extends DeferredScalarSubscription<Long> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        dby upstream;

        C4024a(Subscriber<? super Long> dbxVar) {
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
        public void onNext(Object obj) {
            this.count++;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
