package com.tendcloud.tenddata;

import java.text.DecimalFormat;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fc */
/* loaded from: classes2.dex */
final class C2867fc {
    C2867fc() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m15763a(int[][] iArr) {
        if (iArr != null) {
            try {
                if (iArr.length >= 4) {
                    return m15764a(new double[][]{new double[]{iArr[0][0] * iArr[0][0] * iArr[0][0], iArr[0][0] * iArr[0][0], iArr[0][0], 1.0d, iArr[0][1]}, new double[]{iArr[1][0] * iArr[1][0] * iArr[1][0], iArr[1][0] * iArr[1][0], iArr[1][0], 1.0d, iArr[1][1]}, new double[]{iArr[2][0] * iArr[2][0] * iArr[2][0], iArr[2][0] * iArr[2][0], iArr[2][0], 1.0d, iArr[2][1]}, new double[]{iArr[3][0] * iArr[3][0] * iArr[3][0], iArr[3][0] * iArr[3][0], iArr[3][0], 1.0d, iArr[3][1]}});
                }
            } catch (Throwable unused) {
                return 0;
            }
        }
        return 0;
    }

    /* renamed from: a */
    private static int m15764a(double[][] dArr) {
        double[][] dArr2 = {new double[]{dArr[0][0], dArr[0][1], dArr[0][2], dArr[0][3]}, new double[]{dArr[1][0], dArr[1][1], dArr[1][2], dArr[1][3]}, new double[]{dArr[2][0], dArr[2][1], dArr[2][2], dArr[2][3]}, new double[]{dArr[3][0], dArr[3][1], dArr[3][2], dArr[3][3]}};
        double[][] dArr3 = {new double[]{dArr[0][0], dArr[0][1], dArr[0][2], dArr[0][4]}, new double[]{dArr[1][0], dArr[1][1], dArr[1][2], dArr[1][4]}, new double[]{dArr[2][0], dArr[2][1], dArr[2][2], dArr[2][4]}, new double[]{dArr[3][0], dArr[3][1], dArr[3][2], dArr[3][4]}};
        double b = m15762b(dArr2);
        if (b == 0.0d) {
            return 0;
        }
        return m15765a(m15762b(dArr3) / b);
    }

    /* renamed from: b */
    private static double m15762b(double[][] dArr) {
        return (Math.pow(-1.0d, 0.0d) * dArr[0][0] * m15761c(new double[][]{new double[]{dArr[1][1], dArr[1][2], dArr[1][3]}, new double[]{dArr[2][1], dArr[2][2], dArr[2][3]}, new double[]{dArr[3][1], dArr[3][2], dArr[3][3]}})) + (Math.pow(-1.0d, 1.0d) * dArr[0][1] * m15761c(new double[][]{new double[]{dArr[1][0], dArr[1][2], dArr[1][3]}, new double[]{dArr[2][0], dArr[2][2], dArr[2][3]}, new double[]{dArr[3][0], dArr[3][2], dArr[3][3]}})) + (Math.pow(-1.0d, 2.0d) * dArr[0][2] * m15761c(new double[][]{new double[]{dArr[1][0], dArr[1][1], dArr[1][3]}, new double[]{dArr[2][0], dArr[2][1], dArr[2][3]}, new double[]{dArr[3][0], dArr[3][1], dArr[3][3]}})) + (Math.pow(-1.0d, 3.0d) * dArr[0][3] * m15761c(new double[][]{new double[]{dArr[1][0], dArr[1][1], dArr[1][2]}, new double[]{dArr[2][0], dArr[2][1], dArr[2][2]}, new double[]{dArr[3][0], dArr[3][1], dArr[3][2]}}));
    }

    /* renamed from: c */
    private static double m15761c(double[][] dArr) {
        return ((((((dArr[0][0] * dArr[1][1]) * dArr[2][2]) + ((dArr[0][1] * dArr[1][2]) * dArr[2][0])) + ((dArr[0][2] * dArr[1][0]) * dArr[2][1])) + (((-dArr[0][2]) * dArr[1][1]) * dArr[2][0])) - ((dArr[0][1] * dArr[1][0]) * dArr[2][2])) - ((dArr[0][0] * dArr[2][1]) * dArr[1][2]);
    }

    /* renamed from: a */
    private static int m15765a(double d) {
        try {
            return Integer.parseInt(new DecimalFormat("######0").format(d));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return 0;
        }
    }
}
