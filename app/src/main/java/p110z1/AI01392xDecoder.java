package p110z1;

/* renamed from: z1.kd */
/* loaded from: classes3.dex */
public final class AI01392xDecoder extends AI01decoder {

    /* renamed from: d */
    private static final int f22219d = 8;

    /* renamed from: e */
    private static final int f22220e = 2;

    public AI01392xDecoder(BitArray huVar) {
        super(huVar);
    }

    @Override // p110z1.AbstractExpandedDecoder
    /* renamed from: a */
    public final String mo2216a() throws NotFoundException, FormatException {
        if (this.f22233b.f21908b >= 48) {
            StringBuilder sb = new StringBuilder();
            m2223b(sb, 8);
            int a = this.f22234c.m2186a(48, 2);
            sb.append("(392");
            sb.append(a);
            sb.append(')');
            sb.append(this.f22234c.m2185a(50, (String) null).f22246a);
            return sb.toString();
        }
        throw NotFoundException.m1647a();
    }
}
