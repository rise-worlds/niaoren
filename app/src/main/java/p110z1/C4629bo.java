package p110z1;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.bo */
/* loaded from: classes3.dex */
public class C4629bo extends AbstractC4442bk {

    /* renamed from: t */
    public static final String f19624t = "log_v";

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected String mo9547a(C4745bt btVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected JSONObject mo9540a() throws JSONException {
        return null;
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected Map<String, String> mo9546a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractC4442bk.f19086a, String.valueOf(z));
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put(AbstractC4442bk.f19092g, "CBC");
        return hashMap;
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: c */
    protected String mo9554c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(AbstractC4442bk.f19094i, "/sdk/log");
        hashMap.put(AbstractC4442bk.f19095j, "1.0.0");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put(f19624t, "1.0");
        return m9633a(hashMap, hashMap2);
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    public C4330bh mo9548a(C4745bt btVar, Context context, String str) throws Throwable {
        return m9628a(btVar, context, str, C3876ar.f17426c, true);
    }
}
