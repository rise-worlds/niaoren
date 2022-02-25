package org.apache.http.conn;

import java.util.concurrent.TimeUnit;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface ClientConnectionRequest {
    void abortRequest();

    ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException;
}
