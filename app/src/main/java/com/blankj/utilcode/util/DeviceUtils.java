package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.cyjh.p045mq.p049d.C1363e;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* renamed from: com.blankj.utilcode.util.s */
/* loaded from: classes.dex */
public final class DeviceUtils {
    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22416a() {
        String[] strArr;
        for (String str : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"}) {
            if (new File(str + C1363e.f8870a).exists()) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = 17)
    /* renamed from: b */
    public static boolean m22412b() {
        return Settings.Secure.getInt(Utils.m24103a().getContentResolver(), "adb_enabled", 0) > 0;
    }

    /* renamed from: c */
    public static String m22411c() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: d */
    public static int m22410d() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: e */
    public static String m22409e() {
        String string = Settings.Secure.getString(Utils.m24103a().getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    /* renamed from: f */
    public static String m22408f() {
        return m22413a((String[]) null);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    /* renamed from: a */
    public static String m22413a(String... strArr) {
        String q = m22397q();
        if (m22414a(q, strArr)) {
            return q;
        }
        String r = m22396r();
        if (m22414a(r, strArr)) {
            return r;
        }
        String p = m22398p();
        if (m22414a(p, strArr)) {
            return p;
        }
        String t = m22394t();
        return m22414a(t, strArr) ? t : "";
    }

    /* renamed from: a */
    private static boolean m22414a(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return !"02:00:00:00:00:00".equals(str);
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    /* renamed from: p */
    private static String m22398p() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) Utils.m24103a().getApplicationContext().getSystemService("wifi");
            return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "02:00:00:00:00:00" : connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    /* renamed from: q */
    private static String m22397q() {
        byte[] hardwareAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement != null && nextElement.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = nextElement.getHardwareAddress()) != null && hardwareAddress.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i])));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    /* renamed from: r */
    private static String m22396r() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress s = m22395s();
            if (s == null || (byInetAddress = NetworkInterface.getByInetAddress(s)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return "02:00:00:00:00:00";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i])));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    /* renamed from: s */
    private static InetAddress m22395s() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && nextElement2.getHostAddress().indexOf(58) < 0) {
                            return nextElement2;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: t */
    private static String m22394t() {
        String str;
        String str2;
        ShellUtils.C0985a a = ShellUtils.m23276a("getprop wifi.interface", false);
        if (a.f6748a != 0 || (str = a.f6749b) == null) {
            return "02:00:00:00:00:00";
        }
        ShellUtils.C0985a a2 = ShellUtils.m23276a("cat /sys/class/net/" + str + "/address", false);
        return (a2.f6748a != 0 || (str2 = a2.f6749b) == null || str2.length() <= 0) ? "02:00:00:00:00:00" : str2;
    }

    /* renamed from: g */
    public static String m22407g() {
        return Build.MANUFACTURER;
    }

    /* renamed from: h */
    public static String m22406h() {
        String str = Build.MODEL;
        return str != null ? str.trim().replaceAll("\\s*", "") : "";
    }

    /* renamed from: i */
    public static String[] m22405i() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
    }

    /* renamed from: j */
    public static void m22404j() {
        ShellUtils.m23276a("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        Utils.m24103a().startActivity(intent.addFlags(268435456));
    }

    /* renamed from: k */
    public static void m22403k() {
        ShellUtils.m23276a("reboot", true);
        Intent intent = new Intent("android.intent.action.REBOOT");
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        Utils.m24103a().sendBroadcast(intent);
    }

    /* renamed from: a */
    public static void m22415a(String str) {
        ((PowerManager) Utils.m24103a().getSystemService("power")).reboot(str);
    }

    /* renamed from: l */
    public static void m22402l() {
        ShellUtils.m23276a("reboot recovery", true);
    }

    /* renamed from: m */
    public static void m22401m() {
        ShellUtils.m23276a("reboot bootloader", true);
    }

    /* renamed from: n */
    public static boolean m22400n() {
        return (Utils.m24103a().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    /* renamed from: o */
    public static boolean m22399o() {
        String networkOperatorName;
        if ((Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.SERIAL.equalsIgnoreCase("unknown") || Build.SERIAL.equalsIgnoreCase("android") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) || Debug.isDebuggerConnected()) {
            return true;
        }
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) Utils.m24103a().getSystemService(ShippingInfoWidget.f12563f);
        if (!(telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null)) {
            str = networkOperatorName;
        }
        if (str.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return intent.resolveActivity(Utils.m24103a().getPackageManager()) != null;
    }
}
