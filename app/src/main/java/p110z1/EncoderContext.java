package p110z1;

import java.nio.charset.StandardCharsets;

/* renamed from: z1.je */
/* loaded from: classes3.dex */
final class EncoderContext {

    /* renamed from: a */
    final String f22078a;

    /* renamed from: b */
    SymbolShapeHint f22079b;

    /* renamed from: c */
    C5384jq f22080c;

    /* renamed from: d */
    C5384jq f22081d;

    /* renamed from: e */
    final StringBuilder f22082e;

    /* renamed from: f */
    int f22083f;

    /* renamed from: g */
    int f22084g;

    /* renamed from: h */
    SymbolInfo f22085h;

    /* renamed from: i */
    int f22086i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EncoderContext(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            char c = (char) (bytes[i] & 255);
            if (c != '?' || str.charAt(i) == '?') {
                sb.append(c);
            } else {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
        }
        this.f22078a = sb.toString();
        this.f22079b = SymbolShapeHint.FORCE_NONE;
        this.f22082e = new StringBuilder(str.length());
        this.f22084g = -1;
    }

    /* renamed from: a */
    private void m2328a(SymbolShapeHint jiVar) {
        this.f22079b = jiVar;
    }

    /* renamed from: a */
    private void m2327a(C5384jq jqVar, C5384jq jqVar2) {
        this.f22080c = jqVar;
        this.f22081d = jqVar2;
    }

    /* renamed from: e */
    private String m2322e() {
        return this.f22078a;
    }

    /* renamed from: f */
    private void m2321f() {
        this.f22086i = 2;
    }

    /* renamed from: a */
    public final char m2332a() {
        return this.f22078a.charAt(this.f22083f);
    }

    /* renamed from: g */
    private char m2320g() {
        return this.f22078a.charAt(this.f22083f);
    }

    /* renamed from: h */
    private StringBuilder m2319h() {
        return this.f22082e;
    }

    /* renamed from: a */
    public final void m2329a(String str) {
        this.f22082e.append(str);
    }

    /* renamed from: a */
    public final void m2331a(char c) {
        this.f22082e.append(c);
    }

    /* renamed from: i */
    private int m2318i() {
        return this.f22082e.length();
    }

    /* renamed from: j */
    private int m2317j() {
        return this.f22084g;
    }

    /* renamed from: b */
    private void m2325b(int i) {
        this.f22084g = i;
    }

    /* renamed from: k */
    private void m2316k() {
        this.f22084g = -1;
    }

    /* renamed from: b */
    public final boolean m2326b() {
        return this.f22083f < m2315l();
    }

    /* renamed from: l */
    private int m2315l() {
        return this.f22078a.length() - this.f22086i;
    }

    /* renamed from: c */
    public final int m2324c() {
        return m2315l() - this.f22083f;
    }

    /* renamed from: m */
    private SymbolInfo m2314m() {
        return this.f22085h;
    }

    /* renamed from: a */
    public final void m2330a(int i) {
        SymbolInfo jhVar = this.f22085h;
        if (jhVar == null || i > jhVar.f22114b) {
            this.f22085h = SymbolInfo.m2290a(i, this.f22079b, this.f22080c, this.f22081d, true);
        }
    }

    /* renamed from: n */
    private void m2313n() {
        this.f22085h = null;
    }

    /* renamed from: d */
    public final void m2323d() {
        m2330a(this.f22082e.length());
    }
}
