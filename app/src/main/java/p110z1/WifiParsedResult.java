package p110z1;

/* renamed from: z1.gs */
/* loaded from: classes3.dex */
public final class WifiParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String f21809a;

    /* renamed from: b */
    private final String f21810b;

    /* renamed from: c */
    private final String f21811c;

    /* renamed from: d */
    private final boolean f21812d;

    /* renamed from: e */
    private final String f21813e;

    /* renamed from: f */
    private final String f21814f;

    /* renamed from: g */
    private final String f21815g;

    /* renamed from: h */
    private final String f21816h;

    private WifiParsedResult(String str, String str2, String str3) {
        this(str, str2, str3, (byte) 0);
    }

    private WifiParsedResult(String str, String str2, String str3, byte b) {
        this(str, str2, str3, false, null, null, null, null);
    }

    public WifiParsedResult(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7) {
        super(ParsedResultType.f21887j);
        this.f21809a = str2;
        this.f21810b = str;
        this.f21811c = str3;
        this.f21812d = z;
        this.f21813e = str4;
        this.f21814f = str5;
        this.f21815g = str6;
        this.f21816h = str7;
    }

    /* renamed from: b */
    private String m2685b() {
        return this.f21809a;
    }

    /* renamed from: c */
    private String m2684c() {
        return this.f21810b;
    }

    /* renamed from: d */
    private String m2683d() {
        return this.f21811c;
    }

    /* renamed from: e */
    private boolean m2682e() {
        return this.f21812d;
    }

    /* renamed from: f */
    private String m2681f() {
        return this.f21813e;
    }

    /* renamed from: g */
    private String m2680g() {
        return this.f21814f;
    }

    /* renamed from: h */
    private String m2679h() {
        return this.f21815g;
    }

    /* renamed from: i */
    private String m2678i() {
        return this.f21816h;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(80);
        m2597a(this.f21809a, sb);
        m2597a(this.f21810b, sb);
        m2597a(this.f21811c, sb);
        m2597a(Boolean.toString(this.f21812d), sb);
        return sb.toString();
    }
}
