package p110z1;

/* renamed from: z1.oa */
/* loaded from: classes3.dex */
final class MaskUtil {

    /* renamed from: a */
    private static final int f22671a = 3;

    /* renamed from: b */
    private static final int f22672b = 3;

    /* renamed from: c */
    private static final int f22673c = 40;

    /* renamed from: d */
    private static final int f22674d = 10;

    private MaskUtil() {
    }

    /* renamed from: a */
    private static int m1693a(ByteMatrix nyVar) {
        return m1692a(nyVar, true) + m1692a(nyVar, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m1691a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m1690a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m1694a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 & 1) + (i5 % 3);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 & 1) + (i6 % 3)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: ".concat(String.valueOf(i)));
        }
        return i4 == 0;
    }

    /* renamed from: b */
    private static int m1689b(ByteMatrix nyVar) {
        byte[][] bArr = nyVar.f22660a;
        int i = nyVar.f22661b;
        int i2 = nyVar.f22662c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2 - 1) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            int i6 = 0;
            while (i6 < i - 1) {
                byte b = bArr2[i6];
                int i7 = i6 + 1;
                if (b == bArr2[i7]) {
                    int i8 = i3 + 1;
                    if (b == bArr[i8][i6] && b == bArr[i8][i7]) {
                        i5++;
                    }
                }
                i6 = i7;
            }
            i3++;
            i4 = i5;
        }
        return i4 * 3;
    }

    /* renamed from: c */
    private static int m1688c(ByteMatrix nyVar) {
        byte[][] bArr = nyVar.f22660a;
        int i = nyVar.f22661b;
        int i2 = nyVar.f22662c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4;
            for (int i6 = 0; i6 < i; i6++) {
                byte[] bArr2 = bArr[i3];
                int i7 = i6 + 6;
                if (i7 < i && bArr2[i6] == 1 && bArr2[i6 + 1] == 0 && bArr2[i6 + 2] == 1 && bArr2[i6 + 3] == 1 && bArr2[i6 + 4] == 1 && bArr2[i6 + 5] == 0 && bArr2[i7] == 1 && (m1691a(bArr2, i6 - 4, i6) || m1691a(bArr2, i6 + 7, i6 + 11))) {
                    i5++;
                }
                int i8 = i3 + 6;
                if (i8 < i2 && bArr[i3][i6] == 1 && bArr[i3 + 1][i6] == 0 && bArr[i3 + 2][i6] == 1 && bArr[i3 + 3][i6] == 1 && bArr[i3 + 4][i6] == 1 && bArr[i3 + 5][i6] == 0 && bArr[i8][i6] == 1 && (m1690a(bArr, i6, i3 - 4, i3) || m1690a(bArr, i6, i3 + 7, i3 + 11))) {
                    i5++;
                }
            }
            i3++;
            i4 = i5;
        }
        return i4 * 40;
    }

    /* renamed from: d */
    private static int m1687d(ByteMatrix nyVar) {
        byte[][] bArr = nyVar.f22660a;
        int i = nyVar.f22661b;
        int i2 = nyVar.f22662c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            for (int i6 = 0; i6 < i; i6++) {
                if (bArr2[i6] == 1) {
                    i5++;
                }
            }
            i3++;
            i4 = i5;
        }
        int i7 = nyVar.f22662c * nyVar.f22661b;
        return ((Math.abs((i4 << 1) - i7) * 10) / i7) * 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1692a(ByteMatrix nyVar, boolean z) {
        int i;
        int i2;
        if (z) {
            i = nyVar.f22662c;
        } else {
            i = nyVar.f22661b;
        }
        if (z) {
            i2 = nyVar.f22661b;
        } else {
            i2 = nyVar.f22662c;
        }
        byte[][] bArr = nyVar.f22660a;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = i3;
            int i5 = 0;
            byte b = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                byte b2 = z ? bArr[i4][i6] : bArr[i6][i4];
                if (b2 == b) {
                    i5++;
                } else {
                    if (i5 >= 5) {
                        i3 += (i5 - 5) + 3;
                    }
                    i5 = 1;
                    b = b2;
                }
            }
            if (i5 >= 5) {
                i3 += (i5 - 5) + 3;
            }
        }
        return i3;
    }
}
