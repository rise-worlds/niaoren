package com.sun.mail.imap;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: IMAPFolder.java */
/* loaded from: classes2.dex */
class LengthCounter extends OutputStream {
    private int maxsize;
    private int size = 0;
    private byte[] buf = new byte[8192];

    public LengthCounter(int i) {
        this.maxsize = i;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        int i2 = this.size + 1;
        if (this.buf != null) {
            int i3 = this.maxsize;
            if (i2 <= i3 || i3 < 0) {
                byte[] bArr = this.buf;
                if (i2 > bArr.length) {
                    byte[] bArr2 = new byte[Math.max(bArr.length << 1, i2)];
                    System.arraycopy(this.buf, 0, bArr2, 0, this.size);
                    this.buf = bArr2;
                }
                this.buf[this.size] = (byte) i;
            } else {
                this.buf = null;
            }
        }
        this.size = i2;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i4 = this.size + i2;
            if (this.buf != null) {
                int i5 = this.maxsize;
                if (i4 <= i5 || i5 < 0) {
                    byte[] bArr2 = this.buf;
                    if (i4 > bArr2.length) {
                        byte[] bArr3 = new byte[Math.max(bArr2.length << 1, i4)];
                        System.arraycopy(this.buf, 0, bArr3, 0, this.size);
                        this.buf = bArr3;
                    }
                    System.arraycopy(bArr, i, this.buf, this.size, i2);
                } else {
                    this.buf = null;
                }
            }
            this.size = i4;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public int getSize() {
        return this.size;
    }

    public byte[] getBytes() {
        return this.buf;
    }
}
