package p110z1;

/* renamed from: z1.kc */
/* loaded from: classes3.dex */
public final class AI01320xDecoder extends AI013x0xDecoder {
    @Override // p110z1.AI01weightDecoder
    /* renamed from: a */
    protected final int mo2222a(int i) {
        return i < 10000 ? i : i - 10000;
    }

    public AI01320xDecoder(BitArray huVar) {
        super(huVar);
    }

    @Override // p110z1.AI01weightDecoder
    /* renamed from: a */
    protected final void mo2221a(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
