package p110z1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* compiled from: UnZipTools.java */
/* renamed from: z1.amu */
/* loaded from: classes3.dex */
public class amu {

    /* renamed from: b */
    private static amu f16658b = null;

    /* renamed from: c */
    private static final int f16659c = 1048576;

    /* renamed from: a */
    amt f16660a;

    /* compiled from: UnZipTools.java */
    /* renamed from: z1.amu$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3677a {
        /* renamed from: a */
        void mo12437a();

        /* renamed from: a */
        void mo12436a(int i);

        /* renamed from: a */
        void mo12435a(String str);
    }

    /* renamed from: a */
    public void m12498a(String str, String str2, String str3, String str4, ams amsVar) {
        amt amtVar = this.f16660a;
        if (amtVar == null) {
            this.f16660a = new amt(str, str2, str3, str4, amsVar);
            this.f16660a.start();
            return;
        }
        if (amtVar.isAlive() && !this.f16660a.isInterrupted()) {
            this.f16660a.interrupt();
        }
        this.f16660a = new amt(str, str2, str3, str4, amsVar);
        this.f16660a.start();
    }

    /* renamed from: a */
    public static amu m12500a() {
        if (f16658b == null) {
            synchronized (amu.class) {
                if (f16658b == null) {
                    f16658b = new amu();
                }
            }
        }
        return f16658b;
    }

    /* renamed from: b */
    public void m12496b() {
        if (this.f16660a.isAlive() && !this.f16660a.isInterrupted()) {
            this.f16660a.interrupt();
        }
    }

    /* renamed from: a */
    public static void m12497a(String str, String str2, AbstractC3677a aVar) {
        Throwable th;
        Exception e;
        long a = m12499a(str);
        ZipInputStream zipInputStream = null;
        try {
            try {
                try {
                    zipInputStream = new ZipInputStream(new FileInputStream(str));
                } catch (IOException e2) {
                    aVar.mo12435a(e2.getMessage());
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
            aVar.mo12435a(e.getMessage());
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
                    aVar.mo12435a(e5.getMessage());
                }
            }
            throw th;
        }
        if (zipInputStream.available() == 0) {
            aVar.mo12435a("zip错误");
            try {
                zipInputStream.close();
            } catch (IOException e6) {
                aVar.mo12435a(e6.getMessage());
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
                        aVar.mo12436a((int) ((100 * j) / a));
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            }
            aVar.mo12437a();
            zipInputStream.close();
        }
    }

    /* renamed from: a */
    private static long m12499a(String str) {
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
