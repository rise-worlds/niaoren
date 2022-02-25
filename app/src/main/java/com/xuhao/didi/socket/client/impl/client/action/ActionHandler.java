package com.xuhao.didi.socket.client.impl.client.action;

import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter;
import com.xuhao.didi.socket.p089a.p090a.p092b.p094b.IRegister;

/* renamed from: com.xuhao.didi.socket.client.impl.client.action.a */
/* loaded from: classes2.dex */
public class ActionHandler extends SocketActionAdapter {

    /* renamed from: a */
    private IConnectionManager f14417a;

    /* renamed from: b */
    private OkSocketOptions.IOThreadMode f14418b;

    /* renamed from: c */
    private boolean f14419c = false;

    /* renamed from: a */
    public void m15082a(IConnectionManager bVar, IRegister<ISocketActionListener, IConnectionManager> aVar) {
        this.f14417a = bVar;
        aVar.mo15084b(this);
    }

    /* renamed from: a */
    public void m15083a(IRegister aVar) {
        aVar.mo15089a(this);
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadStart(String str) {
        if (this.f14417a.mo14977e().m15040h() != this.f14418b) {
            this.f14418b = this.f14417a.mo14977e().m15040h();
        }
        this.f14419c = false;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketIOThreadShutdown(String str, Exception exc) {
        if (this.f14418b == this.f14417a.mo14977e().m15040h() && !this.f14419c) {
            this.f14419c = true;
            if (!(exc instanceof ManuallyDisconnectException)) {
                this.f14417a.mo15134a(exc);
            }
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p100a.SocketActionAdapter, com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener
    public void onSocketConnectionFailed(ConnectionInfo aVar, String str, Exception exc) {
        this.f14417a.mo15134a(exc);
    }
}
