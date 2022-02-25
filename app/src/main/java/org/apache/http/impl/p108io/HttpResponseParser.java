package org.apache.http.impl.p108io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.HttpResponseParser */
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpResponseParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final HttpResponseFactory responseFactory;

    public HttpResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory, HttpParams params) {
        super(buffer, parser, params);
        if (responseFactory != null) {
            this.responseFactory = responseFactory;
            this.lineBuf = new CharArrayBuffer(128);
            return;
        }
        throw new IllegalArgumentException("Response factory may not be null");
    }

    @Override // org.apache.http.impl.p108io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException, ParseException {
        this.lineBuf.clear();
        int i = sessionBuffer.readLine(this.lineBuf);
        if (i != -1) {
            ParserCursor cursor = new ParserCursor(0, this.lineBuf.length());
            StatusLine statusline = this.lineParser.parseStatusLine(this.lineBuf, cursor);
            return this.responseFactory.newHttpResponse(statusline, null);
        }
        throw new NoHttpResponseException("The target server failed to respond");
    }
}
