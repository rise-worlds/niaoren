package com.cyjh.ddysdk.order.base.model;

import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.Utils;
import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.order.base.bean.DdyOrderStatusAlterRespone;
import com.cyjh.ddysdk.order.base.bean.OrderDetailReqInfo;
import com.cyjh.ddysdk.order.base.bean.OrderInfoRespone;
import com.cyjh.ddysdk.order.base.bean.OrderRequestStartInfo;
import com.cyjh.ddysdk.order.base.bean.OrderStatuAlterRequest;
import com.cyjh.ddysdk.order.base.bean.OrderSteamServerRequestV3;
import com.cyjh.ddysdk.order.base.bean.OrderSteamServerRespone;
import com.cyjh.ddysdk.order.base.bean.OrderlistRequestInfo;
import com.cyjh.ddysdk.order.base.bean.OrderlistResponeInfo;
import com.cyjh.ddysdk.order.base.bean.SdkLoginRequest;
import com.cyjh.ddysdk.order.base.bean.SdkLoginRespone;
import com.cyjh.ddysdk.order.base.constants.HttpStreamServerConstans;
import com.cyjh.ddysdk.order.base.constants.OrderConstants;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddysdk.order.base.model.a */
/* loaded from: classes.dex */
public class HttpRequestModel {

    /* renamed from: a */
    private ActivityHttpHelper f8132a;

    /* renamed from: b */
    private ActivityHttpHelper f8133b;

    /* renamed from: c */
    private ActivityHttpHelper f8134c;

    /* renamed from: d */
    private ActivityHttpHelper f8135d;

    /* renamed from: e */
    private ActivityHttpHelper f8136e;

    /* renamed from: f */
    private ActivityHttpHelper f8137f;

    /* renamed from: g */
    private ActivityHttpHelper f8138g;

    /* renamed from: h */
    private ActivityHttpHelper f8139h;

    /* renamed from: i */
    private ActivityHttpHelper f8140i;

    /* renamed from: j */
    private ActivityHttpHelper f8141j;

    /* renamed from: k */
    private ActivityHttpHelper f8142k;

    /* renamed from: a */
    public void m21135a(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8132a == null) {
                this.f8132a = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<OrderInfoRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$1
                });
            }
            OrderDetailReqInfo orderDetailReqInfo = new OrderDetailReqInfo();
            orderDetailReqInfo.UCID = str;
            orderDetailReqInfo.OrderId = j;
            orderDetailReqInfo.DeviceToken = str2;
            orderDetailReqInfo.UserToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8132a.m21435a(bVar);
            this.f8132a.m21431a(new OrderConstants().f8110c, baseHttpReq.toMapPrames(orderDetailReqInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21134a(long j, String str, String str2, String str3, IUIDataListener bVar, long j2) {
        try {
            if (this.f8133b == null) {
                this.f8133b = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$2
                });
            }
            OrderDetailReqInfo orderDetailReqInfo = new OrderDetailReqInfo();
            orderDetailReqInfo.UCID = str;
            orderDetailReqInfo.OrderId = j;
            orderDetailReqInfo.RoComCphNonRoot = j2;
            orderDetailReqInfo.DeviceToken = str2;
            orderDetailReqInfo.UserToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8133b.m21435a(bVar);
            this.f8133b.m21431a(new OrderConstants().f8111d, baseHttpReq.toMapPrames(orderDetailReqInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m21129b(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8134c == null) {
                this.f8134c = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$3
                });
            }
            OrderDetailReqInfo orderDetailReqInfo = new OrderDetailReqInfo();
            orderDetailReqInfo.UCID = str;
            orderDetailReqInfo.OrderId = j;
            orderDetailReqInfo.DeviceToken = str2;
            orderDetailReqInfo.UserToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8134c.m21435a(bVar);
            this.f8134c.m21431a(new OrderConstants().f8112e, baseHttpReq.toMapPrames(orderDetailReqInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public void m21128c(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8135d == null) {
                this.f8135d = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<DdyOrderStatusAlterRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$4
                });
            }
            OrderStatuAlterRequest orderStatuAlterRequest = new OrderStatuAlterRequest();
            orderStatuAlterRequest.UCID = str;
            orderStatuAlterRequest.OrderId = j;
            orderStatuAlterRequest.UserToken = str2;
            orderStatuAlterRequest.DeviceToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8135d.m21435a(bVar);
            this.f8135d.m21431a(new OrderConstants().f8113f, baseHttpReq.toMapPrames(orderStatuAlterRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public void m21127d(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8135d == null) {
                this.f8135d = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<DdyOrderStatusAlterRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$5
                });
            }
            OrderStatuAlterRequest orderStatuAlterRequest = new OrderStatuAlterRequest();
            orderStatuAlterRequest.UCID = str;
            orderStatuAlterRequest.OrderId = j;
            orderStatuAlterRequest.UserToken = str3;
            orderStatuAlterRequest.DeviceToken = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8135d.m21435a(bVar);
            this.f8135d.m21431a(new OrderConstants().f8114g, baseHttpReq.toMapPrames(orderStatuAlterRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public void m21126e(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8136e == null) {
                this.f8136e = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<DdyOrderStatusAlterRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$6
                });
            }
            OrderStatuAlterRequest orderStatuAlterRequest = new OrderStatuAlterRequest();
            orderStatuAlterRequest.UCID = str;
            orderStatuAlterRequest.OrderId = j;
            orderStatuAlterRequest.UserToken = str3;
            orderStatuAlterRequest.DeviceToken = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8136e.m21435a(bVar);
            this.f8136e.m21431a(new OrderConstants().f8115h, baseHttpReq.toMapPrames(orderStatuAlterRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21132a(IUIDataListener bVar, DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone, String str, boolean z) {
        if (ddyOrderStatusAlterRespone.YunDeviceType != 2) {
            bVar.uiDataError(new Exception("OrderInfoRespone YunDeviceType!=2"));
            return;
        }
        try {
            if (this.f8137f == null) {
                this.f8137f = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<OrderSteamServerRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$7
                });
            }
            OrderRequestStartInfo orderRequestStartInfo = new OrderRequestStartInfo();
            orderRequestStartInfo.OrderId = ddyOrderStatusAlterRespone.OrderId;
            orderRequestStartInfo.NodeId = ddyOrderStatusAlterRespone.NodeId;
            orderRequestStartInfo.UseH265 = Utils.m21732a();
            orderRequestStartInfo.RoomKey = str;
            CLog.m21882i("sdk-order", "sendStartAnboxRequest isSupportH265=" + orderRequestStartInfo.UseH265 + ",IsMultiVideo=" + orderRequestStartInfo.RoomKey);
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8137f.m21435a(bVar);
            this.f8137f.m21431a(new HttpStreamServerConstans(z).f8104d, baseHttpReq.toMapPrames(orderRequestStartInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21131a(String str, int i, boolean z, int i2, IUIDataListener bVar) {
        try {
            if (this.f8138g == null) {
                this.f8138g = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<OrderSteamServerRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$8
                });
            }
            OrderSteamServerRequestV3 orderSteamServerRequestV3 = new OrderSteamServerRequestV3();
            orderSteamServerRequestV3.UseH265 = !z && Utils.m21732a();
            orderSteamServerRequestV3.RoomKey = str;
            orderSteamServerRequestV3.IsHttps = i;
            orderSteamServerRequestV3.IsInternalCoding = z;
            orderSteamServerRequestV3.AreaNo = i2;
            CLog.m21882i("sdk-order", "sendEncodeServiceV3Request isSupportH265=" + orderSteamServerRequestV3.UseH265 + ",IsMultiVideo=" + orderSteamServerRequestV3.RoomKey);
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8138g.m21435a(bVar);
            ActivityHttpHelper aVar = this.f8138g;
            new HttpStreamServerConstans().getClass();
            aVar.m21431a("http://121.37.208.5:8080/api/v3/EncodeService", baseHttpReq.toMapPrames(orderSteamServerRequestV3), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    public void m21125f(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8139h == null) {
                this.f8139h = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$9
                });
            }
            OrderStatuAlterRequest orderStatuAlterRequest = new OrderStatuAlterRequest();
            orderStatuAlterRequest.UCID = str;
            orderStatuAlterRequest.OrderId = j;
            orderStatuAlterRequest.UserToken = str3;
            orderStatuAlterRequest.DeviceToken = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8139h.m21435a(bVar);
            this.f8139h.m21431a(new OrderConstants().f8116i, baseHttpReq.toMapPrames(orderStatuAlterRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    public void m21124g(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8140i == null) {
                this.f8140i = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$10
                });
            }
            OrderDetailReqInfo orderDetailReqInfo = new OrderDetailReqInfo();
            orderDetailReqInfo.UCID = str;
            orderDetailReqInfo.OrderId = j;
            orderDetailReqInfo.UserToken = str3;
            orderDetailReqInfo.DeviceToken = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8140i.m21435a(bVar);
            this.f8140i.m21431a(new OrderConstants().f8117j, baseHttpReq.toMapPrames(orderDetailReqInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21130a(String str, IUIDataListener bVar) {
        try {
            if (this.f8141j == null) {
                this.f8141j = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<SdkLoginRespone>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$11
                });
            }
            SdkLoginRequest sdkLoginRequest = new SdkLoginRequest();
            sdkLoginRequest.OpenId = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8141j.m21435a(bVar);
            this.f8141j.m21431a(new OrderConstants().f8118k, baseHttpReq.toMapPrames(sdkLoginRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21133a(IUIDataListener bVar, int i, long j, String str) {
        try {
            if (this.f8142k == null) {
                this.f8142k = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<OrderlistResponeInfo>>() { // from class: com.cyjh.ddysdk.order.base.model.HttpRequestModel$12
                });
            }
            OrderlistRequestInfo orderlistRequestInfo = new OrderlistRequestInfo();
            orderlistRequestInfo.OrderStatus = i;
            orderlistRequestInfo.lastOrderId = j;
            orderlistRequestInfo.UCID = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8142k.m21435a(bVar);
            this.f8142k.m21431a(new OrderConstants().f8119m, baseHttpReq.toMapPrames(orderlistRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
