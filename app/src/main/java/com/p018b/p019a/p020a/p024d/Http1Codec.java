package com.p018b.p019a.p020a.p024d;

import com.p018b.p019a.C0896ab;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.Headers;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.ResponseBody;
import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import com.p018b.p019a.p020a.p023c.HttpCodec;
import com.p018b.p019a.p020a.p023c.HttpHeaders;
import com.p018b.p019a.p020a.p023c.RealResponseBody;
import com.p018b.p019a.p020a.p023c.RequestLine;
import com.p018b.p019a.p020a.p023c.StatusLine;
import com.p018b.p029b.BufferedSink;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.ForwardingTimeout;
import com.p018b.p029b.Okio;
import com.p018b.p029b.Sink;
import com.p018b.p029b.Source;
import com.p018b.p029b.Timeout;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import org.apache.http.protocol.HTTP;

/* renamed from: com.b.a.a.d.a */
/* loaded from: classes.dex */
public final class Http1Codec implements HttpCodec {

    /* renamed from: a */
    final OkHttpClient f5818a;

    /* renamed from: b */
    final StreamAllocation f5819b;

    /* renamed from: c */
    final BufferedSource f5820c;

    /* renamed from: d */
    final BufferedSink f5821d;

    /* renamed from: e */
    int f5822e = 0;

    public Http1Codec(OkHttpClient aiVar, StreamAllocation gVar, BufferedSource hVar, BufferedSink gVar2) {
        this.f5818a = aiVar;
        this.f5819b = gVar;
        this.f5820c = hVar;
        this.f5821d = gVar2;
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final Sink mo24639a(Request aoVar, long j) {
        if (HTTP.CHUNK_CODING.equalsIgnoreCase(aoVar.m24470a(HTTP.TRANSFER_ENCODING))) {
            if (this.f5822e == 1) {
                this.f5822e = 2;
                return new C0861c(this);
            }
            throw new IllegalStateException("state: " + this.f5822e);
        } else if (j == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        } else if (this.f5822e == 1) {
            this.f5822e = 2;
            return new C0863e(this, j);
        } else {
            throw new IllegalStateException("state: " + this.f5822e);
        }
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final void mo24640a(Request aoVar) {
        Proxy.Type type = this.f5819b.m24776b().mo24399a().m24423b().type();
        StringBuilder sb = new StringBuilder();
        sb.append(aoVar.m24469b());
        sb.append(' ');
        if (!aoVar.m24464g() && type == Proxy.Type.HTTP) {
            sb.append(aoVar.m24471a());
        } else {
            sb.append(RequestLine.m24730a(aoVar.m24471a()));
        }
        sb.append(" HTTP/1.1");
        m24722a(aoVar.m24468c(), sb.toString());
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final void mo24641a() {
        this.f5821d.flush();
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: b */
    public final void mo24636b() {
        this.f5821d.flush();
    }

    /* renamed from: a */
    public final void m24722a(Headers aaVar, String str) {
        if (this.f5822e == 0) {
            this.f5821d.mo24298b(str).mo24298b("\r\n");
            int a = aaVar.m24559a();
            for (int i = 0; i < a; i++) {
                this.f5821d.mo24298b(aaVar.m24558a(i)).mo24298b(": ").mo24298b(aaVar.m24555b(i)).mo24298b("\r\n");
            }
            this.f5821d.mo24298b("\r\n");
            this.f5822e = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.f5822e);
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final C0905at mo24637a(boolean z) {
        int i = this.f5822e;
        if (i == 1 || i == 3) {
            try {
                StatusLine a = StatusLine.m24724a(this.f5820c.mo24273m());
                C0905at a2 = new C0905at().m24440a(a.f5813a).m24443a(a.f5814b).m24435a(a.f5815c).m24441a(m24720c());
                if (z && a.f5814b == 100) {
                    return null;
                }
                this.f5822e = 4;
                return a2;
            } catch (EOFException e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.f5819b);
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException("state: " + this.f5822e);
        }
    }

    /* renamed from: c */
    public final Headers m24720c() {
        C0896ab abVar = new C0896ab();
        while (true) {
            String m = this.f5820c.mo24273m();
            if (m.length() == 0) {
                return abVar.m24554a();
            }
            Internal.f5689a.mo24486a(abVar, m);
        }
    }

    /* renamed from: a */
    public final Source m24723a(long j) {
        if (this.f5822e == 4) {
            this.f5822e = 5;
            return new C0864f(this, j);
        }
        throw new IllegalStateException("state: " + this.f5822e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m24721a(ForwardingTimeout kVar) {
        Timeout a = kVar.m24311a();
        kVar.m24310a(Timeout.f6462b);
        a.mo24244e_();
        a.mo24245d_();
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final ResponseBody mo24638a(Response asVar) {
        Source xVar;
        if (!HttpHeaders.m24737b(asVar)) {
            xVar = m24723a(0L);
        } else if (HTTP.CHUNK_CODING.equalsIgnoreCase(asVar.m24453a(HTTP.TRANSFER_ENCODING))) {
            HttpUrl a = asVar.m24454a().m24471a();
            if (this.f5822e == 4) {
                this.f5822e = 5;
                xVar = new C0862d(this, a);
            } else {
                throw new IllegalStateException("state: " + this.f5822e);
            }
        } else {
            long a2 = HttpHeaders.m24742a(asVar);
            if (a2 != -1) {
                xVar = m24723a(a2);
            } else if (this.f5822e == 4) {
                StreamAllocation gVar = this.f5819b;
                if (gVar != null) {
                    this.f5822e = 5;
                    gVar.m24772d();
                    xVar = new C0865g(this);
                } else {
                    throw new IllegalStateException("streamAllocation == null");
                }
            } else {
                throw new IllegalStateException("state: " + this.f5822e);
            }
        }
        return new RealResponseBody(asVar.m24450d(), Okio.m24305a(xVar));
    }
}
