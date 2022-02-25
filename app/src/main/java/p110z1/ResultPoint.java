package p110z1;

/* renamed from: z1.on */
/* loaded from: classes3.dex */
public class ResultPoint {

    /* renamed from: c */
    public final float f22726c;

    /* renamed from: d */
    public final float f22727d;

    public ResultPoint(float f, float f2) {
        this.f22726c = f;
        this.f22727d = f2;
    }

    /* renamed from: a */
    private float m1625a() {
        return this.f22726c;
    }

    /* renamed from: b */
    private float m1621b() {
        return this.f22727d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint onVar = (ResultPoint) obj;
        return this.f22726c == onVar.f22726c && this.f22727d == onVar.f22727d;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f22726c) * 31) + Float.floatToIntBits(this.f22727d);
    }

    public final String toString() {
        return "(" + this.f22726c + ',' + this.f22727d + ')';
    }

    /* renamed from: a */
    public static void m1622a(ResultPoint[] onVarArr) {
        ResultPoint onVar;
        ResultPoint onVar2;
        ResultPoint onVar3;
        float a = m1624a(onVarArr[0], onVarArr[1]);
        float a2 = m1624a(onVarArr[1], onVarArr[2]);
        float a3 = m1624a(onVarArr[0], onVarArr[2]);
        if (a2 >= a && a2 >= a3) {
            onVar3 = onVarArr[0];
            onVar2 = onVarArr[1];
            onVar = onVarArr[2];
        } else if (a3 < a2 || a3 < a) {
            onVar3 = onVarArr[2];
            onVar2 = onVarArr[0];
            onVar = onVarArr[1];
        } else {
            onVar3 = onVarArr[1];
            onVar2 = onVarArr[0];
            onVar = onVarArr[2];
        }
        float f = onVar3.f22726c;
        float f2 = onVar3.f22727d;
        if (((onVar.f22726c - f) * (onVar2.f22727d - f2)) - ((onVar.f22727d - f2) * (onVar2.f22726c - f)) < 0.0f) {
            onVar = onVar2;
            onVar2 = onVar;
        }
        onVarArr[0] = onVar2;
        onVarArr[1] = onVar3;
        onVarArr[2] = onVar;
    }

    /* renamed from: a */
    public static float m1624a(ResultPoint onVar, ResultPoint onVar2) {
        return MathUtils.m2530a(onVar.f22726c, onVar.f22727d, onVar2.f22726c, onVar2.f22727d);
    }

    /* renamed from: a */
    private static float m1623a(ResultPoint onVar, ResultPoint onVar2, ResultPoint onVar3) {
        float f = onVar2.f22726c;
        float f2 = onVar2.f22727d;
        return ((onVar3.f22726c - f) * (onVar.f22727d - f2)) - ((onVar3.f22727d - f2) * (onVar.f22726c - f));
    }
}
