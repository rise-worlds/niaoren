package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tendcloud.tenddata.C2916gp;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bq */
/* loaded from: classes2.dex */
public class C2744bq {

    /* renamed from: a */
    static final String f13615a = "events";

    /* renamed from: b */
    static final String f13616b = "TalingDataConfig";

    /* renamed from: c */
    static final String f13617c = "config.";

    /* renamed from: d */
    static final int f13618d = 0;

    /* renamed from: e */
    private static volatile C2744bq f13619e;

    /* renamed from: f */
    private boolean f13620f = false;

    /* renamed from: g */
    private C2717bl f13621g;

    /* renamed from: h */
    private RunnableC2745a f13622h;

    /* renamed from: i */
    private Handler f13623i;

    /* renamed from: a */
    public static C2744bq m16223a() {
        if (f13619e == null) {
            synchronized (C2744bq.class) {
                if (f13619e == null) {
                    f13619e = new C2744bq();
                }
            }
        }
        return f13619e;
    }

    private C2744bq() {
        this.f13622h = null;
        this.f13622h = new RunnableC2745a();
    }

    static {
        try {
            C2858ev.m15778a().register(m16223a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    public void m16219b() {
        if (!this.f13620f) {
            try {
                if (Build.VERSION.SDK_INT >= 16 && C3034zz.f14348d != null && m16222a(C2664ab.f13513g)) {
                    this.f13621g = C2717bl.m16251a(C2664ab.f13513g, C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP), C3034zz.f14348d);
                }
                C2852ep.f13904a.execute(new RunnableC2746b());
                this.f13620f = true;
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    public final void onTDEBEventCodelessEvent(C2916gp.C2917a aVar) {
        Object obj;
        try {
            Object obj2 = aVar.paraMap.get("cloudSettingsType");
            if (obj2 != null && obj2.toString().equals("codeless") && (obj = aVar.paraMap.get("data")) != null && (obj instanceof JSONArray)) {
                Context context = C2664ab.f13513g;
                C2843eh.m15842a(context, f13616b + C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP), "config.events", obj.toString());
                if (this.f13621g != null) {
                    this.f13621g.m16248a((JSONArray) obj, 0);
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: a */
    private static boolean m16222a(Context context) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bq$a */
    /* loaded from: classes2.dex */
    public static class RunnableC2745a implements Runnable {
        private volatile boolean mStopped = true;

        RunnableC2745a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (C2744bq.m16223a().f13623i != null && !this.mStopped) {
                    C2744bq.m16223a().f13623i.sendMessage(C2744bq.m16223a().f13623i.obtainMessage(1));
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        public void start() {
            try {
                if (C2744bq.m16223a().f13623i != null) {
                    this.mStopped = false;
                    C2744bq.m16223a().f13623i.post(this);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        void stop() {
            try {
                if (C2744bq.m16223a().f13623i != null) {
                    this.mStopped = true;
                    C2744bq.m16223a().f13623i.removeCallbacks(this);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bq$b */
    /* loaded from: classes2.dex */
    public static class RunnableC2746b implements Runnable {
        private RunnableC2746b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C2848em.m15820a(C2664ab.f13513g).registerTestDeviceListener(new C2748bs(this));
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: c */
    public void m16218c() {
        try {
            if (m16216e()) {
                this.f13622h.start();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: d */
    public void m16217d() {
        try {
            if (m16216e()) {
                this.f13622h.stop();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: e */
    private boolean m16216e() {
        if (!Build.HARDWARE.equals("goldfish") && !Build.HARDWARE.equals("ranchu") && !Build.HARDWARE.equals("vbox86")) {
            return false;
        }
        if (!Build.BRAND.startsWith("generic") && !Build.BRAND.startsWith("Android")) {
            return false;
        }
        if (!Build.DEVICE.startsWith("generic") && !Build.DEVICE.startsWith("vbox86")) {
            return false;
        }
        if (Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("Genymotion") || Build.PRODUCT.contains("vbox86")) {
            return Build.MODEL.toLowerCase(Locale.US).contains("sdk") || Build.MODEL.toLowerCase(Locale.US).contains("api");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16221a(Handler handler) {
        this.f13623i = handler;
    }
}
