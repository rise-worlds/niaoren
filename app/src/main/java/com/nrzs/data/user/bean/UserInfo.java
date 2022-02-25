package com.nrzs.data.user.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.nrzs.data.user.bean.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };
    public static final String USER_INFO = "user_info";
    private static final long serialVersionUID = 1;
    public long AscriptionAuthorId;
    private int AttentionCount;
    private String AuthorTitle;
    private String Content;
    public List<OrderDaileInfo> DDY_OrderInfos;
    public long DisCountSecond;
    public String DurationCardTimes;
    private int FansCount;
    public long FreeSecond;
    private int GameNoticeNum;
    public float GoldCoinNum;
    public String GoldExpireTime;
    public String HeadImgPath;
    private int Honey;
    private String Ico;
    private int IfAuthentic;
    private int IfBind;
    private int IsExistNickName;
    public boolean IsExpire;
    private boolean IsTmpAccount;
    public int IsVip;
    private String MobileNumber;
    private int MsgNum;
    private int MyFriendCount;
    public String NRVipExpireTime;
    public String NickName;
    public int OpenNum;
    private String PayUrl;
    private String PersonalInfo;
    private int ReceviceRP;
    public String RechargeUrl;
    private String RegPacketUrl;
    private int ReleaseTwitterCount;
    public double RemainDuration;
    public float SGB;
    private int SGCoin;
    public String ToolSecret;
    private long UCID;
    public long UserID;
    public String UserName;
    public String UserSessionId;
    public String UserTags;
    public String VIPExpireTime;
    private int VIPType;
    private int fromWhere;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUserTags() {
        return this.UserTags;
    }

    public void setUserTags(String str) {
        this.UserTags = str;
    }

    public UserInfo() {
    }

    public String getformatSGB() {
        return new DecimalFormat("#").format(this.SGB);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.SGB);
        parcel.writeString(this.UserName);
        parcel.writeString(this.PayUrl);
        parcel.writeLong(this.UCID);
        parcel.writeLong(this.UserID);
        parcel.writeString(this.UserSessionId);
        parcel.writeString(this.NickName);
        parcel.writeString(this.HeadImgPath);
        parcel.writeString(this.PersonalInfo);
        parcel.writeInt(this.ReleaseTwitterCount);
        parcel.writeInt(this.FansCount);
        parcel.writeInt(this.AttentionCount);
        parcel.writeInt(this.MyFriendCount);
        parcel.writeInt(this.IsExistNickName);
        parcel.writeString(this.Content);
        parcel.writeInt(this.IfBind);
        parcel.writeInt(this.MsgNum);
        parcel.writeInt(this.IfAuthentic);
        parcel.writeInt(this.GameNoticeNum);
        parcel.writeString(this.AuthorTitle);
        parcel.writeString(this.Ico);
        parcel.writeInt(this.fromWhere);
        parcel.writeInt(this.IsVip);
        parcel.writeString(this.VIPExpireTime);
        parcel.writeInt(this.SGCoin);
        parcel.writeString(this.RegPacketUrl);
        parcel.writeInt(this.ReceviceRP);
        parcel.writeInt(this.Honey);
        parcel.writeInt(this.VIPType);
        parcel.writeByte(this.IsTmpAccount ? (byte) 1 : (byte) 0);
        parcel.writeString(this.MobileNumber);
        parcel.writeLong(this.DisCountSecond);
        parcel.writeDouble(this.RemainDuration);
        parcel.writeLong(this.FreeSecond);
        parcel.writeString(this.UserTags);
        parcel.writeString(this.DurationCardTimes);
        parcel.writeString(this.RechargeUrl);
        parcel.writeString(this.ToolSecret);
        parcel.writeFloat(this.GoldCoinNum);
        parcel.writeInt(this.OpenNum);
        parcel.writeString(this.GoldExpireTime);
        parcel.writeLong(this.AscriptionAuthorId);
        parcel.writeByte(this.IsExpire ? (byte) 1 : (byte) 0);
        parcel.writeString(this.NRVipExpireTime);
        parcel.writeList(this.DDY_OrderInfos);
    }

    protected UserInfo(Parcel parcel) {
        this.SGB = parcel.readFloat();
        this.UserName = parcel.readString();
        this.PayUrl = parcel.readString();
        this.UCID = parcel.readLong();
        this.UserID = parcel.readLong();
        this.UserSessionId = parcel.readString();
        this.NickName = parcel.readString();
        this.HeadImgPath = parcel.readString();
        this.PersonalInfo = parcel.readString();
        this.ReleaseTwitterCount = parcel.readInt();
        this.FansCount = parcel.readInt();
        this.AttentionCount = parcel.readInt();
        this.MyFriendCount = parcel.readInt();
        this.IsExistNickName = parcel.readInt();
        this.Content = parcel.readString();
        this.IfBind = parcel.readInt();
        this.MsgNum = parcel.readInt();
        this.IfAuthentic = parcel.readInt();
        this.GameNoticeNum = parcel.readInt();
        this.AuthorTitle = parcel.readString();
        this.Ico = parcel.readString();
        this.fromWhere = parcel.readInt();
        this.IsVip = parcel.readInt();
        this.VIPExpireTime = parcel.readString();
        this.SGCoin = parcel.readInt();
        this.RegPacketUrl = parcel.readString();
        this.ReceviceRP = parcel.readInt();
        this.Honey = parcel.readInt();
        this.VIPType = parcel.readInt();
        boolean z = true;
        this.IsTmpAccount = parcel.readByte() != 0;
        this.MobileNumber = parcel.readString();
        this.DisCountSecond = parcel.readLong();
        this.RemainDuration = parcel.readDouble();
        this.FreeSecond = parcel.readLong();
        this.UserTags = parcel.readString();
        this.DurationCardTimes = parcel.readString();
        this.RechargeUrl = parcel.readString();
        this.ToolSecret = parcel.readString();
        this.GoldCoinNum = parcel.readFloat();
        this.OpenNum = parcel.readInt();
        this.GoldExpireTime = parcel.readString();
        this.AscriptionAuthorId = parcel.readLong();
        this.IsExpire = parcel.readByte() == 0 ? false : z;
        this.NRVipExpireTime = parcel.readString();
        this.DDY_OrderInfos = new ArrayList();
        parcel.readList(this.DDY_OrderInfos, OrderDaileInfo.class.getClassLoader());
    }
}
