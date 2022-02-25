package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gb */
/* loaded from: classes2.dex */
public class C2901gb {
    /* renamed from: a */
    public static C2900ga[] m15641a(C2900ga[] gaVarArr) {
        int length = gaVarArr.length;
        if (length == 1) {
            return new C2900ga[]{gaVarArr[0]};
        }
        if (length % 2 == 0) {
            int i = length / 2;
            C2900ga[] gaVarArr2 = new C2900ga[i];
            for (int i2 = 0; i2 < i; i2++) {
                gaVarArr2[i2] = gaVarArr[i2 * 2];
            }
            C2900ga[] a = m15641a(gaVarArr2);
            for (int i3 = 0; i3 < i; i3++) {
                gaVarArr2[i3] = gaVarArr[(i3 * 2) + 1];
            }
            C2900ga[] a2 = m15641a(gaVarArr2);
            C2900ga[] gaVarArr3 = new C2900ga[length];
            for (int i4 = 0; i4 < i; i4++) {
                double d = ((i4 * (-2)) * 3.141592653589793d) / length;
                C2900ga gaVar = new C2900ga(Math.cos(d), Math.sin(d));
                gaVarArr3[i4] = a[i4].m15653a(gaVar.m15648c(a2[i4]));
                gaVarArr3[i4 + i] = a[i4].m15650b(gaVar.m15648c(a2[i4]));
            }
            return gaVarArr3;
        }
        throw new RuntimeException("N is not a power of 2");
    }
}
