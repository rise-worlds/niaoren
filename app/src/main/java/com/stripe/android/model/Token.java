package com.stripe.android.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.s */
/* loaded from: classes2.dex */
public class Token implements StripePaymentSource {

    /* renamed from: a */
    public static final String f12283a = "card";

    /* renamed from: b */
    public static final String f12284b = "bank_account";

    /* renamed from: c */
    public static final String f12285c = "pii";

    /* renamed from: d */
    public static final String f12286d = "account";

    /* renamed from: e */
    private static final String f12287e = "bank_account";

    /* renamed from: f */
    private static final String f12288f = "card";

    /* renamed from: g */
    private static final String f12289g = "created";

    /* renamed from: h */
    private static final String f12290h = "id";

    /* renamed from: i */
    private static final String f12291i = "livemode";

    /* renamed from: j */
    private static final String f12292j = "type";

    /* renamed from: k */
    private static final String f12293k = "used";

    /* renamed from: l */
    private final String f12294l;

    /* renamed from: m */
    private final String f12295m;

    /* renamed from: n */
    private final Date f12296n;

    /* renamed from: o */
    private final boolean f12297o;

    /* renamed from: p */
    private final boolean f12298p;

    /* renamed from: q */
    private final BankAccount f12299q;

    /* renamed from: r */
    private final Card f12300r;

    /* compiled from: Token.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.s$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2401a {
    }

    public Token(String str, boolean z, Date date, Boolean bool, Card cVar) {
        this.f12294l = str;
        this.f12295m = "card";
        this.f12296n = date;
        this.f12297o = z;
        this.f12300r = cVar;
        this.f12298p = bool.booleanValue();
        this.f12299q = null;
    }

    public Token(String str, boolean z, Date date, Boolean bool, BankAccount bVar) {
        this.f12294l = str;
        this.f12295m = "bank_account";
        this.f12296n = date;
        this.f12297o = z;
        this.f12300r = null;
        this.f12298p = bool.booleanValue();
        this.f12299q = bVar;
    }

    public Token(String str, String str2, boolean z, Date date, Boolean bool) {
        this.f12294l = str;
        this.f12295m = str2;
        this.f12296n = date;
        this.f12300r = null;
        this.f12299q = null;
        this.f12298p = bool.booleanValue();
        this.f12297o = z;
    }

    /* renamed from: a */
    public Date m17591a() {
        return this.f12296n;
    }

    @Override // com.stripe.android.model.StripePaymentSource
    /* renamed from: A */
    public String mo17592A() {
        return this.f12294l;
    }

    /* renamed from: b */
    public boolean m17588b() {
        return this.f12297o;
    }

    /* renamed from: c */
    public boolean m17586c() {
        return this.f12298p;
    }

    /* renamed from: d */
    public String m17585d() {
        return this.f12295m;
    }

    /* renamed from: e */
    public Card m17584e() {
        return this.f12300r;
    }

    /* renamed from: f */
    public BankAccount m17583f() {
        return this.f12299q;
    }

    @Nullable
    /* renamed from: a */
    public static Token m17590a(@Nullable String str) {
        try {
            return m17589a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static Token m17589a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String e = StripeJsonUtils.m17603e(jSONObject, "id");
        Long d = StripeJsonUtils.m17604d(jSONObject, f12289g);
        Boolean b = StripeJsonUtils.m17607b(jSONObject, f12291i);
        String b2 = m17587b(StripeJsonUtils.m17603e(jSONObject, "type"));
        Boolean b3 = StripeJsonUtils.m17607b(jSONObject, f12293k);
        if (e == null || d == null || b == null) {
            return null;
        }
        Date date = new Date(d.longValue() * 1000);
        if ("bank_account".equals(b2)) {
            JSONObject optJSONObject = jSONObject.optJSONObject("bank_account");
            if (optJSONObject == null) {
                return null;
            }
            return new Token(e, b.booleanValue(), date, b3, BankAccount.m17921a(optJSONObject));
        } else if ("card".equals(b2)) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject("card");
            if (optJSONObject2 == null) {
                return null;
            }
            return new Token(e, b.booleanValue(), date, b3, Card.m17901a(optJSONObject2));
        } else if (f12285c.equals(b2) || "account".equals(b2)) {
            return new Token(e, b2, b.booleanValue(), date, b3);
        } else {
            return null;
        }
    }

    @Nullable
    /* renamed from: b */
    static String m17587b(@Nullable String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return null;
        }
        if ("card".equals(str)) {
            return "card";
        }
        if ("bank_account".equals(str)) {
            return "bank_account";
        }
        if (f12285c.equals(str)) {
            return f12285c;
        }
        if ("account".equals(str)) {
            return "account";
        }
        return null;
    }
}
