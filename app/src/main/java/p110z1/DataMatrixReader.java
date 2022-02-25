package p110z1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p110z1.C5374iw;

/* renamed from: z1.ip */
/* loaded from: classes3.dex */
public final class DataMatrixReader implements Reader {

    /* renamed from: a */
    private static final ResultPoint[] f22031a = new ResultPoint[0];

    /* renamed from: b */
    private final C5370it f22032b = new C5370it();

    @Override // p110z1.Reader
    /* renamed from: a */
    public final void mo1638a() {
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
        ResultPoint onVar;
        BitMatrix hyVar;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            C5374iw iwVar = new C5374iw(htVar.m2557c());
            ResultPoint[] a = iwVar.f22066b.m2524a();
            ResultPoint onVar2 = a[0];
            ResultPoint onVar3 = a[1];
            ResultPoint onVar4 = a[2];
            ResultPoint onVar5 = a[3];
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(iwVar.m2369b(onVar2, onVar3));
            arrayList.add(iwVar.m2369b(onVar2, onVar4));
            arrayList.add(iwVar.m2369b(onVar3, onVar5));
            arrayList.add(iwVar.m2369b(onVar4, onVar5));
            Collections.sort(arrayList, new C5374iw.C5377b((byte) 0));
            C5374iw.C5376a aVar = (C5374iw.C5376a) arrayList.get(0);
            C5374iw.C5376a aVar2 = (C5374iw.C5376a) arrayList.get(1);
            HashMap hashMap = new HashMap();
            C5374iw.m2375a(hashMap, aVar.f22067a);
            C5374iw.m2375a(hashMap, aVar.f22068b);
            C5374iw.m2375a(hashMap, aVar2.f22067a);
            C5374iw.m2375a(hashMap, aVar2.f22068b);
            ResultPoint onVar6 = null;
            ResultPoint onVar7 = null;
            ResultPoint onVar8 = null;
            for (Map.Entry entry : hashMap.entrySet()) {
                ResultPoint onVar9 = (ResultPoint) entry.getKey();
                if (((Integer) entry.getValue()).intValue() == 2) {
                    onVar7 = onVar9;
                } else if (onVar6 == null) {
                    onVar6 = onVar9;
                } else {
                    onVar8 = onVar9;
                }
            }
            if (onVar6 == null || onVar7 == null || onVar8 == null) {
                throw NotFoundException.m1647a();
            }
            ResultPoint[] onVarArr2 = {onVar6, onVar7, onVar8};
            ResultPoint.m1622a(onVarArr2);
            ResultPoint onVar10 = onVarArr2[0];
            ResultPoint onVar11 = onVarArr2[1];
            ResultPoint onVar12 = onVarArr2[2];
            if (!hashMap.containsKey(onVar2)) {
                onVar5 = onVar2;
            } else if (!hashMap.containsKey(onVar3)) {
                onVar5 = onVar3;
            } else if (!hashMap.containsKey(onVar4)) {
                onVar5 = onVar4;
            }
            int i = iwVar.m2369b(onVar12, onVar5).f22069c;
            int i2 = iwVar.m2369b(onVar10, onVar5).f22069c;
            if ((i & 1) == 1) {
                i++;
            }
            int i3 = i + 2;
            if ((i2 & 1) == 1) {
                i2++;
            }
            int i4 = i2 + 2;
            if (i3 * 4 >= i4 * 7 || i4 * 4 >= i3 * 7) {
                float a2 = C5374iw.m2372a(onVar11, onVar10) / i3;
                float a3 = C5374iw.m2372a(onVar12, onVar5);
                ResultPoint onVar13 = new ResultPoint(onVar5.f22726c + (((onVar5.f22726c - onVar12.f22726c) / a3) * a2), onVar5.f22727d + (a2 * ((onVar5.f22727d - onVar12.f22727d) / a3)));
                float a4 = C5374iw.m2372a(onVar11, onVar12) / i4;
                float a5 = C5374iw.m2372a(onVar10, onVar5);
                onVar = new ResultPoint(onVar5.f22726c + (((onVar5.f22726c - onVar10.f22726c) / a5) * a4), onVar5.f22727d + (a4 * ((onVar5.f22727d - onVar10.f22727d) / a5)));
                if (!iwVar.m2373a(onVar13)) {
                    if (!iwVar.m2373a(onVar)) {
                        onVar = null;
                    }
                } else if (!iwVar.m2373a(onVar) || Math.abs(i3 - iwVar.m2369b(onVar12, onVar13).f22069c) + Math.abs(i4 - iwVar.m2369b(onVar10, onVar13).f22069c) <= Math.abs(i3 - iwVar.m2369b(onVar12, onVar).f22069c) + Math.abs(i4 - iwVar.m2369b(onVar10, onVar).f22069c)) {
                    onVar = onVar13;
                }
                if (onVar == null) {
                    onVar = onVar5;
                }
                int i5 = iwVar.m2369b(onVar12, onVar).f22069c;
                int i6 = iwVar.m2369b(onVar10, onVar).f22069c;
                hyVar = C5374iw.m2374a(iwVar.f22065a, onVar12, onVar11, onVar10, onVar, (i5 & 1) == 1 ? i5 + 1 : i5, (i6 & 1) == 1 ? i6 + 1 : i6);
            } else {
                float min = Math.min(i4, i3);
                float a6 = C5374iw.m2372a(onVar11, onVar10) / min;
                float a7 = C5374iw.m2372a(onVar12, onVar5);
                ResultPoint onVar14 = new ResultPoint(onVar5.f22726c + (((onVar5.f22726c - onVar12.f22726c) / a7) * a6), onVar5.f22727d + (a6 * ((onVar5.f22727d - onVar12.f22727d) / a7)));
                float a8 = C5374iw.m2372a(onVar11, onVar12) / min;
                float a9 = C5374iw.m2372a(onVar10, onVar5);
                ResultPoint onVar15 = new ResultPoint(onVar5.f22726c + (((onVar5.f22726c - onVar10.f22726c) / a9) * a8), onVar5.f22727d + (a8 * ((onVar5.f22727d - onVar10.f22727d) / a9)));
                if (!iwVar.m2373a(onVar14)) {
                    if (!iwVar.m2373a(onVar15)) {
                        onVar15 = null;
                    }
                } else if (!iwVar.m2373a(onVar15) || Math.abs(iwVar.m2369b(onVar12, onVar14).f22069c - iwVar.m2369b(onVar10, onVar14).f22069c) <= Math.abs(iwVar.m2369b(onVar12, onVar15).f22069c - iwVar.m2369b(onVar10, onVar15).f22069c)) {
                    onVar15 = onVar14;
                }
                if (onVar15 != null) {
                    onVar5 = onVar15;
                }
                int max = Math.max(iwVar.m2369b(onVar12, onVar5).f22069c, iwVar.m2369b(onVar10, onVar5).f22069c) + 1;
                int i7 = (max & 1) == 1 ? max + 1 : max;
                hyVar = C5374iw.m2374a(iwVar.f22065a, onVar12, onVar11, onVar10, onVar5, i7, i7);
                onVar = onVar5;
            }
            DetectorResult iiVar = new DetectorResult(hyVar, new ResultPoint[]{onVar12, onVar11, onVar10, onVar});
            igVar = this.f22032b.m2394a(iiVar.f21999d);
            onVarArr = iiVar.f22000e;
        } else {
            BitMatrix c = htVar.m2557c();
            int[] b = c.m2512b();
            int[] c2 = c.m2508c();
            if (b == null || c2 == null) {
                throw NotFoundException.m1647a();
            }
            int i8 = c.f21920a;
            int i9 = b[0];
            int i10 = b[1];
            while (i9 < i8 && c.m2519a(i9, i10)) {
                i9++;
            }
            if (i9 != i8) {
                int i11 = i9 - b[0];
                if (i11 != 0) {
                    int i12 = b[1];
                    int i13 = c2[1];
                    int i14 = b[0];
                    int i15 = ((c2[0] - i14) + 1) / i11;
                    int i16 = ((i13 - i12) + 1) / i11;
                    if (i15 <= 0 || i16 <= 0) {
                        throw NotFoundException.m1647a();
                    }
                    int i17 = i11 / 2;
                    int i18 = i12 + i17;
                    int i19 = i14 + i17;
                    BitMatrix hyVar2 = new BitMatrix(i15, i16);
                    for (int i20 = 0; i20 < i16; i20++) {
                        int i21 = (i20 * i11) + i18;
                        for (int i22 = 0; i22 < i15; i22++) {
                            if (c.m2519a((i22 * i11) + i19, i21)) {
                                hyVar2.m2511b(i22, i20);
                            }
                        }
                    }
                    igVar = this.f22032b.m2394a(hyVar2);
                    onVarArr = f22031a;
                } else {
                    throw NotFoundException.m1647a();
                }
            } else {
                throw NotFoundException.m1647a();
            }
        }
        C5422ol olVar = new C5422ol(igVar.f21991c, igVar.f21989a, onVarArr, BarcodeFormat.DATA_MATRIX);
        List<byte[]> list = igVar.f21992d;
        if (list != null) {
            olVar.m1633a(ResultMetadataType.BYTE_SEGMENTS, list);
        }
        String str = igVar.f21993e;
        if (str != null) {
            olVar.m1633a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
        }
        return olVar;
    }

    /* renamed from: a */
    private static BitMatrix m2419a(BitMatrix hyVar) throws NotFoundException {
        int[] b = hyVar.m2512b();
        int[] c = hyVar.m2508c();
        if (b == null || c == null) {
            throw NotFoundException.m1647a();
        }
        int i = hyVar.f21920a;
        int i2 = b[0];
        int i3 = b[1];
        while (i2 < i && hyVar.m2519a(i2, i3)) {
            i2++;
        }
        if (i2 != i) {
            int i4 = i2 - b[0];
            if (i4 != 0) {
                int i5 = b[1];
                int i6 = c[1];
                int i7 = b[0];
                int i8 = ((c[0] - i7) + 1) / i4;
                int i9 = ((i6 - i5) + 1) / i4;
                if (i8 <= 0 || i9 <= 0) {
                    throw NotFoundException.m1647a();
                }
                int i10 = i4 / 2;
                int i11 = i5 + i10;
                int i12 = i7 + i10;
                BitMatrix hyVar2 = new BitMatrix(i8, i9);
                for (int i13 = 0; i13 < i9; i13++) {
                    int i14 = (i13 * i4) + i11;
                    for (int i15 = 0; i15 < i8; i15++) {
                        if (hyVar.m2519a((i15 * i4) + i12, i14)) {
                            hyVar2.m2511b(i15, i13);
                        }
                    }
                }
                return hyVar2;
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int m2418a(int[] iArr, BitMatrix hyVar) throws NotFoundException {
        int i = hyVar.f21920a;
        int i2 = iArr[0];
        int i3 = iArr[1];
        while (i2 < i && hyVar.m2519a(i2, i3)) {
            i2++;
        }
        if (i2 != i) {
            int i4 = i2 - iArr[0];
            if (i4 != 0) {
                return i4;
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }
}
