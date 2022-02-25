package com.p018b.p019a.p020a.p021a;

import com.p018b.p019a.Request;
import com.p018b.p019a.Response;

/* renamed from: com.b.a.a.a.d */
/* loaded from: classes.dex */
public final class CacheStrategy {

    /* renamed from: a */
    public final Request f5696a;

    /* renamed from: b */
    public final Response f5697b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheStrategy(Request aoVar, Response asVar) {
        this.f5696a = aoVar;
        this.f5697b = asVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r3.m24447g().m24414d() == false) goto L_0x0048;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m24819a(com.p018b.p019a.Response r3, com.p018b.p019a.Request r4) {
        /*
            int r0 = r3.m24452b()
            r1 = 0
            switch(r0) {
                case 200: goto L_0x0031;
                case 203: goto L_0x0031;
                case 204: goto L_0x0031;
                case 300: goto L_0x0031;
                case 301: goto L_0x0031;
                case 302: goto L_0x0009;
                case 307: goto L_0x0009;
                case 308: goto L_0x0031;
                case 404: goto L_0x0031;
                case 405: goto L_0x0031;
                case 410: goto L_0x0031;
                case 414: goto L_0x0031;
                case 501: goto L_0x0031;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0048
        L_0x0009:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.m24453a(r0)
            if (r0 != 0) goto L_0x0031
            com.b.a.e r0 = r3.m24447g()
            int r0 = r0.m24415c()
            r2 = -1
            if (r0 != r2) goto L_0x0031
            com.b.a.e r0 = r3.m24447g()
            boolean r0 = r0.m24413e()
            if (r0 != 0) goto L_0x0031
            com.b.a.e r0 = r3.m24447g()
            boolean r0 = r0.m24414d()
            if (r0 != 0) goto L_0x0031
            goto L_0x0048
        L_0x0031:
            com.b.a.e r3 = r3.m24447g()
            boolean r3 = r3.m24416b()
            if (r3 != 0) goto L_0x0047
            com.b.a.e r3 = r4.m24465f()
            boolean r3 = r3.m24416b()
            if (r3 != 0) goto L_0x0047
            r3 = 1
            return r3
        L_0x0047:
            return r1
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.p021a.CacheStrategy.m24819a(com.b.a.as, com.b.a.ao):boolean");
    }
}
