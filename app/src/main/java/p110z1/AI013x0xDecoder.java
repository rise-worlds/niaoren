package p110z1;

/* renamed from: z1.kg */
/* loaded from: classes3.dex */
abstract class AI013x0xDecoder extends AI01weightDecoder {

    /* renamed from: d */
    private static final int f22229d = 5;

    /* renamed from: e */
    private static final int f22230e = 15;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI013x0xDecoder(BitArray huVar) {
        super(huVar);
    }

    @Override // p110z1.AbstractExpandedDecoder
    /* renamed from: a */
    public final String mo2216a() throws NotFoundException {
        if (this.f22233b.f21908b == 60) {
            StringBuilder sb = new StringBuilder();
            m2223b(sb, 5);
            m2220b(sb, 45, 15);
            return sb.toString();
        }
        throw NotFoundException.m1647a();
    }
}
