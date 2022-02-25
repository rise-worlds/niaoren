package org.apache.http;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HttpConnectionMetrics {
    Object getMetric(String str);

    long getReceivedBytesCount();

    long getRequestCount();

    long getResponseCount();

    long getSentBytesCount();

    void reset();
}
