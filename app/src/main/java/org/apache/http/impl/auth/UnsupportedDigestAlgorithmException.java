package org.apache.http.impl.auth;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class UnsupportedDigestAlgorithmException extends RuntimeException {
    private static final long serialVersionUID = 319558534317118022L;

    public UnsupportedDigestAlgorithmException() {
    }

    public UnsupportedDigestAlgorithmException(String message) {
        super(message);
    }

    public UnsupportedDigestAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}
