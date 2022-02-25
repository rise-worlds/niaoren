package com.alipay.apmobilesecuritysdk.p014d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0624b;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.HashMap;
import java.util.Map;
import p110z1.C5170cw;
import p110z1.C5194cy;

/* renamed from: com.alipay.apmobilesecuritysdk.d.d */
/* loaded from: classes.dex */
public final class C0629d {
    /* renamed from: a */
    public static synchronized Map<String, String> m25421a() {
        HashMap hashMap;
        synchronized (C0629d.class) {
            hashMap = new HashMap();
            try {
                new C0624b();
                hashMap.put("AE16", "");
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m25420a(Context context) {
        HashMap hashMap;
        synchronized (C0629d.class) {
            C5194cy.m3467a();
            C5170cw.m3512a();
            hashMap = new HashMap();
            hashMap.put("AE1", C5194cy.m3464b());
            StringBuilder sb = new StringBuilder();
            sb.append(C5194cy.m3463c() ? "1" : ResultTypeConstant.f7213z);
            hashMap.put("AE2", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(C5194cy.m3466a(context) ? "1" : ResultTypeConstant.f7213z);
            hashMap.put("AE3", sb2.toString());
            hashMap.put("AE4", C5194cy.m3462d());
            hashMap.put("AE5", C5194cy.m3461e());
            hashMap.put("AE6", C5194cy.m3460f());
            hashMap.put("AE7", C5194cy.m3459g());
            hashMap.put("AE8", C5194cy.m3458h());
            hashMap.put("AE9", C5194cy.m3457i());
            hashMap.put("AE10", C5194cy.m3456j());
            hashMap.put("AE11", C5194cy.m3455k());
            hashMap.put("AE12", C5194cy.m3454l());
            hashMap.put("AE13", C5194cy.m3453m());
            hashMap.put("AE14", C5194cy.m3452n());
            hashMap.put("AE15", C5194cy.m3451o());
            hashMap.put("AE21", C5170cw.m3499g());
        }
        return hashMap;
    }
}
