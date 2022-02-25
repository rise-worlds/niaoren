package com.nrzs.data.p066ft.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.nrzs.data.DataApp;
import p110z1.ShareVal;

/* renamed from: com.nrzs.data.ft.bean.EnginInteraRequestInfo */
/* loaded from: classes2.dex */
public class EnginInteraRequestInfo implements Parcelable {
    public static final Parcelable.Creator<EnginInteraRequestInfo> CREATOR = new Parcelable.Creator<EnginInteraRequestInfo>() { // from class: com.nrzs.data.ft.bean.EnginInteraRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnginInteraRequestInfo createFromParcel(Parcel parcel) {
            return new EnginInteraRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnginInteraRequestInfo[] newArray(int i) {
            return new EnginInteraRequestInfo[i];
        }
    };
    public String AppId;
    public String AppSign;
    public String ChannelName;
    public int Command;
    public String DesKey;
    public String DeviceType;
    public int IsInternalTool;
    public int IsNeedChooseGold;
    public int IsVa;
    public int NetworkType;
    public String PackageName;
    public EnginInteraParams Param;
    public String ScriptCacheRPath;
    public String SessionId;
    public String ToolKey;
    public long UserId;
    public String UserKey;
    public int VersionCode;
    public String VersionName;
    public String dycIp;
    @SuppressLint({"MissingPermission"})
    public String imei;
    public int isVip;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: com.nrzs.data.ft.bean.EnginInteraRequestInfo$EnginInteraParams */
    /* loaded from: classes2.dex */
    public static class EnginInteraParams implements Parcelable {
        public static final Parcelable.Creator<EnginInteraParams> CREATOR = new Parcelable.Creator<EnginInteraParams>() { // from class: com.nrzs.data.ft.bean.EnginInteraRequestInfo.EnginInteraParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnginInteraParams createFromParcel(Parcel parcel) {
                return new EnginInteraParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnginInteraParams[] newArray(int i) {
                return new EnginInteraParams[i];
            }
        };
        public String OnlyId;
        public long ToolId;
        public long TopicId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.TopicId);
            parcel.writeLong(this.ToolId);
            parcel.writeString(this.OnlyId);
        }

        public EnginInteraParams() {
        }

        protected EnginInteraParams(Parcel parcel) {
            this.TopicId = parcel.readLong();
            this.ToolId = parcel.readLong();
            this.OnlyId = parcel.readString();
        }

        public String toString() {
            return "EnginInteraParams{TopicId=" + this.TopicId + ", ToolId=" + this.ToolId + ", OnlyId='" + this.OnlyId + "'}";
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Command);
        parcel.writeString(this.AppSign);
        parcel.writeLong(this.UserId);
        parcel.writeString(this.SessionId);
        parcel.writeParcelable(this.Param, i);
        parcel.writeInt(this.NetworkType);
        parcel.writeString(this.DesKey);
        parcel.writeString(this.PackageName);
        parcel.writeString(this.VersionName);
        parcel.writeString(this.imei);
        parcel.writeString(this.DeviceType);
        parcel.writeInt(this.IsInternalTool);
        parcel.writeString(this.ChannelName);
        parcel.writeString(this.AppId);
        parcel.writeString(this.UserKey);
        parcel.writeString(this.ToolKey);
        parcel.writeInt(this.IsVa);
        parcel.writeString(this.ScriptCacheRPath);
        parcel.writeString(this.dycIp);
    }

    public EnginInteraRequestInfo() {
        this.PackageName = AppUtils.m22920i();
        this.VersionName = AppUtils.m22914l();
        this.VersionCode = AppUtils.m22912m();
        this.imei = DataApp.m18939d().m18941c();
        this.DeviceType = "Android";
        this.IsInternalTool = 0;
        this.ChannelName = DataApp.m18939d().m18944b();
        this.AppId = "default";
        this.UserKey = SPUtils.m23341a().m23320b(ShareVal.f16601k, ResultTypeConstant.f7213z);
        this.ToolKey = "";
        this.IsNeedChooseGold = 1;
    }

    protected EnginInteraRequestInfo(Parcel parcel) {
        this.PackageName = AppUtils.m22920i();
        this.VersionName = AppUtils.m22914l();
        this.VersionCode = AppUtils.m22912m();
        this.imei = DataApp.m18939d().m18941c();
        this.DeviceType = "Android";
        this.IsInternalTool = 0;
        this.ChannelName = DataApp.m18939d().m18944b();
        this.AppId = "default";
        this.UserKey = SPUtils.m23341a().m23320b(ShareVal.f16601k, ResultTypeConstant.f7213z);
        this.ToolKey = "";
        this.IsNeedChooseGold = 1;
        this.Command = parcel.readInt();
        this.AppSign = parcel.readString();
        this.UserId = parcel.readLong();
        this.SessionId = parcel.readString();
        this.Param = (EnginInteraParams) parcel.readParcelable(EnginInteraParams.class.getClassLoader());
        this.NetworkType = parcel.readInt();
        this.DesKey = parcel.readString();
        this.PackageName = parcel.readString();
        this.VersionName = parcel.readString();
        this.imei = parcel.readString();
        this.DeviceType = parcel.readString();
        this.IsInternalTool = parcel.readInt();
        this.ChannelName = parcel.readString();
        this.AppId = parcel.readString();
        this.UserKey = parcel.readString();
        this.ToolKey = parcel.readString();
        this.IsVa = parcel.readInt();
        this.ScriptCacheRPath = parcel.readString();
        this.dycIp = parcel.readString();
    }
}
