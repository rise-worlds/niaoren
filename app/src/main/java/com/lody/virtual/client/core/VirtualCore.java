package com.lody.virtual.client.core;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.kaopu.VACallBack;
import com.lody.virtual.C1713R;
import com.lody.virtual.SandXposed;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.fixer.ContextFixer;
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate;
import com.lody.virtual.client.ipc.LocalProxyUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.BroadcastIntentData;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstallResult;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IAppManager;
import com.lody.virtual.server.interfaces.IPackageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.ActivityThread;
import p110z1.C4985cm;

/* loaded from: classes.dex */
public final class VirtualCore {
    public static final int GET_HIDDEN_APP = 1;
    private static final String TAG = "VirtualCore";
    @SuppressLint({"StaticFieldLeak"})
    private static VirtualCore gCore = new VirtualCore();
    private Context context;
    private String hostPkgName;
    private boolean is64Bit;
    private boolean isStartUp;
    private AppCallback mAppCallback;
    private AppRequestListener mAppRequestListener;
    private SettingConfig mConfig;
    private PackageInfo mHostPkgInfo;
    private ConditionVariable mInitLock;
    private IAppManager mService;
    private TaskDescriptionDelegate mTaskDescriptionDelegate;
    private String mainProcessName;
    private Object mainThread;
    private String processName;
    private ProcessType processType;
    private PackageManager unHookPackageManager;
    private VACallBack vaCallBack;
    private final int myUid = Process.myUid();
    private final BroadcastReceiver mDownloadCompleteReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.client.core.VirtualCore.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            VLog.m18986w("DownloadManager", "receive download completed brodcast: " + intent, new Object[0]);
            intent.setExtrasClassLoader(BroadcastIntentData.class.getClassLoader());
            if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                VActivityManager.get().handleDownloadCompleteIntent(intent);
            }
        }
    };

    /* loaded from: classes.dex */
    public interface AppRequestListener {
        void onRequestInstall(String str);

        void onRequestUninstall(String str);
    }

    /* loaded from: classes.dex */
    public interface InstallCallback {
        void onFinish(InstallResult installResult);
    }

    /* loaded from: classes.dex */
    public interface OnEmitShortcutListener {
        Bitmap getIcon(Bitmap bitmap);

        String getName(String str);
    }

    /* loaded from: classes.dex */
    public static abstract class PackageObserver extends IPackageObserver.Stub {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum ProcessType {
        Server,
        VAppClient,
        Main,
        Helper,
        CHILD
    }

    /* loaded from: classes.dex */
    public static abstract class VirtualInitializer {
        public void onChildProcess() {
        }

        public void onMainProcess() {
        }

        public void onServerProcess() {
        }

        public void onVirtualProcess() {
        }
    }

    @Deprecated
    public void preOpt(String str) throws IOException {
    }

    private VirtualCore() {
    }

    public static SettingConfig getConfig() {
        return get().mConfig;
    }

    public static VirtualCore get() {
        return gCore;
    }

    public static PackageManager getPM() {
        return get().getPackageManager();
    }

    public static Object mainThread() {
        return get().mainThread;
    }

    public VACallBack getVaCallBack() {
        return this.vaCallBack;
    }

    public void setVaCallBack(VACallBack vACallBack) {
        this.vaCallBack = vACallBack;
    }

    public ConditionVariable getInitLock() {
        return this.mInitLock;
    }

    public int myUid() {
        return this.myUid;
    }

    public int myUserId() {
        return VUserHandle.getUserId(this.myUid);
    }

    public AppCallback getAppCallback() {
        AppCallback appCallback = this.mAppCallback;
        return appCallback == null ? AppCallback.EMPTY : appCallback;
    }

    public void setComponentDelegate(AppCallback appCallback) {
        setAppCallback(appCallback);
    }

    public void setAppCallback(AppCallback appCallback) {
        this.mAppCallback = appCallback;
    }

    public void setCrashHandler(CrashHandler crashHandler) {
        VClient.get().setCrashHandler(crashHandler);
    }

    public TaskDescriptionDelegate getTaskDescriptionDelegate() {
        return this.mTaskDescriptionDelegate;
    }

    public void setTaskDescriptionDelegate(TaskDescriptionDelegate taskDescriptionDelegate) {
        this.mTaskDescriptionDelegate = taskDescriptionDelegate;
    }

    public int[] getGids() {
        return this.mHostPkgInfo.gids;
    }

    public ApplicationInfo getHostApplicationInfo() {
        return this.mHostPkgInfo.applicationInfo;
    }

    public Context getContext() {
        return this.context;
    }

    public PackageManager getPackageManager() {
        return this.context.getPackageManager();
    }

    public boolean isSystemApp() {
        return (getContext().getApplicationInfo().flags & 1) != 0;
    }

    public String getHostPkg() {
        return this.hostPkgName;
    }

    public int getTargetSdkVersion() {
        return this.context.getApplicationInfo().targetSdkVersion;
    }

    public PackageManager getUnHookPackageManager() {
        return this.unHookPackageManager;
    }

    public boolean checkSelfPermission(String str, boolean z) {
        return z ? this.unHookPackageManager.checkPermission(str, StubManifest.PACKAGE_NAME_64BIT) == 0 : this.unHookPackageManager.checkPermission(str, StubManifest.PACKAGE_NAME) == 0;
    }

    public void waitStartup() {
        ConditionVariable conditionVariable = this.mInitLock;
        if (conditionVariable != null) {
            conditionVariable.block();
        }
    }

    public int getUidForSharedUser(String str) {
        try {
            return getService().getUidForSharedUser(str);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public void startup(Context context, SettingConfig settingConfig) throws Throwable {
        if (this.isStartUp) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.mInitLock = new ConditionVariable();
            this.mConfig = settingConfig;
            String hostPackageName = settingConfig.getHostPackageName();
            String str = settingConfig.get64bitEnginePackageName();
            Constants.ACTION_SHORTCUT = hostPackageName + Constants.ACTION_SHORTCUT;
            Constants.ACTION_BADGER_CHANGE = hostPackageName + Constants.ACTION_BADGER_CHANGE;
            StubManifest.PACKAGE_NAME = hostPackageName;
            StubManifest.STUB_CP_AUTHORITY = hostPackageName + ".virtual_stub_";
            StubManifest.PROXY_CP_AUTHORITY = hostPackageName + ".provider_proxy";
            if (str == null) {
                str = "NO_64BIT";
            }
            StubManifest.PACKAGE_NAME_64BIT = str;
            StubManifest.STUB_CP_AUTHORITY_64BIT = str + ".virtual_stub_64bit_";
            StubManifest.PROXY_CP_AUTHORITY_64BIT = str + ".provider_proxy_64bit";
            this.context = context;
            this.unHookPackageManager = context.getPackageManager();
            this.mHostPkgInfo = this.unHookPackageManager.getPackageInfo(hostPackageName, 256);
            detectProcessType();
            NativeEngine.bypassHiddenAPIEnforcementPolicyIfNeeded();
            if (isServerProcess() || isVAppProcess()) {
                this.mainThread = ActivityThread.currentActivityThread.call(new Object[0]);
            }
            if (is64BitEngine()) {
                VLog.m18991e(TAG, "===========  64Bit Engine(%s) ===========", this.processType.name());
                if (isVAppProcess()) {
                    getService().asBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: com.lody.virtual.client.core.VirtualCore.2
                        @Override // android.os.IBinder.DeathRecipient
                        public void binderDied() {
                            VLog.m18992e(VirtualCore.TAG, "32Bit Engine was dead, kill app process.");
                            Process.killProcess(Process.myPid());
                        }
                    }, 0);
                }
            }
            if (isServerProcess() || is64bitHelperProcess()) {
                VLog.m18986w("DownloadManager", "Listening DownloadManager action  in process: " + this.processType, new Object[0]);
                try {
                    context.registerReceiver(this.mDownloadCompleteReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            InvocationStubManager instance = InvocationStubManager.getInstance();
            instance.init();
            instance.injectAll();
            ContextFixer.fixContext(context);
            this.isStartUp = true;
            this.mInitLock.open();
            return;
        }
        throw new IllegalStateException("VirtualCore.startup() must called in main thread.");
    }

    public void waitForEngine() {
        ServiceManagerNative.ensureServerStarted();
    }

    public boolean isEngineLaunched() {
        if (is64BitEngine()) {
            return true;
        }
        String engineProcessName = getEngineProcessName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.endsWith(engineProcessName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIORelocateWork() {
        try {
            return getService().isIORelocateWork();
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessesEx() {
        ArrayList arrayList = new ArrayList(((ActivityManager) this.context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses());
        List<ActivityManager.RunningAppProcessInfo> runningAppProcess64 = V64BitHelper.getRunningAppProcess64();
        if (runningAppProcess64 != null) {
            arrayList.addAll(runningAppProcess64);
        }
        return arrayList;
    }

    public List<ActivityManager.RunningTaskInfo> getRunningTasksEx(int i) {
        ArrayList arrayList = new ArrayList(((ActivityManager) this.context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(i));
        List<ActivityManager.RunningTaskInfo> runningTasks64 = V64BitHelper.getRunningTasks64(i);
        if (runningTasks64 != null) {
            arrayList.addAll(runningTasks64);
        }
        return arrayList;
    }

    public List<ActivityManager.RecentTaskInfo> getRecentTasksEx(int i, int i2) {
        ArrayList arrayList = new ArrayList(((ActivityManager) this.context.getSystemService(ServiceManagerNative.ACTIVITY)).getRecentTasks(i, i2));
        List<ActivityManager.RecentTaskInfo> recentTasks64 = V64BitHelper.getRecentTasks64(i, i2);
        if (recentTasks64 != null) {
            arrayList.addAll(recentTasks64);
        }
        return arrayList;
    }

    public void requestCopyPackage64(String str) {
        try {
            getService().requestCopyPackage64(str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public String getEngineProcessName() {
        return this.context.getString(C1713R.string.engine_process_name);
    }

    public void initialize(VirtualInitializer virtualInitializer) {
        if (virtualInitializer != null) {
            switch (this.processType) {
                case Main:
                    virtualInitializer.onMainProcess();
                    return;
                case VAppClient:
                    if (Build.VERSION.SDK_INT >= 21) {
                        SandXposed.init();
                    }
                    virtualInitializer.onVirtualProcess();
                    return;
                case Server:
                    virtualInitializer.onServerProcess();
                    return;
                case CHILD:
                    virtualInitializer.onChildProcess();
                    return;
                default:
                    return;
            }
        } else {
            throw new IllegalStateException("Initializer = NULL");
        }
    }

    private static String getProcessName(Context context) {
        String str;
        int myPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                str = next.processName;
                break;
            }
        }
        if (str != null) {
            return str;
        }
        throw new RuntimeException("processName = null");
    }

    private void detectProcessType() {
        this.hostPkgName = this.context.getApplicationInfo().packageName;
        this.mainProcessName = this.context.getApplicationInfo().processName;
        this.processName = getProcessName(this.context);
        this.is64Bit = StubManifest.is64bitPackageName(this.hostPkgName);
        if (this.processName.equals(this.mainProcessName)) {
            this.processType = ProcessType.Main;
        } else if (this.processName.endsWith(Constants.SERVER_PROCESS_NAME)) {
            this.processType = ProcessType.Server;
        } else if (this.processName.endsWith(Constants.HELPER_PROCESS_NAME)) {
            this.processType = ProcessType.Helper;
        } else if (VActivityManager.get().isAppProcess(this.processName)) {
            this.processType = ProcessType.VAppClient;
        } else {
            this.processType = ProcessType.CHILD;
        }
    }

    public boolean is64BitEngine() {
        return this.is64Bit;
    }

    private IAppManager getService() {
        if (!IInterfaceUtils.isAlive(this.mService)) {
            synchronized (this) {
                this.mService = (IAppManager) LocalProxyUtils.genProxy(IAppManager.class, getStubInterface());
            }
        }
        return this.mService;
    }

    private Object getStubInterface() {
        return IAppManager.Stub.asInterface(ServiceManagerNative.getService("app"));
    }

    public boolean isVAppProcess() {
        return ProcessType.VAppClient == this.processType;
    }

    public boolean is64bitHelperProcess() {
        return ProcessType.Helper == this.processType;
    }

    public boolean isMainProcess() {
        return ProcessType.Main == this.processType;
    }

    public boolean isChildProcess() {
        return ProcessType.CHILD == this.processType;
    }

    public boolean isServerProcess() {
        return ProcessType.Server == this.processType;
    }

    public String getProcessName() {
        return this.processName;
    }

    public String getMainProcessName() {
        return this.mainProcessName;
    }

    public boolean isAppRunning(String str, int i, boolean z) {
        return VActivityManager.get().isAppRunning(str, i, z);
    }

    public InstallResult installPackageSync(String str, InstallOptions installOptions) {
        final ConditionVariable conditionVariable = new ConditionVariable();
        final InstallResult[] installResultArr = new InstallResult[1];
        installPackage(str, installOptions, new InstallCallback() { // from class: com.lody.virtual.client.core.VirtualCore.3
            @Override // com.lody.virtual.client.core.VirtualCore.InstallCallback
            public void onFinish(InstallResult installResult) {
                installResultArr[0] = installResult;
                conditionVariable.open();
            }
        });
        conditionVariable.block();
        return installResultArr[0];
    }

    @Deprecated
    public InstallResult installPackage(String str, InstallOptions installOptions) {
        return installPackageSync(str, installOptions);
    }

    public void installPackage(String str, InstallOptions installOptions, final InstallCallback installCallback) {
        try {
            getService().installPackage(str, installOptions, new ResultReceiver(null) { // from class: com.lody.virtual.client.core.VirtualCore.4
                @Override // android.os.ResultReceiver
                protected void onReceiveResult(int i, Bundle bundle) {
                    bundle.setClassLoader(InstallResult.class.getClassLoader());
                    if (installCallback != null) {
                        installCallback.onFinish((InstallResult) bundle.getParcelable(C4985cm.f20833c));
                    }
                }
            });
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public InstallResult installPackageFromAsset(String str, InstallOptions installOptions) {
        InputStream inputStream = null;
        try {
            inputStream = getContext().getAssets().open(str);
            return installPackageFromStream(inputStream, installOptions);
        } catch (Throwable th) {
            try {
                InstallResult installResult = new InstallResult();
                installResult.error = th.getMessage();
                return installResult;
            } finally {
                FileUtils.closeQuietly(inputStream);
            }
        }
    }

    public InstallResult installPackageFromStream(InputStream inputStream, InstallOptions installOptions) {
        try {
            File cacheDir = getContext().getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File file = new File(cacheDir, "tmp_" + System.currentTimeMillis() + ".apk");
            FileUtils.writeToFile(inputStream, file);
            InstallResult installPackageSync = installPackageSync(file.getAbsolutePath(), installOptions);
            file.delete();
            return installPackageSync;
        } catch (Throwable th) {
            InstallResult installResult = new InstallResult();
            installResult.error = th.getMessage();
            return installResult;
        }
    }

    public void addVisibleOutsidePackage(String str) {
        try {
            getService().addVisibleOutsidePackage(str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void removeVisibleOutsidePackage(String str) {
        try {
            getService().removeVisibleOutsidePackage(str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isOutsidePackageVisible(String str) {
        try {
            return getService().isOutsidePackageVisible(str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean isAppInstalled(String str) {
        try {
            return getService().isAppInstalled(str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean isPackageLaunchable(String str) {
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(str, 0);
        return (installedAppInfo == null || getLaunchIntent(str, installedAppInfo.getInstalledUsers()[0]) == null) ? false : true;
    }

    public Intent getLaunchIntent(String str, int i) {
        VPackageManager vPackageManager = VPackageManager.get();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.INFO");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = vPackageManager.queryIntentActivities(intent, intent.resolveType(this.context), 0, i);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            intent.removeCategory("android.intent.category.INFO");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(str);
            queryIntentActivities = vPackageManager.queryIntentActivities(intent, intent.resolveType(this.context), 0, i);
        }
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        intent2.setFlags(268435456);
        intent2.setClassName(queryIntentActivities.get(0).activityInfo.packageName, queryIntentActivities.get(0).activityInfo.name);
        return intent2;
    }

    public boolean createShortcut(int i, String str, OnEmitShortcutListener onEmitShortcutListener) {
        return createShortcut(i, str, null, onEmitShortcutListener);
    }

    public boolean createShortcut(int i, String str, Intent intent, OnEmitShortcutListener onEmitShortcutListener) {
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(str, 0);
        if (installedAppInfo == null) {
            return false;
        }
        ApplicationInfo applicationInfo = installedAppInfo.getApplicationInfo(i);
        PackageManager packageManager = this.context.getPackageManager();
        try {
            String charSequence = applicationInfo.loadLabel(packageManager).toString();
            Bitmap drawableToBitmap = BitmapUtils.drawableToBitmap(applicationInfo.loadIcon(packageManager));
            if (onEmitShortcutListener != null) {
                String name = onEmitShortcutListener.getName(charSequence);
                if (name != null) {
                    charSequence = name;
                }
                Bitmap icon = onEmitShortcutListener.getIcon(drawableToBitmap);
                if (icon != null) {
                    drawableToBitmap = icon;
                }
            }
            Intent launchIntent = getLaunchIntent(str, i);
            if (launchIntent == null) {
                return false;
            }
            Intent wrapperShortcutIntent = wrapperShortcutIntent(launchIntent, intent, str, i);
            if (Build.VERSION.SDK_INT >= 26) {
                Context context = getContext();
                ShortcutInfo build = new ShortcutInfo.Builder(context, str + "@" + i).setLongLabel(charSequence).setShortLabel(charSequence).setIcon(Icon.createWithBitmap(drawableToBitmap)).setIntent(wrapperShortcutIntent).build();
                ShortcutManager shortcutManager = (ShortcutManager) getContext().getSystemService(ShortcutManager.class);
                if (shortcutManager == null) {
                    return true;
                }
                try {
                    shortcutManager.requestPinShortcut(build, PendingIntent.getActivity(getContext(), str.hashCode() + i, wrapperShortcutIntent, 134217728).getIntentSender());
                    return true;
                } catch (Throwable unused) {
                    return false;
                }
            } else {
                Intent intent2 = new Intent();
                intent2.putExtra("android.intent.extra.shortcut.INTENT", wrapperShortcutIntent);
                intent2.putExtra("android.intent.extra.shortcut.NAME", charSequence);
                intent2.putExtra("android.intent.extra.shortcut.ICON", BitmapUtils.warrperIcon(drawableToBitmap, 256, 256));
                intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                try {
                    this.context.sendBroadcast(intent2);
                    return true;
                } catch (Throwable unused2) {
                    return false;
                }
            }
        } catch (Throwable unused3) {
            return false;
        }
    }

    public boolean removeShortcut(int i, String str, Intent intent, OnEmitShortcutListener onEmitShortcutListener) {
        String str2;
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(str, 0);
        if (installedAppInfo == null) {
            return false;
        }
        try {
            String charSequence = installedAppInfo.getApplicationInfo(i).loadLabel(this.context.getPackageManager()).toString();
            if (onEmitShortcutListener == null || (str2 = onEmitShortcutListener.getName(charSequence)) == null) {
                str2 = charSequence;
            }
            Intent launchIntent = getLaunchIntent(str, i);
            if (launchIntent == null) {
                return false;
            }
            Intent wrapperShortcutIntent = wrapperShortcutIntent(launchIntent, intent, str, i);
            if (Build.VERSION.SDK_INT >= 26) {
                return true;
            }
            Intent intent2 = new Intent();
            intent2.putExtra("android.intent.extra.shortcut.INTENT", wrapperShortcutIntent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent2.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
            this.context.sendBroadcast(intent2);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public Intent wrapperShortcutIntent(Intent intent, Intent intent2, String str, int i) {
        Intent intent3 = new Intent();
        intent3.addCategory("android.intent.category.DEFAULT");
        intent3.setAction(Constants.ACTION_SHORTCUT);
        intent3.setPackage(getHostPkg());
        if (intent2 != null) {
            intent3.putExtra("_VA_|_splash_", intent2.toUri(0));
        }
        intent3.putExtra("_VA_|_pkg_", str);
        intent3.putExtra("_VA_|_uri_", intent.toUri(0));
        intent3.putExtra("_VA_|_user_id_", i);
        return intent3;
    }

    public InstalledAppInfo getInstalledAppInfo(String str, int i) {
        try {
            return getService().getInstalledAppInfo(str, i);
        } catch (RemoteException e) {
            return (InstalledAppInfo) VirtualRuntime.crash(e);
        }
    }

    public int getInstalledAppCount() {
        try {
            return getService().getInstalledAppCount();
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public boolean isStartup() {
        return this.isStartUp;
    }

    public boolean uninstallPackageAsUser(String str, int i) {
        try {
            this.vaCallBack.fixFor2(str, i);
            return getService().uninstallPackageAsUser(str, i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean uninstallPackage(String str) {
        try {
            this.vaCallBack.fixFor2(str, 0);
            return getService().uninstallPackage(str);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public Resources getResources(String str) throws Resources.NotFoundException {
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(str, 0);
        if (installedAppInfo != null) {
            AssetManager newInstance = p110z1.AssetManager.ctor.newInstance();
            p110z1.AssetManager.addAssetPath.call(newInstance, installedAppInfo.getApkPath());
            Resources resources = this.context.getResources();
            return new Resources(newInstance, resources.getDisplayMetrics(), resources.getConfiguration());
        }
        throw new Resources.NotFoundException(str);
    }

    public synchronized ActivityInfo resolveActivityInfo(Intent intent, int i) {
        ActivityInfo activityInfo = null;
        if (SpecialComponentList.shouldBlockIntent(intent)) {
            return null;
        }
        if (intent.getComponent() == null) {
            ResolveInfo resolveIntent = VPackageManager.get().resolveIntent(intent, intent.getType(), 0, i);
            if (!(resolveIntent == null || resolveIntent.activityInfo == null)) {
                activityInfo = resolveIntent.activityInfo;
                intent.setClassName(activityInfo.packageName, activityInfo.name);
            }
        } else {
            activityInfo = resolveActivityInfo(intent.getComponent(), i);
        }
        return activityInfo;
    }

    public ActivityInfo resolveActivityInfo(ComponentName componentName, int i) {
        return VPackageManager.get().getActivityInfo(componentName, 0, i);
    }

    public ServiceInfo resolveServiceInfo(Intent intent, int i) {
        ResolveInfo resolveService;
        if (!SpecialComponentList.shouldBlockIntent(intent) && (resolveService = VPackageManager.get().resolveService(intent, intent.getType(), 0, i)) != null) {
            return resolveService.serviceInfo;
        }
        return null;
    }

    public void killApp(String str, int i) {
        VActivityManager.get().killAppByPkg(str, i);
    }

    public void killAllApps() {
        VActivityManager.get().killAllApps();
    }

    public List<InstalledAppInfo> getInstalledApps(int i) {
        try {
            return getService().getInstalledApps(i);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<InstalledAppInfo> getInstalledAppsAsUser(int i, int i2) {
        try {
            return getService().getInstalledAppsAsUser(i, i2);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public void scanApps() {
        try {
            getService().scanApps();
        } catch (RemoteException unused) {
        }
    }

    public AppRequestListener getAppRequestListener() {
        return this.mAppRequestListener;
    }

    public void setAppRequestListener(AppRequestListener appRequestListener) {
        this.mAppRequestListener = appRequestListener;
    }

    public boolean isPackageLaunched(int i, String str) {
        try {
            return getService().isPackageLaunched(i, str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setPackageHidden(int i, String str, boolean z) {
        try {
            getService().setPackageHidden(i, str, z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean installPackageAsUser(int i, String str) {
        try {
            return getService().installPackageAsUser(i, str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean isAppInstalledAsUser(int i, String str) {
        try {
            return getService().isAppInstalledAsUser(i, str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public int[] getPackageInstalledUsers(String str) {
        try {
            return getService().getPackageInstalledUsers(str);
        } catch (RemoteException e) {
            return (int[]) VirtualRuntime.crash(e);
        }
    }

    public void registerObserver(IPackageObserver iPackageObserver) {
        try {
            getService().registerObserver(iPackageObserver);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void unregisterObserver(IPackageObserver iPackageObserver) {
        try {
            getService().unregisterObserver(iPackageObserver);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isOutsideInstalled(String str) {
        if (str == null) {
            return false;
        }
        try {
            return this.unHookPackageManager.getApplicationInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean is64BitEngineInstalled() {
        return isOutsideInstalled(StubManifest.PACKAGE_NAME_64BIT);
    }

    public boolean isRun64BitProcess(String str) {
        try {
            return getService().isRun64BitProcess(str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean cleanPackageData(String str, int i) {
        try {
            return getService().cleanPackageData(str, i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }
}
