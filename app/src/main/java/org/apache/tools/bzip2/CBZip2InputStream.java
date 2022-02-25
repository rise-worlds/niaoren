package org.apache.tools.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class CBZip2InputStream extends InputStream implements BZip2Constants {
    private static final int EOF = 0;
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int START_BLOCK_STATE = 1;
    private boolean blockRandomised;
    private int blockSize100k;
    private int bsBuff;
    private int bsLive;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    private final CRC crc;
    private int currentChar;
    private int currentState;
    private Data data;
    private final boolean decompressConcatenated;

    /* renamed from: in */
    private InputStream f14783in;
    private int last;
    private int nInUse;
    private int origPtr;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    private int su_ch2;
    private int su_chPrev;
    private int su_count;
    private int su_i2;
    private int su_j2;
    private int su_rNToGo;
    private int su_rTPos;
    private int su_tPos;
    private char su_z;

    public CBZip2InputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public CBZip2InputStream(InputStream inputStream, boolean z) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        this.currentState = 1;
        this.f14783in = inputStream;
        this.decompressConcatenated = z;
        init(true);
        initBlock();
        setupBlock();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.f14783in != null) {
            return read0();
        }
        throw new IOException("stream closed");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > dest.length(" + bArr.length + ").");
            } else if (this.f14783in != null) {
                int i4 = i;
                while (i4 < i3) {
                    int read0 = read0();
                    if (read0 < 0) {
                        break;
                    }
                    i4++;
                    bArr[i4] = (byte) read0;
                }
                if (i4 == i) {
                    return -1;
                }
                return i4 - i;
            } else {
                throw new IOException("stream closed");
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void makeMaps() {
        boolean[] zArr = this.data.inUse;
        byte[] bArr = this.data.seqToUnseq;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                i++;
                bArr[i] = (byte) i2;
            }
        }
        this.nInUse = i;
    }

    private int read0() throws IOException {
        int i = this.currentChar;
        switch (this.currentState) {
            case 0:
                return -1;
            case 1:
                throw new IllegalStateException();
            case 2:
                throw new IllegalStateException();
            case 3:
                setupRandPartB();
                break;
            case 4:
                setupRandPartC();
                break;
            case 5:
                throw new IllegalStateException();
            case 6:
                setupNoRandPartB();
                break;
            case 7:
                setupNoRandPartC();
                break;
            default:
                throw new IllegalStateException();
        }
        return i;
    }

    private boolean init(boolean z) throws IOException {
        InputStream inputStream = this.f14783in;
        if (inputStream != null) {
            if (!z) {
                int read = inputStream.read();
                if (read == -1) {
                    return false;
                }
                int read2 = this.f14783in.read();
                if (!(read == 66 && read2 == 90)) {
                    throw new IOException("Garbage after a valid BZip2 stream");
                }
            } else if (inputStream.available() == 0) {
                throw new IOException("Empty InputStream");
            }
            if (this.f14783in.read() != 104) {
                throw new IOException(z ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
            }
            int read3 = this.f14783in.read();
            if (read3 < 49 || read3 > 57) {
                throw new IOException("Stream is not BZip2 formatted: illegal blocksize " + ((char) read3));
            }
            this.blockSize100k = read3 - 48;
            this.bsLive = 0;
            this.computedCombinedCRC = 0;
            return true;
        }
        throw new IOException("No InputStream");
    }

    private void initBlock() throws IOException {
        do {
            char bsGetUByte = bsGetUByte();
            char bsGetUByte2 = bsGetUByte();
            char bsGetUByte3 = bsGetUByte();
            char bsGetUByte4 = bsGetUByte();
            char bsGetUByte5 = bsGetUByte();
            char bsGetUByte6 = bsGetUByte();
            if (bsGetUByte != 23 || bsGetUByte2 != 'r' || bsGetUByte3 != 'E' || bsGetUByte4 != '8' || bsGetUByte5 != 'P' || bsGetUByte6 != 144) {
                boolean z = false;
                if (bsGetUByte == '1' && bsGetUByte2 == 'A' && bsGetUByte3 == 'Y' && bsGetUByte4 == '&' && bsGetUByte5 == 'S' && bsGetUByte6 == 'Y') {
                    this.storedBlockCRC = bsGetInt();
                    if (bsR(1) == 1) {
                        z = true;
                    }
                    this.blockRandomised = z;
                    if (this.data == null) {
                        this.data = new Data(this.blockSize100k);
                    }
                    getAndMoveToFrontDecode();
                    this.crc.initialiseCRC();
                    this.currentState = 1;
                    return;
                }
                this.currentState = 0;
                throw new IOException("bad block header");
            }
        } while (!complete());
    }

    private void endBlock() throws IOException {
        this.computedBlockCRC = this.crc.getFinalCRC();
        int i = this.storedBlockCRC;
        if (i != this.computedBlockCRC) {
            int i2 = this.storedCombinedCRC;
            this.computedCombinedCRC = (i2 >>> 31) | (i2 << 1);
            this.computedCombinedCRC = i ^ this.computedCombinedCRC;
            reportCRCError();
        }
        int i3 = this.computedCombinedCRC;
        this.computedCombinedCRC = (i3 >>> 31) | (i3 << 1);
        this.computedCombinedCRC ^= this.computedBlockCRC;
    }

    private boolean complete() throws IOException {
        this.storedCombinedCRC = bsGetInt();
        this.currentState = 0;
        this.data = null;
        if (this.storedCombinedCRC != this.computedCombinedCRC) {
            reportCRCError();
        }
        return !this.decompressConcatenated || !init(false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.apache.tools.bzip2.CBZip2InputStream$Data, java.io.InputStream] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close() throws java.io.IOException {
        /*
            r3 = this;
            java.io.InputStream r0 = r3.f14783in
            if (r0 == 0) goto L_0x0017
            r1 = 0
            java.io.InputStream r2 = java.lang.System.in     // Catch: all -> 0x0011
            if (r0 == r2) goto L_0x000c
            r0.close()     // Catch: all -> 0x0011
        L_0x000c:
            r3.data = r1
            r3.f14783in = r1
            goto L_0x0017
        L_0x0011:
            r0 = move-exception
            r3.data = r1
            r3.f14783in = r1
            throw r0
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2InputStream.close():void");
    }

    private int bsR(int i) throws IOException {
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        if (i2 < i) {
            InputStream inputStream = this.f14783in;
            do {
                int read = inputStream.read();
                if (read >= 0) {
                    i3 = (i3 << 8) | read;
                    i2 += 8;
                } else {
                    throw new IOException("unexpected end of stream");
                }
            } while (i2 < i);
            this.bsBuff = i3;
        }
        int i4 = i2 - i;
        this.bsLive = i4;
        return ((1 << i) - 1) & (i3 >> i4);
    }

    private boolean bsGetBit() throws IOException {
        int i = this.bsLive;
        int i2 = this.bsBuff;
        if (i < 1) {
            int read = this.f14783in.read();
            if (read >= 0) {
                i2 = (i2 << 8) | read;
                i += 8;
                this.bsBuff = i2;
            } else {
                throw new IOException("unexpected end of stream");
            }
        }
        int i3 = i - 1;
        this.bsLive = i3;
        return ((i2 >> i3) & 1) != 0;
    }

    private char bsGetUByte() throws IOException {
        return (char) bsR(8);
    }

    private int bsGetInt() throws IOException {
        return bsR(8) | (((((bsR(8) << 8) | bsR(8)) << 8) | bsR(8)) << 8);
    }

    private static void hbCreateDecodeTables(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        while (i5 <= i2) {
            for (int i7 = 0; i7 < i3; i7++) {
                if (cArr[i7] == i5) {
                    i6++;
                    iArr3[i6] = i7;
                }
            }
            i5++;
            i6 = i6;
        }
        int i8 = 23;
        while (true) {
            i8--;
            if (i8 <= 0) {
                break;
            }
            iArr2[i8] = 0;
            iArr[i8] = 0;
        }
        for (int i9 = 0; i9 < i3; i9++) {
            int i10 = cArr[i9] + 1;
            iArr2[i10] = iArr2[i10] + 1;
        }
        int i11 = iArr2[0];
        for (int i12 = 1; i12 < 23; i12++) {
            i11 += iArr2[i12];
            iArr2[i12] = i11;
        }
        int i13 = iArr2[i];
        int i14 = i;
        while (i14 <= i2) {
            int i15 = i14 + 1;
            int i16 = iArr2[i15];
            int i17 = i4 + (i16 - i13);
            iArr[i14] = i17 - 1;
            i4 = i17 << 1;
            i14 = i15;
            i13 = i16;
        }
        for (int i18 = i + 1; i18 <= i2; i18++) {
            iArr2[i18] = ((iArr[i18 - 1] + 1) << 1) - iArr2[i18];
        }
    }

    private void recvDecodingTables() throws IOException {
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.recvDecodingTables_pos;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.selectorMtf;
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            if (bsGetBit()) {
                i |= 1 << i2;
            }
        }
        int i3 = 256;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            zArr[i3] = false;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            if (((1 << i4) & i) != 0) {
                int i5 = i4 << 4;
                for (int i6 = 0; i6 < 16; i6++) {
                    if (bsGetBit()) {
                        zArr[i5 + i6] = true;
                    }
                }
            }
        }
        makeMaps();
        int i7 = this.nInUse + 2;
        int bsR = bsR(3);
        int bsR2 = bsR(15);
        for (int i8 = 0; i8 < bsR2; i8++) {
            int i9 = 0;
            while (bsGetBit()) {
                i9++;
            }
            bArr3[i8] = (byte) i9;
        }
        int i10 = bsR;
        while (true) {
            i10--;
            if (i10 < 0) {
                break;
            }
            bArr[i10] = (byte) i10;
        }
        for (int i11 = 0; i11 < bsR2; i11++) {
            int i12 = bArr3[i11] & 255;
            byte b = bArr[i12];
            while (i12 > 0) {
                bArr[i12] = bArr[i12 - 1];
                i12--;
            }
            bArr[0] = b;
            bArr2[i11] = b;
        }
        char[][] cArr = data.temp_charArray2d;
        for (int i13 = 0; i13 < bsR; i13++) {
            int bsR3 = bsR(5);
            char[] cArr2 = cArr[i13];
            int i14 = bsR3;
            for (int i15 = 0; i15 < i7; i15++) {
                while (bsGetBit()) {
                    i14 += bsGetBit() ? -1 : 1;
                }
                cArr2[i15] = (char) i14;
            }
        }
        createHuffmanDecodingTables(i7, bsR);
    }

    private void createHuffmanDecodingTables(int i, int i2) {
        Data data = this.data;
        char[][] cArr = data.temp_charArray2d;
        int[] iArr = data.minLens;
        int[][] iArr2 = data.limit;
        int[][] iArr3 = data.base;
        int[][] iArr4 = data.perm;
        for (int i3 = 0; i3 < i2; i3++) {
            char[] cArr2 = cArr[i3];
            int i4 = i;
            char c = 0;
            int i5 = 32;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    char c2 = cArr2[i4];
                    if (c2 > c) {
                        c = c2;
                    }
                    if (c2 < i5) {
                        i5 = c2;
                    }
                }
            }
            hbCreateDecodeTables(iArr2[i3], iArr3[i3], iArr4[i3], cArr[i3], i5, c, i);
            iArr[i3] = i5;
        }
    }

    private void getAndMoveToFrontDecode() throws IOException {
        int i;
        char c;
        int i2;
        CBZip2InputStream cBZip2InputStream = this;
        cBZip2InputStream.origPtr = cBZip2InputStream.bsR(24);
        recvDecodingTables();
        InputStream inputStream = cBZip2InputStream.f14783in;
        Data data = cBZip2InputStream.data;
        byte[] bArr = data.ll8;
        int[] iArr = data.unzftab;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.seqToUnseq;
        char[] cArr = data.getAndMoveToFrontDecode_yy;
        int[] iArr2 = data.minLens;
        int[][] iArr3 = data.limit;
        int[][] iArr4 = data.base;
        int[][] iArr5 = data.perm;
        int i3 = cBZip2InputStream.blockSize100k * 100000;
        int i4 = 256;
        while (true) {
            i4--;
            if (i4 < 0) {
                break;
            }
            cArr[i4] = (char) i4;
            iArr[i4] = 0;
        }
        int i5 = cBZip2InputStream.nInUse + 1;
        int andMoveToFrontDecode0 = cBZip2InputStream.getAndMoveToFrontDecode0(0);
        int i6 = cBZip2InputStream.bsBuff;
        int i7 = cBZip2InputStream.bsLive;
        int i8 = bArr2[0] & 255;
        int[] iArr6 = iArr4[i8];
        int[] iArr7 = iArr3[i8];
        int i9 = i7;
        int i10 = i6;
        int[] iArr8 = iArr5[i8];
        int i11 = -1;
        int i12 = 49;
        int[] iArr9 = iArr7;
        int[] iArr10 = iArr6;
        int i13 = iArr2[i8];
        int i14 = andMoveToFrontDecode0;
        int i15 = 0;
        while (i14 != i5) {
            if (i14 == 0 || i14 == 1) {
                int i16 = -1;
                int i17 = 1;
                while (true) {
                    if (i14 == 0) {
                        i16 += i17;
                    } else if (i14 == 1) {
                        i16 += i17 << 1;
                    } else {
                        byte b = bArr3[cArr[0]];
                        int i18 = b & 255;
                        iArr[i18] = iArr[i18] + i16 + 1;
                        while (true) {
                            i16--;
                            if (i16 < 0) {
                                break;
                            }
                            i11++;
                            bArr[i11] = b;
                        }
                        if (i11 < i3) {
                            iArr5 = iArr5;
                            i5 = i5;
                            cBZip2InputStream = this;
                        } else {
                            throw new IOException("block overrun");
                        }
                    }
                    if (i12 == 0) {
                        i15++;
                        int i19 = bArr2[i15] & 255;
                        iArr10 = iArr4[i19];
                        iArr9 = iArr3[i19];
                        iArr8 = iArr5[i19];
                        i = iArr2[i19];
                        i12 = 49;
                    } else {
                        i12--;
                        i = i13;
                    }
                    int i20 = i9;
                    while (i20 < i) {
                        int read = inputStream.read();
                        if (read >= 0) {
                            i10 = (i10 << 8) | read;
                            i20 += 8;
                        } else {
                            throw new IOException("unexpected end of stream");
                        }
                    }
                    int i21 = i20 - i;
                    int i22 = (i10 >> i21) & ((1 << i) - 1);
                    i9 = i21;
                    int i23 = i;
                    while (i22 > iArr9[i23]) {
                        i23++;
                        int i24 = i9;
                        for (int i25 = 1; i24 < i25; i25 = 1) {
                            int read2 = inputStream.read();
                            if (read2 >= 0) {
                                i10 = (i10 << 8) | read2;
                                i24 += 8;
                            } else {
                                throw new IOException("unexpected end of stream");
                            }
                        }
                        i9 = i24 - 1;
                        i22 = (i22 << 1) | ((i10 >> i9) & 1);
                        i = i;
                    }
                    i14 = iArr8[i22 - iArr10[i23]];
                    i17 <<= 1;
                    iArr5 = iArr5;
                    i13 = i;
                }
            } else {
                i11++;
                if (i11 < i3) {
                    int i26 = i14 - 1;
                    char c2 = cArr[i26];
                    int i27 = bArr3[c2] & 255;
                    iArr[i27] = iArr[i27] + 1;
                    bArr[i11] = bArr3[c2];
                    if (i14 <= 16) {
                        while (i26 > 0) {
                            int i28 = i26 - 1;
                            cArr[i26] = cArr[i28];
                            i26 = i28;
                        }
                        c = 0;
                    } else {
                        c = 0;
                        System.arraycopy(cArr, 0, cArr, 1, i26);
                    }
                    cArr[c] = c2;
                    if (i12 == 0) {
                        i15++;
                        int i29 = bArr2[i15] & 255;
                        int[] iArr11 = iArr4[i29];
                        int[] iArr12 = iArr3[i29];
                        int[] iArr13 = iArr5[i29];
                        i2 = iArr2[i29];
                        iArr10 = iArr11;
                        iArr9 = iArr12;
                        iArr8 = iArr13;
                        i12 = 49;
                    } else {
                        i12--;
                        i2 = i13;
                    }
                    int i30 = i9;
                    while (i30 < i2) {
                        int read3 = inputStream.read();
                        if (read3 >= 0) {
                            i10 = (i10 << 8) | read3;
                            i30 += 8;
                        } else {
                            throw new IOException("unexpected end of stream");
                        }
                    }
                    int i31 = i30 - i2;
                    int i32 = (i10 >> i31) & ((1 << i2) - 1);
                    i9 = i31;
                    int i33 = i2;
                    while (i32 > iArr9[i33]) {
                        i33++;
                        int i34 = i9;
                        for (int i35 = 1; i34 < i35; i35 = 1) {
                            int read4 = inputStream.read();
                            if (read4 >= 0) {
                                i10 = (i10 << 8) | read4;
                                i34 += 8;
                            } else {
                                throw new IOException("unexpected end of stream");
                            }
                        }
                        i9 = i34 - 1;
                        i32 = (i32 << 1) | ((i10 >> i9) & 1);
                        i2 = i2;
                    }
                    i14 = iArr8[i32 - iArr10[i33]];
                    i13 = i2;
                    i5 = i5;
                } else {
                    throw new IOException("block overrun");
                }
            }
        }
        cBZip2InputStream.last = i11;
        cBZip2InputStream.bsLive = i9;
        cBZip2InputStream.bsBuff = i10;
    }

    private int getAndMoveToFrontDecode0(int i) throws IOException {
        InputStream inputStream = this.f14783in;
        Data data = this.data;
        int i2 = data.selector[i] & 255;
        int[] iArr = data.limit[i2];
        int i3 = data.minLens[i2];
        int bsR = bsR(i3);
        int i4 = this.bsLive;
        int i5 = this.bsBuff;
        while (bsR > iArr[i3]) {
            i3++;
            while (i4 < 1) {
                int read = inputStream.read();
                if (read >= 0) {
                    i5 = (i5 << 8) | read;
                    i4 += 8;
                } else {
                    throw new IOException("unexpected end of stream");
                }
            }
            i4--;
            bsR = (bsR << 1) | (1 & (i5 >> i4));
        }
        this.bsLive = i4;
        this.bsBuff = i5;
        return data.perm[i2][bsR - data.base[i2][i3]];
    }

    private void setupBlock() throws IOException {
        Data data = this.data;
        if (data != null) {
            int[] iArr = data.cftab;
            int[] initTT = this.data.initTT(this.last + 1);
            byte[] bArr = this.data.ll8;
            iArr[0] = 0;
            System.arraycopy(this.data.unzftab, 0, iArr, 1, 256);
            int i = iArr[0];
            for (int i2 = 1; i2 <= 256; i2++) {
                i += iArr[i2];
                iArr[i2] = i;
            }
            int i3 = this.last;
            for (int i4 = 0; i4 <= i3; i4++) {
                int i5 = bArr[i4] & 255;
                int i6 = iArr[i5];
                iArr[i5] = i6 + 1;
                initTT[i6] = i4;
            }
            int i7 = this.origPtr;
            if (i7 < 0 || i7 >= initTT.length) {
                throw new IOException("stream corrupted");
            }
            this.su_tPos = initTT[i7];
            this.su_count = 0;
            this.su_i2 = 0;
            this.su_ch2 = 256;
            if (this.blockRandomised) {
                this.su_rNToGo = 0;
                this.su_rTPos = 0;
                setupRandPartA();
                return;
            }
            setupNoRandPartA();
        }
    }

    private void setupRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            int i = this.data.ll8[this.su_tPos] & 255;
            this.su_tPos = this.data.f14784tt[this.su_tPos];
            int i2 = this.su_rNToGo;
            int i3 = 0;
            if (i2 == 0) {
                int[] iArr = BZip2Constants.rNums;
                int i4 = this.su_rTPos;
                this.su_rNToGo = iArr[i4] - 1;
                int i5 = i4 + 1;
                this.su_rTPos = i5;
                if (i5 == 512) {
                    this.su_rTPos = 0;
                }
            } else {
                this.su_rNToGo = i2 - 1;
            }
            if (this.su_rNToGo == 1) {
                i3 = 1;
            }
            int i6 = i ^ i3;
            this.su_ch2 = i6;
            this.su_i2++;
            this.currentChar = i6;
            this.currentState = 3;
            this.crc.updateCRC(i6);
            return;
        }
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupNoRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            int i = this.data.ll8[this.su_tPos] & 255;
            this.su_ch2 = i;
            this.su_tPos = this.data.f14784tt[this.su_tPos];
            this.su_i2++;
            this.currentChar = i;
            this.currentState = 6;
            this.crc.updateCRC(i);
            return;
        }
        this.currentState = 5;
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.currentState = 2;
            this.su_count = 1;
            setupRandPartA();
            return;
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i >= 4) {
            this.su_z = (char) (this.data.ll8[this.su_tPos] & 255);
            this.su_tPos = this.data.f14784tt[this.su_tPos];
            int i2 = this.su_rNToGo;
            if (i2 == 0) {
                int[] iArr = BZip2Constants.rNums;
                int i3 = this.su_rTPos;
                this.su_rNToGo = iArr[i3] - 1;
                int i4 = i3 + 1;
                this.su_rTPos = i4;
                if (i4 == 512) {
                    this.su_rTPos = 0;
                }
            } else {
                this.su_rNToGo = i2 - 1;
            }
            this.su_j2 = 0;
            this.currentState = 4;
            if (this.su_rNToGo == 1) {
                this.su_z = (char) (this.su_z ^ 1);
            }
            setupRandPartC();
            return;
        }
        this.currentState = 2;
        setupRandPartA();
    }

    private void setupRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int i = this.su_ch2;
            this.currentChar = i;
            this.crc.updateCRC(i);
            this.su_j2++;
            return;
        }
        this.currentState = 2;
        this.su_i2++;
        this.su_count = 0;
        setupRandPartA();
    }

    private void setupNoRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.su_count = 1;
            setupNoRandPartA();
            return;
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i >= 4) {
            this.su_z = (char) (this.data.ll8[this.su_tPos] & 255);
            this.su_tPos = this.data.f14784tt[this.su_tPos];
            this.su_j2 = 0;
            setupNoRandPartC();
            return;
        }
        setupNoRandPartA();
    }

    private void setupNoRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int i = this.su_ch2;
            this.currentChar = i;
            this.crc.updateCRC(i);
            this.su_j2++;
            this.currentState = 7;
            return;
        }
        this.su_i2++;
        this.su_count = 0;
        setupNoRandPartA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Data {
        byte[] ll8;

        /* renamed from: tt */
        int[] f14784tt;
        final boolean[] inUse = new boolean[256];
        final byte[] seqToUnseq = new byte[256];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final int[] unzftab = new int[256];
        final int[][] limit = (int[][]) Array.newInstance(int.class, 6, 258);
        final int[][] base = (int[][]) Array.newInstance(int.class, 6, 258);
        final int[][] perm = (int[][]) Array.newInstance(int.class, 6, 258);
        final int[] minLens = new int[6];
        final int[] cftab = new int[257];
        final char[] getAndMoveToFrontDecode_yy = new char[256];
        final char[][] temp_charArray2d = (char[][]) Array.newInstance(char.class, 6, 258);
        final byte[] recvDecodingTables_pos = new byte[6];

        Data(int i) {
            this.ll8 = new byte[i * 100000];
        }

        final int[] initTT(int i) {
            int[] iArr = this.f14784tt;
            if (iArr != null && iArr.length >= i) {
                return iArr;
            }
            int[] iArr2 = new int[i];
            this.f14784tt = iArr2;
            return iArr2;
        }
    }

    private static void reportCRCError() throws IOException {
        System.err.println("BZip2 CRC error");
    }
}
