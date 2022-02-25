package com.lody.virtual.helper.compat;

import android.annotation.TargetApi;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import java.io.File;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import p110z1.NativeLibraryHelper;
import p110z1.acf;

/* loaded from: classes.dex */
public class NativeLibraryHelperCompat {
    private static String TAG = "NativeLibraryHelperCompat";

    public static int copyNativeBinaries(File file, File file2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return copyNativeBinariesAfterL(file, file2);
        }
        return copyNativeBinariesBeforeL(file, file2);
    }

    private static int copyNativeBinariesBeforeL(File file, File file2) {
        try {
            return ((Integer) Reflect.m18999on(NativeLibraryHelper.TYPE).call("copyNativeBinariesIfNeededLI", file, file2).get()).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    @TargetApi(21)
    private static int copyNativeBinariesAfterL(File file, File file2) {
        int intValue;
        int intValue2;
        try {
            Object call = NativeLibraryHelper.C5198a.create.call(file);
            if (call == null) {
                return -1;
            }
            String str = null;
            Set<String> supportAbiList = getSupportAbiList(file.getAbsolutePath());
            if (supportAbiList != null && !supportAbiList.isEmpty()) {
                if (!VirtualRuntime.is64bit() || !support64bitAbi(supportAbiList)) {
                    if (Build.SUPPORTED_32_BIT_ABIS.length > 0 && (intValue = NativeLibraryHelper.findSupportedAbi.call(call, Build.SUPPORTED_32_BIT_ABIS).intValue()) >= 0) {
                        str = Build.SUPPORTED_32_BIT_ABIS[intValue];
                    }
                } else if (Build.SUPPORTED_64_BIT_ABIS.length > 0 && (intValue2 = NativeLibraryHelper.findSupportedAbi.call(call, Build.SUPPORTED_64_BIT_ABIS).intValue()) >= 0) {
                    str = Build.SUPPORTED_64_BIT_ABIS[intValue2];
                }
                if (str != null) {
                    return NativeLibraryHelper.copyNativeBinaries.call(call, file2, str).intValue();
                }
                VLog.m18991e(TAG, "Not match any abi [%s].", file.getAbsolutePath());
                return -1;
            }
            return 0;
        } catch (Throwable th) {
            VLog.m18993d(TAG, "copyNativeBinaries with error : %s", th.getLocalizedMessage());
            th.printStackTrace();
            return -1;
        }
    }

    public static SoLib getInstalledSo(ApplicationInfo applicationInfo, boolean z) {
        String abiFromElf;
        if (applicationInfo == null) {
            return null;
        }
        File file = new File(applicationInfo.nativeLibraryDir);
        File[] listFiles = file.listFiles();
        if (z) {
            File file2 = new File(applicationInfo.nativeLibraryDir + acf.f15192q);
            if (file2.exists()) {
                listFiles = file2.listFiles();
            } else {
                file2 = file;
            }
            if (!ArrayUtils.isEmpty(listFiles) && (abiFromElf = getAbiFromElf(listFiles[0])) != null) {
                return new SoLib(abiFromElf, file2.getAbsolutePath());
            }
        } else if (!ArrayUtils.isEmpty(listFiles)) {
            String abiFromElf2 = getAbiFromElf(listFiles[0]);
            if ((applicationInfo.flags & 1) == 0 && is32bitAbi(abiFromElf2)) {
                return new SoLib(abiFromElf2, file.getAbsolutePath());
            }
        }
        return null;
    }

    public static void linkInstalledSo(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (!ArrayUtils.isEmpty(listFiles)) {
            for (File file3 : listFiles) {
                try {
                    Runtime.getRuntime().exec(String.format("ln -s %s %s", file3.getAbsoluteFile(), new File(file2, file3.getName()).getAbsolutePath())).waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getAbiFromElf(java.io.File r4) {
        /*
            r0 = 20
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: all -> 0x0087, Exception -> 0x008a
            r2.<init>(r4)     // Catch: all -> 0x0087, Exception -> 0x008a
            int r4 = r2.read(r0)     // Catch: Exception -> 0x0085, all -> 0x00a2
            int r3 = r0.length     // Catch: Exception -> 0x0085, all -> 0x00a2
            if (r4 != r3) goto L_0x0078
            r4 = 5
            byte r4 = r0[r4]     // Catch: Exception -> 0x0085, all -> 0x00a2
            r3 = 1
            if (r4 != r3) goto L_0x001c
            r4 = 18
            byte r4 = r0[r4]     // Catch: Exception -> 0x0085, all -> 0x00a2
            goto L_0x0020
        L_0x001c:
            r4 = 19
            byte r4 = r0[r4]     // Catch: Exception -> 0x0085, all -> 0x00a2
        L_0x0020:
            r0 = -73
            if (r4 == r0) goto L_0x0069
            r0 = 3
            if (r4 == r0) goto L_0x005a
            r0 = 40
            if (r4 == r0) goto L_0x004b
            r0 = 62
            if (r4 == r0) goto L_0x003c
            r2.close()     // Catch: IOException -> 0x0033
            goto L_0x003b
        L_0x0033:
            r4 = move-exception
            java.lang.String r0 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r0, r2, r4)
        L_0x003b:
            return r1
        L_0x003c:
            java.lang.String r4 = "x86_64"
            r2.close()     // Catch: IOException -> 0x0042
            goto L_0x004a
        L_0x0042:
            r0 = move-exception
            java.lang.String r1 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r1, r2, r0)
        L_0x004a:
            return r4
        L_0x004b:
            java.lang.String r4 = "armeabi-v7a"
            r2.close()     // Catch: IOException -> 0x0051
            goto L_0x0059
        L_0x0051:
            r0 = move-exception
            java.lang.String r1 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r1, r2, r0)
        L_0x0059:
            return r4
        L_0x005a:
            java.lang.String r4 = "x86"
            r2.close()     // Catch: IOException -> 0x0060
            goto L_0x0068
        L_0x0060:
            r0 = move-exception
            java.lang.String r1 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r1, r2, r0)
        L_0x0068:
            return r4
        L_0x0069:
            java.lang.String r4 = "arm64-v8a"
            r2.close()     // Catch: IOException -> 0x006f
            goto L_0x0077
        L_0x006f:
            r0 = move-exception
            java.lang.String r1 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r1, r2, r0)
        L_0x0077:
            return r4
        L_0x0078:
            r2.close()     // Catch: IOException -> 0x007c
            goto L_0x0084
        L_0x007c:
            r4 = move-exception
            java.lang.String r0 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r0, r2, r4)
        L_0x0084:
            return r1
        L_0x0085:
            r4 = move-exception
            goto L_0x008c
        L_0x0087:
            r4 = move-exception
            r2 = r1
            goto L_0x00a3
        L_0x008a:
            r4 = move-exception
            r2 = r1
        L_0x008c:
            java.lang.String r0 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG     // Catch: all -> 0x00a2
            java.lang.String r3 = "getAbiFromElf error"
            android.util.Log.e(r0, r3, r4)     // Catch: all -> 0x00a2
            if (r2 == 0) goto L_0x00a1
            r2.close()     // Catch: IOException -> 0x0099
            goto L_0x00a1
        L_0x0099:
            r4 = move-exception
            java.lang.String r0 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r0, r2, r4)
        L_0x00a1:
            return r1
        L_0x00a2:
            r4 = move-exception
        L_0x00a3:
            if (r2 == 0) goto L_0x00b1
            r2.close()     // Catch: IOException -> 0x00a9
            goto L_0x00b1
        L_0x00a9:
            r0 = move-exception
            java.lang.String r1 = com.lody.virtual.helper.compat.NativeLibraryHelperCompat.TAG
            java.lang.String r2 = "getAbiFromElf close error"
            android.util.Log.e(r1, r2, r0)
        L_0x00b1:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.helper.compat.NativeLibraryHelperCompat.getAbiFromElf(java.io.File):java.lang.String");
    }

    @TargetApi(21)
    public static boolean is64bitAbi(String str) {
        return "arm64-v8a".equals(str) || "x86_64".equals(str) || "mips64".equals(str);
    }

    public static boolean is32bitAbi(String str) {
        return "armeabi".equals(str) || "armeabi-v7a".equals(str) || "mips".equals(str) || "x86".equals(str);
    }

    @TargetApi(21)
    public static boolean contain64bitAbi(Set<String> set) {
        for (String str : set) {
            if (is64bitAbi(str)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(21)
    public static boolean support64bitAbi(Set<String> set) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        String[] strArr = Build.SUPPORTED_64_BIT_ABIS;
        if (ArrayUtils.isEmpty(strArr) || set == null || set.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            for (String str2 : set) {
                if (TextUtils.equals(str, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean contain32bitAbi(Set<String> set) {
        for (String str : set) {
            if (is32bitAbi(str)) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> getSupportAbiList(String str) {
        try {
            Enumeration<? extends ZipEntry> entries = new ZipFile(str).entries();
            HashSet hashSet = new HashSet();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String name = zipEntry.getName();
                if (!name.contains("../") && name.startsWith("lib/") && !zipEntry.isDirectory() && name.endsWith(".so")) {
                    String substring = name.substring(name.indexOf("/") + 1, name.lastIndexOf("/"));
                    String[] strArr = Build.SUPPORTED_ABIS;
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        } else if (strArr[i].equals(substring)) {
                            hashSet.add(substring);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            return hashSet;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    /* loaded from: classes.dex */
    public static class SoLib {
        public String ABI;
        public String path;

        public SoLib() {
        }

        public SoLib(String str, String str2) {
            this.ABI = str;
            this.path = str2;
        }

        public boolean is64Bit() {
            String str = this.ABI;
            return str != null && NativeLibraryHelperCompat.is64bitAbi(str);
        }
    }
}
