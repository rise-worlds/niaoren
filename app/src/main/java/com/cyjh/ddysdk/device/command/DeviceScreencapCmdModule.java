package com.cyjh.ddysdk.device.command;

import android.util.Base64;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;

/* loaded from: classes.dex */
class DeviceScreencapCmdModule implements DdyDeviceCommandContract.ScreenCap.Imodel {

    /* renamed from: a */
    private ControlSocket f7876a = null;

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.ScreenCap.Imodel
    public void requestScreenCap(String str, final long j, final long j2, String str2, final DdyDeviceCommandContract.Callback<byte[]> callback) {
        if (m21270b()) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Screencap_TOO_BUSY, "prev request still running.");
        } else {
            this.f7876a = new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceScreencapCmdModule.1
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    String format = String.format("%dx%d", Long.valueOf(j), Long.valueOf(j2));
                    CLog.m21882i("sdk-device", "requestScreenCap onConnected");
                    controlSocket.m21533a("shotPC", format);
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                    CLog.m21882i("sdk-device", "requestScreenCap onSended");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    CLog.m21882i("sdk-device", "requestScreenCap onMessage " + commandResponseInfo.command);
                    if (commandResponseInfo.command.equals("shotPC")) {
                        byte[] decode = Base64.decode(commandResponseInfo.data, 2);
                        DdyDeviceCommandContract.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.success(decode);
                        }
                        DeviceScreencapCmdModule.this.m21272a();
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str3) {
                    CLog.m21883e("sdk-device", "requestScreenCap onFailure " + str3);
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.failuer(DdyDeviceErrorConstants.DDE_Screencap_Net_Failure, str3);
                    }
                    DeviceScreencapCmdModule.this.m21272a();
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str3) {
                    CLog.m21882i("sdk-device", "requestScreenCap onClosed");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21272a() {
        ControlSocket controlSocket = this.f7876a;
        if (controlSocket != null) {
            controlSocket.m21538a();
            this.f7876a = null;
        }
    }

    /* renamed from: b */
    private boolean m21270b() {
        return this.f7876a != null;
    }
}
