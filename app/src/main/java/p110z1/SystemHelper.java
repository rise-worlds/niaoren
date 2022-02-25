package p110z1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.common.utils.log.LogUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: z1.aeu */
/* loaded from: classes3.dex */
public class SystemHelper {
    /* renamed from: a */
    public static boolean m13834a(Context context, String str) {
        if ("".equals(str.trim())) {
            return false;
        }
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(1)) {
            if (packageInfo.packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m13835a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(100)) {
            LogUtils.m22036e("setTopApp", "setTopApp: ");
            LogUtils.m22036e("setTopApp", context.getPackageName());
            LogUtils.m22036e("setTopApp", runningTaskInfo.topActivity.getPackageName());
            if (runningTaskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                LogUtils.m22036e("setTopApp", "context.getPackageName(): ");
                activityManager.moveTaskToFront(runningTaskInfo.id, 0);
                return;
            }
        }
    }

    /* renamed from: b */
    public static boolean m13833b(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                return true;
            }
        }
        return false;
    }
}
