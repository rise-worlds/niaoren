package p110z1;

import java.util.Arrays;

/* renamed from: z1.hy */
/* loaded from: classes3.dex */
public final class BitMatrix implements Cloneable {

    /* renamed from: a */
    public final int f21920a;

    /* renamed from: b */
    public final int f21921b;

    /* renamed from: c */
    public final int f21922c;

    /* renamed from: d */
    public final int[] f21923d;

    public BitMatrix(int i) {
        this(i, i);
    }

    public BitMatrix(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f21920a = i;
        this.f21921b = i2;
        this.f21922c = (i + 31) / 32;
        this.f21923d = new int[this.f21922c * i2];
    }

    private BitMatrix(int i, int i2, int i3, int[] iArr) {
        this.f21920a = i;
        this.f21921b = i2;
        this.f21922c = i3;
        this.f21923d = iArr;
    }

    /* renamed from: a */
    public static BitMatrix m2513a(boolean[][] zArr) {
        int length = zArr.length;
        int length2 = zArr[0].length;
        BitMatrix hyVar = new BitMatrix(length2, length);
        for (int i = 0; i < length; i++) {
            boolean[] zArr2 = zArr[i];
            for (int i2 = 0; i2 < length2; i2++) {
                if (zArr2[i2]) {
                    hyVar.m2511b(i2, i);
                }
            }
        }
        return hyVar;
    }

    /* renamed from: a */
    private static BitMatrix m2515a(String str, String str2, String str3) {
        if (str != null) {
            boolean[] zArr = new boolean[str.length()];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            int i5 = 0;
            while (i < str.length()) {
                if (str.charAt(i) == '\n' || str.charAt(i) == '\r') {
                    if (i2 > i3) {
                        if (i4 == -1) {
                            i4 = i2 - i3;
                        } else if (i2 - i3 != i4) {
                            throw new IllegalArgumentException("row lengths do not match");
                        }
                        i5++;
                        i3 = i2;
                    }
                    i++;
                } else if (str.substring(i, str2.length() + i).equals(str2)) {
                    i += str2.length();
                    zArr[i2] = true;
                    i2++;
                } else if (str.substring(i, str3.length() + i).equals(str3)) {
                    i += str3.length();
                    zArr[i2] = false;
                    i2++;
                } else {
                    throw new IllegalArgumentException("illegal character encountered: " + str.substring(i));
                }
            }
            if (i2 > i3) {
                if (i4 == -1) {
                    i4 = i2 - i3;
                } else if (i2 - i3 != i4) {
                    throw new IllegalArgumentException("row lengths do not match");
                }
                i5++;
            }
            BitMatrix hyVar = new BitMatrix(i4, i5);
            for (int i6 = 0; i6 < i2; i6++) {
                if (zArr[i6]) {
                    hyVar.m2511b(i6 % i4, i6 / i4);
                }
            }
            return hyVar;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public final boolean m2519a(int i, int i2) {
        return ((this.f21923d[(i2 * this.f21922c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    /* renamed from: b */
    public final void m2511b(int i, int i2) {
        int i3 = (i2 * this.f21922c) + (i / 32);
        int[] iArr = this.f21923d;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    /* renamed from: d */
    private void m2504d(int i, int i2) {
        int i3 = (i2 * this.f21922c) + (i / 32);
        int[] iArr = this.f21923d;
        iArr[i3] = (~(1 << (i & 31))) & iArr[i3];
    }

    /* renamed from: c */
    public final void m2507c(int i, int i2) {
        int i3 = (i2 * this.f21922c) + (i / 32);
        int[] iArr = this.f21923d;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    /* renamed from: a */
    private void m2514a(BitMatrix hyVar) {
        int i = this.f21920a;
        if (i == hyVar.f21920a && this.f21921b == hyVar.f21921b && this.f21922c == hyVar.f21922c) {
            BitArray huVar = new BitArray(i);
            for (int i2 = 0; i2 < this.f21921b; i2++) {
                int i3 = this.f21922c * i2;
                int[] iArr = hyVar.m2517a(i2, huVar).f21907a;
                for (int i4 = 0; i4 < this.f21922c; i4++) {
                    int[] iArr2 = this.f21923d;
                    int i5 = i3 + i4;
                    iArr2[i5] = iArr2[i5] ^ iArr[i4];
                }
            }
            return;
        }
        throw new IllegalArgumentException("input matrix dimensions do not match");
    }

    /* renamed from: a */
    public final void m2520a() {
        int length = this.f21923d.length;
        for (int i = 0; i < length; i++) {
            this.f21923d[i] = 0;
        }
    }

    /* renamed from: a */
    public final void m2518a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i4 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i5 = i3 + i;
            int i6 = i4 + i2;
            if (i6 > this.f21921b || i5 > this.f21920a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = this.f21922c * i2;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.f21923d;
                    int i9 = (i8 / 32) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
        }
    }

    /* renamed from: f */
    private int[] m2502f() {
        int i = this.f21920a;
        int i2 = -1;
        int i3 = this.f21921b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < this.f21921b) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i5;
            int i10 = 0;
            while (true) {
                int i11 = this.f21922c;
                if (i10 < i11) {
                    int i12 = this.f21923d[(i11 * i6) + i10];
                    if (i12 != 0) {
                        if (i6 < i3) {
                            i3 = i6;
                        }
                        if (i6 > i7) {
                            i7 = i6;
                        }
                        int i13 = i10 << 5;
                        int i14 = 31;
                        if (i13 < i9) {
                            int i15 = 0;
                            while ((i12 << (31 - i15)) == 0) {
                                i15++;
                            }
                            int i16 = i15 + i13;
                            if (i16 < i9) {
                                i9 = i16;
                            }
                        }
                        if (i13 + 31 > i8) {
                            while ((i12 >>> i14) == 0) {
                                i14--;
                            }
                            int i17 = i13 + i14;
                            if (i17 > i8) {
                                i8 = i17;
                            }
                        }
                    }
                    i10++;
                }
            }
            i6++;
            i5 = i9;
            i2 = i8;
            i4 = i7;
        }
        if (i2 < i5 || i4 < i3) {
            return null;
        }
        return new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
    }

    /* renamed from: b */
    public final int[] m2512b() {
        int i = 0;
        while (true) {
            int[] iArr = this.f21923d;
            if (i >= iArr.length || iArr[i] != 0) {
                break;
            }
            i++;
        }
        int[] iArr2 = this.f21923d;
        if (i == iArr2.length) {
            return null;
        }
        int i2 = this.f21922c;
        int i3 = i / i2;
        int i4 = (i % i2) << 5;
        int i5 = iArr2[i];
        int i6 = 0;
        while ((i5 << (31 - i6)) == 0) {
            i6++;
        }
        return new int[]{i4 + i6, i3};
    }

    /* renamed from: c */
    public final int[] m2508c() {
        int length = this.f21923d.length - 1;
        while (length >= 0 && this.f21923d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = this.f21922c;
        int i2 = length / i;
        int i3 = (length % i) << 5;
        int i4 = 31;
        while ((this.f21923d[length] >>> i4) == 0) {
            i4--;
        }
        return new int[]{i3 + i4, i2};
    }

    /* renamed from: g */
    private int m2501g() {
        return this.f21920a;
    }

    /* renamed from: h */
    private int m2500h() {
        return this.f21921b;
    }

    /* renamed from: i */
    private int m2499i() {
        return this.f21922c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix hyVar = (BitMatrix) obj;
        return this.f21920a == hyVar.f21920a && this.f21921b == hyVar.f21921b && this.f21922c == hyVar.f21922c && Arrays.equals(this.f21923d, hyVar.f21923d);
    }

    public final int hashCode() {
        int i = this.f21920a;
        return (((((((i * 31) + i) * 31) + this.f21921b) * 31) + this.f21922c) * 31) + Arrays.hashCode(this.f21923d);
    }

    /* renamed from: a */
    private String m2516a(String str, String str2) {
        return m2506c(str, str2, "\n");
    }

    @Deprecated
    /* renamed from: b */
    private String m2509b(String str, String str2, String str3) {
        return m2506c(str, str2, str3);
    }

    /* renamed from: c */
    private String m2506c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.f21921b * (this.f21920a + 1));
        for (int i = 0; i < this.f21921b; i++) {
            for (int i2 = 0; i2 < this.f21920a; i2++) {
                sb.append(m2519a(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* renamed from: d */
    public final BitMatrix clone() {
        return new BitMatrix(this.f21920a, this.f21921b, this.f21922c, (int[]) this.f21923d.clone());
    }

    /* renamed from: a */
    public final BitArray m2517a(int i, BitArray huVar) {
        if (huVar == null || huVar.f21908b < this.f21920a) {
            huVar = new BitArray(this.f21920a);
        } else {
            huVar.m2546b();
        }
        int i2 = i * this.f21922c;
        for (int i3 = 0; i3 < this.f21922c; i3++) {
            huVar.f21907a[(i3 << 5) / 32] = this.f21923d[i2 + i3];
        }
        return huVar;
    }

    /* renamed from: b */
    public final void m2510b(int i, BitArray huVar) {
        int[] iArr = huVar.f21907a;
        int[] iArr2 = this.f21923d;
        int i2 = this.f21922c;
        System.arraycopy(iArr, 0, iArr2, i * i2, i2);
    }

    /* renamed from: e */
    private void m2503e() {
        int i = this.f21920a;
        int i2 = this.f21921b;
        BitArray huVar = new BitArray(i);
        BitArray huVar2 = new BitArray(i);
        for (int i3 = 0; i3 < (i2 + 1) / 2; i3++) {
            huVar = m2517a(i3, huVar);
            int i4 = (i2 - 1) - i3;
            huVar2 = m2517a(i4, huVar2);
            huVar.m2542c();
            huVar2.m2542c();
            m2510b(i3, huVar2);
            m2510b(i4, huVar);
        }
    }

    public final String toString() {
        return m2506c("X ", "  ", "\n");
    }
}
