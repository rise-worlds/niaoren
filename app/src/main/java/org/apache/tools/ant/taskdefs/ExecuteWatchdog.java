package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.TimeoutObserver;
import org.apache.tools.ant.util.Watchdog;

/* loaded from: classes2.dex */
public class ExecuteWatchdog implements TimeoutObserver {
    private Exception caught;
    private volatile boolean killedProcess;
    private Process process;
    private volatile boolean watch;
    private Watchdog watchdog;

    public ExecuteWatchdog(long j) {
        this.watch = false;
        this.caught = null;
        this.killedProcess = false;
        this.watchdog = new Watchdog(j);
        this.watchdog.addTimeoutObserver(this);
    }

    public ExecuteWatchdog(int i) {
        this(i);
    }

    public synchronized void start(Process process) {
        try {
            if (process == null) {
                throw new NullPointerException("process is null.");
            } else if (this.process == null) {
                this.caught = null;
                this.killedProcess = false;
                this.watch = true;
                this.process = process;
                this.watchdog.start();
            } else {
                throw new IllegalStateException("Already running.");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void stop() {
        this.watchdog.stop();
        cleanUp();
    }

    @Override // org.apache.tools.ant.util.TimeoutObserver
    public synchronized void timeoutOccured(Watchdog watchdog) {
        try {
            try {
                this.process.exitValue();
            } catch (IllegalThreadStateException unused) {
                if (this.watch) {
                    this.killedProcess = true;
                    this.process.destroy();
                }
            }
        } catch (Exception e) {
            this.caught = e;
        }
        cleanUp();
    }

    protected synchronized void cleanUp() {
        this.watch = false;
        this.process = null;
    }

    public synchronized void checkException() throws BuildException {
        if (this.caught != null) {
            throw new BuildException("Exception in ExecuteWatchdog.run: " + this.caught.getMessage(), this.caught);
        }
    }

    public boolean isWatching() {
        return this.watch;
    }

    public boolean killedProcess() {
        return this.killedProcess;
    }
}
