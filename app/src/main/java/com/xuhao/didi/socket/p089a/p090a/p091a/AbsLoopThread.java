package com.xuhao.didi.socket.p089a.p090a.p091a;

import com.xuhao.didi.p082a.p088e.SLog;

/* renamed from: com.xuhao.didi.socket.a.a.a.a */
/* loaded from: classes2.dex */
public abstract class AbsLoopThread implements Runnable {

    /* renamed from: a */
    private volatile boolean f14374a;

    /* renamed from: b */
    protected volatile String f14375b;
    public volatile Thread thread = null;

    /* renamed from: c */
    private volatile boolean f14376c = true;

    /* renamed from: d */
    private volatile Exception f14377d = null;

    /* renamed from: e */
    private volatile long f14378e = 0;

    /* renamed from: a */
    protected abstract void mo14999a() throws Exception;

    /* renamed from: a */
    protected abstract void mo14998a(Exception exc);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14997b() throws Exception {
    }

    public AbsLoopThread() {
        this.f14375b = "";
        this.f14374a = false;
        this.f14374a = true;
        this.f14375b = getClass().getSimpleName();
    }

    public AbsLoopThread(String str) {
        this.f14375b = "";
        this.f14374a = false;
        this.f14374a = true;
        this.f14375b = str;
    }

    public synchronized void start() {
        if (this.f14374a) {
            this.thread = new Thread(this, this.f14375b);
            this.f14374a = false;
            this.f14378e = 0L;
            this.thread.start();
            SLog.m15173c(this.f14375b + " is starting");
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuilder sb;
        try {
            try {
                this.f14376c = false;
                mo14997b();
                while (!this.f14374a) {
                    mo14999a();
                    this.f14378e++;
                }
                this.f14376c = true;
                mo14998a(this.f14377d);
                this.f14377d = null;
                sb = new StringBuilder();
            } catch (Exception e) {
                if (this.f14377d == null) {
                    this.f14377d = e;
                }
                this.f14376c = true;
                mo14998a(this.f14377d);
                this.f14377d = null;
                sb = new StringBuilder();
            }
            sb.append(this.f14375b);
            sb.append(" is shutting down");
            SLog.m15173c(sb.toString());
        } catch (Throwable th) {
            this.f14376c = true;
            mo14998a(this.f14377d);
            this.f14377d = null;
            SLog.m15173c(this.f14375b + " is shutting down");
            throw th;
        }
    }

    public long getLoopTimes() {
        return this.f14378e;
    }

    public String getThreadName() {
        return this.f14375b;
    }

    public synchronized void shutdown() {
        if (this.thread != null && !this.f14374a) {
            this.f14374a = true;
            this.thread.interrupt();
            this.thread = null;
        }
    }

    public synchronized void shutdown(Exception exc) {
        this.f14377d = exc;
        shutdown();
    }

    public boolean isShutdown() {
        return this.f14376c;
    }
}
