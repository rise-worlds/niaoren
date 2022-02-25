package com.lody.virtual.client.hook.secondary;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import java.io.FileDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
abstract class StubBinder implements IBinder {
    private Context context;
    private IBinder mBase;
    private ClassLoader mClassLoader;
    private IInterface mInterface;

    public abstract InvocationHandler createHandler(Class<?> cls, IInterface iInterface);

    StubBinder(Context context, ClassLoader classLoader, IBinder iBinder) {
        this.context = context;
        this.mClassLoader = classLoader;
        this.mBase = iBinder;
    }

    public String getAppPkg() {
        return this.context.getPackageName();
    }

    public String getHostPkg() {
        return VirtualCore.get().getHostPkg();
    }

    @Override // android.os.IBinder
    public String getInterfaceDescriptor() throws RemoteException {
        return this.mBase.getInterfaceDescriptor();
    }

    @Override // android.os.IBinder
    public boolean pingBinder() {
        return this.mBase.pingBinder();
    }

    @Override // android.os.IBinder
    public boolean isBinderAlive() {
        return this.mBase.isBinderAlive();
    }

    @Override // android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        if (this.mInterface == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length <= 1) {
                return null;
            }
            Class<?> cls = null;
            IInterface iInterface = null;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!stackTraceElement.isNativeMethod()) {
                    try {
                        Method declaredMethod = this.mClassLoader.loadClass(stackTraceElement.getClassName()).getDeclaredMethod(stackTraceElement.getMethodName(), IBinder.class);
                        if ((declaredMethod.getModifiers() & 8) != 0) {
                            declaredMethod.setAccessible(true);
                            Class<?> returnType = declaredMethod.getReturnType();
                            if (returnType.isInterface() && IInterface.class.isAssignableFrom(returnType)) {
                                try {
                                    iInterface = (IInterface) declaredMethod.invoke(null, this.mBase);
                                    cls = returnType;
                                } catch (Exception unused) {
                                    cls = returnType;
                                }
                            }
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
            if (cls == null || iInterface == null) {
                return null;
            }
            this.mInterface = (IInterface) Proxy.newProxyInstance(this.mClassLoader, new Class[]{cls}, createHandler(cls, iInterface));
        }
        return this.mInterface;
    }

    @Override // android.os.IBinder
    public void dump(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.mBase.dump(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.mBase.dumpAsync(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    public boolean transact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return this.mBase.transact(i, parcel, parcel2, i2);
    }

    @Override // android.os.IBinder
    public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) throws RemoteException {
        this.mBase.linkToDeath(deathRecipient, i);
    }

    @Override // android.os.IBinder
    public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        return this.mBase.unlinkToDeath(deathRecipient, i);
    }
}
