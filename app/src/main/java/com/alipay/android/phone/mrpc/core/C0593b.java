package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.net.http.Headers;
import android.util.Base64;
import android.util.Log;
import com.tendcloud.tenddata.AbstractC2752bw;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.AbstractC3144SM;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.C3894au;

/* renamed from: com.alipay.android.phone.mrpc.core.b */
/* loaded from: classes.dex */
public final class C0593b implements HttpClient {

    /* renamed from: a */
    public static long f137a = 160;

    /* renamed from: b */
    private static String[] f138b = {"text/", "application/xml", "application/json"};

    /* renamed from: c */
    private static final HttpRequestInterceptor f139c = new C0596c();

    /* renamed from: d */
    private final HttpClient f140d;

    /* renamed from: e */
    private RuntimeException f141e = new IllegalStateException("AndroidHttpClient created and never closed");

    /* renamed from: f */
    private volatile C0595b f142f;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alipay.android.phone.mrpc.core.b$a */
    /* loaded from: classes.dex */
    public class C0594a implements HttpRequestInterceptor {
        private C0594a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ C0594a(C0593b bVar, byte b) {
            this();
        }

        @Override // org.apache.http.HttpRequestInterceptor
        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            C0595b bVar = C0593b.this.f142f;
            if (bVar != null && C0595b.m25502a(bVar) && (httpRequest instanceof HttpUriRequest)) {
                C0595b.m25501a(bVar, C0593b.m25507a((HttpUriRequest) httpRequest));
            }
        }
    }

    /* renamed from: com.alipay.android.phone.mrpc.core.b$b */
    /* loaded from: classes.dex */
    private static class C0595b {

        /* renamed from: a */
        private final String f144a;

        /* renamed from: b */
        private final int f145b;

        /* renamed from: a */
        static /* synthetic */ void m25501a(C0595b bVar, String str) {
            Log.println(bVar.f145b, bVar.f144a, str);
        }

        /* renamed from: a */
        static /* synthetic */ boolean m25502a(C0595b bVar) {
            return Log.isLoggable(bVar.f144a, bVar.f145b);
        }
    }

    private C0593b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f140d = new C0597d(this, clientConnectionManager, httpParams);
    }

    /* renamed from: a */
    public static C0593b m25511a(String str) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, C3894au.f17525h);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(30000, null), AbstractC2752bw.f13639b));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, WaitFor.ONE_MINUTE);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new C0593b(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: a */
    public static InputStream m25510a(HttpEntity httpEntity) {
        Header contentEncoding;
        String value;
        InputStream content = httpEntity.getContent();
        if (!(content == null || (contentEncoding = httpEntity.getContentEncoding()) == null || (value = contentEncoding.getValue()) == null || !value.contains(com.github.kevinsawicki.http.HttpRequest.ENCODING_GZIP))) {
            return new GZIPInputStream(content);
        }
        return content;
    }

    /* renamed from: a */
    static /* synthetic */ String m25507a(HttpUriRequest httpUriRequest) {
        Header[] allHeaders;
        HttpEntity entity;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("curl ");
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (!header.getName().equals("Authorization") && !header.getName().equals(AbstractC3144SM.COOKIE)) {
                sb.append("--header \"");
                sb.append(header.toString().trim());
                sb.append("\" ");
            }
        }
        URI uri = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            HttpRequest original = ((RequestWrapper) httpUriRequest).getOriginal();
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest) original).getURI();
            }
        }
        sb.append("\"");
        sb.append(uri);
        sb.append("\"");
        if ((httpUriRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity()) != null && entity.isRepeatable()) {
            if (entity.getContentLength() < 1024) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                if (m25503b(httpUriRequest)) {
                    sb.insert(0, "echo '" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2) + "' | base64 -d > /tmp/$$.bin; ");
                    str = " --data-binary @/tmp/$$.bin";
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    sb.append(" --data-ascii \"");
                    sb.append(byteArrayOutputStream2);
                    str = "\"";
                }
            } else {
                str = " [TOO MUCH DATA TO INCLUDE]";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static AbstractHttpEntity m25506a(byte[] bArr) {
        if (bArr.length < f137a) {
            return new ByteArrayEntity(bArr);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding(com.github.kevinsawicki.http.HttpRequest.ENCODING_GZIP);
        StringBuilder sb = new StringBuilder("gzip size:");
        sb.append(bArr.length);
        sb.append("->");
        sb.append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    /* renamed from: a */
    public static void m25509a(HttpRequest httpRequest) {
        httpRequest.addHeader(com.github.kevinsawicki.http.HttpRequest.HEADER_ACCEPT_ENCODING, com.github.kevinsawicki.http.HttpRequest.ENCODING_GZIP);
    }

    /* renamed from: b */
    public static long m25505b(String str) {
        return C0604k.m25495a(str);
    }

    /* renamed from: b */
    public static void m25504b(HttpRequest httpRequest) {
        httpRequest.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
    }

    /* renamed from: b */
    private static boolean m25503b(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders(Headers.CONTENT_ENCODING);
        if (headers != null) {
            for (Header header : headers) {
                if (com.github.kevinsawicki.http.HttpRequest.ENCODING_GZIP.equalsIgnoreCase(header.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders("content-type");
        if (headers2 != null) {
            for (Header header2 : headers2) {
                for (String str : f138b) {
                    if (header2.getValue().startsWith(str)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void m25508a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.f140d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return (T) this.f140d.execute(httpHost, httpRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T) this.f140d.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return (T) this.f140d.execute(httpUriRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return (T) this.f140d.execute(httpUriRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.f140d.execute(httpHost, httpRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.f140d.execute(httpHost, httpRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.f140d.execute(httpUriRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.f140d.execute(httpUriRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final ClientConnectionManager getConnectionManager() {
        return this.f140d.getConnectionManager();
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpParams getParams() {
        return this.f140d.getParams();
    }
}
