package org.apache.http;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface RequestLine {
    String getMethod();

    ProtocolVersion getProtocolVersion();

    String getUri();
}
