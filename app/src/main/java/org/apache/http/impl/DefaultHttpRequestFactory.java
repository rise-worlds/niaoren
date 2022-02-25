package org.apache.http.impl;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private static final String[] RFC2616_COMMON_METHODS = {"GET"};
    private static final String[] RFC2616_ENTITY_ENC_METHODS = {"POST", "PUT"};
    private static final String[] RFC2616_SPECIAL_METHODS = {"HEAD", "OPTIONS", "DELETE", "TRACE"};

    private static boolean isOneOf(String[] methods, String method) {
        for (String str : methods) {
            if (str.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.http.HttpRequestFactory
    public HttpRequest newHttpRequest(RequestLine requestline) throws MethodNotSupportedException {
        if (requestline != null) {
            String method = requestline.getMethod();
            if (isOneOf(RFC2616_COMMON_METHODS, method)) {
                return new BasicHttpRequest(requestline);
            }
            if (isOneOf(RFC2616_ENTITY_ENC_METHODS, method)) {
                return new BasicHttpEntityEnclosingRequest(requestline);
            }
            if (isOneOf(RFC2616_SPECIAL_METHODS, method)) {
                return new BasicHttpRequest(requestline);
            }
            throw new MethodNotSupportedException(method + " method not supported");
        }
        throw new IllegalArgumentException("Request line may not be null");
    }

    @Override // org.apache.http.HttpRequestFactory
    public HttpRequest newHttpRequest(String method, String uri) throws MethodNotSupportedException {
        if (isOneOf(RFC2616_COMMON_METHODS, method)) {
            return new BasicHttpRequest(method, uri);
        }
        if (isOneOf(RFC2616_ENTITY_ENC_METHODS, method)) {
            return new BasicHttpEntityEnclosingRequest(method, uri);
        }
        if (isOneOf(RFC2616_SPECIAL_METHODS, method)) {
            return new BasicHttpRequest(method, uri);
        }
        throw new MethodNotSupportedException(method + " method not supported");
    }
}
