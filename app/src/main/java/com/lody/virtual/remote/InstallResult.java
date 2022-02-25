package com.lody.virtual.remote;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class InstallResult implements Parcelable {
    public static final Parcelable.Creator<InstallResult> CREATOR = new Parcelable.Creator<InstallResult>() { // from class: com.lody.virtual.remote.InstallResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallResult createFromParcel(Parcel parcel) {
            return new InstallResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallResult[] newArray(int i) {
            return new InstallResult[i];
        }
    };
    public String error;
    public boolean isSuccess;
    public boolean isUpdate;
    public String packageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InstallResult() {
    }

    protected InstallResult(Parcel parcel) {
        boolean z = true;
        this.isSuccess = parcel.readByte() != 0;
        this.isUpdate = parcel.readByte() == 0 ? false : z;
        this.packageName = parcel.readString();
        this.error = parcel.readString();
    }

    public static InstallResult makeFailure(String str) {
        InstallResult installResult = new InstallResult();
        installResult.error = str;
        return installResult;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isSuccess ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isUpdate ? (byte) 1 : (byte) 0);
        parcel.writeString(this.packageName);
        parcel.writeString(this.error);
    }

    public String toString() {
        return "InstallResult{isSuccess=" + this.isSuccess + ", isUpdate=" + this.isUpdate + ", packageName='" + this.packageName + "', error='" + this.error + "'}";
    }
}
