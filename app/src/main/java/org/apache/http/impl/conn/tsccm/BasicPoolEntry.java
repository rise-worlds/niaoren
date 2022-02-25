package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicPoolEntry extends AbstractPoolEntry {
    private final BasicPoolEntryRef reference;

    public BasicPoolEntry(ClientConnectionOperator op, HttpRoute route, ReferenceQueue<Object> queue) {
        super(op, route);
        if (route != null) {
            this.reference = new BasicPoolEntryRef(this, queue);
            return;
        }
        throw new IllegalArgumentException("HTTP route may not be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final OperatedClientConnection getConnection() {
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final HttpRoute getPlannedRoute() {
        return this.route;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BasicPoolEntryRef getWeakRef() {
        return this.reference;
    }
}
