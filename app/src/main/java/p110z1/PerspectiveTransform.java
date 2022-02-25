package p110z1;

/* renamed from: z1.im */
/* loaded from: classes3.dex */
public final class PerspectiveTransform {

    /* renamed from: a */
    final float f22014a;

    /* renamed from: b */
    final float f22015b;

    /* renamed from: c */
    final float f22016c;

    /* renamed from: d */
    final float f22017d;

    /* renamed from: e */
    final float f22018e;

    /* renamed from: f */
    final float f22019f;

    /* renamed from: g */
    final float f22020g;

    /* renamed from: h */
    final float f22021h;

    /* renamed from: i */
    final float f22022i;

    private PerspectiveTransform(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.f22014a = f;
        this.f22015b = f4;
        this.f22016c = f7;
        this.f22017d = f2;
        this.f22018e = f5;
        this.f22019f = f8;
        this.f22020g = f3;
        this.f22021h = f6;
        this.f22022i = f9;
    }

    /* renamed from: a */
    private void m2425a(float[] fArr) {
        int length = fArr.length;
        float f = this.f22014a;
        float f2 = this.f22015b;
        float f3 = this.f22016c;
        float f4 = this.f22017d;
        float f5 = this.f22018e;
        float f6 = this.f22019f;
        float f7 = this.f22020g;
        float f8 = this.f22021h;
        float f9 = this.f22022i;
        for (int i = 0; i < length; i += 2) {
            float f10 = fArr[i];
            int i2 = i + 1;
            float f11 = fArr[i2];
            float f12 = (f3 * f10) + (f6 * f11) + f9;
            fArr[i] = (((f * f10) + (f4 * f11)) + f7) / f12;
            fArr[i2] = (((f10 * f2) + (f11 * f5)) + f8) / f12;
        }
    }

    /* renamed from: a */
    private void m2424a(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            float f = fArr[i];
            float f2 = fArr2[i];
            float f3 = (this.f22016c * f) + (this.f22019f * f2) + this.f22022i;
            fArr[i] = (((this.f22014a * f) + (this.f22017d * f2)) + this.f22020g) / f3;
            fArr2[i] = (((this.f22015b * f) + (this.f22018e * f2)) + this.f22021h) / f3;
        }
    }

    /* renamed from: a */
    private static PerspectiveTransform m2428a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = ((f - f3) + f5) - f7;
        float f10 = ((f2 - f4) + f6) - f8;
        if (f9 == 0.0f && f10 == 0.0f) {
            return new PerspectiveTransform(f3 - f, f5 - f3, f, f4 - f2, f6 - f4, f2, 0.0f, 0.0f, 1.0f);
        }
        float f11 = f3 - f5;
        float f12 = f7 - f5;
        float f13 = f4 - f6;
        float f14 = f8 - f6;
        float f15 = (f11 * f14) - (f12 * f13);
        float f16 = ((f14 * f9) - (f12 * f10)) / f15;
        float f17 = ((f11 * f10) - (f9 * f13)) / f15;
        return new PerspectiveTransform((f16 * f3) + (f3 - f), (f17 * f7) + (f7 - f), f, (f4 - f2) + (f16 * f4), (f8 - f2) + (f17 * f8), f2, f16, f17, 1.0f);
    }

    /* renamed from: b */
    private static PerspectiveTransform m2423b(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        PerspectiveTransform a = m2428a(f, f2, f3, f4, f5, f6, f7, f8);
        float f9 = a.f22018e;
        float f10 = a.f22022i;
        float f11 = a.f22019f;
        float f12 = a.f22021h;
        float f13 = (f9 * f10) - (f11 * f12);
        float f14 = a.f22020g;
        float f15 = a.f22017d;
        float f16 = (f11 * f14) - (f15 * f10);
        float f17 = (f15 * f12) - (f9 * f14);
        float f18 = a.f22016c;
        float f19 = a.f22015b;
        float f20 = a.f22014a;
        return new PerspectiveTransform(f13, f16, f17, (f18 * f12) - (f19 * f10), (f10 * f20) - (f18 * f14), (f14 * f19) - (f12 * f20), (f19 * f11) - (f18 * f9), (f18 * f15) - (f11 * f20), (f20 * f9) - (f19 * f15));
    }

    /* renamed from: a */
    private PerspectiveTransform m2429a() {
        float f = this.f22018e;
        float f2 = this.f22022i;
        float f3 = this.f22019f;
        float f4 = this.f22021h;
        float f5 = (f * f2) - (f3 * f4);
        float f6 = this.f22020g;
        float f7 = this.f22017d;
        float f8 = (f3 * f6) - (f7 * f2);
        float f9 = (f7 * f4) - (f * f6);
        float f10 = this.f22016c;
        float f11 = this.f22015b;
        float f12 = (f10 * f4) - (f11 * f2);
        float f13 = this.f22014a;
        return new PerspectiveTransform(f5, f8, f9, f12, (f2 * f13) - (f10 * f6), (f6 * f11) - (f4 * f13), (f11 * f3) - (f10 * f), (f10 * f7) - (f3 * f13), (f13 * f) - (f11 * f7));
    }

    /* renamed from: a */
    private PerspectiveTransform m2426a(PerspectiveTransform imVar) {
        float f = this.f22014a;
        float f2 = imVar.f22014a;
        float f3 = this.f22017d;
        float f4 = imVar.f22015b;
        float f5 = this.f22020g;
        float f6 = imVar.f22016c;
        float f7 = (f * f2) + (f3 * f4) + (f5 * f6);
        float f8 = imVar.f22017d;
        float f9 = imVar.f22018e;
        float f10 = imVar.f22019f;
        float f11 = (f * f8) + (f3 * f9) + (f5 * f10);
        float f12 = imVar.f22020g;
        float f13 = imVar.f22021h;
        float f14 = imVar.f22022i;
        float f15 = (f * f12) + (f3 * f13) + (f5 * f14);
        float f16 = this.f22015b;
        float f17 = this.f22018e;
        float f18 = this.f22021h;
        float f19 = (f18 * f14) + (f16 * f12) + (f17 * f13);
        float f20 = this.f22016c;
        float f21 = this.f22019f;
        float f22 = (f2 * f20) + (f4 * f21);
        float f23 = this.f22022i;
        return new PerspectiveTransform(f7, f11, f15, (f16 * f2) + (f17 * f4) + (f18 * f6), (f16 * f8) + (f17 * f9) + (f18 * f10), f19, (f6 * f23) + f22, (f8 * f20) + (f9 * f21) + (f10 * f23), (f20 * f12) + (f21 * f13) + (f23 * f14));
    }

    /* renamed from: a */
    public static PerspectiveTransform m2427a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        PerspectiveTransform a = m2428a(f, f2, f3, f4, f5, f6, f7, f8);
        float f17 = a.f22018e;
        float f18 = a.f22022i;
        float f19 = a.f22019f;
        float f20 = a.f22021h;
        float f21 = a.f22020g;
        float f22 = a.f22017d;
        float f23 = a.f22016c;
        float f24 = a.f22015b;
        float f25 = a.f22014a;
        PerspectiveTransform imVar = new PerspectiveTransform((f17 * f18) - (f19 * f20), (f19 * f21) - (f22 * f18), (f22 * f20) - (f17 * f21), (f23 * f20) - (f24 * f18), (f18 * f25) - (f23 * f21), (f21 * f24) - (f20 * f25), (f24 * f19) - (f23 * f17), (f23 * f22) - (f19 * f25), (f25 * f17) - (f24 * f22));
        PerspectiveTransform a2 = m2428a(f9, f10, f11, f12, f13, f14, f15, f16);
        float f26 = a2.f22014a;
        float f27 = imVar.f22014a;
        float f28 = a2.f22017d;
        float f29 = imVar.f22015b;
        float f30 = a2.f22020g;
        float f31 = imVar.f22016c;
        float f32 = (f26 * f27) + (f28 * f29) + (f30 * f31);
        float f33 = imVar.f22017d;
        float f34 = imVar.f22018e;
        float f35 = imVar.f22019f;
        float f36 = (f26 * f33) + (f28 * f34) + (f30 * f35);
        float f37 = imVar.f22020g;
        float f38 = imVar.f22021h;
        float f39 = (f26 * f37) + (f28 * f38);
        float f40 = imVar.f22022i;
        float f41 = f39 + (f30 * f40);
        float f42 = a2.f22015b;
        float f43 = a2.f22018e;
        float f44 = a2.f22021h;
        float f45 = (f42 * f37) + (f43 * f38) + (f44 * f40);
        float f46 = a2.f22016c;
        float f47 = a2.f22019f;
        float f48 = a2.f22022i;
        return new PerspectiveTransform(f32, f36, f41, (f42 * f27) + (f43 * f29) + (f44 * f31), (f42 * f33) + (f43 * f34) + (f44 * f35), f45, (f27 * f46) + (f29 * f47) + (f31 * f48), (f33 * f46) + (f34 * f47) + (f35 * f48), (f48 * f40) + (f46 * f37) + (f47 * f38));
    }
}
