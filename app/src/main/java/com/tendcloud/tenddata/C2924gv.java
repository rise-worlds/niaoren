package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import com.stripe.android.view.ShippingInfoWidget;
import org.apache.tools.ant.taskdefs.WaitFor;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gv */
/* loaded from: classes2.dex */
public final class C2924gv {

    /* renamed from: a */
    static Handler f14122a = null;

    /* renamed from: b */
    static HandlerThread f14123b = null;

    /* renamed from: c */
    private static final String f14124c = "check_wifi_permission";

    /* renamed from: d */
    private static final String f14125d = "check_bs_permission";

    /* renamed from: e */
    private static final String f14126e = "check_gps_permission";

    /* renamed from: f */
    private static final int f14127f = 1;

    /* renamed from: g */
    private static final int f14128g = 2;

    /* renamed from: h */
    private static final int f14129h = 3;

    /* renamed from: i */
    private static final int f14130i = 4;

    /* renamed from: j */
    private static final long f14131j = 600000;

    /* renamed from: k */
    private static volatile C2924gv f14132k;

    /* renamed from: l */
    private static WifiManager f14133l;

    public final void onTDEBEventLocationEvent(C2943hl hlVar) {
    }

    /* renamed from: a */
    public static C2924gv m15549a() {
        if (f14132k == null) {
            synchronized (C2924gv.class) {
                if (f14132k == null) {
                    f14132k = new C2924gv();
                }
            }
        }
        return f14132k;
    }

    static {
        try {
            C2858ev.m15778a().register(m15549a());
        } catch (Throwable unused) {
        }
    }

    private C2924gv() {
        try {
            f14123b = new HandlerThread("locHandlerThread");
            f14123b.start();
            f14122a = new HandlerC2925gw(this, f14123b.getLooper());
            m15548a(4, 0L);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15548a(int i, long j) {
        try {
            Message obtain = Message.obtain();
            obtain.what = i;
            f14122a.sendMessageDelayed(obtain, j);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean m15546a(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 23
            boolean r1 = com.tendcloud.tenddata.C2855es.m15807a(r0)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0039
            android.content.Context r1 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x0032
            java.lang.String r4 = "android.permission.READ_PHONE_STATE"
            int r1 = r1.checkSelfPermission(r4)     // Catch: Throwable -> 0x0032
            if (r1 != 0) goto L_0x0016
            r1 = 1
            goto L_0x0017
        L_0x0016:
            r1 = 0
        L_0x0017:
            android.content.Context r4 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x0030
            java.lang.String r5 = "android.permission.ACCESS_COARSE_LOCATION"
            int r4 = r4.checkSelfPermission(r5)     // Catch: Throwable -> 0x0030
            if (r4 == 0) goto L_0x002e
            android.content.Context r4 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x0030
            java.lang.String r5 = "android.permission.ACCESS_FINE_LOCATION"
            int r4 = r4.checkSelfPermission(r5)     // Catch: Throwable -> 0x0030
            if (r4 != 0) goto L_0x002c
            goto L_0x002e
        L_0x002c:
            r4 = 0
            goto L_0x0062
        L_0x002e:
            r4 = 1
            goto L_0x0062
        L_0x0030:
            r4 = move-exception
            goto L_0x0034
        L_0x0032:
            r4 = move-exception
            r1 = 0
        L_0x0034:
            com.tendcloud.tenddata.C2933hb.postSDKError(r4)
            r4 = 0
            goto L_0x0062
        L_0x0039:
            android.content.Context r1 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x005c
            java.lang.String r4 = "android.permission.READ_PHONE_STATE"
            boolean r1 = com.tendcloud.tenddata.C2855es.m15792b(r1, r4)     // Catch: Throwable -> 0x005c
            android.content.Context r4 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x005a
            java.lang.String r5 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r4 = com.tendcloud.tenddata.C2855es.m15792b(r4, r5)     // Catch: Throwable -> 0x005a
            if (r4 != 0) goto L_0x0058
            android.content.Context r4 = com.tendcloud.tenddata.C2664ab.f13513g     // Catch: Throwable -> 0x005a
            java.lang.String r5 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r4 = com.tendcloud.tenddata.C2855es.m15792b(r4, r5)     // Catch: Throwable -> 0x005a
            if (r4 == 0) goto L_0x0056
            goto L_0x0058
        L_0x0056:
            r4 = 0
            goto L_0x0062
        L_0x0058:
            r4 = 1
            goto L_0x0062
        L_0x005a:
            r4 = move-exception
            goto L_0x005e
        L_0x005c:
            r4 = move-exception
            r1 = 0
        L_0x005e:
            com.tendcloud.tenddata.C2933hb.postSDKError(r4)
            r4 = 0
        L_0x0062:
            android.content.Context r5 = com.tendcloud.tenddata.C2664ab.f13513g
            java.lang.String r6 = "android.permission.ACCESS_WIFI_STATE"
            boolean r5 = com.tendcloud.tenddata.C2855es.m15792b(r5, r6)
            java.lang.String r6 = "check_bs_permission"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x0079
            if (r4 == 0) goto L_0x0077
            if (r1 == 0) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r2 = 0
        L_0x0078:
            return r2
        L_0x0079:
            java.lang.String r1 = "check_gps_permission"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x0082
            return r4
        L_0x0082:
            java.lang.String r1 = "check_wifi_permission"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0098
            boolean r8 = com.tendcloud.tenddata.C2855es.m15807a(r0)
            if (r8 == 0) goto L_0x0097
            if (r5 == 0) goto L_0x0095
            if (r4 == 0) goto L_0x0095
            goto L_0x0096
        L_0x0095:
            r2 = 0
        L_0x0096:
            return r2
        L_0x0097:
            return r5
        L_0x0098:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2924gv.m15546a(java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15545b() {
        try {
            if (!m15546a(f14124c)) {
                m15548a(1, WaitFor.DEFAULT_MAX_WAIT_MILLIS);
                return;
            }
            Context context = C2664ab.f13513g;
            Context context2 = C2664ab.f13513g;
            f14133l = (WifiManager) context.getSystemService("wifi");
            if (f14133l.isWifiEnabled()) {
                C2664ab.f13513g.registerReceiver(new C2941hj(f14133l), new IntentFilter("android.net.wifi.SCAN_RESULTS"));
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m15543c() {
        if (!m15546a(f14126e)) {
            m15548a(2, f14131j);
            return;
        }
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "locationUpdate";
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
            m15548a(2, f14131j);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    /* renamed from: d */
    public void m15541d() {
        try {
            if (!m15546a(f14125d)) {
                m15548a(3, WaitFor.DEFAULT_MAX_WAIT_MILLIS);
            } else if (C2664ab.f13513g != null) {
                try {
                    Context context = C2664ab.f13513g;
                    Context context2 = C2664ab.f13513g;
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                    if (telephonyManager.getSimState() == 5) {
                        telephonyManager.getCellLocation();
                        telephonyManager.listen(new C2939hh(), 16);
                        CellLocation.requestLocationUpdate();
                    }
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
