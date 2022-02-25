package p110z1;

import java.util.Map;

/* renamed from: z1.iv */
/* loaded from: classes3.dex */
public final class DataMatrixWriter implements Writer {
    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) {
        C5384jq jqVar;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (fuVar != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got ".concat(String.valueOf(fuVar)));
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions can't be negative: " + i + 'x' + i2);
        } else {
            SymbolShapeHint jiVar = SymbolShapeHint.FORCE_NONE;
            C5384jq jqVar2 = null;
            if (map != null) {
                SymbolShapeHint jiVar2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (jiVar2 != null) {
                    jiVar = jiVar2;
                }
                jqVar = (C5384jq) map.get(EncodeHintType.MIN_SIZE);
                if (jqVar == null) {
                    jqVar = null;
                }
                C5384jq jqVar3 = (C5384jq) map.get(EncodeHintType.MAX_SIZE);
                if (jqVar3 != null) {
                    jqVar2 = jqVar3;
                }
            } else {
                jqVar = null;
            }
            String a = C5380jg.m2303a(str, jiVar, jqVar, jqVar2);
            SymbolInfo a2 = SymbolInfo.m2290a(a.length(), jiVar, jqVar, jqVar2, true);
            DefaultPlacement jbVar = new DefaultPlacement(ErrorCorrection.m2310a(a, a2), a2.m2286b(), a2.m2284c());
            jbVar.m2348a();
            return m2378a(jbVar, a2, i, i2);
        }
    }

    /* renamed from: a */
    private static BitMatrix m2378a(DefaultPlacement jbVar, SymbolInfo jhVar, int i, int i2) {
        BitMatrix hyVar;
        int b = jhVar.m2286b();
        int c = jhVar.m2284c();
        ByteMatrix nyVar = new ByteMatrix(jhVar.m2283d(), jhVar.m2282e());
        int i3 = 0;
        for (int i4 = 0; i4 < c; i4++) {
            if (i4 % jhVar.f22117e == 0) {
                int i5 = 0;
                for (int i6 = 0; i6 < jhVar.m2283d(); i6++) {
                    nyVar.m1742a(i5, i3, i6 % 2 == 0);
                    i5++;
                }
                i3++;
            }
            int i7 = 0;
            for (int i8 = 0; i8 < b; i8++) {
                if (i8 % jhVar.f22116d == 0) {
                    nyVar.m1742a(i7, i3, true);
                    i7++;
                }
                nyVar.m1742a(i7, i3, jbVar.f22075b[(jbVar.f22074a * i4) + i8] == 1);
                i7++;
                if (i8 % jhVar.f22116d == jhVar.f22116d - 1) {
                    nyVar.m1742a(i7, i3, i4 % 2 == 0);
                    i7++;
                }
            }
            i3++;
            if (i4 % jhVar.f22117e == jhVar.f22117e - 1) {
                int i9 = 0;
                for (int i10 = 0; i10 < jhVar.m2283d(); i10++) {
                    nyVar.m1742a(i9, i3, true);
                    i9++;
                }
                i3++;
            }
        }
        int i11 = nyVar.f22661b;
        int i12 = nyVar.f22662c;
        int max = Math.max(i, i11);
        int max2 = Math.max(i2, i12);
        int min = Math.min(max / i11, max2 / i12);
        int i13 = (max - (i11 * min)) / 2;
        int i14 = (max2 - (i12 * min)) / 2;
        if (i2 < i12 || i < i11) {
            hyVar = new BitMatrix(i11, i12);
            i13 = 0;
            i14 = 0;
        } else {
            hyVar = new BitMatrix(i, i2);
        }
        hyVar.m2520a();
        int i15 = 0;
        while (i15 < i12) {
            int i16 = 0;
            while (i16 < i11) {
                if (nyVar.m1745a(i16, i15) == 1) {
                    hyVar.m2518a(i13, i14, min, min);
                }
                i16++;
                i13 += min;
            }
            i15++;
            i14 += min;
        }
        return hyVar;
    }

    /* renamed from: a */
    private static BitMatrix m2377a(ByteMatrix nyVar, int i, int i2) {
        BitMatrix hyVar;
        int i3 = nyVar.f22661b;
        int i4 = nyVar.f22662c;
        int max = Math.max(i, i3);
        int max2 = Math.max(i2, i4);
        int min = Math.min(max / i3, max2 / i4);
        int i5 = (max - (i3 * min)) / 2;
        int i6 = (max2 - (i4 * min)) / 2;
        if (i2 < i4 || i < i3) {
            hyVar = new BitMatrix(i3, i4);
            i5 = 0;
            i6 = 0;
        } else {
            hyVar = new BitMatrix(i, i2);
        }
        hyVar.m2520a();
        int i7 = 0;
        while (i7 < i4) {
            int i8 = 0;
            while (i8 < i3) {
                if (nyVar.m1745a(i8, i7) == 1) {
                    hyVar.m2518a(i5, i6, min, min);
                }
                i8++;
                i5 += min;
            }
            i7++;
            i6 += min;
        }
        return hyVar;
    }
}
