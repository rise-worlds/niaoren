package org.apache.tools.tar;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.tools.zip.ZipEncoding;
import org.apache.tools.zip.ZipEncodingHelper;

/* loaded from: classes2.dex */
public class TarInputStream extends FilterInputStream {
    private static final int BUFFER_SIZE = 8192;
    private static final int BYTE_MASK = 255;
    private static final int LARGE_BUFFER_SIZE = 32768;
    private static final int SMALL_BUFFER_SIZE = 256;
    private final byte[] SKIP_BUF;
    private final byte[] SMALL_BUF;
    protected TarBuffer buffer;
    protected TarEntry currEntry;
    protected boolean debug;
    private final ZipEncoding encoding;
    protected long entryOffset;
    protected long entrySize;
    protected boolean hasHitEOF;
    protected byte[] oneBuf;
    protected byte[] readBuf;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
    }

    public TarInputStream(InputStream inputStream) {
        this(inputStream, (int) TarBuffer.DEFAULT_BLKSIZE, 512);
    }

    public TarInputStream(InputStream inputStream, String str) {
        this(inputStream, TarBuffer.DEFAULT_BLKSIZE, 512, str);
    }

    public TarInputStream(InputStream inputStream, int i) {
        this(inputStream, i, 512);
    }

    public TarInputStream(InputStream inputStream, int i, String str) {
        this(inputStream, i, 512, str);
    }

    public TarInputStream(InputStream inputStream, int i, int i2) {
        this(inputStream, i, i2, null);
    }

    public TarInputStream(InputStream inputStream, int i, int i2, String str) {
        super(inputStream);
        this.SKIP_BUF = new byte[8192];
        this.SMALL_BUF = new byte[256];
        this.buffer = new TarBuffer(inputStream, i, i2);
        this.readBuf = null;
        this.oneBuf = new byte[1];
        this.debug = false;
        this.hasHitEOF = false;
        this.encoding = ZipEncodingHelper.getZipEncoding(str);
    }

    public void setDebug(boolean z) {
        this.debug = z;
        this.buffer.setDebug(z);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buffer.close();
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        long j = this.entrySize;
        long j2 = this.entryOffset;
        if (j - j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (j - j2);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0) {
            byte[] bArr = this.SKIP_BUF;
            int read = read(this.SKIP_BUF, 0, (int) (j2 > ((long) bArr.length) ? bArr.length : j2));
            if (read == -1) {
                break;
            }
            j2 -= read;
        }
        return j - j2;
    }

    public TarEntry getNextEntry() throws IOException {
        if (this.hasHitEOF) {
            return null;
        }
        if (this.currEntry != null) {
            long j = this.entrySize - this.entryOffset;
            if (this.debug) {
                PrintStream printStream = System.err;
                printStream.println("TarInputStream: SKIP currENTRY '" + this.currEntry.getName() + "' SZ " + this.entrySize + " OFF " + this.entryOffset + "  skipping " + j + " bytes");
            }
            while (j > 0) {
                long skip = skip(j);
                if (skip > 0) {
                    j -= skip;
                } else {
                    throw new RuntimeException("failed to skip current tar entry");
                }
            }
            this.readBuf = null;
        }
        byte[] record = getRecord();
        if (this.hasHitEOF) {
            this.currEntry = null;
            return null;
        }
        try {
            this.currEntry = new TarEntry(record, this.encoding);
            if (this.debug) {
                PrintStream printStream2 = System.err;
                printStream2.println("TarInputStream: SET CURRENTRY '" + this.currEntry.getName() + "' size = " + this.currEntry.getSize());
            }
            this.entryOffset = 0L;
            this.entrySize = this.currEntry.getSize();
            if (this.currEntry.isGNULongLinkEntry()) {
                byte[] longNameData = getLongNameData();
                if (longNameData == null) {
                    return null;
                }
                this.currEntry.setLinkName(this.encoding.decode(longNameData));
            }
            if (this.currEntry.isGNULongNameEntry()) {
                byte[] longNameData2 = getLongNameData();
                if (longNameData2 == null) {
                    return null;
                }
                this.currEntry.setName(this.encoding.decode(longNameData2));
            }
            if (this.currEntry.isPaxHeader()) {
                paxHeaders();
            }
            if (this.currEntry.isGNUSparse()) {
                readGNUSparse();
            }
            this.entrySize = this.currEntry.getSize();
            return this.currEntry;
        } catch (IllegalArgumentException e) {
            IOException iOException = new IOException("Error detected parsing the header");
            iOException.initCause(e);
            throw iOException;
        }
    }

    protected byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = read(this.SMALL_BUF);
            if (read < 0) {
                break;
            }
            byteArrayOutputStream.write(this.SMALL_BUF, 0, read);
        }
        getNextEntry();
        if (this.currEntry == null) {
            return null;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        while (length > 0 && byteArray[length - 1] == 0) {
            length--;
        }
        if (length == byteArray.length) {
            return byteArray;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 0, bArr, 0, length);
        return bArr;
    }

    private byte[] getRecord() throws IOException {
        if (this.hasHitEOF) {
            return null;
        }
        byte[] readRecord = this.buffer.readRecord();
        if (readRecord == null) {
            if (this.debug) {
                System.err.println("READ NULL RECORD");
            }
            this.hasHitEOF = true;
        } else if (this.buffer.isEOFRecord(readRecord)) {
            if (this.debug) {
                System.err.println("READ EOF RECORD");
            }
            this.hasHitEOF = true;
        }
        if (this.hasHitEOF) {
            return null;
        }
        return readRecord;
    }

    private void paxHeaders() throws IOException {
        Map<String, String> parsePaxHeaders = parsePaxHeaders(this);
        getNextEntry();
        applyPaxHeadersToCurrentEntry(parsePaxHeaders);
    }

    Map<String, String> parsePaxHeaders(InputStream inputStream) throws IOException {
        int read;
        HashMap hashMap = new HashMap();
        do {
            int i = 0;
            int i2 = 0;
            while (true) {
                read = inputStream.read();
                if (read != -1) {
                    i++;
                    if (read == 32) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read2 = inputStream.read();
                            if (read2 == -1) {
                                read = read2;
                                continue;
                                break;
                            }
                            i++;
                            if (read2 == 61) {
                                String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                                int i3 = i2 - i;
                                byte[] bArr = new byte[i3];
                                read = read2;
                                int i4 = 0;
                                while (i4 < i3) {
                                    read = inputStream.read();
                                    if (read == -1) {
                                        break;
                                    }
                                    i4++;
                                    bArr[i4] = (byte) read;
                                }
                                if (i4 == i3) {
                                    hashMap.put(byteArrayOutputStream2, new String(bArr, 0, i3 - 1, "UTF-8"));
                                    continue;
                                } else {
                                    throw new IOException("Failed to read Paxheader. Expected " + i3 + " bytes, read " + i4);
                                }
                            } else {
                                byteArrayOutputStream.write((byte) read2);
                            }
                        }
                    } else {
                        i2 = (i2 * 10) + (read - 48);
                    }
                }
            }
        } while (read != -1);
        return hashMap;
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("path".equals(key)) {
                this.currEntry.setName(value);
            } else if ("linkpath".equals(key)) {
                this.currEntry.setLinkName(value);
            } else if ("gid".equals(key)) {
                this.currEntry.setGroupId(Long.parseLong(value));
            } else if ("gname".equals(key)) {
                this.currEntry.setGroupName(value);
            } else if ("uid".equals(key)) {
                this.currEntry.setUserId(Long.parseLong(value));
            } else if ("uname".equals(key)) {
                this.currEntry.setUserName(value);
            } else if ("size".equals(key)) {
                this.currEntry.setSize(Long.parseLong(value));
            } else if ("mtime".equals(key)) {
                this.currEntry.setModTime((long) (Double.parseDouble(value) * 1000.0d));
            } else if ("SCHILY.devminor".equals(key)) {
                this.currEntry.setDevMinor(Integer.parseInt(value));
            } else if ("SCHILY.devmajor".equals(key)) {
                this.currEntry.setDevMajor(Integer.parseInt(value));
            }
        }
    }

    private void readGNUSparse() throws IOException {
        byte[] record;
        if (this.currEntry.isExtended()) {
            do {
                record = getRecord();
                if (this.hasHitEOF) {
                    this.currEntry = null;
                    return;
                }
            } while (new TarArchiveSparseEntry(record).isExtended());
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneBuf, 0, 1) == -1) {
            return -1;
        }
        return this.oneBuf[0] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        long j = this.entryOffset;
        long j2 = this.entrySize;
        if (j >= j2) {
            return -1;
        }
        if (i2 + j > j2) {
            i2 = (int) (j2 - j);
        }
        byte[] bArr2 = this.readBuf;
        if (bArr2 != null) {
            int length = i2 > bArr2.length ? bArr2.length : i2;
            System.arraycopy(this.readBuf, 0, bArr, i, length);
            byte[] bArr3 = this.readBuf;
            if (length >= bArr3.length) {
                this.readBuf = null;
            } else {
                int length2 = bArr3.length - length;
                byte[] bArr4 = new byte[length2];
                System.arraycopy(bArr3, length, bArr4, 0, length2);
                this.readBuf = bArr4;
            }
            i3 = length + 0;
            i2 -= length;
            i += length;
        } else {
            i3 = 0;
        }
        while (i2 > 0) {
            byte[] readRecord = this.buffer.readRecord();
            if (readRecord != null) {
                int length3 = readRecord.length;
                if (length3 > i2) {
                    System.arraycopy(readRecord, 0, bArr, i, i2);
                    int i4 = length3 - i2;
                    this.readBuf = new byte[i4];
                    System.arraycopy(readRecord, i2, this.readBuf, 0, i4);
                    length3 = i2;
                } else {
                    System.arraycopy(readRecord, 0, bArr, i, length3);
                }
                i3 += length3;
                i2 -= length3;
                i += length3;
            } else {
                throw new IOException("unexpected EOF with " + i2 + " bytes unread");
            }
        }
        this.entryOffset += i3;
        return i3;
    }

    public void copyEntryContents(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[32768];
        while (true) {
            int read = read(bArr, 0, bArr.length);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public boolean canReadEntryData(TarEntry tarEntry) {
        return !tarEntry.isGNUSparse();
    }
}
