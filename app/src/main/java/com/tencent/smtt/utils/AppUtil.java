package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* renamed from: com.tencent.smtt.utils.b */
/* loaded from: classes2.dex */
public class AppUtil {

    /* renamed from: a */
    public static String f13276a = "";

    /* renamed from: b */
    public static String f13277b = "";

    /* renamed from: c */
    public static String f13278c = "";

    /* renamed from: d */
    public static String f13279d = "";

    /* renamed from: e */
    public static String f13280e = "";

    /* renamed from: a */
    private static String m16506a(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public static String m16511a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static int m16501b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: a */
    public static String m16509a(Context context, String str) {
        String str2;
        try {
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str)))));
            } catch (Exception unused) {
                return str2;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m16500c(Context context) {
        if (!TextUtils.isEmpty(f13276a)) {
            return f13276a;
        }
        try {
            return ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static String m16499d(Context context) {
        if (!TextUtils.isEmpty(f13277b)) {
            return f13277b;
        }
        try {
            return ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:16:0x004a
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public static java.lang.String m16512a() {
        /*
            java.lang.String r0 = com.tencent.smtt.utils.AppUtil.f13278c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = com.tencent.smtt.utils.AppUtil.f13278c
            return r0
        L_0x000b:
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: Throwable -> 0x0059
            java.lang.String r2 = "getprop ro.product.cpu.abi"
            java.lang.Process r1 = r1.exec(r2)     // Catch: Throwable -> 0x0059
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0059
            java.io.InputStream r1 = r1.getInputStream()     // Catch: Throwable -> 0x0059
            r2.<init>(r1)     // Catch: Throwable -> 0x0059
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: Throwable -> 0x004f
            r1.<init>(r2)     // Catch: Throwable -> 0x004f
            java.lang.String r0 = r1.readLine()     // Catch: Throwable -> 0x0048
            java.lang.String r3 = "x86"
            boolean r0 = r0.contains(r3)     // Catch: Throwable -> 0x0048
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = "i686"
            java.lang.String r0 = m16506a(r0)     // Catch: Throwable -> 0x0048
            goto L_0x0041
        L_0x0037:
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch: Throwable -> 0x0048
            java.lang.String r0 = m16506a(r0)     // Catch: Throwable -> 0x0048
        L_0x0041:
            r1.close()     // Catch: IOException -> 0x0044
        L_0x0044:
            r2.close()     // Catch: IOException -> 0x0075
            goto L_0x0075
        L_0x0048:
            r0 = move-exception
            goto L_0x005d
        L_0x004a:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0077
        L_0x004f:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x005d
        L_0x0054:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L_0x0077
        L_0x0059:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x005d:
            java.lang.String r3 = "os.arch"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch: all -> 0x0076
            java.lang.String r3 = m16506a(r3)     // Catch: all -> 0x0076
            r0.printStackTrace()     // Catch: all -> 0x0076
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch: IOException -> 0x006f
        L_0x006f:
            if (r2 == 0) goto L_0x0074
            r2.close()     // Catch: IOException -> 0x0074
        L_0x0074:
            r0 = r3
        L_0x0075:
            return r0
        L_0x0076:
            r0 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch: IOException -> 0x007c
        L_0x007c:
            if (r2 == 0) goto L_0x0081
            r2.close()     // Catch: IOException -> 0x0081
        L_0x0081:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.AppUtil.m16512a():java.lang.String");
    }

    /* renamed from: e */
    public static String m16498e(Context context) {
        if (TextUtils.isEmpty(f13279d)) {
            if (Build.VERSION.SDK_INT < 23) {
                try {
                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                    WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
                    f13279d = connectionInfo == null ? "" : connectionInfo.getMacAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                f13279d = m16502b();
            }
        }
        return f13279d;
    }

    /* renamed from: b */
    public static String m16502b() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
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
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception unused) {
            return "02:00:00:00:00:00";
        }
    }

    /* renamed from: f */
    public static String m16497f(Context context) {
        if (!TextUtils.isEmpty(f13280e)) {
            return f13280e;
        }
        try {
            f13280e = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f13280e;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0064 A[Catch: Throwable -> 0x0079, TryCatch #7 {Throwable -> 0x0079, blocks: (B:30:0x0054, B:32:0x0064, B:34:0x0071), top: B:56:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m16508a(android.content.Context r3, boolean r4, java.io.File r5) {
        /*
            if (r5 == 0) goto L_0x00dc
            boolean r0 = r5.exists()
            if (r0 != 0) goto L_0x000a
            goto L_0x00dc
        L_0x000a:
            if (r4 == 0) goto L_0x0054
            r4 = 0
            r0 = 2
            byte[] r0 = new byte[r0]     // Catch: all -> 0x0038, Exception -> 0x003b
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: all -> 0x0038, Exception -> 0x003b
            java.lang.String r2 = "r"
            r1.<init>(r5, r2)     // Catch: all -> 0x0038, Exception -> 0x003b
            r1.read(r0)     // Catch: Exception -> 0x0036, all -> 0x004a
            java.lang.String r4 = new java.lang.String     // Catch: Exception -> 0x0036, all -> 0x004a
            r4.<init>(r0)     // Catch: Exception -> 0x0036, all -> 0x004a
            java.lang.String r0 = "PK"
            boolean r4 = r4.equalsIgnoreCase(r0)     // Catch: Exception -> 0x0036, all -> 0x004a
            if (r4 != 0) goto L_0x0032
            java.lang.String r3 = ""
            r1.close()     // Catch: IOException -> 0x002d
            goto L_0x0031
        L_0x002d:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0031:
            return r3
        L_0x0032:
            r1.close()     // Catch: IOException -> 0x0045
            goto L_0x0054
        L_0x0036:
            r4 = move-exception
            goto L_0x003e
        L_0x0038:
            r3 = move-exception
            r1 = r4
            goto L_0x004b
        L_0x003b:
            r0 = move-exception
            r1 = r4
            r4 = r0
        L_0x003e:
            r4.printStackTrace()     // Catch: all -> 0x004a
            r1.close()     // Catch: IOException -> 0x0045
            goto L_0x0054
        L_0x0045:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0054
        L_0x004a:
            r3 = move-exception
        L_0x004b:
            r1.close()     // Catch: IOException -> 0x004f
            goto L_0x0053
        L_0x004f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0053:
            throw r3
        L_0x0054:
            android.content.Context r4 = r3.getApplicationContext()     // Catch: Throwable -> 0x0079
            java.lang.String r4 = r4.getPackageName()     // Catch: Throwable -> 0x0079
            java.lang.String r0 = "com.jd.jrapp"
            boolean r4 = r4.contains(r0)     // Catch: Throwable -> 0x0079
            if (r4 == 0) goto L_0x0080
            java.lang.String r4 = "AppUtil"
            java.lang.String r0 = "[AppUtil.getSignatureFromApk]  #1"
            com.tencent.smtt.utils.TbsLog.m16531i(r4, r0)     // Catch: Throwable -> 0x0079
            java.lang.String r4 = m16507a(r5)     // Catch: Throwable -> 0x0079
            if (r4 == 0) goto L_0x0080
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #2"
            com.tencent.smtt.utils.TbsLog.m16531i(r0, r1)     // Catch: Throwable -> 0x0079
            return r4
        L_0x0079:
            java.lang.String r4 = "AppUtil"
            java.lang.String r0 = "[AppUtil.getSignatureFromApk]  #3"
            com.tencent.smtt.utils.TbsLog.m16531i(r4, r0)
        L_0x0080:
            java.lang.String r4 = "AppUtil"
            java.lang.String r0 = "[AppUtil.getSignatureFromApk]  #4"
            com.tencent.smtt.utils.TbsLog.m16531i(r4, r0)
            r4 = 0
            java.lang.String r4 = m16510a(r3, r5, r4)
            java.lang.String r0 = "AppUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[AppUtil.getSignatureFromApk]  android api signature="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.m16531i(r0, r1)
            if (r4 != 0) goto L_0x00be
            java.lang.String r4 = m16507a(r5)
            java.lang.String r0 = "AppUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[AppUtil.getSignatureFromApk]  java get signature="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.m16531i(r0, r1)
        L_0x00be:
            if (r4 != 0) goto L_0x00db
            r4 = 1
            java.lang.String r4 = m16510a(r3, r5, r4)
            java.lang.String r3 = "AppUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "[AppUtil.getSignatureFromApk]  android reflection signature="
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.m16531i(r3, r5)
        L_0x00db:
            return r4
        L_0x00dc:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.AppUtil.m16508a(android.content.Context, boolean, java.io.File):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035 A[Catch: Exception -> 0x003a, TRY_LEAVE, TryCatch #0 {Exception -> 0x003a, blocks: (B:4:0x0005, B:5:0x000e, B:7:0x001c, B:9:0x0020, B:11:0x0025, B:12:0x002b, B:15:0x0035), top: B:18:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m16510a(android.content.Context r2, java.io.File r3, boolean r4) {
        /*
            r0 = 65
            r1 = 0
            if (r4 == 0) goto L_0x000e
            java.lang.String r2 = r3.getAbsolutePath()     // Catch: Exception -> 0x003a
            android.content.pm.PackageInfo r2 = m16505a(r2, r0)     // Catch: Exception -> 0x003a
            goto L_0x001a
        L_0x000e:
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch: Exception -> 0x003a
            java.lang.String r4 = r3.getAbsolutePath()     // Catch: Exception -> 0x003a
            android.content.pm.PackageInfo r2 = r2.getPackageArchiveInfo(r4, r0)     // Catch: Exception -> 0x003a
        L_0x001a:
            if (r2 == 0) goto L_0x0032
            android.content.pm.Signature[] r4 = r2.signatures     // Catch: Exception -> 0x003a
            if (r4 == 0) goto L_0x002b
            android.content.pm.Signature[] r4 = r2.signatures     // Catch: Exception -> 0x003a
            int r4 = r4.length     // Catch: Exception -> 0x003a
            if (r4 <= 0) goto L_0x002b
            android.content.pm.Signature[] r2 = r2.signatures     // Catch: Exception -> 0x003a
            r4 = 0
            r2 = r2[r4]     // Catch: Exception -> 0x003a
            goto L_0x0033
        L_0x002b:
            java.lang.String r2 = "AppUtil"
            java.lang.String r4 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.m16527w(r2, r4)     // Catch: Exception -> 0x003a
        L_0x0032:
            r2 = r1
        L_0x0033:
            if (r2 == 0) goto L_0x0055
            java.lang.String r1 = r2.toCharsString()     // Catch: Exception -> 0x003a
            goto L_0x0055
        L_0x003a:
            java.lang.String r2 = "AppUtil"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "getSign "
            r4.append(r0)
            r4.append(r3)
            java.lang.String r3 = "failed"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.tencent.smtt.utils.TbsLog.m16531i(r2, r3)
        L_0x0055:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.AppUtil.m16510a(android.content.Context, java.io.File, boolean):java.lang.String");
    }

    /* renamed from: a */
    private static String m16507a(File file) {
        String str;
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a = m16503a(m16504a(jarFile, jarFile.getJarEntry("AndroidManifest.xml"), bArr)[0].getEncoded());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                if (name != null) {
                    Certificate[] a2 = m16504a(jarFile, nextElement, bArr);
                    if (a2 != null) {
                        str = m16503a(a2[0].getEncoded());
                    } else {
                        str = null;
                    }
                    if (str == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!str.equals(a)) {
                        return null;
                    }
                }
            }
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static PackageInfo m16505a(String str, int i) {
        Class<?> cls;
        try {
            Class<?> cls2 = Class.forName("android.content.pm.PackageParser");
            Class<?>[] declaredClasses = cls2.getDeclaredClasses();
            int length = declaredClasses.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    cls = null;
                    break;
                }
                cls = declaredClasses[i2];
                if (cls.getName().compareTo("android.content.pm.PackageParser$Package") == 0) {
                    break;
                }
                i2++;
            }
            Constructor<?> constructor = cls2.getConstructor(String.class);
            Method declaredMethod = cls2.getDeclaredMethod("parsePackage", File.class, String.class, DisplayMetrics.class, Integer.TYPE);
            Method declaredMethod2 = cls2.getDeclaredMethod("collectCertificates", cls, Integer.TYPE);
            Method declaredMethod3 = cls2.getDeclaredMethod("generatePackageInfo", cls, int[].class, Integer.TYPE, Long.TYPE, Long.TYPE);
            constructor.setAccessible(true);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            declaredMethod3.setAccessible(true);
            Object newInstance = constructor.newInstance(str);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Object invoke = declaredMethod.invoke(newInstance, new File(str), str, displayMetrics, 0);
            if (invoke == null) {
                return null;
            }
            if ((i & 64) != 0) {
                declaredMethod2.invoke(newInstance, invoke, 0);
            }
            return (PackageInfo) declaredMethod3.invoke(null, invoke, null, Integer.valueOf(i), 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static Certificate[] m16504a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) throws Exception {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    /* renamed from: a */
    private static String m16503a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = (b >> 4) & 15;
            int i3 = i * 2;
            cArr[i3] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            int i4 = b & 15;
            cArr[i3 + 1] = (char) (i4 >= 10 ? (i4 + 97) - 10 : i4 + 48);
        }
        return new String(cArr);
    }
}
