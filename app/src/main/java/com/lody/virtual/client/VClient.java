package com.lody.virtual.client;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Instrumentation;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StrictMode;
import android.support.p003v4.app.NotificationCompat;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.IVClient;
import com.lody.virtual.client.core.CrashHandler;
import com.lody.virtual.client.core.InvocationStubManager;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.fixer.ContextFixer;
import com.lody.virtual.client.hook.delegate.AppInstrumentation;
import com.lody.virtual.client.hook.providers.ProviderHook;
import com.lody.virtual.client.hook.proxies.p059am.HCallbackStub;
import com.lody.virtual.client.hook.secondary.ProxyServiceFactory;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.client.ipc.VirtualStorageManager;
import com.lody.virtual.client.receiver.StaticReceiverSystem;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.ComposeClassLoader;
import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.NativeLibraryHelperCompat;
import com.lody.virtual.helper.compat.StorageManagerCompat;
import com.lody.virtual.helper.compat.StrictModeCompat;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.server.secondary.FakeIdentityBinder;
import java.io.File;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mirror.RefMethod;
import mirror.RefStaticMethod;
import p110z1.ActivityManagerNative;
import p110z1.ActivityThread;
import p110z1.ActivityThreadNMR1;
import p110z1.ActivityThreadQ;
import p110z1.BroadcastReceiver;
import p110z1.C4963cj;
import p110z1.CompatibilityInfo;
import p110z1.CompatibilityInfoHolder;
import p110z1.ContentProviderHolderOreo;
import p110z1.ContextImpl;
import p110z1.ContextImplKitkat;
import p110z1.DisplayAdjustments;
import p110z1.HardwareRenderer;
import p110z1.HwApiCacheManagerEx;
import p110z1.IActivityManager;
import p110z1.LoadedApk;
import p110z1.LoadedApkICS;
import p110z1.LoadedApkKitkat;
import p110z1.NetworkSecurityConfigProvider;
import p110z1.ReferrerIntent;
import p110z1.RenderScript;
import p110z1.RenderScriptCacheDir;
import p110z1.ThreadGroup;
import p110z1.ThreadGroupN;
import p110z1.ThreadedRenderer;
import p110z1.VMRuntime;
import p110z1.cwt;
import patch.MyFixer;
import patch.Socksvr;

/* loaded from: classes.dex */
public final class VClient extends IVClient.Stub {
    private static final int BIND_SERVICE = 17;
    private static final int CREATE_SERVICE = 14;
    private static final int FINISH_ACTIVITY = 13;
    private static final int NEW_INTENT = 11;
    private static final int RECEIVER = 12;
    private static final int SERVICE_ARGS = 15;
    private static final int STOP_SERVICE = 16;
    private static final String TAG = "VClient";
    private static final int UNBIND_SERVICE = 18;
    @SuppressLint({"StaticFieldLeak"})
    private static final VClient gClient = new VClient();
    private ClientConfig clientConfig;
    private CrashHandler crashHandler;
    private InstalledAppInfo mAppInfo;
    private ConditionVariable mBindingApplicationLock;
    private AppBindData mBoundApplication;
    private Application mInitialApplication;
    private int mTargetSdkVersion;

    /* renamed from: mH */
    private final HandlerC1719H f10493mH = new HandlerC1719H();
    private final ArrayMap<IBinder, Service> mServices = new ArrayMap<>();
    private Instrumentation mInstrumentation = AppInstrumentation.getDefault();
    private boolean mEnvironmentPrepared = false;
    private Set<String> mExportedVApiPkgs = new HashSet();

    private VClient() {
    }

    public synchronized void addExportedVApiPkg(String str) {
        this.mExportedVApiPkgs.add(str);
    }

    public InstalledAppInfo getAppInfo() {
        return this.mAppInfo;
    }

    public static VClient get() {
        return gClient;
    }

    public boolean isEnvironmentPrepared() {
        return this.mEnvironmentPrepared;
    }

    public boolean isAppUseOutsideAPK() {
        InstalledAppInfo appInfo = getAppInfo();
        return appInfo != null && appInfo.appMode == 1;
    }

    public VDeviceConfig getDeviceConfig() {
        return VDeviceManager.get().getDeviceConfig(VUserHandle.getUserId(getVUid()));
    }

    public Application getCurrentApplication() {
        return this.mInitialApplication;
    }

    public String getCurrentPackage() {
        AppBindData appBindData = this.mBoundApplication;
        return appBindData != null ? appBindData.appInfo.packageName : VPackageManager.get().getNameForUid(getVUid());
    }

    public ApplicationInfo getCurrentApplicationInfo() {
        AppBindData appBindData = this.mBoundApplication;
        if (appBindData != null) {
            return appBindData.appInfo;
        }
        return null;
    }

    public int getCurrentTargetSdkVersion() {
        int i = this.mTargetSdkVersion;
        return i == 0 ? VirtualCore.get().getTargetSdkVersion() : i;
    }

    public CrashHandler getCrashHandler() {
        return this.crashHandler;
    }

    public void setCrashHandler(CrashHandler crashHandler) {
        this.crashHandler = crashHandler;
    }

    public int getVUid() {
        ClientConfig clientConfig = this.clientConfig;
        if (clientConfig == null) {
            return 0;
        }
        return clientConfig.vuid;
    }

    public int getVUserHandle() {
        ClientConfig clientConfig = this.clientConfig;
        if (clientConfig == null) {
            return 0;
        }
        return VUserHandle.getUserId(clientConfig.vuid);
    }

    public int getVpid() {
        ClientConfig clientConfig = this.clientConfig;
        if (clientConfig == null) {
            return 0;
        }
        return clientConfig.vpid;
    }

    public int getBaseVUid() {
        ClientConfig clientConfig = this.clientConfig;
        if (clientConfig == null) {
            return 0;
        }
        return VUserHandle.getAppId(clientConfig.vuid);
    }

    public int getCallingVUid() {
        return VActivityManager.get().getCallingUid();
    }

    public ClassLoader getClassLoader(ApplicationInfo applicationInfo) {
        return createPackageContext(applicationInfo.packageName).getClassLoader();
    }

    private void sendMessage(int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        this.f10493mH.sendMessage(obtain);
    }

    @Override // com.lody.virtual.client.IVClient
    public IBinder getAppThread() {
        return ActivityThread.getApplicationThread.call(VirtualCore.mainThread(), new Object[0]);
    }

    @Override // com.lody.virtual.client.IVClient
    public IBinder getToken() {
        ClientConfig clientConfig = this.clientConfig;
        if (clientConfig == null) {
            return null;
        }
        return clientConfig.token;
    }

    public ClientConfig getClientConfig() {
        return this.clientConfig;
    }

    @Override // com.lody.virtual.client.IVClient
    public boolean isAppRunning() {
        return this.mBoundApplication != null;
    }

    public void initProcess(ClientConfig clientConfig) {
        if (this.clientConfig == null) {
            this.clientConfig = clientConfig;
            return;
        }
        throw new RuntimeException("reject init process: " + clientConfig.processName + ", this process is : " + this.clientConfig.processName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewIntent(NewIntentData newIntentData) {
        Intent intent;
        if (Build.VERSION.SDK_INT >= 22) {
            intent = ReferrerIntent.ctor.newInstance(newIntentData.intent, newIntentData.creator);
        } else {
            intent = newIntentData.intent;
        }
        if (ActivityThread.performNewIntents != null) {
            ActivityThread.performNewIntents.call(VirtualCore.mainThread(), newIntentData.token, Collections.singletonList(intent));
        } else if (ActivityThreadNMR1.performNewIntents != null) {
            ActivityThreadNMR1.performNewIntents.call(VirtualCore.mainThread(), newIntentData.token, Collections.singletonList(intent), true);
        } else {
            ActivityThreadQ.handleNewIntent.call(VirtualCore.mainThread(), newIntentData.token, Collections.singletonList(intent));
        }
    }

    public void bindApplication(final String str, final String str2) {
        if (this.clientConfig == null) {
            throw new RuntimeException("Unrecorded process: " + str2);
        } else if (!isAppRunning()) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                ConditionVariable conditionVariable = this.mBindingApplicationLock;
                if (conditionVariable != null) {
                    conditionVariable.block();
                    this.mBindingApplicationLock = null;
                } else {
                    this.mBindingApplicationLock = new ConditionVariable();
                }
                VirtualRuntime.getUIHandler().post(new Runnable() { // from class: com.lody.virtual.client.VClient.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VClient.this.bindApplicationNoCheck(str, str2);
                        ConditionVariable conditionVariable2 = VClient.this.mBindingApplicationLock;
                        VClient.this.mBindingApplicationLock = null;
                        if (conditionVariable2 != null) {
                            conditionVariable2.open();
                        }
                    }
                });
                ConditionVariable conditionVariable2 = this.mBindingApplicationLock;
                if (conditionVariable2 != null) {
                    conditionVariable2.block();
                    return;
                }
                return;
            }
            bindApplicationNoCheck(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindApplicationNoCheck(String str, String str2) {
        File file;
        int i;
        boolean z;
        String[] strArr;
        if (!isAppRunning()) {
            String str3 = str2 == null ? str : str2;
            try {
                setupUncaughtHandler();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            int userId = VUserHandle.getUserId(getVUid());
            try {
                fixInstalledProviders();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            VDeviceManager.get().applyBuildProp(getDeviceConfig());
            boolean is64BitEngine = VirtualCore.get().is64BitEngine();
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                    instance.load(null);
                    Enumeration<String> aliases = instance.aliases();
                    while (aliases.hasMoreElements()) {
                        String nextElement = aliases.nextElement();
                        VLog.m18986w(TAG, "remove entry: " + nextElement, new Object[0]);
                        instance.deleteEntry(nextElement);
                    }
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
            ActivityThread.mInitialApplication.set(VirtualCore.mainThread(), null);
            AppBindData appBindData = new AppBindData();
            InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(str, 0);
            if (installedAppInfo == null) {
                new Exception("app not exist").printStackTrace();
                Process.killProcess(0);
                System.exit(0);
            }
            this.mAppInfo = installedAppInfo;
            appBindData.appInfo = VPackageManager.get().getApplicationInfo(str, 0, userId);
            appBindData.processName = str3;
            appBindData.providers = VPackageManager.get().queryContentProviders(str3, getVUid(), 128);
            Iterator<ProviderInfo> it = appBindData.providers.iterator();
            while (it.hasNext()) {
                if (!it.next().enabled) {
                    it.remove();
                }
            }
            this.mTargetSdkVersion = appBindData.appInfo.targetSdkVersion;
            VLog.m18993d(TAG, "Binding application %s (%s [%d])", appBindData.appInfo.packageName, appBindData.processName, Integer.valueOf(Process.myPid()));
            this.mBoundApplication = appBindData;
            VirtualRuntime.setupRuntime(appBindData.processName, appBindData.appInfo);
            if (VirtualCore.get().is64BitEngine()) {
                File file2 = new File(installedAppInfo.getApkPath());
                File file3 = new File(appBindData.appInfo.nativeLibraryDir);
                if (!file2.exists()) {
                    VirtualCore.get().requestCopyPackage64(str);
                }
                String[] list = file3.list();
                if (list == null || list.length == 0) {
                    NativeLibraryHelperCompat.copyNativeBinaries(file2, file3);
                }
            }
            int i2 = appBindData.appInfo.targetSdkVersion;
            if (i2 < 9) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy()).permitNetwork().build());
            }
            if (Build.VERSION.SDK_INT >= 24 && VirtualCore.get().getTargetSdkVersion() >= 24 && i2 < 24) {
                StrictModeCompat.disableDeathOnFileUriExposure();
            }
            if (Build.VERSION.SDK_INT >= 21 && i2 < 21) {
                p110z1.Message.updateCheckRecycle.call(Integer.valueOf(i2));
            }
            AlarmManager alarmManager = (AlarmManager) VirtualCore.get().getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (Build.VERSION.SDK_INT >= 19 && p110z1.AlarmManager.mTargetSdkVersion != null) {
                try {
                    p110z1.AlarmManager.mTargetSdkVersion.set(alarmManager, i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (is64BitEngine) {
                System.setProperty("java.io.tmpdir", new File(VEnvironment.getDataUserPackageDirectory64(userId, installedAppInfo.packageName), "cache").getAbsolutePath());
            } else {
                System.setProperty("java.io.tmpdir", new File(VEnvironment.getDataUserPackageDirectory(userId, installedAppInfo.packageName), "cache").getAbsolutePath());
            }
            NativeEngine.launchEngine(str);
            if (VirtualCore.getConfig().isEnableIORedirect()) {
                if (VirtualCore.get().isIORelocateWork()) {
                    startIORelocater(installedAppInfo, is64BitEngine);
                } else {
                    VLog.m18986w(TAG, "IO Relocate verify fail.", new Object[0]);
                }
            }
            this.mEnvironmentPrepared = true;
            Object mainThread = VirtualCore.mainThread();
            NativeEngine.startDexOverride();
            initDataStorage(is64BitEngine, userId, str);
            StaticReceiverSystem.get().attach(str3, VirtualCore.get().getContext(), appBindData.appInfo, userId);
            Context createPackageContext = createPackageContext(appBindData.appInfo.packageName);
            if (Build.VERSION.SDK_INT >= 23) {
                file = createPackageContext.getCodeCacheDir();
            } else {
                file = createPackageContext.getCacheDir();
            }
            if (Build.VERSION.SDK_INT < 24) {
                if (HardwareRenderer.setupDiskCache != null) {
                    HardwareRenderer.setupDiskCache.call(file);
                }
            } else if (ThreadedRenderer.setupDiskCache != null) {
                ThreadedRenderer.setupDiskCache.call(file);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                if (RenderScriptCacheDir.setupDiskCache != null) {
                    RenderScriptCacheDir.setupDiskCache.call(file);
                }
            } else if (Build.VERSION.SDK_INT >= 16 && RenderScript.setupDiskCache != null) {
                RenderScript.setupDiskCache.call(file);
            }
            this.mBoundApplication.info = ContextImpl.mPackageInfo.get(createPackageContext);
            Object obj = ActivityThread.mBoundApplication.get(VirtualCore.mainThread());
            ActivityThread.C5106b.appInfo.set(obj, appBindData.appInfo);
            ActivityThread.C5106b.processName.set(obj, appBindData.processName);
            ActivityThread.C5106b.instrumentationName.set(obj, new ComponentName(appBindData.appInfo.packageName, Instrumentation.class.getName()));
            ActivityThread.C5106b.info.set(obj, appBindData.info);
            ActivityThread.C5106b.providers.set(obj, appBindData.providers);
            if (LoadedApk.mSecurityViolation != null) {
                LoadedApk.mSecurityViolation.set(this.mBoundApplication.info, false);
            }
            VMRuntime.setTargetSdkVersion.call(VMRuntime.getRuntime.call(new Object[0]), Integer.valueOf(appBindData.appInfo.targetSdkVersion));
            Configuration configuration = createPackageContext.getResources().getConfiguration();
            if (!is64BitEngine && installedAppInfo.flag == 1 && Build.VERSION.SDK_INT >= 21) {
                LinkedList linkedList = new LinkedList();
                for (String str4 : Build.SUPPORTED_ABIS) {
                    if (NativeLibraryHelperCompat.is32bitAbi(str4)) {
                        linkedList.add(str4);
                    }
                }
                Reflect.m18999on((Class<?>) Build.class).set("SUPPORTED_ABIS", (String[]) linkedList.toArray(new String[0]));
            }
            Object newInstance = CompatibilityInfo.ctor != null ? CompatibilityInfo.ctor.newInstance(appBindData.appInfo, Integer.valueOf(configuration.screenLayout), Integer.valueOf(configuration.smallestScreenWidthDp), false) : null;
            if (CompatibilityInfo.ctorLG != null) {
                newInstance = CompatibilityInfo.ctorLG.newInstance(appBindData.appInfo, Integer.valueOf(configuration.screenLayout), Integer.valueOf(configuration.smallestScreenWidthDp), false, 0);
            }
            if (newInstance == null) {
                i = 1;
            } else if (Build.VERSION.SDK_INT >= 19) {
                if (Build.VERSION.SDK_INT < 24) {
                    i = 1;
                    DisplayAdjustments.setCompatibilityInfo.call(ContextImplKitkat.mDisplayAdjustments.get(createPackageContext), newInstance);
                } else {
                    i = 1;
                }
                RefMethod<Void> hVar = DisplayAdjustments.setCompatibilityInfo;
                Object obj2 = LoadedApkKitkat.mDisplayAdjustments.get(this.mBoundApplication.info);
                Object[] objArr = new Object[i];
                objArr[0] = newInstance;
                hVar.call(obj2, objArr);
            } else {
                i = 1;
                CompatibilityInfoHolder.set.call(LoadedApkICS.mCompatibilityInfo.get(this.mBoundApplication.info), newInstance);
            }
            if (NetworkSecurityConfigProvider.install != null) {
                Security.removeProvider("AndroidNSSP");
                RefStaticMethod<Void> kVar = NetworkSecurityConfigProvider.install;
                Object[] objArr2 = new Object[i];
                objArr2[0] = createPackageContext;
                kVar.call(objArr2);
            }
            fixForEmui10();
            VirtualCore.get().getAppCallback().beforeStartApplication(str, str3, createPackageContext);
            if (!this.mExportedVApiPkgs.contains(str) || LoadedApk.mClassLoader == null) {
                z = is64BitEngine;
            } else {
                z = is64BitEngine;
                LoadedApk.mClassLoader.set(appBindData.info, new ComposeClassLoader(VClient.class.getClassLoader(), LoadedApk.getClassLoader.call(appBindData.info, new Object[0])));
            }
            try {
                this.mInitialApplication = LoadedApk.makeApplication.call(appBindData.info, false, null);
                MyFixer.m14536e(this.mInitialApplication);
                if (VirtualCore.get().getVaCallBack() != null) {
                    VirtualCore.get().getVaCallBack().startYafaVa(createPackageContext);
                }
                ActivityThread.mInitialApplication.set(mainThread, this.mInitialApplication);
                ContextFixer.fixContext(this.mInitialApplication);
                if (Build.VERSION.SDK_INT >= 24 && "com.tencent.mm:recovery".equals(str3)) {
                    fixWeChatRecovery(this.mInitialApplication);
                }
                if (GmsSupport.VENDING_PKG.equals(str)) {
                    try {
                        createPackageContext.getSharedPreferences("vending_preferences", 0).edit().putBoolean("notify_updates", false).putBoolean("notify_updates_completion", false).apply();
                        createPackageContext.getSharedPreferences("finsky", 0).edit().putBoolean("auto_update_enabled", false).apply();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                List<ProviderInfo> list2 = ActivityThread.C5106b.providers.get(obj);
                if (list2 != null && !list2.isEmpty()) {
                    installContentProviders(this.mInitialApplication, list2);
                }
                VirtualCore.get().getAppCallback().beforeApplicationCreate(str, str3, this.mInitialApplication);
                try {
                    this.mInstrumentation.callApplicationOnCreate(this.mInitialApplication);
                    InvocationStubManager.getInstance().checkEnv(HCallbackStub.class);
                    InvocationStubManager.getInstance().checkEnv(AppInstrumentation.class);
                    Application application = ActivityThread.mInitialApplication.get(mainThread);
                    if (application != null) {
                        this.mInitialApplication = application;
                    }
                } catch (Exception e2) {
                    if (!this.mInstrumentation.onException(this.mInitialApplication, e2)) {
                        throw new RuntimeException("Unable to create application " + appBindData.appInfo.name + ": " + e2.toString(), e2);
                    }
                }
                if (VirtualCore.get().getVaCallBack() != null) {
                    VirtualCore.get().getVaCallBack().fixForAll(createPackageContext, this.mInitialApplication, installedAppInfo, this.mBoundApplication.appInfo.processName);
                }
                if (VirtualCore.get().getAppCallback() != null) {
                    VirtualCore.get().getAppCallback().afterApplicationCreate(str, str3, this.mInitialApplication);
                }
                VActivityManager.get().appDoneExecuting(installedAppInfo.packageName);
                Socksvr.m14562a(this.mInitialApplication, z);
            } catch (Throwable th5) {
                throw new RuntimeException("Unable to makeApplication", th5);
            }
        }
    }

    private void initDataStorage(boolean z, int i, String str) {
        if (z) {
            VEnvironment.getDataUserPackageDirectory64(i, str);
            VEnvironment.getDeDataUserPackageDirectory64(i, str);
            return;
        }
        VEnvironment.getDataUserPackageDirectory(i, str);
        VEnvironment.getDeDataUserPackageDirectory(i, str);
    }

    private void fixWeChatRecovery(Application application) {
        try {
            Field field = application.getClassLoader().loadClass("com.tencent.recovery.Recovery").getField("context");
            field.setAccessible(true);
            if (field.get(null) == null) {
                field.set(null, application.getBaseContext());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @SuppressLint({"NewApi"})
    private void fixForEmui10() {
        if (BuildCompat.isQ() && BuildCompat.isEMUI() && HwApiCacheManagerEx.mPkg != null) {
            HwApiCacheManagerEx.mPkg.set(HwApiCacheManagerEx.getDefault.call(new Object[0]), VirtualCore.getPM());
        }
    }

    private void setupUncaughtHandler() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        RootThreadGroup rootThreadGroup = new RootThreadGroup(threadGroup);
        if (Build.VERSION.SDK_INT < 24) {
            List<ThreadGroup> list = ThreadGroup.groups.get(threadGroup);
            synchronized (list) {
                ArrayList<ThreadGroup> arrayList = new ArrayList(list);
                arrayList.remove(rootThreadGroup);
                ThreadGroup.groups.set(rootThreadGroup, arrayList);
                list.clear();
                list.add(rootThreadGroup);
                ThreadGroup.groups.set(threadGroup, list);
                for (ThreadGroup threadGroup2 : arrayList) {
                    if (threadGroup2 != rootThreadGroup) {
                        ThreadGroup.parent.set(threadGroup2, rootThreadGroup);
                    }
                }
            }
            return;
        }
        ThreadGroup[] threadGroupArr = ThreadGroupN.groups.get(threadGroup);
        synchronized (threadGroupArr) {
            ThreadGroup[] threadGroupArr2 = (ThreadGroup[]) threadGroupArr.clone();
            ThreadGroupN.groups.set(rootThreadGroup, threadGroupArr2);
            ThreadGroupN.groups.set(threadGroup, new ThreadGroup[]{rootThreadGroup});
            for (ThreadGroup threadGroup3 : threadGroupArr2) {
                if (!(threadGroup3 == null || threadGroup3 == rootThreadGroup)) {
                    ThreadGroupN.parent.set(threadGroup3, rootThreadGroup);
                }
            }
            ThreadGroupN.ngroups.set(threadGroup, 1);
        }
    }

    @SuppressLint({"SdCardPath"})
    private void startIORelocater(InstalledAppInfo installedAppInfo, boolean z) {
        String str;
        String str2;
        String str3;
        File wifiFile;
        String str4 = installedAppInfo.packageName;
        int myUserId = VUserHandle.myUserId();
        if (z) {
            str3 = VEnvironment.getDataUserPackageDirectory64(myUserId, str4).getPath();
            str2 = VEnvironment.getDeDataUserPackageDirectory64(myUserId, str4).getPath();
            str = VEnvironment.getAppLibDirectory64(str4).getAbsolutePath();
        } else {
            str3 = VEnvironment.getDataUserPackageDirectory(myUserId, str4).getPath();
            str2 = VEnvironment.getDeDataUserPackageDirectory(myUserId, str4).getPath();
            str = VEnvironment.getAppLibDirectory(str4).getAbsolutePath();
        }
        if (getDeviceConfig().enable && (wifiFile = getDeviceConfig().getWifiFile(myUserId, z)) != null && wifiFile.exists()) {
            String path = wifiFile.getPath();
            NativeEngine.redirectFile("/sys/class/net/wlan0/address", path);
            NativeEngine.redirectFile("/sys/class/net/eth0/address", path);
            NativeEngine.redirectFile("/sys/class/net/wifi/address", path);
        }
        LinuxCompat.forgeProcDriver(z);
        forbidHost();
        NativeEngine.redirectDirectory("/tmp/", new File(str3, "cache").getAbsolutePath());
        NativeEngine.redirectDirectory("/data/data/" + str4, str3);
        NativeEngine.redirectDirectory("/data/user/" + VUserHandle.realUserId() + "/" + str4, str3);
        if (Build.VERSION.SDK_INT >= 24) {
            NativeEngine.redirectDirectory("/data/user_de/" + VUserHandle.realUserId() + "/" + str4, str2);
        }
        SettingConfig.AppLibConfig appLibConfig = VirtualCore.getConfig().getAppLibConfig(str4);
        if (appLibConfig == SettingConfig.AppLibConfig.UseRealLib && (installedAppInfo.appMode != 1 || !VirtualCore.get().isOutsideInstalled(installedAppInfo.packageName))) {
            appLibConfig = SettingConfig.AppLibConfig.UseOwnLib;
        }
        NativeEngine.whitelist(str);
        NativeEngine.whitelist("/data/user/" + VUserHandle.realUserId() + "/" + str4 + "/lib/");
        if (appLibConfig == SettingConfig.AppLibConfig.UseOwnLib) {
            NativeEngine.redirectDirectory("/data/data/" + str4 + "/lib/", str);
            NativeEngine.redirectDirectory("/data/user/" + VUserHandle.realUserId() + "/" + str4 + "/lib/", str);
        } else {
            NativeEngine.redirectDirectory("/data/data/" + str4 + "/lib/", installedAppInfo.getApplicationInfo(VUserHandle.realUserId()).nativeLibraryDir);
            NativeEngine.whitelist("/data/user/" + VUserHandle.realUserId() + "/" + str4 + "/lib/");
        }
        NativeEngine.redirectDirectory(VEnvironment.getUserAppLibDirectory(myUserId, str4).getPath(), str);
        VirtualStorageManager virtualStorageManager = VirtualStorageManager.get();
        String virtualStorage = virtualStorageManager.getVirtualStorage(installedAppInfo.packageName, myUserId);
        if (virtualStorageManager.isVirtualStorageEnable(installedAppInfo.packageName, myUserId) && virtualStorage != null) {
            File file = new File(virtualStorage);
            if (file.exists() || file.mkdirs()) {
                Iterator<String> it = getMountPoints().iterator();
                while (it.hasNext()) {
                    NativeEngine.redirectDirectory(it.next(), virtualStorage);
                }
            }
        }
        if (VirtualCore.get().getVaCallBack() != null) {
            VirtualCore.get().getVaCallBack().fixFor1(installedAppInfo);
        }
        NativeEngine.enableIORedirect(installedAppInfo);
    }

    private void forbidHost() {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) VirtualCore.get().getContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid != Process.myPid() && runningAppProcessInfo.uid == VirtualCore.get().myUid() && !VActivityManager.get().isAppPid(runningAppProcessInfo.pid) && (runningAppProcessInfo.processName.startsWith(StubManifest.PACKAGE_NAME) || (StubManifest.PACKAGE_NAME_64BIT != null && runningAppProcessInfo.processName.startsWith(StubManifest.PACKAGE_NAME_64BIT)))) {
                NativeEngine.forbid("/proc/" + runningAppProcessInfo.pid + "/maps", true);
                NativeEngine.forbid("/proc/" + runningAppProcessInfo.pid + "/cmdline", true);
            }
        }
    }

    @SuppressLint({"SdCardPath"})
    private HashSet<String> getMountPoints() {
        HashSet<String> hashSet = new HashSet<>(3);
        hashSet.add("/mnt/sdcard/");
        hashSet.add("/sdcard/");
        hashSet.add("/storage/emulated/" + VUserHandle.realUserId() + "/");
        hashSet.add("storage/emulated/" + VUserHandle.realUserId() + "/");
        String[] allPoints = StorageManagerCompat.getAllPoints(VirtualCore.get().getContext());
        if (allPoints != null) {
            Collections.addAll(hashSet, allPoints);
        }
        return hashSet;
    }

    private Context createPackageContext(String str) {
        try {
            return VirtualCore.get().getContext().createPackageContext(str, 3);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
            throw new RuntimeException();
        }
    }

    private void installContentProviders(Context context, List<ProviderInfo> list) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        Object mainThread = VirtualCore.mainThread();
        try {
            for (ProviderInfo providerInfo : list) {
                ActivityThread.installProvider(mainThread, context, providerInfo, null);
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    @Override // com.lody.virtual.client.IVClient
    public IBinder acquireProviderClient(ProviderInfo providerInfo) {
        ContentProviderClient contentProviderClient;
        IInterface iInterface;
        if (!isAppRunning()) {
            get().bindApplication(providerInfo.packageName, providerInfo.processName);
        }
        if (get().getCurrentApplication() == null) {
            return null;
        }
        String[] split = providerInfo.authority.split(C4963cj.f20745b);
        String str = split.length == 0 ? providerInfo.authority : split[0];
        ContentResolver contentResolver = VirtualCore.get().getContext().getContentResolver();
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                contentProviderClient = contentResolver.acquireUnstableContentProviderClient(str);
            } else {
                contentProviderClient = contentResolver.acquireContentProviderClient(str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            contentProviderClient = null;
        }
        if (contentProviderClient != null) {
            iInterface = p110z1.ContentProviderClient.mContentProvider.get(contentProviderClient);
            contentProviderClient.release();
        } else {
            iInterface = null;
        }
        IBinder asBinder = iInterface != null ? iInterface.asBinder() : null;
        if (asBinder == null) {
            return null;
        }
        if (asBinder instanceof Binder) {
            return new FakeIdentityBinder((Binder) asBinder);
        }
        VLog.m18992e(TAG, "binder not instanceof Binder.");
        return asBinder;
    }

    private void fixInstalledProviders() {
        clearSettingProvider();
        for (Map.Entry entry : ActivityThread.mProviderMap.get(VirtualCore.mainThread()).entrySet()) {
            Object value = entry.getValue();
            if (BuildCompat.isOreo()) {
                IInterface iInterface = ActivityThread.C5110f.mProvider.get(value);
                Object obj = ActivityThread.C5110f.mHolder.get(value);
                if (obj != null) {
                    ProviderInfo providerInfo = ContentProviderHolderOreo.info.get(obj);
                    if (!providerInfo.authority.startsWith(StubManifest.STUB_CP_AUTHORITY)) {
                        IInterface createProxy = ProviderHook.createProxy(true, providerInfo.authority, iInterface);
                        ActivityThread.C5110f.mProvider.set(value, createProxy);
                        ContentProviderHolderOreo.provider.set(obj, createProxy);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                IInterface iInterface2 = ActivityThread.C5110f.mProvider.get(value);
                Object obj2 = ActivityThread.C5110f.mHolder.get(value);
                if (obj2 != null) {
                    ProviderInfo providerInfo2 = IActivityManager.C5112a.info.get(obj2);
                    if (!providerInfo2.authority.startsWith(StubManifest.STUB_CP_AUTHORITY)) {
                        IInterface createProxy2 = ProviderHook.createProxy(true, providerInfo2.authority, iInterface2);
                        ActivityThread.C5110f.mProvider.set(value, createProxy2);
                        IActivityManager.C5112a.provider.set(obj2, createProxy2);
                    }
                }
            } else {
                String str = ActivityThread.C5109e.mName.get(value);
                IInterface iInterface3 = ActivityThread.C5109e.mProvider.get(value);
                if (iInterface3 != null && !str.startsWith(StubManifest.STUB_CP_AUTHORITY)) {
                    ActivityThread.C5109e.mProvider.set(value, ProviderHook.createProxy(true, str, iInterface3));
                }
            }
        }
    }

    private void clearSettingProvider() {
        Object obj;
        Object obj2 = cwt.C5182f.sNameValueCache.get();
        if (obj2 != null) {
            clearContentProvider(obj2);
        }
        Object obj3 = cwt.C5181e.sNameValueCache.get();
        if (obj3 != null) {
            clearContentProvider(obj3);
        }
        if (Build.VERSION.SDK_INT >= 17 && cwt.C5178b.TYPE != null && (obj = cwt.C5178b.sNameValueCache.get()) != null) {
            clearContentProvider(obj);
        }
    }

    private static void clearContentProvider(Object obj) {
        if (BuildCompat.isOreo()) {
            Object obj2 = cwt.C5180d.mProviderHolder.get(obj);
            if (obj2 != null) {
                cwt.C5177a.mContentProvider.set(obj2, null);
                return;
            }
            return;
        }
        cwt.C5179c.mContentProvider.set(obj, null);
    }

    @Override // com.lody.virtual.client.IVClient
    public void finishActivity(IBinder iBinder) {
        sendMessage(13, iBinder);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleNewIntent(String str, IBinder iBinder, Intent intent) {
        NewIntentData newIntentData = new NewIntentData();
        newIntentData.creator = str;
        newIntentData.token = iBinder;
        newIntentData.intent = intent;
        sendMessage(11, newIntentData);
    }

    public void scheduleReceiver(String str, ComponentName componentName, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        ReceiverData receiverData = new ReceiverData();
        receiverData.pendingResult = pendingResult;
        receiverData.intent = intent;
        receiverData.component = componentName;
        receiverData.processName = str;
        receiverData.stacktrace = new Exception();
        sendMessage(12, receiverData);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo) {
        CreateServiceData createServiceData = new CreateServiceData();
        createServiceData.token = iBinder;
        createServiceData.info = serviceInfo;
        sendMessage(14, createServiceData);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleBindService(IBinder iBinder, Intent intent, boolean z) {
        BindServiceData bindServiceData = new BindServiceData();
        bindServiceData.token = iBinder;
        bindServiceData.intent = intent;
        bindServiceData.rebind = z;
        sendMessage(17, bindServiceData);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleUnbindService(IBinder iBinder, Intent intent) {
        BindServiceData bindServiceData = new BindServiceData();
        bindServiceData.token = iBinder;
        bindServiceData.intent = intent;
        sendMessage(18, bindServiceData);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleServiceArgs(IBinder iBinder, int i, Intent intent) {
        ServiceArgsData serviceArgsData = new ServiceArgsData();
        serviceArgsData.token = iBinder;
        serviceArgsData.startId = i;
        serviceArgsData.args = intent;
        sendMessage(15, serviceArgsData);
    }

    @Override // com.lody.virtual.client.IVClient
    public void scheduleStopService(IBinder iBinder) {
        sendMessage(16, iBinder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReceiver(ReceiverData receiverData) {
        BroadcastReceiver.PendingResult pendingResult = receiverData.pendingResult;
        try {
            Context baseContext = this.mInitialApplication.getBaseContext();
            Context call = ContextImpl.getReceiverRestrictedContext.call(baseContext, new Object[0]);
            BroadcastReceiver broadcastReceiver = (BroadcastReceiver) LoadedApk.getClassLoader.call(this.mBoundApplication.info, new Object[0]).loadClass(receiverData.component.getClassName()).newInstance();
            p110z1.BroadcastReceiver.setPendingResult.call(broadcastReceiver, pendingResult);
            receiverData.intent.setExtrasClassLoader(baseContext.getClassLoader());
            if (receiverData.intent.getComponent() == null) {
                receiverData.intent.setComponent(receiverData.component);
            }
            broadcastReceiver.onReceive(call, receiverData.intent);
            if (p110z1.BroadcastReceiver.getPendingResult.call(broadcastReceiver, new Object[0]) != null) {
                VActivityManager.get().broadcastFinish(BroadcastReceiver.C5132a.mToken.get(pendingResult));
            }
        } catch (Exception e) {
            receiverData.stacktrace.printStackTrace();
            throw new RuntimeException("Unable to start receiver " + receiverData.component + ": " + e.toString(), e);
        }
    }

    public ClassLoader getClassLoader() {
        return LoadedApk.getClassLoader.call(this.mBoundApplication.info, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCreateService(CreateServiceData createServiceData) {
        ServiceInfo serviceInfo = createServiceData.info;
        if (!isAppRunning()) {
            bindApplication(serviceInfo.packageName, serviceInfo.processName);
        }
        try {
            Service service = (Service) LoadedApk.getClassLoader.call(this.mBoundApplication.info, new Object[0]).loadClass(serviceInfo.name).newInstance();
            try {
                Context createPackageContext = VirtualCore.get().getContext().createPackageContext(createServiceData.info.packageName, 3);
                ContextImpl.setOuterContext.call(createPackageContext, service);
                p110z1.Service.attach.call(service, createPackageContext, VirtualCore.mainThread(), serviceInfo.name, this.clientConfig.token, this.mInitialApplication, ActivityManagerNative.getDefault.call(new Object[0]));
                ContextFixer.fixContext(service);
                service.onCreate();
                this.mServices.put(createServiceData.token, service);
                VActivityManager.get().serviceDoneExecuting(createServiceData.token, 0, 0, 0);
            } catch (Exception e) {
                throw new RuntimeException("Unable to create service " + createServiceData.info.name + ": " + e.toString(), e);
            }
        } catch (Exception e2) {
            throw new RuntimeException("Unable to instantiate service " + serviceInfo.name + ": " + e2.toString(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBindService(BindServiceData bindServiceData) {
        Service service = this.mServices.get(bindServiceData.token);
        if (service != null) {
            try {
                bindServiceData.intent.setExtrasClassLoader(service.getClassLoader());
                if (!bindServiceData.rebind) {
                    VActivityManager.get().publishService(bindServiceData.token, bindServiceData.intent, service.onBind(bindServiceData.intent));
                } else {
                    service.onRebind(bindServiceData.intent);
                    VActivityManager.get().serviceDoneExecuting(bindServiceData.token, 0, 0, 0);
                }
            } catch (Exception e) {
                throw new RuntimeException("Unable to bind to service " + service + " with " + bindServiceData.intent + ": " + e.toString(), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUnbindService(BindServiceData bindServiceData) {
        Service service = this.mServices.get(bindServiceData.token);
        if (service != null) {
            try {
                bindServiceData.intent.setExtrasClassLoader(service.getClassLoader());
                if (service.onUnbind(bindServiceData.intent)) {
                    VActivityManager.get().unbindFinished(bindServiceData.token, bindServiceData.intent, true);
                } else {
                    VActivityManager.get().serviceDoneExecuting(bindServiceData.token, 0, 0, 0);
                }
            } catch (Exception e) {
                throw new RuntimeException("Unable to unbind to service " + service + " with " + bindServiceData.intent + ": " + e.toString(), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServiceArgs(ServiceArgsData serviceArgsData) {
        int i;
        Service service = this.mServices.get(serviceArgsData.token);
        if (service != null) {
            try {
                if (serviceArgsData.args != null) {
                    serviceArgsData.args.setExtrasClassLoader(service.getClassLoader());
                }
                if (!serviceArgsData.taskRemoved) {
                    i = service.onStartCommand(serviceArgsData.args, serviceArgsData.flags, serviceArgsData.startId);
                } else {
                    service.onTaskRemoved(serviceArgsData.args);
                    i = 0;
                }
                VActivityManager.get().serviceDoneExecuting(serviceArgsData.token, 1, serviceArgsData.startId, i);
            } catch (Exception e) {
                throw new RuntimeException("Unable to start service " + service + " with " + serviceArgsData.args + ": " + e.toString(), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopService(IBinder iBinder) {
        Service remove = this.mServices.remove(iBinder);
        if (remove != null) {
            try {
                remove.onDestroy();
                VActivityManager.get().serviceDoneExecuting(iBinder, 2, 0, 0);
            } catch (Exception e) {
                if (!this.mInstrumentation.onException(remove, e)) {
                    throw new RuntimeException("Unable to stop service " + remove + ": " + e.toString(), e);
                }
            }
        }
    }

    public Service createService(ServiceInfo serviceInfo, IBinder iBinder) {
        if (!isAppRunning()) {
            bindApplication(serviceInfo.packageName, serviceInfo.processName);
        }
        try {
            Service service = (Service) LoadedApk.getClassLoader.call(this.mBoundApplication.info, new Object[0]).loadClass(serviceInfo.name).newInstance();
            try {
                Context createPackageContext = VirtualCore.get().getContext().createPackageContext(serviceInfo.packageName, 3);
                ContextImpl.setOuterContext.call(createPackageContext, service);
                p110z1.Service.attach.call(service, createPackageContext, VirtualCore.mainThread(), serviceInfo.name, iBinder, this.mInitialApplication, ActivityManagerNative.getDefault.call(new Object[0]));
                ContextFixer.fixContext(service);
                service.onCreate();
                return service;
            } catch (Exception e) {
                throw new RuntimeException("Unable to create service " + serviceInfo.name + ": " + e.toString(), e);
            }
        } catch (Exception e2) {
            throw new RuntimeException("Unable to instantiate service " + serviceInfo.name + ": " + e2.toString(), e2);
        }
    }

    @Override // com.lody.virtual.client.IVClient
    public IBinder createProxyService(ComponentName componentName, IBinder iBinder) {
        return ProxyServiceFactory.getProxyService(getCurrentApplication(), componentName, iBinder);
    }

    @Override // com.lody.virtual.client.IVClient
    public String getDebugInfo() {
        return VirtualRuntime.getProcessName();
    }

    @Override // com.lody.virtual.client.IVClient
    public boolean finishReceiver(IBinder iBinder) {
        return StaticReceiverSystem.get().broadcastFinish(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RootThreadGroup extends ThreadGroup {
        RootThreadGroup(ThreadGroup threadGroup) {
            super(threadGroup, "VA");
        }

        @Override // java.lang.ThreadGroup, java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            CrashHandler crashHandler = VClient.gClient.crashHandler;
            if (crashHandler != null) {
                crashHandler.handleUncaughtException(thread, th);
                return;
            }
            VLog.m18990e("uncaught", th);
            System.exit(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NewIntentData {
        String creator;
        Intent intent;
        IBinder token;

        private NewIntentData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AppBindData {
        ApplicationInfo appInfo;
        Object info;
        String processName;
        List<ProviderInfo> providers;

        private AppBindData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ReceiverData {
        ComponentName component;
        Intent intent;
        BroadcastReceiver.PendingResult pendingResult;
        String processName;
        Throwable stacktrace;

        private ReceiverData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CreateServiceData {
        ServiceInfo info;
        IBinder token;

        CreateServiceData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class BindServiceData {
        Intent intent;
        boolean rebind;
        IBinder token;

        BindServiceData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ServiceArgsData {
        Intent args;
        int flags;
        int startId;
        boolean taskRemoved;
        IBinder token;

        ServiceArgsData() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    /* renamed from: com.lody.virtual.client.VClient$H */
    /* loaded from: classes.dex */
    public class HandlerC1719H extends Handler {
        private HandlerC1719H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 11:
                    VClient.this.handleNewIntent((NewIntentData) message.obj);
                    return;
                case 12:
                    VClient.this.handleReceiver((ReceiverData) message.obj);
                    return;
                case 13:
                    VActivityManager.get().finishActivity((IBinder) message.obj);
                    return;
                case 14:
                    VClient.this.handleCreateService((CreateServiceData) message.obj);
                    return;
                case 15:
                    VClient.this.handleServiceArgs((ServiceArgsData) message.obj);
                    return;
                case 16:
                    VClient.this.handleStopService((IBinder) message.obj);
                    return;
                case 17:
                    VClient.this.handleBindService((BindServiceData) message.obj);
                    return;
                case 18:
                    VClient.this.handleUnbindService((BindServiceData) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
