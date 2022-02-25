package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import java.io.File;
import java.util.regex.Pattern;
import p110z1.Consts;

/* renamed from: com.tencent.smtt.utils.a */
/* loaded from: classes2.dex */
public class ApkUtil {
    /* renamed from: a */
    public static final String m16515a(boolean z) {
        return z ? "x5.decouple.backup" : "x5.backup";
    }

    /* renamed from: a */
    public static boolean m16518a(Context context, File file, long j, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (j > 0 && j != file.length()) {
            return false;
        }
        try {
            if (i != m16519a(context, file)) {
                return false;
            }
            return "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(AppUtil.m16508a(context, true, file));
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m16516a(java.io.File r10) {
        /*
            r0 = 16
            char[] r1 = new char[r0]
            r1 = {x0074: FILL_ARRAY_DATA  , data: [48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102} // fill-array
            r2 = 32
            char[] r2 = new char[r2]
            r3 = 0
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch: all -> 0x0055, Exception -> 0x0058
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: all -> 0x0055, Exception -> 0x0058
            r5.<init>(r10)     // Catch: all -> 0x0055, Exception -> 0x0058
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch: Exception -> 0x0053, all -> 0x0068
        L_0x001b:
            int r6 = r5.read(r10)     // Catch: Exception -> 0x0053, all -> 0x0068
            r7 = -1
            r8 = 0
            if (r6 == r7) goto L_0x0027
            r4.update(r10, r8, r6)     // Catch: Exception -> 0x0053, all -> 0x0068
            goto L_0x001b
        L_0x0027:
            byte[] r10 = r4.digest()     // Catch: Exception -> 0x0053, all -> 0x0068
            r4 = 0
        L_0x002c:
            if (r8 >= r0) goto L_0x0045
            byte r6 = r10[r8]     // Catch: Exception -> 0x0053, all -> 0x0068
            int r7 = r4 + 1
            int r9 = r6 >>> 4
            r9 = r9 & 15
            char r9 = r1[r9]     // Catch: Exception -> 0x0053, all -> 0x0068
            r2[r4] = r9     // Catch: Exception -> 0x0053, all -> 0x0068
            int r4 = r7 + 1
            r6 = r6 & 15
            char r6 = r1[r6]     // Catch: Exception -> 0x0053, all -> 0x0068
            r2[r7] = r6     // Catch: Exception -> 0x0053, all -> 0x0068
            int r8 = r8 + 1
            goto L_0x002c
        L_0x0045:
            java.lang.String r10 = new java.lang.String     // Catch: Exception -> 0x0053, all -> 0x0068
            r10.<init>(r2)     // Catch: Exception -> 0x0053, all -> 0x0068
            r5.close()     // Catch: IOException -> 0x004e
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0052:
            return r10
        L_0x0053:
            r10 = move-exception
            goto L_0x005a
        L_0x0055:
            r10 = move-exception
            r5 = r3
            goto L_0x0069
        L_0x0058:
            r10 = move-exception
            r5 = r3
        L_0x005a:
            r10.printStackTrace()     // Catch: all -> 0x0068
            if (r5 == 0) goto L_0x0067
            r5.close()     // Catch: IOException -> 0x0063
            goto L_0x0067
        L_0x0063:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0067:
            return r3
        L_0x0068:
            r10 = move-exception
        L_0x0069:
            if (r5 == 0) goto L_0x0073
            r5.close()     // Catch: IOException -> 0x006f
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.ApkUtil.m16516a(java.io.File):java.lang.String");
    }

    /* renamed from: a */
    public static int m16519a(Context context, File file) {
        try {
            return m16517a(context, file, Build.VERSION.SDK_INT >= 20 ? !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME) : false);
        } catch (Exception unused) {
            TbsLog.m16531i("ApkUtil", "getApkVersion Failed");
            return 0;
        }
    }

    /* renamed from: a */
    private static int m16514a(boolean z, File file) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                return -1;
            }
            File[] listFiles = parentFile.listFiles();
            Pattern compile = Pattern.compile(m16515a(z) + "(.*)");
            for (File file2 : listFiles) {
                if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                    return Integer.parseInt(file2.getName().substring(file2.getName().lastIndexOf(Consts.f23430h) + 1));
                }
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static int m16517a(Context context, File file, boolean z) {
        if (file != null) {
            try {
                if (file.exists()) {
                    boolean contains = file.getName().contains("tbs.org");
                    boolean contains2 = file.getName().contains("x5.tbs.decouple");
                    if (contains || contains2) {
                        int a = m16514a(contains2, file);
                        if (a > 0) {
                            return a;
                        }
                        if (!TbsShareManager.isThirdPartyApp(context) && !file.getAbsolutePath().contains(context.getApplicationInfo().packageName)) {
                            return 0;
                        }
                    }
                    boolean z2 = (Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT == 25) && Build.MANUFACTURER.toLowerCase().contains("mi");
                    TbsPVConfig.releaseInstance();
                    int readApk = TbsPVConfig.getInstance(context).getReadApk();
                    if (readApk == 1) {
                        z = false;
                        z2 = false;
                    } else if (readApk == 2) {
                        return 0;
                    }
                    if (z || z2) {
                        int b = m16513b(file);
                        if (b > 0) {
                            return b;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1 = java.lang.Integer.parseInt(r1[1].trim());
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
        r6.close();
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006d, code lost:
        if (r2 != null) goto L_0x004d;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m16513b(java.io.File r6) {
        /*
            java.lang.Class<com.tencent.smtt.utils.a> r0 = com.tencent.smtt.utils.ApkUtil.class
            monitor-enter(r0)
            r1 = 0
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch: all -> 0x005d, Exception -> 0x0060
            r2.<init>(r6)     // Catch: all -> 0x005d, Exception -> 0x0060
            java.lang.String r6 = "assets/webkit/tbs.conf"
            java.util.jar.JarEntry r6 = r2.getJarEntry(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.InputStream r6 = r2.getInputStream(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: Exception -> 0x005b, all -> 0x0073
            r3.<init>(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: Exception -> 0x005b, all -> 0x0073
            r6.<init>(r3)     // Catch: Exception -> 0x005b, all -> 0x0073
        L_0x001d:
            java.lang.String r1 = r6.readLine()     // Catch: all -> 0x0051, Exception -> 0x0056
            if (r1 == 0) goto L_0x004a
            java.lang.String r3 = "tbs_core_version"
            boolean r3 = r1.contains(r3)     // Catch: all -> 0x0051, Exception -> 0x0056
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = "="
            java.lang.String[] r1 = r1.split(r3)     // Catch: all -> 0x0051, Exception -> 0x0056
            if (r1 == 0) goto L_0x001d
            int r3 = r1.length     // Catch: all -> 0x0051, Exception -> 0x0056
            r4 = 2
            if (r3 != r4) goto L_0x001d
            r3 = 1
            r1 = r1[r3]     // Catch: all -> 0x0051, Exception -> 0x0056
            java.lang.String r1 = r1.trim()     // Catch: all -> 0x0051, Exception -> 0x0056
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: all -> 0x0051, Exception -> 0x0056
            r6.close()     // Catch: Exception -> 0x0048, all -> 0x006b
            r2.close()     // Catch: Exception -> 0x0048, all -> 0x006b
        L_0x0048:
            monitor-exit(r0)     // Catch: all -> 0x006b
            return r1
        L_0x004a:
            r6.close()     // Catch: all -> 0x006b, Exception -> 0x0070
        L_0x004d:
            r2.close()     // Catch: all -> 0x006b, Exception -> 0x0070
            goto L_0x0070
        L_0x0051:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0074
        L_0x0056:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0062
        L_0x005b:
            r6 = move-exception
            goto L_0x0062
        L_0x005d:
            r6 = move-exception
            r2 = r1
            goto L_0x0074
        L_0x0060:
            r6 = move-exception
            r2 = r1
        L_0x0062:
            r6.printStackTrace()     // Catch: all -> 0x0073
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch: all -> 0x006b, Exception -> 0x0070
            goto L_0x006d
        L_0x006b:
            r6 = move-exception
            goto L_0x007f
        L_0x006d:
            if (r2 == 0) goto L_0x0070
            goto L_0x004d
        L_0x0070:
            r6 = -1
            monitor-exit(r0)     // Catch: all -> 0x006b
            return r6
        L_0x0073:
            r6 = move-exception
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch: all -> 0x006b, Exception -> 0x007e
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch: all -> 0x006b, Exception -> 0x007e
        L_0x007e:
            throw r6     // Catch: all -> 0x006b
        L_0x007f:
            monitor-exit(r0)     // Catch: all -> 0x006b
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.ApkUtil.m16513b(java.io.File):int");
    }
}
