package com.lody.virtual.client.env;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.VLog;
import p110z1.DdmHandleAppName;
import p110z1.DdmHandleAppNameJBMR1;
import p110z1.VMRuntime;
import p110z1.cwm;

/* loaded from: classes.dex */
public class VirtualRuntime {
    private static String sInitialPackageName;
    private static String sProcessName;
    private static final Handler sUIHandler = new Handler(Looper.getMainLooper());

    public static Handler getUIHandler() {
        return sUIHandler;
    }

    public static String getProcessName() {
        return sProcessName;
    }

    public static String getInitialPackageName() {
        return sInitialPackageName;
    }

    public static void setupRuntime(String str, ApplicationInfo applicationInfo) {
        if (sProcessName == null) {
            sInitialPackageName = applicationInfo.packageName;
            sProcessName = str;
            cwm.setArgV0.call(str);
            if (Build.VERSION.SDK_INT >= 17) {
                DdmHandleAppNameJBMR1.setAppName.call(str, 0);
            } else {
                DdmHandleAppName.setAppName.call(str);
            }
        }
    }

    public static <T> T crash(Throwable th) throws RuntimeException {
        th.printStackTrace();
        throw new RuntimeException("transact remote server failed", th);
    }

    public static boolean is64bit() {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        return VMRuntime.is64Bit.call(VMRuntime.getRuntime.call(new Object[0]), new Object[0]).booleanValue();
    }

    public static void exit() {
        VLog.m18993d(VirtualRuntime.class.getSimpleName(), "Exit process : %s (%s).", getProcessName(), VirtualCore.get().getProcessName());
        Process.killProcess(Process.myPid());
    }

    public static boolean isArt() {
        return System.getProperty("java.vm.version").startsWith("2");
    }
}
