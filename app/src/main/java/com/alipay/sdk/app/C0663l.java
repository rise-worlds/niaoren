package com.alipay.sdk.app;

import p110z1.C4963cj;

/* renamed from: com.alipay.sdk.app.l */
/* loaded from: classes.dex */
public class C0663l {

    /* renamed from: a */
    private static boolean f332a = false;

    /* renamed from: b */
    private static String f333b;

    /* renamed from: a */
    public static void m25285a(String str) {
        f333b = str;
    }

    /* renamed from: a */
    public static String m25287a() {
        return f333b;
    }

    /* renamed from: b */
    public static boolean m25283b() {
        return f332a;
    }

    /* renamed from: a */
    public static void m25284a(boolean z) {
        f332a = z;
    }

    /* renamed from: c */
    public static String m25282c() {
        EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.CANCELED.m25279a());
        return m25286a(b.m25279a(), b.m25276b(), "");
    }

    /* renamed from: d */
    public static String m25281d() {
        EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.DOUBLE_REQUEST.m25279a());
        return m25286a(b.m25279a(), b.m25276b(), "");
    }

    /* renamed from: e */
    public static String m25280e() {
        EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.PARAMS_ERROR.m25279a());
        return m25286a(b.m25279a(), b.m25276b(), "");
    }

    /* renamed from: a */
    public static String m25286a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + C4963cj.f20747d;
    }
}
