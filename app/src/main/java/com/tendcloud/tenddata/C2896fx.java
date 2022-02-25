package com.tendcloud.tenddata;

import java.lang.reflect.Array;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fx */
/* loaded from: classes2.dex */
public class C2896fx {

    /* renamed from: b */
    private C2898fz f14013b = new C2898fz();

    /* renamed from: a */
    private int f14012a = 0;

    /* renamed from: a */
    public void m15661a(C2897fy[] fyVarArr, int i, int i2) {
        try {
            this.f14012a = fyVarArr.length;
            float[][] fArr = (float[][]) Array.newInstance(float.class, 3, this.f14012a);
            for (int i3 = 0; i3 < this.f14012a; i3++) {
                fArr[0][i3] = fyVarArr[i3].f14014a[0];
                fArr[1][i3] = fyVarArr[i3].f14014a[1];
                fArr[2][i3] = fyVarArr[i3].f14014a[2];
            }
            m15659a(fArr, i, i2);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m15659a(float[][] fArr, int i, int i2) {
        try {
            this.f14013b.f14025e = C2903gd.m15635d(fArr[0]);
            this.f14013b.f14026f = C2903gd.m15635d(fArr[1]);
            this.f14013b.f14027g = C2903gd.m15635d(fArr[2]);
            this.f14013b.f14022b = C2903gd.m15636c(fArr[0]);
            this.f14013b.f14023c = C2903gd.m15636c(fArr[1]);
            this.f14013b.f14024d = C2903gd.m15636c(fArr[2]);
            this.f14013b.f14028h = C2903gd.m15640a(fArr[0]);
            this.f14013b.f14029i = C2903gd.m15640a(fArr[1]);
            this.f14013b.f14030j = C2903gd.m15640a(fArr[2]);
            this.f14013b.f14031k = C2903gd.m15637b(fArr[0]);
            this.f14013b.f14032l = C2903gd.m15637b(fArr[1]);
            this.f14013b.f14033m = C2903gd.m15637b(fArr[2]);
            m15660a(fArr, 0);
            m15660a(fArr, 1);
            m15660a(fArr, 2);
            m15658a(fArr, i, i2, 0);
            m15658a(fArr, i, i2, 1);
            m15658a(fArr, i, i2, 2);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15660a(float[][] fArr, int i) {
        float[] e = C2903gd.m15634e(fArr[i]);
        if (i == 0) {
            this.f14013b.f14034n = C2903gd.m15640a(e);
            this.f14013b.f14037q = C2903gd.m15637b(e);
        } else if (i == 1) {
            this.f14013b.f14035o = C2903gd.m15640a(e);
            this.f14013b.f14038r = C2903gd.m15637b(e);
        } else if (i == 2) {
            this.f14013b.f14036p = C2903gd.m15640a(e);
            this.f14013b.f14039s = C2903gd.m15637b(e);
        }
    }

    /* renamed from: a */
    private void m15658a(float[][] fArr, int i, int i2, int i3) {
        float[] a = C2903gd.m15638a(fArr[i3], i, i2);
        if (i3 == 0) {
            C2898fz fzVar = this.f14013b;
            fzVar.f14043w = a[0];
            fzVar.f14040t = a[1];
        } else if (i3 == 1) {
            C2898fz fzVar2 = this.f14013b;
            fzVar2.f14044x = a[0];
            fzVar2.f14041u = a[1];
        } else if (i3 == 2) {
            C2898fz fzVar3 = this.f14013b;
            fzVar3.f14045y = a[0];
            fzVar3.f14042v = a[1];
        }
    }

    /* renamed from: a */
    public double[] m15662a() {
        return new double[]{this.f14013b.f14022b, this.f14013b.f14025e, this.f14013b.f14028h, this.f14013b.f14031k, this.f14013b.f14034n, this.f14013b.f14037q, this.f14013b.f14040t, this.f14013b.f14043w, this.f14013b.f14023c, this.f14013b.f14026f, this.f14013b.f14029i, this.f14013b.f14032l, this.f14013b.f14035o, this.f14013b.f14038r, this.f14013b.f14041u, this.f14013b.f14044x, this.f14013b.f14024d, this.f14013b.f14027g, this.f14013b.f14030j, this.f14013b.f14033m, this.f14013b.f14036p, this.f14013b.f14039s, this.f14013b.f14042v, this.f14013b.f14045y};
    }
}
