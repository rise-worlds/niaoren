package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.github.kevinsawicki.http.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import p110z1.C4963cj;
import p110z1.SimpleComparison;

/* renamed from: com.alipay.android.phone.mrpc.core.q */
/* loaded from: classes.dex */
public final class CallableC0611q implements Callable<C0615u> {

    /* renamed from: e */
    private static final HttpRequestRetryHandler f183e = new C0592ad();

    /* renamed from: a */
    protected C0606l f184a;

    /* renamed from: b */
    protected Context f185b;

    /* renamed from: c */
    protected C0609o f186c;

    /* renamed from: d */
    String f187d;

    /* renamed from: f */
    private HttpUriRequest f188f;

    /* renamed from: i */
    private CookieManager f191i;

    /* renamed from: j */
    private AbstractHttpEntity f192j;

    /* renamed from: k */
    private HttpHost f193k;

    /* renamed from: l */
    private URL f194l;

    /* renamed from: q */
    private String f199q;

    /* renamed from: g */
    private HttpContext f189g = new BasicHttpContext();

    /* renamed from: h */
    private CookieStore f190h = new BasicCookieStore();

    /* renamed from: m */
    private int f195m = 0;

    /* renamed from: n */
    private boolean f196n = false;

    /* renamed from: o */
    private boolean f197o = false;

    /* renamed from: p */
    private String f198p = null;

    public CallableC0611q(C0606l lVar, C0609o oVar) {
        this.f184a = lVar;
        this.f185b = this.f184a.f161a;
        this.f186c = oVar;
    }

    /* renamed from: a */
    private static long m25463a(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (ClientCookie.MAX_AGE_ATTR.equalsIgnoreCase(strArr[i])) {
                int i2 = i + 1;
                if (strArr[i2] != null) {
                    try {
                        return Long.parseLong(strArr[i2]);
                    } catch (Exception unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return 0L;
    }

    /* renamed from: a */
    private static HttpUrlHeader m25465a(HttpResponse httpResponse) {
        Header[] allHeaders;
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* renamed from: a */
    private C0615u m25464a(HttpResponse httpResponse, int i, String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        String str2;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        String str3 = null;
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    m25466a(entity, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    this.f197o = false;
                    this.f184a.m25484c(System.currentTimeMillis() - currentTimeMillis);
                    this.f184a.m25489a(byteArray.length);
                    new StringBuilder("res:").append(byteArray.length);
                    C0610p pVar = new C0610p(m25465a(httpResponse), i, str, byteArray);
                    long b = m25461b(httpResponse);
                    Header contentType = httpResponse.getEntity().getContentType();
                    if (contentType != null) {
                        HashMap<String, String> a = m25467a(contentType.getValue());
                        str3 = a.get(HttpRequest.PARAM_CHARSET);
                        str2 = a.get("Content-Type");
                    } else {
                        str2 = null;
                    }
                    pVar.m25447b(str2);
                    pVar.m25470a(str3);
                    pVar.m25471a(System.currentTimeMillis());
                    pVar.m25469b(b);
                    try {
                        byteArrayOutputStream.close();
                        return pVar;
                    } catch (IOException e) {
                        throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
            }
        } else if (entity != null) {
            return null;
        } else {
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
    }

    /* renamed from: a */
    private static HashMap<String, String> m25467a(String str) {
        String[] split;
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(C4963cj.f20745b)) {
            String[] split2 = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split(SimpleComparison.f23609c);
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m25466a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a = C0593b.m25510a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = a.read(bArr);
                    if (read == -1 || this.f186c.m25449h()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    if (this.f186c.m25451f() != null) {
                        int i = (contentLength > 0L ? 1 : (contentLength == 0L ? 0 : -1));
                    }
                }
                outputStream.flush();
            } catch (Exception e) {
                e.getCause();
                throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
            }
        } finally {
            C0612r.m25453a(a);
        }
    }

    /* renamed from: b */
    private static long m25461b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(HttpRequest.HEADER_CACHE_CONTROL);
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split(SimpleComparison.f23609c);
            if (split.length >= 2) {
                try {
                    return m25463a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader(HttpRequest.HEADER_EXPIRES);
        if (firstHeader2 != null) {
            return C0593b.m25505b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    /* renamed from: b */
    private URI m25462b() {
        String a = this.f186c.m25483a();
        String str = this.f187d;
        if (str != null) {
            a = str;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    /* renamed from: c */
    private HttpUriRequest m25460c() {
        HttpUriRequest httpUriRequest = this.f188f;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.f192j == null) {
            byte[] b = this.f186c.m25477b();
            String b2 = this.f186c.m25476b(HttpRequest.ENCODING_GZIP);
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.f192j = C0593b.m25506a(b);
                } else {
                    this.f192j = new ByteArrayEntity(b);
                }
                this.f192j.setContentType(this.f186c.m25475c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.f192j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(m25462b());
            httpPost.setEntity(abstractHttpEntity);
            this.f188f = httpPost;
        } else {
            this.f188f = new HttpGet(m25462b());
        }
        return this.f188f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010b A[Catch: Exception -> 0x0264, NullPointerException -> 0x0286, IOException -> 0x02aa, UnknownHostException -> 0x02d4, HttpHostConnectException -> 0x0300, NoHttpResponseException -> 0x0324, SocketTimeoutException -> 0x034f, ConnectTimeoutException -> 0x037a, ConnectionPoolTimeoutException -> 0x03a4, SSLException -> 0x03ce, SSLPeerUnverifiedException -> 0x03f8, SSLHandshakeException -> 0x0422, URISyntaxException -> 0x044c, HttpException -> 0x0459, TryCatch #3 {HttpException -> 0x0459, NullPointerException -> 0x0286, SocketTimeoutException -> 0x034f, URISyntaxException -> 0x044c, UnknownHostException -> 0x02d4, SSLHandshakeException -> 0x0422, SSLPeerUnverifiedException -> 0x03f8, SSLException -> 0x03ce, NoHttpResponseException -> 0x0324, ConnectionPoolTimeoutException -> 0x03a4, ConnectTimeoutException -> 0x037a, HttpHostConnectException -> 0x0300, IOException -> 0x02aa, Exception -> 0x0264, blocks: (B:3:0x0004, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:43:0x010b, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x015e, B:50:0x0165, B:52:0x016b, B:53:0x016f, B:55:0x0175, B:57:0x0181, B:61:0x01b0, B:62:0x01cc, B:70:0x01e9, B:71:0x0202, B:72:0x0203, B:74:0x020b, B:76:0x0211, B:80:0x021d, B:82:0x0221, B:84:0x0231, B:86:0x0239, B:88:0x0243, B:90:0x0258, B:91:0x0263), top: B:176:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012b A[Catch: Exception -> 0x0264, NullPointerException -> 0x0286, IOException -> 0x02aa, UnknownHostException -> 0x02d4, HttpHostConnectException -> 0x0300, NoHttpResponseException -> 0x0324, SocketTimeoutException -> 0x034f, ConnectTimeoutException -> 0x037a, ConnectionPoolTimeoutException -> 0x03a4, SSLException -> 0x03ce, SSLPeerUnverifiedException -> 0x03f8, SSLHandshakeException -> 0x0422, URISyntaxException -> 0x044c, HttpException -> 0x0459, TryCatch #3 {HttpException -> 0x0459, NullPointerException -> 0x0286, SocketTimeoutException -> 0x034f, URISyntaxException -> 0x044c, UnknownHostException -> 0x02d4, SSLHandshakeException -> 0x0422, SSLPeerUnverifiedException -> 0x03f8, SSLException -> 0x03ce, NoHttpResponseException -> 0x0324, ConnectionPoolTimeoutException -> 0x03a4, ConnectTimeoutException -> 0x037a, HttpHostConnectException -> 0x0300, IOException -> 0x02aa, Exception -> 0x0264, blocks: (B:3:0x0004, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:43:0x010b, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x015e, B:50:0x0165, B:52:0x016b, B:53:0x016f, B:55:0x0175, B:57:0x0181, B:61:0x01b0, B:62:0x01cc, B:70:0x01e9, B:71:0x0202, B:72:0x0203, B:74:0x020b, B:76:0x0211, B:80:0x021d, B:82:0x0221, B:84:0x0231, B:86:0x0239, B:88:0x0243, B:90:0x0258, B:91:0x0263), top: B:176:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015e A[Catch: Exception -> 0x0264, NullPointerException -> 0x0286, IOException -> 0x02aa, UnknownHostException -> 0x02d4, HttpHostConnectException -> 0x0300, NoHttpResponseException -> 0x0324, SocketTimeoutException -> 0x034f, ConnectTimeoutException -> 0x037a, ConnectionPoolTimeoutException -> 0x03a4, SSLException -> 0x03ce, SSLPeerUnverifiedException -> 0x03f8, SSLHandshakeException -> 0x0422, URISyntaxException -> 0x044c, HttpException -> 0x0459, TryCatch #3 {HttpException -> 0x0459, NullPointerException -> 0x0286, SocketTimeoutException -> 0x034f, URISyntaxException -> 0x044c, UnknownHostException -> 0x02d4, SSLHandshakeException -> 0x0422, SSLPeerUnverifiedException -> 0x03f8, SSLException -> 0x03ce, NoHttpResponseException -> 0x0324, ConnectionPoolTimeoutException -> 0x03a4, ConnectTimeoutException -> 0x037a, HttpHostConnectException -> 0x0300, IOException -> 0x02aa, Exception -> 0x0264, blocks: (B:3:0x0004, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:43:0x010b, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x015e, B:50:0x0165, B:52:0x016b, B:53:0x016f, B:55:0x0175, B:57:0x0181, B:61:0x01b0, B:62:0x01cc, B:70:0x01e9, B:71:0x0202, B:72:0x0203, B:74:0x020b, B:76:0x0211, B:80:0x021d, B:82:0x0221, B:84:0x0231, B:86:0x0239, B:88:0x0243, B:90:0x0258, B:91:0x0263), top: B:176:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x016b A[Catch: Exception -> 0x0264, NullPointerException -> 0x0286, IOException -> 0x02aa, UnknownHostException -> 0x02d4, HttpHostConnectException -> 0x0300, NoHttpResponseException -> 0x0324, SocketTimeoutException -> 0x034f, ConnectTimeoutException -> 0x037a, ConnectionPoolTimeoutException -> 0x03a4, SSLException -> 0x03ce, SSLPeerUnverifiedException -> 0x03f8, SSLHandshakeException -> 0x0422, URISyntaxException -> 0x044c, HttpException -> 0x0459, TryCatch #3 {HttpException -> 0x0459, NullPointerException -> 0x0286, SocketTimeoutException -> 0x034f, URISyntaxException -> 0x044c, UnknownHostException -> 0x02d4, SSLHandshakeException -> 0x0422, SSLPeerUnverifiedException -> 0x03f8, SSLException -> 0x03ce, NoHttpResponseException -> 0x0324, ConnectionPoolTimeoutException -> 0x03a4, ConnectTimeoutException -> 0x037a, HttpHostConnectException -> 0x0300, IOException -> 0x02aa, Exception -> 0x0264, blocks: (B:3:0x0004, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:43:0x010b, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x015e, B:50:0x0165, B:52:0x016b, B:53:0x016f, B:55:0x0175, B:57:0x0181, B:61:0x01b0, B:62:0x01cc, B:70:0x01e9, B:71:0x0202, B:72:0x0203, B:74:0x020b, B:76:0x0211, B:80:0x021d, B:82:0x0221, B:84:0x0231, B:86:0x0239, B:88:0x0243, B:90:0x0258, B:91:0x0263), top: B:176:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01e0  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.android.phone.mrpc.core.C0615u call() {
        /*
            Method dump skipped, instructions count: 1140
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.CallableC0611q.call():com.alipay.android.phone.mrpc.core.u");
    }

    /* renamed from: e */
    private void m25458e() {
        HttpUriRequest httpUriRequest = this.f188f;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    /* renamed from: f */
    private String m25457f() {
        if (!TextUtils.isEmpty(this.f199q)) {
            return this.f199q;
        }
        this.f199q = this.f186c.m25476b("operationType");
        return this.f199q;
    }

    /* renamed from: g */
    private int m25456g() {
        URL h = m25455h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    /* renamed from: h */
    private URL m25455h() {
        URL url = this.f194l;
        if (url != null) {
            return url;
        }
        this.f194l = new URL(this.f186c.m25483a());
        return this.f194l;
    }

    /* renamed from: i */
    private CookieManager m25454i() {
        CookieManager cookieManager = this.f191i;
        if (cookieManager != null) {
            return cookieManager;
        }
        this.f191i = CookieManager.getInstance();
        return this.f191i;
    }

    /* renamed from: a */
    public final C0609o m25468a() {
        return this.f186c;
    }
}
