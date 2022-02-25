package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p084b.p085a.IWriter;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.p100a.IAction;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import java.io.IOException;

/* renamed from: com.xuhao.didi.socket.client.impl.client.iothreads.b */
/* loaded from: classes2.dex */
public class DuplexWriteThread extends AbsLoopThread {

    /* renamed from: a */
    private IStateSender f14423a;

    /* renamed from: c */
    private IWriter f14424c;

    public DuplexWriteThread(IWriter gVar, IStateSender fVar) {
        super("client_duplex_write_thread");
        this.f14423a = fVar;
        this.f14424c = gVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: b */
    public void mo14997b() {
        this.f14423a.mo15088a(IAction.f14470g);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14999a() throws IOException {
        this.f14424c.mo15187a();
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    public synchronized void shutdown(Exception exc) {
        this.f14424c.mo15183b();
        super.shutdown(exc);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14998a(Exception exc) {
        if (exc instanceof ManuallyDisconnectException) {
            exc = null;
        }
        if (exc != null) {
            SLog.m15176a("duplex write error,thread is dead with exception:" + exc.getMessage());
        }
        this.f14423a.mo15087a(IAction.f14471h, exc);
    }
}
