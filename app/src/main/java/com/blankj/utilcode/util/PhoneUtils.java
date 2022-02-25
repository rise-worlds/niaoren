package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.stripe.android.view.ShippingInfoWidget;
import com.tencent.smtt.sdk.WebView;
import java.lang.reflect.Method;

/* renamed from: com.blankj.utilcode.util.ak */
/* loaded from: classes.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m23548a() {
        return m23530l().getPhoneType() != 0;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: b */
    public static String m23543b() {
        TelephonyManager l = m23530l();
        String deviceId = l.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = l.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = l.getMeid();
        return TextUtils.isEmpty(meid) ? "" : meid;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: c */
    public static String m23539c() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: d */
    public static String m23538d() {
        TelephonyManager l = m23530l();
        if (Build.VERSION.SDK_INT >= 26) {
            return l.getImei();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Method declaredMethod = l.getClass().getDeclaredMethod("getImei", new Class[0]);
                declaredMethod.setAccessible(true);
                String str = (String) declaredMethod.invoke(l, new Object[0]);
                if (str != null) {
                    return str;
                }
            } catch (Exception e) {
                Log.e("PhoneUtils", "getIMEI: ", e);
            }
        }
        String deviceId = l.getDeviceId();
        return (deviceId == null || deviceId.length() != 15) ? "" : deviceId;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: a */
    public static String m23547a(int i) {
        TelephonyManager l = m23530l();
        if (Build.VERSION.SDK_INT >= 26) {
            return l.getImei(i);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Method declaredMethod = l.getClass().getDeclaredMethod("getImei", Integer.TYPE);
                declaredMethod.setAccessible(true);
                String str = (String) declaredMethod.invoke(l, Integer.valueOf(i));
                if (str != null) {
                    return str;
                }
            } catch (Exception e) {
                Log.e("PhoneUtils", "getIMEI: ", e);
            }
        }
        return m23538d();
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: e */
    public static String m23537e() {
        TelephonyManager l = m23530l();
        if (Build.VERSION.SDK_INT >= 26) {
            return l.getMeid();
        }
        return l.getDeviceId();
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: b */
    public static String m23542b(int i) {
        TelephonyManager l = m23530l();
        if (Build.VERSION.SDK_INT >= 26) {
            return l.getMeid(i);
        }
        return m23537e();
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: f */
    public static String m23536f() {
        return m23530l().getSubscriberId();
    }

    /* renamed from: g */
    public static int m23535g() {
        return m23530l().getPhoneType();
    }

    /* renamed from: h */
    public static boolean m23534h() {
        return m23530l().getSimState() == 5;
    }

    /* renamed from: i */
    public static String m23533i() {
        return m23530l().getSimOperatorName();
    }

    /* renamed from: j */
    public static String m23532j() {
        String simOperator = m23530l().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        char c = 65535;
        int hashCode = simOperator.hashCode();
        if (hashCode != 49679479) {
            if (hashCode != 49679502) {
                if (hashCode != 49679532) {
                    switch (hashCode) {
                        case 49679470:
                            if (simOperator.equals("46000")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 49679471:
                            if (simOperator.equals("46001")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 49679472:
                            if (simOperator.equals("46002")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 49679473:
                            if (simOperator.equals("46003")) {
                                c = 7;
                                break;
                            }
                            break;
                        default:
                            switch (hashCode) {
                                case 49679475:
                                    if (simOperator.equals("46005")) {
                                        c = '\b';
                                        break;
                                    }
                                    break;
                                case 49679476:
                                    if (simOperator.equals("46006")) {
                                        c = 5;
                                        break;
                                    }
                                    break;
                                case 49679477:
                                    if (simOperator.equals("46007")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                            }
                    }
                } else if (simOperator.equals("46020")) {
                    c = 3;
                }
            } else if (simOperator.equals("46011")) {
                c = '\t';
            }
        } else if (simOperator.equals("46009")) {
            c = 6;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case '\b':
            case '\t':
                return "中国电信";
            default:
                return simOperator;
        }
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: k */
    public static String m23531k() {
        TelephonyManager l = m23530l();
        return (((((((((((((("DeviceId(IMEI) = " + l.getDeviceId() + "\n") + "DeviceSoftwareVersion = " + l.getDeviceSoftwareVersion() + "\n") + "Line1Number = " + l.getLine1Number() + "\n") + "NetworkCountryIso = " + l.getNetworkCountryIso() + "\n") + "NetworkOperator = " + l.getNetworkOperator() + "\n") + "NetworkOperatorName = " + l.getNetworkOperatorName() + "\n") + "NetworkType = " + l.getNetworkType() + "\n") + "PhoneType = " + l.getPhoneType() + "\n") + "SimCountryIso = " + l.getSimCountryIso() + "\n") + "SimOperator = " + l.getSimOperator() + "\n") + "SimOperatorName = " + l.getSimOperatorName() + "\n") + "SimSerialNumber = " + l.getSimSerialNumber() + "\n") + "SimState = " + l.getSimState() + "\n") + "SubscriberId(IMSI) = " + l.getSubscriberId() + "\n") + "VoiceMailNumber = " + l.getVoiceMailNumber();
    }

    /* renamed from: a */
    public static boolean m23545a(String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str));
        if (!m23546a(intent)) {
            return false;
        }
        Utils.m24103a().startActivity(intent.addFlags(268435456));
        return true;
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    /* renamed from: b */
    public static boolean m23541b(String str) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse(WebView.SCHEME_TEL + str));
        if (!m23546a(intent)) {
            return false;
        }
        Utils.m24103a().startActivity(intent.addFlags(268435456));
        return true;
    }

    /* renamed from: a */
    public static boolean m23544a(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        if (!m23546a(intent)) {
            return false;
        }
        intent.putExtra("sms_body", str2);
        Utils.m24103a().startActivity(intent.addFlags(268435456));
        return true;
    }

    @RequiresPermission("android.permission.SEND_SMS")
    /* renamed from: b */
    public static void m23540b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            PendingIntent broadcast = PendingIntent.getBroadcast(Utils.m24103a(), 0, new Intent("send"), 0);
            SmsManager smsManager = SmsManager.getDefault();
            if (str2.length() >= 70) {
                for (String str3 : smsManager.divideMessage(str2)) {
                    smsManager.sendTextMessage(str, null, str3, broadcast, null);
                }
                return;
            }
            smsManager.sendTextMessage(str, null, str2, broadcast, null);
        }
    }

    /* renamed from: l */
    private static TelephonyManager m23530l() {
        return (TelephonyManager) Utils.m24103a().getSystemService(ShippingInfoWidget.f12563f);
    }

    /* renamed from: a */
    private static boolean m23546a(Intent intent) {
        return Utils.m24103a().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
