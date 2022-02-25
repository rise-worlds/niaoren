package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import com.alipay.apmobilesecuritysdk.p016f.C0640a;
import org.json.JSONObject;
import p110z1.C4814by;
import p110z1.C5097cq;

/* renamed from: com.alipay.apmobilesecuritysdk.e.a */
/* loaded from: classes.dex */
public final class C0631a {
    /* renamed from: a */
    private static C0632b m25411a(String str) {
        try {
            if (C5097cq.m3699a(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new C0632b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString(C4814by.f20407f));
        } catch (Exception e) {
            C0623a.m25428a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m25414a() {
        synchronized (C0631a.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m25413a(Context context) {
        synchronized (C0631a.class) {
            C0640a.m25349a(context, "vkeyid_profiles_v3", "deviceid", "");
            C0640a.m25347a("wxcasxx_v3", "wxcasxx", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m25412a(Context context, C0632b bVar) {
        synchronized (C0631a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", bVar.f222a);
                jSONObject.put("deviceInfoHash", bVar.f223b);
                jSONObject.put(C4814by.f20407f, bVar.f224c);
                String jSONObject2 = jSONObject.toString();
                C0640a.m25349a(context, "vkeyid_profiles_v3", "deviceid", jSONObject2);
                C0640a.m25347a("wxcasxx_v3", "wxcasxx", jSONObject2);
            } catch (Exception e) {
                C0623a.m25428a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C0632b m25410b() {
        synchronized (C0631a.class) {
            String a = C0640a.m25348a("wxcasxx_v3", "wxcasxx");
            if (C5097cq.m3699a(a)) {
                return null;
            }
            return m25411a(a);
        }
    }

    /* renamed from: b */
    public static synchronized C0632b m25409b(Context context) {
        C0632b a;
        synchronized (C0631a.class) {
            String a2 = C0640a.m25350a(context, "vkeyid_profiles_v3", "deviceid");
            if (C5097cq.m3699a(a2)) {
                a2 = C0640a.m25348a("wxcasxx_v3", "wxcasxx");
            }
            a = m25411a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0632b m25408c(Context context) {
        synchronized (C0631a.class) {
            String a = C0640a.m25350a(context, "vkeyid_profiles_v3", "deviceid");
            if (C5097cq.m3699a(a)) {
                return null;
            }
            return m25411a(a);
        }
    }
}
