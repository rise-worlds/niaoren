package dice.util;

/* compiled from: td */
/* loaded from: classes2.dex */
public class BiArrays {
    public static void sort(int[] iArr, Object[] objArr) {
        sort(0, iArr.length, iArr, objArr);
    }

    public static void sort(int[] iArr, double[] dArr) {
        sort(0, iArr.length, iArr, dArr);
    }

    public static void sort(int[] iArr, float[] fArr) {
        sort(0, iArr.length, iArr, fArr);
    }

    public static void sort(double[] dArr, int[] iArr) {
        sort(0, dArr.length, dArr, iArr);
    }

    public static void sort(double[] dArr, boolean[] zArr) {
        sort(0, dArr.length, dArr, zArr);
    }

    public static void sort(float[] fArr, boolean[] zArr) {
        sort(0, fArr.length, fArr, zArr);
    }

    public static void sort(double[] dArr, double[] dArr2) {
        sort(0, dArr.length, dArr, dArr2);
    }

    public static void sort(float[] fArr, int[] iArr) {
        sort(0, fArr.length, fArr, iArr);
    }

    public static void sort(int[] iArr, int[] iArr2) {
        sort(0, iArr.length, iArr, iArr2);
    }

    public static void sort(int[] iArr, short[] sArr) {
        sort(0, iArr.length, iArr, sArr);
    }

    public static void sort(double[][] dArr, int i) {
        sort(0, dArr.length, dArr, i);
    }

    private static int med3(int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[i];
        int i5 = iArr[i2];
        int i6 = iArr[i3];
        if (i4 < i5) {
            if (i5 >= i6) {
                if (i4 >= i6) {
                    return i;
                }
                return i3;
            }
            return i2;
        }
        if (i5 <= i6) {
            if (i4 <= i6) {
                return i;
            }
            return i3;
        }
        return i2;
    }

    private static void sort(int i, int i2, int[] iArr, Object[] objArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (iArr[i7] > iArr[i6]) {
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                            Object obj = objArr[i6];
                            objArr[i6] = objArr[i7];
                            objArr[i7] = obj;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(iArr, i, i + i11, i + i12);
                    i9 = med3(iArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(iArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(iArr, i3, i9, i10);
            }
            int i13 = iArr[i9];
            int i14 = i2 - 1;
            int i15 = i;
            int i16 = i15;
            int i17 = i14;
            int i18 = i17;
            while (true) {
                if (i15 > i17 || iArr[i15] > i13) {
                    while (i17 >= i15 && iArr[i17] >= i13) {
                        if (iArr[i17] == i13) {
                            int i19 = iArr[i17];
                            iArr[i17] = iArr[i18];
                            iArr[i18] = i19;
                            Object obj2 = objArr[i17];
                            objArr[i17] = objArr[i18];
                            i18--;
                            objArr[i18] = obj2;
                        }
                        i17--;
                    }
                    if (i15 > i17) {
                        break;
                    }
                    int i20 = iArr[i15];
                    iArr[i15] = iArr[i17];
                    iArr[i17] = i20;
                    Object obj3 = objArr[i15];
                    i15++;
                    objArr[i15] = objArr[i17];
                    i17--;
                    objArr[i17] = obj3;
                } else {
                    if (iArr[i15] == i13) {
                        int i21 = iArr[i16];
                        iArr[i16] = iArr[i15];
                        iArr[i15] = i21;
                        Object obj4 = objArr[i16];
                        i16++;
                        objArr[i16] = objArr[i15];
                        objArr[i15] = obj4;
                    }
                    i15++;
                }
            }
            int i22 = i16 - i;
            int i23 = i15 - i16;
            if (i22 >= i23) {
                i22 = i23;
            }
            int i24 = i15 - i22;
            int i25 = i;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                int i26 = iArr[i25];
                iArr[i25] = iArr[i24];
                iArr[i24] = i26;
                Object obj5 = objArr[i25];
                i25++;
                objArr[i25] = objArr[i24];
                i24++;
                objArr[i24] = obj5;
            }
            int i27 = i18 - i17;
            int i28 = i14 - i18;
            if (i27 < i28) {
                i28 = i27;
            }
            int i29 = i2 - i28;
            while (true) {
                i28--;
                if (i28 <= 0) {
                    break;
                }
                int i30 = iArr[i15];
                iArr[i15] = iArr[i29];
                iArr[i29] = i30;
                Object obj6 = objArr[i15];
                i15++;
                objArr[i15] = objArr[i29];
                i29++;
                objArr[i29] = obj6;
            }
            if (i23 > 0) {
                sort(i, i23 + i, iArr, objArr);
            }
            if (i27 > 0) {
                sort(i2 - i27, i2, iArr, objArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, int[] iArr, double[] dArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (iArr[i7] > iArr[i6]) {
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                            double d = dArr[i6];
                            dArr[i6] = dArr[i7];
                            dArr[i7] = d;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(iArr, i, i + i11, i + i12);
                    i9 = med3(iArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(iArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(iArr, i3, i9, i10);
            }
            int i13 = iArr[i9];
            int i14 = i2 - 1;
            int i15 = i;
            int i16 = i15;
            int i17 = i14;
            int i18 = i17;
            while (true) {
                if (i15 > i17 || iArr[i15] > i13) {
                    while (i17 >= i15 && iArr[i17] >= i13) {
                        if (iArr[i17] == i13) {
                            int i19 = iArr[i17];
                            iArr[i17] = iArr[i18];
                            iArr[i18] = i19;
                            double d2 = dArr[i17];
                            dArr[i17] = dArr[i18];
                            i18--;
                            dArr[i18] = d2;
                        }
                        i17--;
                    }
                    if (i15 > i17) {
                        break;
                    }
                    int i20 = iArr[i15];
                    iArr[i15] = iArr[i17];
                    iArr[i17] = i20;
                    double d3 = dArr[i15];
                    i15++;
                    dArr[i15] = dArr[i17];
                    i17--;
                    dArr[i17] = d3;
                } else {
                    if (iArr[i15] == i13) {
                        int i21 = iArr[i16];
                        iArr[i16] = iArr[i15];
                        iArr[i15] = i21;
                        double d4 = dArr[i16];
                        i16++;
                        dArr[i16] = dArr[i15];
                        dArr[i15] = d4;
                    }
                    i15++;
                }
            }
            int i22 = i16 - i;
            int i23 = i15 - i16;
            if (i22 >= i23) {
                i22 = i23;
            }
            int i24 = i15 - i22;
            int i25 = i;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                int i26 = iArr[i25];
                iArr[i25] = iArr[i24];
                iArr[i24] = i26;
                double d5 = dArr[i25];
                i25++;
                dArr[i25] = dArr[i24];
                i24++;
                dArr[i24] = d5;
            }
            int i27 = i18 - i17;
            int i28 = i14 - i18;
            if (i27 < i28) {
                i28 = i27;
            }
            int i29 = i2 - i28;
            while (true) {
                i28--;
                if (i28 <= 0) {
                    break;
                }
                int i30 = iArr[i15];
                iArr[i15] = iArr[i29];
                iArr[i29] = i30;
                double d6 = dArr[i15];
                i15++;
                dArr[i15] = dArr[i29];
                i29++;
                dArr[i29] = d6;
            }
            if (i23 > 0) {
                sort(i, i + i23, iArr, dArr);
            }
            if (i27 > 0) {
                sort(i2 - i27, i2, iArr, dArr);
            }
        } catch (Throwable unused) {
        }
    }

    public static void sort(float[] fArr, Object[] objArr) {
        sort(0, fArr.length, fArr, objArr);
    }

    public static void sort(float[] fArr, double[] dArr) {
        sort(0, fArr.length, fArr, dArr);
    }

    private static int med3(float[] fArr, int i, int i2, int i3) {
        float f = fArr[i];
        float f2 = fArr[i2];
        float f3 = fArr[i3];
        if (f < f2) {
            if (f2 >= f3) {
                if (f >= f3) {
                    return i;
                }
                return i3;
            }
            return i2;
        }
        if (f2 <= f3) {
            if (f <= f3) {
                return i;
            }
            return i3;
        }
        return i2;
    }

    private static void sort(int i, int i2, float[] fArr, Object[] objArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (fArr[i7] > fArr[i6]) {
                            float f = fArr[i6];
                            fArr[i6] = fArr[i7];
                            fArr[i7] = f;
                            Object obj = objArr[i6];
                            objArr[i6] = objArr[i7];
                            objArr[i7] = obj;
                        }
                    }
                }
                return;
            }
            int i8 = (i + i2) / 2;
            if (i4 > 7) {
                int i9 = i2 - 1;
                if (i4 > 40) {
                    int i10 = i4 / 8;
                    int i11 = i10 * 2;
                    i3 = med3(fArr, i, i + i10, i + i11);
                    i8 = med3(fArr, i8 - i10, i8, i8 + i10);
                    i9 = med3(fArr, i9 - i11, i9 - i10, i9);
                } else {
                    i3 = i;
                }
                i8 = med3(fArr, i3, i8, i9);
            }
            float f2 = fArr[i8];
            int i12 = i2 - 1;
            int i13 = i;
            int i14 = i13;
            int i15 = i12;
            int i16 = i15;
            while (true) {
                if (i13 > i15 || fArr[i13] > f2) {
                    while (i15 >= i13 && fArr[i15] >= f2) {
                        if (fArr[i15] == f2) {
                            float f3 = fArr[i15];
                            fArr[i15] = fArr[i16];
                            fArr[i16] = f3;
                            Object obj2 = objArr[i15];
                            objArr[i15] = objArr[i16];
                            i16--;
                            objArr[i16] = obj2;
                        }
                        i15--;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    float f4 = fArr[i13];
                    fArr[i13] = fArr[i15];
                    fArr[i15] = f4;
                    Object obj3 = objArr[i13];
                    i13++;
                    objArr[i13] = objArr[i15];
                    i15--;
                    objArr[i15] = obj3;
                } else {
                    if (fArr[i13] == f2) {
                        float f5 = fArr[i14];
                        fArr[i14] = fArr[i13];
                        fArr[i13] = f5;
                        Object obj4 = objArr[i14];
                        i14++;
                        objArr[i14] = objArr[i13];
                        objArr[i13] = obj4;
                    }
                    i13++;
                }
            }
            int i17 = i14 - i;
            int i18 = i13 - i14;
            if (i17 >= i18) {
                i17 = i18;
            }
            int i19 = i13 - i17;
            int i20 = i;
            while (true) {
                i17--;
                if (i17 <= 0) {
                    break;
                }
                float f6 = fArr[i20];
                fArr[i20] = fArr[i19];
                fArr[i19] = f6;
                Object obj5 = objArr[i20];
                i20++;
                objArr[i20] = objArr[i19];
                i19++;
                objArr[i19] = obj5;
            }
            int i21 = i16 - i15;
            int i22 = i12 - i16;
            if (i21 < i22) {
                i22 = i21;
            }
            int i23 = i2 - i22;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                float f7 = fArr[i13];
                fArr[i13] = fArr[i23];
                fArr[i23] = f7;
                Object obj6 = objArr[i13];
                i13++;
                objArr[i13] = objArr[i23];
                i23++;
                objArr[i23] = obj6;
            }
            if (i18 > 0) {
                sort(i, i18 + i, fArr, objArr);
            }
            if (i21 > 0) {
                sort(i2 - i21, i2, fArr, objArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, float[] fArr, boolean[] zArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (fArr[i7] > fArr[i6]) {
                            float f = fArr[i6];
                            fArr[i6] = fArr[i7];
                            fArr[i7] = f;
                            boolean z = zArr[i6];
                            zArr[i6] = zArr[i7];
                            zArr[i7] = z;
                        }
                    }
                }
                return;
            }
            int i8 = (i + i2) / 2;
            if (i4 > 7) {
                int i9 = i2 - 1;
                if (i4 > 40) {
                    int i10 = i4 / 8;
                    int i11 = i10 * 2;
                    i3 = med3(fArr, i, i + i10, i + i11);
                    i8 = med3(fArr, i8 - i10, i8, i8 + i10);
                    i9 = med3(fArr, i9 - i11, i9 - i10, i9);
                } else {
                    i3 = i;
                }
                i8 = med3(fArr, i3, i8, i9);
            }
            float f2 = fArr[i8];
            int i12 = i2 - 1;
            int i13 = i;
            int i14 = i13;
            int i15 = i12;
            int i16 = i15;
            while (true) {
                if (i13 > i15 || fArr[i13] > f2) {
                    while (i15 >= i13 && fArr[i15] >= f2) {
                        if (fArr[i15] == f2) {
                            float f3 = fArr[i15];
                            fArr[i15] = fArr[i16];
                            fArr[i16] = f3;
                            boolean z2 = zArr[i15];
                            zArr[i15] = zArr[i16];
                            i16--;
                            zArr[i16] = z2;
                        }
                        i15--;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    float f4 = fArr[i13];
                    fArr[i13] = fArr[i15];
                    fArr[i15] = f4;
                    boolean z3 = zArr[i13];
                    i13++;
                    zArr[i13] = zArr[i15];
                    i15--;
                    zArr[i15] = z3;
                } else {
                    if (fArr[i13] == f2) {
                        float f5 = fArr[i14];
                        fArr[i14] = fArr[i13];
                        fArr[i13] = f5;
                        boolean z4 = zArr[i14];
                        i14++;
                        zArr[i14] = zArr[i13];
                        zArr[i13] = z4;
                    }
                    i13++;
                }
            }
            int i17 = i14 - i;
            int i18 = i13 - i14;
            if (i17 >= i18) {
                i17 = i18;
            }
            int i19 = i13 - i17;
            int i20 = i;
            while (true) {
                i17--;
                if (i17 <= 0) {
                    break;
                }
                float f6 = fArr[i20];
                fArr[i20] = fArr[i19];
                fArr[i19] = f6;
                boolean z5 = zArr[i20];
                i20++;
                zArr[i20] = zArr[i19];
                i19++;
                zArr[i19] = z5;
            }
            int i21 = i16 - i15;
            int i22 = i12 - i16;
            if (i21 < i22) {
                i22 = i21;
            }
            int i23 = i2 - i22;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                float f7 = fArr[i13];
                fArr[i13] = fArr[i23];
                fArr[i23] = f7;
                boolean z6 = zArr[i13];
                i13++;
                zArr[i13] = zArr[i23];
                i23++;
                zArr[i23] = z6;
            }
            if (i18 > 0) {
                sort(i, i18 + i, fArr, zArr);
            }
            if (i21 > 0) {
                sort(i2 - i21, i2, fArr, zArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, float[] fArr, double[] dArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (fArr[i7] > fArr[i6]) {
                            float f = fArr[i6];
                            fArr[i6] = fArr[i7];
                            fArr[i7] = f;
                            double d = dArr[i6];
                            dArr[i6] = dArr[i7];
                            dArr[i7] = d;
                        }
                    }
                }
                return;
            }
            int i8 = (i + i2) / 2;
            if (i4 > 7) {
                int i9 = i2 - 1;
                if (i4 > 40) {
                    int i10 = i4 / 8;
                    int i11 = i10 * 2;
                    i3 = med3(fArr, i, i + i10, i + i11);
                    i8 = med3(fArr, i8 - i10, i8, i8 + i10);
                    i9 = med3(fArr, i9 - i11, i9 - i10, i9);
                } else {
                    i3 = i;
                }
                i8 = med3(fArr, i3, i8, i9);
            }
            float f2 = fArr[i8];
            int i12 = i2 - 1;
            int i13 = i;
            int i14 = i13;
            int i15 = i12;
            int i16 = i15;
            while (true) {
                if (i13 > i15 || fArr[i13] > f2) {
                    while (i15 >= i13 && fArr[i15] >= f2) {
                        if (fArr[i15] == f2) {
                            float f3 = fArr[i15];
                            fArr[i15] = fArr[i16];
                            fArr[i16] = f3;
                            double d2 = dArr[i15];
                            dArr[i15] = dArr[i16];
                            i16--;
                            dArr[i16] = d2;
                        }
                        i15--;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    float f4 = fArr[i13];
                    fArr[i13] = fArr[i15];
                    fArr[i15] = f4;
                    double d3 = dArr[i13];
                    i13++;
                    dArr[i13] = dArr[i15];
                    i15--;
                    dArr[i15] = d3;
                } else {
                    if (fArr[i13] == f2) {
                        float f5 = fArr[i14];
                        fArr[i14] = fArr[i13];
                        fArr[i13] = f5;
                        double d4 = dArr[i14];
                        i14++;
                        dArr[i14] = dArr[i13];
                        dArr[i13] = d4;
                    }
                    i13++;
                }
            }
            int i17 = i14 - i;
            int i18 = i13 - i14;
            if (i17 >= i18) {
                i17 = i18;
            }
            int i19 = i13 - i17;
            int i20 = i;
            while (true) {
                i17--;
                if (i17 <= 0) {
                    break;
                }
                float f6 = fArr[i20];
                fArr[i20] = fArr[i19];
                fArr[i19] = f6;
                double d5 = dArr[i20];
                i20++;
                dArr[i20] = dArr[i19];
                i19++;
                dArr[i19] = d5;
            }
            int i21 = i16 - i15;
            int i22 = i12 - i16;
            if (i21 < i22) {
                i22 = i21;
            }
            int i23 = i2 - i22;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                float f7 = fArr[i13];
                fArr[i13] = fArr[i23];
                fArr[i23] = f7;
                double d6 = dArr[i13];
                i13++;
                dArr[i13] = dArr[i23];
                i23++;
                dArr[i23] = d6;
            }
            if (i18 > 0) {
                sort(i, i + i18, fArr, dArr);
            }
            if (i21 > 0) {
                sort(i2 - i21, i2, fArr, dArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static int med3(double[] dArr, int i, int i2, int i3) {
        double d = dArr[i];
        double d2 = dArr[i2];
        double d3 = dArr[i3];
        if (d < d2) {
            if (d2 >= d3) {
                if (d >= d3) {
                    return i;
                }
                return i3;
            }
            return i2;
        }
        if (d2 <= d3) {
            if (d <= d3) {
                return i;
            }
            return i3;
        }
        return i2;
    }

    private static void sort(int i, int i2, double[] dArr, int[] iArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (dArr[i7] > dArr[i6]) {
                            double d = dArr[i6];
                            dArr[i6] = dArr[i7];
                            dArr[i7] = d;
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(dArr, i, i + i11, i + i12);
                    i9 = med3(dArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(dArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(dArr, i3, i9, i10);
            }
            double d2 = dArr[i9];
            int i13 = i2 - 1;
            int i14 = i;
            int i15 = i14;
            int i16 = i13;
            int i17 = i16;
            while (true) {
                if (i14 > i16 || dArr[i14] > d2) {
                    while (i16 >= i14 && dArr[i16] >= d2) {
                        if (dArr[i16] == d2) {
                            double d3 = dArr[i16];
                            dArr[i16] = dArr[i17];
                            dArr[i17] = d3;
                            int i18 = iArr[i16];
                            iArr[i16] = iArr[i17];
                            i17--;
                            iArr[i17] = i18;
                        }
                        i16--;
                    }
                    if (i14 > i16) {
                        break;
                    }
                    double d4 = dArr[i14];
                    dArr[i14] = dArr[i16];
                    dArr[i16] = d4;
                    int i19 = iArr[i14];
                    i14++;
                    iArr[i14] = iArr[i16];
                    i16--;
                    iArr[i16] = i19;
                } else {
                    if (dArr[i14] == d2) {
                        double d5 = dArr[i15];
                        dArr[i15] = dArr[i14];
                        dArr[i14] = d5;
                        int i20 = iArr[i15];
                        i15++;
                        iArr[i15] = iArr[i14];
                        iArr[i14] = i20;
                    }
                    i14++;
                }
            }
            int i21 = i15 - i;
            int i22 = i14 - i15;
            if (i21 >= i22) {
                i21 = i22;
            }
            int i23 = i14 - i21;
            int i24 = i;
            while (true) {
                i21--;
                if (i21 <= 0) {
                    break;
                }
                double d6 = dArr[i24];
                dArr[i24] = dArr[i23];
                dArr[i23] = d6;
                int i25 = iArr[i24];
                i24++;
                iArr[i24] = iArr[i23];
                i23++;
                iArr[i23] = i25;
            }
            int i26 = i17 - i16;
            int i27 = i13 - i17;
            if (i26 < i27) {
                i27 = i26;
            }
            int i28 = i2 - i27;
            while (true) {
                i27--;
                if (i27 <= 0) {
                    break;
                }
                double d7 = dArr[i14];
                dArr[i14] = dArr[i28];
                dArr[i28] = d7;
                int i29 = iArr[i14];
                i14++;
                iArr[i14] = iArr[i28];
                i28++;
                iArr[i28] = i29;
            }
            if (i22 > 0) {
                sort(i, i22 + i, dArr, iArr);
            }
            if (i26 > 0) {
                sort(i2 - i26, i2, dArr, iArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, double[] dArr, boolean[] zArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (dArr[i7] > dArr[i6]) {
                            double d = dArr[i6];
                            dArr[i6] = dArr[i7];
                            dArr[i7] = d;
                            boolean z = zArr[i6];
                            zArr[i6] = zArr[i7];
                            zArr[i7] = z;
                        }
                    }
                }
                return;
            }
            int i8 = (i + i2) / 2;
            if (i4 > 7) {
                int i9 = i2 - 1;
                if (i4 > 40) {
                    int i10 = i4 / 8;
                    int i11 = i10 * 2;
                    i3 = med3(dArr, i, i + i10, i + i11);
                    i8 = med3(dArr, i8 - i10, i8, i8 + i10);
                    i9 = med3(dArr, i9 - i11, i9 - i10, i9);
                } else {
                    i3 = i;
                }
                i8 = med3(dArr, i3, i8, i9);
            }
            double d2 = dArr[i8];
            int i12 = i2 - 1;
            int i13 = i;
            int i14 = i13;
            int i15 = i12;
            int i16 = i15;
            while (true) {
                if (i13 > i15 || dArr[i13] > d2) {
                    while (i15 >= i13 && dArr[i15] >= d2) {
                        if (dArr[i15] == d2) {
                            double d3 = dArr[i15];
                            dArr[i15] = dArr[i16];
                            dArr[i16] = d3;
                            boolean z2 = zArr[i15];
                            zArr[i15] = zArr[i16];
                            i16--;
                            zArr[i16] = z2;
                        }
                        i15--;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    double d4 = dArr[i13];
                    dArr[i13] = dArr[i15];
                    dArr[i15] = d4;
                    boolean z3 = zArr[i13];
                    i13++;
                    zArr[i13] = zArr[i15];
                    i15--;
                    zArr[i15] = z3;
                } else {
                    if (dArr[i13] == d2) {
                        double d5 = dArr[i14];
                        dArr[i14] = dArr[i13];
                        dArr[i13] = d5;
                        boolean z4 = zArr[i14];
                        i14++;
                        zArr[i14] = zArr[i13];
                        zArr[i13] = z4;
                    }
                    i13++;
                }
            }
            int i17 = i14 - i;
            int i18 = i13 - i14;
            if (i17 >= i18) {
                i17 = i18;
            }
            int i19 = i13 - i17;
            int i20 = i;
            while (true) {
                i17--;
                if (i17 <= 0) {
                    break;
                }
                double d6 = dArr[i20];
                dArr[i20] = dArr[i19];
                dArr[i19] = d6;
                boolean z5 = zArr[i20];
                i20++;
                zArr[i20] = zArr[i19];
                i19++;
                zArr[i19] = z5;
            }
            int i21 = i16 - i15;
            int i22 = i12 - i16;
            if (i21 < i22) {
                i22 = i21;
            }
            int i23 = i2 - i22;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                double d7 = dArr[i13];
                dArr[i13] = dArr[i23];
                dArr[i23] = d7;
                boolean z6 = zArr[i13];
                i13++;
                zArr[i13] = zArr[i23];
                i23++;
                zArr[i23] = z6;
            }
            if (i18 > 0) {
                sort(i, i18 + i, dArr, zArr);
            }
            if (i21 > 0) {
                sort(i2 - i21, i2, dArr, zArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, int[] iArr, int[] iArr2) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (iArr[i7] > iArr[i6]) {
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                            int i9 = iArr2[i6];
                            iArr2[i6] = iArr2[i7];
                            iArr2[i7] = i9;
                        }
                    }
                }
                return;
            }
            int i10 = (i + i2) / 2;
            if (i4 > 7) {
                int i11 = i2 - 1;
                if (i4 > 40) {
                    int i12 = i4 / 8;
                    int i13 = i12 * 2;
                    i3 = med3(iArr, i, i + i12, i + i13);
                    i10 = med3(iArr, i10 - i12, i10, i10 + i12);
                    i11 = med3(iArr, i11 - i13, i11 - i12, i11);
                } else {
                    i3 = i;
                }
                i10 = med3(iArr, i3, i10, i11);
            }
            double d = iArr[i10];
            int i14 = i2 - 1;
            int i15 = i;
            int i16 = i15;
            int i17 = i14;
            int i18 = i17;
            while (true) {
                if (i15 > i17 || iArr[i15] > d) {
                    while (i17 >= i15 && iArr[i17] >= d) {
                        if (iArr[i17] == d) {
                            int i19 = iArr[i17];
                            iArr[i17] = iArr[i18];
                            iArr[i18] = i19;
                            int i20 = iArr2[i17];
                            iArr2[i17] = iArr2[i18];
                            i18--;
                            iArr2[i18] = i20;
                        }
                        i17--;
                    }
                    if (i15 > i17) {
                        break;
                    }
                    int i21 = iArr[i15];
                    iArr[i15] = iArr[i17];
                    iArr[i17] = i21;
                    int i22 = iArr2[i15];
                    i15++;
                    iArr2[i15] = iArr2[i17];
                    i17--;
                    iArr2[i17] = i22;
                } else {
                    if (iArr[i15] == d) {
                        int i23 = iArr[i16];
                        iArr[i16] = iArr[i15];
                        iArr[i15] = i23;
                        int i24 = iArr2[i16];
                        i16++;
                        iArr2[i16] = iArr2[i15];
                        iArr2[i15] = i24;
                    }
                    i15++;
                }
            }
            int i25 = i16 - i;
            int i26 = i15 - i16;
            if (i25 >= i26) {
                i25 = i26;
            }
            int i27 = i15 - i25;
            int i28 = i;
            while (true) {
                i25--;
                if (i25 <= 0) {
                    break;
                }
                int i29 = iArr[i28];
                iArr[i28] = iArr[i27];
                iArr[i27] = i29;
                int i30 = iArr2[i28];
                i28++;
                iArr2[i28] = iArr2[i27];
                i27++;
                iArr2[i27] = i30;
            }
            int i31 = i18 - i17;
            int i32 = i14 - i18;
            if (i31 < i32) {
                i32 = i31;
            }
            int i33 = i2 - i32;
            while (true) {
                i32--;
                if (i32 <= 0) {
                    break;
                }
                int i34 = iArr[i15];
                iArr[i15] = iArr[i33];
                iArr[i33] = i34;
                int i35 = iArr2[i15];
                i15++;
                iArr2[i15] = iArr2[i33];
                i33++;
                iArr2[i33] = i35;
            }
            if (i26 > 0) {
                sort(i, i26 + i, iArr, iArr2);
            }
            if (i31 > 0) {
                sort(i2 - i31, i2, iArr, iArr2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void sort(int i, int i2, int[] iArr, short[] sArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (iArr[i7] > iArr[i6]) {
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                            short s = sArr[i6];
                            sArr[i6] = sArr[i7];
                            sArr[i7] = s;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(iArr, i, i + i11, i + i12);
                    i9 = med3(iArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(iArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(iArr, i3, i9, i10);
            }
            double d = iArr[i9];
            int i13 = i2 - 1;
            int i14 = i;
            int i15 = i14;
            int i16 = i13;
            int i17 = i16;
            while (true) {
                if (i14 > i16 || iArr[i14] > d) {
                    while (i16 >= i14 && iArr[i16] >= d) {
                        if (iArr[i16] == d) {
                            int i18 = iArr[i16];
                            iArr[i16] = iArr[i17];
                            iArr[i17] = i18;
                            short s2 = sArr[i16];
                            sArr[i16] = sArr[i17];
                            i17--;
                            sArr[i17] = s2;
                        }
                        i16--;
                    }
                    if (i14 > i16) {
                        break;
                    }
                    int i19 = iArr[i14];
                    iArr[i14] = iArr[i16];
                    iArr[i16] = i19;
                    short s3 = sArr[i14];
                    i14++;
                    sArr[i14] = sArr[i16];
                    i16--;
                    sArr[i16] = s3;
                } else {
                    if (iArr[i14] == d) {
                        int i20 = iArr[i15];
                        iArr[i15] = iArr[i14];
                        iArr[i14] = i20;
                        short s4 = sArr[i15];
                        i15++;
                        sArr[i15] = sArr[i14];
                        sArr[i14] = s4;
                    }
                    i14++;
                }
            }
            int i21 = i15 - i;
            int i22 = i14 - i15;
            if (i21 >= i22) {
                i21 = i22;
            }
            int i23 = i14 - i21;
            int i24 = i;
            while (true) {
                i21--;
                if (i21 <= 0) {
                    break;
                }
                int i25 = iArr[i24];
                iArr[i24] = iArr[i23];
                iArr[i23] = i25;
                short s5 = sArr[i24];
                i24++;
                sArr[i24] = sArr[i23];
                i23++;
                sArr[i23] = s5;
            }
            int i26 = i17 - i16;
            int i27 = i13 - i17;
            if (i26 < i27) {
                i27 = i26;
            }
            int i28 = i2 - i27;
            while (true) {
                i27--;
                if (i27 <= 0) {
                    break;
                }
                int i29 = iArr[i14];
                iArr[i14] = iArr[i28];
                iArr[i28] = i29;
                short s6 = sArr[i14];
                i14++;
                sArr[i14] = sArr[i28];
                i28++;
                sArr[i28] = s6;
            }
            if (i22 > 0) {
                sort(i, i22 + i, iArr, sArr);
            }
            if (i26 > 0) {
                sort(i2 - i26, i2, iArr, sArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, float[] fArr, int[] iArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (fArr[i7] > fArr[i6]) {
                            float f = fArr[i6];
                            fArr[i6] = fArr[i7];
                            fArr[i7] = f;
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(fArr, i, i + i11, i + i12);
                    i9 = med3(fArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(fArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(fArr, i3, i9, i10);
            }
            double d = fArr[i9];
            int i13 = i2 - 1;
            int i14 = i;
            int i15 = i14;
            int i16 = i13;
            int i17 = i16;
            while (true) {
                if (i14 > i16 || fArr[i14] > d) {
                    while (i16 >= i14 && fArr[i16] >= d) {
                        if (fArr[i16] == d) {
                            float f2 = fArr[i16];
                            fArr[i16] = fArr[i17];
                            fArr[i17] = f2;
                            int i18 = iArr[i16];
                            iArr[i16] = iArr[i17];
                            i17--;
                            iArr[i17] = i18;
                        }
                        i16--;
                    }
                    if (i14 > i16) {
                        break;
                    }
                    float f3 = fArr[i14];
                    fArr[i14] = fArr[i16];
                    fArr[i16] = f3;
                    int i19 = iArr[i14];
                    i14++;
                    iArr[i14] = iArr[i16];
                    i16--;
                    iArr[i16] = i19;
                } else {
                    if (fArr[i14] == d) {
                        float f4 = fArr[i15];
                        fArr[i15] = fArr[i14];
                        fArr[i14] = f4;
                        int i20 = iArr[i15];
                        i15++;
                        iArr[i15] = iArr[i14];
                        iArr[i14] = i20;
                    }
                    i14++;
                }
            }
            int i21 = i15 - i;
            int i22 = i14 - i15;
            if (i21 >= i22) {
                i21 = i22;
            }
            int i23 = i14 - i21;
            int i24 = i;
            while (true) {
                i21--;
                if (i21 <= 0) {
                    break;
                }
                float f5 = fArr[i24];
                fArr[i24] = fArr[i23];
                fArr[i23] = f5;
                int i25 = iArr[i24];
                i24++;
                iArr[i24] = iArr[i23];
                i23++;
                iArr[i23] = i25;
            }
            int i26 = i17 - i16;
            int i27 = i13 - i17;
            if (i26 < i27) {
                i27 = i26;
            }
            int i28 = i2 - i27;
            while (true) {
                i27--;
                if (i27 <= 0) {
                    break;
                }
                float f6 = fArr[i14];
                fArr[i14] = fArr[i28];
                fArr[i28] = f6;
                int i29 = iArr[i14];
                i14++;
                iArr[i14] = iArr[i28];
                i28++;
                iArr[i28] = i29;
            }
            if (i22 > 0) {
                sort(i, i22 + i, fArr, iArr);
            }
            if (i26 > 0) {
                sort(i2 - i26, i2, fArr, iArr);
            }
        } catch (Throwable unused) {
        }
    }

    public static void sort(int i, int i2, int[] iArr, float[] fArr) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (iArr[i7] > iArr[i6]) {
                            int i8 = iArr[i6];
                            iArr[i6] = iArr[i7];
                            iArr[i7] = i8;
                            float f = fArr[i6];
                            fArr[i6] = fArr[i7];
                            fArr[i7] = f;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i4 > 7) {
                int i10 = i2 - 1;
                if (i4 > 40) {
                    int i11 = i4 / 8;
                    int i12 = i11 * 2;
                    i3 = med3(iArr, i, i + i11, i + i12);
                    i9 = med3(iArr, i9 - i11, i9, i9 + i11);
                    i10 = med3(iArr, i10 - i12, i10 - i11, i10);
                } else {
                    i3 = i;
                }
                i9 = med3(iArr, i3, i9, i10);
            }
            int i13 = iArr[i9];
            int i14 = i2 - 1;
            int i15 = i;
            int i16 = i15;
            int i17 = i14;
            int i18 = i17;
            while (true) {
                if (i15 > i17 || iArr[i15] > i13) {
                    while (i17 >= i15 && iArr[i17] >= i13) {
                        if (iArr[i17] == i13) {
                            int i19 = iArr[i17];
                            iArr[i17] = iArr[i18];
                            iArr[i18] = i19;
                            float f2 = fArr[i17];
                            fArr[i17] = fArr[i18];
                            i18--;
                            fArr[i18] = f2;
                        }
                        i17--;
                    }
                    if (i15 > i17) {
                        break;
                    }
                    int i20 = iArr[i15];
                    iArr[i15] = iArr[i17];
                    iArr[i17] = i20;
                    float f3 = fArr[i15];
                    i15++;
                    fArr[i15] = fArr[i17];
                    i17--;
                    fArr[i17] = f3;
                } else {
                    if (iArr[i15] == i13) {
                        int i21 = iArr[i16];
                        iArr[i16] = iArr[i15];
                        iArr[i15] = i21;
                        float f4 = fArr[i16];
                        i16++;
                        fArr[i16] = fArr[i15];
                        fArr[i15] = f4;
                    }
                    i15++;
                }
            }
            int i22 = i16 - i;
            int i23 = i15 - i16;
            if (i22 >= i23) {
                i22 = i23;
            }
            int i24 = i15 - i22;
            int i25 = i;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                int i26 = iArr[i25];
                iArr[i25] = iArr[i24];
                iArr[i24] = i26;
                float f5 = fArr[i25];
                i25++;
                fArr[i25] = fArr[i24];
                i24++;
                fArr[i24] = f5;
            }
            int i27 = i18 - i17;
            int i28 = i14 - i18;
            if (i27 < i28) {
                i28 = i27;
            }
            int i29 = i2 - i28;
            while (true) {
                i28--;
                if (i28 <= 0) {
                    break;
                }
                int i30 = iArr[i15];
                iArr[i15] = iArr[i29];
                iArr[i29] = i30;
                float f6 = fArr[i15];
                i15++;
                fArr[i15] = fArr[i29];
                i29++;
                fArr[i29] = f6;
            }
            if (i23 > 0) {
                sort(i, i23 + i, iArr, fArr);
            }
            if (i27 > 0) {
                sort(i2 - i27, i2, iArr, fArr);
            }
        } catch (Throwable unused) {
        }
    }

    private static void sort(int i, int i2, double[] dArr, double[] dArr2) {
        int i3;
        int i4 = i2 - i;
        try {
            if (i4 < 7) {
                for (int i5 = i + 1; i5 < i2; i5++) {
                    for (int i6 = i5; i6 > i; i6--) {
                        int i7 = i6 - 1;
                        if (dArr[i7] > dArr[i6]) {
                            double d = dArr[i6];
                            dArr[i6] = dArr[i7];
                            dArr[i7] = d;
                            double d2 = dArr2[i6];
                            dArr2[i6] = dArr2[i7];
                            dArr2[i7] = d2;
                        }
                    }
                }
                return;
            }
            int i8 = (i + i2) / 2;
            if (i4 > 7) {
                int i9 = i2 - 1;
                if (i4 > 40) {
                    int i10 = i4 / 8;
                    int i11 = i10 * 2;
                    i3 = med3(dArr, i, i + i10, i + i11);
                    i8 = med3(dArr, i8 - i10, i8, i8 + i10);
                    i9 = med3(dArr, i9 - i11, i9 - i10, i9);
                } else {
                    i3 = i;
                }
                i8 = med3(dArr, i3, i8, i9);
            }
            double d3 = dArr[i8];
            int i12 = i2 - 1;
            int i13 = i;
            int i14 = i13;
            int i15 = i12;
            int i16 = i15;
            while (true) {
                if (i13 > i15 || dArr[i13] > d3) {
                    while (i15 >= i13 && dArr[i15] >= d3) {
                        if (dArr[i15] == d3) {
                            double d4 = dArr[i15];
                            dArr[i15] = dArr[i16];
                            dArr[i16] = d4;
                            double d5 = dArr2[i15];
                            dArr2[i15] = dArr2[i16];
                            i16--;
                            dArr2[i16] = d5;
                        }
                        i15--;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    double d6 = dArr[i13];
                    dArr[i13] = dArr[i15];
                    dArr[i15] = d6;
                    double d7 = dArr2[i13];
                    i13++;
                    dArr2[i13] = dArr2[i15];
                    i15--;
                    dArr2[i15] = d7;
                } else {
                    if (dArr[i13] == d3) {
                        double d8 = dArr[i14];
                        dArr[i14] = dArr[i13];
                        dArr[i13] = d8;
                        double d9 = dArr2[i14];
                        i14++;
                        dArr2[i14] = dArr2[i13];
                        dArr2[i13] = d9;
                    }
                    i13++;
                }
            }
            int i17 = i14 - i;
            int i18 = i13 - i14;
            if (i17 >= i18) {
                i17 = i18;
            }
            int i19 = i13 - i17;
            int i20 = i;
            while (true) {
                i17--;
                if (i17 <= 0) {
                    break;
                }
                double d10 = dArr[i20];
                dArr[i20] = dArr[i19];
                dArr[i19] = d10;
                double d11 = dArr2[i20];
                i20++;
                dArr2[i20] = dArr2[i19];
                i19++;
                dArr2[i19] = d11;
            }
            int i21 = i16 - i15;
            int i22 = i12 - i16;
            if (i21 < i22) {
                i22 = i21;
            }
            int i23 = i2 - i22;
            while (true) {
                i22--;
                if (i22 <= 0) {
                    break;
                }
                double d12 = dArr[i13];
                dArr[i13] = dArr[i23];
                dArr[i23] = d12;
                double d13 = dArr2[i13];
                i13++;
                dArr2[i13] = dArr2[i23];
                i23++;
                dArr2[i23] = d13;
            }
            if (i18 > 0) {
                sort(i, i18 + i, dArr, dArr2);
            }
            if (i21 > 0) {
                sort(i2 - i21, i2, dArr, dArr2);
            }
        } catch (Throwable unused) {
        }
    }

    private static int med3(double[][] dArr, int i, int i2, int i3, int i4) {
        double d = dArr[i][i4];
        double d2 = dArr[i2][i4];
        double d3 = dArr[i3][i4];
        if (d < d2) {
            if (d2 >= d3) {
                if (d >= d3) {
                    return i;
                }
                return i3;
            }
            return i2;
        }
        if (d2 <= d3) {
            if (d <= d3) {
                return i;
            }
            return i3;
        }
        return i2;
    }

    private static void sort(int i, int i2, double[][] dArr, int i3) {
        int i4;
        int i5 = i2 - i;
        try {
            if (i5 < 7) {
                for (int i6 = i + 1; i6 < i2; i6++) {
                    for (int i7 = i6; i7 > i; i7--) {
                        int i8 = i7 - 1;
                        if (dArr[i8][i3] > dArr[i7][i3]) {
                            double[] dArr2 = dArr[i7];
                            dArr[i7] = dArr[i8];
                            dArr[i8] = dArr2;
                        }
                    }
                }
                return;
            }
            int i9 = (i + i2) / 2;
            if (i5 > 7) {
                int i10 = i2 - 1;
                if (i5 > 40) {
                    int i11 = i5 / 8;
                    int i12 = i11 * 2;
                    i4 = med3(dArr, i, i + i11, i + i12, i3);
                    i9 = med3(dArr, i9 - i11, i9, i9 + i11, i3);
                    i10 = med3(dArr, i10 - i12, i10 - i11, i10, i3);
                } else {
                    i4 = i;
                }
                i9 = med3(dArr, i4, i9, i10, i3);
            }
            double d = dArr[i9][i3];
            int i13 = i2 - 1;
            int i14 = i;
            int i15 = i13;
            while (true) {
                if (i14 > i15 || dArr[i14][i3] > d) {
                    while (i15 >= i14 && dArr[i15][i3] >= d) {
                        if (dArr[i15][i3] == d) {
                            double[] dArr3 = dArr[i15];
                            dArr[i15] = dArr[i13];
                            dArr[i13] = dArr3;
                        }
                        i15--;
                    }
                    if (i14 > i15) {
                        break;
                    }
                    double[] dArr4 = dArr[i14];
                    dArr[i14] = dArr[i15];
                    dArr[i15] = dArr4;
                } else {
                    if (dArr[i14][i3] == d) {
                        double[] dArr5 = dArr[i];
                        dArr[i] = dArr[i14];
                        dArr[i14] = dArr5;
                    }
                    i14++;
                }
            }
            int i16 = i - i;
            int i17 = i14 - i;
            if (i16 >= i17) {
                i16 = i17;
            }
            int i18 = i14 - i16;
            while (true) {
                i16--;
                if (i16 <= 0) {
                    break;
                }
                double[] dArr6 = dArr[i];
                dArr[i] = dArr[i18];
                dArr[i18] = dArr6;
            }
            int i19 = i13 - i15;
            int i20 = i13 - i13;
            if (i19 < i20) {
                i20 = i19;
            }
            int i21 = i2 - i20;
            while (true) {
                i20--;
                if (i20 <= 0) {
                    break;
                }
                double[] dArr7 = dArr[i14];
                dArr[i14] = dArr[i21];
                dArr[i21] = dArr7;
            }
            if (i17 > 0) {
                sort(i, i17 + i, dArr, i3);
            }
            if (i19 > 0) {
                sort(i2 - i19, i2, dArr, i3);
            }
        } catch (Throwable unused) {
        }
    }
}
