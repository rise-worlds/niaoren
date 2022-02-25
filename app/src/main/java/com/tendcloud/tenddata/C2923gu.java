package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gu */
/* loaded from: classes2.dex */
final class C2923gu implements AbstractC2978ik {
    final /* synthetic */ AbstractC2790d val$features;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2923gu(AbstractC2790d dVar) {
        this.val$features = dVar;
    }

    @Override // com.tendcloud.tenddata.AbstractC2978ik
    public void onStoreSuccess() {
        try {
            C2812dr.m16028a(false, this.val$features);
        } catch (Throwable unused) {
        }
    }

    @Override // com.tendcloud.tenddata.AbstractC2978ik
    public void onStoreFailed() {
        try {
            C2933hb.postSDKError(new Exception("init event store failed"));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
