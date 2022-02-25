package com.nrzs.p067ft;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nrzs.p067ft.IOnNewLoginInfoListener;

/* renamed from: com.nrzs.ft.c */
/* loaded from: classes2.dex */
public interface INRZSAidlInterface extends IInterface {
    void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException;

    void registListener(IOnNewLoginInfoListener dVar) throws RemoteException;

    void unregistListener(IOnNewLoginInfoListener dVar) throws RemoteException;

    /* compiled from: INRZSAidlInterface.java */
    /* renamed from: com.nrzs.ft.c$a */
    /* loaded from: classes2.dex */
    public static abstract class AbstractBinderC2008a extends Binder implements INRZSAidlInterface {

        /* renamed from: a */
        static final int f10714a = 1;

        /* renamed from: b */
        static final int f10715b = 2;

        /* renamed from: c */
        static final int f10716c = 3;

        /* renamed from: d */
        private static final String f10717d = "com.nrzs.ft.INRZSAidlInterface";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC2008a() {
            attachInterface(this, f10717d);
        }

        public static INRZSAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f10717d);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof INRZSAidlInterface)) {
                return new C2009a(iBinder);
            }
            return (INRZSAidlInterface) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(f10717d);
                        basicTypes(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(f10717d);
                        registListener(IOnNewLoginInfoListener.AbstractBinderC2010a.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(f10717d);
                        unregistListener(IOnNewLoginInfoListener.AbstractBinderC2010a.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(f10717d);
                return true;
            }
        }

        /* compiled from: INRZSAidlInterface.java */
        /* renamed from: com.nrzs.ft.c$a$a */
        /* loaded from: classes2.dex */
        private static class C2009a implements INRZSAidlInterface {

            /* renamed from: a */
            private IBinder f10718a;

            /* renamed from: a */
            public String m18895a() {
                return AbstractBinderC2008a.f10717d;
            }

            C2009a(IBinder iBinder) {
                this.f10718a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f10718a;
            }

            @Override // com.nrzs.p067ft.INRZSAidlInterface
            public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC2008a.f10717d);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeFloat(f);
                    obtain.writeDouble(d);
                    obtain.writeString(str);
                    this.f10718a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.nrzs.p067ft.INRZSAidlInterface
            public void registListener(IOnNewLoginInfoListener dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC2008a.f10717d);
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.f10718a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.nrzs.p067ft.INRZSAidlInterface
            public void unregistListener(IOnNewLoginInfoListener dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC2008a.f10717d);
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.f10718a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
