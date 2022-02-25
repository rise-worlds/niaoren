package com.nrzs.data.p066ft.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.nrzs.data.ft.bean.AssistInfo */
/* loaded from: classes2.dex */
public class AssistInfo implements Parcelable {
    public static final Parcelable.Creator<AssistInfo> CREATOR = new Parcelable.Creator<AssistInfo>() { // from class: com.nrzs.data.ft.bean.AssistInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistInfo createFromParcel(Parcel parcel) {
            return new AssistInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistInfo[] newArray(int i) {
            return new AssistInfo[i];
        }
    };
    public String AuthorRewardSGBTotalNum;
    public String AuthorRewardSGBTotalNumStr;
    public float Gold;
    public int IsVip;
    public String OnlyID;
    public long ScriptAuthor;
    public String ScriptAuthorName;
    public String ScriptDesc;
    public long ScriptID;
    public String ScriptIco;
    public String ScriptName;
    public String ScriptNickName;
    public String ScriptPath;
    public float ScriptScort;
    public float StarLevel;
    public long TopicId;
    public String UpdateTime;
    public int UseOcrText;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AssistInfo() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.TopicId);
        parcel.writeLong(this.ScriptID);
        parcel.writeString(this.OnlyID);
        parcel.writeString(this.ScriptName);
        parcel.writeString(this.ScriptIco);
        parcel.writeString(this.UpdateTime);
        parcel.writeFloat(this.StarLevel);
        parcel.writeInt(this.IsVip);
        parcel.writeString(this.ScriptDesc);
        parcel.writeString(this.ScriptPath);
        parcel.writeFloat(this.ScriptScort);
        parcel.writeLong(this.ScriptAuthor);
        parcel.writeString(this.ScriptAuthorName);
        parcel.writeString(this.ScriptNickName);
        parcel.writeString(this.AuthorRewardSGBTotalNum);
        parcel.writeString(this.AuthorRewardSGBTotalNumStr);
        parcel.writeFloat(this.Gold);
        parcel.writeInt(this.UseOcrText);
    }

    protected AssistInfo(Parcel parcel) {
        this.TopicId = parcel.readLong();
        this.ScriptID = parcel.readLong();
        this.OnlyID = parcel.readString();
        this.ScriptName = parcel.readString();
        this.ScriptIco = parcel.readString();
        this.UpdateTime = parcel.readString();
        this.StarLevel = parcel.readFloat();
        this.IsVip = parcel.readInt();
        this.ScriptDesc = parcel.readString();
        this.ScriptPath = parcel.readString();
        this.ScriptScort = parcel.readFloat();
        this.ScriptAuthor = parcel.readLong();
        this.ScriptAuthorName = parcel.readString();
        this.ScriptNickName = parcel.readString();
        this.AuthorRewardSGBTotalNum = parcel.readString();
        this.AuthorRewardSGBTotalNumStr = parcel.readString();
        this.Gold = parcel.readFloat();
        this.UseOcrText = parcel.readInt();
    }
}
