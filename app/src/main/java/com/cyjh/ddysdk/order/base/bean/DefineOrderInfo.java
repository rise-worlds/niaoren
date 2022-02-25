package com.cyjh.ddysdk.order.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DefineOrderInfo extends DdyOrderInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<DefineOrderInfo> CREATOR = new Parcelable.Creator<DefineOrderInfo>() { // from class: com.cyjh.ddysdk.order.base.bean.DefineOrderInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefineOrderInfo createFromParcel(Parcel parcel) {
            return new DefineOrderInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DefineOrderInfo[] newArray(int i) {
            return new DefineOrderInfo[i];
        }
    };
    public String DeviceTcpHost;
    public String DeviceToken;

    @Override // com.cyjh.ddysdk.order.base.bean.DdyOrderInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.cyjh.ddysdk.order.base.bean.DdyOrderInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.DeviceTcpHost);
        parcel.writeString(this.DeviceToken);
    }

    public DefineOrderInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DefineOrderInfo(Parcel parcel) {
        super(parcel);
        this.DeviceTcpHost = parcel.readString();
        this.DeviceToken = parcel.readString();
    }
}
