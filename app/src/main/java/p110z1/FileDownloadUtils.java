package p110z1;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;

/* renamed from: z1.ahx */
/* loaded from: classes3.dex */
public class FileDownloadUtils {

    /* renamed from: a */
    private static int f15878a = 65536;

    /* renamed from: b */
    private static long f15879b = 2000;

    /* renamed from: c */
    private static String f15880c = null;

    /* renamed from: d */
    private static Boolean f15881d = null;

    /* renamed from: e */
    private static final String f15882e = "filedownloader";

    /* renamed from: f */
    private static final String f15883f = ".old_file_converted";

    /* renamed from: g */
    private static Boolean f15884g = null;

    /* renamed from: h */
    private static final Pattern f15885h = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");

    /* renamed from: i */
    private static final Pattern f15886i = Pattern.compile("attachment;\\s*filename\\s*=\\s*(.*)");

    /* renamed from: j */
    private static final String f15887j = "FileDownloader";

    /* renamed from: a */
    public static boolean m13186a(String str) {
        return true;
    }

    /* renamed from: a */
    public static void m13194a(int i) throws IllegalAccessException {
        if (m13187a(FileDownloadHelper.m13229a())) {
            f15878a = i;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-step'.");
    }

    /* renamed from: a */
    public static void m13189a(long j) throws IllegalAccessException {
        if (m13187a(FileDownloadHelper.m13229a())) {
            f15879b = j;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-time'.");
    }

    /* renamed from: a */
    public static int m13195a() {
        return f15878a;
    }

    /* renamed from: b */
    public static long m13177b() {
        return f15879b;
    }

    /* renamed from: c */
    public static String m13171c() {
        if (!TextUtils.isEmpty(f15880c)) {
            return f15880c;
        }
        if (FileDownloadHelper.m13229a().getExternalCacheDir() == null) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return FileDownloadHelper.m13229a().getExternalCacheDir().getAbsolutePath();
    }

    /* renamed from: b */
    public static String m13174b(String str) {
        return m13185a(m13171c(), m13168c(str));
    }

    /* renamed from: c */
    public static String m13168c(String str) {
        return m13158f(str);
    }

    /* renamed from: a */
    public static String m13185a(String str, String str2) {
        if (str2 == null) {
            throw new IllegalStateException("can't generate real path, the file name is null");
        } else if (str != null) {
            return m13182a("%s%s%s", str, File.separator, str2);
        } else {
            throw new IllegalStateException("can't generate real path, the directory is null");
        }
    }

    /* renamed from: d */
    public static void m13163d(String str) {
        f15880c = str;
    }

    /* renamed from: e */
    public static String m13160e(String str) {
        return m13182a("%s.temp", str);
    }

    /* renamed from: b */
    public static int m13173b(String str, String str2) {
        return CustomComponentHolder.m13415a().m13409b().mo13220a(str, str2, false);
    }

    /* renamed from: a */
    public static int m13184a(String str, String str2, boolean z) {
        return CustomComponentHolder.m13415a().m13409b().mo13220a(str, str2, z);
    }

    /* renamed from: f */
    public static String m13158f(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append(ResultTypeConstant.f7213z);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    /* renamed from: d */
    public static String m13165d() {
        return m13179a(true);
    }

    /* renamed from: a */
    public static String m13179a(boolean z) {
        return m13178a(new Throwable().getStackTrace(), z);
    }

    /* renamed from: a */
    public static String m13178a(StackTraceElement[] stackTraceElementArr, boolean z) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 4) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (stackTraceElementArr[i].getClassName().contains(afb.f15494b)) {
                sb.append("[");
                sb.append(stackTraceElementArr[i].getClassName().substring(28));
                sb.append(":");
                sb.append(stackTraceElementArr[i].getMethodName());
                if (z) {
                    sb.append("(");
                    sb.append(stackTraceElementArr[i].getLineNumber());
                    sb.append(")]");
                } else {
                    sb.append("]");
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m13187a(Context context) {
        Boolean bool = f15881d;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = false;
        if (!FileDownloadProperties.m13208a().f15860d) {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY);
            if (activityManager != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActivityManager.RunningAppProcessInfo next = it.next();
                        if (next.pid == myPid) {
                            z = next.processName.endsWith(":filedownloader");
                            break;
                        }
                    }
                } else {
                    FileDownloadLog.m13210d(FileDownloadUtils.class, "The running app process info list from ActivityManager is null or empty, maybe current App is not running.", new Object[0]);
                    return false;
                }
            } else {
                FileDownloadLog.m13210d(FileDownloadUtils.class, "fail to get the activity manager!", new Object[0]);
                return false;
            }
        } else {
            z = true;
        }
        f15881d = Boolean.valueOf(z);
        return f15881d.booleanValue();
    }

    /* renamed from: g */
    public static String[] m13157g(String str) {
        String[] split = str.split("\n");
        String[] strArr = new String[split.length * 2];
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(": ");
            int i2 = i * 2;
            strArr[i2] = split2[0];
            strArr[i2 + 1] = split2[1];
        }
        return strArr;
    }

    /* renamed from: h */
    public static long m13156h(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBytes();
        }
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    /* renamed from: a */
    public static String m13182a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    /* renamed from: b */
    public static void m13175b(Context context) {
        File d = m13164d(context);
        try {
            d.getParentFile().mkdirs();
            d.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public static boolean m13169c(Context context) {
        if (f15884g == null) {
            f15884g = Boolean.valueOf(m13164d(context).exists());
        }
        return f15884g.booleanValue();
    }

    /* renamed from: d */
    public static File m13164d(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separator + "filedownloader", f15883f);
    }

    /* renamed from: i */
    public static long m13155i(String str) {
        if (str == null) {
            return -1L;
        }
        String[] split = str.split("/");
        if (split.length >= 2) {
            try {
                return Long.parseLong(split[1]);
            } catch (NumberFormatException unused) {
                FileDownloadLog.m13210d(FileDownloadUtils.class, "parse instance length failed with %s", str);
            }
        }
        return -1L;
    }

    /* renamed from: j */
    public static String m13154j(String str) {
        Matcher matcher;
        if (str == null) {
            return null;
        }
        try {
            matcher = f15885h.matcher(str);
        } catch (IllegalStateException unused) {
        }
        if (matcher.find()) {
            return matcher.group(1);
        }
        Matcher matcher2 = f15886i.matcher(str);
        if (matcher2.find()) {
            return matcher2.group(1);
        }
        return null;
    }

    /* renamed from: a */
    public static String m13183a(String str, boolean z, String str2) {
        if (str == null) {
            return null;
        }
        if (!z) {
            return str;
        }
        if (str2 == null) {
            return null;
        }
        return m13185a(str, str2);
    }

    /* renamed from: k */
    public static String m13153k(String str) {
        int length = str.length();
        int i = 2;
        int i2 = (File.separatorChar == '\\' && length > 2 && str.charAt(1) == ':') ? 2 : 0;
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf != -1 || i2 <= 0) {
            i = lastIndexOf;
        }
        if (i == -1 || str.charAt(length - 1) == File.separatorChar) {
            return null;
        }
        if (str.indexOf(File.separatorChar) == i && str.charAt(i2) == File.separatorChar) {
            return str.substring(0, i + 1);
        }
        return str.substring(0, i);
    }

    /* renamed from: l */
    public static String m13152l(String str) {
        return "FileDownloader-" + str;
    }

    /* renamed from: e */
    public static boolean m13161e() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FileDownloadHelper.m13229a().getSystemService("connectivity");
        if (connectivityManager == null) {
            FileDownloadLog.m13210d(FileDownloadUtils.class, "failed to get connectivity manager!", new Object[0]);
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || activeNetworkInfo.getType() != 1;
    }

    /* renamed from: m */
    public static boolean m13151m(String str) {
        return FileDownloadHelper.m13229a().checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: n */
    public static long m13150n(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    /* renamed from: a */
    public static String m13190a(int i, FileDownloadConnection agiVar) {
        if (agiVar != null) {
            String a = agiVar.mo13498a("Etag");
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(FileDownloadUtils.class, "etag find %s for task(%d)", a, Integer.valueOf(i));
            }
            return a;
        }
        throw new RuntimeException("connection is null when findEtag");
    }

    /* renamed from: b */
    public static boolean m13176b(int i, FileDownloadConnection agiVar) {
        if (i == 206 || i == 1) {
            return true;
        }
        return "bytes".equals(agiVar.mo13498a("Accept-Ranges"));
    }

    /* renamed from: a */
    public static long m13181a(FileDownloadConnection agiVar) {
        long b = m13172b(agiVar);
        if (b < 0) {
            FileDownloadLog.m13210d(FileDownloadUtils.class, "don't get instance length fromContent-Range header", new Object[0]);
            b = -1;
        }
        if (b != 0 || !FileDownloadProperties.m13208a().f15864h) {
            return b;
        }
        return -1L;
    }

    /* renamed from: b */
    public static long m13172b(FileDownloadConnection agiVar) {
        return m13155i(m13162d(agiVar));
    }

    /* renamed from: d */
    private static String m13162d(FileDownloadConnection agiVar) {
        return agiVar.mo13498a("Content-Range");
    }

    /* renamed from: c */
    public static long m13170c(int i, FileDownloadConnection agiVar) {
        long n = m13150n(agiVar.mo13498a("Content-Length"));
        String a = agiVar.mo13498a(HTTP.TRANSFER_ENCODING);
        if (n >= 0) {
            return n;
        }
        if (a != null && a.equals(HTTP.CHUNK_CODING)) {
            return -1L;
        }
        if (!FileDownloadProperties.m13208a().f15859c) {
            throw new FileDownloadGiveUpRetryException("can't know the size of the download file, and its Transfer-Encoding is not Chunked either.\nyou can ignore such exception by add http.lenient=true to the filedownloader.properties");
        } else if (!FileDownloadLog.f15845a) {
            return -1L;
        } else {
            FileDownloadLog.m13211c(FileDownloadUtils.class, "%d response header is not legal but HTTP lenient is true, so handle as the case of transfer encoding chunk", Integer.valueOf(i));
            return -1L;
        }
    }

    /* renamed from: c */
    public static long m13166c(FileDownloadConnection agiVar) {
        long o = m13149o(m13162d(agiVar));
        if (o < 0) {
            return -1L;
        }
        return o;
    }

    /* renamed from: o */
    public static long m13149o(String str) {
        if (str == null || str.length() == 0) {
            return -1L;
        }
        try {
            Matcher matcher = Pattern.compile("bytes (\\d+)-(\\d+)/\\d+").matcher(str);
            if (matcher.find()) {
                return (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            }
        } catch (Exception e) {
            FileDownloadLog.m13213a(FileDownloadUtils.class, e, "parse content length from content range error", new Object[0]);
        }
        return -1L;
    }

    /* renamed from: a */
    public static String m13180a(FileDownloadConnection agiVar, String str) throws FileDownloadSecurityException {
        String j = m13154j(agiVar.mo13498a(MIME.CONTENT_DISPOSITION));
        if (TextUtils.isEmpty(j)) {
            return m13168c(str);
        }
        if (!j.contains("../")) {
            return j;
        }
        throw new FileDownloadSecurityException(m13182a("The filename [%s] from the response is not allowable, because it contains '../', which can raise the directory traversal vulnerability", j));
    }

    /* renamed from: p */
    public static FileDownloadOutputStream m13148p(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        } else if (m13186a(str)) {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                throw new RuntimeException(m13182a("found invalid internal destination path[%s], & path is directory[%B]", str, Boolean.valueOf(file.isDirectory())));
            } else if (file.exists() || file.createNewFile()) {
                return CustomComponentHolder.m13415a().m13412a(file);
            } else {
                throw new IOException(m13182a("create new file error  %s", file.getAbsolutePath()));
            }
        } else {
            throw new RuntimeException(m13182a("found invalid internal destination filename %s", str));
        }
    }

    /* renamed from: a */
    public static boolean m13193a(int i, FileDownloadModel fileDownloadModel) {
        return m13192a(i, fileDownloadModel, (Boolean) null);
    }

    /* renamed from: a */
    public static boolean m13192a(int i, FileDownloadModel fileDownloadModel, Boolean bool) {
        if (fileDownloadModel == null) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d model == null", Integer.valueOf(i));
            }
            return false;
        } else if (fileDownloadModel.m19120e() != null) {
            return m13191a(i, fileDownloadModel, fileDownloadModel.m19120e(), bool);
        } else {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d temp path == null", Integer.valueOf(i));
            }
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m13191a(int i, FileDownloadModel fileDownloadModel, String str, Boolean bool) {
        if (str != null) {
            File file = new File(str);
            boolean exists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (exists && !isDirectory) {
                long length = file.length();
                long g = fileDownloadModel.m19118g();
                if (fileDownloadModel.m19111n() > 1 || g != 0) {
                    long h = fileDownloadModel.m19117h();
                    if (length < g || (h != -1 && (length > h || g >= h))) {
                        if (!FileDownloadLog.f15845a) {
                            return false;
                        }
                        FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d dirty data fileLength[%d] sofar[%d] total[%d]", Integer.valueOf(i), Long.valueOf(length), Long.valueOf(g), Long.valueOf(h));
                        return false;
                    } else if (bool == null || bool.booleanValue() || h != length) {
                        return true;
                    } else {
                        if (!FileDownloadLog.f15845a) {
                            return false;
                        }
                        FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d, because of the output stream doesn't support seek, but the task has already pre-allocated, so we only can download it from the very beginning.", Integer.valueOf(i));
                        return false;
                    }
                } else if (!FileDownloadLog.f15845a) {
                    return false;
                } else {
                    FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d the downloaded-record is zero.", Integer.valueOf(i));
                    return false;
                }
            } else if (!FileDownloadLog.f15845a) {
                return false;
            } else {
                FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d file not suit, exists[%B], directory[%B]", Integer.valueOf(i), Boolean.valueOf(exists), Boolean.valueOf(isDirectory));
                return false;
            }
        } else if (!FileDownloadLog.f15845a) {
            return false;
        } else {
            FileDownloadLog.m13211c(FileDownloadUtils.class, "can't continue %d path = null", Integer.valueOf(i));
            return false;
        }
    }

    /* renamed from: c */
    public static void m13167c(String str, String str2) {
        m13147q(str2);
        m13146r(str);
    }

    /* renamed from: q */
    public static void m13147q(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* renamed from: r */
    public static void m13146r(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* renamed from: a */
    public static boolean m13188a(long j, long j2) {
        return j > ((long) m13195a()) && j2 > m13177b();
    }

    /* renamed from: f */
    public static String m13159f() {
        return m13182a("FileDownloader/%s", afb.f15498f);
    }
}
