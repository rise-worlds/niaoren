package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.b.a.a.e.j */
/* loaded from: classes.dex */
public final class Http2Connection implements Closeable {

    /* renamed from: b */
    final boolean f5946b;

    /* renamed from: c */
    final AbstractC0882s f5947c;

    /* renamed from: e */
    final String f5949e;

    /* renamed from: f */
    int f5950f;

    /* renamed from: g */
    int f5951g;

    /* renamed from: h */
    boolean f5952h;

    /* renamed from: i */
    final PushObserver f5953i;

    /* renamed from: k */
    long f5955k;

    /* renamed from: o */
    final Socket f5959o;

    /* renamed from: p */
    final Http2Writer f5960p;

    /* renamed from: q */
    final C0884u f5961q;

    /* renamed from: t */
    private final ExecutorService f5963t;

    /* renamed from: u */
    private Map<Integer, Ping> f5964u;

    /* renamed from: v */
    private int f5965v;

    /* renamed from: s */
    static final /* synthetic */ boolean f5945s = !Http2Connection.class.desiredAssertionStatus();

    /* renamed from: a */
    static final ExecutorService f5944a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.m24756a("OkHttp Http2Connection", true));

    /* renamed from: d */
    final Map<Integer, Http2Stream> f5948d = new LinkedHashMap();

    /* renamed from: j */
    long f5954j = 0;

    /* renamed from: l */
    Settings f5956l = new Settings();

    /* renamed from: m */
    final Settings f5957m = new Settings();

    /* renamed from: n */
    boolean f5958n = false;

    /* renamed from: r */
    final Set<Integer> f5962r = new LinkedHashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static boolean m24618d(int i) {
        return i != 0 && (i & 1) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Connection(C0881r rVar) {
        this.f5953i = rVar.f5997f;
        this.f5946b = rVar.f5998g;
        this.f5947c = rVar.f5996e;
        int i = 2;
        this.f5951g = rVar.f5998g ? 1 : 2;
        if (rVar.f5998g) {
            this.f5951g += 2;
        }
        this.f5965v = rVar.f5998g ? 1 : i;
        if (rVar.f5998g) {
            this.f5956l.m24671a(7, 16777216);
        }
        this.f5949e = rVar.f5993b;
        this.f5963t = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.m24756a(Util.m24755a("OkHttp %s Push Observer", this.f5949e), true));
        this.f5957m.m24671a(7, 65535);
        this.f5957m.m24671a(5, 16384);
        this.f5955k = this.f5957m.m24666d();
        this.f5959o = rVar.f5992a;
        this.f5960p = new Http2Writer(rVar.f5995d, this.f5946b);
        this.f5961q = new C0884u(this, new C0888y(rVar.f5994c, this.f5946b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized Http2Stream m24634a(int i) {
        return this.f5948d.get(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized Http2Stream m24624b(int i) {
        Http2Stream remove;
        remove = this.f5948d.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    /* renamed from: a */
    public final synchronized int m24635a() {
        return this.f5957m.m24668c();
    }

    /* renamed from: a */
    public final Http2Stream m24626a(List<Header> list, boolean z) {
        return m24622b(list, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037 A[Catch: all -> 0x0055, TryCatch #1 {, blocks: (B:4:0x0005, B:20:0x0041, B:21:0x0046, B:5:0x0006, B:7:0x000a, B:9:0x001f, B:11:0x0027, B:16:0x0031, B:18:0x0037, B:19:0x0040, B:25:0x004f, B:26:0x0054), top: B:30:0x0005 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.p018b.p019a.p020a.p025e.Http2Stream m24622b(java.util.List<com.p018b.p019a.p020a.p025e.Header> r11, boolean r12) {
        /*
            r10 = this;
            r6 = r12 ^ 1
            com.b.a.a.e.af r7 = r10.f5960p
            monitor-enter(r7)
            monitor-enter(r10)     // Catch: all -> 0x0058
            boolean r0 = r10.f5952h     // Catch: all -> 0x0055
            if (r0 != 0) goto L_0x004f
            int r8 = r10.f5951g     // Catch: all -> 0x0055
            int r0 = r10.f5951g     // Catch: all -> 0x0055
            int r0 = r0 + 2
            r10.f5951g = r0     // Catch: all -> 0x0055
            com.b.a.a.e.ab r9 = new com.b.a.a.e.ab     // Catch: all -> 0x0055
            r4 = 0
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: all -> 0x0055
            if (r12 == 0) goto L_0x0030
            long r0 = r10.f5955k     // Catch: all -> 0x0055
            r2 = 0
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 == 0) goto L_0x0030
            long r0 = r9.f5843b     // Catch: all -> 0x0055
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 != 0) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            r12 = 0
            goto L_0x0031
        L_0x0030:
            r12 = 1
        L_0x0031:
            boolean r0 = r9.m24718a()     // Catch: all -> 0x0055
            if (r0 == 0) goto L_0x0040
            java.util.Map<java.lang.Integer, com.b.a.a.e.ab> r0 = r10.f5948d     // Catch: all -> 0x0055
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch: all -> 0x0055
            r0.put(r1, r9)     // Catch: all -> 0x0055
        L_0x0040:
            monitor-exit(r10)     // Catch: all -> 0x0055
            com.b.a.a.e.af r0 = r10.f5960p     // Catch: all -> 0x0058
            r0.m24690a(r6, r8, r11)     // Catch: all -> 0x0058
            monitor-exit(r7)     // Catch: all -> 0x0058
            if (r12 == 0) goto L_0x004e
            com.b.a.a.e.af r11 = r10.f5960p
            r11.m24689b()
        L_0x004e:
            return r9
        L_0x004f:
            com.b.a.a.e.a r11 = new com.b.a.a.e.a     // Catch: all -> 0x0055
            r11.<init>()     // Catch: all -> 0x0055
            throw r11     // Catch: all -> 0x0055
        L_0x0055:
            r11 = move-exception
            monitor-exit(r10)     // Catch: all -> 0x0055
            throw r11     // Catch: all -> 0x0058
        L_0x0058:
            r11 = move-exception
            monitor-exit(r7)     // Catch: all -> 0x0058
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.p025e.Http2Connection.m24622b(java.util.List, boolean):com.b.a.a.e.ab");
    }

    /* renamed from: a */
    public final void m24628a(int i, boolean z, Buffer fVar, long j) {
        int min;
        long j2;
        if (j == 0) {
            this.f5960p.m24691a(z, i, fVar, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.f5955k <= 0) {
                    try {
                        if (this.f5948d.containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.f5955k), this.f5960p.m24686c());
                j2 = min;
                this.f5955k -= j2;
            }
            j -= j2;
            this.f5960p.m24691a(z && j == 0, i, fVar, min);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24632a(int i, ErrorCode bVar) {
        f5944a.execute(new C0874k(this, "OkHttp %s stream %d", new Object[]{this.f5949e, Integer.valueOf(i)}, i, bVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m24623b(int i, ErrorCode bVar) {
        this.f5960p.m24695a(i, bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24633a(int i, long j) {
        f5944a.execute(new C0875l(this, "OkHttp Window Update %s stream %d", new Object[]{this.f5949e, Integer.valueOf(i)}, i, j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized Ping m24620c(int i) {
        if (this.f5964u == null) {
            return null;
        }
        return this.f5964u.remove(Integer.valueOf(i));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        m24627a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24627a(ErrorCode bVar, ErrorCode bVar2) {
        IOException e;
        Http2Stream[] abVarArr;
        if (f5945s || !Thread.holdsLock(this)) {
            Ping[] aiVarArr = null;
            try {
                synchronized (this.f5960p) {
                    synchronized (this) {
                        if (!this.f5952h) {
                            this.f5952h = true;
                            this.f5960p.m24694a(this.f5950f, bVar, Util.f5777a);
                        }
                    }
                }
                e = null;
            } catch (IOException e2) {
                e = e2;
            }
            synchronized (this) {
                if (!this.f5948d.isEmpty()) {
                    abVarArr = (Http2Stream[]) this.f5948d.values().toArray(new Http2Stream[this.f5948d.size()]);
                    this.f5948d.clear();
                } else {
                    abVarArr = null;
                }
                if (this.f5964u != null) {
                    aiVarArr = (Ping[]) this.f5964u.values().toArray(new Ping[this.f5964u.size()]);
                    this.f5964u = null;
                }
            }
            if (abVarArr != null) {
                IOException e3 = e;
                for (Http2Stream abVar : abVarArr) {
                    try {
                        abVar.m24716a(bVar2);
                    } catch (IOException e4) {
                        e3 = e4;
                        if (e3 != null) {
                        }
                    }
                }
                e = e3;
            }
            if (aiVarArr != null) {
                for (Ping aiVar : aiVarArr) {
                    aiVar.m24678c();
                }
            }
            try {
                this.f5960p.close();
            } catch (IOException e5) {
                e = e5;
                if (e == null) {
                }
            }
            try {
                this.f5959o.close();
            } catch (IOException e6) {
                e = e6;
            }
            if (e != null) {
                throw e;
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public final synchronized boolean m24621c() {
        return this.f5952h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24630a(int i, List<Header> list) {
        synchronized (this) {
            if (this.f5962r.contains(Integer.valueOf(i))) {
                m24632a(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.f5962r.add(Integer.valueOf(i));
            this.f5963t.execute(new C0877n(this, "OkHttp %s Push Request[%s]", new Object[]{this.f5949e, Integer.valueOf(i)}, i, list));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24629a(int i, List<Header> list, boolean z) {
        this.f5963t.execute(new C0878o(this, "OkHttp %s Push Headers[%s]", new Object[]{this.f5949e, Integer.valueOf(i)}, i, list, z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24631a(int i, BufferedSource hVar, int i2, boolean z) {
        Buffer fVar = new Buffer();
        long j = i2;
        hVar.mo24288a(j);
        hVar.mo24249a(fVar, j);
        if (fVar.m24331b() == j) {
            this.f5963t.execute(new C0879p(this, "OkHttp %s Push Data[%s]", new Object[]{this.f5949e, Integer.valueOf(i)}, i, fVar, i2, z));
            return;
        }
        throw new IOException(fVar.m24331b() + " != " + i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m24619c(int i, ErrorCode bVar) {
        this.f5963t.execute(new C0880q(this, "OkHttp %s Push Reset[%s]", new Object[]{this.f5949e, Integer.valueOf(i)}, i, bVar));
    }

    /* renamed from: b */
    public final void m24625b() {
        this.f5960p.m24698a();
        this.f5960p.m24687b(this.f5956l);
        int d = this.f5956l.m24666d();
        if (d != 65535) {
            this.f5960p.m24696a(0, d - 65535);
        }
        new Thread(this.f5961q).start();
    }
}
