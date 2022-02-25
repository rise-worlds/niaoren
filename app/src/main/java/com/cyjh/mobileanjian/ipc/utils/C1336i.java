package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.FileReader;
import java.io.Reader;
import java.net.NetworkInterface;
import java.util.Collections;

/* compiled from: PhoneUtils.java */
/* renamed from: com.cyjh.mobileanjian.ipc.utils.i */
/* loaded from: classes.dex */
public final class C1336i {
    private C1336i() {
        throw new RuntimeException();
    }

    /* renamed from: a */
    private static String m20645a(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
            return deviceId == null ? "" : deviceId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    private static String m20641b(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    private static String m20640c(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return m20646a();
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return macAddress == null ? "" : macAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
        r1 = r0.trim();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m20646a() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: Exception -> 0x002a
            java.lang.String r3 = "cat /sys/class/net/wlan0/address "
            java.lang.Process r2 = r2.exec(r3)     // Catch: Exception -> 0x002a
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: Exception -> 0x002a
            java.io.InputStream r2 = r2.getInputStream()     // Catch: Exception -> 0x002a
            r3.<init>(r2)     // Catch: Exception -> 0x002a
            java.io.LineNumberReader r2 = new java.io.LineNumberReader     // Catch: Exception -> 0x002a
            r2.<init>(r3)     // Catch: Exception -> 0x002a
        L_0x001c:
            if (r0 == 0) goto L_0x002e
            java.lang.String r0 = r2.readLine()     // Catch: Exception -> 0x002a
            if (r0 == 0) goto L_0x001c
            java.lang.String r0 = r0.trim()     // Catch: Exception -> 0x002a
            r1 = r0
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002e:
            if (r1 == 0) goto L_0x0038
            java.lang.String r0 = ""
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005a
        L_0x0038:
            java.lang.String r0 = "/sys/class/net/eth0/address"
            java.io.FileReader r1 = new java.io.FileReader     // Catch: Exception -> 0x0052
            r1.<init>(r0)     // Catch: Exception -> 0x0052
            java.lang.String r0 = m20644a(r1)     // Catch: Exception -> 0x0052
            r1.close()     // Catch: Exception -> 0x0052
            java.lang.String r0 = r0.toUpperCase()     // Catch: Exception -> 0x0052
            r1 = 0
            r2 = 17
            java.lang.String r0 = r0.substring(r1, r2)     // Catch: Exception -> 0x0052
            return r0
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r1 = m20642b()
        L_0x005a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.utils.C1336i.m20646a():java.lang.String");
    }

    /* renamed from: b */
    private static String m20642b() {
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
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static String m20643a(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String a = m20644a(fileReader);
        fileReader.close();
        return a;
    }

    /* renamed from: a */
    private static String m20644a(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int read = reader.read(cArr);
        while (read >= 0) {
            sb.append(cArr, 0, read);
            read = reader.read(cArr);
        }
        return sb.toString();
    }
}
