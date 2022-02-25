package com.lody.virtual.server.secondary;

import android.os.Binder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;

/* loaded from: classes.dex */
public class FakeIdentityBinder extends Binder {
    private Binder mBase;

    public FakeIdentityBinder(Binder binder) {
        this.mBase = binder;
    }

    @Override // android.os.Binder
    public final void attachInterface(IInterface iInterface, String str) {
        this.mBase.attachInterface(iInterface, str);
    }

    @Override // android.os.Binder, android.os.IBinder
    public final String getInterfaceDescriptor() {
        return this.mBase.getInterfaceDescriptor();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Binder.restoreCallingIdentity(getFakeIdentity());
            return this.mBase.transact(i, parcel, parcel2, i2);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    protected long getFakeIdentity() {
        return (getFakeUid() << 32) | getFakePid();
    }

    protected int getFakeUid() {
        return Process.myUid();
    }

    protected int getFakePid() {
        return Process.myPid();
    }

    @Override // android.os.Binder, android.os.IBinder
    public final IInterface queryLocalInterface(String str) {
        return this.mBase.queryLocalInterface(str);
    }
}
