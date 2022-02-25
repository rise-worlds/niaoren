package com.goldcoast.sdk.p052c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/* compiled from: FileUtils.java */
/* renamed from: com.goldcoast.sdk.c.e */
/* loaded from: classes.dex */
public final class C1392e {

    /* renamed from: a */
    private static Method f9011a;

    /* renamed from: b */
    private static final Pattern f9012b;

    static {
        try {
            Method method = Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            f9011a = method;
            method.setAccessible(true);
        } catch (Exception unused) {
        }
        f9012b = Pattern.compile("[\\w%+,./=_-]+");
    }

    /* renamed from: a */
    public static int m20328a(File file) {
        try {
            return ((Integer) f9011a.invoke(null, file.getAbsolutePath(), 493, -1, -1)).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static int m20325a(String str, int i) {
        try {
            return ((Integer) f9011a.invoke(null, str, Integer.valueOf(i), -1, -1)).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static boolean m20327a(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            boolean a = m20326a(fileInputStream, file2);
            fileInputStream.close();
            return a;
        } catch (IOException unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m20326a(InputStream inputStream, File file) {
        Exception e;
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception e3) {
            e = e3;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public static void m20324a(String[] strArr, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder sb = new StringBuilder("#!/system/bin/sh\n");
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%s\n", strArr[i]));
            }
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
