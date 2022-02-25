package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class EventCollectCommandParamEntity implements Parcelable {
    public static final Parcelable.Creator<EventCollectCommandParamEntity> CREATOR = new Parcelable.Creator<EventCollectCommandParamEntity>() { // from class: com.nrzs.data.base.EventCollectCommandParamEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectCommandParamEntity createFromParcel(Parcel parcel) {
            return new EventCollectCommandParamEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectCommandParamEntity[] newArray(int i) {
            return new EventCollectCommandParamEntity[i];
        }
    };
    public String Key;
    public String Value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Key);
        parcel.writeString(this.Value);
    }

    public EventCollectCommandParamEntity() {
    }

    protected EventCollectCommandParamEntity(Parcel parcel) {
        this.Key = parcel.readString();
        this.Value = parcel.readString();
    }
}
