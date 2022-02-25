package org.apache.http.client.methods;

import java.net.URI;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpGet extends HttpRequestBase {
    public static final String METHOD_NAME = "GET";

    public HttpGet() {
    }

    public HttpGet(URI uri) {
        setURI(uri);
    }

    public HttpGet(String uri) {
        setURI(URI.create(uri));
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return "GET";
    }
}
