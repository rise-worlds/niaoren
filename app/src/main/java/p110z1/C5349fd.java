package p110z1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* compiled from: FileUtils.java */
@SuppressLint({"DefaultLocale"})
/* renamed from: z1.fd */
/* loaded from: classes3.dex */
public class C5349fd {

    /* renamed from: d */
    private static final String f21621d = "FileUtil";

    /* renamed from: c */
    private static final String f21620c = Environment.getExternalStorageDirectory().getPath() + File.separatorChar;

    /* renamed from: a */
    public static final String f21618a = f21620c + ShareVal.f16591a + File.separatorChar;

    /* renamed from: b */
    public static final String f21619b = f21618a + "welImg" + File.separatorChar;

    /* renamed from: a */
    public static void m2927a(InputStream inputStream, String str) {
        byte[] bArr = new byte[1000];
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str)));
            while (true) {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m2929a(File file, List<byte[]> list) {
        Throwable th;
        Exception e;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            for (byte[] bArr : list) {
                bufferedOutputStream.write(bArr);
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
                Log.e(f21621d, e3.getMessage());
            }
            return true;
        } catch (Exception e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            Log.e(f21621d, e.getMessage());
            if (bufferedOutputStream2 == null) {
                return false;
            }
            try {
                bufferedOutputStream2.close();
                return false;
            } catch (IOException e5) {
                e5.printStackTrace();
                Log.e(f21621d, e5.getMessage());
                return false;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                    Log.e(f21621d, e6.getMessage());
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m2928a(File file, byte[]... bArr) {
        Throwable th;
        Exception e;
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            randomAccessFile.seek(file.length());
            for (byte[] bArr2 : bArr) {
                randomAccessFile.write(bArr2);
            }
            try {
                randomAccessFile.close();
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                return true;
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m2917a(String str, byte[]... bArr) {
        Throwable th;
        Exception e;
        File b;
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                b = !m2906g(str) ? m2916b(str) : null;
                randomAccessFile = new RandomAccessFile(b, "rw");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            randomAccessFile.seek(b.length());
            for (byte[] bArr2 : bArr) {
                randomAccessFile.write(bArr2);
            }
            try {
                randomAccessFile.close();
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                return true;
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile2 = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m2920a(String str, String str2, boolean z) {
        try {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                file = new File(str);
            }
            FileWriter fileWriter = new FileWriter(file, z);
            if (str2 != null && !"".equals(str2)) {
                fileWriter.write(str2);
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m2926a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /* renamed from: b */
    public static File m2916b(String str) {
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return new File(str);
        } catch (IOException e) {
            e.printStackTrace();
            return file;
        }
    }

    /* renamed from: c */
    public static void m2914c(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: d */
    public static void m2912d(String str) {
        try {
            m2910e(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static boolean m2910e(String str) {
        File file;
        File file2 = new File(str);
        if (!(file2.exists() && file2.isDirectory())) {
            return false;
        }
        String[] list = file2.list();
        boolean z = false;
        for (int i = 0; i < list.length; i++) {
            if (str.endsWith(File.separator)) {
                file = new File(str + list[i]);
            } else {
                file = new File(str + File.separator + list[i]);
            }
            if (file.isFile()) {
                file.delete();
            }
            if (file.isDirectory()) {
                m2910e(str + "/" + list[i]);
                m2912d(str + "/" + list[i]);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m2923a(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static void m2915b(String str, String str2) {
        File file;
        new File(str2).mkdirs();
        String[] list = new File(str).list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                try {
                    if (str.endsWith(File.separator)) {
                        file = new File(str + list[i]);
                    } else {
                        file = new File(str + File.separator + list[i]);
                    }
                    if (file.isFile()) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + file.getName().toString());
                        byte[] bArr = new byte[5120];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        fileInputStream.close();
                    }
                    if (file.isDirectory()) {
                        m2915b(str + "/" + list[i], str2 + "/" + list[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: c */
    public static void m2913c(String str, String str2) {
        m2923a(str, str2);
        m2914c(str);
    }

    /* renamed from: d */
    public static void m2911d(String str, String str2) {
        m2915b(str, str2);
        m2912d(str);
    }

    /* renamed from: e */
    public static boolean m2909e(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    /* renamed from: a */
    public static File[] m2925a(String str, FileFilter fileFilter) {
        File file = new File(str);
        if (!file.isDirectory()) {
            return null;
        }
        if (fileFilter != null) {
            return file.listFiles(fileFilter);
        }
        return file.listFiles();
    }

    /* renamed from: a */
    public static List<String> m2924a(String str, FileFilter fileFilter, boolean z) {
        File[] listFiles = new File(str).listFiles(fileFilter);
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            for (File file : listFiles) {
                arrayList.add(m2919a(file.getAbsolutePath(), z));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<String> m2918a(String str, boolean z, String[] strArr) {
        File[] listFiles = new File(str).listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.addAll(m2918a(file.getPath(), z, strArr));
                } else {
                    String lowerCase = file.getName().toLowerCase();
                    if (strArr != null) {
                        for (String str2 : strArr) {
                            if (lowerCase.endsWith(str2)) {
                                arrayList.add(file.getAbsolutePath());
                            }
                        }
                    } else {
                        arrayList.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m2919a(String str, boolean z) {
        if (str == null || -1 == str.lastIndexOf("/") || -1 == str.lastIndexOf(Consts.f23430h)) {
            return null;
        }
        if (!z) {
            return str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(Consts.f23430h));
        }
        return str.substring(str.lastIndexOf("/") + 1);
    }

    /* renamed from: f */
    public static String m2908f(String str) {
        File file = new File(str);
        try {
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public static boolean m2907f(String str, String str2) {
        String str3;
        if (str2 == null) {
            str2 = "";
        }
        if (str == null) {
            str = "";
        }
        if (str.lastIndexOf("/") == str.length() - 1) {
            str3 = str + str2;
        } else {
            str3 = str + "/" + str2;
        }
        return new File(str3).exists();
    }

    /* renamed from: g */
    public static boolean m2906g(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2922a(String str, String str2, Bitmap bitmap) {
        try {
            File file = new File(str);
            if (!file.exists() && !file.mkdirs()) {
                return false;
            }
            if (str2 == null || str2.trim().length() == 0) {
                str2 = System.currentTimeMillis() + ".jpg";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str, str2));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2921a(String str, String str2, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        try {
            File file = new File(str);
            if (!file.exists() && !file.mkdirs()) {
                return false;
            }
            if (compressFormat == null) {
                compressFormat = Bitmap.CompressFormat.JPEG;
            }
            if (str2 == null || str2.trim().length() == 0) {
                String str3 = System.currentTimeMillis() + "";
                if (compressFormat.equals(Bitmap.CompressFormat.PNG)) {
                    str2 = str3 + ".png";
                } else {
                    str2 = str3 + ".jpg";
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str, str2));
            bitmap.compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: h */
    public static long m2904h(String str) {
        File file = new File(str);
        long j = 0;
        if (!file.exists()) {
            return 0L;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        for (File file2 : file.listFiles()) {
            j += m2904h(file2.getPath());
        }
        return j;
    }

    /* renamed from: i */
    public static String m2902i(String str) {
        Throwable th;
        Exception e;
        StringBuffer stringBuffer = new StringBuffer();
        if (!m2906g(str)) {
            return stringBuffer.toString();
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(new File(str));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            fileInputStream.close();
        } catch (Exception e4) {
            e = e4;
            fileInputStream = fileInputStream;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return stringBuffer.toString();
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static boolean m2930a(Context context, String str, String str2, String str3) {
        Throwable th;
        DataInputStream dataInputStream;
        Exception e;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                dataInputStream = new DataInputStream(context.getAssets().open(str));
                try {
                    m2926a(str2);
                    File file = new File(str2, str3);
                    if (file.exists()) {
                        file.delete();
                    }
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            dataInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = dataInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
                dataInputStream.close();
            } catch (Exception unused) {
            }
            return true;
        } catch (Exception e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            Log.w(f21621d, "copy assets file failed:" + e.toString());
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused2) {
                    return false;
                }
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused3) {
                    throw th;
                }
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2931a(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: all -> 0x002f, Exception -> 0x0032
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch: all -> 0x002f, Exception -> 0x0032
            java.io.InputStream r4 = r4.open(r5)     // Catch: all -> 0x002f, Exception -> 0x0032
            r1.<init>(r4)     // Catch: all -> 0x002f, Exception -> 0x0032
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: Exception -> 0x002d, all -> 0x0054
            r4.<init>()     // Catch: Exception -> 0x002d, all -> 0x0054
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: Exception -> 0x002d, all -> 0x0054
        L_0x0017:
            int r2 = r1.read(r5)     // Catch: Exception -> 0x002d, all -> 0x0054
            r3 = -1
            if (r2 == r3) goto L_0x0022
            r4.append(r5)     // Catch: Exception -> 0x002d, all -> 0x0054
            goto L_0x0017
        L_0x0022:
            r0.flush()     // Catch: Exception -> 0x002d, all -> 0x0054
            java.lang.String r4 = r4.toString()     // Catch: Exception -> 0x002d, all -> 0x0054
            r1.close()     // Catch: Exception -> 0x002c
        L_0x002c:
            return r4
        L_0x002d:
            r4 = move-exception
            goto L_0x0034
        L_0x002f:
            r4 = move-exception
            r1 = r0
            goto L_0x0055
        L_0x0032:
            r4 = move-exception
            r1 = r0
        L_0x0034:
            java.lang.String r5 = "FileUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x0054
            r2.<init>()     // Catch: all -> 0x0054
            java.lang.String r3 = "copy assets file failed:"
            r2.append(r3)     // Catch: all -> 0x0054
            java.lang.String r4 = r4.toString()     // Catch: all -> 0x0054
            r2.append(r4)     // Catch: all -> 0x0054
            java.lang.String r4 = r2.toString()     // Catch: all -> 0x0054
            android.util.Log.w(r5, r4)     // Catch: all -> 0x0054
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch: Exception -> 0x0053
        L_0x0053:
            return r0
        L_0x0054:
            r4 = move-exception
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()     // Catch: Exception -> 0x005a
        L_0x005a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C5349fd.m2931a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: g */
    public static int m2905g(String str, String str2) throws ZipException, IOException {
        File file = new File(str);
        if (!file.exists()) {
            return 1;
        }
        File file2 = new File(str2);
        if (file2.exists()) {
            file2.delete();
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        byte[] bArr = new byte[1024];
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.isDirectory()) {
                Log.d("upZipFile", "ze.getName() = " + zipEntry.getName());
                String str3 = new String((str2 + zipEntry.getName()).getBytes("8859_1"), C5367in.f22024b);
                Log.d("upZipFile", "str = " + str3);
                new File(str3).mkdirs();
            } else {
                Log.d("upZipFile", "ze.getName() = " + zipEntry.getName());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(m2903h(str2, zipEntry.getName())));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();
            }
        }
        zipFile.close();
        return 0;
    }

    /* renamed from: h */
    public static File m2903h(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String substring = str2.substring(str2.lastIndexOf(Consts.f23430h), str2.length());
        return new File(file, "a" + substring);
    }

    /* renamed from: i */
    public static void m2901i(String str, String str2) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && !TextUtils.isEmpty(str2) && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.getName().contains(str2)) {
                        m2912d(file2.getPath());
                    } else if (file2.isDirectory()) {
                        m2901i(file2.getPath(), str2);
                    }
                }
            }
        }
    }
}
