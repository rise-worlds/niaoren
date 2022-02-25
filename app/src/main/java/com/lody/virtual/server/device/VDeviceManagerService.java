package com.lody.virtual.server.device;

import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.server.interfaces.IDeviceManager;

/* loaded from: classes.dex */
public class VDeviceManagerService extends IDeviceManager.Stub {
    private static final VDeviceManagerService sInstance = new VDeviceManagerService();
    final SparseArray<VDeviceConfig> mDeviceConfigs = new SparseArray<>();
    private DeviceInfoPersistenceLayer mPersistenceLayer = new DeviceInfoPersistenceLayer(this);

    public static VDeviceManagerService get() {
        return sInstance;
    }

    private VDeviceManagerService() {
        this.mPersistenceLayer.read();
        for (int i = 0; i < this.mDeviceConfigs.size(); i++) {
            VDeviceConfig.addToPool(this.mDeviceConfigs.valueAt(i));
        }
    }

    @Override // com.lody.virtual.server.interfaces.IDeviceManager
    public VDeviceConfig getDeviceConfig(int i) {
        VDeviceConfig vDeviceConfig;
        synchronized (this.mDeviceConfigs) {
            vDeviceConfig = this.mDeviceConfigs.get(i);
            if (vDeviceConfig == null) {
                vDeviceConfig = VDeviceConfig.random();
                this.mDeviceConfigs.put(i, vDeviceConfig);
                this.mPersistenceLayer.save();
            }
        }
        return vDeviceConfig;
    }

    @Override // com.lody.virtual.server.interfaces.IDeviceManager
    public void updateDeviceConfig(int i, VDeviceConfig vDeviceConfig) {
        synchronized (this.mDeviceConfigs) {
            if (vDeviceConfig != null) {
                this.mDeviceConfigs.put(i, vDeviceConfig);
                this.mPersistenceLayer.save();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IDeviceManager
    public boolean isEnable(int i) {
        return getDeviceConfig(i).enable;
    }

    @Override // com.lody.virtual.server.interfaces.IDeviceManager
    public void setEnable(int i, boolean z) {
        synchronized (this.mDeviceConfigs) {
            VDeviceConfig vDeviceConfig = this.mDeviceConfigs.get(i);
            if (vDeviceConfig == null) {
                vDeviceConfig = VDeviceConfig.random();
                this.mDeviceConfigs.put(i, vDeviceConfig);
            }
            vDeviceConfig.enable = z;
            this.mPersistenceLayer.save();
        }
    }
}
