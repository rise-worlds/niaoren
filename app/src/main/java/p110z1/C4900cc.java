package p110z1;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.cc */
/* loaded from: classes3.dex */
public class C4900cc {
    /* renamed from: a */
    public static JSONObject m5682a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject[] jSONObjectArr;
        JSONObject jSONObject3 = new JSONObject();
        try {
            for (JSONObject jSONObject4 : new JSONObject[]{jSONObject, jSONObject2}) {
                if (jSONObject4 != null) {
                    Iterator<String> keys = jSONObject4.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject3.put(next, jSONObject4.get(next));
                    }
                }
            }
        } catch (JSONException e) {
            C4921cd.m5618a(e);
        }
        return jSONObject3;
    }
}
