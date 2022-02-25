package com.tencent.smtt.sdk;

/* renamed from: com.tencent.smtt.sdk.a */
/* loaded from: classes2.dex */
public class DeviceInfo {

    /* renamed from: a */
    public static int f13068a = 600;

    /* renamed from: b */
    private static int f13069b;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        r1 = r1.substring(r3 + 9).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r1 == null) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        if (r1.length() == 0) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        if (r1.contains("k") == false) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
        r2 = java.lang.Integer.parseInt(r1.substring(0, r1.indexOf("k")).trim()) / 1024;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0058 -> B:46:0x007e). Please submit an issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m16904a() {
        /*
            int r0 = com.tencent.smtt.sdk.DeviceInfo.f13069b
            if (r0 <= 0) goto L_0x0005
            return r0
        L_0x0005:
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: all -> 0x0060, Throwable -> 0x0065, IOException -> 0x0072
            r3.<init>(r0)     // Catch: all -> 0x0060, Throwable -> 0x0065, IOException -> 0x0072
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: all -> 0x0060, Throwable -> 0x0065, IOException -> 0x0072
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch: all -> 0x0060, Throwable -> 0x0065, IOException -> 0x0072
        L_0x0015:
            java.lang.String r1 = r0.readLine()     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            if (r1 == 0) goto L_0x0053
            java.lang.String r3 = "MemTotal:"
            int r3 = r1.indexOf(r3)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            r4 = -1
            if (r4 == r3) goto L_0x0015
            int r3 = r3 + 9
            java.lang.String r1 = r1.substring(r3)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            java.lang.String r1 = r1.trim()     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            if (r1 == 0) goto L_0x0053
            int r3 = r1.length()     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            if (r3 == 0) goto L_0x0053
            java.lang.String r3 = "k"
            boolean r3 = r1.contains(r3)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            if (r3 == 0) goto L_0x0053
            java.lang.String r3 = "k"
            int r3 = r1.indexOf(r3)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            java.lang.String r1 = r1.substring(r2, r3)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            java.lang.String r1 = r1.trim()     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: Throwable -> 0x005c, IOException -> 0x005e, all -> 0x0083
            int r1 = r1 / 1024
            r2 = r1
        L_0x0053:
            r0.close()     // Catch: IOException -> 0x0057
            goto L_0x007e
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x007e
        L_0x005c:
            r1 = move-exception
            goto L_0x0069
        L_0x005e:
            r1 = move-exception
            goto L_0x0076
        L_0x0060:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0084
        L_0x0065:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0069:
            r1.printStackTrace()     // Catch: all -> 0x0083
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch: IOException -> 0x0057
            goto L_0x007e
        L_0x0072:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0076:
            r1.printStackTrace()     // Catch: all -> 0x0083
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch: IOException -> 0x0057
        L_0x007e:
            com.tencent.smtt.sdk.DeviceInfo.f13069b = r2
            int r0 = com.tencent.smtt.sdk.DeviceInfo.f13069b
            return r0
        L_0x0083:
            r1 = move-exception
        L_0x0084:
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch: IOException -> 0x008a
            goto L_0x008e
        L_0x008a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.DeviceInfo.m16904a():int");
    }
}
