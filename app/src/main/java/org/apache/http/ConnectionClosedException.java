package org.apache.http;

import java.io.IOException;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class ConnectionClosedException extends IOException {
    private static final long serialVersionUID = 617550366255636674L;

    public ConnectionClosedException(String message) {
        super(message);
    }
}
