package p110z1;

import android.content.Context;
import java.util.HashMap;

/* renamed from: z1.dc */
/* loaded from: classes3.dex */
public final class C5257dc {
    /* renamed from: a */
    public static synchronized void m3244a(Context context, String str, String str2, String str3) {
        synchronized (C5257dc.class) {
            if (!C5097cq.m3699a(str)) {
                if (!C5097cq.m3699a(str2) && context != null) {
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
}
