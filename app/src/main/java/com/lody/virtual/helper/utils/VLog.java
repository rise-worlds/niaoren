package com.lody.virtual.helper.utils;

import android.os.Bundle;
import android.util.Log;
import java.util.Set;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class VLog {
    public static boolean OPEN_LOG = false;

    /* renamed from: i */
    public static void m18989i(String str, String str2, Object... objArr) {
        if (OPEN_LOG) {
            Log.d(str, String.format(str2, objArr));
        }
    }

    /* renamed from: d */
    public static void m18993d(String str, String str2, Object... objArr) {
        if (OPEN_LOG) {
            Log.d(str, String.format(str2, objArr));
        }
    }

    public static void logbug(String str, String str2) {
        m18993d(str, str2, new Object[0]);
    }

    /* renamed from: w */
    public static void m18986w(String str, String str2, Object... objArr) {
        if (OPEN_LOG) {
            Log.w(str, String.format(str2, objArr));
        }
    }

    /* renamed from: e */
    public static void m18992e(String str, String str2) {
        if (OPEN_LOG) {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m18991e(String str, String str2, Object... objArr) {
        if (OPEN_LOG) {
            Log.e(str, String.format(str2, objArr));
        }
    }

    /* renamed from: v */
    public static void m18988v(String str, String str2) {
        if (OPEN_LOG) {
            Log.v(str, str2);
        }
    }

    /* renamed from: v */
    public static void m18987v(String str, String str2, Object... objArr) {
        if (OPEN_LOG) {
            Log.v(str, String.format(str2, objArr));
        }
    }

    public static String toString(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (Reflect.m18998on(bundle).get("mParcelledData") == null) {
            return bundle.toString();
        }
        Set<String> keySet = bundle.keySet();
        StringBuilder sb = new StringBuilder("Bundle[");
        if (keySet != null) {
            for (String str : keySet) {
                sb.append(str);
                sb.append(SimpleComparison.f23609c);
                sb.append(bundle.get(str));
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static void printStackTrace(String str) {
        Log.e(str, getStackTraceString(new Exception()));
    }

    /* renamed from: e */
    public static void m18990e(String str, Throwable th) {
        Log.e(str, getStackTraceString(th));
    }
}
