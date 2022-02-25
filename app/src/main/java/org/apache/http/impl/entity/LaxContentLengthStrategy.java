package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class LaxContentLengthStrategy implements ContentLengthStrategy {
    @Override // org.apache.http.entity.ContentLengthStrategy
    public long determineLength(HttpMessage message) throws HttpException {
        if (message != null) {
            HttpParams params = message.getParams();
            boolean strict = params.isParameterTrue(CoreProtocolPNames.STRICT_TRANSFER_ENCODING);
            Header transferEncodingHeader = message.getFirstHeader(HTTP.TRANSFER_ENCODING);
            Header contentLengthHeader = message.getFirstHeader("Content-Length");
            if (transferEncodingHeader != null) {
                try {
                    HeaderElement[] encodings = transferEncodingHeader.getElements();
                    if (strict) {
                        for (HeaderElement headerElement : encodings) {
                            String encoding = headerElement.getName();
                            if (encoding != null && encoding.length() > 0 && !encoding.equalsIgnoreCase(HTTP.CHUNK_CODING) && !encoding.equalsIgnoreCase(HTTP.IDENTITY_CODING)) {
                                throw new ProtocolException("Unsupported transfer encoding: " + encoding);
                            }
                        }
                    }
                    int i = encodings.length;
                    if (HTTP.IDENTITY_CODING.equalsIgnoreCase(transferEncodingHeader.getValue())) {
                        return -1L;
                    }
                    if (i > 0 && HTTP.CHUNK_CODING.equalsIgnoreCase(encodings[i - 1].getName())) {
                        return -2L;
                    }
                    if (!strict) {
                        return -1L;
                    }
                    throw new ProtocolException("Chunk-encoding must be the last one applied");
                } catch (ParseException px) {
                    throw new ProtocolException("Invalid Transfer-Encoding header value: " + transferEncodingHeader, px);
                }
            } else if (contentLengthHeader == null) {
                return -1L;
            } else {
                long contentlen = -1;
                Header[] headers = message.getHeaders("Content-Length");
                if (!strict || headers.length <= 1) {
                    for (int i2 = headers.length - 1; i2 >= 0; i2--) {
                        Header header = headers[i2];
                        try {
                            contentlen = Long.parseLong(header.getValue());
                            break;
                        } catch (NumberFormatException e) {
                            if (strict) {
                                throw new ProtocolException("Invalid content length: " + header.getValue());
                            }
                        }
                    }
                    if (contentlen >= 0) {
                        return contentlen;
                    }
                    return -1L;
                }
                throw new ProtocolException("Multiple content length headers");
            }
        } else {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
    }
}
