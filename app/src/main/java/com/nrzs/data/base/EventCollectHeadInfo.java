package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class EventCollectHeadInfo implements Parcelable {
    public static final Parcelable.Creator<EventCollectHeadInfo> CREATOR = new Parcelable.Creator<EventCollectHeadInfo>() { // from class: com.nrzs.data.base.EventCollectHeadInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectHeadInfo createFromParcel(Parcel parcel) {
            return new EventCollectHeadInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectHeadInfo[] newArray(int i) {
            return new EventCollectHeadInfo[i];
        }
    };
    public String AppCode;
    public String AppVersion;
    public String Channel;
    public String DeviceKey;
    public String HardwareOS;
    public String HardwareType;
    public String HardwareVendor;
    public String UserKey;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.AppCode);
        parcel.writeString(this.AppVersion);
        parcel.writeString(this.Channel);
        parcel.writeString(this.UserKey);
        parcel.writeString(this.DeviceKey);
        parcel.writeString(this.HardwareVendor);
        parcel.writeString(this.HardwareType);
        parcel.writeString(this.HardwareOS);
    }

    public EventCollectHeadInfo() {
    }

    protected EventCollectHeadInfo(Parcel parcel) {
        this.AppCode = parcel.readString();
        this.AppVersion = parcel.readString();
        this.Channel = parcel.readString();
        this.UserKey = parcel.readString();
        this.DeviceKey = parcel.readString();
        this.HardwareVendor = parcel.readString();
        this.HardwareType = parcel.readString();
        this.HardwareOS = parcel.readString();
    }
}
