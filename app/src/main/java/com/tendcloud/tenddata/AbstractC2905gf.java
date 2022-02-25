package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gf */
/* loaded from: classes2.dex */
public abstract class AbstractC2905gf {

    /* renamed from: a */
    protected String f14049a = null;

    /* renamed from: b */
    protected int f14050b = 0;

    /* renamed from: c */
    protected int f14051c = 0;

    /* renamed from: d */
    protected double[] f14052d = null;

    /* renamed from: e */
    protected double[] f14053e = null;

    /* renamed from: f */
    protected double[] f14054f = null;

    /* renamed from: g */
    protected double[] f14055g = null;

    /* renamed from: a */
    public String m15627a() {
        return this.f14049a;
    }

    public String toString() {
        return m15627a();
    }

    /* renamed from: a */
    public double[] m15626a(double[] dArr, int i) {
        double[] dArr2 = new double[i];
        int length = dArr2.length >> 1;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 + length;
            dArr2[i3] = 0.0d;
            dArr2[i2] = 0.0d;
            for (int i4 = 0; i4 < this.f14050b; i4++) {
                int i5 = (i2 << 1) + i4;
                while (i5 >= dArr2.length) {
                    i5 -= dArr2.length;
                }
                dArr2[i2] = dArr2[i2] + (dArr[i5] * this.f14052d[i4]);
                dArr2[i3] = dArr2[i3] + (dArr[i5] * this.f14053e[i4]);
            }
        }
        return dArr2;
    }
}
