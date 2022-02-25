package com.lody.virtual.client.ipc;

import android.os.RemoteException;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.server.interfaces.IVirtualStorageService;

/* loaded from: classes.dex */
public class VirtualStorageManager {
    private static final VirtualStorageManager sInstance = new VirtualStorageManager();
    private IVirtualStorageService mService;

    public static VirtualStorageManager get() {
        return sInstance;
    }

    public IVirtualStorageService getService() {
        IVirtualStorageService iVirtualStorageService = this.mService;
        if (iVirtualStorageService == null || !IInterfaceUtils.isAlive(iVirtualStorageService)) {
            synchronized (this) {
                this.mService = (IVirtualStorageService) LocalProxyUtils.genProxy(IVirtualStorageService.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IVirtualStorageService.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.f10497VS));
    }

    public String getVirtualStorage(String str, int i) {
        try {
            return getService().getVirtualStorage(str, i);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public void setVirtualStorageState(String str, int i, boolean z) {
        try {
            getService().setVirtualStorageState(str, i, z);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isVirtualStorageEnable(String str, int i) {
        try {
            return getService().isVirtualStorageEnable(str, i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setVirtualStorage(String str, int i, String str2) {
        try {
            getService().setVirtualStorage(str, i, str2);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }
}
