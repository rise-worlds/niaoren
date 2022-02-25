package com.lody.virtual.server.device;

import android.os.Parcel;
import com.lody.virtual.helper.PersistenceLayer;
import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.VDeviceConfig;

/* loaded from: classes.dex */
public class DeviceInfoPersistenceLayer extends PersistenceLayer {
    private VDeviceManagerService mService;

    @Override // com.lody.virtual.helper.PersistenceLayer
    public int getCurrentVersion() {
        return 3;
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public boolean verifyMagic(Parcel parcel) {
        return true;
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void writeMagic(Parcel parcel) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceInfoPersistenceLayer(VDeviceManagerService vDeviceManagerService) {
        super(VEnvironment.getDeviceInfoFile());
        this.mService = vDeviceManagerService;
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void writePersistenceData(Parcel parcel) {
        SparseArray<VDeviceConfig> sparseArray = this.mService.mDeviceConfigs;
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            parcel.writeInt(sparseArray.keyAt(i));
            sparseArray.valueAt(i).writeToParcel(parcel, 0);
        }
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void readPersistenceData(Parcel parcel, int i) {
        SparseArray<VDeviceConfig> sparseArray = this.mService.mDeviceConfigs;
        sparseArray.clear();
        int readInt = parcel.readInt();
        while (true) {
            readInt--;
            if (readInt > 0) {
                sparseArray.put(parcel.readInt(), new VDeviceConfig(parcel));
            } else {
                return;
            }
        }
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void onPersistenceFileDamage() {
        getPersistenceFile().delete();
    }
}
