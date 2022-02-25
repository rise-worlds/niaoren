package com.alipay.apmobilesecuritysdk.p016f;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
import p110z1.C5097cq;
import p110z1.C5128ct;
import p110z1.C5222da;
import p110z1.C5240db;
import p110z1.C5258dd;

/* renamed from: com.alipay.apmobilesecuritysdk.f.a */
/* loaded from: classes.dex */
public class C0640a {
    /* renamed from: a */
    public static String m25350a(Context context, String str, String str2) {
        String a;
        if (context == null || C5097cq.m3699a(str)) {
            return null;
        }
        if (!C5097cq.m3699a(str2)) {
            try {
                a = C5258dd.m3228a(context, str, str2, "");
                if (C5097cq.m3699a(a)) {
                    return null;
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        return C5128ct.m3516b(C5128ct.m3521a(), a);
    }

    /* renamed from: a */
    public static String m25348a(String str, String str2) {
        synchronized (C0640a.class) {
            if (C5097cq.m3699a(str) || C5097cq.m3699a(str2)) {
                return null;
            }
            String a = C5222da.m3364a(str);
            if (C5097cq.m3699a(a)) {
                return null;
            }
            String string = new JSONObject(a).getString(str2);
            if (C5097cq.m3699a(string)) {
                return null;
            }
            return C5128ct.m3516b(C5128ct.m3521a(), string);
        }
    }

    /* renamed from: a */
    public static void m25349a(Context context, String str, String str2, String str3) {
        if (!C5097cq.m3699a(str) && !C5097cq.m3699a(str2) && context != null) {
            try {
                String a = C5128ct.m3519a(C5128ct.m3521a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                C5258dd.m3227a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m25347a(String str, String str2, String str3) {
        synchronized (C0640a.class) {
            if (!C5097cq.m3699a(str) && !C5097cq.m3699a(str2)) {
                try {
                    String a = C5222da.m3364a(str);
                    JSONObject jSONObject = new JSONObject();
                    if (C5097cq.m3695b(a)) {
                        try {
                            jSONObject = new JSONObject(a);
                        } catch (Exception unused) {
                            jSONObject = new JSONObject();
                        }
                    }
                    jSONObject.put(str2, C5128ct.m3519a(C5128ct.m3521a(), str3));
                    jSONObject.toString();
                    try {
                        System.clearProperty(str);
                    } catch (Throwable unused2) {
                    }
                    if (C5240db.m3335a()) {
                        String str4 = ".SystemConfig" + File.separator + str;
                        if (C5240db.m3335a()) {
                            File file = new File(Environment.getExternalStorageDirectory(), str4);
                            if (file.exists() && file.isFile()) {
                                file.delete();
                            }
                        }
                    }
                } catch (Throwable unused3) {
                }
            }
        }
    }
}
