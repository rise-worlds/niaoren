package com.lody.virtual.server.p063pm;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.lody.virtual.server.pm.PackageUserState */
/* loaded from: classes.dex */
public class PackageUserState implements Parcelable {
    public static final Parcelable.Creator<PackageUserState> CREATOR = new Parcelable.Creator<PackageUserState>() { // from class: com.lody.virtual.server.pm.PackageUserState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageUserState createFromParcel(Parcel parcel) {
            return new PackageUserState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageUserState[] newArray(int i) {
            return new PackageUserState[i];
        }
    };
    public boolean hidden;
    public boolean installed;
    public boolean launched;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PackageUserState() {
        this.installed = false;
        this.launched = true;
        this.hidden = false;
    }

    protected PackageUserState(Parcel parcel) {
        boolean z = true;
        this.launched = parcel.readByte() != 0;
        this.hidden = parcel.readByte() != 0;
        this.installed = parcel.readByte() == 0 ? false : z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.launched ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.hidden ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.installed ? (byte) 1 : (byte) 0);
    }
}
