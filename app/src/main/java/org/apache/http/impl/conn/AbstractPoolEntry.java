package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator connOperator;
    protected final OperatedClientConnection connection;
    protected volatile HttpRoute route;
    protected volatile Object state;
    protected volatile RouteTracker tracker;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPoolEntry(ClientConnectionOperator connOperator, HttpRoute route) {
        if (connOperator != null) {
            this.connOperator = connOperator;
            this.connection = connOperator.createConnection();
            this.route = route;
            this.tracker = null;
            return;
        }
        throw new IllegalArgumentException("Connection operator may not be null");
    }

    public Object getState() {
        return this.state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public void open(HttpRoute route, HttpContext context, HttpParams params) throws IOException {
        HttpHost httpHost;
        if (route == null) {
            throw new IllegalArgumentException("Route must not be null.");
        } else if (params == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            this.tracker = new RouteTracker(route);
            HttpHost proxy = route.getProxyHost();
            ClientConnectionOperator clientConnectionOperator = this.connOperator;
            OperatedClientConnection operatedClientConnection = this.connection;
            if (proxy != null) {
                httpHost = proxy;
            } else {
                httpHost = route.getTargetHost();
            }
            clientConnectionOperator.openConnection(operatedClientConnection, httpHost, route.getLocalAddress(), context, params);
            RouteTracker localTracker = this.tracker;
            if (localTracker == null) {
                throw new IOException("Request aborted");
            } else if (proxy == null) {
                localTracker.connectTarget(this.connection.isSecure());
            } else {
                localTracker.connectProxy(proxy, this.connection.isSecure());
            }
        } else {
            throw new IllegalStateException("Connection already open.");
        }
    }

    public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        } else if (!this.tracker.isTunnelled()) {
            this.connection.update(null, this.tracker.getTargetHost(), secure, params);
            this.tracker.tunnelTarget(secure);
        } else {
            throw new IllegalStateException("Connection is already tunnelled.");
        }
    }

    public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
        if (next == null) {
            throw new IllegalArgumentException("Next proxy must not be null.");
        } else if (params == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        } else {
            this.connection.update(null, next, secure, params);
            this.tracker.tunnelProxy(next, secure);
        }
    }

    public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        } else if (!this.tracker.isTunnelled()) {
            throw new IllegalStateException("Protocol layering without a tunnel not supported.");
        } else if (!this.tracker.isLayered()) {
            HttpHost target = this.tracker.getTargetHost();
            this.connOperator.updateSecureConnection(this.connection, target, context, params);
            this.tracker.layerProtocol(this.connection.isSecure());
        } else {
            throw new IllegalStateException("Multiple protocol layering not supported.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void shutdownEntry() {
        this.tracker = null;
    }
}
