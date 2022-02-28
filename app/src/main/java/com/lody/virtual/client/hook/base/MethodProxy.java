package com.lody.virtual.client.hook.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.LogInvocation;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.VDeviceConfig;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class MethodProxy {
    private boolean enable = true;
    private LogInvocation.Condition mInvocationLoggingCondition;

    public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
        return obj2;
    }

    public boolean beforeCall(Object obj, Method method, Object... objArr) {
        return true;
    }

    public abstract String getMethodName();

    public MethodProxy() {
        this.mInvocationLoggingCondition = LogInvocation.Condition.NEVER;
        LogInvocation logInvocation = (LogInvocation) getClass().getAnnotation(LogInvocation.class);
        if (logInvocation != null) {
            this.mInvocationLoggingCondition = logInvocation.value();
        }
    }

    public static String getHostPkg() {
        return VirtualCore.get().getHostPkg();
    }

    public static String getAppPkg() {
        return VClient.get().getCurrentPackage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Context getHostContext() {
        return VirtualCore.get().getContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isAppProcess() {
        return VirtualCore.get().isVAppProcess();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isServerProcess() {
        return VirtualCore.get().isServerProcess();
    }

    protected static boolean isMainProcess() {
        return VirtualCore.get().isMainProcess();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getVUid() {
        return VClient.get().getVUid();
    }

    public static int getAppUserId() {
        return VUserHandle.getUserId(getVUid());
    }

    public static int getRealUserId() {
        return VUserHandle.realUserId();
    }

    public static void replaceLastUserId(Object[] objArr) {
        if (getRealUserId() != 0) {
            int i = -1;
            for (int i2 = 0; i2 < objArr.length; i2++) {
                if (objArr[i2] == 0) {
                    i = i2;
                }
            }
            if (i >= 0) {
                objArr[i] = Integer.valueOf(getRealUserId());
            }
        }
    }

    public static void replaceFirstUserId(Object[] objArr) {
        if (getRealUserId() != 0) {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] == 0) {
                    objArr[i] = Integer.valueOf(getRealUserId());
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getBaseVUid() {
        return VClient.get().getBaseVUid();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getRealUid() {
        return VirtualCore.get().myUid();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static SettingConfig getConfig() {
        return VirtualCore.getConfig();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static VDeviceConfig getDeviceConfig() {
        return VClient.get().getDeviceConfig();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isFakeLocationEnable() {
        return VirtualLocationManager.get().getMode(VUserHandle.myUserId(), VClient.get().getCurrentPackage()) != 0;
    }

    public static boolean isVisiblePackage(ApplicationInfo applicationInfo) {
        return getHostPkg().equals(applicationInfo.packageName) || ComponentUtils.isSystemApp(applicationInfo) || VirtualCore.get().isOutsidePackageVisible(applicationInfo.packageName);
    }

    public static boolean isVisiblePackage(String str) {
        try {
            return isVisiblePackage(VirtualCore.get().getUnHookPackageManager().getApplicationInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isHostIntent(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            return false;
        }
        String packageName = component.getPackageName();
        SettingConfig config = VirtualCore.getConfig();
        return packageName.equals(config.getHostPackageName()) || packageName.equals(config.get64bitEnginePackageName()) || config.isHostIntent(intent);
    }

    public Object call(Object obj, Method method, Object... objArr) throws Throwable {
        return method.invoke(obj, objArr);
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public LogInvocation.Condition getInvocationLoggingCondition() {
        return this.mInvocationLoggingCondition;
    }

    public void setInvocationloggingCondition(LogInvocation.Condition condition) {
        this.mInvocationLoggingCondition = condition;
    }

    public boolean isAppPkg(String str) {
        return VirtualCore.get().isAppInstalled(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PackageManager getPM() {
        return VirtualCore.getPM();
    }

    public String toString() {
        return "Method : " + getMethodName();
    }
}
