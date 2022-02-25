package p110z1;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: z1.cj */
/* loaded from: classes3.dex */
public class C4963cj {

    /* renamed from: a */
    public static final String f20744a = "pref_trade_token";

    /* renamed from: b */
    public static final String f20745b = ";";

    /* renamed from: c */
    public static final String f20746c = "result={";

    /* renamed from: d */
    public static final String f20747d = "}";

    /* renamed from: e */
    public static final String f20748e = "trade_token=\"";

    /* renamed from: f */
    public static final String f20749f = "\"";

    /* renamed from: g */
    public static final String f20750g = "trade_token=";

    /* renamed from: a */
    public static void m5209a(C4745bt btVar, Context context, String str) {
        try {
            String a = m5211a(str);
            C4921cd.m5620a(C3876ar.f17447x, "trade token: " + a);
            if (!TextUtils.isEmpty(a)) {
                C4964ck.m5127a(btVar, context, f20744a, a);
            }
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17210B, th);
            C4921cd.m5618a(th);
        }
    }

    /* renamed from: a */
    public static String m5211a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(f20745b);
        String str2 = null;
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(f20746c) && split[i].endsWith(f20747d)) {
                String[] split2 = split[i].substring(8, split[i].length() - 1).split(C4745bt.f20071b);
                int i2 = 0;
                while (true) {
                    if (i2 >= split2.length) {
                        break;
                    } else if (split2[i2].startsWith(f20748e) && split2[i2].endsWith("\"")) {
                        str2 = split2[i2].substring(13, split2[i2].length() - 1);
                        break;
                    } else if (split2[i2].startsWith(f20750g)) {
                        str2 = split2[i2].substring(12);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m5210a(C4745bt btVar, Context context) {
        String b = C4964ck.m5125b(btVar, context, f20744a, "");
        C4921cd.m5620a(C3876ar.f17447x, "get trade token: " + b);
        return b;
    }
}
