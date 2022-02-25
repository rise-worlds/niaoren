package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.p003v4.content.FileProvider;
import android.util.Log;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.mq.p049d.C1363e;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.C4963cj;
import p110z1.Typography;

/* renamed from: com.blankj.utilcode.util.c */
/* loaded from: classes.dex */
public final class AppUtils {

    /* renamed from: a */
    private static final char[] f6835a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static void m22951a(@NonNull Object obj, @NonNull Utils.AbstractC0955d dVar) {
        if (obj == null) {
            throw new NullPointerException("Argument 'obj' of type Object (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (dVar != null) {
            Utils.m24097b().m24082a(obj, dVar);
        } else {
            throw new NullPointerException("Argument 'listener' of type Utils.OnAppStatusChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static void m22952a(@NonNull Object obj) {
        if (obj != null) {
            Utils.m24097b().m24083a(obj);
            return;
        }
        throw new NullPointerException("Argument 'obj' of type Object (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static void m22950a(String str) {
        m22956a(m22891x(str));
    }

    /* renamed from: a */
    public static void m22956a(File file) {
        if (m22931d(file)) {
            Utils.m24103a().startActivity(m22953a(file, true));
        }
    }

    /* renamed from: a */
    public static void m22958a(Activity activity, String str, int i) {
        m22959a(activity, m22891x(str), i);
    }

    /* renamed from: a */
    public static void m22959a(Activity activity, File file, int i) {
        if (m22931d(file)) {
            activity.startActivityForResult(m22928e(file), i);
        }
    }

    /* renamed from: b */
    public static boolean m22940b(String str) {
        return m22955a(m22891x(str), (String) null);
    }

    /* renamed from: b */
    public static boolean m22941b(File file) {
        return m22955a(file, (String) null);
    }

    /* renamed from: a */
    public static boolean m22949a(String str, String str2) {
        return m22955a(m22891x(str), str2);
    }

    /* renamed from: a */
    public static boolean m22955a(File file, String str) {
        return m22954a(file, str, m22896u());
    }

    /* renamed from: a */
    public static boolean m22954a(File file, String str, boolean z) {
        String str2;
        if (!m22931d(file)) {
            return false;
        }
        String str3 = Typography.f21049a + file.getAbsolutePath() + Typography.f21049a;
        StringBuilder sb = new StringBuilder();
        sb.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install ");
        if (str == null) {
            str2 = "";
        } else {
            str2 = str + ExpandableTextView.f6958c;
        }
        sb.append(str2);
        sb.append(str3);
        ShellUtils.C0985a a = ShellUtils.m23276a(sb.toString(), z);
        if (a.f6749b != null && a.f6749b.toLowerCase().contains("success")) {
            return true;
        }
        Log.e("AppUtils", "installAppSilent successMsg: " + a.f6749b + ", errorMsg: " + a.f6750c);
        return false;
    }

    /* renamed from: c */
    public static void m22934c(String str) {
        if (!m22890y(str)) {
            Utils.m24103a().startActivity(m22938b(str, true));
        }
    }

    /* renamed from: b */
    public static void m22942b(Activity activity, String str, int i) {
        if (!m22890y(str)) {
            activity.startActivityForResult(m22889z(str), i);
        }
    }

    /* renamed from: d */
    public static boolean m22930d(String str) {
        return m22948a(str, false);
    }

    /* renamed from: a */
    public static boolean m22948a(String str, boolean z) {
        return m22947a(str, z, m22896u());
    }

    /* renamed from: a */
    public static boolean m22947a(String str, boolean z, boolean z2) {
        if (m22890y(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall ");
        sb.append(z ? "-k " : "");
        sb.append(str);
        ShellUtils.C0985a a = ShellUtils.m23276a(sb.toString(), z2);
        if (a.f6749b != null && a.f6749b.toLowerCase().contains("success")) {
            return true;
        }
        Log.e("AppUtils", "uninstallAppSilent successMsg: " + a.f6749b + ", errorMsg: " + a.f6750c);
        return false;
    }

    /* renamed from: e */
    public static boolean m22927e(@NonNull String str) {
        if (str != null) {
            try {
                return Utils.m24103a().getPackageManager().getApplicationInfo(str, 0) != null;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static boolean m22960a() {
        ShellUtils.C0985a a = ShellUtils.m23276a("echo root", true);
        if (a.f6748a == 0) {
            return true;
        }
        if (a.f6750c == null) {
            return false;
        }
        Log.d("AppUtils", "isAppRoot() called" + a.f6750c);
        return false;
    }

    /* renamed from: b */
    public static boolean m22943b() {
        return m22925f(Utils.m24103a().getPackageName());
    }

    /* renamed from: f */
    public static boolean m22925f(String str) {
        if (m22890y(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.m24103a().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return (applicationInfo.flags & 2) != 0;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m22937c() {
        return m22923g(Utils.m24103a().getPackageName());
    }

    /* renamed from: g */
    public static boolean m22923g(String str) {
        if (m22890y(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.m24103a().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: d */
    public static boolean m22932d() {
        return Utils.m24094e();
    }

    /* renamed from: h */
    public static boolean m22921h(@NonNull String str) {
        if (str != null) {
            return !m22890y(str) && str.equals(m22894v());
        }
        throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: i */
    public static boolean m22919i(@NonNull String str) {
        if (str != null) {
            try {
                ApplicationInfo applicationInfo = Utils.m24103a().getPackageManager().getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    return false;
                }
                int i = applicationInfo.uid;
                ActivityManager activityManager = (ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
                if (activityManager != null) {
                    List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                    if (runningTasks != null && runningTasks.size() > 0) {
                        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                            if (str.equals(runningTaskInfo.baseActivity.getPackageName())) {
                                return true;
                            }
                        }
                    }
                    List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null && runningServices.size() > 0) {
                        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                            if (i == runningServiceInfo.uid) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: j */
    public static void m22917j(String str) {
        if (!m22890y(str)) {
            Utils.m24103a().startActivity(m22933c(str, true));
        }
    }

    /* renamed from: c */
    public static void m22936c(Activity activity, String str, int i) {
        if (!m22890y(str)) {
            activity.startActivityForResult(m22961A(str), i);
        }
    }

    /* renamed from: e */
    public static void m22929e() {
        m22946a(false);
    }

    /* renamed from: a */
    public static void m22946a(boolean z) {
        Intent launchIntentForPackage = Utils.m24103a().getPackageManager().getLaunchIntentForPackage(Utils.m24103a().getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(67108864);
            Utils.m24103a().startActivity(launchIntentForPackage);
            if (z) {
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        }
    }

    /* renamed from: f */
    public static void m22926f() {
        m22915k(Utils.m24103a().getPackageName());
    }

    /* renamed from: k */
    public static void m22915k(String str) {
        if (!m22890y(str)) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
            Utils.m24103a().startActivity(intent.addFlags(268435456));
        }
    }

    /* renamed from: g */
    public static void m22924g() {
        LinkedList<Activity> c = Utils.m24096c();
        for (int size = c.size() - 1; size >= 0; size--) {
            c.get(size).finish();
        }
        System.exit(0);
    }

    /* renamed from: h */
    public static Drawable m22922h() {
        return m22913l(Utils.m24103a().getPackageName());
    }

    /* renamed from: l */
    public static Drawable m22913l(String str) {
        if (m22890y(str)) {
            return null;
        }
        try {
            PackageManager packageManager = Utils.m24103a().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: i */
    public static String m22920i() {
        return Utils.m24103a().getPackageName();
    }

    /* renamed from: j */
    public static String m22918j() {
        return m22911m(Utils.m24103a().getPackageName());
    }

    /* renamed from: m */
    public static String m22911m(String str) {
        if (m22890y(str)) {
            return "";
        }
        try {
            PackageManager packageManager = Utils.m24103a().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: k */
    public static String m22916k() {
        return m22909n(Utils.m24103a().getPackageName());
    }

    /* renamed from: n */
    public static String m22909n(String str) {
        if (m22890y(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: l */
    public static String m22914l() {
        return m22907o(Utils.m24103a().getPackageName());
    }

    /* renamed from: o */
    public static String m22907o(String str) {
        if (m22890y(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: m */
    public static int m22912m() {
        return m22905p(Utils.m24103a().getPackageName());
    }

    /* renamed from: p */
    public static int m22905p(String str) {
        if (m22890y(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: n */
    public static Signature[] m22910n() {
        return m22903q(Utils.m24103a().getPackageName());
    }

    /* renamed from: q */
    public static Signature[] m22903q(String str) {
        if (m22890y(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = Utils.m24103a().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: o */
    public static String m22908o() {
        return m22901r(Utils.m24103a().getPackageName());
    }

    /* renamed from: r */
    public static String m22901r(String str) {
        return m22939b(str, "SHA1");
    }

    /* renamed from: p */
    public static String m22906p() {
        return m22899s(Utils.m24103a().getPackageName());
    }

    /* renamed from: s */
    public static String m22899s(String str) {
        return m22939b(str, "SHA256");
    }

    /* renamed from: q */
    public static String m22904q() {
        return m22897t(Utils.m24103a().getPackageName());
    }

    /* renamed from: t */
    public static String m22897t(String str) {
        return m22939b(str, "MD5");
    }

    /* renamed from: r */
    public static int m22902r() {
        return m22895u(Utils.m24103a().getPackageName());
    }

    /* renamed from: u */
    public static int m22895u(String str) {
        try {
            ApplicationInfo applicationInfo = Utils.m24103a().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: b */
    private static String m22939b(String str, String str2) {
        Signature[] q;
        return (!m22890y(str) && (q = m22903q(str)) != null && q.length > 0) ? m22945a(m22944a(q[0].toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0") : "";
    }

    /* renamed from: s */
    public static C1013a m22900s() {
        return m22893v(Utils.m24103a().getPackageName());
    }

    /* renamed from: v */
    public static C1013a m22893v(String str) {
        try {
            PackageManager packageManager = Utils.m24103a().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return m22957a(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: t */
    public static List<C1013a> m22898t() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = Utils.m24103a().getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            C1013a a = m22957a(packageManager, packageInfo);
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public static C1013a m22935c(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return null;
        }
        return m22892w(file.getAbsolutePath());
    }

    /* renamed from: w */
    public static C1013a m22892w(String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (m22890y(str) || (packageManager = Utils.m24103a().getPackageManager()) == null || (packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return m22957a(packageManager, packageArchiveInfo);
    }

    /* renamed from: a */
    private static C1013a m22957a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return new C1013a(packageInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }

    /* compiled from: AppUtils.java */
    /* renamed from: com.blankj.utilcode.util.c$a */
    /* loaded from: classes.dex */
    public static class C1013a {

        /* renamed from: a */
        private String f6836a;

        /* renamed from: b */
        private String f6837b;

        /* renamed from: c */
        private Drawable f6838c;

        /* renamed from: d */
        private String f6839d;

        /* renamed from: e */
        private String f6840e;

        /* renamed from: f */
        private int f6841f;

        /* renamed from: g */
        private boolean f6842g;

        /* renamed from: a */
        public Drawable m22888a() {
            return this.f6838c;
        }

        /* renamed from: a */
        public void m22886a(Drawable drawable) {
            this.f6838c = drawable;
        }

        /* renamed from: b */
        public boolean m22883b() {
            return this.f6842g;
        }

        /* renamed from: a */
        public void m22884a(boolean z) {
            this.f6842g = z;
        }

        /* renamed from: c */
        public String m22881c() {
            return this.f6836a;
        }

        /* renamed from: a */
        public void m22885a(String str) {
            this.f6836a = str;
        }

        /* renamed from: d */
        public String m22879d() {
            return this.f6837b;
        }

        /* renamed from: b */
        public void m22882b(String str) {
            this.f6837b = str;
        }

        /* renamed from: e */
        public String m22877e() {
            return this.f6839d;
        }

        /* renamed from: c */
        public void m22880c(String str) {
            this.f6839d = str;
        }

        /* renamed from: f */
        public int m22876f() {
            return this.f6841f;
        }

        /* renamed from: a */
        public void m22887a(int i) {
            this.f6841f = i;
        }

        /* renamed from: g */
        public String m22875g() {
            return this.f6840e;
        }

        /* renamed from: d */
        public void m22878d(String str) {
            this.f6840e = str;
        }

        public C1013a(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            m22882b(str2);
            m22886a(drawable);
            m22885a(str);
            m22880c(str3);
            m22878d(str4);
            m22887a(i);
            m22884a(z);
        }

        public String toString() {
            return "{\n  pkg name: " + m22881c() + "\n  app icon: " + m22888a() + "\n  app name: " + m22879d() + "\n  app path: " + m22877e() + "\n  app v name: " + m22875g() + "\n  app v code: " + m22876f() + "\n  is system: " + m22883b() + C4963cj.f20747d;
        }
    }

    /* renamed from: d */
    private static boolean m22931d(File file) {
        return file != null && file.exists();
    }

    /* renamed from: x */
    private static File m22891x(String str) {
        if (m22890y(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: y */
    private static boolean m22890y(String str) {
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

    /* renamed from: u */
    private static boolean m22896u() {
        String[] strArr;
        for (String str : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"}) {
            if (new File(str + C1363e.f8870a).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static byte[] m22944a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m22945a(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6835a;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: e */
    private static Intent m22928e(File file) {
        return m22953a(file, false);
    }

    /* renamed from: a */
    private static Intent m22953a(File file, boolean z) {
        Uri uri;
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        } else {
            uri = FileProvider.getUriForFile(Utils.m24103a(), Utils.m24103a().getPackageName() + ".utilcode.provider", file);
            intent.setFlags(1);
        }
        Utils.m24103a().grantUriPermission(Utils.m24103a().getPackageName(), uri, 1);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return z ? intent.addFlags(268435456) : intent;
    }

    /* renamed from: z */
    private static Intent m22889z(String str) {
        return m22938b(str, false);
    }

    /* renamed from: b */
    private static Intent m22938b(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return z ? intent.addFlags(268435456) : intent;
    }

    /* renamed from: A */
    private static Intent m22961A(String str) {
        return m22933c(str, false);
    }

    /* renamed from: c */
    private static Intent m22933c(String str, boolean z) {
        Intent launchIntentForPackage = Utils.m24103a().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return z ? launchIntentForPackage.addFlags(268435456) : launchIntentForPackage;
    }

    /* renamed from: v */
    private static String m22894v() {
        List<UsageStats> list;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        if (Build.VERSION.SDK_INT <= 21) {
            return "";
        }
        PackageManager packageManager = Utils.m24103a().getPackageManager();
        Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        Log.i("ProcessUtils", queryIntentActivities.toString());
        if (queryIntentActivities.size() <= 0) {
            Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.m24103a().getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) Utils.m24103a().getSystemService("appops");
            if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                intent.addFlags(268435456);
                Utils.m24103a().startActivity(intent);
            }
            if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
                return "";
            }
            UsageStatsManager usageStatsManager = (UsageStatsManager) Utils.m24103a().getSystemService("usagestats");
            if (usageStatsManager != null) {
                long currentTimeMillis = System.currentTimeMillis();
                list = usageStatsManager.queryUsageStats(4, currentTimeMillis - WaitFor.ONE_WEEK, currentTimeMillis);
            } else {
                list = null;
            }
            if (list != null && !list.isEmpty()) {
                UsageStats usageStats = null;
                for (UsageStats usageStats2 : list) {
                    if (usageStats == null || usageStats2.getLastTimeUsed() > usageStats.getLastTimeUsed()) {
                        usageStats = usageStats2;
                    }
                }
                if (usageStats == null) {
                    return null;
                }
                return usageStats.getPackageName();
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
