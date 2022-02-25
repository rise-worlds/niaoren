package com.cyjh.ddysdk.order;

import android.content.Context;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.net.inf.CustomDns;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.SdkLoginRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;

/* loaded from: classes.dex */
public interface DdyOrderContract extends NoProGuard {

    /* loaded from: classes.dex */
    public interface Callback extends NoProGuard {
        void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str);

        void onSuccess(Object obj);
    }

    /* loaded from: classes.dex */
    public interface IOrderSavePresenter extends NoProGuard {
        void requestAttemptRepair(long j, String str, String str2, String str3, Callback callback);

        void requestManualOrderReplace(long j, String str, String str2, String str3, Callback callback);

        void requestOrderReplace(long j, String str, String str2, String str3, Callback callback);

        void requestRetryDataRecovery(long j, String str, String str2, String str3, Callback callback);
    }

    /* loaded from: classes.dex */
    public interface IPresenter extends NoProGuard {
        void initHTTPDNS(Context context, boolean z);

        void initKey(String str, String str2);

        void requestOrderDetail(long j, String str, String str2, String str3, TCallback<DdyOrderInfo> tCallback);

        void requestOrderReboot(long j, String str, String str2, String str3, Callback callback);

        void requestOrderReset(long j, String str, String str2, String str3, Callback callback);

        void requestOrderRoot(long j, String str, String str2, String str3, boolean z, Callback callback);

        void requestSDKLogin(String str, TCallback<SdkLoginRespone> tCallback);

        void setCustomDNS(Context context, CustomDns customDns);

        void setEnableD310(boolean z);

        void setPush(boolean z);
    }

    /* loaded from: classes.dex */
    public interface TCallback<T> extends NoProGuard {
        void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str);

        void onSuccess(T t);
    }
}
