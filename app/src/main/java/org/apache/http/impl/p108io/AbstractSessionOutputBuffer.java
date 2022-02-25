package org.apache.http.impl.p108io;

import com.tendcloud.tenddata.C2771ci;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.p109io.HttpTransportMetrics;
import org.apache.http.p109io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractSessionOutputBuffer */
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class AbstractSessionOutputBuffer implements SessionOutputBuffer {
    private static final byte[] CRLF = {C2771ci.f13672f, 10};
    private static final int MAX_CHUNK = 256;
    private ByteArrayBuffer buffer;
    private HttpTransportMetricsImpl metrics;
    private OutputStream outstream;
    private String charset = "US-ASCII";
    private boolean ascii = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(OutputStream outstream, int buffersize, HttpParams params) {
        if (outstream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        } else if (buffersize <= 0) {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        } else if (params != null) {
            this.outstream = outstream;
            this.buffer = new ByteArrayBuffer(buffersize);
            this.charset = HttpProtocolParams.getHttpElementCharset(params);
            this.ascii = this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase(HTTP.ASCII);
            this.metrics = new HttpTransportMetricsImpl();
        } else {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
    }

    protected void flushBuffer() throws IOException {
        int len = this.buffer.length();
        if (len > 0) {
            this.outstream.write(this.buffer.buffer(), 0, len);
            this.buffer.clear();
            this.metrics.incrementBytesTransferred(len);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void flush() throws IOException {
        flushBuffer();
        this.outstream.flush();
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(byte[] b, int off, int len) throws IOException {
        if (b != null) {
            if (len > 256 || len > this.buffer.capacity()) {
                flushBuffer();
                this.outstream.write(b, off, len);
                this.metrics.incrementBytesTransferred(len);
                return;
            }
            int freecapacity = this.buffer.capacity() - this.buffer.length();
            if (len > freecapacity) {
                flushBuffer();
            }
            this.buffer.append(b, off, len);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(byte[] b) throws IOException {
        if (b != null) {
            write(b, 0, b.length);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(int b) throws IOException {
        if (this.buffer.isFull()) {
            flushBuffer();
        }
        this.buffer.append(b);
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void writeLine(String s) throws IOException {
        if (s != null) {
            if (s.length() > 0) {
                write(s.getBytes(this.charset));
            }
            write(CRLF);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void writeLine(CharArrayBuffer s) throws IOException {
        if (s != null) {
            if (this.ascii) {
                int off = 0;
                int remaining = s.length();
                while (remaining > 0) {
                    int chunk = Math.min(this.buffer.capacity() - this.buffer.length(), remaining);
                    if (chunk > 0) {
                        this.buffer.append(s, off, chunk);
                    }
                    if (this.buffer.isFull()) {
                        flushBuffer();
                    }
                    off += chunk;
                    remaining -= chunk;
                }
            } else {
                byte[] tmp = s.toString().getBytes(this.charset);
                write(tmp);
            }
            byte[] tmp2 = CRLF;
            write(tmp2);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
