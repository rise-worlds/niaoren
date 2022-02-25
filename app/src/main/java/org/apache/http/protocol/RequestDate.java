package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestDate implements HttpRequestInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null.");
        } else if ((request instanceof HttpEntityEnclosingRequest) && !request.containsHeader("Date")) {
            String httpdate = DATE_GENERATOR.getCurrentDate();
            request.setHeader("Date", httpdate);
        }
    }
}
