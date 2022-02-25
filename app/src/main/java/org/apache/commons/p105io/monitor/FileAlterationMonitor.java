package org.apache.commons.p105io.monitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;

/* renamed from: org.apache.commons.io.monitor.FileAlterationMonitor */
/* loaded from: classes2.dex */
public final class FileAlterationMonitor implements Runnable {
    private final long interval;
    private final List<FileAlterationObserver> observers;
    private volatile boolean running;
    private Thread thread;
    private ThreadFactory threadFactory;

    public FileAlterationMonitor() {
        this(10000L);
    }

    public FileAlterationMonitor(long j) {
        this.observers = new CopyOnWriteArrayList();
        this.thread = null;
        this.running = false;
        this.interval = j;
    }

    public FileAlterationMonitor(long j, FileAlterationObserver... fileAlterationObserverArr) {
        this(j);
        if (fileAlterationObserverArr != null) {
            for (FileAlterationObserver fileAlterationObserver : fileAlterationObserverArr) {
                addObserver(fileAlterationObserver);
            }
        }
    }

    public final long getInterval() {
        return this.interval;
    }

    public final synchronized void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public final void addObserver(FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            this.observers.add(fileAlterationObserver);
        }
    }

    public final void removeObserver(FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            do {
            } while (this.observers.remove(fileAlterationObserver));
        }
    }

    public final Iterable<FileAlterationObserver> getObservers() {
        return this.observers;
    }

    public final synchronized void start() throws Exception {
        if (!this.running) {
            for (FileAlterationObserver fileAlterationObserver : this.observers) {
                fileAlterationObserver.initialize();
            }
            this.running = true;
            if (this.threadFactory != null) {
                this.thread = this.threadFactory.newThread(this);
            } else {
                this.thread = new Thread(this);
            }
            this.thread.start();
        } else {
            throw new IllegalStateException("Monitor is already running");
        }
    }

    public final synchronized void stop() throws Exception {
        stop(this.interval);
    }

    public final synchronized void stop(long j) throws Exception {
        if (this.running) {
            this.running = false;
            try {
                this.thread.join(j);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            for (FileAlterationObserver fileAlterationObserver : this.observers) {
                fileAlterationObserver.destroy();
            }
        } else {
            throw new IllegalStateException("Monitor is not running");
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (this.running) {
            for (FileAlterationObserver fileAlterationObserver : this.observers) {
                fileAlterationObserver.checkAndNotify();
            }
            if (this.running) {
                try {
                    Thread.sleep(this.interval);
                } catch (InterruptedException unused) {
                }
            } else {
                return;
            }
        }
    }
}
