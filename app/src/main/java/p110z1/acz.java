package p110z1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.common.utils.log.LogUtils;

/* compiled from: Utils.java */
/* renamed from: z1.acz */
/* loaded from: classes3.dex */
public class acz {

    /* renamed from: a */
    private static final String f15280a = "Utils";

    /* renamed from: a */
    public static double m14333a() {
        try {
            String a = m14331a("ro.build.version.emui");
            return Double.parseDouble(a.substring(a.indexOf("_") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return 4.0d;
        }
    }

    /* renamed from: b */
    public static int m14330b() {
        String a = m14331a("ro.miui.ui.version.name");
        if (a == null) {
            return -1;
        }
        try {
            return Integer.parseInt(a.substring(1));
        } catch (Exception unused) {
            LogUtils.m22036e(f15280a, "get miui version code error, version : " + a);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m14331a(java.lang.String r10) {
        /*
            r0 = 0
            r1 = 2
            r2 = 1
            r3 = 0
            r4 = 3
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: all -> 0x004c, IOException -> 0x004e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x004c, IOException -> 0x004e
            r6.<init>()     // Catch: all -> 0x004c, IOException -> 0x004e
            java.lang.String r7 = "getprop "
            r6.append(r7)     // Catch: all -> 0x004c, IOException -> 0x004e
            r6.append(r10)     // Catch: all -> 0x004c, IOException -> 0x004e
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x004c, IOException -> 0x004e
            java.lang.Process r5 = r5.exec(r6)     // Catch: all -> 0x004c, IOException -> 0x004e
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: all -> 0x004c, IOException -> 0x004e
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: all -> 0x004c, IOException -> 0x004e
            java.io.InputStream r5 = r5.getInputStream()     // Catch: all -> 0x004c, IOException -> 0x004e
            r7.<init>(r5)     // Catch: all -> 0x004c, IOException -> 0x004e
            r5 = 1024(0x400, float:1.435E-42)
            r6.<init>(r7, r5)     // Catch: all -> 0x004c, IOException -> 0x004e
            java.lang.String r5 = r6.readLine()     // Catch: IOException -> 0x004a, all -> 0x0085
            r6.close()     // Catch: IOException -> 0x004a, all -> 0x0085
            r6.close()     // Catch: IOException -> 0x0039
            goto L_0x0049
        L_0x0039:
            r10 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r4 = "Utils"
            r0[r3] = r4
            java.lang.String r3 = "Exception while closing InputStream"
            r0[r2] = r3
            r0[r1] = r10
            com.blankj.utilcode.util.LogUtils.m23720e(r0)
        L_0x0049:
            return r5
        L_0x004a:
            r5 = move-exception
            goto L_0x0050
        L_0x004c:
            r10 = move-exception
            goto L_0x0087
        L_0x004e:
            r5 = move-exception
            r6 = r0
        L_0x0050:
            java.lang.String r7 = "Utils"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: all -> 0x0085
            r8.<init>()     // Catch: all -> 0x0085
            java.lang.String r9 = "Unable to read sysprop "
            r8.append(r9)     // Catch: all -> 0x0085
            r8.append(r10)     // Catch: all -> 0x0085
            java.lang.String r10 = "  "
            r8.append(r10)     // Catch: all -> 0x0085
            r8.append(r5)     // Catch: all -> 0x0085
            java.lang.String r10 = r8.toString()     // Catch: all -> 0x0085
            com.common.utils.log.LogUtils.m22036e(r7, r10)     // Catch: all -> 0x0085
            if (r6 == 0) goto L_0x0084
            r6.close()     // Catch: IOException -> 0x0074
            goto L_0x0084
        L_0x0074:
            r10 = move-exception
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "Utils"
            r4[r3] = r5
            java.lang.String r3 = "Exception while closing InputStream"
            r4[r2] = r3
            r4[r1] = r10
            com.blankj.utilcode.util.LogUtils.m23720e(r4)
        L_0x0084:
            return r0
        L_0x0085:
            r10 = move-exception
            r0 = r6
        L_0x0087:
            if (r0 == 0) goto L_0x009d
            r0.close()     // Catch: IOException -> 0x008d
            goto L_0x009d
        L_0x008d:
            r0 = move-exception
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "Utils"
            r4[r3] = r5
            java.lang.String r3 = "Exception while closing InputStream"
            r4[r2] = r3
            r4[r1] = r0
            com.blankj.utilcode.util.LogUtils.m23720e(r4)
        L_0x009d:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.acz.m14331a(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static boolean m14329c() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    /* renamed from: d */
    public static boolean m14328d() {
        return !TextUtils.isEmpty(m14331a("ro.miui.ui.version.name"));
    }

    /* renamed from: e */
    public static boolean m14327e() {
        String a = m14331a("ro.build.display.id");
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        return a.contains("flyme") || a.toLowerCase().contains("flyme");
    }

    /* renamed from: f */
    public static boolean m14326f() {
        return Build.MANUFACTURER.contains("QiKU");
    }

    /* renamed from: a */
    public static boolean m14332a(Context context, Intent intent) {
        return intent != null && context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
