package com.sun.mail.imap;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.protocol.BODY;
import com.sun.mail.imap.protocol.BODYSTRUCTURE;
import com.sun.mail.imap.protocol.ENVELOPE;
import com.sun.mail.imap.protocol.FetchResponse;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.INTERNALDATE;
import com.sun.mail.imap.protocol.Item;
import com.sun.mail.imap.protocol.RFC822DATA;
import com.sun.mail.imap.protocol.RFC822SIZE;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.FolderClosedException;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class IMAPMessage extends MimeMessage {
    private static String EnvelopeCmd = "ENVELOPE INTERNALDATE RFC822.SIZE";

    /* renamed from: bs */
    protected BODYSTRUCTURE f12655bs;
    private String description;
    protected ENVELOPE envelope;
    private Hashtable loadedHeaders;
    private boolean peek;
    private Date receivedDate;
    protected String sectionId;
    private int seqnum;
    private String subject;
    private String type;
    private int size = -1;
    private long uid = -1;
    private boolean headersLoaded = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPMessage(IMAPFolder iMAPFolder, int i, int i2) {
        super(iMAPFolder, i);
        this.seqnum = i2;
        this.flags = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPMessage(Session session) {
        super(session);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPProtocol getProtocol() throws ProtocolException, FolderClosedException {
        ((IMAPFolder) this.folder).waitIfIdle();
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol != null) {
            return iMAPProtocol;
        }
        throw new FolderClosedException(this.folder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isREV1() throws FolderClosedException {
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol != null) {
            return iMAPProtocol.isREV1();
        }
        throw new FolderClosedException(this.folder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getMessageCacheLock() {
        return ((IMAPFolder) this.folder).messageCacheLock;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getSequenceNumber() {
        return this.seqnum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSequenceNumber(int i) {
        this.seqnum = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Message
    public void setMessageNumber(int i) {
        super.setMessageNumber(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getUID() {
        return this.uid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUID(long j) {
        this.uid = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Message
    public void setExpunged(boolean z) {
        super.setExpunged(z);
        this.seqnum = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkExpunged() throws MessageRemovedException {
        if (this.expunged) {
            throw new MessageRemovedException();
        }
    }

    protected void forceCheckExpunged() throws MessageRemovedException, FolderClosedException {
        synchronized (getMessageCacheLock()) {
            try {
                getProtocol().noop();
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException unused) {
            }
        }
        if (this.expunged) {
            throw new MessageRemovedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getFetchBlockSize() {
        return ((IMAPStore) this.folder.getStore()).getFetchBlockSize();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getFrom() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return aaclone(this.envelope.from);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setFrom(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addFrom(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public Address getSender() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (this.envelope.sender != null) {
            return this.envelope.sender[0];
        }
        return null;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSender(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getRecipients(Message.RecipientType recipientType) throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (recipientType == Message.RecipientType.f14650TO) {
            return aaclone(this.envelope.f12658to);
        }
        if (recipientType == Message.RecipientType.f14649CC) {
            return aaclone(this.envelope.f12657cc);
        }
        if (recipientType == Message.RecipientType.BCC) {
            return aaclone(this.envelope.bcc);
        }
        return super.getRecipients(recipientType);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getReplyTo() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return aaclone(this.envelope.replyTo);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setReplyTo(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public String getSubject() throws MessagingException {
        checkExpunged();
        String str = this.subject;
        if (str != null) {
            return str;
        }
        loadEnvelope();
        if (this.envelope.subject == null) {
            return null;
        }
        try {
            this.subject = MimeUtility.decodeText(this.envelope.subject);
        } catch (UnsupportedEncodingException unused) {
            this.subject = this.envelope.subject;
        }
        return this.subject;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSubject(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getSentDate() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (this.envelope.date == null) {
            return null;
        }
        return new Date(this.envelope.date.getTime());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setSentDate(Date date) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getReceivedDate() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        Date date = this.receivedDate;
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getSize() throws MessagingException {
        checkExpunged();
        if (this.size == -1) {
            loadEnvelope();
        }
        return this.size;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getLineCount() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f12655bs.lines;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String[] getContentLanguage() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        if (this.f12655bs.language != null) {
            return (String[]) this.f12655bs.language.clone();
        }
        return null;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentLanguage(String[] strArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    public String getInReplyTo() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return this.envelope.inReplyTo;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getContentType() throws MessagingException {
        checkExpunged();
        if (this.type == null) {
            loadBODYSTRUCTURE();
            this.type = new ContentType(this.f12655bs.type, this.f12655bs.subtype, this.f12655bs.cParams).toString();
        }
        return this.type;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDisposition() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f12655bs.disposition;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getEncoding() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f12655bs.encoding;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentID() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f12655bs.f12656id;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setContentID(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentMD5() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f12655bs.md5;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentMD5(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDescription() throws MessagingException {
        checkExpunged();
        String str = this.description;
        if (str != null) {
            return str;
        }
        loadBODYSTRUCTURE();
        if (this.f12655bs.description == null) {
            return null;
        }
        try {
            this.description = MimeUtility.decodeText(this.f12655bs.description);
        } catch (UnsupportedEncodingException unused) {
            this.description = this.f12655bs.description;
        }
        return this.description;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setDescription(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public String getMessageID() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return this.envelope.messageId;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getFileName() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        String str = this.f12655bs.dParams != null ? this.f12655bs.dParams.get("filename") : null;
        return (str != null || this.f12655bs.cParams == null) ? str : this.f12655bs.cParams.get("name");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
    @Override // javax.mail.internet.MimeMessage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.InputStream getContentStream() throws javax.mail.MessagingException {
        /*
            r6 = this;
            boolean r0 = r6.getPeek()
            java.lang.Object r1 = r6.getMessageCacheLock()
            monitor-enter(r1)
            com.sun.mail.imap.protocol.IMAPProtocol r2 = r6.getProtocol()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            r6.checkExpunged()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            boolean r3 = r2.isREV1()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            if (r3 == 0) goto L_0x0032
            int r3 = r6.getFetchBlockSize()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            r4 = -1
            if (r3 == r4) goto L_0x0032
            com.sun.mail.imap.IMAPInputStream r2 = new com.sun.mail.imap.IMAPInputStream     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            java.lang.String r3 = "TEXT"
            java.lang.String r3 = r6.toSection(r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            com.sun.mail.imap.protocol.BODYSTRUCTURE r5 = r6.f12655bs     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            if (r5 == 0) goto L_0x002d
            com.sun.mail.imap.protocol.BODYSTRUCTURE r4 = r6.f12655bs     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            int r4 = r4.size     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
        L_0x002d:
            r2.<init>(r6, r3, r4, r0)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            monitor-exit(r1)     // Catch: all -> 0x007c
            return r2
        L_0x0032:
            boolean r3 = r2.isREV1()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            if (r3 == 0) goto L_0x005e
            if (r0 == 0) goto L_0x0049
            int r0 = r6.getSequenceNumber()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            java.lang.String r3 = "TEXT"
            java.lang.String r3 = r6.toSection(r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            com.sun.mail.imap.protocol.BODY r0 = r2.peekBody(r0, r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            goto L_0x0057
        L_0x0049:
            int r0 = r6.getSequenceNumber()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            java.lang.String r3 = "TEXT"
            java.lang.String r3 = r6.toSection(r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            com.sun.mail.imap.protocol.BODY r0 = r2.fetchBody(r0, r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
        L_0x0057:
            if (r0 == 0) goto L_0x006f
            java.io.ByteArrayInputStream r0 = r0.getByteArrayInputStream()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            goto L_0x0070
        L_0x005e:
            int r0 = r6.getSequenceNumber()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            java.lang.String r3 = "TEXT"
            com.sun.mail.imap.protocol.RFC822DATA r0 = r2.fetchRFC822(r0, r3)     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            if (r0 == 0) goto L_0x006f
            java.io.ByteArrayInputStream r0 = r0.getByteArrayInputStream()     // Catch: all -> 0x007c, ProtocolException -> 0x007e, ConnectionException -> 0x008c
            goto L_0x0070
        L_0x006f:
            r0 = 0
        L_0x0070:
            monitor-exit(r1)     // Catch: all -> 0x007c
            if (r0 == 0) goto L_0x0074
            return r0
        L_0x0074:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException
            java.lang.String r1 = "No content"
            r0.<init>(r1)
            throw r0
        L_0x007c:
            r0 = move-exception
            goto L_0x0099
        L_0x007e:
            r0 = move-exception
            r6.forceCheckExpunged()     // Catch: all -> 0x007c
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch: all -> 0x007c
            java.lang.String r3 = r0.getMessage()     // Catch: all -> 0x007c
            r2.<init>(r3, r0)     // Catch: all -> 0x007c
            throw r2     // Catch: all -> 0x007c
        L_0x008c:
            r0 = move-exception
            javax.mail.FolderClosedException r2 = new javax.mail.FolderClosedException     // Catch: all -> 0x007c
            javax.mail.Folder r3 = r6.folder     // Catch: all -> 0x007c
            java.lang.String r0 = r0.getMessage()     // Catch: all -> 0x007c
            r2.<init>(r3, r0)     // Catch: all -> 0x007c
            throw r2     // Catch: all -> 0x007c
        L_0x0099:
            monitor-exit(r1)     // Catch: all -> 0x007c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPMessage.getContentStream():java.io.InputStream");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public synchronized DataHandler getDataHandler() throws MessagingException {
        String str;
        checkExpunged();
        if (this.f14655dh == null) {
            loadBODYSTRUCTURE();
            if (this.type == null) {
                this.type = new ContentType(this.f12655bs.type, this.f12655bs.subtype, this.f12655bs.cParams).toString();
            }
            if (this.f12655bs.isMulti()) {
                this.f14655dh = new DataHandler(new IMAPMultipartDataSource(this, this.f12655bs.bodies, this.sectionId, this));
            } else if (this.f12655bs.isNested() && isREV1()) {
                BODYSTRUCTURE bodystructure = this.f12655bs.bodies[0];
                ENVELOPE envelope = this.f12655bs.envelope;
                if (this.sectionId == null) {
                    str = "1";
                } else {
                    str = String.valueOf(this.sectionId) + ".1";
                }
                this.f14655dh = new DataHandler(new IMAPNestedMessage(this, bodystructure, envelope, str), this.type);
            }
        }
        return super.getDataHandler();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        BODY body;
        boolean peek = getPeek();
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                byteArrayInputStream = null;
                if (protocol.isREV1()) {
                    if (peek) {
                        body = protocol.peekBody(getSequenceNumber(), this.sectionId);
                    } else {
                        body = protocol.fetchBody(getSequenceNumber(), this.sectionId);
                    }
                    if (body != null) {
                        byteArrayInputStream = body.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), null);
                    if (fetchRFC822 != null) {
                        byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream != null) {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } else {
            throw new MessagingException("No content");
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        checkExpunged();
        if (isHeaderLoaded(str)) {
            return this.headers.getHeader(str);
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    BODY peekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER.FIELDS (" + str + ")"));
                    if (peekBody != null) {
                        byteArrayInputStream = peekBody.getByteArrayInputStream();
                    }
                    byteArrayInputStream = null;
                } else {
                    RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER.LINES (" + str + ")");
                    if (fetchRFC822 != null) {
                        byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                    }
                    byteArrayInputStream = null;
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream == null) {
            return null;
        }
        if (this.headers == null) {
            this.headers = new InternetHeaders();
        }
        this.headers.load(byteArrayInputStream);
        setHeaderLoaded(str);
        return this.headers.getHeader(str);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        checkExpunged();
        if (getHeader(str) == null) {
            return null;
        }
        return this.headers.getHeader(str, str2);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaders();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void addHeaderLine(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getAllHeaderLines() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaderLines();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized Flags getFlags() throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.getFlags();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized boolean isSet(Flags.Flag flag) throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.isSet(flag);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized void setFlags(Flags flags, boolean z) throws MessagingException {
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                protocol.storeFlags(getSequenceNumber(), flags, z);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    public synchronized void setPeek(boolean z) {
        this.peek = z;
    }

    public synchronized boolean getPeek() {
        return this.peek;
    }

    public synchronized void invalidateHeaders() {
        this.headersLoaded = false;
        this.loadedHeaders = null;
        this.envelope = null;
        this.f12655bs = null;
        this.receivedDate = null;
        this.size = -1;
        this.type = null;
        this.subject = null;
        this.description = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01f9 A[Catch: all -> 0x0221, TryCatch #0 {, blocks: (B:55:0x00b4, B:57:0x00ba, B:59:0x00bc, B:60:0x00c1, B:62:0x00cd, B:63:0x00d6, B:65:0x00d9, B:68:0x00dc, B:70:0x00df, B:72:0x00e5, B:73:0x00ed, B:75:0x00ef, B:77:0x00f3, B:79:0x00f9, B:80:0x0101, B:83:0x0117, B:85:0x0120, B:87:0x0128, B:91:0x0133, B:93:0x0140, B:95:0x0144, B:96:0x014d, B:98:0x0151, B:99:0x015e, B:101:0x0162, B:102:0x016d, B:104:0x0171, B:105:0x017a, B:107:0x017e, B:109:0x0188, B:110:0x018f, B:111:0x019f, B:113:0x01a5, B:117:0x01ac, B:119:0x01b0, B:120:0x01b7, B:121:0x01bd, B:125:0x01cc, B:126:0x01d0, B:129:0x01d7, B:131:0x01e7, B:132:0x01f5, B:134:0x01f9, B:136:0x0200, B:139:0x0205, B:140:0x0209, B:142:0x0212, B:144:0x0217, B:145:0x0220), top: B:150:0x00b4, inners: #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void fetch(com.sun.mail.imap.IMAPFolder r16, javax.mail.Message[] r17, javax.mail.FetchProfile r18) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPMessage.fetch(com.sun.mail.imap.IMAPFolder, javax.mail.Message[], javax.mail.FetchProfile):void");
    }

    private synchronized void loadEnvelope() throws MessagingException {
        if (this.envelope == null) {
            synchronized (getMessageCacheLock()) {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    int sequenceNumber = getSequenceNumber();
                    Response[] fetch = protocol.fetch(sequenceNumber, EnvelopeCmd);
                    for (int i = 0; i < fetch.length; i++) {
                        if (fetch[i] != null && (fetch[i] instanceof FetchResponse) && ((FetchResponse) fetch[i]).getNumber() == sequenceNumber) {
                            FetchResponse fetchResponse = (FetchResponse) fetch[i];
                            int itemCount = fetchResponse.getItemCount();
                            for (int i2 = 0; i2 < itemCount; i2++) {
                                Item item = fetchResponse.getItem(i2);
                                if (item instanceof ENVELOPE) {
                                    this.envelope = (ENVELOPE) item;
                                } else if (item instanceof INTERNALDATE) {
                                    this.receivedDate = ((INTERNALDATE) item).getDate();
                                } else if (item instanceof RFC822SIZE) {
                                    this.size = ((RFC822SIZE) item).size;
                                }
                            }
                        }
                    }
                    protocol.notifyResponseHandlers(fetch);
                    protocol.handleResult(fetch[fetch.length - 1]);
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                } catch (ProtocolException e2) {
                    forceCheckExpunged();
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            if (this.envelope == null) {
                throw new MessagingException("Failed to load IMAP envelope");
            }
        }
    }

    private static String craftHeaderCmd(IMAPProtocol iMAPProtocol, String[] strArr) {
        StringBuffer stringBuffer;
        if (iMAPProtocol.isREV1()) {
            stringBuffer = new StringBuffer("BODY.PEEK[HEADER.FIELDS (");
        } else {
            stringBuffer = new StringBuffer("RFC822.HEADER.LINES (");
        }
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(ExpandableTextView.f6958c);
            }
            stringBuffer.append(strArr[i]);
        }
        if (iMAPProtocol.isREV1()) {
            stringBuffer.append(")]");
        } else {
            stringBuffer.append(")");
        }
        return stringBuffer.toString();
    }

    private synchronized void loadBODYSTRUCTURE() throws MessagingException {
        if (this.f12655bs == null) {
            synchronized (getMessageCacheLock()) {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    this.f12655bs = protocol.fetchBodyStructure(getSequenceNumber());
                    if (this.f12655bs == null) {
                        forceCheckExpunged();
                        throw new MessagingException("Unable to load BODYSTRUCTURE");
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                } catch (ProtocolException e2) {
                    forceCheckExpunged();
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
        }
    }

    private synchronized void loadHeaders() throws MessagingException {
        if (!this.headersLoaded) {
            ByteArrayInputStream byteArrayInputStream = null;
            synchronized (getMessageCacheLock()) {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    if (protocol.isREV1()) {
                        BODY peekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER"));
                        if (peekBody != null) {
                            byteArrayInputStream = peekBody.getByteArrayInputStream();
                        }
                    } else {
                        RFC822DATA fetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER");
                        if (fetchRFC822 != null) {
                            byteArrayInputStream = fetchRFC822.getByteArrayInputStream();
                        }
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                } catch (ProtocolException e2) {
                    forceCheckExpunged();
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            if (byteArrayInputStream != null) {
                this.headers = new InternetHeaders(byteArrayInputStream);
                this.headersLoaded = true;
                return;
            }
            throw new MessagingException("Cannot load header");
        }
    }

    private synchronized void loadFlags() throws MessagingException {
        if (this.flags == null) {
            synchronized (getMessageCacheLock()) {
                try {
                    try {
                        IMAPProtocol protocol = getProtocol();
                        checkExpunged();
                        this.flags = protocol.fetchFlags(getSequenceNumber());
                    } catch (ConnectionException e) {
                        throw new FolderClosedException(this.folder, e.getMessage());
                    }
                } catch (ProtocolException e2) {
                    forceCheckExpunged();
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean areHeadersLoaded() {
        return this.headersLoaded;
    }

    private synchronized void setHeadersLoaded(boolean z) {
        this.headersLoaded = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isHeaderLoaded(String str) {
        if (this.headersLoaded) {
            return true;
        }
        if (this.loadedHeaders == null) {
            return false;
        }
        return this.loadedHeaders.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    private synchronized void setHeaderLoaded(String str) {
        if (this.loadedHeaders == null) {
            this.loadedHeaders = new Hashtable(1);
        }
        this.loadedHeaders.put(str.toUpperCase(Locale.ENGLISH), str);
    }

    private String toSection(String str) {
        String str2 = this.sectionId;
        if (str2 == null) {
            return str;
        }
        return String.valueOf(str2) + Consts.f23430h + str;
    }

    private InternetAddress[] aaclone(InternetAddress[] internetAddressArr) {
        if (internetAddressArr == null) {
            return null;
        }
        return (InternetAddress[]) internetAddressArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Flags _getFlags() {
        return this.flags;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ENVELOPE _getEnvelope() {
        return this.envelope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BODYSTRUCTURE _getBodyStructure() {
        return this.f12655bs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setFlags(Flags flags) {
        this.flags = flags;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session _getSession() {
        return this.session;
    }
}
