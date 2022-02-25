package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.m */
/* loaded from: classes2.dex */
public class SourceRedirect extends StripeJsonModel {

    /* renamed from: a */
    public static final String f12255a = "pending";

    /* renamed from: b */
    public static final String f12256b = "succeeded";

    /* renamed from: c */
    public static final String f12257c = "failed";

    /* renamed from: d */
    static final String f12258d = "return_url";

    /* renamed from: e */
    static final String f12259e = "status";

    /* renamed from: f */
    static final String f12260f = "url";

    /* renamed from: g */
    private String f12261g;

    /* renamed from: h */
    private String f12262h;

    /* renamed from: i */
    private String f12263i;

    SourceRedirect(String str, String str2, String str3) {
        this.f12261g = str;
        this.f12262h = str2;
        this.f12263i = str3;
    }

    /* renamed from: c */
    public String m17650c() {
        return this.f12261g;
    }

    /* renamed from: a */
    public void m17653a(String str) {
        this.f12261g = str;
    }

    /* renamed from: d */
    public String m17648d() {
        return this.f12262h;
    }

    /* renamed from: b */
    public void m17651b(String str) {
        this.f12262h = str;
    }

    /* renamed from: e */
    public String m17646e() {
        return this.f12263i;
    }

    /* renamed from: c */
    public void m17649c(String str) {
        this.f12263i = str;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f12258d, this.f12261g);
        hashMap.put("status", this.f12262h);
        hashMap.put("url", this.f12263i);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, f12258d, this.f12261g);
        StripeJsonUtils.m17612a(jSONObject, "status", this.f12262h);
        StripeJsonUtils.m17612a(jSONObject, "url", this.f12263i);
        return jSONObject;
    }

    @Nullable
    /* renamed from: d */
    public static SourceRedirect m17647d(@Nullable String str) {
        try {
            return m17652a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static SourceRedirect m17652a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceRedirect(StripeJsonUtils.m17603e(jSONObject, f12258d), m17645e(StripeJsonUtils.m17603e(jSONObject, "status")), StripeJsonUtils.m17603e(jSONObject, "url"));
    }

    @Nullable
    /* renamed from: e */
    private static String m17645e(@Nullable String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if (f12256b.equals(str)) {
            return f12256b;
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        return null;
    }
}
