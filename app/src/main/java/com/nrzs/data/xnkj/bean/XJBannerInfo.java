package com.nrzs.data.xnkj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class XJBannerInfo implements Parcelable {
    public static final Parcelable.Creator<XJBannerInfo> CREATOR = new Parcelable.Creator<XJBannerInfo>() { // from class: com.nrzs.data.xnkj.bean.XJBannerInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XJBannerInfo createFromParcel(Parcel parcel) {
            return new XJBannerInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XJBannerInfo[] newArray(int i) {
            return new XJBannerInfo[i];
        }
    };
    public String adName;
    public String execArg;
    public String execCommand;

    /* renamed from: id */
    public long f10659id;
    public String imgUrl;
    public int showPosition;
    public long showTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f10659id);
        parcel.writeString(this.adName);
        parcel.writeInt(this.showPosition);
        parcel.writeString(this.imgUrl);
        parcel.writeLong(this.showTime);
        parcel.writeString(this.execCommand);
        parcel.writeString(this.execArg);
    }

    public XJBannerInfo() {
    }

    protected XJBannerInfo(Parcel parcel) {
        this.f10659id = parcel.readLong();
        this.adName = parcel.readString();
        this.showPosition = parcel.readInt();
        this.imgUrl = parcel.readString();
        this.showTime = parcel.readLong();
        this.execCommand = parcel.readString();
        this.execArg = parcel.readString();
    }

    public String toString() {
        return "XJBannerInfo{id=" + this.f10659id + ", adName='" + this.adName + "', showPosition=" + this.showPosition + ", imgUrl='" + this.imgUrl + "', showTime=" + this.showTime + ", execCommand='" + this.execCommand + "', execArg='" + this.execArg + "'}";
    }
}
