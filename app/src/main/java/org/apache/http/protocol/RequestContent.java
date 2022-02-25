package org.apache.http.protocol;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestContent implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (!(request instanceof HttpEntityEnclosingRequest)) {
        } else {
            if (request.containsHeader(HTTP.TRANSFER_ENCODING)) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (!request.containsHeader("Content-Length")) {
                ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
                HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
                if (entity == null) {
                    request.addHeader("Content-Length", ResultTypeConstant.f7213z);
                    return;
                }
                if (!entity.isChunked() && entity.getContentLength() >= 0) {
                    request.addHeader("Content-Length", Long.toString(entity.getContentLength()));
                } else if (!ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    request.addHeader(HTTP.TRANSFER_ENCODING, HTTP.CHUNK_CODING);
                } else {
                    throw new ProtocolException("Chunked transfer encoding not allowed for " + ver);
                }
                if (entity.getContentType() != null && !request.containsHeader("Content-Type")) {
                    request.addHeader(entity.getContentType());
                }
                if (entity.getContentEncoding() != null && !request.containsHeader("Content-Encoding")) {
                    request.addHeader(entity.getContentEncoding());
                }
            } else {
                throw new ProtocolException("Content-Length header already present");
            }
        }
    }
}
