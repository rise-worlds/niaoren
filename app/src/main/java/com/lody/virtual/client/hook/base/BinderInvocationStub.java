package com.lody.virtual.client.hook.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.lody.virtual.client.core.ServiceLocalManager;
import com.lody.virtual.client.core.VirtualCore;
import java.io.FileDescriptor;
import java.lang.reflect.Method;
import mirror.RefStaticMethod;
import p110z1.ServiceManager;

/* loaded from: classes.dex */
public class BinderInvocationStub extends MethodInvocationStub<IInterface> implements IBinder {
    private static final String TAG = "BinderInvocationStub";
    private IBinder mBaseBinder;

    public BinderInvocationStub(RefStaticMethod<IInterface> kVar, IBinder iBinder) {
        this(asInterface(kVar, iBinder));
    }

    public BinderInvocationStub(Class<?> cls, IBinder iBinder) {
        this(asInterface(cls, iBinder));
    }

    public BinderInvocationStub(IInterface iInterface) {
        super(iInterface);
        this.mBaseBinder = getBaseInterface() != null ? getBaseInterface().asBinder() : null;
        addMethodProxy(new AsBinder());
    }

    private static IInterface asInterface(RefStaticMethod<IInterface> kVar, IBinder iBinder) {
        if (kVar == null || iBinder == null) {
            return null;
        }
        return kVar.call(iBinder);
    }

    private static IInterface asInterface(Class<?> cls, IBinder iBinder) {
        if (cls == null) {
            return null;
        }
        try {
            if (iBinder != null) {
                return (IInterface) cls.getMethod("asInterface", IBinder.class).invoke(null, iBinder);
            }
            String str = TAG;
            Log.w(str, "Could not create stub because binder = null, stubClass=" + cls);
            return null;
        } catch (Exception e) {
            String str2 = TAG;
            Log.d(str2, "Could not create stub " + cls.getName() + ". Cause: " + e);
            return null;
        }
    }

    public void replaceService(String str) {
        if (this.mBaseBinder != null) {
            ServiceManager.sCache.get().put(str, this);
            ServiceLocalManager.addService(str, this);
        }
    }

    /* loaded from: classes.dex */
    private final class AsBinder extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "asBinder";
        }

        private AsBinder() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return BinderInvocationStub.this;
        }
    }

    @Override // android.os.IBinder
    public String getInterfaceDescriptor() throws RemoteException {
        return this.mBaseBinder.getInterfaceDescriptor();
    }

    public Context getContext() {
        return VirtualCore.get().getContext();
    }

    @Override // android.os.IBinder
    public boolean pingBinder() {
        return this.mBaseBinder.pingBinder();
    }

    @Override // android.os.IBinder
    public boolean isBinderAlive() {
        return this.mBaseBinder.isBinderAlive();
    }

    @Override // android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        return getProxyInterface();
    }

    @Override // android.os.IBinder
    public void dump(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.mBaseBinder.dump(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    @TargetApi(13)
    public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.mBaseBinder.dumpAsync(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    public boolean transact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return this.mBaseBinder.transact(i, parcel, parcel2, i2);
    }

    @Override // android.os.IBinder
    public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) throws RemoteException {
        this.mBaseBinder.linkToDeath(deathRecipient, i);
    }

    @Override // android.os.IBinder
    public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        return this.mBaseBinder.unlinkToDeath(deathRecipient, i);
    }

    public IBinder getBaseBinder() {
        return this.mBaseBinder;
    }
}
