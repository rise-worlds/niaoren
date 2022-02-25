package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.impl.client.p099a.IConnectionSwitchListener;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener;
import java.io.Serializable;

/* renamed from: com.xuhao.didi.socket.client.impl.client.a */
/* loaded from: classes2.dex */
public abstract class AbsConnectionManager implements IConnectionManager {

    /* renamed from: a */
    protected ConnectionInfo f14404a;

    /* renamed from: b */
    protected ConnectionInfo f14405b;

    /* renamed from: c */
    protected ActionDispatcher f14406c;

    /* renamed from: d */
    private IConnectionSwitchListener f14407d;

    public AbsConnectionManager(ConnectionInfo aVar) {
        this(aVar, null);
    }

    public AbsConnectionManager(ConnectionInfo aVar, ConnectionInfo aVar2) {
        this.f14404a = aVar;
        this.f14405b = aVar2;
        this.f14406c = new ActionDispatcher(aVar, this);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public IConnectionManager mo15084b(ISocketActionListener bVar) {
        this.f14406c.mo15084b(bVar);
        return this;
    }

    /* renamed from: b  reason: avoid collision after fix types in other method */
    public IConnectionManager mo15089a(ISocketActionListener bVar) {
        this.f14406c.mo15089a(bVar);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15096a(String str, Serializable serializable) {
        this.f14406c.mo15087a(str, serializable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15097a(String str) {
        this.f14406c.mo15088a(str);
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: a */
    public ConnectionInfo mo14975a() {
        ConnectionInfo aVar = this.f14404a;
        if (aVar != null) {
            return aVar.m15013g();
        }
        return null;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: b */
    public ConnectionInfo mo14972b() {
        ConnectionInfo aVar = this.f14405b;
        if (aVar != null) {
            return aVar;
        }
        return null;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
    /* renamed from: a */
    public synchronized void mo14974a(ConnectionInfo aVar) {
        if (aVar != null) {
            ConnectionInfo aVar2 = this.f14404a;
            this.f14404a = aVar.m15013g();
            if (this.f14406c != null) {
                this.f14406c.m15090a(this.f14404a);
            }
            if (this.f14407d != null) {
                this.f14407d.onSwitchConnectionInfo(this, aVar2, this.f14404a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m15099a(IConnectionSwitchListener aVar) {
        this.f14407d = aVar;
    }
}
