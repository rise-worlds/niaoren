package p110z1;

/* renamed from: z1.ke */
/* loaded from: classes3.dex */
public final class AI01393xDecoder extends AI01decoder {

    /* renamed from: d */
    private static final int f22221d = 8;

    /* renamed from: e */
    private static final int f22222e = 2;

    /* renamed from: f */
    private static final int f22223f = 10;

    public AI01393xDecoder(BitArray huVar) {
        super(huVar);
    }

    @Override // p110z1.AbstractExpandedDecoder
    /* renamed from: a */
    public final String mo2216a() throws NotFoundException, FormatException {
        if (this.f22233b.f21908b >= 48) {
            StringBuilder sb = new StringBuilder();
            m2223b(sb, 8);
            int a = this.f22234c.m2186a(48, 2);
            sb.append("(393");
            sb.append(a);
            sb.append(')');
            int a2 = this.f22234c.m2186a(50, 10);
            if (a2 / 100 == 0) {
                sb.append('0');
            }
            if (a2 / 10 == 0) {
                sb.append('0');
            }
            sb.append(a2);
            sb.append(this.f22234c.m2185a(60, (String) null).f22246a);
            return sb.toString();
        }
        throw NotFoundException.m1647a();
    }
}
