package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.Address;
import com.p018b.p019a.Connection;
import com.p018b.p019a.ConnectionPool;
import com.p018b.p019a.Handshake;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Protocol;
import com.p018b.p019a.Route;
import com.p018b.p019a.p020a.p023c.HttpCodec;
import com.p018b.p019a.p020a.p024d.Http1Codec;
import com.p018b.p019a.p020a.p025e.AbstractC0882s;
import com.p018b.p019a.p020a.p025e.ErrorCode;
import com.p018b.p019a.p020a.p025e.Http2Codec;
import com.p018b.p019a.p020a.p025e.Http2Connection;
import com.p018b.p019a.p020a.p025e.Http2Stream;
import com.p018b.p019a.p020a.p027g.Platform;
import com.p018b.p029b.BufferedSink;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.Okio;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p110z1.cjm;

/* renamed from: com.b.a.a.b.c */
/* loaded from: classes.dex */
public final class RealConnection extends AbstractC0882s implements Connection {

    /* renamed from: a */
    public boolean f5739a;

    /* renamed from: b */
    public int f5740b;

    /* renamed from: c */
    public int f5741c = 1;

    /* renamed from: d */
    public final List<Reference<StreamAllocation>> f5742d = new ArrayList();

    /* renamed from: e */
    public long f5743e = cjm.f20759b;

    /* renamed from: g */
    private final ConnectionPool f5744g;

    /* renamed from: h */
    private final Route f5745h;

    /* renamed from: i */
    private Socket f5746i;

    /* renamed from: j */
    private Socket f5747j;

    /* renamed from: k */
    private Handshake f5748k;

    /* renamed from: l */
    private Protocol f5749l;

    /* renamed from: m */
    private Http2Connection f5750m;

    /* renamed from: n */
    private BufferedSource f5751n;

    /* renamed from: o */
    private BufferedSink f5752o;

    public RealConnection(ConnectionPool nVar, Route awVar) {
        this.f5744g = nVar;
        this.f5745h = awVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0345 A[EDGE_INSN: B:122:0x0345->B:105:0x0345 ?: BREAK  ] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0185 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0079 A[Catch: IOException -> 0x0313, TRY_LEAVE, TryCatch #3 {IOException -> 0x0313, blocks: (B:15:0x006f, B:17:0x0079), top: B:110:0x006f }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0198 A[Catch: IOException -> 0x0311, TryCatch #4 {IOException -> 0x0311, blocks: (B:21:0x00f2, B:24:0x0119, B:28:0x0134, B:29:0x014b, B:30:0x014c, B:31:0x0160, B:32:0x0161, B:34:0x016d, B:37:0x017a, B:38:0x0181, B:41:0x0185, B:42:0x018c, B:44:0x0198, B:45:0x01a2, B:62:0x024e, B:63:0x0255, B:65:0x025b, B:91:0x0306, B:92:0x030d, B:93:0x0310), top: B:112:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02a0 A[RETURN] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m24803a(int r17, int r18, int r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.p022b.RealConnection.m24803a(int, int, int, boolean):void");
    }

    /* renamed from: a */
    private void m24804a(int i, int i2) {
        Proxy b = this.f5745h.m24423b();
        this.f5746i = (b.type() == Proxy.Type.DIRECT || b.type() == Proxy.Type.HTTP) ? this.f5745h.m24424a().m24831c().createSocket() : new Socket(b);
        this.f5746i.setSoTimeout(i2);
        try {
            Platform.m24576b().mo24581a(this.f5746i, this.f5745h.m24422c(), i);
            this.f5751n = Okio.m24305a(Okio.m24302b(this.f5746i));
            this.f5752o = Okio.m24306a(Okio.m24303a(this.f5746i));
        } catch (ConnectException e) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f5745h.m24422c());
            connectException.initCause(e);
            throw connectException;
        }
    }

    /* renamed from: a */
    public final boolean m24802a(Address aVar) {
        return this.f5742d.size() < this.f5741c && aVar.equals(this.f5745h.m24424a()) && !this.f5739a;
    }

    /* renamed from: a */
    public final HttpCodec m24801a(OkHttpClient aiVar, StreamAllocation gVar) {
        Http2Connection jVar = this.f5750m;
        if (jVar != null) {
            return new Http2Codec(aiVar, gVar, jVar);
        }
        this.f5747j.setSoTimeout(aiVar.m24505b());
        this.f5751n.mo24250a().mo24242a(aiVar.m24505b(), TimeUnit.MILLISECONDS);
        this.f5752o.mo24252a().mo24242a(aiVar.m24504c(), TimeUnit.MILLISECONDS);
        return new Http1Codec(aiVar, gVar, this.f5751n, this.f5752o);
    }

    @Override // com.p018b.p019a.Connection
    /* renamed from: a */
    public final Route mo24399a() {
        return this.f5745h;
    }

    /* renamed from: b */
    public final Socket m24799b() {
        return this.f5747j;
    }

    /* renamed from: a */
    public final boolean m24800a(boolean z) {
        if (this.f5747j.isClosed() || this.f5747j.isInputShutdown() || this.f5747j.isOutputShutdown()) {
            return false;
        }
        Http2Connection jVar = this.f5750m;
        if (jVar != null) {
            return !jVar.m24621c();
        }
        if (z) {
            try {
                int soTimeout = this.f5747j.getSoTimeout();
                try {
                    this.f5747j.setSoTimeout(1);
                    return !this.f5751n.mo24282d();
                } finally {
                    this.f5747j.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p018b.p019a.p020a.p025e.AbstractC0882s
    /* renamed from: a */
    public final void mo24613a(Http2Stream abVar) {
        abVar.m24716a(ErrorCode.REFUSED_STREAM);
    }

    @Override // com.p018b.p019a.p020a.p025e.AbstractC0882s
    /* renamed from: a */
    public final void mo24614a(Http2Connection jVar) {
        synchronized (this.f5744g) {
            this.f5741c = jVar.m24635a();
        }
    }

    /* renamed from: c */
    public final Handshake m24798c() {
        return this.f5748k;
    }

    /* renamed from: d */
    public final boolean m24797d() {
        return this.f5750m != null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Connection{");
        sb.append(this.f5745h.m24424a().m24833a().m24529f());
        sb.append(":");
        sb.append(this.f5745h.m24424a().m24833a().m24528g());
        sb.append(", proxy=");
        sb.append(this.f5745h.m24423b());
        sb.append(" hostAddress=");
        sb.append(this.f5745h.m24422c());
        sb.append(" cipherSuite=");
        Handshake zVar = this.f5748k;
        sb.append(zVar != null ? zVar.m24360a() : "none");
        sb.append(" protocol=");
        sb.append(this.f5749l);
        sb.append('}');
        return sb.toString();
    }
}
