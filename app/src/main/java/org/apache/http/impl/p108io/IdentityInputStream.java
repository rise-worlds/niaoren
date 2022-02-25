package org.apache.http.impl.p108io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.p109io.SessionInputBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.IdentityInputStream */
/* loaded from: assets/org.apache.http.legacy.boot */
public class IdentityInputStream extends InputStream {
    private boolean closed = false;

    /* renamed from: in */
    private final SessionInputBuffer f14729in;

    public IdentityInputStream(SessionInputBuffer in) {
        if (in != null) {
            this.f14729in = in;
            return;
        }
        throw new IllegalArgumentException("Session input buffer may not be null");
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.closed || !this.f14729in.isDataAvailable(10)) {
            return 0;
        }
        return 1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f14729in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f14729in.read(b, off, len);
    }
}
