package org.apache.tools.ant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public class DemuxOutputStream extends OutputStream {

    /* renamed from: CR */
    private static final int f14734CR = 13;
    private static final int INITIAL_SIZE = 132;

    /* renamed from: LF */
    private static final int f14735LF = 10;
    private static final int MAX_SIZE = 1024;
    private WeakHashMap<Thread, BufferInfo> buffers = new WeakHashMap<>();
    private boolean isErrorStream;
    private Project project;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BufferInfo {
        private ByteArrayOutputStream buffer;
        private boolean crSeen;

        private BufferInfo() {
            this.crSeen = false;
        }
    }

    public DemuxOutputStream(Project project, boolean z) {
        this.project = project;
        this.isErrorStream = z;
    }

    private BufferInfo getBufferInfo() {
        Thread currentThread = Thread.currentThread();
        BufferInfo bufferInfo = this.buffers.get(currentThread);
        if (bufferInfo != null) {
            return bufferInfo;
        }
        BufferInfo bufferInfo2 = new BufferInfo();
        bufferInfo2.buffer = new ByteArrayOutputStream(INITIAL_SIZE);
        bufferInfo2.crSeen = false;
        this.buffers.put(currentThread, bufferInfo2);
        return bufferInfo2;
    }

    private void resetBufferInfo() {
        BufferInfo bufferInfo = this.buffers.get(Thread.currentThread());
        try {
            bufferInfo.buffer.close();
        } catch (IOException unused) {
        }
        bufferInfo.buffer = new ByteArrayOutputStream();
        bufferInfo.crSeen = false;
    }

    private void removeBuffer() {
        this.buffers.remove(Thread.currentThread());
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte b = (byte) i;
        BufferInfo bufferInfo = getBufferInfo();
        if (b == 10) {
            bufferInfo.buffer.write(i);
            processBuffer(bufferInfo.buffer);
        } else {
            if (bufferInfo.crSeen) {
                processBuffer(bufferInfo.buffer);
            }
            bufferInfo.buffer.write(i);
        }
        bufferInfo.crSeen = b == 13;
        if (!bufferInfo.crSeen && bufferInfo.buffer.size() > 1024) {
            processBuffer(bufferInfo.buffer);
        }
    }

    protected void processBuffer(ByteArrayOutputStream byteArrayOutputStream) {
        this.project.demuxOutput(byteArrayOutputStream.toString(), this.isErrorStream);
        resetBufferInfo();
    }

    protected void processFlush(ByteArrayOutputStream byteArrayOutputStream) {
        this.project.demuxFlush(byteArrayOutputStream.toString(), this.isErrorStream);
        resetBufferInfo();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
        removeBuffer();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        BufferInfo bufferInfo = getBufferInfo();
        if (bufferInfo.buffer.size() > 0) {
            processFlush(bufferInfo.buffer);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        BufferInfo bufferInfo = getBufferInfo();
        while (i2 > 0) {
            int i3 = i;
            while (i2 > 0 && bArr[i3] != 10 && bArr[i3] != 13) {
                i3++;
                i2--;
            }
            int i4 = i3 - i;
            if (i4 > 0) {
                bufferInfo.buffer.write(bArr, i, i4);
            }
            i = i3;
            while (i2 > 0 && (bArr[i] == 10 || bArr[i] == 13)) {
                write(bArr[i]);
                i++;
                i2--;
            }
        }
    }
}
