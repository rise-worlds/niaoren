package com.cyjh.ddysdk.order.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class DdyMonthCardsRemindBase implements Parcelable, Serializable {
    public static final Parcelable.Creator<DdyMonthCardsRemindBase> CREATOR = new Parcelable.Creator<DdyMonthCardsRemindBase>() { // from class: com.cyjh.ddysdk.order.base.bean.DdyMonthCardsRemindBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyMonthCardsRemindBase createFromParcel(Parcel parcel) {
            return new DdyMonthCardsRemindBase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyMonthCardsRemindBase[] newArray(int i) {
            return new DdyMonthCardsRemindBase[i];
        }
    };
    public String MonthCardExpiredTime;
    public int ResidueHours;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DdyMonthCardsRemindBase() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.MonthCardExpiredTime);
        parcel.writeInt(this.ResidueHours);
    }

    protected DdyMonthCardsRemindBase(Parcel parcel) {
        this.MonthCardExpiredTime = parcel.readString();
        this.ResidueHours = parcel.readInt();
    }
}
