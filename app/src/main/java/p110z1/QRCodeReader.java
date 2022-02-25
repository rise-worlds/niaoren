package p110z1;

import java.util.List;
import java.util.Map;

/* renamed from: z1.nf */
/* loaded from: classes3.dex */
public class QRCodeReader implements Reader {

    /* renamed from: b */
    private static final ResultPoint[] f22574b = new ResultPoint[0];

    /* renamed from: a */
    protected final C5410nk f22575a = new C5410nk();

    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
    }

    /* renamed from: b */
    private C5410nk m1850b() {
        return this.f22575a;
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1637a(BinaryBitmap htVar) throws NotFoundException, ChecksumException, FormatException {
        return mo1636a(htVar, (Map<DecodeHintType, ?>) null);
    }

    @Override // p110z1.Reader
    /* renamed from: a */
    public final C5422ol mo1636a(BinaryBitmap htVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] onVarArr;
        DecoderResult igVar;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult a = new C5414nt(htVar.m2557c()).m1784a(map);
            DecoderResult a2 = this.f22575a.m1828a(a.f21999d, map);
            onVarArr = a.f22000e;
            igVar = a2;
        } else {
            BitMatrix c = htVar.m2557c();
            int[] b = c.m2512b();
            int[] c2 = c.m2508c();
            if (b == null || c2 == null) {
                throw NotFoundException.m1647a();
            }
            int i = c.f21921b;
            int i2 = c.f21920a;
            int i3 = b[0];
            int i4 = b[1];
            boolean z = true;
            int i5 = 0;
            while (i3 < i2 && i4 < i) {
                if (z != c.m2519a(i3, i4)) {
                    i5++;
                    if (i5 == 5) {
                        break;
                    }
                    z = !z;
                }
                i3++;
                i4++;
            }
            if (i3 == i2 || i4 == i) {
                throw NotFoundException.m1647a();
            }
            float f = (i3 - b[0]) / 7.0f;
            int i6 = b[1];
            int i7 = c2[1];
            int i8 = b[0];
            int i9 = c2[0];
            if (i8 >= i9 || i6 >= i7) {
                throw NotFoundException.m1647a();
            }
            int i10 = i7 - i6;
            if (i10 == i9 - i8 || (i9 = i8 + i10) < c.f21920a) {
                int round = Math.round(((i9 - i8) + 1) / f);
                int round2 = Math.round((i10 + 1) / f);
                if (round <= 0 || round2 <= 0) {
                    throw NotFoundException.m1647a();
                } else if (round2 == round) {
                    int i11 = (int) (f / 2.0f);
                    int i12 = i6 + i11;
                    int i13 = i8 + i11;
                    int i14 = (((int) ((round - 1) * f)) + i13) - i9;
                    if (i14 > 0) {
                        if (i14 <= i11) {
                            i13 -= i14;
                        } else {
                            throw NotFoundException.m1647a();
                        }
                    }
                    int i15 = (((int) ((round2 - 1) * f)) + i12) - i7;
                    if (i15 > 0) {
                        if (i15 <= i11) {
                            i12 -= i15;
                        } else {
                            throw NotFoundException.m1647a();
                        }
                    }
                    BitMatrix hyVar = new BitMatrix(round, round2);
                    for (int i16 = 0; i16 < round2; i16++) {
                        int i17 = ((int) (i16 * f)) + i12;
                        for (int i18 = 0; i18 < round; i18++) {
                            if (c.m2519a(((int) (i18 * f)) + i13, i17)) {
                                hyVar.m2511b(i18, i16);
                            }
                        }
                    }
                    igVar = this.f22575a.m1828a(hyVar, map);
                    onVarArr = f22574b;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else {
                throw NotFoundException.m1647a();
            }
        }
        if (igVar.f21996h instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) igVar.f21996h).m1812a(onVarArr);
        }
        C5422ol olVar = new C5422ol(igVar.f21991c, igVar.f21989a, onVarArr, BarcodeFormat.QR_CODE);
        List<byte[]> list = igVar.f21992d;
        if (list != null) {
            olVar.m1633a(ResultMetadataType.BYTE_SEGMENTS, list);
        }
        String str = igVar.f21993e;
        if (str != null) {
            olVar.m1633a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
        }
        if (igVar.m2460a()) {
            olVar.m1633a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(igVar.f21998j));
            olVar.m1633a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(igVar.f21997i));
        }
        return olVar;
    }

    /* renamed from: a */
    private static BitMatrix m1852a(BitMatrix hyVar) throws NotFoundException {
        int[] b = hyVar.m2512b();
        int[] c = hyVar.m2508c();
        if (b == null || c == null) {
            throw NotFoundException.m1647a();
        }
        int i = hyVar.f21921b;
        int i2 = hyVar.f21920a;
        int i3 = b[0];
        int i4 = b[1];
        boolean z = true;
        int i5 = 0;
        while (i3 < i2 && i4 < i) {
            if (z != hyVar.m2519a(i3, i4)) {
                i5++;
                if (i5 == 5) {
                    break;
                }
                z = !z;
            }
            i3++;
            i4++;
        }
        if (i3 == i2 || i4 == i) {
            throw NotFoundException.m1647a();
        }
        float f = (i3 - b[0]) / 7.0f;
        int i6 = b[1];
        int i7 = c[1];
        int i8 = b[0];
        int i9 = c[0];
        if (i8 >= i9 || i6 >= i7) {
            throw NotFoundException.m1647a();
        }
        int i10 = i7 - i6;
        if (i10 == i9 - i8 || (i9 = i8 + i10) < hyVar.f21920a) {
            int round = Math.round(((i9 - i8) + 1) / f);
            int round2 = Math.round((i10 + 1) / f);
            if (round <= 0 || round2 <= 0) {
                throw NotFoundException.m1647a();
            } else if (round2 == round) {
                int i11 = (int) (f / 2.0f);
                int i12 = i6 + i11;
                int i13 = i8 + i11;
                int i14 = (((int) ((round - 1) * f)) + i13) - i9;
                if (i14 > 0) {
                    if (i14 <= i11) {
                        i13 -= i14;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                }
                int i15 = (((int) ((round2 - 1) * f)) + i12) - i7;
                if (i15 > 0) {
                    if (i15 <= i11) {
                        i12 -= i15;
                    } else {
                        throw NotFoundException.m1647a();
                    }
                }
                BitMatrix hyVar2 = new BitMatrix(round, round2);
                for (int i16 = 0; i16 < round2; i16++) {
                    int i17 = ((int) (i16 * f)) + i12;
                    for (int i18 = 0; i18 < round; i18++) {
                        if (hyVar.m2519a(((int) (i18 * f)) + i13, i17)) {
                            hyVar2.m2511b(i18, i16);
                        }
                    }
                }
                return hyVar2;
            } else {
                throw NotFoundException.m1647a();
            }
        } else {
            throw NotFoundException.m1647a();
        }
    }

    /* renamed from: a */
    private static float m1851a(int[] iArr, BitMatrix hyVar) throws NotFoundException {
        int i = hyVar.f21921b;
        int i2 = hyVar.f21920a;
        int i3 = iArr[0];
        boolean z = true;
        int i4 = iArr[1];
        int i5 = 0;
        while (i3 < i2 && i4 < i) {
            if (z != hyVar.m2519a(i3, i4)) {
                i5++;
                if (i5 == 5) {
                    break;
                }
                z = !z;
            }
            i3++;
            i4++;
        }
        if (i3 != i2 && i4 != i) {
            return (i3 - iArr[0]) / 7.0f;
        }
        throw NotFoundException.m1647a();
    }
}
