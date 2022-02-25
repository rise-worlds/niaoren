package com.p018b.p029b;

import java.util.Arrays;

/* renamed from: com.b.b.v */
/* loaded from: classes.dex */
final class SegmentedByteString extends ByteString {

    /* renamed from: f */
    final transient byte[][] f6460f;

    /* renamed from: g */
    final transient int[] f6461g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SegmentedByteString(Buffer fVar, int i) {
        super(null);
        C0916aa.m24347a(fVar.f6422b, 0L, i);
        int i2 = 0;
        Segment tVar = fVar.f6421a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (tVar.f6453c != tVar.f6452b) {
                i3 += tVar.f6453c - tVar.f6452b;
                i4++;
                tVar = tVar.f6456f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.f6460f = new byte[i4];
        this.f6461g = new int[i4 * 2];
        Segment tVar2 = fVar.f6421a;
        int i5 = 0;
        while (i2 < i) {
            this.f6460f[i5] = tVar2.f6451a;
            i2 += tVar2.f6453c - tVar2.f6452b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.f6461g;
            iArr[i5] = i2;
            iArr[this.f6460f.length + i5] = tVar2.f6452b;
            tVar2.f6454d = true;
            i5++;
            tVar2 = tVar2.f6456f;
        }
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final String mo24267a() {
        return m24253i().mo24267a();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: b */
    public final String mo24261b() {
        return m24253i().mo24261b();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: e */
    public final String mo24257e() {
        return m24253i().mo24257e();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: f */
    public final ByteString mo24256f() {
        return m24253i().mo24256f();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: c */
    public final ByteString mo24259c() {
        return m24253i().mo24259c();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: d */
    public final ByteString mo24258d() {
        return m24253i().mo24258d();
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final ByteString mo24265a(int i, int i2) {
        return m24253i().mo24265a(i, i2);
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final byte mo24266a(int i) {
        C0916aa.m24347a(this.f6461g[this.f6460f.length - 1], i, 1L);
        int b = m24260b(i);
        int i2 = b == 0 ? 0 : this.f6461g[b - 1];
        int[] iArr = this.f6461g;
        byte[][] bArr = this.f6460f;
        return bArr[b][(i - i2) + iArr[bArr.length + b]];
    }

    /* renamed from: b */
    private int m24260b(int i) {
        int binarySearch = Arrays.binarySearch(this.f6461g, 0, this.f6460f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: g */
    public final int mo24255g() {
        return this.f6461g[this.f6460f.length - 1];
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: h */
    public final byte[] mo24254h() {
        int[] iArr = this.f6461g;
        byte[][] bArr = this.f6460f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.f6461g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f6460f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final void mo24262a(Buffer fVar) {
        int length = this.f6460f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.f6461g;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            Segment tVar = new Segment(this.f6460f[i], i3, (i3 + i4) - i2);
            if (fVar.f6421a == null) {
                tVar.f6457g = tVar;
                tVar.f6456f = tVar;
                fVar.f6421a = tVar;
            } else {
                fVar.f6421a.f6457g.m24271a(tVar);
            }
            i++;
            i2 = i4;
        }
        fVar.f6422b += i2;
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final boolean mo24264a(int i, ByteString iVar, int i2, int i3) {
        if (mo24255g() - i3 < 0) {
            return false;
        }
        int b = m24260b(0);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.f6461g[b - 1];
            int min = Math.min(i3, ((this.f6461g[b] - i4) + i4) - i);
            int[] iArr = this.f6461g;
            byte[][] bArr = this.f6460f;
            if (!iVar.mo24263a(i2, bArr[b], (i - i4) + iArr[bArr.length + b], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    @Override // com.p018b.p029b.ByteString
    /* renamed from: a */
    public final boolean mo24263a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > mo24255g() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b = m24260b(i);
        while (i3 > 0) {
            int i4 = b == 0 ? 0 : this.f6461g[b - 1];
            int min = Math.min(i3, ((this.f6461g[b] - i4) + i4) - i);
            int[] iArr = this.f6461g;
            byte[][] bArr2 = this.f6460f;
            if (!C0916aa.m24344a(bArr2[b], (i - i4) + iArr[bArr2.length + b], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b++;
        }
        return true;
    }

    /* renamed from: i */
    private ByteString m24253i() {
        return new ByteString(mo24254h());
    }

    @Override // com.p018b.p029b.ByteString
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString iVar = (ByteString) obj;
            if (iVar.mo24255g() == mo24255g() && mo24264a(0, iVar, 0, mo24255g())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.p018b.p029b.ByteString
    public final int hashCode() {
        int i = this.f6426d;
        if (i != 0) {
            return i;
        }
        int length = this.f6460f.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.f6460f[i2];
            int[] iArr = this.f6461g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.f6426d = i3;
        return i3;
    }

    @Override // com.p018b.p029b.ByteString
    public final String toString() {
        return m24253i().toString();
    }
}
