package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.p108io.ChunkedOutputStream;
import org.apache.http.impl.p108io.ContentLengthOutputStream;
import org.apache.http.impl.p108io.IdentityOutputStream;
import org.apache.http.p109io.SessionOutputBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy lenStrategy) {
        if (lenStrategy != null) {
            this.lenStrategy = lenStrategy;
            return;
        }
        throw new IllegalArgumentException("Content length strategy may not be null");
    }

    protected OutputStream doSerialize(SessionOutputBuffer outbuffer, HttpMessage message) throws HttpException, IOException {
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            return new ChunkedOutputStream(outbuffer);
        }
        if (len == -1) {
            return new IdentityOutputStream(outbuffer);
        }
        return new ContentLengthOutputStream(outbuffer, len);
    }

    public void serialize(SessionOutputBuffer outbuffer, HttpMessage message, HttpEntity entity) throws HttpException, IOException {
        if (outbuffer == null) {
            throw new IllegalArgumentException("Session output buffer may not be null");
        } else if (message == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        } else if (entity != null) {
            OutputStream outstream = doSerialize(outbuffer, message);
            entity.writeTo(outstream);
            outstream.close();
        } else {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
    }
}
