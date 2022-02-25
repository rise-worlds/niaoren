package p110z1;

import java.util.Map;

/* renamed from: z1.lo */
/* loaded from: classes3.dex */
public final class EAN8Writer extends UPCEANWriter {

    /* renamed from: a */
    private static final int f22363a = 67;

    @Override // p110z1.OneDimensionalCodeWriter, p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.EAN_8) {
            return super.mo1618a(str, fuVar, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int length = str.length();
        switch (length) {
            case 7:
                try {
                    str = str + UPCEANReader.m2061b(str);
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
        boolean[] zArr = new boolean[67];
        int a = m2084a(zArr, 0, UPCEANReader.f22394b, true) + 0;
        for (int i = 0; i <= 3; i++) {
            a += m2084a(zArr, a, UPCEANReader.f22397e[Character.digit(str.charAt(i), 10)], false);
        }
        int a2 = a + m2084a(zArr, a, UPCEANReader.f22395c, false);
        for (int i2 = 4; i2 <= 7; i2++) {
            a2 += m2084a(zArr, a2, UPCEANReader.f22397e[Character.digit(str.charAt(i2), 10)], true);
        }
        m2084a(zArr, a2, UPCEANReader.f22394b, true);
        return zArr;
    }
}
