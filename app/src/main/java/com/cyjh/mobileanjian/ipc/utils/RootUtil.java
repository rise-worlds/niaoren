package com.cyjh.mobileanjian.ipc.utils;

import com.cyjh.mq.p049d.C1363e;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RootUtil {

    /* renamed from: a */
    private static final String f8677a = "ROOT_UTIL";

    public static boolean isRoot() {
        String str = System.getenv("PATH");
        new ArrayList();
        for (String str2 : str.split(":")) {
            File file = new File(str2, C1363e.f8870a);
            if (file.exists() && file.canExecute()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m20675a() {
        Process process;
        DataOutputStream dataOutputStream;
        Throwable th;
        DataOutputStream dataOutputStream2 = null;
        try {
            process = Runtime.getRuntime().exec(C1363e.f8870a);
            try {
                dataOutputStream = new DataOutputStream(process.getOutputStream());
            } catch (Exception unused) {
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream = null;
            }
        } catch (Exception unused2) {
            process = null;
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            process = null;
        }
        try {
            dataOutputStream.writeBytes(C1363e.f8872c);
            dataOutputStream.flush();
            process.waitFor();
            try {
                dataOutputStream.close();
                process.destroy();
                return true;
            } catch (Exception unused3) {
                return true;
            }
        } catch (Exception unused4) {
            dataOutputStream2 = dataOutputStream;
            if (dataOutputStream2 != null) {
                try {
                    dataOutputStream2.close();
                } catch (Exception unused5) {
                    return false;
                }
            }
            process.destroy();
            return false;
        } catch (Throwable th4) {
            th = th4;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (Exception unused6) {
                    throw th;
                }
            }
            process.destroy();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0067, code lost:
        if (r1 != null) goto L_0x0079;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0077, code lost:
        if (r1 == null) goto L_0x007c;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<java.lang.String> m20674a(java.lang.String r5, java.util.ArrayList<java.lang.String> r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.lang.Process r1 = r2.exec(r5)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r5.<init>(r2)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.io.InputStream r4 = r1.getInputStream()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r3.<init>(r4)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r2.<init>(r3)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.util.Iterator r6 = r6.iterator()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
        L_0x0029:
            boolean r3 = r6.hasNext()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r6.next()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.lang.String r3 = (java.lang.String) r3     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r4.<init>()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r4.append(r3)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.lang.String r3 = " 2>&1\n"
            r4.append(r3)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            java.lang.String r3 = r4.toString()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            byte[] r3 = r3.getBytes()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r5.write(r3)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            goto L_0x0029
        L_0x004e:
            java.lang.String r6 = "exit\n"
            byte[] r6 = r6.getBytes()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r5.write(r6)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            r5.flush()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
        L_0x005a:
            java.lang.String r5 = r2.readLine()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            if (r5 == 0) goto L_0x0064
            r0.add(r5)     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            goto L_0x005a
        L_0x0064:
            r1.waitFor()     // Catch: all -> 0x006a, InterruptedException -> 0x006c, IOException -> 0x0073
            if (r1 == 0) goto L_0x007c
            goto L_0x0079
        L_0x006a:
            r5 = move-exception
            goto L_0x007d
        L_0x006c:
            r5 = move-exception
            r5.printStackTrace()     // Catch: all -> 0x006a
            if (r1 == 0) goto L_0x007c
            goto L_0x0079
        L_0x0073:
            r5 = move-exception
            r5.printStackTrace()     // Catch: all -> 0x006a
            if (r1 == 0) goto L_0x007c
        L_0x0079:
            r1.destroy()
        L_0x007c:
            return r0
        L_0x007d:
            if (r1 == 0) goto L_0x0082
            r1.destroy()
        L_0x0082:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.utils.RootUtil.m20674a(java.lang.String, java.util.ArrayList):java.util.ArrayList");
    }
}
