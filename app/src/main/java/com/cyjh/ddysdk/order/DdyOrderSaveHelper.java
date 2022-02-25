package com.cyjh.ddysdk.order;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderStatusAlterRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import com.cyjh.ddysdk.order.base.model.HttpRequestModel;

/* loaded from: classes.dex */
public class DdyOrderSaveHelper implements DdyOrderContract.IOrderSavePresenter {

    /* renamed from: a */
    private static DdyOrderSaveHelper f8077a;

    private DdyOrderSaveHelper() {
    }

    public static DdyOrderSaveHelper getInstance() {
        if (f8077a == null) {
            f8077a = new DdyOrderSaveHelper();
        }
        return f8077a;
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IOrderSavePresenter
    public void requestOrderReplace(long j, String str, String str2, String str3, DdyOrderContract.Callback callback) {
        DdyOrderHelperEx.m21149a().m21147a(j, str, str2, str3, false, callback);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IOrderSavePresenter
    public void requestManualOrderReplace(long j, String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestManualOrderReplace orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21126e(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderSaveHelper.1
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestManualOrderReplace wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestManualOrderReplace onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else if (baseResultWrapper.data == 0 || TextUtils.isEmpty(((DdyOrderStatusAlterRespone) baseResultWrapper.data).ChangeDeviceUrl)) {
                    callback.onSuccess(baseResultWrapper.data);
                } else {
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_APP_100, ((DdyOrderStatusAlterRespone) baseResultWrapper.data).ChangeDeviceUrl);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestManualOrderReplace uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IOrderSavePresenter
    public void requestRetryDataRecovery(long j, String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestRetryDataRecovery orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21125f(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderSaveHelper.2
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestRetryDataRecovery wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestRetryDataRecovery onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestRetryDataRecovery uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IOrderSavePresenter
    public void requestAttemptRepair(long j, String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestAttemptRepair orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21124g(j, str2, str, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderSaveHelper.3
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestAttemptRepair wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestAttemptRepair onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestAttemptRepair uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }
}
