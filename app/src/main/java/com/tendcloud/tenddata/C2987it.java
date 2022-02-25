package com.tendcloud.tenddata;

import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.stripe.android.model.SourceCardData;
import org.json.JSONArray;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.it */
/* loaded from: classes2.dex */
public class C2987it extends AbstractC2984iq {
    public C2987it() {
        m15410a("manufacture", C2821dv.m15936c());
        m15410a(SourceCardData.f12157g, C2821dv.m15933d());
        m15410a(FileDownloadBroadcastHandler.f10432b, C2821dv.m15931e());
        JSONArray jSONArray = new JSONArray();
        for (String str : C2821dv.m15915m()) {
            jSONArray.put(str);
        }
        m15410a("cpuInfo", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        for (int i : C2821dv.m15905r()) {
            jSONArray2.put(i);
        }
        m15410a("memoryInfo", jSONArray2);
        JSONArray jSONArray3 = new JSONArray();
        for (int i2 : C2821dv.m15907q()) {
            jSONArray3.put(i2);
        }
        m15410a("sdCardInfo", jSONArray3);
        C2821dv.m15943a(C2664ab.f13513g, this.f14268b);
        C2821dv.m15938b(C2664ab.f13513g, this.f14268b);
        m15410a("totalDiskSpace", Integer.valueOf(m15403b()));
        m15410a("support", C2821dv.m15928f(C2664ab.f13513g));
        m15410a("cpu", C2821dv.m15913n());
        m15410a("nfcHce", C2821dv.m15945a(C2664ab.f13513g));
    }

    /* renamed from: b */
    public static int m15403b() {
        try {
            int[] s = C2821dv.m15903s();
            return s[0] + s[2];
        } catch (Throwable unused) {
            return 0;
        }
    }

    public void setSlots(int i) {
        m15410a("slots", Integer.valueOf(i));
    }

    /* renamed from: c */
    public void m15402c() {
        try {
            m15410a("support", (Object) C2821dv.m15928f(C2664ab.f13513g));
        } catch (Throwable unused) {
        }
    }
}
