package p110z1;

/* compiled from: FinderPattern.java */
/* renamed from: z1.nu */
/* loaded from: classes3.dex */
public final class C5415nu extends ResultPoint {

    /* renamed from: a */
    public final float f22643a;

    /* renamed from: b */
    final int f22644b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5415nu(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5415nu(float f, float f2, float f3, int i) {
        super(f, f2);
        this.f22643a = f3;
        this.f22644b = i;
    }

    /* renamed from: a */
    private float m1774a() {
        return this.f22643a;
    }

    /* renamed from: b */
    private int m1772b() {
        return this.f22644b;
    }

    /* renamed from: b */
    private C5415nu m1771b(float f, float f2, float f3) {
        int i = this.f22644b;
        int i2 = i + 1;
        float f4 = (i * this.f22726c) + f2;
        float f5 = i2;
        return new C5415nu(f4 / f5, ((this.f22644b * this.f22727d) + f) / f5, ((this.f22644b * this.f22643a) + f3) / f5, i2);
    }

    /* renamed from: a */
    private boolean m1773a(float f, float f2, float f3) {
        if (Math.abs(f2 - this.f22727d) > f || Math.abs(f3 - this.f22726c) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f22643a);
        return abs <= 1.0f || abs <= this.f22643a;
    }
}
