package com.lody.virtual.remote;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class InstallOptions implements Parcelable {
    public static final Parcelable.Creator<InstallOptions> CREATOR = new Parcelable.Creator<InstallOptions>() { // from class: com.lody.virtual.remote.InstallOptions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallOptions createFromParcel(Parcel parcel) {
            return new InstallOptions(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallOptions[] newArray(int i) {
            return new InstallOptions[i];
        }
    };
    public boolean notify;
    public UpdateStrategy updateStrategy;
    public boolean useSourceLocationApk;

    /* loaded from: classes.dex */
    public enum UpdateStrategy {
        TERMINATE_IF_EXIST,
        FORCE_UPDATE,
        COMPARE_VERSION,
        IGNORE_NEW_VERSION
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InstallOptions() {
        this.useSourceLocationApk = false;
        this.notify = true;
        this.updateStrategy = UpdateStrategy.COMPARE_VERSION;
    }

    public InstallOptions(boolean z, boolean z2, UpdateStrategy updateStrategy) {
        this.useSourceLocationApk = false;
        this.notify = true;
        this.updateStrategy = UpdateStrategy.COMPARE_VERSION;
        this.useSourceLocationApk = z;
        this.notify = z2;
        this.updateStrategy = updateStrategy;
    }

    public static InstallOptions makeOptions(boolean z, boolean z2, UpdateStrategy updateStrategy) {
        return new InstallOptions(z, z2, updateStrategy);
    }

    public static InstallOptions makeOptions(boolean z, UpdateStrategy updateStrategy) {
        return new InstallOptions(z, true, updateStrategy);
    }

    public static InstallOptions makeOptions(boolean z) {
        return new InstallOptions(z, true, UpdateStrategy.COMPARE_VERSION);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.useSourceLocationApk ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.notify ? (byte) 1 : (byte) 0);
        UpdateStrategy updateStrategy = this.updateStrategy;
        parcel.writeInt(updateStrategy == null ? -1 : updateStrategy.ordinal());
    }

    protected InstallOptions(Parcel parcel) {
        boolean z = false;
        this.useSourceLocationApk = false;
        this.notify = true;
        this.updateStrategy = UpdateStrategy.COMPARE_VERSION;
        this.useSourceLocationApk = parcel.readByte() != 0;
        this.notify = parcel.readByte() != 0 ? true : z;
        int readInt = parcel.readInt();
        this.updateStrategy = readInt == -1 ? null : UpdateStrategy.values()[readInt];
    }
}
