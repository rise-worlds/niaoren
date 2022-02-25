package p110z1;

/* renamed from: z1.ml */
/* loaded from: classes3.dex */
final class Codeword {

    /* renamed from: f */
    private static final int f22438f = -1;

    /* renamed from: a */
    final int f22439a;

    /* renamed from: b */
    final int f22440b;

    /* renamed from: c */
    final int f22441c;

    /* renamed from: d */
    final int f22442d;

    /* renamed from: e */
    int f22443e = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Codeword(int i, int i2, int i3, int i4) {
        this.f22439a = i;
        this.f22440b = i2;
        this.f22441c = i3;
        this.f22442d = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2011a() {
        return m2010a(this.f22443e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2010a(int i) {
        return i != -1 && this.f22441c == (i % 3) * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m2009b() {
        this.f22443e = ((this.f22442d / 30) * 3) + (this.f22441c / 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final int m2007c() {
        return this.f22440b - this.f22439a;
    }

    /* renamed from: d */
    private int m2006d() {
        return this.f22439a;
    }

    /* renamed from: e */
    private int m2005e() {
        return this.f22440b;
    }

    /* renamed from: f */
    private int m2004f() {
        return this.f22441c;
    }

    /* renamed from: g */
    private int m2003g() {
        return this.f22442d;
    }

    /* renamed from: h */
    private int m2002h() {
        return this.f22443e;
    }

    /* renamed from: b */
    private void m2008b(int i) {
        this.f22443e = i;
    }

    public final String toString() {
        return this.f22443e + "|" + this.f22442d;
    }
}
