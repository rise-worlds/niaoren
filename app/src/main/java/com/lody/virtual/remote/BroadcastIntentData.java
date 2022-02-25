package com.lody.virtual.remote;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BroadcastIntentData implements Parcelable {
    public static final Parcelable.Creator<BroadcastIntentData> CREATOR = new Parcelable.Creator<BroadcastIntentData>() { // from class: com.lody.virtual.remote.BroadcastIntentData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BroadcastIntentData createFromParcel(Parcel parcel) {
            return new BroadcastIntentData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BroadcastIntentData[] newArray(int i) {
            return new BroadcastIntentData[i];
        }
    };
    public Intent intent;
    public String targetPackage;
    public int userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeParcelable(this.intent, i);
        parcel.writeString(this.targetPackage);
    }

    public BroadcastIntentData(int i, Intent intent, String str) {
        this.userId = i;
        this.intent = intent;
        this.targetPackage = str;
    }

    public BroadcastIntentData(Parcel parcel) {
        this.userId = parcel.readInt();
        this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.targetPackage = parcel.readString();
    }
}
