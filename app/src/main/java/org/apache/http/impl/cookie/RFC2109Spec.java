package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.AbstractC3144SM;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookiePathComparator;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import p110z1.SimpleComparison;
import p110z1.Typography;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RFC2109Spec extends CookieSpecBase {
    private final String[] datepatterns;
    private final boolean oneHeader;
    private static final CookiePathComparator PATH_COMPARATOR = new CookiePathComparator();
    private static final String[] DATE_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", DateUtils.PATTERN_RFC1036, DateUtils.PATTERN_ASCTIME};

    public RFC2109Spec(String[] datepatterns, boolean oneHeader) {
        if (datepatterns != null) {
            this.datepatterns = (String[]) datepatterns.clone();
        } else {
            this.datepatterns = DATE_PATTERNS;
        }
        this.oneHeader = oneHeader;
        registerAttribHandler(ClientCookie.VERSION_ATTR, new RFC2109VersionHandler());
        registerAttribHandler("path", new BasicPathHandler());
        registerAttribHandler(ClientCookie.DOMAIN_ATTR, new RFC2109DomainHandler());
        registerAttribHandler(ClientCookie.MAX_AGE_ATTR, new BasicMaxAgeHandler());
        registerAttribHandler(ClientCookie.SECURE_ATTR, new BasicSecureHandler());
        registerAttribHandler(ClientCookie.COMMENT_ATTR, new BasicCommentHandler());
        registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
    }

    public RFC2109Spec() {
        this(null, false);
    }

    @Override // org.apache.http.cookie.CookieSpec
    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        } else if (origin != null) {
            HeaderElement[] elems = header.getElements();
            return parse(elems, origin);
        } else {
            throw new IllegalArgumentException("Cookie origin may not be null");
        }
    }

    @Override // org.apache.http.impl.cookie.CookieSpecBase, org.apache.http.cookie.CookieSpec
    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        if (cookie != null) {
            String name = cookie.getName();
            if (name.indexOf(32) != -1) {
                throw new MalformedCookieException("Cookie name may not contain blanks");
            } else if (!name.startsWith("$")) {
                super.validate(cookie, origin);
            } else {
                throw new MalformedCookieException("Cookie name may not start with $");
            }
        } else {
            throw new IllegalArgumentException("Cookie may not be null");
        }
    }

    @Override // org.apache.http.cookie.CookieSpec
    public List<Header> formatCookies(List<Cookie> cookies) {
        if (cookies == null) {
            throw new IllegalArgumentException("List of cookies may not be null");
        } else if (!cookies.isEmpty()) {
            if (cookies.size() > 1) {
                cookies = new ArrayList(cookies);
                Collections.sort(cookies, PATH_COMPARATOR);
            }
            if (this.oneHeader) {
                return doFormatOneHeader(cookies);
            }
            return doFormatManyHeaders(cookies);
        } else {
            throw new IllegalArgumentException("List of cookies may not be empty");
        }
    }

    private List<Header> doFormatOneHeader(List<Cookie> cookies) {
        int version = Integer.MAX_VALUE;
        for (Cookie cookie : cookies) {
            if (cookie.getVersion() < version) {
                version = cookie.getVersion();
            }
        }
        CharArrayBuffer buffer = new CharArrayBuffer(40 * cookies.size());
        buffer.append(AbstractC3144SM.COOKIE);
        buffer.append(": ");
        buffer.append("$Version=");
        buffer.append(Integer.toString(version));
        for (Cookie cooky : cookies) {
            buffer.append("; ");
            formatCookieAsVer(buffer, cooky, version);
        }
        List<Header> headers = new ArrayList<>(1);
        headers.add(new BufferedHeader(buffer));
        return headers;
    }

    private List<Header> doFormatManyHeaders(List<Cookie> cookies) {
        List<Header> headers = new ArrayList<>(cookies.size());
        for (Cookie cookie : cookies) {
            int version = cookie.getVersion();
            CharArrayBuffer buffer = new CharArrayBuffer(40);
            buffer.append("Cookie: ");
            buffer.append("$Version=");
            buffer.append(Integer.toString(version));
            buffer.append("; ");
            formatCookieAsVer(buffer, cookie, version);
            headers.add(new BufferedHeader(buffer));
        }
        return headers;
    }

    protected void formatParamAsVer(CharArrayBuffer buffer, String name, String value, int version) {
        buffer.append(name);
        buffer.append(SimpleComparison.f23609c);
        if (value == null) {
            return;
        }
        if (version > 0) {
            buffer.append(Typography.f21049a);
            buffer.append(value);
            buffer.append(Typography.f21049a);
            return;
        }
        buffer.append(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void formatCookieAsVer(CharArrayBuffer buffer, Cookie cookie, int version) {
        formatParamAsVer(buffer, cookie.getName(), cookie.getValue(), version);
        if (cookie.getPath() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute("path")) {
            buffer.append("; ");
            formatParamAsVer(buffer, "$Path", cookie.getPath(), version);
        }
        if (cookie.getDomain() != null && (cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute(ClientCookie.DOMAIN_ATTR)) {
            buffer.append("; ");
            formatParamAsVer(buffer, "$Domain", cookie.getDomain(), version);
        }
    }

    @Override // org.apache.http.cookie.CookieSpec
    public int getVersion() {
        return 1;
    }

    @Override // org.apache.http.cookie.CookieSpec
    public Header getVersionHeader() {
        return null;
    }
}
