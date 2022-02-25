package p110z1;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import p110z1.IFileDownloadIPCCallback;

/* renamed from: z1.ahl */
/* loaded from: classes3.dex */
public interface IFileDownloadIPCService extends IInterface {
    boolean checkDownloading(String str, String str2) throws RemoteException;

    void clearAllTaskData() throws RemoteException;

    boolean clearTaskData(int i) throws RemoteException;

    long getSofar(int i) throws RemoteException;

    byte getStatus(int i) throws RemoteException;

    long getTotal(int i) throws RemoteException;

    boolean isIdle() throws RemoteException;

    boolean pause(int i) throws RemoteException;

    void pauseAllTasks() throws RemoteException;

    void registerCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException;

    boolean setMaxNetworkThreadCount(int i) throws RemoteException;

    void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException;

    void startForeground(int i, Notification notification) throws RemoteException;

    void stopForeground(boolean z) throws RemoteException;

    void unregisterCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException;

    /* compiled from: IFileDownloadIPCService.java */
    /* renamed from: z1.ahl$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractBinderC3473a extends Binder implements IFileDownloadIPCService {

        /* renamed from: a */
        static final int f15807a = 1;

        /* renamed from: b */
        static final int f15808b = 2;

        /* renamed from: c */
        static final int f15809c = 3;

        /* renamed from: d */
        static final int f15810d = 4;

        /* renamed from: e */
        static final int f15811e = 5;

        /* renamed from: f */
        static final int f15812f = 6;

        /* renamed from: g */
        static final int f15813g = 7;

        /* renamed from: h */
        static final int f15814h = 8;

        /* renamed from: i */
        static final int f15815i = 9;

        /* renamed from: j */
        static final int f15816j = 10;

        /* renamed from: k */
        static final int f15817k = 11;

        /* renamed from: l */
        static final int f15818l = 12;

        /* renamed from: m */
        static final int f15819m = 13;

        /* renamed from: n */
        static final int f15820n = 14;

        /* renamed from: o */
        static final int f15821o = 15;

        /* renamed from: p */
        private static final String f15822p = "com.liulishuo.filedownloader.i.IFileDownloadIPCService";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC3473a() {
            attachInterface(this, f15822p);
        }

        public static IFileDownloadIPCService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f15822p);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFileDownloadIPCService)) {
                return new C3474a(iBinder);
            }
            return (IFileDownloadIPCService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                Notification notification = null;
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(f15822p);
                        registerCallback(IFileDownloadIPCCallback.AbstractBinderC3471a.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 2:
                        parcel.enforceInterface(f15822p);
                        unregisterCallback(IFileDownloadIPCCallback.AbstractBinderC3471a.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel.enforceInterface(f15822p);
                        boolean checkDownloading = checkDownloading(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(checkDownloading ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface(f15822p);
                        start(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0 ? FileDownloadHeader.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(f15822p);
                        boolean pause = pause(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(pause ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface(f15822p);
                        pauseAllTasks();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(f15822p);
                        boolean maxNetworkThreadCount = setMaxNetworkThreadCount(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(maxNetworkThreadCount ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(f15822p);
                        long sofar = getSofar(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeLong(sofar);
                        return true;
                    case 9:
                        parcel.enforceInterface(f15822p);
                        long total = getTotal(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeLong(total);
                        return true;
                    case 10:
                        parcel.enforceInterface(f15822p);
                        byte status = getStatus(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeByte(status);
                        return true;
                    case 11:
                        parcel.enforceInterface(f15822p);
                        boolean isIdle = isIdle();
                        parcel2.writeNoException();
                        parcel2.writeInt(isIdle ? 1 : 0);
                        return true;
                    case 12:
                        parcel.enforceInterface(f15822p);
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            notification = (Notification) Notification.CREATOR.createFromParcel(parcel);
                        }
                        startForeground(readInt, notification);
                        return true;
                    case 13:
                        parcel.enforceInterface(f15822p);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        stopForeground(z);
                        return true;
                    case 14:
                        parcel.enforceInterface(f15822p);
                        boolean clearTaskData = clearTaskData(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(clearTaskData ? 1 : 0);
                        return true;
                    case 15:
                        parcel.enforceInterface(f15822p);
                        clearAllTaskData();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(f15822p);
                return true;
            }
        }

        /* compiled from: IFileDownloadIPCService.java */
        /* renamed from: z1.ahl$a$a */
        /* loaded from: classes3.dex */
        private static class C3474a implements IFileDownloadIPCService {

            /* renamed from: a */
            private IBinder f15823a;

            /* renamed from: a */
            public String m13298a() {
                return AbstractBinderC3473a.f15822p;
            }

            C3474a(IBinder iBinder) {
                this.f15823a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f15823a;
            }

            @Override // p110z1.IFileDownloadIPCService
            public void registerCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeStrongBinder(ahkVar != null ? ahkVar.asBinder() : null);
                    this.f15823a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public void unregisterCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeStrongBinder(ahkVar != null ? ahkVar.asBinder() : null);
                    this.f15823a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public boolean checkDownloading(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.f15823a.transact(3, obtain, obtain2, 0);
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

            @Override // p110z1.IFileDownloadIPCService
            public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    int i4 = 1;
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (fileDownloadHeader != null) {
                        obtain.writeInt(1);
                        fileDownloadHeader.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z3) {
                        i4 = 0;
                    }
                    obtain.writeInt(i4);
                    this.f15823a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public boolean pause(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.f15823a.transact(5, obtain, obtain2, 0);
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

            @Override // p110z1.IFileDownloadIPCService
            public void pauseAllTasks() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    this.f15823a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.f15823a.transact(7, obtain, obtain2, 0);
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

            @Override // p110z1.IFileDownloadIPCService
            public long getSofar(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    this.f15823a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public long getTotal(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    this.f15823a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public byte getStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    this.f15823a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readByte();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public boolean isIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    boolean z = false;
                    this.f15823a.transact(11, obtain, obtain2, 0);
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

            @Override // p110z1.IFileDownloadIPCService
            public void startForeground(int i, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f15823a.transact(12, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public void stopForeground(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(z ? 1 : 0);
                    this.f15823a.transact(13, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // p110z1.IFileDownloadIPCService
            public boolean clearTaskData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.f15823a.transact(14, obtain, obtain2, 0);
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

            @Override // p110z1.IFileDownloadIPCService
            public void clearAllTaskData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3473a.f15822p);
                    this.f15823a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
