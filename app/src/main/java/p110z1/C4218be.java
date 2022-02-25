package p110z1;

import android.content.Context;
import android.os.SystemClock;

/* renamed from: z1.be */
/* loaded from: classes3.dex */
public class C4218be {

    /* renamed from: a */
    private static long f18403a;

    /* renamed from: z1.be$a */
    /* loaded from: classes3.dex */
    public interface AbstractC4219a {
        /* renamed from: a */
        void m9728a(String str);
    }

    /* renamed from: a */
    public static void m9729a(AbstractC4219a aVar) {
        C4921cd.m5617a(aVar);
    }

    /* renamed from: a */
    public static boolean m9730a(Context context) {
        try {
            C4759bu.m9273a().m9272a(context);
            long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (elapsedRealtime - f18403a < 600) {
                return false;
            }
            f18403a = elapsedRealtime;
            C3754ao.m12159a(context);
            return true;
        } catch (Exception e) {
            C4921cd.m5618a(e);
            return false;
        }
    }
}
