package org.apache.http.impl.conn.tsccm;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    /* JADX INFO: Access modifiers changed from: protected */
    public BasicPooledConnAdapter(ThreadSafeClientConnManager tsccm, AbstractPoolEntry entry) {
        super(tsccm, entry);
        markReusable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.conn.AbstractClientConnAdapter
    public ClientConnectionManager getManager() {
        return super.getManager();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.conn.AbstractPooledConnAdapter, org.apache.http.impl.conn.AbstractClientConnAdapter
    public void detach() {
        super.detach();
    }
}
