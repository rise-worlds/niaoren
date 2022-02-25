package javax.mail.internet;

import com.github.kevinsawicki.http.HttpRequest;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.FolderClosedIOException;
import com.sun.mail.util.LineOutputStream;
import com.sun.mail.util.MessageRemovedIOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.HeaderTokenizer;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.taskdefs.email.EmailTask;

/* loaded from: classes2.dex */
public class MimeBodyPart extends BodyPart implements MimePart {
    static boolean cacheMultipart = true;
    private static boolean decodeFileName = false;
    private static boolean encodeFileName = false;
    private static boolean setContentTypeFileName = true;
    private static boolean setDefaultTextCharset = true;
    private Object cachedContent;
    protected byte[] content;
    protected InputStream contentStream;

    /* renamed from: dh */
    protected DataHandler f14654dh;
    protected InternetHeaders headers;

    @Override // javax.mail.Part
    public int getLineCount() throws MessagingException {
        return -1;
    }

    static {
        try {
            String property = System.getProperty("mail.mime.setdefaulttextcharset");
            boolean z = false;
            setDefaultTextCharset = property == null || !property.equalsIgnoreCase("false");
            String property2 = System.getProperty("mail.mime.setcontenttypefilename");
            setContentTypeFileName = property2 == null || !property2.equalsIgnoreCase("false");
            String property3 = System.getProperty("mail.mime.encodefilename");
            encodeFileName = property3 != null && !property3.equalsIgnoreCase("false");
            String property4 = System.getProperty("mail.mime.decodefilename");
            decodeFileName = property4 != null && !property4.equalsIgnoreCase("false");
            String property5 = System.getProperty("mail.mime.cachemultipart");
            if (property5 == null || !property5.equalsIgnoreCase("false")) {
                z = true;
            }
            cacheMultipart = z;
        } catch (SecurityException unused) {
        }
    }

    public MimeBodyPart() {
        this.headers = new InternetHeaders();
    }

    public MimeBodyPart(InputStream inputStream) throws MessagingException {
        if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.headers = new InternetHeaders(inputStream);
        if (inputStream instanceof SharedInputStream) {
            SharedInputStream sharedInputStream = (SharedInputStream) inputStream;
            this.contentStream = sharedInputStream.newStream(sharedInputStream.getPosition(), -1L);
            return;
        }
        try {
            this.content = ASCIIUtility.getBytes(inputStream);
        } catch (IOException e) {
            throw new MessagingException("Error reading input stream", e);
        }
    }

    public MimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        this.headers = internetHeaders;
        this.content = bArr;
    }

    @Override // javax.mail.Part
    public int getSize() throws MessagingException {
        byte[] bArr = this.content;
        if (bArr != null) {
            return bArr.length;
        }
        InputStream inputStream = this.contentStream;
        if (inputStream == null) {
            return -1;
        }
        try {
            int available = inputStream.available();
            if (available > 0) {
                return available;
            }
            return -1;
        } catch (IOException unused) {
            return -1;
        }
    }

    @Override // javax.mail.Part
    public String getContentType() throws MessagingException {
        String header = getHeader("Content-Type", null);
        return header == null ? "text/plain" : header;
    }

    @Override // javax.mail.Part
    public boolean isMimeType(String str) throws MessagingException {
        return isMimeType(this, str);
    }

    @Override // javax.mail.Part
    public String getDisposition() throws MessagingException {
        return getDisposition(this);
    }

    @Override // javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        setDisposition(this, str);
    }

    public String getEncoding() throws MessagingException {
        return getEncoding(this);
    }

    public String getContentID() throws MessagingException {
        return getHeader("Content-Id", null);
    }

    public void setContentID(String str) throws MessagingException {
        if (str == null) {
            removeHeader("Content-ID");
        } else {
            setHeader("Content-ID", str);
        }
    }

    public String getContentMD5() throws MessagingException {
        return getHeader("Content-MD5", null);
    }

    public void setContentMD5(String str) throws MessagingException {
        setHeader("Content-MD5", str);
    }

    @Override // javax.mail.internet.MimePart
    public String[] getContentLanguage() throws MessagingException {
        return getContentLanguage(this);
    }

    @Override // javax.mail.internet.MimePart
    public void setContentLanguage(String[] strArr) throws MessagingException {
        setContentLanguage(this, strArr);
    }

    @Override // javax.mail.Part
    public String getDescription() throws MessagingException {
        return getDescription(this);
    }

    @Override // javax.mail.Part
    public void setDescription(String str) throws MessagingException {
        setDescription(str, null);
    }

    public void setDescription(String str, String str2) throws MessagingException {
        setDescription(this, str, str2);
    }

    @Override // javax.mail.Part
    public String getFileName() throws MessagingException {
        return getFileName(this);
    }

    @Override // javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        setFileName(this, str);
    }

    @Override // javax.mail.Part
    public InputStream getInputStream() throws IOException, MessagingException {
        return getDataHandler().getInputStream();
    }

    public InputStream getContentStream() throws MessagingException {
        InputStream inputStream = this.contentStream;
        if (inputStream != null) {
            return ((SharedInputStream) inputStream).newStream(0L, -1L);
        }
        byte[] bArr = this.content;
        if (bArr != null) {
            return new ByteArrayInputStream(bArr);
        }
        throw new MessagingException("No content");
    }

    public InputStream getRawInputStream() throws MessagingException {
        return getContentStream();
    }

    @Override // javax.mail.Part
    public DataHandler getDataHandler() throws MessagingException {
        if (this.f14654dh == null) {
            this.f14654dh = new DataHandler(new MimePartDataSource(this));
        }
        return this.f14654dh;
    }

    @Override // javax.mail.Part
    public Object getContent() throws IOException, MessagingException {
        Object obj = this.cachedContent;
        if (obj != null) {
            return obj;
        }
        try {
            Object content = getDataHandler().getContent();
            if (cacheMultipart && (((content instanceof Multipart) || (content instanceof Message)) && !(this.content == null && this.contentStream == null))) {
                this.cachedContent = content;
            }
            return content;
        } catch (FolderClosedIOException e) {
            throw new FolderClosedException(e.getFolder(), e.getMessage());
        } catch (MessageRemovedIOException e2) {
            throw new MessageRemovedException(e2.getMessage());
        }
    }

    @Override // javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        this.f14654dh = dataHandler;
        this.cachedContent = null;
        invalidateContentHeaders(this);
    }

    @Override // javax.mail.Part
    public void setContent(Object obj, String str) throws MessagingException {
        if (obj instanceof Multipart) {
            setContent((Multipart) obj);
        } else {
            setDataHandler(new DataHandler(obj, str));
        }
    }

    @Override // javax.mail.Part, javax.mail.internet.MimePart
    public void setText(String str) throws MessagingException {
        setText(str, null);
    }

    @Override // javax.mail.internet.MimePart
    public void setText(String str, String str2) throws MessagingException {
        setText(this, str, str2, EmailTask.PLAIN);
    }

    @Override // javax.mail.internet.MimePart
    public void setText(String str, String str2, String str3) throws MessagingException {
        setText(this, str, str2, str3);
    }

    @Override // javax.mail.Part
    public void setContent(Multipart multipart) throws MessagingException {
        setDataHandler(new DataHandler(multipart, multipart.getContentType()));
        multipart.setParent(this);
    }

    public void attachFile(File file) throws IOException, MessagingException {
        FileDataSource fileDataSource = new FileDataSource(file);
        setDataHandler(new DataHandler(fileDataSource));
        setFileName(fileDataSource.getName());
    }

    public void attachFile(String str) throws IOException, MessagingException {
        attachFile(new File(str));
    }

    public void saveFile(File file) throws IOException, MessagingException {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                inputStream = getInputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                try {
                    bufferedOutputStream.close();
                } catch (IOException unused2) {
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
        }
    }

    public void saveFile(String str) throws IOException, MessagingException {
        saveFile(new File(str));
    }

    @Override // javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        writeTo(this, outputStream, null);
    }

    @Override // javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        return this.headers.getHeader(str);
    }

    @Override // javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        return this.headers.getHeader(str, str2);
    }

    @Override // javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        this.headers.setHeader(str, str2);
    }

    @Override // javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        this.headers.addHeader(str, str2);
    }

    @Override // javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        this.headers.removeHeader(str);
    }

    @Override // javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        return this.headers.getAllHeaders();
    }

    @Override // javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaders(strArr);
    }

    @Override // javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaders(strArr);
    }

    public void addHeaderLine(String str) throws MessagingException {
        this.headers.addHeaderLine(str);
    }

    public Enumeration getAllHeaderLines() throws MessagingException {
        return this.headers.getAllHeaderLines();
    }

    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaderLines(strArr);
    }

    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaderLines(strArr);
    }

    public void updateHeaders() throws MessagingException {
        updateHeaders(this);
        Object obj = this.cachedContent;
        if (obj != null) {
            this.f14654dh = new DataHandler(obj, getContentType());
            this.cachedContent = null;
            this.content = null;
            InputStream inputStream = this.contentStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            this.contentStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMimeType(MimePart mimePart, String str) throws MessagingException {
        try {
            return new ContentType(mimePart.getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setText(MimePart mimePart, String str, String str2, String str3) throws MessagingException {
        if (str2 == null) {
            str2 = MimeUtility.checkAscii(str) != 1 ? MimeUtility.getDefaultMIMECharset() : "us-ascii";
        }
        mimePart.setContent(str, "text/" + str3 + HTTP.CHARSET_PARAM + MimeUtility.quote(str2, HeaderTokenizer.MIME));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDisposition(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader(MIME.CONTENT_DISPOSITION, null);
        if (header == null) {
            return null;
        }
        return new ContentDisposition(header).getDisposition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDisposition(MimePart mimePart, String str) throws MessagingException {
        if (str == null) {
            mimePart.removeHeader(MIME.CONTENT_DISPOSITION);
            return;
        }
        String header = mimePart.getHeader(MIME.CONTENT_DISPOSITION, null);
        if (header != null) {
            ContentDisposition contentDisposition = new ContentDisposition(header);
            contentDisposition.setDisposition(str);
            str = contentDisposition.toString();
        }
        mimePart.setHeader(MIME.CONTENT_DISPOSITION, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDescription(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Description", null);
        if (header == null) {
            return null;
        }
        try {
            return MimeUtility.decodeText(MimeUtility.unfold(header));
        } catch (UnsupportedEncodingException unused) {
            return header;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDescription(MimePart mimePart, String str, String str2) throws MessagingException {
        if (str == null) {
            mimePart.removeHeader("Content-Description");
            return;
        }
        try {
            mimePart.setHeader("Content-Description", MimeUtility.fold(21, MimeUtility.encodeText(str, str2, null)));
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Encoding error", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getFileName(MimePart mimePart) throws MessagingException {
        String header;
        String header2 = mimePart.getHeader(MIME.CONTENT_DISPOSITION, null);
        String parameter = header2 != null ? new ContentDisposition(header2).getParameter("filename") : null;
        if (parameter == null && (header = mimePart.getHeader("Content-Type", null)) != null) {
            try {
                parameter = new ContentType(header).getParameter("name");
            } catch (ParseException unused) {
            }
        }
        if (!decodeFileName || parameter == null) {
            return parameter;
        }
        try {
            return MimeUtility.decodeText(parameter);
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Can't decode filename", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setFileName(MimePart mimePart, String str) throws MessagingException {
        String header;
        if (encodeFileName && str != null) {
            try {
                str = MimeUtility.encodeText(str);
            } catch (UnsupportedEncodingException e) {
                throw new MessagingException("Can't encode filename", e);
            }
        }
        String header2 = mimePart.getHeader(MIME.CONTENT_DISPOSITION, null);
        if (header2 == null) {
            header2 = "attachment";
        }
        ContentDisposition contentDisposition = new ContentDisposition(header2);
        contentDisposition.setParameter("filename", str);
        mimePart.setHeader(MIME.CONTENT_DISPOSITION, contentDisposition.toString());
        if (setContentTypeFileName && (header = mimePart.getHeader("Content-Type", null)) != null) {
            try {
                ContentType contentType = new ContentType(header);
                contentType.setParameter("name", str);
                mimePart.setHeader("Content-Type", contentType.toString());
            } catch (ParseException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getContentLanguage(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Language", null);
        if (header == null) {
            return null;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(header, HeaderTokenizer.MIME);
        Vector vector = new Vector();
        while (true) {
            HeaderTokenizer.Token next = headerTokenizer.next();
            int type = next.getType();
            if (type == -4) {
                break;
            } else if (type == -1) {
                vector.addElement(next.getValue());
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContentLanguage(MimePart mimePart, String[] strArr) throws MessagingException {
        StringBuffer stringBuffer = new StringBuffer(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(strArr[i]);
        }
        mimePart.setHeader("Content-Language", stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getEncoding(MimePart mimePart) throws MessagingException {
        HeaderTokenizer.Token next;
        int type;
        String header = mimePart.getHeader(MIME.CONTENT_TRANSFER_ENC, null);
        if (header == null) {
            return null;
        }
        String trim = header.trim();
        if (trim.equalsIgnoreCase("7bit") || trim.equalsIgnoreCase("8bit") || trim.equalsIgnoreCase("quoted-printable") || trim.equalsIgnoreCase("binary") || trim.equalsIgnoreCase("base64")) {
            return trim;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(trim, HeaderTokenizer.MIME);
        do {
            next = headerTokenizer.next();
            type = next.getType();
            if (type == -4) {
                return trim;
            }
        } while (type != -1);
        return next.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setEncoding(MimePart mimePart, String str) throws MessagingException {
        mimePart.setHeader(MIME.CONTENT_TRANSFER_ENC, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateHeaders(MimePart mimePart) throws MessagingException {
        String parameter;
        Object obj;
        DataHandler dataHandler = mimePart.getDataHandler();
        if (dataHandler != null) {
            try {
                String contentType = dataHandler.getContentType();
                boolean z = true;
                boolean z2 = mimePart.getHeader("Content-Type") == null;
                ContentType contentType2 = new ContentType(contentType);
                if (contentType2.match("multipart/*")) {
                    if (mimePart instanceof MimeBodyPart) {
                        MimeBodyPart mimeBodyPart = (MimeBodyPart) mimePart;
                        obj = mimeBodyPart.cachedContent != null ? mimeBodyPart.cachedContent : dataHandler.getContent();
                    } else if (mimePart instanceof MimeMessage) {
                        MimeMessage mimeMessage = (MimeMessage) mimePart;
                        obj = mimeMessage.cachedContent != null ? mimeMessage.cachedContent : dataHandler.getContent();
                    } else {
                        obj = dataHandler.getContent();
                    }
                    if (obj instanceof MimeMultipart) {
                        ((MimeMultipart) obj).updateHeaders();
                    } else {
                        throw new MessagingException("MIME part of type \"" + contentType + "\" contains object of type " + obj.getClass().getName() + " instead of MimeMultipart");
                    }
                } else if (!contentType2.match("message/rfc822")) {
                    z = false;
                }
                if (!z) {
                    if (mimePart.getHeader(MIME.CONTENT_TRANSFER_ENC) == null) {
                        setEncoding(mimePart, MimeUtility.getEncoding(dataHandler));
                    }
                    if (z2 && setDefaultTextCharset && contentType2.match("text/*") && contentType2.getParameter(HttpRequest.PARAM_CHARSET) == null) {
                        String encoding = mimePart.getEncoding();
                        contentType2.setParameter(HttpRequest.PARAM_CHARSET, (encoding == null || !encoding.equalsIgnoreCase("7bit")) ? MimeUtility.getDefaultMIMECharset() : "us-ascii");
                        contentType = contentType2.toString();
                    }
                }
                if (z2) {
                    String header = mimePart.getHeader(MIME.CONTENT_DISPOSITION, null);
                    if (!(header == null || (parameter = new ContentDisposition(header).getParameter("filename")) == null)) {
                        contentType2.setParameter("name", parameter);
                        contentType = contentType2.toString();
                    }
                    mimePart.setHeader("Content-Type", contentType);
                }
            } catch (IOException e) {
                throw new MessagingException("IOException updating headers", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invalidateContentHeaders(MimePart mimePart) throws MessagingException {
        mimePart.removeHeader("Content-Type");
        mimePart.removeHeader(MIME.CONTENT_TRANSFER_ENC);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeTo(MimePart mimePart, OutputStream outputStream, String[] strArr) throws IOException, MessagingException {
        LineOutputStream lineOutputStream;
        if (outputStream instanceof LineOutputStream) {
            lineOutputStream = (LineOutputStream) outputStream;
        } else {
            lineOutputStream = new LineOutputStream(outputStream);
        }
        Enumeration nonMatchingHeaderLines = mimePart.getNonMatchingHeaderLines(strArr);
        while (nonMatchingHeaderLines.hasMoreElements()) {
            lineOutputStream.writeln((String) nonMatchingHeaderLines.nextElement());
        }
        lineOutputStream.writeln();
        OutputStream encode = MimeUtility.encode(outputStream, mimePart.getEncoding());
        mimePart.getDataHandler().writeTo(encode);
        encode.flush();
    }
}
