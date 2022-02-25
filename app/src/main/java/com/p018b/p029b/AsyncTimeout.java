package com.p018b.p029b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import p110z1.C3894au;

/* renamed from: com.b.b.a */
/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {

    /* renamed from: a */
    private static final long f6407a = TimeUnit.SECONDS.toMillis(60);

    /* renamed from: c */
    private static final long f6408c = TimeUnit.MILLISECONDS.toNanos(f6407a);

    /* renamed from: d */
    private static AsyncTimeout f6409d;

    /* renamed from: e */
    private boolean f6410e;

    /* renamed from: f */
    private AsyncTimeout f6411f;

    /* renamed from: g */
    private long f6412g;

    /* renamed from: a */
    public void mo24300a() {
    }

    /* renamed from: c */
    public final void m24352c() {
        if (!this.f6410e) {
            long b_ = mo24248b_();
            boolean c_ = mo24247c_();
            if (b_ != 0 || c_) {
                this.f6410e = true;
                m24356a(this, b_, c_);
                return;
            }
            return;
        }
        throw new IllegalStateException("Unbalanced enter/exit");
    }

    /* renamed from: a */
    private static synchronized void m24356a(AsyncTimeout aVar, long j, boolean z) {
        synchronized (AsyncTimeout.class) {
            if (f6409d == null) {
                f6409d = new AsyncTimeout();
                new C0919d().start();
            }
            long nanoTime = System.nanoTime();
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i != 0 && z) {
                aVar.f6412g = Math.min(j, aVar.mo24246d() - nanoTime) + nanoTime;
            } else if (i != 0) {
                aVar.f6412g = j + nanoTime;
            } else if (z) {
                aVar.f6412g = aVar.mo24246d();
            } else {
                throw new AssertionError();
            }
            long j2 = aVar.f6412g - nanoTime;
            AsyncTimeout aVar2 = f6409d;
            while (aVar2.f6411f != null && j2 >= aVar2.f6411f.f6412g - nanoTime) {
                aVar2 = aVar2.f6411f;
            }
            aVar.f6411f = aVar2.f6411f;
            aVar2.f6411f = aVar;
            if (aVar2 == f6409d) {
                AsyncTimeout.class.notify();
            }
        }
    }

    /* renamed from: a_ */
    public final boolean m24354a_() {
        if (!this.f6410e) {
            return false;
        }
        this.f6410e = false;
        return m24357a(this);
    }

    /* renamed from: a */
    private static synchronized boolean m24357a(AsyncTimeout aVar) {
        synchronized (AsyncTimeout.class) {
            for (AsyncTimeout aVar2 = f6409d; aVar2 != null; aVar2 = aVar2.f6411f) {
                if (aVar2.f6411f == aVar) {
                    aVar2.f6411f = aVar.f6411f;
                    aVar.f6411f = null;
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24355a(boolean z) {
        if (m24354a_() && z) {
            throw mo24299a((IOException) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final IOException m24353b(IOException iOException) {
        return !m24354a_() ? iOException : mo24299a(iOException);
    }

    /* renamed from: a */
    public IOException mo24299a(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException(C3894au.f17527j);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static AsyncTimeout m24351e() {
        AsyncTimeout aVar = f6409d.f6411f;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            AsyncTimeout.class.wait(f6407a);
            if (f6409d.f6411f != null || System.nanoTime() - nanoTime < f6408c) {
                return null;
            }
            return f6409d;
        }
        long nanoTime2 = aVar.f6412g - System.nanoTime();
        if (nanoTime2 > 0) {
            long j = nanoTime2 / 1000000;
            AsyncTimeout.class.wait(j, (int) (nanoTime2 - (1000000 * j)));
            return null;
        }
        f6409d.f6411f = aVar.f6411f;
        aVar.f6411f = null;
        return aVar;
    }
}
