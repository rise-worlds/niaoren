package org.apache.http.impl.cookie;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SetCookie;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicClientCookie implements SetCookie, ClientCookie, Cloneable {
    private Map<String, String> attribs;
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean isSecure;
    private final String name;
    private String value;

    public BasicClientCookie(String name, String value) {
        if (name != null) {
            this.name = name;
            this.attribs = new HashMap();
            this.value = value;
            return;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    @Override // org.apache.http.cookie.Cookie
    public String getName() {
        return this.name;
    }

    @Override // org.apache.http.cookie.Cookie
    public String getValue() {
        return this.value;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setValue(String value) {
        this.value = value;
    }

    @Override // org.apache.http.cookie.Cookie
    public String getComment() {
        return this.cookieComment;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setComment(String comment) {
        this.cookieComment = comment;
    }

    @Override // org.apache.http.cookie.Cookie
    public String getCommentURL() {
        return null;
    }

    @Override // org.apache.http.cookie.Cookie
    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setExpiryDate(Date expiryDate) {
        this.cookieExpiryDate = expiryDate;
    }

    @Override // org.apache.http.cookie.Cookie
    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }

    @Override // org.apache.http.cookie.Cookie
    public String getDomain() {
        return this.cookieDomain;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setDomain(String domain) {
        if (domain != null) {
            this.cookieDomain = domain.toLowerCase(Locale.ENGLISH);
        } else {
            this.cookieDomain = null;
        }
    }

    @Override // org.apache.http.cookie.Cookie
    public String getPath() {
        return this.cookiePath;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setPath(String path) {
        this.cookiePath = path;
    }

    @Override // org.apache.http.cookie.Cookie
    public boolean isSecure() {
        return this.isSecure;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setSecure(boolean secure) {
        this.isSecure = secure;
    }

    @Override // org.apache.http.cookie.Cookie
    public int[] getPorts() {
        return null;
    }

    @Override // org.apache.http.cookie.Cookie
    public int getVersion() {
        return this.cookieVersion;
    }

    @Override // org.apache.http.cookie.SetCookie
    public void setVersion(int version) {
        this.cookieVersion = version;
    }

    @Override // org.apache.http.cookie.Cookie
    public boolean isExpired(Date date) {
        if (date != null) {
            return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= date.getTime();
        }
        throw new IllegalArgumentException("Date may not be null");
    }

    public void setAttribute(String name, String value) {
        this.attribs.put(name, value);
    }

    @Override // org.apache.http.cookie.ClientCookie
    public String getAttribute(String name) {
        return this.attribs.get(name);
    }

    @Override // org.apache.http.cookie.ClientCookie
    public boolean containsAttribute(String name) {
        return this.attribs.get(name) != null;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie clone = (BasicClientCookie) super.clone();
        clone.attribs = new HashMap(this.attribs);
        return clone;
    }

    public String toString() {
        return "[version: " + Integer.toString(this.cookieVersion) + "][name: " + this.name + "][value: " + this.value + "][domain: " + this.cookieDomain + "][path: " + this.cookiePath + "][expiry: " + this.cookieExpiryDate + "]";
    }
}
