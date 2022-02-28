package com.lody.virtual.server.vs;

import android.os.Parcel;
import android.util.SparseArray;
import com.lody.virtual.helper.PersistenceLayer;
import com.lody.virtual.os.VEnvironment;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: com.lody.virtual.server.vs.VSPersistenceLayer */
/* loaded from: classes.dex */
class VSPersistenceLayer extends PersistenceLayer {
    private static final int CURRENT_VERSION = 1;
    private static final char[] MAGIC = {'v', 's', 'a'};
    private final VirtualStorageService mService;

    @Override // com.lody.virtual.helper.PersistenceLayer
    public int getCurrentVersion() {
        return 1;
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void onPersistenceFileDamage() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VSPersistenceLayer(VirtualStorageService virtualStorageService) {
        super(VEnvironment.getVSConfigFile());
        this.mService = virtualStorageService;
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void writeMagic(Parcel parcel) {
        parcel.writeCharArray(MAGIC);
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public boolean verifyMagic(Parcel parcel) {
        return Arrays.equals(parcel.createCharArray(), MAGIC);
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void writePersistenceData(Parcel parcel) {
        SparseArray<HashMap<String, VSConfig>> configs = this.mService.getConfigs();
        int size = configs.size();
        parcel.writeInt(size);
        while (true) {
            int i = size - 1;
            if (size > 0) {
                parcel.writeInt(configs.keyAt(i));
                parcel.writeMap(configs.valueAt(i));
                size = i;
            } else {
                return;
            }
        }
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void readPersistenceData(Parcel parcel, int i) {
        SparseArray<HashMap<String, VSConfig>> configs = this.mService.getConfigs();
        int readInt = parcel.readInt();
        while (true) {
            readInt--;
            if (readInt > 0) {
                configs.put(parcel.readInt(), parcel.readHashMap(VSConfig.class.getClassLoader()));
            } else {
                return;
            }
        }
    }
}
