package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import android.content.SharedPreferences;
import p110z1.C5097cq;
import p110z1.C5128ct;
import p110z1.C5258dd;

/* renamed from: com.alipay.apmobilesecuritysdk.e.g */
/* loaded from: classes.dex */
public final class C0637g {
    /* renamed from: a */
    public static synchronized String m25387a(Context context, String str) {
        synchronized (C0637g.class) {
            String a = C5258dd.m3228a(context, "openapi_file_pri", "openApi" + str, "");
            if (C5097cq.m3699a(a)) {
                return "";
            }
            String b = C5128ct.m3516b(C5128ct.m3521a(), a);
            return C5097cq.m3699a(b) ? "" : b;
        }
    }

    /* renamed from: a */
    public static synchronized void m25389a() {
        synchronized (C0637g.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m25388a(Context context) {
        synchronized (C0637g.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m25386a(Context context, String str, String str2) {
        synchronized (C0637g.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, C5128ct.m3519a(C5128ct.m3521a(), str2));
                    edit.commit();
                }
            } catch (Throwable unused) {
            }
        }
    }
}
