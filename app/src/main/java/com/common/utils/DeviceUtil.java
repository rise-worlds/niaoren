package com.common.utils;

import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.lbd.p054xj.app.XJApp;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.UUID;

/* loaded from: classes.dex */
public class DeviceUtil {
    private static String sp_uuid = "sp_uuid";
    private static String deviceUUID = SharedPreferencesUtil.getInstance().getString(sp_uuid);

    public static String getUUID() {
        if (!TextUtils.isEmpty(deviceUUID)) {
            return deviceUUID;
        }
        String string = Settings.System.getString(XJApp.getInstance().getApplicationContext().getContentResolver(), "android_id");
        try {
            if (!TextUtils.isEmpty(string)) {
                if (!"9774d56d682e549c".equals(string)) {
                    return string;
                }
                TelephonyManager telephonyManager = (TelephonyManager) XJApp.getInstance().getApplicationContext().getSystemService(ShippingInfoWidget.f12563f);
                if (ActivityCompat.checkSelfPermission(XJApp.getInstance().getApplicationContext(), "android.permission.READ_PHONE_STATE") != 0) {
                    String deviceId = telephonyManager.getDeviceId();
                    if (TextUtils.isEmpty(deviceId)) {
                        deviceId = Build.SERIAL;
                    }
                    deviceUUID = deviceId;
                }
            }
            if (TextUtils.isEmpty(deviceUUID)) {
                deviceUUID = UUID.randomUUID().toString();
            }
            SharedPreferencesUtil.getInstance().putStringValue(sp_uuid, deviceUUID);
            return deviceUUID;
        } catch (Exception unused) {
            deviceUUID = UUID.randomUUID().toString();
            SharedPreferencesUtil.getInstance().putStringValue(sp_uuid, deviceUUID);
            return deviceUUID;
        }
    }
}
