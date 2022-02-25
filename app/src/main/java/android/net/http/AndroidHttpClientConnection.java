package android.net.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.http.HttpConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.p108io.ChunkedInputStream;
import org.apache.http.impl.p108io.ContentLengthInputStream;
import org.apache.http.impl.p108io.HttpRequestWriter;
import org.apache.http.impl.p108io.IdentityInputStream;
import org.apache.http.impl.p108io.SocketInputBuffer;
import org.apache.http.impl.p108io.SocketOutputBuffer;
import org.apache.http.p109io.HttpMessageWriter;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.p109io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* loaded from: assets/org.apache.http.legacy.boot */
public class AndroidHttpClientConnection implements HttpInetConnection, HttpConnection {
    private int maxHeaderCount;
    private int maxLineLength;
    private volatile boolean open;
    private SessionInputBuffer inbuffer = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageWriter requestWriter = null;
    private HttpConnectionMetricsImpl metrics = null;
    private Socket socket = null;
    private final EntitySerializer entityserializer = new EntitySerializer(new StrictContentLengthStrategy());

    public void bind(Socket socket, HttpParams params) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        } else if (params != null) {
            assertNotOpen();
            socket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(params));
            socket.setSoTimeout(HttpConnectionParams.getSoTimeout(params));
            int linger = HttpConnectionParams.getLinger(params);
            if (linger >= 0) {
                socket.setSoLinger(linger > 0, linger);
            }
            this.socket = socket;
            int buffersize = HttpConnectionParams.getSocketBufferSize(params);
            this.inbuffer = new SocketInputBuffer(socket, buffersize, params);
            this.outbuffer = new SocketOutputBuffer(socket, buffersize, params);
            this.maxHeaderCount = params.getIntParameter("http.connection.max-header-count", -1);
            this.maxLineLength = params.getIntParameter("http.connection.max-line-length", -1);
            this.requestWriter = new HttpRequestWriter(this.outbuffer, null, params);
            this.metrics = new HttpConnectionMetricsImpl(this.inbuffer.getMetrics(), this.outbuffer.getMetrics());
            this.open = true;
        } else {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getClass().getSimpleName());
        buffer.append("[");
        if (isOpen()) {
            buffer.append(getRemotePort());
        } else {
            buffer.append("closed");
        }
        buffer.append("]");
        return buffer.toString();
    }

    private void assertNotOpen() {
        if (this.open) {
            throw new IllegalStateException("Connection is already open");
        }
    }

    private void assertOpen() {
        if (!this.open) {
            throw new IllegalStateException("Connection is not open");
        }
    }

    @Override // org.apache.http.HttpConnection
    public boolean isOpen() {
        return this.open && this.socket != null && this.socket.isConnected();
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        if (this.socket != null) {
            return this.socket.getLocalAddress();
        }
        return null;
    }

    @Override // org.apache.http.HttpInetConnection
    public int getLocalPort() {
        if (this.socket != null) {
            return this.socket.getLocalPort();
        }
        return -1;
    }

    @Override // org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        }
        return null;
    }

    @Override // org.apache.http.HttpInetConnection
    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        }
        return -1;
    }

    @Override // org.apache.http.HttpConnection
    public void setSocketTimeout(int timeout) {
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(timeout);
            } catch (SocketException e) {
            }
        }
    }

    @Override // org.apache.http.HttpConnection
    public int getSocketTimeout() {
        if (this.socket == null) {
            return -1;
        }
        try {
            return this.socket.getSoTimeout();
        } catch (SocketException e) {
            return -1;
        }
    }

    @Override // org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        this.open = false;
        Socket tmpsocket = this.socket;
        if (tmpsocket != null) {
            tmpsocket.close();
        }
    }

    @Override // org.apache.http.HttpConnection
    public void close() throws IOException {
        if (this.open) {
            this.open = false;
            doFlush();
            try {
                try {
                    this.socket.shutdownOutput();
                } catch (IOException e) {
                }
                try {
                    this.socket.shutdownInput();
                } catch (IOException e2) {
                }
            } catch (UnsupportedOperationException e3) {
            }
            this.socket.close();
        }
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        if (request != null) {
            assertOpen();
            this.requestWriter.write(request);
            this.metrics.incrementRequestCount();
            return;
        }
        throw new IllegalArgumentException("HTTP request may not be null");
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        if (request != null) {
            assertOpen();
            if (request.getEntity() != null) {
                this.entityserializer.serialize(this.outbuffer, request, request.getEntity());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("HTTP request may not be null");
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        assertOpen();
        doFlush();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c1, code lost:
        if (r9 == null) goto L_0x00c6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c3, code lost:
        r20.parseHeader(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (r6 < 200) goto L_0x00cf;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ca, code lost:
        r19.metrics.incrementResponseCount();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cf, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.http.StatusLine parseResponseHeader(android.net.http.Headers r20) throws java.io.IOException, org.apache.http.ParseException {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.AndroidHttpClientConnection.parseResponseHeader(android.net.http.Headers):org.apache.http.StatusLine");
    }

    public HttpEntity receiveResponseEntity(Headers headers) {
        assertOpen();
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = determineLength(headers);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1L);
            entity.setContent(new ChunkedInputStream(this.inbuffer));
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1L);
            entity.setContent(new IdentityInputStream(this.inbuffer));
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(new ContentLengthInputStream(this.inbuffer, len));
        }
        String contentTypeHeader = headers.getContentType();
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        String contentEncodingHeader = headers.getContentEncoding();
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    private long determineLength(Headers headers) {
        long transferEncoding = headers.getTransferEncoding();
        if (transferEncoding < 0) {
            return transferEncoding;
        }
        long contentlen = headers.getContentLength();
        if (contentlen > -1) {
            return contentlen;
        }
        return -1L;
    }

    @Override // org.apache.http.HttpConnection
    public boolean isStale() {
        assertOpen();
        try {
            this.inbuffer.isDataAvailable(1);
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    @Override // org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
