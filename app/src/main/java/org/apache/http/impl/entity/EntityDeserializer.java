package org.apache.http.impl.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.p108io.ChunkedInputStream;
import org.apache.http.impl.p108io.ContentLengthInputStream;
import org.apache.http.impl.p108io.IdentityInputStream;
import org.apache.http.p109io.SessionInputBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public EntityDeserializer(ContentLengthStrategy lenStrategy) {
        if (lenStrategy != null) {
            this.lenStrategy = lenStrategy;
            return;
        }
        throw new IllegalArgumentException("Content length strategy may not be null");
    }

    protected BasicHttpEntity doDeserialize(SessionInputBuffer inbuffer, HttpMessage message) throws HttpException, IOException {
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1L);
            entity.setContent(new ChunkedInputStream(inbuffer));
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1L);
            entity.setContent(new IdentityInputStream(inbuffer));
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(new ContentLengthInputStream(inbuffer, len));
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        Header contentEncodingHeader = message.getFirstHeader("Content-Encoding");
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    public HttpEntity deserialize(SessionInputBuffer inbuffer, HttpMessage message) throws HttpException, IOException {
        if (inbuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        } else if (message != null) {
            return doDeserialize(inbuffer, message);
        } else {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
    }
}
