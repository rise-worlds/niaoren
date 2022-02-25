package com.tendcloud.tenddata;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.iq */
/* loaded from: classes2.dex */
public abstract class AbstractC2984iq {

    /* renamed from: b */
    protected JSONObject f14268b = new JSONObject();

    /* renamed from: a_ */
    public Object mo15408a_() {
        return this.f14268b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15410a(String str, Object obj) {
        if (obj != null) {
            try {
                if (!m15411a(obj)) {
                    this.f14268b.put(str, obj);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15409a(String str, JSONObject jSONObject) {
        if (str != null) {
            try {
                if (jSONObject.has(str)) {
                    jSONObject.remove(str);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    protected boolean m15411a(Object obj) {
        return obj instanceof JSONObject ? ((JSONObject) obj).length() <= 0 : (obj instanceof JSONArray) && ((JSONArray) obj).length() <= 0;
    }
}
