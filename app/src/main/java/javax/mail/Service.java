package javax.mail;

import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;

/* loaded from: classes2.dex */
public abstract class Service {
    protected boolean debug;

    /* renamed from: q */
    private EventQueue f14651q;
    protected Session session;
    protected URLName url;
    private boolean connected = false;
    private Vector connectionListeners = null;
    private Object qLock = new Object();

    public boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Service(Session session, URLName uRLName) {
        this.url = null;
        this.debug = false;
        this.session = session;
        this.url = uRLName;
        this.debug = session.getDebug();
    }

    public void connect() throws MessagingException {
        connect(null, null, null);
    }

    public void connect(String str, String str2, String str3) throws MessagingException {
        connect(str, -1, str2, str3);
    }

    public void connect(String str, String str2) throws MessagingException {
        connect(null, str, str2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:6|(6:8|(1:10)(1:11)|12|(1:14)(1:15)|(2:17|(1:19)(1:20))(1:(1:25)(1:24))|26)(1:27)|(2:(1:30)|(1:32))|(1:34)|(2:36|37)(1:38)|(10:92|40|46|(1:59)(7:50|(1:(1:53)(2:54|(2:56|57)))(1:58)|87|61|62|(5:89|66|67|69|(1:71))|(1:(1:74)(2:75|76))(4:77|(1:79)|80|81))|60|87|61|62|(0)|(0)(0))|45|46|(1:48)|59|60|87|61|62|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0124, code lost:
        r14 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0125, code lost:
        r0 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0159 A[Catch: all -> 0x0188, TryCatch #2 {, blocks: (B:4:0x0005, B:6:0x000b, B:8:0x000f, B:10:0x0017, B:14:0x0025, B:17:0x002f, B:19:0x0037, B:22:0x0043, B:24:0x004f, B:26:0x0058, B:30:0x006c, B:32:0x0087, B:34:0x00a2, B:36:0x00ac, B:40:0x00b9, B:42:0x00c1, B:44:0x00c5, B:48:0x00d2, B:50:0x00d6, B:53:0x00f8, B:54:0x0104, B:56:0x010e, B:61:0x011e, B:66:0x0129, B:69:0x0130, B:71:0x013c, B:74:0x0152, B:75:0x0153, B:76:0x0158, B:77:0x0159, B:79:0x016a, B:80:0x0178, B:83:0x0180, B:84:0x0187), top: B:91:0x0005, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void connect(java.lang.String r19, int r20, java.lang.String r21, java.lang.String r22) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Service.connect(java.lang.String, int, java.lang.String, java.lang.String):void");
    }

    public synchronized boolean isConnected() {
        return this.connected;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setConnected(boolean z) {
        this.connected = z;
    }

    public synchronized void close() throws MessagingException {
        setConnected(false);
        notifyConnectionListeners(3);
    }

    public synchronized URLName getURLName() {
        if (this.url == null || (this.url.getPassword() == null && this.url.getFile() == null)) {
            return this.url;
        }
        return new URLName(this.url.getProtocol(), this.url.getHost(), this.url.getPort(), null, this.url.getUsername(), null);
    }

    protected synchronized void setURLName(URLName uRLName) {
        this.url = uRLName;
    }

    public synchronized void addConnectionListener(ConnectionListener connectionListener) {
        if (this.connectionListeners == null) {
            this.connectionListeners = new Vector();
        }
        this.connectionListeners.addElement(connectionListener);
    }

    public synchronized void removeConnectionListener(ConnectionListener connectionListener) {
        if (this.connectionListeners != null) {
            this.connectionListeners.removeElement(connectionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void notifyConnectionListeners(int i) {
        if (this.connectionListeners != null) {
            queueEvent(new ConnectionEvent(this, i), this.connectionListeners);
        }
        if (i == 3) {
            terminateQueue();
        }
    }

    public String toString() {
        URLName uRLName = getURLName();
        if (uRLName != null) {
            return uRLName.toString();
        }
        return super.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void queueEvent(MailEvent mailEvent, Vector vector) {
        synchronized (this.qLock) {
            if (this.f14651q == null) {
                this.f14651q = new EventQueue();
            }
        }
        this.f14651q.enqueue(mailEvent, (Vector) vector.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TerminatorEvent extends MailEvent {
        private static final long serialVersionUID = 5542172141759168416L;

        TerminatorEvent() {
            super(new Object());
        }

        @Override // javax.mail.event.MailEvent
        public void dispatch(Object obj) {
            Thread.currentThread().interrupt();
        }
    }

    private void terminateQueue() {
        synchronized (this.qLock) {
            if (this.f14651q != null) {
                Vector vector = new Vector();
                vector.setSize(1);
                this.f14651q.enqueue(new TerminatorEvent(), vector);
                this.f14651q = null;
            }
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        terminateQueue();
    }
}
