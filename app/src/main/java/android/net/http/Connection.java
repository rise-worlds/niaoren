package android.net.http;

import android.content.Context;
import android.os.SystemClock;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class Connection {
    private static final int DONE = 3;
    private static final int DRAIN = 2;
    private static final String HTTP_CONNECTION = "http.connection";
    private static final int MAX_PIPE = 3;
    private static final int MIN_PIPE = 2;
    private static final int READ = 1;
    private static final int RETRY_REQUEST_LIMIT = 2;
    private static final int SEND = 0;
    static final int SOCKET_TIMEOUT = 60000;
    private byte[] mBuf;
    Context mContext;
    HttpHost mHost;
    RequestFeeder mRequestFeeder;
    private static final String[] states = {"SEND", "READ", "DRAIN", "DONE"};
    private static int STATE_NORMAL = 0;
    private static int STATE_CANCEL_REQUESTED = 1;
    protected AndroidHttpClientConnection mHttpClientConnection = null;
    protected SslCertificate mCertificate = null;
    private int mActive = STATE_NORMAL;
    private boolean mCanPersist = false;
    private HttpContext mHttpContext = new BasicHttpContext(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void closeConnection();

    abstract String getScheme();

    abstract AndroidHttpClientConnection openConnection(Request request) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Connection(Context context, HttpHost host, RequestFeeder requestFeeder) {
        this.mContext = context;
        this.mHost = host;
        this.mRequestFeeder = requestFeeder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpHost getHost() {
        return this.mHost;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Connection getConnection(Context context, HttpHost host, HttpHost proxy, RequestFeeder requestFeeder) {
        if (host.getSchemeName().equals(HttpHost.DEFAULT_SCHEME_NAME)) {
            return new HttpConnection(context, host, requestFeeder);
        }
        return new HttpsConnection(context, host, proxy, requestFeeder);
    }

    SslCertificate getCertificate() {
        return this.mCertificate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.mActive = STATE_CANCEL_REQUESTED;
        closeConnection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
        if (r10 == false) goto L_0x0049;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
        r8 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void processRequests(android.net.http.Request r14) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.Connection.processRequests(android.net.http.Request):void");
    }

    private boolean clearPipe(LinkedList<Request> pipe) {
        boolean empty = true;
        synchronized (this.mRequestFeeder) {
            while (!pipe.isEmpty()) {
                Request tReq = pipe.removeLast();
                this.mRequestFeeder.requeueRequest(tReq);
                empty = false;
            }
            if (empty) {
                empty = !this.mRequestFeeder.haveRequest(this.mHost);
            }
        }
        return empty;
    }

    private boolean openHttpConnection(Request req) {
        SystemClock.uptimeMillis();
        int error = 0;
        Exception exception = null;
        try {
            this.mCertificate = null;
            this.mHttpClientConnection = openConnection(req);
        } catch (SSLConnectionClosedByUserException e) {
            req.mFailCount = 2;
            return false;
        } catch (IllegalArgumentException e2) {
            error = -6;
            req.mFailCount = 2;
            exception = e2;
        } catch (UnknownHostException e3) {
            error = -2;
            exception = e3;
        } catch (SSLHandshakeException e4) {
            req.mFailCount = 2;
            error = -11;
            exception = e4;
        } catch (IOException e5) {
            error = -6;
            exception = e5;
        }
        if (this.mHttpClientConnection != null) {
            this.mHttpClientConnection.setSocketTimeout(60000);
            this.mHttpContext.setAttribute("http.connection", this.mHttpClientConnection);
            if (error == 0) {
                return true;
            }
            if (req.mFailCount < 2) {
                this.mRequestFeeder.requeueRequest(req);
                req.mFailCount++;
            } else {
                httpFailure(req, error, exception);
            }
            if (error == 0) {
                return true;
            }
            return false;
        }
        req.mFailCount = 2;
        return false;
    }

    private boolean httpFailure(Request req, int errorId, Exception e) {
        String error;
        boolean ret = true;
        int i = req.mFailCount + 1;
        req.mFailCount = i;
        if (i >= 2) {
            ret = false;
            if (errorId < 0) {
                error = getEventHandlerErrorString(errorId);
            } else {
                Throwable cause = e.getCause();
                error = cause != null ? cause.toString() : e.getMessage();
            }
            req.mEventHandler.error(errorId, error);
            req.complete();
        }
        closeConnection();
        this.mHttpContext.removeAttribute("http.connection");
        return ret;
    }

    private static String getEventHandlerErrorString(int errorId) {
        switch (errorId) {
            case -15:
                return "TOO_MANY_REQUESTS_ERROR";
            case -14:
                return "FILE_NOT_FOUND_ERROR";
            case -13:
                return "FILE_ERROR";
            case -12:
                return "ERROR_BAD_URL";
            case -11:
                return "ERROR_FAILED_SSL_HANDSHAKE";
            case -10:
                return "ERROR_UNSUPPORTED_SCHEME";
            case -9:
                return "ERROR_REDIRECT_LOOP";
            case -8:
                return "ERROR_TIMEOUT";
            case -7:
                return "ERROR_IO";
            case -6:
                return "ERROR_CONNECT";
            case -5:
                return "ERROR_PROXYAUTH";
            case -4:
                return "ERROR_AUTH";
            case -3:
                return "ERROR_UNSUPPORTED_AUTH_SCHEME";
            case -2:
                return "ERROR_LOOKUP";
            case -1:
                return "ERROR";
            case 0:
                return "OK";
            default:
                return "UNKNOWN_ERROR";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpContext getHttpContext() {
        return this.mHttpContext;
    }

    private boolean keepAlive(HttpEntity entity, ProtocolVersion ver, int connType, HttpContext context) {
        HttpConnection conn = (HttpConnection) context.getAttribute("http.connection");
        if (conn != null && !conn.isOpen()) {
            return false;
        }
        if ((entity != null && entity.getContentLength() < 0 && (!entity.isChunked() || ver.lessEquals(HttpVersion.HTTP_1_0))) || connType == 1) {
            return false;
        }
        if (connType == 2) {
            return true;
        }
        return !ver.lessEquals(HttpVersion.HTTP_1_0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCanPersist(HttpEntity entity, ProtocolVersion ver, int connType) {
        this.mCanPersist = keepAlive(entity, ver, connType, this.mHttpContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCanPersist(boolean canPersist) {
        this.mCanPersist = canPersist;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getCanPersist() {
        return this.mCanPersist;
    }

    public synchronized String toString() {
        return this.mHost.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getBuf() {
        if (this.mBuf == null) {
            this.mBuf = new byte[8192];
        }
        return this.mBuf;
    }
}
