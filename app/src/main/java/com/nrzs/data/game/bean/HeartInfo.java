package com.nrzs.data.game.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class HeartInfo implements Parcelable {
    public static final Parcelable.Creator<HeartInfo> CREATOR = new Parcelable.Creator<HeartInfo>() { // from class: com.nrzs.data.game.bean.HeartInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeartInfo createFromParcel(Parcel parcel) {
            return new HeartInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeartInfo[] newArray(int i) {
            return new HeartInfo[i];
        }
    };
    public int BtnType;
    public CouponInfo CouponInfo;
    public int ExpireDate;
    public int HeartbeatInterval;
    public boolean IsShowMsg;
    public String Msg;
    public int Status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Status);
        parcel.writeInt(this.HeartbeatInterval);
        parcel.writeString(this.Msg);
        parcel.writeInt(this.BtnType);
        parcel.writeInt(this.ExpireDate);
        parcel.writeByte(this.IsShowMsg ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.CouponInfo, i);
    }

    public HeartInfo() {
    }

    protected HeartInfo(Parcel parcel) {
        this.Status = parcel.readInt();
        this.HeartbeatInterval = parcel.readInt();
        this.Msg = parcel.readString();
        this.BtnType = parcel.readInt();
        this.ExpireDate = parcel.readInt();
        this.IsShowMsg = parcel.readByte() != 0;
        this.CouponInfo = (CouponInfo) parcel.readParcelable(CouponInfo.class.getClassLoader());
    }
}
