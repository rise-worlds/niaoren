package p110z1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@SuppressLint({"DefaultLocale"})
/* renamed from: z1.aqc */
/* loaded from: classes3.dex */
public class NRZSFileUtils {

    /* renamed from: d */
    private static final String f17291d = "FileUtil";

    /* renamed from: e */
    private static final String f17292e = "yyyy-MM-dd-HH-mm-ss";

    /* renamed from: c */
    private static final String f17290c = Environment.getExternalStorageDirectory().getPath() + File.separatorChar;

    /* renamed from: a */
    public static final String f17288a = f17290c + ShareVal.f16591a + File.separatorChar;

    /* renamed from: b */
    public static final String f17289b = f17288a + "welImg" + File.separatorChar;

    /* renamed from: a */
    public static void m11625a(InputStream inputStream, String str) {
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
    public static boolean m11627a(File file, List<byte[]> list) {
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
            }
            return true;
        } catch (Exception e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 == null) {
                return false;
            }
            try {
                bufferedOutputStream2.close();
                return false;
            } catch (IOException e5) {
                e5.printStackTrace();
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
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m11626a(File file, byte[]... bArr) {
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
    public static boolean m11614a(String str, byte[]... bArr) {
        Throwable th;
        Exception e;
        File b;
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                b = !m11603g(str) ? m11613b(str) : null;
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
    public static void m11617a(String str, String str2, boolean z) {
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
    public static void m11624a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /* renamed from: b */
    public static File m11613b(String str) {
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
    public static void m11611c(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: d */
    public static void m11609d(String str) {
        try {
            m11607e(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static boolean m11607e(String str) {
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
                m11607e(str + "/" + list[i]);
                m11609d(str + "/" + list[i]);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m11618a(String str, String str2, String str3) {
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(str3 + "/" + str);
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

    /* renamed from: a */
    public static void m11621a(String str, String str2) {
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
                        m11621a(str + "/" + list[i], str2 + "/" + list[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: b */
    public static void m11612b(String str, String str2) {
        m11618a("", str, str2);
        m11611c(str);
    }

    /* renamed from: c */
    public static void m11610c(String str, String str2) {
        m11621a(str, str2);
        m11609d(str);
    }

    /* renamed from: d */
    public static boolean m11608d(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    /* renamed from: a */
    public static File[] m11623a(String str, FileFilter fileFilter) {
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
    public static List<String> m11622a(String str, FileFilter fileFilter, boolean z) {
        File[] listFiles = new File(str).listFiles(fileFilter);
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            for (File file : listFiles) {
                arrayList.add(m11616a(file.getAbsolutePath(), z));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<String> m11615a(String str, boolean z, String[] strArr) {
        File[] listFiles = new File(str).listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.addAll(m11615a(file.getPath(), z, strArr));
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
    public static String m11616a(String str, boolean z) {
        if (str == null || -1 == str.lastIndexOf("/") || -1 == str.lastIndexOf(Consts.f23430h)) {
            return null;
        }
        if (!z) {
            return str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(Consts.f23430h));
        }
        return str.substring(str.lastIndexOf("/") + 1);
    }

    /* renamed from: f */
    public static String m11605f(String str) {
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

    /* renamed from: e */
    public static boolean m11606e(String str, String str2) {
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
    public static boolean m11603g(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m11620a(String str, String str2, Bitmap bitmap) {
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
    public static boolean m11619a(String str, String str2, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
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
    public static long m11601h(String str) {
        File file = new File(str);
        long j = 0;
        if (!file.exists()) {
            return 0L;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        for (File file2 : file.listFiles()) {
            j += m11601h(file2.getPath());
        }
        return j;
    }

    /* renamed from: i */
    public static String m11599i(String str) {
        Throwable th;
        Exception e;
        StringBuffer stringBuffer = new StringBuffer();
        if (!m11603g(str)) {
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
    public static boolean m11629a(Context context, String str, String str2, String str3) {
        DataInputStream dataInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            dataInputStream = new DataInputStream(context.getAssets().open(str));
            try {
                m11624a(str2);
                File file = new File(str2, str3);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused2) {
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
            } catch (Exception unused3) {
            }
            return true;
        } catch (Exception unused4) {
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused5) {
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
                } catch (Exception unused6) {
                    throw th;
                }
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static String m11630a(Context context, String str) {
        DataInputStream dataInputStream;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            dataInputStream = new DataInputStream(context.getAssets().open(str));
            try {
                StringBuffer stringBuffer = new StringBuffer();
                byte[] bArr = new byte[1024];
                while (dataInputStream.read(bArr) != -1) {
                    stringBuffer.append(bArr);
                }
                fileOutputStream.flush();
                String stringBuffer2 = stringBuffer.toString();
                try {
                    dataInputStream.close();
                } catch (Exception unused) {
                }
                return stringBuffer2;
            } catch (Exception unused2) {
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            dataInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
        }
    }

    /* renamed from: f */
    public static int m11604f(String str, String str2) throws ZipException, IOException {
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
                new File(new String((str2 + zipEntry.getName()).getBytes("8859_1"), C5367in.f22024b)).mkdirs();
            } else {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(m11602g(str2, zipEntry.getName())));
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

    /* renamed from: g */
    public static File m11602g(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String substring = str2.substring(str2.lastIndexOf(Consts.f23430h), str2.length());
        return new File(file, "a" + substring);
    }

    /* renamed from: a */
    public static String m11628a(File file) {
        Calendar instance = Calendar.getInstance();
        long lastModified = file.lastModified();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f17292e);
        instance.setTimeInMillis(lastModified);
        return simpleDateFormat.format(instance.getTime());
    }

    /* renamed from: h */
    public static void m11600h(String str, String str2) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && !TextUtils.isEmpty(str2) && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.getName().contains(str2)) {
                        m11609d(file2.getPath());
                    } else if (file2.isDirectory()) {
                        m11600h(file2.getPath(), str2);
                    }
                }
            }
        }
    }
}
