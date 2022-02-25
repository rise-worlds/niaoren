package p110z1;

/* renamed from: z1.ne */
/* loaded from: classes3.dex */
public final class InvertedLuminanceSource extends LuminanceSource {

    /* renamed from: c */
    private final LuminanceSource f22573c;

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final byte[] mo1640a(int i, byte[] bArr) {
        byte[] a = this.f22573c.mo1640a(i, bArr);
        int i2 = this.f22688a;
        for (int i3 = 0; i3 < i2; i3++) {
            a[i3] = (byte) (255 - (a[i3] & 255));
        }
        return a;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final byte[] mo1642a() {
        byte[] a = this.f22573c.mo1642a();
        int i = this.f22688a * this.f22689b;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (255 - (a[i2] & 255));
        }
        return bArr;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: b */
    public final boolean mo1639b() {
        return this.f22573c.mo1639b();
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: a */
    public final LuminanceSource mo1641a(int i, int i2, int i3, int i4) {
        return new InvertedLuminanceSource(this.f22573c.mo1641a(i, i2, i3, i4));
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: c */
    public final boolean mo1656c() {
        return this.f22573c.mo1656c();
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: d */
    public final LuminanceSource mo1655d() {
        return this.f22573c;
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: e */
    public final LuminanceSource mo1654e() {
        return new InvertedLuminanceSource(this.f22573c.mo1654e());
    }

    @Override // p110z1.LuminanceSource
    /* renamed from: f */
    public final LuminanceSource mo1653f() {
        return new InvertedLuminanceSource(this.f22573c.mo1653f());
    }

    public InvertedLuminanceSource(LuminanceSource odVar) {
        super(odVar.f22688a, odVar.f22689b);
        this.f22573c = odVar;
    }
}
