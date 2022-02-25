package org.apache.http.conn.params;

import org.apache.http.conn.routing.HttpRoute;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface ConnPerRoute {
    int getMaxForRoute(HttpRoute httpRoute);
}
