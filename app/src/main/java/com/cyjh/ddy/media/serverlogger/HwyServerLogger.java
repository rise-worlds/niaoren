package com.cyjh.ddy.media.serverlogger;

import android.content.Context;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.IPUtil;
import com.cyjh.ddy.media.bean.OrderReceiptRequestInfo;
import com.cyjh.ddy.media.beaninner.MWYSdkBean;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.order.base.constants.OrderConstants;
import java.util.HashMap;
import java.util.Map;
import p110z1.TypeToken;

/* loaded from: classes.dex */
public class HwyServerLogger {

    /* renamed from: b */
    public static final String f7420b = "yyyyMMddHHmmss";

    /* renamed from: c */
    private Context f7422c;

    /* renamed from: d */
    private ActivityHttpHelper f7423d;

    /* renamed from: a */
    public int f7421a = 30000;

    /* renamed from: e */
    private Map<Integer, ICreateLog> f7424e = new HashMap();

    /* renamed from: f */
    private ICreateLog f7425f = new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.1
        @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
        public String createLog(int i, MWYSdkBean aVar, String str) {
            return "可视化异常，错误码-AND" + AppUtils.m22912m() + str + ", PhoneID:" + aVar.f7225a + "，OrderID:" + aVar.f7231g + "(s:" + aVar.f7227c + ",p:" + aVar.f7228d + "), UserIP:" + IPUtil.m21824b() + ", Apk:" + AppUtils.m22918j() + ">>" + DeviceUtils.m22407g();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ICreateLog {
        String createLog(int i, MWYSdkBean aVar, String str);
    }

    public HwyServerLogger(Context context) {
        this.f7422c = context;
        m21490a();
    }

    /* renamed from: a */
    public void m21489a(int i, MWYSdkBean aVar, String str, String str2) {
        String str3 = "";
        if (this.f7424e.containsKey(Integer.valueOf(i))) {
            str3 = this.f7424e.get(Integer.valueOf(i)).createLog(i, aVar, str2);
        } else if (ActionCode.isMediaRefuseAction(i)) {
            str3 = this.f7425f.createLog(i, aVar, str2);
        }
        if (str3.isEmpty()) {
            CLog.m21882i("HwyServerLogger", "upload > pass, actionCode=" + i);
            return;
        }
        CLog.m21882i("HwyServerLogger", "upload > " + str2);
        m21488a(aVar.f7231g, str, str3);
    }

    /* renamed from: a */
    private void m21490a() {
        this.f7424e.put(Integer.valueOf((int) ActionCode.MediaCloseSuccess), new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.2
            @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
            public String createLog(int i, MWYSdkBean aVar, String str) {
                return "ReleaseFrameINFO-AND" + AppUtils.m22912m() + "【RF】";
            }
        });
        this.f7424e.put(Integer.valueOf((int) ActionCode.MediaFirstFrameSuccess), new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.3
            @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
            public String createLog(int i, MWYSdkBean aVar, String str) {
                return "FirstFrameINFO-AND" + AppUtils.m22912m() + "【FF】, Phone-" + aVar.f7225a + ",[" + str + "(s:" + aVar.f7227c + ",p:" + aVar.f7228d + ")], UserIP:" + IPUtil.m21824b() + ">>" + DeviceUtils.m22407g();
            }
        });
        this.f7424e.put(Integer.valueOf((int) ActionCode.CtrlConnectRefuse_2001), new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.4
            @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
            public String createLog(int i, MWYSdkBean aVar, String str) {
                return "可视化异常，错误码-AND" + AppUtils.m22912m() + str + ", Phone-" + aVar.f7225a + ", UserIP:" + IPUtil.m21824b() + ", Apk:" + AppUtils.m22918j() + ">>" + DeviceUtils.m22407g();
            }
        });
        this.f7424e.put(Integer.valueOf((int) ActionCode.CtrlConnectRefuse_2002), new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.5
            @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
            public String createLog(int i, MWYSdkBean aVar, String str) {
                return "可视化异常，错误码-AND" + AppUtils.m22912m() + str + ", Phone-" + aVar.f7225a + ", UserIP:" + IPUtil.m21824b() + ", Apk:" + AppUtils.m22918j() + ">>" + DeviceUtils.m22407g();
            }
        });
        this.f7424e.put(1000, new ICreateLog() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.6
            @Override // com.cyjh.ddy.media.serverlogger.HwyServerLogger.ICreateLog
            public String createLog(int i, MWYSdkBean aVar, String str) {
                return "";
            }
        });
    }

    /* renamed from: a */
    private void m21488a(long j, String str, String str2) {
        OrderReceiptRequestInfo orderReceiptRequestInfo = new OrderReceiptRequestInfo();
        orderReceiptRequestInfo.ReceiptType = 2;
        orderReceiptRequestInfo.OrderId = j;
        orderReceiptRequestInfo.ErrorCode = 1;
        orderReceiptRequestInfo.UCID = str;
        orderReceiptRequestInfo.ErrorMsg = str2;
        m21487a(orderReceiptRequestInfo);
    }

    /* renamed from: a */
    private void m21487a(OrderReceiptRequestInfo orderReceiptRequestInfo) {
        try {
            if (this.f7423d == null) {
                this.f7423d = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.7
                });
            }
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7423d.m21435a(new IUIDataListener() { // from class: com.cyjh.ddy.media.serverlogger.HwyServerLogger.8
                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataError(Exception exc) {
                }

                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataSuccess(Object obj) {
                }
            });
            this.f7423d.m21431a(new OrderConstants().f8109b, baseHttpReq.toMapPrames(orderReceiptRequestInfo), this.f7421a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
