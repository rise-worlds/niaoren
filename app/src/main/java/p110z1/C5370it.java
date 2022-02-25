package p110z1;

import p110z1.Version;

/* compiled from: Decoder.java */
/* renamed from: z1.it */
/* loaded from: classes3.dex */
public final class C5370it {

    /* renamed from: a */
    private final ReedSolomonDecoder f22052a = new ReedSolomonDecoder(GenericGF.f21929f);

    /* renamed from: a */
    private DecoderResult m2392a(boolean[][] zArr) throws FormatException, ChecksumException {
        return m2394a(BitMatrix.m2513a(zArr));
    }

    /* renamed from: a */
    public final DecoderResult m2394a(BitMatrix hyVar) throws FormatException, ChecksumException {
        boolean z;
        int i;
        int i2;
        BitMatrixParser iqVar = new BitMatrixParser(hyVar);
        Version iuVar = iqVar.f22035c;
        byte[] bArr = new byte[iqVar.f22035c.f22060g];
        int i3 = iqVar.f22033a.f21921b;
        int i4 = iqVar.f22033a.f21920a;
        int i5 = 0;
        int i6 = 4;
        int i7 = 0;
        boolean z2 = false;
        int i8 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            if (i6 == i3 && i7 == 0 && !z2) {
                i8++;
                int i9 = i3 - 1;
                int i10 = (iqVar.m2415a(i9, i5, i3, i4) ? 1 : 0) << 1;
                if (iqVar.m2415a(i9, 1, i3, i4)) {
                    i10 |= 1;
                }
                int i11 = i10 << 1;
                if (iqVar.m2415a(i9, 2, i3, i4)) {
                    i11 |= 1;
                }
                int i12 = i11 << 1;
                if (iqVar.m2415a(i5, i4 - 2, i3, i4)) {
                    i12 |= 1;
                }
                int i13 = i12 << 1;
                int i14 = i4 - 1;
                if (iqVar.m2415a(i5, i14, i3, i4)) {
                    i13 |= 1;
                }
                int i15 = i13 << 1;
                if (iqVar.m2415a(1, i14, i3, i4)) {
                    i15 |= 1;
                }
                int i16 = i15 << 1;
                if (iqVar.m2415a(2, i14, i3, i4)) {
                    i16 |= 1;
                }
                int i17 = i16 << 1;
                if (iqVar.m2415a(3, i14, i3, i4)) {
                    i17 |= 1;
                }
                bArr[i8] = (byte) i17;
                i6 -= 2;
                i7 += 2;
                z = true;
                z2 = true;
            } else {
                int i18 = i3 - 2;
                if (i6 == i18 && i7 == 0 && (i4 & 3) != 0 && !z3) {
                    i8++;
                    int i19 = (iqVar.m2415a(i3 + (-3), 0, i3, i4) ? 1 : 0) << 1;
                    if (iqVar.m2415a(i18, 0, i3, i4)) {
                        i19 |= 1;
                    }
                    int i20 = i19 << 1;
                    if (iqVar.m2415a(i3 - 1, 0, i3, i4)) {
                        i20 |= 1;
                    }
                    int i21 = i20 << 1;
                    if (iqVar.m2415a(0, i4 - 4, i3, i4)) {
                        i21 |= 1;
                    }
                    int i22 = i21 << 1;
                    if (iqVar.m2415a(0, i4 - 3, i3, i4)) {
                        i22 |= 1;
                    }
                    int i23 = i22 << 1;
                    if (iqVar.m2415a(0, i4 - 2, i3, i4)) {
                        i23 |= 1;
                    }
                    int i24 = i23 << 1;
                    int i25 = i4 - 1;
                    if (iqVar.m2415a(0, i25, i3, i4)) {
                        i24 |= 1;
                    }
                    int i26 = i24 << 1;
                    if (iqVar.m2415a(1, i25, i3, i4)) {
                        i26 |= 1;
                    }
                    bArr[i8] = (byte) i26;
                    i6 -= 2;
                    i7 += 2;
                    z2 = z2;
                    z3 = true;
                    z = true;
                } else if (i6 == i3 + 4 && i7 == 2 && (i4 & 7) == 0 && !z4) {
                    i8++;
                    int i27 = i3 - 1;
                    int i28 = (iqVar.m2415a(i27, 0, i3, i4) ? 1 : 0) << 1;
                    int i29 = i4 - 1;
                    if (iqVar.m2415a(i27, i29, i3, i4)) {
                        i28 |= 1;
                    }
                    int i30 = i28 << 1;
                    int i31 = i4 - 3;
                    if (iqVar.m2415a(0, i31, i3, i4)) {
                        i30 |= 1;
                        i = 1;
                    } else {
                        i = 1;
                    }
                    int i32 = i30 << i;
                    int i33 = i4 - 2;
                    if (iqVar.m2415a(0, i33, i3, i4)) {
                        i32 |= 1;
                        z2 = z2;
                        i2 = 1;
                    } else {
                        z2 = z2;
                        i2 = 1;
                    }
                    int i34 = i32 << i2;
                    if (iqVar.m2415a(0, i29, i3, i4)) {
                        i34 |= 1;
                    }
                    int i35 = i34 << 1;
                    if (iqVar.m2415a(i2, i31, i3, i4)) {
                        i35 |= 1;
                    }
                    int i36 = i35 << i2;
                    if (iqVar.m2415a(i2, i33, i3, i4)) {
                        i36 |= 1;
                    }
                    int i37 = i36 << 1;
                    if (iqVar.m2415a(i2, i29, i3, i4)) {
                        i37 |= 1;
                    }
                    bArr[i8] = (byte) i37;
                    i6 -= 2;
                    i7 += 2;
                    z4 = true;
                    z = true;
                } else {
                    z2 = z2;
                    if (i6 == i18 && i7 == 0) {
                        if ((i4 & 7) == 4 && !z5) {
                            i8++;
                            z = true;
                            int i38 = (iqVar.m2415a(i3 + (-3), 0, i3, i4) ? 1 : 0) << 1;
                            if (iqVar.m2415a(i18, 0, i3, i4)) {
                                i38 |= 1;
                            }
                            int i39 = i38 << 1;
                            if (iqVar.m2415a(i3 - 1, 0, i3, i4)) {
                                i39 |= 1;
                            }
                            int i40 = i39 << 1;
                            if (iqVar.m2415a(0, i4 - 2, i3, i4)) {
                                i40 |= 1;
                            }
                            int i41 = i40 << 1;
                            int i42 = i4 - 1;
                            if (iqVar.m2415a(0, i42, i3, i4)) {
                                i41 |= 1;
                            }
                            int i43 = i41 << 1;
                            if (iqVar.m2415a(1, i42, i3, i4)) {
                                i43 |= 1;
                            }
                            int i44 = i43 << 1;
                            if (iqVar.m2415a(2, i42, i3, i4)) {
                                i44 |= 1;
                            }
                            int i45 = i44 << 1;
                            if (iqVar.m2415a(3, i42, i3, i4)) {
                                i45 |= 1;
                            }
                            bArr[i8] = (byte) i45;
                            i6 -= 2;
                            i7 += 2;
                            z5 = true;
                        }
                    }
                    z = true;
                    do {
                        if (i6 < i3 && i7 >= 0 && !iqVar.f22034b.m2519a(i7, i6)) {
                            i8++;
                            bArr[i8] = (byte) iqVar.m2411b(i6, i7, i3, i4);
                        }
                        i6 -= 2;
                        i7 += 2;
                        if (i6 < 0) {
                            break;
                        }
                    } while (i7 < i4);
                    int i46 = i6 + 1;
                    int i47 = i7 + 3;
                    do {
                        if (i46 >= 0 && i47 < i4 && !iqVar.f22034b.m2519a(i47, i46)) {
                            i8++;
                            bArr[i8] = (byte) iqVar.m2411b(i46, i47, i3, i4);
                        }
                        i46 += 2;
                        i47 -= 2;
                        if (i46 >= i3) {
                            break;
                        }
                    } while (i47 >= 0);
                    i6 = i46 + 3;
                    i7 = i47 + 1;
                }
            }
            if (i6 >= i3 && i7 >= i4) {
                break;
            }
            i5 = 0;
        }
        if (i8 == iqVar.f22035c.f22060g) {
            Version.C5373b bVar = iuVar.f22059f;
            Version.C5372a[] aVarArr = bVar.f22064b;
            int i48 = 0;
            for (Version.C5372a aVar : aVarArr) {
                i48 += aVar.f22061a;
            }
            DataBlock[] irVarArr = new DataBlock[i48];
            int length = aVarArr.length;
            int i49 = 0;
            int i50 = 0;
            while (i49 < length) {
                Version.C5372a aVar2 = aVarArr[i49];
                int i51 = i50;
                for (int i52 = 0; i52 < aVar2.f22061a; i52++) {
                    int i53 = aVar2.f22062b;
                    i51++;
                    irVarArr[i51] = new DataBlock(i53, new byte[bVar.f22063a + i53]);
                }
                i49++;
                i50 = i51;
            }
            int length2 = irVarArr[0].f22037b.length - bVar.f22063a;
            int i54 = length2 - 1;
            int i55 = 0;
            int i56 = 0;
            while (i55 < i54) {
                int i57 = i56;
                for (int i58 = 0; i58 < i50; i58++) {
                    i57++;
                    irVarArr[i58].f22037b[i55] = bArr[i57];
                }
                i55++;
                i56 = i57;
            }
            if (iuVar.f22054a != 24) {
                z = false;
            }
            int i59 = z ? 8 : i50;
            for (int i60 = 0; i60 < i59; i60++) {
                i56++;
                irVarArr[i60].f22037b[i54] = bArr[i56];
            }
            int length3 = irVarArr[0].f22037b.length;
            while (length2 < length3) {
                for (int i61 = 0; i61 < i50; i61++) {
                    int i62 = z ? (i61 + 8) % i50 : i61;
                    i56++;
                    irVarArr[i62].f22037b[(!z || i62 <= 7) ? length2 : length2 - 1] = bArr[i56];
                }
                length2++;
            }
            if (i56 == bArr.length) {
                int i63 = 0;
                for (DataBlock irVar : irVarArr) {
                    i63 += irVar.f22036a;
                }
                byte[] bArr2 = new byte[i63];
                int length4 = irVarArr.length;
                for (int i64 = 0; i64 < length4; i64++) {
                    DataBlock irVar2 = irVarArr[i64];
                    byte[] bArr3 = irVar2.f22037b;
                    int i65 = irVar2.f22036a;
                    m2393a(bArr3, i65);
                    for (int i66 = 0; i66 < i65; i66++) {
                        bArr2[(i66 * length4) + i64] = bArr3[i66];
                    }
                }
                return DecodedBitStreamParser.m2399a(bArr2);
            }
            throw new IllegalArgumentException();
        }
        throw FormatException.m2059a();
    }

    /* renamed from: a */
    private void m2393a(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f22052a.m2470a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.m2421a();
        }
    }
}
