package org.apache.tools.ant.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public abstract class LineOrientedOutputStream extends OutputStream {

    /* renamed from: CR */
    private static final int f14778CR = 13;
    private static final int INITIAL_SIZE = 132;

    /* renamed from: LF */
    private static final int f14779LF = 10;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream(INITIAL_SIZE);
    private boolean skip = false;

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
    }

    protected abstract void processLine(String str) throws IOException;

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        byte b = (byte) i;
        if (b != 10 && b != 13) {
            this.buffer.write(i);
        } else if (!this.skip) {
            processBuffer();
        }
        this.skip = b == 13;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processBuffer() throws IOException {
        try {
            processLine(this.buffer.toByteArray());
        } finally {
            this.buffer.reset();
        }
    }

    protected void processLine(byte[] bArr) throws IOException {
        processLine(new String(bArr));
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.buffer.size() > 0) {
            processBuffer();
        }
        super.close();
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int i3 = i;
            while (i2 > 0 && bArr[i3] != 10 && bArr[i3] != 13) {
                i3++;
                i2--;
            }
            int i4 = i3 - i;
            if (i4 > 0) {
                this.buffer.write(bArr, i, i4);
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
