package com.lody.virtual.helper.dedex;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DataReader implements Closeable {
    private ArrayList<DataReader> mAssociatedReaders;
    private final File mFile;
    private final MappedByteBuffer mMappedBuffer;
    private final RandomAccessFile mRaf;

    public DataReader(String str) throws Exception {
        this(new File(str));
    }

    public DataReader(File file) throws Exception {
        this.mFile = file;
        this.mRaf = new RandomAccessFile(this.mFile, "r");
        this.mMappedBuffer = this.mRaf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
        this.mMappedBuffer.rewind();
        setLittleEndian(true);
    }

    public void setLittleEndian(boolean z) {
        this.mMappedBuffer.order(z ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
    }

    public void seek(long j) {
        position((int) j);
    }

    public void position(int i) {
        this.mMappedBuffer.position(i);
    }

    public int position() {
        return this.mMappedBuffer.position();
    }

    public int readByte() {
        return this.mMappedBuffer.get() & 255;
    }

    public void readBytes(byte[] bArr) {
        this.mMappedBuffer.get(bArr, 0, bArr.length);
    }

    public void readBytes(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        readBytes(bArr);
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) bArr[i];
        }
    }

    public short readShort() {
        return this.mMappedBuffer.getShort();
    }

    public int readInt() {
        return this.mMappedBuffer.getInt();
    }

    public int previewInt() {
        this.mMappedBuffer.mark();
        int readInt = readInt();
        this.mMappedBuffer.reset();
        return readInt;
    }

    public final long readLong() {
        return this.mMappedBuffer.getLong();
    }

    public int readUleb128() {
        int readByte = readByte();
        if (readByte <= 127) {
            return readByte;
        }
        int readByte2 = readByte();
        int i = (readByte & 127) | ((readByte2 & 127) << 7);
        if (readByte2 <= 127) {
            return i;
        }
        int readByte3 = readByte();
        int i2 = i | ((readByte3 & 127) << 14);
        if (readByte3 <= 127) {
            return i2;
        }
        int readByte4 = readByte();
        int i3 = i2 | ((readByte4 & 127) << 21);
        return readByte4 > 127 ? i3 | (readByte() << 28) : i3;
    }

    public File getFile() {
        return this.mFile;
    }

    public FileChannel getChannel() {
        return this.mRaf.getChannel();
    }

    public void addAssociatedReader(DataReader dataReader) {
        if (this.mAssociatedReaders == null) {
            this.mAssociatedReaders = new ArrayList<>();
        }
        this.mAssociatedReaders.add(dataReader);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.mRaf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<DataReader> arrayList = this.mAssociatedReaders;
        if (arrayList != null) {
            Iterator<DataReader> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
        }
    }

    public static int toInt(String str) {
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
                i++;
                cArr[i] = charAt;
            }
        }
        if (i == 0) {
            return 0;
        }
        return Integer.parseInt(new String(cArr, 0, i));
    }
}
