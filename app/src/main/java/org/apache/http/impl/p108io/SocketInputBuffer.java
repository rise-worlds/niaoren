package org.apache.http.impl.p108io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.apache.http.impl.io.SocketInputBuffer */
/* loaded from: assets/org.apache.http.legacy.boot */
public class SocketInputBuffer extends AbstractSessionInputBuffer {
    private final Socket socket;

    public SocketInputBuffer(Socket socket, int buffersize, HttpParams params) throws IOException {
        if (socket != null) {
            this.socket = socket;
            init(socket.getInputStream(), 8192, params);
            return;
        }
        throw new IllegalArgumentException("Socket may not be null");
    }

    @Override // org.apache.http.p109io.SessionInputBuffer
    public boolean isDataAvailable(int timeout) throws IOException {
        boolean result = hasBufferedData();
        if (!result) {
            int oldtimeout = this.socket.getSoTimeout();
            try {
                try {
                    this.socket.setSoTimeout(timeout);
                    fillBuffer();
                    result = hasBufferedData();
                } catch (InterruptedIOException e) {
                    if (!(e instanceof SocketTimeoutException)) {
                        throw e;
                    }
                }
            } finally {
                this.socket.setSoTimeout(oldtimeout);
            }
        }
        return result;
    }

    public boolean isStale() throws IOException {
        boolean z = false;
        if (hasBufferedData()) {
            return false;
        }
        int oldTimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(1);
            if (fillBuffer() == -1) {
                z = true;
            }
            return z;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        } finally {
            this.socket.setSoTimeout(oldTimeout);
        }
    }
}
