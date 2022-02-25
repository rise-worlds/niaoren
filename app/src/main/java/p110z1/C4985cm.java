package p110z1;

import com.alipay.sdk.app.EnumC0664m;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.cm */
/* loaded from: classes3.dex */
public class C4985cm {

    /* renamed from: a */
    public static final String f20831a = "resultStatus";

    /* renamed from: b */
    public static final String f20832b = "memo";

    /* renamed from: c */
    public static final String f20833c = "result";

    /* renamed from: a */
    public static Map<String, String> m4836a(C4745bt btVar, String str) {
        Map<String, String> a = m4839a();
        try {
            return m4838a(str);
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17256g, th);
            return a;
        }
    }

    /* renamed from: a */
    private static Map<String, String> m4839a() {
        EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.CANCELED.m25279a());
        HashMap hashMap = new HashMap();
        hashMap.put(f20831a, Integer.toString(b.m25279a()));
        hashMap.put(f20832b, b.m25276b());
        hashMap.put(f20833c, "");
        return hashMap;
    }

    /* renamed from: a */
    public static Map<String, String> m4838a(String str) {
        String[] split = str.split(C4963cj.f20745b);
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            hashMap.put(substring, m4837a(str2, substring));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static String m4837a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(C4963cj.f20747d));
    }
}
