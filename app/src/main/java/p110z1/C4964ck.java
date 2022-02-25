package p110z1;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* renamed from: z1.ck */
/* loaded from: classes3.dex */
public class C4964ck {

    /* renamed from: a */
    private static String f20769a;

    /* renamed from: a */
    public static boolean m5128a(Context context, String str) {
        try {
            return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static void m5126b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).apply();
        } catch (Throwable th) {
            C4921cd.m5618a(th);
        }
    }

    /* renamed from: a */
    public static void m5127a(C4745bt btVar, Context context, String str, String str2) {
        try {
            String a = C4187bd.m9734a(m5129a(context), str2, str);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a)) {
                C3754ao.m12156a(btVar, C3857aq.f17252c, C3857aq.f17275z, String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a).apply();
        } catch (Throwable th) {
            C4921cd.m5618a(th);
        }
    }

    /* renamed from: b */
    public static String m5125b(C4745bt btVar, Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (!TextUtils.isEmpty(string)) {
                str3 = C4187bd.m9732b(m5129a(context), string, str);
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(str3)) {
                C3754ao.m12156a(btVar, C3857aq.f17252c, C3857aq.f17274y, String.format("%s,%s", str, string));
            }
        } catch (Exception e) {
            C4921cd.m5618a(e);
        }
        return str3;
    }

    /* renamed from: a */
    private static String m5129a(Context context) {
        if (TextUtils.isEmpty(f20769a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C4921cd.m5618a(th);
            }
            f20769a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return f20769a;
    }
}
