package org.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class HeaderGroup implements Cloneable {
    private List headers = new ArrayList(16);

    public void clear() {
        this.headers.clear();
    }

    public void addHeader(Header header) {
        if (header != null) {
            this.headers.add(header);
        }
    }

    public void removeHeader(Header header) {
        if (header != null) {
            this.headers.remove(header);
        }
    }

    public void updateHeader(Header header) {
        if (header != null) {
            for (int i = 0; i < this.headers.size(); i++) {
                Header current = (Header) this.headers.get(i);
                if (current.getName().equalsIgnoreCase(header.getName())) {
                    this.headers.set(i, header);
                    return;
                }
            }
            this.headers.add(header);
        }
    }

    public void setHeaders(Header[] headers) {
        clear();
        if (headers != null) {
            for (Header header : headers) {
                this.headers.add(header);
            }
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:11:0x0024 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.http.Header getCondensedHeader(java.lang.String r6) {
        /*
            r5 = this;
            org.apache.http.Header[] r0 = r5.getHeaders(r6)
            int r1 = r0.length
            if (r1 != 0) goto L_0x0009
            r1 = 0
            return r1
        L_0x0009:
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L_0x0011
            r1 = r0[r2]
            return r1
        L_0x0011:
            org.apache.http.util.CharArrayBuffer r1 = new org.apache.http.util.CharArrayBuffer
            r4 = 128(0x80, float:1.794E-43)
            r1.<init>(r4)
            r2 = r0[r2]
            java.lang.String r2 = r2.getValue()
            r1.append(r2)
        L_0x0022:
            r2 = r3
            int r3 = r0.length
            if (r2 >= r3) goto L_0x0037
            java.lang.String r3 = ", "
            r1.append(r3)
            r3 = r0[r2]
            java.lang.String r3 = r3.getValue()
            r1.append(r3)
            int r3 = r2 + 1
            goto L_0x0022
        L_0x0037:
            org.apache.http.message.BasicHeader r2 = new org.apache.http.message.BasicHeader
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.String r3 = r6.toLowerCase(r3)
            java.lang.String r4 = r1.toString()
            r2.<init>(r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.message.HeaderGroup.getCondensedHeader(java.lang.String):org.apache.http.Header");
    }

    public Header[] getHeaders(String name) {
        ArrayList headersFound = new ArrayList();
        for (int i = 0; i < this.headers.size(); i++) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                headersFound.add(header);
            }
        }
        int i2 = headersFound.size();
        return (Header[]) headersFound.toArray(new Header[i2]);
    }

    public Header getFirstHeader(String name) {
        for (int i = 0; i < this.headers.size(); i++) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    public Header getLastHeader(String name) {
        for (int i = this.headers.size() - 1; i >= 0; i--) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        return null;
    }

    public Header[] getAllHeaders() {
        return (Header[]) this.headers.toArray(new Header[this.headers.size()]);
    }

    public boolean containsHeader(String name) {
        for (int i = 0; i < this.headers.size(); i++) {
            Header header = (Header) this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public HeaderIterator iterator() {
        return new BasicListHeaderIterator(this.headers, null);
    }

    public HeaderIterator iterator(String name) {
        return new BasicListHeaderIterator(this.headers, name);
    }

    public HeaderGroup copy() {
        HeaderGroup clone = new HeaderGroup();
        clone.headers.addAll(this.headers);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        HeaderGroup clone = (HeaderGroup) super.clone();
        clone.headers = new ArrayList(this.headers);
        return clone;
    }
}
