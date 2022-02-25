package p110z1;

import java.lang.reflect.Array;

/* renamed from: z1.il */
/* loaded from: classes3.dex */
public final class HybridBinarizer extends GlobalHistogramBinarizer {

    /* renamed from: b */
    private static final int f22008b = 3;

    /* renamed from: c */
    private static final int f22009c = 8;

    /* renamed from: d */
    private static final int f22010d = 7;

    /* renamed from: e */
    private static final int f22011e = 40;

    /* renamed from: f */
    private static final int f22012f = 24;

    /* renamed from: g */
    private BitMatrix f22013g;

    /* renamed from: a */
    private static int m2434a(int i, int i2) {
        if (i < 2) {
            return 2;
        }
        return i > i2 ? i2 : i;
    }

    public HybridBinarizer(LuminanceSource odVar) {
        super(odVar);
    }

    @Override // p110z1.GlobalHistogramBinarizer, p110z1.Binarizer
    /* renamed from: a */
    public final BitMatrix mo2435a() throws NotFoundException {
        HybridBinarizer ilVar;
        BitMatrix hyVar = this.f22013g;
        if (hyVar != null) {
            return hyVar;
        }
        LuminanceSource odVar = this.f21780a;
        int i = odVar.f22688a;
        int i2 = odVar.f22689b;
        if (i < 40 || i2 < 40) {
            ilVar = this;
            ilVar.f22013g = super.mo2435a();
        } else {
            byte[] a = odVar.mo1642a();
            int i3 = i >> 3;
            if ((i & 7) != 0) {
                i3++;
            }
            int i4 = i2 >> 3;
            if ((i2 & 7) != 0) {
                i4++;
            }
            int i5 = i2 - 8;
            int i6 = i - 8;
            int[][] iArr = (int[][]) Array.newInstance(int.class, i4, i3);
            int i7 = 0;
            while (true) {
                int i8 = 8;
                if (i7 >= i4) {
                    break;
                }
                int i9 = i7 << 3;
                if (i9 > i5) {
                    i9 = i5;
                }
                int i10 = 0;
                while (i10 < i3) {
                    int i11 = i10 << 3;
                    if (i11 > i6) {
                        i11 = i6;
                    }
                    int i12 = (i9 * i) + i11;
                    int i13 = 0;
                    int i14 = 255;
                    int i15 = 0;
                    int i16 = 0;
                    while (i13 < i8) {
                        int i17 = i16;
                        int i18 = i14;
                        int i19 = 0;
                        while (i19 < i8) {
                            int i20 = a[i12 + i19] & 255;
                            i15 += i20;
                            if (i20 < i18) {
                                i18 = i20;
                            }
                            if (i20 > i17) {
                                i17 = i20;
                            }
                            i19++;
                            i8 = 8;
                        }
                        if (i17 - i18 <= 24) {
                            i13++;
                            i12 += i;
                            i16 = i17;
                            i14 = i18;
                            i9 = i9;
                            i8 = 8;
                        }
                        while (true) {
                            i13++;
                            i12 += i;
                            if (i13 < 8) {
                                int i21 = 0;
                                for (int i22 = 8; i21 < i22; i22 = 8) {
                                    i15 += a[i12 + i21] & 255;
                                    i21++;
                                }
                            }
                        }
                        i13++;
                        i12 += i;
                        i16 = i17;
                        i14 = i18;
                        i9 = i9;
                        i8 = 8;
                    }
                    int i23 = i15 >> 6;
                    if (i16 - i14 <= 24) {
                        i23 = i14 / 2;
                        if (i7 > 0 && i10 > 0) {
                            int i24 = i7 - 1;
                            int i25 = i10 - 1;
                            int i26 = ((iArr[i24][i10] + (iArr[i7][i25] * 2)) + iArr[i24][i25]) / 4;
                            if (i14 < i26) {
                                i23 = i26;
                            }
                        }
                    }
                    iArr[i7][i10] = i23;
                    i10++;
                    i9 = i9;
                    i8 = 8;
                }
                i7++;
            }
            BitMatrix hyVar2 = new BitMatrix(i, i2);
            for (int i27 = 0; i27 < i4; i27++) {
                int i28 = i27 << 3;
                if (i28 > i5) {
                    i28 = i5;
                }
                int a2 = m2434a(i27, i4 - 3);
                int i29 = 0;
                while (i29 < i3) {
                    int i30 = i29 << 3;
                    if (i30 > i6) {
                        i30 = i6;
                    }
                    int a3 = m2434a(i29, i3 - 3);
                    int i31 = -2;
                    int i32 = 0;
                    for (int i33 = 2; i31 <= i33; i33 = 2) {
                        int[] iArr2 = iArr[a2 + i31];
                        i32 += iArr2[a3 - 2] + iArr2[a3 - 1] + iArr2[a3] + iArr2[a3 + 1] + iArr2[a3 + 2];
                        i31++;
                    }
                    int i34 = i32 / 25;
                    int i35 = (i28 * i) + i30;
                    int i36 = 0;
                    while (true) {
                        if (i36 < 8) {
                            int i37 = 0;
                            for (int i38 = 8; i37 < i38; i38 = 8) {
                                if ((a[i35 + i37] & 255) <= i34) {
                                    hyVar2.m2511b(i30 + i37, i28 + i36);
                                }
                                i37++;
                                a = a;
                            }
                            i36++;
                            i35 += i;
                            i4 = i4;
                        }
                    }
                    i29++;
                    i3 = i3;
                }
            }
            ilVar = this;
            ilVar.f22013g = hyVar2;
        }
        return ilVar.f22013g;
    }

    @Override // p110z1.GlobalHistogramBinarizer, p110z1.Binarizer
    /* renamed from: a */
    public final Binarizer mo2433a(LuminanceSource odVar) {
        return new HybridBinarizer(odVar);
    }

    /* renamed from: a */
    private static void m2430a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix hyVar) {
        int i5 = 8;
        int i6 = i4 - 8;
        int i7 = i3 - 8;
        int i8 = 0;
        while (i8 < i2) {
            int i9 = i8 << 3;
            if (i9 > i6) {
                i9 = i6;
            }
            int a = m2434a(i8, i2 - 3);
            int i10 = 0;
            while (i10 < i) {
                int i11 = i10 << 3;
                if (i11 > i7) {
                    i11 = i7;
                }
                int a2 = m2434a(i10, i - 3);
                int i12 = 0;
                for (int i13 = -2; i13 <= 2; i13++) {
                    int[] iArr2 = iArr[a + i13];
                    i12 += iArr2[a2 - 2] + iArr2[a2 - 1] + iArr2[a2] + iArr2[a2 + 1] + iArr2[2 + a2];
                }
                int i14 = i12 / 25;
                int i15 = (i9 * i3) + i11;
                int i16 = 0;
                while (i16 < i5) {
                    int i17 = 0;
                    while (i17 < i5) {
                        if ((bArr[i15 + i17] & 255) <= i14) {
                            hyVar.m2511b(i11 + i17, i9 + i16);
                        }
                        i17++;
                        i5 = 8;
                    }
                    i16++;
                    i15 += i3;
                    i5 = 8;
                }
                i10++;
                i5 = 8;
            }
            i8++;
            i5 = 8;
        }
    }

    /* renamed from: a */
    private static void m2431a(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix hyVar) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    hyVar.m2511b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    /* renamed from: a */
    private static int[][] m2432a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = 8;
        int i6 = i4 - 8;
        int i7 = i3 - 8;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i2, i);
        for (int i8 = 0; i8 < i2; i8++) {
            int i9 = i8 << 3;
            if (i9 > i6) {
                i9 = i6;
            }
            for (int i10 = 0; i10 < i; i10++) {
                int i11 = i10 << 3;
                if (i11 > i7) {
                    i11 = i7;
                }
                int i12 = (i9 * i3) + i11;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 255;
                while (i13 < i5) {
                    i14 = i14;
                    int i17 = 0;
                    while (i17 < i5) {
                        int i18 = bArr[i12 + i17] & 255;
                        i14 += i18;
                        if (i18 < i16) {
                            i16 = i18;
                        }
                        if (i18 > i15) {
                            i15 = i18;
                        }
                        i17++;
                        i5 = 8;
                    }
                    if (i15 - i16 > 24) {
                        i13++;
                        i12 += i3;
                        i5 = 8;
                        while (i13 < 8) {
                            for (int i19 = 0; i19 < 8; i19++) {
                                i14 += bArr[i12 + i19] & 255;
                            }
                            i13++;
                            i12 += i3;
                        }
                    } else {
                        i5 = 8;
                    }
                    i13++;
                    i12 += i3;
                }
                int i20 = i14 >> 6;
                if (i15 - i16 <= 24) {
                    i20 = i16 / 2;
                    if (i8 > 0 && i10 > 0) {
                        int i21 = i8 - 1;
                        int i22 = i10 - 1;
                        int i23 = ((iArr[i21][i10] + (iArr[i8][i22] * 2)) + iArr[i21][i22]) / 4;
                        if (i16 < i23) {
                            i20 = i23;
                        }
                    }
                }
                iArr[i8][i10] = i20;
            }
        }
        return iArr;
    }
}
