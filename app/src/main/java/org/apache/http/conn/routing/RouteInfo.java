package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface RouteInfo {

    /* loaded from: assets/org.apache.http.legacy.boot */
    public enum LayerType {
        PLAIN,
        LAYERED
    }

    /* loaded from: assets/org.apache.http.legacy.boot */
    public enum TunnelType {
        PLAIN,
        TUNNELLED
    }

    int getHopCount();

    HttpHost getHopTarget(int i);

    LayerType getLayerType();

    InetAddress getLocalAddress();

    HttpHost getProxyHost();

    HttpHost getTargetHost();

    TunnelType getTunnelType();

    boolean isLayered();

    boolean isSecure();

    boolean isTunnelled();
}
