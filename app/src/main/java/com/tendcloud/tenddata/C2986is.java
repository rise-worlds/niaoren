package com.tendcloud.tenddata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.is */
/* loaded from: classes2.dex */
public class C2986is extends AbstractC2984iq {

    /* renamed from: a */
    public static final String f14279a = "accounts";

    /* renamed from: b */
    public void m15405b() {
        m15410a(f14279a, C2854er.m15810d(C2664ab.f13513g));
    }

    /* renamed from: c */
    public void m15404c() {
        try {
            JSONObject jSONObject = (JSONObject) mo15408a_();
            if (jSONObject.has(f14279a)) {
                JSONArray jSONArray = jSONObject.getJSONArray(f14279a);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if ("sim".equals(jSONArray.getJSONObject(i).getString("type"))) {
                        JSONArray a = C2913gm.m15591a().m15583a("IMEI", C2998jc.m15383a().f14301a, C2998jc.m15383a().f14302b);
                        if (a != null) {
                            jSONArray.getJSONObject(i).put("extra", C2836ec.m15895C(C2664ab.f13513g));
                            if (a.length() > 0) {
                                C2998jc.m15383a().m15379a("IMEI", a);
                            }
                        } else {
                            JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("extra");
                            if (jSONArray2 != null) {
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    m15409a("imei", jSONArray2.getJSONObject(i2));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void setUserAccount(C2980im imVar) {
        if (imVar != null && imVar.mo15408a_() != null) {
            if (this.f14268b.isNull(f14279a)) {
                new JSONArray().put(imVar.mo15408a_());
                m15410a(f14279a, imVar.mo15408a_());
                return;
            }
            try {
                this.f14268b.getJSONArray(f14279a).put(imVar.mo15408a_());
            } catch (JSONException e) {
                C2933hb.postSDKError(e);
                e.printStackTrace();
            }
        }
    }
}
