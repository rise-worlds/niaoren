package org.apache.http.protocol;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class ResponseContent implements HttpResponseInterceptor {
    @Override // org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        if (response == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (response.containsHeader(HTTP.TRANSFER_ENCODING)) {
            throw new ProtocolException("Transfer-encoding header already present");
        } else if (!response.containsHeader("Content-Length")) {
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (entity.isChunked() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    response.addHeader(HTTP.TRANSFER_ENCODING, HTTP.CHUNK_CODING);
                } else if (len >= 0) {
                    response.addHeader("Content-Length", Long.toString(entity.getContentLength()));
                }
                if (entity.getContentType() != null && !response.containsHeader("Content-Type")) {
                    response.addHeader(entity.getContentType());
                }
                if (entity.getContentEncoding() != null && !response.containsHeader("Content-Encoding")) {
                    response.addHeader(entity.getContentEncoding());
                    return;
                }
                return;
            }
            int status = response.getStatusLine().getStatusCode();
            if (status != 204 && status != 304 && status != 205) {
                response.addHeader("Content-Length", ResultTypeConstant.f7213z);
            }
        } else {
            throw new ProtocolException("Content-Length header already present");
        }
    }
}
