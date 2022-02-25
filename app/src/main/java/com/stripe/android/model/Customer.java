package com.stripe.android.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.stripe.android.StripeNetworkUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.stripe.android.model.d */
/* loaded from: classes2.dex */
public class Customer extends StripeJsonModel {

    /* renamed from: a */
    private static final String f12072a = "id";

    /* renamed from: b */
    private static final String f12073b = "object";

    /* renamed from: c */
    private static final String f12074c = "default_source";

    /* renamed from: d */
    private static final String f12075d = "shipping";

    /* renamed from: e */
    private static final String f12076e = "sources";

    /* renamed from: f */
    private static final String f12077f = "data";

    /* renamed from: g */
    private static final String f12078g = "has_more";

    /* renamed from: h */
    private static final String f12079h = "total_count";

    /* renamed from: i */
    private static final String f12080i = "url";

    /* renamed from: j */
    private static final String f12081j = "list";

    /* renamed from: k */
    private static final String f12082k = "customer";

    /* renamed from: l */
    private static final String f12083l = "apple_pay";
    @Nullable

    /* renamed from: m */
    private String f12084m;
    @Nullable

    /* renamed from: n */
    private String f12085n;
    @Nullable

    /* renamed from: o */
    private ShippingInformation f12086o;
    @NonNull

    /* renamed from: p */
    private List<CustomerSource> f12087p = new ArrayList();
    @Nullable

    /* renamed from: q */
    private Boolean f12088q;
    @Nullable

    /* renamed from: r */
    private Integer f12089r;
    @Nullable

    /* renamed from: s */
    private String f12090s;

    private Customer() {
    }

    /* renamed from: c */
    public String m17813c() {
        return this.f12084m;
    }

    /* renamed from: d */
    public String m17812d() {
        return this.f12085n;
    }

    /* renamed from: e */
    public ShippingInformation m17811e() {
        return this.f12086o;
    }

    @NonNull
    /* renamed from: f */
    public List<CustomerSource> m17810f() {
        return this.f12087p;
    }

    /* renamed from: g */
    public Boolean m17809g() {
        return this.f12088q;
    }

    /* renamed from: h */
    public Integer m17808h() {
        return this.f12089r;
    }

    /* renamed from: i */
    public String m17807i() {
        return this.f12090s;
    }

    @Nullable
    /* renamed from: a */
    public CustomerSource m17816a(@NonNull String str) {
        for (CustomerSource eVar : this.f12087p) {
            if (str.equals(eVar.mo17592A())) {
                return eVar;
            }
        }
        return null;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: a */
    public JSONObject mo17628a() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject, "id", this.f12084m);
        StripeJsonUtils.m17612a(jSONObject, f12073b, f12082k);
        StripeJsonUtils.m17612a(jSONObject, f12074c, this.f12085n);
        StripeJsonModel.m17625a(jSONObject, f12075d, this.f12086o);
        JSONObject jSONObject2 = new JSONObject();
        StripeJsonUtils.m17612a(jSONObject2, f12073b, "list");
        StripeJsonUtils.m17616a(jSONObject2, f12078g, this.f12088q);
        StripeJsonUtils.m17614a(jSONObject2, f12079h, this.f12089r);
        m17624a(jSONObject2, "data", this.f12087p);
        StripeJsonUtils.m17612a(jSONObject2, "url", this.f12090s);
        StripeJsonUtils.m17610a(jSONObject, f12076e, jSONObject2);
        return jSONObject;
    }

    @Override // com.stripe.android.model.StripeJsonModel
    @NonNull
    /* renamed from: b */
    public Map<String, Object> mo17623b() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.f12084m);
        hashMap.put(f12073b, f12082k);
        hashMap.put(f12074c, this.f12085n);
        StripeJsonModel.m17627a(hashMap, f12075d, this.f12086o);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(f12078g, this.f12088q);
        hashMap2.put(f12079h, this.f12089r);
        hashMap2.put(f12073b, "list");
        hashMap2.put("url", this.f12090s);
        StripeJsonModel.m17626a(hashMap2, "data", this.f12087p);
        StripeNetworkUtils.m17470a(hashMap2);
        hashMap.put(f12076e, hashMap2);
        StripeNetworkUtils.m17470a(hashMap);
        return hashMap;
    }

    @Nullable
    /* renamed from: b */
    public static Customer m17814b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return m17815a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    /* renamed from: a */
    public static Customer m17815a(JSONObject jSONObject) {
        if (!f12082k.equals(StripeJsonUtils.m17603e(jSONObject, f12073b))) {
            return null;
        }
        Customer dVar = new Customer();
        dVar.f12084m = StripeJsonUtils.m17603e(jSONObject, "id");
        dVar.f12085n = StripeJsonUtils.m17603e(jSONObject, f12074c);
        dVar.f12086o = ShippingInformation.m17940a(jSONObject.optJSONObject(f12075d));
        JSONObject optJSONObject = jSONObject.optJSONObject(f12076e);
        if (optJSONObject != null && "list".equals(StripeJsonUtils.m17603e(optJSONObject, f12073b))) {
            dVar.f12088q = StripeJsonUtils.m17607b(optJSONObject, f12078g);
            dVar.f12089r = StripeJsonUtils.m17605c(optJSONObject, f12079h);
            dVar.f12090s = StripeJsonUtils.m17603e(optJSONObject, "url");
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = optJSONObject.optJSONArray("data");
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    CustomerSource a = CustomerSource.m17805a(optJSONArray.getJSONObject(i));
                    if (a != null && !f12083l.equals(a.m17802e())) {
                        arrayList.add(a);
                    }
                } catch (JSONException unused) {
                }
            }
            dVar.f12087p = arrayList;
        }
        return dVar;
    }
}
