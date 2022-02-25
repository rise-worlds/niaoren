package com.p018b.p029b;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* renamed from: com.b.b.y */
/* loaded from: classes.dex */
public class Timeout {

    /* renamed from: b */
    public static final Timeout f6462b = new C0923z();

    /* renamed from: a */
    private boolean f6463a;

    /* renamed from: c */
    private long f6464c;

    /* renamed from: d */
    private long f6465d;

    /* renamed from: a */
    public Timeout mo24242a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit != null) {
            this.f6465d = timeUnit.toNanos(j);
            return this;
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    /* renamed from: b_ */
    public long mo24248b_() {
        return this.f6465d;
    }

    /* renamed from: c_ */
    public boolean mo24247c_() {
        return this.f6463a;
    }

    /* renamed from: d */
    public long mo24246d() {
        if (this.f6463a) {
            return this.f6464c;
        }
        throw new IllegalStateException("No deadline");
    }

    /* renamed from: a */
    public Timeout mo24243a(long j) {
        this.f6463a = true;
        this.f6464c = j;
        return this;
    }

    /* renamed from: d_ */
    public Timeout mo24245d_() {
        this.f6465d = 0L;
        return this;
    }

    /* renamed from: e_ */
    public Timeout mo24244e_() {
        this.f6463a = false;
        return this;
    }

    /* renamed from: f_ */
    public void mo24241f_() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.f6463a && this.f6464c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
