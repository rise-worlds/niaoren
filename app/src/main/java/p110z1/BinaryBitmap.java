package p110z1;

/* renamed from: z1.ht */
/* loaded from: classes3.dex */
public final class BinaryBitmap {

    /* renamed from: a */
    public final Binarizer f21905a;

    /* renamed from: b */
    private BitMatrix f21906b;

    public BinaryBitmap(Binarizer ghVar) {
        if (ghVar != null) {
            this.f21905a = ghVar;
            return;
        }
        throw new IllegalArgumentException("Binarizer must be non-null.");
    }

    /* renamed from: a */
    public final int m2561a() {
        return this.f21905a.f21780a.f22688a;
    }

    /* renamed from: b */
    public final int m2558b() {
        return this.f21905a.f21780a.f22689b;
    }

    /* renamed from: a */
    private BitArray m2559a(int i, BitArray huVar) throws NotFoundException {
        return this.f21905a.mo2442a(i, huVar);
    }

    /* renamed from: c */
    public final BitMatrix m2557c() throws NotFoundException {
        if (this.f21906b == null) {
            this.f21906b = this.f21905a.mo2435a();
        }
        return this.f21906b;
    }

    /* renamed from: d */
    private boolean m2556d() {
        return this.f21905a.f21780a.mo1639b();
    }

    /* renamed from: a */
    public final BinaryBitmap m2560a(int i, int i2, int i3, int i4) {
        return new BinaryBitmap(this.f21905a.mo2433a(this.f21905a.f21780a.mo1641a(i, i2, i3, i4)));
    }

    /* renamed from: e */
    private boolean m2555e() {
        return this.f21905a.f21780a.mo1656c();
    }

    /* renamed from: f */
    private BinaryBitmap m2554f() {
        return new BinaryBitmap(this.f21905a.mo2433a(this.f21905a.f21780a.mo1654e()));
    }

    /* renamed from: g */
    private BinaryBitmap m2553g() {
        return new BinaryBitmap(this.f21905a.mo2433a(this.f21905a.f21780a.mo1653f()));
    }

    public final String toString() {
        try {
            return m2557c().toString();
        } catch (NotFoundException unused) {
            return "";
        }
    }
}
