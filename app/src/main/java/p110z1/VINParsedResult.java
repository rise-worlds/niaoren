package p110z1;

/* renamed from: z1.gq */
/* loaded from: classes3.dex */
public final class VINParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String f21798a;

    /* renamed from: b */
    private final String f21799b;

    /* renamed from: c */
    private final String f21800c;

    /* renamed from: d */
    private final String f21801d;

    /* renamed from: e */
    private final String f21802e;

    /* renamed from: f */
    private final String f21803f;

    /* renamed from: g */
    private final int f21804g;

    /* renamed from: h */
    private final char f21805h;

    /* renamed from: i */
    private final String f21806i;

    public VINParsedResult(String str, String str2, String str3, String str4, String str5, String str6, int i, char c, String str7) {
        super(ParsedResultType.f21889l);
        this.f21798a = str;
        this.f21799b = str2;
        this.f21800c = str3;
        this.f21801d = str4;
        this.f21802e = str5;
        this.f21803f = str6;
        this.f21804g = i;
        this.f21805h = c;
        this.f21806i = str7;
    }

    /* renamed from: b */
    private String m2701b() {
        return this.f21798a;
    }

    /* renamed from: c */
    private String m2700c() {
        return this.f21799b;
    }

    /* renamed from: d */
    private String m2699d() {
        return this.f21800c;
    }

    /* renamed from: e */
    private String m2698e() {
        return this.f21801d;
    }

    /* renamed from: f */
    private String m2697f() {
        return this.f21802e;
    }

    /* renamed from: g */
    private String m2696g() {
        return this.f21803f;
    }

    /* renamed from: h */
    private int m2695h() {
        return this.f21804g;
    }

    /* renamed from: i */
    private char m2694i() {
        return this.f21805h;
    }

    /* renamed from: j */
    private String m2693j() {
        return this.f21806i;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(this.f21799b);
        sb.append(' ');
        sb.append(this.f21800c);
        sb.append(' ');
        sb.append(this.f21801d);
        sb.append('\n');
        String str = this.f21802e;
        if (str != null) {
            sb.append(str);
            sb.append(' ');
        }
        sb.append(this.f21804g);
        sb.append(' ');
        sb.append(this.f21805h);
        sb.append(' ');
        sb.append(this.f21806i);
        sb.append('\n');
        return sb.toString();
    }
}
