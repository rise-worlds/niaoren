package com.tendcloud.tenddata;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import org.apache.commons.p105io.FilenameUtils;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dt */
/* loaded from: classes2.dex */
public class C2819dt {

    /* renamed from: a */
    static TelephonyManager f13814a = null;

    /* renamed from: b */
    static String f13815b = null;

    /* renamed from: c */
    private static final String f13816c = "pref.deviceid.key";

    /* renamed from: d */
    private static final String f13817d = "00:00:00:00:00:00";

    /* renamed from: h */
    private static final String f13821h = ".tcookieid";

    /* renamed from: e */
    private static final Pattern f13818e = Pattern.compile("^([0-9A-F]{2}:){5}([0-9A-F]{2})$");

    /* renamed from: f */
    private static final Pattern f13819f = Pattern.compile("[0-3][0-9a-f]{24,32}");

    /* renamed from: g */
    private static final Pattern f13820g = Pattern.compile("[0-3][0-9a-f]{32}");

    /* renamed from: i */
    private static String f13822i = null;

    /* renamed from: j */
    private static boolean f13823j = false;

    /* renamed from: k */
    private static String f13824k = null;

    public static void init(Context context) {
        try {
            f13814a = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static synchronized String m15969a(Context context) {
        String str;
        synchronized (C2819dt.class) {
            if (f13815b == null) {
                f13815b = m15949l(context);
            }
            str = f13815b;
        }
        return str;
    }

    /* renamed from: b */
    public static String m15960b(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003d A[Catch: Throwable -> 0x0044, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Throwable -> 0x0044, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0012, B:10:0x001a, B:12:0x001e, B:13:0x0021, B:15:0x0027, B:19:0x002f, B:22:0x003d), top: B:29:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m15958c(android.content.Context r3) {
        /*
            r0 = 23
            r1 = 0
            boolean r0 = com.tendcloud.tenddata.C2855es.m15807a(r0)     // Catch: Throwable -> 0x0044
            if (r0 == 0) goto L_0x0012
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            int r0 = r3.checkSelfPermission(r0)     // Catch: Throwable -> 0x0044
            if (r0 == 0) goto L_0x0012
            return r1
        L_0x0012:
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = com.tendcloud.tenddata.C2855es.m15792b(r3, r0)     // Catch: Throwable -> 0x0044
            if (r0 == 0) goto L_0x0044
            android.telephony.TelephonyManager r0 = com.tendcloud.tenddata.C2819dt.f13814a     // Catch: Throwable -> 0x0044
            if (r0 != 0) goto L_0x0021
            init(r3)     // Catch: Throwable -> 0x0044
        L_0x0021:
            org.json.JSONArray r3 = com.tendcloud.tenddata.C2836ec.m15895C(r3)     // Catch: Throwable -> 0x0044
            if (r3 == 0) goto L_0x003a
            int r0 = r3.length()     // Catch: Throwable -> 0x0044
            r2 = 2
            if (r0 != r2) goto L_0x003a
            r0 = 1
            org.json.JSONObject r3 = r3.getJSONObject(r0)     // Catch: Exception -> 0x003a, Throwable -> 0x0044
            java.lang.String r0 = "imei"
            java.lang.String r3 = r3.getString(r0)     // Catch: Exception -> 0x003a, Throwable -> 0x0044
            goto L_0x003b
        L_0x003a:
            r3 = r1
        L_0x003b:
            if (r3 != 0) goto L_0x0043
            android.telephony.TelephonyManager r3 = com.tendcloud.tenddata.C2819dt.f13814a     // Catch: Throwable -> 0x0044
            java.lang.String r3 = r3.getDeviceId()     // Catch: Throwable -> 0x0044
        L_0x0043:
            return r3
        L_0x0044:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2819dt.m15958c(android.content.Context):java.lang.String");
    }

    /* renamed from: d */
    public static String m15957d(Context context) {
        try {
            if ((!C2855es.m15807a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && C2855es.m15792b(context, "android.permission.READ_PHONE_STATE")) {
                if (f13814a == null) {
                    init(context);
                }
                return f13814a.getSimSerialNumber();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: e */
    public static String m15956e(Context context) {
        try {
            if ((!C2855es.m15807a(23) || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) && C2855es.m15792b(context, "android.permission.READ_PHONE_STATE")) {
                if (f13814a == null) {
                    init(context);
                }
                return f13814a.getSubscriberId();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: f */
    public static String m15955f(Context context) {
        Throwable th;
        ArrayList<NetworkInterface> list;
        WifiInfo connectionInfo;
        String trim;
        String str = null;
        try {
            if (C2855es.m15807a(23)) {
                try {
                    list = Collections.list(NetworkInterface.getNetworkInterfaces());
                } catch (Throwable unused) {
                }
                if (list != null && list.size() > 0) {
                    for (NetworkInterface networkInterface : list) {
                        if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            if (hardwareAddress == null) {
                                return "";
                            }
                            StringBuilder sb = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                            }
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            str = sb.toString().toUpperCase().trim();
                        }
                    }
                    return !C2855es.m15791b(str) ? str : "02:00:00:00:00:00";
                }
                return "02:00:00:00:00:00";
            }
            if (C2855es.m15792b(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager.isWifiEnabled() && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    String macAddress = connectionInfo.getMacAddress();
                    if (macAddress == null) {
                        return macAddress;
                    }
                    try {
                        trim = macAddress.toUpperCase().trim();
                        if (!f13817d.equals(trim)) {
                            if (f13818e.matcher(trim).matches()) {
                                return trim;
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        str = trim;
                        C2933hb.postSDKError(th);
                        return str;
                    }
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: g */
    public static final String m15954g(Context context) {
        try {
            if (!f13823j) {
                C2852ep.f13904a.execute(new RunnableC2820du(context));
            }
            return f13822i;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: h */
    public static final String m15953h(Context context) {
        try {
            if (C2855es.m15807a(9) && C2855es.m15793b(26)) {
                return Build.SERIAL;
            }
            if (!C2855es.m15807a(26) || !C2855es.m15792b(context, "android.permission.READ_PHONE_STATE")) {
                return null;
            }
            return Build.getSerial();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: i */
    public static final String m15952i(Context context) {
        try {
            String f = m15955f(context);
            if (!TextUtils.isEmpty(f)) {
                f = String.valueOf(Long.parseLong(f.replaceAll(":", ""), 16));
            }
            String b = m15960b(context);
            String c = m15958c(context);
            String e = m15956e(context);
            String d = m15957d(context);
            String a = m15969a(context);
            String g = m15954g(context);
            String h = m15953h(context);
            return "2|" + f + "|" + b + "|" + c + "|" + e + "|" + d + "|" + a + "|" + g + "|" + h;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: l */
    private static String m15949l(Context context) {
        String str;
        String str2;
        String j = m15951j(context);
        String a = m15970a();
        boolean b = m15961b();
        String a2 = m15966a(context, b);
        String[] strArr = {j, a, a2};
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = null;
                break;
            }
            str = strArr[i];
            if (!C2855es.m15791b(str) && f13820g.matcher(str).matches()) {
                break;
            }
            i++;
        }
        if (C2855es.m15791b(str) && !C2855es.m15791b(j) && Math.random() < 0.99d) {
            int length2 = strArr.length;
            for (int i2 = 0; i2 < length2; i2++) {
                str2 = strArr[i2];
                if (!C2855es.m15791b(str2) && f13819f.matcher(str2).matches()) {
                    break;
                }
            }
        }
        str2 = str;
        if (C2855es.m15791b(str2)) {
            str2 = m15948m(context);
        }
        if (!str2.equals(j)) {
            m15959b(context, str2);
        }
        if (!str2.equals(a2)) {
            m15967a(context, str2, b);
        }
        if (!str2.equals(a)) {
            m15968a(context, str2);
        }
        return str2;
    }

    /* renamed from: a */
    static String m15966a(Context context, boolean z) {
        String str;
        if (C2855es.m15807a(23) && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return null;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return "";
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (z) {
            str = f13821h;
        } else {
            str = f13821h + m15950k(context);
        }
        String a = m15965a(new File(externalStorageDirectory, str));
        if (!C2855es.m15791b(a)) {
            return a;
        }
        return m15965a(new File(Environment.getExternalStorageDirectory(), ".tid" + m15950k(context)));
    }

    /* renamed from: a */
    static String m15970a() {
        File[] listFiles;
        try {
            File[] listFiles2 = new File("/").listFiles();
            if (!(listFiles2 == null || listFiles2.length == 0)) {
                String str = null;
                for (File file : listFiles2) {
                    try {
                        if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                            if (file.canWrite()) {
                                str = m15965a(new File(file, f13821h));
                                if (!C2855es.m15791b(str)) {
                                    return str;
                                }
                            }
                            if (file.listFiles() != null) {
                                String str2 = str;
                                for (File file2 : file.listFiles()) {
                                    try {
                                        if (file2.isDirectory()) {
                                            str2 = m15965a(new File(file2, f13821h));
                                            if (!C2855es.m15791b(str2)) {
                                                return str2;
                                            }
                                        }
                                    } catch (Throwable unused) {
                                        return str2;
                                    }
                                }
                                str = str2;
                            } else {
                                continue;
                            }
                        }
                    } catch (Throwable unused2) {
                        return str;
                    }
                }
                return str;
            }
            return null;
        } catch (Throwable unused3) {
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:12:0x0024
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static java.lang.String m15965a(java.io.File r5) {
        /*
            r0 = 0
            boolean r1 = r5.exists()     // Catch: Throwable -> 0x0037
            if (r1 == 0) goto L_0x0029
            boolean r1 = r5.canRead()     // Catch: Throwable -> 0x0037
            if (r1 == 0) goto L_0x0029
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Throwable -> 0x0027
            r1.<init>(r5)     // Catch: Throwable -> 0x0027
            r5 = 128(0x80, float:1.794E-43)
            byte[] r5 = new byte[r5]     // Catch: Throwable -> 0x002a
            int r2 = r1.read(r5)     // Catch: Throwable -> 0x002a
            java.lang.String r3 = new java.lang.String     // Catch: Throwable -> 0x002a
            r4 = 0
            r3.<init>(r5, r4, r2)     // Catch: Throwable -> 0x002a
            r1.close()     // Catch: Throwable -> 0x0023
        L_0x0023:
            return r3
        L_0x0024:
            r5 = move-exception
            r0 = r1
            goto L_0x0031
        L_0x0027:
            r1 = r0
            goto L_0x002a
        L_0x0029:
            r1 = r0
        L_0x002a:
            if (r1 == 0) goto L_0x0037
            r1.close()     // Catch: Throwable -> 0x0037
            goto L_0x0037
        L_0x0030:
            r5 = move-exception
        L_0x0031:
            if (r0 == 0) goto L_0x0036
            r0.close()     // Catch: Throwable -> 0x0036
        L_0x0036:
            throw r5
        L_0x0037:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2819dt.m15965a(java.io.File):java.lang.String");
    }

    /* renamed from: j */
    static String m15951j(Context context) {
        try {
            String b = C2843eh.m15840b(context, "tdid", f13816c, (String) null);
            return C2855es.m15791b(b) ? PreferenceManager.getDefaultSharedPreferences(context).getString(f13816c, null) : b;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static void m15967a(Context context, String str, boolean z) {
        String str2;
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (z) {
                str2 = f13821h;
            } else {
                str2 = f13821h + m15950k(context);
            }
            m15964a(new File(externalStorageDirectory, str2), str);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static void m15968a(Context context, String str) {
        File[] listFiles;
        try {
            File[] listFiles2 = new File("/").listFiles();
            if (!(listFiles2 == null || listFiles2.length == 0)) {
                for (File file : listFiles2) {
                    if (file.isDirectory() && !"/sdcard".equals(file.getAbsolutePath())) {
                        if (file.canWrite()) {
                            if (!new File(file, f13821h + m15950k(context)).exists()) {
                                m15964a(new File(file, f13821h), str);
                            }
                        }
                        if (file.listFiles() != null) {
                            for (File file2 : file.listFiles()) {
                                if (file2.isDirectory() && file2.canWrite()) {
                                    if (!new File(file2, f13821h + m15950k(context)).exists()) {
                                        m15964a(new File(file2, f13821h), str);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:11:0x0062
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static void m15964a(java.io.File r7, java.lang.String r8) {
        /*
            r0 = 0
            boolean r1 = r7.canWrite()     // Catch: Throwable -> 0x0074
            if (r1 == 0) goto L_0x0066
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: Throwable -> 0x0074
            r1.<init>(r7)     // Catch: Throwable -> 0x0074
            byte[] r8 = r8.getBytes()     // Catch: Throwable -> 0x0064
            r1.write(r8)     // Catch: Throwable -> 0x0064
            r8 = 9
            boolean r8 = com.tendcloud.tenddata.C2855es.m15807a(r8)     // Catch: Throwable -> 0x0064
            if (r8 == 0) goto L_0x0044
            java.lang.Class r8 = r7.getClass()     // Catch: Throwable -> 0x0064
            java.lang.String r0 = "setReadable"
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: Throwable -> 0x0064
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch: Throwable -> 0x0064
            r5 = 0
            r3[r5] = r4     // Catch: Throwable -> 0x0064
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch: Throwable -> 0x0064
            r6 = 1
            r3[r6] = r4     // Catch: Throwable -> 0x0064
            java.lang.reflect.Method r8 = r8.getMethod(r0, r3)     // Catch: Throwable -> 0x0064
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: Throwable -> 0x0064
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r6)     // Catch: Throwable -> 0x0064
            r0[r5] = r2     // Catch: Throwable -> 0x0064
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r5)     // Catch: Throwable -> 0x0064
            r0[r6] = r2     // Catch: Throwable -> 0x0064
            r8.invoke(r7, r0)     // Catch: Throwable -> 0x0064
            goto L_0x0060
        L_0x0044:
            java.lang.Runtime r8 = java.lang.Runtime.getRuntime()     // Catch: Throwable -> 0x0064
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0064
            r0.<init>()     // Catch: Throwable -> 0x0064
            java.lang.String r2 = "chmod 444 "
            r0.append(r2)     // Catch: Throwable -> 0x0064
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: Throwable -> 0x0064
            r0.append(r7)     // Catch: Throwable -> 0x0064
            java.lang.String r7 = r0.toString()     // Catch: Throwable -> 0x0064
            r8.exec(r7)     // Catch: Throwable -> 0x0064
        L_0x0060:
            r0 = r1
            goto L_0x0066
        L_0x0062:
            r7 = move-exception
            goto L_0x006e
        L_0x0064:
            goto L_0x0075
        L_0x0066:
            if (r0 == 0) goto L_0x007a
            r0.close()     // Catch: Throwable -> 0x007a
            goto L_0x007a
        L_0x006c:
            r7 = move-exception
            r1 = r0
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch: Throwable -> 0x0073
        L_0x0073:
            throw r7
        L_0x0074:
            r1 = r0
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r1.close()     // Catch: Throwable -> 0x007a
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2819dt.m15964a(java.io.File, java.lang.String):void");
    }

    /* renamed from: b */
    private static void m15959b(Context context, String str) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("tdid", 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(f13816c, str);
                edit.apply();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: m */
    private static String m15948m(Context context) {
        String n = m15947n(context);
        return DdyConstants.APP_INSTALL_ERROR + C2855es.m15786c(n);
    }

    /* renamed from: n */
    private static String m15947n(Context context) {
        try {
            return m15958c(context) + '-' + m15955f(context) + '-' + m15960b(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: b */
    static boolean m15961b() {
        boolean z;
        try {
            z = C2855es.m15807a(9) ? Environment.isExternalStorageRemovable() : true;
        } catch (Throwable unused) {
            z = true;
        }
        return !z;
    }

    /* renamed from: k */
    static String m15950k(Context context) {
        if (f13824k == null) {
            try {
                Sensor[] sensorArr = new Sensor[64];
                for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
                    if (sensor.getType() < sensorArr.length && sensor.getType() >= 0) {
                        sensorArr[sensor.getType()] = sensor;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < sensorArr.length; i++) {
                    if (sensorArr[i] != null) {
                        stringBuffer.append(i);
                        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                        stringBuffer.append(sensorArr[i].getVendor());
                        stringBuffer.append('-');
                        stringBuffer.append(sensorArr[i].getName());
                        stringBuffer.append('-');
                        stringBuffer.append(sensorArr[i].getVersion());
                        stringBuffer.append('\n');
                    }
                }
                f13824k = String.valueOf(stringBuffer.toString().hashCode());
            } catch (Throwable unused) {
            }
        }
        return f13824k;
    }
}
