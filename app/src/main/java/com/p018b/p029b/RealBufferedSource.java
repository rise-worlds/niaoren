package com.p018b.p029b;

import android.support.p003v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.b.b.s */
/* loaded from: classes.dex */
public final class RealBufferedSource implements BufferedSource {

    /* renamed from: a */
    public final Buffer f6448a = new Buffer();

    /* renamed from: b */
    public final Source f6449b;

    /* renamed from: c */
    boolean f6450c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RealBufferedSource(Source xVar) {
        if (xVar != null) {
            this.f6449b = xVar;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: c */
    public final Buffer mo24284c() {
        return this.f6448a;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (fVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f6450c) {
            throw new IllegalStateException("closed");
        } else if (this.f6448a.f6422b == 0 && this.f6449b.mo24249a(this.f6448a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        } else {
            return this.f6448a.mo24249a(fVar, Math.min(j, this.f6448a.f6422b));
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: d */
    public final boolean mo24282d() {
        if (!this.f6450c) {
            return this.f6448a.mo24282d() && this.f6449b.mo24249a(this.f6448a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final void mo24288a(long j) {
        if (!m24286b(j)) {
            throw new EOFException();
        }
    }

    /* renamed from: b */
    private boolean m24286b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.f6450c) {
            while (this.f6448a.f6422b < j) {
                if (this.f6449b.mo24249a(this.f6448a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: e */
    public final byte mo24281e() {
        mo24288a(1L);
        return this.f6448a.mo24281e();
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: c */
    public final ByteString mo24283c(long j) {
        mo24288a(j);
        return this.f6448a.mo24283c(j);
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: e */
    public final byte[] mo24280e(long j) {
        mo24288a(j);
        return this.f6448a.mo24280e(j);
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final String mo24287a(Charset charset) {
        if (charset != null) {
            this.f6448a.m24336a(this.f6449b);
            return this.f6448a.mo24287a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: m */
    public final String mo24273m() {
        long a = mo24289a((byte) 10);
        if (a != -1) {
            return this.f6448a.m24325d(a);
        }
        Buffer fVar = new Buffer();
        Buffer fVar2 = this.f6448a;
        fVar2.m24338a(fVar, 0L, Math.min(32L, fVar2.f6422b));
        throw new EOFException("\\n not found: size=" + this.f6448a.f6422b + " content=" + fVar.m24321k().mo24257e() + "…");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: f */
    public final short mo24279f() {
        mo24288a(2L);
        return this.f6448a.mo24279f();
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: h */
    public final short mo24276h() {
        mo24288a(2L);
        return C0916aa.m24345a(this.f6448a.mo24279f());
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: g */
    public final int mo24277g() {
        mo24288a(4L);
        return this.f6448a.mo24277g();
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: i */
    public final int mo24275i() {
        mo24288a(4L);
        return C0916aa.m24348a(this.f6448a.mo24277g());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r1 == 0) goto L_0x0035;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0049, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.format("Expected leading [0-9a-fA-F] character but was %#x", java.lang.Byte.valueOf(r3)));
     */
    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long mo24274j() {
        /*
            r6 = this;
            r0 = 1
            r6.mo24288a(r0)
            r0 = 0
            r1 = 0
        L_0x0007:
            int r2 = r1 + 1
            long r3 = (long) r2
            boolean r3 = r6.m24286b(r3)
            if (r3 == 0) goto L_0x004a
            com.b.b.f r3 = r6.f6448a
            long r4 = (long) r1
            byte r3 = r3.m24329b(r4)
            r4 = 48
            if (r3 < r4) goto L_0x001f
            r4 = 57
            if (r3 <= r4) goto L_0x0030
        L_0x001f:
            r4 = 97
            if (r3 < r4) goto L_0x0027
            r4 = 102(0x66, float:1.43E-43)
            if (r3 <= r4) goto L_0x0030
        L_0x0027:
            r4 = 65
            if (r3 < r4) goto L_0x0032
            r4 = 70
            if (r3 <= r4) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r1 = r2
            goto L_0x0007
        L_0x0032:
            if (r1 == 0) goto L_0x0035
            goto L_0x004a
        L_0x0035:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2[r0] = r3
            java.lang.String r0 = "Expected leading [0-9a-fA-F] character but was %#x"
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r1.<init>(r0)
            throw r1
        L_0x004a:
            com.b.b.f r0 = r6.f6448a
            long r0 = r0.mo24274j()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p029b.RealBufferedSource.mo24274j():long");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: f */
    public final void mo24278f(long j) {
        if (!this.f6450c) {
            while (j > 0) {
                if (this.f6448a.f6422b == 0 && this.f6449b.mo24249a(this.f6448a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.f6448a.f6422b);
                this.f6448a.mo24278f(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: b */
    public final boolean mo24285b(ByteString iVar) {
        int g = iVar.mo24255g();
        if (this.f6450c) {
            throw new IllegalStateException("closed");
        } else if (g < 0 || iVar.mo24255g() - 0 < g) {
            return false;
        } else {
            for (int i = 0; i < g; i++) {
                long j = i + 0;
                if (!(m24286b(1 + j) && this.f6448a.m24329b(j) == iVar.mo24266a(i + 0))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f6450c) {
            this.f6450c = true;
            this.f6449b.close();
            this.f6448a.m24317o();
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6449b.mo24250a();
    }

    public final String toString() {
        return "buffer(" + this.f6449b + ")";
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final long mo24289a(byte b) {
        if (!this.f6450c) {
            long j = 0;
            while (true) {
                long a = this.f6448a.m24341a(b, j);
                if (a != -1) {
                    return a;
                }
                long j2 = this.f6448a.f6422b;
                if (this.f6449b.mo24249a(this.f6448a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1L;
                }
                j = Math.max(j, j2);
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }
}
