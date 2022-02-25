package org.apache.http.impl.p108io;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.p109io.SessionOutputBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.ChunkedOutputStream */
/* loaded from: assets/org.apache.http.legacy.boot */
public class ChunkedOutputStream extends OutputStream {
    private byte[] cache;
    private int cachePosition;
    private boolean closed;
    private final SessionOutputBuffer out;
    private boolean wroteLastChunk;

    public ChunkedOutputStream(SessionOutputBuffer out, int bufferSize) throws IOException {
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.closed = false;
        this.cache = new byte[bufferSize];
        this.out = out;
    }

    public ChunkedOutputStream(SessionOutputBuffer out) throws IOException {
        this(out, 2048);
    }

    protected void flushCache() throws IOException {
        if (this.cachePosition > 0) {
            this.out.writeLine(Integer.toHexString(this.cachePosition));
            this.out.write(this.cache, 0, this.cachePosition);
            this.out.writeLine("");
            this.cachePosition = 0;
        }
    }

    protected void flushCacheWithAppend(byte[] bufferToAppend, int off, int len) throws IOException {
        this.out.writeLine(Integer.toHexString(this.cachePosition + len));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bufferToAppend, off, len);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    protected void writeClosingChunk() throws IOException {
        this.out.writeLine(ResultTypeConstant.f7213z);
        this.out.writeLine("");
    }

    public void finish() throws IOException {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        if (!this.closed) {
            this.cache[this.cachePosition] = (byte) b;
            this.cachePosition++;
            if (this.cachePosition == this.cache.length) {
                flushCache();
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] src, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        } else if (len >= this.cache.length - this.cachePosition) {
            flushCacheWithAppend(src, off, len);
        } else {
            System.arraycopy(src, off, this.cache, this.cachePosition, len);
            this.cachePosition += len;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        flushCache();
        this.out.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            finish();
            this.out.flush();
        }
    }
}
