package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.client.action.ActionHandler;
import com.xuhao.didi.socket.client.impl.client.iothreads.IOThreadManager;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.impl.p098a.UnConnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.OkSocketSSLConfig;
import com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.p100a.IAction;
import com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager;
import com.xuhao.didi.socket.p089a.p090a.p096c.DefaultX509ProtocolTrustManager;
import com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes2.dex */
public class ConnectionManagerImpl extends AbsConnectionManager {

    /* renamed from: d */
    private volatile Socket f14379d;

    /* renamed from: e */
    private volatile OkSocketOptions f14380e;

    /* renamed from: f */
    private IIOManager f14381f;

    /* renamed from: g */
    private Thread f14382g;

    /* renamed from: h */
    private ActionHandler f14383h;

    /* renamed from: i */
    private volatile PulseManager f14384i;

    /* renamed from: j */
    private volatile AbsReconnectionManager f14385j;

    /* renamed from: k */
    private volatile boolean f14386k;

    /* renamed from: l */
    private volatile boolean f14387l;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConnectionManagerImpl(ConnectionInfo aVar) {
        this(aVar, null);
    }

    public ConnectionManagerImpl(ConnectionInfo aVar, ConnectionInfo aVar2) {
        super(aVar, aVar2);
        this.f14386k = true;
        this.f14387l = false;
        String str = "";
        String str2 = "";
        if (aVar != null) {
            str = aVar.m15021a();
            str2 = aVar.m15018b() + "";
        }
        SLog.m15174b("block connection init with:" + str + ":" + str2);
        if (aVar2 != null) {
            SLog.m15174b("binding local addr:" + aVar2.m15021a() + " port:" + aVar2.m15018b());
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConnectable
    /* renamed from: c */
    public synchronized void mo14976c() {
        SLog.m15174b("Thread name:" + Thread.currentThread().getName() + " id:" + Thread.currentThread().getId());
        if (this.f14386k) {
            this.f14386k = false;
            if (!mo14970f()) {
                this.f14387l = false;
                if (this.f14404a != null) {
                    if (this.f14383h != null) {
                        this.f14383h.m15083a(this);
                        SLog.m15174b("mActionHandler is detached.");
                    }
                    this.f14383h = new ActionHandler();
                    this.f14383h.m15082a(this, this);
                    SLog.m15174b("mActionHandler is attached.");
                    if (this.f14385j != null) {
                        this.f14385j.mo14984a();
                        SLog.m15174b("ReconnectionManager is detached.");
                    }
                    this.f14385j = this.f14380e.m15033o();
                    if (this.f14385j != null) {
                        this.f14385j.m14983a(this);
                        SLog.m15174b("ReconnectionManager is attached.");
                    }
                    this.f14382g = new ConnectionThread(" Connect thread for " + (this.f14404a.m15021a() + ":" + this.f14404a.m15018b()));
                    this.f14382g.setDaemon(true);
                    this.f14382g.start();
                    return;
                }
                this.f14386k = true;
                throw new UnConnectException("连接参数为空,检查连接参数");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public synchronized Socket m15124j() throws Exception {
        if (this.f14380e.m15037k() != null) {
            return this.f14380e.m15037k().m15012a(this.f14404a, this.f14380e);
        }
        OkSocketSSLConfig j = this.f14380e.m15038j();
        if (j == null) {
            return new Socket();
        }
        SSLSocketFactory d = j.m15022d();
        if (d == null) {
            String str = "SSL";
            if (!TextUtils.m15142a(j.m15024b())) {
                str = j.m15024b();
            }
            TrustManager[] c = j.m15023c();
            if (c == null || c.length == 0) {
                c = new TrustManager[]{new DefaultX509ProtocolTrustManager()};
            }
            try {
                SSLContext instance = SSLContext.getInstance(str);
                instance.init(j.m15029a(), c, new SecureRandom());
                return instance.getSocketFactory().createSocket();
            } catch (Exception e) {
                if (this.f14380e.mo15041g()) {
                    e.printStackTrace();
                }
                SLog.m15176a(e.getMessage());
                return new Socket();
            }
        } else {
            try {
                return d.createSocket();
            } catch (IOException e2) {
                if (this.f14380e.mo15041g()) {
                    e2.printStackTrace();
                }
                SLog.m15176a(e2.getMessage());
                return new Socket();
            }
        }
    }

    /* loaded from: classes2.dex */
    private class ConnectionThread extends Thread {
        public ConnectionThread(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
            } catch (Exception e) {
                if (ConnectionManagerImpl.this.f14380e.mo15041g()) {
                    e.printStackTrace();
                }
                UnConnectException cVar = new UnConnectException(e);
                SLog.m15176a("Socket server " + ConnectionManagerImpl.this.f14404a.m15021a() + ":" + ConnectionManagerImpl.this.f14404a.m15018b() + " connect failed! error msg:" + e.getMessage());
                ConnectionManagerImpl.this.m15096a(IAction.f14473j, cVar);
            }
            try {
                try {
                    ConnectionManagerImpl.this.f14379d = ConnectionManagerImpl.this.m15124j();
                    if (ConnectionManagerImpl.this.f14405b != null) {
                        SLog.m15174b("try bind: " + ConnectionManagerImpl.this.f14405b.m15021a() + " port:" + ConnectionManagerImpl.this.f14405b.m15018b());
                        ConnectionManagerImpl.this.f14379d.bind(new InetSocketAddress(ConnectionManagerImpl.this.f14405b.m15021a(), ConnectionManagerImpl.this.f14405b.m15018b()));
                    }
                    SLog.m15174b("Start connect: " + ConnectionManagerImpl.this.f14404a.m15021a() + ":" + ConnectionManagerImpl.this.f14404a.m15018b() + " socket server...");
                    ConnectionManagerImpl.this.f14379d.connect(new InetSocketAddress(ConnectionManagerImpl.this.f14404a.m15021a(), ConnectionManagerImpl.this.f14404a.m15018b()), ConnectionManagerImpl.this.f14380e.m15036l() * 1000);
                    ConnectionManagerImpl.this.f14379d.setTcpNoDelay(true);
                    ConnectionManagerImpl.this.m15123k();
                    ConnectionManagerImpl.this.m15097a(IAction.f14472i);
                    SLog.m15174b("Socket server: " + ConnectionManagerImpl.this.f14404a.m15021a() + ":" + ConnectionManagerImpl.this.f14404a.m15018b() + " connect successful!");
                    ConnectionManagerImpl.this.f14386k = true;
                } catch (Exception e2) {
                    if (ConnectionManagerImpl.this.f14380e.mo15041g()) {
                        e2.printStackTrace();
                    }
                    throw new UnConnectException("Create socket failed.", e2);
                }
            } catch (Throwable th) {
                ConnectionManagerImpl.this.f14386k = true;
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m15123k() throws IOException {
        this.f14384i = new PulseManager(this, this.f14380e);
        this.f14381f = new IOThreadManager(this.f14379d.getInputStream(), this.f14379d.getOutputStream(), this.f14380e, this.f14406c);
        this.f14381f.mo15081a();
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.p093a.IDisConnectable
    /* renamed from: a */
    public void mo15134a(Exception exc) {
        synchronized (this) {
            if (!this.f14387l) {
                this.f14387l = true;
                if (this.f14384i != null) {
                    this.f14384i.mo15009d();
                    this.f14384i = null;
                }
                if ((exc instanceof ManuallyDisconnectException) && this.f14385j != null) {
                    this.f14385j.mo14984a();
                    SLog.m15174b("ReconnectionManager is detached.");
                }
                synchronized (this) {
                    DisconnectThread disconnectThread = new DisconnectThread(exc, "Disconnect Thread for " + (this.f14404a.m15021a() + ":" + this.f14404a.m15018b()));
                    disconnectThread.setDaemon(true);
                    disconnectThread.start();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DisconnectThread extends Thread {

        /* renamed from: b */
        private Exception f14390b;

        public DisconnectThread(Exception exc, String str) {
            super(str);
            this.f14390b = exc;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Exception] */
        /* JADX WARN: Type inference failed for: r0v32 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Exception exc;
            try {
                if (ConnectionManagerImpl.this.f14381f != null) {
                    ConnectionManagerImpl.this.f14381f.mo15077a(this.f14390b);
                }
                if (ConnectionManagerImpl.this.f14382g != null && ConnectionManagerImpl.this.f14382g.isAlive()) {
                    ConnectionManagerImpl.this.f14382g.interrupt();
                    try {
                        SLog.m15174b("disconnect thread need waiting for connection thread done.");
                        ConnectionManagerImpl.this.f14382g.join();
                    } catch (InterruptedException unused) {
                    }
                    SLog.m15174b("connection thread is done. disconnection thread going on");
                    ConnectionManagerImpl.this.f14382g = null;
                }
                if (ConnectionManagerImpl.this.f14379d != null) {
                    try {
                        ConnectionManagerImpl.this.f14379d.close();
                    } catch (IOException unused2) {
                    }
                }
                if (ConnectionManagerImpl.this.f14383h != null) {
                    ConnectionManagerImpl.this.f14383h.m15083a(ConnectionManagerImpl.this);
                    SLog.m15174b("mActionHandler is detached.");
                    ConnectionManagerImpl.this.f14383h = null;
                }
                if (exc == null) {
                    return;
                }
            } finally {
                ConnectionManagerImpl.this.f14387l = false;
                ConnectionManagerImpl.this.f14386k = true;
                if (!(this.f14390b instanceof UnConnectException) && ConnectionManagerImpl.this.f14379d != null) {
                    Exception exc2 = this.f14390b;
                    if (exc2 instanceof ManuallyDisconnectException) {
                        exc2 = null;
                    }
                    this.f14390b = exc2;
                    ConnectionManagerImpl.this.m15096a(IAction.f14474k, this.f14390b);
                }
                ConnectionManagerImpl.this.f14379d = null;
                if (this.f14390b != null) {
                    SLog.m15176a("socket is disconnecting because: " + this.f14390b.getMessage());
                    if (ConnectionManagerImpl.this.f14380e.mo15041g()) {
                        this.f14390b.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.p093a.IDisConnectable
    /* renamed from: d */
    public void mo15129d() {
        mo15134a((Exception) new ManuallyDisconnectException());
    }

    /* renamed from: a */
    public IConnectionManager mo15133b(ISendable eVar) {
        if (!(this.f14381f == null || eVar == null || !mo14970f())) {
            this.f14381f.mo15079a(eVar);
        }
        return this;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConfiguration
    /* renamed from: a */
    public IConnectionManager mo14978a(OkSocketOptions okSocketOptions) {
        if (okSocketOptions == null) {
            return this;
        }
        this.f14380e = okSocketOptions;
        IIOManager aVar = this.f14381f;
        if (aVar != null) {
            aVar.mo15080a((IIOManager) this.f14380e);
        }
        if (this.f14384i != null) {
            this.f14384i.m15108a(this.f14380e);
        }
        if (this.f14385j != null && !this.f14385j.equals(this.f14380e.m15033o())) {
            if (this.f14385j != null) {
                this.f14385j.mo14984a();
            }
            SLog.m15174b("reconnection manager is replaced");
            this.f14385j = this.f14380e.m15033o();
            this.f14385j.m14983a(this);
        }
        return this;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConfiguration
    /* renamed from: e */
    public OkSocketOptions mo14977e() {
        return this.f14380e;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: f */
    public boolean mo14970f() {
        return this.f14379d != null && this.f14379d.isConnected() && !this.f14379d.isClosed();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: g */
    public boolean mo14969g() {
        return this.f14387l;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: h */
    public PulseManager mo14968h() {
        return this.f14384i;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: a */
    public void mo14973a(boolean z) {
        this.f14380e = new OkSocketOptions.Builder(this.f14380e).setConnectionHolden(z).build();
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: i */
    public AbsReconnectionManager mo14967i() {
        return this.f14380e.m15033o();
    }

    @Override // com.xuhao.didi.socket.client.impl.client.AbsConnectionManager, com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: b */
    public ConnectionInfo mo14972b() {
        InetSocketAddress inetSocketAddress;
        ConnectionInfo b = super.mo14972b();
        return (b != null || !mo14970f() || (inetSocketAddress = (InetSocketAddress) this.f14379d.getLocalSocketAddress()) == null) ? b : new ConnectionInfo(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: b */
    public void mo14971b(ConnectionInfo aVar) {
        if (!mo14970f()) {
            this.f14405b = aVar;
            return;
        }
        throw new IllegalStateException("Socket is connected, can't set local info after connect.");
    }
}
