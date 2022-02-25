package org.apache.http.p109io;

import java.io.IOException;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.io.SessionInputBuffer */
/* loaded from: assets/org.apache.http.legacy.boot */
public interface SessionInputBuffer {
    HttpTransportMetrics getMetrics();

    boolean isDataAvailable(int i) throws IOException;

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    int readLine(CharArrayBuffer charArrayBuffer) throws IOException;

    String readLine() throws IOException;
}
