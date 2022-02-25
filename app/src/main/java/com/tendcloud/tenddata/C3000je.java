package com.tendcloud.tenddata;

import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.je */
/* loaded from: classes2.dex */
public final class C3000je {

    /* renamed from: a */
    public static final int f14315a = 4;

    /* renamed from: b */
    public static final int f14316b = 8;

    /* renamed from: c */
    private static final int f14317c = 3;

    /* renamed from: d */
    private final ByteBuffer f14318d;

    /* renamed from: a */
    public static int m15344a(double d) {
        return 8;
    }

    /* renamed from: a */
    public static int m15343a(float f) {
        return 4;
    }

    /* renamed from: a */
    public static int m15326a(boolean z) {
        return 1;
    }

    /* renamed from: b */
    public static int m15322b(int i) {
        return 4;
    }

    /* renamed from: c */
    public static int m15308c(long j) {
        return 8;
    }

    /* renamed from: d */
    public static int m15304d(long j) {
        return 8;
    }

    /* renamed from: e */
    public static int m15303e(int i) {
        return 4;
    }

    /* renamed from: f */
    public static int m15296f(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    /* renamed from: g */
    public static long m15293g(long j) {
        return (j >> 63) ^ (j << 1);
    }

    /* renamed from: h */
    public static int m15292h(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    /* renamed from: i */
    public static int m15290i(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private C3000je(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private C3000je(ByteBuffer byteBuffer) {
        this.f14318d = byteBuffer;
        this.f14318d.order(ByteOrder.LITTLE_ENDIAN);
    }

    /* renamed from: a */
    public static C3000je m15325a(byte[] bArr) {
        return m15324a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static C3000je m15324a(byte[] bArr, int i, int i2) {
        return new C3000je(bArr, i, i2);
    }

    /* renamed from: a */
    public void m15338a(int i, long j) {
        m15291h(i, 0);
        writeInt64NoTag(j);
    }

    /* renamed from: a */
    public void m15339a(int i, int i2) {
        m15291h(i, 0);
        writeInt32NoTag(i2);
    }

    /* renamed from: a */
    public void m15336a(int i, String str) {
        m15291h(i, 2);
        writeStringNoTag(str);
    }

    /* renamed from: a */
    public void m15334a(int i, byte[] bArr) {
        m15291h(i, 2);
        writeBytesNoTag(bArr);
    }

    public void writeDoubleNoTag(double d) {
        writeRawLittleEndian64(Double.doubleToLongBits(d));
    }

    public void writeFloatNoTag(float f) {
        writeRawLittleEndian32(Float.floatToIntBits(f));
    }

    public void writeUInt64NoTag(long j) {
        writeRawVarint64(j);
    }

    public void writeInt64NoTag(long j) {
        writeRawVarint64(j);
    }

    public void writeInt32NoTag(int i) {
        if (i >= 0) {
            writeRawVarint32(i);
        } else {
            writeRawVarint64(i);
        }
    }

    public void writeFixed64NoTag(long j) {
        writeRawLittleEndian64(j);
    }

    public void writeFixed32NoTag(int i) {
        writeRawLittleEndian32(i);
    }

    public void writeBoolNoTag(boolean z) {
        writeRawByte(z ? 1 : 0);
    }

    public void writeStringNoTag(String str) {
        try {
            int h = m15292h(str.length());
            if (h == m15292h(str.length() * 3)) {
                int position = this.f14318d.position();
                if (this.f14318d.remaining() >= h) {
                    this.f14318d.position(position + h);
                    m15329a(str, this.f14318d);
                    int position2 = this.f14318d.position();
                    this.f14318d.position(position);
                    writeRawVarint32((position2 - position) - h);
                    this.f14318d.position(position2);
                    return;
                }
                throw new C3001a(position + h, this.f14318d.limit());
            }
            writeRawVarint32(m15331a((CharSequence) str));
            m15329a(str, this.f14318d);
        } catch (BufferOverflowException e) {
            C3001a aVar = new C3001a(this.f14318d.position(), this.f14318d.limit());
            aVar.initCause(e);
            throw aVar;
        }
    }

    /* renamed from: a */
    private static int m15331a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += m15330a(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i2 + 4294967296L));
    }

    /* renamed from: a */
    private static int m15330a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
                        i++;
                    } else {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    /* renamed from: a */
    private static void m15329a(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(m15328a(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            m15314b(charSequence, byteBuffer);
        }
    }

    /* renamed from: b */
    private static void m15314b(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> '\f') | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else {
                int i2 = i + 1;
                if (i2 != charSequence.length()) {
                    char charAt2 = charSequence.charAt(i2);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int codePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((codePoint >>> 18) | TbsListener.ErrorCode.TPATCH_VERSION_FAILED));
                        byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint & 63) | 128));
                        i = i2;
                    } else {
                        i = i2;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unpaired surrogate at index ");
                sb.append(i - 1);
                throw new IllegalArgumentException(sb.toString());
            }
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r9 + r0;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m15328a(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C3000je.m15328a(java.lang.CharSequence, byte[], int, int):int");
    }

    public void writeGroupNoTag(AbstractC3010jm jmVar) {
        jmVar.writeTo(this);
    }

    public void writeMessageNoTag(AbstractC3010jm jmVar) {
        writeRawVarint32(jmVar.m15253d());
        jmVar.writeTo(this);
    }

    public void writeBytesNoTag(byte[] bArr) {
        writeRawVarint32(bArr.length);
        writeRawBytes(bArr);
    }

    public void writeUInt32NoTag(int i) {
        writeRawVarint32(i);
    }

    public void writeEnumNoTag(int i) {
        writeRawVarint32(i);
    }

    public void writeSFixed32NoTag(int i) {
        writeRawLittleEndian32(i);
    }

    public void writeSFixed64NoTag(long j) {
        writeRawLittleEndian64(j);
    }

    public void writeSInt32NoTag(int i) {
        writeRawVarint32(m15290i(i));
    }

    public void writeSInt64NoTag(long j) {
        writeRawVarint64(m15293g(j));
    }

    /* renamed from: a */
    public static int m15341a(int i, double d) {
        return m15295g(i) + m15344a(d);
    }

    /* renamed from: a */
    public static int m15340a(int i, float f) {
        return m15295g(i) + m15343a(f);
    }

    /* renamed from: b */
    public static int m15320b(int i, long j) {
        return m15295g(i) + m15333a(j);
    }

    /* renamed from: c */
    public static int m15309c(int i, long j) {
        return m15295g(i) + m15316b(j);
    }

    /* renamed from: b */
    public static int m15321b(int i, int i2) {
        return m15295g(i) + m15342a(i2);
    }

    /* renamed from: d */
    public static int m15305d(int i, long j) {
        return m15295g(i) + m15308c(j);
    }

    /* renamed from: c */
    public static int m15310c(int i, int i2) {
        return m15295g(i) + m15322b(i2);
    }

    /* renamed from: a */
    public static int m15335a(int i, boolean z) {
        return m15295g(i) + m15326a(z);
    }

    /* renamed from: b */
    public static int m15318b(int i, String str) {
        return m15295g(i) + m15327a(str);
    }

    /* renamed from: a */
    public static int m15337a(int i, AbstractC3010jm jmVar) {
        return (m15295g(i) * 2) + m15332a(jmVar);
    }

    /* renamed from: b */
    public static int m15319b(int i, AbstractC3010jm jmVar) {
        return m15295g(i) + m15315b(jmVar);
    }

    /* renamed from: b */
    public static int m15317b(int i, byte[] bArr) {
        return m15295g(i) + m15313b(bArr);
    }

    /* renamed from: d */
    public static int m15306d(int i, int i2) {
        return m15295g(i) + m15311c(i2);
    }

    /* renamed from: e */
    public static int m15302e(int i, int i2) {
        return m15295g(i) + m15307d(i2);
    }

    /* renamed from: f */
    public static int m15298f(int i, int i2) {
        return m15295g(i) + m15303e(i2);
    }

    /* renamed from: e */
    public static int m15301e(int i, long j) {
        return m15295g(i) + m15304d(j);
    }

    /* renamed from: g */
    public static int m15294g(int i, int i2) {
        return m15295g(i) + m15299f(i2);
    }

    /* renamed from: f */
    public static int m15297f(int i, long j) {
        return m15295g(i) + m15300e(j);
    }

    /* renamed from: a */
    public static int m15333a(long j) {
        return m15296f(j);
    }

    /* renamed from: b */
    public static int m15316b(long j) {
        return m15296f(j);
    }

    /* renamed from: a */
    public static int m15342a(int i) {
        if (i >= 0) {
            return m15292h(i);
        }
        return 10;
    }

    /* renamed from: a */
    public static int m15327a(String str) {
        int a = m15331a((CharSequence) str);
        return m15292h(a) + a;
    }

    /* renamed from: a */
    public static int m15332a(AbstractC3010jm jmVar) {
        return jmVar.m15252e();
    }

    /* renamed from: b */
    public static int m15315b(AbstractC3010jm jmVar) {
        int e = jmVar.m15252e();
        return m15292h(e) + e;
    }

    /* renamed from: b */
    public static int m15313b(byte[] bArr) {
        return m15292h(bArr.length) + bArr.length;
    }

    /* renamed from: c */
    public static int m15311c(int i) {
        return m15292h(i);
    }

    /* renamed from: d */
    public static int m15307d(int i) {
        return m15292h(i);
    }

    /* renamed from: f */
    public static int m15299f(int i) {
        return m15292h(m15290i(i));
    }

    /* renamed from: e */
    public static int m15300e(long j) {
        return m15296f(m15293g(j));
    }

    /* renamed from: a */
    public int m15345a() {
        return this.f14318d.remaining();
    }

    /* renamed from: b */
    public void m15323b() {
        if (m15345a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.je$a */
    /* loaded from: classes2.dex */
    public static class C3001a extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        C3001a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    public void writeRawByte(byte b) {
        if (this.f14318d.hasRemaining()) {
            this.f14318d.put(b);
            return;
        }
        throw new C3001a(this.f14318d.position(), this.f14318d.limit());
    }

    public void writeRawByte(int i) {
        writeRawByte((byte) i);
    }

    public void writeRawBytes(byte[] bArr) {
        m15312b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public void m15312b(byte[] bArr, int i, int i2) {
        if (this.f14318d.remaining() >= i2) {
            this.f14318d.put(bArr, i, i2);
            return;
        }
        throw new C3001a(this.f14318d.position(), this.f14318d.limit());
    }

    /* renamed from: h */
    public void m15291h(int i, int i2) {
        writeRawVarint32(C3013jp.m15242a(i, i2));
    }

    /* renamed from: g */
    public static int m15295g(int i) {
        return m15292h(C3013jp.m15242a(i, 0));
    }

    public void writeRawVarint32(int i) {
        while ((i & (-128)) != 0) {
            writeRawByte((i & 127) | 128);
            i >>>= 7;
        }
        writeRawByte(i);
    }

    public void writeRawVarint64(long j) {
        while (((-128) & j) != 0) {
            writeRawByte((((int) j) & 127) | 128);
            j >>>= 7;
        }
        writeRawByte((int) j);
    }

    public void writeRawLittleEndian32(int i) {
        if (this.f14318d.remaining() >= 4) {
            this.f14318d.putInt(i);
            return;
        }
        throw new C3001a(this.f14318d.position(), this.f14318d.limit());
    }

    public void writeRawLittleEndian64(long j) {
        if (this.f14318d.remaining() >= 8) {
            this.f14318d.putLong(j);
            return;
        }
        throw new C3001a(this.f14318d.position(), this.f14318d.limit());
    }
}
