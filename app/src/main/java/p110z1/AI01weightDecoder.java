package p110z1;

/* renamed from: z1.kj */
/* loaded from: classes3.dex */
abstract class AI01weightDecoder extends AI01decoder {
    /* renamed from: a */
    protected abstract int mo2222a(int i);

    /* renamed from: a */
    protected abstract void mo2221a(StringBuilder sb, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI01weightDecoder(BitArray huVar) {
        super(huVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m2220b(StringBuilder sb, int i, int i2) {
        int a = this.f22234c.m2186a(i, i2);
        mo2221a(sb, a);
        int a2 = mo2222a(a);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (a2 / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(a2);
    }
}
