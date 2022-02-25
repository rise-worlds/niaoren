package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.p082a.p084b.ReaderImpl;
import com.xuhao.didi.p082a.p084b.WriterImpl;
import com.xuhao.didi.p082a.p084b.p085a.IReader;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p084b.p085a.IWriter;
import com.xuhao.didi.p082a.p087d.IReaderProtocol;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.p098a.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.xuhao.didi.socket.client.impl.client.iothreads.c */
/* loaded from: classes2.dex */
public class IOThreadManager implements IIOManager<OkSocketOptions> {

    /* renamed from: a */
    private InputStream f14425a;

    /* renamed from: b */
    private OutputStream f14426b;

    /* renamed from: c */
    private volatile OkSocketOptions f14427c;

    /* renamed from: d */
    private IStateSender f14428d;

    /* renamed from: e */
    private IReader f14429e;

    /* renamed from: f */
    private IWriter f14430f;

    /* renamed from: g */
    private AbsLoopThread f14431g;

    /* renamed from: h */
    private DuplexReadThread f14432h;

    /* renamed from: i */
    private DuplexWriteThread f14433i;

    /* renamed from: j */
    private OkSocketOptions.IOThreadMode f14434j;

    public IOThreadManager(InputStream inputStream, OutputStream outputStream, OkSocketOptions okSocketOptions, IStateSender fVar) {
        this.f14425a = inputStream;
        this.f14426b = outputStream;
        this.f14427c = okSocketOptions;
        this.f14428d = fVar;
        m15074c();
    }

    /* renamed from: c */
    private void m15074c() {
        m15071f();
        this.f14429e = new ReaderImpl();
        this.f14429e.mo15192a(this.f14425a, this.f14428d);
        this.f14430f = new WriterImpl();
        this.f14430f.mo15184a(this.f14426b, this.f14428d);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager
    /* renamed from: a */
    public void mo15081a() {
        this.f14434j = this.f14427c.m15040h();
        this.f14429e.mo15193a(this.f14427c);
        this.f14430f.mo15186a((IWriter) this.f14427c);
        switch (this.f14427c.m15040h()) {
            case DUPLEX:
                SLog.m15173c("DUPLEX is processing");
                m15073d();
                return;
            case SIMPLEX:
                SLog.m15173c("SIMPLEX is processing");
                m15072e();
                return;
            default:
                throw new RuntimeException("未定义的线程模式");
        }
    }

    /* renamed from: d */
    private void m15073d() {
        m15075b(null);
        this.f14433i = new DuplexWriteThread(this.f14430f, this.f14428d);
        this.f14432h = new DuplexReadThread(this.f14429e, this.f14428d);
        this.f14433i.start();
        this.f14432h.start();
    }

    /* renamed from: e */
    private void m15072e() {
        m15075b(null);
        this.f14431g = new SimplexIOThread(this.f14429e, this.f14430f, this.f14428d);
        this.f14431g.start();
    }

    /* renamed from: b */
    private void m15075b(Exception exc) {
        AbsLoopThread aVar = this.f14431g;
        if (aVar != null) {
            aVar.shutdown(exc);
            this.f14431g = null;
        }
        DuplexReadThread aVar2 = this.f14432h;
        if (aVar2 != null) {
            aVar2.shutdown(exc);
            this.f14432h = null;
        }
        DuplexWriteThread bVar = this.f14433i;
        if (bVar != null) {
            bVar.shutdown(exc);
            this.f14433i = null;
        }
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo15080a(OkSocketOptions okSocketOptions) {
        this.f14427c = okSocketOptions;
        if (this.f14434j == null) {
            this.f14434j = this.f14427c.m15040h();
        }
        m15070g();
        m15071f();
        this.f14430f.mo15186a((IWriter) this.f14427c);
        this.f14429e.mo15193a(this.f14427c);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager
    /* renamed from: a */
    public void mo15079a(ISendable eVar) {
        this.f14430f.mo15185a(eVar);
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager
    /* renamed from: b */
    public void mo15076b() {
        mo15077a(new ManuallyDisconnectException());
    }

    @Override // com.xuhao.didi.socket.p089a.p090a.p092b.IIOManager
    /* renamed from: a */
    public void mo15077a(Exception exc) {
        m15075b(exc);
        this.f14434j = null;
    }

    /* renamed from: f */
    private void m15071f() {
        IReaderProtocol c = this.f14427c.mo15048c();
        if (c == null) {
            throw new IllegalArgumentException("The reader protocol can not be Null.");
        } else if (c.mo15148a() == 0) {
            throw new IllegalArgumentException("The header length can not be zero.");
        }
    }

    /* renamed from: g */
    private void m15070g() {
        if (this.f14427c.m15040h() != this.f14434j) {
            throw new IllegalArgumentException("can't hot change iothread mode from " + this.f14434j + " to " + this.f14427c.m15040h() + " in blocking io manager");
        }
    }
}
