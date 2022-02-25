package com.cyjh.ddysdk.order;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.CustomDns;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddy.net.utils.DomainUtils;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.OrderInfoRespone;
import com.cyjh.ddysdk.order.base.bean.OrderlistResponeInfo;
import com.cyjh.ddysdk.order.base.bean.SdkLoginRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import com.cyjh.ddysdk.order.base.model.HttpRequestModel;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DdyOrderHelper implements DdyOrderContract.IPresenter {

    /* renamed from: a */
    private static DdyOrderHelper f8052a;

    private DdyOrderHelper() {
    }

    public static DdyOrderContract.IPresenter getInstance() {
        if (f8052a == null) {
            f8052a = new DdyOrderHelper();
        }
        return f8052a;
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void initKey(String str, String str2) {
        SdkKeyUtil.getInstance().setSdkKey(str);
        SdkKeyUtil.getInstance().setSdkType(str2);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void setEnableD310(boolean z) {
        SdkKeyUtil.getInstance().setEnableD310(z);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void setPush(boolean z) {
        SdkKeyUtil.getInstance().setPush(z);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void initHTTPDNS(Context context, boolean z) {
        DomainUtils.m21395a(context, z);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void setCustomDNS(Context context, CustomDns customDns) {
        DomainUtils.m21396a(context, customDns);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void requestOrderDetail(long j, String str, final String str2, String str3, final DdyOrderContract.TCallback<DdyOrderInfo> tCallback) {
        CLog.m21882i("sdk-order", "requestOrderDetail orderId=" + j + ",ucid=" + str + ",token=" + str2);
        if (!SdkUtils.m21760b() || (!TextUtils.isEmpty(str2) && str2.length() == 64)) {
            new HttpRequestModel().m21135a(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.1
                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataSuccess(Object obj) {
                    BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                    if (baseResultWrapper == null) {
                        CLog.m21883e("sdk-order", "requestOrderDetail wrapper == null");
                        tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                    } else if (baseResultWrapper.code != 1) {
                        CLog.m21883e("sdk-order", "requestOrderDetail onFail  code=" + baseResultWrapper.code);
                        tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                    } else {
                        ((OrderInfoRespone) baseResultWrapper.data).DeviceToken = str2;
                        tCallback.onSuccess((OrderInfoRespone) baseResultWrapper.data);
                    }
                }

                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataError(Exception exc) {
                    CLog.m21883e("sdk-order", "requestOrderDetail uiDataError ");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
                }
            });
        } else {
            tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, "deviceToken check error.");
        }
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void requestOrderReboot(long j, String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestOrderReboot orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21134a(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.2
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderReboot wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestOrderReboot onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderReboot uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
            }
        }, -1L);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void requestOrderRoot(long j, String str, String str2, String str3, boolean z, final DdyOrderContract.Callback callback) {
        new HttpRequestModel().m21134a(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.3
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderRoot wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestOrderRoot onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderReset uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
            }
        }, z ? 1L : 0L);
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void requestOrderReset(long j, String str, String str2, String str3, final DdyOrderContract.Callback callback) {
        new HttpRequestModel().m21129b(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.4
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderReset wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestOrderReset onFail  code=" + baseResultWrapper.code);
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderReset uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.order.DdyOrderContract.IPresenter
    public void requestSDKLogin(String str, final DdyOrderContract.TCallback<SdkLoginRespone> tCallback) {
        new HttpRequestModel().m21130a(str, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.5
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestSDKLogin wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestSDKLogin onFail  code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    tCallback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestSDKLogin uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
            }
        });
    }

    public void requestOrderList(String str, final DdyOrderContract.TCallback<ArrayList<DdyOrderInfo>> tCallback) {
        new HttpRequestModel().m21133a(new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelper.6
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderList wrapper == null");
                    tCallback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-order", "requestOrderList onFail  code=" + baseResultWrapper.code);
                    tCallback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                } else {
                    ArrayList arrayList = new ArrayList();
                    if (!(baseResultWrapper.data == 0 || ((OrderlistResponeInfo) baseResultWrapper.data).getOrderList() == null)) {
                        arrayList.addAll(((OrderlistResponeInfo) baseResultWrapper.data).getOrderList());
                    }
                    tCallback.onSuccess(arrayList);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderList uiDataError=" + exc);
                tCallback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, exc == null ? "" : exc.getMessage());
            }
        }, 100, -1L, str);
    }
}
