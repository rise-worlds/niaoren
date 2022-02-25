package com.cyjh.ddy.net.bean.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.p032a.C1118a;

/* loaded from: classes.dex */
public class BaseRequest implements Parcelable, NoProGuard {
    public static final Parcelable.Creator<BaseRequest> CREATOR = new Parcelable.Creator<BaseRequest>() { // from class: com.cyjh.ddy.net.bean.base.BaseRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequest createFromParcel(Parcel parcel) {
            return new BaseRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequest[] newArray(int i) {
            return new BaseRequest[i];
        }
    };
    public String AppId;
    public int signType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseRequest() {
        this.signType = 1;
        if (SdkUtils.m21761a()) {
            this.AppId = SdkKeyUtil.getInstance().getSdkKey();
            this.signType = 1;
        }
    }

    protected BaseRequest(Parcel parcel) {
        this.signType = 1;
        this.signType = parcel.readInt();
        this.AppId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.signType);
        parcel.writeString(this.AppId);
    }

    public String toJson(String str) throws Exception {
        return TextUtils.isEmpty(str) ? "" : C1118a.m21981a(str);
    }

    public String getJson() {
        String a = JsonUtil.m21804a(this);
        CLog.m21884d("BaseRequestValueInfo", "getJson: = " + a);
        return a;
    }
}
