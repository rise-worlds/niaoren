package com.cyjh.p045mq.p049d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* renamed from: com.cyjh.mq.d.d */
/* loaded from: classes.dex */
public final class PackageUtils {
    /* renamed from: a */
    public static String m20443a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            return applicationInfo != null ? (String) packageManager.getApplicationLabel(applicationInfo) : "NULL";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "NULL";
        }
    }

    /* renamed from: c */
    private static int m20441c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: b */
    public static String m20442b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            return packageInfo != null ? packageInfo.versionName : "NULL";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "NULL";
        }
    }

    /* renamed from: d */
    private static String m20440d(Context context) {
        return context.getPackageName();
    }
}
