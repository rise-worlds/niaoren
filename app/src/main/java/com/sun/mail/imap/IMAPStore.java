package com.sun.mail.imap;

import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.iap.ResponseHandler;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.Namespaces;
import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Quota;
import javax.mail.QuotaAwareStore;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.URLName;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.ciq;

/* loaded from: classes2.dex */
public class IMAPStore extends Store implements ResponseHandler, QuotaAwareStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int RESPONSE = 1000;
    private int appendBufferSize;
    private String authorizationID;
    private int blksize;
    private volatile boolean connected;
    private int defaultPort;
    private boolean disableAuthLogin;
    private boolean disableAuthPlain;
    private boolean enableImapEvents;
    private boolean enableSASL;
    private boolean enableStartTLS;
    private boolean forcePasswordRefresh;
    private String host;
    private boolean isSSL;
    private int minIdleTime;
    private String name;
    private Namespaces namespaces;
    private PrintStream out;
    private String password;
    private ConnectionPool pool;
    private int port;
    private String proxyAuthUser;
    private String[] saslMechanisms;
    private String saslRealm;
    private int statusCacheTimeout;
    private String user;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ConnectionPool {
        private static final int ABORTING = 2;
        private static final int IDLE = 1;
        private static final int RUNNING = 0;
        private Vector folders;
        private IMAPProtocol idleProtocol;
        private long lastTimePruned;
        private Vector authenticatedConnections = new Vector();
        private boolean separateStoreConnection = false;
        private boolean storeConnectionInUse = false;
        private long clientTimeoutInterval = 45000;
        private long serverTimeoutInterval = 1800000;
        private int poolSize = 1;
        private long pruningInterval = WaitFor.ONE_MINUTE;
        private boolean debug = false;
        private int idleState = 0;

        ConnectionPool() {
        }
    }

    public IMAPStore(Session session, URLName uRLName) {
        this(session, uRLName, "imap", TbsListener.ErrorCode.NEEDDOWNLOAD_4, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPStore(Session session, URLName uRLName, String str, int i, boolean z) {
        super(session, uRLName);
        this.name = "imap";
        this.defaultPort = TbsListener.ErrorCode.NEEDDOWNLOAD_4;
        this.isSSL = false;
        this.port = -1;
        this.blksize = 16384;
        this.statusCacheTimeout = 1000;
        this.appendBufferSize = -1;
        this.minIdleTime = 10;
        this.disableAuthLogin = false;
        this.disableAuthPlain = false;
        this.enableStartTLS = false;
        this.enableSASL = false;
        this.forcePasswordRefresh = false;
        this.enableImapEvents = false;
        this.connected = false;
        this.pool = new ConnectionPool();
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        this.defaultPort = i;
        this.isSSL = z;
        this.pool.lastTimePruned = System.currentTimeMillis();
        this.debug = session.getDebug();
        this.out = session.getDebugOut();
        if (this.out == null) {
            this.out = System.out;
        }
        String property = session.getProperty("mail." + str + ".connectionpool.debug");
        if (property != null && property.equalsIgnoreCase("true")) {
            this.pool.debug = true;
        }
        String property2 = session.getProperty("mail." + str + ".partialfetch");
        if (property2 == null || !property2.equalsIgnoreCase("false")) {
            String property3 = session.getProperty("mail." + str + ".fetchsize");
            if (property3 != null) {
                this.blksize = Integer.parseInt(property3);
            }
            if (this.debug) {
                PrintStream printStream = this.out;
                printStream.println("DEBUG: mail.imap.fetchsize: " + this.blksize);
            }
        } else {
            this.blksize = -1;
            if (this.debug) {
                this.out.println("DEBUG: mail.imap.partialfetch: false");
            }
        }
        String property4 = session.getProperty("mail." + str + ".statuscachetimeout");
        if (property4 != null) {
            this.statusCacheTimeout = Integer.parseInt(property4);
            if (this.debug) {
                PrintStream printStream2 = this.out;
                printStream2.println("DEBUG: mail.imap.statuscachetimeout: " + this.statusCacheTimeout);
            }
        }
        String property5 = session.getProperty("mail." + str + ".appendbuffersize");
        if (property5 != null) {
            this.appendBufferSize = Integer.parseInt(property5);
            if (this.debug) {
                PrintStream printStream3 = this.out;
                printStream3.println("DEBUG: mail.imap.appendbuffersize: " + this.appendBufferSize);
            }
        }
        String property6 = session.getProperty("mail." + str + ".minidletime");
        if (property6 != null) {
            this.minIdleTime = Integer.parseInt(property6);
            if (this.debug) {
                PrintStream printStream4 = this.out;
                printStream4.println("DEBUG: mail.imap.minidletime: " + this.minIdleTime);
            }
        }
        String property7 = session.getProperty("mail." + str + ".connectionpoolsize");
        if (property7 != null) {
            try {
                int parseInt = Integer.parseInt(property7);
                if (parseInt > 0) {
                    this.pool.poolSize = parseInt;
                }
            } catch (NumberFormatException unused) {
            }
            if (this.pool.debug) {
                PrintStream printStream5 = this.out;
                printStream5.println("DEBUG: mail.imap.connectionpoolsize: " + this.pool.poolSize);
            }
        }
        String property8 = session.getProperty("mail." + str + ".connectionpooltimeout");
        if (property8 != null) {
            try {
                int parseInt2 = Integer.parseInt(property8);
                if (parseInt2 > 0) {
                    this.pool.clientTimeoutInterval = parseInt2;
                }
            } catch (NumberFormatException unused2) {
            }
            if (this.pool.debug) {
                PrintStream printStream6 = this.out;
                printStream6.println("DEBUG: mail.imap.connectionpooltimeout: " + this.pool.clientTimeoutInterval);
            }
        }
        String property9 = session.getProperty("mail." + str + ".servertimeout");
        if (property9 != null) {
            try {
                int parseInt3 = Integer.parseInt(property9);
                if (parseInt3 > 0) {
                    this.pool.serverTimeoutInterval = parseInt3;
                }
            } catch (NumberFormatException unused3) {
            }
            if (this.pool.debug) {
                PrintStream printStream7 = this.out;
                printStream7.println("DEBUG: mail.imap.servertimeout: " + this.pool.serverTimeoutInterval);
            }
        }
        String property10 = session.getProperty("mail." + str + ".separatestoreconnection");
        if (property10 != null && property10.equalsIgnoreCase("true")) {
            if (this.pool.debug) {
                this.out.println("DEBUG: dedicate a store connection");
            }
            this.pool.separateStoreConnection = true;
        }
        String property11 = session.getProperty("mail." + str + ".proxyauth.user");
        if (property11 != null) {
            this.proxyAuthUser = property11;
            if (this.debug) {
                PrintStream printStream8 = this.out;
                printStream8.println("DEBUG: mail.imap.proxyauth.user: " + this.proxyAuthUser);
            }
        }
        String property12 = session.getProperty("mail." + str + ".auth.login.disable");
        if (property12 != null && property12.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: disable AUTH=LOGIN");
            }
            this.disableAuthLogin = true;
        }
        String property13 = session.getProperty("mail." + str + ".auth.plain.disable");
        if (property13 != null && property13.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: disable AUTH=PLAIN");
            }
            this.disableAuthPlain = true;
        }
        String property14 = session.getProperty("mail." + str + ".starttls.enable");
        if (property14 != null && property14.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: enable STARTTLS");
            }
            this.enableStartTLS = true;
        }
        String property15 = session.getProperty("mail." + str + ".sasl.enable");
        if (property15 != null && property15.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: enable SASL");
            }
            this.enableSASL = true;
        }
        if (this.enableSASL) {
            String property16 = session.getProperty("mail." + str + ".sasl.mechanisms");
            if (property16 != null && property16.length() > 0) {
                if (this.debug) {
                    PrintStream printStream9 = this.out;
                    printStream9.println("DEBUG: SASL mechanisms allowed: " + property16);
                }
                Vector vector = new Vector(5);
                StringTokenizer stringTokenizer = new StringTokenizer(property16, " ,");
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() > 0) {
                        vector.addElement(nextToken);
                    }
                }
                this.saslMechanisms = new String[vector.size()];
                vector.copyInto(this.saslMechanisms);
            }
        }
        String property17 = session.getProperty("mail." + str + ".sasl.authorizationid");
        if (property17 != null) {
            this.authorizationID = property17;
            if (this.debug) {
                PrintStream printStream10 = this.out;
                printStream10.println("DEBUG: mail.imap.sasl.authorizationid: " + this.authorizationID);
            }
        }
        String property18 = session.getProperty("mail." + str + ".sasl.realm");
        if (property18 != null) {
            this.saslRealm = property18;
            if (this.debug) {
                PrintStream printStream11 = this.out;
                printStream11.println("DEBUG: mail.imap.sasl.realm: " + this.saslRealm);
            }
        }
        String property19 = session.getProperty("mail." + str + ".forcepasswordrefresh");
        if (property19 != null && property19.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: enable forcePasswordRefresh");
            }
            this.forcePasswordRefresh = true;
        }
        String property20 = session.getProperty("mail." + str + ".enableimapevents");
        if (property20 != null && property20.equalsIgnoreCase("true")) {
            if (this.debug) {
                this.out.println("DEBUG: enable IMAP events");
            }
            this.enableImapEvents = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public synchronized boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        CommandFailedException e;
        boolean isEmpty;
        IMAPProtocol iMAPProtocol = null;
        if (str == null || str3 == null || str2 == null) {
            if (this.debug) {
                PrintStream printStream = this.out;
                StringBuilder sb = new StringBuilder("DEBUG: protocolConnect returning false, host=");
                sb.append(str);
                sb.append(", user=");
                sb.append(str2);
                sb.append(", password=");
                sb.append(str3 != null ? "<non-null>" : "<null>");
                printStream.println(sb.toString());
            }
            return false;
        }
        if (i != -1) {
            this.port = i;
        } else {
            Session session = this.session;
            String property = session.getProperty("mail." + this.name + ".port");
            if (property != null) {
                this.port = Integer.parseInt(property);
            }
        }
        if (this.port == -1) {
            this.port = this.defaultPort;
        }
        try {
            try {
                try {
                    synchronized (this.pool) {
                        isEmpty = this.pool.authenticatedConnections.isEmpty();
                    }
                    if (isEmpty) {
                        iMAPProtocol = new IMAPProtocol(this.name, str, this.port, this.session.getDebug(), this.session.getDebugOut(), this.session.getProperties(), this.isSSL);
                        try {
                            if (this.debug) {
                                PrintStream printStream2 = this.out;
                                printStream2.println("DEBUG: protocolConnect login, host=" + str + ", user=" + str2 + ", password=<non-null>");
                            }
                            login(iMAPProtocol, str2, str3);
                            iMAPProtocol.addResponseHandler(this);
                            this.host = str;
                            this.user = str2;
                            this.password = str3;
                            synchronized (this.pool) {
                                this.pool.authenticatedConnections.addElement(iMAPProtocol);
                            }
                        } catch (CommandFailedException e2) {
                            e = e2;
                            if (iMAPProtocol != null) {
                                iMAPProtocol.disconnect();
                            }
                            throw new AuthenticationFailedException(e.getResponse().getRest());
                        }
                    }
                    this.connected = true;
                    return true;
                } catch (IOException e3) {
                    throw new MessagingException(e3.getMessage(), e3);
                }
            } catch (CommandFailedException e4) {
                e = e4;
            }
        } catch (ProtocolException e5) {
            throw new MessagingException(e5.getMessage(), e5);
        }
    }

    private void login(IMAPProtocol iMAPProtocol, String str, String str2) throws ProtocolException {
        if (this.enableStartTLS && iMAPProtocol.hasCapability("STARTTLS")) {
            iMAPProtocol.startTLS();
            iMAPProtocol.capability();
        }
        if (!iMAPProtocol.isAuthenticated()) {
            iMAPProtocol.getCapabilities().put("__PRELOGIN__", "");
            String str3 = this.authorizationID;
            if (str3 == null && (str3 = this.proxyAuthUser) == null) {
                str3 = str;
            }
            if (this.enableSASL) {
                iMAPProtocol.sasllogin(this.saslMechanisms, this.saslRealm, str3, str, str2);
            }
            if (!iMAPProtocol.isAuthenticated()) {
                if (iMAPProtocol.hasCapability("AUTH=PLAIN") && !this.disableAuthPlain) {
                    iMAPProtocol.authplain(str3, str, str2);
                } else if ((iMAPProtocol.hasCapability("AUTH-LOGIN") || iMAPProtocol.hasCapability("AUTH=LOGIN")) && !this.disableAuthLogin) {
                    iMAPProtocol.authlogin(str, str2);
                } else if (!iMAPProtocol.hasCapability("LOGINDISABLED")) {
                    iMAPProtocol.login(str, str2);
                } else {
                    throw new ProtocolException("No login methods supported!");
                }
            }
            String str4 = this.proxyAuthUser;
            if (str4 != null) {
                iMAPProtocol.proxyauth(str4);
            }
            if (iMAPProtocol.hasCapability("__PRELOGIN__")) {
                try {
                    iMAPProtocol.capability();
                } catch (ConnectionException e) {
                    throw e;
                } catch (ProtocolException unused) {
                }
            }
        }
    }

    public synchronized void setUsername(String str) {
        this.user = str;
    }

    public synchronized void setPassword(String str) {
        this.password = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:70|7|(11:16|(1:18)|19|20|(3:68|22|80)(1:79)|26|46|(3:48|(1:50)|51)|52|53|84)|27|(1:29)|64|30|(5:72|32|33|35|(1:37))|38|66|39|(6:45|46|(0)|52|53|84)(3:81|54|55)) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e8, code lost:
        r1 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e9, code lost:
        if (r1 != null) goto L_0x00eb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00eb, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ee, code lost:
        r11 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f7 A[Catch: all -> 0x011d, TryCatch #5 {, blocks: (B:7:0x0008, B:9:0x0014, B:11:0x0021, B:13:0x0029, B:16:0x0032, B:18:0x0036, B:19:0x0053, B:22:0x007b, B:24:0x0085, B:26:0x0089, B:27:0x008d, B:29:0x0091, B:30:0x0098, B:32:0x009c, B:35:0x00a5, B:37:0x00b4, B:38:0x00c0, B:39:0x00e0, B:42:0x00eb, B:46:0x00f2, B:48:0x00f7, B:50:0x00ff, B:51:0x0109, B:52:0x0112, B:54:0x0115, B:55:0x011c), top: B:70:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0115 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.sun.mail.imap.protocol.IMAPProtocol getProtocol(com.sun.mail.imap.IMAPFolder r13) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.getProtocol(com.sun.mail.imap.IMAPFolder):com.sun.mail.imap.protocol.IMAPProtocol");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0057 A[Catch: all -> 0x00c9, TRY_ENTER, TryCatch #2 {, blocks: (B:7:0x0008, B:9:0x0017, B:11:0x001f, B:12:0x0026, B:13:0x0046, B:16:0x0051, B:19:0x0057, B:20:0x0064, B:21:0x006b, B:22:0x006c, B:24:0x0074, B:25:0x0091, B:26:0x009e, B:28:0x00a6, B:30:0x00ad, B:32:0x00bb, B:34:0x00c3, B:35:0x00c6), top: B:47:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0064 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.sun.mail.imap.protocol.IMAPProtocol getStoreProtocol() throws com.sun.mail.iap.ProtocolException {
        /*
            r12 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            if (r1 == 0) goto L_0x0005
            return r1
        L_0x0005:
            com.sun.mail.imap.IMAPStore$ConnectionPool r2 = r12.pool
            monitor-enter(r2)
            r12.waitIfIdle()     // Catch: all -> 0x00c9
            com.sun.mail.imap.IMAPStore$ConnectionPool r3 = r12.pool     // Catch: all -> 0x00c9
            java.util.Vector r3 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$10(r3)     // Catch: all -> 0x00c9
            boolean r3 = r3.isEmpty()     // Catch: all -> 0x00c9
            if (r3 == 0) goto L_0x006c
            com.sun.mail.imap.IMAPStore$ConnectionPool r3 = r12.pool     // Catch: all -> 0x00c9
            boolean r3 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$3(r3)     // Catch: all -> 0x00c9
            if (r3 == 0) goto L_0x0026
            java.io.PrintStream r3 = r12.out     // Catch: all -> 0x00c9
            java.lang.String r4 = "DEBUG: getStoreProtocol() - no connections in the pool, creating a new one"
            r3.println(r4)     // Catch: all -> 0x00c9
        L_0x0026:
            com.sun.mail.imap.protocol.IMAPProtocol r11 = new com.sun.mail.imap.protocol.IMAPProtocol     // Catch: Exception -> 0x004f, all -> 0x00c9
            java.lang.String r4 = r12.name     // Catch: Exception -> 0x004f, all -> 0x00c9
            java.lang.String r5 = r12.host     // Catch: Exception -> 0x004f, all -> 0x00c9
            int r6 = r12.port     // Catch: Exception -> 0x004f, all -> 0x00c9
            javax.mail.Session r3 = r12.session     // Catch: Exception -> 0x004f, all -> 0x00c9
            boolean r7 = r3.getDebug()     // Catch: Exception -> 0x004f, all -> 0x00c9
            javax.mail.Session r3 = r12.session     // Catch: Exception -> 0x004f, all -> 0x00c9
            java.io.PrintStream r8 = r3.getDebugOut()     // Catch: Exception -> 0x004f, all -> 0x00c9
            javax.mail.Session r3 = r12.session     // Catch: Exception -> 0x004f, all -> 0x00c9
            java.util.Properties r9 = r3.getProperties()     // Catch: Exception -> 0x004f, all -> 0x00c9
            boolean r10 = r12.isSSL     // Catch: Exception -> 0x004f, all -> 0x00c9
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)     // Catch: Exception -> 0x004f, all -> 0x00c9
            java.lang.String r1 = r12.user     // Catch: Exception -> 0x004e, all -> 0x00c9
            java.lang.String r3 = r12.password     // Catch: Exception -> 0x004e, all -> 0x00c9
            r12.login(r11, r1, r3)     // Catch: Exception -> 0x004e, all -> 0x00c9
            goto L_0x0055
        L_0x004e:
            r1 = r11
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.logout()     // Catch: Exception -> 0x0054, all -> 0x00c9
        L_0x0054:
            r11 = r0
        L_0x0055:
            if (r11 == 0) goto L_0x0064
            r11.addResponseHandler(r12)     // Catch: all -> 0x00c9
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            java.util.Vector r1 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$10(r1)     // Catch: all -> 0x00c9
            r1.addElement(r11)     // Catch: all -> 0x00c9
            goto L_0x009e
        L_0x0064:
            com.sun.mail.iap.ConnectionException r0 = new com.sun.mail.iap.ConnectionException     // Catch: all -> 0x00c9
            java.lang.String r1 = "failed to create new store connection"
            r0.<init>(r1)     // Catch: all -> 0x00c9
            throw r0     // Catch: all -> 0x00c9
        L_0x006c:
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            boolean r1 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$3(r1)     // Catch: all -> 0x00c9
            if (r1 == 0) goto L_0x0091
            java.io.PrintStream r1 = r12.out     // Catch: all -> 0x00c9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x00c9
            java.lang.String r4 = "DEBUG: getStoreProtocol() - connection available -- size: "
            r3.<init>(r4)     // Catch: all -> 0x00c9
            com.sun.mail.imap.IMAPStore$ConnectionPool r4 = r12.pool     // Catch: all -> 0x00c9
            java.util.Vector r4 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$10(r4)     // Catch: all -> 0x00c9
            int r4 = r4.size()     // Catch: all -> 0x00c9
            r3.append(r4)     // Catch: all -> 0x00c9
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x00c9
            r1.println(r3)     // Catch: all -> 0x00c9
        L_0x0091:
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            java.util.Vector r1 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$10(r1)     // Catch: all -> 0x00c9
            java.lang.Object r1 = r1.firstElement()     // Catch: all -> 0x00c9
            r11 = r1
            com.sun.mail.imap.protocol.IMAPProtocol r11 = (com.sun.mail.imap.protocol.IMAPProtocol) r11     // Catch: all -> 0x00c9
        L_0x009e:
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            boolean r1 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$12(r1)     // Catch: all -> 0x00c9
            if (r1 == 0) goto L_0x00ad
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: InterruptedException -> 0x00ab, all -> 0x00c9
            r1.wait()     // Catch: InterruptedException -> 0x00ab, all -> 0x00c9
        L_0x00ab:
            r1 = r0
            goto L_0x00c3
        L_0x00ad:
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            r3 = 1
            com.sun.mail.imap.IMAPStore.ConnectionPool.access$15(r1, r3)     // Catch: all -> 0x00c9
            com.sun.mail.imap.IMAPStore$ConnectionPool r1 = r12.pool     // Catch: all -> 0x00c9
            boolean r1 = com.sun.mail.imap.IMAPStore.ConnectionPool.access$3(r1)     // Catch: all -> 0x00c9
            if (r1 == 0) goto L_0x00c2
            java.io.PrintStream r1 = r12.out     // Catch: all -> 0x00c9
            java.lang.String r3 = "DEBUG: getStoreProtocol() -- storeConnectionInUse"
            r1.println(r3)     // Catch: all -> 0x00c9
        L_0x00c2:
            r1 = r11
        L_0x00c3:
            r12.timeoutConnections()     // Catch: all -> 0x00c9
            monitor-exit(r2)     // Catch: all -> 0x00c9
            goto L_0x0002
        L_0x00c9:
            r0 = move-exception
            monitor-exit(r2)     // Catch: all -> 0x00c9
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.getStoreProtocol():com.sun.mail.imap.protocol.IMAPProtocol");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allowReadOnlySelect() {
        Session session = this.session;
        String property = session.getProperty("mail." + this.name + ".allowreadonlyselect");
        return property != null && property.equalsIgnoreCase("true");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasSeparateStoreConnection() {
        return this.pool.separateStoreConnection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getConnectionPoolDebug() {
        return this.pool.debug;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnectionPoolFull() {
        boolean z;
        synchronized (this.pool) {
            if (this.pool.debug) {
                PrintStream printStream = this.out;
                printStream.println("DEBUG: current size: " + this.pool.authenticatedConnections.size() + "   pool size: " + this.pool.poolSize);
            }
            z = this.pool.authenticatedConnections.size() >= this.pool.poolSize;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseProtocol(IMAPFolder iMAPFolder, IMAPProtocol iMAPProtocol) {
        synchronized (this.pool) {
            if (iMAPProtocol != null) {
                if (!isConnectionPoolFull()) {
                    iMAPProtocol.addResponseHandler(this);
                    this.pool.authenticatedConnections.addElement(iMAPProtocol);
                    if (this.debug) {
                        PrintStream printStream = this.out;
                        printStream.println("DEBUG: added an Authenticated connection -- size: " + this.pool.authenticatedConnections.size());
                    }
                } else {
                    if (this.debug) {
                        this.out.println("DEBUG: pool is full, not adding an Authenticated connection");
                    }
                    try {
                        iMAPProtocol.logout();
                    } catch (ProtocolException unused) {
                    }
                }
            }
            if (this.pool.folders != null) {
                this.pool.folders.removeElement(iMAPFolder);
            }
            timeoutConnections();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseStoreProtocol(IMAPProtocol iMAPProtocol) {
        if (iMAPProtocol != null) {
            synchronized (this.pool) {
                this.pool.storeConnectionInUse = false;
                this.pool.notifyAll();
                if (this.pool.debug) {
                    this.out.println("DEBUG: releaseStoreProtocol()");
                }
                timeoutConnections();
            }
        }
    }

    private void emptyConnectionPool(boolean z) {
        synchronized (this.pool) {
            for (int size = this.pool.authenticatedConnections.size() - 1; size >= 0; size--) {
                try {
                    IMAPProtocol iMAPProtocol = (IMAPProtocol) this.pool.authenticatedConnections.elementAt(size);
                    iMAPProtocol.removeResponseHandler(this);
                    if (z) {
                        iMAPProtocol.disconnect();
                    } else {
                        iMAPProtocol.logout();
                    }
                } catch (ProtocolException unused) {
                }
            }
            this.pool.authenticatedConnections.removeAllElements();
        }
        if (this.pool.debug) {
            this.out.println("DEBUG: removed all authenticated connections");
        }
    }

    private void timeoutConnections() {
        synchronized (this.pool) {
            if (System.currentTimeMillis() - this.pool.lastTimePruned > this.pool.pruningInterval && this.pool.authenticatedConnections.size() > 1) {
                if (this.pool.debug) {
                    this.out.println("DEBUG: checking for connections to prune: " + (System.currentTimeMillis() - this.pool.lastTimePruned));
                    this.out.println("DEBUG: clientTimeoutInterval: " + this.pool.clientTimeoutInterval);
                }
                for (int size = this.pool.authenticatedConnections.size() - 1; size > 0; size--) {
                    IMAPProtocol iMAPProtocol = (IMAPProtocol) this.pool.authenticatedConnections.elementAt(size);
                    if (this.pool.debug) {
                        this.out.println("DEBUG: protocol last used: " + (System.currentTimeMillis() - iMAPProtocol.getTimestamp()));
                    }
                    if (System.currentTimeMillis() - iMAPProtocol.getTimestamp() > this.pool.clientTimeoutInterval) {
                        if (this.pool.debug) {
                            this.out.println("DEBUG: authenticated connection timed out");
                            this.out.println("DEBUG: logging out the connection");
                        }
                        iMAPProtocol.removeResponseHandler(this);
                        this.pool.authenticatedConnections.removeElementAt(size);
                        try {
                            iMAPProtocol.logout();
                        } catch (ProtocolException unused) {
                        }
                    }
                }
                this.pool.lastTimePruned = System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFetchBlockSize() {
        return this.blksize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session getSession() {
        return this.session;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStatusCacheTimeout() {
        return this.statusCacheTimeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAppendBufferSize() {
        return this.appendBufferSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinIdleTime() {
        return this.minIdleTime;
    }

    public synchronized boolean hasCapability(String str) throws MessagingException {
        boolean hasCapability;
        IMAPProtocol iMAPProtocol = null;
        try {
            iMAPProtocol = getStoreProtocol();
            hasCapability = iMAPProtocol.hasCapability(str);
            releaseStoreProtocol(iMAPProtocol);
        } catch (ProtocolException e) {
            if (iMAPProtocol == null) {
                cleanup();
            }
            throw new MessagingException(e.getMessage(), e);
        }
        return hasCapability;
    }

    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        if (!this.connected) {
            super.setConnected(false);
            return false;
        }
        IMAPProtocol iMAPProtocol = null;
        try {
            iMAPProtocol = getStoreProtocol();
            iMAPProtocol.noop();
        } catch (ProtocolException unused) {
            if (iMAPProtocol == null) {
                cleanup();
            }
        }
        releaseStoreProtocol(iMAPProtocol);
        return super.isConnected();
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        boolean isEmpty;
        if (super.isConnected()) {
            try {
                synchronized (this.pool) {
                    isEmpty = this.pool.authenticatedConnections.isEmpty();
                }
                if (isEmpty) {
                    if (this.pool.debug) {
                        this.out.println("DEBUG: close() - no connections ");
                    }
                    cleanup();
                    releaseStoreProtocol(null);
                    return;
                }
                IMAPProtocol storeProtocol = getStoreProtocol();
                synchronized (this.pool) {
                    this.pool.authenticatedConnections.removeElement(storeProtocol);
                }
                storeProtocol.logout();
                releaseStoreProtocol(storeProtocol);
            } catch (ProtocolException e) {
                cleanup();
                throw new MessagingException(e.getMessage(), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        close();
    }

    private void cleanup() {
        cleanup(false);
    }

    private void cleanup(boolean z) {
        boolean z2;
        if (this.debug) {
            this.out.println("DEBUG: IMAPStore cleanup, force " + z);
        }
        Vector vector = null;
        while (true) {
            synchronized (this.pool) {
                if (this.pool.folders != null) {
                    vector = this.pool.folders;
                    this.pool.folders = null;
                    z2 = false;
                } else {
                    z2 = true;
                }
            }
            if (z2) {
                break;
            }
            int size = vector.size();
            for (int i = 0; i < size; i++) {
                IMAPFolder iMAPFolder = (IMAPFolder) vector.elementAt(i);
                if (z) {
                    try {
                        if (this.debug) {
                            this.out.println("DEBUG: force folder to close");
                        }
                        iMAPFolder.forceClose();
                    } catch (IllegalStateException | MessagingException unused) {
                    }
                } else {
                    if (this.debug) {
                        this.out.println("DEBUG: close folder");
                    }
                    iMAPFolder.close(false);
                }
            }
        }
        synchronized (this.pool) {
            emptyConnectionPool(z);
        }
        this.connected = false;
        notifyConnectionListeners(3);
        if (this.debug) {
            this.out.println("DEBUG: IMAPStore cleanup done");
        }
    }

    @Override // javax.mail.Store
    public synchronized Folder getDefaultFolder() throws MessagingException {
        checkConnected();
        return new DefaultFolder(this);
    }

    @Override // javax.mail.Store
    public synchronized Folder getFolder(String str) throws MessagingException {
        checkConnected();
        return new IMAPFolder(str, ciq.f20716b, this);
    }

    @Override // javax.mail.Store
    public synchronized Folder getFolder(URLName uRLName) throws MessagingException {
        checkConnected();
        return new IMAPFolder(uRLName.getFile(), ciq.f20716b, this);
    }

    @Override // javax.mail.Store
    public Folder[] getPersonalNamespaces() throws MessagingException {
        Namespaces namespaces = getNamespaces();
        if (namespaces == null || namespaces.personal == null) {
            return super.getPersonalNamespaces();
        }
        return namespaceToFolders(namespaces.personal, null);
    }

    @Override // javax.mail.Store
    public Folder[] getUserNamespaces(String str) throws MessagingException {
        Namespaces namespaces = getNamespaces();
        if (namespaces == null || namespaces.otherUsers == null) {
            return super.getUserNamespaces(str);
        }
        return namespaceToFolders(namespaces.otherUsers, str);
    }

    @Override // javax.mail.Store
    public Folder[] getSharedNamespaces() throws MessagingException {
        Namespaces namespaces = getNamespaces();
        if (namespaces == null || namespaces.shared == null) {
            return super.getSharedNamespaces();
        }
        return namespaceToFolders(namespaces.shared, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r0 == null) goto L_0x0018;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized com.sun.mail.imap.protocol.Namespaces getNamespaces() throws javax.mail.MessagingException {
        /*
            r4 = this;
            monitor-enter(r4)
            r4.checkConnected()     // Catch: all -> 0x0047
            r0 = 0
            com.sun.mail.imap.protocol.Namespaces r1 = r4.namespaces     // Catch: all -> 0x0047
            if (r1 != 0) goto L_0x0043
            com.sun.mail.imap.protocol.IMAPProtocol r0 = r4.getStoreProtocol()     // Catch: all -> 0x001c, ProtocolException -> 0x001e, ConnectionException -> 0x0029, BadCommandException -> 0x003d
            com.sun.mail.imap.protocol.Namespaces r1 = r0.namespace()     // Catch: all -> 0x001c, ProtocolException -> 0x001e, ConnectionException -> 0x0029, BadCommandException -> 0x003d
            r4.namespaces = r1     // Catch: all -> 0x001c, ProtocolException -> 0x001e, ConnectionException -> 0x0029, BadCommandException -> 0x003d
            r4.releaseStoreProtocol(r0)     // Catch: all -> 0x0047
            if (r0 != 0) goto L_0x0043
        L_0x0018:
            r4.cleanup()     // Catch: all -> 0x0047
            goto L_0x0043
        L_0x001c:
            r1 = move-exception
            goto L_0x0034
        L_0x001e:
            r1 = move-exception
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch: all -> 0x001c
            java.lang.String r3 = r1.getMessage()     // Catch: all -> 0x001c
            r2.<init>(r3, r1)     // Catch: all -> 0x001c
            throw r2     // Catch: all -> 0x001c
        L_0x0029:
            r1 = move-exception
            javax.mail.StoreClosedException r2 = new javax.mail.StoreClosedException     // Catch: all -> 0x001c
            java.lang.String r1 = r1.getMessage()     // Catch: all -> 0x001c
            r2.<init>(r4, r1)     // Catch: all -> 0x001c
            throw r2     // Catch: all -> 0x001c
        L_0x0034:
            r4.releaseStoreProtocol(r0)     // Catch: all -> 0x0047
            if (r0 != 0) goto L_0x003c
            r4.cleanup()     // Catch: all -> 0x0047
        L_0x003c:
            throw r1     // Catch: all -> 0x0047
        L_0x003d:
            r4.releaseStoreProtocol(r0)     // Catch: all -> 0x0047
            if (r0 != 0) goto L_0x0043
            goto L_0x0018
        L_0x0043:
            com.sun.mail.imap.protocol.Namespaces r0 = r4.namespaces     // Catch: all -> 0x0047
            monitor-exit(r4)
            return r0
        L_0x0047:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.getNamespaces():com.sun.mail.imap.protocol.Namespaces");
    }

    private Folder[] namespaceToFolders(Namespaces.Namespace[] namespaceArr, String str) {
        Folder[] folderArr = new Folder[namespaceArr.length];
        for (int i = 0; i < folderArr.length; i++) {
            String str2 = namespaceArr[i].prefix;
            if (str == null) {
                int length = str2.length();
                if (length > 0) {
                    int i2 = length - 1;
                    if (str2.charAt(i2) == namespaceArr[i].delimiter) {
                        str2 = str2.substring(0, i2);
                    }
                }
            } else {
                str2 = String.valueOf(str2) + str;
            }
            folderArr[i] = new IMAPFolder(str2, namespaceArr[i].delimiter, this, str == null);
        }
        return folderArr;
    }

    @Override // javax.mail.QuotaAwareStore
    public synchronized Quota[] getQuota(String str) throws MessagingException {
        Quota[] quotaRoot;
        checkConnected();
        try {
            IMAPProtocol storeProtocol = getStoreProtocol();
            quotaRoot = storeProtocol.getQuotaRoot(str);
            releaseStoreProtocol(storeProtocol);
            if (storeProtocol == null) {
                cleanup();
            }
        } catch (BadCommandException e) {
            throw new MessagingException("QUOTA not supported", e);
        } catch (ConnectionException e2) {
            throw new StoreClosedException(this, e2.getMessage());
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
        return quotaRoot;
    }

    @Override // javax.mail.QuotaAwareStore
    public synchronized void setQuota(Quota quota) throws MessagingException {
        checkConnected();
        try {
            IMAPProtocol storeProtocol = getStoreProtocol();
            storeProtocol.setQuota(quota);
            releaseStoreProtocol(storeProtocol);
            if (storeProtocol == null) {
                cleanup();
            }
        } catch (BadCommandException e) {
            throw new MessagingException("QUOTA not supported", e);
        } catch (ConnectionException e2) {
            throw new StoreClosedException(this, e2.getMessage());
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
    }

    private void checkConnected() {
        if (!this.connected) {
            super.setConnected(false);
            throw new IllegalStateException("Not connected");
        }
    }

    @Override // com.sun.mail.iap.ResponseHandler
    public void handleResponse(Response response) {
        if (response.isOK() || response.isNO() || response.isBAD() || response.isBYE()) {
            handleResponseCode(response);
        }
        if (response.isBYE()) {
            if (this.debug) {
                this.out.println("DEBUG: IMAPStore connection dead");
            }
            if (this.connected) {
                cleanup(response.isSynthetic());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
        if (r5.enableImapEvents == false) goto L_0x0024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003d, code lost:
        if (r1.isUnTagged() == false) goto L_0x0024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003f, code lost:
        notifyStoreListeners(1000, r1.toString());
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void idle() throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.idle():void");
    }

    private void waitIfIdle() throws ProtocolException {
        while (this.pool.idleState != 0) {
            if (this.pool.idleState == 1) {
                this.pool.idleProtocol.idleAbort();
                this.pool.idleState = 2;
            }
            try {
                this.pool.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleResponseCode(Response response) {
        String rest = response.getRest();
        boolean z = false;
        if (rest.startsWith("[")) {
            int indexOf = rest.indexOf(93);
            if (indexOf > 0 && rest.substring(0, indexOf + 1).equalsIgnoreCase("[ALERT]")) {
                z = true;
            }
            rest = rest.substring(indexOf + 1).trim();
        }
        if (z) {
            notifyStoreListeners(1, rest);
        } else if (response.isUnTagged() && rest.length() > 0) {
            notifyStoreListeners(2, rest);
        }
    }
}
