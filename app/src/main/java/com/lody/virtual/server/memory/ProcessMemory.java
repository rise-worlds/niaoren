package com.lody.virtual.server.memory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

/* loaded from: classes.dex */
public class ProcessMemory {
    private RandomAccessFile memFile;
    private int pid;

    public ProcessMemory(int i) throws IOException {
        this.pid = i;
        this.memFile = new RandomAccessFile(String.format(Locale.ENGLISH, "/proc/%d/mem", Integer.valueOf(i)), "rw");
    }

    public void write(long j, byte[] bArr) throws IOException {
        this.memFile.seek(j);
        this.memFile.write(bArr);
    }

    public int read(long j, byte[] bArr, int i) throws IOException {
        this.memFile.seek(j);
        return this.memFile.read(bArr, 0, i);
    }

    public void close() throws IOException {
        this.memFile.close();
    }
}
