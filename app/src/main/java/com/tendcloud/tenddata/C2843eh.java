package com.tendcloud.tenddata;

import android.content.Context;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.eh */
/* loaded from: classes2.dex */
public class C2843eh {
    /* renamed from: a */
    public static void m15843a(Context context, String str, String str2, long j) {
        try {
            context.getSharedPreferences(str, 0).edit().putLong(str2, j).commit();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static void m15842a(Context context, String str, String str2, String str3) {
        try {
            context.getSharedPreferences(str, 0).edit().putString(str2, str3).commit();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public static long m15841b(Context context, String str, String str2, long j) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, j);
        } catch (Throwable unused) {
            return j;
        }
    }

    /* renamed from: b */
    public static String m15840b(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable unused) {
            return str3;
        }
    }
}
