package com.lody.virtual.server.p063pm;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.fixer.ComponentFixer;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.helper.compat.PermissionCompat;
import com.lody.virtual.helper.utils.SignaturesUtils;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.ReceiverInfo;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.interfaces.IPackageManager;
import com.lody.virtual.server.p063pm.installer.VPackageInstallerService;
import com.lody.virtual.server.p063pm.parser.PackageParserEx;
import com.lody.virtual.server.p063pm.parser.VPackage;
import com.stripe.android.model.C2395g;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p110z1.C4963cj;

/* renamed from: com.lody.virtual.server.pm.VPackageManagerService */
/* loaded from: classes.dex */
public class VPackageManagerService extends IPackageManager.Stub {
    static final String TAG = "PackageManager";
    private final ActivityIntentResolver mActivities;
    private final Map<String, String[]> mDangerousPermissions;
    private final Map<String, VPackage> mPackages;
    private final HashMap<String, VPackage.PermissionGroupComponent> mPermissionGroups;
    private final HashMap<String, VPackage.PermissionComponent> mPermissions;
    private final ProviderIntentResolver mProviders;
    private final HashMap<String, VPackage.ProviderComponent> mProvidersByAuthority;
    private final HashMap<ComponentName, VPackage.ProviderComponent> mProvidersByComponent;
    private final ActivityIntentResolver mReceivers;
    private final ServiceIntentResolver mServices;
    static final Comparator<ResolveInfo> sResolvePrioritySorter = new Comparator<ResolveInfo>() { // from class: com.lody.virtual.server.pm.VPackageManagerService.1
        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            int i = resolveInfo.priority;
            int i2 = resolveInfo2.priority;
            if (i != i2) {
                return i > i2 ? -1 : 1;
            }
            int i3 = resolveInfo.preferredOrder;
            int i4 = resolveInfo2.preferredOrder;
            if (i3 != i4) {
                return i3 > i4 ? -1 : 1;
            }
            if (resolveInfo.isDefault != resolveInfo2.isDefault) {
                return resolveInfo.isDefault ? -1 : 1;
            }
            int i5 = resolveInfo.match;
            int i6 = resolveInfo2.match;
            if (i5 != i6) {
                return i5 > i6 ? -1 : 1;
            }
            return 0;
        }
    };
    private static final Singleton<VPackageManagerService> gService = new Singleton<VPackageManagerService>() { // from class: com.lody.virtual.server.pm.VPackageManagerService.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VPackageManagerService create() {
            return new VPackageManagerService();
        }
    };
    private static final Comparator<ProviderInfo> sProviderInitOrderSorter = new Comparator<ProviderInfo>() { // from class: com.lody.virtual.server.pm.VPackageManagerService.3
        public int compare(ProviderInfo providerInfo, ProviderInfo providerInfo2) {
            int i = providerInfo.initOrder;
            int i2 = providerInfo2.initOrder;
            if (i > i2) {
                return -1;
            }
            return i < i2 ? 1 : 0;
        }
    };

    private ResolveInfo findPreferredActivity(Intent intent, String str, int i, List<ResolveInfo> list, int i2) {
        return null;
    }

    private VPackageManagerService() {
        ProviderIntentResolver providerIntentResolver = null;
        this.mActivities = new ActivityIntentResolver();
        this.mServices = new ServiceIntentResolver();
        this.mReceivers = new ActivityIntentResolver();
        this.mProviders = Build.VERSION.SDK_INT >= 19 ? new ProviderIntentResolver() : providerIntentResolver;
        this.mProvidersByComponent = new HashMap<>();
        this.mPermissions = new HashMap<>();
        this.mPermissionGroups = new HashMap<>();
        this.mProvidersByAuthority = new HashMap<>();
        this.mPackages = PackageCacheManager.PACKAGE_CACHE;
        this.mDangerousPermissions = new HashMap();
    }

    public static void systemReady() {
        new VUserManagerService(VirtualCore.get().getContext(), get(), new char[0], get().mPackages);
    }

    public static VPackageManagerService get() {
        return gService.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void analyzePackageLocked(VPackage vPackage) {
        int size = vPackage.activities.size();
        for (int i = 0; i < size; i++) {
            VPackage.ActivityComponent activityComponent = vPackage.activities.get(i);
            if (activityComponent.info.processName == null) {
                activityComponent.info.processName = activityComponent.info.packageName;
            }
            this.mActivities.addActivity(activityComponent, ServiceManagerNative.ACTIVITY);
        }
        int size2 = vPackage.services.size();
        for (int i2 = 0; i2 < size2; i2++) {
            VPackage.ServiceComponent serviceComponent = vPackage.services.get(i2);
            if (serviceComponent.info.processName == null) {
                serviceComponent.info.processName = serviceComponent.info.packageName;
            }
            this.mServices.addService(serviceComponent);
        }
        int size3 = vPackage.receivers.size();
        for (int i3 = 0; i3 < size3; i3++) {
            VPackage.ActivityComponent activityComponent2 = vPackage.receivers.get(i3);
            if (activityComponent2.info.processName == null) {
                activityComponent2.info.processName = activityComponent2.info.packageName;
            }
            this.mReceivers.addActivity(activityComponent2, C2395g.f12127u);
        }
        int size4 = vPackage.providers.size();
        for (int i4 = 0; i4 < size4; i4++) {
            VPackage.ProviderComponent providerComponent = vPackage.providers.get(i4);
            if (providerComponent.info.processName == null) {
                providerComponent.info.processName = providerComponent.info.packageName;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                this.mProviders.addProvider(providerComponent);
            }
            String[] split = providerComponent.info.authority.split(C4963cj.f20745b);
            synchronized (this.mProvidersByAuthority) {
                for (String str : split) {
                    if (!this.mProvidersByAuthority.containsKey(str)) {
                        this.mProvidersByAuthority.put(str, providerComponent);
                    }
                }
            }
            this.mProvidersByComponent.put(providerComponent.getComponentName(), providerComponent);
        }
        int size5 = vPackage.permissions.size();
        for (int i5 = 0; i5 < size5; i5++) {
            VPackage.PermissionComponent permissionComponent = vPackage.permissions.get(i5);
            this.mPermissions.put(permissionComponent.info.name, permissionComponent);
        }
        int size6 = vPackage.permissionGroups.size();
        for (int i6 = 0; i6 < size6; i6++) {
            VPackage.PermissionGroupComponent permissionGroupComponent = vPackage.permissionGroups.get(i6);
            this.mPermissionGroups.put(permissionGroupComponent.className, permissionGroupComponent);
        }
        synchronized (this.mDangerousPermissions) {
            this.mDangerousPermissions.put(vPackage.packageName, PermissionCompat.findDangerousPermissions(vPackage.requestedPermissions));
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public String[] getDangrousPermissions(String str) {
        String[] strArr;
        synchronized (this.mDangerousPermissions) {
            strArr = this.mDangerousPermissions.get(str);
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deletePackageLocked(String str) {
        VPackage vPackage = this.mPackages.get(str);
        if (vPackage != null) {
            int size = vPackage.activities.size();
            for (int i = 0; i < size; i++) {
                this.mActivities.removeActivity(vPackage.activities.get(i), ServiceManagerNative.ACTIVITY);
            }
            int size2 = vPackage.services.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.mServices.removeService(vPackage.services.get(i2));
            }
            int size3 = vPackage.receivers.size();
            for (int i3 = 0; i3 < size3; i3++) {
                this.mReceivers.removeActivity(vPackage.receivers.get(i3), C2395g.f12127u);
            }
            int size4 = vPackage.providers.size();
            for (int i4 = 0; i4 < size4; i4++) {
                VPackage.ProviderComponent providerComponent = vPackage.providers.get(i4);
                if (Build.VERSION.SDK_INT >= 19) {
                    this.mProviders.removeProvider(providerComponent);
                }
                String[] split = providerComponent.info.authority.split(C4963cj.f20745b);
                synchronized (this.mProvidersByAuthority) {
                    for (String str2 : split) {
                        this.mProvidersByAuthority.remove(str2);
                    }
                }
                this.mProvidersByComponent.remove(providerComponent.getComponentName());
            }
            int size5 = vPackage.permissions.size();
            for (int i5 = 0; i5 < size5; i5++) {
                this.mPermissions.remove(vPackage.permissions.get(i5).className);
            }
            int size6 = vPackage.permissionGroups.size();
            for (int i6 = 0; i6 < size6; i6++) {
                this.mPermissionGroups.remove(vPackage.permissionGroups.get(i6).className);
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<String> getSharedLibraries(String str) {
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (vPackage == null) {
                return null;
            }
            return vPackage.usesLibraries;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public PackageInfo getPackageInfo(String str, int i, int i2) {
        checkUserId(i2);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (vPackage == null) {
                return null;
            }
            return generatePackageInfo(vPackage, (PackageSetting) vPackage.mExtras, i, i2);
        }
    }

    private PackageSetting getPackageSettingLocked(String str) {
        VPackage vPackage;
        synchronized (this.mPackages) {
            vPackage = this.mPackages.get(str);
        }
        if (vPackage != null) {
            return (PackageSetting) vPackage.mExtras;
        }
        return null;
    }

    private PackageInfo generatePackageInfo(VPackage vPackage, PackageSetting packageSetting, int i, int i2) {
        PackageInfo generatePackageInfo = PackageParserEx.generatePackageInfo(vPackage, updateFlagsNought(i), packageSetting.firstInstallTime, packageSetting.lastUpdateTime, packageSetting.readUserState(i2), i2);
        if (generatePackageInfo != null) {
            return generatePackageInfo;
        }
        return null;
    }

    private int updateFlagsNought(int i) {
        return (Build.VERSION.SDK_INT >= 24 && (i & 786432) == 0) ? i | 786432 : i;
    }

    private void checkUserId(int i) {
        if (!VUserManagerService.get().exists(i)) {
            throw new SecurityException("Invalid userId " + i);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(componentName.getPackageName());
            if (vPackage != null) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                VPackage.ActivityComponent activityComponent = (VPackage.ActivityComponent) this.mActivities.mActivities.get(componentName);
                if (activityComponent != null) {
                    ActivityInfo generateActivityInfo = PackageParserEx.generateActivityInfo(activityComponent, updateFlagsNought, packageSetting.readUserState(i2), i2);
                    ComponentFixer.fixComponentInfo(generateActivityInfo);
                    return generateActivityInfo;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) {
        synchronized (this.mPackages) {
            VPackage.ActivityComponent activityComponent = (VPackage.ActivityComponent) this.mActivities.mActivities.get(componentName);
            if (activityComponent == null) {
                return false;
            }
            for (int i = 0; i < activityComponent.intents.size(); i++) {
                if (((VPackage.ActivityIntentInfo) activityComponent.intents.get(i)).filter.match(intent.getAction(), str, intent.getScheme(), intent.getData(), intent.getCategories(), TAG) >= 0) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(componentName.getPackageName());
            if (vPackage != null) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                VPackage.ActivityComponent activityComponent = (VPackage.ActivityComponent) this.mReceivers.mActivities.get(componentName);
                if (activityComponent != null && PackageParserEx.isEnabledLPr(activityComponent.info, updateFlagsNought, i2)) {
                    ActivityInfo generateActivityInfo = PackageParserEx.generateActivityInfo(activityComponent, updateFlagsNought, packageSetting.readUserState(i2), i2);
                    ComponentFixer.fixComponentInfo(generateActivityInfo);
                    return generateActivityInfo;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(componentName.getPackageName());
            if (vPackage != null) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                VPackage.ServiceComponent serviceComponent = (VPackage.ServiceComponent) this.mServices.mServices.get(componentName);
                if (serviceComponent != null) {
                    ServiceInfo generateServiceInfo = PackageParserEx.generateServiceInfo(serviceComponent, updateFlagsNought, packageSetting.readUserState(i2), i2);
                    ComponentFixer.fixComponentInfo(generateServiceInfo);
                    return generateServiceInfo;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(componentName.getPackageName());
            if (vPackage != null) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                VPackage.ProviderComponent providerComponent = this.mProvidersByComponent.get(componentName);
                if (providerComponent != null && PackageParserEx.isEnabledLPr(providerComponent.info, updateFlagsNought, i2)) {
                    ProviderInfo generateProviderInfo = PackageParserEx.generateProviderInfo(providerComponent, updateFlagsNought, packageSetting.readUserState(i2), i2);
                    ComponentFixer.fixComponentInfo(generateProviderInfo);
                    return generateProviderInfo;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        return chooseBestActivity(intent, str, updateFlagsNought, queryIntentActivities(intent, str, updateFlagsNought, 0));
    }

    private ResolveInfo chooseBestActivity(Intent intent, String str, int i, List<ResolveInfo> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size == 1) {
            return list.get(0);
        }
        if (size <= 1) {
            return null;
        }
        ResolveInfo resolveInfo = list.get(0);
        ResolveInfo resolveInfo2 = list.get(1);
        if (resolveInfo.priority != resolveInfo2.priority || resolveInfo.preferredOrder != resolveInfo2.preferredOrder || resolveInfo.isDefault != resolveInfo2.isDefault) {
            return list.get(0);
        }
        ResolveInfo findPreferredActivity = findPreferredActivity(intent, str, i, list, resolveInfo.priority);
        return findPreferredActivity != null ? findPreferredActivity : list.get(0);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<ResolveInfo> queryIntentActivities(Intent intent, String str, int i, int i2) {
        Intent intent2;
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        ComponentName component = intent.getComponent();
        if (component != null || Build.VERSION.SDK_INT < 15 || intent.getSelector() == null) {
            intent2 = intent;
        } else {
            Intent selector = intent.getSelector();
            component = selector.getComponent();
            intent2 = selector;
        }
        if (component != null) {
            ArrayList arrayList = new ArrayList(1);
            ActivityInfo activityInfo = getActivityInfo(component, updateFlagsNought, i2);
            if (activityInfo != null) {
                ResolveInfo resolveInfo = new ResolveInfo();
                resolveInfo.activityInfo = activityInfo;
                arrayList.add(resolveInfo);
            }
            return arrayList;
        }
        synchronized (this.mPackages) {
            String str2 = intent2.getPackage();
            if (str2 == null) {
                return this.mActivities.queryIntent(intent2, str, updateFlagsNought, i2);
            }
            VPackage vPackage = this.mPackages.get(str2);
            if (vPackage != null) {
                return this.mActivities.queryIntentForPackage(intent2, str, updateFlagsNought, vPackage.activities, i2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<ResolveInfo> queryIntentReceivers(Intent intent, String str, int i, int i2) {
        Intent intent2;
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        ComponentName component = intent.getComponent();
        if (component != null || Build.VERSION.SDK_INT < 15 || intent.getSelector() == null) {
            intent2 = intent;
        } else {
            Intent selector = intent.getSelector();
            component = selector.getComponent();
            intent2 = selector;
        }
        if (component != null) {
            ArrayList arrayList = new ArrayList(1);
            ActivityInfo receiverInfo = getReceiverInfo(component, updateFlagsNought, i2);
            if (receiverInfo != null) {
                ResolveInfo resolveInfo = new ResolveInfo();
                resolveInfo.activityInfo = receiverInfo;
                arrayList.add(resolveInfo);
            }
            return arrayList;
        }
        synchronized (this.mPackages) {
            String str2 = intent2.getPackage();
            if (str2 == null) {
                return this.mReceivers.queryIntent(intent2, str, updateFlagsNought, i2);
            }
            VPackage vPackage = this.mPackages.get(str2);
            if (vPackage != null) {
                return this.mReceivers.queryIntentForPackage(intent2, str, updateFlagsNought, vPackage.receivers, i2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ResolveInfo resolveService(Intent intent, String str, int i, int i2) {
        checkUserId(i2);
        List<ResolveInfo> queryIntentServices = queryIntentServices(intent, str, updateFlagsNought(i), i2);
        if (queryIntentServices == null || queryIntentServices.size() < 1) {
            return null;
        }
        return queryIntentServices.get(0);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<ResolveInfo> queryIntentServices(Intent intent, String str, int i, int i2) {
        Intent intent2;
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        ComponentName component = intent.getComponent();
        if (component != null || Build.VERSION.SDK_INT < 15 || intent.getSelector() == null) {
            intent2 = intent;
        } else {
            Intent selector = intent.getSelector();
            component = selector.getComponent();
            intent2 = selector;
        }
        if (component != null) {
            ArrayList arrayList = new ArrayList(1);
            ServiceInfo serviceInfo = getServiceInfo(component, updateFlagsNought, i2);
            if (serviceInfo != null) {
                ResolveInfo resolveInfo = new ResolveInfo();
                resolveInfo.serviceInfo = serviceInfo;
                arrayList.add(resolveInfo);
            }
            return arrayList;
        }
        synchronized (this.mPackages) {
            String str2 = intent2.getPackage();
            if (str2 == null) {
                return this.mServices.queryIntent(intent2, str, updateFlagsNought, i2);
            }
            VPackage vPackage = this.mPackages.get(str2);
            if (vPackage != null) {
                return this.mServices.queryIntentForPackage(intent2, str, updateFlagsNought, vPackage.services, i2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    @TargetApi(19)
    public List<ResolveInfo> queryIntentContentProviders(Intent intent, String str, int i, int i2) {
        Intent intent2;
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        ComponentName component = intent.getComponent();
        if (component != null || Build.VERSION.SDK_INT < 15 || intent.getSelector() == null) {
            intent2 = intent;
        } else {
            Intent selector = intent.getSelector();
            component = selector.getComponent();
            intent2 = selector;
        }
        if (component != null) {
            ArrayList arrayList = new ArrayList(1);
            ProviderInfo providerInfo = getProviderInfo(component, updateFlagsNought, i2);
            if (providerInfo != null) {
                ResolveInfo resolveInfo = new ResolveInfo();
                resolveInfo.providerInfo = providerInfo;
                arrayList.add(resolveInfo);
            }
            return arrayList;
        }
        synchronized (this.mPackages) {
            String str2 = intent2.getPackage();
            if (str2 == null) {
                return this.mProviders.queryIntent(intent2, str, updateFlagsNought, i2);
            }
            VPackage vPackage = this.mPackages.get(str2);
            if (vPackage != null) {
                return this.mProviders.queryIntentForPackage(intent2, str, updateFlagsNought, vPackage.providers, i2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public VParceledListSlice<ProviderInfo> queryContentProviders(String str, int i, int i2) {
        int userId = VUserHandle.getUserId(i);
        checkUserId(userId);
        int updateFlagsNought = updateFlagsNought(i2);
        ArrayList arrayList = new ArrayList(3);
        synchronized (this.mPackages) {
            for (VPackage.ProviderComponent providerComponent : this.mProvidersByAuthority.values()) {
                PackageSetting packageSetting = (PackageSetting) providerComponent.owner.mExtras;
                if (str == null || (packageSetting.appId == VUserHandle.getAppId(i) && providerComponent.info.processName.equals(str))) {
                    arrayList.add(PackageParserEx.generateProviderInfo(providerComponent, updateFlagsNought, packageSetting.readUserState(userId), userId));
                }
            }
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList, sProviderInitOrderSorter);
        }
        return new VParceledListSlice<>(arrayList);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public VParceledListSlice<PackageInfo> getInstalledPackages(int i, int i2) {
        checkUserId(i2);
        ArrayList arrayList = new ArrayList(this.mPackages.size());
        synchronized (this.mPackages) {
            for (VPackage vPackage : this.mPackages.values()) {
                PackageInfo generatePackageInfo = generatePackageInfo(vPackage, (PackageSetting) vPackage.mExtras, i, i2);
                if (generatePackageInfo != null) {
                    arrayList.add(generatePackageInfo);
                }
            }
        }
        return new VParceledListSlice<>(arrayList);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public VParceledListSlice<ApplicationInfo> getInstalledApplications(int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        ArrayList arrayList = new ArrayList(this.mPackages.size());
        synchronized (this.mPackages) {
            for (VPackage vPackage : this.mPackages.values()) {
                ApplicationInfo generateApplicationInfo = PackageParserEx.generateApplicationInfo(vPackage, updateFlagsNought, ((PackageSetting) vPackage.mExtras).readUserState(i2), i2);
                if (generateApplicationInfo != null) {
                    arrayList.add(generateApplicationInfo);
                }
            }
        }
        return new VParceledListSlice<>(arrayList);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<ReceiverInfo> getReceiverInfos(String str, String str2, int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (vPackage == null) {
                return Collections.emptyList();
            }
            Iterator<VPackage.ActivityComponent> it = vPackage.receivers.iterator();
            while (it.hasNext()) {
                VPackage.ActivityComponent next = it.next();
                if (PackageParserEx.isEnabledLPr(next.info, 0, i) && next.info.processName.equals(str2)) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it2 = next.intents.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((VPackage.ActivityIntentInfo) it2.next()).filter);
                    }
                    arrayList.add(new ReceiverInfo(next.info, arrayList2));
                }
            }
            return arrayList;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public PermissionInfo getPermissionInfo(String str, int i) {
        synchronized (this.mPackages) {
            VPackage.PermissionComponent permissionComponent = this.mPermissions.get(str);
            if (permissionComponent == null) {
                return null;
            }
            return new PermissionInfo(permissionComponent.info);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<PermissionInfo> queryPermissionsByGroup(String str, int i) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            synchronized (this.mPackages) {
                for (VPackage.PermissionComponent permissionComponent : this.mPermissions.values()) {
                    if (permissionComponent.info.group.equals(str)) {
                        arrayList.add(permissionComponent.info);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public PermissionGroupInfo getPermissionGroupInfo(String str, int i) {
        synchronized (this.mPackages) {
            VPackage.PermissionGroupComponent permissionGroupComponent = this.mPermissionGroups.get(str);
            if (permissionGroupComponent == null) {
                return null;
            }
            return new PermissionGroupInfo(permissionGroupComponent.info);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        ArrayList arrayList;
        synchronized (this.mPackages) {
            arrayList = new ArrayList(this.mPermissionGroups.size());
            for (VPackage.PermissionGroupComponent permissionGroupComponent : this.mPermissionGroups.values()) {
                arrayList.add(new PermissionGroupInfo(permissionGroupComponent.info));
            }
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ProviderInfo resolveContentProvider(String str, int i, int i2) {
        VPackage.ProviderComponent providerComponent;
        ProviderInfo generateProviderInfo;
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mProvidersByAuthority) {
            providerComponent = this.mProvidersByAuthority.get(str);
        }
        if (providerComponent == null || (generateProviderInfo = PackageParserEx.generateProviderInfo(providerComponent, updateFlagsNought, ((PackageSetting) providerComponent.owner.mExtras).readUserState(i2), i2)) == null) {
            return null;
        }
        ComponentFixer.fixComponentInfo(generateProviderInfo);
        return generateProviderInfo;
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public ApplicationInfo getApplicationInfo(String str, int i, int i2) {
        checkUserId(i2);
        int updateFlagsNought = updateFlagsNought(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (vPackage == null) {
                return null;
            }
            return PackageParserEx.generateApplicationInfo(vPackage, updateFlagsNought, ((PackageSetting) vPackage.mExtras).readUserState(i2), i2);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public String[] getPackagesForUid(int i) {
        int userId = VUserHandle.getUserId(i);
        checkUserId(userId);
        synchronized (this) {
            ArrayList arrayList = new ArrayList(2);
            for (VPackage vPackage : this.mPackages.values()) {
                if (VUserHandle.getUid(userId, ((PackageSetting) vPackage.mExtras).appId) == i || i == 9001) {
                    arrayList.add(vPackage.packageName);
                }
            }
            if (arrayList.isEmpty()) {
                VLog.m18992e(TAG, "getPackagesForUid return an empty result.");
                return null;
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public int getPackageUid(String str, int i) {
        checkUserId(i);
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (vPackage == null) {
                return -1;
            }
            return VUserHandle.getUid(i, ((PackageSetting) vPackage.mExtras).appId);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public String getNameForUid(int i) {
        int appId = VUserHandle.getAppId(i);
        synchronized (this.mPackages) {
            for (VPackage vPackage : this.mPackages.values()) {
                PackageSetting packageSetting = (PackageSetting) vPackage.mExtras;
                if (packageSetting.appId == appId) {
                    return packageSetting.packageName;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public List<String> querySharedPackages(String str) {
        synchronized (this.mPackages) {
            VPackage vPackage = this.mPackages.get(str);
            if (!(vPackage == null || vPackage.mSharedUserId == null)) {
                ArrayList arrayList = new ArrayList();
                for (VPackage vPackage2 : this.mPackages.values()) {
                    if (TextUtils.equals(vPackage2.mSharedUserId, vPackage.mSharedUserId)) {
                        arrayList.add(vPackage2.packageName);
                    }
                }
                return arrayList;
            }
            return Collections.EMPTY_LIST;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public IBinder getPackageInstaller() {
        return VPackageInstallerService.get();
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public boolean isVirtualAuthority(String str) {
        boolean containsKey;
        synchronized (this.mProvidersByAuthority) {
            containsKey = this.mProvidersByAuthority.containsKey(str);
        }
        return containsKey;
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) {
        if (componentName != null) {
            checkUserId(i3);
            ComponentStateManager.user(i3).set(componentName, i);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public int getComponentEnabledSetting(ComponentName componentName, int i) {
        int i2;
        if (componentName == null) {
            return 0;
        }
        checkUserId(i);
        synchronized (this.mPackages) {
            i2 = ComponentStateManager.user(i).get(componentName);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createNewUser(int i, File file) {
        for (VPackage vPackage : this.mPackages.values()) {
            ((PackageSetting) vPackage.mExtras).modifyUserState(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanUpUser(int i) {
        for (VPackage vPackage : this.mPackages.values()) {
            ((PackageSetting) vPackage.mExtras).removeUser(i);
        }
        ComponentStateManager.user(i).clearAll();
    }

    private PermissionInfo findPermission(String str) {
        synchronized (this.mPackages) {
            for (VPackage vPackage : this.mPackages.values()) {
                ArrayList<VPackage.PermissionComponent> arrayList = vPackage.permissions;
                if (arrayList != null) {
                    Iterator<VPackage.PermissionComponent> it = arrayList.iterator();
                    while (it.hasNext()) {
                        VPackage.PermissionComponent next = it.next();
                        if (next.info != null && TextUtils.equals(str, next.info.name)) {
                            return next.info;
                        }
                    }
                    continue;
                }
            }
            return null;
        }
    }

    private boolean hasRequestedPermission(String str, String str2) {
        VPackage vPackage;
        synchronized (this.mPackages) {
            vPackage = this.mPackages.get(str2);
        }
        if (vPackage == null || vPackage.requestedPermissions == null) {
            return false;
        }
        return vPackage.requestedPermissions.contains(str);
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public int checkPermission(boolean z, String str, String str2, int i) {
        if ("android.permission.INTERACT_ACROSS_USERS".equals(str) || "android.permission.INTERACT_ACROSS_USERS_FULL".equals(str)) {
            return -1;
        }
        if (getPermissionInfo(str, 0) != null) {
            return 0;
        }
        return VirtualCore.getPM().checkPermission(str, StubManifest.getStubPackageName(z));
    }

    @Override // com.lody.virtual.server.interfaces.IPackageManager
    public int checkSignatures(String str, String str2) {
        if (TextUtils.equals(str, str2)) {
            return 0;
        }
        PackageInfo packageInfo = getPackageInfo(str, 64, 0);
        PackageInfo packageInfo2 = getPackageInfo(str2, 64, 0);
        if (packageInfo == null) {
            try {
                packageInfo = VirtualCore.get().getUnHookPackageManager().getPackageInfo(str, 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return -4;
            }
        }
        if (packageInfo2 == null) {
            try {
                packageInfo2 = VirtualCore.get().getUnHookPackageManager().getPackageInfo(str2, 64);
            } catch (PackageManager.NameNotFoundException unused2) {
                return -4;
            }
        }
        return SignaturesUtils.compareSignatures(packageInfo.signatures, packageInfo2.signatures);
    }

    public int checkUidPermission(boolean z, String str, int i) {
        if (getPermissionInfo(str, 0) != null) {
            return 0;
        }
        return VirtualCore.getPM().checkPermission(str, StubManifest.getStubPackageName(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lody.virtual.server.pm.VPackageManagerService$ActivityIntentResolver */
    /* loaded from: classes.dex */
    public final class ActivityIntentResolver extends IntentResolver<VPackage.ActivityIntentInfo, ResolveInfo> {
        private final HashMap<ComponentName, VPackage.ActivityComponent> mActivities;
        private int mFlags;

        /* JADX INFO: Access modifiers changed from: protected */
        public void dumpFilter(PrintWriter printWriter, String str, VPackage.ActivityIntentInfo activityIntentInfo) {
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        protected void dumpFilterLabel(PrintWriter printWriter, String str, Object obj, int i) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean isFilterStopped(VPackage.ActivityIntentInfo activityIntentInfo) {
            return false;
        }

        private ActivityIntentResolver() {
            this.mActivities = new HashMap<>();
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        public List<ResolveInfo> queryIntent(Intent intent, String str, boolean z, int i) {
            this.mFlags = z ? 65536 : 0;
            return super.queryIntent(intent, str, z, i);
        }

        List<ResolveInfo> queryIntent(Intent intent, String str, int i, int i2) {
            this.mFlags = i;
            return super.queryIntent(intent, str, (i & 65536) != 0, i2);
        }

        List<ResolveInfo> queryIntentForPackage(Intent intent, String str, int i, ArrayList<VPackage.ActivityComponent> arrayList, int i2) {
            if (arrayList == null) {
                return null;
            }
            this.mFlags = i;
            boolean z = (i & 65536) != 0;
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                ArrayList<II> arrayList3 = arrayList.get(i3).intents;
                if (arrayList3 != 0 && arrayList3.size() > 0) {
                    VPackage.ActivityIntentInfo[] activityIntentInfoArr = new VPackage.ActivityIntentInfo[arrayList3.size()];
                    arrayList3.toArray(activityIntentInfoArr);
                    arrayList2.add(activityIntentInfoArr);
                }
            }
            return super.queryIntentFromList(intent, str, z, arrayList2, i2);
        }

        public final void addActivity(VPackage.ActivityComponent activityComponent, String str) {
            this.mActivities.put(activityComponent.getComponentName(), activityComponent);
            int size = activityComponent.intents.size();
            for (int i = 0; i < size; i++) {
                VPackage.ActivityIntentInfo activityIntentInfo = (VPackage.ActivityIntentInfo) activityComponent.intents.get(i);
                if (activityIntentInfo.filter.getPriority() > 0 && ServiceManagerNative.ACTIVITY.equals(str)) {
                    activityIntentInfo.filter.setPriority(0);
                    Log.w(VPackageManagerService.TAG, "Package " + activityComponent.info.applicationInfo.packageName + " has activity " + activityComponent.className + " with priority > 0, forcing to 0");
                }
                addFilter(activityIntentInfo);
            }
        }

        public final void removeActivity(VPackage.ActivityComponent activityComponent, String str) {
            this.mActivities.remove(activityComponent.getComponentName());
            int size = activityComponent.intents.size();
            for (int i = 0; i < size; i++) {
                removeFilter((VPackage.ActivityIntentInfo) activityComponent.intents.get(i));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean allowFilterResult(VPackage.ActivityIntentInfo activityIntentInfo, List<ResolveInfo> list) {
            ActivityInfo activityInfo = activityIntentInfo.activity.info;
            for (int size = list.size() - 1; size >= 0; size--) {
                ActivityInfo activityInfo2 = list.get(size).activityInfo;
                if (ObjectsCompat.equals(activityInfo2.name, activityInfo.name) && ObjectsCompat.equals(activityInfo2.packageName, activityInfo.packageName)) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.server.p063pm.IntentResolver
        public VPackage.ActivityIntentInfo[] newArray(int i) {
            return new VPackage.ActivityIntentInfo[i];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean isPackageForFilter(String str, VPackage.ActivityIntentInfo activityIntentInfo) {
            return str.equals(activityIntentInfo.activity.owner.packageName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ResolveInfo newResult(VPackage.ActivityIntentInfo activityIntentInfo, int i, int i2) {
            ActivityInfo generateActivityInfo;
            VPackage.ActivityComponent activityComponent = activityIntentInfo.activity;
            if (!PackageParserEx.isEnabledLPr(activityComponent.info, this.mFlags, i2) || (generateActivityInfo = PackageParserEx.generateActivityInfo(activityComponent, this.mFlags, ((PackageSetting) activityComponent.owner.mExtras).readUserState(i2), i2)) == null) {
                return null;
            }
            ResolveInfo resolveInfo = new ResolveInfo();
            resolveInfo.activityInfo = generateActivityInfo;
            if ((this.mFlags & 64) != 0) {
                resolveInfo.filter = activityIntentInfo.filter;
            }
            resolveInfo.priority = activityIntentInfo.filter.getPriority();
            resolveInfo.preferredOrder = activityComponent.owner.mPreferredOrder;
            resolveInfo.match = i;
            resolveInfo.isDefault = activityIntentInfo.hasDefault;
            resolveInfo.labelRes = activityIntentInfo.labelRes;
            resolveInfo.nonLocalizedLabel = activityIntentInfo.nonLocalizedLabel;
            resolveInfo.icon = activityIntentInfo.icon;
            return resolveInfo;
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        protected void sortResults(List<ResolveInfo> list) {
            Collections.sort(list, VPackageManagerService.sResolvePrioritySorter);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Object filterToLabel(VPackage.ActivityIntentInfo activityIntentInfo) {
            return activityIntentInfo.activity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lody.virtual.server.pm.VPackageManagerService$ServiceIntentResolver */
    /* loaded from: classes.dex */
    public final class ServiceIntentResolver extends IntentResolver<VPackage.ServiceIntentInfo, ResolveInfo> {
        private int mFlags;
        private final HashMap<ComponentName, VPackage.ServiceComponent> mServices;

        /* JADX INFO: Access modifiers changed from: protected */
        public void dumpFilter(PrintWriter printWriter, String str, VPackage.ServiceIntentInfo serviceIntentInfo) {
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        protected void dumpFilterLabel(PrintWriter printWriter, String str, Object obj, int i) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean isFilterStopped(VPackage.ServiceIntentInfo serviceIntentInfo) {
            return false;
        }

        private ServiceIntentResolver() {
            this.mServices = new HashMap<>();
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        public List<ResolveInfo> queryIntent(Intent intent, String str, boolean z, int i) {
            this.mFlags = z ? 65536 : 0;
            return super.queryIntent(intent, str, z, i);
        }

        public List<ResolveInfo> queryIntent(Intent intent, String str, int i, int i2) {
            this.mFlags = i;
            return super.queryIntent(intent, str, (i & 65536) != 0, i2);
        }

        public List<ResolveInfo> queryIntentForPackage(Intent intent, String str, int i, ArrayList<VPackage.ServiceComponent> arrayList, int i2) {
            if (arrayList == null) {
                return null;
            }
            this.mFlags = i;
            boolean z = (i & 65536) != 0;
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                ArrayList<II> arrayList3 = arrayList.get(i3).intents;
                if (arrayList3 != 0 && arrayList3.size() > 0) {
                    VPackage.ServiceIntentInfo[] serviceIntentInfoArr = new VPackage.ServiceIntentInfo[arrayList3.size()];
                    arrayList3.toArray(serviceIntentInfoArr);
                    arrayList2.add(serviceIntentInfoArr);
                }
            }
            return super.queryIntentFromList(intent, str, z, arrayList2, i2);
        }

        public final void addService(VPackage.ServiceComponent serviceComponent) {
            this.mServices.put(serviceComponent.getComponentName(), serviceComponent);
            int size = serviceComponent.intents.size();
            for (int i = 0; i < size; i++) {
                addFilter((VPackage.ServiceIntentInfo) serviceComponent.intents.get(i));
            }
        }

        public final void removeService(VPackage.ServiceComponent serviceComponent) {
            this.mServices.remove(serviceComponent.getComponentName());
            int size = serviceComponent.intents.size();
            for (int i = 0; i < size; i++) {
                removeFilter((VPackage.ServiceIntentInfo) serviceComponent.intents.get(i));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean allowFilterResult(VPackage.ServiceIntentInfo serviceIntentInfo, List<ResolveInfo> list) {
            ServiceInfo serviceInfo = serviceIntentInfo.service.info;
            for (int size = list.size() - 1; size >= 0; size--) {
                ServiceInfo serviceInfo2 = list.get(size).serviceInfo;
                if (ObjectsCompat.equals(serviceInfo2.name, serviceInfo.name) && ObjectsCompat.equals(serviceInfo2.packageName, serviceInfo.packageName)) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.lody.virtual.server.p063pm.IntentResolver
        public VPackage.ServiceIntentInfo[] newArray(int i) {
            return new VPackage.ServiceIntentInfo[i];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean isPackageForFilter(String str, VPackage.ServiceIntentInfo serviceIntentInfo) {
            return str.equals(serviceIntentInfo.service.owner.packageName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ResolveInfo newResult(VPackage.ServiceIntentInfo serviceIntentInfo, int i, int i2) {
            ServiceInfo generateServiceInfo;
            VPackage.ServiceComponent serviceComponent = serviceIntentInfo.service;
            if (!PackageParserEx.isEnabledLPr(serviceComponent.info, this.mFlags, i2) || (generateServiceInfo = PackageParserEx.generateServiceInfo(serviceComponent, this.mFlags, ((PackageSetting) serviceComponent.owner.mExtras).readUserState(i2), i2)) == null) {
                return null;
            }
            ResolveInfo resolveInfo = new ResolveInfo();
            resolveInfo.serviceInfo = generateServiceInfo;
            if ((this.mFlags & 64) != 0) {
                resolveInfo.filter = serviceIntentInfo.filter;
            }
            resolveInfo.priority = serviceIntentInfo.filter.getPriority();
            resolveInfo.preferredOrder = serviceComponent.owner.mPreferredOrder;
            resolveInfo.match = i;
            resolveInfo.isDefault = serviceIntentInfo.hasDefault;
            resolveInfo.labelRes = serviceIntentInfo.labelRes;
            resolveInfo.nonLocalizedLabel = serviceIntentInfo.nonLocalizedLabel;
            resolveInfo.icon = serviceIntentInfo.icon;
            return resolveInfo;
        }

        @Override // com.lody.virtual.server.p063pm.IntentResolver
        protected void sortResults(List<ResolveInfo> list) {
            Collections.sort(list, VPackageManagerService.sResolvePrioritySorter);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Object filterToLabel(VPackage.ServiceIntentInfo serviceIntentInfo) {
            return serviceIntentInfo.service;
        }
    }
}
