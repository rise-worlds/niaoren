package com.lody.virtual.client.ipc;

import android.os.RemoteException;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.remote.vloc.VCell;
import com.lody.virtual.remote.vloc.VLocation;
import com.lody.virtual.server.interfaces.IVirtualLocationManager;
import java.util.List;

/* loaded from: classes.dex */
public class VirtualLocationManager {
    public static final int MODE_CLOSE = 0;
    public static final int MODE_USE_GLOBAL = 1;
    public static final int MODE_USE_SELF = 2;
    private static final VirtualLocationManager sInstance = new VirtualLocationManager();
    private IVirtualLocationManager mService;

    public static VirtualLocationManager get() {
        return sInstance;
    }

    public IVirtualLocationManager getService() {
        IVirtualLocationManager iVirtualLocationManager = this.mService;
        if (iVirtualLocationManager == null || !IInterfaceUtils.isAlive(iVirtualLocationManager)) {
            synchronized (this) {
                this.mService = (IVirtualLocationManager) LocalProxyUtils.genProxy(IVirtualLocationManager.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IVirtualLocationManager.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.VIRTUAL_LOC));
    }

    public int getMode(int i, String str) {
        try {
            return getService().getMode(i, str);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public int getMode() {
        return getMode(MethodProxy.getAppUserId(), MethodProxy.getAppPkg());
    }

    public boolean isUseVirtualLocation(int i, String str) {
        return getMode(i, str) != 0;
    }

    public void setMode(int i, String str, int i2) {
        try {
            getService().setMode(i, str, i2);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setCell(int i, String str, VCell vCell) {
        try {
            getService().setCell(i, str, vCell);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setAllCell(int i, String str, List<VCell> list) {
        try {
            getService().setAllCell(i, str, list);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setNeighboringCell(int i, String str, List<VCell> list) {
        try {
            getService().setNeighboringCell(i, str, list);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public VCell getCell(int i, String str) {
        try {
            return getService().getCell(i, str);
        } catch (RemoteException e) {
            return (VCell) VirtualRuntime.crash(e);
        }
    }

    public List<VCell> getAllCell(int i, String str) {
        try {
            return getService().getAllCell(i, str);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public List<VCell> getNeighboringCell(int i, String str) {
        try {
            return getService().getNeighboringCell(i, str);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public void setGlobalCell(VCell vCell) {
        try {
            getService().setGlobalCell(vCell);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setGlobalAllCell(List<VCell> list) {
        try {
            getService().setGlobalAllCell(list);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setGlobalNeighboringCell(List<VCell> list) {
        try {
            getService().setGlobalNeighboringCell(list);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void setLocation(int i, String str, VLocation vLocation) {
        try {
            getService().setLocation(i, str, vLocation);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public VLocation getLocation(int i, String str) {
        try {
            return getService().getLocation(i, str);
        } catch (RemoteException e) {
            return (VLocation) VirtualRuntime.crash(e);
        }
    }

    public VLocation getLocation() {
        return getLocation(MethodProxy.getAppUserId(), MethodProxy.getAppPkg());
    }

    public void setGlobalLocation(VLocation vLocation) {
        try {
            getService().setGlobalLocation(vLocation);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public VLocation getGlobalLocation() {
        try {
            return getService().getGlobalLocation();
        } catch (RemoteException e) {
            return (VLocation) VirtualRuntime.crash(e);
        }
    }
}
