package com.lody.virtual.server.interfaces;

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
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.lody.virtual.remote.ReceiverInfo;
import com.lody.virtual.remote.VParceledListSlice;
import java.util.List;

/* loaded from: classes.dex */
public interface IPackageManager extends IInterface {
    boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) throws RemoteException;

    int checkPermission(boolean z, String str, String str2, int i) throws RemoteException;

    int checkSignatures(String str, String str2) throws RemoteException;

    ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    List<PermissionGroupInfo> getAllPermissionGroups(int i) throws RemoteException;

    ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException;

    int getComponentEnabledSetting(ComponentName componentName, int i) throws RemoteException;

    String[] getDangrousPermissions(String str) throws RemoteException;

    VParceledListSlice getInstalledApplications(int i, int i2) throws RemoteException;

    VParceledListSlice getInstalledPackages(int i, int i2) throws RemoteException;

    String getNameForUid(int i) throws RemoteException;

    PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException;

    IBinder getPackageInstaller() throws RemoteException;

    int getPackageUid(String str, int i) throws RemoteException;

    String[] getPackagesForUid(int i) throws RemoteException;

    PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException;

    PermissionInfo getPermissionInfo(String str, int i) throws RemoteException;

    ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    List<ReceiverInfo> getReceiverInfos(String str, String str2, int i) throws RemoteException;

    ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    List<String> getSharedLibraries(String str) throws RemoteException;

    boolean isVirtualAuthority(String str) throws RemoteException;

    VParceledListSlice queryContentProviders(String str, int i, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentActivities(Intent intent, String str, int i, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentContentProviders(Intent intent, String str, int i, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentReceivers(Intent intent, String str, int i, int i2) throws RemoteException;

    List<ResolveInfo> queryIntentServices(Intent intent, String str, int i, int i2) throws RemoteException;

    List<PermissionInfo> queryPermissionsByGroup(String str, int i) throws RemoteException;

    List<String> querySharedPackages(String str) throws RemoteException;

    ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException;

    ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException;

    ResolveInfo resolveService(Intent intent, String str, int i, int i2) throws RemoteException;

    void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPackageManager {
        private static final String DESCRIPTOR = "com.lody.virtual.server.interfaces.IPackageManager";
        static final int TRANSACTION_activitySupportsIntent = 7;
        static final int TRANSACTION_checkPermission = 4;
        static final int TRANSACTION_checkSignatures = 30;
        static final int TRANSACTION_getActivityInfo = 6;
        static final int TRANSACTION_getAllPermissionGroups = 23;
        static final int TRANSACTION_getApplicationInfo = 25;
        static final int TRANSACTION_getComponentEnabledSetting = 34;
        static final int TRANSACTION_getDangrousPermissions = 31;
        static final int TRANSACTION_getInstalledApplications = 18;
        static final int TRANSACTION_getInstalledPackages = 17;
        static final int TRANSACTION_getNameForUid = 28;
        static final int TRANSACTION_getPackageInfo = 5;
        static final int TRANSACTION_getPackageInstaller = 29;
        static final int TRANSACTION_getPackageUid = 1;
        static final int TRANSACTION_getPackagesForUid = 2;
        static final int TRANSACTION_getPermissionGroupInfo = 22;
        static final int TRANSACTION_getPermissionInfo = 20;
        static final int TRANSACTION_getProviderInfo = 10;
        static final int TRANSACTION_getReceiverInfo = 8;
        static final int TRANSACTION_getReceiverInfos = 19;
        static final int TRANSACTION_getServiceInfo = 9;
        static final int TRANSACTION_getSharedLibraries = 3;
        static final int TRANSACTION_isVirtualAuthority = 32;
        static final int TRANSACTION_queryContentProviders = 26;
        static final int TRANSACTION_queryIntentActivities = 12;
        static final int TRANSACTION_queryIntentContentProviders = 16;
        static final int TRANSACTION_queryIntentReceivers = 13;
        static final int TRANSACTION_queryIntentServices = 15;
        static final int TRANSACTION_queryPermissionsByGroup = 21;
        static final int TRANSACTION_querySharedPackages = 27;
        static final int TRANSACTION_resolveContentProvider = 24;
        static final int TRANSACTION_resolveIntent = 11;
        static final int TRANSACTION_resolveService = 14;
        static final int TRANSACTION_setComponentEnabledSetting = 33;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPackageManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPackageManager)) {
                return new Proxy(iBinder);
            }
            return (IPackageManager) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                ComponentName componentName = null;
                ComponentName componentName2 = null;
                ComponentName componentName3 = null;
                Intent intent = null;
                Intent intent2 = null;
                Intent intent3 = null;
                Intent intent4 = null;
                Intent intent5 = null;
                Intent intent6 = null;
                ComponentName componentName4 = null;
                ComponentName componentName5 = null;
                ComponentName componentName6 = null;
                Intent intent7 = null;
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int packageUid = getPackageUid(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(packageUid);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] packagesForUid = getPackagesForUid(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringArray(packagesForUid);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> sharedLibraries = getSharedLibraries(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeStringList(sharedLibraries);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int checkPermission = checkPermission(z, parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(checkPermission);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        PackageInfo packageInfo = getPackageInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (packageInfo != null) {
                            parcel2.writeInt(1);
                            packageInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        ActivityInfo activityInfo = getActivityInfo(componentName, parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (activityInfo != null) {
                            parcel2.writeInt(1);
                            activityInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        ComponentName componentName7 = parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            intent7 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        boolean activitySupportsIntent = activitySupportsIntent(componentName7, intent7, parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(activitySupportsIntent ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName6 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        ActivityInfo receiverInfo = getReceiverInfo(componentName6, parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (receiverInfo != null) {
                            parcel2.writeInt(1);
                            receiverInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName5 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        ServiceInfo serviceInfo = getServiceInfo(componentName5, parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (serviceInfo != null) {
                            parcel2.writeInt(1);
                            serviceInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName4 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        ProviderInfo providerInfo = getProviderInfo(componentName4, parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (providerInfo != null) {
                            parcel2.writeInt(1);
                            providerInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent6 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        ResolveInfo resolveIntent = resolveIntent(intent6, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (resolveIntent != null) {
                            parcel2.writeInt(1);
                            resolveIntent.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent5 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        List<ResolveInfo> queryIntentActivities = queryIntentActivities(intent5, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(queryIntentActivities);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent4 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        List<ResolveInfo> queryIntentReceivers = queryIntentReceivers(intent4, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(queryIntentReceivers);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent3 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        ResolveInfo resolveService = resolveService(intent3, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (resolveService != null) {
                            parcel2.writeInt(1);
                            resolveService.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent2 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        List<ResolveInfo> queryIntentServices = queryIntentServices(intent2, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(queryIntentServices);
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        List<ResolveInfo> queryIntentContentProviders = queryIntentContentProviders(intent, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(queryIntentContentProviders);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        VParceledListSlice installedPackages = getInstalledPackages(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (installedPackages != null) {
                            parcel2.writeInt(1);
                            installedPackages.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        VParceledListSlice installedApplications = getInstalledApplications(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (installedApplications != null) {
                            parcel2.writeInt(1);
                            installedApplications.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<ReceiverInfo> receiverInfos = getReceiverInfos(parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(receiverInfos);
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        PermissionInfo permissionInfo = getPermissionInfo(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        if (permissionInfo != null) {
                            parcel2.writeInt(1);
                            permissionInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<PermissionInfo> queryPermissionsByGroup = queryPermissionsByGroup(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(queryPermissionsByGroup);
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        PermissionGroupInfo permissionGroupInfo = getPermissionGroupInfo(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        if (permissionGroupInfo != null) {
                            parcel2.writeInt(1);
                            permissionGroupInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<PermissionGroupInfo> allPermissionGroups = getAllPermissionGroups(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allPermissionGroups);
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        ProviderInfo resolveContentProvider = resolveContentProvider(parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (resolveContentProvider != null) {
                            parcel2.writeInt(1);
                            resolveContentProvider.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        ApplicationInfo applicationInfo = getApplicationInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (applicationInfo != null) {
                            parcel2.writeInt(1);
                            applicationInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        VParceledListSlice queryContentProviders = queryContentProviders(parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (queryContentProviders != null) {
                            parcel2.writeInt(1);
                            queryContentProviders.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> querySharedPackages = querySharedPackages(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeStringList(querySharedPackages);
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        String nameForUid = getNameForUid(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(nameForUid);
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder packageInstaller = getPackageInstaller();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(packageInstaller);
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        int checkSignatures = checkSignatures(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(checkSignatures);
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        String[] dangrousPermissions = getDangrousPermissions(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeStringArray(dangrousPermissions);
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isVirtualAuthority = isVirtualAuthority(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isVirtualAuthority ? 1 : 0);
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName3 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        setComponentEnabledSetting(componentName3, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 34:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName2 = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        int componentEnabledSetting = getComponentEnabledSetting(componentName2, parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(componentEnabledSetting);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IPackageManager {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public int getPackageUid(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public String[] getPackagesForUid(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<String> getSharedLibraries(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public int checkPermission(boolean z, String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (PackageInfo) PackageInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ActivityInfo) ActivityInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ActivityInfo) ActivityInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ServiceInfo) ServiceInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ProviderInfo) ProviderInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ResolveInfo) ResolveInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<ResolveInfo> queryIntentActivities(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ResolveInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<ResolveInfo> queryIntentReceivers(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ResolveInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ResolveInfo resolveService(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ResolveInfo) ResolveInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<ResolveInfo> queryIntentServices(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ResolveInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<ResolveInfo> queryIntentContentProviders(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ResolveInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public VParceledListSlice getInstalledPackages(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? VParceledListSlice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public VParceledListSlice getInstalledApplications(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? VParceledListSlice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<ReceiverInfo> getReceiverInfos(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ReceiverInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public PermissionInfo getPermissionInfo(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (PermissionInfo) PermissionInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<PermissionInfo> queryPermissionsByGroup(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PermissionInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (PermissionGroupInfo) PermissionGroupInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<PermissionGroupInfo> getAllPermissionGroups(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PermissionGroupInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ProviderInfo) ProviderInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ApplicationInfo) ApplicationInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public VParceledListSlice queryContentProviders(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? VParceledListSlice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public List<String> querySharedPackages(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public String getNameForUid(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public IBinder getPackageInstaller() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public int checkSignatures(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public String[] getDangrousPermissions(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public boolean isVirtualAuthority(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IPackageManager
            public int getComponentEnabledSetting(ComponentName componentName, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
