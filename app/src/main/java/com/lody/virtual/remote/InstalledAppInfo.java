package com.lody.virtual.remote;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.p061os.VEnvironment;
import java.io.File;

/* loaded from: classes.dex */
public final class InstalledAppInfo implements Parcelable {
    public static final Parcelable.Creator<InstalledAppInfo> CREATOR = new Parcelable.Creator<InstalledAppInfo>() { // from class: com.lody.virtual.remote.InstalledAppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstalledAppInfo createFromParcel(Parcel parcel) {
            return new InstalledAppInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstalledAppInfo[] newArray(int i) {
            return new InstalledAppInfo[i];
        }
    };
    public static final int MODE_APP_COPY_APK = 0;
    public static final int MODE_APP_USE_OUTSIDE_APK = 1;
    public int appId;
    public int appMode;
    public int flag;
    public String packageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InstalledAppInfo(String str, int i, int i2, int i3) {
        this.packageName = str;
        this.appMode = i;
        this.flag = i2;
        this.appId = i3;
    }

    public String getApkPath() {
        return getApkPath(VirtualCore.get().is64BitEngine());
    }

    public String getApkPath(boolean z) {
        if (this.appMode == 1) {
            try {
                return VirtualCore.get().getUnHookPackageManager().getApplicationInfo(this.packageName, 0).publicSourceDir;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(e);
            }
        } else if (z) {
            return VEnvironment.getPackageResourcePath64(this.packageName).getPath();
        } else {
            return VEnvironment.getPackageResourcePath(this.packageName).getPath();
        }
    }

    public String getOdexPath() {
        return getOdexFile().getPath();
    }

    public String getOdexPath(boolean z) {
        return getOdexFile(z).getPath();
    }

    public File getOdexFile() {
        return getOdexFile(VirtualCore.get().is64BitEngine());
    }

    public File getOdexFile(boolean z) {
        if (z) {
            return VEnvironment.getOdexFile64(this.packageName);
        }
        return VEnvironment.getOdexFile(this.packageName);
    }

    public ApplicationInfo getApplicationInfo(int i) {
        return VPackageManager.get().getApplicationInfo(this.packageName, 0, i);
    }

    public PackageInfo getPackageInfo(int i) {
        return VPackageManager.get().getPackageInfo(this.packageName, 0, i);
    }

    public int[] getInstalledUsers() {
        return VirtualCore.get().getPackageInstalledUsers(this.packageName);
    }

    public boolean isLaunched(int i) {
        return VirtualCore.get().isPackageLaunched(i, this.packageName);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeInt(this.appMode);
        parcel.writeInt(this.flag);
        parcel.writeInt(this.appId);
    }

    protected InstalledAppInfo(Parcel parcel) {
        this.packageName = parcel.readString();
        this.appMode = parcel.readInt();
        this.flag = parcel.readInt();
        this.appId = parcel.readInt();
    }

    public String toString() {
        return "InstalledAppInfo{packageName='" + this.packageName + "', appMode=" + this.appMode + ", flag=" + this.flag + ", appId=" + this.appId + '}';
    }
}
