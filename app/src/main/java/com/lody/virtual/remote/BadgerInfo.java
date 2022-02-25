package com.lody.virtual.remote;

import android.os.Parcel;
import android.os.Parcelable;
import com.lody.virtual.p061os.VUserHandle;

/* loaded from: classes.dex */
public class BadgerInfo implements Parcelable {
    public static final Parcelable.Creator<BadgerInfo> CREATOR = new Parcelable.Creator<BadgerInfo>() { // from class: com.lody.virtual.remote.BadgerInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BadgerInfo createFromParcel(Parcel parcel) {
            return new BadgerInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BadgerInfo[] newArray(int i) {
            return new BadgerInfo[i];
        }
    };
    public int badgerCount;
    public String className;
    public String packageName;
    public int userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BadgerInfo() {
        this.userId = VUserHandle.myUserId();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeString(this.packageName);
        parcel.writeInt(this.badgerCount);
        parcel.writeString(this.className);
    }

    protected BadgerInfo(Parcel parcel) {
        this.userId = parcel.readInt();
        this.packageName = parcel.readString();
        this.badgerCount = parcel.readInt();
        this.className = parcel.readString();
    }
}
