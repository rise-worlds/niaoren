package org.apache.http.message;

import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;
import p110z1.SimpleComparison;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicNameValuePair implements NameValuePair, Cloneable {
    private final String name;
    private final String value;

    public BasicNameValuePair(String name, String value) {
        if (name != null) {
            this.name = name;
            this.value = value;
            return;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    @Override // org.apache.http.NameValuePair
    public String getName() {
        return this.name;
    }

    @Override // org.apache.http.NameValuePair
    public String getValue() {
        return this.value;
    }

    public String toString() {
        int len = this.name.length();
        if (this.value != null) {
            len += 1 + this.value.length();
        }
        CharArrayBuffer buffer = new CharArrayBuffer(len);
        buffer.append(this.name);
        if (this.value != null) {
            buffer.append(SimpleComparison.f23609c);
            buffer.append(this.value);
        }
        return buffer.toString();
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair that = (BasicNameValuePair) object;
        if (!this.name.equals(that.name) || !LangUtils.equals(this.value, that.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = LangUtils.hashCode(17, this.name);
        return LangUtils.hashCode(hash, this.value);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
