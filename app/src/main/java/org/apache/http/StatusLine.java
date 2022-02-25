package org.apache.http;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface StatusLine {
    ProtocolVersion getProtocolVersion();

    String getReasonPhrase();

    int getStatusCode();
}
