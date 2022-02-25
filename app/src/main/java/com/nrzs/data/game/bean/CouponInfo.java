package com.nrzs.data.game.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CouponInfo implements Parcelable {
    public static final Parcelable.Creator<CouponInfo> CREATOR = new Parcelable.Creator<CouponInfo>() { // from class: com.nrzs.data.game.bean.CouponInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponInfo createFromParcel(Parcel parcel) {
            return new CouponInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponInfo[] newArray(int i) {
            return new CouponInfo[i];
        }
    };
    public String BatchName;
    public String CouponNum;
    public String DiscountAmount;
    public String ExpiryTime;
    public String MinUseAmount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.CouponNum);
        parcel.writeString(this.BatchName);
        parcel.writeString(this.ExpiryTime);
        parcel.writeString(this.DiscountAmount);
        parcel.writeString(this.MinUseAmount);
    }

    public CouponInfo() {
    }

    protected CouponInfo(Parcel parcel) {
        this.CouponNum = parcel.readString();
        this.BatchName = parcel.readString();
        this.ExpiryTime = parcel.readString();
        this.DiscountAmount = parcel.readString();
        this.MinUseAmount = parcel.readString();
    }
}
