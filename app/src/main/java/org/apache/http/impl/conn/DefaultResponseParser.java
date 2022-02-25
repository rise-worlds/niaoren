package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.conn.params.ConnConnectionPNames;
import org.apache.http.impl.p108io.AbstractMessageParser;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class DefaultResponseParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final int maxGarbageLines;
    private final HttpResponseFactory responseFactory;

    public DefaultResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory, HttpParams params) {
        super(buffer, parser, params);
        if (responseFactory != null) {
            this.responseFactory = responseFactory;
            this.lineBuf = new CharArrayBuffer(128);
            this.maxGarbageLines = params.getIntParameter(ConnConnectionPNames.MAX_STATUS_LINE_GARBAGE, Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException("Response factory may not be null");
    }

    @Override // org.apache.http.impl.p108io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException {
        this.lineBuf.clear();
        int count = 0;
        while (true) {
            int i = sessionBuffer.readLine(this.lineBuf);
            if (i == -1 && count == 0) {
                throw new NoHttpResponseException("The target server failed to respond");
            }
            ParserCursor cursor = new ParserCursor(0, this.lineBuf.length());
            if (this.lineParser.hasProtocolVersion(this.lineBuf, cursor)) {
                StatusLine statusline = this.lineParser.parseStatusLine(this.lineBuf, cursor);
                return this.responseFactory.newHttpResponse(statusline, null);
            } else if (i == -1 || count >= this.maxGarbageLines) {
                break;
            } else {
                count++;
            }
        }
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
    }
}
