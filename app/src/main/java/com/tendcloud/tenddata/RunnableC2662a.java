package com.tendcloud.tenddata;

import com.talkingdata.sdk.TDAntiCheatingService;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.a */
/* loaded from: classes2.dex */
public class RunnableC2662a implements Runnable {
    final /* synthetic */ TDAntiCheatingService this$0;

    public RunnableC2662a(TDAntiCheatingService tDAntiCheatingService) {
        this.this$0 = tDAntiCheatingService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C2943hl hlVar = new C2943hl();
            hlVar.f14173m.put("eventType", 11);
            C2858ev.m15778a().post(hlVar);
        } catch (Throwable unused) {
        }
    }
}
