package p110z1;

/* renamed from: z1.nr */
/* loaded from: classes3.dex */
public final class AlignmentPattern extends ResultPoint {

    /* renamed from: a */
    final float f22631a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPattern(float f, float f2, float f3) {
        super(f, f2);
        this.f22631a = f3;
    }

    /* renamed from: a */
    private boolean m1794a(float f, float f2, float f3) {
        if (Math.abs(f2 - this.f22727d) > f || Math.abs(f3 - this.f22726c) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f22631a);
        return abs <= 1.0f || abs <= this.f22631a;
    }

    /* renamed from: b */
    private AlignmentPattern m1793b(float f, float f2, float f3) {
        return new AlignmentPattern((this.f22726c + f2) / 2.0f, (this.f22727d + f) / 2.0f, (this.f22631a + f3) / 2.0f);
    }
}
