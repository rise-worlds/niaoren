package com.tendcloud.tenddata;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tendcloud.tenddata.C2956hw;
import com.tendcloud.tenddata.C2962ia;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
import p110z1.C4745bt;
import p110z1.SchedulerSupport;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.zv */
/* loaded from: classes2.dex */
public class C3030zv extends BroadcastReceiver {
    public static final String PUSH_ID = "push_id";
    public static final String SP_NAME = "TalkingData_Push_SharedPreferences";
    public static final String TALKINGDATA_MESSAGE_CLICK = "com.talkingdata.message.click";
    public static final String TALKINGDATA_NOTIFICATION_CANCEL = "com.talkingdata.notification.cancel";
    public static final String TALKINGDATA_NOTIFICATION_CLICK = "com.talkingdata.notification.click";

    /* renamed from: a */
    static String f14344a = "zv";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.zv$a */
    /* loaded from: classes2.dex */
    public enum EnumC3031a {
        baidu,
        getui,
        jpush
    }

    /* renamed from: a */
    static synchronized void m15239a(Context context, String str, EnumC3031a aVar) {
        synchronized (C3030zv.class) {
            if (aVar != null) {
                try {
                    C2951hr.m15495a(context, C2855es.m15787c(context, m15238a(aVar)), str, aVar.name());
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* renamed from: a */
    static String m15238a(EnumC3031a aVar) {
        switch (aVar) {
            case baidu:
                return "api_key";
            case getui:
            default:
                return "PUSH_APPID";
            case jpush:
                return "JPUSH_APPKEY";
        }
    }

    public static HashMap getMapFromJsonString(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next).toString());
            }
            return hashMap;
        } catch (Exception e) {
            C2933hb.postSDKError(e);
            return null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String c = C2951hr.m15492c(context);
            String stringExtra = intent.getStringExtra(C4745bt.f20075f);
            if (c == null) {
                return;
            }
            if (stringExtra == null || stringExtra.equals(c)) {
                if (C2664ab.f13513g == null) {
                    C2664ab.f13513g = context.getApplicationContext();
                }
                String action = intent.getAction();
                if (action.equals("com.baidu.android.pushservice.action.MESSAGE")) {
                    onMessageReceived(context, intent.getExtras().getString(AbstractC2953ht.f14200f));
                } else if (!action.equals("com.baidu.android.pushservice.action.notification.CLICK")) {
                    if (action.equals("com.baidu.android.pushservice.action.RECEIVE")) {
                        if (intent.getByteArrayExtra(ServiceManagerNative.CONTENT) != null) {
                            m15239a(context, new String(intent.getByteArrayExtra(ServiceManagerNative.CONTENT)), EnumC3031a.baidu);
                        } else {
                            Log.e(f14344a, "BAIDU push id is null");
                        }
                    } else if (action.equals("cn.jpush.android.intent.REGISTRATION")) {
                        m15239a(context, intent.getExtras().getString("cn.jpush.android.REGISTRATION_ID"), EnumC3031a.jpush);
                    } else if (action.equals("cn.jpush.android.intent.MESSAGE_RECEIVED")) {
                        onMessageReceived(context, intent.getExtras().getString("cn.jpush.android.MESSAGE"));
                    } else if (action.equals(TALKINGDATA_NOTIFICATION_CLICK)) {
                        int i = intent.getExtras().getInt(ConnectionModel.f10389a, 0);
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(ServiceManagerNative.NOTIFICATION);
                        if (i != 0) {
                            notificationManager.cancel(i);
                        }
                        onNofiticationClick(context, intent);
                    } else if (action.equals(TALKINGDATA_NOTIFICATION_CANCEL)) {
                        String stringExtra2 = intent.getStringExtra("sign");
                        C2962ia.C2963a aVar = new C2962ia.C2963a();
                        aVar.paraMap.put("apiType", 103);
                        aVar.paraMap.put("pushEvent", new C2956hw(stringExtra2, null, C2956hw.EnumC2957a.CANCEL, 0));
                        Message.obtain(C2958hx.m15467a(), 101, aVar).sendToTarget();
                    } else if (!action.equals(TALKINGDATA_MESSAGE_CLICK) && action.startsWith("com.igexin.sdk.action")) {
                        Bundle extras = intent.getExtras();
                        switch (extras.getInt("action")) {
                            case 10001:
                                byte[] byteArray = extras.getByteArray("payload");
                                if (byteArray != null) {
                                    onMessageReceived(context, new String(byteArray));
                                    return;
                                }
                                return;
                            case 10002:
                                m15239a(context, extras.getString("clientid"), EnumC3031a.getui);
                                return;
                            default:
                                return;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void onNofiticationClick(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(SchedulerSupport.f17506b);
            String stringExtra2 = intent.getStringExtra("sign");
            String stringExtra3 = intent.getStringExtra(C4745bt.f20075f);
            String stringExtra4 = intent.getStringExtra("ex");
            if (stringExtra3 != null && stringExtra3.equals(C2951hr.m15492c(context))) {
                HashMap mapFromJsonString = stringExtra4 != null ? getMapFromJsonString(stringExtra4) : null;
                C2954hu.m15487a();
                C2962ia.C2963a aVar = new C2962ia.C2963a();
                aVar.paraMap.put("apiType", 103);
                aVar.paraMap.put("pushEvent", new C2956hw(stringExtra2, null, C2956hw.EnumC2957a.CLICK, 0));
                C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
                if (stringExtra != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(stringExtra);
                        if (jSONObject.isNull(ServiceManagerNative.ACTIVITY)) {
                            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                            launchIntentForPackage.putExtra(SchedulerSupport.f17506b, stringExtra);
                            context.startActivity(launchIntentForPackage);
                        } else {
                            String string = jSONObject.getString(ServiceManagerNative.ACTIVITY);
                            Intent intent2 = new Intent();
                            intent2.setClassName(context, string);
                            intent2.addFlags(268435456);
                            intent2.putExtra(SchedulerSupport.f17506b, stringExtra);
                            context.startActivity(intent2);
                        }
                    } catch (Throwable unused) {
                    }
                } else {
                    Intent launchIntentForPackage2 = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                    if (mapFromJsonString != null) {
                        launchIntentForPackage2.putExtra("ex", mapFromJsonString);
                    }
                    launchIntentForPackage2.addFlags(536870912);
                    context.startActivity(launchIntentForPackage2);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void onMessageReceived(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("sign")) {
                String string = jSONObject.getString("sign");
                C2962ia.C2963a aVar = new C2962ia.C2963a();
                aVar.paraMap.put("apiType", 103);
                aVar.paraMap.put("pushEvent", new C2956hw(string, null, C2956hw.EnumC2957a.ARRIVED, 0));
                C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
            }
            if (jSONObject.has("tc") && jSONObject.getInt("tc") == 1) {
                C2954hu.m15484a(context, jSONObject);
            } else if (jSONObject.getInt("type") == 1) {
                C2954hu.m15479b(context, jSONObject);
            }
        } catch (Throwable unused) {
        }
    }
}
