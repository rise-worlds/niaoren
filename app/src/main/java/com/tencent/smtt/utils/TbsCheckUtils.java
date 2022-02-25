package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileFilter;

/* renamed from: com.tencent.smtt.utils.m */
/* loaded from: classes2.dex */
public class TbsCheckUtils {
    /* renamed from: c */
    private static File m16390c(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_share");
        if (!file.isDirectory() || !file.exists()) {
            return null;
        }
        return file;
    }

    /* renamed from: a */
    public static boolean m16393a(Context context) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return m16391b(context);
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m16391b(Context context) {
        File c;
        File[] listFiles;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 25 || (c = m16390c(context)) == null) {
            return true;
        }
        for (File file : c.listFiles(new FileFilter() { // from class: com.tencent.smtt.utils.m.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                String name = file2.getName();
                return !TextUtils.isEmpty(name) && name.endsWith(".dex");
            }
        })) {
            if (file.isFile() && file.exists()) {
                if (m16392a(file)) {
                    TbsLog.m16527w("TbsCheckUtils", "" + file + " is invalid --> check failed!");
                    file.delete();
                    return false;
                }
                TbsLog.m16531i("TbsCheckUtils", "" + file + " #4 check success!");
            }
        }
        TbsLog.m16531i("TbsCheckUtils", "checkTbsValidity -->#5 check ok!");
        return true;
    }

    /* renamed from: a */
    private static boolean m16392a(File file) {
        try {
            return !Elf.m16476b(file);
        } catch (Throwable th) {
            Log.e("TbsCheckUtils", "isOatFileBroken exception: " + th);
            return false;
        }
    }
}
