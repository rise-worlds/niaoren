package com.cyjh.ddy.media.service;

import android.text.TextUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.base.utils.WSUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.ddy.media.media.listener.IHwyMediaListener;
import com.cyjh.ddy.media.media.listener.IMediaServiceListener;
import com.cyjh.ddy.media.media.qemu.HwyMedia;
import com.cyjh.ddy.media.oksocket.LocalToken;
import com.cyjh.ddy.media.oksocket.MsgDataBean;
import com.cyjh.ddy.media.p035a.ConstantValue;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.CustomReconnectManager;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import com.xuhao.didi.socket.client.sdk.client.connection.ReConnectMgr;
import com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p110z1.C3894au;
import p110z1.Consts;

/* loaded from: classes.dex */
public class MediaWrap implements IMediaWrap {
    public static final String TAG = "MediaService";

    /* renamed from: b */
    private OkSocketOptions f7437b;

    /* renamed from: c */
    private IConnectionManager f7438c;

    /* renamed from: g */
    private boolean f7442g;

    /* renamed from: h */
    private boolean f7443h;

    /* renamed from: i */
    private byte[] f7444i;

    /* renamed from: j */
    private IHwyMediaListener f7445j;

    /* renamed from: l */
    private IMediaServiceListener f7447l;

    /* renamed from: m */
    private String f7448m;

    /* renamed from: d */
    private Map<String, String> f7439d = new HashMap();

    /* renamed from: e */
    private boolean f7440e = false;

    /* renamed from: f */
    private boolean f7441f = false;

    /* renamed from: a */
    SocketActionAdapter f7436a = new SocketActionAdapter() { // from class: com.cyjh.ddy.media.service.MediaWrap.1

        /* renamed from: b */
        private boolean f7450b = false;

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
            if (exc != null) {
                String str2 = MediaWrap.this.f7446k + " onSocketDisconnection: " + exc.getMessage();
                CLog.m21883e(MediaWrap.TAG, str2);
                if (ReConnectMgr.m14986j().m14994b()) {
                    ReConnectMgr.m14986j().m14992d();
                    if (ReConnectMgr.m14986j().m14987i()) {
                        m21468c();
                        return;
                    }
                    return;
                }
                m21471a(exc);
                if (MediaWrap.this.f7445j != null) {
                    MediaWrap.this.f7445j.mediaConnectError(str2);
                }
                if (MediaWrap.this.f7438c != null && !MediaWrap.this.f7441f) {
                    MediaWrap.this.f7438c.mo15089a((IConnectionManager) MediaWrap.this.f7436a);
                    MediaWrap.this.f7438c = null;
                    return;
                }
                return;
            }
            CLog.m21882i(MediaWrap.TAG, MediaWrap.this.f7446k + " onSocketDisconnection: 正常断开 " + aVar.m15014f());
            StringBuilder sb = new StringBuilder();
            sb.append("reason:Disconnect Manually,ws:");
            sb.append(m21472a(MediaWrap.this.f7438c));
            MediaWrap.this.f7439d.put("onClosed", sb.toString());
            if (MediaWrap.this.f7445j != null) {
                MediaWrap.this.f7445j.mediaCloseSuccess();
            }
            if (MediaWrap.this.f7438c != null && !MediaWrap.this.f7441f) {
                MediaWrap.this.f7438c.mo15089a((IConnectionManager) MediaWrap.this.f7436a);
                MediaWrap.this.f7438c = null;
            }
        }

        /* renamed from: a */
        private String m21472a(IConnectionManager bVar) {
            if (bVar == null || bVar.toString().split("@").length <= 2) {
                return "";
            }
            return bVar.toString().split("@")[1];
        }

        /* renamed from: a */
        private void m21471a(Exception exc) {
            if (!MediaWrap.this.f7440e && exc != null) {
                String message = exc.getMessage();
                Throwable cause = exc.getCause();
                String simpleName = HwyMedia.class.getSimpleName();
                CLog.m21883e(simpleName, "onFailure: " + message + " ; " + cause + " ; " + m21472a(MediaWrap.this.f7438c));
                if (TextUtils.isEmpty(message) || cause == null || message.compareTo(cause.toString()) != 0) {
                    Map map = MediaWrap.this.f7439d;
                    map.put("onFailure", "msg:" + message + ",cause:" + cause);
                } else {
                    Map map2 = MediaWrap.this.f7439d;
                    map2.put("onFailure", "msg:" + message + ",cause:[same as message]");
                }
                if (MediaWrap.this.f7445j != null) {
                    IHwyMediaListener gVar = MediaWrap.this.f7445j;
                    gVar.MediaConnectRefuse(ActionCode.MediaConnectRefuse_1022, "【1022" + m21470a(message) + "】" + JsonUtil.m21804a(MediaWrap.this.f7439d));
                }
                MediaWrap.this.f7439d.clear();
            }
        }

        /* renamed from: a */
        private String m21470a(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String[] strArr = new String[10];
            strArr[0] = C3894au.f17527j;
            strArr[1] = "connect timed out";
            strArr[2] = "failed to connect to";
            strArr[3] = "Software caused connection abort";
            strArr[4] = "recvfrom failed: ETIMEDOUT";
            strArr[5] = "that mean this socket is disconnected by server";
            for (int i = 0; i < strArr.length && !TextUtils.isEmpty(strArr[i]); i++) {
                if (str.contains(strArr[i])) {
                    return Consts.f23430h + (i + 1);
                }
            }
            return "";
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
            CLog.m21882i(MediaWrap.TAG, MediaWrap.this.f7446k + " onSocketConnectionSuccess " + aVar.m15014f());
            Map map = MediaWrap.this.f7439d;
            map.put("onOpen", "tcp:" + m21472a(MediaWrap.this.f7438c));
            m21473a();
            if (ReConnectMgr.m14986j().m14991e()) {
                m21469b();
            } else if (MediaWrap.this.f7445j != null) {
                MediaWrap.this.f7445j.mediaConnectSuccess();
            }
        }

        /* renamed from: a */
        private void m21473a() {
            String str = "";
            try {
                str = MediaWrap.this.f7446k;
                CLog.m21882i(MediaWrap.TAG, "sendTcpControlIpAndPortMsg " + str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MediaWrap.this.send(new MsgDataBean(str));
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
            CLog.m21882i(MediaWrap.TAG, MediaWrap.this.f7446k + " onSocketConnectionFailed ");
            if (ReConnectMgr.m14986j().m14987i()) {
                m21468c();
                return;
            }
            if (ReConnectMgr.m14986j().m14991e()) {
                m21469b();
            }
            m21471a(exc);
            if (MediaWrap.this.f7445j != null) {
                MediaWrap.this.f7445j.mediaConnectError(exc.getMessage());
            }
            if (MediaWrap.this.f7438c != null) {
                MediaWrap.this.f7438c.mo15089a((IConnectionManager) MediaWrap.this.f7436a);
                MediaWrap.this.f7438c = null;
            }
        }

        /* renamed from: b */
        private void m21469b() {
            this.f7450b = false;
            if (MediaWrap.this.f7445j != null) {
                MediaWrap.this.f7445j.MediaConnectRefuse(ActionCode.MediaReConnectEnd, "视频重连结束");
            }
        }

        /* renamed from: c */
        private void m21468c() {
            if (!this.f7450b) {
                this.f7450b = true;
                if (MediaWrap.this.f7445j != null) {
                    MediaWrap.this.f7445j.MediaConnectRefuse(ActionCode.MediaReConnectBegin, "视频重连开始");
                }
            }
        }

        @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
        public void onSocketReadResponse(ConnectionInfo aVar, String str, OriginalData aVar2) {
            MediaWrap.this.m21482a(aVar2);
            MediaWrap.this.receiveBytes(aVar2.m15180b());
        }
    };

    /* renamed from: k */
    private String f7446k = "";

    public void receiveBytes(byte[] bArr) {
        IMediaServiceListener hVar = this.f7447l;
        if (hVar != null) {
            hVar.mo21611a(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21482a(OriginalData aVar) {
        byte[] b = aVar.m15180b();
        if (b[0] == 0 && !this.f7443h) {
            if (this.f7442g) {
                m21479b(b);
            } else {
                m21481a(b);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m21481a(byte[] r18) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.media.service.MediaWrap.m21481a(byte[]):void");
    }

    /* renamed from: a */
    private ConnectionInfo m21486a() {
        CLog.m21882i("onMessageMedia", "getLocalConnectionInfo start...");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ConnectionInfo[] aVarArr = {null};
        new Thread(new Runnable() { // from class: com.cyjh.ddy.media.service.MediaWrap.2
            @Override // java.lang.Runnable
            public void run() {
                if (MediaWrap.this.f7438c != null) {
                    try {
                        aVarArr[0] = MediaWrap.this.f7438c.mo14972b();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(aVarArr[0] == null ? "" : aVarArr[0].m15014f());
                        CLog.m21882i("onMessageMedia", sb.toString());
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            boolean await = countDownLatch.await(150L, TimeUnit.MILLISECONDS);
            CLog.m21882i("onMessageMedia", "await=" + await);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aVarArr[0];
    }

    /* renamed from: b */
    private void m21479b(byte[] bArr) {
        int i;
        for (int i2 = 0; i2 < bArr.length - 5; i2++) {
            if (bArr[i2 + 0] == 0 && bArr[i2 + 1] == 0 && bArr[i2 + 2] == 0 && bArr[i2 + 3] == 1 && (i = (bArr[i2 + 4] & 126) >> 1) >= 16 && i <= 21) {
                if (!this.f7443h) {
                    this.f7445j.mediaFirstFrameSuccess(m21486a());
                }
                this.f7443h = true;
                m21477c(bArr);
                return;
            }
        }
    }

    /* renamed from: c */
    private void m21477c(byte[] bArr) {
        this.f7444i = new byte[bArr.length];
        byte[] bArr2 = this.f7444i;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public final String bytesToHexString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
            stringBuffer.append(ExpandableTextView.f6958c);
        }
        return stringBuffer.toString();
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void connect(String str, int i, String str2, IHwyMediaListener gVar, String str3, IMediaServiceListener hVar, boolean z) {
        IConnectionManager bVar;
        this.f7439d.clear();
        String a = WSUtils.m21713a(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String, String> map = this.f7439d;
        map.put("startTcpConnect", "" + a);
        this.f7446k = str3;
        this.f7447l = hVar;
        this.f7445j = gVar;
        this.f7442g = z;
        this.f7443h = false;
        this.f7441f = true;
        if (!TextUtils.equals(str2, this.f7448m) && (bVar = this.f7438c) != null) {
            bVar.mo15129d();
        }
        IConnectionManager bVar2 = this.f7438c;
        if (bVar2 == null || !bVar2.mo14970f() || this.f7438c.mo14969g()) {
            createOkOptions();
            this.f7438c = OkSocket.m15066a(str, i).mo14978a(this.f7437b);
            this.f7438c.mo15084b((IConnectionManager) this.f7436a);
            this.f7438c.mo14976c();
            this.f7448m = str2;
        } else {
            if (gVar != null) {
                gVar.mediaConnectSuccess();
            }
            byte[] bArr = this.f7444i;
            if (bArr != null && bArr.length > 0) {
                receiveBytes(bArr);
            }
        }
        this.f7440e = false;
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void send(MsgDataBean bVar) {
        IConnectionManager bVar2 = this.f7438c;
        if (bVar2 != null && bVar2.mo14970f()) {
            this.f7438c.mo15133b((ISendable) bVar);
        }
    }

    @Override // com.cyjh.ddy.media.service.IMediaWrap
    public void release() {
        this.f7441f = false;
        this.f7440e = true;
        IConnectionManager bVar = this.f7438c;
        if (bVar != null) {
            bVar.mo15129d();
        }
        if (this.f7444i != null) {
            this.f7444i = new byte[0];
        }
    }

    public void createOkOptions() {
        this.f7437b = new OkSocketOptions.Builder().setReconnectionManager(ReConnectMgr.m14986j().m14994b() ? new CustomReconnectManager() : new NoneReconnect()).setConnectTimeoutSecond(5).setMaxReadDataMB(10).setReadPackageBytes(ConstantValue.f7164c).setWritePackageBytes(ConstantValue.f7164c).setCallbackThreadModeToken(new LocalToken()).build();
    }
}
