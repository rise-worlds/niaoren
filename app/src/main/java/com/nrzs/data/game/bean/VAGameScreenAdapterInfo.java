package com.nrzs.data.game.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class VAGameScreenAdapterInfo implements Parcelable {
    public static final Parcelable.Creator<VAGameScreenAdapterInfo> CREATOR = new Parcelable.Creator<VAGameScreenAdapterInfo>() { // from class: com.nrzs.data.game.bean.VAGameScreenAdapterInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAGameScreenAdapterInfo createFromParcel(Parcel parcel) {
            return new VAGameScreenAdapterInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VAGameScreenAdapterInfo[] newArray(int i) {
            return new VAGameScreenAdapterInfo[i];
        }
    };
    public String brand;
    public String model;
    public String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.model);
        parcel.writeString(this.brand);
        parcel.writeString(this.name);
    }

    public VAGameScreenAdapterInfo() {
    }

    protected VAGameScreenAdapterInfo(Parcel parcel) {
        this.model = parcel.readString();
        this.brand = parcel.readString();
        this.name = parcel.readString();
    }
}
