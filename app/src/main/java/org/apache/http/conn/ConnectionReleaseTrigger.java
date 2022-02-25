package org.apache.http.conn;

import java.io.IOException;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface ConnectionReleaseTrigger {
    void abortConnection() throws IOException;

    void releaseConnection() throws IOException;
}
