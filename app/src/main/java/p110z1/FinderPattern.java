package p110z1;

/* renamed from: z1.ky */
/* loaded from: classes3.dex */
public final class FinderPattern {

    /* renamed from: a */
    public final int f22287a;

    /* renamed from: b */
    public final int[] f22288b;

    /* renamed from: c */
    public final ResultPoint[] f22289c;

    public FinderPattern(int i, int[] iArr, int i2, int i3, int i4) {
        this.f22287a = i;
        this.f22288b = iArr;
        float f = i4;
        this.f22289c = new ResultPoint[]{new ResultPoint(i2, f), new ResultPoint(i3, f)};
    }

    /* renamed from: a */
    private int m2140a() {
        return this.f22287a;
    }

    /* renamed from: b */
    private int[] m2139b() {
        return this.f22288b;
    }

    /* renamed from: c */
    private ResultPoint[] m2138c() {
        return this.f22289c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof FinderPattern) && this.f22287a == ((FinderPattern) obj).f22287a;
    }

    public final int hashCode() {
        return this.f22287a;
    }
}
