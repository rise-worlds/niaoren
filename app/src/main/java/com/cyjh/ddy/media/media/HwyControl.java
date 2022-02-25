package com.cyjh.ddy.media.media;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.media.bean.socket.ControlRequest;
import com.cyjh.ddy.media.beaninner.XBYUserInfo;
import com.cyjh.ddy.media.media.listener.IHwyControl;
import com.cyjh.ddy.media.media.listener.IHwyControlListener;
import com.cyjh.ddy.media.oksocket.LocalToken;
import com.cyjh.ddy.media.oksocket.MsgDataBean;
import com.cyjh.ddy.media.oksocket.PulseDataBean;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddy.media.p035a.ConstantValue;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.impl.client.PulseManager;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.CustomReconnectManager;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import com.xuhao.didi.socket.client.sdk.client.connection.ReConnectMgr;
import com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter;
import java.nio.charset.Charset;
import org.apache.commons.mail.EmailConstants;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: com.cyjh.ddy.media.media.a */
/* loaded from: classes.dex */
public class HwyControl implements IHwyControl {

    /* renamed from: a */
    public static final String f7279a = "ctrl";

    /* renamed from: b */
    private IHwyControlListener f7280b;

    /* renamed from: c */
    private String f7281c;

    /* renamed from: d */
    private XBYUserInfo f7282d;

    /* renamed from: e */
    private String f7283e;

    /* renamed from: g */
    private OkSocketOptions f7285g;

    /* renamed from: h */
    private IConnectionManager f7286h;

    /* renamed from: j */
    private PulseDataBean f7288j;

    /* renamed from: f */
    private boolean f7284f = false;

    /* renamed from: i */
    private boolean f7287i = true;

    /* renamed from: k */
    private SocketActionAdapter f7289k = new SocketActionAdapter() { // from class: com.cyjh.ddy.media.media.HwyControl$1
        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
            String str2;
            IHwyControlListener cVar;
            IConnectionManager bVar;
            boolean z;
            IConnectionManager bVar2;
            SocketActionAdapter cVar2;
            String str3;
            IHwyControlListener cVar3;
            super.onSocketDisconnection(aVar, str, exc);
            if (exc != null) {
                StringBuilder sb = new StringBuilder();
                str3 = HwyControl.this.f7283e;
                sb.append(str3);
                sb.append(" onSocketDisconnection: ");
                sb.append(exc.getMessage());
                String sb2 = sb.toString();
                CLog.m21883e(HwyControl.f7279a, sb2);
                if (ReConnectMgr.m14986j().m14994b()) {
                    ReConnectMgr.m14986j().m14992d();
                    return;
                }
                cVar3 = HwyControl.this.f7280b;
                cVar3.controlConnectError(sb2);
                return;
            }
            StringBuilder sb3 = new StringBuilder();
            str2 = HwyControl.this.f7283e;
            sb3.append(str2);
            sb3.append(" onSocketDisconnection: 正常断开 ");
            CLog.m21882i(HwyControl.f7279a, sb3.toString());
            cVar = HwyControl.this.f7280b;
            cVar.controlCloseSuccess();
            bVar = HwyControl.this.f7286h;
            if (bVar != null) {
                z = HwyControl.this.f7284f;
                if (z) {
                    bVar2 = HwyControl.this.f7286h;
                    cVar2 = HwyControl.this.f7289k;
                    bVar2.mo15089a((IConnectionManager) cVar2);
                }
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
            String str2;
            IConnectionManager bVar;
            PulseDataBean cVar;
            String str3;
            IHwyControlListener cVar2;
            super.onSocketConnectionSuccess(aVar, str);
            StringBuilder sb = new StringBuilder();
            str2 = HwyControl.this.f7283e;
            sb.append(str2);
            sb.append(" onSocketConnectionSuccess");
            CLog.m21882i(HwyControl.f7279a, sb.toString());
            bVar = HwyControl.this.f7286h;
            PulseManager h = bVar.mo14968h();
            cVar = HwyControl.this.f7288j;
            h.m15110a(cVar).mo15011b();
            ControlRequest controlRequest = new ControlRequest();
            controlRequest.type = 1;
            str3 = HwyControl.this.f7281c;
            HwyControl.this.m21682c(SocketHelper.m21524a(controlRequest, str3));
            cVar2 = HwyControl.this.f7280b;
            cVar2.controlConnectSuccess();
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
            String str2;
            IHwyControlListener cVar;
            IConnectionManager bVar;
            IConnectionManager bVar2;
            SocketActionAdapter cVar2;
            super.onSocketConnectionFailed(aVar, str, exc);
            StringBuilder sb = new StringBuilder();
            str2 = HwyControl.this.f7283e;
            sb.append(str2);
            sb.append(" onSocketConnectionFailed");
            CLog.m21882i(HwyControl.f7279a, sb.toString());
            if (!ReConnectMgr.m14986j().m14987i()) {
                cVar = HwyControl.this.f7280b;
                cVar.controlConnectError("onSocketConnectionFailed: " + exc.getMessage());
                bVar = HwyControl.this.f7286h;
                if (bVar != null) {
                    bVar2 = HwyControl.this.f7286h;
                    cVar2 = HwyControl.this.f7289k;
                    bVar2.mo15089a((IConnectionManager) cVar2);
                }
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketReadResponse(ConnectionInfo aVar, String str, OriginalData aVar2) {
            IHwyControlListener cVar;
            String str2 = new String(aVar2.m15180b(), Charset.forName(EmailConstants.UTF_8));
            cVar = HwyControl.this.f7280b;
            cVar.controlResponse(str2);
        }
    };

    public HwyControl(IHwyControlListener cVar, String str) {
        this.f7280b = cVar;
        this.f7281c = str;
        this.f7288j = new PulseDataBean(str);
    }

    /* renamed from: d */
    private void m21681d() {
        this.f7285g = new OkSocketOptions.Builder().setReconnectionManager(ReConnectMgr.m14986j().m14994b() ? new CustomReconnectManager() : new NoneReconnect()).setConnectTimeoutSecond(5).setMaxReadDataMB(10).setReadPackageBytes(ConstantValue.f7164c).setWritePackageBytes(ConstantValue.f7164c).setCallbackThreadModeToken(new LocalToken()).setPulseFrequency(WaitFor.ONE_MINUTE).setPulseFeedLoseTimes(-1).build();
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21671a() {
        this.f7284f = true;
        IConnectionManager bVar = this.f7286h;
        if (bVar != null) {
            bVar.mo15129d();
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21668a(String str) {
        CLog.m21882i(f7279a, "control ctrlAddress=" + str);
        this.f7283e = str;
        String a = SocketHelper.m21521a(str);
        int b = SocketHelper.m21518b(str);
        m21681d();
        this.f7286h = OkSocket.m15066a(a, b).mo14978a(this.f7285g);
        this.f7286h.mo15084b((IConnectionManager) this.f7289k);
        this.f7284f = false;
        this.f7286h.mo14976c();
    }

    @Override // com.cyjh.ddy.media.media.listener.OnTouchEventListener
    /* renamed from: b */
    public void mo21647b(String str) {
        if (this.f7287i) {
            m21682c(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m21682c(String str) {
        if (!this.f7284f) {
            if (TextUtils.isEmpty(str)) {
                CLog.m21883e(f7279a, "send is null");
                return;
            }
            IConnectionManager bVar = this.f7286h;
            if (bVar != null && bVar.mo14970f()) {
                this.f7286h.mo15133b((ISendable) new MsgDataBean(str));
            }
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.OnKeyEventListener
    /* renamed from: a */
    public void mo21649a(int i) {
        String str = "";
        if (i == 24) {
            str = "input keyevent KEYCODE_VOLUME_UP";
        } else if (i == 25) {
            str = "input keyevent KEYCODE_VOLUME_DOWN";
        } else if (i == 187) {
            str = "input keyevent KEYCODE_APP_SWITCH";
        } else if (i == 3) {
            str = "input keyevent KEYCODE_HOME";
        } else if (i == 4) {
            str = "input keyevent KEYCODE_BACK";
        } else if (i == 19) {
            str = "input keyevent KEYCODE_DPAD_UP";
        } else if (i == 20) {
            str = "input keyevent KEYCODE_DPAD_DOWN";
        } else if (i == 21) {
            str = "input keyevent KEYCODE_DPAD_LEFT";
        } else if (i == 22) {
            str = "input keyevent KEYCODE_DPAD_RIGHT";
        } else if (i == 23) {
            str = "input keyevent KEYCODE_DPAD_CENTER";
        } else if (i == 82) {
            str = "input keyevent KEYCODE_MENU";
        }
        if (!str.isEmpty()) {
            mo21667a("log", str);
            return;
        }
        CLog.m21882i(f7279a, "onKeyEvent:  unknow " + i);
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21670a(long j) {
        XBYUserInfo xBYUserInfo = this.f7282d;
        if (xBYUserInfo != null) {
            xBYUserInfo.OrderId = j;
            mo21667a("userInfo", JsonUtil.m21804a(xBYUserInfo));
        }
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21669a(XBYUserInfo xBYUserInfo) {
        this.f7282d = xBYUserInfo;
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: b */
    public XBYUserInfo mo21665b() {
        return this.f7282d;
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21667a(String str, String str2) {
        m21682c(SocketHelper.m21519a(str, str2, this.f7281c));
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: a */
    public void mo21666a(boolean z) {
        this.f7287i = z;
    }

    @Override // com.cyjh.ddy.media.media.listener.IHwyControl
    /* renamed from: c */
    public boolean mo21664c() {
        return this.f7287i;
    }
}
