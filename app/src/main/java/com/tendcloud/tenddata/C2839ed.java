package com.tendcloud.tenddata;

import java.util.Comparator;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ed */
/* loaded from: classes2.dex */
final class C2839ed implements Comparator {
    public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            return jSONObject2.getInt("asuLevel") - jSONObject.getInt("asuLevel");
        } catch (Throwable unused) {
            return 0;
        }
    }
}
