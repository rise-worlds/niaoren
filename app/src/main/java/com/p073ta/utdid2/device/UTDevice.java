package com.p073ta.utdid2.device;

import android.content.Context;
import com.p073ta.utdid2.p074a.p075a.C2521g;

/* renamed from: com.ta.utdid2.device.UTDevice */
/* loaded from: classes2.dex */
public class UTDevice {
    @Deprecated
    public static String getUtdid(Context context) {
        return m17117d(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return m17116e(context);
    }

    /* renamed from: d */
    private static String m17117d(Context context) {
        C2532a b = C2533b.m17104b(context);
        return (b == null || C2521g.m17166a(b.m17107f())) ? "ffffffffffffffffffffffff" : b.m17107f();
    }

    /* renamed from: e */
    private static String m17116e(Context context) {
        String h = C2534c.m17103a(context).m17094h();
        return (h == null || C2521g.m17166a(h)) ? "ffffffffffffffffffffffff" : h;
    }
}
