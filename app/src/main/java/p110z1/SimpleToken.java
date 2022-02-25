package p110z1;

/* renamed from: z1.ge */
/* loaded from: classes3.dex */
final class SimpleToken extends AbstractC5363gg {

    /* renamed from: c */
    private final short f21771c;

    /* renamed from: d */
    private final short f21772d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleToken(AbstractC5363gg ggVar, int i, int i2) {
        super(ggVar);
        this.f21771c = (short) i;
        this.f21772d = (short) i2;
    }

    @Override // p110z1.AbstractC5363gg
    /* renamed from: a */
    final void mo2734a(BitArray huVar, byte[] bArr) {
        huVar.m2544b(this.f21771c, this.f21772d);
    }

    public final String toString() {
        short s = this.f21771c;
        short s2 = this.f21772d;
        int i = (s & ((1 << s2) - 1)) | (1 << s2);
        return SimpleComparison.f23612f + Integer.toBinaryString(i | (1 << this.f21772d)).substring(1) + Typography.f21053e;
    }
}
