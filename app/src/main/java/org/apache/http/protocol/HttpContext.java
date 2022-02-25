package org.apache.http.protocol;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HttpContext {
    public static final String RESERVED_PREFIX = "http.";

    Object getAttribute(String str);

    Object removeAttribute(String str);

    void setAttribute(String str, Object obj);
}
