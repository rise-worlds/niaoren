package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.AbstractC0899ag;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import com.p018b.p029b.BufferedSink;
import com.p018b.p029b.Okio;
import java.net.ProtocolException;
import org.apache.http.protocol.HTTP;

/* renamed from: com.b.a.a.c.b */
/* loaded from: classes.dex */
public final class CallServerInterceptor implements Interceptor {

    /* renamed from: a */
    private final boolean f5794a;

    public CallServerInterceptor(boolean z) {
        this.f5794a = z;
    }

    @Override // com.p018b.p019a.Interceptor
    /* renamed from: a */
    public final Response mo24513a(AbstractC0899ag agVar) {
        Response asVar;
        RealInterceptorChain hVar = (RealInterceptorChain) agVar;
        HttpCodec c = hVar.m24731c();
        StreamAllocation b = hVar.m24732b();
        Request a = agVar.mo24512a();
        long currentTimeMillis = System.currentTimeMillis();
        c.mo24640a(a);
        C0905at atVar = null;
        if (HttpMethod.m24734b(a.m24469b()) && a.m24467d() != null) {
            if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(a.m24470a(HTTP.EXPECT_DIRECTIVE))) {
                c.mo24641a();
                atVar = c.mo24637a(true);
            }
            if (atVar == null) {
                BufferedSink a2 = Okio.m24306a(c.mo24639a(a, a.m24467d().mo24363b()));
                a.m24467d().mo24365a(a2);
                a2.close();
            }
        }
        c.mo24636b();
        if (atVar == null) {
            atVar = c.mo24637a(false);
        }
        Response a3 = atVar.m24439a(a).m24436a(b.m24776b().m24798c()).m24442a(currentTimeMillis).m24432b(System.currentTimeMillis()).m24444a();
        int b2 = a3.m24452b();
        if (!this.f5794a || b2 != 101) {
            asVar = a3.m24448f().m24437a(c.mo24638a(a3)).m24444a();
        } else {
            asVar = a3.m24448f().m24437a(Util.f5779c).m24444a();
        }
        if ("close".equalsIgnoreCase(asVar.m24454a().m24470a(HTTP.CONN_DIRECTIVE)) || "close".equalsIgnoreCase(asVar.m24453a(HTTP.CONN_DIRECTIVE))) {
            b.m24772d();
        }
        if ((b2 != 204 && b2 != 205) || asVar.m24449e().mo24426b() <= 0) {
            return asVar;
        }
        throw new ProtocolException("HTTP " + b2 + " had non-zero Content-Length: " + asVar.m24449e().mo24426b());
    }
}
