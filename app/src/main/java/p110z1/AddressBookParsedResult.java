package p110z1;

/* renamed from: z1.gw */
/* loaded from: classes3.dex */
public final class AddressBookParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String[] f21817a;

    /* renamed from: b */
    private final String[] f21818b;

    /* renamed from: c */
    private final String f21819c;

    /* renamed from: d */
    private final String[] f21820d;

    /* renamed from: e */
    private final String[] f21821e;

    /* renamed from: f */
    private final String[] f21822f;

    /* renamed from: g */
    private final String[] f21823g;

    /* renamed from: h */
    private final String f21824h;

    /* renamed from: i */
    private final String f21825i;

    /* renamed from: j */
    private final String[] f21826j;

    /* renamed from: k */
    private final String[] f21827k;

    /* renamed from: l */
    private final String f21828l;

    /* renamed from: m */
    private final String f21829m;

    /* renamed from: n */
    private final String f21830n;

    /* renamed from: o */
    private final String[] f21831o;

    /* renamed from: p */
    private final String[] f21832p;

    private AddressBookParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7) {
        this(strArr, null, null, strArr2, strArr3, strArr4, strArr5, null, null, strArr6, strArr7, null, null, null, null, null);
    }

    public AddressBookParsedResult(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String[] strArr7, String[] strArr8, String str4, String str5, String str6, String[] strArr9, String[] strArr10) {
        super(ParsedResultType.f21878a);
        if (strArr3 != null && strArr4 != null && strArr3.length != strArr4.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        } else if (strArr5 != null && strArr6 != null && strArr5.length != strArr6.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        } else if (strArr7 == null || strArr8 == null || strArr7.length == strArr8.length) {
            this.f21817a = strArr;
            this.f21818b = strArr2;
            this.f21819c = str;
            this.f21820d = strArr3;
            this.f21821e = strArr4;
            this.f21822f = strArr5;
            this.f21823g = strArr6;
            this.f21824h = str2;
            this.f21825i = str3;
            this.f21826j = strArr7;
            this.f21827k = strArr8;
            this.f21828l = str4;
            this.f21829m = str5;
            this.f21830n = str6;
            this.f21831o = strArr9;
            this.f21832p = strArr10;
        } else {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
    }

    /* renamed from: b */
    private String[] m2672b() {
        return this.f21817a;
    }

    /* renamed from: c */
    private String[] m2671c() {
        return this.f21818b;
    }

    /* renamed from: d */
    private String m2670d() {
        return this.f21819c;
    }

    /* renamed from: e */
    private String[] m2669e() {
        return this.f21820d;
    }

    /* renamed from: f */
    private String[] m2668f() {
        return this.f21821e;
    }

    /* renamed from: g */
    private String[] m2667g() {
        return this.f21822f;
    }

    /* renamed from: h */
    private String[] m2666h() {
        return this.f21823g;
    }

    /* renamed from: i */
    private String m2665i() {
        return this.f21824h;
    }

    /* renamed from: j */
    private String m2664j() {
        return this.f21825i;
    }

    /* renamed from: k */
    private String[] m2663k() {
        return this.f21826j;
    }

    /* renamed from: l */
    private String[] m2662l() {
        return this.f21827k;
    }

    /* renamed from: m */
    private String m2661m() {
        return this.f21830n;
    }

    /* renamed from: n */
    private String m2660n() {
        return this.f21828l;
    }

    /* renamed from: o */
    private String[] m2659o() {
        return this.f21831o;
    }

    /* renamed from: p */
    private String m2658p() {
        return this.f21829m;
    }

    /* renamed from: q */
    private String[] m2657q() {
        return this.f21832p;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(100);
        m2596a(this.f21817a, sb);
        m2596a(this.f21818b, sb);
        m2597a(this.f21819c, sb);
        m2597a(this.f21830n, sb);
        m2597a(this.f21828l, sb);
        m2596a(this.f21826j, sb);
        m2596a(this.f21820d, sb);
        m2596a(this.f21822f, sb);
        m2597a(this.f21824h, sb);
        m2596a(this.f21831o, sb);
        m2597a(this.f21829m, sb);
        m2596a(this.f21832p, sb);
        m2597a(this.f21825i, sb);
        return sb.toString();
    }
}
