package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.p105io.FileUtils;
import org.apache.http.protocol.HTTP;

/* compiled from: FileUtils.java */
/* renamed from: z1.aef */
/* loaded from: classes3.dex */
public final class aef {

    /* renamed from: a */
    private static final char[] f15433a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: b */
    private static final String f15434b = System.getProperty("line.separator");

    /* compiled from: FileUtils.java */
    /* renamed from: z1.aef$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3390a {
        /* renamed from: a */
        boolean mo14079a();
    }

    private aef() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static File m14154a(String str) {
        if (m14166B(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: b */
    public static boolean m14141b(String str) {
        return m14164a(m14154a(str));
    }

    /* renamed from: a */
    public static boolean m14164a(File file) {
        return file != null && file.exists();
    }

    /* renamed from: a */
    public static boolean m14151a(String str, String str2) {
        return m14156a(m14154a(str), str2);
    }

    /* renamed from: a */
    public static boolean m14156a(File file, String str) {
        if (file == null || !file.exists() || m14166B(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    /* renamed from: c */
    public static boolean m14134c(String str) {
        return m14147b(m14154a(str));
    }

    /* renamed from: b */
    public static boolean m14147b(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /* renamed from: d */
    public static boolean m14128d(String str) {
        return m14137c(m14154a(str));
    }

    /* renamed from: c */
    public static boolean m14137c(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /* renamed from: e */
    public static boolean m14124e(String str) {
        return m14131d(m14154a(str));
    }

    /* renamed from: d */
    public static boolean m14131d(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: f */
    public static boolean m14121f(String str) {
        return m14125e(m14154a(str));
    }

    /* renamed from: e */
    public static boolean m14125e(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!m14131d(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: g */
    public static boolean m14118g(String str) {
        return m14122f(m14154a(str));
    }

    /* renamed from: f */
    public static boolean m14122f(File file) {
        if (file != null && ((!file.exists() || file.delete()) && m14131d(file.getParentFile()))) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m14139b(String str, String str2) {
        String[] list;
        File file = new File(str);
        if (!file.exists() || (list = file.list()) == null) {
            return false;
        }
        if (!new File(str2).exists()) {
            new File(str2).mkdir();
        }
        for (int i = 0; i < list.length; i++) {
            if (new File(str + File.separator + list[i]).isDirectory()) {
                m14139b(str + File.separator + list[i], str2 + File.separator + list[i]);
            }
            if (new File(str + File.separator + list[i]).isFile()) {
                m14127d(str + File.separator + list[i], str2 + File.separator + list[i]);
            }
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m14133c(String str, String str2) {
        return m14163a(m14154a(str), m14154a(str2));
    }

    /* renamed from: a */
    public static boolean m14150a(String str, String str2, AbstractC3390a aVar) {
        return m14162a(m14154a(str), m14154a(str2), aVar);
    }

    /* renamed from: a */
    public static boolean m14163a(File file, File file2) {
        return m14160a(file, file2, false);
    }

    /* renamed from: a */
    public static boolean m14162a(File file, File file2, AbstractC3390a aVar) {
        return m14161a(file, file2, aVar, false);
    }

    /* renamed from: d */
    public static boolean m14127d(String str, String str2) {
        try {
            File file = new File(str);
            File file2 = new File(str2);
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[2097152];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m14123e(String str, String str2) {
        return m14146b(m14154a(str), m14154a(str2));
    }

    /* renamed from: b */
    public static boolean m14138b(String str, String str2, AbstractC3390a aVar) {
        return m14145b(m14154a(str), m14154a(str2), aVar);
    }

    /* renamed from: b */
    public static boolean m14146b(File file, File file2) {
        return m14143b(file, file2, false);
    }

    /* renamed from: b */
    public static boolean m14145b(File file, File file2, AbstractC3390a aVar) {
        return m14144b(file, file2, aVar, false);
    }

    /* renamed from: f */
    public static boolean m14120f(String str, String str2) {
        return m14136c(m14154a(str), m14154a(str2));
    }

    /* renamed from: c */
    public static boolean m14132c(String str, String str2, AbstractC3390a aVar) {
        return m14135c(m14154a(str), m14154a(str2), aVar);
    }

    /* renamed from: c */
    public static boolean m14136c(File file, File file2) {
        return m14160a(file, file2, true);
    }

    /* renamed from: c */
    public static boolean m14135c(File file, File file2, AbstractC3390a aVar) {
        return m14161a(file, file2, aVar, true);
    }

    /* renamed from: g */
    public static boolean m14117g(String str, String str2) {
        return m14130d(m14154a(str), m14154a(str2));
    }

    /* renamed from: d */
    public static boolean m14126d(String str, String str2, AbstractC3390a aVar) {
        return m14129d(m14154a(str), m14154a(str2), aVar);
    }

    /* renamed from: d */
    public static boolean m14130d(File file, File file2) {
        return m14143b(file, file2, true);
    }

    /* renamed from: d */
    public static boolean m14129d(File file, File file2, AbstractC3390a aVar) {
        return m14144b(file, file2, aVar, true);
    }

    /* renamed from: a */
    private static boolean m14160a(File file, File file2, boolean z) {
        return m14161a(file, file2, new AbstractC3390a() { // from class: z1.aef.1
            @Override // p110z1.aef.AbstractC3390a
            /* renamed from: a */
            public boolean mo14079a() {
                return true;
            }
        }, z);
    }

    /* renamed from: a */
    private static boolean m14161a(File file, File file2, AbstractC3390a aVar, boolean z) {
        File[] listFiles;
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory()) {
            return false;
        }
        if (file2.exists()) {
            if (!(aVar == null || aVar.mo14079a())) {
                return true;
            }
            if (!m14112j(file2)) {
                return false;
            }
        }
        if (!m14131d(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!m14144b(file3, file4, aVar, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !m14161a(file3, file4, aVar, z)) {
                return false;
            }
        }
        return !z || m14116h(file);
    }

    /* renamed from: b */
    private static boolean m14143b(File file, File file2, boolean z) {
        return m14144b(file, file2, new AbstractC3390a() { // from class: z1.aef.2
            @Override // p110z1.aef.AbstractC3390a
            /* renamed from: a */
            public boolean mo14079a() {
                return true;
            }
        }, z);
    }

    /* renamed from: b */
    private static boolean m14144b(File file, File file2, AbstractC3390a aVar, boolean z) {
        if (file == null || file2 == null || file.equals(file2) || !file.exists() || !file.isFile()) {
            return false;
        }
        if (file2.exists()) {
            if (aVar != null && !aVar.mo14079a()) {
                return true;
            }
            if (!file2.delete()) {
                return false;
            }
        }
        if (!m14131d(file2.getParentFile())) {
            return false;
        }
        try {
            if (!m14157a(file2, new FileInputStream(file))) {
                return false;
            }
            if (z) {
                if (!m14114i(file)) {
                    return false;
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: h */
    public static boolean m14115h(String str) {
        return m14119g(m14154a(str));
    }

    /* renamed from: g */
    public static boolean m14119g(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return m14116h(file);
        }
        return m14114i(file);
    }

    /* renamed from: i */
    public static boolean m14113i(String str) {
        return m14116h(m14154a(str));
    }

    /* renamed from: h */
    public static boolean m14116h(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !m14116h(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: j */
    public static boolean m14111j(String str) {
        return m14114i(m14154a(str));
    }

    /* renamed from: i */
    public static boolean m14114i(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    /* renamed from: k */
    public static boolean m14109k(String str) {
        return m14112j(m14154a(str));
    }

    /* renamed from: j */
    public static boolean m14112j(File file) {
        return m14159a(file, new FileFilter() { // from class: z1.aef.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        });
    }

    /* renamed from: l */
    public static boolean m14107l(String str) {
        return m14110k(m14154a(str));
    }

    /* renamed from: k */
    public static boolean m14110k(File file) {
        return m14159a(file, new FileFilter() { // from class: z1.aef.4
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isFile();
            }
        });
    }

    /* renamed from: a */
    public static boolean m14153a(String str, FileFilter fileFilter) {
        return m14159a(m14154a(str), fileFilter);
    }

    /* renamed from: a */
    public static boolean m14159a(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !m14116h(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* renamed from: m */
    public static List<File> m14105m(String str) {
        return m14149a(str, false);
    }

    /* renamed from: l */
    public static List<File> m14108l(File file) {
        return m14155a(file, false);
    }

    /* renamed from: a */
    public static List<File> m14149a(String str, boolean z) {
        return m14155a(m14154a(str), z);
    }

    /* renamed from: a */
    public static List<File> m14155a(File file, boolean z) {
        return m14158a(file, new FileFilter() { // from class: z1.aef.5
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        }, z);
    }

    /* renamed from: b */
    public static List<File> m14140b(String str, FileFilter fileFilter) {
        return m14158a(m14154a(str), fileFilter, false);
    }

    /* renamed from: b */
    public static List<File> m14142b(File file, FileFilter fileFilter) {
        return m14158a(file, fileFilter, false);
    }

    /* renamed from: a */
    public static List<File> m14152a(String str, FileFilter fileFilter, boolean z) {
        return m14158a(m14154a(str), fileFilter, z);
    }

    /* renamed from: a */
    public static List<File> m14158a(File file, FileFilter fileFilter, boolean z) {
        if (!m14147b(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return arrayList;
        }
        for (File file2 : listFiles) {
            if (fileFilter.accept(file2)) {
                arrayList.add(file2);
            }
            if (z && file2.isDirectory()) {
                arrayList.addAll(m14158a(file2, fileFilter, true));
            }
        }
        return arrayList;
    }

    /* renamed from: n */
    public static long m14103n(String str) {
        return m14106m(m14154a(str));
    }

    /* renamed from: m */
    public static long m14106m(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    /* renamed from: o */
    public static String m14101o(String str) {
        return m14104n(m14154a(str));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:18:0x0032
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: n */
    public static java.lang.String m14104n(java.io.File r4) {
        /*
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x0042, IOException -> 0x0068
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: Throwable -> 0x0042, IOException -> 0x0068
            r3.<init>(r4)     // Catch: Throwable -> 0x0042, IOException -> 0x0068
            r2.<init>(r3)     // Catch: Throwable -> 0x0042, IOException -> 0x0068
            int r4 = r2.read()     // Catch: IOException -> 0x0022, Throwable -> 0x0042
            int r4 = r4 << 8
            int r3 = r2.read()     // Catch: IOException -> 0x0022, Throwable -> 0x0042
            int r4 = r4 + r3
            r2.close()     // Catch: IOException -> 0x001b, Throwable -> 0x0043
            goto L_0x004d
        L_0x001b:
            r3 = move-exception
            r3.printStackTrace()     // Catch: IOException -> 0x0020, Throwable -> 0x0043
            goto L_0x004d
        L_0x0020:
            r1 = move-exception
            goto L_0x0024
        L_0x0022:
            r1 = move-exception
            r4 = 0
        L_0x0024:
            r1.printStackTrace()     // Catch: Throwable -> 0x0036
            r2.close()     // Catch: IOException -> 0x002b, Throwable -> 0x0036
            goto L_0x002f
        L_0x002b:
            r1 = move-exception
            r1.printStackTrace()     // Catch: Throwable -> 0x0036
        L_0x002f:
            java.lang.String r4 = "GBK"
            return r4
        L_0x0032:
            r4 = move-exception
            r0 = r4
            r4 = 0
            goto L_0x0037
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            r2.close()     // Catch: IOException -> 0x003b, Throwable -> 0x0040
            goto L_0x003f
        L_0x003b:
            r1 = move-exception
            r1.printStackTrace()     // Catch: Throwable -> 0x0040, IOException -> 0x0068
        L_0x003f:
            throw r0     // Catch: Throwable -> 0x0040, IOException -> 0x0068
        L_0x0040:
            r1 = r2
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch: Exception -> 0x0049
            goto L_0x004d
        L_0x0049:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004d:
            r0 = 61371(0xefbb, float:8.5999E-41)
            if (r4 == r0) goto L_0x0065
            r0 = 65279(0xfeff, float:9.1475E-41)
            if (r4 == r0) goto L_0x0062
            r0 = 65534(0xfffe, float:9.1833E-41)
            if (r4 == r0) goto L_0x005f
            java.lang.String r4 = "GBK"
            return r4
        L_0x005f:
            java.lang.String r4 = "Unicode"
            return r4
        L_0x0062:
            java.lang.String r4 = "UTF-16BE"
            return r4
        L_0x0065:
            java.lang.String r4 = "UTF-8"
            return r4
        L_0x0068:
            r4 = move-exception
            r4.printStackTrace()
            java.lang.String r4 = "GBK"
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.aef.m14104n(java.io.File):java.lang.String");
    }

    /* renamed from: p */
    public static int m14099p(String str) {
        return m14102o(m14154a(str));
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0058: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:34:0x0058 */
    /* renamed from: o */
    public static int m14102o(File file) {
        int i;
        int i2;
        int i3 = 0;
        try {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    byte[] bArr = new byte[1024];
                    try {
                        try {
                            if (!f15434b.endsWith("\n")) {
                                int i4 = 1;
                                while (true) {
                                    i2 = bufferedInputStream.read(bArr, 0, 1024);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    int i5 = i4;
                                    for (int i6 = 0; i6 < i2; i6++) {
                                        if (bArr[i6] == 13) {
                                            i5++;
                                        }
                                    }
                                    i4 = i5;
                                }
                                i3 = i4;
                            } else {
                                int i7 = 1;
                                while (true) {
                                    try {
                                        i2 = bufferedInputStream.read(bArr, 0, 1024);
                                    } catch (IOException unused) {
                                        i7 = i7;
                                    }
                                    if (i2 == -1) {
                                        break;
                                    }
                                    int i8 = i7;
                                    int i9 = 0;
                                    while (i9 < i2) {
                                        try {
                                            if (bArr[i9] == 10) {
                                                i8++;
                                            }
                                            i9++;
                                        } catch (Exception unused2) {
                                        }
                                    }
                                    i7 = i8;
                                }
                                i3 = i7;
                            }
                            try {
                                bufferedInputStream.close();
                                return i3;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return i3;
                            }
                        } catch (Throwable unused3) {
                            return i2;
                        }
                    } catch (Throwable unused4) {
                        return i;
                    }
                } catch (IOException e2) {
                    try {
                        e2.printStackTrace();
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return 1;
                    } finally {
                    }
                }
            } catch (Throwable unused5) {
                return i3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            return 1;
        }
    }

    /* renamed from: q */
    public static String m14097q(String str) {
        return m14100p(m14154a(str));
    }

    /* renamed from: p */
    public static String m14100p(File file) {
        long r = m14096r(file);
        return r == -1 ? "" : m14165a(r);
    }

    /* renamed from: r */
    public static String m14095r(String str) {
        long t = m14091t(str);
        return t == -1 ? "" : m14165a(t);
    }

    /* renamed from: q */
    public static String m14098q(File file) {
        long s = m14094s(file);
        return s == -1 ? "" : m14165a(s);
    }

    /* renamed from: s */
    public static long m14093s(String str) {
        return m14096r(m14154a(str));
    }

    /* renamed from: r */
    public static long m14096r(File file) {
        long j;
        if (!m14147b(file)) {
            return -1L;
        }
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles == null || listFiles.length == 0) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j = m14096r(file2);
            } else {
                j = file2.length();
            }
            j2 += j;
        }
        return j2;
    }

    /* renamed from: t */
    public static long m14091t(String str) {
        if (str.matches(RegexConstants.f21672g)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    return httpURLConnection.getContentLength();
                }
                return -1L;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return m14094s(m14154a(str));
    }

    /* renamed from: s */
    public static long m14094s(File file) {
        if (m14137c(file)) {
            return file.length();
        }
        return -1L;
    }

    /* renamed from: u */
    public static String m14089u(String str) {
        return m14092t(m14166B(str) ? null : new File(str));
    }

    /* renamed from: t */
    public static String m14092t(File file) {
        return m14148a(m14090u(file));
    }

    /* renamed from: v */
    public static byte[] m14087v(String str) {
        return m14090u(m14154a(str));
    }

    /* renamed from: u */
    public static byte[] m14090u(File file) {
        if (file != null) {
            try {
                try {
                    DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                    do {
                        try {
                        } catch (IOException e) {
                            e.printStackTrace();
                            digestInputStream.close();
                            return null;
                        }
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    byte[] digest = digestInputStream.getMessageDigest().digest();
                    try {
                        digestInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return digest;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (NoSuchAlgorithmException e4) {
                e4.printStackTrace();
                return null;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    /* renamed from: v */
    public static String m14088v(File file) {
        return file == null ? "" : m14085w(file.getAbsolutePath());
    }

    /* renamed from: w */
    public static String m14085w(String str) {
        int lastIndexOf;
        return (!m14166B(str) && (lastIndexOf = str.lastIndexOf(File.separator)) != -1) ? str.substring(0, lastIndexOf + 1) : "";
    }

    /* renamed from: w */
    public static String m14086w(File file) {
        return file == null ? "" : m14083x(file.getAbsolutePath());
    }

    /* renamed from: x */
    public static String m14083x(String str) {
        if (m14166B(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : str;
    }

    /* renamed from: x */
    public static String m14084x(File file) {
        return file == null ? "" : m14081y(file.getPath());
    }

    /* renamed from: y */
    public static String m14081y(String str) {
        if (m14166B(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
        }
        if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        }
        return str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    /* renamed from: y */
    public static String m14082y(File file) {
        return file == null ? "" : m14080z(file.getPath());
    }

    /* renamed from: z */
    public static String m14080z(String str) {
        if (m14166B(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    /* renamed from: a */
    private static String m14148a(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f15433a;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    private static String m14165a(long j) {
        return j < 0 ? "shouldn't be less than zero!" : j < 1024 ? String.format(Locale.getDefault(), "%.3fB", Double.valueOf(j)) : j < 1048576 ? String.format(Locale.getDefault(), "%.3fKB", Double.valueOf(j / 1024.0d)) : j < FileUtils.ONE_GB ? String.format(Locale.getDefault(), "%.3fMB", Double.valueOf(j / 1048576.0d)) : String.format(Locale.getDefault(), "%.3fGB", Double.valueOf(j / 1.073741824E9d));
    }

    /* renamed from: B */
    private static boolean m14166B(String str) {
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

    /* JADX WARN: Finally extract failed */
    /* renamed from: a */
    private static boolean m14157a(File file, InputStream inputStream) {
        BufferedOutputStream bufferedOutputStream;
        boolean z = false;
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Throwable unused) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr, 0, 8192);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                z = true;
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return z;
            } catch (IOException e4) {
                try {
                    e4.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        return z;
                    }
                    return z;
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                    throw th;
                }
            }
        } catch (IOException e9) {
            e9.printStackTrace();
            try {
                inputStream.close();
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            return z;
        }
    }

    /* renamed from: A */
    public static String m14167A(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            cArr[i] = (char) (charArray[i] ^ 204);
        }
        return String.valueOf(cArr);
    }
}
