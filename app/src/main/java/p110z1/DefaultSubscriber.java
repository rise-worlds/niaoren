package p110z1;

/* renamed from: z1.bvb */
/* loaded from: classes3.dex */
public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {

    /* renamed from: b */
    dby f20334b;

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (EndConsumerHelper.m9434a(this.f20334b, dbyVar, getClass())) {
            this.f20334b = dbyVar;
            m8930c();
        }
    }

    /* renamed from: a */
    protected final void m8932a(long j) {
        dby dbyVar = this.f20334b;
        if (dbyVar != null) {
            dbyVar.request(j);
        }
    }

    /* renamed from: b */
    protected final void m8931b() {
        dby dbyVar = this.f20334b;
        this.f20334b = SubscriptionHelper.CANCELLED;
        dbyVar.cancel();
    }

    /* renamed from: c */
    protected void m8930c() {
        m8932a(cjm.f20759b);
    }
}
