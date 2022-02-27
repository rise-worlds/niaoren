package com.p018b.p019a.p020a.p025e;

import android.support.v4.media.session.PlaybackStateCompat;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.Sink;
import com.p018b.p029b.Timeout;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Stream.java */
/* renamed from: com.b.a.a.e.ac */
/* loaded from: classes.dex */
public final class C0866ac implements Sink {

    /* renamed from: c */
    static final /* synthetic */ boolean f5854c = !Http2Stream.class.desiredAssertionStatus();

    /* renamed from: a */
    boolean f5855a;

    /* renamed from: b */
    boolean f5856b;

    /* renamed from: d */
    final /* synthetic */ Http2Stream f5857d;

    /* renamed from: e */
    private final Buffer f5858e = new Buffer();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0866ac(Http2Stream abVar) {
        this.f5857d = abVar;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        if (f5854c || !Thread.holdsLock(this.f5857d)) {
            this.f5858e.mo24251a_(fVar, j);
            while (this.f5858e.m24331b() >= PlaybackStateCompat.ACTION_PREPARE) {
                m24702a(false);
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    private void m24702a(boolean z) {
        long min;
        synchronized (this.f5857d) {
            this.f5857d.f5848g.m24352c();
            while (this.f5857d.f5843b <= 0 && !this.f5856b && !this.f5855a && this.f5857d.f5849h == null) {
                this.f5857d.m24703i();
            }
            this.f5857d.f5848g.m24699b();
            this.f5857d.m24704h();
            min = Math.min(this.f5857d.f5843b, this.f5858e.m24331b());
            this.f5857d.f5843b -= min;
        }
        this.f5857d.f5848g.m24352c();
        try {
            this.f5857d.f5845d.m24628a(this.f5857d.f5844c, z && min == this.f5858e.m24331b(), this.f5858e, min);
        } finally {
            this.f5857d.f5848g.m24699b();
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
        if (f5854c || !Thread.holdsLock(this.f5857d)) {
            synchronized (this.f5857d) {
                this.f5857d.m24704h();
            }
            while (this.f5858e.m24331b() > 0) {
                m24702a(false);
                this.f5857d.f5845d.f5960p.m24689b();
            }
            return;
        }
        throw new AssertionError();
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f5857d.f5848g;
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (f5854c || !Thread.holdsLock(this.f5857d)) {
            synchronized (this.f5857d) {
                if (!this.f5855a) {
                    if (!this.f5857d.f5846e.f5856b) {
                        if (this.f5858e.m24331b() > 0) {
                            while (this.f5858e.m24331b() > 0) {
                                m24702a(true);
                            }
                        } else {
                            this.f5857d.f5845d.m24628a(this.f5857d.f5844c, true, (Buffer) null, 0L);
                        }
                    }
                    synchronized (this.f5857d) {
                        this.f5855a = true;
                    }
                    this.f5857d.f5845d.f5960p.m24689b();
                    this.f5857d.m24705g();
                    return;
                }
                return;
            }
        }
        throw new AssertionError();
    }
}
