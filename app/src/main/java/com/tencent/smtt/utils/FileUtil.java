package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import p110z1.cjm;

@SuppressLint({"NewApi"})
/* renamed from: com.tencent.smtt.utils.f */
/* loaded from: classes2.dex */
public class FileUtil {

    /* renamed from: a */
    public static String f13365a = null;

    /* renamed from: b */
    public static final AbstractC2656a f13366b = new AbstractC2656a() { // from class: com.tencent.smtt.utils.f.2
        @Override // com.tencent.smtt.utils.FileUtil.AbstractC2656a
        /* renamed from: a */
        public boolean mo16433a(File file, File file2) {
            return file.length() == file2.length() && file.lastModified() == file2.lastModified();
        }
    };

    /* renamed from: c */
    private static final int f13367c = 4;

    /* renamed from: d */
    private static RandomAccessFile f13368d;

    /* compiled from: FileUtil.java */
    /* renamed from: com.tencent.smtt.utils.f$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2656a {
        /* renamed from: a */
        boolean mo16433a(File file, File file2);
    }

    /* compiled from: FileUtil.java */
    /* renamed from: com.tencent.smtt.utils.f$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2657b {
        /* renamed from: a */
        boolean mo16432a(InputStream inputStream, ZipEntry zipEntry, String str) throws Exception;
    }

    /* renamed from: a */
    public static String m16465a(Context context, int i) {
        return m16462a(context, context.getApplicationInfo().packageName, i, true);
    }

    /* renamed from: a */
    public static String m16462a(Context context, String str, int i, boolean z) {
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (i) {
            case 1:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 2:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tbs" + File.separator + "backup" + File.separator + str;
            case 3:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
            case 4:
                if (str2.equals("")) {
                    return m16446b(context, "backup");
                }
                String str3 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str3;
                }
                File file = new File(str3);
                if (file.exists() && file.canWrite()) {
                    return str3;
                }
                if (file.exists()) {
                    return m16446b(context, "backup");
                }
                file.mkdirs();
                return !file.canWrite() ? m16446b(context, "backup") : str3;
            case 5:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 6:
                String str4 = f13365a;
                if (str4 != null) {
                    return str4;
                }
                f13365a = m16446b(context, "tbslog");
                return f13365a;
            case 7:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + "core";
            default:
                return "";
        }
    }

    /* renamed from: b */
    private static String m16446b(Context context, String str) {
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        try {
            return context.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + context.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /* renamed from: a */
    public static boolean m16466a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return context != null && context.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    /* renamed from: a */
    public static boolean m16458a(File file, File file2) throws Exception {
        return m16451a(file.getPath(), file2.getPath());
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public static boolean m16451a(String str, String str2) throws Exception {
        return m16449a(str, str2, Build.CPU_ABI, Build.VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null, PropertyUtils.m16409a("ro.product.cpu.upgradeabi", "armeabi"));
    }

    /* renamed from: a */
    private static boolean m16449a(String str, final String str2, String str3, String str4, String str5) throws Exception {
        return m16450a(str, str3, str4, str5, new AbstractC2657b() { // from class: com.tencent.smtt.utils.f.1
            @Override // com.tencent.smtt.utils.FileUtil.AbstractC2657b
            /* renamed from: a */
            public boolean mo16432a(InputStream inputStream, ZipEntry zipEntry, String str6) throws Exception {
                try {
                    return FileUtil.m16441b(inputStream, zipEntry, str2, str6);
                } catch (Exception e) {
                    throw new Exception("copyFileIfChanged Exception", e);
                }
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
        if (r6.regionMatches(com.tencent.smtt.utils.FileUtil.f13367c, r14, 0, r14.length()) == false) goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a2, code lost:
        if (r6.charAt(com.tencent.smtt.utils.FileUtil.f13367c + r14.length()) != '/') goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a4, code lost:
        if (r3 != false) goto L_0x000e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a6, code lost:
        if (r4 == false) goto L_0x00aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x000e, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x000e, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x000e, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m16450a(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, com.tencent.smtt.utils.FileUtil.AbstractC2657b r15) throws java.lang.Exception {
        /*
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch: all -> 0x00d5
            r1.<init>(r11)     // Catch: all -> 0x00d5
            java.util.Enumeration r11 = r1.entries()     // Catch: all -> 0x00d3
            r0 = 1
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000e:
            boolean r5 = r11.hasMoreElements()     // Catch: all -> 0x00d3
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r11.nextElement()     // Catch: all -> 0x00d3
            java.util.zip.ZipEntry r5 = (java.util.zip.ZipEntry) r5     // Catch: all -> 0x00d3
            java.lang.String r6 = r5.getName()     // Catch: all -> 0x00d3
            if (r6 != 0) goto L_0x0021
            goto L_0x000e
        L_0x0021:
            java.lang.String r7 = "../"
            boolean r7 = r6.contains(r7)     // Catch: all -> 0x00d3
            if (r7 == 0) goto L_0x002a
            goto L_0x000e
        L_0x002a:
            java.lang.String r7 = "lib/"
            boolean r7 = r6.startsWith(r7)     // Catch: all -> 0x00d3
            if (r7 != 0) goto L_0x003b
            java.lang.String r7 = "assets/"
            boolean r7 = r6.startsWith(r7)     // Catch: all -> 0x00d3
            if (r7 != 0) goto L_0x003b
            goto L_0x000e
        L_0x003b:
            r7 = 47
            int r8 = r6.lastIndexOf(r7)     // Catch: all -> 0x00d3
            java.lang.String r8 = r6.substring(r8)     // Catch: all -> 0x00d3
            java.lang.String r9 = ".so"
            boolean r9 = r8.endsWith(r9)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x00aa
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r12.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r12, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x0068
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r12.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r9 != r7) goto L_0x0068
            r3 = 1
            goto L_0x00aa
        L_0x0068:
            if (r13 == 0) goto L_0x0089
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r13.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r13, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x0089
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r13.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r9 != r7) goto L_0x0089
            if (r3 == 0) goto L_0x0087
            r4 = 1
            goto L_0x000e
        L_0x0087:
            r4 = 1
            goto L_0x00aa
        L_0x0089:
            if (r14 == 0) goto L_0x000e
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r14.length()     // Catch: all -> 0x00d3
            boolean r9 = r6.regionMatches(r9, r14, r2, r10)     // Catch: all -> 0x00d3
            if (r9 == 0) goto L_0x000e
            int r9 = com.tencent.smtt.utils.FileUtil.f13367c     // Catch: all -> 0x00d3
            int r10 = r14.length()     // Catch: all -> 0x00d3
            int r9 = r9 + r10
            char r6 = r6.charAt(r9)     // Catch: all -> 0x00d3
            if (r6 != r7) goto L_0x000e
            if (r3 != 0) goto L_0x000e
            if (r4 == 0) goto L_0x00aa
            goto L_0x000e
        L_0x00aa:
            java.io.InputStream r6 = r1.getInputStream(r5)     // Catch: all -> 0x00d3
            java.lang.String r7 = r8.substring(r0)     // Catch: all -> 0x00c8
            boolean r5 = r15.mo16432a(r6, r5, r7)     // Catch: all -> 0x00c8
            if (r5 != 0) goto L_0x00c1
            if (r6 == 0) goto L_0x00bd
            r6.close()     // Catch: all -> 0x00d3
        L_0x00bd:
            r1.close()
            return r2
        L_0x00c1:
            if (r6 == 0) goto L_0x000e
            r6.close()     // Catch: all -> 0x00d3
            goto L_0x000e
        L_0x00c8:
            r11 = move-exception
            if (r6 == 0) goto L_0x00ce
            r6.close()     // Catch: all -> 0x00d3
        L_0x00ce:
            throw r11     // Catch: all -> 0x00d3
        L_0x00cf:
            r1.close()
            return r0
        L_0x00d3:
            r11 = move-exception
            goto L_0x00d7
        L_0x00d5:
            r11 = move-exception
            r1 = r0
        L_0x00d7:
            if (r1 == 0) goto L_0x00dc
            r1.close()
        L_0x00dc:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.m16450a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.f$b):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static boolean m16441b(InputStream inputStream, ZipEntry zipEntry, String str, String str2) throws Exception {
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream;
        m16459a(new File(str));
        String str3 = str + File.separator + str2;
        File file = new File(str3);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            if (m16452a(str3, zipEntry.getSize(), zipEntry.getTime(), zipEntry.getCrc())) {
                TbsLog.m16533e("FileHelper", "file is different: " + str3);
                return false;
            } else if (file.setLastModified(zipEntry.getTime())) {
                return true;
            } else {
                TbsLog.m16533e("FileHelper", "Couldn't set time for dst file " + file);
                return true;
            }
        } catch (IOException e3) {
            e = e3;
            m16444b(file);
            throw new IOException("Couldn't write dst file " + file, e);
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static boolean m16452a(String str, long j, long j2, long j3) throws Exception {
        Throwable th;
        File file = new File(str);
        if (file.length() != j) {
            TbsLog.m16533e("FileHelper", "file size doesn't match: " + file.length() + " vs " + j);
            return true;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                CRC32 crc32 = new CRC32();
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    crc32.update(bArr, 0, read);
                }
                long value = crc32.getValue();
                TbsLog.m16531i("FileHelper", "" + file.getName() + ": crc = " + value + ", zipCrc = " + j3);
                if (value != j3) {
                    fileInputStream.close();
                    return true;
                }
                fileInputStream.close();
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: b */
    public static boolean m16443b(File file, File file2) throws Exception {
        return m16457a(file, file2, (FileFilter) null);
    }

    /* renamed from: a */
    public static boolean m16457a(File file, File file2, FileFilter fileFilter) throws Exception {
        return m16456a(file, file2, fileFilter, f13366b);
    }

    /* renamed from: a */
    public static boolean m16456a(File file, File file2, FileFilter fileFilter, AbstractC2656a aVar) throws Exception {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return m16442b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles(fileFilter);
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!m16457a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: b */
    private static boolean m16442b(File file, File file2, FileFilter fileFilter, AbstractC2656a aVar) throws Exception {
        Throwable th;
        if (file == null || file2 == null) {
            return false;
        }
        if (fileFilter != null && !fileFilter.accept(file)) {
            return false;
        }
        FileChannel fileChannel = 0;
        try {
            if (file.exists() && file.isFile()) {
                if (file2.exists()) {
                    if (aVar != null && aVar.mo16433a(file, file2)) {
                        return true;
                    }
                    m16444b(file2);
                }
                File parentFile = file2.getParentFile();
                if (parentFile.isFile()) {
                    m16444b(parentFile);
                }
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    return false;
                }
                FileChannel channel = new FileInputStream(file).getChannel();
                try {
                    fileChannel = new FileOutputStream(file2).getChannel();
                    long size = channel.size();
                    if (fileChannel.transferFrom(channel, 0L, size) != size) {
                        m16444b(file2);
                        if (channel != null) {
                            channel.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        return false;
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = channel;
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
        }
    }

    /* renamed from: a */
    public static boolean m16459a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        m16444b(file);
        return file.mkdirs();
    }

    /* renamed from: b */
    public static void m16444b(File file) {
        m16455a(file, false);
    }

    /* renamed from: a */
    public static void m16455a(File file, boolean z) {
        TbsLog.m16531i("FileUtils", "delete file,ignore=" + z + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    m16455a(file2, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m16454a(File file, boolean z, String str) {
        TbsLog.m16531i("FileUtils", "delete file,ignore=" + z + "except" + str + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getName().equals(str)) {
                        m16455a(file2, z);
                    }
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    /* renamed from: c */
    public static boolean m16439c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    /* renamed from: d */
    public static FileOutputStream m16437d(File file) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    /* renamed from: b */
    public static boolean m16447b(Context context) {
        long a = TbsUtils.m16365a();
        boolean z = a >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.m16533e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a);
        }
        return z;
    }

    /* renamed from: c */
    public static String m16440c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    /* renamed from: d */
    static String m16438d(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* renamed from: a */
    public static File m16460a(Context context, boolean z, String str) {
        String str2;
        if (z) {
            str2 = m16438d(context);
        } else {
            str2 = m16440c(context);
        }
        if (str2 == null) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    /* renamed from: a */
    public static File m16463a(Context context, String str) {
        File file = new File(context.getFilesDir(), "tbs");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            TbsLog.m16533e("FileHelper", "getPermanentTbsFile -- no permission!");
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                TbsLog.m16533e("FileHelper", "getPermanentTbsFile -- exception: " + e);
                return null;
            }
        }
        return file2;
    }

    /* renamed from: b */
    public static FileOutputStream m16445b(Context context, boolean z, String str) {
        File a = m16460a(context, z, str);
        if (a == null) {
            return null;
        }
        try {
            return new FileOutputStream(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static FileLock m16464a(Context context, FileOutputStream fileOutputStream) {
        FileLock tryLock;
        if (fileOutputStream == null) {
            return null;
        }
        try {
            tryLock = fileOutputStream.getChannel().tryLock();
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (tryLock.isValid()) {
            return tryLock;
        }
        return null;
    }

    /* renamed from: a */
    public static void m16448a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                FileChannel channel = fileLock.channel();
                if (channel != null && channel.isOpen()) {
                    fileLock.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    public static FileLock m16436e(Context context) {
        boolean z;
        TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock #1");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        FileLock fileLock = null;
        if (!z) {
            FileOutputStream b = m16445b(context, true, "tbs_rename_lock");
            if (b == null) {
                TbsLog.m16531i("FileHelper", "init -- failed to get rename fileLock#1!");
            } else {
                fileLock = m16464a(context, b);
                if (fileLock == null) {
                    TbsLog.m16531i("FileHelper", "init -- failed to get rename fileLock#2!");
                } else {
                    TbsLog.m16531i("FileHelper", "init -- get rename fileLock success!");
                }
            }
            TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock #2 renameFileLock is " + fileLock);
            return fileLock;
        }
        TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock #3");
        File a = m16463a(context, "tbs_rename_lock");
        TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock #4 " + a);
        try {
            f13368d = new RandomAccessFile(a.getAbsolutePath(), "r");
            fileLock = f13368d.getChannel().tryLock(0L, cjm.f20759b, true);
        } catch (Throwable th) {
            TbsLog.m16533e("FileHelper", "getTbsCoreLoadFileLock -- exception: " + th);
        }
        if (fileLock == null) {
            fileLock = m16434g(context);
        }
        if (fileLock == null) {
            TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock -- failed: tbs_rename_lock");
        } else {
            TbsLog.m16531i("FileHelper", "getTbsCoreLoadFileLock -- success: tbs_rename_lock");
        }
        return fileLock;
    }

    /* renamed from: g */
    private static FileLock m16434g(Context context) {
        try {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.setErrorCode(803);
            File a = m16463a(context, "tbs_rename_lock");
            if (!TbsDownloadConfig.getInstance(context).getTbsCoreLoadRenameFileLockWaitEnable()) {
                return null;
            }
            boolean z = false;
            FileLock fileLock = null;
            int i = 0;
            while (i < 20 && fileLock == null) {
                try {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    f13368d = new RandomAccessFile(a.getAbsolutePath(), "r");
                    fileLock = f13368d.getChannel().tryLock(0L, cjm.f20759b, true);
                    i++;
                }
            }
            if (fileLock != null) {
                tbsLogInfo.setErrorCode(802);
            } else {
                tbsLogInfo.setErrorCode(801);
            }
            TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
            StringBuilder sb = new StringBuilder();
            sb.append("getTbsCoreLoadFileLock,retry num=");
            sb.append(i);
            sb.append("success=");
            if (fileLock == null) {
                z = true;
            }
            sb.append(z);
            TbsLog.m16531i("FileHelper", sb.toString());
            return fileLock;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public static FileLock m16435f(Context context) {
        FileLock fileLock;
        File a = m16463a(context, "tbs_rename_lock");
        TbsLog.m16531i("FileHelper", "getTbsCoreRenameFileLock #1 " + a);
        try {
            f13368d = new RandomAccessFile(a.getAbsolutePath(), "rw");
            fileLock = f13368d.getChannel().tryLock(0L, cjm.f20759b, false);
        } catch (Throwable unused) {
            TbsLog.m16533e("FileHelper", "getTbsCoreRenameFileLock -- excpetion: tbs_rename_lock");
            fileLock = null;
        }
        if (fileLock == null) {
            TbsLog.m16531i("FileHelper", "getTbsCoreRenameFileLock -- failed: tbs_rename_lock");
        } else {
            TbsLog.m16531i("FileHelper", "getTbsCoreRenameFileLock -- success: tbs_rename_lock");
        }
        return fileLock;
    }

    /* renamed from: a */
    public static synchronized void m16461a(Context context, FileLock fileLock) {
        synchronized (FileUtil.class) {
            TbsLog.m16531i("FileHelper", "releaseTbsCoreRenameFileLock -- lock: " + fileLock);
            FileChannel channel = fileLock.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    fileLock.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
