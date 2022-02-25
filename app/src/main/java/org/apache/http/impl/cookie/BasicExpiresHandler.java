package org.apache.http.impl.cookie;

import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicExpiresHandler extends AbstractCookieAttributeHandler {
    private final String[] datepatterns;

    public BasicExpiresHandler(String[] datepatterns) {
        if (datepatterns != null) {
            this.datepatterns = datepatterns;
            return;
        }
        throw new IllegalArgumentException("Array of date patterns may not be null");
    }

    @Override // org.apache.http.cookie.CookieAttributeHandler
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        } else if (value != null) {
            try {
                cookie.setExpiryDate(DateUtils.parseDate(value, this.datepatterns));
            } catch (DateParseException e) {
                throw new MalformedCookieException("Unable to parse expires attribute: " + value);
            }
        } else {
            throw new MalformedCookieException("Missing value for expires attribute");
        }
    }
}
