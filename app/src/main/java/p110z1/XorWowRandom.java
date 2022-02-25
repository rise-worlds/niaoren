package p110z1;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B7\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m8860e = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "kotlin-stdlib"})
/* renamed from: z1.clt */
/* loaded from: classes3.dex */
public final class XorWowRandom extends Random {

    /* renamed from: c */
    private int f20811c;

    /* renamed from: d */
    private int f20812d;

    /* renamed from: e */
    private int f20813e;

    /* renamed from: f */
    private int f20814f;

    /* renamed from: g */
    private int f20815g;

    /* renamed from: h */
    private int f20816h;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f20811c = i;
        this.f20812d = i2;
        this.f20813e = i3;
        this.f20814f = i4;
        this.f20815g = i5;
        this.f20816h = i6;
        if (((((this.f20811c | this.f20812d) | this.f20813e) | this.f20814f) | this.f20815g) != 0) {
            for (int i7 = 0; i7 < 64; i7++) {
                mo4862b();
            }
            return;
        }
        throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
    }

    public XorWowRandom(int i, int i2) {
        this(i, i2, 0, 0, ~i, (i << 10) ^ (i2 >>> 4));
    }

    @Override // p110z1.Random
    /* renamed from: b */
    public int mo4862b() {
        int i = this.f20811c;
        int i2 = i ^ (i >>> 2);
        this.f20811c = this.f20812d;
        this.f20812d = this.f20813e;
        this.f20813e = this.f20814f;
        int i3 = this.f20815g;
        this.f20814f = i3;
        int i4 = ((i2 ^ (i2 << 1)) ^ i3) ^ (i3 << 4);
        this.f20815g = i4;
        this.f20816h += 362437;
        return i4 + this.f20816h;
    }

    @Override // p110z1.Random
    /* renamed from: a */
    public int mo4863a(int i) {
        return clr.m4885a(mo4862b(), i);
    }
}
