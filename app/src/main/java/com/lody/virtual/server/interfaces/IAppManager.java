package com.lody.virtual.server.interfaces;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.interfaces.IPackageObserver;
import java.util.List;

/* loaded from: classes.dex */
public interface IAppManager extends IInterface {
    void addVisibleOutsidePackage(String str) throws RemoteException;

    boolean cleanPackageData(String str, int i) throws RemoteException;

    int getInstalledAppCount() throws RemoteException;

    InstalledAppInfo getInstalledAppInfo(String str, int i) throws RemoteException;

    List<InstalledAppInfo> getInstalledApps(int i) throws RemoteException;

    List<InstalledAppInfo> getInstalledAppsAsUser(int i, int i2) throws RemoteException;

    int[] getPackageInstalledUsers(String str) throws RemoteException;

    int getUidForSharedUser(String str) throws RemoteException;

    void installPackage(String str, InstallOptions installOptions, ResultReceiver resultReceiver) throws RemoteException;

    boolean installPackageAsUser(int i, String str) throws RemoteException;

    boolean isAppInstalled(String str) throws RemoteException;

    boolean isAppInstalledAsUser(int i, String str) throws RemoteException;

    boolean isIORelocateWork() throws RemoteException;

    boolean isOutsidePackageVisible(String str) throws RemoteException;

    boolean isPackageLaunched(int i, String str) throws RemoteException;

    boolean isRun64BitProcess(String str) throws RemoteException;

    void registerObserver(IPackageObserver iPackageObserver) throws RemoteException;

    void removeVisibleOutsidePackage(String str) throws RemoteException;

    void requestCopyPackage64(String str) throws RemoteException;

    void scanApps() throws RemoteException;

    void setPackageHidden(int i, String str, boolean z) throws RemoteException;

    boolean uninstallPackage(String str) throws RemoteException;

    boolean uninstallPackageAsUser(String str, int i) throws RemoteException;

    void unregisterObserver(IPackageObserver iPackageObserver) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppManager {
        private static final String DESCRIPTOR = "com.lody.virtual.server.interfaces.IAppManager";
        static final int TRANSACTION_addVisibleOutsidePackage = 3;
        static final int TRANSACTION_cleanPackageData = 24;
        static final int TRANSACTION_getInstalledAppCount = 17;
        static final int TRANSACTION_getInstalledAppInfo = 7;
        static final int TRANSACTION_getInstalledApps = 15;
        static final int TRANSACTION_getInstalledAppsAsUser = 16;
        static final int TRANSACTION_getPackageInstalledUsers = 1;
        static final int TRANSACTION_getUidForSharedUser = 6;
        static final int TRANSACTION_installPackage = 8;
        static final int TRANSACTION_installPackageAsUser = 12;
        static final int TRANSACTION_isAppInstalled = 18;
        static final int TRANSACTION_isAppInstalledAsUser = 19;
        static final int TRANSACTION_isIORelocateWork = 23;
        static final int TRANSACTION_isOutsidePackageVisible = 5;
        static final int TRANSACTION_isPackageLaunched = 10;
        static final int TRANSACTION_isRun64BitProcess = 22;
        static final int TRANSACTION_registerObserver = 20;
        static final int TRANSACTION_removeVisibleOutsidePackage = 4;
        static final int TRANSACTION_requestCopyPackage64 = 9;
        static final int TRANSACTION_scanApps = 2;
        static final int TRANSACTION_setPackageHidden = 11;
        static final int TRANSACTION_uninstallPackage = 14;
        static final int TRANSACTION_uninstallPackageAsUser = 13;
        static final int TRANSACTION_unregisterObserver = 21;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAppManager)) {
                return new Proxy(iBinder);
            }
            return (IAppManager) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] packageInstalledUsers = getPackageInstalledUsers(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeIntArray(packageInstalledUsers);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        scanApps();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        addVisibleOutsidePackage(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeVisibleOutsidePackage(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isOutsidePackageVisible = isOutsidePackageVisible(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isOutsidePackageVisible ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int uidForSharedUser = getUidForSharedUser(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(uidForSharedUser);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        InstalledAppInfo installedAppInfo = getInstalledAppInfo(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        if (installedAppInfo != null) {
                            parcel2.writeInt(1);
                            installedAppInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        ResultReceiver resultReceiver = null;
                        InstallOptions createFromParcel = parcel.readInt() != 0 ? InstallOptions.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
                        }
                        installPackage(readString, createFromParcel, resultReceiver);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        requestCopyPackage64(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isPackageLaunched = isPackageLaunched(parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isPackageLaunched ? 1 : 0);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        int readInt = parcel.readInt();
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setPackageHidden(readInt, readString2, z);
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean installPackageAsUser = installPackageAsUser(parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(installPackageAsUser ? 1 : 0);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean uninstallPackageAsUser = uninstallPackageAsUser(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(uninstallPackageAsUser ? 1 : 0);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean uninstallPackage = uninstallPackage(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(uninstallPackage ? 1 : 0);
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<InstalledAppInfo> installedApps = getInstalledApps(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(installedApps);
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<InstalledAppInfo> installedAppsAsUser = getInstalledAppsAsUser(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(installedAppsAsUser);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        int installedAppCount = getInstalledAppCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(installedAppCount);
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppInstalled = isAppInstalled(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppInstalled ? 1 : 0);
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppInstalledAsUser = isAppInstalledAsUser(parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppInstalledAsUser ? 1 : 0);
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerObserver(IPackageObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterObserver(IPackageObserver.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isRun64BitProcess = isRun64BitProcess(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isRun64BitProcess ? 1 : 0);
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isIORelocateWork = isIORelocateWork();
                        parcel2.writeNoException();
                        parcel2.writeInt(isIORelocateWork ? 1 : 0);
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean cleanPackageData = cleanPackageData(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(cleanPackageData ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAppManager {
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public int[] getPackageInstalledUsers(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void scanApps() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void addVisibleOutsidePackage(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void removeVisibleOutsidePackage(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isOutsidePackageVisible(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public int getUidForSharedUser(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public InstalledAppInfo getInstalledAppInfo(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? InstalledAppInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void installPackage(String str, InstallOptions installOptions, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (installOptions != null) {
                        obtain.writeInt(1);
                        installOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void requestCopyPackage64(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isPackageLaunched(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(10, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void setPackageHidden(int i, String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean installPackageAsUser(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(12, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean uninstallPackageAsUser(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(13, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean uninstallPackage(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(14, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public List<InstalledAppInfo> getInstalledApps(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(InstalledAppInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public List<InstalledAppInfo> getInstalledAppsAsUser(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(InstalledAppInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public int getInstalledAppCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isAppInstalled(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(18, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isAppInstalledAsUser(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(19, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void registerObserver(IPackageObserver iPackageObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPackageObserver != null ? iPackageObserver.asBinder() : null);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public void unregisterObserver(IPackageObserver iPackageObserver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPackageObserver != null ? iPackageObserver.asBinder() : null);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isRun64BitProcess(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(22, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean isIORelocateWork() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(23, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.server.interfaces.IAppManager
            public boolean cleanPackageData(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(24, obtain, obtain2, 0);
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
        }
    }
}
