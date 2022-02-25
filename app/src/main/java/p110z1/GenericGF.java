package p110z1;

/* renamed from: z1.hz */
/* loaded from: classes3.dex */
public final class GenericGF {

    /* renamed from: f */
    public static final GenericGF f21929f;

    /* renamed from: g */
    public static final GenericGF f21930g;

    /* renamed from: i */
    final int[] f21932i;

    /* renamed from: j */
    final GenericGFPoly f21933j;

    /* renamed from: k */
    final GenericGFPoly f21934k;

    /* renamed from: l */
    final int f21935l;

    /* renamed from: m */
    final int f21936m;

    /* renamed from: n */
    private final int[] f21937n;

    /* renamed from: o */
    private final int f21938o;

    /* renamed from: a */
    public static final GenericGF f21924a = new GenericGF(4201, 4096, 1);

    /* renamed from: b */
    public static final GenericGF f21925b = new GenericGF(1033, 1024, 1);

    /* renamed from: c */
    public static final GenericGF f21926c = new GenericGF(67, 64, 1);

    /* renamed from: d */
    public static final GenericGF f21927d = new GenericGF(19, 16, 1);

    /* renamed from: e */
    public static final GenericGF f21928e = new GenericGF(285, 256, 0);

    /* renamed from: h */
    public static final GenericGF f21931h = f21926c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static int m2493b(int i, int i2) {
        return i ^ i2;
    }

    static {
        GenericGF hzVar = new GenericGF(301, 256, 1);
        f21929f = hzVar;
        f21930g = hzVar;
    }

    private GenericGF(int i, int i2, int i3) {
        this.f21938o = i;
        this.f21935l = i2;
        this.f21936m = i3;
        this.f21932i = new int[i2];
        this.f21937n = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.f21932i[i5] = i4;
            i4 <<= 1;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.f21937n[this.f21932i[i6]] = i6;
        }
        this.f21933j = new GenericGFPoly(this, new int[]{0});
        this.f21934k = new GenericGFPoly(this, new int[]{1});
    }

    /* renamed from: a */
    private GenericGFPoly m2498a() {
        return this.f21933j;
    }

    /* renamed from: b */
    private GenericGFPoly m2495b() {
        return this.f21934k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final GenericGFPoly m2496a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f21933j;
        } else {
            int[] iArr = new int[i + 1];
            iArr[0] = i2;
            return new GenericGFPoly(this, iArr);
        }
    }

    /* renamed from: c */
    private int m2491c(int i) {
        return this.f21932i[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m2497a(int i) {
        if (i != 0) {
            return this.f21937n[i];
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m2494b(int i) {
        if (i != 0) {
            return this.f21932i[(this.f21935l - this.f21937n[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final int m2490c(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f21932i;
        int[] iArr2 = this.f21937n;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.f21935l - 1)];
    }

    /* renamed from: c */
    private int m2492c() {
        return this.f21935l;
    }

    /* renamed from: d */
    private int m2489d() {
        return this.f21936m;
    }

    public final String toString() {
        return "GF(0x" + Integer.toHexString(this.f21938o) + ',' + this.f21935l + ')';
    }
}
