package p110z1;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.p003v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.tar.TarConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Marker;

/* renamed from: z1.cw */
/* loaded from: classes3.dex */
public final class C5170cw {

    /* renamed from: a */
    private static C5170cw f21103a = new C5170cw();

    private C5170cw() {
    }

    /* renamed from: a */
    public static String m3511a(Context context) {
        if (m3510a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public static C5170cw m3512a() {
        return f21103a;
    }

    /* renamed from: a */
    private static boolean m3510a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    /* renamed from: b */
    public static String m3509b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* renamed from: b */
    public static String m3508b(Context context) {
        String str = "";
        if (m3510a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /* renamed from: c */
    public static String m3507c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(C5097cq.m3700a().getPath());
                j = statFs.getBlockSize() * statFs.getAvailableBlocks();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    /* renamed from: c */
    public static String m3506c(Context context) {
        int i = 0;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
        }
        return i == 1 ? "1" : ResultTypeConstant.f7213z;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:15:0x0044
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: d */
    public static java.lang.String m3505d() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x0073
            java.io.File r3 = new java.io.File     // Catch: Throwable -> 0x0073
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: Throwable -> 0x0073
            r2.<init>(r3)     // Catch: Throwable -> 0x0073
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: Throwable -> 0x005a
            r3.<init>(r2)     // Catch: Throwable -> 0x005a
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: Throwable -> 0x0055
            r4.<init>(r3)     // Catch: Throwable -> 0x0055
            r1 = 1
            r5 = 1
        L_0x001b:
            r6 = 100
            if (r5 >= r6) goto L_0x0049
            java.lang.String r6 = r4.readLine()     // Catch: Throwable -> 0x0047
            if (r6 == 0) goto L_0x0049
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: Throwable -> 0x0047
            if (r7 < 0) goto L_0x0041
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: Throwable -> 0x0047
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: Throwable -> 0x0047
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: Throwable -> 0x0047
            java.lang.String r0 = r1.trim()     // Catch: Throwable -> 0x0047
            goto L_0x0049
        L_0x0041:
            int r5 = r5 + 1
            goto L_0x001b
        L_0x0044:
            r0 = move-exception
            r1 = r4
            goto L_0x005f
        L_0x0047:
            r1 = r4
            goto L_0x0075
        L_0x0049:
            r4.close()     // Catch: Throwable -> 0x004c
        L_0x004c:
            r3.close()     // Catch: Throwable -> 0x004f
        L_0x004f:
            r2.close()     // Catch: Throwable -> 0x0086
            goto L_0x0086
        L_0x0053:
            r0 = move-exception
            goto L_0x005f
        L_0x0055:
            goto L_0x0075
        L_0x0057:
            r0 = move-exception
            r3 = r1
            goto L_0x005f
        L_0x005a:
            r3 = r1
            goto L_0x0075
        L_0x005c:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L_0x005f:
            if (r1 == 0) goto L_0x0066
            r1.close()     // Catch: Throwable -> 0x0065
            goto L_0x0066
        L_0x0065:
        L_0x0066:
            if (r3 == 0) goto L_0x006d
            r3.close()     // Catch: Throwable -> 0x006c
            goto L_0x006d
        L_0x006c:
        L_0x006d:
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch: Throwable -> 0x0072
        L_0x0072:
            throw r0
        L_0x0073:
            r2 = r1
            r3 = r2
        L_0x0075:
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch: Throwable -> 0x007b
            goto L_0x007c
        L_0x007b:
        L_0x007c:
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch: Throwable -> 0x0082
            goto L_0x0083
        L_0x0082:
        L_0x0083:
            if (r2 == 0) goto L_0x0086
            goto L_0x004f
        L_0x0086:
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = ""
        L_0x008a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3505d():java.lang.String");
    }

    /* renamed from: d */
    public static String m3504d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put(NotificationCompat.CATEGORY_CALL, String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put(NotificationCompat.CATEGORY_ALARM, String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    /* renamed from: e */
    public static String m3502e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    /* renamed from: f */
    public static String m3501f() {
        String v = m3470v();
        return !C5097cq.m3699a(v) ? v : m3469w();
    }

    /* renamed from: f */
    public static String m3500f(Context context) {
        List<Sensor> sensorList;
        String str = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    str = C5097cq.m3691e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:15:0x002e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: g */
    public static java.lang.String m3499g() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: Throwable -> 0x004a
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: Throwable -> 0x004a
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: Throwable -> 0x0037
            r2.<init>(r1)     // Catch: Throwable -> 0x0037
            java.lang.String r0 = r2.readLine()     // Catch: Throwable -> 0x0030
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch: Throwable -> 0x0030
            if (r0 == 0) goto L_0x0027
            int r3 = r0.length     // Catch: Throwable -> 0x0030
            r4 = 1
            if (r3 <= r4) goto L_0x0027
            r0 = r0[r4]     // Catch: Throwable -> 0x0030
            r1.close()     // Catch: Throwable -> 0x0023
        L_0x0023:
            r2.close()     // Catch: Throwable -> 0x0026
        L_0x0026:
            return r0
        L_0x0027:
            r1.close()     // Catch: Throwable -> 0x002a
        L_0x002a:
            r2.close()     // Catch: Throwable -> 0x0057
            goto L_0x0057
        L_0x002e:
            r0 = move-exception
            goto L_0x003d
        L_0x0030:
            r0 = r2
            goto L_0x004b
        L_0x0032:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x003d
        L_0x0037:
            goto L_0x004b
        L_0x0039:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x003d:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch: Throwable -> 0x0043
            goto L_0x0044
        L_0x0043:
        L_0x0044:
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch: Throwable -> 0x0049
        L_0x0049:
            throw r0
        L_0x004a:
            r1 = r0
        L_0x004b:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch: Throwable -> 0x0051
            goto L_0x0052
        L_0x0051:
        L_0x0052:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch: Throwable -> 0x0057
        L_0x0057:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3499g():java.lang.String");
    }

    /* renamed from: g */
    public static String m3498g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put(ClientCookie.VERSION_ATTR, sensor.getVersion());
                            jSONObject.put("vendor", sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:11:0x002d
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: h */
    public static java.lang.String m3497h() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: Throwable -> 0x0049
            r4.<init>(r0)     // Catch: Throwable -> 0x0049
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: Throwable -> 0x0036
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch: Throwable -> 0x0036
            java.lang.String r1 = r0.readLine()     // Catch: Throwable -> 0x002f
            if (r1 == 0) goto L_0x0026
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch: Throwable -> 0x002f
            r5 = 1
            r1 = r1[r5]     // Catch: Throwable -> 0x002f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: Throwable -> 0x002f
            long r1 = (long) r1
            r2 = r1
        L_0x0026:
            r4.close()     // Catch: Throwable -> 0x0029
        L_0x0029:
            r0.close()     // Catch: Throwable -> 0x0056
            goto L_0x0056
        L_0x002d:
            r1 = move-exception
            goto L_0x003c
        L_0x002f:
            r1 = r0
            goto L_0x004a
        L_0x0031:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x003c
        L_0x0036:
            goto L_0x004a
        L_0x0038:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x003c:
            if (r4 == 0) goto L_0x0043
            r4.close()     // Catch: Throwable -> 0x0042
            goto L_0x0043
        L_0x0042:
        L_0x0043:
            if (r0 == 0) goto L_0x0048
            r0.close()     // Catch: Throwable -> 0x0048
        L_0x0048:
            throw r1
        L_0x0049:
            r4 = r1
        L_0x004a:
            if (r4 == 0) goto L_0x0051
            r4.close()     // Catch: Throwable -> 0x0050
            goto L_0x0051
        L_0x0050:
        L_0x0051:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch: Throwable -> 0x0056
        L_0x0056:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3497h():java.lang.String");
    }

    /* renamed from: h */
    public static String m3496h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + Marker.ANY_MARKER + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m3495i() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    /* renamed from: i */
    public static String m3494i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: j */
    public static String m3493j() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = statFs.getBlockSize() * statFs.getBlockCount();
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    /* renamed from: j */
    public static String m3492j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: k */
    public static String m3491k() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: k */
    public static String m3490k(Context context) {
        if (m3510a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        String str = "";
        try {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (str == null || str.length() == 0 || "02:00:00:00:00:00".equals(str)) {
                return m3471u();
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* renamed from: l */
    public static String m3489l() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: l */
    public static String m3488l(Context context) {
        if (m3510a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            str = ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getSimSerialNumber();
            if (str != null) {
                if (str == null) {
                    return str;
                }
                if (str.length() != 0) {
                    return str;
                }
            }
            return "";
        } catch (Throwable unused) {
            return str;
        }
    }

    /* renamed from: m */
    public static String m3487m() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: m */
    public static String m3486m(Context context) {
        String str = "";
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: n */
    public static String m3485n() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: n */
    public static String m3484n(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: o */
    public static String m3483o() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: o */
    public static String m3482o(Context context) {
        String str = "";
        if (m3510a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                str = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: p */
    public static String m3481p() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append(TarConstants.VERSION_POSIX + ":");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : ResultTypeConstant.f7213z);
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: p */
    public static String m3480p(Context context) {
        String str = "";
        try {
            str = Build.VERSION.SDK_INT >= 29 ? "" : (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 28) ? Build.SERIAL : Build.getSerial();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: q */
    public static String m3479q() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append(TarConstants.VERSION_POSIX);
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append(ResultTypeConstant.f7213z);
            }
        }
        return sb.toString();
    }

    /* renamed from: q */
    public static String m3478q(Context context) {
        try {
            String t = m3472t(context);
            String x = m3468x();
            if (!C5097cq.m3695b(t) || !C5097cq.m3695b(x)) {
                return "";
            }
            return t + ":" + m3468x();
        } catch (Throwable unused) {
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:15:0x007c
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: r */
    public static java.lang.String m3477r() {
        /*
            java.lang.String r0 = "00"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            java.lang.String r3 = "/system/build.prop"
            java.lang.String r4 = "ro.product.name=sdk"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/tty/drivers"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/cpuinfo"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ":"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.append(r0)
            java.util.Set r0 = r2.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x003d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0094
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 48
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch: Throwable -> 0x008b
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: Throwable -> 0x008b
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: Throwable -> 0x008b
            r8.<init>(r3)     // Catch: Throwable -> 0x008b
            r7.<init>(r8)     // Catch: Throwable -> 0x008b
            r6.<init>(r7)     // Catch: Throwable -> 0x008b
        L_0x005b:
            java.lang.String r4 = r6.readLine()     // Catch: Throwable -> 0x007f
            if (r4 == 0) goto L_0x0073
            java.lang.String r4 = r4.toLowerCase()     // Catch: Throwable -> 0x007f
            java.lang.Object r7 = r2.get(r3)     // Catch: Throwable -> 0x007f
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: Throwable -> 0x007f
            boolean r4 = r4.contains(r7)     // Catch: Throwable -> 0x007f
            if (r4 == 0) goto L_0x005b
            r5 = 49
        L_0x0073:
            r1.append(r5)
            r6.close()     // Catch: Throwable -> 0x007a
            goto L_0x003d
        L_0x007a:
            goto L_0x003d
        L_0x007c:
            r0 = move-exception
            r4 = r6
            goto L_0x0082
        L_0x007f:
            r4 = r6
            goto L_0x008b
        L_0x0081:
            r0 = move-exception
        L_0x0082:
            r1.append(r5)
            if (r4 == 0) goto L_0x008a
            r4.close()     // Catch: Throwable -> 0x008a
        L_0x008a:
            throw r0
        L_0x008b:
            r1.append(r5)
            if (r4 == 0) goto L_0x003d
            r4.close()     // Catch: Throwable -> 0x007a
            goto L_0x003d
        L_0x0094:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3477r():java.lang.String");
    }

    /* renamed from: r */
    public static String m3476r(Context context) {
        try {
            long j = 0;
            if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            for (int i = 0; i < 5; i++) {
                long j2 = -1;
                try {
                    j2 = new File(strArr[i]).lastModified();
                } catch (Throwable unused) {
                }
                j = Math.max(j2, j);
            }
            return "1:" + j;
        } catch (Throwable unused2) {
            return "";
        }
    }

    /* renamed from: s */
    public static String m3475s() {
        StringBuilder sb = new StringBuilder();
        sb.append(TarConstants.VERSION_POSIX + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = null;
                String str3 = (String) Build.class.getField(str).get(null);
                String str4 = (String) linkedHashMap.get(str);
                if (str3 != null) {
                    str2 = str3.toLowerCase();
                }
                if (str2 != null && str2.contains(str4)) {
                    c = '1';
                }
            } catch (Throwable unused) {
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* renamed from: s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m3474s(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch: Throwable -> 0x003f
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch: Throwable -> 0x003f
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch: Throwable -> 0x003f
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch: Throwable -> 0x003f
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch: Throwable -> 0x003f
            r1 = 2
            if (r3 == r1) goto L_0x0022
            r1 = 5
            if (r3 != r1) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r3 = 0
            goto L_0x0023
        L_0x0022:
            r3 = 1
        L_0x0023:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: Throwable -> 0x003f
            r1.<init>()     // Catch: Throwable -> 0x003f
            if (r3 == 0) goto L_0x002d
            java.lang.String r3 = "1"
            goto L_0x002f
        L_0x002d:
            java.lang.String r3 = "0"
        L_0x002f:
            r1.append(r3)     // Catch: Throwable -> 0x003f
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch: Throwable -> 0x003f
            r1.append(r0)     // Catch: Throwable -> 0x003f
            java.lang.String r3 = r1.toString()     // Catch: Throwable -> 0x003f
            return r3
        L_0x003f:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3474s(android.content.Context):java.lang.String");
    }

    /* renamed from: t */
    public static String m3473t() {
        StringBuilder sb = new StringBuilder();
        sb.append(TarConstants.VERSION_POSIX + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(acf.f15167F, "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String b = C5097cq.m3694b(str, "");
            if (b != null && b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /* renamed from: t */
    private static String m3472t(Context context) {
        if (m3510a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0) {
                return null;
            }
            int subtype = activeNetworkInfo.getSubtype();
            if (!(subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11)) {
                if (!(subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15)) {
                    return subtype == 13 ? AppAgent.f8195g : "UNKNOW";
                }
                return AppAgent.f8194f;
            }
            return AppAgent.f8193e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: u */
    private static String m3471u() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list == null) {
                return "02:00:00:00:00:00";
            }
            for (NetworkInterface networkInterface : list) {
                if (!(networkInterface == null || networkInterface.getName() == null || !networkInterface.getName().equalsIgnoreCase("wlan0"))) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "02:00:00:00:00:00";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Integer.valueOf(hardwareAddress[i] & 255)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:13:0x002b
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: v */
    private static java.lang.String m3470v() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: Throwable -> 0x0041
            r2.<init>(r0)     // Catch: Throwable -> 0x0041
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: Throwable -> 0x0042
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r3)     // Catch: Throwable -> 0x0042
            java.lang.String r1 = r0.readLine()     // Catch: Throwable -> 0x0030
            boolean r3 = p110z1.C5097cq.m3699a(r1)     // Catch: Throwable -> 0x0030
            if (r3 != 0) goto L_0x0024
            java.lang.String r1 = r1.trim()     // Catch: Throwable -> 0x0030
            r0.close()     // Catch: Throwable -> 0x0020
        L_0x0020:
            r2.close()     // Catch: Throwable -> 0x0023
        L_0x0023:
            return r1
        L_0x0024:
            r0.close()     // Catch: Throwable -> 0x0027
        L_0x0027:
            r2.close()     // Catch: Throwable -> 0x004a
            goto L_0x004a
        L_0x002b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0036
        L_0x0030:
            r1 = r0
            goto L_0x0042
        L_0x0032:
            r0 = move-exception
            goto L_0x0036
        L_0x0034:
            r0 = move-exception
            r2 = r1
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch: Throwable -> 0x003b
        L_0x003b:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch: Throwable -> 0x0040
        L_0x0040:
            throw r0
        L_0x0041:
            r2 = r1
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch: Throwable -> 0x0047
        L_0x0047:
            if (r2 == 0) goto L_0x004a
            goto L_0x0027
        L_0x004a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3470v():java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:18:0x0041
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: w */
    private static java.lang.String m3469w() {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: Throwable -> 0x005a
            r3.<init>(r0)     // Catch: Throwable -> 0x005a
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: Throwable -> 0x0048
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch: Throwable -> 0x0048
        L_0x0011:
            java.lang.String r2 = r0.readLine()     // Catch: Throwable -> 0x0043
            if (r2 == 0) goto L_0x003a
            boolean r4 = p110z1.C5097cq.m3699a(r2)     // Catch: Throwable -> 0x0043
            if (r4 != 0) goto L_0x0011
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch: Throwable -> 0x0043
            if (r2 == 0) goto L_0x0011
            int r4 = r2.length     // Catch: Throwable -> 0x0043
            r5 = 1
            if (r4 <= r5) goto L_0x0011
            r4 = 0
            r4 = r2[r4]     // Catch: Throwable -> 0x0043
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch: Throwable -> 0x0043
            if (r4 == 0) goto L_0x0011
            r2 = r2[r5]     // Catch: Throwable -> 0x0043
            java.lang.String r1 = r2.trim()     // Catch: Throwable -> 0x0043
        L_0x003a:
            r3.close()     // Catch: Throwable -> 0x003d
        L_0x003d:
            r0.close()     // Catch: Throwable -> 0x0067
            goto L_0x0067
        L_0x0041:
            r1 = move-exception
            goto L_0x004d
        L_0x0043:
            r2 = r0
            goto L_0x005b
        L_0x0045:
            r1 = move-exception
            r0 = r2
            goto L_0x004d
        L_0x0048:
            goto L_0x005b
        L_0x004a:
            r1 = move-exception
            r0 = r2
            r3 = r0
        L_0x004d:
            if (r3 == 0) goto L_0x0054
            r3.close()     // Catch: Throwable -> 0x0053
            goto L_0x0054
        L_0x0053:
        L_0x0054:
            if (r0 == 0) goto L_0x0059
            r0.close()     // Catch: Throwable -> 0x0059
        L_0x0059:
            throw r1
        L_0x005a:
            r3 = r2
        L_0x005b:
            if (r3 == 0) goto L_0x0062
            r3.close()     // Catch: Throwable -> 0x0061
            goto L_0x0062
        L_0x0061:
        L_0x0062:
            if (r2 == 0) goto L_0x0067
            r2.close()     // Catch: Throwable -> 0x0067
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5170cw.m3469w():java.lang.String");
    }

    /* renamed from: x */
    private static String m3468x() {
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
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: e */
    public final String m3503e() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new C5184cx(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}
