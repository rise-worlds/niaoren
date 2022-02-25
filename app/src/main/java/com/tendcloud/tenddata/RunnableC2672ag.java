package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ag */
/* loaded from: classes2.dex */
class RunnableC2672ag implements Runnable {
    final /* synthetic */ C3034zz this$0;
    final /* synthetic */ boolean val$enabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2672ag(C3034zz zzVar, boolean z) {
        this.this$0 = zzVar;
        this.val$enabled = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C2685at.m16299a(this.val$enabled);
        } catch (Throwable unused) {
        }
    }
}
