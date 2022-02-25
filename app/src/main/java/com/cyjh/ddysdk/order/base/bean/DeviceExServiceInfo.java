package com.cyjh.ddysdk.order.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DeviceExServiceInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<DeviceExServiceInfo> CREATOR = new Parcelable.Creator<DeviceExServiceInfo>() { // from class: com.cyjh.ddysdk.order.base.bean.DeviceExServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceExServiceInfo createFromParcel(Parcel parcel) {
            return new DeviceExServiceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceExServiceInfo[] newArray(int i) {
            return new DeviceExServiceInfo[i];
        }
    };
    public String ExpireTime;
    public String ServeicIcon;
    public String ServiceCode;
    public String ServiceDesc;
    public String ServiceName;
    public int ServiceSort;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected DeviceExServiceInfo(Parcel parcel) {
        this.ServiceCode = parcel.readString();
        this.ServiceName = parcel.readString();
        this.ServiceDesc = parcel.readString();
        this.ServeicIcon = parcel.readString();
        this.ServiceSort = parcel.readInt();
        this.ExpireTime = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ServiceCode);
        parcel.writeString(this.ServiceName);
        parcel.writeString(this.ServiceDesc);
        parcel.writeString(this.ServeicIcon);
        parcel.writeInt(this.ServiceSort);
        parcel.writeString(this.ExpireTime);
    }
}
