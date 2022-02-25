package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import com.xuhao.didi.socket.p089a.p090a.p097d.C3036c;

/* loaded from: classes2.dex */
public class CustomReconnectManager extends AbsReconnectionManager {

    /* renamed from: e */
    private static int f14475e = 12;

    /* renamed from: f */
    private int f14476f = 0;

    /* renamed from: g */
    private volatile ReconnectTestingThread f14477g = new ReconnectTestingThread();

    public CustomReconnectManager() {
        f14475e = ReConnectMgr.m14986j().m14996a();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
        if (m15006b(exc)) {
            this.f14476f++;
            if (this.f14476f <= f14475e) {
                m15004d();
                return;
            }
            return;
        }
        m15005c();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
        ReConnectMgr.m14986j().m14988h();
        this.f14476f = 0;
        m15005c();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
        if (exc != null) {
            this.f14476f++;
            SLog.m15176a("OkSocketmConnectionFailedTimes: " + this.f14476f);
            if (this.f14476f > f14475e) {
                ReConnectMgr.m14986j().m14989g();
            }
            m15004d();
        }
    }

    /* renamed from: b */
    private boolean m15006b(Exception exc) {
        synchronized (this.f14492d) {
            if (exc != null) {
                if (!(exc instanceof ManuallyDisconnectException)) {
                    for (Class<? extends Exception> cls : this.f14492d) {
                        if (cls.isAssignableFrom(exc.getClass())) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: c */
    private synchronized void m15005c() {
        if (this.f14477g != null) {
            this.f14477g.shutdown();
        }
    }

    /* renamed from: d */
    private void m15004d() {
        synchronized (this.f14477g) {
            if (this.f14477g.isShutdown()) {
                this.f14477g.start();
            }
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager
    /* renamed from: a */
    public void mo14984a() {
        super.mo14984a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ReconnectTestingThread extends AbsLoopThread {

        /* renamed from: c */
        private long f14479c;

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14998a(Exception exc) {
        }

        private ReconnectTestingThread() {
            this.f14479c = ReConnectMgr.m14986j().m14993c();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: b */
        public void mo14997b() throws Exception {
            super.mo14997b();
        }

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14999a() throws Exception {
            CustomReconnectManager.this.f14489a.mo15134a(new Exception("ReconnectionManager prepare ReConnect!!!"));
            if (CustomReconnectManager.this.f14491c) {
                SLog.m15174b("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
                return;
            }
            SLog.m15174b("Reconnect after " + this.f14479c + " mills ...");
            C3036c.m15141a(this.f14479c);
            if (CustomReconnectManager.this.f14491c) {
                SLog.m15174b("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
            } else if (CustomReconnectManager.this.f14489a.mo14970f()) {
                shutdown();
            } else if (!CustomReconnectManager.this.f14489a.mo14977e().m15035m()) {
                CustomReconnectManager.this.mo14984a();
                shutdown();
            } else {
                ConnectionInfo a = CustomReconnectManager.this.f14489a.mo14975a();
                SLog.m15174b("Reconnect the server " + a.m15021a() + ":" + a.m15018b() + " ...");
                synchronized (CustomReconnectManager.this.f14489a) {
                    if (!CustomReconnectManager.this.f14489a.mo14970f()) {
                        CustomReconnectManager.this.f14489a.mo14976c();
                    } else {
                        shutdown();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public boolean m15007a(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }
}
