package com.tendcloud.tenddata;

import android.util.Pair;
import com.github.kevinsawicki.http.HttpRequest;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jc */
/* loaded from: classes2.dex */
public class C2998jc {

    /* renamed from: c */
    private static C2982io f14296c;

    /* renamed from: d */
    private static C2985ir f14297d;

    /* renamed from: e */
    private static C2989iv f14298e = new C2989iv();

    /* renamed from: f */
    private static C2986is f14299f = null;

    /* renamed from: g */
    private static volatile C2998jc f14300g = null;

    /* renamed from: a */
    public JSONObject f14301a = null;

    /* renamed from: b */
    public JSONObject f14302b = null;

    /* renamed from: a */
    public synchronized JSONObject m15382a(C2981in inVar, boolean z) {
        return m15381a(inVar, z, null);
    }

    /* renamed from: a */
    public synchronized JSONObject m15381a(C2981in inVar, boolean z, AbstractC2790d dVar) {
        return m15380a(inVar, z, dVar, null);
    }

    /* renamed from: a */
    public synchronized JSONObject m15380a(C2981in inVar, boolean z, AbstractC2790d dVar, Pair pair) {
        if (inVar != null) {
            if (inVar.mo15408a_() != null) {
                JSONObject jSONObject = new JSONObject();
                C2832ea.getFileLock(C2913gm.m15591a().m15578b());
                this.f14301a = new JSONObject();
                this.f14302b = new JSONObject();
                try {
                    if (f14296c == null) {
                        f14296c = C2982io.m15416a();
                        f14296c.setUniqueId(C2996jb.m15386a(C2664ab.f13513g, C2664ab.f13513g.getPackageName()));
                    }
                    f14296c.setSubmitAppId(dVar);
                    f14296c.setSubmitChannelId(dVar);
                    if (f14298e == null) {
                        f14298e = new C2989iv();
                    } else {
                        f14298e.m15399c().m15402c();
                    }
                    C2989iv ivVar = f14298e;
                    C2989iv.f14280a.m15401b();
                    f14298e.m15399c().setSlots(C2836ec.m15894D(C2664ab.f13513g));
                    if (f14297d == null) {
                        f14297d = new C2985ir();
                    }
                    if (f14299f == null) {
                        f14299f = new C2986is();
                        f14299f.m15405b();
                    }
                } catch (Throwable unused) {
                }
                f14299f.m15404c();
                jSONObject.put(ClientCookie.VERSION_ATTR, EjbJar.CMPVersion.CMP2_0);
                jSONObject.put("action", inVar.mo15408a_());
                jSONObject.put("device", f14298e.mo15408a_());
                jSONObject.put("app", f14296c.mo15408a_());
                jSONObject.put("sdk", f14297d.mo15408a_());
                jSONObject.put("appContext", C2979il.m15417a().mo15408a_());
                jSONObject.put(ServiceManagerNative.USER, f14299f.mo15408a_());
                long currentTimeMillis = System.currentTimeMillis();
                jSONObject.put("ts", currentTimeMillis);
                jSONObject.put("fingerprint", C2855es.m15786c(currentTimeMillis + C2996jb.m15387a(C2664ab.f13513g) + f14298e.m15400b().m15397b() + f14298e.m15400b().m15396c()));
                if (z) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(new C2992iy(EnumC2995ja.WIFI).mo15408a_());
                    jSONArray.put(new C2992iy(EnumC2995ja.CELLULAR).mo15408a_());
                    try {
                        if (C2855es.m15792b(C2664ab.f13513g, "android.permission.BLUETOOTH")) {
                            jSONArray.put(new C2992iy(EnumC2995ja.BLUETOOTH).mo15408a_());
                        }
                    } catch (Throwable unused2) {
                    }
                    jSONObject.put("networks", jSONArray);
                    JSONArray a = C2913gm.m15591a().m15585a(HttpRequest.HEADER_LOCATION);
                    if (a != null) {
                        jSONObject.put("locations", new C2983ip().mo15408a_());
                        if (a.length() > 0) {
                            m15379a(HttpRequest.HEADER_LOCATION, a);
                        }
                    }
                }
                if (pair != null && ((JSONArray) pair.second).length() > 0) {
                    m15379a((String) pair.first, (JSONArray) pair.second);
                }
                if (!C2664ab.f13523q) {
                    jSONObject.put("cloudcontrol", this.f14301a);
                }
                C2913gm.m15591a().m15574c();
                C2832ea.releaseFileLock(C2913gm.m15591a().m15578b());
                return jSONObject;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m15379a(String str, JSONArray jSONArray) {
        try {
            if (!C2664ab.f13523q && this.f14301a != null && jSONArray.length() > 0) {
                this.f14301a.put(str, jSONArray);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public static C2998jc m15383a() {
        if (f14300g == null) {
            synchronized (C2998jc.class) {
                if (f14300g == null) {
                    f14300g = new C2998jc();
                }
            }
        }
        return f14300g;
    }
}
