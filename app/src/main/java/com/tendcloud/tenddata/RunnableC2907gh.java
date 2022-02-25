package com.tendcloud.tenddata;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gh */
/* loaded from: classes2.dex */
public class RunnableC2907gh implements Runnable {
    final /* synthetic */ C2906gg this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2907gh(C2906gg ggVar) {
        this.this$0 = ggVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Map F = C2836ec.m15892F(C2664ab.f13513g);
            if (F != null) {
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "env";
                hoVar.f14182c = "arp";
                hoVar.f14183d = F;
                hoVar.f14180a = AbstractC2790d.ENV;
                C2858ev.m15778a().post(hoVar);
            }
        } catch (Throwable unused) {
        }
    }
}
