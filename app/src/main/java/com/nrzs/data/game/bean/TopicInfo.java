package com.nrzs.data.game.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
/* loaded from: classes2.dex */
public class TopicInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<TopicInfo> CREATOR = new Parcelable.Creator<TopicInfo>() { // from class: com.nrzs.data.game.bean.TopicInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopicInfo createFromParcel(Parcel parcel) {
            return new TopicInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopicInfo[] newArray(int i) {
            return new TopicInfo[i];
        }
    };
    @ColumnInfo
    public int BitSportCfg;
    @ColumnInfo
    public String FnTags;
    @ColumnInfo
    public int IfRecommend;
    @ColumnInfo
    public String ImgPath;
    @ColumnInfo
    public int IsShowServerIcon;
    @ColumnInfo
    public int LastBulletinID;
    @ColumnInfo
    public String MatchPackage;
    @ColumnInfo
    public int MatchType;
    @ColumnInfo
    public int OrderNum;
    @ColumnInfo
    public String Package;
    @ColumnInfo
    public String PackageNames;
    @ColumnInfo
    public int ScriptCount;
    @ColumnInfo
    public int ShowOrder;
    @ColumnInfo
    public int ShowType;
    @ColumnInfo
    public int SportBackGround;
    @ColumnInfo
    public long TopicID;
    @ColumnInfo
    public String TopicName;
    @ColumnInfo
    public int VaScriptCount;
    @Ignore
    public boolean isFirst;
    @ColumnInfo
    public int isShield;
    @ColumnInfo
    public long isTop;
    @ColumnInfo
    public int isVaVip;
    @ColumnInfo
    public int isVip;
    @Ignore
    public Drawable localAppIcon;
    @ColumnInfo
    public String localAppName;
    @ColumnInfo
    public String localAppPackage;
    @ColumnInfo
    public String localVersionName;
    @ColumnInfo
    public String sTags;
    @ColumnInfo
    public String sortLetters;
    @ColumnInfo
    public int sportModel;
    @PrimaryKey(autoGenerate = true)
    public long tid;
    @ColumnInfo
    public long time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        String str;
        TopicInfo topicInfo = (TopicInfo) obj;
        String str2 = this.localAppPackage;
        return (str2 == null || (str = topicInfo.localAppPackage) == null) ? topicInfo.TopicID == this.TopicID : topicInfo.TopicID == this.TopicID && str2.equals(str);
    }

    public Object clone() throws CloneNotSupportedException {
        return (TopicInfo) super.clone();
    }

    public String toString() {
        return "TopicInfo{tid=" + this.tid + ", TopicID=" + this.TopicID + ", Package='" + this.Package + "', PackageNames='" + this.PackageNames + "', IfRecommend=" + this.IfRecommend + ", TopicName='" + this.TopicName + "', ImgPath='" + this.ImgPath + "', ScriptCount=" + this.ScriptCount + ", FnTags='" + this.FnTags + "', isVip=" + this.isVip + ", SportBackGround=" + this.SportBackGround + ", isVaVip=" + this.isVaVip + ", isShield=" + this.isShield + ", VaScriptCount=" + this.VaScriptCount + ", sTags='" + this.sTags + "', IsShowServerIcon=" + this.IsShowServerIcon + ", MatchType=" + this.MatchType + ", OrderNum=" + this.OrderNum + ", MatchPackage='" + this.MatchPackage + "', ShowOrder=" + this.ShowOrder + ", LastBulletinID=" + this.LastBulletinID + ", ShowType=" + this.ShowType + ", sportModel=" + this.sportModel + ", isTop=" + this.isTop + ", localAppPackage='" + this.localAppPackage + "', localAppName='" + this.localAppName + "', sortLetters='" + this.sortLetters + "', localVersionName='" + this.localVersionName + "', time=" + this.time + ", isFirst=" + this.isFirst + ", localAppIcon=" + this.localAppIcon + ", BitSportCfg=" + this.BitSportCfg + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.tid);
        parcel.writeLong(this.TopicID);
        parcel.writeString(this.Package);
        parcel.writeString(this.PackageNames);
        parcel.writeInt(this.IfRecommend);
        parcel.writeString(this.TopicName);
        parcel.writeString(this.ImgPath);
        parcel.writeInt(this.ScriptCount);
        parcel.writeString(this.FnTags);
        parcel.writeInt(this.isVip);
        parcel.writeInt(this.isVaVip);
        parcel.writeInt(this.SportBackGround);
        parcel.writeInt(this.isShield);
        parcel.writeInt(this.VaScriptCount);
        parcel.writeString(this.sTags);
        parcel.writeInt(this.IsShowServerIcon);
        parcel.writeInt(this.MatchType);
        parcel.writeInt(this.OrderNum);
        parcel.writeString(this.MatchPackage);
        parcel.writeInt(this.ShowOrder);
        parcel.writeInt(this.LastBulletinID);
        parcel.writeInt(this.ShowType);
        parcel.writeInt(this.sportModel);
        parcel.writeLong(this.isTop);
        parcel.writeString(this.localAppPackage);
        parcel.writeString(this.localAppName);
        parcel.writeString(this.sortLetters);
        parcel.writeString(this.localVersionName);
        parcel.writeLong(this.time);
        parcel.writeByte(this.isFirst ? (byte) 1 : (byte) 0);
    }

    public TopicInfo() {
        this.isTop = 0L;
    }

    protected TopicInfo(Parcel parcel) {
        this.isTop = 0L;
        this.tid = parcel.readLong();
        this.TopicID = parcel.readLong();
        this.Package = parcel.readString();
        this.PackageNames = parcel.readString();
        this.IfRecommend = parcel.readInt();
        this.TopicName = parcel.readString();
        this.ImgPath = parcel.readString();
        this.ScriptCount = parcel.readInt();
        this.FnTags = parcel.readString();
        this.isVip = parcel.readInt();
        this.SportBackGround = parcel.readInt();
        this.isVaVip = parcel.readInt();
        this.isShield = parcel.readInt();
        this.VaScriptCount = parcel.readInt();
        this.sTags = parcel.readString();
        this.IsShowServerIcon = parcel.readInt();
        this.MatchType = parcel.readInt();
        this.OrderNum = parcel.readInt();
        this.MatchPackage = parcel.readString();
        this.ShowOrder = parcel.readInt();
        this.LastBulletinID = parcel.readInt();
        this.ShowType = parcel.readInt();
        this.sportModel = parcel.readInt();
        this.isTop = parcel.readLong();
        this.localAppPackage = parcel.readString();
        this.localAppName = parcel.readString();
        this.sortLetters = parcel.readString();
        this.localVersionName = parcel.readString();
        this.time = parcel.readLong();
        this.isFirst = parcel.readByte() != 0;
    }
}
