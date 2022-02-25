package org.apache.tools.zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipException;

/* loaded from: classes2.dex */
public class ZipOutputStream extends FilterOutputStream {
    private static final int BUFFER_SIZE = 512;
    private static final int CFH_COMMENT_LENGTH_OFFSET = 32;
    private static final int CFH_COMPRESSED_SIZE_OFFSET = 20;
    private static final int CFH_CRC_OFFSET = 16;
    private static final int CFH_DISK_NUMBER_OFFSET = 34;
    private static final int CFH_EXTERNAL_ATTRIBUTES_OFFSET = 38;
    private static final int CFH_EXTRA_LENGTH_OFFSET = 30;
    private static final int CFH_FILENAME_LENGTH_OFFSET = 28;
    private static final int CFH_FILENAME_OFFSET = 46;
    private static final int CFH_GPB_OFFSET = 8;
    private static final int CFH_INTERNAL_ATTRIBUTES_OFFSET = 36;
    private static final int CFH_LFH_OFFSET = 42;
    private static final int CFH_METHOD_OFFSET = 10;
    private static final int CFH_ORIGINAL_SIZE_OFFSET = 24;
    private static final int CFH_SIG_OFFSET = 0;
    private static final int CFH_TIME_OFFSET = 12;
    private static final int CFH_VERSION_MADE_BY_OFFSET = 4;
    private static final int CFH_VERSION_NEEDED_OFFSET = 6;
    public static final int DEFAULT_COMPRESSION = -1;
    static final String DEFAULT_ENCODING = null;
    public static final int DEFLATED = 8;
    private static final int DEFLATER_BLOCK_SIZE = 8192;
    @Deprecated
    public static final int EFS_FLAG = 2048;
    private static final int LFH_COMPRESSED_SIZE_OFFSET = 18;
    private static final int LFH_CRC_OFFSET = 14;
    private static final int LFH_EXTRA_LENGTH_OFFSET = 28;
    private static final int LFH_FILENAME_LENGTH_OFFSET = 26;
    private static final int LFH_FILENAME_OFFSET = 30;
    private static final int LFH_GPB_OFFSET = 6;
    private static final int LFH_METHOD_OFFSET = 8;
    private static final int LFH_ORIGINAL_SIZE_OFFSET = 22;
    private static final int LFH_SIG_OFFSET = 0;
    private static final int LFH_TIME_OFFSET = 10;
    private static final int LFH_VERSION_NEEDED_OFFSET = 4;
    public static final int STORED = 0;
    protected byte[] buf;
    private final Calendar calendarInstance;
    private long cdLength;
    private long cdOffset;
    private String comment;
    private final CRC32 crc;
    private UnicodeExtraFieldPolicy createUnicodeExtraFields;
    protected final Deflater def;
    private String encoding;
    private final List<ZipEntry> entries;
    private CurrentEntry entry;
    private boolean fallbackToUTF8;
    private boolean finished;
    private boolean hasCompressionLevelChanged;
    private boolean hasUsedZip64;
    private int level;
    private int method;
    private final Map<ZipEntry, Long> offsets;
    private final RandomAccessFile raf;
    private boolean useUTF8Flag;
    private long written;
    private Zip64Mode zip64Mode;
    private ZipEncoding zipEncoding;
    private static final byte[] EMPTY = new byte[0];
    private static final byte[] ZERO = {0, 0};
    private static final byte[] LZERO = {0, 0, 0, 0};
    private static final byte[] ONE = ZipLong.getBytes(1);
    protected static final byte[] LFH_SIG = ZipLong.LFH_SIG.getBytes();
    protected static final byte[] DD_SIG = ZipLong.DD_SIG.getBytes();
    protected static final byte[] CFH_SIG = ZipLong.CFH_SIG.getBytes();
    protected static final byte[] EOCD_SIG = ZipLong.getBytes(101010256);
    static final byte[] ZIP64_EOCD_SIG = ZipLong.getBytes(101075792);
    static final byte[] ZIP64_EOCD_LOC_SIG = ZipLong.getBytes(117853008);

    public ZipOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.finished = false;
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new LinkedList();
        this.crc = new CRC32();
        this.written = 0L;
        this.cdOffset = 0L;
        this.cdLength = 0L;
        this.offsets = new HashMap();
        this.encoding = null;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.def = new Deflater(this.level, true);
        this.buf = new byte[512];
        this.useUTF8Flag = true;
        this.fallbackToUTF8 = false;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.hasUsedZip64 = false;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.calendarInstance = Calendar.getInstance();
        this.raf = null;
    }

    public ZipOutputStream(File file) throws IOException {
        super(null);
        RandomAccessFile randomAccessFile;
        this.finished = false;
        this.comment = "";
        this.level = -1;
        this.hasCompressionLevelChanged = false;
        this.method = 8;
        this.entries = new LinkedList();
        this.crc = new CRC32();
        this.written = 0L;
        this.cdOffset = 0L;
        this.cdLength = 0L;
        this.offsets = new HashMap();
        this.encoding = null;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(DEFAULT_ENCODING);
        this.def = new Deflater(this.level, true);
        this.buf = new byte[512];
        this.useUTF8Flag = true;
        this.fallbackToUTF8 = false;
        this.createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
        this.hasUsedZip64 = false;
        this.zip64Mode = Zip64Mode.AsNeeded;
        this.calendarInstance = Calendar.getInstance();
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.setLength(0L);
            } catch (IOException unused) {
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused2) {
                    }
                    randomAccessFile = null;
                }
                this.out = new FileOutputStream(file);
                this.raf = randomAccessFile;
            }
        } catch (IOException unused3) {
            randomAccessFile = null;
        }
        this.raf = randomAccessFile;
    }

    public boolean isSeekable() {
        return this.raf != null;
    }

    public void setEncoding(String str) {
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        if (this.useUTF8Flag && !ZipEncodingHelper.isUTF8(str)) {
            this.useUTF8Flag = false;
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setUseLanguageEncodingFlag(boolean z) {
        this.useUTF8Flag = z && ZipEncodingHelper.isUTF8(this.encoding);
    }

    public void setCreateUnicodeExtraFields(UnicodeExtraFieldPolicy unicodeExtraFieldPolicy) {
        this.createUnicodeExtraFields = unicodeExtraFieldPolicy;
    }

    public void setFallbackToUTF8(boolean z) {
        this.fallbackToUTF8 = z;
    }

    public void setUseZip64(Zip64Mode zip64Mode) {
        this.zip64Mode = zip64Mode;
    }

    public void finish() throws IOException {
        if (!this.finished) {
            if (this.entry != null) {
                closeEntry();
            }
            this.cdOffset = this.written;
            writeCentralDirectoryInChunks();
            this.cdLength = this.written - this.cdOffset;
            writeZip64CentralDirectory();
            writeCentralDirectoryEnd();
            this.offsets.clear();
            this.entries.clear();
            this.def.end();
            this.finished = true;
            return;
        }
        throw new IOException("This archive has already been finished");
    }

    private void writeCentralDirectoryInChunks() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(70000);
        int i = 0;
        for (ZipEntry zipEntry : this.entries) {
            byteArrayOutputStream.write(createCentralFileHeader(zipEntry));
            i++;
            if (i > 1000) {
                writeCounted(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
                i = 0;
            }
        }
        writeCounted(byteArrayOutputStream.toByteArray());
    }

    public void closeEntry() throws IOException {
        preClose();
        flushDeflater();
        Zip64Mode effectiveZip64Mode = getEffectiveZip64Mode(this.entry.entry);
        long j = this.written - this.entry.dataStart;
        long value = this.crc.getValue();
        this.crc.reset();
        closeEntry(handleSizesAndCrc(j, value, effectiveZip64Mode));
    }

    private void closeEntry(boolean z) throws IOException {
        if (this.raf != null) {
            rewriteSizesAndCrc(z);
        }
        writeDataDescriptor(this.entry.entry);
        this.entry = null;
    }

    private void preClose() throws IOException {
        if (!this.finished) {
            CurrentEntry currentEntry = this.entry;
            if (currentEntry == null) {
                throw new IOException("No current entry to close");
            } else if (!currentEntry.hasWritten) {
                write(EMPTY, 0, 0);
            }
        } else {
            throw new IOException("Stream has already been finished");
        }
    }

    private void flushDeflater() throws IOException {
        if (this.entry.entry.getMethod() == 8) {
            this.def.finish();
            while (!this.def.finished()) {
                deflate();
            }
        }
    }

    private boolean handleSizesAndCrc(long j, long j2, Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() == 8) {
            this.entry.entry.setSize(this.entry.bytesRead);
            this.entry.entry.setCompressedSize(j);
            this.entry.entry.setCrc(j2);
            this.def.reset();
        } else if (this.raf != null) {
            this.entry.entry.setSize(j);
            this.entry.entry.setCompressedSize(j);
            this.entry.entry.setCrc(j2);
        } else if (this.entry.entry.getCrc() != j2) {
            throw new ZipException("bad CRC checksum for entry " + this.entry.entry.getName() + ": " + Long.toHexString(this.entry.entry.getCrc()) + " instead of " + Long.toHexString(j2));
        } else if (this.entry.entry.getSize() != j) {
            throw new ZipException("bad size for entry " + this.entry.entry.getName() + ": " + this.entry.entry.getSize() + " instead of " + j);
        }
        return checkIfNeedsZip64(zip64Mode);
    }

    private boolean checkIfNeedsZip64(Zip64Mode zip64Mode) throws ZipException {
        boolean isZip64Required = isZip64Required(this.entry.entry, zip64Mode);
        if (!isZip64Required || zip64Mode != Zip64Mode.Never) {
            return isZip64Required;
        }
        throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
    }

    private boolean isZip64Required(ZipEntry zipEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || isTooLageForZip32(zipEntry);
    }

    private boolean isTooLageForZip32(ZipEntry zipEntry) {
        return zipEntry.getSize() >= 4294967295L || zipEntry.getCompressedSize() >= 4294967295L;
    }

    private void rewriteSizesAndCrc(boolean z) throws IOException {
        long filePointer = this.raf.getFilePointer();
        this.raf.seek(this.entry.localDataStart);
        writeOut(ZipLong.getBytes(this.entry.entry.getCrc()));
        if (!hasZip64Extra(this.entry.entry) || !z) {
            writeOut(ZipLong.getBytes(this.entry.entry.getCompressedSize()));
            writeOut(ZipLong.getBytes(this.entry.entry.getSize()));
        } else {
            writeOut(ZipLong.ZIP64_MAGIC.getBytes());
            writeOut(ZipLong.ZIP64_MAGIC.getBytes());
        }
        if (hasZip64Extra(this.entry.entry)) {
            this.raf.seek(this.entry.localDataStart + 12 + 4 + getName(this.entry.entry).limit() + 4);
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getSize()));
            writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getCompressedSize()));
            if (!z) {
                this.raf.seek(this.entry.localDataStart - 10);
                writeOut(ZipShort.getBytes(10));
                this.entry.entry.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
                this.entry.entry.setExtra();
                if (this.entry.causedUseOfZip64) {
                    this.hasUsedZip64 = false;
                }
            }
        }
        this.raf.seek(filePointer);
    }

    public void putNextEntry(ZipEntry zipEntry) throws IOException {
        if (!this.finished) {
            if (this.entry != null) {
                closeEntry();
            }
            this.entry = new CurrentEntry(zipEntry);
            this.entries.add(this.entry.entry);
            setDefaults(this.entry.entry);
            Zip64Mode effectiveZip64Mode = getEffectiveZip64Mode(this.entry.entry);
            validateSizeInformation(effectiveZip64Mode);
            if (shouldAddZip64Extra(this.entry.entry, effectiveZip64Mode)) {
                Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(this.entry.entry);
                ZipEightByteInteger zipEightByteInteger = ZipEightByteInteger.ZERO;
                ZipEightByteInteger zipEightByteInteger2 = ZipEightByteInteger.ZERO;
                if (this.entry.entry.getMethod() == 0 && this.entry.entry.getSize() != -1) {
                    zipEightByteInteger = new ZipEightByteInteger(this.entry.entry.getSize());
                    zipEightByteInteger2 = zipEightByteInteger;
                }
                zip64Extra.setSize(zipEightByteInteger);
                zip64Extra.setCompressedSize(zipEightByteInteger2);
                this.entry.entry.setExtra();
            }
            if (this.entry.entry.getMethod() == 8 && this.hasCompressionLevelChanged) {
                this.def.setLevel(this.level);
                this.hasCompressionLevelChanged = false;
            }
            writeLocalFileHeader(this.entry.entry);
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    private void setDefaults(ZipEntry zipEntry) {
        if (zipEntry.getMethod() == -1) {
            zipEntry.setMethod(this.method);
        }
        if (zipEntry.getTime() == -1) {
            zipEntry.setTime(System.currentTimeMillis());
        }
    }

    private void validateSizeInformation(Zip64Mode zip64Mode) throws ZipException {
        if (this.entry.entry.getMethod() == 0 && this.raf == null) {
            if (this.entry.entry.getSize() == -1) {
                throw new ZipException("uncompressed size is required for STORED method when not writing to a file");
            } else if (this.entry.entry.getCrc() != -1) {
                this.entry.entry.setCompressedSize(this.entry.entry.getSize());
            } else {
                throw new ZipException("crc checksum is required for STORED method when not writing to a file");
            }
        }
        if ((this.entry.entry.getSize() >= 4294967295L || this.entry.entry.getCompressedSize() >= 4294967295L) && zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
    }

    private boolean shouldAddZip64Extra(ZipEntry zipEntry, Zip64Mode zip64Mode) {
        return zip64Mode == Zip64Mode.Always || zipEntry.getSize() >= 4294967295L || zipEntry.getCompressedSize() >= 4294967295L || !(zipEntry.getSize() != -1 || this.raf == null || zip64Mode == Zip64Mode.Never);
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setLevel(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid compression level: " + i);
        }
        this.hasCompressionLevelChanged = this.level != i;
        this.level = i;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    public boolean canWriteEntryData(ZipEntry zipEntry) {
        return ZipUtil.canHandleEntryData(zipEntry);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry != null) {
            ZipUtil.checkRequestedFeatures(currentEntry.entry);
            this.entry.hasWritten = true;
            if (this.entry.entry.getMethod() == 8) {
                writeDeflated(bArr, i, i2);
            } else {
                writeCounted(bArr, i, i2);
            }
            this.crc.update(bArr, i, i2);
            return;
        }
        throw new IllegalStateException("No current entry");
    }

    private void writeCounted(byte[] bArr) throws IOException {
        writeCounted(bArr, 0, bArr.length);
    }

    private void writeCounted(byte[] bArr, int i, int i2) throws IOException {
        writeOut(bArr, i, i2);
        this.written += i2;
    }

    private void writeDeflated(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0 && !this.def.finished()) {
            CurrentEntry.access$314(this.entry, i2);
            if (i2 <= 8192) {
                this.def.setInput(bArr, i, i2);
                deflateUntilInputIsNeeded();
                return;
            }
            int i3 = i2 / 8192;
            for (int i4 = 0; i4 < i3; i4++) {
                this.def.setInput(bArr, (i4 * 8192) + i, 8192);
                deflateUntilInputIsNeeded();
            }
            int i5 = i3 * 8192;
            if (i5 < i2) {
                this.def.setInput(bArr, i + i5, i2 - i5);
                deflateUntilInputIsNeeded();
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
        destroy();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.out != null) {
            this.out.flush();
        }
    }

    protected final void deflate() throws IOException {
        Deflater deflater = this.def;
        byte[] bArr = this.buf;
        int deflate = deflater.deflate(bArr, 0, bArr.length);
        if (deflate > 0) {
            writeCounted(this.buf, 0, deflate);
        }
    }

    protected void writeLocalFileHeader(ZipEntry zipEntry) throws IOException {
        boolean canEncode = this.zipEncoding.canEncode(zipEntry.getName());
        ByteBuffer name = getName(zipEntry);
        if (this.createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER) {
            addUnicodeExtraFields(zipEntry, canEncode, name);
        }
        byte[] createLocalFileHeader = createLocalFileHeader(zipEntry, name, canEncode);
        long j = this.written;
        this.offsets.put(zipEntry, Long.valueOf(j));
        this.entry.localDataStart = j + 14;
        writeCounted(createLocalFileHeader);
        this.entry.dataStart = this.written;
    }

    private byte[] createLocalFileHeader(ZipEntry zipEntry, ByteBuffer byteBuffer, boolean z) {
        byte[] localFileDataExtra = zipEntry.getLocalFileDataExtra();
        int limit = byteBuffer.limit() - byteBuffer.position();
        int i = limit + 30;
        byte[] bArr = new byte[localFileDataExtra.length + i];
        System.arraycopy(LFH_SIG, 0, bArr, 0, 4);
        int method = zipEntry.getMethod();
        ZipShort.putShort(versionNeededToExtract(method, hasZip64Extra(zipEntry)), bArr, 4);
        getGeneralPurposeBits(method, !z && this.fallbackToUTF8).encode(bArr, 6);
        ZipShort.putShort(method, bArr, 8);
        ZipUtil.toDosTime(this.calendarInstance, zipEntry.getTime(), bArr, 10);
        if (method == 8 || this.raf != null) {
            System.arraycopy(LZERO, 0, bArr, 14, 4);
        } else {
            ZipLong.putLong(zipEntry.getCrc(), bArr, 14);
        }
        if (hasZip64Extra(this.entry.entry)) {
            ZipLong.ZIP64_MAGIC.putLong(bArr, 18);
            ZipLong.ZIP64_MAGIC.putLong(bArr, 22);
        } else if (method == 8 || this.raf != null) {
            System.arraycopy(LZERO, 0, bArr, 18, 4);
            System.arraycopy(LZERO, 0, bArr, 22, 4);
        } else {
            ZipLong.putLong(zipEntry.getSize(), bArr, 18);
            ZipLong.putLong(zipEntry.getSize(), bArr, 22);
        }
        ZipShort.putShort(limit, bArr, 26);
        ZipShort.putShort(localFileDataExtra.length, bArr, 28);
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 30, limit);
        System.arraycopy(localFileDataExtra, 0, bArr, i, localFileDataExtra.length);
        return bArr;
    }

    private void addUnicodeExtraFields(ZipEntry zipEntry, boolean z, ByteBuffer byteBuffer) throws IOException {
        if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !z) {
            zipEntry.addExtraField(new UnicodePathExtraField(zipEntry.getName(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position()));
        }
        String comment = zipEntry.getComment();
        if (comment != null && !"".equals(comment)) {
            boolean canEncode = this.zipEncoding.canEncode(comment);
            if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !canEncode) {
                ByteBuffer encode = getEntryEncoding(zipEntry).encode(comment);
                zipEntry.addExtraField(new UnicodeCommentExtraField(comment, encode.array(), encode.arrayOffset(), encode.limit() - encode.position()));
            }
        }
    }

    protected void writeDataDescriptor(ZipEntry zipEntry) throws IOException {
        if (zipEntry.getMethod() == 8 && this.raf == null) {
            writeCounted(DD_SIG);
            writeCounted(ZipLong.getBytes(zipEntry.getCrc()));
            if (!hasZip64Extra(zipEntry)) {
                writeCounted(ZipLong.getBytes(zipEntry.getCompressedSize()));
                writeCounted(ZipLong.getBytes(zipEntry.getSize()));
                return;
            }
            writeCounted(ZipEightByteInteger.getBytes(zipEntry.getCompressedSize()));
            writeCounted(ZipEightByteInteger.getBytes(zipEntry.getSize()));
        }
    }

    protected void writeCentralFileHeader(ZipEntry zipEntry) throws IOException {
        writeCounted(createCentralFileHeader(zipEntry));
    }

    private byte[] createCentralFileHeader(ZipEntry zipEntry) throws IOException {
        long longValue = this.offsets.get(zipEntry).longValue();
        boolean z = hasZip64Extra(zipEntry) || zipEntry.getCompressedSize() >= 4294967295L || zipEntry.getSize() >= 4294967295L || longValue >= 4294967295L;
        if (!z || this.zip64Mode != Zip64Mode.Never) {
            handleZip64Extra(zipEntry, longValue, z);
            return createCentralFileHeader(zipEntry, getName(zipEntry), longValue, z);
        }
        throw new Zip64RequiredException("archive's size exceeds the limit of 4GByte.");
    }

    private byte[] createCentralFileHeader(ZipEntry zipEntry, ByteBuffer byteBuffer, long j, boolean z) throws IOException {
        byte[] centralDirectoryExtra = zipEntry.getCentralDirectoryExtra();
        String comment = zipEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        ByteBuffer encode = getEntryEncoding(zipEntry).encode(comment);
        int limit = byteBuffer.limit() - byteBuffer.position();
        int limit2 = encode.limit() - encode.position();
        int i = limit + 46;
        byte[] bArr = new byte[centralDirectoryExtra.length + i + limit2];
        System.arraycopy(CFH_SIG, 0, bArr, 0, 4);
        ZipShort.putShort((zipEntry.getPlatform() << 8) | (!this.hasUsedZip64 ? 20 : 45), bArr, 4);
        int method = zipEntry.getMethod();
        boolean canEncode = this.zipEncoding.canEncode(zipEntry.getName());
        ZipShort.putShort(versionNeededToExtract(method, z), bArr, 6);
        getGeneralPurposeBits(method, !canEncode && this.fallbackToUTF8).encode(bArr, 8);
        ZipShort.putShort(method, bArr, 10);
        ZipUtil.toDosTime(this.calendarInstance, zipEntry.getTime(), bArr, 12);
        ZipLong.putLong(zipEntry.getCrc(), bArr, 16);
        if (zipEntry.getCompressedSize() >= 4294967295L || zipEntry.getSize() >= 4294967295L) {
            ZipLong.ZIP64_MAGIC.putLong(bArr, 20);
            ZipLong.ZIP64_MAGIC.putLong(bArr, 24);
        } else {
            ZipLong.putLong(zipEntry.getCompressedSize(), bArr, 20);
            ZipLong.putLong(zipEntry.getSize(), bArr, 24);
        }
        ZipShort.putShort(limit, bArr, 28);
        ZipShort.putShort(centralDirectoryExtra.length, bArr, 30);
        ZipShort.putShort(limit2, bArr, 32);
        System.arraycopy(ZERO, 0, bArr, 34, 2);
        ZipShort.putShort(zipEntry.getInternalAttributes(), bArr, 36);
        ZipLong.putLong(zipEntry.getExternalAttributes(), bArr, 38);
        ZipLong.putLong(Math.min(j, 4294967295L), bArr, 42);
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 46, limit);
        System.arraycopy(centralDirectoryExtra, 0, bArr, i, centralDirectoryExtra.length);
        System.arraycopy(encode.array(), encode.arrayOffset(), bArr, i + limit2, limit2);
        return bArr;
    }

    private void handleZip64Extra(ZipEntry zipEntry, long j, boolean z) {
        if (z) {
            Zip64ExtendedInformationExtraField zip64Extra = getZip64Extra(zipEntry);
            if (zipEntry.getCompressedSize() >= 4294967295L || zipEntry.getSize() >= 4294967295L) {
                zip64Extra.setCompressedSize(new ZipEightByteInteger(zipEntry.getCompressedSize()));
                zip64Extra.setSize(new ZipEightByteInteger(zipEntry.getSize()));
            } else {
                zip64Extra.setCompressedSize(null);
                zip64Extra.setSize(null);
            }
            if (j >= 4294967295L) {
                zip64Extra.setRelativeHeaderOffset(new ZipEightByteInteger(j));
            }
            zipEntry.setExtra();
        }
    }

    protected void writeCentralDirectoryEnd() throws IOException {
        writeCounted(EOCD_SIG);
        writeCounted(ZERO);
        writeCounted(ZERO);
        int size = this.entries.size();
        if (size > 65535 && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException("archive contains more than 65535 entries.");
        } else if (this.cdOffset <= 4294967295L || this.zip64Mode != Zip64Mode.Never) {
            byte[] bytes = ZipShort.getBytes(Math.min(size, 65535));
            writeCounted(bytes);
            writeCounted(bytes);
            writeCounted(ZipLong.getBytes(Math.min(this.cdLength, 4294967295L)));
            writeCounted(ZipLong.getBytes(Math.min(this.cdOffset, 4294967295L)));
            ByteBuffer encode = this.zipEncoding.encode(this.comment);
            int limit = encode.limit() - encode.position();
            writeCounted(ZipShort.getBytes(limit));
            writeCounted(encode.array(), encode.arrayOffset(), limit);
        } else {
            throw new Zip64RequiredException("archive's size exceeds the limit of 4GByte.");
        }
    }

    @Deprecated
    protected static ZipLong toDosTime(Date date) {
        return ZipUtil.toDosTime(date);
    }

    @Deprecated
    protected static byte[] toDosTime(long j) {
        return ZipUtil.toDosTime(j);
    }

    protected byte[] getBytes(String str) throws ZipException {
        try {
            ByteBuffer encode = ZipEncodingHelper.getZipEncoding(this.encoding).encode(str);
            byte[] bArr = new byte[encode.limit()];
            System.arraycopy(encode.array(), encode.arrayOffset(), bArr, 0, bArr.length);
            return bArr;
        } catch (IOException e) {
            throw new ZipException("Failed to encode name: " + e.getMessage());
        }
    }

    protected void writeZip64CentralDirectory() throws IOException {
        if (this.zip64Mode != Zip64Mode.Never) {
            if (!this.hasUsedZip64 && (this.cdOffset >= 4294967295L || this.cdLength >= 4294967295L || this.entries.size() >= 65535)) {
                this.hasUsedZip64 = true;
            }
            if (this.hasUsedZip64) {
                long j = this.written;
                writeOut(ZIP64_EOCD_SIG);
                writeOut(ZipEightByteInteger.getBytes(44L));
                writeOut(ZipShort.getBytes(45));
                writeOut(ZipShort.getBytes(45));
                writeOut(LZERO);
                writeOut(LZERO);
                byte[] bytes = ZipEightByteInteger.getBytes(this.entries.size());
                writeOut(bytes);
                writeOut(bytes);
                writeOut(ZipEightByteInteger.getBytes(this.cdLength));
                writeOut(ZipEightByteInteger.getBytes(this.cdOffset));
                writeOut(ZIP64_EOCD_LOC_SIG);
                writeOut(LZERO);
                writeOut(ZipEightByteInteger.getBytes(j));
                writeOut(ONE);
            }
        }
    }

    protected final void writeOut(byte[] bArr) throws IOException {
        writeOut(bArr, 0, bArr.length);
    }

    protected final void writeOut(byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.write(bArr, i, i2);
        } else {
            this.out.write(bArr, i, i2);
        }
    }

    @Deprecated
    protected static long adjustToLong(int i) {
        return ZipUtil.adjustToLong(i);
    }

    private void deflateUntilInputIsNeeded() throws IOException {
        while (!this.def.needsInput()) {
            deflate();
        }
    }

    private GeneralPurposeBit getGeneralPurposeBits(int i, boolean z) {
        GeneralPurposeBit generalPurposeBit = new GeneralPurposeBit();
        generalPurposeBit.useUTF8ForNames(this.useUTF8Flag || z);
        if (isDeflatedToOutputStream(i)) {
            generalPurposeBit.useDataDescriptor(true);
        }
        return generalPurposeBit;
    }

    private int versionNeededToExtract(int i, boolean z) {
        if (z) {
            return 45;
        }
        return isDeflatedToOutputStream(i) ? 20 : 10;
    }

    private boolean isDeflatedToOutputStream(int i) {
        return i == 8 && this.raf == null;
    }

    private Zip64ExtendedInformationExtraField getZip64Extra(ZipEntry zipEntry) {
        CurrentEntry currentEntry = this.entry;
        if (currentEntry != null) {
            currentEntry.causedUseOfZip64 = !this.hasUsedZip64;
        }
        this.hasUsedZip64 = true;
        Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) zipEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (zip64ExtendedInformationExtraField == null) {
            zip64ExtendedInformationExtraField = new Zip64ExtendedInformationExtraField();
        }
        zipEntry.addAsFirstExtraField(zip64ExtendedInformationExtraField);
        return zip64ExtendedInformationExtraField;
    }

    private boolean hasZip64Extra(ZipEntry zipEntry) {
        return zipEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID) != null;
    }

    private Zip64Mode getEffectiveZip64Mode(ZipEntry zipEntry) {
        if (this.zip64Mode == Zip64Mode.AsNeeded && this.raf == null && zipEntry.getMethod() == 8 && zipEntry.getSize() == -1) {
            return Zip64Mode.Never;
        }
        return this.zip64Mode;
    }

    private ZipEncoding getEntryEncoding(ZipEntry zipEntry) {
        return (this.zipEncoding.canEncode(zipEntry.getName()) || !this.fallbackToUTF8) ? this.zipEncoding : ZipEncodingHelper.UTF8_ZIP_ENCODING;
    }

    private ByteBuffer getName(ZipEntry zipEntry) throws IOException {
        return getEntryEncoding(zipEntry).encode(zipEntry.getName());
    }

    void destroy() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        if (this.out != null) {
            this.out.close();
        }
    }

    /* loaded from: classes2.dex */
    public static final class UnicodeExtraFieldPolicy {
        public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy("always");
        public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
        public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE = new UnicodeExtraFieldPolicy("not encodeable");
        private final String name;

        private UnicodeExtraFieldPolicy(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CurrentEntry {
        private long bytesRead;
        private boolean causedUseOfZip64;
        private long dataStart;
        private final ZipEntry entry;
        private boolean hasWritten;
        private long localDataStart;

        static /* synthetic */ long access$314(CurrentEntry currentEntry, long j) {
            long j2 = currentEntry.bytesRead + j;
            currentEntry.bytesRead = j2;
            return j2;
        }

        private CurrentEntry(ZipEntry zipEntry) {
            this.localDataStart = 0L;
            this.dataStart = 0L;
            this.bytesRead = 0L;
            this.causedUseOfZip64 = false;
            this.entry = zipEntry;
        }
    }
}
