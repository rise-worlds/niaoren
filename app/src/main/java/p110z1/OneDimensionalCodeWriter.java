package p110z1;

import java.util.Map;

/* renamed from: z1.lv */
/* loaded from: classes3.dex */
public abstract class OneDimensionalCodeWriter implements Writer {
    /* renamed from: a */
    public int mo2060a() {
        return 10;
    }

    /* renamed from: a */
    public abstract boolean[] mo2086a(String str);

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) throws WriterException {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + i + 'x' + i2);
        } else {
            int a = mo2060a();
            if (map != null && map.containsKey(EncodeHintType.MARGIN)) {
                a = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
            }
            return m2085a(mo2086a(str), i, i2, a);
        }
    }

    /* renamed from: a */
    private static BitMatrix m2085a(boolean[] zArr, int i, int i2, int i3) {
        int length = zArr.length;
        int i4 = i3 + length;
        int max = Math.max(i, i4);
        int max2 = Math.max(1, i2);
        int i5 = max / i4;
        BitMatrix hyVar = new BitMatrix(max, max2);
        int i6 = (max - (length * i5)) / 2;
        int i7 = 0;
        while (i7 < length) {
            if (zArr[i7]) {
                hyVar.m2518a(i6, 0, i5, max2);
            }
            i7++;
            i6 += i5;
        }
        return hyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static int m2084a(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = i;
        boolean z2 = z;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = iArr[i3];
            for (int i6 = 0; i6 < i5; i6++) {
                i2++;
                zArr[i2] = z2;
            }
            i4 += i5;
            z2 = !z2;
            i3++;
            i2 = i2;
        }
        return i4;
    }
}
