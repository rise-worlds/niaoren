package org.apache.http.impl.p108io;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.p109io.HttpMessageParser;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractMessageParser */
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class AbstractMessageParser implements HttpMessageParser {
    protected final LineParser lineParser;
    private final int maxHeaderCount;
    private final int maxLineLen;
    private final SessionInputBuffer sessionBuffer;

    protected abstract HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException;

    public AbstractMessageParser(SessionInputBuffer buffer, LineParser parser, HttpParams params) {
        if (buffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        } else if (params != null) {
            this.sessionBuffer = buffer;
            this.maxHeaderCount = params.getIntParameter("http.connection.max-header-count", -1);
            this.maxLineLen = params.getIntParameter("http.connection.max-line-length", -1);
            this.lineParser = parser != null ? parser : BasicLineParser.DEFAULT;
        } else {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
    }

    public static Header[] parseHeaders(SessionInputBuffer inbuffer, int maxHeaderCount, int maxLineLen, LineParser parser) throws HttpException, IOException {
        int i;
        char ch;
        if (inbuffer != null) {
            if (parser == null) {
                parser = BasicLineParser.DEFAULT;
            }
            ArrayList headerLines = new ArrayList();
            CharArrayBuffer current = null;
            CharArrayBuffer previous = null;
            while (true) {
                if (current == null) {
                    current = new CharArrayBuffer(64);
                } else {
                    current.clear();
                }
                int l = inbuffer.readLine(current);
                i = 0;
                if (l == -1 || current.length() < 1) {
                    break;
                }
                if ((current.charAt(0) == ' ' || current.charAt(0) == '\t') && previous != null) {
                    while (i < current.length() && ((ch = current.charAt(i)) == ' ' || ch == '\t')) {
                        i++;
                    }
                    if (maxLineLen <= 0 || ((previous.length() + 1) + current.length()) - i <= maxLineLen) {
                        previous.append(' ');
                        previous.append(current, i, current.length() - i);
                    } else {
                        throw new IOException("Maximum line length limit exceeded");
                    }
                } else {
                    headerLines.add(current);
                    previous = current;
                    current = null;
                }
                if (maxHeaderCount > 0 && headerLines.size() >= maxHeaderCount) {
                    throw new IOException("Maximum header count exceeded");
                }
            }
            Header[] headers = new Header[headerLines.size()];
            while (i < headerLines.size()) {
                CharArrayBuffer buffer = (CharArrayBuffer) headerLines.get(i);
                try {
                    headers[i] = parser.parseHeader(buffer);
                    i++;
                } catch (ParseException ex) {
                    throw new ProtocolException(ex.getMessage());
                }
            }
            return headers;
        }
        throw new IllegalArgumentException("Session input buffer may not be null");
    }

    @Override // org.apache.http.p109io.HttpMessageParser
    public HttpMessage parse() throws IOException, HttpException {
        try {
            HttpMessage message = parseHead(this.sessionBuffer);
            Header[] headers = parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser);
            message.setHeaders(headers);
            return message;
        } catch (ParseException px) {
            throw new ProtocolException(px.getMessage(), px);
        }
    }
}
