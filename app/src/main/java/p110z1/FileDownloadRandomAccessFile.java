package p110z1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import p110z1.FileDownloadHelper;

/* renamed from: z1.ahq */
/* loaded from: classes3.dex */
public class FileDownloadRandomAccessFile implements FileDownloadOutputStream {

    /* renamed from: a */
    private final BufferedOutputStream f15834a;

    /* renamed from: b */
    private final FileDescriptor f15835b;

    /* renamed from: c */
    private final RandomAccessFile f15836c;

    FileDownloadRandomAccessFile(File file) throws IOException {
        this.f15836c = new RandomAccessFile(file, "rw");
        this.f15835b = this.f15836c.getFD();
        this.f15834a = new BufferedOutputStream(new FileOutputStream(this.f15836c.getFD()));
    }

    @Override // p110z1.FileDownloadOutputStream
    /* renamed from: a */
    public void mo13249a(byte[] bArr, int i, int i2) throws IOException {
        this.f15834a.write(bArr, i, i2);
    }

    @Override // p110z1.FileDownloadOutputStream
    /* renamed from: a */
    public void mo13251a() throws IOException {
        this.f15834a.flush();
        this.f15835b.sync();
    }

    @Override // p110z1.FileDownloadOutputStream
    /* renamed from: b */
    public void mo13248b() throws IOException {
        this.f15834a.close();
        this.f15836c.close();
    }

    @Override // p110z1.FileDownloadOutputStream
    /* renamed from: a */
    public void mo13250a(long j) throws IOException {
        this.f15836c.seek(j);
    }

    @Override // p110z1.FileDownloadOutputStream
    /* renamed from: b */
    public void mo13247b(long j) throws IOException {
        this.f15836c.setLength(j);
    }

    /* compiled from: FileDownloadRandomAccessFile.java */
    /* renamed from: z1.ahq$a */
    /* loaded from: classes3.dex */
    public static class C3475a implements FileDownloadHelper.AbstractC3481e {
        @Override // p110z1.FileDownloadHelper.AbstractC3481e
        /* renamed from: a */
        public boolean mo13219a() {
            return true;
        }

        @Override // p110z1.FileDownloadHelper.AbstractC3481e
        /* renamed from: a */
        public FileDownloadOutputStream mo13218a(File file) throws IOException {
            return new FileDownloadRandomAccessFile(file);
        }
    }
}
