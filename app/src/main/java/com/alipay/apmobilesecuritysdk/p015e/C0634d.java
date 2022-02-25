package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import com.alipay.apmobilesecuritysdk.p016f.C0640a;
import org.json.JSONObject;
import p110z1.C3877as;
import p110z1.C4814by;
import p110z1.C5097cq;

/* renamed from: com.alipay.apmobilesecuritysdk.e.d */
/* loaded from: classes.dex */
public final class C0634d {
    /* renamed from: a */
    private static C0633c m25404a(String str) {
        try {
            if (C5097cq.m3699a(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new C0633c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString(C4814by.f20407f), jSONObject.optString("tid"), jSONObject.optString(C3877as.f17455g));
        } catch (Exception e) {
            C0623a.m25428a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m25407a() {
        synchronized (C0634d.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m25406a(Context context) {
        synchronized (C0634d.class) {
            C0640a.m25349a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            C0640a.m25347a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m25405a(Context context, C0633c cVar) {
        synchronized (C0634d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", cVar.f225a);
                jSONObject.put("deviceInfoHash", cVar.f226b);
                jSONObject.put(C4814by.f20407f, cVar.f227c);
                jSONObject.put("tid", cVar.f228d);
                jSONObject.put(C3877as.f17455g, cVar.f229e);
                String jSONObject2 = jSONObject.toString();
                C0640a.m25349a(context, "vkeyid_profiles_v4", "key_deviceid_v4", jSONObject2);
                C0640a.m25347a("wxcasxx_v4", "key_wxcasxx_v4", jSONObject2);
            } catch (Exception e) {
                C0623a.m25428a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C0633c m25403b() {
        synchronized (C0634d.class) {
            String a = C0640a.m25348a("wxcasxx_v4", "key_wxcasxx_v4");
            if (C5097cq.m3699a(a)) {
                return null;
            }
            return m25404a(a);
        }
    }

    /* renamed from: b */
    public static synchronized C0633c m25402b(Context context) {
        C0633c a;
        synchronized (C0634d.class) {
            String a2 = C0640a.m25350a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C5097cq.m3699a(a2)) {
                a2 = C0640a.m25348a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            a = m25404a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0633c m25401c(Context context) {
        synchronized (C0634d.class) {
            String a = C0640a.m25350a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C5097cq.m3699a(a)) {
                return null;
            }
            return m25404a(a);
        }
    }
}
