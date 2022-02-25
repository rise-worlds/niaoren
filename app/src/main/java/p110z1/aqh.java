package p110z1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* compiled from: ZipUtils.java */
/* renamed from: z1.aqh */
/* loaded from: classes3.dex */
public class aqh {

    /* renamed from: a */
    private static final int f17333a = 1048576;

    /* renamed from: a */
    public static void m11576a(Collection<File> collection, File file) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File file2 : collection) {
            m11579a(file2, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    /* renamed from: a */
    public static void m11575a(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File file2 : collection) {
            m11579a(file2, zipOutputStream, "");
        }
        zipOutputStream.setComment(str);
        zipOutputStream.close();
    }

    /* renamed from: a */
    public static void m11581a(File file, String str) throws ZipException, IOException {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            File file3 = new File(new String((str + File.separator + zipEntry.getName()).getBytes("8859_1"), C5367in.f22024b));
            if (!file3.exists()) {
                File parentFile = file3.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file3.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            inputStream.close();
            fileOutputStream.close();
        }
    }

    /* renamed from: a */
    public static ArrayList<File> m11580a(File file, String str, String str2) throws ZipException, IOException {
        ArrayList<File> arrayList = new ArrayList<>();
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().contains(str2)) {
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                File file3 = new File(new String((str + File.separator + zipEntry.getName()).getBytes("8859_1"), C5367in.f22024b));
                if (!file3.exists()) {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file3.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                byte[] bArr = new byte[1048576];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
                arrayList.add(file3);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static ArrayList<String> m11582a(File file) throws ZipException, IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        Enumeration<?> b = m11573b(file);
        while (b.hasMoreElements()) {
            arrayList.add(new String(m11572b((ZipEntry) b.nextElement()).getBytes(C5367in.f22024b), "8859_1"));
        }
        return arrayList;
    }

    /* renamed from: b */
    public static Enumeration<?> m11573b(File file) throws ZipException, IOException {
        return new ZipFile(file).entries();
    }

    /* renamed from: a */
    public static String m11574a(ZipEntry zipEntry) throws UnsupportedEncodingException {
        return new String(zipEntry.getComment().getBytes(C5367in.f22024b), "8859_1");
    }

    /* renamed from: b */
    public static String m11572b(ZipEntry zipEntry) throws UnsupportedEncodingException {
        return new String(zipEntry.getName().getBytes(C5367in.f22024b), "8859_1");
    }

    /* renamed from: a */
    private static void m11579a(File file, ZipOutputStream zipOutputStream, String str) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        String str2 = new String(sb.toString().getBytes("8859_1"), C5367in.f22024b);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m11579a(file2, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m11578a(String str, String str2) {
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(str2 + "allupload.zip")));
            File file2 = new File(str);
            m11577a(file2.getParent() + File.separator, file2.getName(), zipOutputStream);
            zipOutputStream.finish();
            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m11577a(String str, String str2, ZipOutputStream zipOutputStream) {
        if (zipOutputStream != null) {
            try {
                File file = new File(str + str2);
                if (file.isFile()) {
                    ZipEntry zipEntry = new ZipEntry(str2);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            zipOutputStream.write(bArr, 0, read);
                        } else {
                            zipOutputStream.closeEntry();
                            return;
                        }
                    }
                } else {
                    String[] list = file.list();
                    if (list.length <= 0) {
                        zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                        zipOutputStream.closeEntry();
                    }
                    for (String str3 : list) {
                        m11577a(str + str2 + "/", str3, zipOutputStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
