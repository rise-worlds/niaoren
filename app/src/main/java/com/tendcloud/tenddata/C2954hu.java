package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.text.Html;
import com.alipay.sdk.widget.C0675j;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tendcloud.tenddata.C2945hn;
import com.tendcloud.tenddata.C2956hw;
import com.tendcloud.tenddata.C2962ia;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.C4745bt;
import p110z1.ChannelReader;
import p110z1.MemoryConstants;
import p110z1.SchedulerSupport;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hu */
/* loaded from: classes2.dex */
public class C2954hu {

    /* renamed from: a */
    public static boolean f14201a = false;

    /* renamed from: b */
    static boolean f14202b = false;

    /* renamed from: c */
    private static final String f14203c = "push";

    /* renamed from: d */
    private static final String f14204d = "deviceToken";

    /* renamed from: e */
    private static final String f14205e = "message";

    /* renamed from: f */
    private static final long f14206f = 864000000;

    /* renamed from: g */
    private static volatile C2954hu f14207g;

    /* renamed from: h */
    private static C2960hz f14208h;

    static {
        try {
            C2858ev.m15778a().register(m15487a());
            f14202b = m15485a(C2664ab.f13513g);
            if (f14202b) {
                f14208h = new C2960hz(C2664ab.f13513g);
                m15480b();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    private C2954hu() {
        m15475e();
    }

    /* renamed from: e */
    private static void m15475e() {
        C2855es.execute(new RunnableC2955hv());
    }

    /* renamed from: a */
    public static C2954hu m15487a() {
        if (f14207g == null) {
            synchronized (C2954hu.class) {
                if (f14207g == null) {
                    f14207g = new C2954hu();
                }
            }
        }
        return f14207g;
    }

    /* renamed from: a */
    private static boolean m15485a(Context context) {
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
        return false;
    }

    /* renamed from: b */
    static void m15480b() {
        try {
            C2962ia.C2963a aVar = new C2962ia.C2963a();
            aVar.paraMap.put("apiType", 101);
            C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
            if (Math.abs(System.currentTimeMillis() - C2812dr.m15997t()) > f14206f) {
                m15477c();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static void m15482a(String str, String str2, String str3) {
        JSONArray jSONArray = new JSONArray();
        try {
            String s = C2812dr.m15998s();
            if (s.length() == 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("3rdAppId", str);
                jSONObject.put(ChannelReader.f15967a, str2);
                jSONObject.put("dt", str3);
                jSONArray.put(jSONObject);
                C2812dr.setPushAppContext(jSONArray.toString());
                return;
            }
            JSONArray jSONArray2 = new JSONArray(s);
            boolean z = false;
            for (int i = 0; i < jSONArray2.length(); i++) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                String string = jSONObject2.getString(ChannelReader.f15967a);
                String string2 = jSONObject2.getString("dt");
                String string3 = jSONObject2.getString("3rdAppId");
                if (string.equals(str2)) {
                    if (!string3.equals(str)) {
                        jSONObject2.put("3rdAppId", str);
                    }
                    if (!string2.equals(str3)) {
                        jSONObject2.put("dt", str3);
                    }
                    z = true;
                }
                jSONArray.put(jSONObject2);
            }
            if (!z) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(ChannelReader.f15967a, str2);
                jSONObject3.put("3rdAppId", str);
                jSONObject3.put("dt", str3);
                jSONArray.put(jSONObject3);
            }
            C2812dr.setPushAppContext(jSONArray.toString());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: c */
    public static void m15477c() {
        try {
            String s = C2812dr.m15998s();
            if (s.length() != 0) {
                JSONArray jSONArray = new JSONArray(s);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    C2962ia.C2963a aVar = new C2962ia.C2963a();
                    aVar.paraMap.put("apiType", 102);
                    aVar.paraMap.put("pushEvent", new C2956hw(jSONObject.getString("3rdAppId"), jSONObject.getString("dt"), jSONObject.getString(ChannelReader.f15967a)));
                    C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
                    C2812dr.setPushSyncTokenLastTime(System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m15484a(Context context, JSONObject jSONObject) {
        try {
            HashMap a = C2951hr.m15494a(jSONObject.getJSONObject("ex").toString());
            Intent intent = new Intent();
            intent.putExtra("ex", a);
            intent.setPackage(context.getPackageName());
            intent.setAction(AbstractC2953ht.f14199e);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m15479b(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("msg");
            String string = jSONObject2.getJSONObject(C0675j.f373k).getString("val");
            String string2 = jSONObject2.getJSONObject(ServiceManagerNative.CONTENT).getString("val");
            String string3 = jSONObject.getString("sign");
            if (C2951hr.m15490e(context)) {
                m15481a(string3, false);
            }
            int currentTimeMillis = (int) System.currentTimeMillis();
            Notification a = f14208h != null ? f14208h.m15464a(string, string2) : null;
            if (a != null) {
                Intent intent = new Intent(C3030zv.TALKINGDATA_NOTIFICATION_CLICK);
                intent.putExtra("sign", string3);
                intent.putExtra(C4745bt.f20075f, C2951hr.m15492c(context));
                if (jSONObject2.has("config") && jSONObject2.getJSONObject("config") != null) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("config");
                    if (jSONObject3.has("sound") && jSONObject3.getInt("sound") > 0) {
                        a.defaults |= 1;
                    }
                    if (jSONObject3.has("vibrate") && jSONObject3.getInt("vibrate") > 0) {
                        a.defaults |= 2;
                    }
                    if (jSONObject3.has("wakeup") && jSONObject3.getInt("wakeup") > 0) {
                        m15486a(2000L, context);
                    }
                    if (jSONObject3.has("clearable") && jSONObject3.getInt("clearable") == 0) {
                        a.flags = 32;
                        intent.putExtra(ConnectionModel.f10389a, currentTimeMillis);
                    }
                }
                if (!jSONObject.isNull(SchedulerSupport.f17506b)) {
                    intent.putExtra(SchedulerSupport.f17506b, jSONObject.getJSONObject(SchedulerSupport.f17506b).toString());
                }
                if (!jSONObject.isNull("ex")) {
                    intent.putExtra("ex", jSONObject.getJSONObject("ex").toString());
                }
                intent.setPackage(context.getPackageName());
                PendingIntent broadcast = PendingIntent.getBroadcast(context, currentTimeMillis + 1, intent, 268435456);
                Intent intent2 = new Intent(C3030zv.TALKINGDATA_NOTIFICATION_CANCEL);
                intent2.putExtra("sign", string3);
                intent2.putExtra(C4745bt.f20075f, C2951hr.m15492c(context));
                PendingIntent broadcast2 = PendingIntent.getBroadcast(context, currentTimeMillis + 2, intent2, MemoryConstants.f21646d);
                if (Build.VERSION.SDK_INT < 23) {
                    a.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(a, context, Html.fromHtml(string), Html.fromHtml(string2), null);
                }
                a.contentIntent = broadcast;
                a.deleteIntent = broadcast2;
                f14208h.m15465a(currentTimeMillis, a);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    static void m15486a(long j, Context context) {
        try {
            if (C2855es.m15792b(context, "android.permission.WAKE_LOCK")) {
                ((PowerManager) context.getSystemService("power")).newWakeLock(805306394, "TDAcquireWakeLock").acquire(j);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    static void m15481a(String str, boolean z) {
        try {
            C2962ia.C2963a aVar = new C2962ia.C2963a();
            aVar.paraMap.put("apiType", 103);
            aVar.paraMap.put("pushEvent", new C2956hw(str, null, z ? C2956hw.EnumC2957a.INAPP_SHOW : C2956hw.EnumC2957a.SHOW, 0));
            C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public final void onTDEBEventPushEvent(C2962ia.C2963a aVar) {
        if (aVar != null) {
            try {
                int parseInt = Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType")));
                C2956hw hwVar = (C2956hw) aVar.paraMap.get("pushEvent");
                String s = C2812dr.m15998s();
                if (!C2855es.m15791b(s)) {
                    C2979il.m15417a().setPushInfo(s);
                }
                switch (parseInt) {
                    case 101:
                        m15476d();
                        return;
                    case 102:
                        if (hwVar != null) {
                            m15478b(hwVar);
                            return;
                        }
                        return;
                    case 103:
                        if (hwVar != null) {
                            m15483a(hwVar);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: d */
    void m15476d() {
        try {
            C2945hn hnVar = new C2945hn();
            hnVar.f14178a = AbstractC2790d.PUSH;
            hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
            C2858ev.m15778a().post(hnVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    void m15483a(C2956hw hwVar) {
        if (hwVar != null) {
            try {
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = f14203c;
                hoVar.f14182c = f14205e;
                TreeMap treeMap = new TreeMap();
                treeMap.put("action", Integer.valueOf(hwVar.m15469f().index()));
                treeMap.put("msgSign", hwVar.m15471d());
                hoVar.f14183d = treeMap;
                hoVar.f14180a = AbstractC2790d.PUSH;
                C2858ev.m15778a().post(hoVar);
                C2945hn hnVar = new C2945hn();
                hnVar.f14178a = AbstractC2790d.PUSH;
                hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
                C2858ev.m15778a().post(hnVar);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: b */
    void m15478b(C2956hw hwVar) {
        if (hwVar != null) {
            try {
                m15482a(hwVar.m15473b(), hwVar.m15472c(), hwVar.m15474a());
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = f14203c;
                hoVar.f14182c = f14204d;
                TreeMap treeMap = new TreeMap();
                treeMap.put("appId", hwVar.m15473b());
                treeMap.put(ChannelReader.f15967a, hwVar.m15472c());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dt", hwVar.m15474a());
                treeMap.put(f14204d, jSONObject);
                C2979il.m15417a().setPushInfo(C2812dr.m15998s());
                hoVar.f14183d = treeMap;
                hoVar.f14180a = AbstractC2790d.PUSH;
                C2858ev.m15778a().post(hoVar);
                C2945hn hnVar = new C2945hn();
                hnVar.f14178a = AbstractC2790d.PUSH;
                hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
                C2858ev.m15778a().post(hnVar);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }
}
