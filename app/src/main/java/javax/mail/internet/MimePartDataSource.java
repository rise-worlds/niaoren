package javax.mail.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownServiceException;
import javax.activation.DataSource;
import javax.mail.MessageAware;
import javax.mail.MessageContext;
import javax.mail.MessagingException;

/* loaded from: classes2.dex */
public class MimePartDataSource implements DataSource, MessageAware {
    private static boolean ignoreMultipartEncoding = true;
    private MessageContext context;
    protected MimePart part;

    static {
        try {
            String property = System.getProperty("mail.mime.ignoremultipartencoding");
            ignoreMultipartEncoding = property == null || !property.equalsIgnoreCase("false");
        } catch (SecurityException unused) {
        }
    }

    public MimePartDataSource(MimePart mimePart) {
        this.part = mimePart;
    }

    @Override // javax.activation.DataSource
    public InputStream getInputStream() throws IOException {
        InputStream inputStream;
        try {
            if (this.part instanceof MimeBodyPart) {
                inputStream = ((MimeBodyPart) this.part).getContentStream();
            } else if (this.part instanceof MimeMessage) {
                inputStream = ((MimeMessage) this.part).getContentStream();
            } else {
                throw new MessagingException("Unknown part");
            }
            String restrictEncoding = restrictEncoding(this.part.getEncoding(), this.part);
            return restrictEncoding != null ? MimeUtility.decode(inputStream, restrictEncoding) : inputStream;
        } catch (MessagingException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static String restrictEncoding(String str, MimePart mimePart) throws MessagingException {
        String contentType;
        ContentType contentType2;
        if (!ignoreMultipartEncoding || str == null || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase("8bit") || str.equalsIgnoreCase("binary") || (contentType = mimePart.getContentType()) == null) {
            return str;
        }
        try {
            contentType2 = new ContentType(contentType);
        } catch (ParseException unused) {
        }
        if (contentType2.match("multipart/*")) {
            return null;
        }
        if (contentType2.match("message/*")) {
            return null;
        }
        return str;
    }

    @Override // javax.activation.DataSource
    public OutputStream getOutputStream() throws IOException {
        throw new UnknownServiceException();
    }

    @Override // javax.activation.DataSource
    public String getContentType() {
        try {
            return this.part.getContentType();
        } catch (MessagingException unused) {
            return "application/octet-stream";
        }
    }

    @Override // javax.activation.DataSource
    public String getName() {
        try {
            return this.part instanceof MimeBodyPart ? ((MimeBodyPart) this.part).getFileName() : "";
        } catch (MessagingException unused) {
            return "";
        }
    }

    @Override // javax.mail.MessageAware
    public synchronized MessageContext getMessageContext() {
        if (this.context == null) {
            this.context = new MessageContext(this.part);
        }
        return this.context;
    }
}
