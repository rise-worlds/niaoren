package p110z1;

import java.util.Map;

/* renamed from: z1.lr */
/* loaded from: classes3.dex */
public final class ITFWriter extends OneDimensionalCodeWriter {

    /* renamed from: c */
    private static final int f22378c = 3;

    /* renamed from: d */
    private static final int f22379d = 1;

    /* renamed from: a */
    private static final int[] f22376a = {1, 1, 1, 1};

    /* renamed from: b */
    private static final int[] f22377b = {3, 1, 1};

    /* renamed from: e */
    private static final int[][] f22380e = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.ITF) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode ITF, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length <= 80) {
            boolean[] zArr = new boolean[(length * 9) + 9];
            int a = m2084a(zArr, 0, f22376a, true);
            for (int i = 0; i < length; i += 2) {
                int digit = Character.digit(str.charAt(i), 10);
                int digit2 = Character.digit(str.charAt(i + 1), 10);
                int[] iArr = new int[10];
                for (int i2 = 0; i2 < 5; i2++) {
                    int i3 = i2 * 2;
                    int[][] iArr2 = f22380e;
                    iArr[i3] = iArr2[digit][i2];
                    iArr[i3 + 1] = iArr2[digit2][i2];
                }
                a += m2084a(zArr, a, iArr, true);
            }
            m2084a(zArr, a, f22377b, true);
            return zArr;
        } else {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
    }
}
