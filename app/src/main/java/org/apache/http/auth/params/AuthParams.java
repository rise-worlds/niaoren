package org.apache.http.auth.params;

import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class AuthParams {
    private AuthParams() {
    }

    public static String getCredentialCharset(HttpParams params) {
        if (params != null) {
            String charset = (String) params.getParameter(AuthPNames.CREDENTIAL_CHARSET);
            if (charset == null) {
                return "US-ASCII";
            }
            return charset;
        }
        throw new IllegalArgumentException("HTTP parameters may not be null");
    }

    public static void setCredentialCharset(HttpParams params, String charset) {
        if (params != null) {
            params.setParameter(AuthPNames.CREDENTIAL_CHARSET, charset);
            return;
        }
        throw new IllegalArgumentException("HTTP parameters may not be null");
    }
}
