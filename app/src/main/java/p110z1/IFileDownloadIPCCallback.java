package p110z1;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.liulishuo.filedownloader.message.MessageSnapshot;

/* renamed from: z1.ahk */
/* loaded from: classes3.dex */
public interface IFileDownloadIPCCallback extends IInterface {
    void callback(MessageSnapshot messageSnapshot) throws RemoteException;

    /* compiled from: IFileDownloadIPCCallback.java */
    /* renamed from: z1.ahk$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractBinderC3471a extends Binder implements IFileDownloadIPCCallback {

        /* renamed from: a */
        static final int f15804a = 1;

        /* renamed from: b */
        private static final String f15805b = "com.liulishuo.filedownloader.i.IFileDownloadIPCCallback";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC3471a() {
            attachInterface(this, f15805b);
        }

        public static IFileDownloadIPCCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f15805b);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFileDownloadIPCCallback)) {
                return new C3472a(iBinder);
            }
            return (IFileDownloadIPCCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(f15805b);
                callback(parcel.readInt() != 0 ? MessageSnapshot.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(f15805b);
                return true;
            }
        }

        /* compiled from: IFileDownloadIPCCallback.java */
        /* renamed from: z1.ahk$a$a */
        /* loaded from: classes3.dex */
        private static class C3472a implements IFileDownloadIPCCallback {

            /* renamed from: a */
            private IBinder f15806a;

            /* renamed from: a */
            public String m13299a() {
                return AbstractBinderC3471a.f15805b;
            }

            C3472a(IBinder iBinder) {
                this.f15806a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f15806a;
            }

            @Override // p110z1.IFileDownloadIPCCallback
            public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC3471a.f15805b);
                    if (messageSnapshot != null) {
                        obtain.writeInt(1);
                        messageSnapshot.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f15806a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
