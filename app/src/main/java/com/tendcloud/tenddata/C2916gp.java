package com.tendcloud.tenddata;

import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Marker;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gp */
/* loaded from: classes2.dex */
public class C2916gp {

    /* renamed from: a */
    public static final long f14105a = 259200000;

    /* renamed from: d */
    private static final long f14107d = 5;

    /* renamed from: g */
    private static final String f14110g = "frequency";

    /* renamed from: h */
    private static final String f14111h = "interval";

    /* renamed from: i */
    private static final String f14112i = "configVersion";

    /* renamed from: j */
    private static final String f14113j = "lastGetCloudSettingsTime";

    /* renamed from: k */
    private static final String f14114k = "SDKInitNumber";

    /* renamed from: l */
    private static final String f14115l = "TD";

    /* renamed from: b */
    protected final String f14117b = "TDCloudSettingsConfig" + C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP);

    /* renamed from: c */
    private static final String f14106c = C2663aa.f13439I + "?";

    /* renamed from: e */
    private static long f14108e = 0;

    /* renamed from: f */
    private static long f14109f = 0;

    /* renamed from: m */
    private static volatile C2916gp f14116m = null;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.gp$a */
    /* loaded from: classes2.dex */
    public static final class C2917a {
        public HashMap paraMap = new HashMap();
    }

    static {
        try {
            C2858ev.m15778a().register(m15567a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public static C2916gp m15567a() {
        if (f14116m == null) {
            synchronized (C2916gp.class) {
                if (f14116m == null) {
                    f14116m = new C2916gp();
                }
            }
        }
        return f14116m;
    }

    private C2916gp() {
        try {
            C2852ep.f13904a.execute(new RunnableC2918gq(this));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m15565b() {
        try {
            if (C2664ab.m16354b()) {
                C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14114k, 0L);
                return true;
            }
            C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14114k, C2843eh.m15841b(C2664ab.f13513g, this.f14117b, f14114k, 0L) + 1);
            long currentTimeMillis = System.currentTimeMillis() - C2843eh.m15841b(C2664ab.f13513g, this.f14117b, f14113j, System.currentTimeMillis());
            long b = C2843eh.m15841b(C2664ab.f13513g, this.f14117b, f14111h, 0L);
            long b2 = C2843eh.m15841b(C2664ab.f13513g, this.f14117b, f14110g, 1L);
            long b3 = C2843eh.m15841b(C2664ab.f13513g, this.f14117b, f14114k, 0L);
            if (currentTimeMillis < b && b3 < b2) {
                return false;
            }
            C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14114k, 0L);
            return true;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m15563c() {
        String b;
        String str;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("id=" + C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP));
            stringBuffer.append("&p=1");
            String c = C2810dp.m16048a().m16045c(C2664ab.f13513g);
            int b2 = C2810dp.m16048a().m16046b(C2664ab.f13513g);
            StringBuilder sb = new StringBuilder();
            sb.append("&v=");
            sb.append(URLEncoder.encode(c + Marker.ANY_NON_NULL_MARKER + b2, "UTF-8"));
            stringBuffer.append(sb.toString());
            stringBuffer.append("&sv=" + URLEncoder.encode(C2684as.f13538d.substring(1), "UTF-8"));
            if (C2843eh.m15840b(C2664ab.f13513g, this.f14117b, f14112i, "a").length() == 0) {
                str = "";
            } else {
                str = "&cv=" + URLEncoder.encode(b, "UTF-8");
            }
            stringBuffer.append(str);
            String a = C2813ds.m15984a(f14106c + stringBuffer.toString(), "", true);
            if (a != null && !C2855es.m15791b(a)) {
                C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14113j, System.currentTimeMillis());
                JSONObject jSONObject = new JSONObject(a);
                if (jSONObject.has("cv")) {
                    Object obj = jSONObject.get("cv");
                    C2843eh.m15842a(C2664ab.f13513g, this.f14117b, f14112i, obj + "");
                }
                if (jSONObject.has("pipline_settings")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("pipline_settings");
                    if (jSONObject2.has(f14110g)) {
                        f14108e = jSONObject2.getLong(f14110g);
                        f14108e = f14108e >= 5 ? 5L : f14108e < 1 ? 1L : f14108e;
                        C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14110g, f14108e);
                    }
                    if (jSONObject2.has(f14111h)) {
                        f14109f = jSONObject2.getLong(f14111h);
                        long j = f14108e;
                        long j2 = f14105a;
                        if (j < 1) {
                            j2 = 0;
                        } else if (f14109f <= f14105a) {
                            j2 = f14109f;
                        }
                        f14109f = j2;
                        C2843eh.m15843a(C2664ab.f13513g, this.f14117b, f14111h, f14109f);
                    }
                }
                if (jSONObject.has("events")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("events");
                    C2917a aVar = new C2917a();
                    aVar.paraMap.put("cloudSettingsType", "codeless");
                    aVar.paraMap.put("data", jSONArray);
                    C2858ev.m15778a().post(aVar);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
