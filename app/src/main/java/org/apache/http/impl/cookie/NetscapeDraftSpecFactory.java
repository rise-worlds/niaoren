package org.apache.http.impl.cookie;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class NetscapeDraftSpecFactory implements CookieSpecFactory {
    @Override // org.apache.http.cookie.CookieSpecFactory
    public CookieSpec newInstance(HttpParams params) {
        if (params != null) {
            return new NetscapeDraftSpec((String[]) params.getParameter(CookieSpecPNames.DATE_PATTERNS));
        }
        return new NetscapeDraftSpec();
    }
}
