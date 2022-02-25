package com.alipay.apmobilesecuritysdk.p014d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import com.alipay.apmobilesecuritysdk.p015e.C0635e;
import com.alipay.apmobilesecuritysdk.p015e.C0636f;
import com.alipay.apmobilesecuritysdk.p016f.C0640a;
import java.util.HashMap;
import java.util.Map;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.json.JSONObject;
import p110z1.C5097cq;
import p110z1.C5170cw;

/* renamed from: com.alipay.apmobilesecuritysdk.d.c */
/* loaded from: classes.dex */
public final class C0628c {
    /* renamed from: a */
    public static Map<String, String> m25422a(Context context) {
        C5170cw a = C5170cw.m3512a();
        HashMap hashMap = new HashMap();
        C0636f a2 = C0635e.m25400a(context);
        String a3 = C5170cw.m3511a(context);
        String b = C5170cw.m3508b(context);
        String k = C5170cw.m3490k(context);
        String m = C5170cw.m3486m(context);
        if (a2 != null) {
            if (C5097cq.m3699a(a3)) {
                a3 = a2.m25399a();
            }
            if (C5097cq.m3699a(b)) {
                b = a2.m25397b();
            }
            if (C5097cq.m3699a(k)) {
                k = a2.m25395c();
            }
            if (C5097cq.m3699a(m)) {
                m = a2.m25391e();
            }
        }
        C0636f fVar = new C0636f(a3, b, k, "", m);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", fVar.m25399a());
                jSONObject.put("imsi", fVar.m25397b());
                jSONObject.put(C3209Os.FAMILY_MAC, fVar.m25395c());
                jSONObject.put("bluetoothmac", fVar.m25393d());
                jSONObject.put("gsi", fVar.m25391e());
                String jSONObject2 = jSONObject.toString();
                C0640a.m25347a("device_feature_file_name", "device_feature_file_key", jSONObject2);
                C0640a.m25349a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e) {
                C0623a.m25428a(e);
            }
        }
        hashMap.put("AD1", a3);
        hashMap.put("AD2", b);
        hashMap.put("AD3", C5170cw.m3500f(context));
        hashMap.put("AD5", C5170cw.m3496h(context));
        hashMap.put("AD6", C5170cw.m3494i(context));
        hashMap.put("AD7", C5170cw.m3492j(context));
        hashMap.put("AD8", k);
        hashMap.put("AD9", C5170cw.m3488l(context));
        hashMap.put("AD10", m);
        hashMap.put("AD11", C5170cw.m3505d());
        hashMap.put("AD12", a.m3503e());
        hashMap.put("AD13", C5170cw.m3501f());
        hashMap.put("AD14", C5170cw.m3497h());
        hashMap.put("AD15", C5170cw.m3495i());
        hashMap.put("AD16", C5170cw.m3493j());
        hashMap.put("AD17", "");
        hashMap.put("AD19", C5170cw.m3484n(context));
        hashMap.put("AD20", C5170cw.m3491k());
        hashMap.put("AD22", "");
        hashMap.put("AD23", C5170cw.m3480p(context));
        hashMap.put("AD24", C5097cq.m3689g(C5170cw.m3498g(context)));
        hashMap.put("AD26", C5170cw.m3502e(context));
        hashMap.put("AD27", C5170cw.m3481p());
        hashMap.put("AD28", C5170cw.m3477r());
        hashMap.put("AD29", C5170cw.m3473t());
        hashMap.put("AD30", C5170cw.m3479q());
        hashMap.put("AD31", C5170cw.m3475s());
        hashMap.put("AD32", C5170cw.m3485n());
        hashMap.put("AD33", C5170cw.m3483o());
        hashMap.put("AD34", C5170cw.m3476r(context));
        hashMap.put("AD35", C5170cw.m3474s(context));
        hashMap.put("AD36", C5170cw.m3478q(context));
        hashMap.put("AD37", C5170cw.m3487m());
        hashMap.put("AD38", C5170cw.m3489l());
        hashMap.put("AD39", C5170cw.m3506c(context));
        hashMap.put("AD40", C5170cw.m3504d(context));
        hashMap.put("AD41", C5170cw.m3509b());
        hashMap.put("AD42", C5170cw.m3507c());
        hashMap.put("AL3", C5170cw.m3482o(context));
        return hashMap;
    }
}
