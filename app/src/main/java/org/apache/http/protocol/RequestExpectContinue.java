package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpProtocolParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestExpectContinue implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        HttpEntity entity;
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if ((request instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) request).getEntity()) != null && entity.getContentLength() != 0) {
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if (HttpProtocolParams.useExpectContinue(request.getParams()) && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                request.addHeader(HTTP.EXPECT_DIRECTIVE, HTTP.EXPECT_CONTINUE);
            }
        }
    }
}
