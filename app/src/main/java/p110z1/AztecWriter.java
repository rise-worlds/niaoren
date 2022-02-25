package p110z1;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/* renamed from: z1.fz */
/* loaded from: classes3.dex */
public final class AztecWriter implements Writer {
    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) {
        Charset charset = StandardCharsets.ISO_8859_1;
        int i3 = 33;
        int i4 = 0;
        if (map != null) {
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i3 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (map.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                i4 = Integer.parseInt(map.get(EncodeHintType.AZTEC_LAYERS).toString());
            }
        }
        if (fuVar == BarcodeFormat.AZTEC) {
            return m2774a(Encoder.m2755a(str.getBytes(charset), i3, i4), i, i2);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(fuVar)));
    }

    /* renamed from: a */
    private static BitMatrix m2775a(String str, BarcodeFormat fuVar, int i, int i2, Charset charset, int i3, int i4) {
        if (fuVar == BarcodeFormat.AZTEC) {
            return m2774a(Encoder.m2755a(str.getBytes(charset), i3, i4), i, i2);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(fuVar)));
    }

    /* renamed from: a */
    private static BitMatrix m2774a(AztecCode gaVar, int i, int i2) {
        BitMatrix hyVar = gaVar.f21752e;
        if (hyVar != null) {
            int i3 = hyVar.f21920a;
            int i4 = hyVar.f21921b;
            int max = Math.max(i, i3);
            int max2 = Math.max(i2, i4);
            int min = Math.min(max / i3, max2 / i4);
            int i5 = (max - (i3 * min)) / 2;
            int i6 = (max2 - (i4 * min)) / 2;
            BitMatrix hyVar2 = new BitMatrix(max, max2);
            int i7 = 0;
            while (i7 < i4) {
                int i8 = i5;
                int i9 = 0;
                while (i9 < i3) {
                    if (hyVar.m2519a(i9, i7)) {
                        hyVar2.m2518a(i8, i6, min, min);
                    }
                    i9++;
                    i8 += min;
                }
                i7++;
                i6 += min;
            }
            return hyVar2;
        }
        throw new IllegalStateException();
    }
}
