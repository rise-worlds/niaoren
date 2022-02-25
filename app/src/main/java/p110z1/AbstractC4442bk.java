package p110z1;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.app.C0662k;
import com.bumptech.glide.BuildConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C4268bf;

/* renamed from: z1.bk */
/* loaded from: classes3.dex */
public abstract class AbstractC4442bk {

    /* renamed from: a */
    public static final String f19086a = "msp-gzip";

    /* renamed from: b */
    public static final String f19087b = "Msp-Param";

    /* renamed from: c */
    public static final String f19088c = "Operation-Type";

    /* renamed from: d */
    public static final String f19089d = "content-type";

    /* renamed from: e */
    public static final String f19090e = "Version";

    /* renamed from: f */
    public static final String f19091f = "AppId";

    /* renamed from: g */
    public static final String f19092g = "des-mode";

    /* renamed from: h */
    public static final String f19093h = "namespace";

    /* renamed from: i */
    public static final String f19094i = "api_name";

    /* renamed from: j */
    public static final String f19095j = "api_version";

    /* renamed from: k */
    public static final String f19096k = "data";

    /* renamed from: l */
    public static final String f19097l = "params";

    /* renamed from: m */
    public static final String f19098m = "public_key";

    /* renamed from: n */
    public static final String f19099n = "device";

    /* renamed from: o */
    public static final String f19100o = "action";

    /* renamed from: p */
    public static final String f19101p = "type";

    /* renamed from: q */
    public static final String f19102q = "method";

    /* renamed from: r */
    protected boolean f19103r = true;

    /* renamed from: s */
    protected boolean f19104s = true;

    /* renamed from: a */
    protected abstract JSONObject mo9540a() throws JSONException;

    /* renamed from: b */
    protected String mo9573b() {
        return BuildConfig.VERSION_NAME;
    }

    /* renamed from: a */
    protected Map<String, String> mo9546a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(f19086a, String.valueOf(z));
        hashMap.put(f19088c, "alipay.msp.cashier.dispatch.bytes");
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put(f19090e, EjbJar.CMPVersion.CMP2_0);
        hashMap.put(f19091f, "TAOBAO");
        hashMap.put(f19087b, C4301bg.m9702a(str));
        hashMap.put(f19092g, "CBC");
        return hashMap;
    }

    /* renamed from: c */
    protected String mo9554c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("device", Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put(f19094i, "com.alipay.mcpay");
        hashMap.put(f19095j, mo9573b());
        return m9633a(hashMap, new HashMap<>());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static JSONObject m9634a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put(f19102q, str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    /* renamed from: a */
    protected String mo9547a(C4745bt btVar, String str, JSONObject jSONObject) {
        C4759bu a = C4759bu.m9273a();
        C4814by a2 = C4814by.m8377a(a.m9271b());
        JSONObject a3 = C4900cc.m5682a(new JSONObject(), jSONObject);
        try {
            a3.put("tid", a2.m8378a());
            a3.put(C3877as.f17450b, a.m9270c().m9862a(btVar, a2));
            a3.put(C3877as.f17453e, C5019co.m4483b(btVar, a.m9271b(), C0662k.f328a));
            a3.put(C3877as.f17454f, C5019co.m4502a(a.m9271b()));
            a3.put(C3877as.f17452d, str);
            a3.put(C3877as.f17456h, C3876ar.f17429f);
            a3.put(C3877as.f17455g, a.m9268e());
            a3.put(C3877as.f17458j, a2.m8373b());
            a3.put(C3877as.f17459k, C3937aw.m9865a(a.m9271b()));
        } catch (Throwable th) {
            C4921cd.m5618a(th);
        }
        return a3.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static boolean m9632a(C4268bf.C4270b bVar) {
        return Boolean.valueOf(m9631a(bVar, f19086a)).booleanValue();
    }

    /* renamed from: a */
    protected static String m9631a(C4268bf.C4270b bVar, String str) {
        List<String> list;
        if (bVar == null || str == null || bVar.f18559a == null || (list = bVar.f18559a.get(str)) == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String m9633a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (hashMap2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static boolean m9635a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has("params")) {
                return false;
            }
            String optString = jSONObject.getJSONObject("params").optString(f19098m, null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            C3937aw.m9864a(optString);
            return true;
        } catch (JSONException e) {
            C4921cd.m5618a(e);
            return false;
        }
    }

    /* renamed from: a */
    public C4330bh m9630a(C4745bt btVar, Context context) throws Throwable {
        return mo9548a(btVar, context, "");
    }

    /* renamed from: a */
    public C4330bh mo9548a(C4745bt btVar, Context context, String str) throws Throwable {
        return m9629a(btVar, context, str, C4998cn.m4582a(context));
    }

    /* renamed from: a */
    public C4330bh m9629a(C4745bt btVar, Context context, String str, String str2) throws Throwable {
        return m9628a(btVar, context, str, str2, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public C4330bh m9628a(C4745bt btVar, Context context, String str, String str2, boolean z) throws Throwable {
        C4921cd.m5620a(C3876ar.f17447x, "Packet: " + str2);
        C4369bi biVar = new C4369bi(this.f19104s);
        C4330bh bhVar = new C4330bh(mo9554c(), mo9547a(btVar, str, mo9540a()));
        Map<String, String> a = mo9546a(false, str);
        C4409bj a2 = biVar.m9679a(bhVar, this.f19103r, a.get("iSr"));
        C4268bf.C4270b a3 = C4268bf.m9708a(context, new C4268bf.C4269a(str2, mo9546a(a2.m9640a(), str), a2.m9639b()));
        if (a3 != null) {
            C4330bh a4 = biVar.m9678a(new C4409bj(m9632a(a3), a3.f18561c), a.get("iSr"));
            return (a4 == null || !m9635a(a4.m9691a()) || !z) ? a4 : m9628a(btVar, context, str, str2, false);
        }
        throw new RuntimeException("Response is null.");
    }
}
