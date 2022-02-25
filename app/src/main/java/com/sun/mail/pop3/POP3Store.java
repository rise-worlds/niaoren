package com.sun.mail.pop3;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/* loaded from: classes2.dex */
public class POP3Store extends Store {
    private int defaultPort;
    boolean disableTop;
    boolean forgetTopHeaders;
    private String host;
    private boolean isSSL;
    Constructor messageConstructor;
    private String name;
    private String passwd;
    private Protocol port;
    private int portNum;
    private POP3Folder portOwner;
    boolean rsetBeforeQuit;
    private String user;

    public POP3Store(Session session, URLName uRLName) {
        this(session, uRLName, "pop3", 110, false);
    }

    public POP3Store(Session session, URLName uRLName, String str, int i, boolean z) {
        super(session, uRLName);
        Class<?> cls;
        this.name = "pop3";
        this.defaultPort = 110;
        this.isSSL = false;
        this.port = null;
        this.portOwner = null;
        this.host = null;
        this.portNum = -1;
        this.user = null;
        this.passwd = null;
        this.rsetBeforeQuit = false;
        this.disableTop = false;
        this.forgetTopHeaders = false;
        this.messageConstructor = null;
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        this.defaultPort = i;
        this.isSSL = z;
        String property = session.getProperty("mail." + str + ".rsetbeforequit");
        if (property != null && property.equalsIgnoreCase("true")) {
            this.rsetBeforeQuit = true;
        }
        String property2 = session.getProperty("mail." + str + ".disabletop");
        if (property2 != null && property2.equalsIgnoreCase("true")) {
            this.disableTop = true;
        }
        String property3 = session.getProperty("mail." + str + ".forgettopheaders");
        if (property3 != null && property3.equalsIgnoreCase("true")) {
            this.forgetTopHeaders = true;
        }
        String property4 = session.getProperty("mail." + str + ".message.class");
        if (property4 != null) {
            if (session.getDebug()) {
                PrintStream debugOut = session.getDebugOut();
                debugOut.println("DEBUG: POP3 message class: " + property4);
            }
            try {
                try {
                    cls = getClass().getClassLoader().loadClass(property4);
                } catch (ClassNotFoundException unused) {
                    cls = Class.forName(property4);
                }
                this.messageConstructor = cls.getConstructor(Folder.class, Integer.TYPE);
            } catch (Exception e) {
                if (session.getDebug()) {
                    PrintStream debugOut2 = session.getDebugOut();
                    debugOut2.println("DEBUG: failed to load POP3 message class: " + e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public synchronized boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        if (str == null || str3 == null || str2 == null) {
            return false;
        }
        if (i == -1) {
            try {
                Session session = this.session;
                String property = session.getProperty("mail." + this.name + ".port");
                if (property != null) {
                    i = Integer.parseInt(property);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (i == -1) {
            i = this.defaultPort;
        }
        this.host = str;
        this.portNum = i;
        this.user = str2;
        this.passwd = str3;
        try {
            this.port = getPort(null);
            return true;
        } catch (EOFException e) {
            throw new AuthenticationFailedException(e.getMessage());
        } catch (IOException e2) {
            throw new MessagingException("Connect failed", e2);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: AttachTryCatchVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Null type added to not empty exception handler: MessagingException -> 0x0025
        	at jadx.core.dex.trycatch.ExceptionHandler.addCatchType(ExceptionHandler.java:54)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.createHandler(AttachTryCatchVisitor.java:136)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:123)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:59)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        /*
            r2 = this;
            monitor-enter(r2)
            super.isConnected()     // Catch: all -> 0x002a
            r0 = move-result     // Catch: all -> 0x002a
            r1 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r2)
            return r1
            monitor-enter(r2)     // Catch: all -> 0x002a
            com.sun.mail.pop3.Protocol r0 = r2.port     // Catch: all -> 0x0020, IOException -> 0x0022
            if (r0 != 0) goto L_0x0017     // Catch: all -> 0x0020, IOException -> 0x0022
            r0 = 0     // Catch: all -> 0x0020, IOException -> 0x0022
            r2.getPort(r0)     // Catch: all -> 0x0020, IOException -> 0x0022
            r0 = move-result     // Catch: all -> 0x0020, IOException -> 0x0022
            r2.port = r0     // Catch: all -> 0x0020, IOException -> 0x0022
            goto L_0x001c     // Catch: all -> 0x0020, IOException -> 0x0022
            com.sun.mail.pop3.Protocol r0 = r2.port     // Catch: all -> 0x0020, IOException -> 0x0022
            r0.noop()     // Catch: all -> 0x0020, IOException -> 0x0022
            monitor-exit(r2)     // Catch: all -> 0x0020
            r0 = 1
            monitor-exit(r2)
            return r0
        L_0x0020:
            r0 = move-exception
            goto L_0x0028
        L_0x0022:
            super.close()
        L_0x0025:
            monitor-exit(r2)
            monitor-exit(r2)
            return r1
            monitor-exit(r2)
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.POP3Store.isConnected():boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: AttachTryCatchVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Null type added to not empty exception handler: IOException -> 0x005c
        	at jadx.core.dex.trycatch.ExceptionHandler.addCatchType(ExceptionHandler.java:54)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.createHandler(AttachTryCatchVisitor.java:136)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:123)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:59)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    synchronized com.sun.mail.pop3.Protocol getPort(com.sun.mail.pop3.POP3Folder r10) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.sun.mail.pop3.Protocol r0 = r9.port     // Catch: all -> 0x0062
            if (r0 == 0) goto L_0x000f     // Catch: all -> 0x0062
            com.sun.mail.pop3.POP3Folder r0 = r9.portOwner     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x000f     // Catch: all -> 0x0062
            r9.portOwner = r10     // Catch: all -> 0x0062
            com.sun.mail.pop3.Protocol r10 = r9.port     // Catch: all -> 0x0062
            monitor-exit(r9)
            return r10
            com.sun.mail.pop3.Protocol r8 = new com.sun.mail.pop3.Protocol     // Catch: all -> 0x0062
            java.lang.String r1 = r9.host     // Catch: all -> 0x0062
            int r2 = r9.portNum     // Catch: all -> 0x0062
            javax.mail.Session r0 = r9.session     // Catch: all -> 0x0062
            r0.getDebug()     // Catch: all -> 0x0062
            r3 = move-result     // Catch: all -> 0x0062
            javax.mail.Session r0 = r9.session     // Catch: all -> 0x0062
            r0.getDebugOut()     // Catch: all -> 0x0062
            r4 = move-result     // Catch: all -> 0x0062
            javax.mail.Session r0 = r9.session     // Catch: all -> 0x0062
            r0.getProperties()     // Catch: all -> 0x0062
            r5 = move-result     // Catch: all -> 0x0062
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x0062
            java.lang.String r6 = "mail."     // Catch: all -> 0x0062
            r0.<init>(r6)     // Catch: all -> 0x0062
            java.lang.String r6 = r9.name     // Catch: all -> 0x0062
            r0.append(r6)     // Catch: all -> 0x0062
            r0.toString()     // Catch: all -> 0x0062
            r6 = move-result     // Catch: all -> 0x0062
            boolean r7 = r9.isSSL     // Catch: all -> 0x0062
            r0 = r8     // Catch: all -> 0x0062
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)     // Catch: all -> 0x0062
            java.lang.String r0 = r9.user     // Catch: all -> 0x0062
            java.lang.String r1 = r9.passwd     // Catch: all -> 0x0062
            r8.login(r0, r1)     // Catch: all -> 0x0062
            r0 = move-result     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x0059     // Catch: all -> 0x0062
            com.sun.mail.pop3.Protocol r0 = r9.port     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x0051     // Catch: all -> 0x0062
            if (r10 == 0) goto L_0x0051     // Catch: all -> 0x0062
            r9.port = r8     // Catch: all -> 0x0062
            r9.portOwner = r10     // Catch: all -> 0x0062
            com.sun.mail.pop3.POP3Folder r0 = r9.portOwner     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x0057     // Catch: all -> 0x0062
            r9.portOwner = r10     // Catch: all -> 0x0062
            monitor-exit(r9)
            return r8
            r8.quit()
        L_0x005c:
            java.io.EOFException r10 = new java.io.EOFException
            r10.<init>(r0)
            throw r10
        L_0x0062:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.POP3Store.getPort(com.sun.mail.pop3.POP3Folder):com.sun.mail.pop3.Protocol");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void closePort(POP3Folder pOP3Folder) {
        if (this.portOwner == pOP3Folder) {
            this.port = null;
            this.portOwner = null;
        }
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        try {
            if (this.port != null) {
                this.port.quit();
            }
            this.port = null;
            super.close();
        } catch (IOException unused) {
            this.port = null;
            super.close();
        }
    }

    @Override // javax.mail.Store
    public Folder getDefaultFolder() throws MessagingException {
        checkConnected();
        return new DefaultFolder(this);
    }

    @Override // javax.mail.Store
    public Folder getFolder(String str) throws MessagingException {
        checkConnected();
        return new POP3Folder(this, str);
    }

    @Override // javax.mail.Store
    public Folder getFolder(URLName uRLName) throws MessagingException {
        checkConnected();
        return new POP3Folder(this, uRLName.getFile());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        if (this.port != null) {
            close();
        }
    }

    private void checkConnected() throws MessagingException {
        if (!super.isConnected()) {
            throw new MessagingException("Not connected");
        }
    }
}
