package org.apache.http;

import org.apache.http.util.ExceptionUtils;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpException extends Exception {
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message);
        ExceptionUtils.initCause(this, cause);
    }
}
