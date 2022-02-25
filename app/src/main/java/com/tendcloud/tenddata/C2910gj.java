package com.tendcloud.tenddata;

import android.os.Build;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gj */
/* loaded from: classes2.dex */
public class C2910gj {

    /* renamed from: a */
    private static volatile C2910gj f14073a;

    /* renamed from: a */
    public static C2910gj m15607a() {
        if (f14073a == null) {
            synchronized (C2910gj.class) {
                if (f14073a == null) {
                    f14073a = new C2910gj();
                }
            }
        }
        return f14073a;
    }

    static {
        try {
            C2858ev.m15778a().register(m15607a());
        } catch (Throwable unused) {
        }
    }

    private C2910gj() {
    }

    public final void onTDEBEventAntiCheating(C2943hl hlVar) {
        if (hlVar != null && hlVar.f14173m != null) {
            try {
                int parseInt = Integer.parseInt(String.valueOf(hlVar.f14173m.get("eventType")));
                if (parseInt == 11 || parseInt == 12 || parseInt == 15 || parseInt == 16) {
                    if (parseInt == 16) {
                        m15605a(hlVar.f14173m);
                    }
                    m15606a(parseInt);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private void m15606a(int i) {
        try {
            C2943hl hlVar = new C2943hl();
            if (i != 15) {
                switch (i) {
                    case 11:
                        hlVar.f14173m.put("eventType", 13);
                        C2858ev.m15778a().post(hlVar);
                        break;
                    case 12:
                        hlVar.f14173m.put("eventType", 14);
                        C2858ev.m15778a().post(hlVar);
                        break;
                }
            } else {
                m15603b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15605a(Map map) {
        try {
            HashMap hashMap = (HashMap) map.get("regAppsMap");
            if (hashMap != null && hashMap.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (JSONObject jSONObject : hashMap.values()) {
                    jSONArray.put(jSONObject);
                }
                m15604a(jSONArray);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15604a(JSONArray jSONArray) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("regList", jSONArray);
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "antiCheating";
            hoVar.f14182c = "regApps";
            hoVar.f14183d = hashMap;
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m15603b() {
        try {
            C2944hm hmVar = new C2944hm();
            hmVar.f14174a = 3;
            C2858ev.m15778a().post(hmVar);
            C2944hm hmVar2 = new C2944hm();
            hmVar2.f14174a = 1;
            C2858ev.m15778a().post(hmVar2);
            C2943hl hlVar = new C2943hl();
            hlVar.f14173m.put("eventType", 16);
            C2858ev.m15778a().post(hlVar);
            SparseArray sparseArray = new SparseArray();
            if (Build.VERSION.SDK_INT >= 14) {
                sparseArray.put(sparseArray.size(), m15602b(13));
                sparseArray.put(sparseArray.size(), m15602b(12));
            }
            sparseArray.put(sparseArray.size(), m15602b(5));
            sparseArray.put(sparseArray.size(), m15602b(6));
            sparseArray.put(sparseArray.size(), m15602b(2));
            C2858ev.m15778a().post(sparseArray);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private C2944hm m15602b(int i) {
        C2944hm hmVar = new C2944hm();
        hmVar.f14176c = i;
        hmVar.f14177d = 0;
        return hmVar;
    }
}
