package com.sun.mail.imap.protocol;

import com.sun.mail.iap.Argument;
import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.Literal;
import com.sun.mail.iap.LiteralException;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.ACL;
import com.sun.mail.imap.AppendUID;
import com.sun.mail.imap.Rights;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.tendcloud.tenddata.C2771ci;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Flags;
import javax.mail.Quota;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchException;
import javax.mail.search.SearchTerm;
import org.slf4j.Marker;
import p110z1.Consts;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class IMAPProtocol extends Protocol {
    private static final byte[] CRLF = {C2771ci.f13672f, 10};
    private static final byte[] DONE = {68, 79, 78, 69, C2771ci.f13672f, 10};
    private boolean authenticated;

    /* renamed from: ba */
    private ByteArray f12659ba;
    private boolean connected;
    private String idleTag;
    private String name;
    private boolean rev1;
    private SaslAuthenticator saslAuthenticator;
    private String[] searchCharsets;
    private Map capabilities = null;
    private List authmechs = null;

    public IMAPProtocol(String str, String str2, int i, boolean z, PrintStream printStream, Properties properties, boolean z2) throws IOException, ProtocolException {
        super(str2, i, z, printStream, properties, "mail." + str, z2);
        this.connected = false;
        this.rev1 = false;
        try {
            this.name = str;
            if (this.capabilities == null) {
                capability();
            }
            if (hasCapability("IMAP4rev1")) {
                this.rev1 = true;
            }
            this.searchCharsets = new String[2];
            this.searchCharsets[0] = "UTF-8";
            this.searchCharsets[1] = MimeUtility.mimeCharset(MimeUtility.getDefaultJavaCharset());
            this.connected = true;
        } finally {
            if (!this.connected) {
                disconnect();
            }
        }
    }

    public void capability() throws ProtocolException {
        Response[] command = command("CAPABILITY", null);
        if (command[command.length - 1].isOK()) {
            this.capabilities = new HashMap(10);
            this.authmechs = new ArrayList(5);
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals("CAPABILITY")) {
                        parseCapabilities(iMAPResponse);
                    }
                }
            }
            return;
        }
        throw new ProtocolException(command[command.length - 1].toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCapabilities(Response response) {
        byte readByte;
        do {
            readByte = response.readByte();
            if (readByte <= 0) {
                break;
            }
        } while (readByte != 91);
        if (readByte != 0 && response.readAtom().equalsIgnoreCase("CAPABILITY")) {
            this.capabilities = new HashMap(10);
            this.authmechs = new ArrayList(5);
            parseCapabilities(response);
        }
    }

    protected void parseCapabilities(Response response) {
        while (true) {
            String readAtom = response.readAtom(']');
            if (readAtom != null) {
                if (readAtom.length() != 0) {
                    this.capabilities.put(readAtom.toUpperCase(Locale.ENGLISH), readAtom);
                    if (readAtom.regionMatches(true, 0, "AUTH=", 0, 5)) {
                        this.authmechs.add(readAtom.substring(5));
                        if (this.debug) {
                            PrintStream printStream = this.out;
                            printStream.println("IMAP DEBUG: AUTH: " + readAtom.substring(5));
                        }
                    }
                } else if (response.peekByte() != 93) {
                    response.skipToken();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public void processGreeting(Response response) throws ProtocolException {
        super.processGreeting(response);
        if (response.isOK()) {
            setCapabilities(response);
        } else if (((IMAPResponse) response).keyEquals("PREAUTH")) {
            this.authenticated = true;
            setCapabilities(response);
        } else {
            throw new ConnectionException(this, response);
        }
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isREV1() {
        return this.rev1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public boolean supportsNonSyncLiterals() {
        return hasCapability("LITERAL+");
    }

    @Override // com.sun.mail.iap.Protocol
    public Response readResponse() throws IOException, ProtocolException {
        return IMAPResponse.readResponse(this);
    }

    public boolean hasCapability(String str) {
        return this.capabilities.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    public Map getCapabilities() {
        return this.capabilities;
    }

    @Override // com.sun.mail.iap.Protocol
    public void disconnect() {
        super.disconnect();
        this.authenticated = false;
    }

    public void noop() throws ProtocolException {
        if (this.debug) {
            this.out.println("IMAP DEBUG: IMAPProtocol noop");
        }
        simpleCommand("NOOP", null);
    }

    public void logout() throws ProtocolException {
        Response[] command = command("LOGOUT", null);
        this.authenticated = false;
        notifyResponseHandlers(command);
        disconnect();
    }

    public void login(String str, String str2) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        argument.writeString(str2);
        Response[] command = command("LOGIN", argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        setCapabilities(command[command.length - 1]);
        this.authenticated = true;
    }

    public synchronized void authlogin(String str, String str2) throws ProtocolException {
        String str3;
        boolean z;
        Response response;
        Exception e;
        boolean z2;
        String str4;
        Vector vector = new Vector();
        try {
            str3 = writeCommand("AUTHENTICATE LOGIN", null);
            response = null;
            z = false;
        } catch (Exception e2) {
            response = Response.byeResponse(e2);
            str3 = null;
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        boolean z3 = true;
        while (!z) {
            try {
                response = readResponse();
                if (response.isContinuation()) {
                    if (z3) {
                        str4 = str;
                        z2 = false;
                    } else {
                        z2 = z3;
                        str4 = str2;
                    }
                    try {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str4));
                        bASE64EncoderStream.flush();
                        byteArrayOutputStream.write(CRLF);
                        outputStream.write(byteArrayOutputStream.toByteArray());
                        outputStream.flush();
                        byteArrayOutputStream.reset();
                        z3 = z2;
                    } catch (Exception e3) {
                        e = e3;
                        z3 = z2;
                        response = Response.byeResponse(e);
                        z = true;
                    }
                } else if (response.isTagged() && response.getTag().equals(str3)) {
                    z = true;
                } else if (response.isBYE()) {
                    z = true;
                } else {
                    vector.addElement(response);
                }
            } catch (Exception e4) {
                e = e4;
            }
        }
        Response[] responseArr = new Response[vector.size()];
        vector.copyInto(responseArr);
        notifyResponseHandlers(responseArr);
        handleResult(response);
        setCapabilities(response);
        this.authenticated = true;
    }

    public synchronized void authplain(String str, String str2, String str3) throws ProtocolException {
        Response response;
        Vector vector = new Vector();
        boolean z = false;
        String str4 = null;
        try {
            response = null;
            str4 = writeCommand("AUTHENTICATE PLAIN", null);
        } catch (Exception e) {
            response = Response.byeResponse(e);
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        while (!z) {
            try {
                response = readResponse();
                if (response.isContinuation()) {
                    bASE64EncoderStream.write(ASCIIUtility.getBytes(String.valueOf(str) + "\u0000" + str2 + "\u0000" + str3));
                    bASE64EncoderStream.flush();
                    byteArrayOutputStream.write(CRLF);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.flush();
                    byteArrayOutputStream.reset();
                } else if (response.isTagged() && response.getTag().equals(str4)) {
                    z = true;
                } else if (response.isBYE()) {
                    z = true;
                } else {
                    vector.addElement(response);
                }
            } catch (Exception e2) {
                response = Response.byeResponse(e2);
                z = true;
            }
        }
        Response[] responseArr = new Response[vector.size()];
        vector.copyInto(responseArr);
        notifyResponseHandlers(responseArr);
        handleResult(response);
        setCapabilities(response);
        this.authenticated = true;
    }

    public void sasllogin(String[] strArr, String str, String str2, String str3, String str4) throws ProtocolException {
        List list;
        if (this.saslAuthenticator == null) {
            try {
                Constructor<?> constructor = Class.forName("com.sun.mail.imap.protocol.IMAPSaslAuthenticator").getConstructor(IMAPProtocol.class, String.class, Properties.class, Boolean.TYPE, PrintStream.class, String.class);
                Object[] objArr = new Object[6];
                objArr[0] = this;
                objArr[1] = this.name;
                objArr[2] = this.props;
                objArr[3] = this.debug ? Boolean.TRUE : Boolean.FALSE;
                objArr[4] = this.out;
                objArr[5] = this.host;
                this.saslAuthenticator = (SaslAuthenticator) constructor.newInstance(objArr);
            } catch (Exception e) {
                if (this.debug) {
                    PrintStream printStream = this.out;
                    printStream.println("IMAP DEBUG: Can't load SASL authenticator: " + e);
                    return;
                }
                return;
            }
        }
        if (strArr == null || strArr.length <= 0) {
            list = this.authmechs;
        } else {
            list = new ArrayList(strArr.length);
            for (int i = 0; i < strArr.length; i++) {
                if (this.authmechs.contains(strArr[i])) {
                    list.add(strArr[i]);
                }
            }
        }
        if (this.saslAuthenticator.authenticate((String[]) list.toArray(new String[list.size()]), str, str2, str3, str4)) {
            this.authenticated = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream getIMAPOutputStream() {
        return getOutputStream();
    }

    public void proxyauth(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        simpleCommand("PROXYAUTH", argument);
    }

    public void startTLS() throws ProtocolException {
        try {
            super.startTLS("STARTTLS");
        } catch (ProtocolException e) {
            throw e;
        } catch (Exception e2) {
            notifyResponseHandlers(new Response[]{Response.byeResponse(e2)});
            disconnect();
        }
    }

    public MailboxInfo select(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        Response[] command = command("SELECT", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(command);
        notifyResponseHandlers(command);
        Response response = command[command.length - 1];
        if (response.isOK()) {
            if (response.toString().indexOf("READ-ONLY") != -1) {
                mailboxInfo.mode = 1;
            } else {
                mailboxInfo.mode = 2;
            }
        }
        handleResult(response);
        return mailboxInfo;
    }

    public MailboxInfo examine(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        Response[] command = command("EXAMINE", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(command);
        mailboxInfo.mode = 1;
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        return mailboxInfo;
    }

    public Status status(String str, String[] strArr) throws ProtocolException {
        if (isREV1() || hasCapability("IMAP4SUNVERSION")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Argument argument2 = new Argument();
            if (strArr == null) {
                strArr = Status.standardItems;
            }
            for (String str2 : strArr) {
                argument2.writeAtom(str2);
            }
            argument.writeArgument(argument2);
            Response[] command = command("STATUS", argument);
            Response response = command[command.length - 1];
            Status status = null;
            if (response.isOK()) {
                int length = command.length;
                Status status2 = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("STATUS")) {
                            if (status2 == null) {
                                status2 = new Status(iMAPResponse);
                            } else {
                                Status.add(status2, new Status(iMAPResponse));
                            }
                            command[i] = null;
                        }
                    }
                }
                status = status2;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return status;
        }
        throw new BadCommandException("STATUS not supported");
    }

    public void create(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        simpleCommand("CREATE", argument);
    }

    public void delete(String str) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        simpleCommand("DELETE", argument);
    }

    public void rename(String str, String str2) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str);
        String encode2 = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeString(encode);
        argument.writeString(encode2);
        simpleCommand("RENAME", argument);
    }

    public void subscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("SUBSCRIBE", argument);
    }

    public void unsubscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("UNSUBSCRIBE", argument);
    }

    public ListInfo[] list(String str, String str2) throws ProtocolException {
        return doList("LIST", str, str2);
    }

    public ListInfo[] lsub(String str, String str2) throws ProtocolException {
        return doList("LSUB", str, str2);
    }

    private ListInfo[] doList(String str, String str2, String str3) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str2);
        String encode2 = BASE64MailboxEncoder.encode(str3);
        Argument argument = new Argument();
        argument.writeString(encode);
        argument.writeString(encode2);
        Response[] command = command(str, argument);
        Response response = command[command.length - 1];
        ListInfo[] listInfoArr = null;
        if (response.isOK()) {
            Vector vector = new Vector(1);
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals(str)) {
                        vector.addElement(new ListInfo(iMAPResponse));
                        command[i] = null;
                    }
                }
            }
            if (vector.size() > 0) {
                listInfoArr = new ListInfo[vector.size()];
                vector.copyInto(listInfoArr);
            }
        }
        notifyResponseHandlers(command);
        handleResult(response);
        return listInfoArr;
    }

    public void append(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        appenduid(str, flags, date, literal, false);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        return appenduid(str, flags, date, literal, true);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal, boolean z) throws ProtocolException {
        Flags flags2;
        String encode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(encode);
        if (flags != null) {
            if (flags.contains(Flags.Flag.RECENT)) {
                flags2 = new Flags(flags);
                flags2.remove(Flags.Flag.RECENT);
            } else {
                flags2 = flags;
            }
            argument.writeAtom(createFlagList(flags2));
        }
        if (date != null) {
            argument.writeString(INTERNALDATE.format(date));
        }
        argument.writeBytes(literal);
        Response[] command = command("APPEND", argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
        if (z) {
            return getAppendUID(command[command.length - 1]);
        }
        return null;
    }

    private AppendUID getAppendUID(Response response) {
        byte readByte;
        if (!response.isOK()) {
            return null;
        }
        do {
            readByte = response.readByte();
            if (readByte <= 0) {
                break;
            }
        } while (readByte != 91);
        if (readByte != 0 && response.readAtom().equalsIgnoreCase("APPENDUID")) {
            return new AppendUID(response.readLong(), response.readLong());
        }
        return null;
    }

    public void check() throws ProtocolException {
        simpleCommand("CHECK", null);
    }

    public void close() throws ProtocolException {
        simpleCommand("CLOSE", null);
    }

    public void expunge() throws ProtocolException {
        simpleCommand("EXPUNGE", null);
    }

    public void uidexpunge(UIDSet[] uIDSetArr) throws ProtocolException {
        if (hasCapability("UIDPLUS")) {
            simpleCommand("UID EXPUNGE " + UIDSet.toString(uIDSetArr), null);
            return;
        }
        throw new BadCommandException("UID EXPUNGE not supported");
    }

    public BODYSTRUCTURE fetchBodyStructure(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "BODYSTRUCTURE");
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (BODYSTRUCTURE) FetchResponse.getItem(fetch, i, BODYSTRUCTURE.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public BODY peekBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, true);
    }

    public BODY fetchBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, false);
    }

    protected BODY fetchBody(int i, String str, boolean z) throws ProtocolException {
        Response[] responseArr;
        String str2;
        String str3;
        if (z) {
            StringBuilder sb = new StringBuilder("BODY.PEEK[");
            if (str == null) {
                str3 = "]";
            } else {
                str3 = String.valueOf(str) + "]";
            }
            sb.append(str3);
            responseArr = fetch(i, sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder("BODY[");
            if (str == null) {
                str2 = "]";
            } else {
                str2 = String.valueOf(str) + "]";
            }
            sb2.append(str2);
            responseArr = fetch(i, sb2.toString());
        }
        notifyResponseHandlers(responseArr);
        Response response = responseArr[responseArr.length - 1];
        if (response.isOK()) {
            return (BODY) FetchResponse.getItem(responseArr, i, BODY.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public BODY peekBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, null);
    }

    public BODY fetchBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, null);
    }

    public BODY peekBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, byteArray);
    }

    public BODY fetchBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, byteArray);
    }

    protected BODY fetchBody(int i, String str, int i2, int i3, boolean z, ByteArray byteArray) throws ProtocolException {
        String str2;
        this.f12659ba = byteArray;
        StringBuilder sb = new StringBuilder(String.valueOf(z ? "BODY.PEEK[" : "BODY["));
        if (str == null) {
            str2 = "]<";
        } else {
            str2 = String.valueOf(str) + "]<";
        }
        sb.append(str2);
        sb.append(String.valueOf(i2));
        sb.append(Consts.f23430h);
        sb.append(String.valueOf(i3));
        sb.append(SimpleComparison.f23610d);
        Response[] fetch = fetch(i, sb.toString());
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (BODY) FetchResponse.getItem(fetch, i, BODY.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public ByteArray getResponseBuffer() {
        ByteArray byteArray = this.f12659ba;
        this.f12659ba = null;
        return byteArray;
    }

    public RFC822DATA fetchRFC822(int i, String str) throws ProtocolException {
        String str2;
        if (str == null) {
            str2 = "RFC822";
        } else {
            str2 = "RFC822." + str;
        }
        Response[] fetch = fetch(i, str2);
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (RFC822DATA) FetchResponse.getItem(fetch, i, RFC822DATA.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public Flags fetchFlags(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "FLAGS");
        int length = fetch.length;
        int i2 = 0;
        Flags flags = null;
        while (true) {
            if (i2 < length) {
                if (fetch[i2] != null && (fetch[i2] instanceof FetchResponse) && ((FetchResponse) fetch[i2]).getNumber() == i && (flags = (Flags) ((FetchResponse) fetch[i2]).getItem(Flags.class)) != null) {
                    fetch[i2] = null;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        return flags;
    }

    public UID fetchUID(int i) throws ProtocolException {
        Response[] fetch = fetch(i, "UID");
        notifyResponseHandlers(fetch);
        Response response = fetch[fetch.length - 1];
        if (response.isOK()) {
            return (UID) FetchResponse.getItem(fetch, i, UID.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public UID fetchSequenceNumber(long j) throws ProtocolException {
        Response[] fetch = fetch(String.valueOf(j), "UID", true);
        int length = fetch.length;
        UID uid = null;
        for (int i = 0; i < length; i++) {
            if (!(fetch[i] == null || !(fetch[i] instanceof FetchResponse) || (uid = (UID) ((FetchResponse) fetch[i]).getItem(UID.class)) == null)) {
                if (uid.uid == j) {
                    break;
                }
                uid = null;
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        return uid;
    }

    public UID[] fetchSequenceNumbers(long j, long j2) throws ProtocolException {
        UID uid;
        StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(j)));
        sb.append(":");
        sb.append(j2 == -1 ? Marker.ANY_MARKER : String.valueOf(j2));
        Response[] fetch = fetch(sb.toString(), "UID", true);
        Vector vector = new Vector();
        int length = fetch.length;
        for (int i = 0; i < length; i++) {
            if (!(fetch[i] == null || !(fetch[i] instanceof FetchResponse) || (uid = (UID) ((FetchResponse) fetch[i]).getItem(UID.class)) == null)) {
                vector.addElement(uid);
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        UID[] uidArr = new UID[vector.size()];
        vector.copyInto(uidArr);
        return uidArr;
    }

    public UID[] fetchSequenceNumbers(long[] jArr) throws ProtocolException {
        UID uid;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(String.valueOf(jArr[i]));
        }
        Response[] fetch = fetch(stringBuffer.toString(), "UID", true);
        Vector vector = new Vector();
        int length = fetch.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!(fetch[i2] == null || !(fetch[i2] instanceof FetchResponse) || (uid = (UID) ((FetchResponse) fetch[i2]).getItem(UID.class)) == null)) {
                vector.addElement(uid);
            }
        }
        notifyResponseHandlers(fetch);
        handleResult(fetch[fetch.length - 1]);
        UID[] uidArr = new UID[vector.size()];
        vector.copyInto(uidArr);
        return uidArr;
    }

    public Response[] fetch(MessageSet[] messageSetArr, String str) throws ProtocolException {
        return fetch(MessageSet.toString(messageSetArr), str, false);
    }

    public Response[] fetch(int i, int i2, String str) throws ProtocolException {
        return fetch(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), str, false);
    }

    public Response[] fetch(int i, String str) throws ProtocolException {
        return fetch(String.valueOf(i), str, false);
    }

    private Response[] fetch(String str, String str2, boolean z) throws ProtocolException {
        if (z) {
            return command("UID FETCH " + str + " (" + str2 + ")", null);
        }
        return command("FETCH " + str + " (" + str2 + ")", null);
    }

    public void copy(MessageSet[] messageSetArr, String str) throws ProtocolException {
        copy(MessageSet.toString(messageSetArr), str);
    }

    public void copy(int i, int i2, String str) throws ProtocolException {
        copy(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), str);
    }

    private void copy(String str, String str2) throws ProtocolException {
        String encode = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeAtom(str);
        argument.writeString(encode);
        simpleCommand("COPY", argument);
    }

    public void storeFlags(MessageSet[] messageSetArr, Flags flags, boolean z) throws ProtocolException {
        storeFlags(MessageSet.toString(messageSetArr), flags, z);
    }

    public void storeFlags(int i, int i2, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), flags, z);
    }

    public void storeFlags(int i, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(i), flags, z);
    }

    private void storeFlags(String str, Flags flags, boolean z) throws ProtocolException {
        Response[] responseArr;
        if (z) {
            responseArr = command("STORE " + str + " +FLAGS " + createFlagList(flags), null);
        } else {
            responseArr = command("STORE " + str + " -FLAGS " + createFlagList(flags), null);
        }
        notifyResponseHandlers(responseArr);
        handleResult(responseArr[responseArr.length - 1]);
    }

    private String createFlagList(Flags flags) {
        Flags.Flag[] systemFlags;
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        boolean z = true;
        for (Flags.Flag flag : flags.getSystemFlags()) {
            if (flag == Flags.Flag.ANSWERED) {
                str = "\\Answered";
            } else if (flag == Flags.Flag.DELETED) {
                str = "\\Deleted";
            } else if (flag == Flags.Flag.DRAFT) {
                str = "\\Draft";
            } else if (flag == Flags.Flag.FLAGGED) {
                str = "\\Flagged";
            } else if (flag == Flags.Flag.RECENT) {
                str = "\\Recent";
            } else if (flag == Flags.Flag.SEEN) {
                str = "\\Seen";
            }
            if (z) {
                z = false;
            } else {
                stringBuffer.append(' ');
            }
            stringBuffer.append(str);
        }
        String[] userFlags = flags.getUserFlags();
        for (String str2 : userFlags) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(' ');
            }
            stringBuffer.append(str2);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public int[] search(MessageSet[] messageSetArr, SearchTerm searchTerm) throws ProtocolException, SearchException {
        return search(MessageSet.toString(messageSetArr), searchTerm);
    }

    public int[] search(SearchTerm searchTerm) throws ProtocolException, SearchException {
        return search("ALL", searchTerm);
    }

    private int[] search(String str, SearchTerm searchTerm) throws ProtocolException, SearchException {
        if (SearchSequence.isAscii(searchTerm)) {
            try {
                return issueSearch(str, searchTerm, null);
            } catch (IOException unused) {
            }
        }
        int i = 0;
        while (true) {
            String[] strArr = this.searchCharsets;
            if (i < strArr.length) {
                if (strArr[i] != null) {
                    try {
                        return issueSearch(str, searchTerm, strArr[i]);
                    } catch (CommandFailedException unused2) {
                        this.searchCharsets[i] = null;
                    } catch (ProtocolException e) {
                        throw e;
                    } catch (IOException unused3) {
                        continue;
                    } catch (SearchException e2) {
                        throw e2;
                    }
                }
                i++;
            } else {
                throw new SearchException("Search failed");
            }
        }
    }

    private int[] issueSearch(String str, SearchTerm searchTerm, String str2) throws ProtocolException, SearchException, IOException {
        int[] iArr = null;
        Argument generateSequence = SearchSequence.generateSequence(searchTerm, str2 == null ? null : MimeUtility.javaCharset(str2));
        generateSequence.writeAtom(str);
        Response[] command = str2 == null ? command("SEARCH", generateSequence) : command("SEARCH CHARSET " + str2, generateSequence);
        Response response = command[command.length - 1];
        if (response.isOK()) {
            Vector vector = new Vector();
            int length = command.length;
            for (int i = 0; i < length; i++) {
                if (command[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                    if (iMAPResponse.keyEquals("SEARCH")) {
                        while (true) {
                            int readNumber = iMAPResponse.readNumber();
                            if (readNumber == -1) {
                                break;
                            }
                            vector.addElement(new Integer(readNumber));
                        }
                        command[i] = null;
                    }
                }
            }
            int size = vector.size();
            int[] iArr2 = new int[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr2[i2] = ((Integer) vector.elementAt(i2)).intValue();
            }
            iArr = iArr2;
        }
        notifyResponseHandlers(command);
        handleResult(response);
        return iArr;
    }

    public Namespaces namespace() throws ProtocolException {
        if (hasCapability("NAMESPACE")) {
            Namespaces namespaces = null;
            Response[] command = command("NAMESPACE", null);
            Response response = command[command.length - 1];
            if (response.isOK()) {
                int length = command.length;
                Namespaces namespaces2 = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("NAMESPACE")) {
                            if (namespaces2 == null) {
                                namespaces2 = new Namespaces(iMAPResponse);
                            }
                            command[i] = null;
                        }
                    }
                }
                namespaces = namespaces2;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return namespaces;
        }
        throw new BadCommandException("NAMESPACE not supported");
    }

    public Quota[] getQuotaRoot(String str) throws ProtocolException {
        if (hasCapability("QUOTA")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("GETQUOTAROOT", argument);
            Response response = command[command.length - 1];
            Hashtable hashtable = new Hashtable();
            int i = 0;
            if (response.isOK()) {
                int length = command.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (command[i2] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i2];
                        if (iMAPResponse.keyEquals("QUOTAROOT")) {
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString = iMAPResponse.readAtomString();
                                if (readAtomString == null) {
                                    break;
                                }
                                hashtable.put(readAtomString, new Quota(readAtomString));
                            }
                            command[i2] = null;
                        } else if (iMAPResponse.keyEquals("QUOTA")) {
                            Quota parseQuota = parseQuota(iMAPResponse);
                            hashtable.get(parseQuota.quotaRoot);
                            hashtable.put(parseQuota.quotaRoot, parseQuota);
                            command[i2] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            Quota[] quotaArr = new Quota[hashtable.size()];
            Enumeration elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                quotaArr[i] = (Quota) elements.nextElement();
                i++;
            }
            return quotaArr;
        }
        throw new BadCommandException("GETQUOTAROOT not supported");
    }

    public Quota[] getQuota(String str) throws ProtocolException {
        if (hasCapability("QUOTA")) {
            Argument argument = new Argument();
            argument.writeString(str);
            Response[] command = command("GETQUOTA", argument);
            Vector vector = new Vector();
            Response response = command[command.length - 1];
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("QUOTA")) {
                            vector.addElement(parseQuota(iMAPResponse));
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            Quota[] quotaArr = new Quota[vector.size()];
            vector.copyInto(quotaArr);
            return quotaArr;
        }
        throw new BadCommandException("QUOTA not supported");
    }

    public void setQuota(Quota quota) throws ProtocolException {
        if (hasCapability("QUOTA")) {
            Argument argument = new Argument();
            argument.writeString(quota.quotaRoot);
            Argument argument2 = new Argument();
            if (quota.resources != null) {
                for (int i = 0; i < quota.resources.length; i++) {
                    argument2.writeAtom(quota.resources[i].name);
                    argument2.writeNumber(quota.resources[i].limit);
                }
            }
            argument.writeArgument(argument2);
            Response[] command = command("SETQUOTA", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("QUOTA not supported");
    }

    private Quota parseQuota(Response response) throws ParsingException {
        Quota quota = new Quota(response.readAtomString());
        response.skipSpaces();
        if (response.readByte() == 40) {
            Vector vector = new Vector();
            while (response.peekByte() != 41) {
                String readAtom = response.readAtom();
                if (readAtom != null) {
                    vector.addElement(new Quota.Resource(readAtom, response.readLong(), response.readLong()));
                }
            }
            response.readByte();
            quota.resources = new Quota.Resource[vector.size()];
            vector.copyInto(quota.resources);
            return quota;
        }
        throw new ParsingException("parse error in QUOTA");
    }

    public void setACL(String str, char c, ACL acl) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(acl.getName());
            String rights = acl.getRights().toString();
            if (c == '+' || c == '-') {
                rights = String.valueOf(c) + rights;
            }
            argument.writeString(rights);
            Response[] command = command("SETACL", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("ACL not supported");
    }

    public void deleteACL(String str, String str2) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(str2);
            Response[] command = command("DELETEACL", argument);
            Response response = command[command.length - 1];
            notifyResponseHandlers(command);
            handleResult(response);
            return;
        }
        throw new BadCommandException("ACL not supported");
    }

    public ACL[] getACL(String str) throws ProtocolException {
        String readAtomString;
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("GETACL", argument);
            Response response = command[command.length - 1];
            Vector vector = new Vector();
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("ACL")) {
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString2 = iMAPResponse.readAtomString();
                                if (!(readAtomString2 == null || (readAtomString = iMAPResponse.readAtomString()) == null)) {
                                    vector.addElement(new ACL(readAtomString2, new Rights(readAtomString)));
                                }
                            }
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            ACL[] aclArr = new ACL[vector.size()];
            vector.copyInto(aclArr);
            return aclArr;
        }
        throw new BadCommandException("ACL not supported");
    }

    public Rights[] listRights(String str, String str2) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            argument.writeString(str2);
            Response[] command = command("LISTRIGHTS", argument);
            Response response = command[command.length - 1];
            Vector vector = new Vector();
            if (response.isOK()) {
                int length = command.length;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("LISTRIGHTS")) {
                            iMAPResponse.readAtomString();
                            iMAPResponse.readAtomString();
                            while (true) {
                                String readAtomString = iMAPResponse.readAtomString();
                                if (readAtomString == null) {
                                    break;
                                }
                                vector.addElement(new Rights(readAtomString));
                            }
                            command[i] = null;
                        }
                    }
                }
            }
            notifyResponseHandlers(command);
            handleResult(response);
            Rights[] rightsArr = new Rights[vector.size()];
            vector.copyInto(rightsArr);
            return rightsArr;
        }
        throw new BadCommandException("ACL not supported");
    }

    public Rights myRights(String str) throws ProtocolException {
        if (hasCapability("ACL")) {
            String encode = BASE64MailboxEncoder.encode(str);
            Argument argument = new Argument();
            argument.writeString(encode);
            Response[] command = command("MYRIGHTS", argument);
            Response response = command[command.length - 1];
            Rights rights = null;
            if (response.isOK()) {
                int length = command.length;
                Rights rights2 = null;
                for (int i = 0; i < length; i++) {
                    if (command[i] instanceof IMAPResponse) {
                        IMAPResponse iMAPResponse = (IMAPResponse) command[i];
                        if (iMAPResponse.keyEquals("MYRIGHTS")) {
                            iMAPResponse.readAtomString();
                            String readAtomString = iMAPResponse.readAtomString();
                            if (rights2 == null) {
                                rights2 = new Rights(readAtomString);
                            }
                            command[i] = null;
                        }
                    }
                }
                rights = rights2;
            }
            notifyResponseHandlers(command);
            handleResult(response);
            return rights;
        }
        throw new BadCommandException("ACL not supported");
    }

    public synchronized void idleStart() throws ProtocolException {
        Response response;
        if (hasCapability("IDLE")) {
            try {
                this.idleTag = writeCommand("IDLE", null);
                response = readResponse();
            } catch (LiteralException e) {
                response = e.getResponse();
            } catch (Exception e2) {
                response = Response.byeResponse(e2);
            }
            if (!response.isContinuation()) {
                handleResult(response);
            }
        } else {
            throw new BadCommandException("IDLE not supported");
        }
    }

    public synchronized Response readIdleResponse() {
        Response response;
        if (this.idleTag == null) {
            return null;
        }
        try {
            response = readResponse();
        } catch (ProtocolException e) {
            response = Response.byeResponse(e);
        } catch (IOException e2) {
            response = Response.byeResponse(e2);
        }
        return response;
    }

    public boolean processIdleResponse(Response response) throws ProtocolException {
        notifyResponseHandlers(new Response[]{response});
        boolean isBYE = response.isBYE();
        if (response.isTagged() && response.getTag().equals(this.idleTag)) {
            isBYE = true;
        }
        if (isBYE) {
            this.idleTag = null;
        }
        handleResult(response);
        return !isBYE;
    }

    public void idleAbort() throws ProtocolException {
        OutputStream outputStream = getOutputStream();
        try {
            outputStream.write(DONE);
            outputStream.flush();
        } catch (IOException unused) {
        }
    }
}
