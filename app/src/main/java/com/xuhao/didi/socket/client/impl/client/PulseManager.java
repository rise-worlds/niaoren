package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.socket.client.impl.p098a.DogDeadException;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.p101b.IPulse;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class PulseManager implements IPulse {

    /* renamed from: a */
    private volatile IConnectionManager f14395a;

    /* renamed from: b */
    private IPulseSendable f14396b;

    /* renamed from: c */
    private volatile OkSocketOptions f14397c;

    /* renamed from: d */
    private volatile long f14398d;

    /* renamed from: e */
    private volatile OkSocketOptions.IOThreadMode f14399e;

    /* renamed from: f */
    private volatile boolean f14400f = false;

    /* renamed from: g */
    private volatile AtomicInteger f14401g = new AtomicInteger(-1);

    /* renamed from: h */
    private PulseThread f14402h = new PulseThread();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PulseManager(IConnectionManager bVar, OkSocketOptions okSocketOptions) {
        this.f14395a = bVar;
        this.f14397c = okSocketOptions;
        this.f14399e = this.f14397c.m15040h();
    }

    /* renamed from: a */
    public synchronized IPulse m15110a(IPulseSendable cVar) {
        if (cVar != null) {
            this.f14396b = cVar;
        }
        return this;
    }

    /* renamed from: a */
    public IPulseSendable m15111a() {
        return this.f14396b;
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p101b.IPulse
    /* renamed from: b */
    public synchronized void mo15011b() {
        m15100h();
        m15101g();
        if (this.f14399e != OkSocketOptions.IOThreadMode.SIMPLEX && this.f14402h.isShutdown()) {
            this.f14402h.start();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p101b.IPulse
    /* renamed from: c */
    public synchronized void mo15010c() {
        if (!this.f14400f) {
            if (!(this.f14399e == OkSocketOptions.IOThreadMode.SIMPLEX || this.f14395a == null || this.f14396b == null)) {
                this.f14395a.mo15133b((ISendable) this.f14396b);
            }
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p101b.IPulse
    /* renamed from: d */
    public synchronized void mo15009d() {
        this.f14401g.set(0);
        this.f14400f = true;
        m15100h();
    }

    /* renamed from: g */
    private synchronized void m15101g() {
        if (this.f14399e != OkSocketOptions.IOThreadMode.SIMPLEX) {
            this.f14398d = this.f14397c.m15039i();
            long j = 1000;
            if (this.f14398d >= 1000) {
                j = this.f14398d;
            }
            this.f14398d = j;
        } else {
            m15100h();
        }
    }

    @Override // com.xuhao.didi.socket.client.sdk.client.p101b.IPulse
    /* renamed from: e */
    public synchronized void mo15008e() {
        this.f14401g.set(-1);
    }

    /* renamed from: h */
    private void m15100h() {
        PulseThread pulseThread = this.f14402h;
        if (pulseThread != null) {
            pulseThread.shutdown();
        }
    }

    /* renamed from: f */
    public int m15103f() {
        return this.f14401g.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void m15108a(OkSocketOptions okSocketOptions) {
        this.f14397c = okSocketOptions;
        this.f14399e = this.f14397c.m15040h();
        m15101g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class PulseThread extends AbsLoopThread {
        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14998a(Exception exc) {
        }

        private PulseThread() {
        }

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14999a() throws Exception {
            if (PulseManager.this.f14400f) {
                shutdown();
                return;
            }
            if (!(PulseManager.this.f14395a == null || PulseManager.this.f14396b == null)) {
                if (PulseManager.this.f14397c.m15034n() == -1 || PulseManager.this.f14401g.incrementAndGet() < PulseManager.this.f14397c.m15034n()) {
                    PulseManager.this.f14395a.mo15133b((ISendable) PulseManager.this.f14396b);
                } else {
                    PulseManager.this.f14395a.mo15134a((Exception) new DogDeadException("you need feed dog on time,otherwise he will die"));
                }
            }
            Thread.sleep(PulseManager.this.f14398d);
        }
    }
}
