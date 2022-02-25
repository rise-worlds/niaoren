package com.tendcloud.tenddata;

import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C3857aq;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ir */
/* loaded from: classes2.dex */
public class C2985ir extends AbstractC2984iq {

    /* renamed from: a */
    public static final String f14269a = "TalkingData";

    /* renamed from: c */
    public static final String f14270c = "SaaS";

    /* renamed from: d */
    public static final int f14271d = 0;

    /* renamed from: e */
    public static final int f14272e = 1;

    /* renamed from: f */
    public static final int f14273f = 2;

    /* renamed from: g */
    public static final int f14274g = 3;

    /* renamed from: h */
    public static final int f14275h = 4;

    /* renamed from: j */
    private static String f14276j = "";

    /* renamed from: k */
    private static int f14277k = 1;

    /* renamed from: i */
    private final String f14278i = "Android";

    public C2985ir() {
        m15410a(ClientCookie.VERSION_ATTR, (Object) 4);
        m15410a("minorVersion", (Object) 0);
        m15410a("build", (Object) 30);
        if (!C2855es.m15791b(C2664ab.f13526t)) {
            try {
                m15410a("jobNum", Integer.valueOf(Integer.parseInt(C2664ab.f13526t)));
            } catch (Throwable unused) {
            }
        }
        m15410a(C3857aq.f17246ak, f14270c);
        m15410a("platform", "Android");
        m15410a("type", f14269a);
        m15410a("framework", f14276j);
        int i = f14277k;
        if (i > 0) {
            m15410a("from", Integer.valueOf(i));
        }
    }

    public void setFrameWork(String str) {
        f14276j = str;
    }

    /* renamed from: b */
    public String m15406b() {
        return f14276j;
    }

    /* renamed from: a */
    public void m15407a(String str, String str2, String str3) {
        JSONArray jSONArray;
        if (this.f14268b.isNull("features")) {
            jSONArray = new JSONArray();
        } else {
            try {
                jSONArray = this.f14268b.getJSONArray("features");
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray = null;
            }
        }
        if (jSONArray != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", str);
                jSONObject.put(ClientCookie.VERSION_ATTR, str2);
                jSONObject.put("minorVersion", str3);
                jSONArray.put(jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
                C2933hb.postSDKError(e2);
            }
            m15410a("features", jSONArray);
        }
    }
}
