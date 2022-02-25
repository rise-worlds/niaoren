package com.tendcloud.tenddata;

import android.support.p003v4.app.NotificationCompat;
import com.tendcloud.tenddata.C3034zz;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gk */
/* loaded from: classes2.dex */
public class C2911gk {

    /* renamed from: a */
    private static volatile C2911gk f14074a;

    /* renamed from: a */
    private void m15600a(Map map) {
    }

    public final void onTDEBEventAppEvent(C3034zz.C3035a aVar) {
        if (aVar != null) {
            try {
                if (aVar.paraMap != null && Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType"))) == 2) {
                    AbstractC2790d dVar = (AbstractC2790d) aVar.paraMap.get(NotificationCompat.CATEGORY_SERVICE);
                    TreeMap treeMap = new TreeMap();
                    String valueOf = String.valueOf(aVar.paraMap.get("eventId"));
                    if (C2664ab.f13510d.size() > 0 && valueOf != null) {
                        treeMap.putAll(C2664ab.f13510d);
                    }
                    Object obj = aVar.paraMap.get("map");
                    if (obj != null && (obj instanceof Map)) {
                        treeMap.putAll((Map) obj);
                    }
                    C2947ho hoVar = new C2947ho();
                    hoVar.f14181b = "appEvent";
                    hoVar.f14182c = valueOf;
                    hoVar.f14180a = dVar;
                    TreeMap treeMap2 = new TreeMap();
                    treeMap2.put("eventLabel", String.valueOf(aVar.paraMap.get("eventLabel")));
                    Map b = m15599b(treeMap);
                    m15600a(b);
                    treeMap2.put("eventParam", new JSONObject(b));
                    hoVar.f14183d = treeMap2;
                    C2858ev.m15778a().post(hoVar);
                }
            } catch (NumberFormatException e) {
                C2933hb.postSDKError(e);
            }
        }
    }

    /* renamed from: a */
    public static C2911gk m15601a() {
        if (f14074a == null) {
            synchronized (C2911gk.class) {
                if (f14074a == null) {
                    f14074a = new C2911gk();
                }
            }
        }
        return f14074a;
    }

    /* renamed from: b */
    private Map m15599b(Map map) {
        TreeMap treeMap = new TreeMap();
        if (map != null) {
            try {
                if (map.size() != 0) {
                    int i = 0;
                    for (Map.Entry entry : map.entrySet()) {
                        if (entry.getValue() instanceof Number) {
                            treeMap.put(entry.getKey(), entry.getValue());
                        } else {
                            treeMap.put(C2855es.m15800a(String.valueOf(entry.getKey())), C2855es.m15800a(String.valueOf(entry.getValue())));
                        }
                        i++;
                        if (i == 50) {
                            break;
                        }
                    }
                } else {
                    return treeMap;
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
        return treeMap;
    }

    private C2911gk() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15601a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
