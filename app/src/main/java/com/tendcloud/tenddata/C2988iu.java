package com.tendcloud.tenddata;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.iu */
/* loaded from: classes2.dex */
public class C2988iu extends AbstractC2984iq {
    public C2988iu() {
        m15410a("tid", C2819dt.m15969a(C2664ab.f13513g));
        m15410a("serialNo", C2819dt.m15953h(C2664ab.f13513g) == null ? "" : C2819dt.m15953h(C2664ab.f13513g));
    }

    /* renamed from: b */
    public void m15401b() {
        JSONArray a = C2913gm.m15591a().m15585a("AdID");
        if (a != null) {
            m15410a("adId", C2819dt.m15954g(C2664ab.f13513g));
            if (a.length() > 0) {
                C2998jc.m15383a().m15379a("AdID", a);
            }
        } else {
            m15409a("adId", this.f14268b);
        }
        JSONArray a2 = C2913gm.m15591a().m15583a("IMEI", C2998jc.m15383a().f14301a, C2998jc.m15383a().f14302b);
        if (a2 != null) {
            try {
                JSONArray C = C2836ec.m15895C(C2664ab.f13513g);
                JSONArray jSONArray = new JSONArray();
                if (C != null && C.length() > 0) {
                    JSONObject jSONObject = C.getJSONObject(0);
                    if (jSONObject.has("imei")) {
                        jSONArray.put(jSONObject.get("imei"));
                    }
                    if (C.length() == 2) {
                        jSONArray.put(C.getJSONObject(1).get("imei"));
                    }
                }
                m15410a("imeis", jSONArray);
            } catch (Exception unused) {
            }
            if (a2.length() > 0) {
                C2998jc.m15383a().m15379a("IMEI", a2);
            }
        } else {
            m15409a("imeis", this.f14268b);
        }
        JSONArray a3 = C2913gm.m15591a().m15585a("MacAddress");
        if (a3 != null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(C2819dt.m15955f(C2664ab.f13513g));
            m15410a("wifiMacs", jSONArray2);
            if (a3.length() > 0) {
                C2998jc.m15383a().m15379a("MacAddress", a3);
            }
        } else {
            m15409a("wifiMacs", this.f14268b);
        }
        JSONArray a4 = C2913gm.m15591a().m15585a("AndroidId");
        if (a4 != null) {
            m15410a("androidId", C2819dt.m15960b(C2664ab.f13513g));
            if (a4.length() > 0) {
                C2998jc.m15383a().m15379a("AndroidId", a4);
                return;
            }
            return;
        }
        m15409a("androidId", this.f14268b);
    }
}
