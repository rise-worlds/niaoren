package org.apache.http.impl.p108io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.p109io.SessionOutputBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.IdentityOutputStream */
/* loaded from: assets/org.apache.http.legacy.boot */
public class IdentityOutputStream extends OutputStream {
    private boolean closed = false;
    private final SessionOutputBuffer out;

    public IdentityOutputStream(SessionOutputBuffer out) {
        if (out != null) {
            this.out = out;
            return;
        }
        throw new IllegalArgumentException("Session output buffer may not be null");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.closed) {
            this.out.write(b, off, len);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        if (!this.closed) {
            this.out.write(b);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}
