package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gl */
/* loaded from: classes2.dex */
public final class C2912gl {

    /* renamed from: a */
    private static volatile C2912gl f14075a = null;

    /* renamed from: b */
    private static Map f14076b = new TreeMap();

    /* renamed from: c */
    private static final int f14077c = 10800000;

    /* renamed from: a */
    public static C2912gl m15598a() {
        if (f14075a == null) {
            synchronized (C2912gl.class) {
                if (f14075a == null) {
                    f14075a = new C2912gl();
                }
            }
        }
        return f14075a;
    }

    private C2912gl() {
        try {
            if (m15594c()) {
                m15595b();
                m15593d();
                m15592e();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    private void m15595b() {
        try {
            ArrayList arrayList = new ArrayList();
            if (C2855es.m15807a(21)) {
                for (C2808dn dnVar : C2844ei.m15839a()) {
                    if (!dnVar.f13764c.startsWith("android.") && !dnVar.f13764c.equals("system")) {
                        arrayList.add(dnVar.f13764c);
                    }
                }
            } else {
                ActivityManager activityManager = (ActivityManager) C2664ab.f13513g.getSystemService(ServiceManagerNative.ACTIVITY);
                if (activityManager != null) {
                    PackageManager packageManager = C2664ab.f13513g.getPackageManager();
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            String str = runningAppProcessInfo.processName;
                            try {
                                if (packageManager.getLaunchIntentForPackage(str) != null) {
                                    arrayList.add(str);
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }
                }
            }
            f14076b.put("ras", arrayList.toString());
            C2812dr.setCollectRunningTime(System.currentTimeMillis());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: c */
    private boolean m15594c() {
        try {
            return System.currentTimeMillis() - C2812dr.m16010g() > 10800000;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: d */
    private void m15593d() {
        try {
            f14076b.put("aas", m15597a(C2664ab.f13513g).toString());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private List m15597a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages != null) {
                m15596a(installedPackages);
                for (PackageInfo packageInfo : installedPackages) {
                    arrayList.add(packageInfo.packageName);
                    arrayList.add(C2855es.m15790b(packageManager.getApplicationLabel(packageInfo.applicationInfo).toString().getBytes()));
                    if ((packageInfo.applicationInfo.flags & 1) > 0) {
                        arrayList.add("1");
                    } else {
                        arrayList.add(ResultTypeConstant.f7213z);
                    }
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m15596a(List list) {
        String[] strArr;
        try {
            C2690ax a = C2690ax.m16282a(C2692ay.f13556a + C2693az.f13557a + C2695ba.f13558a);
            HashMap hashMap = new HashMap();
            if (!(list == null || a == null)) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    PackageInfo packageInfo = (PackageInfo) it.next();
                    for (String str : C2689aw.f13551a) {
                        try {
                            if (a.m16281b(packageInfo.packageName + str)) {
                                hashMap.put(packageInfo.packageName, str);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            f14076b.put("aac", hashMap);
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARN: Finally extract failed */
    /* renamed from: e */
    private void m15592e() {
        JSONArray jSONArray;
        C2947ho hoVar = new C2947ho();
        try {
            C2832ea.getFileLock(C2913gm.m15591a().m15578b());
            jSONArray = C2913gm.m15591a().m15585a("AppList");
            C2832ea.releaseFileLock(C2913gm.m15591a().m15578b());
        } catch (Throwable th) {
            try {
                C2933hb.postSDKError(th);
                C2832ea.releaseFileLock(C2913gm.m15591a().m15578b());
                jSONArray = null;
            } catch (Throwable th2) {
                C2832ea.releaseFileLock(C2913gm.m15591a().m15578b());
                throw th2;
            }
        }
        if (jSONArray != null) {
            if (jSONArray.length() > 0) {
                hoVar.f14184e = new Pair("AppList", jSONArray);
            }
            hoVar.f14181b = "env";
            hoVar.f14182c = "apps";
            hoVar.f14183d = f14076b;
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        }
    }
}
