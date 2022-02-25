package com.cyjh.ddysdk.order.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DdyOrderInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<DdyOrderInfo> CREATOR = new Parcelable.Creator<DdyOrderInfo>() { // from class: com.cyjh.ddysdk.order.base.bean.DdyOrderInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyOrderInfo createFromParcel(Parcel parcel) {
            return new DdyOrderInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyOrderInfo[] newArray(int i) {
            return new DdyOrderInfo[i];
        }
    };
    public static final int STATUS_Backup_Optimization = 14;
    public static final int STATUS_Connecting = 1;
    public static final int STATUS_Data_Back_Fail = -6;
    public static final int STATUS_Deleted = -1;
    public static final int STATUS_Deleted_Extend = 15;
    public static final int STATUS_Deleted_Save = -4;
    public static final int STATUS_Ended = 4;
    public static final int STATUS_Ended_Exception = 6;
    public static final int STATUS_Ending = 3;
    public static final int STATUS_Maintaining = -2;
    public static final int STATUS_NO_Device = -5;
    public static final int STATUS_PanicBuyNoDev = -101;
    public static final int STATUS_PayBuyNoDev = -102;
    public static final int STATUS_Rebooting = 5;
    public static final int STATUS_Recover_Exception = 12;
    public static final int STATUS_Recover_Optimization = 11;
    public static final int STATUS_Recover_Recharge = 13;
    public static final int STATUS_Recovery_Ab_Normal = -8;
    public static final int STATUS_Recovery_Normal = -7;
    public static final int STATUS_Resetting = 7;
    public static final int STATUS_Running = 2;
    public String OrderCreateTime;
    public long OrderId;
    public int OrderStatus;
    public String ProtectTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("{\"OrderId\":%d,\"OrderStatus\":%d,\"OrderCreateTime\":\"%s\"}", Long.valueOf(this.OrderId), Integer.valueOf(this.OrderStatus), this.OrderCreateTime);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.OrderId);
        parcel.writeInt(this.OrderStatus);
        parcel.writeString(this.OrderCreateTime);
        parcel.writeString(this.ProtectTime);
    }

    public DdyOrderInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DdyOrderInfo(Parcel parcel) {
        this.OrderId = parcel.readLong();
        this.OrderStatus = parcel.readInt();
        this.OrderCreateTime = parcel.readString();
        this.ProtectTime = parcel.readString();
    }
}
