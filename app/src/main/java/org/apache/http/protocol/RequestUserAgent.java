package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.params.HttpProtocolParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestUserAgent implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        String useragent;
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (!request.containsHeader("User-Agent") && (useragent = HttpProtocolParams.getUserAgent(request.getParams())) != null) {
            request.addHeader("User-Agent", useragent);
        }
    }
}
