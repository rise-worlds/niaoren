package org.apache.http.conn.params;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class ConnRouteParams implements ConnRoutePNames {
    public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
    public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);

    private ConnRouteParams() {
    }

    public static HttpHost getDefaultProxy(HttpParams params) {
        if (params != null) {
            HttpHost proxy = (HttpHost) params.getParameter(ConnRoutePNames.DEFAULT_PROXY);
            if (proxy == null || !NO_HOST.equals(proxy)) {
                return proxy;
            }
            return null;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }

    public static void setDefaultProxy(HttpParams params, HttpHost proxy) {
        if (params != null) {
            params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            return;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }

    public static HttpRoute getForcedRoute(HttpParams params) {
        if (params != null) {
            HttpRoute route = (HttpRoute) params.getParameter(ConnRoutePNames.FORCED_ROUTE);
            if (route == null || !NO_ROUTE.equals(route)) {
                return route;
            }
            return null;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }

    public static void setForcedRoute(HttpParams params, HttpRoute route) {
        if (params != null) {
            params.setParameter(ConnRoutePNames.FORCED_ROUTE, route);
            return;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }

    public static InetAddress getLocalAddress(HttpParams params) {
        if (params != null) {
            InetAddress local = (InetAddress) params.getParameter(ConnRoutePNames.LOCAL_ADDRESS);
            return local;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }

    public static void setLocalAddress(HttpParams params, InetAddress local) {
        if (params != null) {
            params.setParameter(ConnRoutePNames.LOCAL_ADDRESS, local);
            return;
        }
        throw new IllegalArgumentException("Parameters must not be null.");
    }
}
