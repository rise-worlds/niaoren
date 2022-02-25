package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import java.util.HashMap;
import java.util.Map;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.C5097cq;

/* renamed from: com.alipay.apmobilesecuritysdk.e.i */
/* loaded from: classes.dex */
public final class C0639i {

    /* renamed from: a */
    private static String f236a = "";

    /* renamed from: b */
    private static String f237b = "";

    /* renamed from: c */
    private static String f238c = "";

    /* renamed from: d */
    private static String f239d = "";

    /* renamed from: e */
    private static String f240e = "";

    /* renamed from: f */
    private static Map<String, String> f241f = new HashMap();

    /* renamed from: a */
    public static synchronized String m25364a(String str) {
        synchronized (C0639i.class) {
            String str2 = "apdidTokenCache" + str;
            if (f241f.containsKey(str2)) {
                String str3 = f241f.get(str2);
                if (C5097cq.m3695b(str3)) {
                    return str3;
                }
            }
            return "";
        }
    }

    /* renamed from: a */
    public static synchronized void m25368a() {
        synchronized (C0639i.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m25366a(C0632b bVar) {
        synchronized (C0639i.class) {
            if (bVar != null) {
                f236a = bVar.f222a;
                f237b = bVar.f223b;
                f238c = bVar.f224c;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m25365a(C0633c cVar) {
        synchronized (C0639i.class) {
            if (cVar != null) {
                f236a = cVar.f225a;
                f237b = cVar.f226b;
                f239d = cVar.f228d;
                f240e = cVar.f229e;
                f238c = cVar.f227c;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m25363a(String str, String str2) {
        synchronized (C0639i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f241f.containsKey(str3)) {
                f241f.remove(str3);
            }
            f241f.put(str3, str2);
        }
    }

    /* renamed from: a */
    public static synchronized boolean m25367a(Context context, String str) {
        boolean z;
        synchronized (C0639i.class) {
            long j = WaitFor.ONE_DAY;
            try {
                long a = C0638h.m25385a(context);
                if (a >= 0) {
                    j = a;
                }
            } catch (Throwable unused) {
            }
            try {
            } catch (Throwable th) {
                C0623a.m25428a(th);
            }
            if (Math.abs(System.currentTimeMillis() - C0638h.m25369h(context, str)) < j) {
                z = true;
            }
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    public static synchronized String m25362b() {
        String str;
        synchronized (C0639i.class) {
            str = f236a;
        }
        return str;
    }

    /* renamed from: b */
    public static void m25361b(String str) {
        f236a = str;
    }

    /* renamed from: c */
    public static synchronized String m25360c() {
        String str;
        synchronized (C0639i.class) {
            str = f237b;
        }
        return str;
    }

    /* renamed from: c */
    public static void m25359c(String str) {
        f237b = str;
    }

    /* renamed from: d */
    public static synchronized String m25358d() {
        String str;
        synchronized (C0639i.class) {
            str = f239d;
        }
        return str;
    }

    /* renamed from: d */
    public static void m25357d(String str) {
        f238c = str;
    }

    /* renamed from: e */
    public static synchronized String m25356e() {
        String str;
        synchronized (C0639i.class) {
            str = f240e;
        }
        return str;
    }

    /* renamed from: e */
    public static void m25355e(String str) {
        f239d = str;
    }

    /* renamed from: f */
    public static synchronized String m25354f() {
        String str;
        synchronized (C0639i.class) {
            str = f238c;
        }
        return str;
    }

    /* renamed from: f */
    public static void m25353f(String str) {
        f240e = str;
    }

    /* renamed from: g */
    public static synchronized C0633c m25352g() {
        C0633c cVar;
        synchronized (C0639i.class) {
            cVar = new C0633c(f236a, f237b, f238c, f239d, f240e);
        }
        return cVar;
    }

    /* renamed from: h */
    public static void m25351h() {
        f241f.clear();
        f236a = "";
        f237b = "";
        f239d = "";
        f240e = "";
        f238c = "";
    }
}
