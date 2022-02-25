package org.apache.http.impl.p108io;

import android.support.p003v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.p109io.SessionInputBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.ContentLengthInputStream */
/* loaded from: assets/org.apache.http.legacy.boot */
public class ContentLengthInputStream extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private long contentLength;

    /* renamed from: in */
    private SessionInputBuffer f14728in;
    private long pos = 0;
    private boolean closed = false;

    public ContentLengthInputStream(SessionInputBuffer in, long contentLength) {
        this.f14728in = null;
        if (in == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        } else if (contentLength >= 0) {
            this.f14728in = in;
            this.contentLength = contentLength;
        } else {
            throw new IllegalArgumentException("Content length may not be negative");
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            try {
                byte[] buffer = new byte[2048];
                do {
                } while (read(buffer) >= 0);
            } finally {
                this.closed = true;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            this.pos++;
            return this.f14728in.read();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            if (this.pos + len > this.contentLength) {
                len = (int) (this.contentLength - this.pos);
            }
            int count = this.f14728in.read(b, off, len);
            this.pos += count;
            return count;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        int l;
        if (n <= 0) {
            return 0L;
        }
        byte[] buffer = new byte[2048];
        long remaining = Math.min(n, this.contentLength - this.pos);
        long remaining2 = remaining;
        long remaining3 = 0;
        while (remaining2 > 0 && (l = read(buffer, 0, (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining2))) != -1) {
            remaining3 += l;
            remaining2 -= l;
        }
        this.pos += remaining3;
        return remaining3;
    }
}
