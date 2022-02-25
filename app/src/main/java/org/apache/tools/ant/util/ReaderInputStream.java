package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: classes2.dex */
public class ReaderInputStream extends InputStream {
    private static final int BYTE_MASK = 255;
    private int begin;
    private String encoding;

    /* renamed from: in */
    private Reader f14780in;
    private byte[] slack;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public ReaderInputStream(Reader reader) {
        this.encoding = System.getProperty("file.encoding");
        this.f14780in = reader;
    }

    public ReaderInputStream(Reader reader, String str) {
        this(reader);
        if (str != null) {
            this.encoding = str;
            return;
        }
        throw new IllegalArgumentException("encoding must not be null");
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        byte b;
        if (this.f14780in != null) {
            if (this.slack == null || this.begin >= this.slack.length) {
                byte[] bArr = new byte[1];
                if (read(bArr, 0, 1) <= 0) {
                    return -1;
                }
                b = bArr[0];
            } else {
                b = this.slack[this.begin];
                int i = this.begin + 1;
                this.begin = i;
                if (i == this.slack.length) {
                    this.slack = null;
                }
            }
            return b & 255;
        }
        throw new IOException("Stream Closed");
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f14780in == null) {
            throw new IOException("Stream Closed");
        } else if (i2 == 0) {
            return 0;
        } else {
            while (this.slack == null) {
                char[] cArr = new char[i2];
                int read = this.f14780in.read(cArr);
                if (read == -1) {
                    return -1;
                }
                if (read > 0) {
                    this.slack = new String(cArr, 0, read).getBytes(this.encoding);
                    this.begin = 0;
                }
            }
            if (i2 > this.slack.length - this.begin) {
                i2 = this.slack.length - this.begin;
            }
            System.arraycopy(this.slack, this.begin, bArr, i, i2);
            this.begin += i2;
            if (this.begin >= this.slack.length) {
                this.slack = null;
            }
            return i2;
        }
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        try {
            this.f14780in.mark(i);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        if (this.f14780in == null) {
            throw new IOException("Stream Closed");
        } else if (this.slack == null) {
            return this.f14780in.ready() ? 1 : 0;
        } else {
            return this.slack.length - this.begin;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.f14780in != null) {
            this.slack = null;
            this.f14780in.reset();
        } else {
            throw new IOException("Stream Closed");
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.f14780in != null) {
            this.f14780in.close();
            this.slack = null;
            this.f14780in = null;
        }
    }
}
