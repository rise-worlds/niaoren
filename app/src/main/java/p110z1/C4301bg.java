package p110z1;

import android.text.TextUtils;

/* renamed from: z1.bg */
/* loaded from: classes3.dex */
public class C4301bg {
    /* renamed from: a */
    public static String m9702a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(C4745bt.f20071b);
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str2)) {
                str2 = m9701b(str6);
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = m9700c(str6);
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = m9699d(str6);
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = m9697f(str6);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("biz_type=" + str2 + C4963cj.f20745b);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("biz_no=" + str3 + C4963cj.f20745b);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("trade_no=" + str4 + C4963cj.f20745b);
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append("app_userid=" + str5 + C4963cj.f20745b);
        }
        String sb2 = sb.toString();
        return sb2.endsWith(C4963cj.f20745b) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    /* renamed from: b */
    private static String m9701b(String str) {
        if (!str.contains("biz_type")) {
            return null;
        }
        return m9698e(str);
    }

    /* renamed from: c */
    private static String m9700c(String str) {
        if (!str.contains("biz_no")) {
            return null;
        }
        return m9698e(str);
    }

    /* renamed from: d */
    private static String m9699d(String str) {
        if (!str.contains(C3857aq.f17248am) || str.startsWith(C3857aq.f17247al)) {
            return null;
        }
        return m9698e(str);
    }

    /* renamed from: e */
    private static String m9698e(String str) {
        String[] split = str.split(SimpleComparison.f23609c);
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
    }

    /* renamed from: f */
    private static String m9697f(String str) {
        if (!str.contains("app_userid")) {
            return null;
        }
        return m9698e(str);
    }
}
