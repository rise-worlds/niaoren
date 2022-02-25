package p110z1;

import java.util.Map;

/* renamed from: z1.lk */
/* loaded from: classes3.dex */
public final class Code93Writer extends OneDimensionalCodeWriter {
    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.CODE_93) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_93, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        if (length <= 80) {
            int[] iArr = new int[9];
            m2105a(Code93Reader.f22354b[47], iArr);
            boolean[] zArr = new boolean[((str.length() + 2 + 2) * 9) + 1];
            int b = m2102b(zArr, 0, iArr);
            for (int i = 0; i < length; i++) {
                m2105a(Code93Reader.f22354b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(i))], iArr);
                b += m2102b(zArr, b, iArr);
            }
            int a = m2104a(str, 20);
            m2105a(Code93Reader.f22354b[a], iArr);
            int b2 = b + m2102b(zArr, b, iArr);
            m2105a(Code93Reader.f22354b[m2104a(str + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(a), 15)], iArr);
            int b3 = b2 + m2102b(zArr, b2, iArr);
            m2105a(Code93Reader.f22354b[47], iArr);
            zArr[b3 + m2102b(zArr, b3, iArr)] = true;
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
    }

    /* renamed from: a */
    private static void m2105a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) == 0) {
                i3 = 0;
            }
            iArr[i2] = i3;
        }
    }

    @Deprecated
    /* renamed from: a */
    private static int m2103a(boolean[] zArr, int i, int[] iArr) {
        return m2102b(zArr, i, iArr);
    }

    /* renamed from: b */
    private static int m2102b(boolean[] zArr, int i, int[] iArr) {
        int i2 = i;
        for (int i3 : iArr) {
            i2++;
            zArr[i2] = i3 != 0;
        }
        return 9;
    }

    /* renamed from: a */
    private static int m2104a(String str, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i3;
            i3++;
            if (i3 > i) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }
}
