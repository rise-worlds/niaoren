package p110z1;

import java.util.Arrays;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.hu */
/* loaded from: classes3.dex */
public final class BitArray implements Cloneable {

    /* renamed from: a */
    public int[] f21907a;

    /* renamed from: b */
    public int f21908b;

    public BitArray() {
        this.f21908b = 0;
        this.f21907a = new int[1];
    }

    public BitArray(int i) {
        this.f21908b = i;
        this.f21907a = m2532g(i);
    }

    private BitArray(int[] iArr, int i) {
        this.f21907a = iArr;
        this.f21908b = i;
    }

    /* renamed from: d */
    private int m2539d() {
        return this.f21908b;
    }

    /* renamed from: a */
    public final int m2552a() {
        return (this.f21908b + 7) / 8;
    }

    /* renamed from: e */
    private void m2535e(int i) {
        if (i > (this.f21907a.length << 5)) {
            int[] g = m2532g(i);
            int[] iArr = this.f21907a;
            System.arraycopy(iArr, 0, g, 0, iArr.length);
            this.f21907a = g;
        }
    }

    /* renamed from: a */
    public final boolean m2551a(int i) {
        return ((1 << (i & 31)) & this.f21907a[i / 32]) != 0;
    }

    /* renamed from: b */
    public final void m2545b(int i) {
        int[] iArr = this.f21907a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    /* renamed from: f */
    private void m2533f(int i) {
        int[] iArr = this.f21907a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    /* renamed from: c */
    public final int m2541c(int i) {
        int i2 = this.f21908b;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & this.f21907a[i3];
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f21907a;
            if (i3 == iArr.length) {
                return this.f21908b;
            }
            i4 = iArr[i3];
        }
        int numberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.f21908b;
        return numberOfTrailingZeros > i5 ? i5 : numberOfTrailingZeros;
    }

    /* renamed from: d */
    public final int m2538d(int i) {
        int i2 = this.f21908b;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & (~this.f21907a[i3]);
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f21907a;
            if (i3 == iArr.length) {
                return this.f21908b;
            }
            i4 = ~iArr[i3];
        }
        int numberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.f21908b;
        return numberOfTrailingZeros > i5 ? i5 : numberOfTrailingZeros;
    }

    /* renamed from: c */
    private void m2540c(int i, int i2) {
        this.f21907a[i / 32] = i2;
    }

    /* renamed from: d */
    private void m2537d(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.f21908b) {
            throw new IllegalArgumentException();
        } else if (i2 != i) {
            int i3 = i2 - 1;
            int i4 = i / 32;
            int i5 = i3 / 32;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = 31;
                int i8 = i6 > i4 ? 0 : i & 31;
                if (i6 >= i5) {
                    i7 = 31 & i3;
                }
                int i9 = (2 << i7) - (1 << i8);
                int[] iArr = this.f21907a;
                iArr[i6] = i9 | iArr[i6];
                i6++;
            }
        }
    }

    /* renamed from: b */
    public final void m2546b() {
        int length = this.f21907a.length;
        for (int i = 0; i < length; i++) {
            this.f21907a[i] = 0;
        }
    }

    /* renamed from: a */
    public final boolean m2550a(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.f21908b) {
            throw new IllegalArgumentException();
        } else if (i2 == i) {
            return true;
        } else {
            int i3 = i2 - 1;
            int i4 = i / 32;
            int i5 = i3 / 32;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = 31;
                int i8 = i6 > i4 ? 0 : i & 31;
                if (i6 >= i5) {
                    i7 = 31 & i3;
                }
                if ((((2 << i7) - (1 << i8)) & this.f21907a[i6]) != 0) {
                    return false;
                }
                i6++;
            }
            return true;
        }
    }

    /* renamed from: a */
    public final void m2547a(boolean z) {
        m2535e(this.f21908b + 1);
        if (z) {
            int[] iArr = this.f21907a;
            int i = this.f21908b;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.f21908b++;
    }

    /* renamed from: b */
    public final void m2544b(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        m2535e(this.f21908b + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            m2547a(z);
            i2--;
        }
    }

    /* renamed from: a */
    public final void m2548a(BitArray huVar) {
        int i = huVar.f21908b;
        m2535e(this.f21908b + i);
        for (int i2 = 0; i2 < i; i2++) {
            m2547a(huVar.m2551a(i2));
        }
    }

    /* renamed from: b */
    private void m2543b(BitArray huVar) {
        if (this.f21908b == huVar.f21908b) {
            int i = 0;
            while (true) {
                int[] iArr = this.f21907a;
                if (i < iArr.length) {
                    iArr[i] = iArr[i] ^ huVar.f21907a[i];
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    /* renamed from: a */
    public final void m2549a(int i, byte[] bArr, int i2) {
        int i3 = i;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (m2551a(i3)) {
                    i5 |= 1 << (7 - i6);
                }
                i3++;
            }
            bArr[i4 + 0] = (byte) i5;
            i4++;
            i3 = i3;
        }
    }

    /* renamed from: e */
    private int[] m2536e() {
        return this.f21907a;
    }

    /* renamed from: c */
    public final void m2542c() {
        int[] iArr = new int[this.f21907a.length];
        int i = (this.f21908b - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.f21907a[i3];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i3] = (int) (((j5 & 65535) << 16) | ((j5 >> 16) & 65535));
        }
        int i4 = this.f21908b;
        int i5 = i2 << 5;
        if (i4 != i5) {
            int i6 = i5 - i4;
            int i7 = iArr[0] >>> i6;
            for (int i8 = 1; i8 < i2; i8++) {
                int i9 = iArr[i8];
                iArr[i8 - 1] = i7 | (i9 << (32 - i6));
                i7 = i9 >>> i6;
            }
            iArr[i2 - 1] = i7;
        }
        this.f21907a = iArr;
    }

    /* renamed from: g */
    private static int[] m2532g(int i) {
        return new int[(i + 31) / 32];
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray huVar = (BitArray) obj;
        return this.f21908b == huVar.f21908b && Arrays.equals(this.f21907a, huVar.f21907a);
    }

    public final int hashCode() {
        return (this.f21908b * 31) + Arrays.hashCode(this.f21907a);
    }

    public final String toString() {
        int i = this.f21908b;
        StringBuilder sb = new StringBuilder(i + (i / 8) + 1);
        for (int i2 = 0; i2 < this.f21908b; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(m2551a(i2) ? 'X' : FilenameUtils.EXTENSION_SEPARATOR);
        }
        return sb.toString();
    }

    /* renamed from: f */
    private BitArray m2534f() {
        return new BitArray((int[]) this.f21907a.clone(), this.f21908b);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new BitArray((int[]) this.f21907a.clone(), this.f21908b);
    }
}
