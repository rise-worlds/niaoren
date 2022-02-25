package com.lody.virtual.client.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.remote.ReceiverInfo;
import com.lody.virtual.server.IPackageInstaller;
import com.lody.virtual.server.interfaces.IPackageManager;
import java.util.List;

/* loaded from: classes.dex */
public class VPackageManager {
    private static final VPackageManager sMgr = new VPackageManager();
    private IPackageManager mService;

    public IPackageManager getService() {
        if (!IInterfaceUtils.isAlive(this.mService)) {
            synchronized (VPackageManager.class) {
                this.mService = (IPackageManager) LocalProxyUtils.genProxy(IPackageManager.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IPackageManager.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.PACKAGE));
    }

    public static VPackageManager get() {
        return sMgr;
    }

    public int checkPermission(String str, String str2, int i) {
        try {
            return getService().checkPermission(VirtualCore.get().is64BitEngine(), str, str2, i);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public ResolveInfo resolveService(Intent intent, String str, int i, int i2) {
        try {
            return getService().resolveService(intent, str, i, i2);
        } catch (RemoteException e) {
            return (ResolveInfo) VirtualRuntime.crash(e);
        }
    }

    public PermissionGroupInfo getPermissionGroupInfo(String str, int i) {
        try {
            return getService().getPermissionGroupInfo(str, i);
        } catch (RemoteException e) {
            return (PermissionGroupInfo) VirtualRuntime.crash(e);
        }
    }

    public List<ApplicationInfo> getInstalledApplications(int i, int i2) {
        try {
            return getService().getInstalledApplications(i, i2).getList();
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public PackageInfo getPackageInfo(String str, int i, int i2) {
        try {
            return getService().getPackageInfo(str, i, i2);
        } catch (RemoteException e) {
            return (PackageInfo) VirtualRuntime.crash(e);
        }
    }

    public ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) {
        try {
            return getService().resolveIntent(intent, str, i, i2);
        } catch (RemoteException e) {
            return (ResolveInfo) VirtualRuntime.crash(e);
        }
    }

    public List<ResolveInfo> queryIntentContentProviders(Intent intent, String str, int i, int i2) {
        try {
            return getService().queryIntentContentProviders(intent, str, i, i2);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) {
        try {
            return getService().getReceiverInfo(componentName, i, i2);
        } catch (RemoteException e) {
            return (ActivityInfo) VirtualRuntime.crash(e);
        }
    }

    public List<PackageInfo> getInstalledPackages(int i, int i2) {
        try {
            return getService().getInstalledPackages(i, i2).getList();
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<PermissionInfo> queryPermissionsByGroup(String str, int i) {
        try {
            return getService().queryPermissionsByGroup(str, i);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public PermissionInfo getPermissionInfo(String str, int i) {
        try {
            return getService().getPermissionInfo(str, i);
        } catch (RemoteException e) {
            return (PermissionInfo) VirtualRuntime.crash(e);
        }
    }

    public ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) {
        try {
            return getService().getActivityInfo(componentName, i, i2);
        } catch (RemoteException e) {
            return (ActivityInfo) VirtualRuntime.crash(e);
        }
    }

    public List<ResolveInfo> queryIntentReceivers(Intent intent, String str, int i, int i2) {
        try {
            return getService().queryIntentReceivers(intent, str, i, i2);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        try {
            return getService().getAllPermissionGroups(i);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<ResolveInfo> queryIntentActivities(Intent intent, String str, int i, int i2) {
        try {
            return getService().queryIntentActivities(intent, str, i, i2);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<ResolveInfo> queryIntentServices(Intent intent, String str, int i, int i2) {
        try {
            return getService().queryIntentServices(intent, str, i, i2);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public ApplicationInfo getApplicationInfo(String str, int i, int i2) {
        try {
            return getService().getApplicationInfo(str, i, i2);
        } catch (RemoteException e) {
            return (ApplicationInfo) VirtualRuntime.crash(e);
        }
    }

    public ProviderInfo resolveContentProvider(String str, int i, int i2) {
        try {
            return getService().resolveContentProvider(str, i, i2);
        } catch (RemoteException e) {
            return (ProviderInfo) VirtualRuntime.crash(e);
        }
    }

    public ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) {
        try {
            return getService().getServiceInfo(componentName, i, i2);
        } catch (RemoteException e) {
            return (ServiceInfo) VirtualRuntime.crash(e);
        }
    }

    public ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) {
        try {
            return getService().getProviderInfo(componentName, i, i2);
        } catch (RemoteException e) {
            return (ProviderInfo) VirtualRuntime.crash(e);
        }
    }

    public boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) {
        try {
            return getService().activitySupportsIntent(componentName, intent, str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public List<ProviderInfo> queryContentProviders(String str, int i, int i2) {
        try {
            return getService().queryContentProviders(str, i, i2).getList();
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<String> querySharedPackages(String str) {
        try {
            return getService().querySharedPackages(str);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public String[] getPackagesForUid(int i) {
        try {
            return getService().getPackagesForUid(i);
        } catch (RemoteException e) {
            return (String[]) VirtualRuntime.crash(e);
        }
    }

    public int getPackageUid(String str, int i) {
        try {
            return getService().getPackageUid(str, i);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public String getNameForUid(int i) {
        try {
            return getService().getNameForUid(i);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public IPackageInstaller getPackageInstaller() {
        try {
            return IPackageInstaller.Stub.asInterface(getService().getPackageInstaller());
        } catch (RemoteException e) {
            return (IPackageInstaller) VirtualRuntime.crash(e);
        }
    }

    public int checkSignatures(String str, String str2) {
        try {
            return getService().checkSignatures(str, str2);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public String[] getDangrousPermissions(String str) {
        try {
            return getService().getDangrousPermissions(str);
        } catch (RemoteException e) {
            return (String[]) VirtualRuntime.crash(e);
        }
    }

    public boolean isVirtualAuthority(String str) {
        try {
            return getService().isVirtualAuthority(str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) {
        try {
            getService().setComponentEnabledSetting(componentName, i, i2, i3);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public int getComponentEnabledSetting(ComponentName componentName, int i) {
        try {
            return getService().getComponentEnabledSetting(componentName, i);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public List<ReceiverInfo> getReceiverInfos(String str, String str2, int i) {
        try {
            return getService().getReceiverInfos(str, str2, i);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }
}
