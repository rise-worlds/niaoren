package com.p018b.p019a.p020a.p025e;

import android.net.http.Headers;
import com.p018b.p029b.ByteString;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import p110z1.C3883at;

/* renamed from: com.b.a.a.e.d */
/* loaded from: classes.dex */
final class Hpack {

    /* renamed from: a */
    static final Header[] f5905a = {new Header(Header.f5901f, ""), new Header(Header.f5898c, "GET"), new Header(Header.f5898c, "POST"), new Header(Header.f5899d, "/"), new Header(Header.f5899d, "/index.html"), new Header(Header.f5900e, HttpHost.DEFAULT_SCHEME_NAME), new Header(Header.f5900e, "https"), new Header(Header.f5897b, "200"), new Header(Header.f5897b, "204"), new Header(Header.f5897b, "206"), new Header(Header.f5897b, "304"), new Header(Header.f5897b, "400"), new Header(Header.f5897b, "404"), new Header(Header.f5897b, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header(Headers.ACCEPT_RANGES, ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header(Headers.CACHE_CONTROL, ""), new Header(Headers.CONTENT_DISPOSITION, ""), new Header(Headers.CONTENT_ENCODING, ""), new Header("content-language", ""), new Header(Headers.CONTENT_LEN, ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header(C3883at.f17482f, ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header(Headers.LAST_MODIFIED, ""), new Header("link", ""), new Header(Headers.LOCATION, ""), new Header("max-forwards", ""), new Header(Headers.PROXY_AUTHENTICATE, ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header(Headers.SET_COOKIE, ""), new Header("strict-transport-security", ""), new Header(Headers.TRANSFER_ENCODING, ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header(Headers.WWW_AUTHENTICATE, "")};

    /* renamed from: b */
    static final Map<ByteString, Integer> f5906b;

    static {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap(f5905a.length);
        while (true) {
            Header[] cVarArr = f5905a;
            if (i < cVarArr.length) {
                if (!linkedHashMap.containsKey(cVarArr[i].f5902g)) {
                    linkedHashMap.put(f5905a[i].f5902g, Integer.valueOf(i));
                }
                i++;
            } else {
                f5906b = Collections.unmodifiableMap(linkedHashMap);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static ByteString m24664a(ByteString iVar) {
        int g = iVar.mo24255g();
        for (int i = 0; i < g; i++) {
            byte a = iVar.mo24266a(i);
            if (a >= 65 && a <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + iVar.mo24267a());
            }
        }
        return iVar;
    }
}
