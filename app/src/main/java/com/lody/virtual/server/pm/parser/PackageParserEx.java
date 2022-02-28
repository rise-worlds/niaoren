package com.lody.virtual.server.pm.parser;

import android.content.p001pm.PackageParser;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Parcel;
import android.text.TextUtils;
import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.fixer.ComponentFixer;
import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.NativeLibraryHelperCompat;
import com.lody.virtual.helper.compat.PackageParserCompat;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.server.pm.ComponentStateManager;
import com.lody.virtual.server.pm.PackageCacheManager;
import com.lody.virtual.server.pm.PackageSetting;
import com.lody.virtual.server.pm.PackageUserState;
import com.lody.virtual.server.pm.parser.VPackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p110z1.ApplicationInfoL;
import p110z1.ApplicationInfoN;
import p110z1.PackageParser;

/* renamed from: com.lody.virtual.server.pm.parser.PackageParserEx */
/* loaded from: classes.dex */
public class PackageParserEx {
    private static final String TAG = "PackageParserEx";
    private static final ArrayMap<String, String[]> sSharedLibCache = new ArrayMap<>();

    public static VPackage parsePackage(File file) throws Throwable {
        PackageParser createParser = PackageParserCompat.createParser(file);
        if (BuildCompat.isQ()) {
            createParser.setCallback(new PackageParser.CallbackImpl(VirtualCore.getPM()));
        }
        PackageParser.Package parsePackage = PackageParserCompat.parsePackage(createParser, file, 0);
        int i = 1;
        if (!parsePackage.requestedPermissions.contains("android.permission.FAKE_PACKAGE_SIGNATURE") || parsePackage.mAppMetaData == null || !parsePackage.mAppMetaData.containsKey(Constants.FEATURE_FAKE_SIGNATURE)) {
            try {
                if (BuildCompat.isPie()) {
                    i = 16;
                }
                PackageParserCompat.collectCertificates(createParser, parsePackage, i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            buildSignature(parsePackage, new Signature[]{new Signature(parsePackage.mAppMetaData.getString(Constants.FEATURE_FAKE_SIGNATURE))});
            String str = TAG;
            VLog.m18993d(str, "Using fake-signature feature on : " + parsePackage.packageName, new Object[0]);
        }
        return buildPackageCache(parsePackage);
    }

    private static void buildSignature(PackageParser.Package r1, Signature[] signatureArr) {
        if (BuildCompat.isQ()) {
            Object obj = PackageParser.C5145c.mSigningDetails.get(r1);
            PackageParser.C5150h.pastSigningCertificates.set(obj, signatureArr);
            PackageParser.C5150h.signatures.set(obj, signatureArr);
            return;
        }
        r1.mSignatures = signatureArr;
    }

    /* JADX WARN: Finally extract failed */
    public static VPackage readPackageCache(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(VEnvironment.getPackageCacheFile(str));
                byte[] byteArray = FileUtils.toByteArray(fileInputStream);
                fileInputStream.close();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                if (obtain.readInt() == 4) {
                    VPackage vPackage = new VPackage(obtain);
                    addOwner(vPackage);
                    obtain.recycle();
                    return vPackage;
                }
                throw new IllegalStateException("Invalid version.");
            } catch (Exception e) {
                e.printStackTrace();
                obtain.recycle();
                return null;
            }
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public static void readSignature(VPackage vPackage) {
        File signatureFile = VEnvironment.getSignatureFile(vPackage.packageName);
        if (signatureFile.exists()) {
            Parcel obtain = Parcel.obtain();
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(signatureFile);
                    byte[] byteArray = FileUtils.toByteArray(fileInputStream);
                    fileInputStream.close();
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    vPackage.mSignatures = (Signature[]) obtain.createTypedArray(Signature.CREATOR);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                obtain.recycle();
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public static void savePackageCache(VPackage vPackage) {
        String str = vPackage.packageName;
        File packageCacheFile = VEnvironment.getPackageCacheFile(str);
        if (packageCacheFile.exists()) {
            packageCacheFile.delete();
        }
        File signatureFile = VEnvironment.getSignatureFile(str);
        if (signatureFile.exists()) {
            signatureFile.delete();
        }
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.writeInt(4);
                vPackage.writeToParcel(obtain, 0);
                FileOutputStream fileOutputStream = new FileOutputStream(packageCacheFile);
                fileOutputStream.write(obtain.marshall());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            obtain.recycle();
            Signature[] signatureArr = vPackage.mSignatures;
            if (signatureArr != null) {
                if (signatureFile.exists() && !signatureFile.delete()) {
                    String str2 = TAG;
                    VLog.m18986w(str2, "Unable to delete the signatures of " + str, new Object[0]);
                }
                obtain = Parcel.obtain();
                try {
                    try {
                        obtain.writeTypedArray(signatureArr, 0);
                        FileUtils.writeParcelToFile(obtain, signatureFile);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } finally {
            obtain.recycle();
        }
    }

    private static VPackage buildPackageCache(PackageParser.Package r5) {
        List<String> list;
        VPackage vPackage = new VPackage();
        vPackage.activities = new ArrayList<>(r5.activities.size());
        vPackage.services = new ArrayList<>(r5.services.size());
        vPackage.receivers = new ArrayList<>(r5.receivers.size());
        vPackage.providers = new ArrayList<>(r5.providers.size());
        vPackage.instrumentation = new ArrayList<>(r5.instrumentation.size());
        vPackage.permissions = new ArrayList<>(r5.permissions.size());
        vPackage.permissionGroups = new ArrayList<>(r5.permissionGroups.size());
        Iterator<PackageParser.Activity> it = r5.activities.iterator();
        while (it.hasNext()) {
            vPackage.activities.add(new VPackage.ActivityComponent(it.next()));
        }
        Iterator<PackageParser.Service> it2 = r5.services.iterator();
        while (it2.hasNext()) {
            vPackage.services.add(new VPackage.ServiceComponent(it2.next()));
        }
        Iterator<PackageParser.Activity> it3 = r5.receivers.iterator();
        while (it3.hasNext()) {
            vPackage.receivers.add(new VPackage.ActivityComponent(it3.next()));
        }
        Iterator<PackageParser.Provider> it4 = r5.providers.iterator();
        while (it4.hasNext()) {
            vPackage.providers.add(new VPackage.ProviderComponent(it4.next()));
        }
        Iterator<PackageParser.Instrumentation> it5 = r5.instrumentation.iterator();
        while (it5.hasNext()) {
            vPackage.instrumentation.add(new VPackage.InstrumentationComponent(it5.next()));
        }
        Iterator<PackageParser.Permission> it6 = r5.permissions.iterator();
        while (it6.hasNext()) {
            vPackage.permissions.add(new VPackage.PermissionComponent(it6.next()));
        }
        Iterator<PackageParser.PermissionGroup> it7 = r5.permissionGroups.iterator();
        while (it7.hasNext()) {
            vPackage.permissionGroups.add(new VPackage.PermissionGroupComponent(it7.next()));
        }
        vPackage.requestedPermissions = new ArrayList<>(r5.requestedPermissions.size());
        vPackage.requestedPermissions.addAll(r5.requestedPermissions);
        if (!(PackageParser.C5145c.protectedBroadcasts == null || (list = PackageParser.C5145c.protectedBroadcasts.get(r5)) == null)) {
            vPackage.protectedBroadcasts = new ArrayList<>(list);
            vPackage.protectedBroadcasts.addAll(list);
        }
        vPackage.applicationInfo = r5.applicationInfo;
        vPackage.mSignatures = getSignature(r5);
        vPackage.mAppMetaData = r5.mAppMetaData;
        vPackage.packageName = r5.packageName;
        vPackage.mPreferredOrder = r5.mPreferredOrder;
        vPackage.mVersionName = r5.mVersionName;
        vPackage.mSharedUserId = r5.mSharedUserId;
        vPackage.mSharedUserLabel = r5.mSharedUserLabel;
        vPackage.usesLibraries = r5.usesLibraries;
        vPackage.mVersionCode = r5.mVersionCode;
        vPackage.configPreferences = r5.configPreferences;
        vPackage.reqFeatures = r5.reqFeatures;
        addOwner(vPackage);
        return vPackage;
    }

    private static Signature[] getSignature(PackageParser.Package r1) {
        if (BuildCompat.isPie()) {
            return r1.mSigningDetails.signatures;
        }
        return r1.mSignatures;
    }

    public static void initApplicationInfoBase(PackageSetting packageSetting, VPackage vPackage) {
        ApplicationInfo applicationInfo = vPackage.applicationInfo;
        if (TextUtils.isEmpty(applicationInfo.processName)) {
            applicationInfo.processName = applicationInfo.packageName;
        }
        applicationInfo.enabled = true;
        applicationInfo.uid = packageSetting.appId;
        applicationInfo.name = ComponentFixer.fixComponentClassName(packageSetting.packageName, applicationInfo.name);
        if (Build.VERSION.SDK_INT >= 21) {
            ApplicationInfoL.scanSourceDir.set(applicationInfo, applicationInfo.dataDir);
            ApplicationInfoL.scanPublicSourceDir.set(applicationInfo, applicationInfo.dataDir);
            ApplicationInfoL.primaryCpuAbi.set(applicationInfo, ApplicationInfoL.primaryCpuAbi.get(VirtualCore.get().getContext().getApplicationInfo()));
        }
        String[] strArr = sSharedLibCache.get(packageSetting.packageName);
        if (strArr == null) {
            LinkedList linkedList = new LinkedList();
            if (packageSetting.appMode == 1) {
                try {
                    ApplicationInfo applicationInfo2 = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(packageSetting.packageName, 1024);
                    if (applicationInfo2.sharedLibraryFiles != null) {
                        Collections.addAll(linkedList, applicationInfo2.sharedLibraryFiles);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            if (Build.VERSION.SDK_INT >= 28 && applicationInfo.targetSdkVersion <= 29) {
                String str = FileUtils.isExist("/system/framework/org.apache.http.legacy.boot.jar") ? "/system/framework/org.apache.http.legacy.boot.jar" : "/system/framework/org.apache.http.legacy.jar";
                if (!BuildCompat.isPie() || BuildCompat.isQ() || FileUtils.fileSize(str) >= 1024) {
                    if (!linkedList.contains(str)) {
                        linkedList.add(str);
                    }
                } else if (!packageSetting.isRunOn64BitProcess()) {
                    linkedList.remove(str);
                    linkedList.add(VEnvironment.getOptimizedFrameworkFile32("org.apache.http.legacy.boot").getAbsolutePath());
                }
            }
            strArr = (String[]) linkedList.toArray(new String[0]);
            sSharedLibCache.put(packageSetting.packageName, strArr);
        }
        applicationInfo.sharedLibraryFiles = strArr;
    }

    private static void initApplicationAsUser(ApplicationInfo applicationInfo, int i) {
        String str;
        String chooseOutsideNativeLib;
        PackageSetting setting = PackageCacheManager.getSetting(applicationInfo.packageName);
        if (setting != null) {
            boolean isRunOn64BitProcess = setting.isRunOn64BitProcess();
            String apkPath = setting.getApkPath(isRunOn64BitProcess);
            applicationInfo.publicSourceDir = apkPath;
            applicationInfo.sourceDir = apkPath;
            SettingConfig config = VirtualCore.getConfig();
            SettingConfig.AppLibConfig appLibConfig = config.getAppLibConfig(applicationInfo.packageName);
            if (isRunOn64BitProcess) {
                applicationInfo.nativeLibraryDir = VEnvironment.getAppLibDirectory64(applicationInfo.packageName).getPath();
            } else {
                applicationInfo.nativeLibraryDir = VEnvironment.getAppLibDirectory(applicationInfo.packageName).getPath();
            }
            if (setting.appMode == 1) {
                ApplicationInfo applicationInfo2 = null;
                try {
                    applicationInfo2 = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(applicationInfo.packageName, 0);
                } catch (PackageManager.NameNotFoundException unused) {
                }
                if (appLibConfig == SettingConfig.AppLibConfig.UseRealLib && applicationInfo2 == null) {
                    appLibConfig = SettingConfig.AppLibConfig.UseOwnLib;
                }
                if (GmsSupport.isGoogleAppOrService(applicationInfo.packageName)) {
                    appLibConfig = SettingConfig.AppLibConfig.UseOwnLib;
                }
                if (applicationInfo2 != null) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        applicationInfo.splitNames = applicationInfo2.splitNames;
                    }
                    if (Build.VERSION.SDK_INT >= 21) {
                        applicationInfo.splitPublicSourceDirs = applicationInfo2.splitPublicSourceDirs;
                        applicationInfo.splitSourceDirs = applicationInfo2.splitSourceDirs;
                    }
                    if (appLibConfig == SettingConfig.AppLibConfig.UseRealLib && (chooseOutsideNativeLib = chooseOutsideNativeLib(applicationInfo2, isRunOn64BitProcess)) != null) {
                        applicationInfo.nativeLibraryDir = chooseOutsideNativeLib;
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (isRunOn64BitProcess) {
                    if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                        ApplicationInfoL.primaryCpuAbi.set(applicationInfo, Build.SUPPORTED_64_BIT_ABIS[0]);
                    }
                    if (setting.flag == 1) {
                        ApplicationInfoL.secondaryCpuAbi.set(applicationInfo, Build.SUPPORTED_32_BIT_ABIS[0]);
                    }
                } else {
                    ApplicationInfoL.primaryCpuAbi.set(applicationInfo, Build.SUPPORTED_32_BIT_ABIS[0]);
                    if (setting.flag == 1 && Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                        ApplicationInfoL.secondaryCpuAbi.set(applicationInfo, Build.SUPPORTED_64_BIT_ABIS[0]);
                    }
                }
            }
            if (isRunOn64BitProcess) {
                applicationInfo.dataDir = VEnvironment.getDataUserPackageDirectory64(i, applicationInfo.packageName).getPath();
            } else {
                applicationInfo.dataDir = VEnvironment.getDataUserPackageDirectory(i, applicationInfo.packageName).getPath();
            }
            String parent = new File(apkPath).getParent();
            if (Build.VERSION.SDK_INT >= 21) {
                ApplicationInfoL.scanSourceDir.set(applicationInfo, parent);
                ApplicationInfoL.scanPublicSourceDir.set(applicationInfo, parent);
            }
            if (Build.VERSION.SDK_INT >= 24) {
                if (isRunOn64BitProcess) {
                    str = VEnvironment.getDeDataUserPackageDirectory64(i, applicationInfo.packageName).getPath();
                } else {
                    str = VEnvironment.getDeDataUserPackageDirectory(i, applicationInfo.packageName).getPath();
                }
                if (ApplicationInfoN.deviceEncryptedDataDir != null) {
                    ApplicationInfoN.deviceEncryptedDataDir.set(applicationInfo, str);
                }
                if (ApplicationInfoN.credentialEncryptedDataDir != null) {
                    ApplicationInfoN.credentialEncryptedDataDir.set(applicationInfo, applicationInfo.dataDir);
                }
                if (ApplicationInfoN.deviceProtectedDataDir != null) {
                    ApplicationInfoN.deviceProtectedDataDir.set(applicationInfo, str);
                }
                if (ApplicationInfoN.credentialProtectedDataDir != null) {
                    ApplicationInfoN.credentialProtectedDataDir.set(applicationInfo, applicationInfo.dataDir);
                }
            }
            if (config.isEnableIORedirect()) {
                if (config.isUseRealDataDir(applicationInfo.packageName)) {
                    applicationInfo.dataDir = "/data/data/" + applicationInfo.packageName + "/";
                }
                if (config.isUseRealLibDir(applicationInfo.packageName)) {
                    applicationInfo.nativeLibraryDir = "/data/data/" + applicationInfo.packageName + "/lib/";
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalStateException();
    }

    private static String chooseOutsideNativeLib(ApplicationInfo applicationInfo, boolean z) {
        boolean z2;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                String str = ApplicationInfoL.primaryCpuAbi.get(applicationInfo);
                String str2 = ApplicationInfoL.secondaryCpuAbi.get(applicationInfo);
                if (str == null) {
                    return null;
                }
                if (z) {
                    z2 = NativeLibraryHelperCompat.is64bitAbi(str);
                } else {
                    z2 = NativeLibraryHelperCompat.is32bitAbi(str);
                }
                if (z2) {
                    return applicationInfo.nativeLibraryDir;
                }
                if (str2 != null) {
                    return ApplicationInfoL.secondaryNativeLibraryDir.get(applicationInfo);
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return applicationInfo.nativeLibraryDir;
    }

    private static void addOwner(VPackage vPackage) {
        Iterator<VPackage.ActivityComponent> it = vPackage.activities.iterator();
        while (it.hasNext()) {
            VPackage.ActivityComponent next = it.next();
            next.owner = vPackage;
            Iterator it2 = next.intents.iterator();
            while (it2.hasNext()) {
                ((VPackage.ActivityIntentInfo) it2.next()).activity = next;
            }
        }
        Iterator<VPackage.ServiceComponent> it3 = vPackage.services.iterator();
        while (it3.hasNext()) {
            VPackage.ServiceComponent next2 = it3.next();
            next2.owner = vPackage;
            Iterator it4 = next2.intents.iterator();
            while (it4.hasNext()) {
                ((VPackage.ServiceIntentInfo) it4.next()).service = next2;
            }
        }
        Iterator<VPackage.ActivityComponent> it5 = vPackage.receivers.iterator();
        while (it5.hasNext()) {
            VPackage.ActivityComponent next3 = it5.next();
            next3.owner = vPackage;
            Iterator it6 = next3.intents.iterator();
            while (it6.hasNext()) {
                ((VPackage.ActivityIntentInfo) it6.next()).activity = next3;
            }
        }
        Iterator<VPackage.ProviderComponent> it7 = vPackage.providers.iterator();
        while (it7.hasNext()) {
            VPackage.ProviderComponent next4 = it7.next();
            next4.owner = vPackage;
            Iterator it8 = next4.intents.iterator();
            while (it8.hasNext()) {
                ((VPackage.ProviderIntentInfo) it8.next()).provider = next4;
            }
        }
        Iterator<VPackage.InstrumentationComponent> it9 = vPackage.instrumentation.iterator();
        while (it9.hasNext()) {
            it9.next().owner = vPackage;
        }
        Iterator<VPackage.PermissionComponent> it10 = vPackage.permissions.iterator();
        while (it10.hasNext()) {
            it10.next().owner = vPackage;
        }
        Iterator<VPackage.PermissionGroupComponent> it11 = vPackage.permissionGroups.iterator();
        while (it11.hasNext()) {
            it11.next().owner = vPackage;
        }
        int i = 4;
        if (GmsSupport.isGoogleService(vPackage.packageName)) {
            i = 12;
        }
        ApplicationInfo applicationInfo = vPackage.applicationInfo;
        applicationInfo.flags = i | applicationInfo.flags;
    }

    public static PackageInfo generatePackageInfo(VPackage vPackage, int i, long j, long j2, PackageUserState packageUserState, int i2) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        if (!checkUseInstalledOrHidden(packageUserState, i)) {
            return null;
        }
        if (vPackage.mSignatures == null) {
            readSignature(vPackage);
        }
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = vPackage.packageName;
        packageInfo.versionCode = vPackage.mVersionCode;
        packageInfo.sharedUserLabel = vPackage.mSharedUserLabel;
        packageInfo.versionName = vPackage.mVersionName;
        packageInfo.sharedUserId = vPackage.mSharedUserId;
        packageInfo.applicationInfo = generateApplicationInfo(vPackage, i, packageUserState, i2);
        packageInfo.firstInstallTime = j;
        packageInfo.lastUpdateTime = j2;
        if (vPackage.requestedPermissions != null && !vPackage.requestedPermissions.isEmpty()) {
            String[] strArr = new String[vPackage.requestedPermissions.size()];
            vPackage.requestedPermissions.toArray(strArr);
            packageInfo.requestedPermissions = strArr;
        }
        if ((i & 256) != 0) {
            packageInfo.gids = PackageParserCompat.GIDS;
        }
        if ((i & 16384) != 0) {
            int size6 = vPackage.configPreferences != null ? vPackage.configPreferences.size() : 0;
            if (size6 > 0) {
                packageInfo.configPreferences = new ConfigurationInfo[size6];
                vPackage.configPreferences.toArray(packageInfo.configPreferences);
            }
            int size7 = vPackage.reqFeatures != null ? vPackage.reqFeatures.size() : 0;
            if (size7 > 0) {
                packageInfo.reqFeatures = new FeatureInfo[size7];
                vPackage.reqFeatures.toArray(packageInfo.reqFeatures);
            }
        }
        if ((i & 1) != 0 && (size5 = vPackage.activities.size()) > 0) {
            ActivityInfo[] activityInfoArr = new ActivityInfo[size5];
            int i3 = 0;
            for (int i4 = 0; i4 < size5; i4++) {
                i3++;
                activityInfoArr[i3] = generateActivityInfo(vPackage.activities.get(i4), i, packageUserState, i2);
            }
            packageInfo.activities = activityInfoArr;
        }
        if ((i & 2) != 0 && (size4 = vPackage.receivers.size()) > 0) {
            ActivityInfo[] activityInfoArr2 = new ActivityInfo[size4];
            int i5 = 0;
            for (int i6 = 0; i6 < size4; i6++) {
                i5++;
                activityInfoArr2[i5] = generateActivityInfo(vPackage.receivers.get(i6), i, packageUserState, i2);
            }
            packageInfo.receivers = activityInfoArr2;
        }
        if ((i & 4) != 0 && (size3 = vPackage.services.size()) > 0) {
            ServiceInfo[] serviceInfoArr = new ServiceInfo[size3];
            int i7 = 0;
            for (int i8 = 0; i8 < size3; i8++) {
                i7++;
                serviceInfoArr[i7] = generateServiceInfo(vPackage.services.get(i8), i, packageUserState, i2);
            }
            packageInfo.services = serviceInfoArr;
        }
        if ((i & 8) != 0 && (size2 = vPackage.providers.size()) > 0) {
            ProviderInfo[] providerInfoArr = new ProviderInfo[size2];
            int i9 = 0;
            for (int i10 = 0; i10 < size2; i10++) {
                i9++;
                providerInfoArr[i9] = generateProviderInfo(vPackage.providers.get(i10), i, packageUserState, i2);
            }
            packageInfo.providers = providerInfoArr;
        }
        if ((i & 16) != 0 && (size = vPackage.instrumentation.size()) > 0) {
            packageInfo.instrumentation = new InstrumentationInfo[size];
            for (int i11 = 0; i11 < size; i11++) {
                packageInfo.instrumentation[i11] = generateInstrumentationInfo(vPackage.instrumentation.get(i11), i);
            }
        }
        if ((i & 4096) != 0) {
            int size8 = vPackage.permissions.size();
            if (size8 > 0) {
                packageInfo.permissions = new PermissionInfo[size8];
                for (int i12 = 0; i12 < size8; i12++) {
                    packageInfo.permissions[i12] = generatePermissionInfo(vPackage.permissions.get(i12), i);
                }
            }
            int size9 = vPackage.requestedPermissions == null ? 0 : vPackage.requestedPermissions.size();
            if (size9 > 0) {
                packageInfo.requestedPermissions = new String[size9];
                for (int i13 = 0; i13 < size9; i13++) {
                    packageInfo.requestedPermissions[i13] = vPackage.requestedPermissions.get(i13);
                }
            }
        }
        if ((i & 64) != 0) {
            int length = vPackage.mSignatures != null ? vPackage.mSignatures.length : 0;
            if (length > 0) {
                packageInfo.signatures = new Signature[length];
                System.arraycopy(vPackage.mSignatures, 0, packageInfo.signatures, 0, length);
            } else {
                try {
                    packageInfo.signatures = VirtualCore.get().getUnHookPackageManager().getPackageInfo(vPackage.packageName, 64).signatures;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return packageInfo;
    }

    public static ApplicationInfo generateApplicationInfo(VPackage vPackage, int i, PackageUserState packageUserState, int i2) {
        if (vPackage == null || !checkUseInstalledOrHidden(packageUserState, i)) {
            return null;
        }
        ApplicationInfo applicationInfo = new ApplicationInfo(vPackage.applicationInfo);
        if ((i & 128) != 0) {
            applicationInfo.metaData = vPackage.mAppMetaData;
        }
        initApplicationAsUser(applicationInfo, i2);
        return applicationInfo;
    }

    public static ActivityInfo generateActivityInfo(VPackage.ActivityComponent activityComponent, int i, PackageUserState packageUserState, int i2) {
        if (activityComponent == null || !checkUseInstalledOrHidden(packageUserState, i)) {
            return null;
        }
        ActivityInfo activityInfo = new ActivityInfo(activityComponent.info);
        if (!((i & 128) == 0 || activityComponent.metaData == null)) {
            activityInfo.metaData = activityComponent.metaData;
        }
        activityInfo.enabled = isEnabledLPr(activityComponent.info, 0, i2);
        activityInfo.applicationInfo = generateApplicationInfo(activityComponent.owner, i, packageUserState, i2);
        return activityInfo;
    }

    public static boolean isEnabledLPr(ComponentInfo componentInfo, int i, int i2) {
        int i3 = ComponentStateManager.user(i2).get(ComponentUtils.toComponentName(componentInfo));
        if (i3 == 0) {
            return componentInfo.enabled;
        }
        if (i3 == 2 || i3 == 4 || i3 == 3) {
            return false;
        }
        return i3 == 1 ? true : true;
    }

    public static ServiceInfo generateServiceInfo(VPackage.ServiceComponent serviceComponent, int i, PackageUserState packageUserState, int i2) {
        if (serviceComponent == null || !checkUseInstalledOrHidden(packageUserState, i)) {
            return null;
        }
        ServiceInfo serviceInfo = new ServiceInfo(serviceComponent.info);
        if (!((i & 128) == 0 || serviceComponent.metaData == null)) {
            serviceInfo.metaData = serviceComponent.metaData;
        }
        serviceInfo.enabled = isEnabledLPr(serviceComponent.info, 0, i2);
        serviceInfo.applicationInfo = generateApplicationInfo(serviceComponent.owner, i, packageUserState, i2);
        return serviceInfo;
    }

    public static ProviderInfo generateProviderInfo(VPackage.ProviderComponent providerComponent, int i, PackageUserState packageUserState, int i2) {
        if (providerComponent == null || !checkUseInstalledOrHidden(packageUserState, i)) {
            return null;
        }
        ProviderInfo providerInfo = new ProviderInfo(providerComponent.info);
        if (!((i & 128) == 0 || providerComponent.metaData == null)) {
            providerInfo.metaData = providerComponent.metaData;
        }
        if ((i & 2048) == 0) {
            providerInfo.uriPermissionPatterns = null;
        }
        providerInfo.enabled = isEnabledLPr(providerComponent.info, 0, i2);
        providerInfo.applicationInfo = generateApplicationInfo(providerComponent.owner, i, packageUserState, i2);
        return providerInfo;
    }

    public static InstrumentationInfo generateInstrumentationInfo(VPackage.InstrumentationComponent instrumentationComponent, int i) {
        if (instrumentationComponent == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return instrumentationComponent.info;
        }
        InstrumentationInfo instrumentationInfo = new InstrumentationInfo(instrumentationComponent.info);
        instrumentationInfo.metaData = instrumentationComponent.metaData;
        return instrumentationInfo;
    }

    public static PermissionInfo generatePermissionInfo(VPackage.PermissionComponent permissionComponent, int i) {
        if (permissionComponent == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return permissionComponent.info;
        }
        PermissionInfo permissionInfo = new PermissionInfo(permissionComponent.info);
        permissionInfo.metaData = permissionComponent.metaData;
        return permissionInfo;
    }

    public static PermissionGroupInfo generatePermissionGroupInfo(VPackage.PermissionGroupComponent permissionGroupComponent, int i) {
        if (permissionGroupComponent == null) {
            return null;
        }
        if ((i & 128) == 0) {
            return permissionGroupComponent.info;
        }
        PermissionGroupInfo permissionGroupInfo = new PermissionGroupInfo(permissionGroupComponent.info);
        permissionGroupInfo.metaData = permissionGroupComponent.metaData;
        return permissionGroupInfo;
    }

    private static boolean checkUseInstalledOrHidden(PackageUserState packageUserState, int i) {
        return (packageUserState.installed && !packageUserState.hidden) || (i & 8192) != 0;
    }
}
