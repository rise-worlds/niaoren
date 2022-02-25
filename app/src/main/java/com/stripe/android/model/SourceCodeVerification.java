package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.i */
/* loaded from: classes2.dex */
public class SourceCodeVerification extends StripeJsonModel {

    /* renamed from: a */
    static final String f12179a = "pending";

    /* renamed from: b */
    static final String f12180b = "succeeded";

    /* renamed from: c */
    static final String f12181c = "failed";

    /* renamed from: d */
    private static final String f12182d = "attempts_remaining";

    /* renamed from: e */
    private static final String f12183e = "status";

    /* renamed from: f */
    private static final int f12184f = -1;

    /* renamed from: g */
    private int f12185g;

    /* renamed from: h */
    private String f12186h;

    SourceCodeVerification(int i, String str) {
        this.f12185g = i;
        this.f12186h = str;
    }

    /* renamed from: c */
    public int m17723c() {
        return this.f12185g;
    }

    /* renamed from: a */
    void m17727a(int i) {
        this.f12185g = i;
    }

    /* renamed from: d */
    public String m17721d() {
        return this.f12186h;
    }

    /* renamed from: a */
    void m17726a(String str) {
        this.f12186h = str;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put(f12182d, Integer.valueOf(this.f12185g));
        String str = this.f12186h;
        if (str != null) {
            hashMap.put("status", str);
        }
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f12182d, this.f12185g);
            StripeJsonUtils.m17612a(jSONObject, "status", this.f12186h);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Nullable
    /* renamed from: b */
    public static SourceCodeVerification m17724b(@Nullable String str) {
        try {
            return m17725a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static SourceCodeVerification m17725a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceCodeVerification(jSONObject.optInt(f12182d, -1), m17722c(StripeJsonUtils.m17603e(jSONObject, "status")));
    }

    @Nullable
    /* renamed from: c */
    private static String m17722c(@Nullable String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if ("succeeded".equals(str)) {
            return "succeeded";
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        return null;
    }
}
