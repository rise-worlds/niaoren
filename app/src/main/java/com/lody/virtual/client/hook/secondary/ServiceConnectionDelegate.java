package com.lody.virtual.client.hook.secondary;

import android.app.IServiceConnection;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.server.IBinderDelegateService;
import p110z1.ActivityThread;
import p110z1.ContextImpl;
import p110z1.IServiceConnectionO;
import p110z1.LoadedApk;

/* loaded from: classes.dex */
public class ServiceConnectionDelegate extends IServiceConnection.Stub {
    private static final ArrayMap<IBinder, ServiceConnectionDelegate> DELEGATE_MAP = new ArrayMap<>();
    private IServiceConnection mConn;

    private ServiceConnectionDelegate(IServiceConnection iServiceConnection) {
        this.mConn = iServiceConnection;
    }

    public static IServiceConnection getDelegate(Context context, ServiceConnection serviceConnection, int i) {
        IServiceConnection iServiceConnection;
        if (serviceConnection != null) {
            try {
                Object call = ActivityThread.currentActivityThread.call(new Object[0]);
                iServiceConnection = LoadedApk.getServiceDispatcher.call(ContextImpl.mPackageInfo.get(VirtualCore.get().getContext()), serviceConnection, context, ActivityThread.getHandler.call(call, new Object[0]), Integer.valueOf(i));
            } catch (Exception e) {
                Log.e("ConnectionDelegate", "getServiceDispatcher", e);
                iServiceConnection = null;
            }
            if (iServiceConnection != null) {
                return getDelegate(iServiceConnection);
            }
            throw new RuntimeException("Not supported in system context");
        }
        throw new IllegalArgumentException("connection is null");
    }

    public static IServiceConnection removeDelegate(Context context, ServiceConnection serviceConnection) {
        IServiceConnection iServiceConnection;
        try {
            iServiceConnection = LoadedApk.forgetServiceDispatcher.call(ContextImpl.mPackageInfo.get(VirtualCore.get().getContext()), context, serviceConnection);
        } catch (Exception e) {
            Log.e("ConnectionDelegate", "forgetServiceDispatcher", e);
            iServiceConnection = null;
        }
        if (iServiceConnection == null) {
            return null;
        }
        return removeDelegate(iServiceConnection);
    }

    public static ServiceConnectionDelegate getDelegate(IServiceConnection iServiceConnection) {
        if (iServiceConnection instanceof ServiceConnectionDelegate) {
            return (ServiceConnectionDelegate) iServiceConnection;
        }
        IBinder asBinder = iServiceConnection.asBinder();
        ServiceConnectionDelegate serviceConnectionDelegate = DELEGATE_MAP.get(asBinder);
        if (serviceConnectionDelegate != null) {
            return serviceConnectionDelegate;
        }
        ServiceConnectionDelegate serviceConnectionDelegate2 = new ServiceConnectionDelegate(iServiceConnection);
        DELEGATE_MAP.put(asBinder, serviceConnectionDelegate2);
        return serviceConnectionDelegate2;
    }

    public static ServiceConnectionDelegate removeDelegate(IServiceConnection iServiceConnection) {
        return DELEGATE_MAP.remove(iServiceConnection.asBinder());
    }

    @Override // android.app.IServiceConnection
    public void connected(ComponentName componentName, IBinder iBinder) throws RemoteException {
        connected(componentName, iBinder, false);
    }

    public void connected(ComponentName componentName, IBinder iBinder, boolean z) throws RemoteException {
        IBinder proxyService;
        IBinderDelegateService asInterface = IBinderDelegateService.Stub.asInterface(iBinder);
        if (!(asInterface == null || (proxyService = ProxyServiceFactory.getProxyService(VClient.get().getCurrentApplication(), (componentName = asInterface.getComponent()), (iBinder = asInterface.getService()))) == null)) {
            iBinder = proxyService;
        }
        if (BuildCompat.isOreo()) {
            IServiceConnectionO.connected.call(this.mConn, componentName, iBinder, Boolean.valueOf(z));
        } else {
            this.mConn.connected(componentName, iBinder);
        }
    }
}
