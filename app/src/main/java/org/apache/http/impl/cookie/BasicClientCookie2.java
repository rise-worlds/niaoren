package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String name, String value) {
        super(name, value);
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public int[] getPorts() {
        return this.ports;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setPorts(int[] ports) {
        this.ports = ports;
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public String getCommentURL() {
        return this.commentURL;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie
    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        clone.ports = (int[]) this.ports.clone();
        return clone;
    }
}
