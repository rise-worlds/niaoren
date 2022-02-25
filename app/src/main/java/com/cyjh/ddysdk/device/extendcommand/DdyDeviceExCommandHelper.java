package com.cyjh.ddysdk.device.extendcommand;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.DefineOrderInfo;

/* loaded from: classes.dex */
public class DdyDeviceExCommandHelper implements NoProGuard, DdyDeviceExCommandContract.IPresenter {

    /* renamed from: b */
    private DefineOrderInfo f7900b;

    /* renamed from: c */
    private String f7901c;

    /* renamed from: a */
    private ControlSocket f7899a = null;

    /* renamed from: d */
    private DdyDeviceExCommandContract.Callback f7902d = null;

    @Override // com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract.IPresenter
    public boolean init(DdyOrderInfo ddyOrderInfo, String str, final DdyDeviceExCommandContract.Callback callback) {
        ControlSocket controlSocket = this.f7899a;
        if (controlSocket != null && controlSocket.m21532b()) {
            callback.onConnected(this);
            return true;
        } else if (!(ddyOrderInfo instanceof DefineOrderInfo)) {
            return false;
        } else {
            this.f7900b = (DefineOrderInfo) ddyOrderInfo;
            this.f7901c = str;
            this.f7899a = new ControlSocket(this.f7900b.DeviceTcpHost, str, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandHelper.1
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket2) {
                    if (commandResponseInfo != null) {
                        CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper onMessage " + commandResponseInfo.command + "," + DdyDeviceExCommandHelper.this.f7900b.OrderId);
                        callback.onMessage(DdyDeviceExCommandHelper.this, commandResponseInfo.command, commandResponseInfo.data, commandResponseInfo.code);
                        return;
                    }
                    callback.onFailure(DdyDeviceExCommandHelper.this, "onMessage responseInfo == null");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket2) {
                    CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper onConnected " + DdyDeviceExCommandHelper.this.f7900b.OrderId);
                    callback.onConnected(DdyDeviceExCommandHelper.this);
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket2) {
                    CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper onSended " + DdyDeviceExCommandHelper.this.f7900b.OrderId);
                    callback.onSended(DdyDeviceExCommandHelper.this);
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket2, String str2) {
                    CLog.m21883e("sdk-device", "DdyDeviceExCommandHelper onFailure " + DdyDeviceExCommandHelper.this.f7900b.OrderId);
                    callback.onFailure(DdyDeviceExCommandHelper.this, str2);
                    DdyDeviceExCommandHelper.this.uninit();
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str2) {
                    CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper onClosed " + DdyDeviceExCommandHelper.this.f7900b.OrderId);
                    callback.onClosed(DdyDeviceExCommandHelper.this, str2);
                    DdyDeviceExCommandHelper.this.uninit();
                }
            });
            return true;
        }
    }

    @Override // com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract.IPresenter
    public boolean sendMsg(String str, String str2) {
        return sendMsg(str, str2, 101);
    }

    @Override // com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract.IPresenter
    public boolean sendMsg(String str, String str2, int i) {
        if (this.f7900b == null) {
            CLog.m21883e("sdk-device", "DdyDeviceExCommandHelper DefineOrderInfo == null" + str + ", " + this.f7900b.OrderId);
            return false;
        }
        CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper sendMsg " + str + ", " + this.f7900b.OrderId);
        if (this.f7899a == null) {
            CLog.m21883e("sdk-device", "DdyDeviceExCommandHelper controlSocket == null" + str + ", " + this.f7900b.OrderId);
            return false;
        } else if (i == 5 || (i >= 100 && i <= 200)) {
            return this.f7899a.m21534a(SocketHelper.m21520a(str, str2, i, this.f7901c));
        } else {
            CLog.m21883e("sdk-device", "DdyDeviceExCommandHelper requestType range error." + str + ", " + this.f7900b.OrderId);
            return false;
        }
    }

    @Override // com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract.IPresenter
    public void uninit() {
        CLog.m21882i("sdk-device", "DdyDeviceExCommandHelper uninit " + this.f7900b.OrderId);
        ControlSocket controlSocket = this.f7899a;
        if (controlSocket != null) {
            controlSocket.m21538a();
            this.f7899a = null;
        }
    }
}
