package com.lody.virtual.client;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Pair;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.natives.NativeMethods;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class NativeEngine {
    private static final String LIB_NAME = "v++";
    private static final String LIB_NAME_64 = "v++_64";
    private static final String[] PKG_NEED_SKIPKILL;
    private static final List<Pair<String, String>> REDIRECT_LISTS;
    private static final String TAG = "NativeEngine";
    private static final List<DexOverride> sDexOverrides = new ArrayList();
    private static boolean sFlag = false;
    private static boolean sEnabled = false;
    private static boolean sBypassedP = false;

    private static native void nativeEnableIORedirect(String str, String str2, String str3, int i, int i2, boolean z, boolean z2);

    private static native String nativeGetRedirectedPath(String str);

    private static native void nativeIOForbid(String str);

    private static native void nativeIOReadOnly(String str);

    public static native void nativeIORedirect(String str, String str2);

    private static native void nativeIOWhitelist(String str);

    private static native void nativeLaunchEngine(Object[] objArr, String str, String str2, boolean z, int i, int i2, int i3);

    private static native void nativeMark();

    private static native String nativeReverseRedirectedPath(String str);

    static {
        try {
            if (VirtualRuntime.is64bit()) {
                System.loadLibrary(LIB_NAME_64);
            } else {
                System.loadLibrary(LIB_NAME);
            }
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
        REDIRECT_LISTS = new LinkedList();
        PKG_NEED_SKIPKILL = new String[]{"com.imo.android.imoim", "com.tencent.tmgp.djsy", "com.riotgames.league.wildrift", "com.jsw.tideng"};
    }

    public static void startDexOverride() {
        String[] strArr;
        for (InstalledAppInfo installedAppInfo : VirtualCore.get().getInstalledApps(0)) {
            if (installedAppInfo.appMode != 1) {
                sDexOverrides.add(new DexOverride(getCanonicalPath(installedAppInfo.getApkPath()), null, null, installedAppInfo.getOdexPath()));
            }
        }
        for (String str : StubManifest.REQUIRED_FRAMEWORK) {
            File frameworkFile32 = VEnvironment.getFrameworkFile32(str);
            File optimizedFrameworkFile32 = VEnvironment.getOptimizedFrameworkFile32(str);
            if (frameworkFile32.exists() && optimizedFrameworkFile32.exists()) {
                sDexOverrides.add(new DexOverride("/system/framework/" + str + ".jar", frameworkFile32.getPath(), null, optimizedFrameworkFile32.getPath()));
            }
        }
    }

    public static String getRedirectedPath(String str) {
        try {
            return nativeGetRedirectedPath(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
            return str;
        }
    }

    public static String resverseRedirectedPath(String str) {
        try {
            return nativeReverseRedirectedPath(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
            return str;
        }
    }

    public static void redirectDirectory(String str, String str2) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        REDIRECT_LISTS.add(new Pair<>(str, str2));
    }

    public static void redirectFile(String str, String str2) {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.endsWith("/")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        REDIRECT_LISTS.add(new Pair<>(str, str2));
    }

    public static void readOnlyFile(String str) {
        try {
            nativeIOReadOnly(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
    }

    public static void readOnly(String str) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        try {
            nativeIOReadOnly(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
    }

    public static void whitelistFile(String str) {
        try {
            nativeIOWhitelist(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
    }

    public static void whitelist(String str) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        try {
            nativeIOWhitelist(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
    }

    public static void forbid(String str, boolean z) {
        if (!z && !str.endsWith("/")) {
            str = str + "/";
        }
        try {
            nativeIOForbid(str);
        } catch (Throwable th) {
            VLog.m18992e(TAG, VLog.getStackTraceString(th));
        }
    }

    public static boolean canHookDlopen(InstalledAppInfo installedAppInfo) {
        if (installedAppInfo == null) {
            return true;
        }
        File appLibDirectory = VEnvironment.getAppLibDirectory(installedAppInfo.packageName);
        if (appLibDirectory.exists()) {
            File[] listFiles = appLibDirectory.listFiles();
            if (ArrayUtils.isEmpty(listFiles)) {
                return true;
            }
            for (File file : listFiles) {
                if (TextUtils.equals(file.getName(), "libDexHelper.so")) {
                    VLog.m18986w(TAG, "detected bang bang, skip hook dlopen!", new Object[0]);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean needSkipKill(InstalledAppInfo installedAppInfo) {
        if (installedAppInfo == null) {
            return false;
        }
        for (String str : PKG_NEED_SKIPKILL) {
            if (TextUtils.equals(str, installedAppInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void enableIORedirect(InstalledAppInfo installedAppInfo) {
        if (!sEnabled) {
            try {
                ApplicationInfo applicationInfo = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(VirtualCore.getConfig().getHostPackageName(), 0);
                Collections.sort(REDIRECT_LISTS, new Comparator<Pair<String, String>>() { // from class: com.lody.virtual.client.NativeEngine.1
                    public int compare(Pair<String, String> pair, Pair<String, String> pair2) {
                        return compare(((String) pair2.first).length(), ((String) pair.first).length());
                    }

                    private int compare(int i, int i2) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            return Integer.compare(i, i2);
                        }
                        if (i < i2) {
                            return -1;
                        }
                        return i == i2 ? 0 : 1;
                    }
                });
                for (Pair<String, String> pair : REDIRECT_LISTS) {
                    try {
                        nativeIORedirect((String) pair.first, (String) pair.second);
                    } catch (Throwable th) {
                        VLog.m18992e(TAG, VLog.getStackTraceString(th));
                    }
                }
                try {
                    nativeEnableIORedirect(new File(applicationInfo.nativeLibraryDir, "libv++.so").getAbsolutePath(), new File(applicationInfo.nativeLibraryDir, "libv++_64.so").getAbsolutePath(), VEnvironment.getNativeCacheDir(VirtualCore.get().is64BitEngine()).getPath(), Build.VERSION.SDK_INT, BuildCompat.getPreviewSDKInt(), canHookDlopen(installedAppInfo), needSkipKill(installedAppInfo));
                } catch (Throwable th2) {
                    VLog.m18992e(TAG, VLog.getStackTraceString(th2));
                }
                sEnabled = true;
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void launchEngine(String str) {
        if (!sFlag) {
            try {
                nativeLaunchEngine(new Object[]{NativeMethods.gOpenDexFileNative, NativeMethods.gCameraNativeSetup, NativeMethods.gAudioRecordNativeCheckPermission, NativeMethods.gMediaRecorderNativeSetup, NativeMethods.gAudioRecordNativeSetup}, VirtualCore.get().getHostPkg(), str, VirtualRuntime.isArt(), Build.VERSION.SDK_INT, NativeMethods.gCameraMethodType, NativeMethods.gAudioRecordMethodType);
            } catch (Throwable th) {
                VLog.m18992e(TAG, VLog.getStackTraceString(th));
            }
            BuildCompat.isQ();
            sFlag = true;
        }
    }

    public static void bypassHiddenAPIEnforcementPolicyIfNeeded() {
        if (!sBypassedP) {
            if (BuildCompat.isPie()) {
                try {
                    Class cls = (Class) Class.class.getDeclaredMethod("forName", String.class).invoke(null, "dalvik.system.VMRuntime");
                    Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                    Method method = (Method) declaredMethod.invoke(cls, "setHiddenApiExemptions", new Class[]{String[].class});
                    Object invoke = ((Method) declaredMethod.invoke(cls, "getRuntime", new Class[0])).invoke(null, new Object[0]);
                    if (!BuildCompat.isQ() || !BuildCompat.isEMUI()) {
                        method.invoke(invoke, new String[]{"Landroid/", "Lcom/android/", "Ljava/lang/", "Ldalvik/system/", "Llibcore/io/"});
                    } else {
                        method.invoke(invoke, new String[]{"Landroid/", "Lcom/android/", "Ljava/lang/", "Ldalvik/system/", "Llibcore/io/", "Lhuawei/"});
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            sBypassedP = true;
        }
    }

    public static boolean onKillProcess(int i, int i2) {
        VLog.m18991e(TAG, "killProcess: pid = %d, signal = %d.", Integer.valueOf(i), Integer.valueOf(i2));
        if (i == Process.myPid()) {
            VLog.m18992e(TAG, VLog.getStackTraceString(new Throwable()));
        }
        return true;
    }

    public static int onGetCallingUid(int i) {
        if (!VClient.get().isAppRunning()) {
            return i;
        }
        int callingPid = Binder.getCallingPid();
        if (callingPid == Process.myUid()) {
            return VClient.get().getVUid();
        }
        return VActivityManager.get().getUidByPid(callingPid);
    }

    private static DexOverride findDexOverride(String str) {
        for (DexOverride dexOverride : sDexOverrides) {
            if (dexOverride.originDexPath.equals(str)) {
                return dexOverride;
            }
        }
        return null;
    }

    public static void onOpenDexFileNative(String[] strArr) {
        DexOverride findDexOverride;
        String str = strArr[0];
        String str2 = strArr[1];
        if (!(str == null || (findDexOverride = findDexOverride(getCanonicalPath(str))) == null)) {
            if (findDexOverride.newDexPath != null) {
                strArr[0] = findDexOverride.newDexPath;
            }
            str2 = findDexOverride.newDexPath;
            if (findDexOverride.originOdexPath == null) {
                strArr[1] = findDexOverride.newOdexPath;
            } else if (getCanonicalPath(str2).equals(findDexOverride.originOdexPath)) {
                strArr[1] = findDexOverride.newOdexPath;
            }
        }
        VLog.m18993d(TAG, "OpenDexFileNative(\"%s\", \"%s\")", str, str2);
    }

    private static final String getCanonicalPath(String str) {
        File file = new File(str);
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return file.getAbsolutePath();
        }
    }

    public static int onGetUid(int i) {
        return !VClient.get().isAppRunning() ? i : VClient.get().getBaseVUid();
    }
}
