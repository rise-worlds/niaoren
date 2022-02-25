package com.nrzs.data.user.bean.respond;

import android.os.Parcel;
import android.os.Parcelable;
import com.nrzs.data.user.bean.UserInfo;
import java.util.List;

/* loaded from: classes2.dex */
public class LoginResultV1Info implements Parcelable {
    public static final Parcelable.Creator<LoginResultV1Info> CREATOR = new Parcelable.Creator<LoginResultV1Info>() { // from class: com.nrzs.data.user.bean.respond.LoginResultV1Info.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginResultV1Info createFromParcel(Parcel parcel) {
            return new LoginResultV1Info(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginResultV1Info[] newArray(int i) {
            return new LoginResultV1Info[i];
        }
    };
    public long AscriptionAuthorId;
    public String AutoLoginToken;
    public List<DeviceListEntity> DeviceList;
    public int ReturnType;
    public String UploadLocalAppPackage;
    public long UserId;
    public UserInfo UserInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes2.dex */
    public static class DeviceListEntity implements Parcelable {
        public static final Parcelable.Creator<DeviceListEntity> CREATOR = new Parcelable.Creator<DeviceListEntity>() { // from class: com.nrzs.data.user.bean.respond.LoginResultV1Info.DeviceListEntity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DeviceListEntity createFromParcel(Parcel parcel) {
                return new DeviceListEntity(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DeviceListEntity[] newArray(int i) {
                return new DeviceListEntity[i];
            }
        };
        public String DeviceModel;
        public int DeviceType;
        public String IMEI;
        public String LoginTime;
        public String SessionId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public DeviceListEntity() {
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.LoginTime);
            parcel.writeString(this.DeviceModel);
            parcel.writeString(this.IMEI);
            parcel.writeString(this.SessionId);
            parcel.writeInt(this.DeviceType);
        }

        protected DeviceListEntity(Parcel parcel) {
            this.LoginTime = parcel.readString();
            this.DeviceModel = parcel.readString();
            this.IMEI = parcel.readString();
            this.SessionId = parcel.readString();
            this.DeviceType = parcel.readInt();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.AutoLoginToken);
        parcel.writeLong(this.UserId);
        parcel.writeString(this.UploadLocalAppPackage);
        parcel.writeInt(this.ReturnType);
        parcel.writeParcelable(this.UserInfo, i);
        parcel.writeLong(this.AscriptionAuthorId);
        parcel.writeTypedList(this.DeviceList);
    }

    public LoginResultV1Info() {
    }

    protected LoginResultV1Info(Parcel parcel) {
        this.AutoLoginToken = parcel.readString();
        this.UserId = parcel.readLong();
        this.UploadLocalAppPackage = parcel.readString();
        this.ReturnType = parcel.readInt();
        this.UserInfo = (UserInfo) parcel.readParcelable(UserInfo.class.getClassLoader());
        this.AscriptionAuthorId = parcel.readLong();
        this.DeviceList = parcel.createTypedArrayList(DeviceListEntity.CREATOR);
    }
}
