package org.apache.http.auth;

import java.security.Principal;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
