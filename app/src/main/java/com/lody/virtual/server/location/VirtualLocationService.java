package com.lody.virtual.server.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.lody.virtual.helper.PersistenceLayer;
import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.vloc.VCell;
import com.lody.virtual.remote.vloc.VLocation;
import com.lody.virtual.server.interfaces.IVirtualLocationManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class VirtualLocationService extends IVirtualLocationManager.Stub {
    private static final VirtualLocationService sInstance = new VirtualLocationService();
    private final SparseArray<Map<String, VLocConfig>> mLocConfigs = new SparseArray<>();
    private final VLocConfig mGlobalConfig = new VLocConfig();
    private final PersistenceLayer mPersistenceLayer = new PersistenceLayer(VEnvironment.getVirtualLocationFile()) { // from class: com.lody.virtual.server.location.VirtualLocationService.1
        @Override // com.lody.virtual.helper.PersistenceLayer
        public int getCurrentVersion() {
            return 1;
        }

        @Override // com.lody.virtual.helper.PersistenceLayer
        public void writePersistenceData(Parcel parcel) {
            VirtualLocationService.this.mGlobalConfig.writeToParcel(parcel, 0);
            parcel.writeInt(VirtualLocationService.this.mLocConfigs.size());
            for (int i = 0; i < VirtualLocationService.this.mLocConfigs.size(); i++) {
                parcel.writeInt(VirtualLocationService.this.mLocConfigs.keyAt(i));
                parcel.writeMap((Map) VirtualLocationService.this.mLocConfigs.valueAt(i));
            }
        }

        @Override // com.lody.virtual.helper.PersistenceLayer
        public void readPersistenceData(Parcel parcel, int i) {
            VirtualLocationService.this.mGlobalConfig.set(new VLocConfig(parcel));
            VirtualLocationService.this.mLocConfigs.clear();
            int readInt = parcel.readInt();
            while (true) {
                readInt--;
                if (readInt > 0) {
                    VirtualLocationService.this.mLocConfigs.put(parcel.readInt(), parcel.readHashMap(getClass().getClassLoader()));
                } else {
                    return;
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VLocConfig implements Parcelable {
        public static final Parcelable.Creator<VLocConfig> CREATOR = new Parcelable.Creator<VLocConfig>() { // from class: com.lody.virtual.server.location.VirtualLocationService.VLocConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public VLocConfig createFromParcel(Parcel parcel) {
                return new VLocConfig(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public VLocConfig[] newArray(int i) {
                return new VLocConfig[i];
            }
        };
        List<VCell> allCell;
        VCell cell;
        VLocation location;
        int mode;
        List<VCell> neighboringCell;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void set(VLocConfig vLocConfig) {
            this.mode = vLocConfig.mode;
            this.cell = vLocConfig.cell;
            this.allCell = vLocConfig.allCell;
            this.neighboringCell = vLocConfig.neighboringCell;
            this.location = vLocConfig.location;
        }

        VLocConfig() {
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mode);
            parcel.writeParcelable(this.cell, i);
            parcel.writeTypedList(this.allCell);
            parcel.writeTypedList(this.neighboringCell);
            parcel.writeParcelable(this.location, i);
        }

        VLocConfig(Parcel parcel) {
            this.mode = parcel.readInt();
            this.cell = (VCell) parcel.readParcelable(VCell.class.getClassLoader());
            this.allCell = parcel.createTypedArrayList(VCell.CREATOR);
            this.neighboringCell = parcel.createTypedArrayList(VCell.CREATOR);
            this.location = (VLocation) parcel.readParcelable(VLocation.class.getClassLoader());
        }
    }

    public static VirtualLocationService get() {
        return sInstance;
    }

    private VirtualLocationService() {
        this.mPersistenceLayer.read();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public int getMode(int i, String str) {
        int i2;
        synchronized (this.mLocConfigs) {
            VLocConfig orCreateConfig = getOrCreateConfig(i, str);
            this.mPersistenceLayer.save();
            i2 = orCreateConfig.mode;
        }
        return i2;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setMode(int i, String str, int i2) {
        synchronized (this.mLocConfigs) {
            getOrCreateConfig(i, str).mode = i2;
            this.mPersistenceLayer.save();
        }
    }

    private VLocConfig getOrCreateConfig(int i, String str) {
        Map<String, VLocConfig> map = this.mLocConfigs.get(i);
        if (map == null) {
            map = new HashMap<>();
            this.mLocConfigs.put(i, map);
        }
        VLocConfig vLocConfig = map.get(str);
        if (vLocConfig != null) {
            return vLocConfig;
        }
        VLocConfig vLocConfig2 = new VLocConfig();
        vLocConfig2.mode = 0;
        map.put(str, vLocConfig2);
        return vLocConfig2;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setCell(int i, String str, VCell vCell) {
        getOrCreateConfig(i, str).cell = vCell;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setAllCell(int i, String str, List<VCell> list) {
        getOrCreateConfig(i, str).allCell = list;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setNeighboringCell(int i, String str, List<VCell> list) {
        getOrCreateConfig(i, str).neighboringCell = list;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setGlobalCell(VCell vCell) {
        this.mGlobalConfig.cell = vCell;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setGlobalAllCell(List<VCell> list) {
        this.mGlobalConfig.allCell = list;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setGlobalNeighboringCell(List<VCell> list) {
        this.mGlobalConfig.neighboringCell = list;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public VCell getCell(int i, String str) {
        VLocConfig orCreateConfig = getOrCreateConfig(i, str);
        this.mPersistenceLayer.save();
        switch (orCreateConfig.mode) {
            case 1:
                return this.mGlobalConfig.cell;
            case 2:
                return orCreateConfig.cell;
            default:
                return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public List<VCell> getAllCell(int i, String str) {
        VLocConfig orCreateConfig = getOrCreateConfig(i, str);
        this.mPersistenceLayer.save();
        switch (orCreateConfig.mode) {
            case 1:
                return this.mGlobalConfig.allCell;
            case 2:
                return orCreateConfig.allCell;
            default:
                return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public List<VCell> getNeighboringCell(int i, String str) {
        VLocConfig orCreateConfig = getOrCreateConfig(i, str);
        this.mPersistenceLayer.save();
        switch (orCreateConfig.mode) {
            case 1:
                return this.mGlobalConfig.neighboringCell;
            case 2:
                return orCreateConfig.neighboringCell;
            default:
                return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setLocation(int i, String str, VLocation vLocation) {
        getOrCreateConfig(i, str).location = vLocation;
        this.mPersistenceLayer.save();
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public VLocation getLocation(int i, String str) {
        VLocConfig orCreateConfig = getOrCreateConfig(i, str);
        this.mPersistenceLayer.save();
        switch (orCreateConfig.mode) {
            case 1:
                return this.mGlobalConfig.location;
            case 2:
                return orCreateConfig.location;
            default:
                return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public void setGlobalLocation(VLocation vLocation) {
        this.mGlobalConfig.location = vLocation;
    }

    @Override // com.lody.virtual.server.interfaces.IVirtualLocationManager
    public VLocation getGlobalLocation() {
        return this.mGlobalConfig.location;
    }
}
