package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;
import p110z1.SimpleComparison;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicHeaderElement implements HeaderElement, Cloneable {
    private final String name;
    private final NameValuePair[] parameters;
    private final String value;

    public BasicHeaderElement(String name, String value, NameValuePair[] parameters) {
        if (name != null) {
            this.name = name;
            this.value = value;
            if (parameters != null) {
                this.parameters = parameters;
            } else {
                this.parameters = new NameValuePair[0];
            }
        } else {
            throw new IllegalArgumentException("Name may not be null");
        }
    }

    public BasicHeaderElement(String name, String value) {
        this(name, value, null);
    }

    @Override // org.apache.http.HeaderElement
    public String getName() {
        return this.name;
    }

    @Override // org.apache.http.HeaderElement
    public String getValue() {
        return this.value;
    }

    @Override // org.apache.http.HeaderElement
    public NameValuePair[] getParameters() {
        return (NameValuePair[]) this.parameters.clone();
    }

    @Override // org.apache.http.HeaderElement
    public int getParameterCount() {
        return this.parameters.length;
    }

    @Override // org.apache.http.HeaderElement
    public NameValuePair getParameter(int index) {
        return this.parameters[index];
    }

    @Override // org.apache.http.HeaderElement
    public NameValuePair getParameterByName(String name) {
        if (name != null) {
            for (int i = 0; i < this.parameters.length; i++) {
                NameValuePair current = this.parameters[i];
                if (current.getName().equalsIgnoreCase(name)) {
                    return current;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof HeaderElement)) {
            return false;
        }
        BasicHeaderElement that = (BasicHeaderElement) object;
        if (!this.name.equals(that.name) || !LangUtils.equals(this.value, that.value) || !LangUtils.equals((Object[]) this.parameters, (Object[]) that.parameters)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = LangUtils.hashCode(17, this.name);
        int hash2 = LangUtils.hashCode(hash, this.value);
        for (int i = 0; i < this.parameters.length; i++) {
            hash2 = LangUtils.hashCode(hash2, this.parameters[i]);
        }
        return hash2;
    }

    public String toString() {
        CharArrayBuffer buffer = new CharArrayBuffer(64);
        buffer.append(this.name);
        if (this.value != null) {
            buffer.append(SimpleComparison.f23609c);
            buffer.append(this.value);
        }
        for (int i = 0; i < this.parameters.length; i++) {
            buffer.append("; ");
            buffer.append(this.parameters[i]);
        }
        return buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
