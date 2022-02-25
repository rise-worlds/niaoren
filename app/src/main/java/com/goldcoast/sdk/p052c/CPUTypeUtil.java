package com.goldcoast.sdk.p052c;

import java.io.File;
import p110z1.acf;

/* renamed from: com.goldcoast.sdk.c.b */
/* loaded from: classes.dex */
public final class CPUTypeUtil {

    /* renamed from: a */
    private static boolean f9004a = false;

    /* renamed from: a */
    public static String m20343a() {
        byte[] a;
        byte[] a2;
        if (m20341a(acf.f15190o, "").length() > 0 || m20340b()) {
            return acf.f15192q;
        }
        File file = new File("/system/lib/libc.so");
        boolean z = true;
        if (!file.exists() || (a2 = m20342a(file)) == null || a2[4] != 2) {
            File file2 = new File("/system/lib64/libc.so");
            if (!file2.exists() || (a = m20342a(file2)) == null || a[4] != 2) {
                z = false;
            }
        }
        return z ? acf.f15192q : acf.f15191p;
    }

    /* renamed from: a */
    private static String m20341a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return str2;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:25:0x0057
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: b */
    private static boolean m20340b() {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/proc/cpuinfo"
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x008c
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x0077
            r2.<init>(r0)     // Catch: Throwable -> 0x0077
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: Throwable -> 0x0078
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0078
            r3.<init>(r2)     // Catch: Throwable -> 0x0078
            r4 = 512(0x200, float:7.175E-43)
            r0.<init>(r3, r4)     // Catch: Throwable -> 0x0078
            java.lang.String r1 = r0.readLine()     // Catch: Throwable -> 0x005c
            if (r1 == 0) goto L_0x004b
            int r3 = r1.length()     // Catch: Throwable -> 0x005c
            if (r3 <= 0) goto L_0x004b
            java.util.Locale r3 = java.util.Locale.US     // Catch: Throwable -> 0x005c
            java.lang.String r1 = r1.toLowerCase(r3)     // Catch: Throwable -> 0x005c
            java.lang.String r3 = "arch64"
            boolean r1 = r1.contains(r3)     // Catch: Throwable -> 0x005c
            if (r1 == 0) goto L_0x004b
            r0.close()     // Catch: Exception -> 0x003d
            goto L_0x0041
        L_0x003d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0041:
            r2.close()     // Catch: Exception -> 0x0045
            goto L_0x0049
        L_0x0045:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0049:
            r0 = 1
            return r0
        L_0x004b:
            r0.close()     // Catch: Exception -> 0x004f
            goto L_0x0053
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0053:
            r2.close()     // Catch: Exception -> 0x0088
            goto L_0x008c
        L_0x0057:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0062
        L_0x005c:
            r1 = r0
            goto L_0x0078
        L_0x005e:
            r0 = move-exception
            goto L_0x0062
        L_0x0060:
            r0 = move-exception
            r2 = r1
        L_0x0062:
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch: Exception -> 0x0068
            goto L_0x006c
        L_0x0068:
            r1 = move-exception
            r1.printStackTrace()
        L_0x006c:
            if (r2 == 0) goto L_0x0076
            r2.close()     // Catch: Exception -> 0x0072
            goto L_0x0076
        L_0x0072:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0076:
            throw r0
        L_0x0077:
            r2 = r1
        L_0x0078:
            if (r1 == 0) goto L_0x0082
            r1.close()     // Catch: Exception -> 0x007e
            goto L_0x0082
        L_0x007e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0082:
            if (r2 == 0) goto L_0x008c
            r2.close()     // Catch: Exception -> 0x0088
            goto L_0x008c
        L_0x0088:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goldcoast.sdk.p052c.CPUTypeUtil.m20340b():boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:13:0x0024
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static byte[] m20342a(java.io.File r4) {
        /*
            boolean r0 = r4.exists()
            r1 = 0
            if (r0 == 0) goto L_0x003e
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: Throwable -> 0x0033
            r0.<init>(r4)     // Catch: Throwable -> 0x0033
            r4 = 16
            byte[] r2 = new byte[r4]     // Catch: Throwable -> 0x0034
            r3 = 0
            int r3 = r0.read(r2, r3, r4)     // Catch: Throwable -> 0x0034
            if (r3 != r4) goto L_0x0020
            r0.close()     // Catch: Exception -> 0x001b
            goto L_0x001f
        L_0x001b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x001f:
            return r2
        L_0x0020:
            r0.close()     // Catch: Exception -> 0x003a
            goto L_0x003e
        L_0x0024:
            r4 = move-exception
            goto L_0x0028
        L_0x0026:
            r4 = move-exception
            r0 = r1
        L_0x0028:
            if (r0 == 0) goto L_0x0032
            r0.close()     // Catch: Exception -> 0x002e
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0032:
            throw r4
        L_0x0033:
            r0 = r1
        L_0x0034:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch: Exception -> 0x003a
            goto L_0x003e
        L_0x003a:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goldcoast.sdk.p052c.CPUTypeUtil.m20342a(java.io.File):byte[]");
    }
}
