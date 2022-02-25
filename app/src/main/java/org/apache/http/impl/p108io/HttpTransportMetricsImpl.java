package org.apache.http.impl.p108io;

import org.apache.http.p109io.HttpTransportMetrics;

@Deprecated
/* renamed from: org.apache.http.impl.io.HttpTransportMetricsImpl */
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    @Override // org.apache.http.p109io.HttpTransportMetrics
    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public void setBytesTransferred(long count) {
        this.bytesTransferred = count;
    }

    public void incrementBytesTransferred(long count) {
        this.bytesTransferred += count;
    }

    @Override // org.apache.http.p109io.HttpTransportMetrics
    public void reset() {
        this.bytesTransferred = 0L;
    }
}
