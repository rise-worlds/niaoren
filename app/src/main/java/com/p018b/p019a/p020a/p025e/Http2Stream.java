package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.Sink;
import com.p018b.p029b.Source;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.b.a.a.e.ab */
/* loaded from: classes.dex */
public final class Http2Stream {

    /* renamed from: i */
    static final /* synthetic */ boolean f5841i = !Http2Stream.class.desiredAssertionStatus();

    /* renamed from: b */
    long f5843b;

    /* renamed from: c */
    final int f5844c;

    /* renamed from: d */
    final Http2Connection f5845d;

    /* renamed from: e */
    final C0866ac f5846e;

    /* renamed from: j */
    private final List<Header> f5850j;

    /* renamed from: k */
    private List<Header> f5851k;

    /* renamed from: l */
    private boolean f5852l;

    /* renamed from: m */
    private final C0867ad f5853m;

    /* renamed from: a */
    long f5842a = 0;

    /* renamed from: f */
    final C0868ae f5847f = new C0868ae(this);

    /* renamed from: g */
    final C0868ae f5848g = new C0868ae(this);

    /* renamed from: h */
    ErrorCode f5849h = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Stream(int i, Http2Connection jVar, boolean z, boolean z2, List<Header> list) {
        if (jVar == null) {
            throw new NullPointerException("connection == null");
        } else if (list != null) {
            this.f5844c = i;
            this.f5845d = jVar;
            this.f5843b = jVar.f5957m.m24666d();
            this.f5853m = new C0867ad(this, jVar.f5956l.m24666d());
            this.f5846e = new C0866ac(this);
            this.f5853m.f5861b = z2;
            this.f5846e.f5856b = z;
            this.f5850j = list;
        } else {
            throw new NullPointerException("requestHeaders == null");
        }
    }

    /* renamed from: a */
    public final synchronized boolean m24718a() {
        if (this.f5849h != null) {
            return false;
        }
        if ((this.f5853m.f5861b || this.f5853m.f5860a) && (this.f5846e.f5856b || this.f5846e.f5855a)) {
            if (this.f5852l) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public final boolean m24713b() {
        return this.f5845d.f5946b == ((this.f5844c & 1) == 1);
    }

    /* renamed from: c */
    public final synchronized List<Header> m24711c() {
        List<Header> list;
        if (m24713b()) {
            this.f5847f.m24352c();
            while (this.f5851k == null && this.f5849h == null) {
                m24703i();
            }
            this.f5847f.m24699b();
            list = this.f5851k;
            if (list != null) {
                this.f5851k = null;
            } else {
                throw new StreamResetException(this.f5849h);
            }
        } else {
            throw new IllegalStateException("servers cannot read response headers");
        }
        return list;
    }

    /* renamed from: d */
    public final Source m24709d() {
        return this.f5853m;
    }

    /* renamed from: e */
    public final Sink m24707e() {
        synchronized (this) {
            if (!this.f5852l && !m24713b()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f5846e;
    }

    /* renamed from: a */
    public final void m24716a(ErrorCode bVar) {
        if (m24708d(bVar)) {
            this.f5845d.m24623b(this.f5844c, bVar);
        }
    }

    /* renamed from: b */
    public final void m24712b(ErrorCode bVar) {
        if (m24708d(bVar)) {
            this.f5845d.m24632a(this.f5844c, bVar);
        }
    }

    /* renamed from: d */
    private boolean m24708d(ErrorCode bVar) {
        if (f5841i || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.f5849h != null) {
                    return false;
                }
                if (this.f5853m.f5861b && this.f5846e.f5856b) {
                    return false;
                }
                this.f5849h = bVar;
                notifyAll();
                this.f5845d.m24624b(this.f5844c);
                return true;
            }
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24714a(List<Header> list) {
        boolean z;
        if (f5841i || !Thread.holdsLock(this)) {
            synchronized (this) {
                z = true;
                this.f5852l = true;
                if (this.f5851k == null) {
                    this.f5851k = list;
                    z = m24718a();
                    notifyAll();
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(this.f5851k);
                    arrayList.add(null);
                    arrayList.addAll(list);
                    this.f5851k = arrayList;
                }
            }
            if (!z) {
                this.f5845d.m24624b(this.f5844c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24715a(BufferedSource hVar, int i) {
        if (f5841i || !Thread.holdsLock(this)) {
            this.f5853m.m24701a(hVar, i);
            return;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public final void m24706f() {
        boolean a;
        if (f5841i || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.f5853m.f5861b = true;
                a = m24718a();
                notifyAll();
            }
            if (!a) {
                this.f5845d.m24624b(this.f5844c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized void m24710c(ErrorCode bVar) {
        if (this.f5849h == null) {
            this.f5849h = bVar;
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public final void m24705g() {
        boolean z;
        boolean a;
        if (f5841i || !Thread.holdsLock(this)) {
            synchronized (this) {
                z = !this.f5853m.f5861b && this.f5853m.f5860a && (this.f5846e.f5856b || this.f5846e.f5855a);
                a = m24718a();
            }
            if (z) {
                m24716a(ErrorCode.CANCEL);
            } else if (!a) {
                this.f5845d.m24624b(this.f5844c);
            }
        } else {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24717a(long j) {
        this.f5843b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public final void m24704h() {
        if (this.f5846e.f5855a) {
            throw new IOException("stream closed");
        } else if (!this.f5846e.f5856b) {
            ErrorCode bVar = this.f5849h;
            if (bVar != null) {
                throw new StreamResetException(bVar);
            }
        } else {
            throw new IOException("stream finished");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public final void m24703i() {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }
}
