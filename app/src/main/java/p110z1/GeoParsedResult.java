package p110z1;

/* renamed from: z1.hf */
/* loaded from: classes3.dex */
public final class GeoParsedResult extends ParsedResult {

    /* renamed from: a */
    private final double f21871a;

    /* renamed from: b */
    private final double f21872b;

    /* renamed from: c */
    private final double f21873c;

    /* renamed from: d */
    private final String f21874d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoParsedResult(double d, double d2, double d3, String str) {
        super(ParsedResultType.f21883f);
        this.f21871a = d;
        this.f21872b = d2;
        this.f21873c = d3;
        this.f21874d = str;
    }

    /* renamed from: b */
    private String m2605b() {
        StringBuilder sb = new StringBuilder();
        sb.append("geo:");
        sb.append(this.f21871a);
        sb.append(',');
        sb.append(this.f21872b);
        if (this.f21873c > 0.0d) {
            sb.append(',');
            sb.append(this.f21873c);
        }
        if (this.f21874d != null) {
            sb.append('?');
            sb.append(this.f21874d);
        }
        return sb.toString();
    }

    /* renamed from: c */
    private double m2604c() {
        return this.f21871a;
    }

    /* renamed from: d */
    private double m2603d() {
        return this.f21872b;
    }

    /* renamed from: e */
    private double m2602e() {
        return this.f21873c;
    }

    /* renamed from: f */
    private String m2601f() {
        return this.f21874d;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.f21871a);
        sb.append(", ");
        sb.append(this.f21872b);
        if (this.f21873c > 0.0d) {
            sb.append(", ");
            sb.append(this.f21873c);
            sb.append('m');
        }
        if (this.f21874d != null) {
            sb.append(" (");
            sb.append(this.f21874d);
            sb.append(')');
        }
        return sb.toString();
    }
}
