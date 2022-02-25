package org.apache.http.impl.p108io;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.params.HttpParams;

@Deprecated
/* renamed from: org.apache.http.impl.io.SocketOutputBuffer */
/* loaded from: assets/org.apache.http.legacy.boot */
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int buffersize, HttpParams params) throws IOException {
        if (socket != null) {
            init(socket.getOutputStream(), 8192, params);
            return;
        }
        throw new IllegalArgumentException("Socket may not be null");
    }
}
