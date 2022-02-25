package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public class SyncHttpHandler {
    private String charset;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private HttpRedirectHandler httpRedirectHandler;
    private String requestMethod;
    private String requestUrl;
    private int retriedTimes = 0;
    private long expiry = HttpCache.getDefaultExpiryTime();

    public void setHttpRedirectHandler(HttpRedirectHandler httpRedirectHandler) {
        this.httpRedirectHandler = httpRedirectHandler;
    }

    public SyncHttpHandler(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str) {
        this.client = abstractHttpClient;
        this.context = httpContext;
        this.charset = str;
    }

    public void setExpiry(long j) {
        this.expiry = j;
    }

    public ResponseStream sendRequest(HttpRequestBase httpRequestBase) throws HttpException {
        IOException e;
        boolean z;
        String str;
        HttpRequestRetryHandler httpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
        do {
            try {
                this.requestUrl = httpRequestBase.getURI().toString();
                this.requestMethod = httpRequestBase.getMethod();
                if (!HttpUtils.sHttpCache.isEnabled(this.requestMethod) || (str = HttpUtils.sHttpCache.get(this.requestUrl)) == null) {
                    return handleResponse(this.client.execute(httpRequestBase, this.context));
                }
                return new ResponseStream(str);
            } catch (HttpException e2) {
                throw e2;
            } catch (NullPointerException e3) {
                e = new IOException(e3.getMessage());
                e.initCause(e3);
                int i = this.retriedTimes + 1;
                this.retriedTimes = i;
                z = httpRequestRetryHandler.retryRequest(e, i, this.context);
                continue;
            } catch (UnknownHostException e4) {
                e = e4;
                int i2 = this.retriedTimes + 1;
                this.retriedTimes = i2;
                z = httpRequestRetryHandler.retryRequest(e, i2, this.context);
                continue;
            } catch (IOException e5) {
                e = e5;
                int i3 = this.retriedTimes + 1;
                this.retriedTimes = i3;
                z = httpRequestRetryHandler.retryRequest(e, i3, this.context);
                continue;
            } catch (Throwable th) {
                e = new IOException(th.getMessage());
                e.initCause(th);
                int i4 = this.retriedTimes + 1;
                this.retriedTimes = i4;
                z = httpRequestRetryHandler.retryRequest(e, i4, this.context);
                continue;
            }
        } while (z);
        throw new HttpException(e);
    }

    private ResponseStream handleResponse(HttpResponse httpResponse) throws HttpException, IOException {
        if (httpResponse != null) {
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode < 300) {
                ResponseStream responseStream = new ResponseStream(httpResponse, this.charset, this.requestUrl, this.expiry);
                responseStream.setRequestMethod(this.requestMethod);
                return responseStream;
            } else if (statusCode == 301 || statusCode == 302) {
                if (this.httpRedirectHandler == null) {
                    this.httpRedirectHandler = new DefaultHttpRedirectHandler();
                }
                HttpRequestBase directRequest = this.httpRedirectHandler.getDirectRequest(httpResponse);
                if (directRequest != null) {
                    return sendRequest(directRequest);
                }
                return null;
            } else if (statusCode == 416) {
                throw new HttpException(statusCode, "maybe the file has downloaded completely");
            } else {
                throw new HttpException(statusCode, statusLine.getReasonPhrase());
            }
        } else {
            throw new HttpException("response is null");
        }
    }
}
