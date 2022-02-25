package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.Source;
import com.p018b.p029b.Timeout;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Stream.java */
/* renamed from: com.b.a.a.e.ad */
/* loaded from: classes.dex */
public final class C0867ad implements Source {

    /* renamed from: c */
    static final /* synthetic */ boolean f5859c = !Http2Stream.class.desiredAssertionStatus();

    /* renamed from: a */
    boolean f5860a;

    /* renamed from: b */
    boolean f5861b;

    /* renamed from: d */
    final /* synthetic */ Http2Stream f5862d;

    /* renamed from: e */
    private final Buffer f5863e = new Buffer();

    /* renamed from: f */
    private final Buffer f5864f = new Buffer();

    /* renamed from: g */
    private final long f5865g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0867ad(Http2Stream abVar, long j) {
        this.f5862d = abVar;
        this.f5865g = j;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (j >= 0) {
            synchronized (this.f5862d) {
                m24700b();
                if (this.f5860a) {
                    throw new IOException("stream closed");
                } else if (this.f5862d.f5849h != null) {
                    throw new StreamResetException(this.f5862d.f5849h);
                } else if (this.f5864f.m24331b() == 0) {
                    return -1L;
                } else {
                    long a = this.f5864f.mo24249a(fVar, Math.min(j, this.f5864f.m24331b()));
                    this.f5862d.f5842a += a;
                    if (this.f5862d.f5842a >= this.f5862d.f5845d.f5956l.m24666d() / 2) {
                        this.f5862d.f5845d.m24633a(this.f5862d.f5844c, this.f5862d.f5842a);
                        this.f5862d.f5842a = 0L;
                    }
                    synchronized (this.f5862d.f5845d) {
                        this.f5862d.f5845d.f5954j += a;
                        if (this.f5862d.f5845d.f5954j >= this.f5862d.f5845d.f5956l.m24666d() / 2) {
                            this.f5862d.f5845d.m24633a(0, this.f5862d.f5845d.f5954j);
                            this.f5862d.f5845d.f5954j = 0L;
                        }
                    }
                    return a;
                }
            }
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
    }

    /* renamed from: b */
    private void m24700b() {
        this.f5862d.f5847f.m24352c();
        while (this.f5864f.m24331b() == 0 && !this.f5861b && !this.f5860a && this.f5862d.f5849h == null) {
            try {
                this.f5862d.m24703i();
            } finally {
                this.f5862d.f5847f.m24699b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24701a(BufferedSource hVar, long j) {
        boolean z;
        boolean z2;
        boolean z3;
        if (f5859c || !Thread.holdsLock(this.f5862d)) {
            while (j > 0) {
                synchronized (this.f5862d) {
                    z = this.f5861b;
                    z2 = true;
                    z3 = this.f5864f.m24331b() + j > this.f5865g;
                }
                if (z3) {
                    hVar.mo24278f(j);
                    this.f5862d.m24712b(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    hVar.mo24278f(j);
                    return;
                } else {
                    long a = hVar.mo24249a(this.f5863e, j);
                    if (a != -1) {
                        j -= a;
                        synchronized (this.f5862d) {
                            if (this.f5864f.m24331b() != 0) {
                                z2 = false;
                            }
                            this.f5864f.m24336a(this.f5863e);
                            if (z2) {
                                this.f5862d.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
            return;
        }
        throw new AssertionError();
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f5862d.f5847f;
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f5862d) {
            this.f5860a = true;
            this.f5864f.m24317o();
            this.f5862d.notifyAll();
        }
        this.f5862d.m24705g();
    }
}
