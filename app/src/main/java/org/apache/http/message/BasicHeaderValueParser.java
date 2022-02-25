package org.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicHeaderValueParser implements HeaderValueParser {
    public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
    private static final char PARAM_DELIMITER = ';';
    private static final char ELEM_DELIMITER = ',';
    private static final char[] ALL_DELIMITERS = {PARAM_DELIMITER, ELEM_DELIMITER};

    public static final HeaderElement[] parseElements(String value, HeaderValueParser parser) throws ParseException {
        if (value != null) {
            if (parser == null) {
                parser = DEFAULT;
            }
            CharArrayBuffer buffer = new CharArrayBuffer(value.length());
            buffer.append(value);
            ParserCursor cursor = new ParserCursor(0, value.length());
            return parser.parseElements(buffer, cursor);
        }
        throw new IllegalArgumentException("Value to parse may not be null");
    }

    @Override // org.apache.http.message.HeaderValueParser
    public HeaderElement[] parseElements(CharArrayBuffer buffer, ParserCursor cursor) {
        if (buffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        } else if (cursor != null) {
            List elements = new ArrayList();
            while (!cursor.atEnd()) {
                HeaderElement element = parseHeaderElement(buffer, cursor);
                if (element.getName().length() != 0 || element.getValue() != null) {
                    elements.add(element);
                }
            }
            return (HeaderElement[]) elements.toArray(new HeaderElement[elements.size()]);
        } else {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
    }

    public static final HeaderElement parseHeaderElement(String value, HeaderValueParser parser) throws ParseException {
        if (value != null) {
            if (parser == null) {
                parser = DEFAULT;
            }
            CharArrayBuffer buffer = new CharArrayBuffer(value.length());
            buffer.append(value);
            ParserCursor cursor = new ParserCursor(0, value.length());
            return parser.parseHeaderElement(buffer, cursor);
        }
        throw new IllegalArgumentException("Value to parse may not be null");
    }

    @Override // org.apache.http.message.HeaderValueParser
    public HeaderElement parseHeaderElement(CharArrayBuffer buffer, ParserCursor cursor) {
        if (buffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        } else if (cursor != null) {
            NameValuePair nvp = parseNameValuePair(buffer, cursor);
            NameValuePair[] params = null;
            if (!cursor.atEnd()) {
                char ch = buffer.charAt(cursor.getPos() - 1);
                if (ch != ',') {
                    params = parseParameters(buffer, cursor);
                }
            }
            return createHeaderElement(nvp.getName(), nvp.getValue(), params);
        } else {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
    }

    protected HeaderElement createHeaderElement(String name, String value, NameValuePair[] params) {
        return new BasicHeaderElement(name, value, params);
    }

    public static final NameValuePair[] parseParameters(String value, HeaderValueParser parser) throws ParseException {
        if (value != null) {
            if (parser == null) {
                parser = DEFAULT;
            }
            CharArrayBuffer buffer = new CharArrayBuffer(value.length());
            buffer.append(value);
            ParserCursor cursor = new ParserCursor(0, value.length());
            return parser.parseParameters(buffer, cursor);
        }
        throw new IllegalArgumentException("Value to parse may not be null");
    }

    @Override // org.apache.http.message.HeaderValueParser
    public NameValuePair[] parseParameters(CharArrayBuffer buffer, ParserCursor cursor) {
        if (buffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        } else if (cursor != null) {
            int pos = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            while (pos < indexTo) {
                char ch = buffer.charAt(pos);
                if (!HTTP.isWhitespace(ch)) {
                    break;
                }
                pos++;
            }
            cursor.updatePos(pos);
            if (cursor.atEnd()) {
                return new NameValuePair[0];
            }
            List params = new ArrayList();
            while (!cursor.atEnd()) {
                NameValuePair param = parseNameValuePair(buffer, cursor);
                params.add(param);
                char ch2 = buffer.charAt(cursor.getPos() - 1);
                if (ch2 == ',') {
                    break;
                }
            }
            return (NameValuePair[]) params.toArray(new NameValuePair[params.size()]);
        } else {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
    }

    public static final NameValuePair parseNameValuePair(String value, HeaderValueParser parser) throws ParseException {
        if (value != null) {
            if (parser == null) {
                parser = DEFAULT;
            }
            CharArrayBuffer buffer = new CharArrayBuffer(value.length());
            buffer.append(value);
            ParserCursor cursor = new ParserCursor(0, value.length());
            return parser.parseNameValuePair(buffer, cursor);
        }
        throw new IllegalArgumentException("Value to parse may not be null");
    }

    @Override // org.apache.http.message.HeaderValueParser
    public NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor) {
        return parseNameValuePair(buffer, cursor, ALL_DELIMITERS);
    }

    private static boolean isOneOf(char ch, char[] chs) {
        if (chs != null) {
            for (char c : chs) {
                if (ch == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor, char[] delimiters) {
        String name;
        if (buffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        } else if (cursor != null) {
            boolean terminated = false;
            int pos = cursor.getPos();
            int indexFrom = cursor.getPos();
            int indexTo = cursor.getUpperBound();
            int pos2 = pos;
            while (true) {
                if (pos2 < indexTo) {
                    char ch = buffer.charAt(pos2);
                    if (ch == '=') {
                        break;
                    } else if (isOneOf(ch, delimiters)) {
                        terminated = true;
                        break;
                    } else {
                        pos2++;
                    }
                } else {
                    break;
                }
            }
            if (pos2 == indexTo) {
                terminated = true;
                name = buffer.substringTrimmed(indexFrom, indexTo);
            } else {
                name = buffer.substringTrimmed(indexFrom, pos2);
                pos2++;
            }
            if (terminated) {
                cursor.updatePos(pos2);
                return createNameValuePair(name, null);
            }
            boolean qouted = false;
            int pos3 = pos2;
            boolean escaped = false;
            while (true) {
                if (pos3 >= indexTo) {
                    break;
                }
                char ch2 = buffer.charAt(pos3);
                escaped = true;
                if (ch2 == '\"' && !escaped) {
                    if (!qouted) {
                        qouted = true;
                    } else {
                        qouted = false;
                    }
                }
                if (!qouted && !escaped && isOneOf(ch2, delimiters)) {
                    terminated = true;
                    break;
                }
                if (escaped) {
                    escaped = false;
                } else if (!qouted || ch2 != '\\') {
                    escaped = false;
                }
                pos3++;
            }
            int i1 = pos2;
            int i2 = pos3;
            while (i1 < i2 && HTTP.isWhitespace(buffer.charAt(i1))) {
                i1++;
            }
            while (i2 > i1 && HTTP.isWhitespace(buffer.charAt(i2 - 1))) {
                i2--;
            }
            if (i2 - i1 >= 2 && buffer.charAt(i1) == '\"' && buffer.charAt(i2 - 1) == '\"') {
                i1++;
                i2--;
            }
            String value = buffer.substring(i1, i2);
            if (terminated) {
                pos3++;
            }
            cursor.updatePos(pos3);
            return createNameValuePair(name, value);
        } else {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
    }

    protected NameValuePair createNameValuePair(String name, String value) {
        return new BasicNameValuePair(name, value);
    }
}
