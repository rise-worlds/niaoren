package com.cyjh.ddysdk.order.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OrderInfoRespone extends DefineOrderInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<OrderInfoRespone> CREATOR = new Parcelable.Creator<OrderInfoRespone>() { // from class: com.cyjh.ddysdk.order.base.bean.OrderInfoRespone.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderInfoRespone createFromParcel(Parcel parcel) {
            return new OrderInfoRespone(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderInfoRespone[] newArray(int i) {
            return new OrderInfoRespone[i];
        }
    };
    public int AbnormalRemind;
    public String DeviceHost;
    public ArrayList<DeviceExServiceInfo> ExServiceInfos;
    public boolean IsLock;
    public boolean IsPaidOrder;
    public DdyMonthCardsRemindBase MonthCardRemindInfo;
    public int MonthCardType;
    public long NewMsgTimeSpan;
    public String OrderIdPrefix;
    public String OrderIdSuffix;
    public String OrderRemark;
    public String PhoneName;
    public int RemainingDuration;
    public int RemainingMonthCard;
    public int RoComCphNonRoot;
    public String Runingtime;
    public String SessionId;
    public String StayOrderName;
    public String Title;
    public boolean isCheck;

    @Override // com.cyjh.ddysdk.order.base.bean.DefineOrderInfo, com.cyjh.ddysdk.order.base.bean.DdyOrderInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OrderInfoRespone() {
    }

    @Override // com.cyjh.ddysdk.order.base.bean.DefineOrderInfo, com.cyjh.ddysdk.order.base.bean.DdyOrderInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.OrderIdPrefix);
        parcel.writeString(this.OrderRemark);
        parcel.writeString(this.OrderIdSuffix);
        parcel.writeString(this.Title);
        parcel.writeString(this.SessionId);
        parcel.writeString(this.DeviceHost);
        parcel.writeInt(this.RemainingDuration);
        parcel.writeString(this.Runingtime);
        parcel.writeInt(this.RemainingMonthCard);
        parcel.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.MonthCardRemindInfo, i);
        parcel.writeInt(this.MonthCardType);
        parcel.writeByte(this.IsLock ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.IsPaidOrder ? (byte) 1 : (byte) 0);
        parcel.writeString(this.PhoneName);
        parcel.writeString(this.StayOrderName);
        parcel.writeTypedList(this.ExServiceInfos);
        parcel.writeLong(this.NewMsgTimeSpan);
        parcel.writeInt(this.AbnormalRemind);
        parcel.writeInt(this.RoComCphNonRoot);
    }

    protected OrderInfoRespone(Parcel parcel) {
        super(parcel);
        this.OrderIdPrefix = parcel.readString();
        this.OrderRemark = parcel.readString();
        this.OrderIdSuffix = parcel.readString();
        this.Title = parcel.readString();
        this.SessionId = parcel.readString();
        this.DeviceHost = parcel.readString();
        this.RemainingDuration = parcel.readInt();
        this.Runingtime = parcel.readString();
        this.RemainingMonthCard = parcel.readInt();
        boolean z = true;
        this.isCheck = parcel.readByte() != 0;
        this.MonthCardRemindInfo = (DdyMonthCardsRemindBase) parcel.readParcelable(DdyMonthCardsRemindBase.class.getClassLoader());
        this.MonthCardType = parcel.readInt();
        this.IsLock = parcel.readByte() != 0;
        this.IsPaidOrder = parcel.readByte() == 0 ? false : z;
        this.PhoneName = parcel.readString();
        this.StayOrderName = parcel.readString();
        this.ExServiceInfos = parcel.createTypedArrayList(DeviceExServiceInfo.CREATOR);
        this.NewMsgTimeSpan = parcel.readLong();
        this.AbnormalRemind = parcel.readInt();
        this.RoComCphNonRoot = parcel.readInt();
    }
}
