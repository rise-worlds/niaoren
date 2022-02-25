package com.google.protobuf;

import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import p110z1.cin;

/* loaded from: classes.dex */
public final class CodedInputStream {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private int sizeLimit;
    private int totalBytesRetired;

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        CodedInputStream codedInputStream = new CodedInputStream(bArr, i, i2);
        try {
            codedInputStream.pushLimit(i2);
            return codedInputStream;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = readRawVarint32();
        if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public final void checkLastTagWas(int i) throws InvalidProtocolBufferException {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public final boolean skipField(int i) throws IOException {
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                readInt32();
                return true;
            case 1:
                readRawLittleEndian64();
                return true;
            case 2:
                skipRawBytes(readRawVarint32());
                return true;
            case 3:
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                readRawLittleEndian32();
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public final void skipMessage() throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag));
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public final long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public final long readInt64() throws IOException {
        return readRawVarint64();
    }

    public final int readInt32() throws IOException {
        return readRawVarint32();
    }

    public final long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public final int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public final boolean readBool() throws IOException {
        return readRawVarint32() != 0;
    }

    public final String readString() throws IOException {
        int readRawVarint32 = readRawVarint32();
        int i = this.bufferSize;
        int i2 = this.bufferPos;
        if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
            return new String(readRawBytes(readRawVarint32), "UTF-8");
        }
        String str = new String(this.buffer, i2, readRawVarint32, "UTF-8");
        this.bufferPos += readRawVarint32;
        return str;
    }

    public final void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int i2 = this.recursionDepth;
        if (i2 < this.recursionLimit) {
            this.recursionDepth = i2 + 1;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int i2 = this.recursionDepth;
        if (i2 < this.recursionLimit) {
            this.recursionDepth = i2 + 1;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return parsePartialFrom;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    @Deprecated
    public final void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
        readGroup(i, builder, (ExtensionRegistryLite) null);
    }

    public final void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return parsePartialFrom;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public final ByteString readBytes() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 == 0) {
            return ByteString.EMPTY;
        }
        int i = this.bufferSize;
        int i2 = this.bufferPos;
        if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
            return ByteString.copyFrom(readRawBytes(readRawVarint32));
        }
        ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
        this.bufferPos += readRawVarint32;
        return copyFrom;
    }

    public final int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public final int readEnum() throws IOException {
        return readRawVarint32();
    }

    public final int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public final long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public final int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public final long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    public final int readRawVarint32() throws IOException {
        byte readRawByte = readRawByte();
        if (readRawByte >= 0) {
            return readRawByte;
        }
        int i = readRawByte & cin.f20710b;
        byte readRawByte2 = readRawByte();
        if (readRawByte2 >= 0) {
            return i | (readRawByte2 << 7);
        }
        int i2 = i | ((readRawByte2 & cin.f20710b) << 7);
        byte readRawByte3 = readRawByte();
        if (readRawByte3 >= 0) {
            return i2 | (readRawByte3 << 14);
        }
        int i3 = i2 | ((readRawByte3 & cin.f20710b) << 14);
        byte readRawByte4 = readRawByte();
        if (readRawByte4 >= 0) {
            return i3 | (readRawByte4 << 21);
        }
        int i4 = i3 | ((readRawByte4 & cin.f20710b) << 21);
        byte readRawByte5 = readRawByte();
        int i5 = i4 | (readRawByte5 << 28);
        if (readRawByte5 >= 0) {
            return i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            if (readRawByte() >= 0) {
                return i5;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i2 |= (read & 127) << i3;
                if ((read & 128) == 0) {
                    return i2;
                }
                i3 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read2 & 128) == 0) {
                return i2;
            } else {
                i3 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public final long readRawVarint64() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readRawByte = readRawByte();
            j |= (readRawByte & cin.f20710b) << i;
            if ((readRawByte & cin.f20709a) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public final int readRawLittleEndian32() throws IOException {
        return (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24);
    }

    public final long readRawLittleEndian64() throws IOException {
        byte readRawByte = readRawByte();
        byte readRawByte2 = readRawByte();
        return ((readRawByte2 & 255) << 8) | (readRawByte & 255) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48) | ((readRawByte() & 255) << 56);
    }

    private CodedInputStream(byte[] bArr, int i, int i2) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.buffer = bArr;
        this.bufferSize = i2 + i;
        this.bufferPos = i;
        this.totalBytesRetired = -i;
        this.input = null;
    }

    private CodedInputStream(InputStream inputStream) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
    }

    public final int setRecursionLimit(int i) {
        if (i >= 0) {
            int i2 = this.recursionLimit;
            this.recursionLimit = i;
            return i2;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
    }

    public final int setSizeLimit(int i) {
        if (i >= 0) {
            int i2 = this.sizeLimit;
            this.sizeLimit = i;
            return i2;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + i);
    }

    public final void resetSizeCounter() {
        this.totalBytesRetired = -this.bufferPos;
    }

    public final int pushLimit(int i) throws InvalidProtocolBufferException {
        if (i >= 0) {
            int i2 = i + this.totalBytesRetired + this.bufferPos;
            int i3 = this.currentLimit;
            if (i2 <= i3) {
                this.currentLimit = i2;
                recomputeBufferSizeAfterLimit();
                return i3;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int i = this.totalBytesRetired;
        int i2 = this.bufferSize;
        int i3 = i + i2;
        int i4 = this.currentLimit;
        if (i3 > i4) {
            this.bufferSizeAfterLimit = i3 - i4;
            this.bufferSize = i2 - this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public final void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public final int getBytesUntilLimit() {
        int i = this.currentLimit;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - (this.totalBytesRetired + this.bufferPos);
    }

    public final boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !refillBuffer(false);
    }

    public final int getTotalBytesRead() {
        return this.totalBytesRetired + this.bufferPos;
    }

    private boolean refillBuffer(boolean z) throws IOException {
        int i = this.bufferPos;
        int i2 = this.bufferSize;
        if (i >= i2) {
            int i3 = this.totalBytesRetired;
            if (i3 + i2 != this.currentLimit) {
                this.totalBytesRetired = i3 + i2;
                this.bufferPos = 0;
                InputStream inputStream = this.input;
                this.bufferSize = inputStream == null ? -1 : inputStream.read(this.buffer);
                int i4 = this.bufferSize;
                if (i4 == 0 || i4 < -1) {
                    throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy.");
                } else if (i4 == -1) {
                    this.bufferSize = 0;
                    if (!z) {
                        return false;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                } else {
                    recomputeBufferSizeAfterLimit();
                    int i5 = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
                    if (i5 <= this.sizeLimit && i5 >= 0) {
                        return true;
                    }
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
            } else if (!z) {
                return false;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        } else {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
    }

    public final byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(true);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public final byte[] readRawBytes(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.totalBytesRetired;
            int i3 = this.bufferPos;
            int i4 = i2 + i3 + i;
            int i5 = this.currentLimit;
            if (i4 <= i5) {
                int i6 = this.bufferSize;
                if (i <= i6 - i3) {
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.buffer, i3, bArr, 0, i);
                    this.bufferPos += i;
                    return bArr;
                } else if (i < 4096) {
                    byte[] bArr2 = new byte[i];
                    int i7 = i6 - i3;
                    System.arraycopy(this.buffer, i3, bArr2, 0, i7);
                    this.bufferPos = this.bufferSize;
                    refillBuffer(true);
                    while (true) {
                        int i8 = i - i7;
                        int i9 = this.bufferSize;
                        if (i8 <= i9) {
                            System.arraycopy(this.buffer, 0, bArr2, i7, i8);
                            this.bufferPos = i8;
                            return bArr2;
                        }
                        System.arraycopy(this.buffer, 0, bArr2, i7, i9);
                        int i10 = this.bufferSize;
                        i7 += i10;
                        this.bufferPos = i10;
                        refillBuffer(true);
                    }
                } else {
                    this.totalBytesRetired = i2 + i6;
                    this.bufferPos = 0;
                    this.bufferSize = 0;
                    int i11 = i6 - i3;
                    int i12 = i - i11;
                    ArrayList<byte[]> arrayList = new ArrayList();
                    while (i12 > 0) {
                        byte[] bArr3 = new byte[Math.min(i12, 4096)];
                        int i13 = 0;
                        while (i13 < bArr3.length) {
                            InputStream inputStream = this.input;
                            int read = inputStream == null ? -1 : inputStream.read(bArr3, i13, bArr3.length - i13);
                            if (read != -1) {
                                this.totalBytesRetired += read;
                                i13 += read;
                            } else {
                                throw InvalidProtocolBufferException.truncatedMessage();
                            }
                        }
                        i12 -= bArr3.length;
                        arrayList.add(bArr3);
                    }
                    byte[] bArr4 = new byte[i];
                    System.arraycopy(this.buffer, i3, bArr4, 0, i11);
                    for (byte[] bArr5 : arrayList) {
                        System.arraycopy(bArr5, 0, bArr4, i11, bArr5.length);
                        i11 += bArr5.length;
                    }
                    return bArr4;
                }
            } else {
                skipRawBytes((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        } else {
            throw InvalidProtocolBufferException.negativeSize();
        }
    }

    public final void skipRawBytes(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.totalBytesRetired;
            int i3 = this.bufferPos;
            int i4 = i2 + i3 + i;
            int i5 = this.currentLimit;
            if (i4 <= i5) {
                int i6 = this.bufferSize;
                if (i <= i6 - i3) {
                    this.bufferPos = i3 + i;
                    return;
                }
                int i7 = i6 - i3;
                this.bufferPos = i6;
                refillBuffer(true);
                while (true) {
                    int i8 = i - i7;
                    int i9 = this.bufferSize;
                    if (i8 <= i9) {
                        this.bufferPos = i8;
                        return;
                    }
                    i7 += i9;
                    this.bufferPos = i9;
                    refillBuffer(true);
                }
            } else {
                skipRawBytes((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        } else {
            throw InvalidProtocolBufferException.negativeSize();
        }
    }
}
