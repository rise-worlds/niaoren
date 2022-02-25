package p110z1;

/* renamed from: z1.bde */
/* loaded from: classes3.dex */
public final class FlowableSerialized<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableSerialized(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new SerializedSubscriber(dbxVar));
    }
}
