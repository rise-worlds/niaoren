package org.apache.http;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HttpEntityEnclosingRequest extends HttpRequest {
    boolean expectContinue();

    HttpEntity getEntity();

    void setEntity(HttpEntity httpEntity);
}
