package com.p073ta.utdid2.device;

import com.p073ta.utdid2.p074a.p075a.C2510a;
import com.p073ta.utdid2.p074a.p075a.C2511b;
import com.p073ta.utdid2.p074a.p075a.C2521g;

/* renamed from: com.ta.utdid2.device.e */
/* loaded from: classes2.dex */
public class C2536e {
    /* renamed from: d */
    public String m17086d(String str) {
        return C2510a.m17181b(str);
    }

    /* renamed from: e */
    public String m17085e(String str) {
        String b = C2510a.m17181b(str);
        if (C2521g.m17166a(b)) {
            return null;
        }
        try {
            return new String(C2511b.decode(b, 0));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
