package com.p018b.p029b;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* renamed from: com.b.b.l */
/* loaded from: classes.dex */
public final class GzipSource implements Source {

    /* renamed from: b */
    private final BufferedSource f6431b;

    /* renamed from: c */
    private final Inflater f6432c;

    /* renamed from: d */
    private final InflaterSource f6433d;

    /* renamed from: a */
    private int f6430a = 0;

    /* renamed from: e */
    private final CRC32 f6434e = new CRC32();

    public GzipSource(Source xVar) {
        if (xVar != null) {
            this.f6432c = new Inflater(true);
            this.f6431b = Okio.m24305a(xVar);
            this.f6433d = new InflaterSource(this.f6431b, this.f6432c);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        long j2;
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (i == 0) {
            return 0L;
        } else {
            if (this.f6430a == 0) {
                this.f6431b.mo24288a(10L);
                byte b = this.f6431b.mo24284c().m24329b(3L);
                boolean z = ((b >> 1) & 1) == 1;
                if (z) {
                    m24309a(this.f6431b.mo24284c(), 0L, 10L);
                }
                m24308a("ID1ID2", 8075, this.f6431b.mo24279f());
                this.f6431b.mo24278f(8L);
                if (((b >> 2) & 1) == 1) {
                    this.f6431b.mo24288a(2L);
                    if (z) {
                        m24309a(this.f6431b.mo24284c(), 0L, 2L);
                    }
                    long h = this.f6431b.mo24284c().mo24276h();
                    this.f6431b.mo24288a(h);
                    if (z) {
                        j2 = h;
                        m24309a(this.f6431b.mo24284c(), 0L, h);
                    } else {
                        j2 = h;
                    }
                    this.f6431b.mo24278f(j2);
                }
                if (((b >> 3) & 1) == 1) {
                    long a = this.f6431b.mo24289a((byte) 0);
                    if (a != -1) {
                        if (z) {
                            m24309a(this.f6431b.mo24284c(), 0L, a + 1);
                        }
                        this.f6431b.mo24278f(a + 1);
                    } else {
                        throw new EOFException();
                    }
                }
                if (((b >> 4) & 1) == 1) {
                    long a2 = this.f6431b.mo24289a((byte) 0);
                    if (a2 != -1) {
                        if (z) {
                            m24309a(this.f6431b.mo24284c(), 0L, a2 + 1);
                        }
                        this.f6431b.mo24278f(a2 + 1);
                    } else {
                        throw new EOFException();
                    }
                }
                if (z) {
                    m24308a("FHCRC", this.f6431b.mo24276h(), (short) this.f6434e.getValue());
                    this.f6434e.reset();
                }
                this.f6430a = 1;
            }
            if (this.f6430a == 1) {
                long j3 = fVar.f6422b;
                long a3 = this.f6433d.mo24249a(fVar, j);
                if (a3 != -1) {
                    m24309a(fVar, j3, a3);
                    return a3;
                }
                this.f6430a = 2;
            }
            if (this.f6430a == 2) {
                m24308a("CRC", this.f6431b.mo24275i(), (int) this.f6434e.getValue());
                m24308a("ISIZE", this.f6431b.mo24275i(), (int) this.f6432c.getBytesWritten());
                this.f6430a = 3;
                if (!this.f6431b.mo24282d()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1L;
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6431b.mo24250a();
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6433d.close();
    }

    /* renamed from: a */
    private void m24309a(Buffer fVar, long j, long j2) {
        int i;
        Segment tVar = fVar.f6421a;
        while (j >= tVar.f6453c - tVar.f6452b) {
            j -= tVar.f6453c - tVar.f6452b;
            tVar = tVar.f6456f;
        }
        while (j2 > 0) {
            int min = (int) Math.min(tVar.f6453c - i, j2);
            this.f6434e.update(tVar.f6451a, (int) (tVar.f6452b + j), min);
            j2 -= min;
            tVar = tVar.f6456f;
            j = 0;
        }
    }

    /* renamed from: a */
    private static void m24308a(String str, int i, int i2) {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
