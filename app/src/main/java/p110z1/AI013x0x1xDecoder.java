package p110z1;

/* renamed from: z1.kf */
/* loaded from: classes3.dex */
public final class AI013x0x1xDecoder extends AI01weightDecoder {

    /* renamed from: d */
    private static final int f22224d = 8;

    /* renamed from: e */
    private static final int f22225e = 20;

    /* renamed from: f */
    private static final int f22226f = 16;

    /* renamed from: g */
    private final String f22227g;

    /* renamed from: h */
    private final String f22228h;

    public AI013x0x1xDecoder(BitArray huVar, String str, String str2) {
        super(huVar);
        this.f22227g = str2;
        this.f22228h = str;
    }

    @Override // p110z1.AI01weightDecoder
    /* renamed from: a */
    protected final void mo2221a(StringBuilder sb, int i) {
        sb.append('(');
        sb.append(this.f22228h);
        sb.append(i / 100000);
        sb.append(')');
    }

    @Override // p110z1.AI01weightDecoder
    /* renamed from: a */
    protected final int mo2222a(int i) {
        return i % 100000;
    }

    @Override // p110z1.AbstractExpandedDecoder
    /* renamed from: a */
    public final String mo2216a() throws NotFoundException {
        if (this.f22233b.f21908b == 84) {
            StringBuilder sb = new StringBuilder();
            m2223b(sb, 8);
            m2220b(sb, 48, 20);
            int a = this.f22234c.m2186a(68, 16);
            if (a != 38400) {
                sb.append('(');
                sb.append(this.f22227g);
                sb.append(')');
                int i = a % 32;
                int i2 = a / 32;
                int i3 = (i2 % 12) + 1;
                int i4 = i2 / 12;
                if (i4 / 10 == 0) {
                    sb.append('0');
                }
                sb.append(i4);
                if (i3 / 10 == 0) {
                    sb.append('0');
                }
                sb.append(i3);
                if (i / 10 == 0) {
                    sb.append('0');
                }
                sb.append(i);
            }
            return sb.toString();
        }
        throw NotFoundException.m1647a();
    }

    /* renamed from: a */
    private void m2226a(StringBuilder sb) {
        int a = this.f22234c.m2186a(68, 16);
        if (a != 38400) {
            sb.append('(');
            sb.append(this.f22227g);
            sb.append(')');
            int i = a % 32;
            int i2 = a / 32;
            int i3 = (i2 % 12) + 1;
            int i4 = i2 / 12;
            if (i4 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i4);
            if (i3 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i3);
            if (i / 10 == 0) {
                sb.append('0');
            }
            sb.append(i);
        }
    }
}
