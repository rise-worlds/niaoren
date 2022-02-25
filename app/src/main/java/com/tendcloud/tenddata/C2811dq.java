package com.tendcloud.tenddata;

import android.util.Log;
import p110z1.Consts;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dq */
/* loaded from: classes2.dex */
public class C2811dq {

    /* renamed from: a */
    public static boolean f13769a = true;

    public static void dForInternal(String... strArr) {
    }

    public static void eForInternal(Throwable th) {
    }

    public static void eForInternal(String... strArr) {
    }

    public static void iForInternal(String... strArr) {
    }

    public static void json(String str) {
    }

    public static void iForDeveloper(String str) {
        if (f13769a) {
            m16037a(str, 4);
        }
    }

    public static void dForDeveloper(String str) {
        if (f13769a) {
            m16037a(str, 3);
        }
    }

    public static void eForDeveloper(String str) {
        if (f13769a) {
            m16037a(str, 6);
        }
    }

    /* renamed from: a */
    public static void m16036a(String str, Throwable th) {
        if (f13769a) {
            m16037a(str, 4);
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m16037a(String str, int i) {
        if (str != null) {
            try {
                int length = str.length();
                int i2 = 2000;
                int i3 = 0;
                int i4 = 0;
                while (i3 < 100) {
                    if (length > i2) {
                        m16035b(str.substring(i4, i2), i);
                        i2 += 2000;
                        i3++;
                        i4 = i2;
                    } else {
                        m16035b(str.substring(i4, length), i);
                        return;
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: b */
    private static void m16035b(String str, int i) {
        String a = m16038a();
        switch (i) {
            case 2:
                Log.v(a, str);
                return;
            case 3:
                Log.d(a, str);
                return;
            case 4:
                Log.i(a, str);
                return;
            case 5:
                Log.w(a, str);
                return;
            case 6:
                Log.e(a, str);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static synchronized String m16038a() {
        synchronized (C2811dq.class) {
            try {
                new Exception().getStackTrace()[4].getClassName().lastIndexOf(Consts.f23430h);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return C2664ab.f13525s;
            }
        }
        return C2664ab.f13525s;
    }
}
