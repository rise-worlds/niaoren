package p110z1;

/* renamed from: z1.bby */
/* loaded from: classes3.dex */
public final class FlowableNever extends Flowable<Object> {

    /* renamed from: b */
    public static final Flowable<Object> f18202b = new FlowableNever();

    private FlowableNever() {
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super Object> dbxVar) {
        dbxVar.onSubscribe(EmptySubscription.INSTANCE);
    }
}
