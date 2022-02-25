package com.tendcloud.tenddata;

import java.util.Comparator;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ee */
/* loaded from: classes2.dex */
final class C2840ee implements Comparator {
    public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            return jSONObject.getInt("networkId") - jSONObject2.getInt("networkId");
        } catch (Throwable unused) {
            return 0;
        }
    }
}
