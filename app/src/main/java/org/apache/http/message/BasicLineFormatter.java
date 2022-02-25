package org.apache.http.message;

import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicLineFormatter implements LineFormatter {
    public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();

    protected CharArrayBuffer initBuffer(CharArrayBuffer buffer) {
        if (buffer == null) {
            return new CharArrayBuffer(64);
        }
        buffer.clear();
        return buffer;
    }

    public static final String formatProtocolVersion(ProtocolVersion version, LineFormatter formatter) {
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.appendProtocolVersion(null, version).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer buffer, ProtocolVersion version) {
        if (version != null) {
            CharArrayBuffer result = buffer;
            int len = estimateProtocolVersionLen(version);
            if (result == null) {
                result = new CharArrayBuffer(len);
            } else {
                result.ensureCapacity(len);
            }
            result.append(version.getProtocol());
            result.append(IOUtils.DIR_SEPARATOR_UNIX);
            result.append(Integer.toString(version.getMajor()));
            result.append(FilenameUtils.EXTENSION_SEPARATOR);
            result.append(Integer.toString(version.getMinor()));
            return result;
        }
        throw new IllegalArgumentException("Protocol version may not be null");
    }

    protected int estimateProtocolVersionLen(ProtocolVersion version) {
        return version.getProtocol().length() + 4;
    }

    public static final String formatRequestLine(RequestLine reqline, LineFormatter formatter) {
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatRequestLine(null, reqline).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatRequestLine(CharArrayBuffer buffer, RequestLine reqline) {
        if (reqline != null) {
            CharArrayBuffer result = initBuffer(buffer);
            doFormatRequestLine(result, reqline);
            return result;
        }
        throw new IllegalArgumentException("Request line may not be null");
    }

    protected void doFormatRequestLine(CharArrayBuffer buffer, RequestLine reqline) {
        String method = reqline.getMethod();
        String uri = reqline.getUri();
        int len = method.length() + 1 + uri.length() + 1 + estimateProtocolVersionLen(reqline.getProtocolVersion());
        buffer.ensureCapacity(len);
        buffer.append(method);
        buffer.append(' ');
        buffer.append(uri);
        buffer.append(' ');
        appendProtocolVersion(buffer, reqline.getProtocolVersion());
    }

    public static final String formatStatusLine(StatusLine statline, LineFormatter formatter) {
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatStatusLine(null, statline).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatStatusLine(CharArrayBuffer buffer, StatusLine statline) {
        if (statline != null) {
            CharArrayBuffer result = initBuffer(buffer);
            doFormatStatusLine(result, statline);
            return result;
        }
        throw new IllegalArgumentException("Status line may not be null");
    }

    protected void doFormatStatusLine(CharArrayBuffer buffer, StatusLine statline) {
        int len = estimateProtocolVersionLen(statline.getProtocolVersion()) + 1 + 3 + 1;
        String reason = statline.getReasonPhrase();
        if (reason != null) {
            len += reason.length();
        }
        buffer.ensureCapacity(len);
        appendProtocolVersion(buffer, statline.getProtocolVersion());
        buffer.append(' ');
        buffer.append(Integer.toString(statline.getStatusCode()));
        buffer.append(' ');
        if (reason != null) {
            buffer.append(reason);
        }
    }

    public static final String formatHeader(Header header, LineFormatter formatter) {
        if (formatter == null) {
            formatter = DEFAULT;
        }
        return formatter.formatHeader(null, header).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatHeader(CharArrayBuffer buffer, Header header) {
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        } else if (header instanceof FormattedHeader) {
            return ((FormattedHeader) header).getBuffer();
        } else {
            CharArrayBuffer result = initBuffer(buffer);
            doFormatHeader(result, header);
            return result;
        }
    }

    protected void doFormatHeader(CharArrayBuffer buffer, Header header) {
        String name = header.getName();
        String value = header.getValue();
        int len = name.length() + 2;
        if (value != null) {
            len += value.length();
        }
        buffer.ensureCapacity(len);
        buffer.append(name);
        buffer.append(": ");
        if (value != null) {
            buffer.append(value);
        }
    }
}
