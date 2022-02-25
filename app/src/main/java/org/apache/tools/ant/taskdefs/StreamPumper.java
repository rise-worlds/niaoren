package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class StreamPumper implements Runnable {
    private static final long POLL_INTERVAL = 100;
    private static final int SMALL_BUFFER_SIZE = 128;
    private boolean autoflush;
    private int bufferSize;
    private final boolean closeWhenExhausted;
    private Exception exception;
    private volatile boolean finish;
    private volatile boolean finished;

    /* renamed from: is */
    private final InputStream f14759is;

    /* renamed from: os */
    private final OutputStream f14760os;
    private boolean started;
    private final boolean useAvailable;

    public StreamPumper(InputStream inputStream, OutputStream outputStream, boolean z) {
        this(inputStream, outputStream, z, false);
    }

    public StreamPumper(InputStream inputStream, OutputStream outputStream, boolean z, boolean z2) {
        this.autoflush = false;
        this.exception = null;
        this.bufferSize = 128;
        this.started = false;
        this.f14759is = inputStream;
        this.f14760os = outputStream;
        this.closeWhenExhausted = z;
        this.useAvailable = z2;
    }

    public StreamPumper(InputStream inputStream, OutputStream outputStream) {
        this(inputStream, outputStream, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAutoflush(boolean z) {
        this.autoflush = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        int read;
        int read2;
        synchronized (this) {
            this.started = true;
        }
        this.finished = false;
        byte[] bArr = new byte[this.bufferSize];
        do {
            try {
                try {
                    waitForInput(this.f14759is);
                    if (this.finish || Thread.interrupted() || (read2 = this.f14759is.read(bArr)) <= 0 || Thread.interrupted()) {
                        break;
                    }
                    this.f14760os.write(bArr, 0, read2);
                    if (this.autoflush) {
                        this.f14760os.flush();
                    }
                } catch (InterruptedException unused) {
                    if (this.closeWhenExhausted) {
                        FileUtils.close(this.f14760os);
                    }
                    this.finished = true;
                    this.finish = false;
                    synchronized (this) {
                        notifyAll();
                        return;
                    }
                } catch (Exception e) {
                    synchronized (this) {
                        this.exception = e;
                        if (this.closeWhenExhausted) {
                            FileUtils.close(this.f14760os);
                        }
                        this.finished = true;
                        this.finish = false;
                        synchronized (this) {
                            notifyAll();
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                if (this.closeWhenExhausted) {
                    FileUtils.close(this.f14760os);
                }
                this.finished = true;
                this.finish = false;
                synchronized (this) {
                    notifyAll();
                    throw th;
                }
            }
        } while (!this.finish);
        if (this.finish) {
            while (true) {
                int available = this.f14759is.available();
                if (available <= 0 || Thread.interrupted() || (read = this.f14759is.read(bArr, 0, Math.min(available, bArr.length))) <= 0) {
                    break;
                }
                this.f14760os.write(bArr, 0, read);
            }
        }
        this.f14760os.flush();
        if (this.closeWhenExhausted) {
            FileUtils.close(this.f14760os);
        }
        this.finished = true;
        this.finish = false;
        synchronized (this) {
            notifyAll();
        }
    }

    public boolean isFinished() {
        return this.finished;
    }

    public synchronized void waitFor() throws InterruptedException {
        while (!isFinished()) {
            wait();
        }
    }

    public synchronized void setBufferSize(int i) {
        if (!this.started) {
            this.bufferSize = i;
        } else {
            throw new IllegalStateException("Cannot set buffer size on a running StreamPumper");
        }
    }

    public synchronized int getBufferSize() {
        return this.bufferSize;
    }

    public synchronized Exception getException() {
        return this.exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void stop() {
        this.finish = true;
        notifyAll();
    }

    private void waitForInput(InputStream inputStream) throws IOException, InterruptedException {
        if (this.useAvailable) {
            while (!this.finish && inputStream.available() == 0) {
                if (!Thread.interrupted()) {
                    synchronized (this) {
                        wait(POLL_INTERVAL);
                    }
                } else {
                    throw new InterruptedException();
                }
            }
        }
    }
}
