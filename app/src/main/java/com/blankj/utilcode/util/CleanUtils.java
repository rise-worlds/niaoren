package com.blankj.utilcode.util;

import android.os.Environment;
import java.io.File;

/* renamed from: com.blankj.utilcode.util.l */
/* loaded from: classes.dex */
public final class CleanUtils {
    private CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22518a() {
        return m22514b(Utils.m24103a().getCacheDir());
    }

    /* renamed from: b */
    public static boolean m22515b() {
        return m22514b(Utils.m24103a().getFilesDir());
    }

    /* renamed from: c */
    public static boolean m22512c() {
        return m22514b(new File(Utils.m24103a().getFilesDir().getParent(), "databases"));
    }

    /* renamed from: a */
    public static boolean m22516a(String str) {
        return Utils.m24103a().deleteDatabase(str);
    }

    /* renamed from: d */
    public static boolean m22509d() {
        return m22514b(new File(Utils.m24103a().getFilesDir().getParent(), "shared_prefs"));
    }

    /* renamed from: e */
    public static boolean m22507e() {
        return "mounted".equals(Environment.getExternalStorageState()) && m22514b(Utils.m24103a().getExternalCacheDir());
    }

    /* renamed from: b */
    public static boolean m22513b(String str) {
        return m22510c(str);
    }

    /* renamed from: a */
    public static boolean m22517a(File file) {
        return m22514b(file);
    }

    /* renamed from: c */
    public static boolean m22510c(String str) {
        return m22514b(m22508d(str));
    }

    /* renamed from: b */
    private static boolean m22514b(File file) {
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
                } else if (file2.isDirectory() && !m22511c(file2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: c */
    private static boolean m22511c(File file) {
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
                } else if (file2.isDirectory() && !m22511c(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: d */
    private static File m22508d(String str) {
        if (m22506e(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: e */
    private static boolean m22506e(String str) {
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
