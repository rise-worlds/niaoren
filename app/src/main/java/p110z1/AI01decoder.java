package p110z1;

/* renamed from: z1.ki */
/* loaded from: classes3.dex */
abstract class AI01decoder extends AbstractExpandedDecoder {

    /* renamed from: a */
    static final int f22232a = 40;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI01decoder(BitArray huVar) {
        super(huVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m2223b(StringBuilder sb, int i) {
        sb.append("(01)");
        int length = sb.length();
        sb.append('9');
        m2224a(sb, i, length);
    }

    /* renamed from: a */
    private static void m2225a(StringBuilder sb, int i) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 13; i4++) {
            int charAt = sb.charAt(i4 + i) - '0';
            if ((i4 & 1) == 0) {
                charAt *= 3;
            }
            i3 += charAt;
        }
        int i5 = 10 - (i3 % 10);
        if (i5 != 10) {
            i2 = i5;
        }
        sb.append(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2224a(StringBuilder sb, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int a = this.f22234c.m2186a((i3 * 10) + i, 10);
            if (a / 100 == 0) {
                sb.append('0');
            }
            if (a / 10 == 0) {
                sb.append('0');
            }
            sb.append(a);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 13; i5++) {
            int charAt = sb.charAt(i5 + i2) - '0';
            if ((i5 & 1) == 0) {
                charAt *= 3;
            }
            i4 += charAt;
        }
        int i6 = 10 - (i4 % 10);
        if (i6 == 10) {
            i6 = 0;
        }
        sb.append(i6);
    }
}
