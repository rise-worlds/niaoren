package com.lody.virtual.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IVClient extends IInterface {
    IBinder acquireProviderClient(ProviderInfo providerInfo) throws RemoteException;

    IBinder createProxyService(ComponentName componentName, IBinder iBinder) throws RemoteException;

    void finishActivity(IBinder iBinder) throws RemoteException;

    boolean finishReceiver(IBinder iBinder) throws RemoteException;

    IBinder getAppThread() throws RemoteException;

    String getDebugInfo() throws RemoteException;

    IBinder getToken() throws RemoteException;

    boolean isAppRunning() throws RemoteException;

    void scheduleBindService(IBinder iBinder, Intent intent, boolean z) throws RemoteException;

    void scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo) throws RemoteException;

    void scheduleNewIntent(String str, IBinder iBinder, Intent intent) throws RemoteException;

    void scheduleServiceArgs(IBinder iBinder, int i, Intent intent) throws RemoteException;

    void scheduleStopService(IBinder iBinder) throws RemoteException;

    void scheduleUnbindService(IBinder iBinder, Intent intent) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IVClient {
        private static final String DESCRIPTOR = "com.lody.virtual.client.IVClient";
        static final int TRANSACTION_acquireProviderClient = 4;
        static final int TRANSACTION_createProxyService = 3;
        static final int TRANSACTION_finishActivity = 2;
        static final int TRANSACTION_finishReceiver = 9;
        static final int TRANSACTION_getAppThread = 5;
        static final int TRANSACTION_getDebugInfo = 8;
        static final int TRANSACTION_getToken = 6;
        static final int TRANSACTION_isAppRunning = 7;
        static final int TRANSACTION_scheduleBindService = 11;
        static final int TRANSACTION_scheduleCreateService = 10;
        static final int TRANSACTION_scheduleNewIntent = 1;
        static final int TRANSACTION_scheduleServiceArgs = 13;
        static final int TRANSACTION_scheduleStopService = 14;
        static final int TRANSACTION_scheduleUnbindService = 12;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IVClient)) {
                return new Proxy(iBinder);
            }
            return (IVClient) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                Intent intent = null;
                Intent intent2 = null;
                Intent intent3 = null;
                Intent intent4 = null;
                ServiceInfo serviceInfo = null;
                ProviderInfo providerInfo = null;
                ComponentName componentName = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        IBinder readStrongBinder = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        scheduleNewIntent(readString, readStrongBinder, intent);
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        finishActivity(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        IBinder createProxyService = createProxyService(componentName, parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(createProxyService);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            providerInfo = (ProviderInfo) ProviderInfo.CREATOR.createFromParcel(parcel);
                        }
                        IBinder acquireProviderClient = acquireProviderClient(providerInfo);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(acquireProviderClient);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder appThread = getAppThread();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(appThread);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder token = getToken();
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(token);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppRunning = isAppRunning();
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppRunning ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        String debugInfo = getDebugInfo();
                        parcel2.writeNoException();
                        parcel2.writeString(debugInfo);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean finishReceiver = finishReceiver(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeInt(finishReceiver ? 1 : 0);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder2 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            serviceInfo = (ServiceInfo) ServiceInfo.CREATOR.createFromParcel(parcel);
                        }
                        scheduleCreateService(readStrongBinder2, serviceInfo);
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder3 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent4 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        scheduleBindService(readStrongBinder3, intent4, parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder4 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent3 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        scheduleUnbindService(readStrongBinder4, intent3);
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder5 = parcel.readStrongBinder();
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            intent2 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        scheduleServiceArgs(readStrongBinder5, readInt, intent2);
                        parcel2.writeNoException();
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        scheduleStopService(parcel.readStrongBinder());
                        parcel2.writeNoException();
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
        private static class Proxy implements IVClient {
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

            @Override // com.lody.virtual.client.IVClient
            public void scheduleNewIntent(String str, IBinder iBinder, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public void finishActivity(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public IBinder createProxyService(ComponentName componentName, IBinder iBinder) throws RemoteException {
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
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public IBinder acquireProviderClient(ProviderInfo providerInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (providerInfo != null) {
                        obtain.writeInt(1);
                        providerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public IBinder getAppThread() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public IBinder getToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public boolean isAppRunning() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.client.IVClient
            public String getDebugInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public boolean finishReceiver(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    this.mRemote.transact(9, obtain, obtain2, 0);
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

            @Override // com.lody.virtual.client.IVClient
            public void scheduleCreateService(IBinder iBinder, ServiceInfo serviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (serviceInfo != null) {
                        obtain.writeInt(1);
                        serviceInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public void scheduleBindService(IBinder iBinder, Intent intent, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    int i = 1;
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public void scheduleUnbindService(IBinder iBinder, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public void scheduleServiceArgs(IBinder iBinder, int i, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.client.IVClient
            public void scheduleStopService(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
