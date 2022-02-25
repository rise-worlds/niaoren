package p110z1;

/* renamed from: z1.hs */
/* loaded from: classes3.dex */
public final class TelParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String f21902a;

    /* renamed from: b */
    private final String f21903b;

    /* renamed from: c */
    private final String f21904c = null;

    public TelParsedResult(String str, String str2) {
        super(ParsedResultType.f21884g);
        this.f21902a = str;
        this.f21903b = str2;
    }

    /* renamed from: b */
    private String m2564b() {
        return this.f21902a;
    }

    /* renamed from: c */
    private String m2563c() {
        return this.f21903b;
    }

    /* renamed from: d */
    private String m2562d() {
        return this.f21904c;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(20);
        m2597a(this.f21902a, sb);
        m2597a(this.f21904c, sb);
        return sb.toString();
    }
}
