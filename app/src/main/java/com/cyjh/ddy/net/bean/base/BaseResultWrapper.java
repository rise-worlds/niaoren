package com.cyjh.ddy.net.bean.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import p110z1.SerializedName;

/* loaded from: classes.dex */
public class BaseResultWrapper<T> implements Parcelable {
    public static final Parcelable.Creator<BaseResultWrapper> CREATOR = new Parcelable.Creator<BaseResultWrapper>() { // from class: com.cyjh.ddy.net.bean.base.BaseResultWrapper.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseResultWrapper createFromParcel(Parcel parcel) {
            return new BaseResultWrapper(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseResultWrapper[] newArray(int i) {
            return new BaseResultWrapper[i];
        }
    };
    public final String DATA_KEY = "data_key";
    @SerializedName(m1427a = "code", m1426b = {"Code"})
    public int code;
    @SerializedName(m1427a = "data", m1426b = {"Data"})
    public T data;
    @SerializedName(m1427a = "msg", m1426b = {"Msg"})
    public String msg;
    @SerializedName(m1427a = "msgtype", m1426b = {"Msgtype"})
    public int msgtype;
    @SerializedName(m1427a = "r", m1426b = {"R"})

    /* renamed from: r */
    public int f7481r;
    @SerializedName(m1427a = "sign", m1426b = {"Sign"})
    public String sign;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.msg);
        parcel.writeInt(this.code);
        parcel.writeString(this.sign);
        parcel.writeInt(this.f7481r);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data_key", (Parcelable) this.data);
        parcel.writeBundle(bundle);
        parcel.writeInt(this.msgtype);
    }

    public BaseResultWrapper() {
    }

    protected BaseResultWrapper(Parcel parcel) {
        this.msg = parcel.readString();
        this.code = parcel.readInt();
        this.sign = parcel.readString();
        this.f7481r = parcel.readInt();
        this.data = (T) parcel.readBundle().getParcelable("data_key");
        this.msgtype = parcel.readInt();
    }
}
