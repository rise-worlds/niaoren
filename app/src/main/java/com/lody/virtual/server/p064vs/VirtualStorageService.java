package com.lody.virtual.server.p064vs;

import android.util.SparseArray;
import com.lody.virtual.server.interfaces.IVirtualStorageService;
import com.lody.virtual.server.p063pm.VUserManagerService;
import java.util.HashMap;

/* renamed from: com.lody.virtual.server.vs.VirtualStorageService */
/* loaded from: classes.dex */
public class VirtualStorageService extends IVirtualStorageService.Stub {
    private static final VirtualStorageService sService = new VirtualStorageService();
    private final VSPersistenceLayer mLayer = new VSPersistenceLayer(this);
    private final SparseArray<HashMap<String, VSConfig>> mConfigs = new SparseArray<>();

    public static VirtualStorageService get() {
        return sService;
    }

    private VirtualStorageService() {
        this.mLayer.read();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SparseArray<HashMap<String, VSConfig>> getConfigs() {
        return this.mConfigs;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualStorageService
    public void setVirtualStorage(String str, int i, String str2) {
        checkUserId(i);
        synchronized (this.mConfigs) {
            getOrCreateVSConfigLocked(str, i).vsPath = str2;
            this.mLayer.save();
        }
    }

    private VSConfig getOrCreateVSConfigLocked(String str, int i) {
        HashMap<String, VSConfig> hashMap = this.mConfigs.get(i);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.mConfigs.put(i, hashMap);
        }
        VSConfig vSConfig = hashMap.get(str);
        if (vSConfig != null) {
            return vSConfig;
        }
        VSConfig vSConfig2 = new VSConfig();
        vSConfig2.enable = true;
        hashMap.put(str, vSConfig2);
        return vSConfig2;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualStorageService
    public String getVirtualStorage(String str, int i) {
        String str2;
        checkUserId(i);
        synchronized (this.mConfigs) {
            str2 = getOrCreateVSConfigLocked(str, i).vsPath;
        }
        return str2;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualStorageService
    public void setVirtualStorageState(String str, int i, boolean z) {
        checkUserId(i);
        synchronized (this.mConfigs) {
            getOrCreateVSConfigLocked(str, i).enable = z;
            this.mLayer.save();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualStorageService
    public boolean isVirtualStorageEnable(String str, int i) {
        boolean z;
        checkUserId(i);
        synchronized (this.mConfigs) {
            z = getOrCreateVSConfigLocked(str, i).enable;
        }
        return z;
    }

    private void checkUserId(int i) {
        if (!VUserManagerService.get().exists(i)) {
            throw new IllegalStateException("Invalid userId " + i);
        }
    }
}
