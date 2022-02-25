package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: com.blankj.utilcode.util.al */
/* loaded from: classes.dex */
public final class ProcessUtils {
    private ProcessUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m23529a() {
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
            return "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    /* renamed from: b */
    public static Set<String> m23527b() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
        HashSet hashSet = new HashSet();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                Collections.addAll(hashSet, runningAppProcessInfo.pkgList);
            }
        }
        return hashSet;
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    /* renamed from: c */
    public static Set<String> m23526c() {
        String[] strArr;
        ActivityManager activityManager = (ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        HashSet hashSet = new HashSet();
        if (runningAppProcesses == null) {
            return hashSet;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            for (String str : it.next().pkgList) {
                activityManager.killBackgroundProcesses(str);
                hashSet.add(str);
            }
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            for (String str2 : runningAppProcessInfo.pkgList) {
                hashSet.remove(str2);
            }
        }
        return hashSet;
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    /* renamed from: a */
    public static boolean m23528a(@NonNull String str) {
        if (str != null) {
            ActivityManager activityManager = (ActivityManager) Utils.m24103a().getSystemService(ServiceManagerNative.ACTIVITY);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
                return true;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                    activityManager.killBackgroundProcesses(str);
                }
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses2 = activityManager.getRunningAppProcesses();
            if (runningAppProcesses2 == null || runningAppProcesses2.size() == 0) {
                return true;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 : runningAppProcesses2) {
                if (Arrays.asList(runningAppProcessInfo2.pkgList).contains(str)) {
                    return false;
                }
            }
            return true;
        }
        throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: d */
    public static boolean m23525d() {
        return Utils.m24103a().getPackageName().equals(Utils.m24093f());
    }

    /* renamed from: e */
    public static String m23524e() {
        return Utils.m24093f();
    }
}
