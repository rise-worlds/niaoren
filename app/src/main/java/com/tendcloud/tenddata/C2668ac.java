package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
import com.tendcloud.tenddata.TDAccount;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ac */
/* loaded from: classes2.dex */
public final class C2668ac {

    /* renamed from: a */
    public static final boolean f13533a = TCAgent.ENABLE_MULTI_PROCESS_POST;

    /* renamed from: b */
    private static AbstractC2680ao f13534b;

    /* renamed from: a */
    public static synchronized void m16341a(Context context, AbstractC2790d dVar) {
        synchronized (C2668ac.class) {
            try {
                m16305e(context, dVar);
                f13534b.mo15233a(context, dVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m16339a(Context context, String str, String str2, AbstractC2790d dVar) {
        synchronized (C2668ac.class) {
            try {
                m16305e(context, dVar);
                f13534b.mo15230a(context, str, str2, dVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static String m16318b(Context context, AbstractC2790d dVar) {
        return C2664ab.m16358a(context, dVar);
    }

    /* renamed from: c */
    public static String m16311c(Context context, AbstractC2790d dVar) {
        return C2664ab.m16353b(context, dVar);
    }

    /* renamed from: a */
    public static boolean m16345a() {
        return C3034zz.f14345a;
    }

    @Deprecated
    /* renamed from: b */
    public static void m16321b() {
        C2954hu.f14201a = true;
    }

    /* renamed from: d */
    public static synchronized String m16307d(Context context, AbstractC2790d dVar) {
        String b;
        synchronized (C2668ac.class) {
            try {
                m16305e(context, dVar);
                b = f13534b.mo15212b(context, dVar);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return b;
    }

    /* renamed from: a */
    public static synchronized String m16342a(Context context) {
        String b;
        synchronized (C2668ac.class) {
            try {
                m16305e(context, (AbstractC2790d) null);
                b = f13534b.mo15213b(context);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return b;
    }

    /* renamed from: c */
    public static Context m16312c() {
        return f13534b.mo15201d();
    }

    /* renamed from: d */
    public static void m16308d() {
        try {
            C2811dq.f13769a = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16343a(Activity activity, String str, String str2, AbstractC2790d dVar) {
        try {
            m16305e(activity, dVar);
            f13534b.mo15235a(activity, str, str2, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16344a(Activity activity, AbstractC2790d dVar) {
        try {
            m16305e(activity, dVar);
            f13534b.mo15236a(activity, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m16320b(Activity activity, AbstractC2790d dVar) {
        try {
            m16305e(activity, dVar);
            f13534b.mo15214b(activity, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16340a(Context context, String str, AbstractC2790d dVar) {
        try {
            m16305e(context, dVar);
            f13534b.mo15231a(context, str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m16317b(Context context, String str, AbstractC2790d dVar) {
        try {
            m16305e(context, dVar);
            f13534b.mo15211b(context, str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16329a(String str, Object obj, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15222a(str, obj);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16330a(String str, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.removeGlobalKV(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public static void m16310c(Context context, String str, AbstractC2790d dVar) {
        m16316b(context, str, "", dVar);
    }

    /* renamed from: b */
    public static void m16316b(Context context, String str, String str2, AbstractC2790d dVar) {
        m16338a(context, str, str2, (Map) null, dVar);
    }

    /* renamed from: a */
    public static void m16337a(Context context, String str, Map map, AbstractC2790d dVar) {
        m16338a(context, str, (String) null, map, dVar);
    }

    /* renamed from: a */
    public static void m16338a(Context context, String str, String str2, Map map, AbstractC2790d dVar) {
        try {
            m16305e(context, dVar);
            f13534b.mo15229a(context, str, str2, map, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16333a(AbstractC2790d dVar, String str) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15229a(C2664ab.f13513g, str, "", (Map) null, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16322a(boolean z, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15202c(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16336a(Context context, Throwable th, AbstractC2790d dVar) {
        try {
            m16305e(context, dVar);
            f13534b.mo15228a(context, th, dVar);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* renamed from: b */
    public static int m16319b(Context context) {
        return C2821dv.m15939b(context);
    }

    /* renamed from: e */
    private static synchronized void m16305e(Context context, AbstractC2790d dVar) {
        synchronized (C2668ac.class) {
            if (context != null) {
                C2664ab.f13513g = context.getApplicationContext();
            }
            if (C2664ab.f13513g == null) {
                C2811dq.eForDeveloper("Init failed Context is null ");
                return;
            }
            if (f13534b == null) {
                System.currentTimeMillis();
                f13534b = C3034zz.m15237a();
            }
        }
    }

    /* renamed from: a */
    public static void m16335a(Context context, boolean z, AbstractC2790d dVar) {
        try {
            m16305e(context, dVar);
            f13534b.setAntiCheatingDisabled(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m16314b(String str, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15223a(str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16331a(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15209b(str, accountType, str2, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public static void m16309c(String str, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15208b(str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m16315b(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15224a(str, accountType, str2, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    public static void m16306d(String str, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15197e(str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16327a(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15207b(str, str2, i, str3, str4, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16325a(String str, String str2, Order order, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15218a(str, str2, order, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16328a(String str, String str2, int i, String str3, String str4, Order order, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15221a(str, str2, i, str3, str4, order, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16326a(String str, String str2, int i, String str3, String str4, String str5, int i2, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15219a(str, str2, i, str3, str4, str5, i2, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16332a(String str, Order order, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15226a(str, order, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m16313b(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15220a(str, str2, i, str3, str4, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16324a(String str, String str2, String str3, int i, int i2, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15217a(str, str2, str3, i, i2, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16323a(String str, String str2, String str3, int i, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15216a(str, str2, str3, i, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16334a(ShoppingCart shoppingCart, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15227a(shoppingCart, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m16304e(String str, AbstractC2790d dVar) {
        try {
            m16305e(C2664ab.f13513g, dVar);
            f13534b.mo15199d(str, dVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
