package p110z1;

/* compiled from: BitMatrixParser.java */
/* renamed from: z1.ng */
/* loaded from: classes3.dex */
final class C5398ng {

    /* renamed from: a */
    final BitMatrix f22576a;

    /* renamed from: b */
    C5411np f22577b;

    /* renamed from: c */
    FormatInformation f22578c;

    /* renamed from: d */
    boolean f22579d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5398ng(BitMatrix hyVar) throws FormatException {
        int i = hyVar.f21921b;
        if (i < 21 || (i & 3) != 1) {
            throw FormatException.m2059a();
        }
        this.f22576a = hyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final FormatInformation m1849a() throws FormatException {
        FormatInformation nmVar = this.f22578c;
        if (nmVar != null) {
            return nmVar;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 = m1848a(i3, 8, i2);
        }
        int a = m1848a(8, 7, m1848a(8, 8, m1848a(7, 8, i2)));
        for (int i4 = 5; i4 >= 0; i4--) {
            a = m1848a(8, i4, a);
        }
        int i5 = this.f22576a.f21921b;
        int i6 = i5 - 7;
        for (int i7 = i5 - 1; i7 >= i6; i7--) {
            i = m1848a(8, i7, i);
        }
        for (int i8 = i5 - 8; i8 < i5; i8++) {
            i = m1848a(i8, 8, i);
        }
        this.f22578c = FormatInformation.m1818b(a, i);
        FormatInformation nmVar2 = this.f22578c;
        if (nmVar2 != null) {
            return nmVar2;
        }
        throw FormatException.m2059a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final C5411np m1847b() throws FormatException {
        C5411np npVar = this.f22577b;
        if (npVar != null) {
            return npVar;
        }
        int i = this.f22576a.f21921b;
        int i2 = (i - 17) / 4;
        if (i2 <= 6) {
            return C5411np.m1807b(i2);
        }
        int i3 = i - 11;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = i - 9; i7 >= i3; i7--) {
                i5 = m1848a(i7, i6, i5);
            }
        }
        C5411np c = C5411np.m1805c(i5);
        if (c == null || c.m1811a() != i) {
            for (int i8 = 5; i8 >= 0; i8--) {
                for (int i9 = i - 9; i9 >= i3; i9--) {
                    i4 = m1848a(i8, i9, i4);
                }
            }
            C5411np c2 = C5411np.m1805c(i4);
            if (c2 == null || c2.m1811a() != i) {
                throw FormatException.m2059a();
            }
            this.f22577b = c2;
            return c2;
        }
        this.f22577b = c;
        return c;
    }

    /* renamed from: a */
    private int m1848a(int i, int i2, int i3) {
        return this.f22579d ? this.f22576a.m2519a(i2, i) : this.f22576a.m2519a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    /* renamed from: c */
    private byte[] m1846c() throws FormatException {
        int i;
        FormatInformation a = m1849a();
        C5411np b = m1847b();
        DataMask niVar = DataMask.values()[a.f22605b];
        int i2 = this.f22576a.f21921b;
        niVar.m1839a(this.f22576a, i2);
        int a2 = b.m1811a();
        BitMatrix hyVar = new BitMatrix(a2);
        hyVar.m2518a(0, 0, 9, 9);
        int i3 = a2 - 8;
        hyVar.m2518a(i3, 0, 8, 9);
        hyVar.m2518a(0, i3, 9, 8);
        int length = b.f22623b.length;
        int i4 = 0;
        while (true) {
            i = 2;
            if (i4 >= length) {
                break;
            }
            int i5 = b.f22623b[i4] - 2;
            for (int i6 = 0; i6 < length; i6++) {
                if (!((i4 == 0 && (i6 == 0 || i6 == length - 1)) || (i4 == length - 1 && i6 == 0))) {
                    hyVar.m2518a(b.f22623b[i6] - 2, i5, 5, 5);
                }
            }
            i4++;
        }
        int i7 = a2 - 17;
        int i8 = 6;
        hyVar.m2518a(6, 9, 1, i7);
        hyVar.m2518a(9, 6, i7, 1);
        if (b.f22622a > 6) {
            int i9 = a2 - 11;
            hyVar.m2518a(i9, 0, 3, 6);
            hyVar.m2518a(0, i9, 6, 3);
        }
        byte[] bArr = new byte[b.f22624c];
        int i10 = i2 - 1;
        int i11 = i10;
        int i12 = 0;
        boolean z = true;
        int i13 = 0;
        int i14 = 0;
        while (i11 > 0) {
            if (i11 == i8) {
                i11--;
            }
            int i15 = i12;
            int i16 = 0;
            while (i16 < i2) {
                int i17 = z ? i10 - i16 : i16;
                int i18 = i14;
                int i19 = i13;
                int i20 = i15;
                int i21 = 0;
                while (i21 < i) {
                    int i22 = i11 - i21;
                    if (!hyVar.m2519a(i22, i17)) {
                        i19++;
                        int i23 = i18 << 1;
                        int i24 = this.f22576a.m2519a(i22, i17) ? i23 | 1 : i23;
                        if (i19 == 8) {
                            i20++;
                            bArr[i20] = (byte) i24;
                            i19 = 0;
                            i18 = 0;
                        } else {
                            i18 = i24;
                        }
                    }
                    i21++;
                    i = 2;
                }
                i16++;
                i15 = i20;
                i13 = i19;
                i14 = i18;
                i = 2;
            }
            z = !z;
            i11 -= 2;
            i12 = i15;
            i13 = i13;
            i14 = i14;
            i8 = 6;
            i = 2;
        }
        if (i12 == b.f22624c) {
            return bArr;
        }
        throw FormatException.m2059a();
    }

    /* renamed from: d */
    private void m1845d() {
        if (this.f22578c != null) {
            DataMask.values()[this.f22578c.f22605b].m1839a(this.f22576a, this.f22576a.f21921b);
        }
    }

    /* renamed from: e */
    private void m1844e() {
        this.f22577b = null;
        this.f22578c = null;
        this.f22579d = true;
    }

    /* renamed from: f */
    private void m1843f() {
        int i = 0;
        while (i < this.f22576a.f21920a) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f22576a.f21921b; i3++) {
                if (this.f22576a.m2519a(i, i3) != this.f22576a.m2519a(i3, i)) {
                    this.f22576a.m2507c(i3, i);
                    this.f22576a.m2507c(i, i3);
                }
            }
            i = i2;
        }
    }
}
