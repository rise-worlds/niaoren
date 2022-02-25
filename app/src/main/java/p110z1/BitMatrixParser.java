package p110z1;

/* renamed from: z1.iq */
/* loaded from: classes3.dex */
final class BitMatrixParser {

    /* renamed from: a */
    final BitMatrix f22033a;

    /* renamed from: b */
    final BitMatrix f22034b;

    /* renamed from: c */
    final Version f22035c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix hyVar) throws FormatException {
        int i = hyVar.f21921b;
        if (i < 8 || i > 144 || (i & 1) != 0) {
            throw FormatException.m2059a();
        }
        this.f22035c = Version.m2390a(hyVar.f21921b, hyVar.f21920a);
        this.f22033a = m2410b(hyVar);
        this.f22034b = new BitMatrix(this.f22033a.f21920a, this.f22033a.f21921b);
    }

    /* renamed from: a */
    private Version m2417a() {
        return this.f22035c;
    }

    /* renamed from: b */
    private byte[] m2413b() throws FormatException {
        int i;
        byte[] bArr = new byte[this.f22035c.f22060g];
        int i2 = this.f22033a.f21921b;
        int i3 = this.f22033a.f21920a;
        int i4 = 4;
        int i5 = 0;
        boolean z = false;
        int i6 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            if (i4 == i2 && i5 == 0 && !z) {
                i6++;
                int i7 = i2 - 1;
                int i8 = (m2415a(i7, 0, i2, i3) ? 1 : 0) << 1;
                if (m2415a(i7, 1, i2, i3)) {
                    i8 |= 1;
                }
                int i9 = i8 << 1;
                if (m2415a(i7, 2, i2, i3)) {
                    i9 |= 1;
                }
                int i10 = i9 << 1;
                if (m2415a(0, i3 - 2, i2, i3)) {
                    i10 |= 1;
                }
                int i11 = i10 << 1;
                int i12 = i3 - 1;
                if (m2415a(0, i12, i2, i3)) {
                    i11 |= 1;
                }
                int i13 = i11 << 1;
                if (m2415a(1, i12, i2, i3)) {
                    i13 |= 1;
                }
                int i14 = i13 << 1;
                if (m2415a(2, i12, i2, i3)) {
                    i14 |= 1;
                }
                int i15 = i14 << 1;
                if (m2415a(3, i12, i2, i3)) {
                    i15 |= 1;
                }
                bArr[i6] = (byte) i15;
                i4 -= 2;
                i5 += 2;
                z = true;
            } else {
                int i16 = i2 - 2;
                if (i4 == i16 && i5 == 0 && (i3 & 3) != 0 && !z2) {
                    i6++;
                    int i17 = (m2415a(i2 + (-3), 0, i2, i3) ? 1 : 0) << 1;
                    if (m2415a(i16, 0, i2, i3)) {
                        i17 |= 1;
                    }
                    int i18 = i17 << 1;
                    if (m2415a(i2 - 1, 0, i2, i3)) {
                        i18 |= 1;
                    }
                    int i19 = i18 << 1;
                    if (m2415a(0, i3 - 4, i2, i3)) {
                        i19 |= 1;
                    }
                    int i20 = i19 << 1;
                    if (m2415a(0, i3 - 3, i2, i3)) {
                        i20 |= 1;
                    }
                    int i21 = i20 << 1;
                    if (m2415a(0, i3 - 2, i2, i3)) {
                        i21 |= 1;
                    }
                    int i22 = i21 << 1;
                    int i23 = i3 - 1;
                    if (m2415a(0, i23, i2, i3)) {
                        i22 |= 1;
                    }
                    int i24 = i22 << 1;
                    if (m2415a(1, i23, i2, i3)) {
                        i24 |= 1;
                    }
                    bArr[i6] = (byte) i24;
                    i4 -= 2;
                    i5 += 2;
                    z2 = true;
                } else if (i4 == i2 + 4 && i5 == 2 && (i3 & 7) == 0 && !z3) {
                    int i25 = i6 + 1;
                    int i26 = i2 - 1;
                    int i27 = (m2415a(i26, 0, i2, i3) ? 1 : 0) << 1;
                    int i28 = i3 - 1;
                    if (m2415a(i26, i28, i2, i3)) {
                        i27 |= 1;
                    }
                    int i29 = i27 << 1;
                    int i30 = i3 - 3;
                    if (m2415a(0, i30, i2, i3)) {
                        i29 |= 1;
                    }
                    int i31 = i29 << 1;
                    int i32 = i3 - 2;
                    if (m2415a(0, i32, i2, i3)) {
                        i31 |= 1;
                        i6 = i25;
                        i = 1;
                    } else {
                        i6 = i25;
                        i = 1;
                    }
                    int i33 = i31 << i;
                    if (m2415a(0, i28, i2, i3)) {
                        i33 |= 1;
                    }
                    int i34 = i33 << i;
                    if (m2415a(i, i30, i2, i3)) {
                        i34 |= 1;
                    }
                    int i35 = i34 << i;
                    if (m2415a(i, i32, i2, i3)) {
                        i35 |= 1;
                    }
                    int i36 = i35 << i;
                    if (m2415a(i, i28, i2, i3)) {
                        i36 |= 1;
                    }
                    bArr[i6] = (byte) i36;
                    i4 -= 2;
                    i5 += 2;
                    z3 = true;
                } else if (i4 == i16 && i5 == 0 && (i3 & 7) == 4 && !z4) {
                    i6++;
                    int i37 = (m2415a(i2 + (-3), 0, i2, i3) ? 1 : 0) << 1;
                    if (m2415a(i16, 0, i2, i3)) {
                        i37 |= 1;
                    }
                    int i38 = i37 << 1;
                    if (m2415a(i2 - 1, 0, i2, i3)) {
                        i38 |= 1;
                    }
                    int i39 = i38 << 1;
                    if (m2415a(0, i3 - 2, i2, i3)) {
                        i39 |= 1;
                    }
                    int i40 = i39 << 1;
                    int i41 = i3 - 1;
                    if (m2415a(0, i41, i2, i3)) {
                        i40 |= 1;
                    }
                    int i42 = i40 << 1;
                    if (m2415a(1, i41, i2, i3)) {
                        i42 |= 1;
                    }
                    int i43 = i42 << 1;
                    if (m2415a(2, i41, i2, i3)) {
                        i43 |= 1;
                    }
                    int i44 = i43 << 1;
                    if (m2415a(3, i41, i2, i3)) {
                        i44 |= 1;
                    }
                    bArr[i6] = (byte) i44;
                    i4 -= 2;
                    i5 += 2;
                    z4 = true;
                } else {
                    do {
                        if (i4 < i2 && i5 >= 0 && !this.f22034b.m2519a(i5, i4)) {
                            i6++;
                            bArr[i6] = (byte) m2411b(i4, i5, i2, i3);
                        }
                        i4 -= 2;
                        i5 += 2;
                        if (i4 < 0) {
                            break;
                        }
                    } while (i5 < i3);
                    int i45 = i4 + 1;
                    int i46 = i5 + 3;
                    do {
                        if (i45 >= 0 && i46 < i3 && !this.f22034b.m2519a(i46, i45)) {
                            i6++;
                            bArr[i6] = (byte) m2411b(i45, i46, i2, i3);
                        }
                        i45 += 2;
                        i46 -= 2;
                        if (i45 >= i2) {
                            break;
                        }
                    } while (i46 >= 0);
                    i4 = i45 + 3;
                    i5 = i46 + 1;
                }
            }
            if (i4 >= i2 && i5 >= i3) {
                break;
            }
        }
        if (i6 == this.f22035c.f22060g) {
            return bArr;
        }
        throw FormatException.m2059a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2415a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.f22034b.m2511b(i2, i);
        return this.f22033a.m2519a(i2, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m2411b(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (m2415a(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (m2415a(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i - 1;
        if (m2415a(i10, i6, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (m2415a(i10, i8, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (m2415a(i10, i2, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (m2415a(i, i6, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (m2415a(i, i8, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return m2415a(i, i2, i3, i4) ? i15 | 1 : i15;
    }

    /* renamed from: a */
    private int m2416a(int i, int i2) {
        int i3 = i - 1;
        int i4 = (m2415a(i3, 0, i, i2) ? 1 : 0) << 1;
        if (m2415a(i3, 1, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m2415a(i3, 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (m2415a(0, i2 - 2, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i2 - 1;
        if (m2415a(0, i8, i, i2)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (m2415a(1, i8, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (m2415a(2, i8, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return m2415a(3, i8, i, i2) ? i11 | 1 : i11;
    }

    /* renamed from: b */
    private int m2412b(int i, int i2) {
        int i3 = (m2415a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (m2415a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (m2415a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m2415a(0, i2 - 4, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (m2415a(0, i2 - 3, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (m2415a(0, i2 - 2, i, i2)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i2 - 1;
        if (m2415a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        return m2415a(1, i9, i, i2) ? i10 | 1 : i10;
    }

    /* renamed from: c */
    private int m2409c(int i, int i2) {
        int i3 = i - 1;
        int i4 = (m2415a(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (m2415a(i3, i5, i, i2)) {
            i4 |= 1;
        }
        int i6 = i4 << 1;
        int i7 = i2 - 3;
        if (m2415a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i2 - 2;
        if (m2415a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (m2415a(0, i5, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (m2415a(1, i7, i, i2)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (m2415a(1, i9, i, i2)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return m2415a(1, i5, i, i2) ? i13 | 1 : i13;
    }

    /* renamed from: d */
    private int m2408d(int i, int i2) {
        int i3 = (m2415a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (m2415a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (m2415a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m2415a(0, i2 - 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        int i7 = i2 - 1;
        if (m2415a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        if (m2415a(1, i7, i, i2)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (m2415a(2, i7, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        return m2415a(3, i7, i, i2) ? i10 | 1 : i10;
    }

    /* renamed from: b */
    private BitMatrix m2410b(BitMatrix hyVar) {
        int i = this.f22035c.f22055b;
        int i2 = this.f22035c.f22056c;
        if (hyVar.f21921b == i) {
            int i3 = this.f22035c.f22057d;
            int i4 = this.f22035c.f22058e;
            int i5 = i / i3;
            int i6 = i2 / i4;
            BitMatrix hyVar2 = new BitMatrix(i6 * i4, i5 * i3);
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = i7 * i3;
                for (int i9 = 0; i9 < i6; i9++) {
                    int i10 = i9 * i4;
                    for (int i11 = 0; i11 < i3; i11++) {
                        int i12 = ((i3 + 2) * i7) + 1 + i11;
                        int i13 = i8 + i11;
                        for (int i14 = 0; i14 < i4; i14++) {
                            if (hyVar.m2519a(((i4 + 2) * i9) + 1 + i14, i12)) {
                                hyVar2.m2511b(i10 + i14, i13);
                            }
                        }
                    }
                }
            }
            return hyVar2;
        }
        throw new IllegalArgumentException("Dimension of bitMatrix must match the version size");
    }

    /* renamed from: a */
    private static Version m2414a(BitMatrix hyVar) throws FormatException {
        return Version.m2390a(hyVar.f21921b, hyVar.f21920a);
    }
}
