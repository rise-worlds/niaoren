package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.Address;
import com.p018b.p019a.ConnectionPool;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Route;
import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p023c.HttpCodec;
import com.p018b.p019a.p020a.p025e.ConnectionShutdownException;
import com.p018b.p019a.p020a.p025e.ErrorCode;
import com.p018b.p019a.p020a.p025e.StreamResetException;
import java.io.IOException;
import java.net.Socket;

/* renamed from: com.b.a.a.b.g */
/* loaded from: classes.dex */
public final class StreamAllocation {

    /* renamed from: b */
    static final /* synthetic */ boolean f5765b = !StreamAllocation.class.desiredAssertionStatus();

    /* renamed from: a */
    public final Address f5766a;

    /* renamed from: c */
    private Route f5767c;

    /* renamed from: d */
    private final ConnectionPool f5768d;

    /* renamed from: e */
    private final Object f5769e;

    /* renamed from: f */
    private final RouteSelector f5770f;

    /* renamed from: g */
    private int f5771g;

    /* renamed from: h */
    private RealConnection f5772h;

    /* renamed from: i */
    private boolean f5773i;

    /* renamed from: j */
    private boolean f5774j;

    /* renamed from: k */
    private HttpCodec f5775k;

    public StreamAllocation(ConnectionPool nVar, Address aVar, Object obj) {
        this.f5768d = nVar;
        this.f5766a = aVar;
        this.f5770f = new RouteSelector(aVar, m24770f());
        this.f5769e = obj;
    }

    /* renamed from: a */
    public final HttpCodec m24780a(OkHttpClient aiVar, boolean z) {
        try {
            HttpCodec a = m24782a(aiVar.m24508a(), aiVar.m24505b(), aiVar.m24504c(), aiVar.m24490q(), z).m24801a(aiVar, this);
            synchronized (this.f5768d) {
                this.f5775k = a;
            }
            return a;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /* renamed from: a */
    private RealConnection m24782a(int i, int i2, int i3, boolean z, boolean z2) {
        while (true) {
            RealConnection a = m24783a(i, i2, i3, z);
            synchronized (this.f5768d) {
                if (a.f5740b == 0) {
                    return a;
                }
                if (a.m24800a(z2)) {
                    return a;
                }
                m24772d();
            }
        }
    }

    /* renamed from: a */
    private RealConnection m24783a(int i, int i2, int i3, boolean z) {
        RealConnection cVar;
        synchronized (this.f5768d) {
            if (this.f5773i) {
                throw new IllegalStateException("released");
            } else if (this.f5775k != null) {
                throw new IllegalStateException("codec != null");
            } else if (!this.f5774j) {
                RealConnection cVar2 = this.f5772h;
                if (cVar2 != null && !cVar2.f5739a) {
                    return cVar2;
                }
                Internal.f5689a.mo24481a(this.f5768d, this.f5766a, this);
                if (this.f5772h != null) {
                    return this.f5772h;
                }
                Route awVar = this.f5767c;
                if (awVar == null) {
                    awVar = this.f5770f.m24788b();
                }
                synchronized (this.f5768d) {
                    this.f5767c = awVar;
                    this.f5771g = 0;
                    cVar = new RealConnection(this.f5768d, awVar);
                    m24781a(cVar);
                    if (this.f5774j) {
                        throw new IOException("Canceled");
                    }
                }
                cVar.m24803a(i, i2, i3, z);
                m24770f().m24795b(cVar.mo24399a());
                Socket socket = null;
                synchronized (this.f5768d) {
                    Internal.f5689a.mo24479b(this.f5768d, cVar);
                    if (cVar.m24797d()) {
                        socket = Internal.f5689a.mo24478b(this.f5768d, this.f5766a, this);
                        cVar = this.f5772h;
                    }
                }
                Util.m24754a(socket);
                return cVar;
            } else {
                throw new IOException("Canceled");
            }
        }
    }

    /* renamed from: a */
    public final void m24778a(boolean z, HttpCodec cVar) {
        Socket a;
        synchronized (this.f5768d) {
            if (cVar != null) {
                if (cVar == this.f5775k) {
                    if (!z) {
                        this.f5772h.f5740b++;
                    }
                    a = m24777a(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.f5775k + " but was " + cVar);
        }
        Util.m24754a(a);
    }

    /* renamed from: a */
    public final HttpCodec m24784a() {
        HttpCodec cVar;
        synchronized (this.f5768d) {
            cVar = this.f5775k;
        }
        return cVar;
    }

    /* renamed from: f */
    private RouteDatabase m24770f() {
        return Internal.f5689a.mo24483a(this.f5768d);
    }

    /* renamed from: b */
    public final synchronized RealConnection m24776b() {
        return this.f5772h;
    }

    /* renamed from: c */
    public final void m24774c() {
        Socket a;
        synchronized (this.f5768d) {
            a = m24777a(false, true, false);
        }
        Util.m24754a(a);
    }

    /* renamed from: d */
    public final void m24772d() {
        Socket a;
        synchronized (this.f5768d) {
            a = m24777a(true, false, false);
        }
        Util.m24754a(a);
    }

    /* renamed from: a */
    private Socket m24777a(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (f5765b || Thread.holdsLock(this.f5768d)) {
            if (z3) {
                this.f5775k = null;
            }
            if (z2) {
                this.f5773i = true;
            }
            RealConnection cVar = this.f5772h;
            if (cVar != null) {
                if (z) {
                    cVar.f5739a = true;
                }
                if (this.f5775k == null && (this.f5773i || this.f5772h.f5739a)) {
                    m24773c(this.f5772h);
                    if (this.f5772h.f5742d.isEmpty()) {
                        this.f5772h.f5743e = System.nanoTime();
                        if (Internal.f5689a.mo24482a(this.f5768d, this.f5772h)) {
                            socket = this.f5772h.m24799b();
                            this.f5772h = null;
                            return socket;
                        }
                    }
                    socket = null;
                    this.f5772h = null;
                    return socket;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public final void m24779a(IOException iOException) {
        boolean z;
        Socket a;
        synchronized (this.f5768d) {
            if (iOException instanceof StreamResetException) {
                StreamResetException amVar = (StreamResetException) iOException;
                if (amVar.f5887a == ErrorCode.REFUSED_STREAM) {
                    this.f5771g++;
                }
                if (amVar.f5887a == ErrorCode.REFUSED_STREAM && this.f5771g <= 1) {
                    z = false;
                }
                this.f5767c = null;
                z = true;
            } else if (this.f5772h == null || (this.f5772h.m24797d() && !(iOException instanceof ConnectionShutdownException))) {
                z = false;
            } else {
                if (this.f5772h.f5740b == 0) {
                    if (!(this.f5767c == null || iOException == null)) {
                        this.f5770f.m24790a(this.f5767c, iOException);
                    }
                    this.f5767c = null;
                }
                z = true;
            }
            a = m24777a(z, false, true);
        }
        Util.m24754a(a);
    }

    /* renamed from: a */
    public final void m24781a(RealConnection cVar) {
        if (!f5765b && !Thread.holdsLock(this.f5768d)) {
            throw new AssertionError();
        } else if (this.f5772h == null) {
            this.f5772h = cVar;
            cVar.f5742d.add(new C0857h(this, this.f5769e));
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: c */
    private void m24773c(RealConnection cVar) {
        int size = cVar.f5742d.size();
        for (int i = 0; i < size; i++) {
            if (cVar.f5742d.get(i).get() == this) {
                cVar.f5742d.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public final Socket m24775b(RealConnection cVar) {
        if (!f5765b && !Thread.holdsLock(this.f5768d)) {
            throw new AssertionError();
        } else if (this.f5775k == null && this.f5772h.f5742d.size() == 1) {
            Socket a = m24777a(true, false, false);
            this.f5772h = cVar;
            cVar.f5742d.add(this.f5772h.f5742d.get(0));
            return a;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: e */
    public final boolean m24771e() {
        return this.f5767c != null || this.f5770f.m24791a();
    }

    public final String toString() {
        RealConnection b = m24776b();
        return b != null ? b.toString() : this.f5766a.toString();
    }
}
