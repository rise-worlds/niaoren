package com.cyjh.ddy.thirdlib.lib_hwobs;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

/* loaded from: classes.dex */
public class UploadFileInputStream extends FileInputStream {

    /* renamed from: a */
    private long f7577a;

    /* renamed from: b */
    private long f7578b;

    /* renamed from: c */
    private OnReadListener f7579c;

    /* renamed from: d */
    private long f7580d;

    /* renamed from: e */
    private long f7581e;

    /* loaded from: classes.dex */
    public interface OnReadListener {
        void onRead(long j, long j2);
    }

    public UploadFileInputStream(File file) {
        super(file);
    }

    public UploadFileInputStream(FileDescriptor fileDescriptor) {
        super(fileDescriptor);
    }

    public UploadFileInputStream(String str) {
        super(str);
    }

    /* renamed from: a */
    private void m21364a(int i) {
        this.f7577a += i;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f7580d;
        long j2 = currentTimeMillis - j;
        if (j == 0) {
            this.f7580d = System.currentTimeMillis();
        }
        if (j2 > 0) {
            this.f7578b = (this.f7577a * 1000) / j2;
        }
        OnReadListener onReadListener = this.f7579c;
        if (onReadListener != null && currentTimeMillis - this.f7581e > 500) {
            onReadListener.onRead(this.f7577a, this.f7578b);
            this.f7581e = currentTimeMillis;
        }
    }

    public long getReadedSize() {
        return this.f7577a;
    }

    public long getSpeedByte() {
        return this.f7578b;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() {
        int read = super.read();
        m21364a(read);
        return read;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        int read = super.read(bArr);
        m21364a(read);
        return read;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        m21364a(read);
        return read;
    }

    public void setReadListener(OnReadListener onReadListener) {
        this.f7579c = onReadListener;
    }
}
