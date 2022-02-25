package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gc */
/* loaded from: classes2.dex */
public class C2902gc extends AbstractC2905gf {
    public C2902gc() {
        try {
            this.f14049a = "Haar";
            this.f14051c = 2;
            this.f14050b = 2;
            double sqrt = Math.sqrt(2.0d);
            this.f14052d = new double[this.f14050b];
            double d = 1.0d / sqrt;
            this.f14052d[0] = d;
            this.f14052d[1] = d;
            this.f14053e = new double[this.f14050b];
            this.f14053e[0] = this.f14052d[1];
            this.f14053e[1] = -this.f14052d[0];
            this.f14054f = new double[this.f14050b];
            this.f14055g = new double[this.f14050b];
            for (int i = 0; i < this.f14050b; i++) {
                this.f14054f[i] = this.f14052d[i];
                this.f14055g[i] = this.f14053e[i];
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
