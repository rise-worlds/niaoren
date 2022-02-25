package p110z1;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: z1.bw */
/* loaded from: classes3.dex */
public class C4801bw {
    /* renamed from: a */
    public static C4796bv m8882a(Context context) {
        m8871k(context);
        C4796bv a = m8881a(context, C4814by.m8377a(context));
        if (a == null) {
            C4921cd.m5620a(C3876ar.f17447x, "load_tid null");
        }
        return a;
    }

    /* renamed from: b */
    public static synchronized C4796bv m8880b(Context context) {
        synchronized (C4801bw.class) {
            C4921cd.m5620a(C3876ar.f17447x, "load_create_tid");
            m8871k(context);
            C4796bv a = m8882a(context);
            if (C4796bv.m8950a(a)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    return null;
                }
                try {
                    a = m8870l(context);
                } catch (Throwable unused) {
                }
            }
            return a;
        }
    }

    /* renamed from: c */
    public static synchronized String m8879c(Context context) {
        String a;
        synchronized (C4801bw.class) {
            C4796bv b = m8880b(context);
            a = C4796bv.m8950a(b) ? "" : b.m8951a();
        }
        return a;
    }

    /* renamed from: d */
    public static boolean m8878d(Context context) throws Exception {
        C4921cd.m5620a(C3876ar.f17447x, "reset_tid");
        if (Looper.myLooper() != Looper.getMainLooper()) {
            m8871k(context);
            m8877e(context);
            C4796bv bvVar = null;
            try {
                bvVar = m8870l(context);
            } catch (Throwable unused) {
            }
            return !C4796bv.m8950a(bvVar);
        }
        throw new Exception("Must be called on worker thread");
    }

    /* renamed from: e */
    public static void m8877e(Context context) {
        C4814by.m8377a(context).m8367g();
    }

    /* renamed from: f */
    public static String m8876f(Context context) {
        m8871k(context);
        return C4873ca.m6517a(context).m6516b();
    }

    /* renamed from: g */
    public static String m8875g(Context context) {
        m8871k(context);
        return C4873ca.m6517a(context).m6518a();
    }

    /* renamed from: h */
    public static String m8874h(Context context) {
        m8871k(context);
        C3937aw.m9861b();
        return C3937aw.m9858c();
    }

    /* renamed from: i */
    public static String m8873i(Context context) {
        m8871k(context);
        C3937aw.m9861b();
        return C3937aw.m9855d();
    }

    /* renamed from: k */
    private static void m8871k(Context context) {
        if (context != null) {
            C4759bu.m9273a().m9272a(context);
        }
    }

    /* renamed from: l */
    private static C4796bv m8870l(Context context) throws Exception {
        try {
            C4330bh a = new C4576bn().m9630a(C4745bt.m9418a(), context);
            if (a == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(a.m9690b());
            C4814by a2 = C4814by.m8377a(context);
            String optString = jSONObject.optString("tid");
            String string = jSONObject.getString(C4814by.f20406e);
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(string)) {
                a2.m8376a(optString, string);
            }
            return m8881a(context, a2);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static C4796bv m8881a(Context context, C4814by byVar) {
        if (byVar == null || byVar.m8369e()) {
            return null;
        }
        return new C4796bv(byVar.m8378a(), byVar.m8373b(), byVar.m8365i().longValue());
    }

    /* renamed from: j */
    public static C4796bv m8872j(Context context) {
        C4814by a = C4814by.m8377a(context);
        if (a.m8366h()) {
            return null;
        }
        return new C4796bv(a.m8378a(), a.m8373b(), a.m8365i().longValue());
    }
}
