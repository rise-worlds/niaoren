package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/* renamed from: com.blankj.utilcode.util.r */
/* loaded from: classes.dex */
public final class CrashUtils {

    /* renamed from: a */
    private static String f6898a;

    /* renamed from: b */
    private static String f6899b;

    /* renamed from: c */
    private static String f6900c;

    /* renamed from: d */
    private static int f6901d;

    /* renamed from: e */
    private static final String f6902e = System.getProperty("file.separator");
    @SuppressLint({"SimpleDateFormat"})

    /* renamed from: f */
    private static final Format f6903f = new SimpleDateFormat("MM-dd HH-mm-ss");

    /* renamed from: g */
    private static final Thread.UncaughtExceptionHandler f6904g;

    /* renamed from: h */
    private static final Thread.UncaughtExceptionHandler f6905h;

    /* renamed from: i */
    private static AbstractC1028a f6906i;

    /* compiled from: CrashUtils.java */
    /* renamed from: com.blankj.utilcode.util.r$a */
    /* loaded from: classes.dex */
    public interface AbstractC1028a {
        /* renamed from: a */
        void m22417a(String str, Throwable th);
    }

    static {
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(Utils.m24103a().getPackageName(), 0);
            if (packageInfo != null) {
                f6900c = packageInfo.versionName;
                f6901d = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        f6904g = Thread.getDefaultUncaughtExceptionHandler();
        f6905h = new Thread.UncaughtExceptionHandler() { // from class: com.blankj.utilcode.util.r.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                if (th != null) {
                    String format = CrashUtils.f6903f.format(new Date(System.currentTimeMillis()));
                    StringBuilder sb = new StringBuilder();
                    sb.append("************* Log Head ****************\nTime Of Crash      : " + format + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + CrashUtils.f6900c + "\nApp VersionCode    : " + CrashUtils.f6901d + "\n************* Log Head ****************\n\n");
                    sb.append(ThrowableUtils.m23131a(th));
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(CrashUtils.f6899b == null ? CrashUtils.f6898a : CrashUtils.f6899b);
                    sb3.append(format);
                    sb3.append(".txt");
                    String sb4 = sb3.toString();
                    if (CrashUtils.m22425c(sb4)) {
                        CrashUtils.m22427b(sb2, sb4);
                    } else {
                        Log.e("CrashUtils", "create " + sb4 + " failed!");
                    }
                    if (CrashUtils.f6906i != null) {
                        CrashUtils.f6906i.m22417a(sb2, th);
                    }
                    if (CrashUtils.f6904g != null) {
                        CrashUtils.f6904g.uncaughtException(thread, th);
                    }
                } else if (CrashUtils.f6904g != null) {
                    CrashUtils.f6904g.uncaughtException(thread, null);
                } else {
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        };
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public static void m22437a() {
        m22433a("");
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    /* renamed from: a */
    public static void m22435a(@NonNull File file) {
        if (file != null) {
            m22432a(file.getAbsolutePath(), (AbstractC1028a) null);
            return;
        }
        throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    /* renamed from: a */
    public static void m22433a(String str) {
        m22432a(str, (AbstractC1028a) null);
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public static void m22436a(AbstractC1028a aVar) {
        m22432a("", aVar);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    /* renamed from: a */
    public static void m22434a(@NonNull File file, AbstractC1028a aVar) {
        if (file != null) {
            m22432a(file.getAbsolutePath(), aVar);
            return;
        }
        throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    /* renamed from: a */
    public static void m22432a(String str, AbstractC1028a aVar) {
        if (m22423d(str)) {
            f6899b = null;
        } else {
            if (!str.endsWith(f6902e)) {
                str = str + f6902e;
            }
            f6899b = str;
        }
        if (!"mounted".equals(Environment.getExternalStorageState()) || Utils.m24103a().getExternalCacheDir() == null) {
            f6898a = Utils.m24103a().getCacheDir() + f6902e + "crash" + f6902e;
        } else {
            f6898a = Utils.m24103a().getExternalCacheDir() + f6902e + "crash" + f6902e;
        }
        f6906i = aVar;
        Thread.setDefaultUncaughtExceptionHandler(f6905h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m22427b(final String str, final String str2) {
        try {
            if (((Boolean) Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() { // from class: com.blankj.utilcode.util.r.2
                /* renamed from: a */
                public Boolean call() {
                    Throwable th;
                    IOException e;
                    BufferedWriter bufferedWriter;
                    BufferedWriter bufferedWriter2 = null;
                    try {
                        try {
                            bufferedWriter = new BufferedWriter(new FileWriter(str2, true));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                    try {
                        bufferedWriter.write(str);
                        try {
                            bufferedWriter.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return true;
                    } catch (IOException e4) {
                        e = e4;
                        bufferedWriter2 = bufferedWriter;
                        e.printStackTrace();
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedWriter2 = bufferedWriter;
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }).get()).booleanValue()) {
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        Log.e("CrashUtils", "write crash info to " + str2 + " failed!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static boolean m22425c(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!m22429b(file.getParentFile())) {
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
    private static boolean m22429b(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: d */
    private static boolean m22423d(String str) {
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
