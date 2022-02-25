package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import com.xuhao.didi.socket.p089a.p090a.p097d.C3036c;

/* loaded from: classes2.dex */
public class DefaultReconnectManager extends AbsReconnectionManager {

    /* renamed from: e */
    private static final int f14480e = 12;

    /* renamed from: f */
    private int f14481f = 0;

    /* renamed from: g */
    private volatile ReconnectTestingThread f14482g = new ReconnectTestingThread();

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketDisconnection(ConnectionInfo aVar, String str, Exception exc) {
        if (m15002b(exc)) {
            m15000d();
        } else {
            m15001c();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionSuccess(ConnectionInfo aVar, String str) {
        m15001c();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
        if (exc != null) {
            this.f14481f++;
            if (this.f14481f > 12) {
                m15001c();
                ConnectionInfo a = this.f14489a.mo14975a();
                ConnectionInfo c = a.m15017c();
                if (c != null) {
                    c.m15020a(new ConnectionInfo(a.m15021a(), a.m15018b()));
                    if (!this.f14489a.mo14970f()) {
                        SLog.m15174b("Prepare switch to the backup line " + c.m15021a() + ":" + c.m15018b() + " ...");
                        synchronized (this.f14489a) {
                            this.f14489a.mo14974a(c);
                        }
                        m15000d();
                        return;
                    }
                    return;
                }
                m15000d();
                return;
            }
            m15000d();
        }
    }

    /* renamed from: b */
    private boolean m15002b(Exception exc) {
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
    private synchronized void m15001c() {
        if (this.f14482g != null) {
            this.f14482g.shutdown();
        }
    }

    /* renamed from: d */
    private void m15000d() {
        synchronized (this.f14482g) {
            if (this.f14482g.isShutdown()) {
                this.f14482g.start();
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
        private long f14484c;

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14998a(Exception exc) {
        }

        private ReconnectTestingThread() {
            this.f14484c = 10000L;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: b */
        public void mo14997b() throws Exception {
            super.mo14997b();
            if (this.f14484c < DefaultReconnectManager.this.f14489a.mo14977e().m15036l() * 1000) {
                this.f14484c = DefaultReconnectManager.this.f14489a.mo14977e().m15036l() * 1000;
            }
        }

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14999a() throws Exception {
            if (DefaultReconnectManager.this.f14491c) {
                SLog.m15174b("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
                return;
            }
            SLog.m15174b("Reconnect after " + this.f14484c + " mills ...");
            C3036c.m15141a(this.f14484c);
            if (DefaultReconnectManager.this.f14491c) {
                SLog.m15174b("ReconnectionManager already detached by framework.We decide gave up this reconnection mission!");
                shutdown();
            } else if (DefaultReconnectManager.this.f14489a.mo14970f()) {
                shutdown();
            } else if (!DefaultReconnectManager.this.f14489a.mo14977e().m15035m()) {
                DefaultReconnectManager.this.mo14984a();
                shutdown();
            } else {
                ConnectionInfo a = DefaultReconnectManager.this.f14489a.mo14975a();
                SLog.m15174b("Reconnect the server " + a.m15021a() + ":" + a.m15018b() + " ...");
                synchronized (DefaultReconnectManager.this.f14489a) {
                    if (!DefaultReconnectManager.this.f14489a.mo14970f()) {
                        DefaultReconnectManager.this.f14489a.mo14976c();
                    } else {
                        shutdown();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public boolean m15003a(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }
}
