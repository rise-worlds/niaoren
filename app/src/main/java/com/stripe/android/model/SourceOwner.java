package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.j */
/* loaded from: classes2.dex */
public class SourceOwner extends StripeJsonModel {

    /* renamed from: a */
    private static final String f12187a = "verified_";

    /* renamed from: b */
    private static final String f12188b = "address";

    /* renamed from: c */
    private static final String f12189c = "email";

    /* renamed from: d */
    private static final String f12190d = "name";

    /* renamed from: e */
    private static final String f12191e = "phone";

    /* renamed from: f */
    private static final String f12192f = "verified_address";

    /* renamed from: g */
    private static final String f12193g = "verified_email";

    /* renamed from: h */
    private static final String f12194h = "verified_name";

    /* renamed from: i */
    private static final String f12195i = "verified_phone";

    /* renamed from: j */
    private Address f12196j;

    /* renamed from: k */
    private String f12197k;

    /* renamed from: l */
    private String f12198l;

    /* renamed from: m */
    private String f12199m;

    /* renamed from: n */
    private Address f12200n;

    /* renamed from: o */
    private String f12201o;

    /* renamed from: p */
    private String f12202p;

    /* renamed from: q */
    private String f12203q;

    SourceOwner(Address address, String str, String str2, String str3, Address address2, String str4, String str5, String str6) {
        this.f12196j = address;
        this.f12197k = str;
        this.f12198l = str2;
        this.f12199m = str3;
        this.f12200n = address2;
        this.f12201o = str4;
        this.f12202p = str5;
        this.f12203q = str6;
    }

    /* renamed from: c */
    public Address m17715c() {
        return this.f12196j;
    }

    /* renamed from: d */
    public String m17713d() {
        return this.f12197k;
    }

    /* renamed from: e */
    public String m17711e() {
        return this.f12198l;
    }

    /* renamed from: f */
    public String m17709f() {
        return this.f12199m;
    }

    /* renamed from: g */
    public Address m17707g() {
        return this.f12200n;
    }

    /* renamed from: h */
    public String m17705h() {
        return this.f12201o;
    }

    /* renamed from: i */
    public String m17704i() {
        return this.f12202p;
    }

    /* renamed from: j */
    public String m17703j() {
        return this.f12203q;
    }

    /* renamed from: a */
    void m17720a(Address address) {
        this.f12196j = address;
    }

    /* renamed from: a */
    void m17719a(String str) {
        this.f12197k = str;
    }

    /* renamed from: b */
    void m17716b(String str) {
        this.f12198l = str;
    }

    /* renamed from: c */
    void m17714c(String str) {
        this.f12199m = str;
    }

    /* renamed from: b */
    void m17717b(Address address) {
        this.f12200n = address;
    }

    /* renamed from: d */
    void m17712d(String str) {
        this.f12201o = str;
    }

    /* renamed from: e */
    void m17710e(String str) {
        this.f12202p = str;
    }

    /* renamed from: f */
    void m17708f(String str) {
        this.f12203q = str;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        Address address = this.f12196j;
        if (address != null) {
            hashMap.put(f12188b, address.mo17623b());
        }
        hashMap.put("email", this.f12197k);
        hashMap.put("name", this.f12198l);
        hashMap.put("phone", this.f12199m);
        Address address2 = this.f12200n;
        if (address2 != null) {
            hashMap.put(f12192f, address2.mo17623b());
        }
        hashMap.put(f12193g, this.f12201o);
        hashMap.put(f12194h, this.f12202p);
        hashMap.put(f12195i, this.f12203q);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        Address address = this.f12196j;
        JSONObject jSONObject2 = null;
        JSONObject a = address == null ? null : address.mo17628a();
        Address address2 = this.f12200n;
        if (address2 != null) {
            jSONObject2 = address2.mo17628a();
        }
        if (a != null) {
            try {
                if (a.length() > 0) {
                    jSONObject.put(f12188b, a);
                }
            } catch (JSONException unused) {
            }
        }
        StripeJsonUtils.m17612a(jSONObject, "email", this.f12197k);
        StripeJsonUtils.m17612a(jSONObject, "name", this.f12198l);
        StripeJsonUtils.m17612a(jSONObject, "phone", this.f12199m);
        if (jSONObject2 != null && jSONObject2.length() > 0) {
            jSONObject.put(f12192f, jSONObject2);
        }
        StripeJsonUtils.m17612a(jSONObject, f12193g, this.f12201o);
        StripeJsonUtils.m17612a(jSONObject, f12194h, this.f12202p);
        StripeJsonUtils.m17612a(jSONObject, f12195i, this.f12203q);
        return jSONObject;
    }

    @Nullable
    /* renamed from: g */
    public static SourceOwner m17706g(@Nullable String str) {
        try {
            return m17718a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static SourceOwner m17718a(@Nullable JSONObject jSONObject) {
        Address address;
        Address address2;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(f12188b);
        if (optJSONObject != null) {
            address = Address.m17968a(optJSONObject);
        } else {
            address = null;
        }
        String e = StripeJsonUtils.m17603e(jSONObject, "email");
        String e2 = StripeJsonUtils.m17603e(jSONObject, "name");
        String e3 = StripeJsonUtils.m17603e(jSONObject, "phone");
        JSONObject optJSONObject2 = jSONObject.optJSONObject(f12192f);
        if (optJSONObject2 != null) {
            address2 = Address.m17968a(optJSONObject2);
        } else {
            address2 = null;
        }
        return new SourceOwner(address, e, e2, e3, address2, StripeJsonUtils.m17603e(jSONObject, f12193g), StripeJsonUtils.m17603e(jSONObject, f12194h), StripeJsonUtils.m17603e(jSONObject, f12195i));
    }
}
