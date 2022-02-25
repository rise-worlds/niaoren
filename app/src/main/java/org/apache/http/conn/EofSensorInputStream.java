package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class EofSensorInputStream extends InputStream implements ConnectionReleaseTrigger {
    private EofSensorWatcher eofWatcher;
    private boolean selfClosed;
    protected InputStream wrappedStream;

    public EofSensorInputStream(InputStream in, EofSensorWatcher watcher) {
        if (in != null) {
            this.wrappedStream = in;
            this.selfClosed = false;
            this.eofWatcher = watcher;
            return;
        }
        throw new IllegalArgumentException("Wrapped stream may not be null.");
    }

    protected boolean isReadAllowed() throws IOException {
        if (!this.selfClosed) {
            return this.wrappedStream != null;
        }
        throw new IOException("Attempted read on closed stream.");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        try {
            int l = this.wrappedStream.read();
            checkEOF(l);
            return l;
        } catch (IOException ex) {
            checkAbort();
            throw ex;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        try {
            int l = this.wrappedStream.read(b, off, len);
            checkEOF(l);
            return l;
        } catch (IOException ex) {
            checkAbort();
            throw ex;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        try {
            int l = this.wrappedStream.read(b);
            checkEOF(l);
            return l;
        } catch (IOException ex) {
            checkAbort();
            throw ex;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (!isReadAllowed()) {
            return 0;
        }
        try {
            int a = this.wrappedStream.available();
            return a;
        } catch (IOException ex) {
            checkAbort();
            throw ex;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.selfClosed = true;
        checkClose();
    }

    protected void checkEOF(int eof) throws IOException {
        if (this.wrappedStream != null && eof < 0) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.eofDetected(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    protected void checkClose() throws IOException {
        if (this.wrappedStream != null) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.streamClosed(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    protected void checkAbort() throws IOException {
        if (this.wrappedStream != null) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.streamAbort(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    @Override // org.apache.http.conn.ConnectionReleaseTrigger
    public void releaseConnection() throws IOException {
        close();
    }

    @Override // org.apache.http.conn.ConnectionReleaseTrigger
    public void abortConnection() throws IOException {
        this.selfClosed = true;
        checkAbort();
    }
}
