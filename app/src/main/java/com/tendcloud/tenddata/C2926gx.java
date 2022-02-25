package com.tendcloud.tenddata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gx */
/* loaded from: classes2.dex */
public final class C2926gx {

    /* renamed from: a */
    static Handler f14134a = null;

    /* renamed from: b */
    static HandlerThread f14135b = null;

    /* renamed from: c */
    private static volatile C2926gx f14136c = null;

    /* renamed from: d */
    private static final int f14137d = 1;

    /* renamed from: e */
    private static final int f14138e = 2;

    /* renamed from: f */
    private static final int f14139f = 3;

    /* renamed from: a */
    public static C2926gx m15540a() {
        if (f14136c == null) {
            synchronized (C2926gx.class) {
                if (f14136c == null) {
                    f14136c = new C2926gx();
                }
            }
        }
        return f14136c;
    }

    private C2926gx() {
        f14135b = new HandlerThread("lockScreenThread");
        f14135b.start();
        f14134a = new HandlerC2928gy(this, f14135b.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public Handler m15536c() {
        return f14134a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m15534d() {
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "userPresent";
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m15532e() {
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "screenOff";
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m15531f() {
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "screenOn";
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    static {
        try {
            C2858ev.m15778a().register(m15540a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* renamed from: b */
    public void m15538b() {
        try {
            if (C2664ab.f13513g != null) {
                C2927a aVar = new C2927a();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                C2664ab.f13513g.registerReceiver(aVar, intentFilter);
            }
        } catch (Throwable unused) {
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.gx$a */
    /* loaded from: classes2.dex */
    final class C2927a extends BroadcastReceiver {
        private String action = null;

        C2927a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    this.action = intent.getAction();
                    if ("android.intent.action.SCREEN_ON".equals(this.action)) {
                        C2926gx.m15540a().m15536c().sendEmptyMessage(1);
                    } else if ("android.intent.action.SCREEN_OFF".equals(this.action)) {
                        C2926gx.m15540a().m15536c().sendEmptyMessage(2);
                    } else if ("android.intent.action.USER_PRESENT".equals(this.action)) {
                        C2926gx.m15540a().m15536c().sendEmptyMessage(3);
                    }
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                }
            }
        }
    }
}
