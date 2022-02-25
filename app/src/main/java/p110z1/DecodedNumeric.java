package p110z1;

/* renamed from: z1.kq */
/* loaded from: classes3.dex */
final class DecodedNumeric extends DecodedObject {

    /* renamed from: c */
    static final int f22249c = 10;

    /* renamed from: a */
    final int f22250a;

    /* renamed from: b */
    final int f22251b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedNumeric(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw FormatException.m2059a();
        }
        this.f22250a = i2;
        this.f22251b = i3;
    }

    /* renamed from: b */
    private int m2197b() {
        return this.f22250a;
    }

    /* renamed from: c */
    private int m2196c() {
        return this.f22251b;
    }

    /* renamed from: d */
    private int m2195d() {
        return (this.f22250a * 10) + this.f22251b;
    }

    /* renamed from: e */
    private boolean m2194e() {
        return this.f22250a == 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m2198a() {
        return this.f22251b == 10;
    }

    /* renamed from: f */
    private boolean m2193f() {
        return this.f22250a == 10 || this.f22251b == 10;
    }
}
