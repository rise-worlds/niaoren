package com.p018b.p019a.p020a.p025e;

import android.net.http.Headers;
import com.p018b.p019a.C0896ab;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.OkHttpClient;
import com.p018b.p019a.Protocol;
import com.p018b.p019a.Request;
import com.p018b.p019a.Response;
import com.p018b.p019a.ResponseBody;
import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import com.p018b.p019a.p020a.p023c.HttpCodec;
import com.p018b.p019a.p020a.p023c.RealResponseBody;
import com.p018b.p019a.p020a.p023c.RequestLine;
import com.p018b.p019a.p020a.p023c.StatusLine;
import com.p018b.p029b.ByteString;
import com.p018b.p029b.Okio;
import com.p018b.p029b.Sink;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.http.protocol.HTTP;
import p110z1.C3883at;

/* renamed from: com.b.a.a.e.h */
/* loaded from: classes.dex */
public final class Http2Codec implements HttpCodec {

    /* renamed from: b */
    private static final ByteString f5929b = ByteString.m24315a(Headers.CONN_DIRECTIVE);

    /* renamed from: c */
    private static final ByteString f5930c = ByteString.m24315a(C3883at.f17482f);

    /* renamed from: d */
    private static final ByteString f5931d = ByteString.m24315a("keep-alive");

    /* renamed from: e */
    private static final ByteString f5932e = ByteString.m24315a(Headers.PROXY_CONNECTION);

    /* renamed from: f */
    private static final ByteString f5933f = ByteString.m24315a(Headers.TRANSFER_ENCODING);

    /* renamed from: g */
    private static final ByteString f5934g = ByteString.m24315a("te");

    /* renamed from: h */
    private static final ByteString f5935h = ByteString.m24315a("encoding");

    /* renamed from: i */
    private static final ByteString f5936i = ByteString.m24315a("upgrade");

    /* renamed from: j */
    private static final List<ByteString> f5937j = Util.m24752a(f5929b, f5930c, f5931d, f5932e, f5934g, f5933f, f5935h, f5936i, Header.f5898c, Header.f5899d, Header.f5900e, Header.f5901f);

    /* renamed from: k */
    private static final List<ByteString> f5938k = Util.m24752a(f5929b, f5930c, f5931d, f5932e, f5934g, f5933f, f5935h, f5936i);

    /* renamed from: a */
    final StreamAllocation f5939a;

    /* renamed from: l */
    private final OkHttpClient f5940l;

    /* renamed from: m */
    private final Http2Connection f5941m;

    /* renamed from: n */
    private Http2Stream f5942n;

    public Http2Codec(OkHttpClient aiVar, StreamAllocation gVar, Http2Connection jVar) {
        this.f5940l = aiVar;
        this.f5939a = gVar;
        this.f5941m = jVar;
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final Sink mo24639a(Request aoVar, long j) {
        return this.f5942n.m24707e();
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final void mo24640a(Request aoVar) {
        if (this.f5942n == null) {
            boolean z = aoVar.m24467d() != null;
            com.p018b.p019a.Headers c = aoVar.m24468c();
            ArrayList arrayList = new ArrayList(c.m24559a() + 4);
            arrayList.add(new Header(Header.f5898c, aoVar.m24469b()));
            arrayList.add(new Header(Header.f5899d, RequestLine.m24730a(aoVar.m24471a())));
            String a = aoVar.m24470a(HTTP.TARGET_HOST);
            if (a != null) {
                arrayList.add(new Header(Header.f5901f, a));
            }
            arrayList.add(new Header(Header.f5900e, aoVar.m24471a().m24538b()));
            int a2 = c.m24559a();
            for (int i = 0; i < a2; i++) {
                ByteString a3 = ByteString.m24315a(c.m24558a(i).toLowerCase(Locale.US));
                if (!f5937j.contains(a3)) {
                    arrayList.add(new Header(a3, c.m24555b(i)));
                }
            }
            this.f5942n = this.f5941m.m24626a(arrayList, z);
            this.f5942n.f5847f.mo24242a(this.f5940l.m24505b(), TimeUnit.MILLISECONDS);
            this.f5942n.f5848g.mo24242a(this.f5940l.m24504c(), TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final void mo24641a() {
        this.f5941m.f5960p.m24689b();
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: b */
    public final void mo24636b() {
        this.f5942n.m24707e().close();
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final C0905at mo24637a(boolean z) {
        List<Header> c = this.f5942n.m24711c();
        C0896ab abVar = new C0896ab();
        int size = c.size();
        C0896ab abVar2 = abVar;
        StatusLine lVar = null;
        for (int i = 0; i < size; i++) {
            Header cVar = c.get(i);
            if (cVar != null) {
                ByteString iVar = cVar.f5902g;
                String a = cVar.f5903h.mo24267a();
                if (iVar.equals(Header.f5897b)) {
                    lVar = StatusLine.m24724a("HTTP/1.1 " + a);
                } else if (!f5938k.contains(iVar)) {
                    Internal.f5689a.mo24485a(abVar2, iVar.mo24267a(), a);
                }
            } else if (lVar != null && lVar.f5814b == 100) {
                abVar2 = new C0896ab();
                lVar = null;
            }
        }
        if (lVar != null) {
            C0905at a2 = new C0905at().m24440a(Protocol.HTTP_2).m24443a(lVar.f5814b).m24435a(lVar.f5815c).m24441a(abVar2.m24554a());
            if (!z || Internal.f5689a.mo24484a(a2) != 100) {
                return a2;
            }
            return null;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // com.p018b.p019a.p020a.p023c.HttpCodec
    /* renamed from: a */
    public final ResponseBody mo24638a(Response asVar) {
        return new RealResponseBody(asVar.m24450d(), Okio.m24305a(new C0873i(this, this.f5942n.m24709d())));
    }
}
