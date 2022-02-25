package p110z1;

import java.util.Map;

/* renamed from: z1.nq */
/* loaded from: classes3.dex */
public final class QRCodeWriter implements Writer {

    /* renamed from: a */
    private static final int f22630a = 4;

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) throws WriterException {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (fuVar != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got ".concat(String.valueOf(fuVar)));
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            ErrorCorrectionLevel nlVar = ErrorCorrectionLevel.L;
            int i3 = 4;
            if (map != null) {
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    nlVar = ErrorCorrectionLevel.valueOf(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
            }
            return m1795a(C5419nz.m1726a(str, nlVar, map), i, i2, i3);
        }
    }

    /* renamed from: a */
    private static BitMatrix m1795a(QRCode ocVar, int i, int i2, int i3) {
        ByteMatrix nyVar = ocVar.f22687f;
        if (nyVar != null) {
            int i4 = nyVar.f22661b;
            int i5 = nyVar.f22662c;
            int i6 = i3 << 1;
            int i7 = i4 + i6;
            int i8 = i6 + i5;
            int max = Math.max(i, i7);
            int max2 = Math.max(i2, i8);
            int min = Math.min(max / i7, max2 / i8);
            int i9 = (max - (i4 * min)) / 2;
            int i10 = (max2 - (i5 * min)) / 2;
            BitMatrix hyVar = new BitMatrix(max, max2);
            int i11 = 0;
            while (i11 < i5) {
                int i12 = i9;
                int i13 = 0;
                while (i13 < i4) {
                    if (nyVar.m1745a(i13, i11) == 1) {
                        hyVar.m2518a(i12, i10, min, min);
                    }
                    i13++;
                    i12 += min;
                }
                i11++;
                i10 += min;
            }
            return hyVar;
        }
        throw new IllegalStateException();
    }
}
