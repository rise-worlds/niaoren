package com.lody.virtual.server.p063pm;

import com.lody.virtual.helper.DexOptimizer;
import com.lody.virtual.helper.dedex.Dex;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* renamed from: com.lody.virtual.server.pm.OatHelper */
/* loaded from: classes.dex */
public class OatHelper {
    private static final String[] ABIS_32BIT = {"arm", "mips", "x86"};
    private static final String[] ABIS_64BIT = {"arm64", "mips64", "x86_64"};

    public static boolean extractFrameworkFor32Bit(String str, File file, File file2) {
        return extractFramework(str, ABIS_32BIT, ABIS_64BIT, file, file2);
    }

    public static boolean extractFrameworkFor64Bit(String str, File file, File file2) {
        return extractFramework(str, ABIS_64BIT, ABIS_32BIT, file, file2);
    }

    public static boolean extractFramework(String str, String[] strArr, String[] strArr2, File file, File file2) {
        boolean z;
        File file3 = new File("/system/framework/" + str + ".jar");
        if (!file3.exists() || containDex(file3.getPath())) {
            return false;
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (new File(String.format("/system/framework/oat/%s/%s.oat", strArr[i], str)).exists()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return false;
        }
        for (String str2 : strArr2) {
            File file4 = new File(String.format("/system/framework/oat/%s/%s.oat", str2, str));
            if (file4.exists()) {
                return extractDexFromOatFile(file4, file, file2);
            }
            File file5 = new File(String.format("/system/framework/oat/%s/%s.vdex", str2, str));
            if (file5.exists()) {
                return extractDexFromVDexFile(file5, file, file2);
            }
        }
        return false;
    }

    private static void addDexToZip(ZipOutputStream zipOutputStream, int i, Dex dex) throws IOException {
        String str;
        if (i == 0) {
            str = "classes.dex";
        } else {
            str = "classes" + (i + 1) + ".dex";
        }
        zipOutputStream.putNextEntry(new ZipEntry(str));
        zipOutputStream.write(dex.getFixedBytes());
        zipOutputStream.closeEntry();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.lody.virtual.helper.dedex.DataReader, java.io.Closeable] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean extractDexFromVDexFile(java.io.File r7, java.io.File r8, java.io.File r9) {
        /*
            r0 = 0
            r1 = 0
            com.lody.virtual.helper.dedex.DataReader r2 = new com.lody.virtual.helper.dedex.DataReader     // Catch: all -> 0x003c, Exception -> 0x003f
            r2.<init>(r7)     // Catch: all -> 0x003c, Exception -> 0x003f
            com.lody.virtual.helper.dedex.Vdex r7 = new com.lody.virtual.helper.dedex.Vdex     // Catch: all -> 0x0036, Exception -> 0x0038
            r7.<init>(r2)     // Catch: all -> 0x0036, Exception -> 0x0038
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch: all -> 0x0036, Exception -> 0x0038
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: all -> 0x0036, Exception -> 0x0038
            r4.<init>(r8)     // Catch: all -> 0x0036, Exception -> 0x0038
            r3.<init>(r4)     // Catch: all -> 0x0036, Exception -> 0x0038
            com.lody.virtual.helper.dedex.Vdex$QuickenDex[] r7 = r7.dexFiles     // Catch: all -> 0x0032, Exception -> 0x0034
            int r0 = r7.length     // Catch: all -> 0x0032, Exception -> 0x0034
            r4 = 0
            r5 = 0
        L_0x001b:
            if (r4 >= r0) goto L_0x0027
            r6 = r7[r4]     // Catch: all -> 0x0032, Exception -> 0x0034
            addDexToZip(r3, r5, r6)     // Catch: all -> 0x0032, Exception -> 0x0034
            int r5 = r5 + 1
            int r4 = r4 + 1
            goto L_0x001b
        L_0x0027:
            generateOdex(r8, r9)     // Catch: all -> 0x0032, Exception -> 0x0034
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r2)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r3)
            r7 = 1
            return r7
        L_0x0032:
            r7 = move-exception
            goto L_0x004d
        L_0x0034:
            r7 = move-exception
            goto L_0x003a
        L_0x0036:
            r7 = move-exception
            goto L_0x004e
        L_0x0038:
            r7 = move-exception
            r3 = r0
        L_0x003a:
            r0 = r2
            goto L_0x0041
        L_0x003c:
            r7 = move-exception
            r2 = r0
            goto L_0x004e
        L_0x003f:
            r7 = move-exception
            r3 = r0
        L_0x0041:
            r7.printStackTrace()     // Catch: all -> 0x004b
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r0)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r3)
            return r1
        L_0x004b:
            r7 = move-exception
            r2 = r0
        L_0x004d:
            r0 = r3
        L_0x004e:
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r2)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.p063pm.OatHelper.extractDexFromVDexFile(java.io.File, java.io.File, java.io.File):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable, com.lody.virtual.helper.dedex.Elf] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean extractDexFromOatFile(java.io.File r7, java.io.File r8, java.io.File r9) {
        /*
            r0 = 0
            r1 = 0
            com.lody.virtual.helper.dedex.Elf r2 = new com.lody.virtual.helper.dedex.Elf     // Catch: all -> 0x0056, Exception -> 0x0059
            r2.<init>(r7)     // Catch: all -> 0x0056, Exception -> 0x0059
            java.lang.String r7 = ".rodata"
            com.lody.virtual.helper.dedex.Elf$Elf_Shdr r7 = r2.getSection(r7)     // Catch: all -> 0x0050, Exception -> 0x0052
            if (r7 != 0) goto L_0x0016
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r2)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r0)
            return r1
        L_0x0016:
            com.lody.virtual.helper.dedex.DataReader r3 = r2.getReader()     // Catch: all -> 0x0050, Exception -> 0x0052
            long r4 = r7.getOffset()     // Catch: all -> 0x0050, Exception -> 0x0052
            r3.seek(r4)     // Catch: all -> 0x0050, Exception -> 0x0052
            com.lody.virtual.helper.dedex.Oat r7 = new com.lody.virtual.helper.dedex.Oat     // Catch: all -> 0x0050, Exception -> 0x0052
            r7.<init>(r3)     // Catch: all -> 0x0050, Exception -> 0x0052
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch: all -> 0x0050, Exception -> 0x0052
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: all -> 0x0050, Exception -> 0x0052
            r4.<init>(r8)     // Catch: all -> 0x0050, Exception -> 0x0052
            r3.<init>(r4)     // Catch: all -> 0x0050, Exception -> 0x0052
            com.lody.virtual.helper.dedex.Dex[] r7 = r7.dexFiles     // Catch: all -> 0x004c, Exception -> 0x004e
            int r0 = r7.length     // Catch: all -> 0x004c, Exception -> 0x004e
            r4 = 0
            r5 = 0
        L_0x0035:
            if (r4 >= r0) goto L_0x0041
            r6 = r7[r4]     // Catch: all -> 0x004c, Exception -> 0x004e
            addDexToZip(r3, r5, r6)     // Catch: all -> 0x004c, Exception -> 0x004e
            int r5 = r5 + 1
            int r4 = r4 + 1
            goto L_0x0035
        L_0x0041:
            generateOdex(r8, r9)     // Catch: all -> 0x004c, Exception -> 0x004e
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r2)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r3)
            r7 = 1
            return r7
        L_0x004c:
            r7 = move-exception
            goto L_0x0067
        L_0x004e:
            r7 = move-exception
            goto L_0x0054
        L_0x0050:
            r7 = move-exception
            goto L_0x0068
        L_0x0052:
            r7 = move-exception
            r3 = r0
        L_0x0054:
            r0 = r2
            goto L_0x005b
        L_0x0056:
            r7 = move-exception
            r2 = r0
            goto L_0x0068
        L_0x0059:
            r7 = move-exception
            r3 = r0
        L_0x005b:
            r7.printStackTrace()     // Catch: all -> 0x0065
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r0)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r3)
            return r1
        L_0x0065:
            r7 = move-exception
            r2 = r0
        L_0x0067:
            r0 = r3
        L_0x0068:
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r2)
            com.lody.virtual.helper.utils.FileUtils.closeQuietly(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.p063pm.OatHelper.extractDexFromOatFile(java.io.File, java.io.File, java.io.File):boolean");
    }

    private static void generateOdex(File file, File file2) throws IOException {
        DexOptimizer.interpretDex2Oat(file.getPath(), file2.getPath());
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0039 -> B:32:0x003c). Please submit an issue!!! */
    public static boolean containDex(String str) {
        Throwable th;
        ZipFile zipFile;
        if (str == null) {
            return false;
        }
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            ZipEntry entry = zipFile.getEntry("classes.dex");
            if (entry != null) {
                if (!entry.isDirectory()) {
                    try {
                        zipFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return true;
                }
            }
            zipFile.close();
        } catch (Throwable th3) {
            th = th3;
            zipFile2 = zipFile;
            try {
                th.printStackTrace();
                if (zipFile2 != null) {
                    zipFile2.close();
                }
                return false;
            } catch (Throwable th4) {
                if (zipFile2 != null) {
                    try {
                        zipFile2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th4;
            }
        }
        return false;
    }
}
