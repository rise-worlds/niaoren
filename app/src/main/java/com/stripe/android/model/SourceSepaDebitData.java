package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.n */
/* loaded from: classes2.dex */
public class SourceSepaDebitData extends StripeSourceTypeModel {

    /* renamed from: a */
    private static final String f12264a = "bank_code";

    /* renamed from: b */
    private static final String f12265b = "branch_code";

    /* renamed from: c */
    private static final String f12266c = "country";

    /* renamed from: d */
    private static final String f12267d = "fingerprint";

    /* renamed from: e */
    private static final String f12268e = "last4";

    /* renamed from: f */
    private static final String f12269f = "mandate_reference";

    /* renamed from: g */
    private static final String f12270g = "mandate_url";

    /* renamed from: h */
    private String f12271h;

    /* renamed from: i */
    private String f12272i;

    /* renamed from: j */
    private String f12273j;

    /* renamed from: k */
    private String f12274k;

    /* renamed from: l */
    private String f12275l;

    /* renamed from: m */
    private String f12276m;

    /* renamed from: n */
    private String f12277n;

    @Override // com.stripe.android.model.StripeSourceTypeModel
    @NonNull
    /* renamed from: o */
    public /* bridge */ /* synthetic */ Map mo17593o() {
        return super.mo17593o();
    }

    private SourceSepaDebitData() {
        m17594a(f12264a, f12265b, "country", f12267d, "last4", f12269f, f12270g);
    }

    @Nullable
    /* renamed from: a */
    public static SourceSepaDebitData m17643a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SourceSepaDebitData nVar = new SourceSepaDebitData();
        nVar.m17642b(StripeJsonUtils.m17603e(jSONObject, f12264a)).m17640c(StripeJsonUtils.m17603e(jSONObject, f12265b)).m17638d(StripeJsonUtils.m17603e(jSONObject, "country")).m17636e(StripeJsonUtils.m17603e(jSONObject, f12267d)).m17634f(StripeJsonUtils.m17603e(jSONObject, "last4")).m17632g(StripeJsonUtils.m17603e(jSONObject, f12269f)).m17630h(StripeJsonUtils.m17603e(jSONObject, f12270g));
        Map<String, Object> a = m17595a(jSONObject, nVar.f12282r);
        if (a != null) {
            nVar.m17598a(a);
        }
        return nVar;
    }

    /* renamed from: c */
    public String m17641c() {
        return this.f12271h;
    }

    /* renamed from: d */
    public String m17639d() {
        return this.f12272i;
    }

    /* renamed from: e */
    public String m17637e() {
        return this.f12273j;
    }

    /* renamed from: f */
    public String m17635f() {
        return this.f12274k;
    }

    /* renamed from: g */
    public String m17633g() {
        return this.f12275l;
    }

    /* renamed from: h */
    public String m17631h() {
        return this.f12276m;
    }

    /* renamed from: i */
    public String m17629i() {
        return this.f12277n;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, f12264a, this.f12271h);
        StripeJsonUtils.m17612a(jSONObject, f12265b, this.f12272i);
        StripeJsonUtils.m17612a(jSONObject, "country", this.f12273j);
        StripeJsonUtils.m17612a(jSONObject, f12267d, this.f12274k);
        StripeJsonUtils.m17612a(jSONObject, "last4", this.f12275l);
        StripeJsonUtils.m17612a(jSONObject, f12269f, this.f12276m);
        StripeJsonUtils.m17612a(jSONObject, f12270g, this.f12277n);
        m17596a(jSONObject, this.f12281q);
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f12264a, this.f12271h);
        hashMap.put(f12265b, this.f12272i);
        hashMap.put("country", this.f12273j);
        hashMap.put(f12267d, this.f12274k);
        hashMap.put("last4", this.f12275l);
        hashMap.put(f12269f, this.f12276m);
        hashMap.put(f12270g, this.f12277n);
        m17597a(hashMap, this.f12281q);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: a */
    static SourceSepaDebitData m17644a(String str) {
        try {
            return m17643a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: b */
    private SourceSepaDebitData m17642b(String str) {
        this.f12271h = str;
        return this;
    }

    /* renamed from: c */
    private SourceSepaDebitData m17640c(String str) {
        this.f12272i = str;
        return this;
    }

    /* renamed from: d */
    private SourceSepaDebitData m17638d(String str) {
        this.f12273j = str;
        return this;
    }

    /* renamed from: e */
    private SourceSepaDebitData m17636e(String str) {
        this.f12274k = str;
        return this;
    }

    /* renamed from: f */
    private SourceSepaDebitData m17634f(String str) {
        this.f12275l = str;
        return this;
    }

    /* renamed from: g */
    private SourceSepaDebitData m17632g(String str) {
        this.f12276m = str;
        return this;
    }

    /* renamed from: h */
    private SourceSepaDebitData m17630h(String str) {
        this.f12277n = str;
        return this;
    }
}
