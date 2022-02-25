package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

/* compiled from: FileUtil.java */
/* renamed from: z1.aoy */
/* loaded from: classes3.dex */
public class aoy {
    /* renamed from: a */
    public static void m11884a(String str, String str2, boolean z) {
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
    public static void m11885a(String str, String str2) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && !TextUtils.isEmpty(str2) && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.getName().contains(str2)) {
                        m11886a(file2.getPath());
                    } else if (file2.isDirectory()) {
                        m11885a(file2.getPath(), str2);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m11886a(String str) {
        try {
            m11883b(str);
            new File(str.toString()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static boolean m11883b(String str) {
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
                m11883b(str + "/" + list[i]);
                m11886a(str + "/" + list[i]);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m11888a(Context context, String str, String str2, String str3) {
        Throwable th;
        DataInputStream dataInputStream;
        Exception e;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                dataInputStream = new DataInputStream(context.getAssets().open(str));
                try {
                    m11882c(str2);
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
            Log.w("lbssss", "copy assets file failed:" + e.toString());
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

    /* renamed from: c */
    public static void m11882c(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /* renamed from: a */
    public static String m11887a(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
