package p110z1;

/* renamed from: z1.fv */
/* loaded from: classes3.dex */
public final class AztecDetectorResult extends DetectorResult {

    /* renamed from: a */
    public final boolean f21720a;

    /* renamed from: b */
    public final int f21721b;

    /* renamed from: c */
    public final int f21722c;

    public AztecDetectorResult(BitMatrix hyVar, ResultPoint[] onVarArr, boolean z, int i, int i2) {
        super(hyVar, onVarArr);
        this.f21720a = z;
        this.f21721b = i;
        this.f21722c = i2;
    }

    /* renamed from: a */
    private int m2812a() {
        return this.f21722c;
    }

    /* renamed from: b */
    private int m2811b() {
        return this.f21721b;
    }

    /* renamed from: c */
    private boolean m2810c() {
        return this.f21720a;
    }
}
