package org.apache.http.impl.p108io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.p109io.HttpTransportMetrics;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractSessionInputBuffer */
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class AbstractSessionInputBuffer implements SessionInputBuffer {
    private byte[] buffer;
    private int bufferlen;
    private int bufferpos;
    private InputStream instream;
    private HttpTransportMetricsImpl metrics;
    private ByteArrayBuffer linebuffer = null;
    private String charset = "US-ASCII";
    private boolean ascii = true;
    private int maxLineLen = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(InputStream instream, int buffersize, HttpParams params) {
        if (instream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        } else if (buffersize <= 0) {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        } else if (params != null) {
            this.instream = instream;
            this.buffer = new byte[buffersize];
            boolean z = false;
            this.bufferpos = 0;
            this.bufferlen = 0;
            this.linebuffer = new ByteArrayBuffer(buffersize);
            this.charset = HttpProtocolParams.getHttpElementCharset(params);
            if (this.charset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase(HTTP.ASCII)) {
                z = true;
            }
            this.ascii = z;
            this.maxLineLen = params.getIntParameter("http.connection.max-line-length", -1);
            this.metrics = new HttpTransportMetricsImpl();
        } else {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int fillBuffer() throws IOException {
        if (this.bufferpos > 0) {
            int len = this.bufferlen - this.bufferpos;
            if (len > 0) {
                System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, len);
            }
            this.bufferpos = 0;
            this.bufferlen = len;
        }
        int len2 = this.bufferlen;
        int l = this.instream.read(this.buffer, len2, this.buffer.length - len2);
        if (l == -1) {
            return -1;
        }
        this.bufferlen = len2 + l;
        this.metrics.incrementBytesTransferred(l);
        return l;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read() throws IOException {
        while (!hasBufferedData()) {
            int noRead = fillBuffer();
            if (noRead == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufferpos;
        this.bufferpos = i + 1;
        return bArr[i] & 255;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            return 0;
        }
        while (!hasBufferedData()) {
            int noRead = fillBuffer();
            if (noRead == -1) {
                return -1;
            }
        }
        int chunk = this.bufferlen - this.bufferpos;
        if (chunk > len) {
            chunk = len;
        }
        System.arraycopy(this.buffer, this.bufferpos, b, off, chunk);
        this.bufferpos += chunk;
        return chunk;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read(byte[] b) throws IOException {
        if (b == null) {
            return 0;
        }
        return read(b, 0, b.length);
    }

    private int locateLF() {
        for (int i = this.bufferpos; i < this.bufferlen; i++) {
            if (this.buffer[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int readLine(CharArrayBuffer charbuffer) throws IOException {
        if (charbuffer != null) {
            this.linebuffer.clear();
            int noRead = 0;
            boolean retry = true;
            while (retry) {
                int i = locateLF();
                if (i == -1) {
                    if (hasBufferedData()) {
                        int len = this.bufferlen - this.bufferpos;
                        this.linebuffer.append(this.buffer, this.bufferpos, len);
                        this.bufferpos = this.bufferlen;
                    }
                    noRead = fillBuffer();
                    if (noRead == -1) {
                        retry = false;
                    }
                } else if (this.linebuffer.isEmpty()) {
                    return lineFromReadBuffer(charbuffer, i);
                } else {
                    retry = false;
                    int len2 = (i + 1) - this.bufferpos;
                    this.linebuffer.append(this.buffer, this.bufferpos, len2);
                    this.bufferpos = i + 1;
                }
                if (this.maxLineLen > 0 && this.linebuffer.length() >= this.maxLineLen) {
                    throw new IOException("Maximum line length limit exceeded");
                }
            }
            if (noRead != -1 || !this.linebuffer.isEmpty()) {
                return lineFromLineBuffer(charbuffer);
            }
            return -1;
        }
        throw new IllegalArgumentException("Char array buffer may not be null");
    }

    private int lineFromLineBuffer(CharArrayBuffer charbuffer) throws IOException {
        int l = this.linebuffer.length();
        if (l > 0) {
            if (this.linebuffer.byteAt(l - 1) == 10) {
                l--;
                this.linebuffer.setLength(l);
            }
            if (l > 0 && this.linebuffer.byteAt(l - 1) == 13) {
                this.linebuffer.setLength(l - 1);
            }
        }
        int l2 = this.linebuffer.length();
        if (this.ascii) {
            charbuffer.append(this.linebuffer, 0, l2);
        } else {
            String s = new String(this.linebuffer.buffer(), 0, l2, this.charset);
            charbuffer.append(s);
        }
        return l2;
    }

    private int lineFromReadBuffer(CharArrayBuffer charbuffer, int pos) throws IOException {
        int off = this.bufferpos;
        this.bufferpos = pos + 1;
        if (pos > off && this.buffer[pos - 1] == 13) {
            pos--;
        }
        int len = pos - off;
        if (this.ascii) {
            charbuffer.append(this.buffer, off, len);
        } else {
            String s = new String(this.buffer, off, len, this.charset);
            charbuffer.append(s);
        }
        return len;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public String readLine() throws IOException {
        CharArrayBuffer charbuffer = new CharArrayBuffer(64);
        int l = readLine(charbuffer);
        if (l != -1) {
            return charbuffer.toString();
        }
        return null;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
