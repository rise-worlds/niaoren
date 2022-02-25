package com.cyjh.ddysdk.device.command;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.DefineOrderInfo;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DdyDeviceToolHelper implements NoProGuard {

    /* renamed from: a */
    private DeviceToolModule f7745a;

    public static DdyDeviceToolHelper getInstance() {
        return LazyHolder.f7746a;
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final DdyDeviceToolHelper f7746a = new DdyDeviceToolHelper();

        private LazyHolder() {
        }
    }

    private DdyDeviceToolHelper() {
        this.f7745a = new DeviceToolModule();
    }

    public void checkDevice(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7745a.m21235a(defineOrderInfo.DeviceTcpHost, ".normal", defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void logcatDevice(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7745a.m21236a(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void alterDevice(DdyOrderInfo ddyOrderInfo, String str, String str2, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            HashMap hashMap = new HashMap();
            hashMap.put("sys.prop.writeimei", str);
            hashMap.put("phone.oaid", str2);
            this.f7745a.m21234a(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, hashMap, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }
}
