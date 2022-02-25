package org.apache.http.auth;

import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface AuthSchemeFactory {
    AuthScheme newInstance(HttpParams httpParams);
}
