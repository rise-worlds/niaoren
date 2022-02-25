package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.support.p003v4.app.NotificationCompat;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tendcloud.tenddata.C2945hn;
import com.tendcloud.tenddata.C3034zz;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gt */
/* loaded from: classes2.dex */
public class C2922gt {

    /* renamed from: a */
    private static volatile C2922gt f14120a;

    /* renamed from: b */
    private static boolean f14121b;

    public final void onTDEBEventInitEvent(C3034zz.C3035a aVar) {
        try {
            if (Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType"))) == 1) {
                String valueOf = String.valueOf(aVar.paraMap.get("action"));
                AbstractC2790d dVar = (AbstractC2790d) aVar.paraMap.get(NotificationCompat.CATEGORY_SERVICE);
                if (valueOf.equals("install") && dVar.name().equals("TRACKING")) {
                    C2947ho hoVar = new C2947ho();
                    Object obj = aVar.paraMap.get("data");
                    hoVar.f14181b = String.valueOf(aVar.paraMap.get(ClientCookie.DOMAIN_ATTR));
                    hoVar.f14182c = valueOf;
                    if (obj != null && (obj instanceof Map)) {
                        hoVar.f14183d = (Map) obj;
                    }
                    hoVar.f14180a = dVar;
                    C2858ev.m15778a().post(hoVar);
                } else if (valueOf.equals("init")) {
                    Context context = C2664ab.f13513g;
                    C2936he.m15520a();
                    C2908gi.m15623a();
                    C2921gs.m15556a();
                    C2911gk.m15601a();
                    C2929gz.m15530a();
                    C2912gl.m15598a();
                    if (!m15554a(context)) {
                        C2906gg.m15625a().m15624b();
                        C2926gx.m15540a().m15538b();
                        C2937hf.m15514a().m15513b();
                    }
                    m15552a(dVar);
                    m15551b();
                    C2664ab.f13508b = true;
                    if (dVar.name().equals("APP")) {
                        m15553a(context, dVar);
                    }
                    sendInitEventWithTDFeatures(dVar);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private void m15553a(Context context, AbstractC2790d dVar) {
        System.currentTimeMillis();
        C2855es.f13912b = C2664ab.f13525s;
        if (!f14121b) {
            try {
                String str = "TalkingData App Analytics SDK init...\n\tSDK_VERSION is: Android+TD+V4.0.30 gp Type:" + C2664ab.m16350c() + "  Build_Num:" + C2664ab.f13526t + "\n\tApp ID is: " + C2664ab.m16358a(context, dVar) + "\n\tApp Channel is: " + C2664ab.m16353b(context, dVar) + "\n\tSDK_OVC is: " + C2684as.f13539e;
                if (C2664ab.f13508b || C2811dq.f13769a) {
                    Log.i(C2664ab.f13525s, str);
                }
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appKey", C2664ab.m16358a(context, dVar));
                jSONObject.put(NotificationCompat.CATEGORY_SERVICE, "app");
                jSONArray.put(jSONObject);
                C2982io.m15416a().m15414a(jSONArray, AbstractC2790d.PUSH);
                C2954hu.m15487a();
                f14121b = true;
            } catch (Throwable th) {
                C2811dq.m16036a("[SDKInit] Failed to initialize!", th);
            }
        }
    }

    /* renamed from: b */
    private static void m15551b() {
        try {
            if (C2812dr.m16014e() == 0) {
                C2812dr.setInitTime(System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static void m15552a(AbstractC2790d dVar) {
        try {
            if (dVar == null) {
                C2811dq.eForInternal("TDFeatures is null...");
            } else if (C2812dr.m16015d(dVar) == 0) {
                C2812dr.m16026b(System.currentTimeMillis(), dVar);
            } else if (System.currentTimeMillis() - C2812dr.m16015d(dVar) > WaitFor.ONE_DAY) {
                C2855es.f13913c = true;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public static void sendInitEventWithTDFeatures(AbstractC2790d dVar) {
        try {
            if (dVar == null) {
                C2811dq.eForInternal("TDFeatures is null...");
                return;
            }
            TreeMap treeMap = new TreeMap();
            boolean a = C2664ab.m16357a(dVar);
            treeMap.put("first", Boolean.valueOf(a));
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "app";
            hoVar.f14182c = "init";
            hoVar.f14183d = treeMap;
            hoVar.f14180a = dVar;
            if (a) {
                hoVar.f14185f = new C2923gu(dVar);
            }
            C2858ev.m15778a().post(hoVar);
            C2945hn hnVar = new C2945hn();
            hnVar.f14178a = dVar;
            hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
            C2858ev.m15778a().post(hnVar);
            if (a) {
                m15550c();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private static boolean m15554a(Context context) {
        try {
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (Process.myPid() == runningAppProcessInfo.pid && runningAppProcessInfo.processName.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return C2668ac.f13533a;
    }

    /* renamed from: c */
    private static void m15550c() {
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "getProp";
            TreeMap treeMap = new TreeMap();
            treeMap.put("sysproperty", C2855es.m15808a());
            hoVar.f14183d = treeMap;
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    public static C2922gt m15555a() {
        if (f14120a == null) {
            synchronized (C2922gt.class) {
                if (f14120a == null) {
                    f14120a = new C2922gt();
                }
            }
        }
        return f14120a;
    }

    private C2922gt() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15555a());
        } catch (Throwable unused) {
        }
        f14120a = null;
        f14121b = false;
    }
}
