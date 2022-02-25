package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gd */
/* loaded from: classes2.dex */
public class C2903gd {
    /* renamed from: a */
    public static float m15640a(float[] fArr) {
        float f = 0.0f;
        for (float f2 : fArr) {
            f += f2;
        }
        return f / fArr.length;
    }

    /* renamed from: b */
    public static float m15637b(float[] fArr) {
        return (float) Math.sqrt(m15633f(fArr));
    }

    /* renamed from: c */
    public static float m15636c(float[] fArr) {
        float f = 10000.0f;
        for (float f2 : fArr) {
            f = Math.min(f, f2);
        }
        return f;
    }

    /* renamed from: d */
    public static float m15635d(float[] fArr) {
        float f = -10000.0f;
        for (float f2 : fArr) {
            f = Math.max(f, f2);
        }
        return f;
    }

    /* renamed from: a */
    public static float[] m15638a(float[] fArr, int i, int i2) {
        int length = fArr.length;
        float[] fArr2 = new float[2];
        C2900ga[] gaVarArr = new C2900ga[length];
        for (int i3 = 0; i3 < length; i3++) {
            gaVarArr[i3] = new C2900ga(fArr[i3], 0.0d);
        }
        C2900ga[] a = C2901gb.m15641a(gaVarArr);
        double a2 = a[1].m15655a();
        int i4 = 1;
        for (int i5 = 2; i5 < length / 2; i5++) {
            double a3 = a[i5].m15655a();
            if (a3 > a2) {
                i4 = i5;
                a2 = a3;
            }
        }
        fArr2[0] = ((i * 1.0f) / i2) * i4;
        fArr2[1] = (float) a2;
        return fArr2;
    }

    /* renamed from: e */
    public static float[] m15634e(float[] fArr) {
        double[] dArr = new double[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            dArr[i] = fArr[i];
        }
        double[] a = new C2902gc().m15626a(dArr, dArr.length);
        float[] fArr2 = new float[a.length / 2];
        for (int i2 = 0; i2 < a.length / 2; i2++) {
            fArr2[i2] = (float) a[i2];
        }
        return fArr2;
    }

    /* renamed from: f */
    private static float m15633f(float[] fArr) {
        return m15639a(fArr, 0);
    }

    /* renamed from: a */
    private static float m15639a(float[] fArr, int i) {
        float a = m15640a(fArr);
        float f = 0.0f;
        for (float f2 : fArr) {
            float f3 = f2 - a;
            f += f3 * f3;
        }
        if (i >= fArr.length) {
            return f / fArr.length;
        }
        return f / (fArr.length - i);
    }
}
