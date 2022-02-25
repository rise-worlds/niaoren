package com.p018b.p019a.p020a.p023c;

import com.github.kevinsawicki.http.HttpRequest;
import com.p018b.p019a.AbstractC0899ag;
import com.p018b.p019a.C0903ap;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.Cookie;
import com.p018b.p019a.CookieJar;
import com.p018b.p019a.Headers;
import com.p018b.p019a.Interceptor;
import com.p018b.p019a.MediaType;
import com.p018b.p019a.Request;
import com.p018b.p019a.RequestBody;
import com.p018b.p019a.Response;
import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.GzipSource;
import com.p018b.p029b.Okio;
import java.util.List;
import org.apache.http.cookie.AbstractC3144SM;
import org.apache.http.protocol.HTTP;

/* renamed from: com.b.a.a.c.a */
/* loaded from: classes.dex */
public final class BridgeInterceptor implements Interceptor {

    /* renamed from: a */
    private final CookieJar f5793a;

    public BridgeInterceptor(CookieJar sVar) {
        this.f5793a = sVar;
    }

    @Override // com.p018b.p019a.Interceptor
    /* renamed from: a */
    public final Response mo24513a(AbstractC0899ag agVar) {
        boolean z;
        Request a = agVar.mo24512a();
        C0903ap e = a.m24466e();
        RequestBody d = a.m24467d();
        if (d != null) {
            MediaType a2 = d.mo24366a();
            if (a2 != null) {
                e.m24457a("Content-Type", a2.toString());
            }
            long b = d.mo24363b();
            if (b != -1) {
                e.m24457a("Content-Length", Long.toString(b));
                e.m24456b(HTTP.TRANSFER_ENCODING);
            } else {
                e.m24457a(HTTP.TRANSFER_ENCODING, HTTP.CHUNK_CODING);
                e.m24456b("Content-Length");
            }
        }
        if (a.m24470a(HTTP.TARGET_HOST) == null) {
            e.m24457a(HTTP.TARGET_HOST, Util.m24768a(a.m24471a(), false));
        }
        if (a.m24470a(HTTP.CONN_DIRECTIVE) == null) {
            e.m24457a(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        }
        if (a.m24470a(HttpRequest.HEADER_ACCEPT_ENCODING) == null && a.m24470a("Range") == null) {
            e.m24457a(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
            z = true;
        } else {
            z = false;
        }
        List<Cookie> b2 = this.f5793a.mo24377b();
        if (!b2.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = b2.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                Cookie rVar = b2.get(i);
                sb.append(rVar.m24384a());
                sb.append('=');
                sb.append(rVar.m24379b());
            }
            e.m24457a(AbstractC3144SM.COOKIE, sb.toString());
        }
        if (a.m24470a("User-Agent") == null) {
            e.m24457a("User-Agent", "okhttp/3.6.0");
        }
        Response a3 = agVar.mo24511a(e.m24463a());
        HttpHeaders.m24741a(this.f5793a, a.m24471a(), a3.m24450d());
        C0905at a4 = a3.m24448f().m24439a(a);
        if (z && HttpRequest.ENCODING_GZIP.equalsIgnoreCase(a3.m24453a("Content-Encoding")) && HttpHeaders.m24737b(a3)) {
            GzipSource lVar = new GzipSource(a3.m24449e().mo24425c());
            Headers a5 = a3.m24450d().m24556b().m24553a("Content-Encoding").m24553a("Content-Length").m24554a();
            a4.m24441a(a5);
            a4.m24437a(new RealResponseBody(a5, Okio.m24305a(lVar)));
        }
        return a4.m24444a();
    }
}
