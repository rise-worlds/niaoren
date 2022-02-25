package org.apache.http.client;

import org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface UserTokenHandler {
    Object getUserToken(HttpContext httpContext);
}
