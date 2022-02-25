package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.p082a.p084b.p085a.IReader;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p084b.p085a.IWriter;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.p100a.IAction;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import java.io.IOException;

/* renamed from: com.xuhao.didi.socket.client.impl.client.iothreads.d */
/* loaded from: classes2.dex */
public class SimplexIOThread extends AbsLoopThread {

    /* renamed from: a */
    private IStateSender f14435a;

    /* renamed from: c */
    private IReader f14436c;

    /* renamed from: d */
    private IWriter f14437d;

    /* renamed from: e */
    private boolean f14438e = false;

    public SimplexIOThread(IReader dVar, IWriter gVar, IStateSender fVar) {
        super("client_simplex_io_thread");
        this.f14435a = fVar;
        this.f14436c = dVar;
        this.f14437d = gVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: b */
    public void mo14997b() throws IOException {
        this.f14435a.mo15088a(IAction.f14470g);
        this.f14435a.mo15088a(IAction.f14468e);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14999a() throws IOException {
        this.f14438e = this.f14437d.mo15187a();
        if (this.f14438e) {
            this.f14438e = false;
            this.f14436c.mo15188b();
        }
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    public synchronized void shutdown(Exception exc) {
        this.f14436c.mo15194a();
        this.f14437d.mo15183b();
        super.shutdown(exc);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
    /* renamed from: a */
    protected void mo14998a(Exception exc) {
        if (exc instanceof ManuallyDisconnectException) {
            exc = null;
        }
        if (exc != null) {
            SLog.m15176a("simplex error,thread is dead with exception:" + exc.getMessage());
        }
        this.f14435a.mo15087a(IAction.f14471h, exc);
        this.f14435a.mo15087a(IAction.f14469f, exc);
    }
}
