package com.tendcloud.tenddata;

import org.apache.tools.ant.taskdefs.WaitFor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hi */
/* loaded from: classes2.dex */
public class RunnableC2940hi implements Runnable {
    final /* synthetic */ C2939hh this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2940hi(C2939hh hhVar) {
        this.this$0 = hhVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.this$0.f14148b = System.currentTimeMillis();
            if (this.this$0.f14150d != this.this$0.f14151e && this.this$0.f14150d > 1 && this.this$0.f14148b - this.this$0.f14149c > WaitFor.DEFAULT_MAX_WAIT_MILLIS) {
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "env";
                hoVar.f14182c = "cellUpdate";
                hoVar.f14180a = AbstractC2790d.ENV;
                C2858ev.m15778a().post(hoVar);
                this.this$0.f14149c = this.this$0.f14148b;
                this.this$0.f14151e = this.this$0.f14150d;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
