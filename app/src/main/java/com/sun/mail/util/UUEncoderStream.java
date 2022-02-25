package com.sun.mail.util;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* loaded from: classes2.dex */
public class UUEncoderStream extends FilterOutputStream {
    private byte[] buffer;
    private int bufsize;
    protected int mode;
    protected String name;
    private boolean wrotePrefix;

    public UUEncoderStream(OutputStream outputStream) {
        this(outputStream, "encoder.buf", 644);
    }

    public UUEncoderStream(OutputStream outputStream, String str) {
        this(outputStream, str, 644);
    }

    public UUEncoderStream(OutputStream outputStream, String str, int i) {
        super(outputStream);
        this.bufsize = 0;
        this.wrotePrefix = false;
        this.name = str;
        this.mode = i;
        this.buffer = new byte[45];
    }

    public void setNameMode(String str, int i) {
        this.name = str;
        this.mode = i;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(bArr[i + i3]);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.buffer;
        int i2 = this.bufsize;
        this.bufsize = i2 + 1;
        bArr[i2] = (byte) i;
        if (this.bufsize == 45) {
            writePrefix();
            encode();
            this.bufsize = 0;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.bufsize > 0) {
            writePrefix();
            encode();
        }
        writeSuffix();
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
        this.out.close();
    }

    private void writePrefix() throws IOException {
        if (!this.wrotePrefix) {
            PrintStream printStream = new PrintStream(this.out);
            printStream.println("begin " + this.mode + ExpandableTextView.f6958c + this.name);
            printStream.flush();
            this.wrotePrefix = true;
        }
    }

    private void writeSuffix() throws IOException {
        PrintStream printStream = new PrintStream(this.out);
        printStream.println(" \nend");
        printStream.flush();
    }

    private void encode() throws IOException {
        byte b;
        this.out.write((this.bufsize & 63) + 32);
        int i = 0;
        while (true) {
            int i2 = this.bufsize;
            if (i >= i2) {
                this.out.write(10);
                return;
            }
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b2 = bArr[i];
            byte b3 = 1;
            if (i3 < i2) {
                int i4 = i3 + 1;
                b = bArr[i3];
                if (i4 < i2) {
                    i = i4 + 1;
                    b3 = bArr[i4];
                } else {
                    i = i4;
                }
            } else {
                i = i3;
                b = 1;
            }
            int i5 = ((b2 << 4) & 48) | ((b >>> 4) & 15);
            this.out.write(((b2 >>> 2) & 63) + 32);
            this.out.write(i5 + 32);
            this.out.write((((b << 2) & 60) | ((b3 >>> 6) & 3)) + 32);
            this.out.write((b3 & 63) + 32);
        }
    }
}
