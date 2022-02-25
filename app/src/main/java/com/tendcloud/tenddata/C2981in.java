package com.tendcloud.tenddata;

import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.in */
/* loaded from: classes2.dex */
public class C2981in extends AbstractC2984iq {
    public C2981in(String str, String str2) {
        m15410a(ClientCookie.DOMAIN_ATTR, str);
        m15410a("name", str2);
    }

    public void setData(Map map) {
        if (map != null) {
            m15410a("data", (Object) new JSONObject(map));
        }
    }
}
