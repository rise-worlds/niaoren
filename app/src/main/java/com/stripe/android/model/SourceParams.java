package com.stripe.android.model;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.stripe.android.model.k */
/* loaded from: classes2.dex */
public class SourceParams {

    /* renamed from: A */
    static final String f12204A = "statement_descriptor";

    /* renamed from: B */
    static final String f12205B = "visa_checkout";

    /* renamed from: C */
    static final String f12206C = "callid";

    /* renamed from: D */
    static final String f12207D = "masterpass";

    /* renamed from: E */
    static final String f12208E = "transaction_id";

    /* renamed from: F */
    static final String f12209F = "cart_id";

    /* renamed from: a */
    static final String f12210a = "amount";

    /* renamed from: b */
    static final String f12211b = "currency";

    /* renamed from: c */
    static final String f12212c = "metadata";

    /* renamed from: d */
    static final String f12213d = "owner";

    /* renamed from: e */
    static final String f12214e = "redirect";

    /* renamed from: f */
    static final String f12215f = "type";

    /* renamed from: g */
    static final String f12216g = "token";

    /* renamed from: h */
    static final String f12217h = "usage";

    /* renamed from: i */
    static final String f12218i = "client_secret";

    /* renamed from: j */
    static final String f12219j = "address";

    /* renamed from: k */
    static final String f12220k = "bank";

    /* renamed from: l */
    static final String f12221l = "card";

    /* renamed from: m */
    static final String f12222m = "city";

    /* renamed from: n */
    static final String f12223n = "country";

    /* renamed from: o */
    static final String f12224o = "cvc";

    /* renamed from: p */
    static final String f12225p = "email";

    /* renamed from: q */
    static final String f12226q = "exp_month";

    /* renamed from: r */
    static final String f12227r = "exp_year";

    /* renamed from: s */
    static final String f12228s = "iban";

    /* renamed from: t */
    static final String f12229t = "line1";

    /* renamed from: u */
    static final String f12230u = "line2";

    /* renamed from: v */
    static final String f12231v = "name";

    /* renamed from: w */
    static final String f12232w = "number";

    /* renamed from: x */
    static final String f12233x = "postal_code";

    /* renamed from: y */
    static final String f12234y = "return_url";

    /* renamed from: z */
    static final String f12235z = "state";
    @IntRange(from = 0)

    /* renamed from: G */
    private Long f12236G;

    /* renamed from: H */
    private Map<String, Object> f12237H;

    /* renamed from: I */
    private String f12238I;
    @Nullable

    /* renamed from: J */
    private String f12239J;

    /* renamed from: K */
    private Map<String, Object> f12240K;

    /* renamed from: L */
    private Map<String, String> f12241L;

    /* renamed from: M */
    private Map<String, Object> f12242M;

    /* renamed from: N */
    private Map<String, Object> f12243N;

    /* renamed from: O */
    private String f12244O;
    @Nullable

    /* renamed from: P */
    private String f12245P;

    /* renamed from: Q */
    private String f12246Q;

    private SourceParams() {
    }

    /* renamed from: a */
    public static SourceParams m17699a(@IntRange(from = 0) long j, @NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4) {
        SourceParams c = new SourceParams().m17701a(j).m17674e(C2395g.f12116j).m17681c(str).m17680c(m17696a(f12234y, (Object) str4));
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.m17470a(hashMap);
        if (hashMap.keySet().size() > 0) {
            c.m17685b(hashMap);
        }
        return c;
    }

    /* renamed from: a */
    public static SourceParams m17693a(@NonNull String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        SourceParams h = new SourceParams().m17674e(C2395g.f12108b).m17681c(str).m17680c(m17696a(f12234y, (Object) str4)).m17667h(C2395g.f12124r);
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.m17470a(hashMap);
        if (hashMap.keySet().size() > 0) {
            h.m17685b(hashMap);
        }
        return h;
    }

    @NonNull
    /* renamed from: b */
    public static SourceParams m17687b(@IntRange(from = 0) long j, @NonNull String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12108b).m17681c(str).m17701a(j).m17680c(m17696a(f12234y, (Object) str4));
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.m17470a(hashMap);
        if (hashMap.keySet().size() > 0) {
            c.m17685b(hashMap);
        }
        return c;
    }

    @NonNull
    /* renamed from: a */
    public static SourceParams m17700a(@IntRange(from = 0) long j, @NonNull String str, @NonNull String str2, @Nullable String str3) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12115i).m17681c("eur").m17701a(j).m17685b(m17696a("name", (Object) str)).m17680c(m17696a(f12234y, (Object) str2));
        if (str3 != null) {
            c.m17690a(m17696a(f12204A, (Object) str3));
        }
        return c;
    }

    @NonNull
    /* renamed from: a */
    public static SourceParams m17702a() {
        return new SourceParams();
    }

    @NonNull
    /* renamed from: a */
    public static SourceParams m17698a(@NonNull Card cVar) {
        SourceParams e = new SourceParams().m17674e("card");
        HashMap hashMap = new HashMap();
        hashMap.put(f12232w, cVar.m17886h());
        hashMap.put("exp_month", cVar.m17880k());
        hashMap.put("exp_year", cVar.m17878l());
        hashMap.put(f12224o, cVar.m17882j());
        StripeNetworkUtils.m17470a(hashMap);
        e.m17690a(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(f12229t, cVar.m17874n());
        hashMap2.put(f12230u, cVar.m17872o());
        hashMap2.put("city", cVar.m17870p());
        hashMap2.put("country", cVar.m17867s());
        hashMap2.put("state", cVar.m17868r());
        hashMap2.put("postal_code", cVar.m17869q());
        StripeNetworkUtils.m17470a(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("name", cVar.m17876m());
        if (hashMap2.keySet().size() > 0) {
            hashMap3.put(f12219j, hashMap2);
        }
        StripeNetworkUtils.m17470a(hashMap3);
        if (hashMap3.keySet().size() > 0) {
            e.m17685b(hashMap3);
        }
        return e;
    }

    @NonNull
    /* renamed from: b */
    public static SourceParams m17688b(@IntRange(from = 0) long j, @NonNull String str, @NonNull String str2, @Nullable String str3) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12111e).m17681c("eur").m17701a(j).m17685b(m17696a("name", (Object) str)).m17680c(m17696a(f12234y, (Object) str2));
        if (str3 != null) {
            c.m17690a(m17696a(f12204A, (Object) str3));
        }
        return c;
    }

    @NonNull
    /* renamed from: c */
    public static SourceParams m17682c(@IntRange(from = 0) long j, @NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12113g).m17681c("eur").m17701a(j).m17685b(m17696a("name", (Object) str)).m17680c(m17696a(f12234y, (Object) str2));
        if (!(str3 == null || str4 == null)) {
            c.m17690a(m17695a(f12204A, (Object) str3, f12220k, (Object) str4));
        }
        return c;
    }

    @NonNull
    /* renamed from: a */
    public static SourceParams m17692a(@NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull String str4, @NonNull String str5, @Size(2) @NonNull String str6) {
        return m17691a(str, str2, null, str3, str4, str5, str6);
    }

    @NonNull
    /* renamed from: a */
    public static SourceParams m17691a(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @NonNull String str5, @NonNull String str6, @Size(2) @NonNull String str7) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12112f).m17681c("eur");
        HashMap hashMap = new HashMap();
        hashMap.put(f12229t, str4);
        hashMap.put("city", str5);
        hashMap.put("postal_code", str6);
        hashMap.put("country", str7);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("name", str);
        hashMap2.put("email", str3);
        hashMap2.put(f12219j, hashMap);
        c.m17685b(hashMap2).m17690a(m17696a(f12228s, (Object) str2));
        return c;
    }

    @NonNull
    /* renamed from: c */
    public static SourceParams m17683c(@IntRange(from = 0) long j, @NonNull String str, @Size(2) @NonNull String str2, @Nullable String str3) {
        SourceParams c = new SourceParams().m17674e(C2395g.f12114h).m17681c("eur").m17701a(j).m17680c(m17696a(f12234y, (Object) str));
        Map<String, Object> a = m17696a("country", (Object) str2);
        if (str3 != null) {
            a.put(f12204A, str3);
        }
        c.m17690a(a);
        return c;
    }

    @NonNull
    /* renamed from: d */
    public static SourceParams m17678d(@IntRange(from = 0) long j, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        SourceParams c = new SourceParams().m17674e("three_d_secure").m17681c(str).m17701a(j).m17680c(m17696a(f12234y, (Object) str2));
        c.m17690a(m17696a("card", (Object) str3));
        return c;
    }

    /* renamed from: a */
    public static SourceParams m17697a(@NonNull String str) {
        SourceParams e = new SourceParams().m17674e("card");
        e.m17690a(m17696a(f12205B, m17696a(f12206C, (Object) str)));
        return e;
    }

    /* renamed from: a */
    public static SourceParams m17694a(@NonNull String str, @NonNull String str2) {
        SourceParams e = new SourceParams().m17674e("card");
        Map<String, Object> a = m17696a(f12208E, (Object) str);
        a.put(f12209F, str2);
        e.m17690a(m17696a(f12207D, a));
        return e;
    }

    @NonNull
    /* renamed from: b */
    public static Map<String, Object> m17686b(@Size(min = 1) @NonNull String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(f12218i, str);
        return hashMap;
    }

    @Nullable
    /* renamed from: b */
    public Long m17689b() {
        return this.f12236G;
    }

    @Nullable
    /* renamed from: c */
    public Map<String, Object> m17684c() {
        return this.f12237H;
    }

    @Nullable
    /* renamed from: d */
    public String m17679d() {
        return this.f12238I;
    }

    @Nullable
    /* renamed from: e */
    public Map<String, Object> m17675e() {
        return this.f12240K;
    }

    @Nullable
    /* renamed from: f */
    public Map<String, Object> m17672f() {
        return this.f12242M;
    }

    @NonNull
    /* renamed from: g */
    public String m17670g() {
        return this.f12246Q;
    }

    @Nullable
    /* renamed from: h */
    public String m17668h() {
        return this.f12239J;
    }

    @Nullable
    /* renamed from: i */
    public String m17666i() {
        return this.f12245P;
    }

    /* renamed from: j */
    public Map<String, String> m17665j() {
        return this.f12241L;
    }

    /* renamed from: a */
    public SourceParams m17701a(long j) {
        this.f12236G = Long.valueOf(j);
        return this;
    }

    /* renamed from: a */
    public SourceParams m17690a(@NonNull Map<String, Object> map) {
        this.f12237H = map;
        return this;
    }

    /* renamed from: c */
    public SourceParams m17681c(String str) {
        this.f12238I = str;
        return this;
    }

    /* renamed from: b */
    public SourceParams m17685b(Map<String, Object> map) {
        this.f12240K = map;
        return this;
    }

    /* renamed from: c */
    public SourceParams m17680c(Map<String, Object> map) {
        this.f12242M = map;
        return this;
    }

    /* renamed from: d */
    public SourceParams m17676d(Map<String, Object> map) {
        this.f12243N = map;
        return this;
    }

    /* renamed from: d */
    public SourceParams m17677d(@Size(min = 1) @NonNull String str) {
        Map<String, Object> map = this.f12242M;
        if (map == null) {
            m17680c(m17696a(f12234y, (Object) str));
        } else {
            map.put(f12234y, str);
        }
        return this;
    }

    /* renamed from: e */
    public SourceParams m17674e(String str) {
        this.f12246Q = str;
        this.f12239J = str;
        return this;
    }

    /* renamed from: f */
    public SourceParams m17671f(@NonNull String str) {
        this.f12246Q = C2395g.m17765k(str);
        if (this.f12246Q == null) {
            this.f12246Q = "unknown";
        }
        this.f12239J = str;
        return this;
    }

    /* renamed from: e */
    public SourceParams m17673e(@NonNull Map<String, String> map) {
        this.f12241L = map;
        return this;
    }

    /* renamed from: g */
    public SourceParams m17669g(@NonNull String str) {
        this.f12244O = str;
        return this;
    }

    /* renamed from: h */
    public SourceParams m17667h(@NonNull String str) {
        this.f12245P = str;
        return this;
    }

    @NonNull
    /* renamed from: k */
    public Map<String, Object> m17664k() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", this.f12239J);
        hashMap.put(this.f12239J, this.f12237H);
        hashMap.put(f12210a, this.f12236G);
        hashMap.put(f12211b, this.f12238I);
        hashMap.put(f12213d, this.f12240K);
        hashMap.put("redirect", this.f12242M);
        hashMap.put(f12212c, this.f12241L);
        hashMap.put(f12216g, this.f12244O);
        hashMap.put(f12217h, this.f12245P);
        Map<String, Object> map = this.f12243N;
        if (map != null) {
            hashMap.putAll(map);
        }
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @NonNull
    /* renamed from: a */
    private static Map<String, Object> m17696a(@NonNull String str, @NonNull Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, obj);
        return hashMap;
    }

    @NonNull
    /* renamed from: a */
    private static Map<String, Object> m17695a(@NonNull String str, @NonNull Object obj, @NonNull String str2, @NonNull Object obj2) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, obj);
        hashMap.put(str2, obj2);
        return hashMap;
    }
}
