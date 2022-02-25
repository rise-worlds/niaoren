package org.apache.tools.mail;

import android.support.p006v7.widget.helper.ItemTouchHelper;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.Manifest;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class MailMessage {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 25;
    private static final int OK_DATA = 354;
    private static final int OK_DOT = 250;
    private static final int OK_FROM = 250;
    private static final int OK_HELO = 250;
    private static final int OK_QUIT = 221;
    private static final int OK_RCPT_1 = 250;
    private static final int OK_RCPT_2 = 251;
    private static final int OK_READY = 220;

    /* renamed from: cc */
    private Vector f14785cc;
    private String from;
    private Vector headersKeys;
    private Vector headersValues;
    private String host;

    /* renamed from: in */
    private SmtpResponseReader f14786in;
    private MailPrintStream out;
    private int port;
    private Vector replyto;
    private Socket socket;

    /* renamed from: to */
    private Vector f14787to;

    public MailMessage() throws IOException {
        this(DEFAULT_HOST, 25);
    }

    public MailMessage(String str) throws IOException {
        this(str, 25);
    }

    public MailMessage(String str, int i) throws IOException {
        this.port = 25;
        this.port = i;
        this.host = str;
        this.replyto = new Vector();
        this.f14787to = new Vector();
        this.f14785cc = new Vector();
        this.headersKeys = new Vector();
        this.headersValues = new Vector();
        connect();
        sendHelo();
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void from(String str) throws IOException {
        sendFrom(str);
        this.from = str;
    }

    public void replyto(String str) {
        this.replyto.addElement(str);
    }

    /* renamed from: to */
    public void m14821to(String str) throws IOException {
        sendRcpt(str);
        this.f14787to.addElement(str);
    }

    /* renamed from: cc */
    public void m14822cc(String str) throws IOException {
        sendRcpt(str);
        this.f14785cc.addElement(str);
    }

    public void bcc(String str) throws IOException {
        sendRcpt(str);
    }

    public void setSubject(String str) {
        setHeader("Subject", str);
    }

    public void setHeader(String str, String str2) {
        this.headersKeys.add(str);
        this.headersValues.add(str2);
    }

    public PrintStream getPrintStream() throws IOException {
        setFromHeader();
        setReplyToHeader();
        setToHeader();
        setCcHeader();
        setHeader("X-Mailer", "org.apache.tools.mail.MailMessage (ant.apache.org)");
        sendData();
        flushHeaders();
        return this.out;
    }

    void setFromHeader() {
        setHeader(Manifest.ATTRIBUTE_FROM, this.from);
    }

    void setReplyToHeader() {
        if (!this.replyto.isEmpty()) {
            setHeader("Reply-To", vectorToList(this.replyto));
        }
    }

    void setToHeader() {
        if (!this.f14787to.isEmpty()) {
            setHeader("To", vectorToList(this.f14787to));
        }
    }

    void setCcHeader() {
        if (!this.f14785cc.isEmpty()) {
            setHeader("Cc", vectorToList(this.f14785cc));
        }
    }

    String vectorToList(Vector vector) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            stringBuffer.append(elements.nextElement());
            if (elements.hasMoreElements()) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.toString();
    }

    void flushHeaders() throws IOException {
        int size = this.headersKeys.size();
        for (int i = 0; i < size; i++) {
            MailPrintStream mailPrintStream = this.out;
            mailPrintStream.println(((String) this.headersKeys.elementAt(i)) + ": " + ((String) this.headersValues.elementAt(i)));
        }
        this.out.println();
        this.out.flush();
    }

    public void sendAndClose() throws IOException {
        try {
            sendDot();
            sendQuit();
        } finally {
            disconnect();
        }
    }

    static String sanitizeAddress(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt == '(') {
                i3++;
                if (i2 == 0) {
                    i = i4;
                }
            } else if (charAt == ')') {
                i3--;
                if (i == 0) {
                    i2 = i4 + 1;
                }
            } else if (i3 == 0 && charAt == '<') {
                i2 = i4 + 1;
            } else if (i3 == 0 && charAt == '>') {
                i = i4;
            }
        }
        if (i != 0) {
            length = i;
        }
        return str.substring(i2, length);
    }

    void connect() throws IOException {
        this.socket = new Socket(this.host, this.port);
        this.out = new MailPrintStream(new BufferedOutputStream(this.socket.getOutputStream()));
        this.f14786in = new SmtpResponseReader(this.socket.getInputStream());
        getReady();
    }

    void getReady() throws IOException {
        String response = this.f14786in.getResponse();
        if (!isResponseOK(response, new int[]{220})) {
            throw new IOException("Didn't get introduction from server: " + response);
        }
    }

    void sendHelo() throws IOException {
        String hostName = InetAddress.getLocalHost().getHostName();
        int[] iArr = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
        send("HELO " + hostName, iArr);
    }

    void sendFrom(String str) throws IOException {
        int[] iArr = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
        send("MAIL FROM: <" + sanitizeAddress(str) + SimpleComparison.f23610d, iArr);
    }

    void sendRcpt(String str) throws IOException {
        int[] iArr = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, OK_RCPT_2};
        send("RCPT TO: <" + sanitizeAddress(str) + SimpleComparison.f23610d, iArr);
    }

    void sendData() throws IOException {
        send("DATA", new int[]{OK_DATA});
    }

    void sendDot() throws IOException {
        send("\r\n.", new int[]{ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION});
    }

    void sendQuit() throws IOException {
        try {
            send("QUIT", new int[]{221});
        } catch (IOException e) {
            throw new ErrorInQuitException(e);
        }
    }

    void send(String str, int[] iArr) throws IOException {
        MailPrintStream mailPrintStream = this.out;
        mailPrintStream.rawPrint(str + "\r\n");
        String response = this.f14786in.getResponse();
        if (!isResponseOK(response, iArr)) {
            throw new IOException("Unexpected reply to command: " + str + ": " + response);
        }
    }

    boolean isResponseOK(String str, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            if (str.startsWith("" + iArr[i])) {
                return true;
            }
        }
        return false;
    }

    void disconnect() throws IOException {
        MailPrintStream mailPrintStream = this.out;
        if (mailPrintStream != null) {
            mailPrintStream.close();
        }
        SmtpResponseReader smtpResponseReader = this.f14786in;
        if (smtpResponseReader != null) {
            try {
                smtpResponseReader.close();
            } catch (IOException unused) {
            }
        }
        Socket socket = this.socket;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused2) {
            }
        }
    }
}
