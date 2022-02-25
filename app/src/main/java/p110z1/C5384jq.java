package p110z1;

/* compiled from: Dimension.java */
/* renamed from: z1.jq */
/* loaded from: classes3.dex */
public final class C5384jq {

    /* renamed from: a */
    public final int f22166a;

    /* renamed from: b */
    public final int f22167b;

    private C5384jq(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.f22166a = i;
        this.f22167b = i2;
    }

    /* renamed from: a */
    private int m2256a() {
        return this.f22166a;
    }

    /* renamed from: b */
    private int m2255b() {
        return this.f22167b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C5384jq)) {
            return false;
        }
        C5384jq jqVar = (C5384jq) obj;
        return this.f22166a == jqVar.f22166a && this.f22167b == jqVar.f22167b;
    }

    public final int hashCode() {
        return (this.f22166a * 32713) + this.f22167b;
    }

    public final String toString() {
        return this.f22166a + "x" + this.f22167b;
    }
}
