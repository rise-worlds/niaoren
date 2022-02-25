package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.p109io.HttpTransportMetrics;
import org.apache.http.p109io.SessionOutputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer out, Wire wire) {
        this.out = out;
        this.wire = wire;
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        if (this.wire.enabled()) {
            this.wire.output(b, off, len);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(int b) throws IOException {
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void write(byte[] b) throws IOException {
        this.out.write(b);
        if (this.wire.enabled()) {
            this.wire.output(b);
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void writeLine(CharArrayBuffer buffer) throws IOException {
        this.out.writeLine(buffer);
        if (this.wire.enabled()) {
            String s = new String(buffer.buffer(), 0, buffer.length());
            Wire wire = this.wire;
            wire.output(s + "[EOL]");
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public void writeLine(String s) throws IOException {
        this.out.writeLine(s);
        if (this.wire.enabled()) {
            Wire wire = this.wire;
            wire.output(s + "[EOL]");
        }
    }

    @Override // org.apache.http.p109io.SessionOutputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }
}
