package com.cyjh.ddy.base.utils;

import android.util.Log;
import com.cyjh.ddy.base.p033a.NoProGuard;

/* loaded from: classes.dex */
public final class CLog implements NoProGuard {
    public static int mLogPriority = 6;
    public static boolean mPrintLog = true;

    /* renamed from: a */
    private static boolean m21885a(int i) {
        return mPrintLog && i >= mLogPriority;
    }

    /* renamed from: v */
    public static void m21881v(String str, String str2) {
        if (m21885a(2)) {
            Log.v(str, str2);
        }
    }

    /* renamed from: e */
    public static void m21883e(String str, String str2) {
        if (m21885a(6)) {
            Log.e(str, str2);
        }
    }

    /* renamed from: w */
    public static void m21880w(String str, String str2) {
        if (m21885a(5)) {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m21879w(String str, String str2, Throwable th) {
        if (m21885a(5)) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: d */
    public static void m21884d(String str, String str2) {
        if (m21885a(3)) {
            Log.d(str, str2);
        }
    }

    /* renamed from: i */
    public static void m21882i(String str, String str2) {
        if (m21885a(4)) {
            Log.i(str, str2);
        }
    }
}
