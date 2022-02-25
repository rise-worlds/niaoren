package p110z1;

import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

/* renamed from: z1.le */
/* loaded from: classes3.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {

    /* renamed from: a */
    private static final char[] f22305a = {'A', 'B', 'C', 'D'};

    /* renamed from: b */
    private static final char[] f22306b = {'T', 'N', '*', 'E'};

    /* renamed from: c */
    private static final char[] f22307c = {IOUtils.DIR_SEPARATOR_UNIX, ':', '+', FilenameUtils.EXTENSION_SEPARATOR};

    /* renamed from: d */
    private static final char f22308d = f22305a[0];

    @Override // p110z1.OneDimensionalCodeWriter
    /* renamed from: a */
    public final boolean[] mo2086a(String str) {
        int i;
        if (str.length() < 2) {
            str = f22308d + str + f22308d;
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean a = CodaBarReader.m2243a(f22305a, upperCase);
            boolean a2 = CodaBarReader.m2243a(f22305a, upperCase2);
            boolean a3 = CodaBarReader.m2243a(f22306b, upperCase);
            boolean a4 = CodaBarReader.m2243a(f22306b, upperCase2);
            if (a) {
                if (!a2) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (a3) {
                if (!a4) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (a2 || a4) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
            } else {
                str = f22308d + str + f22308d;
            }
        }
        int i2 = 20;
        for (int i3 = 1; i3 < str.length() - 1; i3++) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                i2 += 9;
            } else if (CodaBarReader.m2243a(f22307c, str.charAt(i3))) {
                i2 += 10;
            } else {
                throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i3) + '\'');
            }
        }
        boolean[] zArr = new boolean[i2 + (str.length() - 1)];
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i5));
            if (i5 == 0 || i5 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i6 = 0;
            while (true) {
                if (i6 >= CodaBarReader.f22193a.length) {
                    i = 0;
                    break;
                } else if (upperCase3 == CodaBarReader.f22193a[i6]) {
                    i = CodaBarReader.f22194b[i6];
                    break;
                } else {
                    i6++;
                }
            }
            int i7 = 0;
            boolean z = true;
            int i8 = 0;
            while (i7 < 7) {
                zArr[i4] = z;
                i4++;
                if (((i >> (6 - i7)) & 1) == 0 || i8 == 1) {
                    z = !z;
                    i7++;
                    i8 = 0;
                } else {
                    i8++;
                }
            }
            if (i5 < str.length() - 1) {
                zArr[i4] = false;
                i4++;
            }
        }
        return zArr;
    }
}
