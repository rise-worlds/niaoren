package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fo */
/* loaded from: classes2.dex */
public class C2886fo implements AbstractC2885fn {
    @Override // com.tendcloud.tenddata.AbstractC2885fn
    /* renamed from: a */
    public double[] mo15666a(C2897fy[] fyVarArr, int i, int i2) {
        float[] fArr = new float[fyVarArr.length];
        float[] fArr2 = new float[fyVarArr.length];
        for (int i3 = 0; i3 < fyVarArr.length; i3++) {
            fArr[i3] = fyVarArr[i3].f14018e[1];
            fArr2[i3] = fyVarArr[i3].f14018e[2];
        }
        float degrees = (float) Math.toDegrees(C2903gd.m15640a(fArr));
        float degrees2 = (float) Math.toDegrees(C2903gd.m15640a(fArr2));
        boolean z = degrees < -30.0f && degrees > -90.0f;
        boolean z2 = degrees2 < -15.0f && degrees2 > -90.0f;
        double[] dArr = new double[2];
        if (z || z2) {
            dArr[0] = 1.0d;
            dArr[1] = 0.0d;
        } else {
            dArr[0] = 0.0d;
            dArr[1] = 1.0d;
        }
        return dArr;
    }
}
