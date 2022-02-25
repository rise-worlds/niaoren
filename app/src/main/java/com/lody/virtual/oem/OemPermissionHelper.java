package com.lody.virtual.oem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.lody.virtual.helper.compat.BuildCompat;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class OemPermissionHelper {
    private static List<ComponentName> EMUI_AUTO_START_COMPONENTS = Arrays.asList(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"), new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.bootstart.BootStartActivity"), new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"), new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupAwakedAppListActivity"));
    private static List<ComponentName> FLYME_AUTO_START_COMPONENTS = Arrays.asList(new ComponentName("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity"), new ComponentName("com.meizu.safe", "com.meizu.safe.security.HomeActivity"));
    private static List<ComponentName> VIVO_AUTO_START_COMPONENTS = Arrays.asList(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"), new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity"), new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"), new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewActivity"));

    public static Intent getPermissionActivityIntent(Context context) {
        switch (BuildCompat.getROMType()) {
            case EMUI:
                for (ComponentName componentName : EMUI_AUTO_START_COMPONENTS) {
                    Intent intent = new Intent();
                    intent.addFlags(268435456);
                    intent.setComponent(componentName);
                    if (verifyIntent(context, intent)) {
                        return intent;
                    }
                }
                return null;
            case MIUI:
                Intent intent2 = new Intent();
                intent2.addFlags(268435456);
                intent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
                if (verifyIntent(context, intent2)) {
                    return intent2;
                }
                return null;
            case FLYME:
                for (ComponentName componentName2 : FLYME_AUTO_START_COMPONENTS) {
                    Intent intent3 = new Intent();
                    intent3.addFlags(268435456);
                    intent3.setComponent(componentName2);
                    if (verifyIntent(context, intent3)) {
                        return intent3;
                    }
                }
                return null;
            case COLOR_OS:
                Intent intent4 = new Intent();
                intent4.addFlags(268435456);
                intent4.setClassName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity");
                if (verifyIntent(context, intent4)) {
                    return intent4;
                }
                return null;
            case LETV:
                Intent intent5 = new Intent();
                intent5.addFlags(268435456);
                intent5.setClassName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity");
                if (verifyIntent(context, intent5)) {
                    return intent5;
                }
                return null;
            case VIVO:
                for (ComponentName componentName3 : VIVO_AUTO_START_COMPONENTS) {
                    Intent intent6 = new Intent();
                    intent6.addFlags(268435456);
                    intent6.setComponent(componentName3);
                    if (verifyIntent(context, intent6)) {
                        return intent6;
                    }
                }
                return null;
            case _360:
                Intent intent7 = new Intent();
                intent7.addFlags(268435456);
                intent7.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
                if (verifyIntent(context, intent7)) {
                    return intent7;
                }
                return null;
            default:
                return null;
        }
    }

    private static boolean verifyIntent(Context context, Intent intent) {
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return (resolveActivity == null || resolveActivity.activityInfo == null || !resolveActivity.activityInfo.exported) ? false : true;
    }
}
