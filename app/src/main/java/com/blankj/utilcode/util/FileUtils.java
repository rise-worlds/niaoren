package com.blankj.utilcode.util;

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
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.protocol.HTTP;
import p110z1.RegexConstants;

/* renamed from: com.blankj.utilcode.util.w */
/* loaded from: classes.dex */
public final class FileUtils {

    /* renamed from: a */
    private static final String f6911a = System.getProperty("line.separator");

    /* renamed from: b */
    private static final char[] f6912b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* compiled from: FileUtils.java */
    /* renamed from: com.blankj.utilcode.util.w$a */
    /* loaded from: classes.dex */
    public interface AbstractC1034a {
        /* renamed from: a */
        boolean mo22169a();
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static File m22242a(String str) {
        if (m22254A(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: b */
    public static boolean m22229b(String str) {
        return m22252a(m22242a(str));
    }

    /* renamed from: a */
    public static boolean m22252a(File file) {
        return file != null && file.exists();
    }

    /* renamed from: a */
    public static boolean m22239a(String str, String str2) {
        return m22244a(m22242a(str), str2);
    }

    /* renamed from: a */
    public static boolean m22244a(File file, String str) {
        if (file == null || !file.exists() || m22254A(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    /* renamed from: c */
    public static boolean m22222c(String str) {
        return m22235b(m22242a(str));
    }

    /* renamed from: b */
    public static boolean m22235b(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /* renamed from: d */
    public static boolean m22216d(String str) {
        return m22225c(m22242a(str));
    }

    /* renamed from: c */
    public static boolean m22225c(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /* renamed from: e */
    public static boolean m22212e(String str) {
        return m22219d(m22242a(str));
    }

    /* renamed from: d */
    public static boolean m22219d(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: f */
    public static boolean m22209f(String str) {
        return m22213e(m22242a(str));
    }

    /* renamed from: e */
    public static boolean m22213e(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!m22219d(file.getParentFile())) {
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
    public static boolean m22207g(String str) {
        return m22210f(m22242a(str));
    }

    /* renamed from: f */
    public static boolean m22210f(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !m22219d(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m22227b(String str, String str2) {
        return m22251a(m22242a(str), m22242a(str2));
    }

    /* renamed from: a */
    public static boolean m22238a(String str, String str2, AbstractC1034a aVar) {
        return m22250a(m22242a(str), m22242a(str2), aVar);
    }

    /* renamed from: a */
    public static boolean m22251a(File file, File file2) {
        return m22248a(file, file2, false);
    }

    /* renamed from: a */
    public static boolean m22250a(File file, File file2, AbstractC1034a aVar) {
        return m22249a(file, file2, aVar, false);
    }

    /* renamed from: c */
    public static boolean m22221c(String str, String str2) {
        return m22234b(m22242a(str), m22242a(str2));
    }

    /* renamed from: b */
    public static boolean m22226b(String str, String str2, AbstractC1034a aVar) {
        return m22233b(m22242a(str), m22242a(str2), aVar);
    }

    /* renamed from: b */
    public static boolean m22234b(File file, File file2) {
        return m22231b(file, file2, false);
    }

    /* renamed from: b */
    public static boolean m22233b(File file, File file2, AbstractC1034a aVar) {
        return m22232b(file, file2, aVar, false);
    }

    /* renamed from: d */
    public static boolean m22215d(String str, String str2) {
        return m22224c(m22242a(str), m22242a(str2));
    }

    /* renamed from: c */
    public static boolean m22220c(String str, String str2, AbstractC1034a aVar) {
        return m22223c(m22242a(str), m22242a(str2), aVar);
    }

    /* renamed from: c */
    public static boolean m22224c(File file, File file2) {
        return m22248a(file, file2, true);
    }

    /* renamed from: c */
    public static boolean m22223c(File file, File file2, AbstractC1034a aVar) {
        return m22249a(file, file2, aVar, true);
    }

    /* renamed from: e */
    public static boolean m22211e(String str, String str2) {
        return m22218d(m22242a(str), m22242a(str2));
    }

    /* renamed from: d */
    public static boolean m22214d(String str, String str2, AbstractC1034a aVar) {
        return m22217d(m22242a(str), m22242a(str2), aVar);
    }

    /* renamed from: d */
    public static boolean m22218d(File file, File file2) {
        return m22231b(file, file2, true);
    }

    /* renamed from: d */
    public static boolean m22217d(File file, File file2, AbstractC1034a aVar) {
        return m22232b(file, file2, aVar, true);
    }

    /* renamed from: a */
    private static boolean m22248a(File file, File file2, boolean z) {
        return m22249a(file, file2, new AbstractC1034a() { // from class: com.blankj.utilcode.util.w.1
            @Override // com.blankj.utilcode.util.FileUtils.AbstractC1034a
            /* renamed from: a */
            public boolean mo22169a() {
                return true;
            }
        }, z);
    }

    /* renamed from: a */
    private static boolean m22249a(File file, File file2, AbstractC1034a aVar, boolean z) {
        File[] listFiles;
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (!(!str.contains(file.getPath() + File.separator) && file.exists() && file.isDirectory())) {
            return false;
        }
        if (file2.exists()) {
            if (!(aVar == null || aVar.mo22169a())) {
                return true;
            }
            if (!m22202j(file2)) {
                return false;
            }
        }
        if (!m22219d(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!m22232b(file3, file4, aVar, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !m22249a(file3, file4, aVar, z)) {
                return false;
            }
        }
        return !z || m22206h(file);
    }

    /* renamed from: b */
    private static boolean m22231b(File file, File file2, boolean z) {
        return m22232b(file, file2, new AbstractC1034a() { // from class: com.blankj.utilcode.util.w.2
            @Override // com.blankj.utilcode.util.FileUtils.AbstractC1034a
            /* renamed from: a */
            public boolean mo22169a() {
                return true;
            }
        }, z);
    }

    /* renamed from: b */
    private static boolean m22232b(File file, File file2, AbstractC1034a aVar, boolean z) {
        if (file == null || file2 == null || file.equals(file2) || !file.exists() || !file.isFile()) {
            return false;
        }
        if (file2.exists()) {
            if (aVar != null && !aVar.mo22169a()) {
                return true;
            }
            if (!file2.delete()) {
                return false;
            }
        }
        if (!m22219d(file2.getParentFile())) {
            return false;
        }
        try {
            if (!m22245a(file2, new FileInputStream(file))) {
                return false;
            }
            if (z) {
                if (!m22204i(file)) {
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
    public static boolean m22205h(String str) {
        return m22208g(m22242a(str));
    }

    /* renamed from: g */
    public static boolean m22208g(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return m22206h(file);
        }
        return m22204i(file);
    }

    /* renamed from: i */
    public static boolean m22203i(String str) {
        return m22206h(m22242a(str));
    }

    /* renamed from: h */
    public static boolean m22206h(File file) {
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
                } else if (file2.isDirectory() && !m22206h(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: j */
    public static boolean m22201j(String str) {
        return m22204i(m22242a(str));
    }

    /* renamed from: i */
    public static boolean m22204i(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    /* renamed from: k */
    public static boolean m22199k(String str) {
        return m22202j(m22242a(str));
    }

    /* renamed from: j */
    public static boolean m22202j(File file) {
        return m22247a(file, new FileFilter() { // from class: com.blankj.utilcode.util.w.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        });
    }

    /* renamed from: l */
    public static boolean m22197l(String str) {
        return m22200k(m22242a(str));
    }

    /* renamed from: k */
    public static boolean m22200k(File file) {
        return m22247a(file, new FileFilter() { // from class: com.blankj.utilcode.util.w.4
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isFile();
            }
        });
    }

    /* renamed from: a */
    public static boolean m22241a(String str, FileFilter fileFilter) {
        return m22247a(m22242a(str), fileFilter);
    }

    /* renamed from: a */
    public static boolean m22247a(File file, FileFilter fileFilter) {
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
                    } else if (file2.isDirectory() && !m22206h(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* renamed from: m */
    public static List<File> m22195m(String str) {
        return m22237a(str, false);
    }

    /* renamed from: l */
    public static List<File> m22198l(File file) {
        return m22243a(file, false);
    }

    /* renamed from: a */
    public static List<File> m22237a(String str, boolean z) {
        return m22243a(m22242a(str), z);
    }

    /* renamed from: a */
    public static List<File> m22243a(File file, boolean z) {
        return m22246a(file, new FileFilter() { // from class: com.blankj.utilcode.util.w.5
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        }, z);
    }

    /* renamed from: b */
    public static List<File> m22228b(String str, FileFilter fileFilter) {
        return m22246a(m22242a(str), fileFilter, false);
    }

    /* renamed from: b */
    public static List<File> m22230b(File file, FileFilter fileFilter) {
        return m22246a(file, fileFilter, false);
    }

    /* renamed from: a */
    public static List<File> m22240a(String str, FileFilter fileFilter, boolean z) {
        return m22246a(m22242a(str), fileFilter, z);
    }

    /* renamed from: a */
    public static List<File> m22246a(File file, FileFilter fileFilter, boolean z) {
        if (!m22235b(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    arrayList.add(file2);
                }
                if (z && file2.isDirectory()) {
                    arrayList.addAll(m22246a(file2, fileFilter, true));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: n */
    public static long m22193n(String str) {
        return m22196m(m22242a(str));
    }

    /* renamed from: m */
    public static long m22196m(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    /* renamed from: o */
    public static String m22191o(String str) {
        return m22194n(m22242a(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004e A[RETURN] */
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m22194n(java.io.File r3) {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: all -> 0x0025, IOException -> 0x0027
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: all -> 0x0025, IOException -> 0x0027
            r2.<init>(r3)     // Catch: all -> 0x0025, IOException -> 0x0027
            r1.<init>(r2)     // Catch: all -> 0x0025, IOException -> 0x0027
            int r3 = r1.read()     // Catch: all -> 0x001f, IOException -> 0x0022
            int r3 = r3 << 8
            int r0 = r1.read()     // Catch: all -> 0x001f, IOException -> 0x0022
            int r3 = r3 + r0
            r1.close()     // Catch: IOException -> 0x001a
            goto L_0x0036
        L_0x001a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x001f:
            r3 = move-exception
            r0 = r1
            goto L_0x0051
        L_0x0022:
            r3 = move-exception
            r0 = r1
            goto L_0x0028
        L_0x0025:
            r3 = move-exception
            goto L_0x0051
        L_0x0027:
            r3 = move-exception
        L_0x0028:
            r3.printStackTrace()     // Catch: all -> 0x0025
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch: IOException -> 0x0031
            goto L_0x0035
        L_0x0031:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0035:
            r3 = 0
        L_0x0036:
            r0 = 61371(0xefbb, float:8.5999E-41)
            if (r3 == r0) goto L_0x004e
            r0 = 65279(0xfeff, float:9.1475E-41)
            if (r3 == r0) goto L_0x004b
            r0 = 65534(0xfffe, float:9.1833E-41)
            if (r3 == r0) goto L_0x0048
            java.lang.String r3 = "GBK"
            return r3
        L_0x0048:
            java.lang.String r3 = "Unicode"
            return r3
        L_0x004b:
            java.lang.String r3 = "UTF-16BE"
            return r3
        L_0x004e:
            java.lang.String r3 = "UTF-8"
            return r3
        L_0x0051:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch: IOException -> 0x0057
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileUtils.m22194n(java.io.File):java.lang.String");
    }

    /* renamed from: p */
    public static int m22189p(String str) {
        return m22192o(m22242a(str));
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0033: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:17:0x0032 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0053 -> B:49:0x0068). Please submit an issue!!! */
    /* renamed from: o */
    public static int m22192o(File file) {
        Throwable th;
        IOException e;
        byte[] bArr;
        int i = 1;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        try {
                            bArr = new byte[1024];
                        } catch (IOException e2) {
                            e = e2;
                            bufferedInputStream = bufferedInputStream;
                        }
                        try {
                            if (!f6911a.endsWith("\n")) {
                                while (true) {
                                    int read = bufferedInputStream.read(bArr, 0, 1024);
                                    if (read == -1) {
                                        break;
                                    }
                                    int i2 = i;
                                    for (int i3 = 0; i3 < read; i3++) {
                                        if (bArr[i3] == 13) {
                                            i2++;
                                        }
                                    }
                                    i = i2;
                                }
                            } else {
                                while (true) {
                                    int read2 = bufferedInputStream.read(bArr, 0, 1024);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    int i4 = i;
                                    for (int i5 = 0; i5 < read2; i5++) {
                                        if (bArr[i5] == 10) {
                                            i4++;
                                        }
                                    }
                                    i = i4;
                                }
                            }
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                            e = e3;
                            bufferedInputStream = bufferedInputStream;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return i;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            } catch (IOException e6) {
                e = e6;
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: q */
    public static String m22187q(String str) {
        return m22190p(m22242a(str));
    }

    /* renamed from: p */
    public static String m22190p(File file) {
        long r = m22186r(file);
        return r == -1 ? "" : m22253a(r);
    }

    /* renamed from: r */
    public static String m22185r(String str) {
        long t = m22181t(str);
        return t == -1 ? "" : m22253a(t);
    }

    /* renamed from: q */
    public static String m22188q(File file) {
        long s = m22184s(file);
        return s == -1 ? "" : m22253a(s);
    }

    /* renamed from: s */
    public static long m22183s(String str) {
        return m22186r(m22242a(str));
    }

    /* renamed from: r */
    public static long m22186r(File file) {
        long j;
        if (!m22235b(file)) {
            return -1L;
        }
        long j2 = 0;
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j = m22186r(file2);
                } else {
                    j = file2.length();
                }
                j2 += j;
            }
        }
        return j2;
    }

    /* renamed from: t */
    public static long m22181t(String str) {
        if (str.matches(RegexConstants.f21672g)) {
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                httpsURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    return httpsURLConnection.getContentLength();
                }
                return -1L;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return m22184s(m22242a(str));
    }

    /* renamed from: s */
    public static long m22184s(File file) {
        if (!m22225c(file)) {
            return -1L;
        }
        return file.length();
    }

    /* renamed from: u */
    public static String m22179u(String str) {
        return m22182t(m22254A(str) ? null : new File(str));
    }

    /* renamed from: t */
    public static String m22182t(File file) {
        return m22236a(m22180u(file));
    }

    /* renamed from: v */
    public static byte[] m22177v(String str) {
        return m22180u(m22242a(str));
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0044: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:26:0x0044 */
    /* renamed from: u */
    public static byte[] m22180u(File file) {
        Throwable th;
        Exception e;
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                try {
                    do {
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    byte[] digest = digestInputStream.getMessageDigest().digest();
                    try {
                        digestInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return digest;
                } catch (IOException | NoSuchAlgorithmException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (digestInputStream != null) {
                        try {
                            digestInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException | NoSuchAlgorithmException e5) {
                e = e5;
                digestInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (digestInputStream2 != null) {
                    try {
                        digestInputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: v */
    public static String m22178v(File file) {
        return file == null ? "" : m22175w(file.getAbsolutePath());
    }

    /* renamed from: w */
    public static String m22175w(String str) {
        int lastIndexOf;
        return (!m22254A(str) && (lastIndexOf = str.lastIndexOf(File.separator)) != -1) ? str.substring(0, lastIndexOf + 1) : "";
    }

    /* renamed from: w */
    public static String m22176w(File file) {
        return file == null ? "" : m22173x(file.getAbsolutePath());
    }

    /* renamed from: x */
    public static String m22173x(String str) {
        if (m22254A(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    /* renamed from: x */
    public static String m22174x(File file) {
        return file == null ? "" : m22171y(file.getPath());
    }

    /* renamed from: y */
    public static String m22171y(String str) {
        if (m22254A(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        }
        return str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    /* renamed from: y */
    public static String m22172y(File file) {
        return file == null ? "" : m22170z(file.getPath());
    }

    /* renamed from: z */
    public static String m22170z(String str) {
        if (m22254A(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    /* renamed from: a */
    private static String m22236a(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6912b;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    private static String m22253a(long j) {
        return j < 0 ? "shouldn't be less than zero!" : j < 1024 ? String.format(Locale.getDefault(), "%.3fB", Double.valueOf(j)) : j < 1048576 ? String.format(Locale.getDefault(), "%.3fKB", Double.valueOf(j / 1024.0d)) : j < org.apache.commons.p105io.FileUtils.ONE_GB ? String.format(Locale.getDefault(), "%.3fMB", Double.valueOf(j / 1048576.0d)) : String.format(Locale.getDefault(), "%.3fGB", Double.valueOf(j / 1.073741824E9d));
    }

    /* renamed from: A */
    private static boolean m22254A(String str) {
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

    /* renamed from: a */
    private static boolean m22245a(File file, InputStream inputStream) {
        Throwable th;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
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
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return true;
        } catch (IOException e5) {
            e = e5;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                inputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th;
        }
    }
}
