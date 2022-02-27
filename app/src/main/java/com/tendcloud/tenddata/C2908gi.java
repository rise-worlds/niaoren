package com.tendcloud.tenddata;

import android.support.v4.app.NotificationCompat;

import java.util.Map;
import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.SchedulerSupport;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gi */
/* loaded from: classes2.dex */
public class C2908gi {

    /* renamed from: a */
    private static volatile C2908gi f14057a;

    /* renamed from: b */
    private static String f14058b;

    /* renamed from: c */
    private static String f14059c;

    /* renamed from: d */
    private static String f14060d;

    /* renamed from: e */
    private static String f14061e;

    /* renamed from: f */
    private static String f14062f;

    /* renamed from: g */
    private static String f14063g;

    /* renamed from: h */
    private static String f14064h;

    /* renamed from: j */
    private static String f14065j;

    /* renamed from: p */
    private static JSONObject f14066p;

    /* renamed from: i */
    private String f14067i;

    /* renamed from: k */
    private EnumC2909a f14068k = EnumC2909a.UNKNOWN;

    /* renamed from: l */
    private String f14069l;

    /* renamed from: m */
    private int f14070m;

    /* renamed from: n */
    private String f14071n;

    /* renamed from: o */
    private JSONObject f14072o;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.gi$a */
    /* loaded from: classes2.dex */
    public enum EnumC2909a {
        MALE,
        FEMALE,
        UNKNOWN
    }

    public final void onTDEBEventAccount(C3034zz.C3035a aVar) {
        if (aVar != null) {
            try {
                if (aVar.paraMap != null && Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType"))) == 9) {
                    aVar.paraMap.get("account");
                    AbstractC2790d dVar = (AbstractC2790d) aVar.paraMap.get(NotificationCompat.CATEGORY_SERVICE);
                    Object obj = aVar.paraMap.get("data");
                    Object obj2 = aVar.paraMap.get(ClientCookie.DOMAIN_ATTR);
                    Object obj3 = aVar.paraMap.get("action");
                    Object obj4 = aVar.paraMap.get("immediate");
                    if (obj3 != null && (obj3.equals("login") || obj3.equals("register") || obj3.equals("apply") || obj3.equals("activate"))) {
                        TreeMap treeMap = new TreeMap();
                        treeMap.put("accountId", String.valueOf(aVar.paraMap.get("accountId")));
                        String str = (String) aVar.paraMap.get("type");
                        String str2 = (String) aVar.paraMap.get("name");
                        if (str != null && !str.isEmpty()) {
                            treeMap.put("type", str);
                        }
                        if (str2 != null && !str2.isEmpty()) {
                            treeMap.put("name", str2);
                        }
                        m15620a(obj2, obj3, treeMap, dVar);
                    } else if (obj3 != null) {
                        m15620a(obj2, obj3, obj, dVar);
                        if (m15621a(obj4)) {
                            m15622a(dVar);
                        }
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private boolean m15621a(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private void m15622a(AbstractC2790d dVar) {
        try {
            C2945hn hnVar = new C2945hn();
            hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
            hnVar.f14178a = dVar;
            C2858ev.m15778a().post(hnVar);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    protected static void m15620a(Object obj, Object obj2, Object obj3, AbstractC2790d dVar) {
        if (dVar != null && obj != null && obj2 != null && (obj instanceof String) && (obj2 instanceof String)) {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = String.valueOf(obj);
            hoVar.f14182c = String.valueOf(obj2);
            if (obj3 != null && (obj3 instanceof Map)) {
                hoVar.f14183d = (Map) obj3;
            }
            hoVar.f14180a = dVar;
            C2858ev.m15778a().post(hoVar);
        }
    }

    /* renamed from: b */
    private void m15615b() {
        try {
            Map d = m15610d();
            C3034zz.C3035a aVar = new C3034zz.C3035a();
            aVar.paraMap.put("apiType", 9);
            aVar.paraMap.put(ClientCookie.DOMAIN_ATTR, f14058b);
            aVar.paraMap.put("action", "update");
            aVar.paraMap.put("data", d);
            C3034zz.m15206c().obtainMessage(102, aVar).sendToTarget();
            C2979il.m15417a().setAccount(new JSONObject(d));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15619a(String str) {
        try {
            this.f14067i = str;
            String a = C2812dr.m16031a(this.f14067i);
            if (a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a);
                    if (jSONObject.has(f14060d)) {
                        this.f14069l = jSONObject.getString(f14060d);
                    }
                    if (jSONObject.has(f14061e)) {
                        this.f14068k = EnumC2909a.valueOf(jSONObject.getString(f14061e));
                    }
                    if (jSONObject.has(f14062f)) {
                        this.f14070m = jSONObject.getInt(f14062f);
                    }
                    if (jSONObject.has(f14063g)) {
                        this.f14071n = jSONObject.getString(f14063g);
                    }
                    if (jSONObject.has(f14064h)) {
                        this.f14072o = jSONObject.getJSONObject(f14064h);
                    }
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void setName(String str) {
        try {
            if (this.f14069l == null || !this.f14069l.equalsIgnoreCase(str)) {
                this.f14069l = str;
                m15615b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void setGender(EnumC2909a aVar) {
        try {
            if (this.f14068k != aVar) {
                this.f14068k = aVar;
                m15615b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void setAge(int i) {
        try {
            if (this.f14070m != i) {
                this.f14070m = i;
                m15615b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void setAccountType(String str) {
        try {
            if (this.f14071n == null || !this.f14071n.equalsIgnoreCase(str)) {
                this.f14071n = str;
                m15615b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public static synchronized void m15617a(String str, AbstractC2790d dVar) {
        synchronized (C2908gi.class) {
            try {
                C2812dr.setLastRoleName(str);
                f14066p = null;
                f14065j = str;
                String b = C2812dr.m16024b(str);
                if (b != null) {
                    try {
                        f14066p = new JSONObject(b);
                        m15614b(dVar);
                    } catch (JSONException e) {
                        C2811dq.dForInternal(e.getMessage());
                    }
                } else {
                    f14066p = new JSONObject();
                    m15611c();
                    Map e2 = m15609e();
                    m15620a(f14058b, "roleCreate", e2, dVar);
                    C2979il.m15417a().setSubaccount(new JSONObject(e2));
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public synchronized void m15616a(String str, String str2) {
        if (f14066p == null) {
            f14066p = new JSONObject();
        }
        try {
            f14066p.put(str, str2);
            m15611c();
            m15608f();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public synchronized void m15618a(String str, int i) {
        if (f14066p == null) {
            f14066p = new JSONObject();
        }
        try {
            f14066p.put(str, i);
            m15611c();
            m15608f();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public synchronized void m15612b(String str, String str2) {
        if (this.f14072o == null) {
            this.f14072o = new JSONObject();
        }
        try {
            this.f14072o.put(str, str2);
            m15615b();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public synchronized void m15613b(String str, int i) {
        if (this.f14072o == null) {
            this.f14072o = new JSONObject();
        }
        try {
            this.f14072o.put(str, i);
            m15615b();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    private static void m15611c() {
        C2812dr.setLastRoleName(f14065j);
        C2812dr.m16022b(f14065j, f14066p.toString());
    }

    /* renamed from: d */
    private Map m15610d() {
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put(f14059c, this.f14067i);
            if (this.f14070m != 0) {
                treeMap.put(f14062f, Integer.valueOf(this.f14070m));
            }
            if (!"UNKNOWN".equals(this.f14068k.name())) {
                treeMap.put(f14061e, this.f14068k.name());
            }
            if (this.f14069l != null) {
                treeMap.put(f14060d, this.f14069l);
            }
            if (this.f14071n != null) {
                treeMap.put(f14063g, this.f14071n);
            }
            if (this.f14072o != null && this.f14072o.length() > 0) {
                treeMap.put(SchedulerSupport.f17506b, this.f14072o);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return treeMap;
    }

    /* renamed from: e */
    private static Map m15609e() {
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("name", f14065j);
            if (f14066p != null && f14066p.length() > 0) {
                treeMap.put(SchedulerSupport.f17506b, f14066p);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return treeMap;
    }

    /* renamed from: f */
    private static void m15608f() {
        try {
            C2979il.m15417a().setSubaccount(new JSONObject(m15609e()));
            m15614b(null);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private static void m15614b(AbstractC2790d dVar) {
        try {
            Map e = m15609e();
            C2979il.m15417a().setSubaccount(new JSONObject(e));
            m15620a(f14058b, "roleUpdate", e, dVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public static C2908gi m15623a() {
        if (f14057a == null) {
            synchronized (C2908gi.class) {
                if (f14057a == null) {
                    f14057a = new C2908gi();
                }
            }
        }
        return f14057a;
    }

    private C2908gi() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15623a());
        } catch (Throwable unused) {
        }
        f14058b = "account";
        f14059c = "accountId";
        f14060d = "name";
        f14061e = "gender";
        f14062f = "age";
        f14063g = "type";
        f14064h = "accountCus";
        f14065j = "default";
    }
}
