package p110z1;

/* renamed from: z1.jz */
/* loaded from: classes3.dex */
public abstract class AbstractRSSReader extends OneDReader {

    /* renamed from: g */
    private static final float f22203g = 0.2f;

    /* renamed from: h */
    private static final float f22204h = 0.45f;

    /* renamed from: i */
    private static final float f22205i = 0.7916667f;

    /* renamed from: j */
    private static final float f22206j = 0.89285713f;

    /* renamed from: a */
    protected final int[] f22207a = new int[4];

    /* renamed from: b */
    protected final int[] f22208b = new int[8];

    /* renamed from: c */
    protected final float[] f22209c = new float[4];

    /* renamed from: d */
    protected final float[] f22210d = new float[4];

    /* renamed from: e */
    protected final int[] f22211e;

    /* renamed from: f */
    protected final int[] f22212f;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRSSReader() {
        int[] iArr = this.f22208b;
        this.f22211e = new int[iArr.length / 2];
        this.f22212f = new int[iArr.length / 2];
    }

    /* renamed from: b */
    private int[] m2236b() {
        return this.f22207a;
    }

    /* renamed from: c */
    private int[] m2233c() {
        return this.f22208b;
    }

    /* renamed from: d */
    private float[] m2232d() {
        return this.f22209c;
    }

    /* renamed from: e */
    private float[] m2231e() {
        return this.f22210d;
    }

    /* renamed from: f */
    private int[] m2230f() {
        return this.f22211e;
    }

    /* renamed from: g */
    private int[] m2229g() {
        return this.f22212f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static int m2237a(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i = 0; i < iArr2.length; i++) {
            if (m2089a(iArr, iArr2[i], (float) f22204h) < f22203g) {
                return i;
            }
        }
        throw NotFoundException.m1647a();
    }

    @Deprecated
    /* renamed from: b */
    private static int m2235b(int[] iArr) {
        return MathUtils.m2528a(iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m2238a(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static void m2234b(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m2239a(int[] iArr) {
        int i;
        float f = (iArr[0] + iArr[1]) / ((iArr[2] + i) + iArr[3]);
        if (f < f22205i || f > f22206j) {
            return false;
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        for (int i4 : iArr) {
            if (i4 > i2) {
                i2 = i4;
            }
            if (i4 < i3) {
                i3 = i4;
            }
        }
        return i2 < i3 * 10;
    }
}
