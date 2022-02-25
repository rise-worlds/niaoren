package org.apache.http.auth;

import java.security.Principal;
import org.apache.http.util.LangUtils;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class BasicUserPrincipal implements Principal {
    private final String username;

    public BasicUserPrincipal(String username) {
        if (username != null) {
            this.username = username;
            return;
        }
        throw new IllegalArgumentException("User name may not be null");
    }

    @Override // java.security.Principal
    public String getName() {
        return this.username;
    }

    @Override // java.security.Principal
    public int hashCode() {
        int hash = LangUtils.hashCode(17, this.username);
        return hash;
    }

    @Override // java.security.Principal
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof BasicUserPrincipal) {
            BasicUserPrincipal that = (BasicUserPrincipal) o;
            if (LangUtils.equals(this.username, that.username)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.Principal
    public String toString() {
        return "[principal: " + this.username + "]";
    }
}
