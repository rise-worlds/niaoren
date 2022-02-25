package p110z1;

import android.content.Context;
import com.p073ta.utdid2.device.UTDevice;
import java.io.File;

/* renamed from: z1.bu */
/* loaded from: classes3.dex */
public class C4759bu {

    /* renamed from: a */
    private static C4759bu f20143a;

    /* renamed from: b */
    private Context f20144b;

    private C4759bu() {
    }

    /* renamed from: a */
    public static C4759bu m9273a() {
        if (f20143a == null) {
            f20143a = new C4759bu();
        }
        return f20143a;
    }

    /* renamed from: b */
    public Context m9271b() {
        return this.f20144b;
    }

    /* renamed from: a */
    public void m9272a(Context context) {
        C3937aw.m9861b();
        this.f20144b = context.getApplicationContext();
    }

    /* renamed from: c */
    public C3937aw m9270c() {
        return C3937aw.m9861b();
    }

    /* renamed from: d */
    public static boolean m9269d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public String m9268e() {
        try {
            return UTDevice.getUtdid(this.f20144b);
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            return "getUtdidEx";
        }
    }
}
