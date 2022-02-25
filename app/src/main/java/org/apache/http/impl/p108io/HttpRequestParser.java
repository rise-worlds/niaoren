package org.apache.http.impl.p108io;

import java.io.IOException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequestFactory;
import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.p109io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* renamed from: org.apache.http.impl.io.HttpRequestParser */
/* loaded from: assets/org.apache.http.legacy.boot */
public class HttpRequestParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final HttpRequestFactory requestFactory;

    public HttpRequestParser(SessionInputBuffer buffer, LineParser parser, HttpRequestFactory requestFactory, HttpParams params) {
        super(buffer, parser, params);
        if (requestFactory != null) {
            this.requestFactory = requestFactory;
            this.lineBuf = new CharArrayBuffer(128);
            return;
        }
        throw new IllegalArgumentException("Request factory may not be null");
    }

    @Override // org.apache.http.impl.p108io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws IOException, HttpException, ParseException {
        this.lineBuf.clear();
        int i = sessionBuffer.readLine(this.lineBuf);
        if (i != -1) {
            ParserCursor cursor = new ParserCursor(0, this.lineBuf.length());
            RequestLine requestline = this.lineParser.parseRequestLine(this.lineBuf, cursor);
            return this.requestFactory.newHttpRequest(requestline);
        }
        throw new ConnectionClosedException("Client closed connection");
    }
}
