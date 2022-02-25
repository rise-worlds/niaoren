package com.p018b.p019a;

/* compiled from: ConnectionPool.java */
/* renamed from: com.b.a.o */
/* loaded from: classes.dex */
final class RunnableC0911o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ConnectionPool f6363a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0911o(ConnectionPool nVar) {
        this.f6363a = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (true) {
            long a = this.f6363a.m24398a(System.nanoTime());
            if (a != -1) {
                if (a > 0) {
                    long j = a / 1000000;
                    long j2 = a - (1000000 * j);
                    synchronized (this.f6363a) {
                        try {
                            this.f6363a.wait(j, (int) j2);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
