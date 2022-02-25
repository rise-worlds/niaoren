package p110z1;

import com.tencent.smtt.sdk.WebView;

/* renamed from: z1.ha */
/* loaded from: classes3.dex */
public final class EmailAddressParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String[] f21847a;

    /* renamed from: b */
    private final String[] f21848b;

    /* renamed from: c */
    private final String[] f21849c;

    /* renamed from: d */
    private final String f21850d;

    /* renamed from: e */
    private final String f21851e;

    @Deprecated
    /* renamed from: h */
    private static String m2629h() {
        return WebView.SCHEME_MAILTO;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmailAddressParsedResult(String str) {
        this(new String[]{str}, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmailAddressParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        super(ParsedResultType.f21879b);
        this.f21847a = strArr;
        this.f21848b = strArr2;
        this.f21849c = strArr3;
        this.f21850d = str;
        this.f21851e = str2;
    }

    @Deprecated
    /* renamed from: b */
    private String m2635b() {
        String[] strArr = this.f21847a;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return strArr[0];
    }

    /* renamed from: c */
    private String[] m2634c() {
        return this.f21847a;
    }

    /* renamed from: d */
    private String[] m2633d() {
        return this.f21848b;
    }

    /* renamed from: e */
    private String[] m2632e() {
        return this.f21849c;
    }

    /* renamed from: f */
    private String m2631f() {
        return this.f21850d;
    }

    /* renamed from: g */
    private String m2630g() {
        return this.f21851e;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        StringBuilder sb = new StringBuilder(30);
        m2596a(this.f21847a, sb);
        m2596a(this.f21848b, sb);
        m2596a(this.f21849c, sb);
        m2597a(this.f21850d, sb);
        m2597a(this.f21851e, sb);
        return sb.toString();
    }
}
