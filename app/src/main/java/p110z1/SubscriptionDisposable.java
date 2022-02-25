package p110z1;

/* renamed from: z1.atx */
/* loaded from: classes3.dex */
final class SubscriptionDisposable extends ReferenceDisposable<dby> {
    private static final long serialVersionUID = -707001650852963139L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriptionDisposable(dby dbyVar) {
        super(dbyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDisposed(@AbstractC3889atm dby dbyVar) {
        dbyVar.cancel();
    }
}
