package com.cyjh.mobileanjian.ipc.stuff;

import android.content.Context;
import android.content.pm.PackageManager;

/* renamed from: com.cyjh.mobileanjian.ipc.stuff.a */
/* loaded from: classes.dex */
public final class AppAttr {

    /* renamed from: a */
    public static final String f8418a = "com.cyjh.mobileanjian";

    /* renamed from: b */
    private static boolean f8419b = true;

    /* renamed from: c */
    private static String f8420c = "12345678-0000-0000-0000-BA9876543210";

    /* renamed from: d */
    private static String f8421d = "2015-06-24";

    /* renamed from: e */
    private static String f8422e = "";

    /* renamed from: a */
    private static void m20924a(boolean z) {
        f8419b = z;
    }

    /* renamed from: a */
    public static boolean m20927a() {
        return f8419b;
    }

    /* renamed from: a */
    private static void m20925a(String str) {
        f8421d = str;
    }

    /* renamed from: b */
    public static String m20923b() {
        return f8421d;
    }

    /* renamed from: b */
    private static void m20921b(String str) {
        f8420c = str;
    }

    /* renamed from: c */
    public static String m20920c() {
        return f8420c;
    }

    /* renamed from: a */
    private static String m20926a(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    /* renamed from: b */
    private static String m20922b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "NOT_FOUND";
        }
    }

    /* renamed from: c */
    private static int m20919c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -2000;
        }
    }

    /* renamed from: c */
    private static void m20918c(String str) {
        f8422e = str;
    }

    /* renamed from: d */
    private static String m20917d() {
        return f8422e;
    }

    /* renamed from: d */
    private static boolean m20916d(Context context) {
        return context.getApplicationContext().getPackageName().equals(f8418a);
    }
}
