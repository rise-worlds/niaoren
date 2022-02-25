package com.tendcloud.tenddata;

import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fp */
/* loaded from: classes2.dex */
public class C2887fp extends AbstractC2894fv {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C2887fp(AbstractC2885fn fnVar) {
        super(fnVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.tendcloud.tenddata.AbstractC2894fv
    /* renamed from: a */
    public JSONObject mo15678a(double[] dArr) {
        JSONObject jSONObject = new JSONObject();
        if (dArr[0] == 1.0d) {
            jSONObject.put("status", true);
        } else if (dArr[1] == 1.0d) {
            jSONObject.put("status", false);
        }
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }
}
