package com.p018b.p019a.p020a.p021a;

import com.p018b.p019a.AbstractC0899ag;
import com.p018b.p019a.C0896ab;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.Headers;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.Protocol;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.ResponseBody;
import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p023c.HttpHeaders;
import com.p018b.p019a.p020a.p023c.RealResponseBody;
import com.p018b.p029b.Okio;
import com.p018b.p029b.Sink;
import org.apache.http.auth.AUTH;
import org.apache.http.protocol.HTTP;

/* renamed from: com.b.a.a.a.a */
/* loaded from: classes.dex */
public final class CacheInterceptor implements Interceptor {

    /* renamed from: a */
    final InternalCache f5690a;

    public CacheInterceptor(InternalCache iVar) {
        this.f5690a = iVar;
    }

    @Override // com.p018b.p019a.Interceptor
    /* renamed from: a */
    public final Response mo24513a(AbstractC0899ag agVar) {
        Sink a;
        InternalCache iVar = this.f5690a;
        CacheRequest cVar = null;
        Response a2 = iVar != null ? iVar.m24809a() : null;
        CacheStrategy a3 = new C0854e(System.currentTimeMillis(), agVar.mo24512a(), a2).m24818a();
        Request aoVar = a3.f5696a;
        Response asVar = a3.f5697b;
        if (a2 != null && asVar == null) {
            Util.m24764a(a2.m24449e());
        }
        if (aoVar == null && asVar == null) {
            return new C0905at().m24439a(agVar.mo24512a()).m24440a(Protocol.HTTP_1_1).m24443a(504).m24435a("Unsatisfiable Request (only-if-cached)").m24437a(Util.f5779c).m24442a(-1L).m24432b(System.currentTimeMillis()).m24444a();
        }
        if (aoVar == null) {
            return asVar.m24448f().m24431b(m24822a(asVar)).m24444a();
        }
        try {
            Response a4 = agVar.mo24511a(aoVar);
            if (a4 == null && a2 != null) {
            }
            if (asVar != null) {
                if (a4.m24452b() == 304) {
                    C0905at f = asVar.m24448f();
                    Headers d = asVar.m24450d();
                    Headers d2 = a4.m24450d();
                    C0896ab abVar = new C0896ab();
                    int a5 = d.m24559a();
                    for (int i = 0; i < a5; i++) {
                        String a6 = d.m24558a(i);
                        String b = d.m24555b(i);
                        if ((!"Warning".equalsIgnoreCase(a6) || !b.startsWith("1")) && (!m24821a(a6) || d2.m24557a(a6) == null)) {
                            Internal.f5689a.mo24485a(abVar, a6, b);
                        }
                    }
                    int a7 = d2.m24559a();
                    for (int i2 = 0; i2 < a7; i2++) {
                        String a8 = d2.m24558a(i2);
                        if (!"Content-Length".equalsIgnoreCase(a8) && m24821a(a8)) {
                            Internal.f5689a.mo24485a(abVar, a8, d2.m24555b(i2));
                        }
                    }
                    Response a9 = f.m24441a(abVar.m24554a()).m24442a(a4.m24446h()).m24432b(a4.m24445i()).m24431b(m24822a(asVar)).m24438a(m24822a(a4)).m24444a();
                    a4.m24449e().close();
                    return a9;
                }
                Util.m24764a(asVar.m24449e());
            }
            Response a10 = a4.m24448f().m24431b(m24822a(asVar)).m24438a(m24822a(a4)).m24444a();
            if (!HttpHeaders.m24737b(a10)) {
                return a10;
            }
            Request a11 = a4.m24454a();
            InternalCache iVar2 = this.f5690a;
            if (iVar2 != null) {
                if (!CacheStrategy.m24819a(a10, a11)) {
                    String b2 = a11.m24469b();
                    if (!b2.equals("POST") && !b2.equals("PATCH") && !b2.equals("PUT") && !b2.equals("DELETE")) {
                        b2.equals("MOVE");
                    }
                } else {
                    cVar = iVar2.m24808b();
                }
            }
            return (cVar == null || (a = cVar.m24820a()) == null) ? a10 : a10.m24448f().m24437a(new RealResponseBody(a10.m24450d(), Okio.m24305a(new C0853b(this, a10.m24449e().mo24425c(), cVar, Okio.m24306a(a))))).m24444a();
        } finally {
            if (a2 != null) {
                Util.m24764a(a2.m24449e());
            }
        }
    }

    /* renamed from: a */
    private static Response m24822a(Response asVar) {
        return (asVar == null || asVar.m24449e() == null) ? asVar : asVar.m24448f().m24437a((ResponseBody) null).m24444a();
    }

    /* renamed from: a */
    private static boolean m24821a(String str) {
        return !HTTP.CONN_DIRECTIVE.equalsIgnoreCase(str) && !HTTP.CONN_KEEP_ALIVE.equalsIgnoreCase(str) && !AUTH.PROXY_AUTH.equalsIgnoreCase(str) && !"Proxy-Authorization".equalsIgnoreCase(str) && !"TE".equalsIgnoreCase(str) && !"Trailers".equalsIgnoreCase(str) && !HTTP.TRANSFER_ENCODING.equalsIgnoreCase(str) && !"Upgrade".equalsIgnoreCase(str);
    }
}
