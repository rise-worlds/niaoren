package p110z1;

import java.util.Map;

/* renamed from: z1.hd */
/* loaded from: classes3.dex */
public final class ExpandedProductParsedResult extends ParsedResult {

    /* renamed from: a */
    public static final String f21854a = "KG";

    /* renamed from: b */
    public static final String f21855b = "LB";

    /* renamed from: c */
    private final String f21856c;

    /* renamed from: d */
    private final String f21857d;

    /* renamed from: e */
    private final String f21858e;

    /* renamed from: f */
    private final String f21859f;

    /* renamed from: g */
    private final String f21860g;

    /* renamed from: h */
    private final String f21861h;

    /* renamed from: i */
    private final String f21862i;

    /* renamed from: j */
    private final String f21863j;

    /* renamed from: k */
    private final String f21864k;

    /* renamed from: l */
    private final String f21865l;

    /* renamed from: m */
    private final String f21866m;

    /* renamed from: n */
    private final String f21867n;

    /* renamed from: o */
    private final String f21868o;

    /* renamed from: p */
    private final String f21869p;

    /* renamed from: q */
    private final Map<String, String> f21870q;

    public ExpandedProductParsedResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Map<String, String> map) {
        super(ParsedResultType.f21880c);
        this.f21856c = str;
        this.f21857d = str2;
        this.f21858e = str3;
        this.f21859f = str4;
        this.f21860g = str5;
        this.f21861h = str6;
        this.f21862i = str7;
        this.f21863j = str8;
        this.f21864k = str9;
        this.f21865l = str10;
        this.f21866m = str11;
        this.f21867n = str12;
        this.f21868o = str13;
        this.f21869p = str14;
        this.f21870q = map;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ExpandedProductParsedResult)) {
            return false;
        }
        ExpandedProductParsedResult hdVar = (ExpandedProductParsedResult) obj;
        return m2624a(this.f21857d, hdVar.f21857d) && m2624a(this.f21858e, hdVar.f21858e) && m2624a(this.f21859f, hdVar.f21859f) && m2624a(this.f21860g, hdVar.f21860g) && m2624a(this.f21862i, hdVar.f21862i) && m2624a(this.f21863j, hdVar.f21863j) && m2624a(this.f21864k, hdVar.f21864k) && m2624a(this.f21865l, hdVar.f21865l) && m2624a(this.f21866m, hdVar.f21866m) && m2624a(this.f21867n, hdVar.f21867n) && m2624a(this.f21868o, hdVar.f21868o) && m2624a(this.f21869p, hdVar.f21869p) && m2624a(this.f21870q, hdVar.f21870q);
    }

    /* renamed from: a */
    private static boolean m2624a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public final int hashCode() {
        return ((((((((((((m2625a(this.f21857d) ^ 0) ^ m2625a(this.f21858e)) ^ m2625a(this.f21859f)) ^ m2625a(this.f21860g)) ^ m2625a(this.f21862i)) ^ m2625a(this.f21863j)) ^ m2625a(this.f21864k)) ^ m2625a(this.f21865l)) ^ m2625a(this.f21866m)) ^ m2625a(this.f21867n)) ^ m2625a(this.f21868o)) ^ m2625a(this.f21869p)) ^ m2625a(this.f21870q);
    }

    /* renamed from: a */
    private static int m2625a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: b */
    private String m2623b() {
        return this.f21856c;
    }

    /* renamed from: c */
    private String m2622c() {
        return this.f21857d;
    }

    /* renamed from: d */
    private String m2621d() {
        return this.f21858e;
    }

    /* renamed from: e */
    private String m2620e() {
        return this.f21859f;
    }

    /* renamed from: f */
    private String m2619f() {
        return this.f21860g;
    }

    /* renamed from: g */
    private String m2618g() {
        return this.f21861h;
    }

    /* renamed from: h */
    private String m2617h() {
        return this.f21862i;
    }

    /* renamed from: i */
    private String m2616i() {
        return this.f21863j;
    }

    /* renamed from: j */
    private String m2615j() {
        return this.f21864k;
    }

    /* renamed from: k */
    private String m2614k() {
        return this.f21865l;
    }

    /* renamed from: l */
    private String m2613l() {
        return this.f21866m;
    }

    /* renamed from: m */
    private String m2612m() {
        return this.f21867n;
    }

    /* renamed from: n */
    private String m2611n() {
        return this.f21868o;
    }

    /* renamed from: o */
    private String m2610o() {
        return this.f21869p;
    }

    /* renamed from: p */
    private Map<String, String> m2609p() {
        return this.f21870q;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        return String.valueOf(this.f21856c);
    }
}
