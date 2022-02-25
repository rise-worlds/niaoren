package com.lody.virtual.server.p063pm;

import android.os.Parcel;
import com.lody.virtual.helper.PersistenceLayer;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.server.p063pm.legacy.PackageSettingV1;
import com.lody.virtual.server.p063pm.parser.VPackage;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.lody.virtual.server.pm.PackagePersistenceLayer */
/* loaded from: classes.dex */
public class PackagePersistenceLayer extends PersistenceLayer {
    private static final int CURRENT_VERSION = 5;
    private static final char[] MAGIC = {'v', 'p', 'k', 'g'};
    public boolean changed = false;
    private VAppManagerService mService;

    @Override // com.lody.virtual.helper.PersistenceLayer
    public int getCurrentVersion() {
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackagePersistenceLayer(VAppManagerService vAppManagerService) {
        super(VEnvironment.getPackageListFile());
        this.mService = vAppManagerService;
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
        synchronized (PackageCacheManager.PACKAGE_CACHE) {
            parcel.writeInt(PackageCacheManager.PACKAGE_CACHE.size());
            for (VPackage vPackage : PackageCacheManager.PACKAGE_CACHE.values()) {
                ((PackageSetting) vPackage.mExtras).writeToParcel(parcel, 0);
            }
        }
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void readPersistenceData(Parcel parcel, int i) {
        PackageSetting packageSetting;
        int readInt = parcel.readInt();
        while (true) {
            readInt--;
            if (readInt > 0) {
                if (i < 5) {
                    this.changed = true;
                    PackageSettingV1 packageSettingV1 = new PackageSettingV1();
                    packageSettingV1.readFromParcel(parcel, i);
                    packageSetting = new PackageSetting();
                    packageSetting.packageName = packageSettingV1.packageName;
                    packageSetting.appMode = packageSettingV1.notCopyApk ? 1 : 0;
                    packageSetting.appId = packageSettingV1.appId;
                    packageSetting.flag = packageSettingV1.flag;
                    packageSetting.userState = packageSettingV1.userState;
                    packageSetting.firstInstallTime = System.currentTimeMillis();
                    packageSetting.lastUpdateTime = packageSetting.firstInstallTime;
                } else {
                    packageSetting = new PackageSetting(i, parcel);
                }
                if (!this.mService.loadPackage(packageSetting)) {
                    this.changed = true;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.lody.virtual.helper.PersistenceLayer
    public void onPersistenceFileDamage() {
        getPersistenceFile().delete();
        VAppManagerService.get().restoreFactoryState();
    }
}
