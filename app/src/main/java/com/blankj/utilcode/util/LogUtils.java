package com.blankj.utilcode.util;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresApi;
import android.support.p003v4.util.SimpleArrayMap;
import android.util.Log;
import com.lody.virtual.server.p063pm.installer.PackageHelper;
import com.tencent.smtt.sdk.TbsListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C4963cj;
import p110z1.Gson;
import p110z1.GsonBuilder;
import p110z1.SimpleComparison;

/* renamed from: com.blankj.utilcode.util.ae */
/* loaded from: classes.dex */
public final class LogUtils {

    /* renamed from: B */
    private static SimpleDateFormat f6638B = null;

    /* renamed from: a */
    public static final int f6641a = 2;

    /* renamed from: b */
    public static final int f6642b = 3;

    /* renamed from: c */
    public static final int f6643c = 4;

    /* renamed from: d */
    public static final int f6644d = 5;

    /* renamed from: e */
    public static final int f6645e = 6;

    /* renamed from: f */
    public static final int f6646f = 7;

    /* renamed from: h */
    private static final int f6648h = 16;

    /* renamed from: i */
    private static final int f6649i = 32;

    /* renamed from: j */
    private static final int f6650j = 48;

    /* renamed from: m */
    private static final String f6653m = "┌";

    /* renamed from: n */
    private static final String f6654n = "├";

    /* renamed from: o */
    private static final String f6655o = "│ ";

    /* renamed from: p */
    private static final String f6656p = "└";

    /* renamed from: q */
    private static final String f6657q = "────────────────────────────────────────────────────────";

    /* renamed from: r */
    private static final String f6658r = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";

    /* renamed from: s */
    private static final String f6659s = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";

    /* renamed from: t */
    private static final String f6660t = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";

    /* renamed from: u */
    private static final String f6661u = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";

    /* renamed from: v */
    private static final int f6662v = 1100;

    /* renamed from: w */
    private static final String f6663w = "log nothing";

    /* renamed from: x */
    private static final String f6664x = "null";

    /* renamed from: y */
    private static final String f6665y = "args";

    /* renamed from: z */
    private static final String f6666z = " ";

    /* renamed from: g */
    private static final char[] f6647g = {'V', 'D', 'I', 'W', 'E', 'A'};

    /* renamed from: k */
    private static final String f6651k = System.getProperty("file.separator");

    /* renamed from: l */
    private static final String f6652l = System.getProperty("line.separator");

    /* renamed from: A */
    private static final C0964a f6637A = new C0964a();

    /* renamed from: C */
    private static final ExecutorService f6639C = Executors.newSingleThreadExecutor();

    /* renamed from: D */
    private static final SimpleArrayMap<Class, AbstractC0966c> f6640D = new SimpleArrayMap<>();

    /* compiled from: LogUtils.java */
    /* renamed from: com.blankj.utilcode.util.ae$b */
    /* loaded from: classes.dex */
    public interface AbstractC0965b {
        /* renamed from: a */
        void m23677a(String str, String str2);
    }

    /* compiled from: LogUtils.java */
    /* renamed from: com.blankj.utilcode.util.ae$c */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0966c<T> {
        /* renamed from: a */
        public abstract String m23676a(T t);
    }

    /* compiled from: LogUtils.java */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.blankj.utilcode.util.ae$e */
    /* loaded from: classes.dex */
    public @interface AbstractC0968e {
    }

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static C0964a m23772a() {
        return f6637A;
    }

    /* renamed from: a */
    public static void m23754a(Object... objArr) {
        m23766a(2, f6637A.m23687g(), objArr);
    }

    /* renamed from: a */
    public static void m23755a(String str, Object... objArr) {
        m23766a(2, str, objArr);
    }

    /* renamed from: b */
    public static void m23742b(Object... objArr) {
        m23766a(3, f6637A.m23687g(), objArr);
    }

    /* renamed from: b */
    public static void m23743b(String str, Object... objArr) {
        m23766a(3, str, objArr);
    }

    /* renamed from: c */
    public static void m23734c(Object... objArr) {
        m23766a(4, f6637A.m23687g(), objArr);
    }

    /* renamed from: c */
    public static void m23735c(String str, Object... objArr) {
        m23766a(4, str, objArr);
    }

    /* renamed from: d */
    public static void m23727d(Object... objArr) {
        m23766a(5, f6637A.m23687g(), objArr);
    }

    /* renamed from: d */
    public static void m23728d(String str, Object... objArr) {
        m23766a(5, str, objArr);
    }

    /* renamed from: e */
    public static void m23720e(Object... objArr) {
        m23766a(6, f6637A.m23687g(), objArr);
    }

    /* renamed from: e */
    public static void m23721e(String str, Object... objArr) {
        m23766a(6, str, objArr);
    }

    /* renamed from: f */
    public static void m23716f(Object... objArr) {
        m23766a(7, f6637A.m23687g(), objArr);
    }

    /* renamed from: f */
    public static void m23717f(String str, Object... objArr) {
        m23766a(7, str, objArr);
    }

    /* renamed from: a */
    public static void m23760a(Object obj) {
        m23766a(19, f6637A.m23687g(), obj);
    }

    /* renamed from: a */
    public static void m23771a(int i, Object obj) {
        m23766a(i | 16, f6637A.m23687g(), obj);
    }

    /* renamed from: a */
    public static void m23757a(String str, Object obj) {
        m23766a(19, str, obj);
    }

    /* renamed from: a */
    public static void m23769a(int i, String str, Object obj) {
        m23766a(i | 16, str, obj);
    }

    /* renamed from: b */
    public static void m23747b(Object obj) {
        m23766a(35, f6637A.m23687g(), obj);
    }

    /* renamed from: b */
    public static void m23752b(int i, Object obj) {
        m23766a(i | 32, f6637A.m23687g(), obj);
    }

    /* renamed from: b */
    public static void m23745b(String str, Object obj) {
        m23766a(35, str, obj);
    }

    /* renamed from: b */
    public static void m23751b(int i, String str, Object obj) {
        m23766a(i | 32, str, obj);
    }

    /* renamed from: a */
    public static void m23758a(String str) {
        m23766a(51, f6637A.m23687g(), str);
    }

    /* renamed from: a */
    public static void m23770a(int i, String str) {
        m23766a(i | 48, f6637A.m23687g(), str);
    }

    /* renamed from: a */
    public static void m23756a(String str, String str2) {
        m23766a(51, str, str2);
    }

    /* renamed from: a */
    public static void m23768a(int i, String str, String str2) {
        m23766a(i | 48, str, str2);
    }

    /* renamed from: a */
    public static void m23766a(int i, String str, Object... objArr) {
        if (f6637A.m23692e()) {
            final int i2 = i & 15;
            int i3 = i & TbsListener.ErrorCode.TPATCH_VERSION_FAILED;
            if (!f6637A.m23689f() && !f6637A.m23685i() && i3 != 16) {
                return;
            }
            if (i2 >= f6637A.f6682l || i2 >= f6637A.f6683m) {
                final C0969f c = m23737c(str);
                final String a = m23763a(i3, objArr);
                if (f6637A.m23689f() && i3 != 16 && i2 >= f6637A.f6682l) {
                    m23764a(i2, c.f6690a, c.f6691b, a);
                }
                if ((f6637A.m23685i() || i3 == 16) && i2 >= f6637A.f6683m) {
                    f6639C.execute(new Runnable() { // from class: com.blankj.utilcode.util.ae.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int i4 = i2;
                            String str2 = c.f6690a;
                            LogUtils.m23718f(i4, str2, c.f6692c + a);
                        }
                    });
                }
            }
        }
    }

    /* renamed from: c */
    private static C0969f m23737c(String str) {
        String str2;
        String str3;
        String name;
        if (f6637A.f6677g || f6637A.m23686h()) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int o = f6637A.m23679o() + 3;
            if (o >= stackTrace.length) {
                String a = m23759a(stackTrace[3]);
                if (!f6637A.f6677g || !m23723e(str)) {
                    a = str;
                } else {
                    int indexOf = a.indexOf(46);
                    if (indexOf != -1) {
                        a = a.substring(0, indexOf);
                    }
                }
                return new C0969f(a, null, ": ");
            }
            StackTraceElement stackTraceElement = stackTrace[o];
            String a2 = m23759a(stackTraceElement);
            if (!f6637A.f6677g || !m23723e(str)) {
                str3 = str;
            } else {
                int indexOf2 = a2.indexOf(46);
                str3 = indexOf2 == -1 ? a2 : a2.substring(0, indexOf2);
            }
            if (f6637A.m23686h()) {
                String formatter = new Formatter().format("%s, %s.%s(%s:%d)", Thread.currentThread().getName(), stackTraceElement.getClassName(), stackTraceElement.getMethodName(), a2, Integer.valueOf(stackTraceElement.getLineNumber())).toString();
                String str4 = " [" + formatter + "]: ";
                if (f6637A.m23680n() <= 1) {
                    return new C0969f(str3, new String[]{formatter}, str4);
                }
                String[] strArr = new String[Math.min(f6637A.m23680n(), stackTrace.length - o)];
                strArr[0] = formatter;
                String formatter2 = new Formatter().format("%" + (name.length() + 2) + "s", "").toString();
                int length = strArr.length;
                for (int i = 1; i < length; i++) {
                    StackTraceElement stackTraceElement2 = stackTrace[i + o];
                    strArr[i] = new Formatter().format("%s%s.%s(%s:%d)", formatter2, stackTraceElement2.getClassName(), stackTraceElement2.getMethodName(), m23759a(stackTraceElement2), Integer.valueOf(stackTraceElement2.getLineNumber())).toString();
                }
                return new C0969f(str3, strArr, str4);
            }
            str2 = str3;
        } else {
            str2 = f6637A.m23687g();
        }
        return new C0969f(str2, null, ": ");
    }

    /* renamed from: a */
    private static String m23759a(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        if (fileName != null) {
            return fileName;
        }
        String className = stackTraceElement.getClassName();
        String[] split = className.split("\\.");
        if (split.length > 0) {
            className = split[split.length - 1];
        }
        int indexOf = className.indexOf(36);
        if (indexOf != -1) {
            className = className.substring(0, indexOf);
        }
        return className + ".java";
    }

    /* renamed from: a */
    private static String m23763a(int i, Object... objArr) {
        String str = f6664x;
        if (objArr != null) {
            if (objArr.length == 1) {
                str = m23740c(i, objArr[0]);
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj = objArr[i2];
                    sb.append(f6665y);
                    sb.append("[");
                    sb.append(i2);
                    sb.append("]");
                    sb.append(" = ");
                    sb.append(m23731d(obj));
                    sb.append(f6652l);
                }
                str = sb.toString();
            }
        }
        return str.length() == 0 ? f6663w : str;
    }

    /* renamed from: c */
    private static String m23740c(int i, Object obj) {
        if (obj == null) {
            return f6664x;
        }
        if (i == 32) {
            return C0967d.m23671a(obj, 32);
        }
        if (i == 48) {
            return C0967d.m23671a(obj, 48);
        }
        return m23731d(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static String m23731d(Object obj) {
        AbstractC0966c cVar;
        if (obj == null) {
            return f6664x;
        }
        if (f6640D.isEmpty() || (cVar = f6640D.get(m23724e(obj))) == null) {
            return C0967d.m23672a(obj);
        }
        return cVar.m23676a(obj);
    }

    /* renamed from: a */
    private static void m23764a(int i, String str, String[] strArr, String str2) {
        if (f6637A.m23683k()) {
            m23725e(i, str, m23749b(i, str, strArr, str2));
            return;
        }
        m23767a(i, str, true);
        m23765a(i, str, strArr);
        m23739c(i, str, str2);
        m23767a(i, str, false);
    }

    /* renamed from: a */
    private static void m23767a(int i, String str, boolean z) {
        if (f6637A.m23684j()) {
            Log.println(i, str, z ? f6659s : f6661u);
        }
    }

    /* renamed from: a */
    private static void m23765a(int i, String str, String[] strArr) {
        if (strArr != null) {
            for (String str2 : strArr) {
                if (f6637A.m23684j()) {
                    str2 = f6655o + str2;
                }
                Log.println(i, str, str2);
            }
            if (f6637A.m23684j()) {
                Log.println(i, str, f6660t);
            }
        }
    }

    /* renamed from: c */
    private static void m23739c(int i, String str, String str2) {
        int length = str2.length();
        int i2 = length / f6662v;
        if (i2 > 0) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i4 + f6662v;
                m23732d(i, str, str2.substring(i4, i5));
                i3++;
                i4 = i5;
            }
            if (i4 != length) {
                m23732d(i, str, str2.substring(i4, length));
                return;
            }
            return;
        }
        m23732d(i, str, str2);
    }

    /* renamed from: d */
    private static void m23732d(int i, String str, String str2) {
        String[] split;
        if (!f6637A.m23684j()) {
            Log.println(i, str, str2);
            return;
        }
        new StringBuilder();
        for (String str3 : str2.split(f6652l)) {
            Log.println(i, str, f6655o + str3);
        }
    }

    /* renamed from: b */
    private static String m23749b(int i, String str, String[] strArr, String str2) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (f6637A.m23684j()) {
            sb.append(" ");
            sb.append(f6652l);
            sb.append(f6659s);
            sb.append(f6652l);
            if (strArr != null) {
                for (String str3 : strArr) {
                    sb.append(f6655o);
                    sb.append(str3);
                    sb.append(f6652l);
                }
                sb.append(f6660t);
                sb.append(f6652l);
            }
            String[] split = str2.split(f6652l);
            int length = split.length;
            while (i2 < length) {
                String str4 = split[i2];
                sb.append(f6655o);
                sb.append(str4);
                sb.append(f6652l);
                i2++;
            }
            sb.append(f6661u);
        } else {
            if (strArr != null) {
                sb.append(" ");
                sb.append(f6652l);
                int length2 = strArr.length;
                while (i2 < length2) {
                    sb.append(strArr[i2]);
                    sb.append(f6652l);
                    i2++;
                }
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    /* renamed from: e */
    private static void m23725e(int i, String str, String str2) {
        int length = str2.length();
        boolean j = f6637A.m23684j();
        int i2 = f6662v;
        int i3 = j ? (length + PackageHelper.INSTALL_FAILED_NO_MATCHING_ABIS) / f6662v : length / f6662v;
        if (i3 > 0) {
            int i4 = 1;
            if (f6637A.m23684j()) {
                Log.println(i, str, str2.substring(0, f6662v) + f6652l + f6661u);
                while (i4 < i3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" ");
                    sb.append(f6652l);
                    sb.append(f6659s);
                    sb.append(f6652l);
                    sb.append(f6655o);
                    int i5 = i2 + f6662v;
                    sb.append(str2.substring(i2, i5));
                    sb.append(f6652l);
                    sb.append(f6661u);
                    Log.println(i, str, sb.toString());
                    i4++;
                    i2 = i5;
                }
                if (i2 != length + PackageHelper.INSTALL_FAILED_NO_MATCHING_ABIS) {
                    Log.println(i, str, " " + f6652l + f6659s + f6652l + f6655o + str2.substring(i2, length));
                    return;
                }
                return;
            }
            Log.println(i, str, str2.substring(0, f6662v));
            while (i4 < i3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(" ");
                sb2.append(f6652l);
                int i6 = i2 + f6662v;
                sb2.append(str2.substring(i2, i6));
                Log.println(i, str, sb2.toString());
                i4++;
                i2 = i6;
            }
            if (i2 != length) {
                Log.println(i, str, " " + f6652l + str2.substring(i2, length));
                return;
            }
            return;
        }
        Log.println(i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static void m23718f(int i, String str, String str2) {
        String format = m23715g().format(new Date());
        String substring = format.substring(0, 10);
        String substring2 = format.substring(11);
        String str3 = f6637A.m23701c() + f6637A.m23696d() + "_" + substring + "_" + f6637A.m23714a() + ".txt";
        if (!m23744b(str3, substring)) {
            Log.e("LogUtils", "create " + str3 + " failed!");
            return;
        }
        m23722e(substring2 + f6647g[i - 2] + "/" + str + str2 + f6652l, str3);
    }

    /* renamed from: g */
    private static SimpleDateFormat m23715g() {
        if (f6638B == null) {
            f6638B = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss.SSS ", Locale.getDefault());
        }
        return f6638B;
    }

    /* renamed from: b */
    private static boolean m23744b(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!m23761a(file.getParentFile())) {
            return false;
        }
        try {
            m23736c(str, str2);
            boolean createNewFile = file.createNewFile();
            if (createNewFile) {
                m23729d(str, str2);
            }
            return createNewFile;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    private static void m23736c(String str, String str2) {
        File[] listFiles;
        if (f6637A.m23678p() > 0 && (listFiles = new File(str).getParentFile().listFiles(new FilenameFilter() { // from class: com.blankj.utilcode.util.ae.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str3) {
                return str3.matches("^" + LogUtils.f6637A.m23696d() + "_[0-9]{4}_[0-9]{2}_[0-9]{2}_.*$");
            }
        })) != null && listFiles.length > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
            try {
                long time = simpleDateFormat.parse(str2).getTime() - (f6637A.m23678p() * WaitFor.ONE_DAY);
                for (final File file : listFiles) {
                    String name = file.getName();
                    name.length();
                    if (simpleDateFormat.parse(m23730d(name)).getTime() <= time) {
                        f6639C.execute(new Runnable() { // from class: com.blankj.utilcode.util.ae.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (!file.delete()) {
                                    Log.e("LogUtils", "delete " + file + " failed!");
                                }
                            }
                        });
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    private static String m23730d(String str) {
        Matcher matcher = Pattern.compile("[0-9]{4}_[0-9]{2}_[0-9]{2}").matcher(str);
        return matcher.find() ? matcher.group() : "";
    }

    /* renamed from: d */
    private static void m23729d(String str, String str2) {
        String str3 = "";
        int i = 0;
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(Utils.m24103a().getPackageName(), 0);
            if (packageInfo != null) {
                str3 = packageInfo.versionName;
                i = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        m23722e("************* Log Head ****************\nDate of Log        : " + str2 + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + str3 + "\nApp VersionCode    : " + i + "\n************* Log Head ****************\n\n", str);
    }

    /* renamed from: a */
    private static boolean m23761a(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static boolean m23723e(String str) {
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

    /* renamed from: e */
    private static void m23722e(String str, String str2) {
        Throwable th;
        IOException e;
        BufferedWriter bufferedWriter;
        if (f6637A.f6688r == null) {
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(str2, true));
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    bufferedWriter.write(str);
                    bufferedWriter.close();
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter2 = bufferedWriter;
                    e.printStackTrace();
                    Log.e("LogUtils", "log to " + str2 + " failed!");
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } else {
            f6637A.f6688r.m23677a(str2, str);
        }
    }

    /* compiled from: LogUtils.java */
    /* renamed from: com.blankj.utilcode.util.ae$a */
    /* loaded from: classes.dex */
    public static final class C0964a {

        /* renamed from: a */
        private String f6671a;

        /* renamed from: b */
        private String f6672b;

        /* renamed from: c */
        private String f6673c;

        /* renamed from: d */
        private boolean f6674d;

        /* renamed from: e */
        private boolean f6675e;

        /* renamed from: f */
        private String f6676f;

        /* renamed from: g */
        private boolean f6677g;

        /* renamed from: h */
        private boolean f6678h;

        /* renamed from: i */
        private boolean f6679i;

        /* renamed from: j */
        private boolean f6680j;

        /* renamed from: k */
        private boolean f6681k;

        /* renamed from: l */
        private int f6682l;

        /* renamed from: m */
        private int f6683m;

        /* renamed from: n */
        private int f6684n;

        /* renamed from: o */
        private int f6685o;

        /* renamed from: p */
        private int f6686p;

        /* renamed from: q */
        private String f6687q;

        /* renamed from: r */
        private AbstractC0965b f6688r;

        private C0964a() {
            this.f6673c = "util";
            this.f6674d = true;
            this.f6675e = true;
            this.f6676f = "";
            this.f6677g = true;
            this.f6678h = true;
            this.f6679i = false;
            this.f6680j = true;
            this.f6681k = true;
            this.f6682l = 2;
            this.f6683m = 2;
            this.f6684n = 1;
            this.f6685o = 0;
            this.f6686p = -1;
            this.f6687q = Utils.m24093f();
            if (this.f6671a == null) {
                if (!"mounted".equals(Environment.getExternalStorageState()) || Utils.m24103a().getExternalCacheDir() == null) {
                    this.f6671a = Utils.m24103a().getCacheDir() + LogUtils.f6651k + "log" + LogUtils.f6651k;
                    return;
                }
                this.f6671a = Utils.m24103a().getExternalCacheDir() + LogUtils.f6651k + "log" + LogUtils.f6651k;
            }
        }

        /* renamed from: a */
        public final C0964a m23707a(boolean z) {
            this.f6674d = z;
            return this;
        }

        /* renamed from: b */
        public final C0964a m23702b(boolean z) {
            this.f6675e = z;
            return this;
        }

        /* renamed from: a */
        public final C0964a m23708a(String str) {
            if (LogUtils.m23723e(str)) {
                this.f6676f = "";
                this.f6677g = true;
            } else {
                this.f6676f = str;
                this.f6677g = false;
            }
            return this;
        }

        /* renamed from: c */
        public final C0964a m23697c(boolean z) {
            this.f6678h = z;
            return this;
        }

        /* renamed from: d */
        public final C0964a m23693d(boolean z) {
            this.f6679i = z;
            return this;
        }

        /* renamed from: b */
        public final C0964a m23703b(String str) {
            if (LogUtils.m23723e(str)) {
                this.f6672b = null;
            } else {
                if (!str.endsWith(LogUtils.f6651k)) {
                    str = str + LogUtils.f6651k;
                }
                this.f6672b = str;
            }
            return this;
        }

        /* renamed from: a */
        public final C0964a m23709a(File file) {
            String str;
            if (file == null) {
                str = null;
            } else {
                str = file.getAbsolutePath() + LogUtils.f6651k;
            }
            this.f6672b = str;
            return this;
        }

        /* renamed from: c */
        public final C0964a m23698c(String str) {
            if (LogUtils.m23723e(str)) {
                this.f6673c = "util";
            } else {
                this.f6673c = str;
            }
            return this;
        }

        /* renamed from: e */
        public final C0964a m23690e(boolean z) {
            this.f6680j = z;
            return this;
        }

        /* renamed from: f */
        public final C0964a m23688f(boolean z) {
            this.f6681k = z;
            return this;
        }

        /* renamed from: a */
        public final C0964a m23713a(int i) {
            this.f6682l = i;
            return this;
        }

        /* renamed from: b */
        public final C0964a m23705b(int i) {
            this.f6683m = i;
            return this;
        }

        /* renamed from: c */
        public final C0964a m23700c(@IntRange(from = 1) int i) {
            this.f6684n = i;
            return this;
        }

        /* renamed from: d */
        public final C0964a m23695d(@IntRange(from = 0) int i) {
            this.f6685o = i;
            return this;
        }

        /* renamed from: e */
        public final C0964a m23691e(@IntRange(from = 1) int i) {
            this.f6686p = i;
            return this;
        }

        /* renamed from: a */
        public final <T> C0964a m23710a(AbstractC0966c<T> cVar) {
            if (cVar != null) {
                LogUtils.f6640D.put(LogUtils.m23748b((AbstractC0966c) cVar), cVar);
            }
            return this;
        }

        /* renamed from: a */
        public final C0964a m23711a(AbstractC0965b bVar) {
            this.f6688r = bVar;
            return this;
        }

        /* renamed from: a */
        public final String m23714a() {
            String str = this.f6687q;
            return str == null ? "" : str.replace(":", "_");
        }

        /* renamed from: b */
        public final String m23706b() {
            return this.f6671a;
        }

        /* renamed from: c */
        public final String m23701c() {
            String str = this.f6672b;
            return str == null ? this.f6671a : str;
        }

        /* renamed from: d */
        public final String m23696d() {
            return this.f6673c;
        }

        /* renamed from: e */
        public final boolean m23692e() {
            return this.f6674d;
        }

        /* renamed from: f */
        public final boolean m23689f() {
            return this.f6675e;
        }

        /* renamed from: g */
        public final String m23687g() {
            return LogUtils.m23723e(this.f6676f) ? "" : this.f6676f;
        }

        /* renamed from: h */
        public final boolean m23686h() {
            return this.f6678h;
        }

        /* renamed from: i */
        public final boolean m23685i() {
            return this.f6679i;
        }

        /* renamed from: j */
        public final boolean m23684j() {
            return this.f6680j;
        }

        /* renamed from: k */
        public final boolean m23683k() {
            return this.f6681k;
        }

        /* renamed from: l */
        public final char m23682l() {
            return LogUtils.f6647g[this.f6682l - 2];
        }

        /* renamed from: m */
        public final char m23681m() {
            return LogUtils.f6647g[this.f6683m - 2];
        }

        /* renamed from: n */
        public final int m23680n() {
            return this.f6684n;
        }

        /* renamed from: o */
        public final int m23679o() {
            return this.f6685o;
        }

        /* renamed from: p */
        public final int m23678p() {
            return this.f6686p;
        }

        public String toString() {
            return "process: " + m23714a() + LogUtils.f6652l + "switch: " + m23692e() + LogUtils.f6652l + "console: " + m23689f() + LogUtils.f6652l + "tag: " + m23687g() + LogUtils.f6652l + "head: " + m23686h() + LogUtils.f6652l + "file: " + m23685i() + LogUtils.f6652l + "dir: " + m23701c() + LogUtils.f6652l + "filePrefix: " + m23696d() + LogUtils.f6652l + "border: " + m23684j() + LogUtils.f6652l + "singleTag: " + m23683k() + LogUtils.f6652l + "consoleFilter: " + m23682l() + LogUtils.f6652l + "fileFilter: " + m23681m() + LogUtils.f6652l + "stackDeep: " + m23680n() + LogUtils.f6652l + "stackOffset: " + m23679o() + LogUtils.f6652l + "saveDays: " + m23678p() + LogUtils.f6652l + "formatter: " + LogUtils.f6640D;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LogUtils.java */
    /* renamed from: com.blankj.utilcode.util.ae$f */
    /* loaded from: classes.dex */
    public static final class C0969f {

        /* renamed from: a */
        String f6690a;

        /* renamed from: b */
        String[] f6691b;

        /* renamed from: c */
        String f6692c;

        C0969f(String str, String[] strArr, String str2) {
            this.f6690a = str;
            this.f6691b = strArr;
            this.f6692c = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LogUtils.java */
    /* renamed from: com.blankj.utilcode.util.ae$d */
    /* loaded from: classes.dex */
    public static final class C0967d {

        /* renamed from: a */
        private static final Gson f6689a = new GsonBuilder().m1537f().m1540c().m1533j();

        private C0967d() {
        }

        /* renamed from: a */
        static String m23672a(Object obj) {
            return m23671a(obj, -1);
        }

        /* renamed from: a */
        static String m23671a(Object obj, int i) {
            if (obj.getClass().isArray()) {
                return m23666c(obj);
            }
            if (obj instanceof Throwable) {
                return m23669a((Throwable) obj);
            }
            if (obj instanceof Bundle) {
                return m23673a((Bundle) obj);
            }
            if (obj instanceof Intent) {
                return m23674a((Intent) obj);
            }
            if (i == 32) {
                return m23668b(obj);
            }
            if (i == 48) {
                return m23667b(obj.toString());
            }
            return obj.toString();
        }

        /* renamed from: a */
        private static String m23669a(Throwable th) {
            return ThrowableUtils.m23131a(th);
        }

        /* renamed from: a */
        private static String m23673a(Bundle bundle) {
            Iterator<String> it = bundle.keySet().iterator();
            if (!it.hasNext()) {
                return "Bundle {}";
            }
            StringBuilder sb = new StringBuilder(128);
            sb.append("Bundle { ");
            while (true) {
                String next = it.next();
                Object obj = bundle.get(next);
                sb.append(next);
                sb.append('=');
                if (obj instanceof Bundle) {
                    sb.append(obj == bundle ? "(this Bundle)" : m23673a((Bundle) obj));
                } else {
                    sb.append(LogUtils.m23731d(obj));
                }
                if (!it.hasNext()) {
                    sb.append(" }");
                    return sb.toString();
                }
                sb.append(',');
                sb.append(' ');
            }
        }

        /* renamed from: a */
        private static String m23674a(Intent intent) {
            boolean z;
            Intent selector;
            ClipData clipData;
            StringBuilder sb = new StringBuilder(128);
            sb.append("Intent { ");
            String action = intent.getAction();
            boolean z2 = true;
            if (action != null) {
                sb.append("act=");
                sb.append(action);
                z = false;
            } else {
                z = true;
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cat=[");
                for (String str : categories) {
                    if (!z2) {
                        sb.append(',');
                    }
                    sb.append(str);
                    z2 = false;
                }
                sb.append("]");
                z = false;
            }
            Uri data = intent.getData();
            if (data != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("dat=");
                sb.append(data);
                z = false;
            }
            String type = intent.getType();
            if (type != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("typ=");
                sb.append(type);
                z = false;
            }
            int flags = intent.getFlags();
            if (flags != 0) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("flg=0x");
                sb.append(Integer.toHexString(flags));
                z = false;
            }
            String str2 = intent.getPackage();
            if (str2 != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("pkg=");
                sb.append(str2);
                z = false;
            }
            ComponentName component = intent.getComponent();
            if (component != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cmp=");
                sb.append(component.flattenToShortString());
                z = false;
            }
            Rect sourceBounds = intent.getSourceBounds();
            if (sourceBounds != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("bnds=");
                sb.append(sourceBounds.toShortString());
                z = false;
            }
            if (Build.VERSION.SDK_INT >= 16 && (clipData = intent.getClipData()) != null) {
                if (!z) {
                    sb.append(' ');
                }
                m23675a(clipData, sb);
                z = false;
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("extras={");
                sb.append(m23673a(extras));
                sb.append('}');
                z = false;
            }
            if (Build.VERSION.SDK_INT >= 15 && (selector = intent.getSelector()) != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("sel={");
                sb.append(selector == intent ? "(this Intent)" : m23674a(selector));
                sb.append(C4963cj.f20747d);
            }
            sb.append(" }");
            return sb.toString();
        }

        @RequiresApi(api = 16)
        /* renamed from: a */
        private static void m23675a(ClipData clipData, StringBuilder sb) {
            ClipData.Item itemAt = clipData.getItemAt(0);
            if (itemAt == null) {
                sb.append("ClipData.Item {}");
                return;
            }
            sb.append("ClipData.Item { ");
            String htmlText = itemAt.getHtmlText();
            if (htmlText != null) {
                sb.append("H:");
                sb.append(htmlText);
                sb.append(C4963cj.f20747d);
                return;
            }
            CharSequence text = itemAt.getText();
            if (text != null) {
                sb.append("T:");
                sb.append(text);
                sb.append(C4963cj.f20747d);
                return;
            }
            Uri uri = itemAt.getUri();
            if (uri != null) {
                sb.append("U:");
                sb.append(uri);
                sb.append(C4963cj.f20747d);
                return;
            }
            Intent intent = itemAt.getIntent();
            if (intent != null) {
                sb.append("I:");
                sb.append(m23674a(intent));
                sb.append(C4963cj.f20747d);
                return;
            }
            sb.append("NULL");
            sb.append(C4963cj.f20747d);
        }

        /* renamed from: b */
        private static String m23668b(Object obj) {
            if (obj instanceof CharSequence) {
                return m23670a(obj.toString());
            }
            try {
                return f6689a.m1575b(obj);
            } catch (Throwable unused) {
                return obj.toString();
            }
        }

        /* renamed from: a */
        private static String m23670a(String str) {
            try {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt == '{') {
                        return new JSONObject(str).toString(2);
                    }
                    if (charAt == '[') {
                        return new JSONArray(str).toString(2);
                    }
                    if (!Character.isWhitespace(charAt)) {
                        return str;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return str;
        }

        /* renamed from: b */
        private static String m23667b(String str) {
            try {
                StreamSource streamSource = new StreamSource(new StringReader(str));
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                newTransformer.transform(streamSource, streamResult);
                String obj = streamResult.getWriter().toString();
                return obj.replaceFirst(SimpleComparison.f23610d, SimpleComparison.f23610d + LogUtils.f6652l);
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        }

        /* renamed from: c */
        private static String m23666c(Object obj) {
            if (obj instanceof Object[]) {
                return Arrays.deepToString((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return Arrays.toString((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return Arrays.toString((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return Arrays.toString((char[]) obj);
            }
            if (obj instanceof double[]) {
                return Arrays.toString((double[]) obj);
            }
            if (obj instanceof float[]) {
                return Arrays.toString((float[]) obj);
            }
            if (obj instanceof int[]) {
                return Arrays.toString((int[]) obj);
            }
            if (obj instanceof long[]) {
                return Arrays.toString((long[]) obj);
            }
            if (obj instanceof short[]) {
                return Arrays.toString((short[]) obj);
            }
            throw new IllegalArgumentException("Array has incompatible type: " + obj.getClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static <T> Class m23748b(AbstractC0966c<T> cVar) {
        Type type;
        Type[] genericInterfaces = cVar.getClass().getGenericInterfaces();
        if (genericInterfaces.length == 1) {
            type = genericInterfaces[0];
        } else {
            type = cVar.getClass().getGenericSuperclass();
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        while (type2 instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type2).getRawType();
        }
        String obj = type2.toString();
        if (obj.startsWith("class ")) {
            obj = obj.substring(6);
        } else if (obj.startsWith("interface ")) {
            obj = obj.substring(10);
        }
        try {
            return Class.forName(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private static Class m23724e(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        if (cls.isAnonymousClass() || cls.isSynthetic()) {
            Type[] genericInterfaces = cls.getGenericInterfaces();
            if (genericInterfaces.length == 1) {
                Type type = genericInterfaces[0];
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                str = type.toString();
            } else {
                Type genericSuperclass = cls.getGenericSuperclass();
                while (genericSuperclass instanceof ParameterizedType) {
                    genericSuperclass = ((ParameterizedType) genericSuperclass).getRawType();
                }
                str = genericSuperclass.toString();
            }
            if (str.startsWith("class ")) {
                str = str.substring(6);
            } else if (str.startsWith("interface ")) {
                str = str.substring(10);
            }
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cls;
    }
}
