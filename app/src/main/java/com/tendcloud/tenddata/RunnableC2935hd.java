package com.tendcloud.tenddata;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hd */
/* loaded from: classes2.dex */
public final class RunnableC2935hd implements Runnable {
    final /* synthetic */ Map val$data;
    final /* synthetic */ boolean val$sendStatusSuccess;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2935hd(Map map, boolean z) {
        this.val$data = map;
        this.val$sendStatusSuccess = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean b;
        try {
            if (this.val$data != null && !this.val$data.isEmpty()) {
                b = C2933hb.m15521b(this.val$sendStatusSuccess, String.valueOf(this.val$data.get("targetUrl")));
                if (b) {
                    C2811dq.dForInternal(this.val$data.toString());
                    C2947ho hoVar = new C2947ho();
                    hoVar.f14181b = "sdk";
                    hoVar.f14182c = this.val$sendStatusSuccess ? "send_ok" : "send_fail";
                    hoVar.f14183d = this.val$data;
                    hoVar.f14180a = AbstractC2790d.ENV;
                    C2858ev.m15778a().post(hoVar);
                }
            }
        } catch (Throwable unused) {
        }
    }
}
