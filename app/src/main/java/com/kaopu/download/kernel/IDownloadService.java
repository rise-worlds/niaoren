package com.kaopu.download.kernel;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes.dex */
public interface IDownloadService extends IInterface {
    boolean addNewDownloadTask(Map map) throws RemoteException;

    boolean cancelDownloadTask(Map map) throws RemoteException;

    void clearAllDownloadTask() throws RemoteException;

    boolean continueDownload(Map map) throws RemoteException;

    Map getDownloadInfo(String str) throws RemoteException;

    Map getDownloadInfos() throws RemoteException;

    int getTaskCount() throws RemoteException;

    boolean isServiceAlive() throws RemoteException;

    boolean pauseDownloadTask(Map map) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDownloadService {
        private static final String DESCRIPTOR = "com.kaopu.download.kernel.IDownloadService";
        static final int TRANSACTION_addNewDownloadTask = 1;
        static final int TRANSACTION_cancelDownloadTask = 3;
        static final int TRANSACTION_clearAllDownloadTask = 5;
        static final int TRANSACTION_continueDownload = 9;
        static final int TRANSACTION_getDownloadInfo = 8;
        static final int TRANSACTION_getDownloadInfos = 6;
        static final int TRANSACTION_getTaskCount = 7;
        static final int TRANSACTION_isServiceAlive = 4;
        static final int TRANSACTION_pauseDownloadTask = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDownloadService)) {
                return new Proxy(iBinder);
            }
            return (IDownloadService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean addNewDownloadTask = addNewDownloadTask(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeInt(addNewDownloadTask ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean pauseDownloadTask = pauseDownloadTask(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeInt(pauseDownloadTask ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean cancelDownloadTask = cancelDownloadTask(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeInt(cancelDownloadTask ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isServiceAlive = isServiceAlive();
                        parcel2.writeNoException();
                        parcel2.writeInt(isServiceAlive ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        clearAllDownloadTask();
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        Map downloadInfos = getDownloadInfos();
                        parcel2.writeNoException();
                        parcel2.writeMap(downloadInfos);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        int taskCount = getTaskCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(taskCount);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        Map downloadInfo = getDownloadInfo(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeMap(downloadInfo);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean continueDownload = continueDownload(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeInt(continueDownload ? 1 : 0);
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
        private static class Proxy implements IDownloadService {
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

            @Override // com.kaopu.download.kernel.IDownloadService
            public boolean addNewDownloadTask(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    boolean z = false;
                    this.mRemote.transact(1, obtain, obtain2, 0);
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

            @Override // com.kaopu.download.kernel.IDownloadService
            public boolean pauseDownloadTask(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
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

            @Override // com.kaopu.download.kernel.IDownloadService
            public boolean cancelDownloadTask(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
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

            @Override // com.kaopu.download.kernel.IDownloadService
            public boolean isServiceAlive() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
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

            @Override // com.kaopu.download.kernel.IDownloadService
            public void clearAllDownloadTask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kaopu.download.kernel.IDownloadService
            public Map getDownloadInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kaopu.download.kernel.IDownloadService
            public int getTaskCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kaopu.download.kernel.IDownloadService
            public Map getDownloadInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kaopu.download.kernel.IDownloadService
            public boolean continueDownload(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
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
        }
    }
}
