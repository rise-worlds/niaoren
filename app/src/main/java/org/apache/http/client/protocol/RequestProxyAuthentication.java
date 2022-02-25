package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RequestProxyAuthentication implements HttpRequestInterceptor {
    private final Log log = LogFactory.getLog(getClass());

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        AuthState authState;
        AuthScheme authScheme;
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        } else if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        } else if (!request.containsHeader("Proxy-Authorization") && (authState = (AuthState) context.getAttribute(ClientContext.PROXY_AUTH_STATE)) != null && (authScheme = authState.getAuthScheme()) != null) {
            Credentials creds = authState.getCredentials();
            if (creds == null) {
                this.log.debug("User credentials not available");
            } else if (authState.getAuthScope() != null || !authScheme.isConnectionBased()) {
                try {
                    request.addHeader(authScheme.authenticate(creds, request));
                } catch (AuthenticationException ex) {
                    if (this.log.isErrorEnabled()) {
                        Log log = this.log;
                        log.error("Proxy authentication error: " + ex.getMessage());
                    }
                }
            }
        }
    }
}
