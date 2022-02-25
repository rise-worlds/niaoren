package p110z1;

import java.util.Map;

/* renamed from: z1.ld */
/* loaded from: classes3.dex */
public final class UPCEWriter extends UPCEANWriter {

    /* renamed from: a */
    private static final int f22304a = 51;

    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.UPC_E) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC_E, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        switch (length) {
            case 7:
                try {
                    str = str + UPCEANReader.m2061b(UPCEReader.m2123b(str));
                    break;
                } catch (FormatException e) {
                    throw new IllegalArgumentException(e);
                }
            case 8:
                try {
                    if (UPCEANReader.m2070a((CharSequence) str)) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Contents do not pass checksum");
                    }
                } catch (FormatException unused) {
                    throw new IllegalArgumentException("Illegal contents");
                }
            default:
                throw new IllegalArgumentException("Requested contents should be 8 digits long, but got ".concat(String.valueOf(length)));
        }
        int digit = Character.digit(str.charAt(0), 10);
        if (digit == 0 || digit == 1) {
            int i = UPCEReader.f22301a[digit][Character.digit(str.charAt(7), 10)];
            boolean[] zArr = new boolean[51];
            int a = m2084a(zArr, 0, UPCEANReader.f22394b, true) + 0;
            for (int i2 = 1; i2 <= 6; i2++) {
                int digit2 = Character.digit(str.charAt(i2), 10);
                if (((i >> (6 - i2)) & 1) == 1) {
                    digit2 += 10;
                }
                a += m2084a(zArr, a, UPCEANReader.f22398f[digit2], false);
            }
            m2084a(zArr, a, UPCEANReader.f22396d, false);
            return zArr;
        }
        throw new IllegalArgumentException("Number system must be 0 or 1");
    }
}
