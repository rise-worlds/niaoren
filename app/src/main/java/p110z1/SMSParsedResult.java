package p110z1;

/* renamed from: z1.hp */
/* loaded from: classes3.dex */
public final class SMSParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String[] f21898a;

    /* renamed from: b */
    private final String[] f21899b;

    /* renamed from: c */
    private final String f21900c;

    /* renamed from: d */
    private final String f21901d;

    public SMSParsedResult(String str, String str2) {
        super(ParsedResultType.f21885h);
        this.f21898a = new String[]{str};
        this.f21899b = new String[]{null};
        this.f21900c = null;
        this.f21901d = str2;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.f21885h);
        this.f21898a = strArr;
        this.f21899b = strArr2;
        this.f21900c = str;
        this.f21901d = str2;
    }

    /* renamed from: b */
    private String m2573b() {
        StringBuilder sb = new StringBuilder();
        sb.append("sms:");
        boolean z = false;
        boolean z2 = true;
        for (int i = 0; i < this.f21898a.length; i++) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(',');
            }
            sb.append(this.f21898a[i]);
            String[] strArr = this.f21899b;
            if (!(strArr == null || strArr[i] == null)) {
                sb.append(";via=");
                sb.append(this.f21899b[i]);
            }
        }
        boolean z3 = this.f21901d != null;
        if (this.f21900c != null) {
            z = true;
        }
        if (z3 || z) {
            sb.append('?');
            if (z3) {
                sb.append("body=");
                sb.append(this.f21901d);
            }
            if (z) {
                if (z3) {
                    sb.append(Typography.f21051c);
                }
                sb.append("subject=");
                sb.append(this.f21900c);
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    private String[] m2572c() {
        return this.f21898a;
    }

    /* renamed from: d */
    private String[] m2571d() {
        return this.f21899b;
    }

    /* renamed from: e */
    private String m2570e() {
        return this.f21900c;
    }

    /* renamed from: f */
    private String m2569f() {
        return this.f21901d;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(100);
        m2596a(this.f21898a, sb);
        m2597a(this.f21900c, sb);
        m2597a(this.f21901d, sb);
        return sb.toString();
    }
}
