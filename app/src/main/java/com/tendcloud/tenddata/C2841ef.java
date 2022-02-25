package com.tendcloud.tenddata;

import java.util.Comparator;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ef */
/* loaded from: classes2.dex */
final class C2841ef implements Comparator {
    public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            return jSONObject2.getInt("level") - jSONObject.getInt("level");
        } catch (Throwable unused) {
            return 0;
        }
    }
}
