package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSink;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.b.a.a.e.af */
/* loaded from: classes.dex */
public final class Http2Writer implements Closeable {

    /* renamed from: b */
    private static final Logger f5867b = Logger.getLogger(Http2.class.getName());

    /* renamed from: c */
    private final BufferedSink f5869c;

    /* renamed from: d */
    private final boolean f5870d;

    /* renamed from: g */
    private boolean f5873g;

    /* renamed from: e */
    private final Buffer f5871e = new Buffer();

    /* renamed from: a */
    final C0872f f5868a = new C0872f(this.f5871e);

    /* renamed from: f */
    private int f5872f = 16384;

    public Http2Writer(BufferedSink gVar, boolean z) {
        this.f5869c = gVar;
        this.f5870d = z;
    }

    /* renamed from: a */
    public final synchronized void m24698a() {
        if (this.f5873g) {
            throw new IOException("closed");
        } else if (this.f5870d) {
            if (f5867b.isLoggable(Level.FINE)) {
                f5867b.fine(Util.m24755a(">> CONNECTION %s", Http2.f5925a.mo24257e()));
            }
            this.f5869c.mo24297b(Http2.f5925a.mo24254h());
            this.f5869c.flush();
        }
    }

    /* renamed from: a */
    public final synchronized void m24693a(Settings alVar) {
        if (!this.f5873g) {
            this.f5872f = alVar.m24667c(this.f5872f);
            if (alVar.m24670b() != -1) {
                this.f5868a.m24650a(alVar.m24670b());
            }
            m24697a(0, 0, (byte) 4, (byte) 1);
            this.f5869c.flush();
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: b */
    public final synchronized void m24689b() {
        if (!this.f5873g) {
            this.f5869c.flush();
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: a */
    public final synchronized void m24690a(boolean z, int i, List<Header> list) {
        if (this.f5873g) {
            throw new IOException("closed");
        } else if (!this.f5873g) {
            this.f5868a.m24646a(list);
            long b = this.f5871e.m24331b();
            int min = (int) Math.min(this.f5872f, b);
            long j = min;
            int i2 = (b > j ? 1 : (b == j ? 0 : -1));
            byte b2 = i2 == 0 ? (byte) 4 : (byte) 0;
            if (z) {
                b2 = (byte) (b2 | 1);
            }
            m24697a(i, min, (byte) 1, b2);
            this.f5869c.mo24251a_(this.f5871e, j);
            if (i2 > 0) {
                m24688b(i, b - j);
            }
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: a */
    public final synchronized void m24695a(int i, ErrorCode bVar) {
        if (this.f5873g) {
            throw new IOException("closed");
        } else if (bVar.f5895g != -1) {
            m24697a(i, 4, (byte) 3, (byte) 0);
            this.f5869c.mo24295f(bVar.f5895g);
            this.f5869c.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: c */
    public final int m24686c() {
        return this.f5872f;
    }

    /* renamed from: a */
    public final synchronized void m24691a(boolean z, int i, Buffer fVar, int i2) {
        if (!this.f5873g) {
            m24697a(i, i2, (byte) 0, z ? (byte) 1 : (byte) 0);
            if (i2 > 0) {
                this.f5869c.mo24251a_(fVar, i2);
            }
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: b */
    public final synchronized void m24687b(Settings alVar) {
        if (!this.f5873g) {
            int i = 0;
            m24697a(0, alVar.m24673a() * 6, (byte) 4, (byte) 0);
            while (i < 10) {
                if (alVar.m24672a(i)) {
                    this.f5869c.mo24294g(i == 4 ? 3 : i == 7 ? 4 : i);
                    this.f5869c.mo24295f(alVar.m24669b(i));
                }
                i++;
            }
            this.f5869c.flush();
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: a */
    public final synchronized void m24692a(boolean z, int i, int i2) {
        if (!this.f5873g) {
            m24697a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f5869c.mo24295f(i);
            this.f5869c.mo24295f(i2);
            this.f5869c.flush();
        } else {
            throw new IOException("closed");
        }
    }

    /* renamed from: a */
    public final synchronized void m24694a(int i, ErrorCode bVar, byte[] bArr) {
        if (this.f5873g) {
            throw new IOException("closed");
        } else if (bVar.f5895g != -1) {
            m24697a(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.f5869c.mo24295f(i);
            this.f5869c.mo24295f(bVar.f5895g);
            if (bArr.length > 0) {
                this.f5869c.mo24297b(bArr);
            }
            this.f5869c.flush();
        } else {
            throw Http2.m24644a("errorCode.httpCode == -1", new Object[0]);
        }
    }

    /* renamed from: a */
    public final synchronized void m24696a(int i, long j) {
        if (this.f5873g) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw Http2.m24644a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        } else {
            m24697a(i, 4, (byte) 8, (byte) 0);
            this.f5869c.mo24295f((int) j);
            this.f5869c.flush();
        }
    }

    /* renamed from: a */
    private void m24697a(int i, int i2, byte b, byte b2) {
        if (f5867b.isLoggable(Level.FINE)) {
            f5867b.fine(Http2.m24643a(false, i, i2, b, b2));
        }
        int i3 = this.f5872f;
        if (i2 > i3) {
            throw Http2.m24644a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
        } else if ((Integer.MIN_VALUE & i) == 0) {
            BufferedSink gVar = this.f5869c;
            gVar.mo24293h((i2 >>> 16) & 255);
            gVar.mo24293h((i2 >>> 8) & 255);
            gVar.mo24293h(i2 & 255);
            this.f5869c.mo24293h(b & 255);
            this.f5869c.mo24293h(b2 & 255);
            this.f5869c.mo24295f(i & Integer.MAX_VALUE);
        } else {
            throw Http2.m24644a("reserved bit set: %s", Integer.valueOf(i));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f5873g = true;
        this.f5869c.close();
    }

    /* renamed from: b */
    private void m24688b(int i, long j) {
        while (j > 0) {
            int min = (int) Math.min(this.f5872f, j);
            long j2 = min;
            j -= j2;
            m24697a(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f5869c.mo24251a_(this.f5871e, j2);
        }
    }
}
