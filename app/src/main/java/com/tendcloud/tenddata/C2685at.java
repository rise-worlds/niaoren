package com.tendcloud.tenddata;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.LocalServerSocket;
import android.os.Handler;
import android.os.HandlerThread;
import com.talkingdata.sdk.TDAntiCheatingService;
import com.tendcloud.tenddata.C2832ea;
import java.io.RandomAccessFile;
import java.util.HashMap;
import org.apache.tools.ant.taskdefs.WaitFor;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.at */
/* loaded from: classes2.dex */
public class C2685at {

    /* renamed from: a */
    static final String f13541a = "com.talkingdata.sdk.TDAntiCheatingService";

    /* renamed from: b */
    private static boolean f13542b = true;

    /* renamed from: c */
    private static Intent f13543c;

    /* renamed from: d */
    private static Handler f13544d;

    /* renamed from: e */
    private static LocalServerSocket f13545e;

    /* renamed from: f */
    private static C2686a f13546f;

    /* renamed from: g */
    private static RandomAccessFile f13547g;

    /* renamed from: h */
    private static String f13548h = C2832ea.EnumC2834b.AntiCheating_Switch_Lock_File.toString();

    /* renamed from: i */
    private static TDAntiCheatingService f13549i;

    /* renamed from: j */
    private static Handler f13550j;

    C2685at() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m16301a() {
        synchronized (C2685at.class) {
            if (!m16296c().equalsIgnoreCase("EmotionUI_5.0")) {
                m16288k();
                if (!f13542b) {
                    C2811dq.iForDeveloper("[AntiCheating] Anti Cheating functionality has been disabled!");
                    return;
                }
                if (f13544d == null) {
                    HandlerThread handlerThread = new HandlerThread("negotiationHandlerThread");
                    handlerThread.start();
                    f13544d = new Handler(handlerThread.getLooper());
                }
                try {
                    f13545e = new LocalServerSocket(f13541a);
                    f13546f = new C2686a(null);
                    C2664ab.f13513g.registerReceiver(f13546f, new IntentFilter(f13541a));
                    m16287l();
                    f13544d.postDelayed(new RunnableC2687au(), 5000L);
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized void m16299a(boolean z) {
        synchronized (C2685at.class) {
            if (z) {
                if (f13550j == null) {
                    m16283p();
                }
            } else if (f13550j != null) {
                C2811dq.iForInternal("serviceHeartBeatCheckHandler != null, stopServerHeartBeatCheck");
                f13550j.removeCallbacks(null);
            }
            C2832ea.getFileLock(f13548h);
            f13547g.seek(0L);
            f13547g.writeBoolean(z);
            C2832ea.releaseFileLock(f13548h);
            if (f13542b != z) {
                f13542b = z;
                if (!f13542b) {
                    m16286m();
                } else {
                    m16301a();
                }
                m16284o();
            }
        }
    }

    /* renamed from: a */
    private static void m16300a(String str) {
        f13543c = new Intent();
        f13543c.setAction(f13541a);
        f13543c.setComponent(new ComponentName(str, f13541a));
        f13543c.setFlags(32);
    }

    /* renamed from: k */
    private static void m16288k() {
        try {
            Intent intent = new Intent(f13541a);
            intent.putExtra("pkg", C2664ab.f13513g.getPackageName());
            intent.putExtra("isCheck", false);
            intent.putExtra("packageName", C2664ab.f13513g.getPackageName());
            intent.putExtra("appKey", C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP));
            intent.putExtra("tdId", C2668ac.m16307d(C2664ab.f13513g, AbstractC2790d.APP));
            C2664ab.f13513g.sendBroadcast(intent);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: l */
    private static void m16287l() {
        try {
            Intent intent = new Intent(f13541a);
            intent.putExtra("pkg", C2664ab.f13513g.getPackageName());
            intent.putExtra("isCheck", true);
            C2664ab.f13513g.sendBroadcast(intent);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: m */
    private static void m16286m() {
        try {
            if (f13549i == null) {
                Intent intent = new Intent(f13541a);
                intent.putExtra("pkg", C2664ab.f13513g.getPackageName());
                intent.putExtra("isStop", true);
                C2664ab.f13513g.sendBroadcast(intent);
            } else {
                f13549i.onDestroy();
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public static void m16285n() {
        C2811dq.iForDeveloper("[Negotiation] Start anti cheating service.");
        try {
            m16300a(C2664ab.f13513g.getPackageName());
            if (f13542b) {
                if (f13549i == null) {
                    f13549i = new TDAntiCheatingService();
                    f13549i.onCreate();
                }
                f13549i.onStartCommand(f13543c, 0, 0);
            }
            f13545e.close();
            C2664ab.f13513g.unregisterReceiver(f13546f);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.at$a */
    /* loaded from: classes2.dex */
    public static class C2686a extends BroadcastReceiver {
        private C2686a() {
        }

        /* synthetic */ C2686a(RunnableC2687au auVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                try {
                    if (!C2664ab.f13513g.getPackageName().equals(intent.getStringExtra("pkg"))) {
                        String stringExtra = intent.getStringExtra("packageName");
                        if (intent.getBooleanExtra("isCheck", true) && stringExtra != null && !stringExtra.equals(C2664ab.f13513g.getPackageName())) {
                            C2685at.f13544d.removeCallbacksAndMessages(null);
                            if (C2685at.f13545e != null) {
                                try {
                                    C2685at.f13545e.close();
                                } catch (Throwable unused) {
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                }
            }
        }
    }

    /* renamed from: o */
    private static void m16284o() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
            hashMap.put("status", Integer.valueOf(f13542b ? 1 : 0));
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "antiCheating";
            hoVar.f14182c = "switch";
            hoVar.f14183d = hashMap;
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: p */
    private static void m16283p() {
        HandlerThread handlerThread = new HandlerThread("serviceHandlerThread");
        handlerThread.start();
        f13550j = new HandlerC2688av(handlerThread.getLooper());
        f13550j.sendEmptyMessageDelayed(0, WaitFor.ONE_HOUR);
    }

    static {
        m16283p();
        try {
            f13547g = C2832ea.m15898b(f13548h);
            C2832ea.getFileLock(f13548h);
            if (f13547g.length() <= 0) {
                f13547g.seek(0L);
                f13547g.writeBoolean(f13542b);
            } else {
                f13547g.seek(0L);
                f13542b = f13547g.readBoolean();
            }
        } catch (Throwable unused) {
        }
        C2832ea.releaseFileLock(f13548h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m16298b() {
        try {
            C2832ea.getFileLock(f13548h);
            if (f13547g.length() > 0) {
                f13547g.seek(0L);
                f13542b = f13547g.readBoolean();
            } else {
                f13542b = true;
            }
        } catch (Throwable unused) {
        }
        C2832ea.releaseFileLock(f13548h);
        return f13542b;
    }

    /* renamed from: c */
    public static String m16296c() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
        } catch (Throwable unused) {
            return "";
        }
    }
}
