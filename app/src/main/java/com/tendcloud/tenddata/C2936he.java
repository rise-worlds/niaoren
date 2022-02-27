package com.tendcloud.tenddata;

import android.support.v4.app.NotificationCompat;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.he */
/* loaded from: classes2.dex */
public class C2936he {

    /* renamed from: a */
    private static volatile C2936he f14145a;

    public final void onTDEBEventSession(C3034zz.C3035a aVar) {
        if (aVar != null && aVar.paraMap != null) {
            try {
                int parseInt = Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType")));
                if (parseInt == 10) {
                    m15517a(aVar.paraMap);
                } else if (parseInt == 11) {
                    m15515b(aVar.paraMap);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private final void m15517a(HashMap hashMap) {
        try {
            AbstractC2790d dVar = (AbstractC2790d) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            long parseLong = Long.parseLong(String.valueOf(hashMap.get("occurTime")));
            long c = C2812dr.m16019c(dVar);
            long f = C2812dr.m16011f(dVar);
            if (f <= c) {
                f = c;
            }
            if (parseLong - f > C2664ab.f13498O) {
                m15518a(dVar);
                m15519a(parseLong, dVar);
                C2812dr.setLastActivity("");
            } else {
                C2811dq.iForDeveloper("[Session] - Same session as before!");
                C2979il.m15417a().setSessionId(C2812dr.m16032a(dVar));
                C2979il.m15417a().setSessionStartTime(c);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15518a(AbstractC2790d dVar) {
        try {
            String a = C2812dr.m16032a(dVar);
            if (a != null && !a.trim().isEmpty()) {
                long c = C2812dr.m16019c(dVar);
                long f = C2812dr.m16011f(dVar) - c;
                if ((dVar.name().equals("APP") || dVar.name().equals("APP_SQL") || dVar.name().equals("TRACKING") || dVar.name().equals("FINTECH")) && f < 500) {
                    f = -1000;
                }
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "session";
                hoVar.f14182c = "end";
                TreeMap treeMap = new TreeMap();
                treeMap.put("sessionId", a);
                treeMap.put("start", Long.valueOf(c));
                treeMap.put("duration", Long.valueOf(f / 1000));
                hoVar.f14183d = treeMap;
                hoVar.f14180a = dVar;
                C2858ev.m15778a().post(hoVar);
                m15516b(dVar);
                C2812dr.m16030a((String) null, dVar);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15519a(long j, AbstractC2790d dVar) {
        try {
            C2811dq.iForDeveloper("[Session] - New session!");
            String uuid = UUID.randomUUID().toString();
            C2811dq.iForDeveloper("[Session] - Id: " + uuid);
            long f = C2812dr.m16011f(dVar);
            long j2 = j - f;
            if (0 == f) {
                j2 = 0;
            }
            C2812dr.m16030a(uuid, dVar);
            C2812dr.m16033a(j, dVar);
            C2812dr.m16023b(uuid, dVar);
            C2979il.m15417a().setSessionId(uuid);
            C2979il.m15417a().setSessionStartTime(j);
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "session";
            hoVar.f14182c = "begin";
            TreeMap treeMap = new TreeMap();
            treeMap.put("sessionId", uuid);
            treeMap.put("interval", Long.valueOf(j2 / 1000));
            hoVar.f14183d = treeMap;
            hoVar.f14180a = dVar;
            C2858ev.m15778a().post(hoVar);
            C2664ab.f13497N.set(false);
            m15516b(dVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private final void m15515b(HashMap hashMap) {
        try {
            AbstractC2790d dVar = (AbstractC2790d) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            long parseLong = Long.parseLong(String.valueOf(hashMap.get("occurTime")));
            if (hashMap.containsKey("sessionEnd")) {
                m15518a(dVar);
                return;
            }
            if (hashMap.containsKey("pageName")) {
                C2812dr.setLastActivity(String.valueOf(hashMap.get("pageName")));
            }
            m15516b(dVar);
            C2812dr.m16020c(parseLong, dVar);
            C2664ab.f13486C = null;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m15516b(AbstractC2790d dVar) {
        C2945hn hnVar = new C2945hn();
        hnVar.f14178a = dVar;
        hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
        C2858ev.m15778a().post(hnVar);
    }

    /* renamed from: a */
    public static C2936he m15520a() {
        if (f14145a == null) {
            synchronized (C2936he.class) {
                if (f14145a == null) {
                    f14145a = new C2936he();
                }
            }
        }
        return f14145a;
    }

    private C2936he() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15520a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
