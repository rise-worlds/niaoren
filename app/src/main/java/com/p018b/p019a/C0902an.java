package com.p018b.p019a;

import com.p018b.p019a.p020a.NamedRunnable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RealCall.java */
/* renamed from: com.b.a.an */
/* loaded from: classes.dex */
public final class C0902an extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ RealCall f6154a;

    /* renamed from: c */
    private final Callback f6155c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0902an(RealCall amVar, Callback hVar) {
        super("OkHttp %s", amVar.m24476b());
        this.f6154a = amVar;
        this.f6155c = hVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final String m24473a() {
        return this.f6154a.f6151c.f6156a.f6061b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.b.a.u] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo24472b() {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            com.b.a.am r2 = r7.f6154a     // Catch: all -> 0x0030, IOException -> 0x0032
            com.b.a.as r2 = r2.m24475c()     // Catch: all -> 0x0030, IOException -> 0x0032
            com.b.a.am r3 = r7.f6154a     // Catch: all -> 0x0030, IOException -> 0x0032
            com.b.a.a.c.k r3 = r3.f6150b     // Catch: all -> 0x0030, IOException -> 0x0032
            boolean r1 = r3.m24729a()     // Catch: all -> 0x0030, IOException -> 0x0032
            if (r1 == 0) goto L_0x001f
            com.b.a.h r1 = r7.f6155c     // Catch: IOException -> 0x002e, all -> 0x0030
            java.io.IOException r2 = new java.io.IOException     // Catch: IOException -> 0x002e, all -> 0x0030
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: IOException -> 0x002e, all -> 0x0030
            r1.mo20274a(r2)     // Catch: IOException -> 0x002e, all -> 0x0030
            goto L_0x0024
        L_0x001f:
            com.b.a.h r1 = r7.f6155c     // Catch: IOException -> 0x002e, all -> 0x0030
            r1.mo20275a(r2)     // Catch: IOException -> 0x002e, all -> 0x0030
        L_0x0024:
            com.b.a.am r0 = r7.f6154a
            com.b.a.ai r0 = r0.f6149a
            com.b.a.u r0 = r0.f6093c
        L_0x002a:
            r0.m24370b(r7)
            return
        L_0x002e:
            r1 = move-exception
            goto L_0x0035
        L_0x0030:
            r0 = move-exception
            goto L_0x008d
        L_0x0032:
            r0 = move-exception
            r1 = r0
            r0 = 0
        L_0x0035:
            if (r0 == 0) goto L_0x0081
            com.b.a.a.g.h r0 = com.p018b.p019a.p020a.p027g.Platform.m24576b()     // Catch: all -> 0x0030
            r2 = 4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0030
            java.lang.String r4 = "Callback failure for "
            r3.<init>(r4)     // Catch: all -> 0x0030
            com.b.a.am r4 = r7.f6154a     // Catch: all -> 0x0030
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0030
            r5.<init>()     // Catch: all -> 0x0030
            com.b.a.a.c.k r6 = r4.f6150b     // Catch: all -> 0x0030
            boolean r6 = r6.m24729a()     // Catch: all -> 0x0030
            if (r6 == 0) goto L_0x0055
            java.lang.String r6 = "canceled "
            goto L_0x0057
        L_0x0055:
            java.lang.String r6 = ""
        L_0x0057:
            r5.append(r6)     // Catch: all -> 0x0030
            boolean r6 = r4.f6152d     // Catch: all -> 0x0030
            if (r6 == 0) goto L_0x0061
            java.lang.String r6 = "web socket"
            goto L_0x0063
        L_0x0061:
            java.lang.String r6 = "call"
        L_0x0063:
            r5.append(r6)     // Catch: all -> 0x0030
            java.lang.String r6 = " to "
            r5.append(r6)     // Catch: all -> 0x0030
            java.lang.String r4 = r4.m24476b()     // Catch: all -> 0x0030
            r5.append(r4)     // Catch: all -> 0x0030
            java.lang.String r4 = r5.toString()     // Catch: all -> 0x0030
            r3.append(r4)     // Catch: all -> 0x0030
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x0030
            r0.mo24584a(r2, r3, r1)     // Catch: all -> 0x0030
            goto L_0x0086
        L_0x0081:
            com.b.a.h r0 = r7.f6155c     // Catch: all -> 0x0030
            r0.mo20274a(r1)     // Catch: all -> 0x0030
        L_0x0086:
            com.b.a.am r0 = r7.f6154a
            com.b.a.ai r0 = r0.f6149a
            com.b.a.u r0 = r0.f6093c
            goto L_0x002a
        L_0x008d:
            com.b.a.am r1 = r7.f6154a
            com.b.a.ai r1 = r1.f6149a
            com.b.a.u r1 = r1.f6093c
            r1.m24370b(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.C0902an.mo24472b():void");
    }
}
