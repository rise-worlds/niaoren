package p110z1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* renamed from: z1.aey */
/* loaded from: classes3.dex */
public class UnZipTools {

    /* renamed from: b */
    private static UnZipTools f15487b = null;

    /* renamed from: c */
    private static final int f15488c = 1048576;

    /* renamed from: a */
    UnZipThread f15489a;

    /* compiled from: UnZipTools.java */
    /* renamed from: z1.aey$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3401a {
        /* renamed from: a */
        void m13819a();

        /* renamed from: a */
        void m13818a(int i);

        /* renamed from: a */
        void m13817a(String str);
    }

    /* renamed from: a */
    public void m13822a(String str, String str2, String str3, String str4, UnZipListener aewVar) {
        UnZipThread aexVar = this.f15489a;
        if (aexVar == null) {
            this.f15489a = new UnZipThread(str, str2, str3, str4, aewVar);
            this.f15489a.start();
            return;
        }
        if (aexVar.isAlive() && !this.f15489a.isInterrupted()) {
            this.f15489a.interrupt();
        }
        this.f15489a = new UnZipThread(str, str2, str3, str4, aewVar);
        this.f15489a.start();
    }

    /* renamed from: a */
    public static UnZipTools m13824a() {
        if (f15487b == null) {
            synchronized (UnZipTools.class) {
                if (f15487b == null) {
                    f15487b = new UnZipTools();
                }
            }
        }
        return f15487b;
    }

    /* renamed from: b */
    public void m13820b() {
        if (this.f15489a.isAlive() && !this.f15489a.isInterrupted()) {
            this.f15489a.interrupt();
        }
    }

    /* renamed from: a */
    public static void m13821a(String str, String str2, AbstractC3401a aVar) {
        Throwable th;
        Exception e;
        long a = m13823a(str);
        ZipInputStream zipInputStream = null;
        try {
            try {
                try {
                    zipInputStream = new ZipInputStream(new FileInputStream(str));
                } catch (IOException e2) {
                    aVar.m13817a(e2.getMessage());
                    return;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
        } catch (Exception e4) {
            e = e4;
            zipInputStream = zipInputStream;
            aVar.m13817a(e.getMessage());
            if (zipInputStream != null) {
                zipInputStream.close();
            }
            return;
        } catch (Throwable th3) {
            th = th3;
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e5) {
                    aVar.m13817a(e5.getMessage());
                }
            }
            throw th;
        }
        if (zipInputStream.available() == 0) {
            aVar.m13817a("zip错误");
            try {
                zipInputStream.close();
            } catch (IOException e6) {
                aVar.m13817a(e6.getMessage());
            }
        } else {
            long j = 0;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    String substring = name.substring(0, name.length() - 1);
                    new File(str2 + File.separator + substring).mkdirs();
                } else {
                    File file = new File(str2 + "/" + name);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        j += read;
                        aVar.m13818a((int) ((100 * j) / a));
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            }
            aVar.m13819a();
            zipInputStream.close();
        }
    }

    /* renamed from: a */
    private static long m13823a(String str) {
        try {
            Enumeration<? extends ZipEntry> entries = new ZipFile(str).entries();
            long j = 0;
            while (entries.hasMoreElements()) {
                j += ((ZipEntry) entries.nextElement()).getSize();
            }
            return j;
        } catch (IOException unused) {
            return 0L;
        }
    }
}
