package p110z1;

/* renamed from: z1.kh */
/* loaded from: classes3.dex */
public final class AI01AndOtherAIs extends AI01decoder {

    /* renamed from: d */
    private static final int f22231d = 4;

    public AI01AndOtherAIs(BitArray huVar) {
        super(huVar);
    }

    @Override // p110z1.AbstractExpandedDecoder
    /* renamed from: a */
    public final String mo2216a() throws NotFoundException, FormatException {
        StringBuilder sb = new StringBuilder();
        sb.append("(01)");
        int length = sb.length();
        sb.append(this.f22234c.m2186a(4, 4));
        m2224a(sb, 8, length);
        return this.f22234c.m2184a(sb, 48);
    }
}
