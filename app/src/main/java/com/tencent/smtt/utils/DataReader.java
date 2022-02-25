package com.tencent.smtt.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.tencent.smtt.utils.c */
/* loaded from: classes2.dex */
public class DataReader implements Closeable {

    /* renamed from: a */
    private final RandomAccessFile f13281a;

    /* renamed from: b */
    private final File f13282b;

    /* renamed from: c */
    private final byte[] f13283c;

    /* renamed from: d */
    private boolean f13284d;

    public DataReader(String str) throws FileNotFoundException {
        this(new File(str));
    }

    public DataReader(File file) throws FileNotFoundException {
        this.f13283c = new byte[8];
        this.f13282b = file;
        this.f13281a = new RandomAccessFile(this.f13282b, "r");
    }

    /* renamed from: a */
    public void m16494a(boolean z) {
        this.f13284d = z;
    }

    /* renamed from: a */
    public void m16495a(long j) throws IOException {
        this.f13281a.seek(j);
    }

    /* renamed from: a */
    public final int m16493a(byte[] bArr) throws IOException {
        return this.f13281a.read(bArr);
    }

    /* renamed from: a */
    public final int m16492a(char[] cArr) throws IOException {
        byte[] bArr = new byte[cArr.length];
        int read = this.f13281a.read(bArr);
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) bArr[i];
        }
        return read;
    }

    /* renamed from: a */
    public final short m16496a() throws IOException {
        short readShort = this.f13281a.readShort();
        if (!this.f13284d) {
            return readShort;
        }
        return (short) (((readShort & 65280) >>> 8) | ((readShort & 255) << 8));
    }

    /* renamed from: b */
    public final int m16491b() throws IOException {
        int readInt = this.f13281a.readInt();
        if (!this.f13284d) {
            return readInt;
        }
        return ((readInt & (-16777216)) >>> 24) | ((readInt & 255) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >>> 8);
    }

    /* renamed from: c */
    public final long m16490c() throws IOException {
        if (!this.f13284d) {
            return this.f13281a.readLong();
        }
        this.f13281a.readFully(this.f13283c, 0, 8);
        byte[] bArr = this.f13283c;
        return ((bArr[1] & 255) << 8) | (bArr[7] << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | (bArr[0] & 255);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.f13281a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
