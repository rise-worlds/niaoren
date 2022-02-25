package org.apache.http.impl;

import java.util.HashMap;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.p109io.HttpTransportMetrics;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
    public static final String REQUEST_COUNT = "http.request-count";
    public static final String RESPONSE_COUNT = "http.response-count";
    public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
    private final HttpTransportMetrics inTransportMetric;
    private HashMap metricsCache;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics inTransportMetric, HttpTransportMetrics outTransportMetric) {
        this.inTransportMetric = inTransportMetric;
        this.outTransportMetric = outTransportMetric;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getReceivedBytesCount() {
        if (this.inTransportMetric != null) {
            return this.inTransportMetric.getBytesTransferred();
        }
        return -1L;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getSentBytesCount() {
        if (this.outTransportMetric != null) {
            return this.outTransportMetric.getBytesTransferred();
        }
        return -1L;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getRequestCount() {
        return this.requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getResponseCount() {
        return this.responseCount;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public Object getMetric(String metricName) {
        Object value = null;
        if (this.metricsCache != null) {
            value = this.metricsCache.get(metricName);
        }
        if (value != null) {
            return value;
        }
        if (REQUEST_COUNT.equals(metricName)) {
            Object value2 = new Long(this.requestCount);
            return value2;
        } else if (RESPONSE_COUNT.equals(metricName)) {
            Object value3 = new Long(this.responseCount);
            return value3;
        } else if (RECEIVED_BYTES_COUNT.equals(metricName)) {
            if (this.inTransportMetric != null) {
                return new Long(this.inTransportMetric.getBytesTransferred());
            }
            return null;
        } else if (!SENT_BYTES_COUNT.equals(metricName)) {
            return value;
        } else {
            if (this.outTransportMetric != null) {
                return new Long(this.outTransportMetric.getBytesTransferred());
            }
            return null;
        }
    }

    public void setMetric(String metricName, Object obj) {
        if (this.metricsCache == null) {
            this.metricsCache = new HashMap();
        }
        this.metricsCache.put(metricName, obj);
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public void reset() {
        if (this.outTransportMetric != null) {
            this.outTransportMetric.reset();
        }
        if (this.inTransportMetric != null) {
            this.inTransportMetric.reset();
        }
        this.requestCount = 0L;
        this.responseCount = 0L;
        this.metricsCache = null;
    }
}
