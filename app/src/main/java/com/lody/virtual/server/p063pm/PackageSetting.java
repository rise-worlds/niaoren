package com.lody.virtual.server.p063pm;

import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;

/* renamed from: com.lody.virtual.server.pm.PackageSetting */
/* loaded from: classes.dex */
public class PackageSetting implements Parcelable {
    public static final int CURRENT_VERSION = 5;
    public static final int FIRST_V2_VERSION = 5;
    public static final int FLAG_RUN_32BIT = 0;
    public static final int FLAG_RUN_64BIT = 2;
    public static final int FLAG_RUN_BOTH_32BIT_64BIT = 1;
    public static final boolean RUN_WITH_32BIT_FIRST = false;
    public int appId;
    public int appMode;
    public long firstInstallTime;
    public int flag;
    public long lastUpdateTime;
    public String packageName;
    SparseArray<PackageUserState> userState;
    public int version;
    private static final PackageUserState DEFAULT_USER_STATE = new PackageUserState();
    public static final Parcelable.Creator<PackageSetting> CREATOR = new Parcelable.Creator<PackageSetting>() { // from class: com.lody.virtual.server.pm.PackageSetting.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageSetting createFromParcel(Parcel parcel) {
            return new PackageSetting(5, parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageSetting[] newArray(int i) {
            return new PackageSetting[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PackageSetting() {
        this.userState = new SparseArray<>();
        this.version = 5;
    }

    public String getApkPath(boolean z) {
        if (this.appMode == 1) {
            try {
                return VirtualCore.get().getUnHookPackageManager().getApplicationInfo(this.packageName, 0).publicSourceDir;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else if (z) {
            return VEnvironment.getPackageResourcePath64(this.packageName).getPath();
        } else {
            return VEnvironment.getPackageResourcePath(this.packageName).getPath();
        }
    }

    public InstalledAppInfo getAppInfo() {
        return new InstalledAppInfo(this.packageName, this.appMode, this.flag, this.appId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeUser(int i) {
        this.userState.delete(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackageUserState modifyUserState(int i) {
        PackageUserState packageUserState = this.userState.get(i);
        if (packageUserState != null) {
            return packageUserState;
        }
        PackageUserState packageUserState2 = new PackageUserState();
        this.userState.put(i, packageUserState2);
        return packageUserState2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUserState(int i, boolean z, boolean z2, boolean z3) {
        PackageUserState modifyUserState = modifyUserState(i);
        modifyUserState.launched = z;
        modifyUserState.hidden = z2;
        modifyUserState.installed = z3;
    }

    public PackageUserState readUserState(int i) {
        PackageUserState packageUserState = this.userState.get(i);
        return packageUserState != null ? packageUserState : DEFAULT_USER_STATE;
    }

    public boolean isLaunched(int i) {
        return readUserState(i).launched;
    }

    public boolean isHidden(int i) {
        return readUserState(i).hidden;
    }

    public boolean isInstalled(int i) {
        return readUserState(i).installed;
    }

    public void setLaunched(int i, boolean z) {
        modifyUserState(i).launched = z;
    }

    public void setHidden(int i, boolean z) {
        modifyUserState(i).hidden = z;
    }

    public void setInstalled(int i, boolean z) {
        modifyUserState(i).installed = z;
    }

    public boolean isRunOn64BitProcess() {
        switch (this.flag) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return true;
            default:
                return false;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeInt(this.appId);
        parcel.writeInt(this.appMode);
        parcel.writeSparseArray(this.userState);
        parcel.writeInt(this.flag);
        parcel.writeLong(this.firstInstallTime);
        parcel.writeLong(this.lastUpdateTime);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackageSetting(int i, Parcel parcel) {
        this.userState = new SparseArray<>();
        this.version = i;
        this.packageName = parcel.readString();
        this.appId = parcel.readInt();
        this.appMode = parcel.readInt();
        this.userState = parcel.readSparseArray(PackageUserState.class.getClassLoader());
        this.flag = parcel.readInt();
        this.firstInstallTime = parcel.readLong();
        this.lastUpdateTime = parcel.readLong();
    }
}
