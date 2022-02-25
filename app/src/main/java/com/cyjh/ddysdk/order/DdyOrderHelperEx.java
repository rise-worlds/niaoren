package com.cyjh.ddysdk.order;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderStatusAlterRespone;
import com.cyjh.ddysdk.order.base.bean.OrderSteamServerRespone;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import com.cyjh.ddysdk.order.base.model.HttpRequestModel;
import com.cyjh.ddysdk.order.base.p041a.EncodeServiceManager;

/* renamed from: com.cyjh.ddysdk.order.b */
/* loaded from: classes.dex */
public class DdyOrderHelperEx {

    /* renamed from: a */
    private static DdyOrderHelperEx f8090a;

    private DdyOrderHelperEx() {
    }

    /* renamed from: a */
    public static DdyOrderHelperEx m21149a() {
        if (f8090a == null) {
            f8090a = new DdyOrderHelperEx();
        }
        return f8090a;
    }

    /* renamed from: a */
    public void m21148a(long j, String str, String str2, String str3, final String str4, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestOrderStart orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21128c(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelperEx$1
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderStart wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code == 1) {
                    DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone = (DdyOrderStatusAlterRespone) baseResultWrapper.data;
                    if (TextUtils.isEmpty(ddyOrderStatusAlterRespone.PullStreamTcpUrl) || TextUtils.isEmpty(ddyOrderStatusAlterRespone.DeviceTcpHost)) {
                        callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), "订单信息错误");
                    } else if (ddyOrderStatusAlterRespone.YunDeviceType == 2) {
                        DdyOrderHelperEx.this.m21144a(ddyOrderStatusAlterRespone, str4, callback, SdkKeyUtil.getInstance().isEnableD310());
                    } else {
                        ddyOrderStatusAlterRespone.PullStreamParam = ddyOrderStatusAlterRespone.DeviceTcpHost;
                        callback.onSuccess(ddyOrderStatusAlterRespone);
                    }
                } else {
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderStart uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21144a(final DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone, String str, final DdyOrderContract.Callback callback, boolean z) {
        if (EncodeServiceManager.m21141a().m21140a(ddyOrderStatusAlterRespone.OrderId)) {
            m21143a(EncodeServiceManager.m21141a().m21136c(), ddyOrderStatusAlterRespone, callback);
        } else {
            new HttpRequestModel().m21132a(new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelperEx$2
                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataSuccess(Object obj) {
                    BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                    if (baseResultWrapper != null && baseResultWrapper.code == 1) {
                        OrderSteamServerRespone orderSteamServerRespone = (OrderSteamServerRespone) baseResultWrapper.data;
                        EncodeServiceManager.m21141a().m21139a(ddyOrderStatusAlterRespone.OrderId, orderSteamServerRespone);
                        DdyOrderHelperEx.this.m21143a(orderSteamServerRespone, ddyOrderStatusAlterRespone, callback);
                    } else if (baseResultWrapper != null) {
                        callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                    } else {
                        callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, "");
                    }
                }

                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataError(Exception exc) {
                    CLog.m21883e("sdk-order", "requestOrderAnboxStart uiDataError=" + exc);
                    callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
                }
            }, ddyOrderStatusAlterRespone, str, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21143a(OrderSteamServerRespone orderSteamServerRespone, DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone, DdyOrderContract.Callback callback) {
        String str;
        String[] split;
        DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone2 = new DdyOrderStatusAlterRespone(ddyOrderStatusAlterRespone);
        ddyOrderStatusAlterRespone2.PullStreamTcpUrl = orderSteamServerRespone.AnboxStreamUrl;
        ddyOrderStatusAlterRespone2.ProxyUrl = orderSteamServerRespone.ProxyUrl;
        ddyOrderStatusAlterRespone2.isH265 = orderSteamServerRespone.ServerType == 4;
        ddyOrderStatusAlterRespone2.OtherParam = orderSteamServerRespone.OtherParam;
        ddyOrderStatusAlterRespone2.ServerType = orderSteamServerRespone.ServerType;
        ddyOrderStatusAlterRespone2.TransportMode = orderSteamServerRespone.TransportMode;
        String str2 = "";
        if (orderSteamServerRespone.ServerType == 1 && orderSteamServerRespone.TransportMode == 0) {
            str = ddyOrderStatusAlterRespone2.HDIPArg;
        } else {
            str = ddyOrderStatusAlterRespone2.SDIPArg;
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.split("\\|").length > 2) {
                str2 = split[0] + ":" + split[1] + "|" + split[2];
            }
        }
        if (TextUtils.isEmpty(str2)) {
            callback.onFail(DdyOrderErrorConstants.DOE_ORDER_SERVERTYPE, "ServerType ==" + orderSteamServerRespone.ServerType + " IPArg ==" + str);
            return;
        }
        ddyOrderStatusAlterRespone2.PullStreamParam = str2;
        callback.onSuccess(ddyOrderStatusAlterRespone2);
    }

    /* renamed from: a */
    public void m21147a(long j, String str, String str2, String str3, final boolean z, final DdyOrderContract.Callback callback) {
        CLog.m21882i("sdk-order", "requestOrderReplace orderId=" + j + ",ucid=" + str);
        new HttpRequestModel().m21127d(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelperEx$3
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestOrderStart wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code == 1) {
                    DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone = (DdyOrderStatusAlterRespone) baseResultWrapper.data;
                    if (!TextUtils.isEmpty(ddyOrderStatusAlterRespone.ChangeDeviceUrl)) {
                        callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_APP_100, ((DdyOrderStatusAlterRespone) baseResultWrapper.data).ChangeDeviceUrl);
                    } else if (TextUtils.isEmpty(ddyOrderStatusAlterRespone.PullStreamTcpUrl) || TextUtils.isEmpty(ddyOrderStatusAlterRespone.DeviceTcpHost)) {
                        callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), "订单信息错误");
                    } else if (!z) {
                        callback.onSuccess(ddyOrderStatusAlterRespone);
                    } else if (ddyOrderStatusAlterRespone.YunDeviceType == 2) {
                        DdyOrderHelperEx.m21149a().m21144a(ddyOrderStatusAlterRespone, (String) null, callback, SdkKeyUtil.getInstance().isEnableD310());
                    } else {
                        ddyOrderStatusAlterRespone.PullStreamParam = ddyOrderStatusAlterRespone.DeviceTcpHost;
                        callback.onSuccess(ddyOrderStatusAlterRespone);
                    }
                } else {
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestOrderStart uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m21142a(String str, int i, boolean z, int i2, final DdyOrderContract.Callback callback) {
        new HttpRequestModel().m21131a(str, i, z, i2, new IUIDataListener() { // from class: com.cyjh.ddysdk.order.DdyOrderHelperEx$4
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-order", "requestEncodeServiceV3Request wrapper == null");
                    callback.onFail(DdyOrderErrorConstants.DOE_NET_WRAPPER_CODE_NULL, "wrapper == null");
                } else if (baseResultWrapper.code == 1) {
                    callback.onSuccess((OrderSteamServerRespone) baseResultWrapper.data);
                } else {
                    callback.onFail(DdyOrderErrorConstants.valueOf(Integer.valueOf(baseResultWrapper.code)), baseResultWrapper.msg);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-order", "requestEncodeServiceV3Request uiDataError=" + exc);
                callback.onFail(DdyOrderErrorConstants.DOE_EXCEPTION, (exc == null || exc.getMessage() == null) ? "" : exc.getMessage());
            }
        });
    }
}
