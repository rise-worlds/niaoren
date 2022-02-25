package p110z1;

import com.common.utils.log.LogUtils;
import com.lbd.p054xj.app.XJApp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.aeg */
/* loaded from: classes3.dex */
public class FileWriteUtils {

    /* renamed from: a */
    private static final String f15435a = "/r/ot01/sdcard/xjdata/";

    /* renamed from: a */
    public static void m14077a(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "install_app");
    }

    /* renamed from: b */
    public static void m14072b(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "launcher_app");
    }

    /* renamed from: c */
    public static void m14070c(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "get_apk");
    }

    /* renamed from: d */
    public static void m14069d(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "uinfo");
    }

    /* renamed from: e */
    public static void m14068e(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "open_app");
    }

    /* renamed from: f */
    public static void m14067f(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "heart_sid");
    }

    /* renamed from: a */
    public static void m14078a() {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append(f15435a);
        File file2 = new File("open_app");
        if (file2.exists()) {
            file2.delete();
        }
    }

    /* renamed from: g */
    public static void m14066g(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        m14075a(str, file + f15435a, "engin_intera");
    }

    /* renamed from: a */
    private static void m14075a(String str, String str2, String str3) {
        m14071b(str2, str3);
        String str4 = str2 + str3;
        try {
            File file = new File(str4);
            if (!file.exists()) {
                LogUtils.m22038d("TestFile", "Create the file:" + str4);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            LogUtils.m22036e("TestFile", "Error on write File:" + e);
        }
    }

    /* renamed from: b */
    private static File m14071b(String str, String str2) {
        Exception e;
        m14064i(str);
        File file = null;
        try {
            file = new File(str + str2);
        } catch (Exception e2) {
            e = e2;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return file;
        }
        return file;
    }

    /* renamed from: i */
    private static void m14064i(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            LogUtils.m22034i("error:", e + "");
        }
    }

    /* renamed from: a */
    public static boolean m14076a(String str, String str2) {
        Throwable th;
        Exception e;
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                try {
                    randomAccessFile = new RandomAccessFile(file + "/r/ot01/system/build.prop", "rw");
                    long j = 0;
                    while (true) {
                        try {
                            String readLine = randomAccessFile.readLine();
                            if (readLine == null) {
                                break;
                            }
                            long filePointer = randomAccessFile.getFilePointer();
                            if (readLine.contains(str)) {
                                String replace = readLine.replace(str, str2);
                                randomAccessFile.seek(j);
                                randomAccessFile.writeBytes(replace);
                            }
                            j = filePointer;
                        } catch (Exception e2) {
                            e = e2;
                            randomAccessFile2 = randomAccessFile;
                            e.printStackTrace();
                            randomAccessFile2.close();
                            randomAccessFile = randomAccessFile2;
                            return true;
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                randomAccessFile.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            throw th;
                        }
                    }
                    randomAccessFile.close();
                    randomAccessFile = j;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e4) {
                e = e4;
            }
            return true;
        } catch (IOException e5) {
            e5.printStackTrace();
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* renamed from: b */
    public static List<String> m14073b() {
        Throwable th;
        RandomAccessFile randomAccessFile;
        Exception e;
        ArrayList arrayList = new ArrayList();
        RandomAccessFile randomAccessFile2 = "osimg";
        try {
            try {
                try {
                    randomAccessFile = new RandomAccessFile(new File(XJApp.getInstance().getApplicationInfo().dataDir, (String) randomAccessFile2) + "/r/ot01/rmdir", "rw");
                    while (true) {
                        try {
                            String readLine = randomAccessFile.readLine();
                            if (readLine == null) {
                                break;
                            }
                            arrayList.add(readLine);
                            LogUtils.m22039d("line:" + readLine);
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            randomAccessFile.close();
                            return arrayList;
                        }
                    }
                    randomAccessFile.close();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                randomAccessFile = null;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile2 = 0;
                randomAccessFile2.close();
                throw th;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: h */
    public static String m14065h(String str) {
        return m14074a(str, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0084, code lost:
        if (r6 == false) goto L_0x008d;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m14074a(java.lang.String r5, boolean r6) {
        /*
            java.io.File r0 = new java.io.File
            com.lbd.xj.app.XJApp r1 = com.lbd.p054xj.app.XJApp.getInstance()
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()
            java.lang.String r1 = r1.dataDir
            java.lang.String r2 = "osimg"
            r0.<init>(r1, r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "/r/ot01/sdcard/xjdata/"
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.common.utils.log.LogUtils.m22039d(r5)
            java.io.File r5 = new java.io.File
            java.lang.String r0 = r1.toString()
            r5.<init>(r0)
            java.lang.String r0 = ""
            boolean r1 = r5.isDirectory()
            if (r1 != 0) goto L_0x008d
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            r1.<init>(r5)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            java.lang.String r3 = "UTF-8"
            r2.<init>(r1, r3)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            r3.<init>(r2)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
        L_0x004a:
            java.lang.String r2 = r3.readLine()     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            if (r2 == 0) goto L_0x0065
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            r4.<init>()     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            r4.append(r0)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            r4.append(r2)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            java.lang.String r2 = "\n"
            r4.append(r2)     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            java.lang.String r0 = r4.toString()     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            goto L_0x004a
        L_0x0065:
            r1.close()     // Catch: all -> 0x006e, IOException -> 0x0070, FileNotFoundException -> 0x007d
            if (r6 == 0) goto L_0x008d
        L_0x006a:
            r5.delete()
            goto L_0x008d
        L_0x006e:
            r0 = move-exception
            goto L_0x0087
        L_0x0070:
            r1 = move-exception
            java.lang.String r2 = "TestFile"
            java.lang.String r1 = r1.getMessage()     // Catch: all -> 0x006e
            android.util.Log.d(r2, r1)     // Catch: all -> 0x006e
            if (r6 == 0) goto L_0x008d
            goto L_0x006a
        L_0x007d:
            java.lang.String r1 = "TestFile"
            java.lang.String r2 = "The File doesn't not exist."
            android.util.Log.d(r1, r2)     // Catch: all -> 0x006e
            if (r6 == 0) goto L_0x008d
            goto L_0x006a
        L_0x0087:
            if (r6 == 0) goto L_0x008c
            r5.delete()
        L_0x008c:
            throw r0
        L_0x008d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FileWriteUtils.m14074a(java.lang.String, boolean):java.lang.String");
    }
}
