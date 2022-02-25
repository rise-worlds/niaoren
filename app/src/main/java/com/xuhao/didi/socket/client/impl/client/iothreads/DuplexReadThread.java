package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.p082a.p084b.p085a.IReader;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.p100a.IAction;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import java.io.IOException;

/* renamed from: com.xuhao.didi.socket.client.impl.client.iothreads.a */
/* loaded from: classes2.dex */
public class DuplexReadThread extends AbsLoopThread {

    /* renamed from: a */
    private IStateSender f14421a;

    /* renamed from: c */
    private IReader f14422c;

    public DuplexReadThread(IReader dVar, IStateSender fVar) {
        super("client_duplex_read_thread");
        this.f14421a = fVar;
        this.f14422c = dVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: b */
    public void mo14997b() {
        this.f14421a.mo15088a(IAction.f14468e);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14999a() throws IOException {
        this.f14422c.mo15188b();
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    public synchronized void shutdown(Exception exc) {
        this.f14422c.mo15194a();
        super.shutdown(exc);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14998a(Exception exc) {
        if (exc instanceof ManuallyDisconnectException) {
            exc = null;
        }
        if (exc != null) {
            SLog.m15176a("duplex read error,thread is dead with exception:" + exc.getMessage());
        }
        this.f14421a.mo15087a(IAction.f14469f, exc);
    }
}
