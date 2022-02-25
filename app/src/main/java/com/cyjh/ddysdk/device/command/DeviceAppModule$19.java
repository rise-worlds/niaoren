package com.cyjh.ddysdk.device.command;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.FileUtils;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.RemoteFileInfo;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeviceAppModule$19 implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f7779a;

    /* renamed from: b */
    final /* synthetic */ String f7780b;

    /* renamed from: c */
    final /* synthetic */ String f7781c;

    /* renamed from: d */
    final /* synthetic */ ObsCert f7782d;

    /* renamed from: e */
    final /* synthetic */ DdyDeviceCommandContract.Callback f7783e;

    /* renamed from: f */
    final /* synthetic */ String f7784f;

    /* renamed from: g */
    final /* synthetic */ DeviceAppModule f7785g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceAppModule$19(DeviceAppModule aVar, String str, String str2, String str3, ObsCert obsCert, DdyDeviceCommandContract.Callback callback, String str4) {
        this.f7785g = aVar;
        this.f7779a = str;
        this.f7780b = str2;
        this.f7781c = str3;
        this.f7782d = obsCert;
        this.f7783e = callback;
        this.f7784f = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        String x = FileUtils.m22173x(this.f7779a);
        final String str = this.f7780b;
        if (TextUtils.m15142a(str)) {
            str = "/sdcard/";
        }
        if (str.endsWith("/")) {
            str = str + x;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sdkpush/");
        sb.append(EncryptUtils.m22365b(this.f7781c + this.f7779a + str));
        sb.append("/");
        sb.append(x);
        final String sb2 = sb.toString();
        if (HWYunManager.m21372b().m21376a(sb2, this.f7779a, (HWYunManager.UploadProgressCallback) null, this.f7782d) != 1) {
            DdyDeviceCommandContract.Callback callback = this.f7783e;
            if (callback != null) {
                callback.failuer(DdyDeviceErrorConstants.DDE_File_Upload_Failue, "obs upload error");
                return;
            }
            return;
        }
        new ControlSocket(this.f7781c, this.f7784f, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$19.1
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestPushFile onConnected");
                controlSocket.m21533a("pushFile", C1123f.m21845a(new RemoteFileInfo(sb2, str, DeviceAppModule$19.this.f7782d)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestPushFile onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestPushFile onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("pushFile")) {
                    if (DeviceAppModule$19.this.f7783e != null) {
                        if (commandResponseInfo.data.equals("success")) {
                            new Thread(new Runnable() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule.19.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    HWYunManager.m21372b().m21379a(sb2, DeviceAppModule$19.this.f7782d, false);
                                }
                            }).start();
                            DeviceAppModule$19.this.f7783e.success(1);
                        } else {
                            DeviceAppModule$19.this.f7783e.failuer(DdyDeviceErrorConstants.DDE_File_Download_Failue, commandResponseInfo.data);
                        }
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str2) {
                CLog.m21882i("sdk-device", "requestPushFile onFailure");
                if (DeviceAppModule$19.this.f7783e != null) {
                    DeviceAppModule$19.this.f7783e.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str2);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str2) {
                CLog.m21882i("sdk-device", "requestPushFile onClosed");
            }
        });
    }
}
