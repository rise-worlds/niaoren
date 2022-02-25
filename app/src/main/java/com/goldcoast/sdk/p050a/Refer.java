package com.goldcoast.sdk.p050a;

import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.goldcoast.sdk.a.g */
/* loaded from: classes.dex */
public final class Refer extends Entity<Refer> {

    /* renamed from: a */
    private String f8987a;

    /* renamed from: b */
    private int f8988b;

    /* renamed from: c */
    private String f8989c;

    /* renamed from: d */
    private long f8990d;

    /* renamed from: e */
    private String f8991e;

    /* renamed from: f */
    private String f8992f;

    /* renamed from: g */
    private String f8993g;

    /* renamed from: h */
    private String f8994h;

    /* renamed from: i */
    private String f8995i;

    /* renamed from: j */
    private String f8996j;

    /* renamed from: k */
    private int f8997k;

    /* renamed from: l */
    private PropInfo f8998l;

    /* renamed from: m */
    private String f8999m;

    /* renamed from: n */
    private String f9000n;

    /* renamed from: o */
    private String f9001o;

    /* renamed from: a */
    public final void m20359a(String str) {
        this.f8987a = str;
    }

    /* renamed from: a */
    public final void m20362a(int i) {
        this.f8988b = i;
    }

    /* renamed from: b */
    public final void m20357b(String str) {
        this.f8989c = str;
    }

    /* renamed from: a */
    public final void m20361a(long j) {
        this.f8990d = j;
    }

    /* renamed from: c */
    public final void m20356c(String str) {
        this.f8991e = str;
    }

    /* renamed from: d */
    public final void m20355d(String str) {
        this.f8992f = str;
    }

    /* renamed from: e */
    public final void m20354e(String str) {
        this.f8993g = str;
    }

    /* renamed from: f */
    public final void m20353f(String str) {
        this.f8994h = str;
    }

    /* renamed from: g */
    public final void m20352g(String str) {
        this.f8995i = str;
    }

    /* renamed from: h */
    public final void m20351h(String str) {
        this.f8996j = str;
    }

    /* renamed from: b */
    public final void m20358b(int i) {
        this.f8997k = i;
    }

    /* renamed from: a */
    public final void m20360a(PropInfo fVar) {
        this.f8998l = fVar;
    }

    /* renamed from: i */
    public final void m20350i(String str) {
        this.f8999m = str;
    }

    /* renamed from: j */
    public final void m20349j(String str) {
        this.f9000n = str;
    }

    /* renamed from: k */
    public final void m20348k(String str) {
        this.f9001o = str;
    }

    /* renamed from: a */
    public final JSONObject m20363a() {
        JSONObject jSONObject;
        PropInfo fVar = this.f8998l;
        if (fVar != null) {
            jSONObject = fVar.m20364b();
        } else {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("rf_md5", this.f8987a);
            jSONObject.put("status", this.f8988b);
            jSONObject.put("msg", this.f8989c);
            jSONObject.put("runTime", this.f8990d);
            jSONObject.put("androidId", this.f8991e);
            jSONObject.put("netOperator", this.f8992f);
            jSONObject.put("simOperatorName", this.f8993g);
            jSONObject.put("displayLanguage", this.f8994h);
            jSONObject.put("displayCountry", this.f8995i);
            jSONObject.put("token", this.f8996j);
            jSONObject.put("category", this.f8997k);
            jSONObject.put("sdkV", this.f8999m);
            jSONObject.put(HWYunManager.f7540a, this.f9000n);
            jSONObject.put("signature", this.f9001o);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
