package com.tendcloud.tenddata;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Message;
import com.tendcloud.tenddata.C2962ia;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hr */
/* loaded from: classes2.dex */
final class C2951hr {

    /* renamed from: a */
    private static String f14188a = null;

    /* renamed from: b */
    private static String f14189b = null;

    /* renamed from: c */
    private static String f14190c = null;

    /* renamed from: d */
    private static final String f14191d = "checkOpNoThrow";

    /* renamed from: e */
    private static final String f14192e = "OP_POST_NOTIFICATION";

    C2951hr() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m15496a(Context context) {
        String str = f14188a;
        if (str != null && !str.isEmpty()) {
            return f14188a;
        }
        try {
            f14188a = C2819dt.m15969a(context);
            if (f14188a != null && !f14188a.isEmpty()) {
                return f14188a;
            }
        } catch (Throwable unused) {
        }
        String str2 = f14188a;
        if (str2 != null) {
            str2.isEmpty();
        }
        return f14188a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m15493b(Context context) {
        try {
        } catch (Throwable th) {
            C2811dq.m16036a("get AppId Error", th);
        }
        if (f14189b != null && !f14189b.isEmpty()) {
            return f14189b;
        }
        f14189b = "app-" + C2664ab.m16358a(context, AbstractC2790d.APP);
        String str = f14189b;
        if (str == null || str.isEmpty()) {
            C2811dq.iForDeveloper("[push] start service error, app id is required");
        }
        return f14189b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m15492c(Context context) {
        try {
        } catch (Throwable th) {
            C2811dq.m16036a("get rawAppId Error", th);
        }
        if (f14190c != null && !f14190c.isEmpty()) {
            return f14190c;
        }
        f14190c = C2664ab.m16358a(context, AbstractC2790d.APP);
        String str = f14190c;
        if (str == null || str.isEmpty()) {
            C2811dq.eForDeveloper("[push] app id is null");
        }
        return f14190c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static HashMap m15494a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next).toString());
            }
            return hashMap;
        } catch (Throwable th) {
            C2811dq.m16036a("getMapFromJsonString error !", th);
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: d */
    static String m15491d(Context context) {
        try {
            String displayCountry = context.getResources().getConfiguration().locale.getDisplayCountry();
            if (!"中国".equals(displayCountry) && !"ZH".equals(displayCountry)) {
                if (!"zh".equals(displayCountry)) {
                    return displayCountry;
                }
            }
            return "CN";
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static boolean m15490e(Context context) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            if (Build.VERSION.SDK_INT < 19) {
                return true;
            }
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return Integer.parseInt(String.valueOf(cls.getMethod(f14191d, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(Integer.parseInt(String.valueOf(cls.getDeclaredField(f14192e).get(Integer.class)))), Integer.valueOf(i), packageName))) == 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m15495a(Context context, String str, String str2, String str3) {
        if (str != null) {
            try {
                String b = C2843eh.m15840b(context, AbstractC2953ht.f14197c, str3 + str, (String) null);
                if (b == null || !b.equalsIgnoreCase(str2)) {
                    C2954hu.m15487a();
                    C2962ia.C2963a aVar = new C2962ia.C2963a();
                    aVar.paraMap.put("apiType", 102);
                    aVar.paraMap.put("pushEvent", new C2956hw(str, str2, str3));
                    C2958hx.m15467a().sendMessage(Message.obtain(C2958hx.m15467a(), 101, aVar));
                    Context context2 = C2664ab.f13513g;
                    C2843eh.m15842a(context2, AbstractC2953ht.f14197c, str3 + str, str2);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
