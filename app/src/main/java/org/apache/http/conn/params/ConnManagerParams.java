package org.apache.http.conn.params;

import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class ConnManagerParams implements ConnManagerPNames {
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute() { // from class: org.apache.http.conn.params.ConnManagerParams.1
        @Override // org.apache.http.conn.params.ConnPerRoute
        public int getMaxForRoute(HttpRoute route) {
            return 2;
        }
    };
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

    public static long getTimeout(HttpParams params) {
        if (params != null) {
            return params.getLongParameter(ConnManagerPNames.TIMEOUT, 0L);
        }
        throw new IllegalArgumentException("HTTP parameters may not be null");
    }

    public static void setTimeout(HttpParams params, long timeout) {
        if (params != null) {
            params.setLongParameter(ConnManagerPNames.TIMEOUT, timeout);
            return;
        }
        throw new IllegalArgumentException("HTTP parameters may not be null");
    }

    public static void setMaxConnectionsPerRoute(HttpParams params, ConnPerRoute connPerRoute) {
        if (params != null) {
            params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
            return;
        }
        throw new IllegalArgumentException("HTTP parameters must not be null.");
    }

    public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams params) {
        if (params != null) {
            ConnPerRoute connPerRoute = (ConnPerRoute) params.getParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE);
            if (connPerRoute == null) {
                return DEFAULT_CONN_PER_ROUTE;
            }
            return connPerRoute;
        }
        throw new IllegalArgumentException("HTTP parameters must not be null.");
    }

    public static void setMaxTotalConnections(HttpParams params, int maxTotalConnections) {
        if (params != null) {
            params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxTotalConnections);
            return;
        }
        throw new IllegalArgumentException("HTTP parameters must not be null.");
    }

    public static int getMaxTotalConnections(HttpParams params) {
        if (params != null) {
            return params.getIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 20);
        }
        throw new IllegalArgumentException("HTTP parameters must not be null.");
    }
}
