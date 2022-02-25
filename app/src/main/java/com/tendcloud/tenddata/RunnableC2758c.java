package com.tendcloud.tenddata;

import com.talkingdata.sdk.TDAntiCheatingService;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.c */
/* loaded from: classes2.dex */
public class RunnableC2758c implements Runnable {
    final /* synthetic */ TDAntiCheatingService.C2537a this$1;

    public RunnableC2758c(TDAntiCheatingService.C2537a aVar) {
        this.this$1 = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C2943hl hlVar = new C2943hl();
            hlVar.f14173m.put("eventType", 16);
            hlVar.f14173m.put("regAppsMap", TDAntiCheatingService.this.f12739c);
            C2858ev.m15778a().post(hlVar);
        } catch (Throwable unused) {
        }
    }
}
