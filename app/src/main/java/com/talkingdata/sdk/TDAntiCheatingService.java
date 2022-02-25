package com.talkingdata.sdk;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import com.tendcloud.tenddata.C2664ab;
import com.tendcloud.tenddata.C2852ep;
import com.tendcloud.tenddata.C2858ev;
import com.tendcloud.tenddata.C2890fr;
import com.tendcloud.tenddata.C2906gg;
import com.tendcloud.tenddata.C2910gj;
import com.tendcloud.tenddata.C2933hb;
import com.tendcloud.tenddata.C2966id;
import com.tendcloud.tenddata.C2969ig;
import com.tendcloud.tenddata.RunnableC2662a;
import com.tendcloud.tenddata.RunnableC2694b;
import com.tendcloud.tenddata.RunnableC2758c;
import java.util.HashMap;
import org.json.JSONObject;
import p110z1.C4745bt;

/* compiled from: td */
/* loaded from: classes2.dex */
public class TDAntiCheatingService extends Service {

    /* renamed from: a */
    private C2537a f12737a;

    /* renamed from: b */
    private int f12738b = 0;

    /* renamed from: c */
    private HashMap f12739c = new HashMap();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    /* renamed from: c */
    static /* synthetic */ int m17079c(TDAntiCheatingService tDAntiCheatingService) {
        int i = tDAntiCheatingService.f12738b + 1;
        tDAntiCheatingService.f12738b = i;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
            if (C2664ab.f13513g == null) {
                C2664ab.f13513g = getApplicationContext();
            }
            m17080c();
            this.f12737a = new C2537a(this, null);
            m17084a();
            C2664ab.f13513g.registerReceiver(this.f12737a, new IntentFilter("com.talkingdata.sdk.TDAntiCheatingService"));
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            m17082b();
            if (C2664ab.f13513g != null) {
                C2664ab.f13513g.unregisterReceiver(this.f12737a);
            }
            super.onDestroy();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m17084a() {
        try {
            C2852ep.f13904a.execute(new RunnableC2662a(this));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    private void m17082b() {
        try {
            C2852ep.f13904a.execute(new RunnableC2694b(this));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    private void m17080c() {
        C2858ev.m15778a();
        C2969ig.m15443a();
        C2966id.m15457a();
        C2910gj.m15607a();
        C2890fr.m15707a();
        C2906gg.m15625a().m15624b();
    }

    /* compiled from: td */
    /* renamed from: com.talkingdata.sdk.TDAntiCheatingService$a */
    /* loaded from: classes2.dex */
    public class C2537a extends BroadcastReceiver {
        private C2537a() {
        }

        /* synthetic */ C2537a(TDAntiCheatingService tDAntiCheatingService, RunnableC2662a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                if (C2664ab.f13513g == null) {
                    C2664ab.f13513g = context.getApplicationContext();
                }
                try {
                    String packageName = C2664ab.f13513g.getPackageName();
                    String stringExtra = intent.getStringExtra("pkg");
                    if (stringExtra == null || !stringExtra.equals(packageName)) {
                        if (intent.getBooleanExtra("isCheck", false)) {
                            m17077a(context);
                        } else if (TDAntiCheatingService.this.f12738b < 50) {
                            String stringExtra2 = intent.getStringExtra("packageName");
                            if (!TDAntiCheatingService.this.f12739c.containsKey(stringExtra2)) {
                                String stringExtra3 = intent.getStringExtra("appKey");
                                String stringExtra4 = intent.getStringExtra("tdId");
                                if (stringExtra2 != null && stringExtra3 != null && stringExtra4 != null && !stringExtra2.isEmpty() && !stringExtra3.isEmpty() && !stringExtra4.isEmpty()) {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("packageName", stringExtra2);
                                    jSONObject.put(C4745bt.f20075f, stringExtra3);
                                    jSONObject.put("tdid", stringExtra4);
                                    TDAntiCheatingService.this.f12739c.put(stringExtra2, jSONObject);
                                    m17078a();
                                    TDAntiCheatingService.m17079c(TDAntiCheatingService.this);
                                }
                            }
                        }
                    } else if (intent.getBooleanExtra("isStop", false)) {
                        TDAntiCheatingService.this.stopSelf();
                    }
                } catch (Throwable unused) {
                }
            }
        }

        /* renamed from: a */
        private void m17078a() {
            try {
                C2852ep.f13904a.execute(new RunnableC2758c(this));
            } catch (Throwable unused) {
            }
        }

        /* renamed from: a */
        private void m17077a(Context context) {
            try {
                Intent intent = new Intent("com.talkingdata.sdk.TDAntiCheatingService");
                intent.putExtra("pkg", context.getPackageName());
                intent.putExtra("packageName", context.getPackageName());
                context.sendBroadcast(intent);
            } catch (Throwable unused) {
            }
        }
    }
}
