package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.p109io.HttpTransportMetrics;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class LoggingSessionInputBuffer implements SessionInputBuffer {

    /* renamed from: in */
    private final SessionInputBuffer f14726in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire) {
        this.f14726in = in;
        this.wire = wire;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public boolean isDataAvailable(int timeout) throws IOException {
        return this.f14726in.isDataAvailable(timeout);
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read(byte[] b, int off, int len) throws IOException {
        int l = this.f14726in.read(b, off, len);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, off, l);
        }
        return l;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read() throws IOException {
        int l = this.f14726in.read();
        if (this.wire.enabled() && l > 0) {
            this.wire.input(l);
        }
        return l;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int read(byte[] b) throws IOException {
        int l = this.f14726in.read(b);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, 0, l);
        }
        return l;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public String readLine() throws IOException {
        String s = this.f14726in.readLine();
        if (this.wire.enabled() && s != null) {
            Wire wire = this.wire;
            wire.input(s + "[EOL]");
        }
        return s;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public int readLine(CharArrayBuffer buffer) throws IOException {
        int l = this.f14726in.readLine(buffer);
        if (this.wire.enabled() && l > 0) {
            int pos = buffer.length() - l;
            String s = new String(buffer.buffer(), pos, l);
            Wire wire = this.wire;
            wire.input(s + "[EOL]");
        }
        return l;
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.f14726in.getMetrics();
    }
}
