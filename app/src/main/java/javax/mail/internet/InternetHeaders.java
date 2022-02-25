package javax.mail.internet;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.util.LineInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.mail.Header;
import javax.mail.MessagingException;
import org.apache.tools.ant.taskdefs.Manifest;

/* loaded from: classes2.dex */
public class InternetHeaders {
    protected List headers = new ArrayList(40);

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class InternetHeader extends Header {
        String line;

        public InternetHeader(String str) {
            super("", "");
            int indexOf = str.indexOf(58);
            if (indexOf < 0) {
                this.name = str.trim();
            } else {
                this.name = str.substring(0, indexOf).trim();
            }
            this.line = str;
        }

        public InternetHeader(String str, String str2) {
            super(str, "");
            if (str2 != null) {
                this.line = String.valueOf(str) + ": " + str2;
                return;
            }
            this.line = null;
        }

        @Override // javax.mail.Header
        public final String getValue() {
            char charAt;
            int indexOf = this.line.indexOf(58);
            if (indexOf < 0) {
                return this.line;
            }
            while (true) {
                indexOf++;
                if (indexOf < this.line.length() && ((charAt = this.line.charAt(indexOf)) == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n')) {
                }
            }
            return this.line.substring(indexOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class matchEnum implements Enumeration {

        /* renamed from: e */
        private Iterator f14652e;
        private boolean match;
        private String[] names;
        private InternetHeader next_header = null;
        private boolean want_line;

        matchEnum(List list, String[] strArr, boolean z, boolean z2) {
            this.f14652e = list.iterator();
            this.names = strArr;
            this.match = z;
            this.want_line = z2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            if (this.next_header == null) {
                this.next_header = nextMatch();
            }
            return this.next_header != null;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            if (this.next_header == null) {
                this.next_header = nextMatch();
            }
            InternetHeader internetHeader = this.next_header;
            if (internetHeader != null) {
                this.next_header = null;
                if (this.want_line) {
                    return internetHeader.line;
                }
                return new Header(internetHeader.getName(), internetHeader.getValue());
            }
            throw new NoSuchElementException("No more headers");
        }

        private InternetHeader nextMatch() {
            while (this.f14652e.hasNext()) {
                InternetHeader internetHeader = (InternetHeader) this.f14652e.next();
                if (internetHeader.line != null) {
                    if (this.names != null) {
                        int i = 0;
                        while (true) {
                            String[] strArr = this.names;
                            if (i >= strArr.length) {
                                if (!this.match) {
                                    return internetHeader;
                                }
                            } else if (!strArr[i].equalsIgnoreCase(internetHeader.getName())) {
                                i++;
                            } else if (this.match) {
                                return internetHeader;
                            }
                        }
                    } else if (this.match) {
                        return null;
                    } else {
                        return internetHeader;
                    }
                }
            }
            return null;
        }
    }

    public InternetHeaders() {
        this.headers.add(new InternetHeader("Return-Path", null));
        this.headers.add(new InternetHeader("Received", null));
        this.headers.add(new InternetHeader("Resent-Date", null));
        this.headers.add(new InternetHeader("Resent-From", null));
        this.headers.add(new InternetHeader("Resent-Sender", null));
        this.headers.add(new InternetHeader("Resent-To", null));
        this.headers.add(new InternetHeader("Resent-Cc", null));
        this.headers.add(new InternetHeader("Resent-Bcc", null));
        this.headers.add(new InternetHeader("Resent-Message-Id", null));
        this.headers.add(new InternetHeader("Date", null));
        this.headers.add(new InternetHeader(Manifest.ATTRIBUTE_FROM, null));
        this.headers.add(new InternetHeader("Sender", null));
        this.headers.add(new InternetHeader("Reply-To", null));
        this.headers.add(new InternetHeader("To", null));
        this.headers.add(new InternetHeader("Cc", null));
        this.headers.add(new InternetHeader("Bcc", null));
        this.headers.add(new InternetHeader("Message-Id", null));
        this.headers.add(new InternetHeader("In-Reply-To", null));
        this.headers.add(new InternetHeader("References", null));
        this.headers.add(new InternetHeader("Subject", null));
        this.headers.add(new InternetHeader("Comments", null));
        this.headers.add(new InternetHeader("Keywords", null));
        this.headers.add(new InternetHeader("Errors-To", null));
        this.headers.add(new InternetHeader("MIME-Version", null));
        this.headers.add(new InternetHeader("Content-Type", null));
        this.headers.add(new InternetHeader(MIME.CONTENT_TRANSFER_ENC, null));
        this.headers.add(new InternetHeader("Content-MD5", null));
        this.headers.add(new InternetHeader(":", null));
        this.headers.add(new InternetHeader("Content-Length", null));
        this.headers.add(new InternetHeader("Status", null));
    }

    public InternetHeaders(InputStream inputStream) throws MessagingException {
        load(inputStream);
    }

    public void load(InputStream inputStream) throws MessagingException {
        String readLine;
        LineInputStream lineInputStream = new LineInputStream(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        String str = null;
        do {
            try {
                readLine = lineInputStream.readLine();
                if (readLine == null || (!readLine.startsWith(ExpandableTextView.f6958c) && !readLine.startsWith("\t"))) {
                    if (str != null) {
                        addHeaderLine(str);
                    } else if (stringBuffer.length() > 0) {
                        addHeaderLine(stringBuffer.toString());
                        stringBuffer.setLength(0);
                    }
                    str = readLine;
                } else {
                    if (str != null) {
                        stringBuffer.append(str);
                        str = null;
                    }
                    stringBuffer.append("\r\n");
                    stringBuffer.append(readLine);
                }
                if (readLine == null) {
                    return;
                }
            } catch (IOException e) {
                throw new MessagingException("Error in input stream", e);
            }
        } while (readLine.length() > 0);
    }

    public String[] getHeader(String str) {
        ArrayList arrayList = new ArrayList();
        for (InternetHeader internetHeader : this.headers) {
            if (str.equalsIgnoreCase(internetHeader.getName()) && internetHeader.line != null) {
                arrayList.add(internetHeader.getValue());
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String getHeader(String str, String str2) {
        String[] header = getHeader(str);
        if (header == null) {
            return null;
        }
        if (header.length == 1 || str2 == null) {
            return header[0];
        }
        StringBuffer stringBuffer = new StringBuffer(header[0]);
        for (int i = 1; i < header.length; i++) {
            stringBuffer.append(str2);
            stringBuffer.append(header[i]);
        }
        return stringBuffer.toString();
    }

    public void setHeader(String str, String str2) {
        int indexOf;
        int i = 0;
        boolean z = false;
        while (i < this.headers.size()) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (!z) {
                    if (internetHeader.line == null || (indexOf = internetHeader.line.indexOf(58)) < 0) {
                        internetHeader.line = String.valueOf(str) + ": " + str2;
                    } else {
                        internetHeader.line = String.valueOf(internetHeader.line.substring(0, indexOf + 1)) + ExpandableTextView.f6958c + str2;
                    }
                    z = true;
                } else {
                    this.headers.remove(i);
                    i--;
                }
            }
            i++;
        }
        if (!z) {
            addHeader(str, str2);
        }
    }

    public void addHeader(String str, String str2) {
        int size = this.headers.size();
        boolean z = str.equalsIgnoreCase("Received") || str.equalsIgnoreCase("Return-Path");
        if (z) {
            size = 0;
        }
        for (int size2 = this.headers.size() - 1; size2 >= 0; size2--) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(size2);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (z) {
                    size = size2;
                } else {
                    this.headers.add(size2 + 1, new InternetHeader(str, str2));
                    return;
                }
            }
            if (internetHeader.getName().equals(":")) {
                size = size2;
            }
        }
        this.headers.add(size, new InternetHeader(str, str2));
    }

    public void removeHeader(String str) {
        for (int i = 0; i < this.headers.size(); i++) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                internetHeader.line = null;
            }
        }
    }

    public Enumeration getAllHeaders() {
        return new matchEnum(this.headers, null, false, false);
    }

    public Enumeration getMatchingHeaders(String[] strArr) {
        return new matchEnum(this.headers, strArr, true, false);
    }

    public Enumeration getNonMatchingHeaders(String[] strArr) {
        return new matchEnum(this.headers, strArr, false, false);
    }

    public void addHeaderLine(String str) {
        try {
            char charAt = str.charAt(0);
            if (!(charAt == ' ' || charAt == '\t')) {
                this.headers.add(new InternetHeader(str));
                return;
            }
            InternetHeader internetHeader = (InternetHeader) this.headers.get(this.headers.size() - 1);
            internetHeader.line = String.valueOf(internetHeader.line) + "\r\n" + str;
        } catch (StringIndexOutOfBoundsException unused) {
        } catch (NoSuchElementException unused2) {
        }
    }

    public Enumeration getAllHeaderLines() {
        return getNonMatchingHeaderLines(null);
    }

    public Enumeration getMatchingHeaderLines(String[] strArr) {
        return new matchEnum(this.headers, strArr, true, true);
    }

    public Enumeration getNonMatchingHeaderLines(String[] strArr) {
        return new matchEnum(this.headers, strArr, false, true);
    }
}
