package p110z1;

/* renamed from: z1.mh */
/* loaded from: classes3.dex */
public final class ModulusGF {

    /* renamed from: a */
    public static final ModulusGF f22420a = new ModulusGF();

    /* renamed from: d */
    public final ModulusPoly f22423d;

    /* renamed from: e */
    public final ModulusPoly f22424e;

    /* renamed from: f */
    final int f22425f = PDF417Common.f22404a;

    /* renamed from: b */
    public final int[] f22421b = new int[PDF417Common.f22404a];

    /* renamed from: c */
    public final int[] f22422c = new int[PDF417Common.f22404a];

    private ModulusGF() {
        int i = 1;
        for (int i2 = 0; i2 < 929; i2++) {
            this.f22421b[i2] = i;
            i = (i * 3) % PDF417Common.f22404a;
        }
        for (int i3 = 0; i3 < 928; i3++) {
            this.f22422c[this.f22421b[i3]] = i3;
        }
        this.f22423d = new ModulusPoly(this, new int[]{0});
        this.f22424e = new ModulusPoly(this, new int[]{1});
    }

    /* renamed from: a */
    private ModulusPoly m2045a() {
        return this.f22423d;
    }

    /* renamed from: b */
    private ModulusPoly m2042b() {
        return this.f22424e;
    }

    /* renamed from: a */
    public final ModulusPoly m2043a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.f22423d;
        } else {
            int[] iArr = new int[i + 1];
            iArr[0] = i2;
            return new ModulusPoly(this, iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final int m2040b(int i, int i2) {
        return (i + i2) % this.f22425f;
    }

    /* renamed from: c */
    public final int m2037c(int i, int i2) {
        int i3 = this.f22425f;
        return ((i + i3) - i2) % i3;
    }

    /* renamed from: b */
    private int m2041b(int i) {
        return this.f22421b[i];
    }

    /* renamed from: c */
    private int m2038c(int i) {
        if (i != 0) {
            return this.f22422c[i];
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public final int m2044a(int i) {
        if (i != 0) {
            return this.f22421b[(this.f22425f - this.f22422c[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* renamed from: d */
    public final int m2036d(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = this.f22421b;
        int[] iArr2 = this.f22422c;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.f22425f - 1)];
    }

    /* renamed from: c */
    private int m2039c() {
        return this.f22425f;
    }
}
