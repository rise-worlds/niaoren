package com.blankj.utilcode.util;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.p105io.IOUtils;

/* renamed from: com.blankj.utilcode.util.bg */
/* loaded from: classes.dex */
public final class ZipUtils {

    /* renamed from: a */
    private static final int f6834a = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22973a(Collection<String> collection, String str) throws IOException {
        return m22972a(collection, str, (String) null);
    }

    /* renamed from: a */
    public static boolean m22972a(Collection<String> collection, String str, String str2) throws IOException {
        Throwable th;
        if (collection == null || str == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
            try {
                for (String str3 : collection) {
                    if (!m22980a(m22964c(str3), "", zipOutputStream, str2)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    public static boolean m22975a(Collection<File> collection, File file) throws IOException {
        return m22974a(collection, file, (String) null);
    }

    /* renamed from: a */
    public static boolean m22974a(Collection<File> collection, File file, String str) throws IOException {
        Throwable th;
        if (collection == null || file == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
            try {
                for (File file2 : collection) {
                    if (!m22980a(file2, "", zipOutputStream, str)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    public static boolean m22977a(String str, String str2) throws IOException {
        return m22981a(m22964c(str), m22964c(str2), (String) null);
    }

    /* renamed from: a */
    public static boolean m22976a(String str, String str2, String str3) throws IOException {
        return m22981a(m22964c(str), m22964c(str2), str3);
    }

    /* renamed from: a */
    public static boolean m22982a(File file, File file2) throws IOException {
        return m22981a(file, file2, (String) null);
    }

    /* renamed from: a */
    public static boolean m22981a(File file, File file2, String str) throws IOException {
        Throwable th;
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file2));
            try {
                boolean a = m22980a(file, "", zipOutputStream2, str);
                zipOutputStream2.close();
                return a;
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    private static boolean m22980a(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        Throwable th;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(m22962d(str) ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(sb2 + IOUtils.DIR_SEPARATOR_UNIX);
                zipEntry.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.closeEntry();
                return true;
            }
            for (File file2 : listFiles) {
                if (!m22980a(file2, sb2, zipOutputStream, str2)) {
                    return false;
                }
            }
            return true;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            ZipEntry zipEntry2 = new ZipEntry(sb2);
            zipEntry2.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    bufferedInputStream.close();
                    return true;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    public static List<File> m22967b(String str, String str2) throws IOException {
        return m22966b(str, str2, (String) null);
    }

    /* renamed from: b */
    public static List<File> m22970b(File file, File file2) throws IOException {
        return m22969b(file, file2, (String) null);
    }

    /* renamed from: b */
    public static List<File> m22966b(String str, String str2, String str3) throws IOException {
        return m22969b(m22964c(str), m22964c(str2), str3);
    }

    /* renamed from: b */
    public static List<File> m22969b(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        try {
            if (m22962d(str)) {
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    String name = zipEntry.getName();
                    if (name.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + name + " is dangerous!");
                    } else if (!m22979a(file2, arrayList, zipFile, zipEntry, name)) {
                        return arrayList;
                    }
                }
            } else {
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry2 = (ZipEntry) entries.nextElement();
                    String name2 = zipEntry2.getName();
                    if (name2.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + name2 + " is dangerous!");
                    } else if (name2.contains(str) && !m22979a(file2, arrayList, zipFile, zipEntry2, name2)) {
                        return arrayList;
                    }
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    /* renamed from: a */
    private static boolean m22979a(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return m22965c(file2);
        }
        if (!m22963d(file2)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file2));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            bufferedOutputStream2.write(bArr, 0, read);
                        } else {
                            bufferedInputStream.close();
                            bufferedOutputStream2.close();
                            return true;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
    }

    /* renamed from: a */
    public static List<String> m22978a(String str) throws IOException {
        return m22983a(m22964c(str));
    }

    /* renamed from: a */
    public static List<String> m22983a(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String name = ((ZipEntry) entries.nextElement()).getName();
            if (name.contains("../")) {
                Log.e("ZipUtils", "entryName: " + name + " is dangerous!");
                arrayList.add(name);
            } else {
                arrayList.add(name);
            }
        }
        zipFile.close();
        return arrayList;
    }

    /* renamed from: b */
    public static List<String> m22968b(String str) throws IOException {
        return m22971b(m22964c(str));
    }

    /* renamed from: b */
    public static List<String> m22971b(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getComment());
        }
        zipFile.close();
        return arrayList;
    }

    /* renamed from: c */
    private static boolean m22965c(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: d */
    private static boolean m22963d(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!m22965c(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    private static File m22964c(String str) {
        if (m22962d(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: d */
    private static boolean m22962d(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
