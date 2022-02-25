package p110z1;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: z1.au */
/* loaded from: classes3.dex */
public final class C3894au {

    /* renamed from: C */
    private static C3894au f17517C = null;

    /* renamed from: a */
    public static final int f17518a = 3500;

    /* renamed from: b */
    public static final String f17519b = "https://h5.m.taobao.com/mlapp/olist.html";

    /* renamed from: c */
    public static final int f17520c = 10;

    /* renamed from: d */
    public static final boolean f17521d = true;

    /* renamed from: e */
    public static final boolean f17522e = true;

    /* renamed from: f */
    public static final boolean f17523f = false;

    /* renamed from: g */
    public static final int f17524g = 1000;

    /* renamed from: h */
    public static final int f17525h = 20000;

    /* renamed from: i */
    public static final String f17526i = "alipay_cashier_dynamic_config";

    /* renamed from: j */
    public static final String f17527j = "timeout";

    /* renamed from: k */
    public static final String f17528k = "h5_port_degrade";

    /* renamed from: l */
    public static final String f17529l = "st_sdk_config";

    /* renamed from: m */
    public static final String f17530m = "tbreturl";

    /* renamed from: n */
    public static final String f17531n = "launchAppSwitch";

    /* renamed from: o */
    public static final String f17532o = "configQueryInterval";

    /* renamed from: p */
    public static final String f17533p = "deg_log_mcgw";

    /* renamed from: q */
    public static final String f17534q = "scheme_pay_2";

    /* renamed from: r */
    public static final String f17535r = "intercept_batch";

    /* renamed from: t */
    private static final String f17536t = "DynCon";

    /* renamed from: u */
    private int f17540u = 3500;

    /* renamed from: v */
    private boolean f17541v = false;

    /* renamed from: w */
    private String f17542w = f17519b;

    /* renamed from: x */
    private int f17543x = 10;

    /* renamed from: y */
    private boolean f17544y = true;

    /* renamed from: z */
    private boolean f17545z = true;

    /* renamed from: s */
    public boolean f17539s = false;

    /* renamed from: A */
    private boolean f17537A = false;

    /* renamed from: B */
    private List<C3895a> f17538B = null;

    /* renamed from: a */
    public int m9982a() {
        int i = this.f17540u;
        if (i < 1000 || i > 20000) {
            C4921cd.m5620a(f17536t, "time(def) = 3500");
            return 3500;
        }
        C4921cd.m5620a(f17536t, "time = " + this.f17540u);
        return this.f17540u;
    }

    /* renamed from: b */
    public boolean m9975b() {
        return this.f17541v;
    }

    /* renamed from: c */
    public boolean m9973c() {
        return this.f17544y;
    }

    /* renamed from: d */
    public boolean m9972d() {
        return this.f17545z;
    }

    /* renamed from: e */
    public String m9971e() {
        return this.f17542w;
    }

    /* renamed from: f */
    public int m9970f() {
        return this.f17543x;
    }

    /* renamed from: g */
    public boolean m9969g() {
        return this.f17537A;
    }

    /* renamed from: h */
    public List<C3895a> m9968h() {
        return this.f17538B;
    }

    /* renamed from: a */
    public void m9976a(boolean z) {
        this.f17539s = z;
    }

    /* renamed from: i */
    public static C3894au m9967i() {
        if (f17517C == null) {
            f17517C = new C3894au();
            f17517C.m9966j();
        }
        return f17517C;
    }

    /* renamed from: j */
    private void m9966j() {
        m9981a(C4964ck.m5125b(C4745bt.m9418a(), C4759bu.m9273a().m9271b(), f17526i, null));
    }

    /* renamed from: a */
    private void m9981a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f17540u = jSONObject.optInt(f17527j, 3500);
                this.f17541v = jSONObject.optBoolean(f17528k, false);
                this.f17542w = jSONObject.optString(f17530m, f17519b).trim();
                this.f17543x = jSONObject.optInt(f17532o, 10);
                this.f17538B = C3895a.m9964a(jSONObject.optJSONArray(f17531n));
                this.f17544y = jSONObject.optBoolean(f17534q, true);
                this.f17545z = jSONObject.optBoolean(f17535r, true);
                this.f17537A = jSONObject.optBoolean(f17533p, false);
            } catch (Throwable th) {
                C4921cd.m5618a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m9978a(C4745bt btVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f17527j, m9982a());
            jSONObject.put(f17528k, m9975b());
            jSONObject.put(f17530m, m9971e());
            jSONObject.put(f17532o, m9970f());
            jSONObject.put(f17531n, C3895a.m9965a(m9968h()));
            jSONObject.put(f17534q, m9973c());
            jSONObject.put(f17535r, m9972d());
            jSONObject.put(f17533p, m9969g());
            C4964ck.m5127a(btVar, C4759bu.m9273a().m9271b(), f17526i, jSONObject.toString());
        } catch (Exception e) {
            C4921cd.m5618a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m9974b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(f17529l);
                if (optJSONObject != null) {
                    this.f17540u = optJSONObject.optInt(f17527j, 3500);
                    this.f17541v = optJSONObject.optBoolean(f17528k, false);
                    this.f17542w = optJSONObject.optString(f17530m, f17519b).trim();
                    this.f17543x = optJSONObject.optInt(f17532o, 10);
                    this.f17538B = C3895a.m9964a(optJSONObject.optJSONArray(f17531n));
                    this.f17544y = optJSONObject.optBoolean(f17534q, true);
                    this.f17545z = optJSONObject.optBoolean(f17535r, true);
                    this.f17537A = optJSONObject.optBoolean(f17533p, false);
                } else {
                    C4921cd.m5614c(f17536t, "empty config");
                }
            } catch (Throwable th) {
                C4921cd.m5618a(th);
            }
        }
    }

    /* renamed from: a */
    public void m9977a(C4745bt btVar, Context context) {
        new Thread(new RunnableC3896av(this, btVar, context)).start();
    }

    /* renamed from: z1.au$a */
    /* loaded from: classes3.dex */
    public static final class C3895a {

        /* renamed from: a */
        public final String f17546a;

        /* renamed from: b */
        public final int f17547b;

        /* renamed from: c */
        public final String f17548c;

        public C3895a(String str, int i, String str2) {
            this.f17546a = str;
            this.f17547b = i;
            this.f17548c = str2;
        }

        /* renamed from: a */
        public static C3895a m9963a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C3895a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        /* renamed from: a */
        public static List<C3895a> m9964a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C3895a a = m9963a(jSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        }

        /* renamed from: a */
        public static JSONObject m9962a(C3895a aVar) {
            if (aVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", aVar.f17546a).put("v", aVar.f17547b).put("pk", aVar.f17548c);
            } catch (JSONException e) {
                C4921cd.m5618a(e);
                return null;
            }
        }

        /* renamed from: a */
        public static JSONArray m9965a(List<C3895a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C3895a aVar : list) {
                jSONArray.put(m9962a(aVar));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(m9962a(this));
        }
    }
}
