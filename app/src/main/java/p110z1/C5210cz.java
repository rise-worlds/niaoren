package p110z1;

import android.content.Context;
import java.util.HashMap;

/* renamed from: z1.cz */
/* loaded from: classes3.dex */
public class C5210cz {
    /* renamed from: a */
    public static String m3450a(Context context, String str, String str2) {
        String a;
        synchronized (C5210cz.class) {
            String str3 = null;
            if (context != null) {
                if (!C5097cq.m3699a(str) && !C5097cq.m3699a(str2)) {
                    try {
                        a = C5258dd.m3228a(context, str, str2, "");
                    } catch (Throwable unused) {
                    }
                    if (C5097cq.m3699a(a)) {
                        return null;
                    }
                    str3 = C5128ct.m3516b(C5128ct.m3521a(), a);
                    return str3;
                }
            }
            return null;
        }
    }

    /* renamed from: a */
    public static void m3449a(Context context, String str, String str2, String str3) {
        synchronized (C5210cz.class) {
            if (!C5097cq.m3699a(str) && !C5097cq.m3699a(str2) && context != null) {
                try {
                    String a = C5128ct.m3519a(C5128ct.m3521a(), str3);
                    HashMap hashMap = new HashMap();
                    hashMap.put(str2, a);
                    C5258dd.m3227a(context, str, hashMap);
                } catch (Throwable unused) {
                }
            }
        }
    }
}
