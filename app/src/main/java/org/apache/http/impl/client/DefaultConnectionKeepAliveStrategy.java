package org.apache.http.impl.client;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import p110z1.C3894au;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class DefaultConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        if (response != null) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase(C3894au.f17527j)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return -1L;
        }
        throw new IllegalArgumentException("HTTP response may not be null");
    }
}
