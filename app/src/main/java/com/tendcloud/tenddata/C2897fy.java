package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fy */
/* loaded from: classes2.dex */
public class C2897fy {

    /* renamed from: a */
    public float[] f14014a = new float[3];

    /* renamed from: b */
    public float[] f14015b = new float[3];

    /* renamed from: c */
    public float[] f14016c = new float[3];

    /* renamed from: d */
    public float[] f14017d = new float[3];

    /* renamed from: g */
    public float[] f14020g = new float[3];

    /* renamed from: e */
    public float[] f14018e = new float[3];

    /* renamed from: f */
    public float[] f14019f = new float[3];

    public void clone(C2897fy fyVar) {
        try {
            System.arraycopy(fyVar.f14014a, 0, this.f14014a, 0, 3);
            System.arraycopy(fyVar.f14015b, 0, this.f14015b, 0, 3);
            System.arraycopy(fyVar.f14016c, 0, this.f14016c, 0, 3);
            System.arraycopy(fyVar.f14017d, 0, this.f14017d, 0, 3);
            System.arraycopy(fyVar.f14019f, 0, this.f14019f, 0, 3);
            System.arraycopy(fyVar.f14020g, 0, this.f14020g, 0, 3);
            System.arraycopy(fyVar.f14018e, 0, this.f14018e, 0, 3);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private String m15657a() {
        return m15656a(this.f14014a) + "," + m15656a(this.f14015b) + "," + m15656a(this.f14016c) + "," + m15656a(this.f14017d) + "," + m15656a(this.f14019f) + "," + m15656a(this.f14020g);
    }

    public String toString() {
        return m15657a();
    }

    /* renamed from: a */
    private String m15656a(float[] fArr) {
        return String.valueOf(fArr[0]) + "," + String.valueOf(fArr[1]) + "," + String.valueOf(fArr[2]);
    }
}
