package com.stripe.android.model;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.stripe.android.C2364R;
import com.stripe.android.CardUtils;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.StripeTextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.acf;

/* renamed from: com.stripe.android.model.c */
/* loaded from: classes2.dex */
public class Card extends StripeJsonModel implements StripePaymentSource {

    /* renamed from: A */
    private static final String f11977A = "object";

    /* renamed from: B */
    private static final String f11978B = "address_city";

    /* renamed from: C */
    private static final String f11979C = "address_country";

    /* renamed from: D */
    private static final String f11980D = "address_line1";

    /* renamed from: E */
    private static final String f11981E = "address_line1_check";

    /* renamed from: F */
    private static final String f11982F = "address_line2";

    /* renamed from: G */
    private static final String f11983G = "address_state";

    /* renamed from: H */
    private static final String f11984H = "address_zip";

    /* renamed from: I */
    private static final String f11985I = "address_zip_check";

    /* renamed from: J */
    private static final String f11986J = "brand";

    /* renamed from: K */
    private static final String f11987K = "country";

    /* renamed from: L */
    private static final String f11988L = "currency";

    /* renamed from: M */
    private static final String f11989M = "customer";

    /* renamed from: N */
    private static final String f11990N = "cvc_check";

    /* renamed from: O */
    private static final String f11991O = "exp_month";

    /* renamed from: P */
    private static final String f11992P = "exp_year";

    /* renamed from: Q */
    private static final String f11993Q = "fingerprint";

    /* renamed from: R */
    private static final String f11994R = "funding";

    /* renamed from: S */
    private static final String f11995S = "name";

    /* renamed from: T */
    private static final String f11996T = "last4";

    /* renamed from: U */
    private static final String f11997U = "id";

    /* renamed from: V */
    private static final String f11998V = "tokenization_method";

    /* renamed from: a */
    public static final String f11999a = "American Express";

    /* renamed from: b */
    public static final String f12000b = "Discover";

    /* renamed from: c */
    public static final String f12001c = "JCB";

    /* renamed from: d */
    public static final String f12002d = "Diners Club";

    /* renamed from: e */
    public static final String f12003e = "Visa";

    /* renamed from: f */
    public static final String f12004f = "MasterCard";

    /* renamed from: g */
    public static final String f12005g = "UnionPay";

    /* renamed from: h */
    public static final String f12006h = "Unknown";

    /* renamed from: i */
    public static final int f12007i = 4;

    /* renamed from: j */
    public static final int f12008j = 3;

    /* renamed from: k */
    public static final String f12009k = "credit";

    /* renamed from: l */
    public static final String f12010l = "debit";

    /* renamed from: m */
    public static final String f12011m = "prepaid";

    /* renamed from: n */
    public static final String f12012n = "unknown";

    /* renamed from: o */
    public static final Map<String, Integer> f12013o = new HashMap<String, Integer>() { // from class: com.stripe.android.model.c.1
        {
            put(Card.f11999a, Integer.valueOf(C2364R.C2365drawable.ic_amex));
            put(Card.f12002d, Integer.valueOf(C2364R.C2365drawable.ic_diners));
            put(Card.f12000b, Integer.valueOf(C2364R.C2365drawable.ic_discover));
            put(Card.f12001c, Integer.valueOf(C2364R.C2365drawable.ic_jcb));
            put(Card.f12004f, Integer.valueOf(C2364R.C2365drawable.ic_mastercard));
            put(Card.f12003e, Integer.valueOf(C2364R.C2365drawable.ic_visa));
            put(Card.f12005g, Integer.valueOf(C2364R.C2365drawable.ic_unionpay));
            put(Card.f12006h, Integer.valueOf(C2364R.C2365drawable.ic_unknown));
        }
    };

    /* renamed from: p */
    public static final String[] f12014p = {"34", "37"};

    /* renamed from: q */
    public static final String[] f12015q = {"60", acf.f15192q, "65"};

    /* renamed from: r */
    public static final String[] f12016r = {"35"};

    /* renamed from: s */
    public static final String[] f12017s = {"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};

    /* renamed from: t */
    public static final String[] f12018t = {DdyConstants.APP_INSTALL_DOWNLOADING};

    /* renamed from: u */
    public static final String[] f12019u = {"2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229", "223", "224", "225", "226", "227", "228", "229", "23", "24", "25", "26", "270", "271", "2720", "50", "51", "52", "53", "54", "55", "67"};

    /* renamed from: v */
    public static final String[] f12020v = {"62"};

    /* renamed from: w */
    public static final int f12021w = 16;

    /* renamed from: x */
    public static final int f12022x = 15;

    /* renamed from: y */
    public static final int f12023y = 14;

    /* renamed from: z */
    static final String f12024z = "card";

    /* renamed from: W */
    private String f12025W;

    /* renamed from: X */
    private String f12026X;

    /* renamed from: Y */
    private Integer f12027Y;

    /* renamed from: Z */
    private Integer f12028Z;

    /* renamed from: aa */
    private String f12029aa;

    /* renamed from: ab */
    private String f12030ab;

    /* renamed from: ac */
    private String f12031ac;

    /* renamed from: ad */
    private String f12032ad;

    /* renamed from: ae */
    private String f12033ae;

    /* renamed from: af */
    private String f12034af;

    /* renamed from: ag */
    private String f12035ag;

    /* renamed from: ah */
    private String f12036ah;

    /* renamed from: ai */
    private String f12037ai;
    @Size(4)

    /* renamed from: aj */
    private String f12038aj;

    /* renamed from: ak */
    private String f12039ak;

    /* renamed from: al */
    private String f12040al;

    /* renamed from: am */
    private String f12041am;

    /* renamed from: an */
    private String f12042an;

    /* renamed from: ao */
    private String f12043ao;

    /* renamed from: ap */
    private String f12044ap;

    /* renamed from: aq */
    private String f12045aq;

    /* renamed from: ar */
    private String f12046ar;
    @NonNull

    /* renamed from: as */
    private List<String> f12047as;
    @Nullable

    /* renamed from: at */
    private String f12048at;

    /* compiled from: Card.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.c$b */
    /* loaded from: classes2.dex */
    public @interface AbstractC2393b {
    }

    /* compiled from: Card.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.c$c */
    /* loaded from: classes2.dex */
    public @interface AbstractC2394c {
    }

    /* compiled from: Card.java */
    /* renamed from: com.stripe.android.model.c$a */
    /* loaded from: classes2.dex */
    public static class C2392a {

        /* renamed from: a */
        private final String f12049a;

        /* renamed from: b */
        private final String f12050b;

        /* renamed from: c */
        private final Integer f12051c;

        /* renamed from: d */
        private final Integer f12052d;

        /* renamed from: e */
        private String f12053e;

        /* renamed from: f */
        private String f12054f;

        /* renamed from: g */
        private String f12055g;

        /* renamed from: h */
        private String f12056h;

        /* renamed from: i */
        private String f12057i;

        /* renamed from: j */
        private String f12058j;

        /* renamed from: k */
        private String f12059k;

        /* renamed from: l */
        private String f12060l;

        /* renamed from: m */
        private String f12061m;

        /* renamed from: n */
        private String f12062n;

        /* renamed from: o */
        private String f12063o;
        @Size(4)

        /* renamed from: p */
        private String f12064p;

        /* renamed from: q */
        private String f12065q;

        /* renamed from: r */
        private String f12066r;

        /* renamed from: s */
        private String f12067s;

        /* renamed from: t */
        private String f12068t;

        /* renamed from: u */
        private String f12069u;

        /* renamed from: v */
        private String f12070v;

        /* renamed from: w */
        private String f12071w;

        public C2392a(String str, @IntRange(from = 1, m25695to = 12) Integer num, @IntRange(from = 0) Integer num2, String str2) {
            this.f12049a = str;
            this.f12051c = num;
            this.f12052d = num2;
            this.f12050b = str2;
        }

        @NonNull
        /* renamed from: a */
        public C2392a m17857a(String str) {
            this.f12053e = str;
            return this;
        }

        @NonNull
        /* renamed from: b */
        public C2392a m17855b(String str) {
            this.f12054f = str;
            return this;
        }

        @NonNull
        /* renamed from: c */
        public C2392a m17853c(String str) {
            this.f12055g = str;
            return this;
        }

        @NonNull
        /* renamed from: d */
        public C2392a m17851d(String str) {
            this.f12056h = str;
            return this;
        }

        @NonNull
        /* renamed from: e */
        public C2392a m17849e(String str) {
            this.f12057i = str;
            return this;
        }

        @NonNull
        /* renamed from: f */
        public C2392a m17847f(String str) {
            this.f12058j = str;
            return this;
        }

        @NonNull
        /* renamed from: g */
        public C2392a m17845g(String str) {
            this.f12059k = str;
            return this;
        }

        @NonNull
        /* renamed from: h */
        public C2392a m17843h(String str) {
            this.f12060l = str;
            return this;
        }

        @NonNull
        /* renamed from: i */
        public C2392a m17841i(String str) {
            this.f12061m = str;
            return this;
        }

        @NonNull
        /* renamed from: j */
        public C2392a m17839j(String str) {
            this.f12062n = str;
            return this;
        }

        @NonNull
        /* renamed from: k */
        public C2392a m17837k(String str) {
            this.f12065q = str;
            return this;
        }

        @NonNull
        /* renamed from: l */
        public C2392a m17835l(String str) {
            this.f12063o = str;
            return this;
        }

        @NonNull
        /* renamed from: m */
        public C2392a m17833m(String str) {
            this.f12066r = str;
            return this;
        }

        @NonNull
        /* renamed from: n */
        public C2392a m17831n(String str) {
            this.f12067s = str;
            return this;
        }

        @NonNull
        /* renamed from: o */
        public C2392a m17829o(String str) {
            this.f12068t = str;
            return this;
        }

        @NonNull
        /* renamed from: p */
        public C2392a m17827p(String str) {
            this.f12069u = str;
            return this;
        }

        @NonNull
        /* renamed from: q */
        public C2392a m17825q(String str) {
            this.f12064p = str;
            return this;
        }

        @NonNull
        /* renamed from: r */
        public C2392a m17823r(String str) {
            this.f12070v = str;
            return this;
        }

        @NonNull
        /* renamed from: s */
        public C2392a m17821s(@Nullable String str) {
            this.f12071w = str;
            return this;
        }

        /* renamed from: a */
        public Card m17859a() {
            return new Card(this);
        }
    }

    @Nullable
    /* renamed from: a */
    public static String m17903a(@Nullable String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return null;
        }
        return f11999a.equalsIgnoreCase(str) ? f11999a : f12004f.equalsIgnoreCase(str) ? f12004f : f12002d.equalsIgnoreCase(str) ? f12002d : f12000b.equalsIgnoreCase(str) ? f12000b : f12001c.equalsIgnoreCase(str) ? f12001c : f12003e.equalsIgnoreCase(str) ? f12003e : f12005g.equalsIgnoreCase(str) ? f12005g : f12006h;
    }

    @Nullable
    /* renamed from: b */
    public static String m17899b(@Nullable String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return null;
        }
        return f12009k.equalsIgnoreCase(str) ? f12009k : f12010l.equalsIgnoreCase(str) ? f12010l : f12011m.equalsIgnoreCase(str) ? f12011m : "unknown";
    }

    @Nullable
    /* renamed from: c */
    public static Card m17896c(String str) {
        try {
            return m17901a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static Card m17901a(JSONObject jSONObject) {
        if (jSONObject == null || !"card".equals(jSONObject.optString(f11977A))) {
            return null;
        }
        Integer c = StripeJsonUtils.m17605c(jSONObject, "exp_month");
        Integer c2 = StripeJsonUtils.m17605c(jSONObject, "exp_year");
        if (c != null && (c.intValue() < 1 || c.intValue() > 12)) {
            c = null;
        }
        if (c2 != null && c2.intValue() < 0) {
            c2 = null;
        }
        C2392a aVar = new C2392a(null, c, c2, null);
        aVar.m17849e(StripeJsonUtils.m17603e(jSONObject, f11978B));
        aVar.m17855b(StripeJsonUtils.m17603e(jSONObject, f11980D));
        aVar.m17853c(StripeJsonUtils.m17603e(jSONObject, "address_line1_check"));
        aVar.m17851d(StripeJsonUtils.m17603e(jSONObject, f11982F));
        aVar.m17841i(StripeJsonUtils.m17603e(jSONObject, f11979C));
        aVar.m17847f(StripeJsonUtils.m17603e(jSONObject, f11983G));
        aVar.m17845g(StripeJsonUtils.m17603e(jSONObject, f11984H));
        aVar.m17843h(StripeJsonUtils.m17603e(jSONObject, "address_zip_check"));
        aVar.m17839j(m17903a(StripeJsonUtils.m17603e(jSONObject, "brand")));
        aVar.m17833m(StripeJsonUtils.m17602f(jSONObject, "country"));
        aVar.m17829o(StripeJsonUtils.m17603e(jSONObject, f11989M));
        aVar.m17831n(StripeJsonUtils.m17601g(jSONObject, f11988L));
        aVar.m17827p(StripeJsonUtils.m17603e(jSONObject, "cvc_check"));
        aVar.m17835l(m17899b(StripeJsonUtils.m17603e(jSONObject, "funding")));
        aVar.m17837k(StripeJsonUtils.m17603e(jSONObject, f11993Q));
        aVar.m17823r(StripeJsonUtils.m17603e(jSONObject, "id"));
        aVar.m17825q(StripeJsonUtils.m17603e(jSONObject, "last4"));
        aVar.m17857a(StripeJsonUtils.m17603e(jSONObject, "name"));
        aVar.m17821s(StripeJsonUtils.m17603e(jSONObject, "tokenization_method"));
        return aVar.m17859a();
    }

    public Card(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, @Size(4) String str11, String str12, String str13, String str14, String str15, String str16) {
        this.f12047as = new ArrayList();
        this.f12025W = StripeTextUtils.m17205a(m17871o(str));
        this.f12027Y = num;
        this.f12028Z = num2;
        this.f12026X = StripeTextUtils.m17205a(str2);
        this.f12029aa = StripeTextUtils.m17205a(str3);
        this.f12030ab = StripeTextUtils.m17205a(str4);
        this.f12032ad = StripeTextUtils.m17205a(str5);
        this.f12033ae = StripeTextUtils.m17205a(str6);
        this.f12034af = StripeTextUtils.m17205a(str7);
        this.f12035ag = StripeTextUtils.m17205a(str8);
        this.f12037ai = StripeTextUtils.m17205a(str9);
        this.f12039ak = m17903a(str10) == null ? m17863w() : str10;
        this.f12038aj = StripeTextUtils.m17205a(str11) == null ? m17865u() : str11;
        this.f12041am = StripeTextUtils.m17205a(str12);
        this.f12040al = m17899b(str13);
        this.f12042an = StripeTextUtils.m17205a(str14);
        this.f12043ao = StripeTextUtils.m17205a(str15);
        this.f12046ar = StripeTextUtils.m17205a(str16);
    }

    public Card(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this(str, num, num2, str2, str3, str4, str5, str6, str7, str8, str9, null, null, null, null, null, str10, null);
    }

    public Card(String str, Integer num, Integer num2, String str2) {
        this(str, num, num2, str2, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /* renamed from: c */
    public boolean m17897c() {
        return m17898b(Calendar.getInstance());
    }

    /* renamed from: d */
    public boolean m17894d() {
        return CardUtils.m18075b(this.f12025W);
    }

    /* renamed from: e */
    public boolean m17892e() {
        return m17895c(Calendar.getInstance());
    }

    /* renamed from: f */
    public boolean m17890f() {
        if (StripeTextUtils.m17202b(this.f12026X)) {
            return false;
        }
        String trim = this.f12026X.trim();
        String w = m17863w();
        return ModelUtils.m17797a(trim) && ((w == null && trim.length() >= 3 && trim.length() <= 4) || ((f11999a.equals(w) && trim.length() == 4) || trim.length() == 3));
    }

    /* renamed from: g */
    public boolean m17888g() {
        Integer num = this.f12027Y;
        return num != null && num.intValue() >= 1 && this.f12027Y.intValue() <= 12;
    }

    /* renamed from: a */
    boolean m17902a(Calendar calendar) {
        Integer num = this.f12028Z;
        return num != null && !ModelUtils.m17798a(num.intValue(), calendar);
    }

    /* renamed from: h */
    public String m17886h() {
        return this.f12025W;
    }

    @NonNull
    /* renamed from: i */
    public List<String> m17884i() {
        return this.f12047as;
    }

    @NonNull
    /* renamed from: d */
    public Card m17893d(@NonNull String str) {
        this.f12047as.add(str);
        return this;
    }

    @Deprecated
    /* renamed from: e */
    public void m17891e(String str) {
        this.f12025W = str;
        this.f12039ak = null;
        this.f12038aj = null;
    }

    /* renamed from: j */
    public String m17882j() {
        return this.f12026X;
    }

    @Deprecated
    /* renamed from: f */
    public void m17889f(String str) {
        this.f12026X = str;
    }

    @IntRange(from = 1, m25695to = 12)
    @Nullable
    /* renamed from: k */
    public Integer m17880k() {
        return this.f12027Y;
    }

    @Deprecated
    /* renamed from: a */
    public void m17904a(@IntRange(from = 1, m25695to = 12) @Nullable Integer num) {
        this.f12027Y = num;
    }

    /* renamed from: l */
    public Integer m17878l() {
        return this.f12028Z;
    }

    @Deprecated
    /* renamed from: b */
    public void m17900b(Integer num) {
        this.f12028Z = num;
    }

    /* renamed from: m */
    public String m17876m() {
        return this.f12029aa;
    }

    /* renamed from: g */
    public void m17887g(String str) {
        this.f12029aa = str;
    }

    /* renamed from: n */
    public String m17874n() {
        return this.f12030ab;
    }

    /* renamed from: h */
    public void m17885h(String str) {
        this.f12030ab = str;
    }

    /* renamed from: o */
    public String m17872o() {
        return this.f12032ad;
    }

    /* renamed from: i */
    public void m17883i(String str) {
        this.f12032ad = str;
    }

    /* renamed from: p */
    public String m17870p() {
        return this.f12033ae;
    }

    /* renamed from: j */
    public void m17881j(String str) {
        this.f12033ae = str;
    }

    /* renamed from: q */
    public String m17869q() {
        return this.f12035ag;
    }

    /* renamed from: k */
    public void m17879k(String str) {
        this.f12035ag = str;
    }

    /* renamed from: r */
    public String m17868r() {
        return this.f12034af;
    }

    /* renamed from: l */
    public void m17877l(String str) {
        this.f12034af = str;
    }

    /* renamed from: s */
    public String m17867s() {
        return this.f12037ai;
    }

    /* renamed from: m */
    public void m17875m(String str) {
        this.f12037ai = str;
    }

    /* renamed from: t */
    public String m17866t() {
        return this.f12043ao;
    }

    /* renamed from: n */
    public void m17873n(String str) {
        this.f12043ao = str;
    }

    /* renamed from: u */
    public String m17865u() {
        if (!StripeTextUtils.m17202b(this.f12038aj)) {
            return this.f12038aj;
        }
        String str = this.f12025W;
        if (str == null || str.length() <= 4) {
            return null;
        }
        String str2 = this.f12025W;
        this.f12038aj = str2.substring(str2.length() - 4, this.f12025W.length());
        return this.f12038aj;
    }

    @Deprecated
    /* renamed from: v */
    public String m17864v() {
        return m17863w();
    }

    /* renamed from: w */
    public String m17863w() {
        if (StripeTextUtils.m17202b(this.f12039ak) && !StripeTextUtils.m17202b(this.f12025W)) {
            this.f12039ak = CardUtils.m18078a(this.f12025W);
        }
        return this.f12039ak;
    }

    /* renamed from: x */
    public String m17862x() {
        return this.f12041am;
    }

    @Nullable
    /* renamed from: y */
    public String m17861y() {
        return this.f12040al;
    }

    /* renamed from: z */
    public String m17860z() {
        return this.f12042an;
    }

    @Override // com.stripe.android.model.StripePaymentSource
    /* renamed from: A */
    public String mo17592A() {
        return this.f12046ar;
    }

    @Nullable
    /* renamed from: B */
    public String m17909B() {
        return this.f12031ac;
    }

    @Nullable
    /* renamed from: C */
    public String m17908C() {
        return this.f12036ah;
    }

    @Nullable
    /* renamed from: D */
    public String m17907D() {
        return this.f12044ap;
    }

    @Nullable
    /* renamed from: E */
    public String m17906E() {
        return this.f12045aq;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, "name", this.f12029aa);
        StripeJsonUtils.m17612a(jSONObject, f11978B, this.f12033ae);
        StripeJsonUtils.m17612a(jSONObject, f11979C, this.f12037ai);
        StripeJsonUtils.m17612a(jSONObject, f11980D, this.f12030ab);
        StripeJsonUtils.m17612a(jSONObject, "address_line1_check", this.f12031ac);
        StripeJsonUtils.m17612a(jSONObject, f11982F, this.f12032ad);
        StripeJsonUtils.m17612a(jSONObject, f11983G, this.f12034af);
        StripeJsonUtils.m17612a(jSONObject, f11984H, this.f12035ag);
        StripeJsonUtils.m17612a(jSONObject, "address_zip_check", this.f12036ah);
        StripeJsonUtils.m17612a(jSONObject, "brand", this.f12039ak);
        StripeJsonUtils.m17612a(jSONObject, f11988L, this.f12043ao);
        StripeJsonUtils.m17612a(jSONObject, "country", this.f12042an);
        StripeJsonUtils.m17612a(jSONObject, f11989M, this.f12044ap);
        StripeJsonUtils.m17614a(jSONObject, "exp_month", this.f12027Y);
        StripeJsonUtils.m17614a(jSONObject, "exp_year", this.f12028Z);
        StripeJsonUtils.m17612a(jSONObject, f11993Q, this.f12041am);
        StripeJsonUtils.m17612a(jSONObject, "funding", this.f12040al);
        StripeJsonUtils.m17612a(jSONObject, "cvc_check", this.f12045aq);
        StripeJsonUtils.m17612a(jSONObject, "last4", this.f12038aj);
        StripeJsonUtils.m17612a(jSONObject, "id", this.f12046ar);
        StripeJsonUtils.m17612a(jSONObject, "tokenization_method", this.f12048at);
        StripeJsonUtils.m17612a(jSONObject, f11977A, "card");
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.f12029aa);
        hashMap.put(f11978B, this.f12033ae);
        hashMap.put(f11979C, this.f12037ai);
        hashMap.put(f11980D, this.f12030ab);
        hashMap.put("address_line1_check", this.f12031ac);
        hashMap.put(f11982F, this.f12032ad);
        hashMap.put(f11983G, this.f12034af);
        hashMap.put(f11984H, this.f12035ag);
        hashMap.put("address_zip_check", this.f12036ah);
        hashMap.put("brand", this.f12039ak);
        hashMap.put(f11988L, this.f12043ao);
        hashMap.put("country", this.f12042an);
        hashMap.put(f11989M, this.f12044ap);
        hashMap.put("cvc_check", this.f12045aq);
        hashMap.put("exp_month", this.f12027Y);
        hashMap.put("exp_year", this.f12028Z);
        hashMap.put(f11993Q, this.f12041am);
        hashMap.put("funding", this.f12040al);
        hashMap.put("id", this.f12046ar);
        hashMap.put("last4", this.f12038aj);
        hashMap.put("tokenization_method", this.f12048at);
        hashMap.put(f11977A, "card");
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: F */
    public String m17905F() {
        return this.f12048at;
    }

    /* renamed from: b */
    boolean m17898b(Calendar calendar) {
        return this.f12026X == null ? m17894d() && m17895c(calendar) : m17894d() && m17895c(calendar) && m17890f();
    }

    /* renamed from: c */
    boolean m17895c(Calendar calendar) {
        if (m17888g() && m17902a(calendar)) {
            return !ModelUtils.m17799a(this.f12028Z.intValue(), this.f12027Y.intValue(), calendar);
        }
        return false;
    }

    private Card(C2392a aVar) {
        String str;
        String str2;
        this.f12047as = new ArrayList();
        this.f12025W = StripeTextUtils.m17205a(m17871o(aVar.f12049a));
        this.f12027Y = aVar.f12051c;
        this.f12028Z = aVar.f12052d;
        this.f12026X = StripeTextUtils.m17205a(aVar.f12050b);
        this.f12029aa = StripeTextUtils.m17205a(aVar.f12053e);
        this.f12030ab = StripeTextUtils.m17205a(aVar.f12054f);
        this.f12031ac = StripeTextUtils.m17205a(aVar.f12055g);
        this.f12032ad = StripeTextUtils.m17205a(aVar.f12056h);
        this.f12033ae = StripeTextUtils.m17205a(aVar.f12057i);
        this.f12034af = StripeTextUtils.m17205a(aVar.f12058j);
        this.f12035ag = StripeTextUtils.m17205a(aVar.f12059k);
        this.f12036ah = StripeTextUtils.m17205a(aVar.f12060l);
        this.f12037ai = StripeTextUtils.m17205a(aVar.f12061m);
        if (StripeTextUtils.m17205a(aVar.f12064p) == null) {
            str = m17865u();
        } else {
            str = aVar.f12064p;
        }
        this.f12038aj = str;
        if (m17903a(aVar.f12062n) == null) {
            str2 = m17863w();
        } else {
            str2 = aVar.f12062n;
        }
        this.f12039ak = str2;
        this.f12041am = StripeTextUtils.m17205a(aVar.f12065q);
        this.f12040al = m17899b(aVar.f12063o);
        this.f12042an = StripeTextUtils.m17205a(aVar.f12066r);
        this.f12043ao = StripeTextUtils.m17205a(aVar.f12067s);
        this.f12044ap = StripeTextUtils.m17205a(aVar.f12068t);
        this.f12045aq = StripeTextUtils.m17205a(aVar.f12069u);
        this.f12046ar = StripeTextUtils.m17205a(aVar.f12070v);
        this.f12048at = StripeTextUtils.m17205a(aVar.f12071w);
    }

    /* renamed from: o */
    private String m17871o(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+|-", "");
    }
}
