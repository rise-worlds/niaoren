package p110z1;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: z1.du */
/* loaded from: classes3.dex */
public interface ILockSettings extends IInterface {
    int[] getRecoverySecretTypes() throws RemoteException;

    void setRecoverySecretTypes(int[] iArr) throws RemoteException;

    /* compiled from: ILockSettings.java */
    /* renamed from: z1.du$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractBinderC5275a extends Binder implements ILockSettings {
        private static final String DESCRIPTOR = "com.android.internal.widget.ILockSettings";
        static final int TRANSACTION_getRecoverySecretTypes = 2;
        static final int TRANSACTION_setRecoverySecretTypes = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC5275a() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILockSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILockSettings)) {
                return new C5276a(iBinder);
            }
            return (ILockSettings) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        setRecoverySecretTypes(parcel.createIntArray());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        int[] recoverySecretTypes = getRecoverySecretTypes();
                        parcel2.writeNoException();
                        parcel2.writeIntArray(recoverySecretTypes);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* compiled from: ILockSettings.java */
        /* renamed from: z1.du$a$a */
        /* loaded from: classes3.dex */
        private static class C5276a implements ILockSettings {

            /* renamed from: a */
            private IBinder f21344a;

            /* renamed from: a */
            public String m3200a() {
                return AbstractBinderC5275a.DESCRIPTOR;
            }

            C5276a(IBinder iBinder) {
                this.f21344a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f21344a;
            }

            @Override // p110z1.ILockSettings
            public void setRecoverySecretTypes(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC5275a.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.f21344a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // p110z1.ILockSettings
            public int[] getRecoverySecretTypes() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC5275a.DESCRIPTOR);
                    this.f21344a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
