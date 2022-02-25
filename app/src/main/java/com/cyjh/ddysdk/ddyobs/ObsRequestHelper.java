package com.cyjh.ddysdk.ddyobs;

import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.ddyobs.ObsContract;
import com.cyjh.ddysdk.ddyobs.bean.request.DeviceOrderRequest;
import com.cyjh.ddysdk.ddyobs.bean.response.CreatCertResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.DeviceOrderResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FileSyncInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FilesInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.UploadUserResponeInfo;
import com.cyjh.ddysdk.ddyobs.model.ObsRequestModel;
import java.util.List;

/* loaded from: classes.dex */
public class ObsRequestHelper {

    /* renamed from: a */
    private DeviceOrderResponse f7615a;

    private ObsRequestHelper() {
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final ObsRequestHelper f7632a = new ObsRequestHelper();

        private LazyHolder() {
        }
    }

    /* renamed from: a */
    public static ObsRequestHelper m21331a() {
        return LazyHolder.f7632a;
    }

    /* renamed from: a */
    public void m21329a(int i, String str, ObsContract.Callback<CreatCertResponse> callback) {
        m21330a(i, 0, "", str, callback);
    }

    /* renamed from: a */
    public void m21330a(int i, int i2, String str, String str2, final ObsContract.Callback<CreatCertResponse> callback) {
        CLog.m21882i("sdk-obs", "requestObsCreatCert opType=" + i + ",ucid=" + str2);
        new ObsRequestModel().m21308a(i, i2, str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.1
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestObsCreatCert wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsCreatCert onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsFileSync uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m21323a(String str, String str2, final ObsContract.Callback<Integer> callback) {
        CLog.m21882i("sdk-obs", "requestObsDelFile fileName=" + str + ",ucid=" + str2);
        new ObsRequestModel().m21305a(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.2
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestObsDelFile wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsDelFile onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(1);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsDelFile uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: b */
    public void m21321b(String str, String str2, final ObsContract.Callback<List<FilesInfoResponse>> callback) {
        CLog.m21882i("sdk-obs", "requestObsFiles fileName=" + str + ",ucid=" + str2);
        new ObsRequestModel().m21303b(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.3
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestObsFiles wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsFiles onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsFiles uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: c */
    public void m21320c(String str, String str2, final ObsContract.Callback<FileSyncInfoResponse> callback) {
        CLog.m21882i("sdk-obs", "requestObsFileSync fileName=" + str + ",ucid=" + str2);
        new ObsRequestModel().m21302c(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.4
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null || baseResultWrapper.data == 0) {
                    CLog.m21883e("sdk-obs", "requestObsFileSync wrapper == null or wrapper.data == null");
                    callback.onFail(0, "wrapper == null or wrapper.data == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsFileSync onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsFileSync uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m21322a(List<String> list, String str, final ObsContract.Callback<List<FileSyncInfoResponse>> callback) {
        CLog.m21882i("sdk-obs", "requestObsFileSyncInfo ,ucid=" + str);
        new ObsRequestModel().m21304a(list, str, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.5
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestObsFileSyncInfo wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsFileSyncInfo onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsFileSyncInfo uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: a */
    public void m21328a(long j, String str, ObsContract.Callback<List<DeviceOrderResponse>> callback) {
        DeviceOrderRequest deviceOrderRequest = new DeviceOrderRequest();
        deviceOrderRequest.UCID = str;
        deviceOrderRequest.OrderIds = j;
        m21325a(deviceOrderRequest, callback);
    }

    /* renamed from: d */
    public void m21319d(String str, String str2, ObsContract.Callback<List<DeviceOrderResponse>> callback) {
        DeviceOrderRequest deviceOrderRequest = new DeviceOrderRequest();
        deviceOrderRequest.UCID = str2;
        deviceOrderRequest.DeviceCodes = str;
        m21325a(deviceOrderRequest, callback);
    }

    /* renamed from: a */
    public void m21325a(DeviceOrderRequest deviceOrderRequest, final ObsContract.Callback<List<DeviceOrderResponse>> callback) {
        CLog.m21882i("sdk-obs", "requestApiDeviceOrder ,data=" + deviceOrderRequest.toString());
        new ObsRequestModel().m21307a(deviceOrderRequest, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.6
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestApiDeviceOrder wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestApiDeviceOrder onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestApiDeviceOrder uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }

    /* renamed from: e */
    public void m21318e(String str, String str2, final ObsContract.Callback<DeviceOrderResponse> callback) {
        DeviceOrderResponse deviceOrderResponse = this.f7615a;
        if (deviceOrderResponse != null) {
            callback.onSuccess(deviceOrderResponse);
        } else {
            m21319d(str, str2, new ObsContract.Callback<List<DeviceOrderResponse>>() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.7
                public void onSuccess(List<DeviceOrderResponse> list) {
                    if (list != null && !list.isEmpty()) {
                        ObsRequestHelper.this.f7615a = list.get(0);
                    }
                    callback.onSuccess(ObsRequestHelper.this.f7615a);
                }

                @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
                public void onFail(int i, String str3) {
                    callback.onSuccess(null);
                }
            });
        }
    }

    /* renamed from: a */
    public void m21324a(String str, final ObsContract.Callback<UploadUserResponeInfo> callback) {
        CLog.m21882i("sdk-obs", "requestObsPermission ,ucid=" + str);
        new ObsRequestModel().m21306a(str, new IUIDataListener() { // from class: com.cyjh.ddysdk.ddyobs.ObsRequestHelper.8
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null) {
                    CLog.m21883e("sdk-obs", "requestObsPermission wrapper == null");
                    callback.onFail(0, "wrapper == null");
                } else if (baseResultWrapper.code != 1) {
                    CLog.m21883e("sdk-obs", "requestObsPermission onFail  code=" + baseResultWrapper.code);
                    callback.onFail(baseResultWrapper.code, baseResultWrapper.msg);
                } else {
                    callback.onSuccess(baseResultWrapper.data);
                }
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-obs", "requestObsPermission uiDataError ");
                callback.onFail(0, exc == null ? "" : exc.getMessage());
            }
        });
    }
}
