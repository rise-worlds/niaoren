package com.tendcloud.tenddata;

import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.il */
/* loaded from: classes2.dex */
public class C2979il extends AbstractC2984iq {

    /* renamed from: a */
    static C2979il f14257a;

    private C2979il() {
    }

    /* renamed from: a */
    public static synchronized C2979il m15417a() {
        C2979il ilVar;
        synchronized (C2979il.class) {
            if (f14257a == null) {
                f14257a = new C2979il();
            }
            ilVar = f14257a;
        }
        return ilVar;
    }

    public void setSessionId(String str) {
        m15410a("sessionId", str);
    }

    public void setCurrentPageName(String str) {
        m15410a("page", str);
    }

    public void setAccount(JSONObject jSONObject) {
        m15410a("account", (Object) jSONObject);
    }

    public void setSubaccount(JSONObject jSONObject) {
        m15410a("subaccount", (Object) jSONObject);
    }

    public void setDeepLink(String str) {
        try {
            m15410a("deeplink", str);
            C2812dr.setDeepLink(str);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void setSessionStartTime(long j) {
        m15410a("sessionStartTime", Long.valueOf(j));
    }

    public void setPushInfo(String str) {
        m15410a("push", str);
    }

    public void setAntiCheatingstatus(int i) {
        m15410a("antiCheating", Integer.valueOf(i));
    }
}
