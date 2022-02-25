package com.p018b.p029b;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncTimeout.java */
/* renamed from: com.b.b.d */
/* loaded from: classes.dex */
public final class C0919d extends Thread {
    public C0919d() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0017, code lost:
        r1.mo24300a();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r3 = this;
        L_0x0000:
            java.lang.Class<com.b.b.a> r0 = com.p018b.p029b.AsyncTimeout.class
            monitor-enter(r0)     // Catch: InterruptedException -> 0x0000
            com.b.b.a r1 = com.p018b.p029b.AsyncTimeout.m24351e()     // Catch: all -> 0x001b
            if (r1 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch: all -> 0x001b
            goto L_0x0000
        L_0x000b:
            com.b.b.a r2 = com.p018b.p029b.AsyncTimeout.m24350f()     // Catch: all -> 0x001b
            if (r1 != r2) goto L_0x0016
            com.p018b.p029b.AsyncTimeout.m24349g()     // Catch: all -> 0x001b
            monitor-exit(r0)     // Catch: all -> 0x001b
            return
        L_0x0016:
            monitor-exit(r0)     // Catch: all -> 0x001b
            r1.mo24300a()     // Catch: InterruptedException -> 0x0000
            goto L_0x0000
        L_0x001b:
            r1 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x001b
            throw r1     // Catch: InterruptedException -> 0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p029b.C0919d.run():void");
    }
}
