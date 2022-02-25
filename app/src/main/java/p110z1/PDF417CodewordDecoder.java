package p110z1;

import java.lang.reflect.Array;

/* renamed from: z1.mq */
/* loaded from: classes3.dex */
final class PDF417CodewordDecoder {

    /* renamed from: a */
    private static final float[][] f22491a = (float[][]) Array.newInstance(float.class, PDF417Common.f22411h.length, 8);

    static {
        int i;
        for (int i2 = 0; i2 < PDF417Common.f22411h.length; i2++) {
            int i3 = PDF417Common.f22411h[i2];
            int i4 = i3 & 1;
            int i5 = i3;
            int i6 = 0;
            while (i6 < 8) {
                float f = 0.0f;
                while (true) {
                    i = i5 & 1;
                    if (i == i4) {
                        f += 1.0f;
                        i5 >>= 1;
                    }
                }
                f22491a[i2][(8 - i6) - 1] = f / 17.0f;
                i6++;
                i4 = i;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    /* renamed from: b */
    private static int[] m1962b(int[] iArr) {
        float a = MathUtils.m2528a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (a / 34.0f) + ((i3 * a) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    /* renamed from: d */
    private static int m1960d(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j2 = j3 | i3;
            }
            i++;
            j = j2;
        }
        return (int) j;
    }

    /* renamed from: e */
    private static int m1959e(int[] iArr) {
        int a = MathUtils.m2528a(iArr);
        float[] fArr = new float[8];
        if (a > 1) {
            for (int i = 0; i < 8; i++) {
                fArr[i] = iArr[i] / a;
            }
        }
        int i2 = -1;
        int i3 = 0;
        float f = Float.MAX_VALUE;
        while (true) {
            float[][] fArr2 = f22491a;
            if (i3 >= fArr2.length) {
                return i2;
            }
            float[] fArr3 = fArr2[i3];
            float f2 = 0.0f;
            for (int i4 = 0; i4 < 8; i4++) {
                float f3 = fArr3[i4] - fArr[i4];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i2 = PDF417Common.f22411h[i3];
                f = f2;
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1963a(int[] iArr) {
        float a = MathUtils.m2528a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (a / 34.0f) + ((i3 * a) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        long j = 0;
        int i4 = 0;
        while (i4 < 8) {
            long j2 = j;
            for (int i5 = 0; i5 < iArr2[i4]; i5++) {
                j2 = (j2 << 1) | (i4 % 2 == 0 ? 1 : 0);
            }
            i4++;
            j = j2;
        }
        int i6 = (int) j;
        int i7 = -1;
        if (PDF417Common.m2057a(i6) == -1) {
            i6 = -1;
        }
        if (i6 != -1) {
            return i6;
        }
        int a2 = MathUtils.m2528a(iArr);
        float[] fArr = new float[8];
        if (a2 > 1) {
            for (int i8 = 0; i8 < 8; i8++) {
                fArr[i8] = iArr[i8] / a2;
            }
        }
        int i9 = 0;
        float f = Float.MAX_VALUE;
        while (true) {
            float[][] fArr2 = f22491a;
            if (i9 >= fArr2.length) {
                return i7;
            }
            float[] fArr3 = fArr2[i9];
            float f2 = 0.0f;
            for (int i10 = 0; i10 < 8; i10++) {
                float f3 = fArr3[i10] - fArr[i10];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i7 = PDF417Common.f22411h[i9];
                f = f2;
            }
            i9++;
        }
    }

    /* renamed from: c */
    private static int m1961c(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j2 = j3 | i3;
            }
            i++;
            j = j2;
        }
        int i4 = (int) j;
        if (PDF417Common.m2057a(i4) == -1) {
            return -1;
        }
        return i4;
    }
}
