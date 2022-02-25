package p110z1;

import java.util.Map;

/* renamed from: z1.jm */
/* loaded from: classes3.dex */
public final class MaxiCodeReader implements Reader {

    /* renamed from: a */
    private static final ResultPoint[] f22139a = new ResultPoint[0];

    /* renamed from: b */
    private static final int f22140b = 30;

    /* renamed from: c */
    private static final int f22141c = 33;

    /* renamed from: d */
    private final C5383jp f22142d = new C5383jp();

    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, ChecksumException, FormatException {
        return mo1636a(htVar, null);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.m1647a();
        }
        BitMatrix c = htVar.m2557c();
        int i = c.f21920a;
        int i2 = -1;
        int i3 = c.f21921b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < c.f21921b) {
            int i7 = i3;
            int i8 = i5;
            for (int i9 = 0; i9 < c.f21922c; i9++) {
                int i10 = c.f21923d[(c.f21922c * i6) + i9];
                if (i10 != 0) {
                    if (i6 < i7) {
                        i7 = i6;
                    }
                    if (i6 > i4) {
                        i4 = i6;
                    }
                    int i11 = i9 << 5;
                    int i12 = 31;
                    if (i11 < i8) {
                        int i13 = 0;
                        while ((i10 << (31 - i13)) == 0) {
                            i13++;
                        }
                        int i14 = i13 + i11;
                        if (i14 < i8) {
                            i8 = i14;
                        }
                    }
                    if (i11 + 31 > i2) {
                        while ((i10 >>> i12) == 0) {
                            i12--;
                        }
                        int i15 = i11 + i12;
                        if (i15 > i2) {
                            i2 = i15;
                        }
                    }
                }
            }
            i6++;
            i5 = i8;
            i3 = i7;
        }
        int[] iArr = (i2 < i5 || i4 < i3) ? null : new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
        if (iArr != null) {
            int i16 = iArr[0];
            int i17 = iArr[1];
            int i18 = iArr[2];
            int i19 = iArr[3];
            BitMatrix hyVar = new BitMatrix(30, 33);
            for (int i20 = 0; i20 < 33; i20++) {
                int i21 = (((i20 * i19) + (i19 / 2)) / 33) + i17;
                for (int i22 = 0; i22 < 30; i22++) {
                    if (c.m2519a(((((i22 * i18) + (i18 / 2)) + (((i20 & 1) * i18) / 2)) / 30) + i16, i21)) {
                        hyVar.m2511b(i22, i20);
                    }
                }
            }
            DecoderResult a = this.f22142d.m2259a(hyVar);
            C5422ol olVar = new C5422ol(a.f21991c, a.f21989a, f22139a, BarcodeFormat.MAXICODE);
            String str = a.f21993e;
            if (str != null) {
                olVar.m1633a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
            }
            return olVar;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static BitMatrix m2270a(BitMatrix hyVar) throws NotFoundException {
        int i = hyVar.f21920a;
        int i2 = -1;
        int i3 = hyVar.f21921b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < hyVar.f21921b) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i5;
            for (int i10 = 0; i10 < hyVar.f21922c; i10++) {
                int i11 = hyVar.f21923d[(hyVar.f21922c * i6) + i10];
                if (i11 != 0) {
                    if (i6 < i3) {
                        i3 = i6;
                    }
                    if (i6 > i7) {
                        i7 = i6;
                    }
                    int i12 = i10 << 5;
                    int i13 = 31;
                    if (i12 < i9) {
                        int i14 = 0;
                        while ((i11 << (31 - i14)) == 0) {
                            i14++;
                        }
                        int i15 = i14 + i12;
                        if (i15 < i9) {
                            i9 = i15;
                        }
                    }
                    if (i12 + 31 > i8) {
                        while ((i11 >>> i13) == 0) {
                            i13--;
                        }
                        int i16 = i12 + i13;
                        if (i16 > i8) {
                            i8 = i16;
                        }
                    }
                }
            }
            i6++;
            i5 = i9;
            i2 = i8;
            i4 = i7;
        }
        int[] iArr = (i2 < i5 || i4 < i3) ? null : new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
        if (iArr != null) {
            int i17 = iArr[0];
            int i18 = iArr[1];
            int i19 = iArr[2];
            int i20 = iArr[3];
            BitMatrix hyVar2 = new BitMatrix(30, 33);
            for (int i21 = 0; i21 < 33; i21++) {
                int i22 = (((i21 * i20) + (i20 / 2)) / 33) + i18;
                for (int i23 = 0; i23 < 30; i23++) {
                    if (hyVar.m2519a(((((i23 * i19) + (i19 / 2)) + (((i21 & 1) * i19) / 2)) / 30) + i17, i22)) {
                        hyVar2.m2511b(i23, i21);
                    }
                }
            }
            return hyVar2;
        }
        throw NotFoundException.m1647a();
    }
}
