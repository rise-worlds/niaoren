package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.stripe.android.StripeNetworkUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Source.java */
/* renamed from: com.stripe.android.model.g */
/* loaded from: classes2.dex */
public class C2395g extends StripeJsonModel implements StripePaymentSource {

    /* renamed from: A */
    static final String f12092A = "object";

    /* renamed from: B */
    static final String f12093B = "amount";

    /* renamed from: C */
    static final String f12094C = "client_secret";

    /* renamed from: D */
    static final String f12095D = "code_verification";

    /* renamed from: E */
    static final String f12096E = "created";

    /* renamed from: F */
    static final String f12097F = "currency";

    /* renamed from: G */
    static final String f12098G = "flow";

    /* renamed from: H */
    static final String f12099H = "livemode";

    /* renamed from: I */
    static final String f12100I = "metadata";

    /* renamed from: J */
    static final String f12101J = "owner";

    /* renamed from: K */
    static final String f12102K = "receiver";

    /* renamed from: L */
    static final String f12103L = "redirect";

    /* renamed from: M */
    static final String f12104M = "status";

    /* renamed from: N */
    static final String f12105N = "type";

    /* renamed from: O */
    static final String f12106O = "usage";

    /* renamed from: a */
    static final String f12107a = "source";

    /* renamed from: b */
    public static final String f12108b = "alipay";

    /* renamed from: c */
    public static final String f12109c = "card";

    /* renamed from: d */
    public static final String f12110d = "three_d_secure";

    /* renamed from: e */
    public static final String f12111e = "giropay";

    /* renamed from: f */
    public static final String f12112f = "sepa_debit";

    /* renamed from: g */
    public static final String f12113g = "ideal";

    /* renamed from: h */
    public static final String f12114h = "sofort";

    /* renamed from: i */
    public static final String f12115i = "bancontact";

    /* renamed from: j */
    public static final String f12116j = "p24";

    /* renamed from: k */
    public static final String f12117k = "unknown";

    /* renamed from: l */
    public static final Set<String> f12118l = new HashSet();

    /* renamed from: m */
    public static final String f12119m = "pending";

    /* renamed from: n */
    public static final String f12120n = "chargeable";

    /* renamed from: o */
    public static final String f12121o = "consumed";

    /* renamed from: p */
    public static final String f12122p = "canceled";

    /* renamed from: q */
    public static final String f12123q = "failed";

    /* renamed from: r */
    public static final String f12124r = "reusable";

    /* renamed from: s */
    public static final String f12125s = "single_use";

    /* renamed from: t */
    public static final String f12126t = "redirect";

    /* renamed from: u */
    public static final String f12127u = "receiver";

    /* renamed from: v */
    public static final String f12128v = "code_verification";

    /* renamed from: w */
    public static final String f12129w = "none";

    /* renamed from: x */
    static final String f12130x = "eur";

    /* renamed from: y */
    static final String f12131y = "usd";

    /* renamed from: z */
    static final String f12132z = "id";

    /* renamed from: P */
    private String f12133P;

    /* renamed from: Q */
    private Long f12134Q;

    /* renamed from: R */
    private String f12135R;

    /* renamed from: S */
    private SourceCodeVerification f12136S;

    /* renamed from: T */
    private Long f12137T;

    /* renamed from: U */
    private String f12138U;

    /* renamed from: V */
    private String f12139V;

    /* renamed from: W */
    private String f12140W;

    /* renamed from: X */
    private Boolean f12141X;

    /* renamed from: Y */
    private Map<String, String> f12142Y;

    /* renamed from: Z */
    private SourceOwner f12143Z;

    /* renamed from: aa */
    private SourceReceiver f12144aa;

    /* renamed from: ab */
    private SourceRedirect f12145ab;

    /* renamed from: ac */
    private String f12146ac;

    /* renamed from: ad */
    private Map<String, Object> f12147ad;

    /* renamed from: ae */
    private StripeSourceTypeModel f12148ae;

    /* renamed from: af */
    private String f12149af;

    /* renamed from: ag */
    private String f12150ag;

    /* compiled from: Source.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.g$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2396a {
    }

    /* compiled from: Source.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.g$b */
    /* loaded from: classes2.dex */
    public @interface AbstractC2397b {
    }

    /* compiled from: Source.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.g$c */
    /* loaded from: classes2.dex */
    public @interface AbstractC2398c {
    }

    /* compiled from: Source.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.g$d */
    /* loaded from: classes2.dex */
    public @interface AbstractC2399d {
    }

    static {
        f12118l.add("card");
        f12118l.add(f12112f);
    }

    C2395g(String str, Long l, String str2, SourceCodeVerification iVar, Long l2, String str3, String str4, Boolean bool, Map<String, String> map, SourceOwner jVar, SourceReceiver lVar, SourceRedirect mVar, String str5, Map<String, Object> map2, StripeSourceTypeModel rVar, String str6, String str7, String str8) {
        this.f12133P = str;
        this.f12134Q = l;
        this.f12135R = str2;
        this.f12136S = iVar;
        this.f12137T = l2;
        this.f12138U = str3;
        this.f12140W = str4;
        this.f12141X = bool;
        this.f12142Y = map;
        this.f12143Z = jVar;
        this.f12144aa = lVar;
        this.f12145ab = mVar;
        this.f12146ac = str5;
        this.f12147ad = map2;
        this.f12148ae = rVar;
        this.f12149af = str6;
        this.f12139V = str7;
        this.f12150ag = str8;
    }

    @Override // com.stripe.android.model.StripePaymentSource
    /* renamed from: A */
    public String mo17592A() {
        return this.f12133P;
    }

    /* renamed from: c */
    public Long m17782c() {
        return this.f12134Q;
    }

    /* renamed from: d */
    public String m17780d() {
        return this.f12135R;
    }

    /* renamed from: e */
    public SourceCodeVerification m17778e() {
        return this.f12136S;
    }

    /* renamed from: f */
    public Long m17776f() {
        return this.f12137T;
    }

    /* renamed from: g */
    public String m17774g() {
        return this.f12138U;
    }

    /* renamed from: h */
    public String m17772h() {
        return this.f12140W;
    }

    /* renamed from: i */
    public Boolean m17770i() {
        return this.f12141X;
    }

    /* renamed from: j */
    public Map<String, String> m17768j() {
        return this.f12142Y;
    }

    /* renamed from: k */
    public SourceOwner m17766k() {
        return this.f12143Z;
    }

    /* renamed from: l */
    public SourceReceiver m17764l() {
        return this.f12144aa;
    }

    /* renamed from: m */
    public SourceRedirect m17762m() {
        return this.f12145ab;
    }

    /* renamed from: n */
    public String m17760n() {
        return this.f12146ac;
    }

    /* renamed from: o */
    public Map<String, Object> m17759o() {
        return this.f12147ad;
    }

    /* renamed from: p */
    public StripeSourceTypeModel m17758p() {
        return this.f12148ae;
    }

    /* renamed from: q */
    public String m17757q() {
        return this.f12149af;
    }

    /* renamed from: r */
    public String m17756r() {
        return this.f12139V;
    }

    /* renamed from: s */
    public String m17755s() {
        return this.f12150ag;
    }

    /* renamed from: a */
    public void m17790a(String str) {
        this.f12133P = str;
    }

    /* renamed from: a */
    public void m17795a(long j) {
        this.f12134Q = Long.valueOf(j);
    }

    /* renamed from: b */
    public void m17784b(String str) {
        this.f12135R = str;
    }

    /* renamed from: a */
    public void m17794a(SourceCodeVerification iVar) {
        this.f12136S = iVar;
    }

    /* renamed from: b */
    public void m17785b(long j) {
        this.f12137T = Long.valueOf(j);
    }

    /* renamed from: c */
    public void m17781c(String str) {
        this.f12138U = str;
    }

    /* renamed from: d */
    public void m17779d(String str) {
        this.f12140W = str;
    }

    /* renamed from: a */
    public void m17786a(boolean z) {
        this.f12141X = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public void m17789a(Map<String, String> map) {
        this.f12142Y = map;
    }

    /* renamed from: a */
    public void m17793a(SourceOwner jVar) {
        this.f12143Z = jVar;
    }

    /* renamed from: a */
    public void m17792a(SourceReceiver lVar) {
        this.f12144aa = lVar;
    }

    /* renamed from: a */
    public void m17791a(SourceRedirect mVar) {
        this.f12145ab = mVar;
    }

    /* renamed from: e */
    public void m17777e(String str) {
        this.f12146ac = str;
    }

    /* renamed from: b */
    public void m17783b(Map<String, Object> map) {
        this.f12147ad = map;
    }

    /* renamed from: f */
    public void m17775f(@Size(min = 1) @NonNull String str) {
        this.f12139V = str;
        m17773g("unknown");
    }

    /* renamed from: g */
    public void m17773g(String str) {
        this.f12149af = str;
    }

    /* renamed from: h */
    public void m17771h(String str) {
        this.f12150ag = str;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.f12133P);
        hashMap.put(f12093B, this.f12134Q);
        hashMap.put(f12094C, this.f12135R);
        m17627a(hashMap, "code_verification", this.f12136S);
        hashMap.put(f12096E, this.f12137T);
        hashMap.put(f12097F, this.f12138U);
        hashMap.put(f12098G, this.f12140W);
        hashMap.put(f12099H, this.f12141X);
        hashMap.put(f12100I, this.f12142Y);
        m17627a(hashMap, f12101J, this.f12143Z);
        m17627a(hashMap, "receiver", this.f12144aa);
        m17627a(hashMap, "redirect", this.f12145ab);
        hashMap.put(this.f12139V, this.f12147ad);
        hashMap.put("status", this.f12146ac);
        hashMap.put("type", this.f12139V);
        hashMap.put(f12106O, this.f12150ag);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        try {
            StripeJsonUtils.m17612a(jSONObject, "id", this.f12133P);
            jSONObject.put(f12092A, "source");
            jSONObject.put(f12093B, this.f12134Q);
            StripeJsonUtils.m17612a(jSONObject, f12094C, this.f12135R);
            m17625a(jSONObject, "code_verification", this.f12136S);
            jSONObject.put(f12096E, this.f12137T);
            StripeJsonUtils.m17612a(jSONObject, f12097F, this.f12138U);
            StripeJsonUtils.m17612a(jSONObject, f12098G, this.f12140W);
            jSONObject.put(f12099H, this.f12141X);
            JSONObject a = StripeJsonUtils.m17620a(this.f12142Y);
            if (a != null) {
                jSONObject.put(f12100I, a);
            }
            JSONObject a2 = StripeJsonUtils.m17620a(this.f12147ad);
            if (a2 != null) {
                jSONObject.put(this.f12139V, a2);
            }
            m17625a(jSONObject, f12101J, this.f12143Z);
            m17625a(jSONObject, "receiver", this.f12144aa);
            m17625a(jSONObject, "redirect", this.f12145ab);
            StripeJsonUtils.m17612a(jSONObject, "status", this.f12146ac);
            StripeJsonUtils.m17612a(jSONObject, "type", this.f12139V);
            StripeJsonUtils.m17612a(jSONObject, f12106O, this.f12150ag);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Nullable
    /* renamed from: i */
    public static C2395g m17769i(@Nullable String str) {
        try {
            return m17788a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static C2395g m17788a(@Nullable JSONObject jSONObject) {
        StripeSourceTypeModel rVar = null;
        if (jSONObject == null || !"source".equals(jSONObject.optString(f12092A))) {
            return null;
        }
        String e = StripeJsonUtils.m17603e(jSONObject, "id");
        Long d = StripeJsonUtils.m17604d(jSONObject, f12093B);
        String e2 = StripeJsonUtils.m17603e(jSONObject, f12094C);
        SourceCodeVerification iVar = (SourceCodeVerification) m17787a(jSONObject, "code_verification", SourceCodeVerification.class);
        Long d2 = StripeJsonUtils.m17604d(jSONObject, f12096E);
        String e3 = StripeJsonUtils.m17603e(jSONObject, f12097F);
        String m = m17761m(StripeJsonUtils.m17603e(jSONObject, f12098G));
        Boolean valueOf = Boolean.valueOf(jSONObject.optBoolean(f12099H));
        Map<String, String> b = StripeJsonUtils.m17608b(jSONObject.optJSONObject(f12100I));
        SourceOwner jVar = (SourceOwner) m17787a(jSONObject, f12101J, SourceOwner.class);
        SourceReceiver lVar = (SourceReceiver) m17787a(jSONObject, "receiver", SourceReceiver.class);
        SourceRedirect mVar = (SourceRedirect) m17787a(jSONObject, "redirect", SourceRedirect.class);
        String j = m17767j(StripeJsonUtils.m17603e(jSONObject, "status"));
        String e4 = StripeJsonUtils.m17603e(jSONObject, "type");
        if (e4 == null) {
            e4 = "unknown";
        }
        String k = m17765k(e4);
        String str = k == null ? "unknown" : k;
        Map<String, Object> a = StripeJsonUtils.m17618a(jSONObject.optJSONObject(e4));
        if (f12118l.contains(e4)) {
            rVar = (StripeSourceTypeModel) m17787a(jSONObject, e4, StripeSourceTypeModel.class);
        }
        return new C2395g(e, d, e2, iVar, d2, e3, m, valueOf, b, jVar, lVar, mVar, j, a, rVar, str, e4, m17763l(StripeJsonUtils.m17603e(jSONObject, f12106O)));
    }

    @Nullable
    /* renamed from: a */
    static <T extends StripeJsonModel> T m17787a(@NonNull JSONObject jSONObject, @Size(min = 1) @NonNull String str, Class<T> cls) {
        if (!jSONObject.has(str)) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -808719889:
                if (str.equals("receiver")) {
                    c = 2;
                    break;
                }
                break;
            case -776144932:
                if (str.equals("redirect")) {
                    c = 3;
                    break;
                }
                break;
            case 3046160:
                if (str.equals("card")) {
                    c = 4;
                    break;
                }
                break;
            case 106164915:
                if (str.equals(f12101J)) {
                    c = 1;
                    break;
                }
                break;
            case 1615551277:
                if (str.equals("code_verification")) {
                    c = 0;
                    break;
                }
                break;
            case 1636477296:
                if (str.equals(f12112f)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return cls.cast(SourceCodeVerification.m17725a(jSONObject.optJSONObject("code_verification")));
            case 1:
                return cls.cast(SourceOwner.m17718a(jSONObject.optJSONObject(f12101J)));
            case 2:
                return cls.cast(SourceReceiver.m17661a(jSONObject.optJSONObject("receiver")));
            case 3:
                return cls.cast(SourceRedirect.m17652a(jSONObject.optJSONObject("redirect")));
            case 4:
                return cls.cast(SourceCardData.m17752a(jSONObject.optJSONObject("card")));
            case 5:
                return cls.cast(SourceSepaDebitData.m17643a(jSONObject.optJSONObject(f12112f)));
            default:
                return null;
        }
    }

    @Nullable
    /* renamed from: j */
    static String m17767j(@Nullable String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if (f12120n.equals(str)) {
            return f12120n;
        }
        if (f12121o.equals(str)) {
            return f12121o;
        }
        if ("canceled".equals(str)) {
            return "canceled";
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: k */
    public static String m17765k(@Nullable String str) {
        if ("card".equals(str)) {
            return "card";
        }
        if ("three_d_secure".equals(str)) {
            return "three_d_secure";
        }
        if (f12111e.equals(str)) {
            return f12111e;
        }
        if (f12112f.equals(str)) {
            return f12112f;
        }
        if (f12113g.equals(str)) {
            return f12113g;
        }
        if (f12114h.equals(str)) {
            return f12114h;
        }
        if (f12115i.equals(str)) {
            return f12115i;
        }
        if (f12108b.equals(str)) {
            return f12108b;
        }
        if (f12116j.equals(str)) {
            return f12116j;
        }
        if ("unknown".equals(str)) {
            return "unknown";
        }
        return null;
    }

    @Nullable
    /* renamed from: l */
    static String m17763l(@Nullable String str) {
        if (f12124r.equals(str)) {
            return f12124r;
        }
        if (f12125s.equals(str)) {
            return f12125s;
        }
        return null;
    }

    @Nullable
    /* renamed from: m */
    static String m17761m(@Nullable String str) {
        if ("redirect".equals(str)) {
            return "redirect";
        }
        if ("receiver".equals(str)) {
            return "receiver";
        }
        if ("code_verification".equals(str)) {
            return "code_verification";
        }
        if ("none".equals(str)) {
            return "none";
        }
        return null;
    }
}
