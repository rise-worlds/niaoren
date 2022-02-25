package p110z1;

import java.util.Arrays;
import java.util.Map;

/* renamed from: z1.mb */
/* loaded from: classes3.dex */
public abstract class UPCEANReader extends OneDReader {

    /* renamed from: a */
    private static final float f22393a = 0.48f;

    /* renamed from: b */
    static final int[] f22394b = {1, 1, 1};

    /* renamed from: c */
    static final int[] f22395c = {1, 1, 1, 1, 1};

    /* renamed from: d */
    static final int[] f22396d = {1, 1, 1, 1, 1, 1};

    /* renamed from: e */
    static final int[][] f22397e = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};

    /* renamed from: f */
    static final int[][] f22398f = new int[20];

    /* renamed from: g */
    private static final float f22399g = 0.7f;

    /* renamed from: h */
    private final StringBuilder f22400h = new StringBuilder(20);

    /* renamed from: i */
    private final UPCEANExtensionSupport f22401i = new UPCEANExtensionSupport();

    /* renamed from: j */
    private final EANManufacturerOrgSupport f22402j = new EANManufacturerOrgSupport();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo2063a(BitArray huVar, int[] iArr, StringBuilder sb) throws NotFoundException;

    /* renamed from: b */
    abstract BarcodeFormat mo2062b();

    static {
        System.arraycopy(f22397e, 0, f22398f, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = f22397e[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            f22398f[i] = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int[] m2068a(BitArray huVar) throws NotFoundException {
        int[] iArr = new int[f22394b.length];
        int[] iArr2 = null;
        boolean z = false;
        int i = 0;
        while (!z) {
            Arrays.fill(iArr, 0, f22394b.length, 0);
            iArr2 = m2065a(huVar, i, false, f22394b, iArr);
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            int i4 = i2 - (i3 - i2);
            if (i4 >= 0) {
                z = huVar.m2550a(i4, i2);
            }
            i = i3;
        }
        return iArr2;
    }

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return mo2071a(i, huVar, m2068a(huVar), map);
    }

    /* renamed from: a */
    public C5422ol mo2071a(int i, BitArray huVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPointCallback ooVar;
        int i2;
        int[] iArr2;
        boolean z;
        String str = null;
        if (map == null) {
            ooVar = null;
        } else {
            ooVar = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        if (ooVar != null) {
            new ResultPoint((iArr[0] + iArr[1]) / 2.0f, i);
        }
        StringBuilder sb = this.f22400h;
        sb.setLength(0);
        int a = mo2063a(huVar, iArr, sb);
        if (ooVar != null) {
            new ResultPoint(a, i);
        }
        int[] a2 = mo2067a(huVar, a);
        if (ooVar != null) {
            new ResultPoint((a2[0] + a2[1]) / 2.0f, i);
        }
        int i3 = a2[1];
        int i4 = (i3 - a2[0]) + i3;
        if (i4 >= huVar.f21908b || !huVar.m2550a(i3, i4)) {
            throw NotFoundException.m1647a();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.m2059a();
        } else if (mo2069a(sb2)) {
            BarcodeFormat b = mo2062b();
            float f = i;
            C5422ol olVar = new C5422ol(sb2, null, new ResultPoint[]{new ResultPoint((iArr[1] + iArr[0]) / 2.0f, f), new ResultPoint((a2[1] + a2[0]) / 2.0f, f)}, b);
            try {
                C5422ol a3 = this.f22401i.m2073a(i, huVar, a2[1]);
                olVar.m1633a(ResultMetadataType.UPC_EAN_EXTENSION, a3.f22707a);
                olVar.m1634a(a3.f22712f);
                ResultPoint[] onVarArr = a3.f22710d;
                ResultPoint[] onVarArr2 = olVar.f22710d;
                if (onVarArr2 == null) {
                    olVar.f22710d = onVarArr;
                } else if (onVarArr != null && onVarArr.length > 0) {
                    ResultPoint[] onVarArr3 = new ResultPoint[onVarArr2.length + onVarArr.length];
                    System.arraycopy(onVarArr2, 0, onVarArr3, 0, onVarArr2.length);
                    System.arraycopy(onVarArr, 0, onVarArr3, onVarArr2.length, onVarArr.length);
                    olVar.f22710d = onVarArr3;
                }
                i2 = a3.f22707a.length();
            } catch (ReaderException unused) {
                i2 = 0;
            }
            if (map == null) {
                iArr2 = null;
            } else {
                iArr2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (iArr2 != null) {
                int length = iArr2.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        z = false;
                        break;
                    } else if (i2 == iArr2[i5]) {
                        z = true;
                        break;
                    } else {
                        i5++;
                    }
                }
                if (!z) {
                    throw NotFoundException.m1647a();
                }
            }
            if (b == BarcodeFormat.EAN_13 || b == BarcodeFormat.UPC_A) {
                EANManufacturerOrgSupport lpVar = this.f22402j;
                lpVar.m2100a();
                int parseInt = Integer.parseInt(sb2.substring(0, 3));
                int size = lpVar.f22364a.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size) {
                        break;
                    }
                    int[] iArr3 = lpVar.f22364a.get(i6);
                    int i7 = iArr3[0];
                    if (parseInt < i7) {
                        break;
                    }
                    if (iArr3.length != 1) {
                        i7 = iArr3[1];
                    }
                    if (parseInt <= i7) {
                        str = lpVar.f22365b.get(i6);
                        break;
                    }
                    i6++;
                }
                if (str != null) {
                    olVar.m1633a(ResultMetadataType.POSSIBLE_COUNTRY, str);
                }
            }
            return olVar;
        } else {
            throw ChecksumException.m2421a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo2069a(String str) throws FormatException {
        return m2070a((CharSequence) str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2070a(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = length - 1;
        return m2061b(charSequence.subSequence(0, i)) == Character.digit(charSequence.charAt(i), 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static int m2061b(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            int charAt = charSequence.charAt(i2) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.m2059a();
            }
            i += charAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 2; i4 >= 0; i4 -= 2) {
            int charAt2 = charSequence.charAt(i4) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw FormatException.m2059a();
            }
            i3 += charAt2;
        }
        return (1000 - i3) % 10;
    }

    /* renamed from: a */
    int[] mo2067a(BitArray huVar, int i) throws NotFoundException {
        return m2066a(huVar, i, false, f22394b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int[] m2066a(BitArray huVar, int i, boolean z, int[] iArr) throws NotFoundException {
        return m2065a(huVar, i, z, iArr, new int[iArr.length]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m2064a(BitArray huVar, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        m2090a(huVar, i, iArr);
        int length = iArr2.length;
        float f = f22393a;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float a = m2089a(iArr, iArr2[i3], (float) f22399g);
            if (a < f) {
                i2 = i3;
                f = a;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private static int[] m2065a(BitArray huVar, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int i2 = huVar.f21908b;
        int d = z ? huVar.m2538d(i) : huVar.m2541c(i);
        int length = iArr.length;
        int i3 = d;
        int i4 = 0;
        while (d < i2) {
            z = true;
            if (huVar.m2551a(d) != z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (m2089a(iArr2, iArr, (float) f22399g) < f22393a) {
                    return new int[]{i3, d};
                } else {
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                if (z) {
                    z = false;
                }
            }
            d++;
        }
        throw NotFoundException.m1647a();
    }
}
