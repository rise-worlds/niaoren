package com.p018b.p019a.p020a.p025e;

import java.util.concurrent.CountDownLatch;

/* renamed from: com.b.a.a.e.ai */
/* loaded from: classes.dex */
final class Ping {

    /* renamed from: a */
    private final CountDownLatch f5881a = new CountDownLatch(1);

    /* renamed from: b */
    private long f5882b = -1;

    /* renamed from: c */
    private long f5883c = -1;

    Ping() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24680a() {
        if (this.f5882b == -1) {
            this.f5882b = System.nanoTime();
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m24679b() {
        if (this.f5883c != -1 || this.f5882b == -1) {
            throw new IllegalStateException();
        }
        this.f5883c = System.nanoTime();
        this.f5881a.countDown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m24678c() {
        if (this.f5883c == -1) {
            long j = this.f5882b;
            if (j != -1) {
                this.f5883c = j - 1;
                this.f5881a.countDown();
                return;
            }
        }
        throw new IllegalStateException();
    }
}
