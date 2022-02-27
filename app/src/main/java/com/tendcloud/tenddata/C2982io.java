package com.tendcloud.tenddata;

import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.ChannelReader;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.io */
/* loaded from: classes2.dex */
public class C2982io extends AbstractC2984iq {

    /* renamed from: a */
    private static HashMap f14263a = new HashMap();

    /* renamed from: c */
    private static HashMap f14264c = new HashMap();

    /* renamed from: f */
    private static volatile C2982io f14265f = null;

    /* renamed from: d */
    private final String[] f14266d = {"PUSH", "EAuth"};

    /* renamed from: e */
    private final String[] f14267e = {"APP", "TRACKING", "GAME", "BG", "FINTECH"};

    private C2982io() {
        m15410a("displayName", C2810dp.m16048a().m16040h(C2664ab.f13513g));
        m15410a("globalId", C2810dp.m16048a().m16047a(C2664ab.f13513g));
        m15410a("versionName", C2812dr.m16005l());
        m15410a("versionCode", Integer.valueOf(C2812dr.m16006k()));
        m15410a("installTime", Long.valueOf(C2810dp.m16048a().m16044d(C2664ab.f13513g)));
        m15410a("updateTime", Long.valueOf(C2810dp.m16048a().m16043e(C2664ab.f13513g)));
    }

    /* renamed from: a */
    public void m15414a(Object obj, AbstractC2790d dVar) {
        f14263a.put(dVar.name(), obj);
    }

    /* renamed from: b */
    public void m15412b(Object obj, AbstractC2790d dVar) {
        f14264c.put(dVar.name(), obj);
    }

    /* renamed from: b */
    private ArrayList m15413b() {
        ArrayList arrayList = new ArrayList();
        try {
            for (Map.Entry entry : f14263a.entrySet()) {
                arrayList.add(AbstractC2790d.valueOf(entry.getKey().toString()));
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public void setSubmitAppId(AbstractC2790d dVar) {
        if (dVar != null) {
            try {
                Object obj = f14263a.get(dVar.name());
                if (obj == null && f14263a.size() > 0) {
                    obj = m15415a(dVar);
                }
                m15410a("appKey", obj);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    private Object m15415a(AbstractC2790d dVar) {
        String str;
        String str2;
        if (dVar == null) {
            return null;
        }
        try {
            if (Arrays.asList(this.f14266d).contains(dVar.name())) {
                if (!dVar.name().equals(AbstractC2790d.PUSH.name()) || !dVar.name().equals(AbstractC2790d.APP.name())) {
                    str2 = null;
                    str = null;
                } else {
                    str2 = "app";
                    str = C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP);
                }
                if (dVar.name().equals(AbstractC2790d.PUSH.name()) && !AbstractC2790d.getFeaturesNameList().contains("GAME") && !AbstractC2790d.getFeaturesNameList().contains("APP")) {
                    str2 = "push";
                    str = C2664ab.m16358a(C2664ab.f13513g, dVar);
                }
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appKey", str);
                jSONObject.put(NotificationCompat.CATEGORY_SERVICE, str2);
                jSONArray.put(jSONObject);
                try {
                    if (!C2855es.m15791b(str)) {
                        m15414a(jSONArray, dVar);
                    }
                    return jSONArray;
                } catch (Throwable unused) {
                    return jSONArray;
                }
            } else if (!Arrays.asList(this.f14267e).contains(dVar.name())) {
                return f14263a.get(((AbstractC2790d) m15413b().get(0)).name());
            } else {
                Object a = C2664ab.m16358a(C2664ab.f13513g, dVar);
                if (C2855es.m15791b((String) a)) {
                    return a;
                }
                m15414a(a, dVar);
                return a;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    public void setSubmitChannelId(AbstractC2790d dVar) {
        if (dVar != null) {
            try {
                Object obj = f14264c.get(dVar.name());
                if (obj == null && f14264c.size() > 0) {
                    obj = f14264c.get(((AbstractC2790d) m15413b().get(0)).name());
                }
                m15410a(ChannelReader.f15967a, obj);
            } catch (Throwable unused) {
            }
        } else {
            setAppChannel("Default");
        }
    }

    public void setAppChannel(String str) {
        m15410a(ChannelReader.f15967a, str);
    }

    public void setUniqueId(String str) {
        m15410a("uniqueId", str);
    }

    /* renamed from: a */
    public static C2982io m15416a() {
        if (f14265f == null) {
            synchronized (C2969ig.class) {
                if (f14265f == null) {
                    f14265f = new C2982io();
                }
            }
        }
        return f14265f;
    }
}
