package org.apache.http.message;

import org.apache.http.FormattedHeader;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BufferedHeader implements FormattedHeader, Cloneable {
    private final CharArrayBuffer buffer;
    private final String name;
    private final int valuePos;

    public BufferedHeader(CharArrayBuffer buffer) throws ParseException {
        if (buffer != null) {
            int colon = buffer.indexOf(58);
            if (colon != -1) {
                String s = buffer.substringTrimmed(0, colon);
                if (s.length() != 0) {
                    this.buffer = buffer;
                    this.name = s;
                    this.valuePos = colon + 1;
                    return;
                }
                throw new ParseException("Invalid header: " + buffer.toString());
            }
            throw new ParseException("Invalid header: " + buffer.toString());
        }
        throw new IllegalArgumentException("Char array buffer may not be null");
    }

    @Override // org.apache.http.Header
    public String getName() {
        return this.name;
    }

    @Override // org.apache.http.Header
    public String getValue() {
        return this.buffer.substringTrimmed(this.valuePos, this.buffer.length());
    }

    @Override // org.apache.http.Header
    public HeaderElement[] getElements() throws ParseException {
        ParserCursor cursor = new ParserCursor(0, this.buffer.length());
        cursor.updatePos(this.valuePos);
        return BasicHeaderValueParser.DEFAULT.parseElements(this.buffer, cursor);
    }

    @Override // org.apache.http.FormattedHeader
    public int getValuePos() {
        return this.valuePos;
    }

    @Override // org.apache.http.FormattedHeader
    public CharArrayBuffer getBuffer() {
        return this.buffer;
    }

    public String toString() {
        return this.buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
