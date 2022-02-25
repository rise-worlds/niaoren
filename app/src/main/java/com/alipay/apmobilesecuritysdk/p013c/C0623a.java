package com.alipay.apmobilesecuritysdk.p013c;

import android.content.Context;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import p110z1.C5260df;
import p110z1.C5263di;

/* renamed from: com.alipay.apmobilesecuritysdk.c.a */
/* loaded from: classes.dex */
public final class C0623a {
    /* renamed from: a */
    public static synchronized void m25430a(Context context, String str, String str2, String str3) {
        synchronized (C0623a.class) {
            C5260df b = m25427b(context, str, str2, str3);
            C5263di.m3220a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", b.toString());
        }
    }

    /* renamed from: a */
    public static synchronized void m25429a(String str) {
        synchronized (C0623a.class) {
            C5263di.m3221a(str);
        }
    }

    /* renamed from: a */
    public static synchronized void m25428a(Throwable th) {
        synchronized (C0623a.class) {
            C5263di.m3219a(th);
        }
    }

    /* renamed from: b */
    private static C5260df m25427b(Context context, String str, String str2, String str3) {
        String str4 = "";
        try {
            str4 = context.getPackageName();
        } catch (Throwable unused) {
        }
        return new C5260df(Build.MODEL, str4, "APPSecuritySDK-ALIPAYSDK", "3.4.0.201910161639", str, str2, str3);
    }
}
