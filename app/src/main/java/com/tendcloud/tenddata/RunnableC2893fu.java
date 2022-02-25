package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fu */
/* loaded from: classes2.dex */
class RunnableC2893fu implements Runnable {
    final /* synthetic */ RunnableC2892ft this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2893fu(RunnableC2892ft ftVar) {
        this.this$1 = ftVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.this$1.this$0.m15700a(this.this$1.val$recognizer, this.this$1.val$recognizer.mo15678a(this.this$1.val$recognizer.f13995a.mo15666a(this.this$1.val$recognizer.f13999e, 1000 / this.this$1.val$recognizer.m15675c(), this.this$1.val$recognizer.m15673d())));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
