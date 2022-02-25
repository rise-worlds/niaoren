package p110z1;

/* renamed from: z1.gb */
/* loaded from: classes3.dex */
final class BinaryShiftToken extends AbstractC5363gg {

    /* renamed from: c */
    private final short f21753c;

    /* renamed from: d */
    private final short f21754d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryShiftToken(AbstractC5363gg ggVar, int i, int i2) {
        super(ggVar);
        this.f21753c = (short) i;
        this.f21754d = (short) i2;
    }

    @Override // p110z1.AbstractC5363gg
    /* renamed from: a */
    public final void mo2734a(BitArray huVar, byte[] bArr) {
        int i = 0;
        while (true) {
            short s = this.f21754d;
            if (i < s) {
                if (i == 0 || (i == 31 && s <= 62)) {
                    huVar.m2544b(31, 5);
                    short s2 = this.f21754d;
                    if (s2 > 62) {
                        huVar.m2544b(s2 - 31, 16);
                    } else if (i == 0) {
                        huVar.m2544b(Math.min((int) s2, 31), 5);
                    } else {
                        huVar.m2544b(s2 - 31, 5);
                    }
                }
                huVar.m2544b(bArr[this.f21753c + i], 8);
                i++;
            } else {
                return;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(SimpleComparison.f23612f);
        sb.append((int) this.f21753c);
        sb.append("::");
        sb.append((this.f21753c + this.f21754d) - 1);
        sb.append(Typography.f21053e);
        return sb.toString();
    }
}
