package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.r */
/* loaded from: classes.dex */
public final class UniqIdUtil {

    /* renamed from: a */
    private static String f8712a = "";

    /* renamed from: a */
    private static String m20614a() {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.startsWith("Serial")) {
                    str = readLine.substring(readLine.indexOf(":") + 1).trim();
                }
            }
            bufferedReader.close();
        } catch (IOException unused) {
        }
        if (str == null) {
            str = "";
        }
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        return i == str.length() ? "" : str;
    }

    /* renamed from: b */
    private static String m20611b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.serialno", "cyjhuser");
        } catch (ClassNotFoundException unused) {
            return "";
        } catch (IllegalAccessException | InvocationTargetException unused2) {
            return "";
        } catch (NoSuchMethodException unused3) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m20609c(android.content.Context r6) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.String r2 = "phone"
            java.lang.Object r2 = r6.getSystemService(r2)     // Catch: Exception -> 0x0014
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: Exception -> 0x0014
            int r3 = r2.getPhoneType()     // Catch: Exception -> 0x0014
            java.lang.String r2 = r2.getDeviceId()     // Catch: Exception -> 0x0015
            goto L_0x0017
        L_0x0014:
            r3 = 0
        L_0x0015:
            java.lang.String r2 = ""
        L_0x0017:
            java.lang.String r4 = "phone"
            java.lang.Object r6 = r6.getSystemService(r4)     // Catch: Exception -> 0x0028
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6     // Catch: Exception -> 0x0028
            int r1 = r6.getPhoneType()     // Catch: Exception -> 0x0028
            java.lang.String r6 = r6.getDeviceId()     // Catch: Exception -> 0x0028
            goto L_0x002a
        L_0x0028:
            java.lang.String r6 = ""
        L_0x002a:
            r4 = 1
            r5 = 2
            if (r3 != r4) goto L_0x0045
            if (r1 != r5) goto L_0x0062
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r1 = "|"
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            goto L_0x0063
        L_0x0045:
            if (r1 != r4) goto L_0x0060
            if (r3 != r5) goto L_0x005e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            java.lang.String r6 = "|"
            r0.append(r6)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            goto L_0x0063
        L_0x005e:
            r0 = r6
            goto L_0x0063
        L_0x0060:
            if (r3 != r5) goto L_0x0063
        L_0x0062:
            r0 = r2
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.utils.UniqIdUtil.m20609c(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    private static String m20612a(Context context, boolean z) {
        String str;
        String c = m20609c(context);
        String a = m20614a();
        String b = m20611b();
        if (!z || c == null || c == "") {
            if (!(c == null || c == "")) {
                a = c;
            }
            str = (a == null || a == "") ? b : a;
        } else if (b == null || b == "") {
            str = c;
        } else {
            str = c + "-" + b;
        }
        if (c == "") {
            return "imei:none-" + str;
        }
        return "imei:" + str;
    }

    /* renamed from: d */
    private static String m20608d(Context context) {
        String str;
        String a = m20612a(context, false);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                str = bufferedReader.readLine();
                if (str != null) {
                    if (str.startsWith("Hardware")) {
                        str = str.split(":")[1].trim().replaceAll(ExpandableTextView.f6958c, "_");
                        break;
                    }
                } else {
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException unused) {
            str = "unknown";
        } catch (IOException unused2) {
            str = "unknown";
        } catch (Exception unused3) {
            str = "unknown";
        }
        return a + "[arm]@_@" + str;
    }

    /* renamed from: e */
    private static String m20607e(Context context) {
        String a = m20612a(context, true);
        return a + "[x86]@_@" + FileDownloadBroadcastHandler.f10432b;
    }

    /* renamed from: f */
    private static String m20606f(Context context) {
        String str;
        String a = m20612a(context, true);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                str = bufferedReader.readLine();
                if (str != null) {
                    if (str.startsWith("model name")) {
                        str = str.split(":")[1].trim().replaceAll(ExpandableTextView.f6958c, "_");
                        break;
                    }
                } else {
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException unused) {
            str = "unknown";
        } catch (IOException unused2) {
            str = "unknown";
        } catch (Exception unused3) {
            str = "unknown";
        }
        return a + "[x86]@_@" + str;
    }

    /* renamed from: g */
    private static String m20605g(Context context) {
        String str = f8712a;
        if (str != "") {
            return str;
        }
        String d = m20608d(context);
        if (d.endsWith("null") || d.endsWith("unknown")) {
            d = m20612a(context, true) + "[x86]@_@" + FileDownloadBroadcastHandler.f10432b;
        }
        f8712a = d;
        return d;
    }

    /* renamed from: a */
    public static String m20613a(Context context) {
        String d = m20608d(context);
        return (d.endsWith("null") || d.endsWith("unknown")) ? m20606f(context) : d;
    }

    /* renamed from: b */
    public static boolean m20610b(Context context) {
        String d = m20608d(context);
        return d.endsWith("null") || d.endsWith("unknown");
    }
}
