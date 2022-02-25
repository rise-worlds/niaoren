package com.cyjh.ddy.media.oksocket;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.bean.socket.BaseSocketResponse;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.media.p035a.ConstantValue;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter;
import java.nio.charset.Charset;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.TypeToken;

/* loaded from: classes.dex */
public class ControlSocket {

    /* renamed from: a */
    private String f7382a;

    /* renamed from: b */
    private int f7383b;

    /* renamed from: c */
    private boolean f7384c;

    /* renamed from: d */
    private String f7385d;

    /* renamed from: e */
    private boolean f7386e;

    /* renamed from: f */
    private String f7387f;

    /* renamed from: g */
    private IConnectionManager f7388g;

    /* renamed from: h */
    private OkSocketOptions f7389h;

    /* renamed from: i */
    private SocketActionAdapter f7390i;

    /* renamed from: j */
    private IControlSocketListener.IOnMessageListener f7391j;

    public ControlSocket(String str, String str2, IControlSocketListener.IOnMessageListener iOnMessageListener) {
        this(str, 0, str2, iOnMessageListener);
    }

    public ControlSocket(String str, int i, String str2, IControlSocketListener.IOnMessageListener iOnMessageListener) {
        this.f7384c = false;
        this.f7390i = new SocketActionAdapter() { // from class: com.cyjh.ddy.media.oksocket.ControlSocket.1
            @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
            public void onSocketDisconnection(ConnectionInfo aVar, String str3, Exception exc) {
                if (exc != null) {
                    CLog.m21882i(ControlSocket.class.getSimpleName(), "onSocketDisconnection: " + exc.getMessage());
                    if (ControlSocket.this.f7391j != null) {
                        String str4 = ControlSocket.this.f7382a + " onSocketDisconnection: " + exc.getMessage();
                        if (ControlSocket.this.f7386e) {
                            ControlSocket.this.f7391j.onClosed(str4);
                        } else {
                            ControlSocket.this.f7391j.onFailure(ControlSocket.this, str4);
                        }
                    }
                    if (ControlSocket.this.f7388g != null) {
                        ControlSocket.this.f7388g.mo15089a((IConnectionManager) ControlSocket.this.f7390i);
                        return;
                    }
                    return;
                }
                CLog.m21882i(ControlSocket.class.getSimpleName(), "onSocketDisconnection: 正常断开");
                if (ControlSocket.this.f7391j != null) {
                    ControlSocket.this.f7391j.onClosed("正常断开");
                }
                if (ControlSocket.this.f7388g != null) {
                    ControlSocket.this.f7388g.mo15089a((IConnectionManager) ControlSocket.this.f7390i);
                }
            }

            @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
            public void onSocketConnectionSuccess(ConnectionInfo aVar, String str3) {
                if (ControlSocket.this.f7391j != null) {
                    ControlSocket.this.f7391j.onConnected(ControlSocket.this);
                }
            }

            @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
            public void onSocketConnectionFailed(ConnectionInfo aVar, String str3, Exception exc) {
                String str4 = "";
                if (exc != null) {
                    str4 = exc.getMessage();
                }
                String simpleName = ControlSocket.class.getSimpleName();
                CLog.m21883e(simpleName, ControlSocket.this.f7382a + " onSocketConnectionFailed:  " + str4);
                if (ControlSocket.this.f7391j != null) {
                    IControlSocketListener.IOnMessageListener iOnMessageListener2 = ControlSocket.this.f7391j;
                    ControlSocket controlSocket = ControlSocket.this;
                    iOnMessageListener2.onFailure(controlSocket, "onSocketConnectionFailed: " + str4);
                }
                if (ControlSocket.this.f7388g != null) {
                    ControlSocket.this.f7388g.mo15089a((IConnectionManager) ControlSocket.this.f7390i);
                }
            }

            @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
            public void onSocketReadResponse(ConnectionInfo aVar, String str3, OriginalData aVar2) {
                String str4 = new String(aVar2.m15180b(), Charset.forName(EmailConstants.UTF_8));
                String simpleName = ControlSocket.class.getSimpleName();
                CLog.m21882i(simpleName, ControlSocket.this.f7382a + " onSocketReadResponse text:" + str4 + "     ,onMessageListener=" + ControlSocket.this.f7391j.toString());
                if (ControlSocket.this.f7391j != null) {
                    if (ControlSocket.this.f7391j instanceof IControlSocketListener.OnTextMessageListener) {
                        ((IControlSocketListener.OnTextMessageListener) ControlSocket.this.f7391j).onMessage(str4);
                    } else if (ControlSocket.this.f7391j instanceof IControlSocketListener.OnCommandResponseInfoMessageListener) {
                        try {
                            int i2 = new JSONObject(str4).getInt("code");
                            if (i2 != 1 && (i2 <= 100 || i2 >= 200)) {
                                if (i2 == 0) {
                                    String simpleName2 = ControlSocket.class.getSimpleName();
                                    CLog.m21883e(simpleName2, "onSocketReadResponse error:" + str4);
                                    ControlSocket.this.f7391j.onFailure(ControlSocket.this, str4);
                                }
                            }
                            BaseSocketResponse baseSocketResponse = (BaseSocketResponse) JsonUtil.m21802a(str4, new TypeToken<BaseSocketResponse<CommandResponseInfo>>() { // from class: com.cyjh.ddy.media.oksocket.ControlSocket.1.1
                            }.getType());
                            if (!(baseSocketResponse == null || baseSocketResponse.data == 0)) {
                                CommandResponseInfo commandResponseInfo = (CommandResponseInfo) baseSocketResponse.data;
                                String simpleName3 = ControlSocket.class.getSimpleName();
                                CLog.m21882i(simpleName3, ControlSocket.this.f7382a + " onSocketReadResponse 32 cmd:" + commandResponseInfo.command + ", data:" + commandResponseInfo.data);
                                ((IControlSocketListener.OnCommandResponseInfoMessageListener) ControlSocket.this.f7391j).onMessage(commandResponseInfo, ControlSocket.this);
                            }
                        } catch (JSONException e) {
                            String simpleName4 = ControlSocket.class.getSimpleName();
                            CLog.m21883e(simpleName4, "onSocketReadResponse json error:" + e.getMessage());
                        }
                    }
                }
            }

            @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
            public void onSocketWriteResponse(ConnectionInfo aVar, String str3, ISendable eVar) {
                if (ControlSocket.this.f7391j != null) {
                    ControlSocket.this.f7391j.onSended(ControlSocket.this);
                }
            }
        };
        this.f7391j = iOnMessageListener;
        if (TextUtils.isEmpty(str) || !str.contains(":")) {
            String simpleName = ControlSocket.class.getSimpleName();
            CLog.m21883e(simpleName, "ControlSocket: wsUrl illegal wsUrl = " + str);
            if (iOnMessageListener != null) {
                iOnMessageListener.onFailure(this, "Control ip illegal");
                return;
            }
            return;
        }
        this.f7382a = SocketHelper.m21521a(str);
        this.f7383b = SocketHelper.m21518b(str);
        this.f7387f = str2;
        m21530c();
        if (i != 0) {
            m21537a(i);
        }
    }

    /* renamed from: c */
    private void m21530c() {
        this.f7386e = false;
        m21528d();
        this.f7388g.mo14976c();
    }

    /* renamed from: d */
    private void m21528d() {
        ConnectionInfo aVar = new ConnectionInfo(this.f7382a, this.f7383b);
        this.f7389h = new OkSocketOptions.Builder().setReconnectionManager(new NoneReconnect()).setConnectTimeoutSecond(5).setMaxReadDataMB(10).setReadPackageBytes(ConstantValue.f7164c).setWritePackageBytes(ConstantValue.f7164c).setCallbackThreadModeToken(new LocalToken()).build();
        this.f7388g = OkSocket.m15068a(aVar).mo14978a(this.f7389h);
        this.f7388g.mo15084b((IConnectionManager) this.f7390i);
    }

    /* renamed from: a */
    public boolean m21533a(String str, String str2) {
        IConnectionManager bVar = this.f7388g;
        if (bVar == null || !bVar.mo14970f()) {
            CLog.m21883e(ControlSocket.class.getSimpleName(), "sendMsg -- mWebSocket==null");
            return false;
        }
        String str3 = null;
        if (this.f7391j instanceof IControlSocketListener.OnCommandResponseInfoMessageListener) {
            str3 = SocketHelper.m21519a(str, str2, this.f7387f);
        }
        return m21534a(str3);
    }

    /* renamed from: a */
    public <T> boolean m21535a(T t, Class<T> cls) {
        if (!cls.equals(t.getClass())) {
            return false;
        }
        IConnectionManager bVar = this.f7388g;
        if (bVar == null || !bVar.mo14970f()) {
            CLog.m21883e(ControlSocket.class.getSimpleName(), "sendMsg -- mWebSocket==null");
            return false;
        }
        String str = null;
        if (this.f7391j instanceof IControlSocketListener.OnCommandResponseInfoMessageListener) {
            str = SocketHelper.m21522a(t, this.f7387f);
        }
        return m21534a(str);
    }

    /* renamed from: a */
    public boolean m21534a(String str) {
        IConnectionManager bVar = this.f7388g;
        if (bVar == null || !bVar.mo14970f()) {
            CLog.m21883e(ControlSocket.class.getSimpleName(), "sendMsg -- mWebSocket==null");
            return false;
        } else if (TextUtils.isEmpty(str)) {
            String simpleName = ControlSocket.class.getSimpleName();
            CLog.m21883e(simpleName, this.f7382a + " sendMsg -- cmd is not json string:");
            return false;
        } else {
            this.f7388g.mo15133b((ISendable) new MsgDataBean(str));
            return true;
        }
    }

    /* renamed from: a */
    public void m21538a() {
        this.f7386e = true;
        IConnectionManager bVar = this.f7388g;
        if (bVar != null) {
            bVar.mo15129d();
            this.f7388g = null;
        }
    }

    /* renamed from: b */
    public boolean m21532b() {
        IConnectionManager bVar = this.f7388g;
        if (bVar != null) {
            return bVar.mo14970f();
        }
        return false;
    }

    /* renamed from: a */
    private void m21537a(int i) {
        if (i != 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new AutoDisConnectRunnable(), i * 1000);
        }
    }

    /* loaded from: classes.dex */
    public class AutoDisConnectRunnable implements Runnable {
        public AutoDisConnectRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ControlSocket controlSocket = ControlSocket.this;
            if (controlSocket != null && controlSocket.m21532b()) {
                String simpleName = ControlSocket.class.getSimpleName();
                CLog.m21882i(simpleName, ControlSocket.this.f7382a + "AutoDisConnectRunnable to close");
                ControlSocket.this.m21538a();
            }
        }
    }
}
