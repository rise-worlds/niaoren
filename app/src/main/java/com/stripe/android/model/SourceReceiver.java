package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.StripeTextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.l */
/* loaded from: classes2.dex */
public class SourceReceiver extends StripeJsonModel {

    /* renamed from: a */
    private static final String f12247a = "address";

    /* renamed from: b */
    private static final String f12248b = "amount_charged";

    /* renamed from: c */
    private static final String f12249c = "amount_received";

    /* renamed from: d */
    private static final String f12250d = "amount_returned";

    /* renamed from: e */
    private String f12251e;

    /* renamed from: f */
    private long f12252f;

    /* renamed from: g */
    private long f12253g;

    /* renamed from: h */
    private long f12254h;

    SourceReceiver(String str, long j, long j2, long j3) {
        this.f12251e = str;
        this.f12252f = j;
        this.f12253g = j2;
        this.f12254h = j3;
    }

    /* renamed from: c */
    public String m17658c() {
        return this.f12251e;
    }

    /* renamed from: a */
    public void m17662a(String str) {
        this.f12251e = str;
    }

    /* renamed from: d */
    public long m17656d() {
        return this.f12252f;
    }

    /* renamed from: a */
    public void m17663a(long j) {
        this.f12252f = j;
    }

    /* renamed from: e */
    public long m17655e() {
        return this.f12253g;
    }

    /* renamed from: b */
    public void m17660b(long j) {
        this.f12253g = j;
    }

    /* renamed from: f */
    public long m17654f() {
        return this.f12254h;
    }

    /* renamed from: c */
    public void m17657c(long j) {
        this.f12254h = j;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        if (!StripeTextUtils.m17202b(this.f12251e)) {
            hashMap.put(f12247a, this.f12251e);
        }
        hashMap.put(f12247a, this.f12251e);
        hashMap.put(f12248b, Long.valueOf(this.f12252f));
        hashMap.put(f12249c, Long.valueOf(this.f12253g));
        hashMap.put(f12250d, Long.valueOf(this.f12254h));
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, f12247a, this.f12251e);
        try {
            jSONObject.put(f12248b, this.f12252f);
            jSONObject.put(f12249c, this.f12253g);
            jSONObject.put(f12250d, this.f12254h);
            return jSONObject;
        } catch (JSONException unused) {
            return jSONObject;
        }
    }

    @Nullable
    /* renamed from: b */
    public static SourceReceiver m17659b(@Nullable String str) {
        try {
            return m17661a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static SourceReceiver m17661a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceReceiver(StripeJsonUtils.m17603e(jSONObject, f12247a), jSONObject.optLong(f12248b), jSONObject.optLong(f12249c), jSONObject.optLong(f12250d));
    }
}
