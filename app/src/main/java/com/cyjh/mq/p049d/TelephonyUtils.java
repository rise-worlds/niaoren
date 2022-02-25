package com.cyjh.mq.p049d;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import com.stripe.android.view.ShippingInfoWidget;

/* renamed from: com.cyjh.mq.d.f */
/* loaded from: classes.dex */
public final class TelephonyUtils {
    /* renamed from: b */
    private static String m20430b(@NonNull Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
        return (deviceId == null || deviceId.equals("")) ? m20431a(context) : deviceId;
    }

    /* renamed from: a */
    public static String m20431a(@NonNull Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }
}
