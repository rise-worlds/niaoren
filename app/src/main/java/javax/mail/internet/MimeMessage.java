package javax.mail.internet;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.FolderClosedIOException;
import com.sun.mail.util.LineOutputStream;
import com.sun.mail.util.MessageRemovedIOException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.util.SharedByteArrayInputStream;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.email.EmailTask;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class MimeMessage extends Message implements MimePart {
    Object cachedContent;
    protected byte[] content;
    protected InputStream contentStream;

    /* renamed from: dh */
    protected DataHandler f14655dh;
    protected Flags flags;
    protected InternetHeaders headers;
    protected boolean modified;
    protected boolean saved;
    private boolean strict;
    private static MailDateFormat mailDateFormat = new MailDateFormat();
    private static final Flags answeredFlag = new Flags(Flags.Flag.ANSWERED);

    @Override // javax.mail.Part
    public int getLineCount() throws MessagingException {
        return -1;
    }

    @Override // javax.mail.Message
    public Date getReceivedDate() throws MessagingException {
        return null;
    }

    public MimeMessage(Session session) {
        super(session);
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.modified = true;
        this.headers = new InternetHeaders();
        this.flags = new Flags();
        initStrict();
    }

    public MimeMessage(Session session, InputStream inputStream) throws MessagingException {
        super(session);
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.flags = new Flags();
        initStrict();
        parse(inputStream);
        this.saved = true;
    }

    public MimeMessage(MimeMessage mimeMessage) throws MessagingException {
        super(mimeMessage.session);
        ByteArrayOutputStream byteArrayOutputStream;
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.flags = mimeMessage.getFlags();
        int size = mimeMessage.getSize();
        if (size > 0) {
            byteArrayOutputStream = new ByteArrayOutputStream(size);
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
        }
        try {
            this.strict = mimeMessage.strict;
            mimeMessage.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.close();
            SharedByteArrayInputStream sharedByteArrayInputStream = new SharedByteArrayInputStream(byteArrayOutputStream.toByteArray());
            parse(sharedByteArrayInputStream);
            sharedByteArrayInputStream.close();
            this.saved = true;
        } catch (IOException e) {
            throw new MessagingException("IOException while copying message", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MimeMessage(Folder folder, int i) {
        super(folder, i);
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.flags = new Flags();
        this.saved = true;
        initStrict();
    }

    protected MimeMessage(Folder folder, InputStream inputStream, int i) throws MessagingException {
        this(folder, i);
        initStrict();
        parse(inputStream);
    }

    protected MimeMessage(Folder folder, InternetHeaders internetHeaders, byte[] bArr, int i) throws MessagingException {
        this(folder, i);
        this.headers = internetHeaders;
        this.content = bArr;
        initStrict();
    }

    private void initStrict() {
        if (this.session != null) {
            String property = this.session.getProperty("mail.mime.address.strict");
            this.strict = property == null || !property.equalsIgnoreCase("false");
        }
    }

    protected void parse(InputStream inputStream) throws MessagingException {
        if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.headers = createInternetHeaders(inputStream);
        if (inputStream instanceof SharedInputStream) {
            SharedInputStream sharedInputStream = (SharedInputStream) inputStream;
            this.contentStream = sharedInputStream.newStream(sharedInputStream.getPosition(), -1L);
        } else {
            try {
                this.content = ASCIIUtility.getBytes(inputStream);
            } catch (IOException e) {
                throw new MessagingException("IOException", e);
            }
        }
        this.modified = false;
    }

    @Override // javax.mail.Message
    public Address[] getFrom() throws MessagingException {
        Address[] addressHeader = getAddressHeader(Manifest.ATTRIBUTE_FROM);
        return addressHeader == null ? getAddressHeader("Sender") : addressHeader;
    }

    @Override // javax.mail.Message
    public void setFrom(Address address) throws MessagingException {
        if (address == null) {
            removeHeader(Manifest.ATTRIBUTE_FROM);
        } else {
            setHeader(Manifest.ATTRIBUTE_FROM, address.toString());
        }
    }

    @Override // javax.mail.Message
    public void setFrom() throws MessagingException {
        InternetAddress localAddress = InternetAddress.getLocalAddress(this.session);
        if (localAddress != null) {
            setFrom(localAddress);
            return;
        }
        throw new MessagingException("No From address");
    }

    @Override // javax.mail.Message
    public void addFrom(Address[] addressArr) throws MessagingException {
        addAddressHeader(Manifest.ATTRIBUTE_FROM, addressArr);
    }

    public Address getSender() throws MessagingException {
        Address[] addressHeader = getAddressHeader("Sender");
        if (addressHeader == null || addressHeader.length == 0) {
            return null;
        }
        return addressHeader[0];
    }

    public void setSender(Address address) throws MessagingException {
        if (address == null) {
            removeHeader("Sender");
        } else {
            setHeader("Sender", address.toString());
        }
    }

    /* loaded from: classes2.dex */
    public static class RecipientType extends Message.RecipientType {
        public static final RecipientType NEWSGROUPS = new RecipientType("Newsgroups");
        private static final long serialVersionUID = -5468290701714395543L;

        protected RecipientType(String str) {
            super(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // javax.mail.Message.RecipientType
        public Object readResolve() throws ObjectStreamException {
            if (this.type.equals("Newsgroups")) {
                return NEWSGROUPS;
            }
            return super.readResolve();
        }
    }

    @Override // javax.mail.Message
    public Address[] getRecipients(Message.RecipientType recipientType) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            return getAddressHeader(getHeaderName(recipientType));
        }
        String header = getHeader("Newsgroups", ",");
        if (header == null) {
            return null;
        }
        return NewsAddress.parse(header);
    }

    @Override // javax.mail.Message
    public Address[] getAllRecipients() throws MessagingException {
        Address[] allRecipients = super.getAllRecipients();
        Address[] recipients = getRecipients(RecipientType.NEWSGROUPS);
        if (recipients == null) {
            return allRecipients;
        }
        if (allRecipients == null) {
            return recipients;
        }
        Address[] addressArr = new Address[allRecipients.length + recipients.length];
        System.arraycopy(allRecipients, 0, addressArr, 0, allRecipients.length);
        System.arraycopy(recipients, 0, addressArr, allRecipients.length, recipients.length);
        return addressArr;
    }

    @Override // javax.mail.Message
    public void setRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            setAddressHeader(getHeaderName(recipientType), addressArr);
        } else if (addressArr == null || addressArr.length == 0) {
            removeHeader("Newsgroups");
        } else {
            setHeader("Newsgroups", NewsAddress.toString(addressArr));
        }
    }

    public void setRecipients(Message.RecipientType recipientType, String str) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            setAddressHeader(getHeaderName(recipientType), InternetAddress.parse(str));
        } else if (str == null || str.length() == 0) {
            removeHeader("Newsgroups");
        } else {
            setHeader("Newsgroups", str);
        }
    }

    @Override // javax.mail.Message
    public void addRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        if (recipientType == RecipientType.NEWSGROUPS) {
            String newsAddress = NewsAddress.toString(addressArr);
            if (newsAddress != null) {
                addHeader("Newsgroups", newsAddress);
                return;
            }
            return;
        }
        addAddressHeader(getHeaderName(recipientType), addressArr);
    }

    public void addRecipients(Message.RecipientType recipientType, String str) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            addAddressHeader(getHeaderName(recipientType), InternetAddress.parse(str));
        } else if (str != null && str.length() != 0) {
            addHeader("Newsgroups", str);
        }
    }

    @Override // javax.mail.Message
    public Address[] getReplyTo() throws MessagingException {
        Address[] addressHeader = getAddressHeader("Reply-To");
        return addressHeader == null ? getFrom() : addressHeader;
    }

    @Override // javax.mail.Message
    public void setReplyTo(Address[] addressArr) throws MessagingException {
        setAddressHeader("Reply-To", addressArr);
    }

    private Address[] getAddressHeader(String str) throws MessagingException {
        String header = getHeader(str, ",");
        if (header == null) {
            return null;
        }
        return InternetAddress.parseHeader(header, this.strict);
    }

    private void setAddressHeader(String str, Address[] addressArr) throws MessagingException {
        String internetAddress = InternetAddress.toString(addressArr);
        if (internetAddress == null) {
            removeHeader(str);
        } else {
            setHeader(str, internetAddress);
        }
    }

    private void addAddressHeader(String str, Address[] addressArr) throws MessagingException {
        String internetAddress = InternetAddress.toString(addressArr);
        if (internetAddress != null) {
            addHeader(str, internetAddress);
        }
    }

    @Override // javax.mail.Message
    public String getSubject() throws MessagingException {
        String header = getHeader("Subject", null);
        if (header == null) {
            return null;
        }
        try {
            return MimeUtility.decodeText(MimeUtility.unfold(header));
        } catch (UnsupportedEncodingException unused) {
            return header;
        }
    }

    @Override // javax.mail.Message
    public void setSubject(String str) throws MessagingException {
        setSubject(str, null);
    }

    public void setSubject(String str, String str2) throws MessagingException {
        if (str == null) {
            removeHeader("Subject");
            return;
        }
        try {
            setHeader("Subject", MimeUtility.fold(9, MimeUtility.encodeText(str, str2, null)));
        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("Encoding error", e);
        }
    }

    @Override // javax.mail.Message
    public Date getSentDate() throws MessagingException {
        Date parse;
        String header = getHeader("Date", null);
        if (header == null) {
            return null;
        }
        try {
            synchronized (mailDateFormat) {
                parse = mailDateFormat.parse(header);
            }
            return parse;
        } catch (ParseException unused) {
            return null;
        }
    }

    @Override // javax.mail.Message
    public void setSentDate(Date date) throws MessagingException {
        if (date == null) {
            removeHeader("Date");
            return;
        }
        synchronized (mailDateFormat) {
            setHeader("Date", mailDateFormat.format(date));
        }
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
        return MimeBodyPart.isMimeType(this, str);
    }

    @Override // javax.mail.Part
    public String getDisposition() throws MessagingException {
        return MimeBodyPart.getDisposition(this);
    }

    @Override // javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        MimeBodyPart.setDisposition(this, str);
    }

    public String getEncoding() throws MessagingException {
        return MimeBodyPart.getEncoding(this);
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

    @Override // javax.mail.Part
    public String getDescription() throws MessagingException {
        return MimeBodyPart.getDescription(this);
    }

    @Override // javax.mail.Part
    public void setDescription(String str) throws MessagingException {
        setDescription(str, null);
    }

    public void setDescription(String str, String str2) throws MessagingException {
        MimeBodyPart.setDescription(this, str, str2);
    }

    public String[] getContentLanguage() throws MessagingException {
        return MimeBodyPart.getContentLanguage(this);
    }

    public void setContentLanguage(String[] strArr) throws MessagingException {
        MimeBodyPart.setContentLanguage(this, strArr);
    }

    public String getMessageID() throws MessagingException {
        return getHeader("Message-ID", null);
    }

    @Override // javax.mail.Part
    public String getFileName() throws MessagingException {
        return MimeBodyPart.getFileName(this);
    }

    @Override // javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        MimeBodyPart.setFileName(this, str);
    }

    private String getHeaderName(Message.RecipientType recipientType) throws MessagingException {
        if (recipientType == Message.RecipientType.f14650TO) {
            return "To";
        }
        if (recipientType == Message.RecipientType.f14649CC) {
            return "Cc";
        }
        if (recipientType == Message.RecipientType.BCC) {
            return "Bcc";
        }
        if (recipientType == RecipientType.NEWSGROUPS) {
            return "Newsgroups";
        }
        throw new MessagingException("Invalid Recipient Type");
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
            return new SharedByteArrayInputStream(bArr);
        }
        throw new MessagingException("No content");
    }

    public InputStream getRawInputStream() throws MessagingException {
        return getContentStream();
    }

    @Override // javax.mail.Part
    public synchronized DataHandler getDataHandler() throws MessagingException {
        if (this.f14655dh == null) {
            this.f14655dh = new DataHandler(new MimePartDataSource(this));
        }
        return this.f14655dh;
    }

    @Override // javax.mail.Part
    public Object getContent() throws IOException, MessagingException {
        Object obj = this.cachedContent;
        if (obj != null) {
            return obj;
        }
        try {
            Object content = getDataHandler().getContent();
            if (MimeBodyPart.cacheMultipart && (((content instanceof Multipart) || (content instanceof Message)) && !(this.content == null && this.contentStream == null))) {
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
    public synchronized void setDataHandler(DataHandler dataHandler) throws MessagingException {
        this.f14655dh = dataHandler;
        this.cachedContent = null;
        MimeBodyPart.invalidateContentHeaders(this);
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
        MimeBodyPart.setText(this, str, str2, EmailTask.PLAIN);
    }

    @Override // javax.mail.internet.MimePart
    public void setText(String str, String str2, String str3) throws MessagingException {
        MimeBodyPart.setText(this, str, str2, str3);
    }

    @Override // javax.mail.Part
    public void setContent(Multipart multipart) throws MessagingException {
        setDataHandler(new DataHandler(multipart, multipart.getContentType()));
        multipart.setParent(this);
    }

    @Override // javax.mail.Message
    public Message reply(boolean z) throws MessagingException {
        MimeMessage createMimeMessage = createMimeMessage(this.session);
        String header = getHeader("Subject", null);
        if (header != null) {
            if (!header.regionMatches(true, 0, "Re: ", 0, 4)) {
                header = "Re: " + header;
            }
            createMimeMessage.setHeader("Subject", header);
        }
        Address[] replyTo = getReplyTo();
        createMimeMessage.setRecipients(Message.RecipientType.f14650TO, replyTo);
        if (z) {
            Vector vector = new Vector();
            InternetAddress localAddress = InternetAddress.getLocalAddress(this.session);
            if (localAddress != null) {
                vector.addElement(localAddress);
            }
            String property = this.session != null ? this.session.getProperty("mail.alternates") : null;
            boolean z2 = false;
            if (property != null) {
                eliminateDuplicates(vector, InternetAddress.parse(property, false));
            }
            String property2 = this.session != null ? this.session.getProperty("mail.replyallcc") : null;
            if (property2 != null && property2.equalsIgnoreCase("true")) {
                z2 = true;
            }
            eliminateDuplicates(vector, replyTo);
            Address[] eliminateDuplicates = eliminateDuplicates(vector, getRecipients(Message.RecipientType.f14650TO));
            if (eliminateDuplicates != null && eliminateDuplicates.length > 0) {
                if (z2) {
                    createMimeMessage.addRecipients(Message.RecipientType.f14649CC, eliminateDuplicates);
                } else {
                    createMimeMessage.addRecipients(Message.RecipientType.f14650TO, eliminateDuplicates);
                }
            }
            Address[] eliminateDuplicates2 = eliminateDuplicates(vector, getRecipients(Message.RecipientType.f14649CC));
            if (eliminateDuplicates2 != null && eliminateDuplicates2.length > 0) {
                createMimeMessage.addRecipients(Message.RecipientType.f14649CC, eliminateDuplicates2);
            }
            Address[] recipients = getRecipients(RecipientType.NEWSGROUPS);
            if (recipients != null && recipients.length > 0) {
                createMimeMessage.setRecipients(RecipientType.NEWSGROUPS, recipients);
            }
        }
        String header2 = getHeader("Message-Id", null);
        if (header2 != null) {
            createMimeMessage.setHeader("In-Reply-To", header2);
        }
        String header3 = getHeader("References", ExpandableTextView.f6958c);
        if (header3 == null) {
            header3 = getHeader("In-Reply-To", ExpandableTextView.f6958c);
        }
        if (header2 == null) {
            header2 = header3;
        } else if (header3 != null) {
            header2 = String.valueOf(MimeUtility.unfold(header3)) + ExpandableTextView.f6958c + header2;
        }
        if (header2 != null) {
            createMimeMessage.setHeader("References", MimeUtility.fold(12, header2));
        }
        try {
            setFlags(answeredFlag, true);
        } catch (MessagingException unused) {
        }
        return createMimeMessage;
    }

    private Address[] eliminateDuplicates(Vector vector, Address[] addressArr) {
        Address[] addressArr2;
        boolean z;
        if (addressArr == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < addressArr.length; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= vector.size()) {
                    z = false;
                    break;
                } else if (((InternetAddress) vector.elementAt(i3)).equals(addressArr[i2])) {
                    i++;
                    addressArr[i2] = null;
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                vector.addElement(addressArr[i2]);
            }
        }
        if (i == 0) {
            return addressArr;
        }
        if (addressArr instanceof InternetAddress[]) {
            addressArr2 = new InternetAddress[addressArr.length - i];
        } else {
            addressArr2 = new Address[addressArr.length - i];
        }
        int i4 = 0;
        for (int i5 = 0; i5 < addressArr.length; i5++) {
            if (addressArr[i5] != null) {
                i4++;
                addressArr2[i4] = addressArr[i5];
            }
        }
        return addressArr2;
    }

    @Override // javax.mail.Part
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        writeTo(outputStream, null);
    }

    public void writeTo(OutputStream outputStream, String[] strArr) throws IOException, MessagingException {
        if (!this.saved) {
            saveChanges();
        }
        if (this.modified) {
            MimeBodyPart.writeTo(this, outputStream, strArr);
            return;
        }
        Enumeration nonMatchingHeaderLines = getNonMatchingHeaderLines(strArr);
        LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
        while (nonMatchingHeaderLines.hasMoreElements()) {
            lineOutputStream.writeln((String) nonMatchingHeaderLines.nextElement());
        }
        lineOutputStream.writeln();
        byte[] bArr = this.content;
        if (bArr == null) {
            InputStream contentStream = getContentStream();
            byte[] bArr2 = new byte[8192];
            while (true) {
                int read = contentStream.read(bArr2);
                if (read <= 0) {
                    break;
                }
                outputStream.write(bArr2, 0, read);
            }
            contentStream.close();
        } else {
            outputStream.write(bArr);
        }
        outputStream.flush();
    }

    @Override // javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        return this.headers.getHeader(str);
    }

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

    @Override // javax.mail.Message
    public synchronized Flags getFlags() throws MessagingException {
        return (Flags) this.flags.clone();
    }

    @Override // javax.mail.Message
    public synchronized boolean isSet(Flags.Flag flag) throws MessagingException {
        return this.flags.contains(flag);
    }

    @Override // javax.mail.Message
    public synchronized void setFlags(Flags flags, boolean z) throws MessagingException {
        if (z) {
            this.flags.add(flags);
        } else {
            this.flags.remove(flags);
        }
    }

    @Override // javax.mail.Message
    public void saveChanges() throws MessagingException {
        this.modified = true;
        this.saved = true;
        updateHeaders();
    }

    protected void updateMessageID() throws MessagingException {
        setHeader("Message-ID", SimpleComparison.f23612f + UniqueValue.getUniqueMessageIDValue(this.session) + SimpleComparison.f23610d);
    }

    protected void updateHeaders() throws MessagingException {
        MimeBodyPart.updateHeaders(this);
        setHeader("MIME-Version", "1.0");
        updateMessageID();
        Object obj = this.cachedContent;
        if (obj != null) {
            this.f14655dh = new DataHandler(obj, getContentType());
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

    protected InternetHeaders createInternetHeaders(InputStream inputStream) throws MessagingException {
        return new InternetHeaders(inputStream);
    }

    protected MimeMessage createMimeMessage(Session session) throws MessagingException {
        return new MimeMessage(session);
    }
}
