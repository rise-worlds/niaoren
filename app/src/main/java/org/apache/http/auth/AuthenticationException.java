package org.apache.http.auth;

import org.apache.http.ProtocolException;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class AuthenticationException extends ProtocolException {
    private static final long serialVersionUID = -6794031905674764776L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
