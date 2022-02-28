package com.lody.virtual.server.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.collection.IntArray;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstallResult;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.accounts.VAccountManagerService;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IAppManager;
import com.lody.virtual.server.interfaces.IPackageObserver;
import com.lody.virtual.server.notification.VNotificationManagerService;
import com.lody.virtual.server.am.UidSystem;
import com.lody.virtual.server.am.VActivityManagerService;
import com.lody.virtual.server.pm.parser.PackageParserEx;
import com.lody.virtual.server.pm.parser.VPackage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p110z1.C4985cm;

/* renamed from: com.lody.virtual.server.pm.VAppManagerService */
/* loaded from: classes.dex */
public class VAppManagerService extends IAppManager.Stub {
    private static final String PKG_WE_WORK = "com.tencent.wework";
    private static final String TAG = "VAppManagerService";
    private static final Singleton<VAppManagerService> sService = new Singleton<VAppManagerService>() { // from class: com.lody.virtual.server.pm.VAppManagerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VAppManagerService create() {
            return new VAppManagerService();
        }
    };
    private boolean mBooting;
    private final UidSystem mUidSystem = new UidSystem();
    private final PackagePersistenceLayer mPersistenceLayer = new PackagePersistenceLayer(this);
    private final Set<String> mVisibleOutsidePackages = new HashSet();
    private RemoteCallbackList<IPackageObserver> mRemoteCallbackList = new RemoteCallbackList<>();
    private BroadcastReceiver appEventReciever = new BroadcastReceiver() { // from class: com.lody.virtual.server.pm.VAppManagerService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Uri data;
            String schemeSpecificPart;
            PackageSetting setting;
            if (!VAppManagerService.this.mBooting) {
                BroadcastReceiver.PendingResult goAsync = goAsync();
                String action = intent.getAction();
                if (action != null && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null && (setting = PackageCacheManager.getSetting(schemeSpecificPart)) != null && setting.appMode == 1) {
                    VActivityManagerService.get().killAppByPkg(schemeSpecificPart, -1);
                    if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                        ApplicationInfo applicationInfo = null;
                        try {
                            applicationInfo = VirtualCore.getPM().getApplicationInfo(schemeSpecificPart, 0);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (applicationInfo != null) {
                            InstallResult installPackageImpl = VAppManagerService.this.installPackageImpl(applicationInfo.publicSourceDir, InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE));
                            String str = VAppManagerService.TAG;
                            Object[] objArr = new Object[2];
                            objArr[0] = installPackageImpl.packageName;
                            objArr[1] = installPackageImpl.isSuccess ? "success" : "failed";
                            VLog.m18991e(str, "Update package %s %s", objArr);
                        } else {
                            return;
                        }
                    } else if (action.equals("android.intent.action.PACKAGE_REMOVED") && intent.getBooleanExtra("android.intent.extra.DATA_REMOVED", false)) {
                        VLog.m18991e(VAppManagerService.TAG, "Removing package %s...", setting.packageName);
                        VAppManagerService.this.uninstallPackageFully(setting, true);
                    }
                    goAsync.finish();
                }
            }
        }
    };

    public static VAppManagerService get() {
        return sService.get();
    }

    public static void systemReady() {
        VEnvironment.systemReady();
        if (!BuildCompat.isPie()) {
            get().extractRequiredFrameworks();
        }
        if (BuildCompat.isPie() && !BuildCompat.isQ()) {
            get().extractApacheFrameworksForPie();
        }
        get().startup();
    }

    private void startup() {
        this.mVisibleOutsidePackages.add("com.android.providers.downloads");
        this.mUidSystem.initUidList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(ServiceManagerNative.PACKAGE);
        VirtualCore.get().getContext().registerReceiver(this.appEventReciever, intentFilter);
    }

    public boolean isBooting() {
        return this.mBooting;
    }

    private void extractRequiredFrameworks() {
        String[] strArr;
        for (String str : StubManifest.REQUIRED_FRAMEWORK) {
            File frameworkFile32 = VEnvironment.getFrameworkFile32(str);
            File optimizedFrameworkFile32 = VEnvironment.getOptimizedFrameworkFile32(str);
            if (!optimizedFrameworkFile32.exists()) {
                OatHelper.extractFrameworkFor32Bit(str, frameworkFile32, optimizedFrameworkFile32);
            }
        }
    }

    private void extractApacheFrameworksForPie() {
        File optimizedFrameworkFile32 = VEnvironment.getOptimizedFrameworkFile32("org.apache.http.legacy.boot");
        if (!optimizedFrameworkFile32.exists()) {
            try {
                FileUtils.copyFileFromAssets(VirtualCore.get().getContext(), "org.apache.http.legacy.boot", optimizedFrameworkFile32);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void scanApps() {
        if (!this.mBooting) {
            synchronized (this) {
                this.mBooting = true;
                this.mPersistenceLayer.read();
                if (this.mPersistenceLayer.changed) {
                    this.mPersistenceLayer.changed = false;
                    this.mPersistenceLayer.save();
                    VLog.m18986w(TAG, "Package PersistenceLayer updated.", new Object[0]);
                }
                for (String str : SpecialComponentList.getPreInstallPackages()) {
                    if (!isAppInstalled(str)) {
                        try {
                            ApplicationInfo applicationInfo = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(str, 0);
                            installPackageImpl(applicationInfo.publicSourceDir, InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE));
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    }
                }
                PrivilegeAppOptimizer.get().performOptimizeAllApps();
                this.mBooting = false;
            }
        }
    }

    private void cleanUpResidualFiles(PackageSetting packageSetting) {
        VLog.m18991e(TAG, "cleanup residual files for : %s", packageSetting.packageName);
        uninstallPackageFully(packageSetting, false);
    }

    public void onUserCreated(VUserInfo vUserInfo) {
        VEnvironment.getUserDataDirectory(vUserInfo.f10500id).mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean loadPackage(PackageSetting packageSetting) {
        if (loadPackageInnerLocked(packageSetting)) {
            return true;
        }
        cleanUpResidualFiles(packageSetting);
        return false;
    }

    private boolean loadPackageInnerLocked(PackageSetting packageSetting) {
        boolean z = packageSetting.appMode == 1;
        if (z && !VirtualCore.get().isOutsideInstalled(packageSetting.packageName)) {
            return false;
        }
        File packageCacheFile = VEnvironment.getPackageCacheFile(packageSetting.packageName);
        VPackage vPackage = null;
        try {
            vPackage = PackageParserEx.readPackageCache(packageSetting.packageName);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (vPackage == null || vPackage.packageName == null) {
            return false;
        }
        VEnvironment.chmodPackageDictionary(packageCacheFile);
        PackageCacheManager.put(vPackage, packageSetting);
        if (z) {
            try {
                PackageInfo packageInfo = VirtualCore.get().getUnHookPackageManager().getPackageInfo(packageSetting.packageName, 0);
                if (vPackage.mVersionCode != packageInfo.versionCode) {
                    String str = TAG;
                    VLog.m18993d(str, "app (" + packageSetting.packageName + ") has changed version, update it.", new Object[0]);
                    installPackageImpl(packageInfo.applicationInfo.publicSourceDir, InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean isOutsidePackageVisible(String str) {
        return str != null && this.mVisibleOutsidePackages.contains(str);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public int getUidForSharedUser(String str) {
        if (str == null) {
            return -1;
        }
        return this.mUidSystem.getUid(str);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void addVisibleOutsidePackage(String str) {
        if (str != null) {
            this.mVisibleOutsidePackages.add(str);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void removeVisibleOutsidePackage(String str) {
        if (str != null) {
            this.mVisibleOutsidePackages.remove(str);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void installPackage(String str, InstallOptions installOptions, ResultReceiver resultReceiver) {
        InstallResult installPackageImpl;
        synchronized (this) {
            installPackageImpl = installPackageImpl(str, installOptions);
        }
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(C4985cm.f20833c, installPackageImpl);
            resultReceiver.send(0, bundle);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void requestCopyPackage64(String str) {
        synchronized (VActivityManagerService.get()) {
            PackageSetting setting = PackageCacheManager.getSetting(str);
            if (setting != null && setting.appMode == 1) {
                V64BitHelper.copyPackage64(setting.getApkPath(false), str);
            }
        }
    }

    public InstallResult installPackage(String str, InstallOptions installOptions) {
        InstallResult installPackageImpl;
        synchronized (this) {
            installPackageImpl = installPackageImpl(str, installOptions);
        }
        return installPackageImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.lody.virtual.remote.InstallResult installPackageImpl(java.lang.String r18, com.lody.virtual.remote.InstallOptions r19) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.pm.VAppManagerService.installPackageImpl(java.lang.String, com.lody.virtual.remote.InstallOptions):com.lody.virtual.remote.InstallResult");
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public synchronized boolean installPackageAsUser(int i, String str) {
        PackageSetting setting;
        if (!VUserManagerService.get().exists(i) || (setting = PackageCacheManager.getSetting(str)) == null) {
            return false;
        }
        if (setting.isInstalled(i)) {
            return true;
        }
        setting.setInstalled(i, true);
        notifyAppInstalled(setting, i);
        this.mPersistenceLayer.save();
        return true;
    }

    private boolean isAllowedUpdate(VPackage vPackage, VPackage vPackage2, InstallOptions.UpdateStrategy updateStrategy) {
        switch (updateStrategy) {
            case FORCE_UPDATE:
                return true;
            case COMPARE_VERSION:
                return vPackage.mVersionCode < vPackage2.mVersionCode;
            case TERMINATE_IF_EXIST:
                return false;
            default:
                return true;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public synchronized boolean uninstallPackage(String str) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting == null) {
            return false;
        }
        uninstallPackageFully(setting, true);
        return true;
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public synchronized boolean uninstallPackageAsUser(String str, int i) {
        if (!VUserManagerService.get().exists(i)) {
            return false;
        }
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting == null) {
            return false;
        }
        int[] packageInstalledUsers = getPackageInstalledUsers(str);
        if (!ArrayUtils.contains(packageInstalledUsers, i)) {
            return false;
        }
        if (packageInstalledUsers.length == 1) {
            uninstallPackageFully(setting, true);
        } else {
            VActivityManagerService.get().killAppByPkg(str, i);
            setting.setInstalled(i, false);
            this.mPersistenceLayer.save();
            deletePackageDataAsUser(i, setting);
            notifyAppUninstalled(setting, i);
        }
        return true;
    }

    private boolean isPackageSupport32Bit(PackageSetting packageSetting) {
        return packageSetting.flag == 0 || packageSetting.flag == 1;
    }

    private boolean isPackageSupport64Bit(PackageSetting packageSetting) {
        return packageSetting.flag == 2 || packageSetting.flag == 1;
    }

    private void deletePackageDataAsUser(int i, PackageSetting packageSetting) {
        if (isPackageSupport32Bit(packageSetting)) {
            if (i == -1) {
                List<VUserInfo> users = VUserManager.get().getUsers();
                if (users != null) {
                    for (VUserInfo vUserInfo : users) {
                        FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory(vUserInfo.f10500id, packageSetting.packageName));
                    }
                }
            } else {
                FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory(i, packageSetting.packageName));
            }
        }
        if (isPackageSupport64Bit(packageSetting)) {
            V64BitHelper.cleanPackageData64(i, packageSetting.packageName);
        }
        VNotificationManagerService.get().cancelAllNotification(packageSetting.packageName, i);
        ComponentStateManager.user(i).clear(packageSetting.packageName);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean cleanPackageData(String str, int i) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting == null) {
            return false;
        }
        VActivityManagerService.get().killAppByPkg(str, i);
        deletePackageDataAsUser(i, setting);
        ComponentStateManager.user(i).clear(str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninstallPackageFully(PackageSetting packageSetting, boolean z) {
        String str = packageSetting.packageName;
        try {
            try {
                VActivityManagerService.get().killAppByPkg(str, -1);
                if (isPackageSupport32Bit(packageSetting)) {
                    VEnvironment.getPackageResourcePath(str).delete();
                    FileUtils.deleteDir(VEnvironment.getDataAppPackageDirectory(str));
                    VEnvironment.getOdexFile(str).delete();
                    for (int i : VUserManagerService.get().getUserIds()) {
                        deletePackageDataAsUser(i, packageSetting);
                    }
                }
                if (isPackageSupport64Bit(packageSetting)) {
                    V64BitHelper.uninstallPackage64(-1, str);
                }
                PackageCacheManager.remove(str);
                VEnvironment.getPackageCacheFile(str).delete();
                VEnvironment.getSignatureFile(str).delete();
                if (!z) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!z) {
                    return;
                }
            }
            notifyAppUninstalled(packageSetting, -1);
        } catch (Throwable th) {
            if (z) {
                notifyAppUninstalled(packageSetting, -1);
            }
            throw th;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public int[] getPackageInstalledUsers(String str) {
        int[] userIds;
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting == null) {
            return new int[0];
        }
        IntArray intArray = new IntArray(5);
        for (int i : VUserManagerService.get().getUserIds()) {
            if (setting.readUserState(i).installed) {
                intArray.add(i);
            }
        }
        return intArray.getAll();
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public List<InstalledAppInfo> getInstalledApps(int i) {
        ArrayList arrayList = new ArrayList(getInstalledAppCount());
        for (VPackage vPackage : PackageCacheManager.PACKAGE_CACHE.values()) {
            arrayList.add(((PackageSetting) vPackage.mExtras).getAppInfo());
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public List<InstalledAppInfo> getInstalledAppsAsUser(int i, int i2) {
        ArrayList arrayList = new ArrayList(getInstalledAppCount());
        for (VPackage vPackage : PackageCacheManager.PACKAGE_CACHE.values()) {
            PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
            boolean isInstalled = packageSetting.isInstalled(i);
            if ((i2 & 1) == 0 && packageSetting.isHidden(i)) {
                isInstalled = false;
            }
            if (isInstalled) {
                arrayList.add(packageSetting.getAppInfo());
            }
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public int getInstalledAppCount() {
        return PackageCacheManager.PACKAGE_CACHE.size();
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean isAppInstalled(String str) {
        return str != null && PackageCacheManager.PACKAGE_CACHE.containsKey(str);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean isAppInstalledAsUser(int i, String str) {
        PackageSetting setting;
        if (str == null || !VUserManagerService.get().exists(i) || (setting = PackageCacheManager.getSetting(str)) == null) {
            return false;
        }
        return setting.isInstalled(i);
    }

    private void notifyAppInstalled(PackageSetting packageSetting, int i) {
        String str = packageSetting.packageName;
        int beginBroadcast = this.mRemoteCallbackList.beginBroadcast();
        while (true) {
            int i2 = beginBroadcast - 1;
            if (beginBroadcast > 0) {
                if (i == -1) {
                    try {
                        this.mRemoteCallbackList.getBroadcastItem(i2).onPackageInstalled(str);
                        this.mRemoteCallbackList.getBroadcastItem(i2).onPackageInstalledAsUser(0, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.mRemoteCallbackList.getBroadcastItem(i2).onPackageInstalledAsUser(i, str);
                }
                beginBroadcast = i2;
            } else {
                sendInstalledBroadcast(str, new VUserHandle(i));
                this.mRemoteCallbackList.finishBroadcast();
                VAccountManagerService.get().refreshAuthenticatorCache(null);
                return;
            }
        }
    }

    private void notifyAppUninstalled(PackageSetting packageSetting, int i) {
        String str = packageSetting.packageName;
        int beginBroadcast = this.mRemoteCallbackList.beginBroadcast();
        while (true) {
            int i2 = beginBroadcast - 1;
            if (beginBroadcast > 0) {
                if (i == -1) {
                    try {
                        this.mRemoteCallbackList.getBroadcastItem(i2).onPackageUninstalled(str);
                        this.mRemoteCallbackList.getBroadcastItem(i2).onPackageUninstalledAsUser(0, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.mRemoteCallbackList.getBroadcastItem(i2).onPackageUninstalledAsUser(i, str);
                }
                beginBroadcast = i2;
            } else {
                sendUninstalledBroadcast(str, new VUserHandle(i));
                this.mRemoteCallbackList.finishBroadcast();
                VAccountManagerService.get().refreshAuthenticatorCache(null);
                return;
            }
        }
    }

    private void sendInstalledBroadcast(String str, VUserHandle vUserHandle) {
        Intent intent = new Intent("android.intent.action.PACKAGE_ADDED");
        intent.setData(Uri.parse("package:" + str));
        VActivityManagerService.get().sendBroadcastAsUser(intent, vUserHandle);
    }

    private void sendUninstalledBroadcast(String str, VUserHandle vUserHandle) {
        Intent intent = new Intent("android.intent.action.PACKAGE_REMOVED");
        intent.setData(Uri.parse("package:" + str));
        VActivityManagerService.get().sendBroadcastAsUser(intent, vUserHandle);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void registerObserver(IPackageObserver iPackageObserver) {
        try {
            this.mRemoteCallbackList.register(iPackageObserver);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void unregisterObserver(IPackageObserver iPackageObserver) {
        try {
            this.mRemoteCallbackList.unregister(iPackageObserver);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public InstalledAppInfo getInstalledAppInfo(String str, int i) {
        synchronized (PackageCacheManager.class) {
            if (str != null) {
                PackageSetting setting = PackageCacheManager.getSetting(str);
                if (setting != null) {
                    return setting.getAppInfo();
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean isRun64BitProcess(String str) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        return setting != null && setting.isRunOn64BitProcess();
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public synchronized boolean isIORelocateWork() {
        return true;
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public boolean isPackageLaunched(int i, String str) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        return setting != null && setting.isLaunched(i);
    }

    @Override // com.lody.virtual.server.interfaces.IAppManager
    public void setPackageHidden(int i, String str, boolean z) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting != null && VUserManagerService.get().exists(i)) {
            setting.setHidden(i, z);
            this.mPersistenceLayer.save();
        }
    }

    public int getAppId(String str) {
        PackageSetting setting = PackageCacheManager.getSetting(str);
        if (setting != null) {
            return setting.appId;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreFactoryState() {
        VLog.m18986w(TAG, "Warning: Restore the factory state...", new Object[0]);
        VEnvironment.getDalvikCacheDirectory().delete();
        VEnvironment.getUserSystemDirectory().delete();
        VEnvironment.getUserDeSystemDirectory().delete();
        VEnvironment.getDataAppDirectory().delete();
    }

    public void savePersistenceData() {
        this.mPersistenceLayer.save();
    }

    public boolean is64BitUid(int i) throws PackageManager.NameNotFoundException {
        int appId = VUserHandle.getAppId(i);
        synchronized (PackageCacheManager.PACKAGE_CACHE) {
            for (VPackage vPackage : PackageCacheManager.PACKAGE_CACHE.values()) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                if (packageSetting.appId == appId) {
                    return packageSetting.isRunOn64BitProcess();
                }
            }
            throw new PackageManager.NameNotFoundException();
        }
    }
}
