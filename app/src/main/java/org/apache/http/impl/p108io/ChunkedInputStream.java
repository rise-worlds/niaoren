package org.apache.http.impl.p108io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.ExceptionUtils;

@Deprecated
/* renamed from: org.apache.http.impl.io.ChunkedInputStream */
/* loaded from: assets/org.apache.http.legacy.boot */
public class ChunkedInputStream extends InputStream {
    private final CharArrayBuffer buffer;
    private int chunkSize;

    /* renamed from: in */
    private SessionInputBuffer f14727in;
    private int pos;
    private boolean bof = true;
    private boolean eof = false;
    private boolean closed = false;
    private Header[] footers = new Header[0];

    public ChunkedInputStream(SessionInputBuffer in) {
        if (in != null) {
            this.f14727in = in;
            this.pos = 0;
            this.buffer = new CharArrayBuffer(16);
            return;
        }
        throw new IllegalArgumentException("Session input buffer may not be null");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            this.pos++;
            return this.f14727in.read();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.pos >= this.chunkSize) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int count = this.f14727in.read(b, off, Math.min(len, this.chunkSize - this.pos));
            this.pos += count;
            return count;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    private void nextChunk() throws IOException {
        this.chunkSize = getChunkSize();
        if (this.chunkSize >= 0) {
            this.bof = false;
            this.pos = 0;
            if (this.chunkSize == 0) {
                this.eof = true;
                parseTrailerHeaders();
                return;
            }
            return;
        }
        throw new MalformedChunkCodingException("Negative chunk size");
    }

    private int getChunkSize() throws IOException {
        if (!this.bof) {
            int cr = this.f14727in.read();
            int lf = this.f14727in.read();
            if (!(cr == 13 && lf == 10)) {
                throw new MalformedChunkCodingException("CRLF expected at end of chunk");
            }
        }
        this.buffer.clear();
        int i = this.f14727in.readLine(this.buffer);
        if (i != -1) {
            int separator = this.buffer.indexOf(59);
            if (separator < 0) {
                separator = this.buffer.length();
            }
            try {
                return Integer.parseInt(this.buffer.substringTrimmed(0, separator), 16);
            } catch (NumberFormatException e) {
                throw new MalformedChunkCodingException("Bad chunk header");
            }
        } else {
            throw new MalformedChunkCodingException("Chunked stream ended unexpectedly");
        }
    }

    private void parseTrailerHeaders() throws IOException {
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.f14727in, -1, -1, null);
        } catch (HttpException e) {
            IOException ioe = new MalformedChunkCodingException("Invalid footer: " + e.getMessage());
            ExceptionUtils.initCause(ioe, e);
            throw ioe;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    exhaustInputStream(this);
                }
            } finally {
                this.eof = true;
                this.closed = true;
            }
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.footers.clone();
    }

    static void exhaustInputStream(InputStream inStream) throws IOException {
        byte[] buffer = new byte[1024];
        do {
        } while (inStream.read(buffer) >= 0);
    }
}
