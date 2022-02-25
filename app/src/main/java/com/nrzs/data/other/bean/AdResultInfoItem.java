package com.nrzs.data.other.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
/* loaded from: classes2.dex */
public class AdResultInfoItem implements Parcelable {
    public static final Parcelable.Creator<AdResultInfoItem> CREATOR = new Parcelable.Creator<AdResultInfoItem>() { // from class: com.nrzs.data.other.bean.AdResultInfoItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdResultInfoItem createFromParcel(Parcel parcel) {
            return new AdResultInfoItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdResultInfoItem[] newArray(int i) {
            return new AdResultInfoItem[i];
        }
    };
    static final long serialVersionUID = -1;
    @ColumnInfo
    public String AdName;
    @ColumnInfo
    public int AdPosition;
    @ColumnInfo
    public String Appid;
    @ColumnInfo
    public String BigImg;
    @ColumnInfo
    public String CreateTime;
    @ColumnInfo
    public String EffectiveTime;
    @ColumnInfo
    public String ExecArgs;
    @ColumnInfo
    public String ExecCommand;
    @ColumnInfo
    public String FailureTime;
    @ColumnInfo

    /* renamed from: Id */
    public int f10647Id;
    @ColumnInfo
    public String ImgUrl;
    @ColumnInfo
    public String SubTitle;
    @ColumnInfo
    public String Title;
    @ColumnInfo
    public int UserType;
    @PrimaryKey(autoGenerate = true)
    public long tid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AdResultInfoItem() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10647Id);
        parcel.writeString(this.Appid);
        parcel.writeString(this.AdName);
        parcel.writeString(this.ImgUrl);
        parcel.writeString(this.Title);
        parcel.writeString(this.SubTitle);
        parcel.writeString(this.ExecCommand);
        parcel.writeString(this.ExecArgs);
        parcel.writeString(this.EffectiveTime);
        parcel.writeString(this.FailureTime);
        parcel.writeString(this.CreateTime);
        parcel.writeInt(this.UserType);
        parcel.writeString(this.BigImg);
        parcel.writeInt(this.AdPosition);
    }

    protected AdResultInfoItem(Parcel parcel) {
        this.f10647Id = parcel.readInt();
        this.Appid = parcel.readString();
        this.AdName = parcel.readString();
        this.ImgUrl = parcel.readString();
        this.Title = parcel.readString();
        this.SubTitle = parcel.readString();
        this.ExecCommand = parcel.readString();
        this.ExecArgs = parcel.readString();
        this.EffectiveTime = parcel.readString();
        this.FailureTime = parcel.readString();
        this.CreateTime = parcel.readString();
        this.UserType = parcel.readInt();
        this.BigImg = parcel.readString();
        this.AdPosition = parcel.readInt();
    }
}
