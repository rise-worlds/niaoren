package com.cyjh.mobileanjian.ipc.rpc;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import org.apache.commons.p105io.FilenameUtils;

/* loaded from: classes.dex */
public class AndroidHelper {

    /* renamed from: a */
    private static Context f8301a = null;

    /* renamed from: b */
    private static int f8302b = 100;

    /* renamed from: c */
    private static boolean f8303c;

    public static void init(Context context) {
        f8301a = context;
        Telephony.init(context);
    }

    public static void setClipboardText(String str) {
        ((ClipboardManager) f8301a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", str));
    }

    public static String getClipboardText() {
        ClipData primaryClip;
        ClipData.Item itemAt;
        ClipboardManager clipboardManager = (ClipboardManager) f8301a.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip() || (primaryClip = clipboardManager.getPrimaryClip()) == null || (itemAt = primaryClip.getItemAt(0)) == null) {
            return "";
        }
        return itemAt.getText().toString();
    }

    public static String getForegroundPackage() {
        if (Build.VERSION.SDK_INT < 21) {
            return ((ActivityManager) f8301a.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<UsageStats> queryUsageStats = ((UsageStatsManager) f8301a.getSystemService("usagestats")).queryUsageStats(4, currentTimeMillis - 2000, currentTimeMillis);
        UsageStats usageStats = null;
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            return null;
        }
        for (UsageStats usageStats2 : queryUsageStats) {
            if (usageStats == null || usageStats.getLastTimeUsed() < usageStats2.getLastTimeUsed()) {
                usageStats = usageStats2;
            }
        }
        return usageStats.getPackageName();
    }

    public static boolean isAppForeground(String str) {
        return str.equals(getForegroundPackage());
    }

    public static String getAppDataPath(String str) {
        try {
            return f8301a.getPackageManager().getApplicationInfo(str, 0).dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIp() {
        if (((ConnectivityManager) f8301a.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            WifiManager wifiManager = (WifiManager) f8301a.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ipAddress & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                int i = ipAddress >>> 8;
                stringBuffer.append(i & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                int i2 = i >>> 8;
                stringBuffer.append(i2 & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                stringBuffer.append((i2 >>> 8) & 255);
                return stringBuffer.toString();
            }
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getImsi() {
        return ((TelephonyManager) f8301a.getSystemService(ShippingInfoWidget.f12563f)).getSubscriberId();
    }

    public static String getModel() {
        return Build.BRAND + ExpandableTextView.f6958c + Build.MODEL;
    }

    public static String getIccid() {
        return ((TelephonyManager) f8301a.getSystemService(ShippingInfoWidget.f12563f)).getSimSerialNumber();
    }

    public static String getNetworkTime() {
        try {
            URLConnection openConnection = new URL("http://www.baidu.com").openConnection();
            openConnection.setReadTimeout(5000);
            openConnection.setConnectTimeout(5000);
            openConnection.connect();
            Date date = new Date(openConnection.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Log.e("tag", "getNetworkTime: by baidu url:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            try {
                URLConnection openConnection2 = new URL("http://www.taobao.com").openConnection();
                openConnection2.setReadTimeout(5000);
                openConnection2.setConnectTimeout(5000);
                openConnection2.connect();
                Date date2 = new Date(openConnection2.getDate());
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                Log.e("tag", "getNetworkTime: by a li url:" + simpleDateFormat2.format(date2));
                return simpleDateFormat2.format(date2);
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            URLConnection openConnection22 = new URL("http://www.taobao.com").openConnection();
            openConnection22.setReadTimeout(5000);
            openConnection22.setConnectTimeout(5000);
            openConnection22.connect();
            Date date22 = new Date(openConnection22.getDate());
            SimpleDateFormat simpleDateFormat22 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Log.e("tag", "getNetworkTime: by a li url:" + simpleDateFormat22.format(date22));
            return simpleDateFormat22.format(date22);
        }
    }

    public static String batteryLevel() {
        f8303c = false;
        f8301a.registerReceiver(new BroadcastReceiver() { // from class: com.cyjh.mobileanjian.ipc.rpc.AndroidHelper.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int intExtra = intent.getIntExtra("level", -1);
                int intExtra2 = intent.getIntExtra("scale", -1);
                if (intExtra >= 0 && intExtra2 > 0) {
                    int unused = AndroidHelper.f8302b = (intExtra * 100) / intExtra2;
                }
                boolean unused2 = AndroidHelper.f8303c = true;
            }
        }, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        while (!f8303c) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(f8302b);
    }

    public static String isScreenOn() {
        return ((PowerManager) f8301a.getSystemService("power")).isScreenOn() ? "true" : "false";
    }

    public static String getBacklightLevel() {
        try {
            return String.valueOf((int) ((Settings.System.getInt(f8301a.getContentResolver(), "screen_brightness") * 100) / 255.0d));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return String.valueOf(0);
        }
    }
}
