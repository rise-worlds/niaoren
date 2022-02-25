package com.cyjh.ddysdk.device.command;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeviceScreencapCmdPresenter implements DdyDeviceCommandContract.ScreenCap.IPresenter {

    /* renamed from: c */
    private static Map<Long, Long> f7881c = new HashMap();

    /* renamed from: a */
    private DdyDeviceCommandContract.ScreenCap.Imodel f7882a = new DeviceScreencapCmdModule();

    /* renamed from: b */
    private DdyDeviceCommandContract.ScreenCap.IView f7883b;

    public DeviceScreencapCmdPresenter(DdyDeviceCommandContract.ScreenCap.IView iView) {
        this.f7883b = null;
        this.f7883b = iView;
    }

    public void changleView(DdyDeviceCommandContract.ScreenCap.IView iView) {
        this.f7883b = iView;
    }

    /* JADX WARN: Type inference failed for: r3v7, types: [com.cyjh.ddysdk.device.command.DeviceScreencapCmdPresenter$1] */
    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.ScreenCap.IPresenter
    public void screenCap(long j, String str, long j2, long j3, String str2) {
        if (TextUtils.isEmpty(str)) {
            m21269a(j, DdyDeviceErrorConstants.DDE_Screencap_Param_TCP_HOST, "");
        } else if (j2 < 10 || j3 < 10) {
            m21269a(j, DdyDeviceErrorConstants.DDE_Screencap_Param_WH, "");
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (!f7881c.containsKey(Long.valueOf(j)) || currentTimeMillis - f7881c.get(Long.valueOf(j)).longValue() >= 2000) {
                f7881c.put(Long.valueOf(j), Long.valueOf(currentTimeMillis));
                CLog.m21882i("sdk-device", "screenCap " + j);
                this.f7882a.requestScreenCap(str, j2, j3, str2, new DdyDeviceCommandContract.Callback<byte[]>() { // from class: com.cyjh.ddysdk.device.command.DeviceScreencapCmdPresenter.1

                    /* renamed from: b */
                    private long f7885b;

                    public void success(byte[] bArr) {
                        DeviceScreencapCmdPresenter.this.f7883b.updateScreenCap(this.f7885b, bArr);
                        CLog.m21882i("sdk-device", "screenCap success " + this.f7885b);
                    }

                    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                    public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str3) {
                        DeviceScreencapCmdPresenter.this.m21269a(this.f7885b, ddyDeviceErrorConstants, str3);
                    }

                    public DdyDeviceCommandContract.Callback setOrderId(long j4) {
                        this.f7885b = j4;
                        return this;
                    }
                }.setOrderId(j));
                return;
            }
            m21269a(j, DdyDeviceErrorConstants.DDE_Screencap_TOO_BUSY, "request less 2 seconds time.");
        }
    }

    /* renamed from: a */
    void m21269a(long j, DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
        this.f7883b.updateScreenCapFailure(j, ddyDeviceErrorConstants, str);
        CLog.m21883e("sdk-device", "screenCap failuer " + j + ",errcode " + ddyDeviceErrorConstants + ", msg " + str);
    }
}
