package p110z1;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* renamed from: z1.nd */
/* loaded from: classes3.dex */
public final class PDF417Writer implements Writer {

    /* renamed from: a */
    private static final int f22571a = 30;

    /* renamed from: b */
    private static final int f22572b = 2;

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        boolean z;
        if (fuVar == BarcodeFormat.PDF_417) {
            PDF417 naVar = new PDF417();
            int i3 = 30;
            int i4 = 2;
            if (map != null) {
                if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                    naVar.f22542b = Boolean.valueOf(map.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue();
                }
                if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                    naVar.f22543c = Compaction.valueOf(map.get(EncodeHintType.PDF417_COMPACTION).toString());
                }
                if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                    Dimensions mzVar = (Dimensions) map.get(EncodeHintType.PDF417_DIMENSIONS);
                    int i5 = mzVar.f22532b;
                    int i6 = mzVar.f22531a;
                    int i7 = mzVar.f22534d;
                    int i8 = mzVar.f22533c;
                    naVar.f22546f = i5;
                    naVar.f22545e = i6;
                    naVar.f22547g = i7;
                    naVar.f22548h = i8;
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    i4 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                    naVar.f22544d = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
                }
            }
            naVar.m1877a(str, i4);
            byte[][] a = naVar.f22541a.m1895a(1, 4);
            if ((i2 > i) != (a[0].length < a.length)) {
                a = m1854a(a);
                z = true;
            } else {
                z = false;
            }
            int length = i / a[0].length;
            int length2 = i2 / a.length;
            if (length >= length2) {
                length = length2;
            }
            if (length <= 1) {
                return m1853a(a, i3);
            }
            byte[][] a2 = naVar.f22541a.m1895a(length, length << 2);
            if (z) {
                a2 = m1854a(a2);
            }
            return m1853a(a2, i3);
        }
        throw new IllegalArgumentException("Can only encode PDF_417, but got ".concat(String.valueOf(fuVar)));
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) throws WriterException {
        return mo1618a(str, fuVar, i, i2, null);
    }

    /* renamed from: a */
    private static BitMatrix m1853a(byte[][] bArr, int i) {
        int i2 = i * 2;
        BitMatrix hyVar = new BitMatrix(bArr[0].length + i2, bArr.length + i2);
        hyVar.m2520a();
        int i3 = (hyVar.f21921b - i) - 1;
        int i4 = 0;
        while (i4 < bArr.length) {
            byte[] bArr2 = bArr[i4];
            for (int i5 = 0; i5 < bArr[0].length; i5++) {
                if (bArr2[i5] == 1) {
                    hyVar.m2511b(i5 + i, i3);
                }
            }
            i4++;
            i3--;
        }
        return hyVar;
    }

    /* renamed from: a */
    private static byte[][] m1854a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, bArr[0].length, bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }

    /* renamed from: a */
    private static BitMatrix m1855a(PDF417 naVar, String str, int i, int i2, int i3, int i4) throws WriterException {
        int i5;
        boolean z;
        int i6;
        int a;
        int a2 = PDF417ErrorCorrection.m1872a(i);
        String a3 = PDF417HighLevelEncoder.m1863a(str, naVar.f22543c, naVar.f22544d);
        int length = a3.length();
        float f = 0.0f;
        int[] iArr = null;
        for (int i7 = naVar.f22545e; i7 <= naVar.f22546f && (a = PDF417.m1881a(length, a2, i7)) >= naVar.f22548h; i7++) {
            if (a <= naVar.f22547g) {
                float f2 = (((i7 * 17) + 69) * 0.357f) / (a * 2.0f);
                if (iArr == null || Math.abs(f2 - 3.0f) <= Math.abs(f - 3.0f)) {
                    iArr = new int[]{i7, a};
                    f = f2;
                }
            }
        }
        if (iArr != null) {
            i5 = 1;
        } else if (PDF417.m1881a(length, a2, naVar.f22545e) < naVar.f22548h) {
            i5 = 1;
            iArr = new int[]{naVar.f22545e, naVar.f22548h};
        } else {
            i5 = 1;
        }
        if (iArr != null) {
            int i8 = iArr[0];
            int i9 = iArr[i5];
            int a4 = PDF417.m1880a(length, a2, i8, i9);
            if (a2 + length + i5 <= 929) {
                int i10 = length + a4 + i5;
                StringBuilder sb = new StringBuilder(i10);
                sb.append((char) i10);
                sb.append(a3);
                for (int i11 = 0; i11 < a4; i11++) {
                    sb.append((char) 900);
                }
                String sb2 = sb.toString();
                String a5 = PDF417ErrorCorrection.m1871a(sb2, i);
                naVar.f22541a = new BarcodeMatrix(i9, i8);
                naVar.m1878a(sb2 + a5, i8, i9, i, naVar.f22541a);
                byte[][] a6 = naVar.f22541a.m1895a(1, 4);
                if ((i3 > i2) != (a6[0].length < a6.length)) {
                    a6 = m1854a(a6);
                    z = true;
                } else {
                    z = false;
                }
                int length2 = i2 / a6[0].length;
                int length3 = i3 / a6.length;
                if (length2 >= length3) {
                    length2 = length3;
                    i6 = 1;
                } else {
                    i6 = 1;
                }
                if (length2 <= i6) {
                    return m1853a(a6, i4);
                }
                byte[][] a7 = naVar.f22541a.m1895a(length2, length2 << 2);
                if (z) {
                    a7 = m1854a(a7);
                }
                return m1853a(a7, i4);
            }
            throw new WriterException("Encoded message contains too many code words, message too big (" + str.length() + " bytes)");
        }
        throw new WriterException("Unable to fit message in columns");
    }
}
