package p110z1;

import java.util.Map;

/* renamed from: z1.lm */
/* loaded from: classes3.dex */
public final class EAN13Writer extends UPCEANWriter {

    /* renamed from: a */
    private static final int f22361a = 95;

    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.EAN_13) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        switch (length) {
            case 12:
                try {
                    str = str + UPCEANReader.m2061b(str);
                    break;
                } catch (FormatException e) {
                    throw new IllegalArgumentException(e);
                }
            case 13:
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
                throw new IllegalArgumentException("Requested contents should be 12 or 13 digits long, but got ".concat(String.valueOf(length)));
        }
        int i = EAN13Reader.f22359a[Character.digit(str.charAt(0), 10)];
        boolean[] zArr = new boolean[95];
        int a = m2084a(zArr, 0, UPCEANReader.f22394b, true) + 0;
        for (int i2 = 1; i2 <= 6; i2++) {
            int digit = Character.digit(str.charAt(i2), 10);
            if (((i >> (6 - i2)) & 1) == 1) {
                digit += 10;
            }
            a += m2084a(zArr, a, UPCEANReader.f22398f[digit], false);
        }
        int a2 = a + m2084a(zArr, a, UPCEANReader.f22395c, false);
        for (int i3 = 7; i3 <= 12; i3++) {
            a2 += m2084a(zArr, a2, UPCEANReader.f22397e[Character.digit(str.charAt(i3), 10)], true);
        }
        m2084a(zArr, a2, UPCEANReader.f22394b, true);
        return zArr;
    }
}
