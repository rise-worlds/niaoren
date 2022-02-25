package org.apache.http.impl.conn.tsccm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class RefQueueWorker implements Runnable {
    private final Log log = LogFactory.getLog(getClass());
    protected final RefQueueHandler refHandler;
    protected final ReferenceQueue<?> refQueue;
    protected volatile Thread workerThread;

    public RefQueueWorker(ReferenceQueue<?> queue, RefQueueHandler handler) {
        if (queue == null) {
            throw new IllegalArgumentException("Queue must not be null.");
        } else if (handler != null) {
            this.refQueue = queue;
            this.refHandler = handler;
        } else {
            throw new IllegalArgumentException("Handler must not be null.");
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.workerThread == null) {
            this.workerThread = Thread.currentThread();
        }
        while (this.workerThread == Thread.currentThread()) {
            try {
                Reference<?> ref = this.refQueue.remove();
                this.refHandler.handleReference(ref);
            } catch (InterruptedException e) {
                if (this.log.isDebugEnabled()) {
                    Log log = this.log;
                    log.debug(toString() + " interrupted", e);
                }
            }
        }
    }

    public void shutdown() {
        Thread wt = this.workerThread;
        if (wt != null) {
            this.workerThread = null;
            wt.interrupt();
        }
    }

    public String toString() {
        return "RefQueueWorker::" + this.workerThread;
    }
}
