package com.sun.mail.imap;

import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.protocol.BODY;
import com.sun.mail.imap.protocol.BODYSTRUCTURE;
import com.sun.mail.imap.protocol.IMAPProtocol;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import javax.activation.DataHandler;
import javax.mail.FolderClosedException;
import javax.mail.IllegalWriteException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

/* loaded from: classes2.dex */
public class IMAPBodyPart extends MimeBodyPart {

    /* renamed from: bs */
    private BODYSTRUCTURE f12654bs;
    private String description;
    private boolean headersLoaded = false;
    private IMAPMessage message;
    private String sectionId;
    private String type;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.internet.MimeBodyPart
    public void updateHeaders() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPBodyPart(BODYSTRUCTURE bodystructure, String str, IMAPMessage iMAPMessage) {
        this.f12654bs = bodystructure;
        this.sectionId = str;
        this.message = iMAPMessage;
        this.type = new ContentType(bodystructure.type, bodystructure.subtype, bodystructure.cParams).toString();
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public int getSize() throws MessagingException {
        return this.f12654bs.size;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public int getLineCount() throws MessagingException {
        return this.f12654bs.lines;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public String getContentType() throws MessagingException {
        return this.type;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public String getDisposition() throws MessagingException {
        return this.f12654bs.disposition;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public String getEncoding() throws MessagingException {
        return this.f12654bs.encoding;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public String getContentID() throws MessagingException {
        return this.f12654bs.f12656id;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public String getContentMD5() throws MessagingException {
        return this.f12654bs.md5;
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public void setContentMD5(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public String getDescription() throws MessagingException {
        String str = this.description;
        if (str != null) {
            return str;
        }
        if (this.f12654bs.description == null) {
            return null;
        }
        try {
            this.description = MimeUtility.decodeText(this.f12654bs.description);
        } catch (UnsupportedEncodingException unused) {
            this.description = this.f12654bs.description;
        }
        return this.description;
    }

    @Override // javax.mail.internet.MimeBodyPart
    public void setDescription(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public String getFileName() throws MessagingException {
        String str = this.f12654bs.dParams != null ? this.f12654bs.dParams.get("filename") : null;
        return (str != null || this.f12654bs.cParams == null) ? str : this.f12654bs.cParams.get("name");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.internet.MimeBodyPart
    public InputStream getContentStream() throws MessagingException {
        BODY body;
        boolean peek = this.message.getPeek();
        synchronized (this.message.getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = this.message.getProtocol();
                this.message.checkExpunged();
                if (!protocol.isREV1() || this.message.getFetchBlockSize() == -1) {
                    int sequenceNumber = this.message.getSequenceNumber();
                    if (peek) {
                        body = protocol.peekBody(sequenceNumber, this.sectionId);
                    } else {
                        body = protocol.fetchBody(sequenceNumber, this.sectionId);
                    }
                    ByteArrayInputStream byteArrayInputStream = body != null ? body.getByteArrayInputStream() : null;
                    if (byteArrayInputStream != null) {
                        return byteArrayInputStream;
                    }
                    throw new MessagingException("No content");
                }
                return new IMAPInputStream(this.message, this.sectionId, this.f12654bs.size, peek);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.message.getFolder(), e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public synchronized DataHandler getDataHandler() throws MessagingException {
        if (this.f14654dh == null) {
            if (this.f12654bs.isMulti()) {
                this.f14654dh = new DataHandler(new IMAPMultipartDataSource(this, this.f12654bs.bodies, this.sectionId, this.message));
            } else if (this.f12654bs.isNested() && this.message.isREV1()) {
                this.f14654dh = new DataHandler(new IMAPNestedMessage(this.message, this.f12654bs.bodies[0], this.f12654bs.envelope, this.sectionId), this.type);
            }
        }
        return super.getDataHandler();
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setContent(Object obj, String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setContent(Multipart multipart) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        loadHeaders();
        return super.getHeader(str);
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        loadHeaders();
        return super.getAllHeaders();
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        loadHeaders();
        return super.getMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        loadHeaders();
        return super.getNonMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public void addHeaderLine(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPBodyPart is read-only");
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public Enumeration getAllHeaderLines() throws MessagingException {
        loadHeaders();
        return super.getAllHeaderLines();
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        loadHeaders();
        return super.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeBodyPart, javax.mail.internet.MimePart
    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        loadHeaders();
        return super.getNonMatchingHeaderLines(strArr);
    }

    private synchronized void loadHeaders() throws MessagingException {
        if (!this.headersLoaded) {
            if (this.headers == null) {
                this.headers = new InternetHeaders();
            }
            synchronized (this.message.getMessageCacheLock()) {
                try {
                    try {
                        IMAPProtocol protocol = this.message.getProtocol();
                        this.message.checkExpunged();
                        if (protocol.isREV1()) {
                            int sequenceNumber = this.message.getSequenceNumber();
                            BODY peekBody = protocol.peekBody(sequenceNumber, String.valueOf(this.sectionId) + ".MIME");
                            if (peekBody != null) {
                                ByteArrayInputStream byteArrayInputStream = peekBody.getByteArrayInputStream();
                                if (byteArrayInputStream != null) {
                                    this.headers.load(byteArrayInputStream);
                                } else {
                                    throw new MessagingException("Failed to fetch headers");
                                }
                            } else {
                                throw new MessagingException("Failed to fetch headers");
                            }
                        } else {
                            this.headers.addHeader("Content-Type", this.type);
                            this.headers.addHeader(MIME.CONTENT_TRANSFER_ENC, this.f12654bs.encoding);
                            if (this.f12654bs.description != null) {
                                this.headers.addHeader("Content-Description", this.f12654bs.description);
                            }
                            if (this.f12654bs.f12656id != null) {
                                this.headers.addHeader("Content-ID", this.f12654bs.f12656id);
                            }
                            if (this.f12654bs.md5 != null) {
                                this.headers.addHeader("Content-MD5", this.f12654bs.md5);
                            }
                        }
                    } catch (ConnectionException e) {
                        throw new FolderClosedException(this.message.getFolder(), e.getMessage());
                    }
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            this.headersLoaded = true;
        }
    }
}
