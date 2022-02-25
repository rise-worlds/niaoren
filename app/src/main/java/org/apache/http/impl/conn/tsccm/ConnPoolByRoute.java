package org.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class ConnPoolByRoute extends AbstractConnPool {
    private final ConnPerRoute connPerRoute;
    protected Queue<BasicPoolEntry> freeConnections;
    private final Log log = LogFactory.getLog(getClass());
    protected final int maxTotalConnections;
    protected final ClientConnectionOperator operator;
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
    protected Queue<WaitingThread> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator operator, HttpParams params) {
        if (operator != null) {
            this.operator = operator;
            this.freeConnections = createFreeConnQueue();
            this.waitingThreads = createWaitingThreadQueue();
            this.routeToPool = createRouteToPoolMap();
            this.maxTotalConnections = ConnManagerParams.getMaxTotalConnections(params);
            this.connPerRoute = ConnManagerParams.getMaxConnectionsPerRoute(params);
            return;
        }
        throw new IllegalArgumentException("Connection operator may not be null");
    }

    protected Queue<BasicPoolEntry> createFreeConnQueue() {
        return new LinkedList();
    }

    protected Queue<WaitingThread> createWaitingThreadQueue() {
        return new LinkedList();
    }

    protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        return new HashMap();
    }

    protected RouteSpecificPool newRouteSpecificPool(HttpRoute route) {
        return new RouteSpecificPool(route, this.connPerRoute.getMaxForRoute(route));
    }

    protected WaitingThread newWaitingThread(Condition cond, RouteSpecificPool rospl) {
        return new WaitingThread(cond, rospl);
    }

    protected RouteSpecificPool getRoutePool(HttpRoute route, boolean create) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = this.routeToPool.get(route);
            if (rospl == null && create) {
                rospl = newRouteSpecificPool(route);
                this.routeToPool.put(route, rospl);
            }
            return rospl;
        } finally {
            this.poolLock.unlock();
        }
    }

    public int getConnectionsInPool(HttpRoute route) {
        this.poolLock.lock();
        int i = 0;
        try {
            RouteSpecificPool rospl = getRoutePool(route, false);
            if (rospl != null) {
                i = rospl.getEntryCount();
            }
            return i;
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public PoolEntryRequest requestPoolEntry(final HttpRoute route, final Object state) {
        final WaitingThreadAborter aborter = new WaitingThreadAborter();
        return new PoolEntryRequest() { // from class: org.apache.http.impl.conn.tsccm.ConnPoolByRoute.1
            @Override // org.apache.http.impl.conn.tsccm.PoolEntryRequest
            public void abortRequest() {
                ConnPoolByRoute.this.poolLock.lock();
                try {
                    aborter.abort();
                } finally {
                    ConnPoolByRoute.this.poolLock.unlock();
                }
            }

            @Override // org.apache.http.impl.conn.tsccm.PoolEntryRequest
            public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                return ConnPoolByRoute.this.getEntryBlocking(route, state, timeout, tunit, aborter);
            }
        };
    }

    protected BasicPoolEntry getEntryBlocking(HttpRoute route, Object state, long timeout, TimeUnit tunit, WaitingThreadAborter aborter) throws ConnectionPoolTimeoutException, InterruptedException {
        Date deadline;
        Throwable th;
        if (timeout > 0) {
            deadline = new Date(System.currentTimeMillis() + tunit.toMillis(timeout));
        } else {
            deadline = null;
        }
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, true);
            WaitingThread waitingThread = null;
            while (entry == null) {
                if (!this.isShutDown) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Total connections kept alive: " + this.freeConnections.size());
                        this.log.debug("Total issued connections: " + this.issuedConnections.size());
                        this.log.debug("Total allocated connection: " + this.numConnections + " out of " + this.maxTotalConnections);
                    }
                    entry = getFreeEntry(rospl, state);
                    if (entry != null) {
                        break;
                    }
                    boolean hasCapacity = false;
                    if (rospl.getCapacity() > 0) {
                        hasCapacity = true;
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Available capacity: " + rospl.getCapacity() + " out of " + rospl.getMaxEntries() + " [" + route + "][" + state + "]");
                    }
                    if (hasCapacity && this.numConnections < this.maxTotalConnections) {
                        entry = createEntry(rospl, this.operator);
                    } else if (!hasCapacity || this.freeConnections.isEmpty()) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Need to wait for connection [" + route + "][" + state + "]");
                        }
                        if (waitingThread == null) {
                            waitingThread = newWaitingThread(this.poolLock.newCondition(), rospl);
                            try {
                                aborter.setWaitingThread(waitingThread);
                            } catch (Throwable th2) {
                                th = th2;
                                this.poolLock.unlock();
                                throw th;
                            }
                        }
                        rospl.queueThread(waitingThread);
                        this.waitingThreads.add(waitingThread);
                        boolean success = waitingThread.await(deadline);
                        rospl.removeThread(waitingThread);
                        this.waitingThreads.remove(waitingThread);
                        if (!success && deadline != null && deadline.getTime() <= System.currentTimeMillis()) {
                            throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
                        }
                    } else {
                        deleteLeastUsedEntry();
                        entry = createEntry(rospl, this.operator);
                    }
                } else {
                    throw new IllegalStateException("Connection pool shut down.");
                }
            }
            this.poolLock.unlock();
            return entry;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void freeEntry(BasicPoolEntry entry, boolean reusable, long validDuration, TimeUnit timeUnit) {
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Freeing connection [" + route + "][" + entry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            if (this.isShutDown) {
                closeConnection(entry.getConnection());
                return;
            }
            this.issuedConnections.remove(entry.getWeakRef());
            RouteSpecificPool rospl = getRoutePool(route, true);
            if (reusable) {
                rospl.freeEntry(entry);
                this.freeConnections.add(entry);
                this.idleConnHandler.add(entry.getConnection(), validDuration, timeUnit);
            } else {
                rospl.dropEntry();
                this.numConnections--;
            }
            notifyWaitingThread(rospl);
        } finally {
            this.poolLock.unlock();
        }
    }

    protected BasicPoolEntry getFreeEntry(RouteSpecificPool rospl, Object state) {
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        boolean done = false;
        while (!done) {
            try {
                entry = rospl.allocEntry(state);
                if (entry != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Getting free connection [" + rospl.getRoute() + "][" + state + "]");
                    }
                    this.freeConnections.remove(entry);
                    boolean valid = this.idleConnHandler.remove(entry.getConnection());
                    if (!valid) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Closing expired free connection [" + rospl.getRoute() + "][" + state + "]");
                        }
                        closeConnection(entry.getConnection());
                        rospl.dropEntry();
                        this.numConnections--;
                    } else {
                        this.issuedConnections.add(entry.getWeakRef());
                        done = true;
                    }
                } else {
                    done = true;
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("No free connections [" + rospl.getRoute() + "][" + state + "]");
                    }
                }
            } finally {
                this.poolLock.unlock();
            }
        }
        return entry;
    }

    protected BasicPoolEntry createEntry(RouteSpecificPool rospl, ClientConnectionOperator op) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Creating new connection [" + rospl.getRoute() + "]");
        }
        BasicPoolEntry entry = new BasicPoolEntry(op, rospl.getRoute(), this.refQueue);
        this.poolLock.lock();
        try {
            rospl.createdEntry(entry);
            this.numConnections++;
            this.issuedConnections.add(entry.getWeakRef());
            return entry;
        } finally {
            this.poolLock.unlock();
        }
    }

    protected void deleteEntry(BasicPoolEntry entry) {
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Deleting connection [" + route + "][" + entry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            closeConnection(entry.getConnection());
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.deleteEntry(entry);
            this.numConnections--;
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
            this.idleConnHandler.remove(entry.getConnection());
        } finally {
            this.poolLock.unlock();
        }
    }

    protected void deleteLeastUsedEntry() {
        try {
            this.poolLock.lock();
            BasicPoolEntry entry = this.freeConnections.remove();
            if (entry != null) {
                deleteEntry(entry);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete.");
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    protected void handleLostEntry(HttpRoute route) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.dropEntry();
            if (rospl.isUnused()) {
                this.routeToPool.remove(route);
            }
            this.numConnections--;
            notifyWaitingThread(rospl);
        } finally {
            this.poolLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006f A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #0 {all -> 0x003b, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0016, B:9:0x0035, B:12:0x003d, B:14:0x0045, B:16:0x004d, B:17:0x0054, B:18:0x005e, B:20:0x0066, B:22:0x006f), top: B:27:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void notifyWaitingThread(org.apache.http.impl.conn.tsccm.RouteSpecificPool r5) {
        /*
            r4 = this;
            r0 = 0
            java.util.concurrent.locks.Lock r1 = r4.poolLock
            r1.lock()
            if (r5 == 0) goto L_0x003d
            boolean r1 = r5.hasThread()     // Catch: all -> 0x003b
            if (r1 == 0) goto L_0x003d
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            boolean r1 = r1.isDebugEnabled()     // Catch: all -> 0x003b
            if (r1 == 0) goto L_0x0035
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x003b
            r2.<init>()     // Catch: all -> 0x003b
            java.lang.String r3 = "Notifying thread waiting on pool ["
            r2.append(r3)     // Catch: all -> 0x003b
            org.apache.http.conn.routing.HttpRoute r3 = r5.getRoute()     // Catch: all -> 0x003b
            r2.append(r3)     // Catch: all -> 0x003b
            java.lang.String r3 = "]"
            r2.append(r3)     // Catch: all -> 0x003b
            java.lang.String r2 = r2.toString()     // Catch: all -> 0x003b
            r1.debug(r2)     // Catch: all -> 0x003b
        L_0x0035:
            org.apache.http.impl.conn.tsccm.WaitingThread r1 = r5.nextThread()     // Catch: all -> 0x003b
            r0 = r1
            goto L_0x006d
        L_0x003b:
            r1 = move-exception
            goto L_0x0079
        L_0x003d:
            java.util.Queue<org.apache.http.impl.conn.tsccm.WaitingThread> r1 = r4.waitingThreads     // Catch: all -> 0x003b
            boolean r1 = r1.isEmpty()     // Catch: all -> 0x003b
            if (r1 != 0) goto L_0x005e
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            boolean r1 = r1.isDebugEnabled()     // Catch: all -> 0x003b
            if (r1 == 0) goto L_0x0054
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            java.lang.String r2 = "Notifying thread waiting on any pool"
            r1.debug(r2)     // Catch: all -> 0x003b
        L_0x0054:
            java.util.Queue<org.apache.http.impl.conn.tsccm.WaitingThread> r1 = r4.waitingThreads     // Catch: all -> 0x003b
            java.lang.Object r1 = r1.remove()     // Catch: all -> 0x003b
            org.apache.http.impl.conn.tsccm.WaitingThread r1 = (org.apache.http.impl.conn.tsccm.WaitingThread) r1     // Catch: all -> 0x003b
            r0 = r1
            goto L_0x006d
        L_0x005e:
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            boolean r1 = r1.isDebugEnabled()     // Catch: all -> 0x003b
            if (r1 == 0) goto L_0x006d
            org.apache.commons.logging.Log r1 = r4.log     // Catch: all -> 0x003b
            java.lang.String r2 = "Notifying no-one, there are no waiting threads"
            r1.debug(r2)     // Catch: all -> 0x003b
        L_0x006d:
            if (r0 == 0) goto L_0x0072
            r0.wakeup()     // Catch: all -> 0x003b
        L_0x0072:
            java.util.concurrent.locks.Lock r1 = r4.poolLock
            r1.unlock()
            return
        L_0x0079:
            java.util.concurrent.locks.Lock r2 = r4.poolLock
            r2.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(org.apache.http.impl.conn.tsccm.RouteSpecificPool):void");
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void deleteClosedConnections() {
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = iter.next();
                if (!entry.getConnection().isOpen()) {
                    iter.remove();
                    deleteEntry(entry);
                }
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    @Override // org.apache.http.impl.conn.tsccm.AbstractConnPool
    public void shutdown() {
        this.poolLock.lock();
        try {
            super.shutdown();
            Iterator<BasicPoolEntry> ibpe = this.freeConnections.iterator();
            while (ibpe.hasNext()) {
                BasicPoolEntry entry = ibpe.next();
                ibpe.remove();
                closeConnection(entry.getConnection());
            }
            Iterator<WaitingThread> iwth = this.waitingThreads.iterator();
            while (iwth.hasNext()) {
                WaitingThread waiter = iwth.next();
                iwth.remove();
                waiter.wakeup();
            }
            this.routeToPool.clear();
        } finally {
            this.poolLock.unlock();
        }
    }
}
