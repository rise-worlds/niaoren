package org.apache.tools.tar;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.apache.tools.zip.ZipEncoding;
import org.apache.tools.zip.ZipEncodingHelper;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class TarOutputStream extends FilterOutputStream {
    private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding(HTTP.ASCII);
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_POSIX = 2;
    public static final int BIGNUMBER_STAR = 1;
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int LONGFILE_TRUNCATE = 1;
    private boolean addPaxHeadersForNonAsciiNames;
    protected byte[] assemBuf;
    protected int assemLen;
    private int bigNumberMode;
    protected TarBuffer buffer;
    private boolean closed;
    protected long currBytes;
    protected String currName;
    protected long currSize;
    protected boolean debug;
    private final ZipEncoding encoding;
    private boolean finished;
    private boolean haveUnclosedEntry;
    protected int longFileMode;
    protected byte[] oneBuf;
    protected byte[] recordBuf;

    public TarOutputStream(OutputStream outputStream) {
        this(outputStream, (int) TarBuffer.DEFAULT_BLKSIZE, 512);
    }

    public TarOutputStream(OutputStream outputStream, String str) {
        this(outputStream, TarBuffer.DEFAULT_BLKSIZE, 512, str);
    }

    public TarOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, 512);
    }

    public TarOutputStream(OutputStream outputStream, int i, String str) {
        this(outputStream, i, 512, str);
    }

    public TarOutputStream(OutputStream outputStream, int i, int i2) {
        this(outputStream, i, i2, null);
    }

    public TarOutputStream(OutputStream outputStream, int i, int i2, String str) {
        super(outputStream);
        this.longFileMode = 0;
        this.bigNumberMode = 0;
        this.closed = false;
        this.haveUnclosedEntry = false;
        this.finished = false;
        this.addPaxHeadersForNonAsciiNames = false;
        this.encoding = ZipEncodingHelper.getZipEncoding(str);
        this.buffer = new TarBuffer(outputStream, i, i2);
        this.debug = false;
        this.assemLen = 0;
        this.assemBuf = new byte[i2];
        this.recordBuf = new byte[i2];
        this.oneBuf = new byte[1];
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    public void setBigNumberMode(int i) {
        this.bigNumberMode = i;
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean z) {
        this.addPaxHeadersForNonAsciiNames = z;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void setBufferDebug(boolean z) {
        this.buffer.setDebug(z);
    }

    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        } else if (!this.haveUnclosedEntry) {
            writeEOFRecord();
            writeEOFRecord();
            this.buffer.flushBlock();
            this.finished = true;
        } else {
            throw new IOException("This archives contains unclosed entries.");
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        if (!this.closed) {
            this.buffer.close();
            this.out.close();
            this.closed = true;
        }
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    public void putNextEntry(TarEntry tarEntry) throws IOException {
        if (!this.finished) {
            HashMap hashMap = new HashMap();
            String name = tarEntry.getName();
            boolean handleLongName = handleLongName(tarEntry, name, hashMap, "path", TarConstants.LF_GNUTYPE_LONGNAME, "file name");
            String linkName = tarEntry.getLinkName();
            boolean z = false;
            boolean z2 = linkName != null && linkName.length() > 0 && handleLongName(tarEntry, linkName, hashMap, "linkpath", TarConstants.LF_GNUTYPE_LONGLINK, "link name");
            int i = this.bigNumberMode;
            if (i == 2) {
                addPaxHeadersForBigNumbers(hashMap, tarEntry);
            } else if (i != 1) {
                failForBigNumbers(tarEntry);
            }
            if (this.addPaxHeadersForNonAsciiNames && !handleLongName && !ASCII.canEncode(name)) {
                hashMap.put("path", name);
            }
            if (this.addPaxHeadersForNonAsciiNames && !z2 && ((tarEntry.isLink() || tarEntry.isSymbolicLink()) && !ASCII.canEncode(linkName))) {
                hashMap.put("linkpath", linkName);
            }
            if (hashMap.size() > 0) {
                writePaxHeaders(tarEntry, name, hashMap);
            }
            byte[] bArr = this.recordBuf;
            ZipEncoding zipEncoding = this.encoding;
            if (this.bigNumberMode == 1) {
                z = true;
            }
            tarEntry.writeEntryHeader(bArr, zipEncoding, z);
            this.buffer.writeRecord(this.recordBuf);
            this.currBytes = 0L;
            if (tarEntry.isDirectory()) {
                this.currSize = 0L;
            } else {
                this.currSize = tarEntry.getSize();
            }
            this.currName = name;
            this.haveUnclosedEntry = true;
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    public void closeEntry() throws IOException {
        byte[] bArr;
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        } else if (this.haveUnclosedEntry) {
            int i = this.assemLen;
            if (i > 0) {
                while (true) {
                    bArr = this.assemBuf;
                    if (i >= bArr.length) {
                        break;
                    }
                    bArr[i] = 0;
                    i++;
                }
                this.buffer.writeRecord(bArr);
                this.currBytes += this.assemLen;
                this.assemLen = 0;
            }
            if (this.currBytes >= this.currSize) {
                this.haveUnclosedEntry = false;
                return;
            }
            throw new IOException("entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
        } else {
            throw new IOException("No current entry to close");
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneBuf;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.currBytes + i2 <= this.currSize) {
            int i3 = this.assemLen;
            if (i3 > 0) {
                int i4 = i3 + i2;
                byte[] bArr2 = this.recordBuf;
                if (i4 >= bArr2.length) {
                    int length = bArr2.length - i3;
                    System.arraycopy(this.assemBuf, 0, bArr2, 0, i3);
                    System.arraycopy(bArr, i, this.recordBuf, this.assemLen, length);
                    this.buffer.writeRecord(this.recordBuf);
                    this.currBytes += this.recordBuf.length;
                    i += length;
                    i2 -= length;
                    this.assemLen = 0;
                } else {
                    System.arraycopy(bArr, i, this.assemBuf, i3, i2);
                    i += i2;
                    this.assemLen += i2;
                    i2 = 0;
                }
            }
            while (i2 > 0) {
                if (i2 < this.recordBuf.length) {
                    System.arraycopy(bArr, i, this.assemBuf, this.assemLen, i2);
                    this.assemLen += i2;
                    return;
                }
                this.buffer.writeRecord(bArr, i);
                int length2 = this.recordBuf.length;
                this.currBytes += length2;
                i2 -= length2;
                i += length2;
            }
            return;
        }
        throw new IOException("request to write '" + i2 + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
    }

    void writePaxHeaders(TarEntry tarEntry, String str, Map<String, String> map) throws IOException {
        String str2 = "./PaxHeaders.X/" + stripTo7Bits(str);
        if (str2.length() >= 100) {
            str2 = str2.substring(0, 99);
        }
        while (str2.endsWith("/")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        TarEntry tarEntry2 = new TarEntry(str2, (byte) TarConstants.LF_PAX_EXTENDED_HEADER_LC);
        transferModTime(tarEntry, tarEntry2);
        StringWriter stringWriter = new StringWriter();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int length = key.length() + value.length() + 3 + 2;
            String str3 = length + ExpandableTextView.f6958c + key + SimpleComparison.f23609c + value + "\n";
            int length2 = str3.getBytes("UTF-8").length;
            while (length != length2) {
                str3 = length2 + ExpandableTextView.f6958c + key + SimpleComparison.f23609c + value + "\n";
                length2 = str3.getBytes("UTF-8").length;
                length = length2;
            }
            stringWriter.write(str3);
        }
        byte[] bytes = stringWriter.toString().getBytes("UTF-8");
        tarEntry2.setSize(bytes.length);
        putNextEntry(tarEntry2);
        write(bytes);
        closeEntry();
    }

    private String stripTo7Bits(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = (char) (str.charAt(i) & 127);
            if (charAt != 0) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private void writeEOFRecord() throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr = this.recordBuf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.buffer.writeRecord(bArr);
                return;
            }
        }
    }

    private void addPaxHeadersForBigNumbers(Map<String, String> map, TarEntry tarEntry) {
        addPaxHeaderForBigNumber(map, "size", tarEntry.getSize(), TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "gid", tarEntry.getLongGroupId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "mtime", tarEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map, "uid", tarEntry.getLongUserId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "SCHILY.devmajor", tarEntry.getDevMajor(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map, "SCHILY.devminor", tarEntry.getDevMinor(), TarConstants.MAXID);
        failForBigNumber("mode", tarEntry.getMode(), TarConstants.MAXID);
    }

    private void addPaxHeaderForBigNumber(Map<String, String> map, String str, long j, long j2) {
        if (j < 0 || j > j2) {
            map.put(str, String.valueOf(j));
        }
    }

    private void failForBigNumbers(TarEntry tarEntry) {
        failForBigNumber("entry size", tarEntry.getSize(), TarConstants.MAXSIZE);
        failForBigNumberWithPosixMessage("group id", tarEntry.getLongGroupId(), TarConstants.MAXID);
        failForBigNumber("last modification time", tarEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        failForBigNumber("user id", tarEntry.getLongUserId(), TarConstants.MAXID);
        failForBigNumber("mode", tarEntry.getMode(), TarConstants.MAXID);
        failForBigNumber("major device number", tarEntry.getDevMajor(), TarConstants.MAXID);
        failForBigNumber("minor device number", tarEntry.getDevMinor(), TarConstants.MAXID);
    }

    private void failForBigNumber(String str, long j, long j2) {
        failForBigNumber(str, j, j2, "");
    }

    private void failForBigNumberWithPosixMessage(String str, long j, long j2) {
        failForBigNumber(str, j, j2, " Use STAR or POSIX extensions to overcome this limit");
    }

    private void failForBigNumber(String str, long j, long j2, String str2) {
        if (j < 0 || j > j2) {
            throw new RuntimeException(str + " '" + j + "' is too big ( > " + j2 + " )");
        }
    }

    private boolean handleLongName(TarEntry tarEntry, String str, Map<String, String> map, String str2, byte b, String str3) throws IOException {
        ByteBuffer encode = this.encoding.encode(str);
        int limit = encode.limit() - encode.position();
        if (limit >= 100) {
            int i = this.longFileMode;
            if (i == 3) {
                map.put(str2, str);
                return true;
            } else if (i == 2) {
                TarEntry tarEntry2 = new TarEntry(TarConstants.GNU_LONGLINK, b);
                tarEntry2.setSize(limit + 1);
                transferModTime(tarEntry, tarEntry2);
                putNextEntry(tarEntry2);
                write(encode.array(), encode.arrayOffset(), limit);
                write(0);
                closeEntry();
            } else if (i != 1) {
                throw new RuntimeException(str3 + " '" + str + "' is too long ( > 100 bytes)");
            }
        }
        return false;
    }

    private void transferModTime(TarEntry tarEntry, TarEntry tarEntry2) {
        Date modTime = tarEntry.getModTime();
        long time = modTime.getTime() / 1000;
        if (time < 0 || time > TarConstants.MAXSIZE) {
            modTime = new Date(0L);
        }
        tarEntry2.setModTime(modTime);
    }
}
