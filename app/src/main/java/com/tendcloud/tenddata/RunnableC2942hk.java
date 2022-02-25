package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hk */
/* loaded from: classes2.dex */
class RunnableC2942hk implements Runnable {
    final /* synthetic */ C2941hj this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2942hk(C2941hj hjVar) {
        this.this$0 = hjVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        C2880fk b;
        C2880fk c;
        C2880fk c2;
        try {
            this.this$0.f14157f = System.currentTimeMillis();
            long j2 = this.this$0.f14157f - this.this$0.f14158g;
            j = this.this$0.f14159h;
            if (j2 > j) {
                this.this$0.f14158g = this.this$0.f14157f;
                C2941hj hjVar = this.this$0;
                b = this.this$0.m15508b();
                hjVar.f14155d = b;
                if (this.this$0.f14155d == null) {
                    this.this$0.m15511a();
                    C2941hj hjVar2 = this.this$0;
                    c2 = this.this$0.m15506c();
                    hjVar2.f14155d = c2;
                }
                C2941hj hjVar3 = this.this$0;
                c = this.this$0.m15506c();
                hjVar3.f14156e = c;
                if (this.this$0.f14155d != null && this.this$0.f14156e != null && this.this$0.f14152a.m15714a(this.this$0.f14155d, this.this$0.f14156e) < 0.8d) {
                    this.this$0.m15511a();
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
