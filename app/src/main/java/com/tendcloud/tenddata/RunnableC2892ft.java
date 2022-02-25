package com.tendcloud.tenddata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ft */
/* loaded from: classes2.dex */
public class RunnableC2892ft implements Runnable {
    final /* synthetic */ C2890fr this$0;
    final /* synthetic */ AbstractC2894fv val$recognizer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2892ft(C2890fr frVar, AbstractC2894fv fvVar) {
        this.this$0 = frVar;
        this.val$recognizer = fvVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        C2897fy fyVar;
        boolean z2;
        C2897fy fyVar2;
        try {
            if (this.val$recognizer.f13999e[this.val$recognizer.f13996b] == null) {
                this.val$recognizer.f13999e[this.val$recognizer.f13996b] = new C2897fy();
                C2897fy fyVar3 = this.val$recognizer.f13999e[this.val$recognizer.f13996b];
                fyVar2 = this.this$0.f13991f;
                fyVar3.clone(fyVar2);
                C2904ge.calculateWorldAcce(this.val$recognizer.f13999e[this.val$recognizer.f13996b]);
                this.val$recognizer.f13996b++;
                if (this.val$recognizer.f13996b == this.val$recognizer.m15673d() / 2) {
                    this.val$recognizer.f13996b = 0;
                }
            } else {
                if (this.val$recognizer.f13999e[(this.val$recognizer.m15673d() / 2) + this.val$recognizer.f13996b] != null) {
                    this.val$recognizer.f13999e[this.val$recognizer.f13996b].clone(this.val$recognizer.f13999e[(this.val$recognizer.m15673d() / 2) + this.val$recognizer.f13996b]);
                }
                this.val$recognizer.f13999e[(this.val$recognizer.m15673d() / 2) + this.val$recognizer.f13996b] = new C2897fy();
                C2897fy fyVar4 = this.val$recognizer.f13999e[(this.val$recognizer.m15673d() / 2) + this.val$recognizer.f13996b];
                fyVar = this.this$0.f13991f;
                fyVar4.clone(fyVar);
                C2904ge.calculateWorldAcce(this.val$recognizer.f13999e[(this.val$recognizer.m15673d() / 2) + this.val$recognizer.f13996b]);
                if (this.val$recognizer.f13996b == (this.val$recognizer.m15673d() / 2) - 1) {
                    this.val$recognizer.f13998d.post(new RunnableC2893fu(this));
                }
                this.val$recognizer.f13996b = (this.val$recognizer.f13996b + 1) % (this.val$recognizer.m15673d() / 2);
                z2 = this.this$0.f13992g;
                if (!z2) {
                    this.val$recognizer.m15681a();
                }
            }
            z = this.this$0.f13992g;
            if (z) {
                this.val$recognizer.f13997c.postDelayed(this, this.val$recognizer.m15675c());
            } else {
                this.val$recognizer.m15681a();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
