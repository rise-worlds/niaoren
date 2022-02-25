package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p022b.C0857h;
import com.p018b.p019a.p020a.p022b.RealConnection;
import com.p018b.p019a.p020a.p022b.RouteDatabase;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import com.p018b.p019a.p020a.p027g.Platform;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.b.a.n */
/* loaded from: classes.dex */
public final class ConnectionPool {

    /* renamed from: c */
    static final /* synthetic */ boolean f6355c = !ConnectionPool.class.desiredAssertionStatus();

    /* renamed from: d */
    private static final Executor f6356d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.m24756a("OkHttp ConnectionPool", true));

    /* renamed from: a */
    final RouteDatabase f6357a;

    /* renamed from: b */
    boolean f6358b;

    /* renamed from: e */
    private final int f6359e;

    /* renamed from: f */
    private final long f6360f;

    /* renamed from: g */
    private final Runnable f6361g;

    /* renamed from: h */
    private final Deque<RealConnection> f6362h;

    public ConnectionPool() {
        this(TimeUnit.MINUTES);
    }

    private ConnectionPool(TimeUnit timeUnit) {
        this.f6361g = new RunnableC0911o(this);
        this.f6362h = new ArrayDeque();
        this.f6357a = new RouteDatabase();
        this.f6359e = 5;
        this.f6360f = timeUnit.toNanos(5L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final RealConnection m24396a(Address aVar, StreamAllocation gVar) {
        if (f6355c || Thread.holdsLock(this)) {
            for (RealConnection cVar : this.f6362h) {
                if (cVar.m24802a(aVar)) {
                    gVar.m24781a(cVar);
                    return cVar;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final Socket m24394b(Address aVar, StreamAllocation gVar) {
        if (f6355c || Thread.holdsLock(this)) {
            for (RealConnection cVar : this.f6362h) {
                if (cVar.m24802a(aVar) && cVar.m24797d() && cVar != gVar.m24776b()) {
                    return gVar.m24775b(cVar);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24397a(RealConnection cVar) {
        if (f6355c || Thread.holdsLock(this)) {
            if (!this.f6358b) {
                this.f6358b = true;
                f6356d.execute(this.f6361g);
            }
            this.f6362h.add(cVar);
            return;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean m24395b(RealConnection cVar) {
        if (!f6355c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (cVar.f5739a || this.f6359e == 0) {
            this.f6362h.remove(cVar);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final long m24398a(long j) {
        int size;
        synchronized (this) {
            RealConnection cVar = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            int i2 = 0;
            for (RealConnection cVar2 : this.f6362h) {
                List<Reference<StreamAllocation>> list = cVar2.f5742d;
                int i3 = 0;
                while (true) {
                    if (i3 >= list.size()) {
                        size = list.size();
                        break;
                    }
                    Reference<StreamAllocation> reference = list.get(i3);
                    if (reference.get() == null) {
                        Platform.m24576b().mo24582a("A connection to " + cVar2.mo24399a().f6199a.f5678a + " was leaked. Did you forget to close a response body?", ((C0857h) reference).f5776a);
                        list.remove(i3);
                        cVar2.f5739a = true;
                        if (list.isEmpty()) {
                            cVar2.f5743e = j - this.f6360f;
                            size = 0;
                            break;
                        }
                    } else {
                        i3++;
                    }
                }
                if (size > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - cVar2.f5743e;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.f6360f && i <= this.f6359e) {
                if (i > 0) {
                    return this.f6360f - j2;
                } else if (i2 > 0) {
                    return this.f6360f;
                } else {
                    this.f6358b = false;
                    return -1L;
                }
            }
            this.f6362h.remove(cVar);
            Util.m24754a(cVar.m24799b());
            return 0L;
        }
    }
}
