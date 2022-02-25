package com.p018b.p029b;

import android.support.p003v4.media.session.PlaybackStateCompat;
import com.tencent.smtt.sdk.TbsListener;
import java.io.EOFException;
import java.nio.charset.Charset;
import org.apache.tools.tar.TarConstants;

/* renamed from: com.b.b.f */
/* loaded from: classes.dex */
public final class Buffer implements BufferedSink, BufferedSource, Cloneable {

    /* renamed from: c */
    private static final byte[] f6420c = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: a */
    Segment f6421a;

    /* renamed from: b */
    long f6422b;

    @Override // com.p018b.p029b.BufferedSink, com.p018b.p029b.BufferedSource
    /* renamed from: c */
    public final Buffer mo24284c() {
        return this;
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.p018b.p029b.BufferedSink, com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: p */
    public final /* bridge */ /* synthetic */ BufferedSink mo24290p() {
        return this;
    }

    /* renamed from: b */
    public final long m24331b() {
        return this.f6422b;
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: d */
    public final boolean mo24282d() {
        return this.f6422b == 0;
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final void mo24288a(long j) {
        if (this.f6422b < j) {
            throw new EOFException();
        }
    }

    /* renamed from: a */
    public final Buffer m24338a(Buffer fVar, long j, long j2) {
        if (fVar != null) {
            C0916aa.m24347a(this.f6422b, j, j2);
            if (j2 == 0) {
                return this;
            }
            fVar.f6422b += j2;
            Segment tVar = this.f6421a;
            while (j >= tVar.f6453c - tVar.f6452b) {
                j -= tVar.f6453c - tVar.f6452b;
                tVar = tVar.f6456f;
            }
            while (j2 > 0) {
                Segment tVar2 = new Segment(tVar);
                tVar2.f6452b = (int) (tVar2.f6452b + j);
                tVar2.f6453c = Math.min(tVar2.f6452b + ((int) j2), tVar2.f6453c);
                Segment tVar3 = fVar.f6421a;
                if (tVar3 == null) {
                    tVar2.f6457g = tVar2;
                    tVar2.f6456f = tVar2;
                    fVar.f6421a = tVar2;
                } else {
                    tVar3.f6457g.m24271a(tVar2);
                }
                j2 -= tVar2.f6453c - tVar2.f6452b;
                tVar = tVar.f6456f;
                j = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: e */
    public final byte mo24281e() {
        if (this.f6422b != 0) {
            Segment tVar = this.f6421a;
            int i = tVar.f6452b;
            int i2 = tVar.f6453c;
            int i3 = i + 1;
            byte b = tVar.f6451a[i];
            this.f6422b--;
            if (i3 == i2) {
                this.f6421a = tVar.m24272a();
                SegmentPool.m24268a(tVar);
            } else {
                tVar.f6452b = i3;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    /* renamed from: b */
    public final byte m24329b(long j) {
        C0916aa.m24347a(this.f6422b, j, 1L);
        Segment tVar = this.f6421a;
        while (true) {
            long j2 = tVar.f6453c - tVar.f6452b;
            if (j < j2) {
                return tVar.f6451a[tVar.f6452b + ((int) j)];
            }
            j -= j2;
            tVar = tVar.f6456f;
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: f */
    public final short mo24279f() {
        if (this.f6422b >= 2) {
            Segment tVar = this.f6421a;
            int i = tVar.f6452b;
            int i2 = tVar.f6453c;
            if (i2 - i < 2) {
                return (short) (((mo24281e() & 255) << 8) | (mo24281e() & 255));
            }
            byte[] bArr = tVar.f6451a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.f6422b -= 2;
            if (i4 == i2) {
                this.f6421a = tVar.m24272a();
                SegmentPool.m24268a(tVar);
            } else {
                tVar.f6452b = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException("size < 2: " + this.f6422b);
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: g */
    public final int mo24277g() {
        if (this.f6422b >= 4) {
            Segment tVar = this.f6421a;
            int i = tVar.f6452b;
            int i2 = tVar.f6453c;
            if (i2 - i < 4) {
                return ((mo24281e() & 255) << 24) | ((mo24281e() & 255) << 16) | ((mo24281e() & 255) << 8) | (mo24281e() & 255);
            }
            byte[] bArr = tVar.f6451a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            int i6 = i4 + 1;
            int i7 = i5 | ((bArr[i4] & 255) << 8);
            int i8 = i6 + 1;
            int i9 = i7 | (bArr[i6] & 255);
            this.f6422b -= 4;
            if (i8 == i2) {
                this.f6421a = tVar.m24272a();
                SegmentPool.m24268a(tVar);
            } else {
                tVar.f6452b = i8;
            }
            return i9;
        }
        throw new IllegalStateException("size < 4: " + this.f6422b);
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: h */
    public final short mo24276h() {
        return C0916aa.m24345a(mo24279f());
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: i */
    public final int mo24275i() {
        return C0916aa.m24348a(mo24277g());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a1 A[EDGE_INSN: B:42:0x00a1->B:37:0x00a1 ?: BREAK  , SYNTHETIC] */
    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long mo24274j() {
        /*
            r14 = this;
            long r0 = r14.f6422b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x00a8
            r0 = 0
            r0 = r2
            r4 = 0
            r5 = 0
        L_0x000c:
            com.b.b.t r6 = r14.f6421a
            byte[] r7 = r6.f6451a
            int r8 = r6.f6452b
            int r9 = r6.f6453c
        L_0x0014:
            if (r8 >= r9) goto L_0x008d
            byte r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L_0x0023
            r11 = 57
            if (r10 > r11) goto L_0x0023
            int r11 = r10 + (-48)
            goto L_0x003c
        L_0x0023:
            r11 = 97
            if (r10 < r11) goto L_0x0030
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L_0x0030
            int r11 = r10 + (-97)
            int r11 = r11 + 10
            goto L_0x003c
        L_0x0030:
            r11 = 65
            if (r10 < r11) goto L_0x0071
            r11 = 70
            if (r10 > r11) goto L_0x0071
            int r11 = r10 + (-65)
            int r11 = r11 + 10
        L_0x003c:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r0
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L_0x004c
            r10 = 4
            long r0 = r0 << r10
            long r10 = (long) r11
            long r0 = r0 | r10
            int r8 = r8 + 1
            int r4 = r4 + 1
            goto L_0x0014
        L_0x004c:
            com.b.b.f r2 = new com.b.b.f
            r2.<init>()
            com.b.b.f r0 = r2.mo24292i(r0)
            com.b.b.f r0 = r0.mo24293h(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Number too large: "
            r2.<init>(r3)
            java.lang.String r0 = r0.m24319l()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0071:
            if (r4 == 0) goto L_0x0075
            r5 = 1
            goto L_0x008d
        L_0x0075:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.<init>(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x008d:
            if (r8 != r9) goto L_0x0099
            com.b.b.t r7 = r6.m24272a()
            r14.f6421a = r7
            com.p018b.p029b.SegmentPool.m24268a(r6)
            goto L_0x009b
        L_0x0099:
            r6.f6452b = r8
        L_0x009b:
            if (r5 != 0) goto L_0x00a1
            com.b.b.t r6 = r14.f6421a
            if (r6 != 0) goto L_0x000c
        L_0x00a1:
            long r2 = r14.f6422b
            long r4 = (long) r4
            long r2 = r2 - r4
            r14.f6422b = r2
            return r0
        L_0x00a8:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p029b.Buffer.mo24274j():long");
    }

    /* renamed from: k */
    public final ByteString m24321k() {
        return new ByteString(m24318n());
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: c */
    public final ByteString mo24283c(long j) {
        return new ByteString(mo24280e(j));
    }

    /* renamed from: l */
    public final String m24319l() {
        try {
            return m24339a(this.f6422b, C0916aa.f6413a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: k */
    private String m24320k(long j) {
        return m24339a(j, C0916aa.f6413a);
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final String mo24287a(Charset charset) {
        try {
            return m24339a(this.f6422b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    private String m24339a(long j, Charset charset) {
        C0916aa.m24347a(this.f6422b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            Segment tVar = this.f6421a;
            if (tVar.f6452b + j > tVar.f6453c) {
                return new String(mo24280e(j), charset);
            }
            String str = new String(tVar.f6451a, tVar.f6452b, (int) j, charset);
            tVar.f6452b = (int) (tVar.f6452b + j);
            this.f6422b -= j;
            if (tVar.f6452b == tVar.f6453c) {
                this.f6421a = tVar.m24272a();
                SegmentPool.m24268a(tVar);
            }
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final String m24325d(long j) {
        if (j > 0) {
            long j2 = j - 1;
            if (m24329b(j2) == 13) {
                String k = m24320k(j2);
                mo24278f(2L);
                return k;
            }
        }
        String k2 = m24320k(j);
        mo24278f(1L);
        return k2;
    }

    /* renamed from: n */
    public final byte[] m24318n() {
        try {
            return mo24280e(this.f6422b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: e */
    public final byte[] mo24280e(long j) {
        C0916aa.m24347a(this.f6422b, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            m24327c(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    /* renamed from: c */
    private void m24327c(byte[] bArr) {
        int i;
        int i2 = 0;
        while (i2 < bArr.length) {
            int length = bArr.length - i2;
            C0916aa.m24347a(bArr.length, i2, length);
            Segment tVar = this.f6421a;
            if (tVar == null) {
                i = -1;
            } else {
                i = Math.min(length, tVar.f6453c - tVar.f6452b);
                System.arraycopy(tVar.f6451a, tVar.f6452b, bArr, i2, i);
                tVar.f6452b += i;
                this.f6422b -= i;
                if (tVar.f6452b == tVar.f6453c) {
                    this.f6421a = tVar.m24272a();
                    SegmentPool.m24268a(tVar);
                }
            }
            if (i != -1) {
                i2 += i;
            } else {
                throw new EOFException();
            }
        }
    }

    /* renamed from: o */
    public final void m24317o() {
        try {
            mo24278f(this.f6422b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: f */
    public final void mo24278f(long j) {
        Segment tVar;
        while (j > 0) {
            if (this.f6421a != null) {
                int min = (int) Math.min(j, tVar.f6453c - this.f6421a.f6452b);
                long j2 = min;
                this.f6422b -= j2;
                j -= j2;
                this.f6421a.f6452b += min;
                if (this.f6421a.f6452b == this.f6421a.f6453c) {
                    Segment tVar2 = this.f6421a;
                    this.f6421a = tVar2.m24272a();
                    SegmentPool.m24268a(tVar2);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    /* renamed from: a */
    public final Buffer m24337a(ByteString iVar) {
        if (iVar != null) {
            iVar.mo24262a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    /* renamed from: a */
    public final Buffer mo24298b(String str) {
        return m24334a(str, 0, str.length());
    }

    /* renamed from: a */
    public final Buffer m24334a(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 <= str.length()) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    Segment e = m24324e(1);
                    byte[] bArr = e.f6451a;
                    int i3 = e.f6453c - i;
                    int min = Math.min(i2, 8192 - i3);
                    i++;
                    bArr[i + i3] = (byte) charAt;
                    while (i < min) {
                        char charAt2 = str.charAt(i);
                        if (charAt2 >= 128) {
                            break;
                        }
                        i++;
                        bArr[i + i3] = (byte) charAt2;
                    }
                    int i4 = (i3 + i) - e.f6453c;
                    e.f6453c += i4;
                    this.f6422b += i4;
                } else if (charAt < 2048) {
                    mo24293h((charAt >> 6) | 192);
                    mo24293h((charAt & '?') | 128);
                    i++;
                } else if (charAt < 55296 || charAt > 57343) {
                    mo24293h((charAt >> '\f') | TbsListener.ErrorCode.EXCEED_INCR_UPDATE);
                    mo24293h(((charAt >> 6) & 63) | 128);
                    mo24293h((charAt & '?') | 128);
                    i++;
                } else {
                    int i5 = i + 1;
                    char charAt3 = i5 < i2 ? str.charAt(i5) : (char) 0;
                    if (charAt > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                        mo24293h(63);
                        i = i5;
                    } else {
                        int i6 = (((charAt & 10239) << 10) | (9215 & charAt3)) + 65536;
                        mo24293h((i6 >> 18) | TbsListener.ErrorCode.TPATCH_VERSION_FAILED);
                        mo24293h(((i6 >> 12) & 63) | 128);
                        mo24293h(((i6 >> 6) & 63) | 128);
                        mo24293h((i6 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
    }

    /* renamed from: a */
    public final Buffer m24340a(int i) {
        if (i < 128) {
            mo24293h(i);
        } else if (i < 2048) {
            mo24293h((i >> 6) | 192);
            mo24293h((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                mo24293h((i >> 12) | TbsListener.ErrorCode.EXCEED_INCR_UPDATE);
                mo24293h(((i >> 6) & 63) | 128);
                mo24293h((i & 63) | 128);
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
        } else if (i <= 1114111) {
            mo24293h((i >> 18) | TbsListener.ErrorCode.TPATCH_VERSION_FAILED);
            mo24293h(((i >> 12) & 63) | 128);
            mo24293h(((i >> 6) & 63) | 128);
            mo24293h((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    /* renamed from: a */
    public final Buffer mo24297b(byte[] bArr) {
        if (bArr != null) {
            return mo24296b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public final Buffer mo24296b(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            C0916aa.m24347a(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                Segment e = m24324e(1);
                int min = Math.min(i3 - i, 8192 - e.f6453c);
                System.arraycopy(bArr, i, e.f6451a, e.f6453c, min);
                i += min;
                e.f6453c += min;
            }
            this.f6422b += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public final long m24336a(Source xVar) {
        if (xVar != null) {
            long j = 0;
            while (true) {
                long a = xVar.mo24249a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                if (a == -1) {
                    return j;
                }
                j += a;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    /* renamed from: b */
    public final Buffer mo24293h(int i) {
        Segment e = m24324e(1);
        byte[] bArr = e.f6451a;
        int i2 = e.f6453c;
        e.f6453c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f6422b++;
        return this;
    }

    /* renamed from: c */
    public final Buffer mo24294g(int i) {
        Segment e = m24324e(2);
        byte[] bArr = e.f6451a;
        int i2 = e.f6453c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        e.f6453c = i3 + 1;
        this.f6422b += 2;
        return this;
    }

    /* renamed from: d */
    public final Buffer mo24295f(int i) {
        Segment e = m24324e(4);
        byte[] bArr = e.f6451a;
        int i2 = e.f6453c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        e.f6453c = i5 + 1;
        this.f6422b += 4;
        return this;
    }

    /* renamed from: g */
    public final Buffer mo24291j(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            return mo24293h(48);
        }
        boolean z = false;
        int i2 = 1;
        if (i < 0) {
            j = -j;
            if (j < 0) {
                return mo24298b("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            i2 = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i2 = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i2 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        Segment e = m24324e(i2);
        byte[] bArr = e.f6451a;
        int i3 = e.f6453c + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = f6420c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        e.f6453c += i2;
        this.f6422b += i2;
        return this;
    }

    /* renamed from: h */
    public final Buffer mo24292i(long j) {
        if (j == 0) {
            return mo24293h(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment e = m24324e(numberOfTrailingZeros);
        byte[] bArr = e.f6451a;
        int i = e.f6453c;
        for (int i2 = (e.f6453c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f6420c[(int) (15 & j)];
            j >>>= 4;
        }
        e.f6453c += numberOfTrailingZeros;
        this.f6422b += numberOfTrailingZeros;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final Segment m24324e(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        }
        Segment tVar = this.f6421a;
        if (tVar == null) {
            this.f6421a = SegmentPool.m24269a();
            Segment tVar2 = this.f6421a;
            tVar2.f6457g = tVar2;
            tVar2.f6456f = tVar2;
            return tVar2;
        }
        Segment tVar3 = tVar.f6457g;
        return (tVar3.f6453c + i > 8192 || !tVar3.f6455e) ? tVar3.m24271a(SegmentPool.m24269a()) : tVar3;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        Segment tVar;
        if (fVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (fVar != this) {
            C0916aa.m24347a(fVar.f6422b, 0L, j);
            while (j > 0) {
                int i = 0;
                if (j < fVar.f6421a.f6453c - fVar.f6421a.f6452b) {
                    Segment tVar2 = this.f6421a;
                    Segment tVar3 = tVar2 != null ? tVar2.f6457g : null;
                    if (tVar3 != null && tVar3.f6455e) {
                        if ((tVar3.f6453c + j) - (tVar3.f6454d ? 0 : tVar3.f6452b) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            fVar.f6421a.m24270a(tVar3, (int) j);
                            fVar.f6422b -= j;
                            this.f6422b += j;
                            return;
                        }
                    }
                    Segment tVar4 = fVar.f6421a;
                    int i2 = (int) j;
                    if (i2 <= 0 || i2 > tVar4.f6453c - tVar4.f6452b) {
                        throw new IllegalArgumentException();
                    }
                    if (i2 >= 1024) {
                        tVar = new Segment(tVar4);
                    } else {
                        tVar = SegmentPool.m24269a();
                        System.arraycopy(tVar4.f6451a, tVar4.f6452b, tVar.f6451a, 0, i2);
                    }
                    tVar.f6453c = tVar.f6452b + i2;
                    tVar4.f6452b += i2;
                    tVar4.f6457g.m24271a(tVar);
                    fVar.f6421a = tVar;
                }
                Segment tVar5 = fVar.f6421a;
                long j2 = tVar5.f6453c - tVar5.f6452b;
                fVar.f6421a = tVar5.m24272a();
                Segment tVar6 = this.f6421a;
                if (tVar6 == null) {
                    this.f6421a = tVar5;
                    Segment tVar7 = this.f6421a;
                    tVar7.f6457g = tVar7;
                    tVar7.f6456f = tVar7;
                } else {
                    Segment a = tVar6.f6457g.m24271a(tVar5);
                    if (a.f6457g == a) {
                        throw new IllegalStateException();
                    } else if (a.f6457g.f6455e) {
                        int i3 = a.f6453c - a.f6452b;
                        int i4 = 8192 - a.f6457g.f6453c;
                        if (!a.f6457g.f6454d) {
                            i = a.f6457g.f6452b;
                        }
                        if (i3 <= i4 + i) {
                            a.m24270a(a.f6457g, i3);
                            a.m24272a();
                            SegmentPool.m24268a(a);
                        }
                    }
                }
                fVar.f6422b -= j2;
                this.f6422b += j2;
                j -= j2;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (fVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.f6422b;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            fVar.mo24251a_(this, j);
            return j;
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: a */
    public final long mo24289a(byte b) {
        return m24341a(b, 0L);
    }

    /* renamed from: a */
    public final long m24341a(byte b, long j) {
        long j2 = 0;
        if (j >= 0) {
            Segment tVar = this.f6421a;
            if (tVar == null) {
                return -1L;
            }
            j2 = this.f6422b;
            if (j2 - j >= j) {
                while (true) {
                    j2 = (tVar.f6453c - tVar.f6452b) + j2;
                    if (j2 >= j) {
                        break;
                    }
                    tVar = tVar.f6456f;
                }
            } else {
                while (j2 > j) {
                    tVar = tVar.f6457g;
                    j2 -= tVar.f6453c - tVar.f6452b;
                }
            }
            while (j2 < this.f6422b) {
                byte[] bArr = tVar.f6451a;
                int i = tVar.f6453c;
                for (int i2 = (int) ((tVar.f6452b + j) - j2); i2 < i; i2++) {
                    if (bArr[i2] == b) {
                        return (i2 - tVar.f6452b) + j2;
                    }
                }
                j = (tVar.f6453c - tVar.f6452b) + j2;
                tVar = tVar.f6456f;
                j2 = j;
            }
            return -1L;
        }
        throw new IllegalArgumentException("fromIndex < 0");
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: b */
    public final boolean mo24285b(ByteString iVar) {
        int g = iVar.mo24255g();
        if (g < 0 || this.f6422b - 0 < g || iVar.mo24255g() - 0 < g) {
            return false;
        }
        for (int i = 0; i < g; i++) {
            if (m24329b(i + 0) != iVar.mo24266a(i + 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return Timeout.f6462b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer fVar = (Buffer) obj;
        long j = this.f6422b;
        if (j != fVar.f6422b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment tVar = this.f6421a;
        Segment tVar2 = fVar.f6421a;
        int i = tVar.f6452b;
        int i2 = tVar2.f6452b;
        while (j2 < this.f6422b) {
            long min = Math.min(tVar.f6453c - i, tVar2.f6453c - i2);
            int i3 = i2;
            int i4 = i;
            for (int i5 = 0; i5 < min; i5++) {
                i4++;
                i3++;
                if (tVar.f6451a[i4] != tVar2.f6451a[i3]) {
                    return false;
                }
            }
            if (i4 == tVar.f6453c) {
                tVar = tVar.f6456f;
                i = tVar.f6452b;
            } else {
                i = i4;
            }
            if (i3 == tVar2.f6453c) {
                tVar2 = tVar2.f6456f;
                i2 = tVar2.f6452b;
            } else {
                i2 = i3;
            }
            j2 += min;
        }
        return true;
    }

    public final int hashCode() {
        Segment tVar = this.f6421a;
        if (tVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = tVar.f6453c;
            for (int i3 = tVar.f6452b; i3 < i2; i3++) {
                i = (i * 31) + tVar.f6451a[i3];
            }
            tVar = tVar.f6456f;
        } while (tVar != this.f6421a);
        return i;
    }

    @Override // com.p018b.p029b.BufferedSource
    /* renamed from: m */
    public final String mo24273m() {
        long a = m24341a((byte) 10, 0L);
        if (a != -1) {
            return m24325d(a);
        }
        Buffer fVar = new Buffer();
        m24338a(fVar, 0L, Math.min(32L, this.f6422b));
        throw new EOFException("\\n not found: size=" + this.f6422b + " content=" + fVar.m24321k().mo24257e() + "…");
    }

    public final String toString() {
        ByteString iVar;
        long j = this.f6422b;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                iVar = ByteString.f6424b;
            } else {
                iVar = new SegmentedByteString(this, i);
            }
            return iVar.toString();
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f6422b);
    }

    public final /* synthetic */ Object clone() {
        Buffer fVar = new Buffer();
        if (this.f6422b == 0) {
            return fVar;
        }
        fVar.f6421a = new Segment(this.f6421a);
        Segment tVar = fVar.f6421a;
        tVar.f6457g = tVar;
        tVar.f6456f = tVar;
        Segment tVar2 = this.f6421a;
        while (true) {
            tVar2 = tVar2.f6456f;
            if (tVar2 != this.f6421a) {
                fVar.f6421a.f6457g.m24271a(new Segment(tVar2));
            } else {
                fVar.f6422b = this.f6422b;
                return fVar;
            }
        }
    }
}
