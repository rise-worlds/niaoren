package p110z1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* renamed from: z1.mr */
/* loaded from: classes3.dex */
public final class PDF417ScanningDecoder {

    /* renamed from: a */
    private static final int f22492a = 2;

    /* renamed from: b */
    private static final int f22493b = 3;

    /* renamed from: c */
    private static final int f22494c = 512;

    /* renamed from: d */
    private static final C5392mg f22495d = new C5392mg();

    /* renamed from: a */
    private static int m1958a(int i) {
        return 2 << i;
    }

    /* renamed from: a */
    private static boolean m1957a(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private PDF417ScanningDecoder() {
    }

    /* renamed from: a */
    public static DecoderResult m1952a(BitMatrix hyVar, ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3, ResultPoint onVar4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultRowIndicatorColumn mpVar;
        int i3;
        DetectionResultColumn[] moVarArr;
        Codeword[] mlVarArr;
        int i4;
        int i5;
        boolean z;
        DetectionResultColumn moVar;
        int i6;
        int i7;
        int i8;
        BarcodeMetadata mfVar;
        BarcodeMetadata a;
        BoundingBox mkVar = new BoundingBox(hyVar, onVar, onVar2, onVar3, onVar4);
        int i9 = 0;
        DetectionResultRowIndicatorColumn mpVar2 = null;
        DetectionResult mnVar = null;
        DetectionResultRowIndicatorColumn mpVar3 = null;
        while (i9 < 2) {
            mpVar = onVar != null ? m1953a(hyVar, mkVar, onVar, true, i, i2) : mpVar2;
            if (onVar3 != null) {
                mpVar3 = m1953a(hyVar, mkVar, onVar3, false, i, i2);
            }
            if (mpVar == null && mpVar3 == null) {
                mnVar = null;
            } else {
                if (mpVar == null || (mfVar = mpVar.m1970a()) == null) {
                    mfVar = mpVar3 == null ? null : mpVar3.m1970a();
                } else if (!(mpVar3 == null || (a = mpVar3.m1970a()) == null || mfVar.f22414a == a.f22414a || mfVar.f22415b == a.f22415b || mfVar.f22418e == a.f22418e)) {
                    mfVar = null;
                }
                if (mfVar == null) {
                    mnVar = null;
                } else {
                    BoundingBox a2 = m1947a(mpVar);
                    BoundingBox a3 = m1947a(mpVar3);
                    if (a2 == null) {
                        a2 = a3;
                    } else if (a3 != null) {
                        a2 = new BoundingBox(a2.f22429a, a2.f22430b, a2.f22431c, a3.f22432d, a3.f22433e);
                    }
                    mnVar = new DetectionResult(mfVar, a2);
                }
            }
            if (mnVar == null) {
                throw NotFoundException.m1647a();
            } else if (i9 != 0 || mnVar.f22485c == null || (mnVar.f22485c.f22436h >= mkVar.f22436h && mnVar.f22485c.f22437i <= mkVar.f22437i)) {
                mnVar.f22485c = mkVar;
                break;
            } else {
                mkVar = mnVar.f22485c;
                i9++;
                mpVar2 = mpVar;
            }
        }
        mpVar = mpVar2;
        int i10 = mnVar.f22486d + 1;
        mnVar.f22484b[0] = mpVar;
        mnVar.f22484b[i10] = mpVar3;
        boolean z2 = mpVar != null;
        int i11 = i;
        int i12 = i2;
        for (int i13 = 1; i13 <= i10; i13++) {
            int i14 = z2 ? i13 : i10 - i13;
            if (mnVar.f22484b[i14] == null) {
                if (i14 == 0 || i14 == i10) {
                    moVar = new DetectionResultRowIndicatorColumn(mkVar, i14 == 0);
                } else {
                    moVar = new DetectionResultColumn(mkVar);
                }
                mnVar.f22484b[i14] = moVar;
                int i15 = i11;
                int i16 = i12;
                int i17 = -1;
                for (int i18 = mkVar.f22436h; i18 <= mkVar.f22437i; i18++) {
                    int i19 = z2 ? 1 : -1;
                    int i20 = i14 - i19;
                    Codeword c = m1950a(mnVar, i20) ? mnVar.f22484b[i20].m1971c(i18) : null;
                    if (c != null) {
                        if (z2) {
                            i6 = c.f22440b;
                        } else {
                            i6 = c.f22439a;
                        }
                        i10 = i10;
                    } else {
                        Codeword a4 = mnVar.f22484b[i14].m1975a(i18);
                        if (a4 != null) {
                            if (z2) {
                                i6 = a4.f22439a;
                            } else {
                                i6 = a4.f22440b;
                            }
                            i10 = i10;
                        } else {
                            if (m1950a(mnVar, i20)) {
                                a4 = mnVar.f22484b[i20].m1975a(i18);
                            }
                            if (a4 != null) {
                                if (z2) {
                                    i6 = a4.f22440b;
                                } else {
                                    i6 = a4.f22439a;
                                }
                                i10 = i10;
                            } else {
                                int i21 = i14;
                                int i22 = 0;
                                while (true) {
                                    int i23 = i21 - i19;
                                    if (m1950a(mnVar, i23)) {
                                        Codeword[] mlVarArr2 = mnVar.f22484b[i23].f22489b;
                                        int length = mlVarArr2.length;
                                        int i24 = 0;
                                        while (i24 < length) {
                                            i10 = i10;
                                            Codeword mlVar = mlVarArr2[i24];
                                            if (mlVar != null) {
                                                if (z2) {
                                                    i8 = mlVar.f22440b;
                                                } else {
                                                    i8 = mlVar.f22439a;
                                                }
                                                i6 = (i19 * i22 * (mlVar.f22440b - mlVar.f22439a)) + i8;
                                            } else {
                                                i24++;
                                                i10 = i10;
                                            }
                                        }
                                        i22++;
                                        i21 = i23;
                                    } else {
                                        i10 = i10;
                                        if (z2) {
                                            i6 = mnVar.f22485c.f22434f;
                                        } else {
                                            i6 = mnVar.f22485c.f22435g;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (i6 >= 0 && i6 <= mkVar.f22435g) {
                        i7 = i6;
                    } else if (i17 != -1) {
                        i7 = i17;
                    } else {
                        i16 = i16;
                        i15 = i15;
                    }
                    i16 = i16;
                    i15 = i15;
                    Codeword a5 = m1954a(hyVar, mkVar.f22434f, mkVar.f22435g, z2, i7, i18, i15, i16);
                    if (a5 != null) {
                        moVar.m1974a(i18, a5);
                        i15 = Math.min(i15, a5.m2007c());
                        i16 = Math.max(i16, a5.m2007c());
                        i17 = i7;
                    }
                }
                i10 = i10;
                i12 = i16;
                i11 = i15;
            } else {
                i10 = i10;
            }
        }
        BarcodeValue[][] mjVarArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, mnVar.f22483a.f22418e, mnVar.f22486d + 2);
        for (int i25 = 0; i25 < mjVarArr.length; i25++) {
            for (int i26 = 0; i26 < mjVarArr[i25].length; i26++) {
                mjVarArr[i25][i26] = new BarcodeValue();
            }
        }
        char c2 = 0;
        mnVar.m1986a(mnVar.f22484b[0]);
        int i27 = 1;
        mnVar.m1986a(mnVar.f22484b[mnVar.f22486d + 1]);
        int i28 = PDF417Common.f22405b;
        while (true) {
            if (mnVar.f22484b[c2] != null && mnVar.f22484b[mnVar.f22486d + i27] != null) {
                Codeword[] mlVarArr3 = mnVar.f22484b[c2].f22489b;
                Codeword[] mlVarArr4 = mnVar.f22484b[mnVar.f22486d + i27].f22489b;
                for (int i29 = 0; i29 < mlVarArr3.length; i29++) {
                    if (!(mlVarArr3[i29] == null || mlVarArr4[i29] == null || mlVarArr3[i29].f22443e != mlVarArr4[i29].f22443e)) {
                        for (int i30 = 1; i30 <= mnVar.f22486d; i30++) {
                            Codeword mlVar2 = mnVar.f22484b[i30].f22489b[i29];
                            if (mlVar2 != null) {
                                mlVar2.f22443e = mlVarArr3[i29].f22443e;
                                if (!mlVar2.m2011a()) {
                                    mnVar.f22484b[i30].f22489b[i29] = null;
                                }
                            }
                        }
                    }
                }
            }
            int i31 = 0;
            if (mnVar.f22484b[0] != null) {
                Codeword[] mlVarArr5 = mnVar.f22484b[0].f22489b;
                int i32 = 0;
                for (int i33 = 0; i33 < mlVarArr5.length; i33++) {
                    if (mlVarArr5[i33] != null) {
                        int i34 = mlVarArr5[i33].f22443e;
                        int i35 = i32;
                        int i36 = 0;
                        for (int i37 = 1; i37 < mnVar.f22486d + 1 && i36 < 2; i37++) {
                            Codeword mlVar3 = mnVar.f22484b[i37].f22489b[i33];
                            if (mlVar3 != null) {
                                i36 = DetectionResult.m1991a(i34, i36, mlVar3);
                                if (!mlVar3.m2011a()) {
                                    i35++;
                                }
                            }
                        }
                        i32 = i35;
                    }
                }
                i31 = i32;
            }
            int i38 = 1;
            if (mnVar.f22484b[mnVar.f22486d + 1] == null) {
                i3 = 0;
            } else {
                Codeword[] mlVarArr6 = mnVar.f22484b[mnVar.f22486d + 1].f22489b;
                int i39 = 0;
                i3 = 0;
                while (i39 < mlVarArr6.length) {
                    if (mlVarArr6[i39] != null) {
                        int i40 = mlVarArr6[i39].f22443e;
                        int i41 = 0;
                        for (int i42 = mnVar.f22486d + i38; i42 > 0 && i41 < 2; i42--) {
                            Codeword mlVar4 = mnVar.f22484b[i42].f22489b[i39];
                            if (mlVar4 != null) {
                                i41 = DetectionResult.m1991a(i40, i41, mlVar4);
                                if (!mlVar4.m2011a()) {
                                    i3++;
                                }
                            }
                        }
                    }
                    i39++;
                    i38 = 1;
                }
            }
            int i43 = i31 + i3;
            if (i43 == 0) {
                i43 = 0;
            } else {
                for (int i44 = 1; i44 < mnVar.f22486d + 1; i44++) {
                    Codeword[] mlVarArr7 = mnVar.f22484b[i44].f22489b;
                    for (int i45 = 0; i45 < mlVarArr7.length; i45++) {
                        if (mlVarArr7[i45] != null && !mlVarArr7[i45].m2011a()) {
                            Codeword mlVar5 = mlVarArr7[i45];
                            Codeword[] mlVarArr8 = mnVar.f22484b[i44 - 1].f22489b;
                            int i46 = i44 + 1;
                            Codeword[] mlVarArr9 = mnVar.f22484b[i46] != null ? mnVar.f22484b[i46].f22489b : mlVarArr8;
                            Codeword[] mlVarArr10 = new Codeword[14];
                            mlVarArr10[2] = mlVarArr8[i45];
                            mlVarArr10[3] = mlVarArr9[i45];
                            if (i45 > 0) {
                                int i47 = i45 - 1;
                                mlVarArr10[0] = mlVarArr7[i47];
                                mlVarArr10[4] = mlVarArr8[i47];
                                mlVarArr10[5] = mlVarArr9[i47];
                                i5 = 1;
                            } else {
                                i5 = 1;
                            }
                            if (i45 > i5) {
                                int i48 = i45 - 2;
                                mlVarArr10[8] = mlVarArr7[i48];
                                mlVarArr10[10] = mlVarArr8[i48];
                                mlVarArr10[11] = mlVarArr9[i48];
                            }
                            if (i45 < mlVarArr7.length - 1) {
                                int i49 = i45 + 1;
                                mlVarArr10[1] = mlVarArr7[i49];
                                mlVarArr10[6] = mlVarArr8[i49];
                                mlVarArr10[7] = mlVarArr9[i49];
                            }
                            if (i45 < mlVarArr7.length - 2) {
                                int i50 = i45 + 2;
                                mlVarArr10[9] = mlVarArr7[i50];
                                mlVarArr10[12] = mlVarArr8[i50];
                                mlVarArr10[13] = mlVarArr9[i50];
                            }
                            for (int i51 = 0; i51 < 14; i51++) {
                                Codeword mlVar6 = mlVarArr10[i51];
                                if (mlVar6 == null || !mlVar6.m2011a() || mlVar6.f22441c != mlVar5.f22441c) {
                                    z = false;
                                } else {
                                    mlVar5.f22443e = mlVar6.f22443e;
                                    z = true;
                                }
                                if (!z) {
                                }
                            }
                        }
                    }
                }
            }
            if (i43 <= 0 || i43 >= i28) {
                break;
            }
            i28 = i43;
            c2 = 0;
            i27 = 1;
        }
        int i52 = 0;
        for (DetectionResultColumn moVar2 : mnVar.f22484b) {
            if (moVar2 != null) {
                for (Codeword mlVar7 : moVar2.f22489b) {
                    if (mlVar7 != null && (i4 = mlVar7.f22443e) >= 0 && i4 < mjVarArr.length) {
                        mjVarArr[i4][i52].m2023a(mlVar7.f22442d);
                    }
                }
            }
            i52++;
        }
        BarcodeValue mjVar = mjVarArr[0][1];
        int[] a6 = mjVar.m2024a();
        int i53 = (mnVar.f22486d * mnVar.f22483a.f22418e) - (2 << mnVar.f22483a.f22415b);
        if (a6.length == 0) {
            if (i53 <= 0 || i53 > 928) {
                throw NotFoundException.m1647a();
            }
            mjVar.m2023a(i53);
        } else if (a6[0] != i53) {
            mjVar.m2023a(i53);
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[mnVar.f22483a.f22418e * mnVar.f22486d];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i54 = 0; i54 < mnVar.f22483a.f22418e; i54++) {
            int i55 = 0;
            while (i55 < mnVar.f22486d) {
                int i56 = i55 + 1;
                int[] a7 = mjVarArr[i54][i56].m2024a();
                int i57 = (mnVar.f22486d * i54) + i55;
                if (a7.length == 0) {
                    arrayList.add(Integer.valueOf(i57));
                } else if (a7.length == 1) {
                    iArr[i57] = a7[0];
                } else {
                    arrayList3.add(Integer.valueOf(i57));
                    arrayList2.add(a7);
                }
                i55 = i56;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()];
        for (int i58 = 0; i58 < iArr2.length; i58++) {
            iArr2[i58] = (int[]) arrayList2.get(i58);
        }
        return m1956a(mnVar.f22483a.f22415b, iArr, PDF417Common.m2056a(arrayList), PDF417Common.m2056a(arrayList3), iArr2);
    }

    /* renamed from: a */
    private static int m1945a(int[] iArr) {
        int i = -1;
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }

    /* renamed from: b */
    private static BarcodeMetadata m1937b(DetectionResultRowIndicatorColumn mpVar, DetectionResultRowIndicatorColumn mpVar2) {
        BarcodeMetadata a;
        BarcodeMetadata a2;
        if (mpVar == null || (a = mpVar.m1970a()) == null) {
            if (mpVar2 == null) {
                return null;
            }
            return mpVar2.m1970a();
        } else if (mpVar2 == null || (a2 = mpVar2.m1970a()) == null || a.f22414a == a2.f22414a || a.f22415b == a2.f22415b || a.f22418e == a2.f22418e) {
            return a;
        } else {
            return null;
        }
    }

    /* renamed from: a */
    private static DetectionResultRowIndicatorColumn m1953a(BitMatrix hyVar, BoundingBox mkVar, ResultPoint onVar, boolean z, int i, int i2) {
        DetectionResultRowIndicatorColumn mpVar = new DetectionResultRowIndicatorColumn(mkVar, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int i5 = (int) onVar.f22726c;
            for (int i6 = (int) onVar.f22727d; i6 <= mkVar.f22437i && i6 >= mkVar.f22436h; i6 += i4) {
                Codeword a = m1954a(hyVar, 0, hyVar.f21920a, z, i5, i6, i, i2);
                if (a != null) {
                    mpVar.m1974a(i6, a);
                    if (z) {
                        i5 = a.f22439a;
                    } else {
                        i5 = a.f22440b;
                    }
                }
            }
            i3++;
        }
        return mpVar;
    }

    /* renamed from: a */
    private static void m1948a(DetectionResult mnVar, BarcodeValue[][] mjVarArr) throws NotFoundException {
        BarcodeValue mjVar = mjVarArr[0][1];
        int[] a = mjVar.m2024a();
        int i = (mnVar.f22486d * mnVar.f22483a.f22418e) - (2 << mnVar.f22483a.f22415b);
        if (a.length == 0) {
            if (i <= 0 || i > 928) {
                throw NotFoundException.m1647a();
            }
            mjVar.m2023a(i);
        } else if (a[0] != i) {
            mjVar.m2023a(i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x0226, code lost:
        throw p110z1.ChecksumException.m2421a();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static p110z1.DecoderResult m1956a(int r20, int[] r21, int[] r22, int[] r23, int[][] r24) throws p110z1.FormatException, p110z1.ChecksumException {
        /*
            Method dump skipped, instructions count: 615
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417ScanningDecoder.m1956a(int, int[], int[], int[], int[][]):z1.ig");
    }

    /* renamed from: a */
    private static int m1949a(DetectionResult mnVar, int i, int i2, boolean z) {
        Codeword[] mlVarArr;
        int i3;
        int i4 = z ? 1 : -1;
        Codeword mlVar = null;
        int i5 = i - i4;
        if (m1950a(mnVar, i5)) {
            mlVar = mnVar.f22484b[i5].m1971c(i2);
        }
        if (mlVar == null) {
            Codeword a = mnVar.f22484b[i].m1975a(i2);
            if (a == null) {
                if (m1950a(mnVar, i5)) {
                    a = mnVar.f22484b[i5].m1975a(i2);
                }
                if (a == null) {
                    int i6 = 0;
                    while (true) {
                        i -= i4;
                        if (m1950a(mnVar, i)) {
                            for (Codeword mlVar2 : mnVar.f22484b[i].f22489b) {
                                if (mlVar2 != null) {
                                    if (z) {
                                        i3 = mlVar2.f22440b;
                                    } else {
                                        i3 = mlVar2.f22439a;
                                    }
                                    return i3 + (i4 * i6 * (mlVar2.f22440b - mlVar2.f22439a));
                                }
                            }
                            i6++;
                        } else if (z) {
                            return mnVar.f22485c.f22434f;
                        } else {
                            return mnVar.f22485c.f22435g;
                        }
                    }
                } else if (z) {
                    return a.f22440b;
                } else {
                    return a.f22439a;
                }
            } else if (z) {
                return a.f22439a;
            } else {
                return a.f22440b;
            }
        } else if (z) {
            return mlVar.f22440b;
        } else {
            return mlVar.f22439a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x002b A[EDGE_INSN: B:27:0x002b->B:18:0x002b ?: BREAK  , SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] m1955a(p110z1.BitMatrix r8, int r9, int r10, boolean r11, int r12, int r13) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r11 == 0) goto L_0x0009
            r3 = 1
            goto L_0x000a
        L_0x0009:
            r3 = -1
        L_0x000a:
            r4 = 0
            r6 = r11
            r5 = 0
        L_0x000d:
            if (r11 == 0) goto L_0x0012
            if (r12 >= r10) goto L_0x002b
            goto L_0x0014
        L_0x0012:
            if (r12 < r9) goto L_0x002b
        L_0x0014:
            if (r5 >= r0) goto L_0x002b
            boolean r7 = r8.m2519a(r12, r13)
            if (r7 != r6) goto L_0x0023
            r7 = r1[r5]
            int r7 = r7 + r2
            r1[r5] = r7
            int r12 = r12 + r3
            goto L_0x000d
        L_0x0023:
            int r5 = r5 + 1
            if (r6 != 0) goto L_0x0029
            r6 = 1
            goto L_0x000d
        L_0x0029:
            r6 = 0
            goto L_0x000d
        L_0x002b:
            if (r5 == r0) goto L_0x0038
            if (r11 == 0) goto L_0x0030
            r9 = r10
        L_0x0030:
            if (r12 != r9) goto L_0x0036
            r8 = 7
            if (r5 != r8) goto L_0x0036
            goto L_0x0038
        L_0x0036:
            r8 = 0
            return r8
        L_0x0038:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417ScanningDecoder.m1955a(z1.hy, int, int, boolean, int, int):int[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0026 A[EDGE_INSN: B:30:0x0026->B:18:0x0026 ?: BREAK  , SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m1939b(p110z1.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 1
            if (r10 == 0) goto L_0x0005
            r1 = -1
            goto L_0x0006
        L_0x0005:
            r1 = 1
        L_0x0006:
            r2 = 0
            r4 = r10
            r3 = r1
            r10 = 0
            r1 = r11
        L_0x000b:
            r5 = 2
            if (r10 >= r5) goto L_0x002f
        L_0x000e:
            if (r4 == 0) goto L_0x0013
            if (r1 < r8) goto L_0x0026
            goto L_0x0015
        L_0x0013:
            if (r1 >= r9) goto L_0x0026
        L_0x0015:
            boolean r6 = r7.m2519a(r1, r12)
            if (r4 != r6) goto L_0x0026
            int r6 = r11 - r1
            int r6 = java.lang.Math.abs(r6)
            if (r6 <= r5) goto L_0x0024
            return r11
        L_0x0024:
            int r1 = r1 + r3
            goto L_0x000e
        L_0x0026:
            int r3 = -r3
            if (r4 != 0) goto L_0x002b
            r4 = 1
            goto L_0x002c
        L_0x002b:
            r4 = 0
        L_0x002c:
            int r10 = r10 + 1
            goto L_0x000b
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417ScanningDecoder.m1939b(z1.hy, int, int, boolean, int, int):int");
    }

    /* renamed from: a */
    private static DecoderResult m1943a(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        ModulusPoly miVar;
        if (iArr.length != 0) {
            int i2 = 1;
            int i3 = 1 << (i + 1);
            if ((iArr2 == null || iArr2.length <= (i3 / 2) + 3) && i3 >= 0 && i3 <= 512) {
                C5392mg mgVar = f22495d;
                ModulusPoly miVar2 = new ModulusPoly(mgVar.f22419a, iArr);
                int[] iArr3 = new int[i3];
                int i4 = 0;
                boolean z = false;
                for (int i5 = i3; i5 > 0; i5--) {
                    int b = miVar2.m2030b(mgVar.f22419a.f22421b[i5]);
                    iArr3[i3 - i5] = b;
                    if (b != 0) {
                        z = true;
                    }
                }
                if (z) {
                    ModulusPoly miVar3 = mgVar.f22419a.f22424e;
                    if (iArr2 != null) {
                        ModulusPoly miVar4 = miVar3;
                        for (int i6 : iArr2) {
                            miVar4 = miVar4.m2026c(new ModulusPoly(mgVar.f22419a, new int[]{mgVar.f22419a.m2037c(0, mgVar.f22419a.f22421b[(iArr.length - 1) - i6]), 1}));
                        }
                    }
                    ModulusPoly miVar5 = new ModulusPoly(mgVar.f22419a, iArr3);
                    ModulusPoly a = mgVar.f22419a.m2043a(i3, 1);
                    if (a.f22427b.length - 1 >= miVar5.f22427b.length - 1) {
                        a = miVar5;
                        miVar5 = a;
                    }
                    ModulusPoly miVar6 = mgVar.f22419a.f22423d;
                    ModulusPoly miVar7 = mgVar.f22419a.f22424e;
                    while (a.f22427b.length - i2 >= i3 / 2) {
                        if (!a.m2035a()) {
                            ModulusPoly miVar8 = mgVar.f22419a.f22423d;
                            int a2 = mgVar.f22419a.m2044a(a.m2034a(a.f22427b.length - i2));
                            while (miVar5.f22427b.length - i2 >= a.f22427b.length - i2 && !miVar5.m2035a()) {
                                int length = (miVar5.f22427b.length - i2) - (a.f22427b.length - i2);
                                int d = mgVar.f22419a.m2036d(miVar5.m2034a(miVar5.f22427b.length - i2), a2);
                                miVar8 = miVar8.m2032a(mgVar.f22419a.m2043a(length, d));
                                if (length >= 0) {
                                    if (d == 0) {
                                        miVar = a.f22426a.f22423d;
                                    } else {
                                        int length2 = a.f22427b.length;
                                        int[] iArr4 = new int[length + length2];
                                        for (int i7 = 0; i7 < length2; i7++) {
                                            iArr4[i7] = a.f22426a.m2036d(a.f22427b[i7], d);
                                        }
                                        miVar = new ModulusPoly(a.f22426a, iArr4);
                                    }
                                    miVar5 = miVar5.m2029b(miVar);
                                    i2 = 1;
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            miVar7 = miVar8.m2026c(miVar7).m2029b(miVar6).m2031b();
                            miVar6 = miVar7;
                            i2 = 1;
                            miVar5 = a;
                            a = miVar5;
                        } else {
                            throw ChecksumException.m2421a();
                        }
                    }
                    int a3 = miVar7.m2034a(0);
                    if (a3 != 0) {
                        int a4 = mgVar.f22419a.m2044a(a3);
                        ModulusPoly[] miVarArr = {miVar7.m2027c(a4), a.m2027c(a4)};
                        ModulusPoly miVar9 = miVarArr[0];
                        ModulusPoly miVar10 = miVarArr[1];
                        int[] a5 = mgVar.m2049a(miVar9);
                        int[] a6 = mgVar.m2047a(miVar10, miVar9, a5);
                        for (int i8 = 0; i8 < a5.length; i8++) {
                            int length3 = iArr.length - 1;
                            ModulusGF mhVar = mgVar.f22419a;
                            int i9 = a5[i8];
                            if (i9 != 0) {
                                int i10 = length3 - mhVar.f22422c[i9];
                                if (i10 >= 0) {
                                    iArr[i10] = mgVar.f22419a.m2037c(iArr[i10], a6[i8]);
                                } else {
                                    throw ChecksumException.m2421a();
                                }
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                        i4 = a5.length;
                    } else {
                        throw ChecksumException.m2421a();
                    }
                }
                if (iArr.length >= 4) {
                    int i11 = iArr[0];
                    if (i11 <= iArr.length) {
                        if (i11 == 0) {
                            if (i3 < iArr.length) {
                                iArr[0] = iArr.length - i3;
                            } else {
                                throw FormatException.m2059a();
                            }
                        }
                        DecoderResult a7 = C5393mm.m1997a(iArr, String.valueOf(i));
                        a7.f21994f = Integer.valueOf(i4);
                        a7.f21995g = Integer.valueOf(iArr2.length);
                        return a7;
                    }
                    throw FormatException.m2059a();
                }
                throw FormatException.m2059a();
            }
            throw ChecksumException.m2421a();
        }
        throw FormatException.m2059a();
    }

    /* renamed from: a */
    private static int m1942a(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        int i2;
        ModulusPoly miVar;
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            C5392mg mgVar = f22495d;
            ModulusPoly miVar2 = new ModulusPoly(mgVar.f22419a, iArr);
            int[] iArr3 = new int[i];
            int i3 = i;
            boolean z = false;
            while (true) {
                i2 = 1;
                if (i3 <= 0) {
                    break;
                }
                int b = miVar2.m2030b(mgVar.f22419a.f22421b[i3]);
                iArr3[i - i3] = b;
                if (b != 0) {
                    z = true;
                }
                i3--;
            }
            if (!z) {
                return 0;
            }
            ModulusPoly miVar3 = mgVar.f22419a.f22424e;
            if (iArr2 != null) {
                ModulusPoly miVar4 = miVar3;
                for (int i4 : iArr2) {
                    miVar4 = miVar4.m2026c(new ModulusPoly(mgVar.f22419a, new int[]{mgVar.f22419a.m2037c(0, mgVar.f22419a.f22421b[(iArr.length - 1) - i4]), 1}));
                }
            }
            ModulusPoly miVar5 = new ModulusPoly(mgVar.f22419a, iArr3);
            ModulusPoly a = mgVar.f22419a.m2043a(i, 1);
            if (a.f22427b.length - 1 >= miVar5.f22427b.length - 1) {
                a = miVar5;
                miVar5 = a;
            }
            ModulusPoly miVar6 = mgVar.f22419a.f22423d;
            ModulusPoly miVar7 = mgVar.f22419a.f22424e;
            while (a.f22427b.length - i2 >= i / 2) {
                if (!a.m2035a()) {
                    ModulusPoly miVar8 = mgVar.f22419a.f22423d;
                    int a2 = mgVar.f22419a.m2044a(a.m2034a(a.f22427b.length - i2));
                    while (miVar5.f22427b.length - i2 >= a.f22427b.length - i2 && !miVar5.m2035a()) {
                        int length = (miVar5.f22427b.length - i2) - (a.f22427b.length - i2);
                        int d = mgVar.f22419a.m2036d(miVar5.m2034a(miVar5.f22427b.length - i2), a2);
                        miVar8 = miVar8.m2032a(mgVar.f22419a.m2043a(length, d));
                        if (length >= 0) {
                            if (d == 0) {
                                miVar = a.f22426a.f22423d;
                            } else {
                                int length2 = a.f22427b.length;
                                int[] iArr4 = new int[length + length2];
                                for (int i5 = 0; i5 < length2; i5++) {
                                    iArr4[i5] = a.f22426a.m2036d(a.f22427b[i5], d);
                                }
                                miVar = new ModulusPoly(a.f22426a, iArr4);
                            }
                            miVar5 = miVar5.m2029b(miVar);
                            i2 = 1;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                    miVar7 = miVar8.m2026c(miVar7).m2029b(miVar6).m2031b();
                    miVar6 = miVar7;
                    i2 = 1;
                    miVar5 = a;
                    a = miVar5;
                } else {
                    throw ChecksumException.m2421a();
                }
            }
            int a3 = miVar7.m2034a(0);
            if (a3 != 0) {
                int a4 = mgVar.f22419a.m2044a(a3);
                ModulusPoly[] miVarArr = {miVar7.m2027c(a4), a.m2027c(a4)};
                ModulusPoly miVar9 = miVarArr[0];
                ModulusPoly miVar10 = miVarArr[1];
                int[] a5 = mgVar.m2049a(miVar9);
                int[] a6 = mgVar.m2047a(miVar10, miVar9, a5);
                for (int i6 = 0; i6 < a5.length; i6++) {
                    int length3 = iArr.length - 1;
                    ModulusGF mhVar = mgVar.f22419a;
                    int i7 = a5[i6];
                    if (i7 != 0) {
                        int i8 = length3 - mhVar.f22422c[i7];
                        if (i8 >= 0) {
                            iArr[i8] = mgVar.f22419a.m2037c(iArr[i8], a6[i6]);
                        } else {
                            throw ChecksumException.m2421a();
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                return a5.length;
            }
            throw ChecksumException.m2421a();
        }
        throw ChecksumException.m2421a();
    }

    /* renamed from: a */
    private static void m1944a(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.m2059a();
            } else if (i2 != 0) {
            } else {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.m2059a();
            }
        } else {
            throw FormatException.m2059a();
        }
    }

    /* renamed from: b */
    private static int[] m1940b(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    /* renamed from: b */
    private static int m1936b(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    /* renamed from: a */
    private static String m1941a(BarcodeValue[][] mjVarArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < mjVarArr.length; i++) {
            try {
                formatter.format("Row %2d: ", Integer.valueOf(i));
                for (int i2 = 0; i2 < mjVarArr[i].length; i2++) {
                    BarcodeValue mjVar = mjVarArr[i][i2];
                    if (mjVar.m2024a().length == 0) {
                        formatter.format("        ", null);
                    } else {
                        formatter.format("%4d(%2d)", Integer.valueOf(mjVar.m2024a()[0]), mjVar.f22428a.get(Integer.valueOf(mjVar.m2024a()[0])));
                    }
                }
                formatter.format("%n", new Object[0]);
            } finally {
                try {
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    /* renamed from: a */
    private static DetectionResult m1946a(DetectionResultRowIndicatorColumn mpVar, DetectionResultRowIndicatorColumn mpVar2) throws NotFoundException {
        BarcodeMetadata mfVar;
        BarcodeMetadata a;
        if (mpVar == null && mpVar2 == null) {
            return null;
        }
        if (mpVar == null || (mfVar = mpVar.m1970a()) == null) {
            mfVar = mpVar2 == null ? null : mpVar2.m1970a();
        } else if (!(mpVar2 == null || (a = mpVar2.m1970a()) == null || mfVar.f22414a == a.f22414a || mfVar.f22415b == a.f22415b || mfVar.f22418e == a.f22418e)) {
            mfVar = null;
        }
        if (mfVar == null) {
            return null;
        }
        BoundingBox a2 = m1947a(mpVar);
        BoundingBox a3 = m1947a(mpVar2);
        if (a2 == null) {
            a2 = a3;
        } else if (a3 != null) {
            a2 = new BoundingBox(a2.f22429a, a2.f22430b, a2.f22431c, a3.f22432d, a3.f22433e);
        }
        return new DetectionResult(mfVar, a2);
    }

    /* renamed from: a */
    private static BoundingBox m1947a(DetectionResultRowIndicatorColumn mpVar) throws NotFoundException {
        int[] iArr;
        ResultPoint onVar;
        ResultPoint onVar2;
        ResultPoint onVar3;
        ResultPoint onVar4;
        ResultPoint onVar5;
        ResultPoint onVar6;
        Codeword[] mlVarArr;
        int i;
        if (mpVar == null) {
            return null;
        }
        BarcodeMetadata a = mpVar.m1970a();
        int i2 = 0;
        if (a == null) {
            iArr = null;
        } else {
            BoundingBox mkVar = mpVar.f22488a;
            if (mpVar.f22490c) {
                onVar5 = mkVar.f22430b;
            } else {
                onVar5 = mkVar.f22432d;
            }
            if (mpVar.f22490c) {
                onVar6 = mkVar.f22431c;
            } else {
                onVar6 = mkVar.f22433e;
            }
            int b = mpVar.m1972b((int) onVar6.f22727d);
            Codeword[] mlVarArr2 = mpVar.f22489b;
            int i3 = -1;
            int i4 = 0;
            int i5 = 1;
            for (int b2 = mpVar.m1972b((int) onVar5.f22727d); b2 < b; b2++) {
                if (mlVarArr2[b2] != null) {
                    Codeword mlVar = mlVarArr2[b2];
                    mlVar.m2009b();
                    int i6 = mlVar.f22443e - i3;
                    if (i6 == 0) {
                        i4++;
                    } else if (i6 == 1) {
                        i5 = Math.max(i5, i4);
                        i3 = mlVar.f22443e;
                        i4 = 1;
                    } else if (mlVar.f22443e >= a.f22418e) {
                        mlVarArr2[b2] = null;
                    } else {
                        i3 = mlVar.f22443e;
                        i4 = 1;
                    }
                }
            }
            iArr = new int[a.f22418e];
            for (Codeword mlVar2 : mpVar.f22489b) {
                if (mlVar2 != null && (i = mlVar2.f22443e) < iArr.length) {
                    iArr[i] = iArr[i] + 1;
                }
            }
        }
        if (iArr == null) {
            return null;
        }
        int i7 = -1;
        for (int i8 : iArr) {
            i7 = Math.max(i7, i8);
        }
        int i9 = 0;
        for (int i10 : iArr) {
            i9 += i7 - i10;
            if (i10 > 0) {
                break;
            }
        }
        Codeword[] mlVarArr3 = mpVar.f22489b;
        for (int i11 = 0; i9 > 0 && mlVarArr3[i11] == null; i11++) {
            i9--;
        }
        int i12 = 0;
        for (int length = iArr.length - 1; length >= 0; length--) {
            i12 += i7 - iArr[length];
            if (iArr[length] > 0) {
                break;
            }
        }
        for (int length2 = mlVarArr3.length - 1; i12 > 0 && mlVarArr3[length2] == null; length2--) {
            i12--;
        }
        BoundingBox mkVar2 = mpVar.f22488a;
        boolean z = mpVar.f22490c;
        ResultPoint onVar7 = mkVar2.f22430b;
        ResultPoint onVar8 = mkVar2.f22431c;
        ResultPoint onVar9 = mkVar2.f22432d;
        ResultPoint onVar10 = mkVar2.f22433e;
        if (i9 > 0) {
            ResultPoint onVar11 = z ? mkVar2.f22430b : mkVar2.f22432d;
            int i13 = ((int) onVar11.f22727d) - i9;
            if (i13 >= 0) {
                i2 = i13;
            }
            ResultPoint onVar12 = new ResultPoint(onVar11.f22726c, i2);
            if (z) {
                onVar = onVar9;
                onVar2 = onVar12;
            } else {
                onVar2 = onVar7;
                onVar = onVar12;
            }
        } else {
            onVar2 = onVar7;
            onVar = onVar9;
        }
        if (i12 > 0) {
            ResultPoint onVar13 = z ? mkVar2.f22431c : mkVar2.f22433e;
            int i14 = ((int) onVar13.f22727d) + i12;
            if (i14 >= mkVar2.f22429a.f21921b) {
                i14 = mkVar2.f22429a.f21921b - 1;
            }
            ResultPoint onVar14 = new ResultPoint(onVar13.f22726c, i14);
            if (z) {
                onVar4 = onVar14;
                onVar3 = onVar10;
            } else {
                onVar4 = onVar8;
                onVar3 = onVar14;
            }
        } else {
            onVar4 = onVar8;
            onVar3 = onVar10;
        }
        return new BoundingBox(mkVar2.f22429a, onVar2, onVar4, onVar, onVar3);
    }

    /* renamed from: a */
    private static DecoderResult m1951a(DetectionResult mnVar) throws FormatException, ChecksumException, NotFoundException {
        int i;
        int i2;
        DetectionResultColumn[] moVarArr;
        Codeword[] mlVarArr;
        int i3;
        boolean z;
        BarcodeValue[][] mjVarArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, mnVar.f22483a.f22418e, mnVar.f22486d + 2);
        for (int i4 = 0; i4 < mjVarArr.length; i4++) {
            for (int i5 = 0; i5 < mjVarArr[i4].length; i5++) {
                mjVarArr[i4][i5] = new BarcodeValue();
            }
        }
        mnVar.m1986a(mnVar.f22484b[0]);
        mnVar.m1986a(mnVar.f22484b[mnVar.f22486d + 1]);
        int i6 = PDF417Common.f22405b;
        while (true) {
            if (!(mnVar.f22484b[0] == null || mnVar.f22484b[mnVar.f22486d + 1] == null)) {
                Codeword[] mlVarArr2 = mnVar.f22484b[0].f22489b;
                Codeword[] mlVarArr3 = mnVar.f22484b[mnVar.f22486d + 1].f22489b;
                for (int i7 = 0; i7 < mlVarArr2.length; i7++) {
                    if (!(mlVarArr2[i7] == null || mlVarArr3[i7] == null || mlVarArr2[i7].f22443e != mlVarArr3[i7].f22443e)) {
                        for (int i8 = 1; i8 <= mnVar.f22486d; i8++) {
                            Codeword mlVar = mnVar.f22484b[i8].f22489b[i7];
                            if (mlVar != null) {
                                mlVar.f22443e = mlVarArr2[i7].f22443e;
                                if (!mlVar.m2011a()) {
                                    mnVar.f22484b[i8].f22489b[i7] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (mnVar.f22484b[0] == null) {
                i = 0;
            } else {
                Codeword[] mlVarArr4 = mnVar.f22484b[0].f22489b;
                i = 0;
                for (int i9 = 0; i9 < mlVarArr4.length; i9++) {
                    if (mlVarArr4[i9] != null) {
                        int i10 = mlVarArr4[i9].f22443e;
                        int i11 = i;
                        int i12 = 0;
                        for (int i13 = 1; i13 < mnVar.f22486d + 1 && i12 < 2; i13++) {
                            Codeword mlVar2 = mnVar.f22484b[i13].f22489b[i9];
                            if (mlVar2 != null) {
                                i12 = DetectionResult.m1991a(i10, i12, mlVar2);
                                if (!mlVar2.m2011a()) {
                                    i11++;
                                }
                            }
                        }
                        i = i11;
                    }
                }
            }
            if (mnVar.f22484b[mnVar.f22486d + 1] == null) {
                i2 = 0;
            } else {
                Codeword[] mlVarArr5 = mnVar.f22484b[mnVar.f22486d + 1].f22489b;
                i2 = 0;
                for (int i14 = 0; i14 < mlVarArr5.length; i14++) {
                    if (mlVarArr5[i14] != null) {
                        int i15 = mlVarArr5[i14].f22443e;
                        int i16 = i2;
                        int i17 = 0;
                        for (int i18 = mnVar.f22486d + 1; i18 > 0 && i17 < 2; i18--) {
                            Codeword mlVar3 = mnVar.f22484b[i18].f22489b[i14];
                            if (mlVar3 != null) {
                                i17 = DetectionResult.m1991a(i15, i17, mlVar3);
                                if (!mlVar3.m2011a()) {
                                    i16++;
                                }
                            }
                        }
                        i2 = i16;
                    }
                }
            }
            int i19 = i + i2;
            if (i19 == 0) {
                i19 = 0;
            } else {
                for (int i20 = 1; i20 < mnVar.f22486d + 1; i20++) {
                    Codeword[] mlVarArr6 = mnVar.f22484b[i20].f22489b;
                    for (int i21 = 0; i21 < mlVarArr6.length; i21++) {
                        if (mlVarArr6[i21] != null && !mlVarArr6[i21].m2011a()) {
                            Codeword mlVar4 = mlVarArr6[i21];
                            Codeword[] mlVarArr7 = mnVar.f22484b[i20 - 1].f22489b;
                            int i22 = i20 + 1;
                            Codeword[] mlVarArr8 = mnVar.f22484b[i22] != null ? mnVar.f22484b[i22].f22489b : mlVarArr7;
                            Codeword[] mlVarArr9 = new Codeword[14];
                            mlVarArr9[2] = mlVarArr7[i21];
                            mlVarArr9[3] = mlVarArr8[i21];
                            if (i21 > 0) {
                                int i23 = i21 - 1;
                                mlVarArr9[0] = mlVarArr6[i23];
                                mlVarArr9[4] = mlVarArr7[i23];
                                mlVarArr9[5] = mlVarArr8[i23];
                            }
                            if (i21 > 1) {
                                int i24 = i21 - 2;
                                mlVarArr9[8] = mlVarArr6[i24];
                                mlVarArr9[10] = mlVarArr7[i24];
                                mlVarArr9[11] = mlVarArr8[i24];
                            }
                            if (i21 < mlVarArr6.length - 1) {
                                int i25 = i21 + 1;
                                mlVarArr9[1] = mlVarArr6[i25];
                                mlVarArr9[6] = mlVarArr7[i25];
                                mlVarArr9[7] = mlVarArr8[i25];
                            }
                            if (i21 < mlVarArr6.length - 2) {
                                int i26 = i21 + 2;
                                mlVarArr9[9] = mlVarArr6[i26];
                                mlVarArr9[12] = mlVarArr7[i26];
                                mlVarArr9[13] = mlVarArr8[i26];
                            }
                            int i27 = 0;
                            for (int i28 = 14; i27 < i28; i28 = 14) {
                                Codeword mlVar5 = mlVarArr9[i27];
                                if (mlVar5 == null || !mlVar5.m2011a() || mlVar5.f22441c != mlVar4.f22441c) {
                                    z = false;
                                } else {
                                    mlVar4.f22443e = mlVar5.f22443e;
                                    z = true;
                                }
                                if (!z) {
                                    i27++;
                                }
                            }
                        }
                    }
                }
            }
            if (i19 <= 0 || i19 >= i6) {
                break;
            }
            i6 = i19;
        }
        int i29 = 0;
        for (DetectionResultColumn moVar : mnVar.f22484b) {
            if (moVar != null) {
                for (Codeword mlVar6 : moVar.f22489b) {
                    if (mlVar6 != null && (i3 = mlVar6.f22443e) >= 0 && i3 < mjVarArr.length) {
                        mjVarArr[i3][i29].m2023a(mlVar6.f22442d);
                    }
                }
            }
            i29++;
        }
        BarcodeValue mjVar = mjVarArr[0][1];
        int[] a = mjVar.m2024a();
        int i30 = (mnVar.f22486d * mnVar.f22483a.f22418e) - (2 << mnVar.f22483a.f22415b);
        if (a.length == 0) {
            if (i30 <= 0 || i30 > 928) {
                throw NotFoundException.m1647a();
            }
            mjVar.m2023a(i30);
        } else if (a[0] != i30) {
            mjVar.m2023a(i30);
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[mnVar.f22483a.f22418e * mnVar.f22486d];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i31 = 0; i31 < mnVar.f22483a.f22418e; i31++) {
            int i32 = 0;
            while (i32 < mnVar.f22486d) {
                int i33 = i32 + 1;
                int[] a2 = mjVarArr[i31][i33].m2024a();
                int i34 = (mnVar.f22486d * i31) + i32;
                if (a2.length == 0) {
                    arrayList.add(Integer.valueOf(i34));
                } else if (a2.length == 1) {
                    iArr[i34] = a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(i34));
                    arrayList2.add(a2);
                }
                i32 = i33;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()];
        for (int i35 = 0; i35 < iArr2.length; i35++) {
            iArr2[i35] = (int[]) arrayList2.get(i35);
        }
        return m1956a(mnVar.f22483a.f22415b, iArr, PDF417Common.m2056a(arrayList), PDF417Common.m2056a(arrayList3), iArr2);
    }

    /* renamed from: b */
    private static BarcodeValue[][] m1938b(DetectionResult mnVar) {
        int i;
        int i2;
        DetectionResultColumn[] moVarArr;
        Codeword[] mlVarArr;
        int i3;
        boolean z;
        BarcodeValue[][] mjVarArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, mnVar.f22483a.f22418e, mnVar.f22486d + 2);
        int i4 = 0;
        for (int i5 = 0; i5 < mjVarArr.length; i5++) {
            for (int i6 = 0; i6 < mjVarArr[i5].length; i6++) {
                mjVarArr[i5][i6] = new BarcodeValue();
            }
        }
        mnVar.m1986a(mnVar.f22484b[0]);
        mnVar.m1986a(mnVar.f22484b[mnVar.f22486d + 1]);
        int i7 = PDF417Common.f22405b;
        while (true) {
            if (!(mnVar.f22484b[i4] == null || mnVar.f22484b[mnVar.f22486d + 1] == null)) {
                Codeword[] mlVarArr2 = mnVar.f22484b[i4].f22489b;
                Codeword[] mlVarArr3 = mnVar.f22484b[mnVar.f22486d + 1].f22489b;
                for (int i8 = 0; i8 < mlVarArr2.length; i8++) {
                    if (!(mlVarArr2[i8] == null || mlVarArr3[i8] == null || mlVarArr2[i8].f22443e != mlVarArr3[i8].f22443e)) {
                        for (int i9 = 1; i9 <= mnVar.f22486d; i9++) {
                            Codeword mlVar = mnVar.f22484b[i9].f22489b[i8];
                            if (mlVar != null) {
                                mlVar.f22443e = mlVarArr2[i8].f22443e;
                                if (!mlVar.m2011a()) {
                                    mnVar.f22484b[i9].f22489b[i8] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (mnVar.f22484b[i4] == null) {
                i = 0;
            } else {
                Codeword[] mlVarArr4 = mnVar.f22484b[i4].f22489b;
                i = 0;
                for (int i10 = 0; i10 < mlVarArr4.length; i10++) {
                    if (mlVarArr4[i10] != null) {
                        int i11 = mlVarArr4[i10].f22443e;
                        int i12 = i;
                        int i13 = 0;
                        for (int i14 = 1; i14 < mnVar.f22486d + 1 && i13 < 2; i14++) {
                            Codeword mlVar2 = mnVar.f22484b[i14].f22489b[i10];
                            if (mlVar2 != null) {
                                i13 = DetectionResult.m1991a(i11, i13, mlVar2);
                                if (!mlVar2.m2011a()) {
                                    i12++;
                                }
                            }
                        }
                        i = i12;
                    }
                }
            }
            if (mnVar.f22484b[mnVar.f22486d + 1] == null) {
                i2 = 0;
            } else {
                Codeword[] mlVarArr5 = mnVar.f22484b[mnVar.f22486d + 1].f22489b;
                i2 = 0;
                for (int i15 = 0; i15 < mlVarArr5.length; i15++) {
                    if (mlVarArr5[i15] != null) {
                        int i16 = mlVarArr5[i15].f22443e;
                        int i17 = i2;
                        int i18 = 0;
                        for (int i19 = mnVar.f22486d + 1; i19 > 0 && i18 < 2; i19--) {
                            Codeword mlVar3 = mnVar.f22484b[i19].f22489b[i15];
                            if (mlVar3 != null) {
                                i18 = DetectionResult.m1991a(i16, i18, mlVar3);
                                if (!mlVar3.m2011a()) {
                                    i17++;
                                }
                            }
                        }
                        i2 = i17;
                    }
                }
            }
            int i20 = i + i2;
            if (i20 != 0) {
                int i21 = 1;
                while (i21 < mnVar.f22486d + 1) {
                    Codeword[] mlVarArr6 = mnVar.f22484b[i21].f22489b;
                    int i22 = 0;
                    while (i22 < mlVarArr6.length) {
                        if (mlVarArr6[i22] != null && !mlVarArr6[i22].m2011a()) {
                            Codeword mlVar4 = mlVarArr6[i22];
                            Codeword[] mlVarArr7 = mnVar.f22484b[i21 - 1].f22489b;
                            int i23 = i21 + 1;
                            Codeword[] mlVarArr8 = mnVar.f22484b[i23] != null ? mnVar.f22484b[i23].f22489b : mlVarArr7;
                            Codeword[] mlVarArr9 = new Codeword[14];
                            mlVarArr9[2] = mlVarArr7[i22];
                            mlVarArr9[3] = mlVarArr8[i22];
                            if (i22 > 0) {
                                int i24 = i22 - 1;
                                mlVarArr9[i4] = mlVarArr6[i24];
                                mlVarArr9[4] = mlVarArr7[i24];
                                mlVarArr9[5] = mlVarArr8[i24];
                            }
                            if (i22 > 1) {
                                int i25 = i22 - 2;
                                mlVarArr9[8] = mlVarArr6[i25];
                                mlVarArr9[10] = mlVarArr7[i25];
                                mlVarArr9[11] = mlVarArr8[i25];
                            }
                            if (i22 < mlVarArr6.length - 1) {
                                int i26 = i22 + 1;
                                mlVarArr9[1] = mlVarArr6[i26];
                                mlVarArr9[6] = mlVarArr7[i26];
                                mlVarArr9[7] = mlVarArr8[i26];
                            }
                            if (i22 < mlVarArr6.length - 2) {
                                int i27 = i22 + 2;
                                mlVarArr9[9] = mlVarArr6[i27];
                                mlVarArr9[12] = mlVarArr7[i27];
                                mlVarArr9[13] = mlVarArr8[i27];
                            }
                            for (int i28 = 0; i28 < 14; i28++) {
                                Codeword mlVar5 = mlVarArr9[i28];
                                if (mlVar5 == null || !mlVar5.m2011a() || mlVar5.f22441c != mlVar4.f22441c) {
                                    z = false;
                                } else {
                                    mlVar4.f22443e = mlVar5.f22443e;
                                    z = true;
                                }
                                if (!z) {
                                }
                            }
                        }
                        i22++;
                        i4 = 0;
                    }
                    i21++;
                    i4 = 0;
                }
                i4 = i20;
            }
            if (i4 <= 0 || i4 >= i7) {
                break;
            }
            i7 = i4;
            i4 = 0;
        }
        int i29 = 0;
        for (DetectionResultColumn moVar : mnVar.f22484b) {
            if (moVar != null) {
                for (Codeword mlVar6 : moVar.f22489b) {
                    if (mlVar6 != null && (i3 = mlVar6.f22443e) >= 0 && i3 < mjVarArr.length) {
                        mjVarArr[i3][i29].m2023a(mlVar6.f22442d);
                    }
                }
            }
            i29++;
        }
        return mjVarArr;
    }

    /* renamed from: a */
    private static boolean m1950a(DetectionResult mnVar, int i) {
        return i >= 0 && i <= mnVar.f22486d + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0033 A[EDGE_INSN: B:73:0x0033->B:18:0x0033 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0067 A[EDGE_INSN: B:76:0x0067->B:39:0x0067 ?: BREAK  , SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static p110z1.Codeword m1954a(p110z1.BitMatrix r17, int r18, int r19, boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.PDF417ScanningDecoder.m1954a(z1.hy, int, int, boolean, int, int, int, int):z1.ml");
    }

    /* renamed from: c */
    private static int m1935c(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }
}
