package com.nrzs.p067ft;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.nrzs.ft.d */
/* loaded from: classes2.dex */
public interface IOnNewLoginInfoListener extends IInterface {
    void onCdKeyUpdateInfo(String str) throws RemoteException;

    void onNewLoginInfo(String str) throws RemoteException;

    /* compiled from: IOnNewLoginInfoListener.java */
    /* renamed from: com.nrzs.ft.d$a */
    /* loaded from: classes2.dex */
    public static abstract class AbstractBinderC2010a extends Binder implements IOnNewLoginInfoListener {

        /* renamed from: a */
        private static final String f10719a = "com.nrzs.ft.IOnNewLoginInfoListener";

        /* renamed from: b */
        static final int f10720b = 1;

        /* renamed from: c */
        static final int f10721c = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC2010a() {
            attachInterface(this, f10719a);
        }

        public static IOnNewLoginInfoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f10719a);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnNewLoginInfoListener)) {
                return new C2011a(iBinder);
            }
            return (IOnNewLoginInfoListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(f10719a);
                        onNewLoginInfo(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(f10719a);
                        onCdKeyUpdateInfo(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(f10719a);
                return true;
            }
        }

        /* compiled from: IOnNewLoginInfoListener.java */
        /* renamed from: com.nrzs.ft.d$a$a */
        /* loaded from: classes2.dex */
        private static class C2011a implements IOnNewLoginInfoListener {

            /* renamed from: a */
            private IBinder f10722a;

            /* renamed from: a */
            public String m18894a() {
                return AbstractBinderC2010a.f10719a;
            }

            C2011a(IBinder iBinder) {
                this.f10722a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f10722a;
            }

            @Override // com.nrzs.p067ft.IOnNewLoginInfoListener
            public void onNewLoginInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC2010a.f10719a);
                    obtain.writeString(str);
                    this.f10722a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.nrzs.p067ft.IOnNewLoginInfoListener
            public void onCdKeyUpdateInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC2010a.f10719a);
                    obtain.writeString(str);
                    this.f10722a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
