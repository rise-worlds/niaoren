package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.stripe.android.StripeNetworkUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.h */
/* loaded from: classes2.dex */
public class SourceCardData extends StripeSourceTypeModel {

    /* renamed from: a */
    public static final String f12151a = "required";

    /* renamed from: b */
    public static final String f12152b = "optional";

    /* renamed from: c */
    public static final String f12153c = "not_supported";

    /* renamed from: d */
    public static final String f12154d = "unknown";

    /* renamed from: e */
    public static final String f12155e = "address_line1_check";

    /* renamed from: f */
    public static final String f12156f = "address_zip_check";

    /* renamed from: g */
    public static final String f12157g = "brand";

    /* renamed from: h */
    public static final String f12158h = "country";

    /* renamed from: i */
    public static final String f12159i = "cvc_check";

    /* renamed from: j */
    public static final String f12160j = "dynamic_last4";

    /* renamed from: k */
    public static final String f12161k = "exp_month";

    /* renamed from: l */
    public static final String f12162l = "exp_year";

    /* renamed from: m */
    public static final String f12163m = "funding";

    /* renamed from: n */
    public static final String f12164n = "last4";

    /* renamed from: o */
    public static final String f12165o = "three_d_secure";

    /* renamed from: p */
    public static final String f12166p = "tokenization_method";

    /* renamed from: A */
    private String f12167A;

    /* renamed from: B */
    private String f12168B;

    /* renamed from: C */
    private String f12169C;

    /* renamed from: D */
    private String f12170D;

    /* renamed from: s */
    private String f12171s;

    /* renamed from: t */
    private String f12172t;

    /* renamed from: u */
    private String f12173u;

    /* renamed from: v */
    private String f12174v;

    /* renamed from: w */
    private String f12175w;

    /* renamed from: x */
    private String f12176x;

    /* renamed from: y */
    private Integer f12177y;

    /* renamed from: z */
    private Integer f12178z;

    /* compiled from: SourceCardData.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.model.h$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2400a {
    }

    @Override // com.stripe.android.model.StripeSourceTypeModel
    @NonNull
    /* renamed from: o */
    public /* bridge */ /* synthetic */ Map mo17593o() {
        return super.mo17593o();
    }

    private SourceCardData() {
        m17594a(f12155e, f12156f, f12157g, f12158h, f12159i, f12160j, f12161k, f12162l, f12163m, f12164n, "three_d_secure", f12166p);
    }

    @Nullable
    /* renamed from: c */
    public String m17749c() {
        return this.f12171s;
    }

    @Nullable
    /* renamed from: d */
    public String m17747d() {
        return this.f12172t;
    }

    @Nullable
    /* renamed from: e */
    public String m17745e() {
        return this.f12173u;
    }

    @Nullable
    /* renamed from: f */
    public String m17743f() {
        return this.f12174v;
    }

    @Nullable
    /* renamed from: g */
    public String m17741g() {
        return this.f12175w;
    }

    @Nullable
    /* renamed from: h */
    public String m17739h() {
        return this.f12176x;
    }

    @Nullable
    /* renamed from: i */
    public Integer m17737i() {
        return this.f12177y;
    }

    @Nullable
    /* renamed from: j */
    public Integer m17735j() {
        return this.f12178z;
    }

    @Nullable
    /* renamed from: k */
    public String m17733k() {
        return this.f12167A;
    }

    @Nullable
    /* renamed from: l */
    public String m17731l() {
        return this.f12168B;
    }

    @Nullable
    /* renamed from: m */
    public String m17729m() {
        return this.f12169C;
    }

    @Nullable
    /* renamed from: n */
    public String m17728n() {
        return this.f12170D;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, f12155e, this.f12171s);
        StripeJsonUtils.m17612a(jSONObject, f12156f, this.f12172t);
        StripeJsonUtils.m17612a(jSONObject, f12157g, this.f12173u);
        StripeJsonUtils.m17612a(jSONObject, f12158h, this.f12174v);
        StripeJsonUtils.m17612a(jSONObject, f12160j, this.f12176x);
        StripeJsonUtils.m17614a(jSONObject, f12161k, this.f12177y);
        StripeJsonUtils.m17614a(jSONObject, f12162l, this.f12178z);
        StripeJsonUtils.m17612a(jSONObject, f12163m, this.f12167A);
        StripeJsonUtils.m17612a(jSONObject, f12164n, this.f12168B);
        StripeJsonUtils.m17612a(jSONObject, "three_d_secure", this.f12169C);
        StripeJsonUtils.m17612a(jSONObject, f12166p, this.f12170D);
        m17596a(jSONObject, this.f12281q);
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f12155e, this.f12171s);
        hashMap.put(f12156f, this.f12172t);
        hashMap.put(f12157g, this.f12173u);
        hashMap.put(f12158h, this.f12174v);
        hashMap.put(f12160j, this.f12176x);
        hashMap.put(f12161k, this.f12177y);
        hashMap.put(f12162l, this.f12178z);
        hashMap.put(f12163m, this.f12167A);
        hashMap.put(f12164n, this.f12168B);
        hashMap.put("three_d_secure", this.f12169C);
        hashMap.put(f12166p, this.f12170D);
        m17597a(hashMap, this.f12281q);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    /* renamed from: c */
    private SourceCardData m17748c(String str) {
        this.f12171s = str;
        return this;
    }

    /* renamed from: d */
    private SourceCardData m17746d(String str) {
        this.f12172t = str;
        return this;
    }

    /* renamed from: e */
    private SourceCardData m17744e(String str) {
        this.f12173u = str;
        return this;
    }

    /* renamed from: f */
    private SourceCardData m17742f(String str) {
        this.f12174v = str;
        return this;
    }

    /* renamed from: g */
    private SourceCardData m17740g(String str) {
        this.f12175w = str;
        return this;
    }

    /* renamed from: h */
    private SourceCardData m17738h(String str) {
        this.f12176x = str;
        return this;
    }

    /* renamed from: a */
    private SourceCardData m17754a(Integer num) {
        this.f12177y = num;
        return this;
    }

    /* renamed from: b */
    private SourceCardData m17751b(Integer num) {
        this.f12178z = num;
        return this;
    }

    /* renamed from: i */
    private SourceCardData m17736i(String str) {
        this.f12167A = str;
        return this;
    }

    /* renamed from: j */
    private SourceCardData m17734j(String str) {
        this.f12168B = str;
        return this;
    }

    /* renamed from: k */
    private SourceCardData m17732k(String str) {
        this.f12169C = str;
        return this;
    }

    /* renamed from: l */
    private SourceCardData m17730l(String str) {
        this.f12170D = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static SourceCardData m17752a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SourceCardData hVar = new SourceCardData();
        hVar.m17748c(StripeJsonUtils.m17603e(jSONObject, f12155e)).m17746d(StripeJsonUtils.m17603e(jSONObject, f12156f)).m17744e(Card.m17903a(StripeJsonUtils.m17603e(jSONObject, f12157g))).m17742f(StripeJsonUtils.m17603e(jSONObject, f12158h)).m17740g(StripeJsonUtils.m17603e(jSONObject, f12159i)).m17738h(StripeJsonUtils.m17603e(jSONObject, f12160j)).m17754a(StripeJsonUtils.m17605c(jSONObject, f12161k)).m17751b(StripeJsonUtils.m17605c(jSONObject, f12162l)).m17736i(Card.m17899b(StripeJsonUtils.m17603e(jSONObject, f12163m))).m17734j(StripeJsonUtils.m17603e(jSONObject, f12164n)).m17732k(m17750b(StripeJsonUtils.m17603e(jSONObject, "three_d_secure"))).m17730l(StripeJsonUtils.m17603e(jSONObject, f12166p));
        Map<String, Object> a = m17595a(jSONObject, hVar.f12282r);
        if (a != null) {
            hVar.m17598a(a);
        }
        return hVar;
    }

    @VisibleForTesting
    /* renamed from: a */
    static SourceCardData m17753a(String str) {
        try {
            return m17752a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: b */
    static String m17750b(@Nullable String str) {
        if (StripeJsonUtils.m17622a(str) == null) {
            return null;
        }
        return f12151a.equalsIgnoreCase(str) ? f12151a : f12152b.equalsIgnoreCase(str) ? f12152b : f12153c.equalsIgnoreCase(str) ? f12153c : "unknown";
    }
}
