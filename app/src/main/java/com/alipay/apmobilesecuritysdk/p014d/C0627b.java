package com.alipay.apmobilesecuritysdk.p014d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015e.C0638h;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import java.util.HashMap;
import java.util.Map;
import p110z1.C3877as;
import p110z1.C5097cq;

/* renamed from: com.alipay.apmobilesecuritysdk.d.b */
/* loaded from: classes.dex */
public final class C0627b {
    /* renamed from: a */
    public static synchronized Map<String, String> m25423a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C0627b.class) {
            hashMap = new HashMap();
            String a = C5097cq.m3696a(map, "tid", "");
            String a2 = C5097cq.m3696a(map, C3877as.f17455g, "");
            String a3 = C5097cq.m3696a(map, ChooseTypeAndAccountActivity.KEY_USER_ID, "");
            String a4 = C5097cq.m3696a(map, "appName", "");
            String a5 = C5097cq.m3696a(map, "appKeyClient", "");
            String a6 = C5097cq.m3696a(map, "tmxSessionId", "");
            String f = C0638h.m25372f(context);
            String a7 = C5097cq.m3696a(map, "sessionId", "");
            hashMap.put("AC1", a);
            hashMap.put("AC2", a2);
            hashMap.put("AC3", "");
            hashMap.put("AC4", f);
            hashMap.put("AC5", a3);
            hashMap.put("AC6", a6);
            hashMap.put("AC7", "");
            hashMap.put("AC8", a4);
            hashMap.put("AC9", a5);
            if (C5097cq.m3695b(a7)) {
                hashMap.put("AC10", a7);
            }
        }
        return hashMap;
    }
}
