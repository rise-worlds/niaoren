package com.sun.mail.smtp;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import com.tencent.smtt.sdk.TbsListener;
import com.tendcloud.tenddata.C2771ci;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.ParseException;
import org.apache.tools.mail.MailMessage;
import p110z1.Consts;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class SMTPTransport extends Transport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String UNKNOWN = "UNKNOWN";
    private Address[] addresses;
    private SMTPOutputStream dataStream;
    private int defaultPort;
    private MessagingException exception;
    private Hashtable extMap;
    private Address[] invalidAddr;
    private boolean isSSL;
    private int lastReturnCode;
    private String lastServerResponse;
    private LineInputStream lineInputStream;
    private String localHostName;
    private DigestMD5 md5support;
    private MimeMessage message;
    private String name;
    private PrintStream out;
    private boolean quitWait;
    private boolean reportSuccess;
    private String saslRealm;
    private boolean sendPartiallyFailed;
    private BufferedInputStream serverInput;
    private OutputStream serverOutput;
    private Socket serverSocket;
    private boolean useRset;
    private boolean useStartTLS;
    private Address[] validSentAddr;
    private Address[] validUnsentAddr;
    private static final String[] ignoreList = {"Bcc", "Content-Length"};
    private static final byte[] CRLF = {C2771ci.f13672f, 10};
    private static char[] hexchar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public SMTPTransport(Session session, URLName uRLName) {
        this(session, uRLName, "smtp", 25, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SMTPTransport(Session session, URLName uRLName, String str, int i, boolean z) {
        super(session, uRLName);
        this.name = "smtp";
        this.defaultPort = 25;
        this.isSSL = false;
        this.sendPartiallyFailed = false;
        this.quitWait = false;
        this.saslRealm = UNKNOWN;
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        this.defaultPort = i;
        this.isSSL = z;
        this.out = session.getDebugOut();
        String property = session.getProperty("mail." + str + ".quitwait");
        boolean z2 = true;
        this.quitWait = property == null || property.equalsIgnoreCase("true");
        String property2 = session.getProperty("mail." + str + ".reportsuccess");
        this.reportSuccess = property2 != null && property2.equalsIgnoreCase("true");
        String property3 = session.getProperty("mail." + str + ".starttls.enable");
        this.useStartTLS = property3 != null && property3.equalsIgnoreCase("true");
        String property4 = session.getProperty("mail." + str + ".userset");
        this.useRset = (property4 == null || !property4.equalsIgnoreCase("true")) ? false : z2;
    }

    public synchronized String getLocalHost() {
        try {
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                Session session = this.session;
                this.localHostName = session.getProperty("mail." + this.name + ".localhost");
            }
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                Session session2 = this.session;
                this.localHostName = session2.getProperty("mail." + this.name + ".localaddress");
            }
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                InetAddress localHost = InetAddress.getLocalHost();
                this.localHostName = localHost.getHostName();
                if (this.localHostName == null) {
                    this.localHostName = "[" + localHost.getHostAddress() + "]";
                }
            }
        } catch (UnknownHostException unused) {
        }
        return this.localHostName;
    }

    public synchronized void setLocalHost(String str) {
        this.localHostName = str;
    }

    public synchronized void connect(Socket socket) throws MessagingException {
        this.serverSocket = socket;
        super.connect();
    }

    public synchronized String getSASLRealm() {
        if (this.saslRealm == UNKNOWN) {
            Session session = this.session;
            this.saslRealm = session.getProperty("mail." + this.name + ".sasl.realm");
            if (this.saslRealm == null) {
                Session session2 = this.session;
                this.saslRealm = session2.getProperty("mail." + this.name + ".saslrealm");
            }
        }
        return this.saslRealm;
    }

    public synchronized void setSASLRealm(String str) {
        this.saslRealm = str;
    }

    public synchronized boolean getReportSuccess() {
        return this.reportSuccess;
    }

    public synchronized void setReportSuccess(boolean z) {
        this.reportSuccess = z;
    }

    public synchronized boolean getStartTLS() {
        return this.useStartTLS;
    }

    public synchronized void setStartTLS(boolean z) {
        this.useStartTLS = z;
    }

    public synchronized boolean getUseRset() {
        return this.useRset;
    }

    public synchronized void setUseRset(boolean z) {
        this.useRset = z;
    }

    public synchronized String getLastServerResponse() {
        return this.lastServerResponse;
    }

    public synchronized int getLastReturnCode() {
        return this.lastReturnCode;
    }

    private synchronized DigestMD5 getMD5() {
        if (this.md5support == null) {
            this.md5support = new DigestMD5(this.debug ? this.out : null);
        }
        return this.md5support;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        DigestMD5 md5;
        Session session = this.session;
        String property = session.getProperty("mail." + this.name + ".ehlo");
        boolean z = property == null || !property.equalsIgnoreCase("false");
        Session session2 = this.session;
        String property2 = session2.getProperty("mail." + this.name + ".auth");
        boolean z2 = property2 != null && property2.equalsIgnoreCase("true");
        if (this.debug) {
            PrintStream printStream = this.out;
            printStream.println("DEBUG SMTP: useEhlo " + z + ", useAuth " + z2);
        }
        if (z2 && (str2 == null || str3 == null)) {
            return false;
        }
        if (i == -1) {
            Session session3 = this.session;
            String property3 = session3.getProperty("mail." + this.name + ".port");
            if (property3 != null) {
                i = Integer.parseInt(property3);
            } else {
                i = this.defaultPort;
            }
        }
        String str4 = (str == null || str.length() == 0) ? MailMessage.DEFAULT_HOST : str;
        if (this.serverSocket != null) {
            openServer();
        } else {
            openServer(str4, i);
        }
        if (!(z ? ehlo(getLocalHost()) : false)) {
            helo(getLocalHost());
        }
        if (this.useStartTLS && supportsExtension("STARTTLS")) {
            startTLS();
            ehlo(getLocalHost());
        }
        if ((z2 || !(str2 == null || str3 == null)) && (supportsExtension("AUTH") || supportsExtension("AUTH=LOGIN"))) {
            if (this.debug) {
                this.out.println("DEBUG SMTP: Attempt to authenticate");
                if (!supportsAuthentication("LOGIN") && supportsExtension("AUTH=LOGIN")) {
                    this.out.println("DEBUG SMTP: use AUTH=LOGIN hack");
                }
            }
            if (supportsAuthentication("LOGIN") || supportsExtension("AUTH=LOGIN")) {
                int simpleCommand = simpleCommand("AUTH LOGIN");
                if (simpleCommand == 530) {
                    startTLS();
                    simpleCommand = simpleCommand("AUTH LOGIN");
                }
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
                    if (simpleCommand == 334) {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str2));
                        bASE64EncoderStream.flush();
                        simpleCommand = simpleCommand(byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                    }
                    if (simpleCommand == 334) {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str3));
                        bASE64EncoderStream.flush();
                        simpleCommand = simpleCommand(byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                    }
                    if (simpleCommand != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (IOException unused) {
                    if (simpleCommand != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (Throwable th) {
                    if (simpleCommand != 235) {
                        closeConnection();
                        return false;
                    }
                    throw th;
                }
            } else if (supportsAuthentication("PLAIN")) {
                int simpleCommand2 = simpleCommand("AUTH PLAIN");
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    BASE64EncoderStream bASE64EncoderStream2 = new BASE64EncoderStream(byteArrayOutputStream2, Integer.MAX_VALUE);
                    if (simpleCommand2 == 334) {
                        bASE64EncoderStream2.write(0);
                        bASE64EncoderStream2.write(ASCIIUtility.getBytes(str2));
                        bASE64EncoderStream2.write(0);
                        bASE64EncoderStream2.write(ASCIIUtility.getBytes(str3));
                        bASE64EncoderStream2.flush();
                        simpleCommand2 = simpleCommand(byteArrayOutputStream2.toByteArray());
                    }
                    if (simpleCommand2 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (IOException unused2) {
                    if (simpleCommand2 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (Throwable th2) {
                    if (simpleCommand2 != 235) {
                        closeConnection();
                        return false;
                    }
                    throw th2;
                }
            } else if (supportsAuthentication("DIGEST-MD5") && (md5 = getMD5()) != null) {
                int simpleCommand3 = simpleCommand("AUTH DIGEST-MD5");
                if (simpleCommand3 == 334) {
                    try {
                        try {
                            simpleCommand3 = simpleCommand(md5.authClient(str4, str2, str3, getSASLRealm(), this.lastServerResponse));
                            if (simpleCommand3 == 334) {
                                simpleCommand3 = !md5.authServer(this.lastServerResponse) ? -1 : simpleCommand(new byte[0]);
                            }
                        } catch (Exception e) {
                            if (this.debug) {
                                PrintStream printStream2 = this.out;
                                printStream2.println("DEBUG SMTP: DIGEST-MD5: " + e);
                            }
                            if (simpleCommand3 != 235) {
                                closeConnection();
                                return false;
                            }
                        }
                    } catch (Throwable th3) {
                        if (simpleCommand3 != 235) {
                            closeConnection();
                            return false;
                        }
                        throw th3;
                    }
                }
                if (simpleCommand3 != 235) {
                    closeConnection();
                    return false;
                }
            }
        }
        return true;
    }

    @Override // javax.mail.Transport
    public synchronized void sendMessage(Message message, Address[] addressArr) throws MessagingException, SendFailedException {
        checkConnected();
        if (!(message instanceof MimeMessage)) {
            if (this.debug) {
                this.out.println("DEBUG SMTP: Can only send RFC822 msgs");
            }
            throw new MessagingException("SMTP can only send RFC822 messages");
        }
        for (int i = 0; i < addressArr.length; i++) {
            if (!(addressArr[i] instanceof InternetAddress)) {
                throw new MessagingException(addressArr[i] + " is not an InternetAddress");
            }
        }
        this.message = (MimeMessage) message;
        this.addresses = addressArr;
        this.validUnsentAddr = addressArr;
        expandGroups();
        boolean allow8bitMIME = message instanceof SMTPMessage ? ((SMTPMessage) message).getAllow8bitMIME() : false;
        if (!allow8bitMIME) {
            Session session = this.session;
            String property = session.getProperty("mail." + this.name + ".allow8bitmime");
            allow8bitMIME = property != null && property.equalsIgnoreCase("true");
        }
        if (this.debug) {
            PrintStream printStream = this.out;
            printStream.println("DEBUG SMTP: use8bit " + allow8bitMIME);
        }
        if (allow8bitMIME && supportsExtension("8BITMIME") && convertTo8Bit(this.message)) {
            try {
                this.message.saveChanges();
            } catch (MessagingException unused) {
            }
        }
        try {
            mailFrom();
            rcptTo();
            this.message.writeTo(data(), ignoreList);
            finishData();
            if (this.sendPartiallyFailed) {
                if (this.debug) {
                    this.out.println("DEBUG SMTP: Sending partially failed because of invalid destination addresses");
                }
                notifyTransportListeners(3, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                throw new SMTPSendFailedException(Consts.f23430h, this.lastReturnCode, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
            }
            notifyTransportListeners(1, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
            this.invalidAddr = null;
            this.validUnsentAddr = null;
            this.validSentAddr = null;
            this.addresses = null;
            this.message = null;
            this.exception = null;
            this.sendPartiallyFailed = false;
        } catch (IOException e) {
            if (this.debug) {
                e.printStackTrace(this.out);
            }
            try {
                closeConnection();
            } catch (MessagingException unused2) {
            }
            notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
            throw new MessagingException("IOException while sending message", e);
        } catch (MessagingException e2) {
            if (this.debug) {
                e2.printStackTrace(this.out);
            }
            notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
            throw e2;
        }
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        int readServerResponse;
        if (super.isConnected()) {
            if (this.serverSocket != null) {
                sendCommand("QUIT");
                if (!(!this.quitWait || (readServerResponse = readServerResponse()) == 221 || readServerResponse == -1)) {
                    PrintStream printStream = this.out;
                    printStream.println("DEBUG SMTP: QUIT failed with " + readServerResponse);
                }
            }
            closeConnection();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.OutputStream, java.net.Socket, java.io.BufferedInputStream, com.sun.mail.util.LineInputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void closeConnection() throws javax.mail.MessagingException {
        /*
            r4 = this;
            r0 = 0
            java.net.Socket r1 = r4.serverSocket     // Catch: all -> 0x001c, IOException -> 0x001e
            if (r1 == 0) goto L_0x000a
            java.net.Socket r1 = r4.serverSocket     // Catch: all -> 0x001c, IOException -> 0x001e
            r1.close()     // Catch: all -> 0x001c, IOException -> 0x001e
        L_0x000a:
            r4.serverSocket = r0
            r4.serverOutput = r0
            r4.serverInput = r0
            r4.lineInputStream = r0
            boolean r0 = super.isConnected()
            if (r0 == 0) goto L_0x001b
            super.close()
        L_0x001b:
            return
        L_0x001c:
            r1 = move-exception
            goto L_0x0027
        L_0x001e:
            r1 = move-exception
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch: all -> 0x001c
            java.lang.String r3 = "Server Close Failed"
            r2.<init>(r3, r1)     // Catch: all -> 0x001c
            throw r2     // Catch: all -> 0x001c
        L_0x0027:
            r4.serverSocket = r0
            r4.serverOutput = r0
            r4.serverInput = r0
            r4.lineInputStream = r0
            boolean r0 = super.isConnected()
            if (r0 == 0) goto L_0x0038
            super.close()
        L_0x0038:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.closeConnection():void");
    }

    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        if (!super.isConnected()) {
            return false;
        }
        try {
            try {
                if (this.useRset) {
                    sendCommand("RSET");
                } else {
                    sendCommand("NOOP");
                }
                int readServerResponse = readServerResponse();
                if (readServerResponse >= 0 && readServerResponse != 421) {
                    return true;
                }
                try {
                    closeConnection();
                } catch (MessagingException unused) {
                }
                return false;
            } catch (Exception unused2) {
                closeConnection();
                return false;
            }
        } catch (MessagingException unused3) {
            return false;
        }
    }

    private void expandGroups() {
        Vector vector = null;
        int i = 0;
        while (true) {
            Address[] addressArr = this.addresses;
            if (i >= addressArr.length) {
                break;
            }
            InternetAddress internetAddress = (InternetAddress) addressArr[i];
            if (internetAddress.isGroup()) {
                if (vector == null) {
                    Vector vector2 = new Vector();
                    for (int i2 = 0; i2 < i; i2++) {
                        vector2.addElement(this.addresses[i2]);
                    }
                    vector = vector2;
                }
                try {
                    InternetAddress[] group = internetAddress.getGroup(true);
                    if (group != null) {
                        for (InternetAddress internetAddress2 : group) {
                            vector.addElement(internetAddress2);
                        }
                    } else {
                        vector.addElement(internetAddress);
                    }
                } catch (ParseException unused) {
                    vector.addElement(internetAddress);
                }
            } else if (vector != null) {
                vector.addElement(internetAddress);
            }
            i++;
        }
        if (vector != null) {
            InternetAddress[] internetAddressArr = new InternetAddress[vector.size()];
            vector.copyInto(internetAddressArr);
            this.addresses = internetAddressArr;
        }
    }

    private boolean convertTo8Bit(MimePart mimePart) {
        boolean z = false;
        z = false;
        z = false;
        z = false;
        z = false;
        try {
            if (mimePart.isMimeType("text/*")) {
                String encoding = mimePart.getEncoding();
                if (encoding != null && ((encoding.equalsIgnoreCase("quoted-printable") || encoding.equalsIgnoreCase("base64")) && is8Bit(mimePart.getInputStream()))) {
                    mimePart.setContent(mimePart.getContent(), mimePart.getContentType());
                    mimePart.setHeader(MIME.CONTENT_TRANSFER_ENC, "8bit");
                    z = true;
                }
            } else if (mimePart.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) mimePart.getContent();
                int count = mimeMultipart.getCount();
                boolean z2 = false;
                for (int i = 0; i < count; i++) {
                    try {
                        if (convertTo8Bit((MimePart) mimeMultipart.getBodyPart(i))) {
                            z2 = true;
                        }
                    } catch (IOException unused) {
                        z = z2;
                    } catch (MessagingException unused2) {
                        z = z2;
                    }
                }
                z = z2;
            }
        } catch (IOException | MessagingException unused3) {
        }
        return z;
    }

    private boolean is8Bit(InputStream inputStream) {
        boolean z = false;
        int i = 0;
        while (true) {
            try {
                int read = inputStream.read();
                if (read < 0) {
                    if (this.debug && z) {
                        this.out.println("DEBUG SMTP: found an 8bit part");
                    }
                    return z;
                }
                int i2 = read & 255;
                if (i2 == 13 || i2 == 10) {
                    i = 0;
                } else if (i2 == 0 || (i = i + 1) > 998) {
                    return false;
                }
                if (i2 > 127) {
                    z = true;
                }
            } catch (IOException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        try {
            closeConnection();
        } catch (MessagingException unused) {
        }
    }

    protected void helo(String str) throws MessagingException {
        if (str != null) {
            issueCommand("HELO " + str, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            return;
        }
        issueCommand("HELO", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    protected boolean ehlo(String str) throws MessagingException {
        String str2;
        if (str != null) {
            str2 = "EHLO " + str;
        } else {
            str2 = "EHLO";
        }
        sendCommand(str2);
        int readServerResponse = readServerResponse();
        if (readServerResponse == 250) {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(this.lastServerResponse));
            this.extMap = new Hashtable();
            boolean z = true;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (z) {
                        z = false;
                    } else if (readLine.length() >= 5) {
                        String substring = readLine.substring(4);
                        int indexOf = substring.indexOf(32);
                        String str3 = "";
                        if (indexOf > 0) {
                            str3 = substring.substring(indexOf + 1);
                            substring = substring.substring(0, indexOf);
                        }
                        if (this.debug) {
                            this.out.println("DEBUG SMTP: Found extension \"" + substring + "\", arg \"" + str3 + "\"");
                        }
                        this.extMap.put(substring.toUpperCase(Locale.ENGLISH), str3);
                    }
                } catch (IOException unused) {
                }
            }
        }
        return readServerResponse == 250;
    }

    protected void mailFrom() throws MessagingException {
        InternetAddress internetAddress;
        Address[] from;
        MimeMessage mimeMessage = this.message;
        String str = null;
        String envelopeFrom = mimeMessage instanceof SMTPMessage ? ((SMTPMessage) mimeMessage).getEnvelopeFrom() : null;
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            envelopeFrom = this.session.getProperty("mail." + this.name + ".from");
        }
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            MimeMessage mimeMessage2 = this.message;
            if (mimeMessage2 == null || (from = mimeMessage2.getFrom()) == null || from.length <= 0) {
                internetAddress = InternetAddress.getLocalAddress(this.session);
            } else {
                internetAddress = from[0];
            }
            if (internetAddress != null) {
                envelopeFrom = internetAddress.getAddress();
            } else {
                throw new MessagingException("can't determine local email address");
            }
        }
        String str2 = "MAIL FROM:" + normalizeAddress(envelopeFrom);
        if (supportsExtension("DSN")) {
            MimeMessage mimeMessage3 = this.message;
            String dSNRet = mimeMessage3 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage3).getDSNRet() : null;
            if (dSNRet == null) {
                dSNRet = this.session.getProperty("mail." + this.name + ".dsn.ret");
            }
            if (dSNRet != null) {
                str2 = String.valueOf(str2) + " RET=" + dSNRet;
            }
        }
        if (supportsExtension("AUTH")) {
            MimeMessage mimeMessage4 = this.message;
            String submitter = mimeMessage4 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage4).getSubmitter() : null;
            if (submitter == null) {
                submitter = this.session.getProperty("mail." + this.name + ".submitter");
            }
            if (submitter != null) {
                try {
                    str2 = String.valueOf(str2) + " AUTH=" + xtext(submitter);
                } catch (IllegalArgumentException e) {
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: ignoring invalid submitter: " + submitter + ", Exception: " + e);
                    }
                }
            }
        }
        MimeMessage mimeMessage5 = this.message;
        if (mimeMessage5 instanceof SMTPMessage) {
            str = ((SMTPMessage) mimeMessage5).getMailExtension();
        }
        if (str == null) {
            str = this.session.getProperty("mail." + this.name + ".mailextension");
        }
        if (str != null && str.length() > 0) {
            str2 = String.valueOf(str2) + ExpandableTextView.f6958c + str;
        }
        issueSendCommand(str2, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    protected void rcptTo() throws MessagingException {
        String str;
        boolean z;
        InternetAddress internetAddress;
        String str2;
        int readServerResponse;
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        this.invalidAddr = null;
        this.validUnsentAddr = null;
        this.validSentAddr = null;
        MimeMessage mimeMessage = this.message;
        boolean sendPartial = mimeMessage instanceof SMTPMessage ? ((SMTPMessage) mimeMessage).getSendPartial() : false;
        boolean z2 = true;
        if (!sendPartial) {
            String property = this.session.getProperty("mail." + this.name + ".sendpartial");
            sendPartial = property != null && property.equalsIgnoreCase("true");
        }
        if (this.debug && sendPartial) {
            this.out.println("DEBUG SMTP: sendPartial set");
        }
        if (supportsExtension("DSN")) {
            MimeMessage mimeMessage2 = this.message;
            String dSNNotify = mimeMessage2 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage2).getDSNNotify() : null;
            if (dSNNotify == null) {
                dSNNotify = this.session.getProperty("mail." + this.name + ".dsn.notify");
            }
            if (dSNNotify != null) {
                str = dSNNotify;
                z = true;
            } else {
                str = dSNNotify;
                z = false;
            }
        } else {
            str = null;
            z = false;
        }
        SMTPAddressFailedException sMTPAddressFailedException = null;
        int i = 0;
        boolean z3 = false;
        while (true) {
            Address[] addressArr = this.addresses;
            if (i >= addressArr.length) {
                if (sendPartial && vector.size() == 0) {
                    z3 = true;
                }
                if (z3) {
                    this.invalidAddr = new Address[vector3.size()];
                    vector3.copyInto(this.invalidAddr);
                    this.validUnsentAddr = new Address[vector.size() + vector2.size()];
                    int i2 = 0;
                    for (int i3 = 0; i3 < vector.size(); i3++) {
                        i2++;
                        this.validUnsentAddr[i2] = (Address) vector.elementAt(i3);
                    }
                    for (int i4 = 0; i4 < vector2.size(); i4++) {
                        i2++;
                        this.validUnsentAddr[i2] = (Address) vector2.elementAt(i4);
                    }
                } else if (this.reportSuccess || (sendPartial && (vector3.size() > 0 || vector2.size() > 0))) {
                    this.sendPartiallyFailed = z2;
                    this.exception = sMTPAddressFailedException;
                    this.invalidAddr = new Address[vector3.size()];
                    vector3.copyInto(this.invalidAddr);
                    this.validUnsentAddr = new Address[vector2.size()];
                    vector2.copyInto(this.validUnsentAddr);
                    this.validSentAddr = new Address[vector.size()];
                    vector.copyInto(this.validSentAddr);
                } else {
                    this.validSentAddr = this.addresses;
                }
                if (this.debug) {
                    Address[] addressArr2 = this.validSentAddr;
                    if (addressArr2 != null && addressArr2.length > 0) {
                        this.out.println("DEBUG SMTP: Verified Addresses");
                        for (int i5 = 0; i5 < this.validSentAddr.length; i5++) {
                            this.out.println("DEBUG SMTP:   " + this.validSentAddr[i5]);
                        }
                    }
                    Address[] addressArr3 = this.validUnsentAddr;
                    if (addressArr3 != null && addressArr3.length > 0) {
                        this.out.println("DEBUG SMTP: Valid Unsent Addresses");
                        for (int i6 = 0; i6 < this.validUnsentAddr.length; i6++) {
                            this.out.println("DEBUG SMTP:   " + this.validUnsentAddr[i6]);
                        }
                    }
                    Address[] addressArr4 = this.invalidAddr;
                    if (addressArr4 != null && addressArr4.length > 0) {
                        this.out.println("DEBUG SMTP: Invalid Addresses");
                        for (int i7 = 0; i7 < this.invalidAddr.length; i7++) {
                            this.out.println("DEBUG SMTP:   " + this.invalidAddr[i7]);
                        }
                    }
                }
                if (z3) {
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: Sending failed because of invalid destination addresses");
                    }
                    notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                    String str3 = this.lastServerResponse;
                    int i8 = this.lastReturnCode;
                    try {
                        try {
                            try {
                                if (this.serverSocket != null) {
                                    issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                                }
                            } catch (MessagingException unused) {
                                close();
                            }
                        } catch (MessagingException e) {
                            if (this.debug) {
                                e.printStackTrace(this.out);
                            }
                        }
                        throw new SendFailedException("Invalid Addresses", sMTPAddressFailedException, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
                    } finally {
                        this.lastServerResponse = str3;
                        this.lastReturnCode = i8;
                    }
                } else {
                    return;
                }
            } else {
                internetAddress = (InternetAddress) addressArr[i];
                str2 = "RCPT TO:" + normalizeAddress(internetAddress.getAddress());
                if (z) {
                    str2 = String.valueOf(str2) + " NOTIFY=" + str;
                }
                sendCommand(str2);
                readServerResponse = readServerResponse();
                if (!(readServerResponse == 501 || readServerResponse == 503)) {
                    switch (readServerResponse) {
                        case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                        case 251:
                            vector.addElement(internetAddress);
                            if (this.reportSuccess) {
                                SMTPAddressSucceededException sMTPAddressSucceededException = new SMTPAddressSucceededException(internetAddress, str2, readServerResponse, this.lastServerResponse);
                                if (sMTPAddressFailedException == null) {
                                    sMTPAddressFailedException = sMTPAddressSucceededException;
                                } else {
                                    sMTPAddressFailedException.setNextException(sMTPAddressSucceededException);
                                }
                            } else {
                                continue;
                            }
                            i++;
                            z2 = true;
                        default:
                            switch (readServerResponse) {
                                default:
                                    switch (readServerResponse) {
                                        case 550:
                                        case 551:
                                        case 553:
                                            break;
                                        case 552:
                                            break;
                                        default:
                                            if (readServerResponse >= 400 && readServerResponse <= 499) {
                                                vector2.addElement(internetAddress);
                                            } else if (readServerResponse >= 500 && readServerResponse <= 599) {
                                                vector3.addElement(internetAddress);
                                            }
                                            if (!sendPartial) {
                                                z3 = true;
                                            }
                                            SMTPAddressFailedException sMTPAddressFailedException2 = new SMTPAddressFailedException(internetAddress, str2, readServerResponse, this.lastServerResponse);
                                            if (sMTPAddressFailedException != null) {
                                                sMTPAddressFailedException.setNextException(sMTPAddressFailedException2);
                                                break;
                                            } else {
                                                sMTPAddressFailedException = sMTPAddressFailedException2;
                                                continue;
                                            }
                                            break;
                                    }
                                case 450:
                                case 451:
                                case 452:
                                    if (!sendPartial) {
                                        z3 = true;
                                    }
                                    vector2.addElement(internetAddress);
                                    SMTPAddressFailedException sMTPAddressFailedException3 = new SMTPAddressFailedException(internetAddress, str2, readServerResponse, this.lastServerResponse);
                                    if (sMTPAddressFailedException == null) {
                                        sMTPAddressFailedException = sMTPAddressFailedException3;
                                        break;
                                    } else {
                                        sMTPAddressFailedException.setNextException(sMTPAddressFailedException3);
                                        break;
                                    }
                            }
                            i++;
                            z2 = true;
                            break;
                    }
                }
                if (!sendPartial) {
                    z3 = true;
                }
                vector3.addElement(internetAddress);
                SMTPAddressFailedException sMTPAddressFailedException4 = new SMTPAddressFailedException(internetAddress, str2, readServerResponse, this.lastServerResponse);
                if (sMTPAddressFailedException == null) {
                    sMTPAddressFailedException = sMTPAddressFailedException4;
                } else {
                    sMTPAddressFailedException.setNextException(sMTPAddressFailedException4);
                }
                i++;
                z2 = true;
            }
        }
        if (this.debug) {
            this.out.println("DEBUG SMTP: got response code " + readServerResponse + ", with response: " + this.lastServerResponse);
        }
        String str4 = this.lastServerResponse;
        int i9 = this.lastReturnCode;
        if (this.serverSocket != null) {
            issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
        this.lastServerResponse = str4;
        this.lastReturnCode = i9;
        throw new SMTPAddressFailedException(internetAddress, str2, readServerResponse, str4);
    }

    protected OutputStream data() throws MessagingException {
        issueSendCommand("DATA", 354);
        this.dataStream = new SMTPOutputStream(this.serverOutput);
        return this.dataStream;
    }

    protected void finishData() throws IOException, MessagingException {
        this.dataStream.ensureAtBOL();
        issueSendCommand(Consts.f23430h, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    protected void startTLS() throws MessagingException {
        issueCommand("STARTTLS", TbsListener.ErrorCode.COPY_INSTALL_SUCCESS);
        try {
            Socket socket = this.serverSocket;
            Properties properties = this.session.getProperties();
            this.serverSocket = SocketFetcher.startTLS(socket, properties, "mail." + this.name);
            initStreams();
        } catch (IOException e) {
            closeConnection();
            throw new MessagingException("Could not convert socket to TLS", e);
        }
    }

    private void openServer(String str, int i) throws MessagingException {
        if (this.debug) {
            PrintStream printStream = this.out;
            printStream.println("DEBUG SMTP: trying to connect to host \"" + str + "\", port " + i + ", isSSL " + this.isSSL);
        }
        try {
            Properties properties = this.session.getProperties();
            this.serverSocket = SocketFetcher.getSocket(str, i, properties, "mail." + this.name, this.isSSL);
            int port = this.serverSocket.getPort();
            initStreams();
            int readServerResponse = readServerResponse();
            if (readServerResponse != 220) {
                this.serverSocket.close();
                this.serverSocket = null;
                this.serverOutput = null;
                this.serverInput = null;
                this.lineInputStream = null;
                if (this.debug) {
                    PrintStream printStream2 = this.out;
                    printStream2.println("DEBUG SMTP: could not connect to host \"" + str + "\", port: " + port + ", response: " + readServerResponse + "\n");
                }
                throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + port + ", response: " + readServerResponse);
            } else if (this.debug) {
                PrintStream printStream3 = this.out;
                printStream3.println("DEBUG SMTP: connected to host \"" + str + "\", port: " + port + "\n");
            }
        } catch (UnknownHostException e) {
            throw new MessagingException("Unknown SMTP host: " + str, e);
        } catch (IOException e2) {
            throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + i, e2);
        }
    }

    private void openServer() throws MessagingException {
        try {
            int port = this.serverSocket.getPort();
            String hostName = this.serverSocket.getInetAddress().getHostName();
            if (this.debug) {
                PrintStream printStream = this.out;
                printStream.println("DEBUG SMTP: starting protocol to host \"" + hostName + "\", port " + port);
            }
            initStreams();
            int readServerResponse = readServerResponse();
            if (readServerResponse != 220) {
                this.serverSocket.close();
                this.serverSocket = null;
                this.serverOutput = null;
                this.serverInput = null;
                this.lineInputStream = null;
                if (this.debug) {
                    PrintStream printStream2 = this.out;
                    printStream2.println("DEBUG SMTP: got bad greeting from host \"" + hostName + "\", port: " + port + ", response: " + readServerResponse + "\n");
                }
                throw new MessagingException("Got bad greeting from SMTP host: " + hostName + ", port: " + port + ", response: " + readServerResponse);
            } else if (this.debug) {
                PrintStream printStream3 = this.out;
                printStream3.println("DEBUG SMTP: protocol started to host \"" + hostName + "\", port: " + port + "\n");
            }
        } catch (IOException e) {
            throw new MessagingException("Could not start protocol to SMTP host: " + UNKNOWN + ", port: -1", e);
        }
    }

    private void initStreams() throws IOException {
        Properties properties = this.session.getProperties();
        PrintStream debugOut = this.session.getDebugOut();
        boolean debug = this.session.getDebug();
        String property = properties.getProperty("mail.debug.quote");
        boolean z = property != null && property.equalsIgnoreCase("true");
        TraceInputStream traceInputStream = new TraceInputStream(this.serverSocket.getInputStream(), debugOut);
        traceInputStream.setTrace(debug);
        traceInputStream.setQuote(z);
        TraceOutputStream traceOutputStream = new TraceOutputStream(this.serverSocket.getOutputStream(), debugOut);
        traceOutputStream.setTrace(debug);
        traceOutputStream.setQuote(z);
        this.serverOutput = new BufferedOutputStream(traceOutputStream);
        this.serverInput = new BufferedInputStream(traceInputStream);
        this.lineInputStream = new LineInputStream(this.serverInput);
    }

    public synchronized void issueCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        if (readServerResponse() != i) {
            throw new MessagingException(this.lastServerResponse);
        }
    }

    private void issueSendCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        int readServerResponse = readServerResponse();
        if (readServerResponse != i) {
            Address[] addressArr = this.validSentAddr;
            int length = addressArr == null ? 0 : addressArr.length;
            Address[] addressArr2 = this.validUnsentAddr;
            int length2 = addressArr2 == null ? 0 : addressArr2.length;
            Address[] addressArr3 = new Address[length + length2];
            if (length > 0) {
                System.arraycopy(this.validSentAddr, 0, addressArr3, 0, length);
            }
            if (length2 > 0) {
                System.arraycopy(this.validUnsentAddr, 0, addressArr3, length, length2);
            }
            this.validSentAddr = null;
            this.validUnsentAddr = addressArr3;
            if (this.debug) {
                PrintStream printStream = this.out;
                printStream.println("DEBUG SMTP: got response code " + readServerResponse + ", with response: " + this.lastServerResponse);
            }
            String str2 = this.lastServerResponse;
            int i2 = this.lastReturnCode;
            if (this.serverSocket != null) {
                issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
            this.lastServerResponse = str2;
            this.lastReturnCode = i2;
            throw new SMTPSendFailedException(str, readServerResponse, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
        }
    }

    public synchronized int simpleCommand(String str) throws MessagingException {
        sendCommand(str);
        return readServerResponse();
    }

    protected int simpleCommand(byte[] bArr) throws MessagingException {
        sendCommand(bArr);
        return readServerResponse();
    }

    protected void sendCommand(String str) throws MessagingException {
        sendCommand(ASCIIUtility.getBytes(str));
    }

    private void sendCommand(byte[] bArr) throws MessagingException {
        try {
            this.serverOutput.write(bArr);
            this.serverOutput.write(CRLF);
            this.serverOutput.flush();
        } catch (IOException e) {
            throw new MessagingException("Can't send command to SMTP host", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int readServerResponse() throws javax.mail.MessagingException {
        /*
            r6 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 100
            r0.<init>(r1)
        L_0x0007:
            r1 = 0
            com.sun.mail.util.LineInputStream r2 = r6.lineInputStream     // Catch: IOException -> 0x009c
            java.lang.String r2 = r2.readLine()     // Catch: IOException -> 0x009c
            r3 = -1
            if (r2 != 0) goto L_0x0039
            java.lang.String r0 = r0.toString()     // Catch: IOException -> 0x009c
            int r2 = r0.length()     // Catch: IOException -> 0x009c
            if (r2 != 0) goto L_0x001d
            java.lang.String r0 = "[EOF]"
        L_0x001d:
            r6.lastServerResponse = r0     // Catch: IOException -> 0x009c
            r6.lastReturnCode = r3     // Catch: IOException -> 0x009c
            boolean r2 = r6.debug     // Catch: IOException -> 0x009c
            if (r2 == 0) goto L_0x0038
            java.io.PrintStream r2 = r6.out     // Catch: IOException -> 0x009c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: IOException -> 0x009c
            java.lang.String r5 = "DEBUG SMTP: EOF: "
            r4.<init>(r5)     // Catch: IOException -> 0x009c
            r4.append(r0)     // Catch: IOException -> 0x009c
            java.lang.String r0 = r4.toString()     // Catch: IOException -> 0x009c
            r2.println(r0)     // Catch: IOException -> 0x009c
        L_0x0038:
            return r3
        L_0x0039:
            r0.append(r2)     // Catch: IOException -> 0x009c
            java.lang.String r4 = "\n"
            r0.append(r4)     // Catch: IOException -> 0x009c
            boolean r2 = r6.isNotLastLine(r2)     // Catch: IOException -> 0x009c
            if (r2 != 0) goto L_0x0007
            java.lang.String r0 = r0.toString()     // Catch: IOException -> 0x009c
            if (r0 == 0) goto L_0x007d
            int r2 = r0.length()
            r4 = 3
            if (r2 < r4) goto L_0x007d
            java.lang.String r1 = r0.substring(r1, r4)     // Catch: StringIndexOutOfBoundsException -> 0x005d, NumberFormatException -> 0x006d
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: StringIndexOutOfBoundsException -> 0x005d, NumberFormatException -> 0x006d
            goto L_0x007e
        L_0x005d:
            r6.close()     // Catch: MessagingException -> 0x0061
            goto L_0x006b
        L_0x0061:
            r1 = move-exception
            boolean r2 = r6.debug
            if (r2 == 0) goto L_0x006b
            java.io.PrintStream r2 = r6.out
            r1.printStackTrace(r2)
        L_0x006b:
            r1 = -1
            goto L_0x007e
        L_0x006d:
            r6.close()     // Catch: MessagingException -> 0x0071
            goto L_0x007b
        L_0x0071:
            r1 = move-exception
            boolean r2 = r6.debug
            if (r2 == 0) goto L_0x007b
            java.io.PrintStream r2 = r6.out
            r1.printStackTrace(r2)
        L_0x007b:
            r1 = -1
            goto L_0x007e
        L_0x007d:
            r1 = -1
        L_0x007e:
            if (r1 != r3) goto L_0x0097
            boolean r2 = r6.debug
            if (r2 == 0) goto L_0x0097
            java.io.PrintStream r2 = r6.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "DEBUG SMTP: bad server response: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
        L_0x0097:
            r6.lastServerResponse = r0
            r6.lastReturnCode = r1
            return r1
        L_0x009c:
            r0 = move-exception
            boolean r2 = r6.debug
            if (r2 == 0) goto L_0x00b4
            java.io.PrintStream r2 = r6.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "DEBUG SMTP: exception reading response: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
        L_0x00b4:
            java.lang.String r2 = ""
            r6.lastServerResponse = r2
            r6.lastReturnCode = r1
            javax.mail.MessagingException r1 = new javax.mail.MessagingException
            java.lang.String r2 = "Exception reading response"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.readServerResponse():int");
    }

    protected void checkConnected() {
        if (!super.isConnected()) {
            throw new IllegalStateException("Not connected");
        }
    }

    private boolean isNotLastLine(String str) {
        return str != null && str.length() >= 4 && str.charAt(3) == '-';
    }

    private String normalizeAddress(String str) {
        if (str.startsWith(SimpleComparison.f23612f) || str.endsWith(SimpleComparison.f23610d)) {
            return str;
        }
        return SimpleComparison.f23612f + str + SimpleComparison.f23610d;
    }

    public boolean supportsExtension(String str) {
        Hashtable hashtable = this.extMap;
        return (hashtable == null || hashtable.get(str.toUpperCase(Locale.ENGLISH)) == null) ? false : true;
    }

    public String getExtensionParameter(String str) {
        Hashtable hashtable = this.extMap;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str.toUpperCase(Locale.ENGLISH));
    }

    protected boolean supportsAuthentication(String str) {
        String str2;
        Hashtable hashtable = this.extMap;
        if (hashtable == null || (str2 = (String) hashtable.get("AUTH")) == null) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str2);
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    protected static String xtext(String str) {
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 128) {
                if (charAt < '!' || charAt > '~' || charAt == '+' || charAt == '=') {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer(str.length() + 4);
                        stringBuffer.append(str.substring(0, i));
                    }
                    stringBuffer.append('+');
                    stringBuffer.append(hexchar[(charAt & 240) >> 4]);
                    stringBuffer.append(hexchar[charAt & 15]);
                } else if (stringBuffer != null) {
                    stringBuffer.append(charAt);
                }
            } else {
                throw new IllegalArgumentException("Non-ASCII character in SMTP submitter: " + str);
            }
        }
        return stringBuffer != null ? stringBuffer.toString() : str;
    }
}
