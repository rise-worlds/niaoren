package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestConnControl implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (!request.containsHeader(HTTP.CONN_DIRECTIVE)) {
            request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        }
    }
}
