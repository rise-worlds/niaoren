package org.apache.http;

import java.net.InetAddress;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HttpInetConnection extends HttpConnection {
    InetAddress getLocalAddress();

    int getLocalPort();

    InetAddress getRemoteAddress();

    int getRemotePort();
}
