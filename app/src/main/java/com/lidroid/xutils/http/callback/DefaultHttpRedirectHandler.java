package com.lidroid.xutils.http.callback;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.AbstractC3144SM;

/* loaded from: classes.dex */
public class DefaultHttpRedirectHandler implements HttpRedirectHandler {
    @Override // com.lidroid.xutils.http.callback.HttpRedirectHandler
    public HttpRequestBase getDirectRequest(HttpResponse httpResponse) {
        if (!httpResponse.containsHeader(HttpRequest.HEADER_LOCATION)) {
            return null;
        }
        HttpGet httpGet = new HttpGet(httpResponse.getFirstHeader(HttpRequest.HEADER_LOCATION).getValue());
        if (httpResponse.containsHeader(AbstractC3144SM.SET_COOKIE)) {
            httpGet.addHeader(AbstractC3144SM.COOKIE, httpResponse.getFirstHeader(AbstractC3144SM.SET_COOKIE).getValue());
        }
        return httpGet;
    }
}
