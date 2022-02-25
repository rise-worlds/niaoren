package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.socket.client.impl.client.PulseManager;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.connection.a */
/* loaded from: classes2.dex */
public abstract class AbsReconnectionManager implements ISocketActionListener {

    /* renamed from: a */
    protected volatile IConnectionManager f14489a;

    /* renamed from: b */
    protected PulseManager f14490b;

    /* renamed from: c */
    protected volatile boolean f14491c;

    /* renamed from: d */
    protected volatile Set<Class<? extends Exception>> f14492d = new LinkedHashSet();

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onPulseSend(ConnectionInfo aVar, IPulseSendable cVar) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadShutdown(String str, Exception exc) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadStart(String str) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketReadResponse(ConnectionInfo aVar, String str, OriginalData aVar2) {
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketWriteResponse(ConnectionInfo aVar, String str, ISendable eVar) {
    }

    /* renamed from: a */
    public synchronized void m14983a(IConnectionManager bVar) {
        if (this.f14491c) {
            mo14984a();
        }
        this.f14491c = false;
        this.f14489a = bVar;
        this.f14490b = bVar.mo14968h();
        this.f14489a.mo15084b((IConnectionManager) this);
    }

    /* renamed from: a */
    public synchronized void mo14984a() {
        this.f14491c = true;
        if (this.f14489a != null) {
            this.f14489a.mo15089a((IConnectionManager) this);
        }
    }

    /* renamed from: a */
    public final void m14982a(Class<? extends Exception> cls) {
        synchronized (this.f14492d) {
            this.f14492d.add(cls);
        }
    }

    /* renamed from: a */
    public final void m14981a(Exception exc) {
        synchronized (this.f14492d) {
            this.f14492d.remove(exc.getClass());
        }
    }

    /* renamed from: b */
    public final void m14979b(Class<? extends Exception> cls) {
        synchronized (this.f14492d) {
            this.f14492d.remove(cls);
        }
    }

    /* renamed from: b */
    public final void m14980b() {
        synchronized (this.f14492d) {
            this.f14492d.clear();
        }
    }
}
