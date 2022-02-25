package com.lody.virtual.client.ipc;

import android.os.RemoteException;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.ReflectException;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.server.interfaces.IDeviceManager;
import java.util.Map;
import p110z1.Build;

/* loaded from: classes.dex */
public class VDeviceManager {
    private static final VDeviceManager sInstance = new VDeviceManager();
    private IDeviceManager mService;

    public static VDeviceManager get() {
        return sInstance;
    }

    public IDeviceManager getService() {
        if (!IInterfaceUtils.isAlive(this.mService)) {
            synchronized (this) {
                this.mService = (IDeviceManager) LocalProxyUtils.genProxy(IDeviceManager.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IDeviceManager.Stub.asInterface(ServiceManagerNative.getService("device"));
    }

    public VDeviceConfig getDeviceConfig(int i) {
        try {
            return getService().getDeviceConfig(i);
        } catch (RemoteException e) {
            return (VDeviceConfig) VirtualRuntime.crash(e);
        }
    }

    public void updateDeviceConfig(int i, VDeviceConfig vDeviceConfig) {
        try {
            getService().updateDeviceConfig(i, vDeviceConfig);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isEnable(int i) {
        try {
            return getService().isEnable(i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setEnable(int i, boolean z) {
        try {
            getService().setEnable(i, z);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void applyBuildProp(VDeviceConfig vDeviceConfig) {
        for (Map.Entry<String, String> entry : vDeviceConfig.buildProp.entrySet()) {
            try {
                Reflect.m18999on(Build.TYPE).set(entry.getKey(), entry.getValue());
            } catch (ReflectException e) {
                e.printStackTrace();
            }
        }
        if (vDeviceConfig.serial != null) {
            Reflect.m18999on(Build.TYPE).set("SERIAL", vDeviceConfig.serial);
        }
    }
}
