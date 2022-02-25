package p110z1;

import java.util.Arrays;
import java.util.Map;

/* renamed from: z1.jy */
/* loaded from: classes3.dex */
public final class CodaBarReader extends OneDReader {

    /* renamed from: c */
    private static final float f22195c = 2.0f;

    /* renamed from: d */
    private static final float f22196d = 1.5f;

    /* renamed from: f */
    private static final int f22198f = 3;

    /* renamed from: h */
    private final StringBuilder f22200h = new StringBuilder(20);

    /* renamed from: i */
    private int[] f22201i = new int[80];

    /* renamed from: j */
    private int f22202j = 0;

    /* renamed from: e */
    private static final String f22197e = "0123456789-$:/.+ABCD";

    /* renamed from: a */
    static final char[] f22193a = f22197e.toCharArray();

    /* renamed from: b */
    static final int[] f22194b = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};

    /* renamed from: g */
    private static final char[] f22199g = {'A', 'B', 'C', 'D'};

    @Override // p110z1.OneDReader
    /* renamed from: a */
    public final C5422ol mo2072a(int i, BitArray huVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.f22201i, 0);
        this.f22202j = 0;
        int d = huVar.m2538d(0);
        int i2 = huVar.f21908b;
        if (d < i2) {
            int i3 = 0;
            boolean z = true;
            while (d < i2) {
                if (huVar.m2551a(d) != z) {
                    i3++;
                } else {
                    m2241b(i3);
                    z = !z;
                    i3 = 1;
                }
                d++;
            }
            m2241b(i3);
            int i4 = 1;
            while (i4 < this.f22202j) {
                int c = m2240c(i4);
                if (c != -1 && m2243a(f22199g, f22193a[c])) {
                    int i5 = 0;
                    for (int i6 = i4; i6 < i4 + 7; i6++) {
                        i5 += this.f22201i[i6];
                    }
                    if (i4 == 1 || this.f22201i[i4 - 1] >= i5 / 2) {
                        this.f22200h.setLength(0);
                        int i7 = i4;
                        while (true) {
                            int c2 = m2240c(i7);
                            if (c2 != -1) {
                                this.f22200h.append((char) c2);
                                i7 += 8;
                                if ((this.f22200h.length() <= 1 || !m2243a(f22199g, f22193a[c2])) && i7 < this.f22202j) {
                                }
                            } else {
                                throw NotFoundException.m1647a();
                            }
                        }
                        int i8 = i7 - 1;
                        int i9 = this.f22201i[i8];
                        int i10 = 0;
                        for (int i11 = -8; i11 < -1; i11++) {
                            i10 += this.f22201i[i7 + i11];
                        }
                        if (i7 >= this.f22202j || i9 >= i10 / 2) {
                            int[] iArr = {0, 0, 0, 0};
                            int[] iArr2 = {0, 0, 0, 0};
                            int length = this.f22200h.length() - 1;
                            int i12 = i4;
                            int i13 = 0;
                            while (true) {
                                int i14 = f22194b[this.f22200h.charAt(i13)];
                                for (int i15 = 6; i15 >= 0; i15--) {
                                    int i16 = (i15 & 1) + ((i14 & 1) << 1);
                                    iArr[i16] = iArr[i16] + this.f22201i[i12 + i15];
                                    iArr2[i16] = iArr2[i16] + 1;
                                    i14 >>= 1;
                                }
                                if (i13 >= length) {
                                    break;
                                }
                                i12 += 8;
                                i13++;
                            }
                            float[] fArr = new float[4];
                            float[] fArr2 = new float[4];
                            int i17 = 0;
                            for (int i18 = 2; i17 < i18; i18 = 2) {
                                fArr2[i17] = 0.0f;
                                int i19 = i17 + 2;
                                fArr2[i19] = ((iArr[i17] / iArr2[i17]) + (iArr[i19] / iArr2[i19])) / f22195c;
                                fArr[i17] = fArr2[i19];
                                fArr[i19] = ((iArr[i19] * f22195c) + f22196d) / iArr2[i19];
                                i17++;
                            }
                            int i20 = i4;
                            int i21 = 0;
                            loop8: while (true) {
                                int i22 = f22194b[this.f22200h.charAt(i21)];
                                for (int i23 = 6; i23 >= 0; i23--) {
                                    int i24 = (i23 & 1) + ((i22 & 1) << 1);
                                    float f = this.f22201i[i20 + i23];
                                    if (f < fArr2[i24] || f > fArr[i24]) {
                                        break loop8;
                                    }
                                    i22 >>= 1;
                                }
                                if (i21 < length) {
                                    i20 += 8;
                                    i21++;
                                } else {
                                    for (int i25 = 0; i25 < this.f22200h.length(); i25++) {
                                        StringBuilder sb = this.f22200h;
                                        sb.setCharAt(i25, f22193a[sb.charAt(i25)]);
                                    }
                                    if (m2243a(f22199g, this.f22200h.charAt(0))) {
                                        StringBuilder sb2 = this.f22200h;
                                        if (!m2243a(f22199g, sb2.charAt(sb2.length() - 1))) {
                                            throw NotFoundException.m1647a();
                                        } else if (this.f22200h.length() > 3) {
                                            if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
                                                StringBuilder sb3 = this.f22200h;
                                                sb3.deleteCharAt(sb3.length() - 1);
                                                this.f22200h.deleteCharAt(0);
                                            }
                                            int i26 = 0;
                                            for (int i27 = 0; i27 < i4; i27++) {
                                                i26 += this.f22201i[i27];
                                            }
                                            float f2 = i26;
                                            while (i4 < i8) {
                                                i26 += this.f22201i[i4];
                                                i4++;
                                            }
                                            float f3 = i;
                                            return new C5422ol(this.f22200h.toString(), null, new ResultPoint[]{new ResultPoint(f2, f3), new ResultPoint(i26, f3)}, BarcodeFormat.CODABAR);
                                        } else {
                                            throw NotFoundException.m1647a();
                                        }
                                    } else {
                                        throw NotFoundException.m1647a();
                                    }
                                }
                            }
                            throw NotFoundException.m1647a();
                        }
                        throw NotFoundException.m1647a();
                    }
                }
                i4 += 2;
            }
            throw NotFoundException.m1647a();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2245a(int i) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.f22200h.length() - 1;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = f22194b[this.f22200h.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) << 1);
                iArr[i7] = iArr[i7] + this.f22201i[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i8 = 0; i8 < 2; i8++) {
            fArr2[i8] = 0.0f;
            int i9 = i8 + 2;
            fArr2[i9] = ((iArr[i8] / iArr2[i8]) + (iArr[i9] / iArr2[i9])) / f22195c;
            fArr[i8] = fArr2[i9];
            fArr[i9] = ((iArr[i9] * f22195c) + f22196d) / iArr2[i9];
        }
        loop3: while (true) {
            int i10 = f22194b[this.f22200h.charAt(i2)];
            for (int i11 = 6; i11 >= 0; i11--) {
                int i12 = (i11 & 1) + ((i10 & 1) << 1);
                float f = this.f22201i[i + i11];
                if (f < fArr2[i12] || f > fArr[i12]) {
                    break loop3;
                }
                i10 >>= 1;
            }
            if (i2 < length) {
                i += 8;
                i2++;
            } else {
                return;
            }
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2244a(BitArray huVar) throws NotFoundException {
        int i = 0;
        this.f22202j = 0;
        int d = huVar.m2538d(0);
        int i2 = huVar.f21908b;
        if (d < i2) {
            boolean z = true;
            while (d < i2) {
                if (huVar.m2551a(d) != z) {
                    i++;
                } else {
                    m2241b(i);
                    z = !z;
                    i = 1;
                }
                d++;
            }
            m2241b(i);
            return;
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: b */
    private void m2241b(int i) {
        int[] iArr = this.f22201i;
        int i2 = this.f22202j;
        iArr[i2] = i;
        this.f22202j = i2 + 1;
        int i3 = this.f22202j;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[i3 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.f22201i = iArr2;
        }
    }

    /* renamed from: b */
    private int m2242b() throws NotFoundException {
        for (int i = 1; i < this.f22202j; i += 2) {
            int c = m2240c(i);
            if (c != -1 && m2243a(f22199g, f22193a[c])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.f22201i[i3];
                }
                if (i == 1 || this.f22201i[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.m1647a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m2243a(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: c */
    private int m2240c(int i) {
        int i2 = i + 7;
        if (i2 >= this.f22202j) {
            return -1;
        }
        int[] iArr = this.f22201i;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        for (int i7 = i; i7 < i2; i7 += 2) {
            int i8 = iArr[i7];
            if (i8 < i5) {
                i5 = i8;
            }
            if (i8 > i6) {
                i6 = i8;
            }
        }
        int i9 = (i5 + i6) / 2;
        int i10 = 0;
        for (int i11 = i + 1; i11 < i2; i11 += 2) {
            int i12 = iArr[i11];
            if (i12 < i3) {
                i3 = i12;
            }
            if (i12 > i10) {
                i10 = i12;
            }
        }
        int i13 = (i3 + i10) / 2;
        int i14 = 128;
        int i15 = 0;
        for (int i16 = 0; i16 < 7; i16++) {
            i14 >>= 1;
            if (iArr[i + i16] > ((i16 & 1) == 0 ? i9 : i13)) {
                i15 |= i14;
            }
        }
        while (true) {
            int[] iArr2 = f22194b;
            if (i4 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i4] == i15) {
                return i4;
            }
            i4++;
        }
    }
}
