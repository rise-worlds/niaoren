package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* renamed from: com.blankj.utilcode.util.aj */
/* loaded from: classes.dex */
public class PathUtils {
    private PathUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m23576a() {
        return m23575a(Environment.getRootDirectory());
    }

    /* renamed from: b */
    public static String m23573b() {
        return m23575a(Environment.getDataDirectory());
    }

    /* renamed from: c */
    public static String m23572c() {
        return m23575a(Environment.getDownloadCacheDirectory());
    }

    /* renamed from: d */
    public static String m23571d() {
        if (Build.VERSION.SDK_INT < 24) {
            return Utils.m24103a().getApplicationInfo().dataDir;
        }
        return m23575a(Utils.m24103a().getDataDir());
    }

    /* renamed from: e */
    public static String m23570e() {
        if (Build.VERSION.SDK_INT >= 21) {
            return m23575a(Utils.m24103a().getCodeCacheDir());
        }
        return Utils.m24103a().getApplicationInfo().dataDir + "/code_cache";
    }

    /* renamed from: f */
    public static String m23569f() {
        return m23575a(Utils.m24103a().getCacheDir());
    }

    /* renamed from: g */
    public static String m23568g() {
        return Utils.m24103a().getApplicationInfo().dataDir + "/databases";
    }

    /* renamed from: a */
    public static String m23574a(String str) {
        return m23575a(Utils.m24103a().getDatabasePath(str));
    }

    /* renamed from: h */
    public static String m23567h() {
        return m23575a(Utils.m24103a().getFilesDir());
    }

    /* renamed from: i */
    public static String m23566i() {
        return Utils.m24103a().getApplicationInfo().dataDir + "/shared_prefs";
    }

    /* renamed from: j */
    public static String m23565j() {
        if (Build.VERSION.SDK_INT >= 21) {
            return m23575a(Utils.m24103a().getNoBackupFilesDir());
        }
        return Utils.m24103a().getApplicationInfo().dataDir + "/no_backup";
    }

    /* renamed from: k */
    public static String m23564k() {
        return m23577J() ? "" : m23575a(Environment.getExternalStorageDirectory());
    }

    /* renamed from: l */
    public static String m23563l() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    /* renamed from: m */
    public static String m23562m() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    /* renamed from: n */
    public static String m23561n() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    /* renamed from: o */
    public static String m23560o() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    /* renamed from: p */
    public static String m23559p() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /* renamed from: q */
    public static String m23558q() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    /* renamed from: r */
    public static String m23557r() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    /* renamed from: s */
    public static String m23556s() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    /* renamed from: t */
    public static String m23555t() {
        return m23577J() ? "" : m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    /* renamed from: u */
    public static String m23554u() {
        if (m23577J()) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return m23575a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        }
        return m23575a(Environment.getExternalStorageDirectory()) + "/Documents";
    }

    /* renamed from: v */
    public static String m23553v() {
        File externalCacheDir;
        return (!m23577J() && (externalCacheDir = Utils.m24103a().getExternalCacheDir()) != null) ? m23575a(externalCacheDir.getParentFile()) : "";
    }

    /* renamed from: w */
    public static String m23552w() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalCacheDir());
    }

    /* renamed from: x */
    public static String m23551x() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(null));
    }

    /* renamed from: y */
    public static String m23550y() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    /* renamed from: z */
    public static String m23549z() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    /* renamed from: A */
    public static String m23586A() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    /* renamed from: B */
    public static String m23585B() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    /* renamed from: C */
    public static String m23584C() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /* renamed from: D */
    public static String m23583D() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    /* renamed from: E */
    public static String m23582E() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    /* renamed from: F */
    public static String m23581F() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    /* renamed from: G */
    public static String m23580G() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    /* renamed from: H */
    public static String m23579H() {
        if (m23577J()) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return m23575a(Utils.m24103a().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        }
        return m23575a(Utils.m24103a().getExternalFilesDir(null)) + "/Documents";
    }

    /* renamed from: I */
    public static String m23578I() {
        return m23577J() ? "" : m23575a(Utils.m24103a().getObbDir());
    }

    /* renamed from: J */
    private static boolean m23577J() {
        return !"mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: a */
    private static String m23575a(File file) {
        return file == null ? "" : file.getAbsolutePath();
    }
}
