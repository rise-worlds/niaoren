package com.p073ta.utdid2.device;

import android.content.Context;
import com.p073ta.utdid2.p074a.p075a.C2517e;
import com.p073ta.utdid2.p074a.p075a.C2521g;
import java.util.zip.Adler32;

/* renamed from: com.ta.utdid2.device.b */
/* loaded from: classes2.dex */
public class C2533b {

    /* renamed from: a */
    private static C2532a f12725a;

    /* renamed from: d */
    static final Object f12726d = new Object();

    /* renamed from: a */
    static long m17105a(C2532a aVar) {
        if (aVar == null) {
            return 0L;
        }
        String format = String.format("%s%s%s%s%s", aVar.m17107f(), aVar.getDeviceId(), Long.valueOf(aVar.m17115a()), aVar.getImsi(), aVar.m17109e());
        if (C2521g.m17166a(format)) {
            return 0L;
        }
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(format.getBytes());
        return adler32.getValue();
    }

    /* renamed from: a */
    private static C2532a m17106a(Context context) {
        if (context == null) {
            return null;
        }
        synchronized (f12726d) {
            String value = C2534c.m17103a(context).getValue();
            if (C2521g.m17166a(value)) {
                return null;
            }
            if (value.endsWith("\n")) {
                value = value.substring(0, value.length() - 1);
            }
            C2532a aVar = new C2532a();
            long currentTimeMillis = System.currentTimeMillis();
            String a = C2517e.m17175a(context);
            String c = C2517e.m17171c(context);
            aVar.m17110d(a);
            aVar.m17112b(a);
            aVar.m17113b(currentTimeMillis);
            aVar.m17111c(c);
            aVar.m17108e(value);
            aVar.m17114a(m17105a(aVar));
            return aVar;
        }
    }

    /* renamed from: b */
    public static synchronized C2532a m17104b(Context context) {
        synchronized (C2533b.class) {
            if (f12725a != null) {
                return f12725a;
            } else if (context == null) {
                return null;
            } else {
                C2532a a = m17106a(context);
                f12725a = a;
                return a;
            }
        }
    }
}
