package p110z1;

import java.io.File;

/* renamed from: z1.da */
/* loaded from: classes3.dex */
public final class C5222da {
    /* renamed from: a */
    public static String m3364a(String str) {
        String str2 = "";
        try {
            str2 = C5259de.m3226a(str);
        } catch (Throwable unused) {
        }
        if (!C5097cq.m3699a(str2)) {
            return str2;
        }
        return C5240db.m3334a(".SystemConfig" + File.separator + str);
    }
}
