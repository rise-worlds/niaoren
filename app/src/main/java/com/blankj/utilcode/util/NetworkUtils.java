package com.blankj.utilcode.util;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.Utils;
import com.stripe.android.view.ShippingInfoWidget;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.ag */
/* loaded from: classes.dex */
public final class NetworkUtils {

    /* compiled from: NetworkUtils.java */
    /* renamed from: com.blankj.utilcode.util.ag$a */
    /* loaded from: classes.dex */
    public enum EnumC0976a {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m23658a() {
        Utils.m24103a().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: b */
    public static boolean m23652b() {
        NetworkInfo t = m23625t();
        return t != null && t.isConnected();
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<Boolean> m23657a(@NonNull Utils.AbstractC0953b<Boolean> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<Boolean>(bVar) { // from class: com.blankj.utilcode.util.ag.1
                @RequiresPermission("android.permission.INTERNET")
                /* renamed from: a */
                public Boolean mo23263b() {
                    return Boolean.valueOf(NetworkUtils.m23647c());
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: c */
    public static boolean m23647c() {
        return m23640e() || m23656a((String) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: b */
    public static void m23651b(Utils.AbstractC0953b<Boolean> bVar) {
        m23655a("", bVar);
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<Boolean> m23655a(final String str, @NonNull Utils.AbstractC0953b<Boolean> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<Boolean>(bVar) { // from class: com.blankj.utilcode.util.ag.2
                @RequiresPermission("android.permission.INTERNET")
                /* renamed from: a */
                public Boolean mo23263b() {
                    return Boolean.valueOf(NetworkUtils.m23656a(str));
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: d */
    public static boolean m23642d() {
        return m23656a("");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: a */
    public static boolean m23656a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        boolean z = true;
        ShellUtils.C0985a a = ShellUtils.m23276a(String.format("ping -c 1 %s", str), false);
        if (a.f6748a != 0) {
            z = false;
        }
        if (a.f6750c != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + a.f6750c);
        }
        if (a.f6749b != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + a.f6749b);
        }
        return z;
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: c */
    public static void m23646c(Utils.AbstractC0953b<Boolean> bVar) {
        m23649b("", bVar);
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: b */
    public static Utils.AbstractRunnableC0956e m23649b(final String str, @NonNull Utils.AbstractC0953b<Boolean> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<Boolean>(bVar) { // from class: com.blankj.utilcode.util.ag.3
                @RequiresPermission("android.permission.INTERNET")
                /* renamed from: a */
                public Boolean mo23263b() {
                    return Boolean.valueOf(NetworkUtils.m23650b(str));
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: e */
    public static boolean m23640e() {
        return m23650b("");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: b */
    public static boolean m23650b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            return InetAddress.getByName(str) != null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: f */
    public static boolean m23639f() {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) Utils.m24103a().getSystemService(ShippingInfoWidget.f12563f);
        } catch (Exception e) {
            Log.e("NetworkUtils", "getMobileDataEnabled: ", e);
        }
        if (telephonyManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return telephonyManager.isDataEnabled();
        }
        Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        if (declaredMethod != null) {
            return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
        }
        return false;
    }

    @RequiresPermission("android.permission.MODIFY_PHONE_STATE")
    /* renamed from: a */
    public static boolean m23654a(boolean z) {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) Utils.m24103a().getSystemService(ShippingInfoWidget.f12563f);
        } catch (Exception e) {
            Log.e("NetworkUtils", "setMobileDataEnabled: ", e);
        }
        if (telephonyManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            telephonyManager.setDataEnabled(z);
            return false;
        }
        Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", Boolean.TYPE);
        if (declaredMethod != null) {
            declaredMethod.invoke(telephonyManager, Boolean.valueOf(z));
            return true;
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: g */
    public static boolean m23638g() {
        NetworkInfo t = m23625t();
        return t != null && t.isAvailable() && t.getType() == 0;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: h */
    public static boolean m23637h() {
        NetworkInfo t = m23625t();
        return t != null && t.isAvailable() && t.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    /* renamed from: i */
    public static boolean m23636i() {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    /* renamed from: b */
    public static void m23648b(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        if (wifiManager != null && z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: j */
    public static boolean m23635j() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.m24103a().getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    /* renamed from: k */
    public static boolean m23634k() {
        return m23636i() && m23647c();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    /* renamed from: d */
    public static Utils.AbstractRunnableC0956e<Boolean> m23641d(@NonNull Utils.AbstractC0953b<Boolean> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<Boolean>(bVar) { // from class: com.blankj.utilcode.util.ag.4
                @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
                /* renamed from: a */
                public Boolean mo23263b() {
                    return Boolean.valueOf(NetworkUtils.m23634k());
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: l */
    public static String m23633l() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.m24103a().getSystemService(ShippingInfoWidget.f12563f);
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: m */
    public static EnumC0976a m23632m() {
        if (m23626s()) {
            return EnumC0976a.NETWORK_ETHERNET;
        }
        NetworkInfo t = m23625t();
        if (t != null && t.isAvailable()) {
            if (t.getType() != 1) {
                if (t.getType() == 0) {
                    switch (t.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                            return EnumC0976a.NETWORK_2G;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            return EnumC0976a.NETWORK_3G;
                        case 13:
                        case 18:
                            return EnumC0976a.NETWORK_4G;
                        default:
                            String subtypeName = t.getSubtypeName();
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                return EnumC0976a.NETWORK_3G;
                            }
                            break;
                    }
                }
            } else {
                return EnumC0976a.NETWORK_WIFI;
            }
        }
        return EnumC0976a.NETWORK_UNKNOWN;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: s */
    private static boolean m23626s() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.m24103a().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    /* renamed from: t */
    private static NetworkInfo m23625t() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.m24103a().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<String> m23653a(final boolean z, @NonNull Utils.AbstractC0953b<String> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<String>(bVar) { // from class: com.blankj.utilcode.util.ag.5
                @RequiresPermission("android.permission.INTERNET")
                /* renamed from: a */
                public String mo23263b() {
                    return NetworkUtils.m23643c(z);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: c */
    public static String m23643c(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int indexOf = hostAddress.indexOf(37);
                        if (indexOf < 0) {
                            return hostAddress.toUpperCase();
                        }
                        return hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: n */
    public static String m23631n() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                    int size = interfaceAddresses.size();
                    for (int i = 0; i < size; i++) {
                        InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: c */
    public static Utils.AbstractRunnableC0956e<String> m23644c(final String str, @NonNull Utils.AbstractC0953b<String> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<String>(bVar) { // from class: com.blankj.utilcode.util.ag.6
                @RequiresPermission("android.permission.INTERNET")
                /* renamed from: a */
                public String mo23263b() {
                    return NetworkUtils.m23645c(str);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    /* renamed from: c */
    public static String m23645c(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    /* renamed from: o */
    public static String m23630o() {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    /* renamed from: p */
    public static String m23629p() {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    /* renamed from: q */
    public static String m23628q() {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    /* renamed from: r */
    public static String m23627r() {
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }
}
