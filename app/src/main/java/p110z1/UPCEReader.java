package p110z1;

/* renamed from: z1.lc */
/* loaded from: classes3.dex */
public final class UPCEReader extends UPCEANReader {

    /* renamed from: h */
    private final int[] f22303h = new int[4];

    /* renamed from: g */
    private static final int[] f22302g = {1, 1, 1, 1, 1, 1};

    /* renamed from: a */
    static final int[][] f22301a = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    public final int mo2063a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f22303h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = huVar.f21908b;
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 6 && i2 < i) {
            int a = m2064a(huVar, iArr2, i2, f22398f);
            sb.append((char) ((a % 10) + 48));
            int i5 = i2;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (a >= 10) {
                i4 = (1 << (5 - i3)) | i4;
            }
            i3++;
            i2 = i5;
        }
        for (int i7 = 0; i7 <= 1; i7++) {
            for (int i8 = 0; i8 < 10; i8++) {
                if (i4 == f22301a[i7][i8]) {
                    sb.insert(0, (char) (i7 + 48));
                    sb.append((char) (i8 + 48));
                    return i2;
                }
            }
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    protected final int[] mo2067a(BitArray huVar, int i) throws NotFoundException {
        return m2066a(huVar, i, true, f22302g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.UPCEANReader
    /* renamed from: a */
    public final boolean mo2069a(String str) throws FormatException {
        return super.mo2069a(m2123b(str));
    }

    /* renamed from: a */
    private static void m2124a(StringBuilder sb, int i) throws NotFoundException {
        for (int i2 = 0; i2 <= 1; i2++) {
            for (int i3 = 0; i3 < 10; i3++) {
                if (i == f22301a[i2][i3]) {
                    sb.insert(0, (char) (i2 + 48));
                    sb.append((char) (i3 + 48));
                    return;
                }
            }
        }
        throw NotFoundException.m1647a();
    }

    @Override // p110z1.UPCEANReader
    /* renamed from: b */
    final BarcodeFormat mo2062b() {
        return BarcodeFormat.UPC_E;
    }

    /* renamed from: b */
    public static String m2123b(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case '0':
            case '1':
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c);
                break;
        }
        if (str.length() >= 8) {
            sb.append(str.charAt(7));
        }
        return sb.toString();
    }
}
