package org.apache.http.impl.conn;

import android.net.TrafficStats;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class SingleClientConnManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    protected boolean alwaysShutDown;
    protected ClientConnectionOperator connOperator;
    protected long connectionExpiresTime;
    protected volatile boolean isShutDown;
    protected long lastReleaseTime;
    private final Log log = LogFactory.getLog(getClass());
    protected ConnAdapter managedConn;
    protected SchemeRegistry schemeRegistry;
    protected PoolEntry uniquePoolEntry;

    public SingleClientConnManager(HttpParams params, SchemeRegistry schreg) {
        if (schreg != null) {
            this.schemeRegistry = schreg;
            this.connOperator = createConnectionOperator(schreg);
            this.uniquePoolEntry = new PoolEntry();
            this.managedConn = null;
            this.lastReleaseTime = -1L;
            this.alwaysShutDown = false;
            this.isShutDown = false;
            return;
        }
        throw new IllegalArgumentException("Scheme registry must not be null.");
    }

    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    protected final void assertStillUp() throws IllegalStateException {
        if (this.isShutDown) {
            throw new IllegalStateException("Manager is shut down.");
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public final ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
        return new ClientConnectionRequest() { // from class: org.apache.http.impl.conn.SingleClientConnManager.1
            @Override // org.apache.http.conn.ClientConnectionRequest
            public void abortRequest() {
            }

            @Override // org.apache.http.conn.ClientConnectionRequest
            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) {
                return SingleClientConnManager.this.getConnection(route, state);
            }
        };
    }

    public ManagedClientConnection getConnection(HttpRoute route, Object state) {
        if (route != null) {
            assertStillUp();
            if (this.log.isDebugEnabled()) {
                Log log = this.log;
                log.debug("Get connection for route " + route);
            }
            if (this.managedConn != null) {
                revokeConnection();
            }
            boolean recreate = false;
            boolean shutdown = false;
            closeExpiredConnections();
            if (this.uniquePoolEntry.connection.isOpen()) {
                RouteTracker tracker = this.uniquePoolEntry.tracker;
                shutdown = tracker == null || !tracker.toRoute().equals(route);
            } else {
                recreate = true;
            }
            if (shutdown) {
                recreate = true;
                try {
                    this.uniquePoolEntry.shutdown();
                } catch (IOException iox) {
                    this.log.debug("Problem shutting down connection.", iox);
                }
            }
            if (recreate) {
                this.uniquePoolEntry = new PoolEntry();
            }
            try {
                Socket socket = this.uniquePoolEntry.connection.getSocket();
                if (socket != null) {
                    TrafficStats.tagSocket(socket);
                }
            } catch (IOException iox2) {
                this.log.debug("Problem tagging socket.", iox2);
            }
            this.managedConn = new ConnAdapter(this.uniquePoolEntry, route);
            return this.managedConn;
        }
        throw new IllegalArgumentException("Route may not be null.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0082, code lost:
        if (r12 > 0) goto L_0x00a6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a4, code lost:
        if (r12 <= 0) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a6, code lost:
        r10.connectionExpiresTime = r14.toMillis(r12) + r10.lastReleaseTime;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r10.connectionExpiresTime = p110z1.cjm.f20759b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b3, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    @Override // org.apache.http.conn.ClientConnectionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void releaseConnection(org.apache.http.conn.ManagedClientConnection r11, long r12, java.util.concurrent.TimeUnit r14) {
        /*
            r10 = this;
            r10.assertStillUp()
            boolean r0 = r11 instanceof org.apache.http.impl.conn.SingleClientConnManager.ConnAdapter
            if (r0 == 0) goto L_0x00d0
            org.apache.commons.logging.Log r0 = r10.log
            boolean r0 = r0.isDebugEnabled()
            if (r0 == 0) goto L_0x0025
            org.apache.commons.logging.Log r0 = r10.log
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Releasing connection "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            r0.debug(r1)
        L_0x0025:
            r0 = r11
            org.apache.http.impl.conn.SingleClientConnManager$ConnAdapter r0 = (org.apache.http.impl.conn.SingleClientConnManager.ConnAdapter) r0
            org.apache.http.impl.conn.AbstractPoolEntry r1 = r0.poolEntry
            if (r1 != 0) goto L_0x002d
            return
        L_0x002d:
            org.apache.http.conn.ClientConnectionManager r1 = r0.getManager()
            if (r1 == 0) goto L_0x003e
            if (r1 != r10) goto L_0x0036
            goto L_0x003e
        L_0x0036:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Connection not obtained from this manager."
            r2.<init>(r3)
            throw r2
        L_0x003e:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r4 = 0
            r6 = 0
            org.apache.http.impl.conn.SingleClientConnManager$PoolEntry r7 = r10.uniquePoolEntry     // Catch: all -> 0x0085, IOException -> 0x0087
            org.apache.http.conn.OperatedClientConnection r7 = r7.connection     // Catch: all -> 0x0085, IOException -> 0x0087
            java.net.Socket r7 = r7.getSocket()     // Catch: all -> 0x0085, IOException -> 0x0087
            if (r7 == 0) goto L_0x0053
            android.net.TrafficStats.untagSocket(r7)     // Catch: all -> 0x0085, IOException -> 0x0087
        L_0x0053:
            boolean r8 = r0.isOpen()     // Catch: all -> 0x0085, IOException -> 0x0087
            if (r8 == 0) goto L_0x0075
            boolean r8 = r10.alwaysShutDown     // Catch: all -> 0x0085, IOException -> 0x0087
            if (r8 != 0) goto L_0x0063
            boolean r8 = r0.isMarkedReusable()     // Catch: all -> 0x0085, IOException -> 0x0087
            if (r8 != 0) goto L_0x0075
        L_0x0063:
            org.apache.commons.logging.Log r8 = r10.log     // Catch: all -> 0x0085, IOException -> 0x0087
            boolean r8 = r8.isDebugEnabled()     // Catch: all -> 0x0085, IOException -> 0x0087
            if (r8 == 0) goto L_0x0072
            org.apache.commons.logging.Log r8 = r10.log     // Catch: all -> 0x0085, IOException -> 0x0087
            java.lang.String r9 = "Released connection open but not reusable."
            r8.debug(r9)     // Catch: all -> 0x0085, IOException -> 0x0087
        L_0x0072:
            r0.shutdown()     // Catch: all -> 0x0085, IOException -> 0x0087
        L_0x0075:
            r0.detach()
            r10.managedConn = r6
            long r6 = java.lang.System.currentTimeMillis()
            r10.lastReleaseTime = r6
            int r4 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x00b0
            goto L_0x00a6
        L_0x0085:
            r7 = move-exception
            goto L_0x00b4
        L_0x0087:
            r7 = move-exception
            org.apache.commons.logging.Log r8 = r10.log     // Catch: all -> 0x0085
            boolean r8 = r8.isDebugEnabled()     // Catch: all -> 0x0085
            if (r8 == 0) goto L_0x0097
            org.apache.commons.logging.Log r8 = r10.log     // Catch: all -> 0x0085
            java.lang.String r9 = "Exception shutting down released connection."
            r8.debug(r9, r7)     // Catch: all -> 0x0085
        L_0x0097:
            r0.detach()
            r10.managedConn = r6
            long r6 = java.lang.System.currentTimeMillis()
            r10.lastReleaseTime = r6
            int r4 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x00b0
        L_0x00a6:
            long r2 = r14.toMillis(r12)
            long r4 = r10.lastReleaseTime
            long r2 = r2 + r4
            r10.connectionExpiresTime = r2
            goto L_0x00b3
        L_0x00b0:
            r10.connectionExpiresTime = r2
        L_0x00b3:
            return
        L_0x00b4:
            r0.detach()
            r10.managedConn = r6
            long r8 = java.lang.System.currentTimeMillis()
            r10.lastReleaseTime = r8
            int r4 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x00cd
            long r2 = r14.toMillis(r12)
            long r4 = r10.lastReleaseTime
            long r2 = r2 + r4
            r10.connectionExpiresTime = r2
            goto L_0x00cf
        L_0x00cd:
            r10.connectionExpiresTime = r2
        L_0x00cf:
            throw r7
        L_0x00d0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.SingleClientConnManager.releaseConnection(org.apache.http.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeExpiredConnections() {
        if (System.currentTimeMillis() >= this.connectionExpiresTime) {
            closeIdleConnections(0L, TimeUnit.MILLISECONDS);
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        assertStillUp();
        if (tunit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        } else if (this.managedConn == null && this.uniquePoolEntry.connection.isOpen()) {
            long cutoff = System.currentTimeMillis() - tunit.toMillis(idletime);
            if (this.lastReleaseTime <= cutoff) {
                try {
                    this.uniquePoolEntry.close();
                } catch (IOException iox) {
                    this.log.debug("Problem closing idle connection.", iox);
                }
            }
        }
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void shutdown() {
        this.isShutDown = true;
        if (this.managedConn != null) {
            this.managedConn.detach();
        }
        try {
            try {
                if (this.uniquePoolEntry != null) {
                    this.uniquePoolEntry.shutdown();
                }
            } catch (IOException iox) {
                this.log.debug("Problem while shutting down manager.", iox);
            }
        } finally {
            this.uniquePoolEntry = null;
        }
    }

    protected void revokeConnection() {
        if (this.managedConn != null) {
            this.log.warn(MISUSE_MESSAGE);
            this.managedConn.detach();
            try {
                this.uniquePoolEntry.shutdown();
            } catch (IOException iox) {
                this.log.debug("Problem while shutting down connection.", iox);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: assets/org.apache.http.legacy.boot */
    public class PoolEntry extends AbstractPoolEntry {
        protected PoolEntry() {
            super(SingleClientConnManager.this.connOperator, null);
        }

        protected void close() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.close();
            }
        }

        protected void shutdown() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.shutdown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: assets/org.apache.http.legacy.boot */
    public class ConnAdapter extends AbstractPooledConnAdapter {
        protected ConnAdapter(PoolEntry entry, HttpRoute route) {
            super(SingleClientConnManager.this, entry);
            markReusable();
            entry.route = route;
        }
    }
}
