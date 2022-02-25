package org.apache.commons.p105io.input;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import org.apache.commons.p105io.FileUtils;
import org.apache.commons.p105io.IOUtils;

/* renamed from: org.apache.commons.io.input.Tailer */
/* loaded from: classes2.dex */
public class Tailer implements Runnable {
    private static final int DEFAULT_BUFSIZE = 4096;
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final String RAF_MODE = "r";
    private final Charset cset;
    private final long delayMillis;
    private final boolean end;
    private final File file;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;

    public Tailer(File file, TailerListener tailerListener) {
        this(file, tailerListener, 1000L);
    }

    public Tailer(File file, TailerListener tailerListener, long j) {
        this(file, tailerListener, j, false);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z) {
        this(file, tailerListener, j, z, 4096);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, boolean z2) {
        this(file, tailerListener, j, z, z2, 4096);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, int i) {
        this(file, tailerListener, j, z, false, i);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this(file, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public Tailer(File file, Charset charset, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this.run = true;
        this.file = file;
        this.delayMillis = j;
        this.end = z;
        this.inbuf = new byte[i];
        this.listener = tailerListener;
        tailerListener.init(this);
        this.reOpen = z2;
        this.cset = charset;
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, int i) {
        return create(file, tailerListener, j, z, false, i);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        return create(file, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public static Tailer create(File file, Charset charset, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        Tailer tailer = new Tailer(file, charset, tailerListener, j, z, z2, i);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z) {
        return create(file, tailerListener, j, z, 4096);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, boolean z2) {
        return create(file, tailerListener, j, z, z2, 4096);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j) {
        return create(file, tailerListener, j, false);
    }

    public static Tailer create(File file, TailerListener tailerListener) {
        return create(file, tailerListener, 1000L, false);
    }

    public File getFile() {
        return this.file;
    }

    protected boolean getRun() {
        return this.run;
    }

    public long getDelay() {
        return this.delayMillis;
    }

    @Override // java.lang.Runnable
    public void run() {
        Throwable th;
        InterruptedException e;
        Exception e2;
        RandomAccessFile randomAccessFile = null;
        long j = 0;
        long j2 = 0;
        while (getRun() && randomAccessFile == null) {
            try {
                try {
                    try {
                        randomAccessFile = new RandomAccessFile(this.file, RAF_MODE);
                    } catch (FileNotFoundException unused) {
                        this.listener.fileNotFound();
                    }
                    if (randomAccessFile == null) {
                        Thread.sleep(this.delayMillis);
                    } else {
                        j2 = this.end ? this.file.length() : 0L;
                        j = this.file.lastModified();
                        randomAccessFile.seek(j2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (InterruptedException e3) {
                e = e3;
            } catch (Exception e4) {
                e2 = e4;
            }
        }
        while (getRun()) {
            boolean isFileNewer = FileUtils.isFileNewer(this.file, j);
            int i = (this.file.length() > j2 ? 1 : (this.file.length() == j2 ? 0 : -1));
            if (i < 0) {
                this.listener.fileRotated();
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.file, RAF_MODE);
                    try {
                        try {
                            readLines(randomAccessFile);
                        } catch (IOException e5) {
                            try {
                                this.listener.handle(e5);
                            } catch (FileNotFoundException unused2) {
                                randomAccessFile = randomAccessFile2;
                                this.listener.fileNotFound();
                            }
                        }
                        try {
                            IOUtils.closeQuietly(randomAccessFile);
                            j2 = 0;
                            randomAccessFile = randomAccessFile2;
                        } catch (FileNotFoundException unused3) {
                            j2 = 0;
                            randomAccessFile = randomAccessFile2;
                            this.listener.fileNotFound();
                        }
                    } catch (InterruptedException e6) {
                        e = e6;
                        randomAccessFile = randomAccessFile2;
                        Thread.currentThread().interrupt();
                        stop(e);
                        IOUtils.closeQuietly(randomAccessFile);
                        return;
                    } catch (Exception e7) {
                        e2 = e7;
                        randomAccessFile = randomAccessFile2;
                        stop(e2);
                        IOUtils.closeQuietly(randomAccessFile);
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                } catch (FileNotFoundException unused4) {
                }
            } else {
                if (i > 0) {
                    long readLines = readLines(randomAccessFile);
                    j = this.file.lastModified();
                    j2 = readLines;
                } else if (isFileNewer) {
                    randomAccessFile.seek(0L);
                    long readLines2 = readLines(randomAccessFile);
                    j = this.file.lastModified();
                    j2 = readLines2;
                }
                if (this.reOpen) {
                    IOUtils.closeQuietly(randomAccessFile);
                }
                Thread.sleep(this.delayMillis);
                if (getRun() && this.reOpen) {
                    RandomAccessFile randomAccessFile3 = new RandomAccessFile(this.file, RAF_MODE);
                    randomAccessFile3.seek(j2);
                    randomAccessFile = randomAccessFile3;
                }
            }
        }
        IOUtils.closeQuietly(randomAccessFile);
    }

    private void stop(Exception exc) {
        this.listener.handle(exc);
        stop();
    }

    public void stop() {
        this.run = false;
    }

    private long readLines(RandomAccessFile randomAccessFile) throws IOException {
        int read;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(64);
        long filePointer = randomAccessFile.getFilePointer();
        long j = filePointer;
        boolean z = false;
        while (getRun() && (read = randomAccessFile.read(this.inbuf)) != -1) {
            long j2 = filePointer;
            for (int i = 0; i < read; i++) {
                byte b = this.inbuf[i];
                if (b == 10) {
                    this.listener.handle(new String(byteArrayOutputStream.toByteArray(), this.cset));
                    byteArrayOutputStream.reset();
                    j2 = i + j + 1;
                    z = false;
                } else if (b != 13) {
                    if (z) {
                        this.listener.handle(new String(byteArrayOutputStream.toByteArray(), this.cset));
                        byteArrayOutputStream.reset();
                        j2 = i + j + 1;
                        z = false;
                    }
                    byteArrayOutputStream.write(b);
                } else {
                    if (z) {
                        byteArrayOutputStream.write(13);
                    }
                    z = true;
                }
            }
            j = randomAccessFile.getFilePointer();
            filePointer = j2;
        }
        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
        randomAccessFile.seek(filePointer);
        TailerListener tailerListener = this.listener;
        if (tailerListener instanceof TailerListenerAdapter) {
            ((TailerListenerAdapter) tailerListener).endOfFileReached();
        }
        return filePointer;
    }
}
