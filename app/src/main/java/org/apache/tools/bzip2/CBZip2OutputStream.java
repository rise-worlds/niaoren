package org.apache.tools.bzip2;

import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class CBZip2OutputStream extends OutputStream implements BZip2Constants {
    protected static final int CLEARMASK = -2097153;
    protected static final int DEPTH_THRESH = 10;
    protected static final int GREATER_ICOST = 15;
    private static final int[] INCS = {1, 4, 13, 40, TbsListener.ErrorCode.THREAD_INIT_ERROR, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    protected static final int LESSER_ICOST = 0;
    public static final int MAX_BLOCKSIZE = 9;
    public static final int MIN_BLOCKSIZE = 1;
    protected static final int QSORT_STACK_SIZE = 1000;
    protected static final int SETMASK = 2097152;
    protected static final int SMALL_THRESH = 20;
    protected static final int WORK_FACTOR = 30;
    private final int allowableBlockSize;
    private int blockCRC;
    private final int blockSize100k;
    private BlockSort blockSorter;
    private int bsBuff;
    private int bsLive;
    private int combinedCRC;
    private final CRC crc;
    private int currentChar;
    private Data data;
    private int last;
    private int nInUse;
    private int nMTF;
    private OutputStream out;
    private int runLength;

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    protected static void hbMakeCodeLengths(char[] r17, int[] r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2OutputStream.hbMakeCodeLengths(char[], int[], int, int):void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:
        if (r3[r2[r11]] < r3[r2[r10]]) goto L_0x0097;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void hbMakeCodeLengths(byte[] r17, int[] r18, org.apache.tools.bzip2.CBZip2OutputStream.Data r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2OutputStream.hbMakeCodeLengths(byte[], int[], org.apache.tools.bzip2.CBZip2OutputStream$Data, int, int):void");
    }

    public static int chooseBlockSize(long j) {
        if (j > 0) {
            return (int) Math.min((j / 132000) + 1, 9L);
        }
        return 9;
    }

    public CBZip2OutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }

    public CBZip2OutputStream(OutputStream outputStream, int i) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        this.runLength = 0;
        if (i < 1) {
            throw new IllegalArgumentException("blockSize(" + i + ") < 1");
        } else if (i <= 9) {
            this.blockSize100k = i;
            this.out = outputStream;
            this.allowableBlockSize = (this.blockSize100k * 100000) - 20;
            init();
        } else {
            throw new IllegalArgumentException("blockSize(" + i + ") > 9");
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.out != null) {
            write0(i);
            return;
        }
        throw new IOException("closed");
    }

    private void writeRun() throws IOException {
        int i = this.last;
        if (i < this.allowableBlockSize) {
            int i2 = this.currentChar;
            Data data = this.data;
            data.inUse[i2] = true;
            byte b = (byte) i2;
            int i3 = this.runLength;
            this.crc.updateCRC(i2, i3);
            switch (i3) {
                case 1:
                    data.block[i + 2] = b;
                    this.last = i + 1;
                    return;
                case 2:
                    int i4 = i + 2;
                    data.block[i4] = b;
                    data.block[i + 3] = b;
                    this.last = i4;
                    return;
                case 3:
                    byte[] bArr = data.block;
                    bArr[i + 2] = b;
                    int i5 = i + 3;
                    bArr[i5] = b;
                    bArr[i + 4] = b;
                    this.last = i5;
                    return;
                default:
                    int i6 = i3 - 4;
                    data.inUse[i6] = true;
                    byte[] bArr2 = data.block;
                    bArr2[i + 2] = b;
                    bArr2[i + 3] = b;
                    bArr2[i + 4] = b;
                    int i7 = i + 5;
                    bArr2[i7] = b;
                    bArr2[i + 6] = (byte) i6;
                    this.last = i7;
                    return;
            }
        } else {
            endBlock();
            initBlock();
            writeRun();
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.OutputStream, org.apache.tools.bzip2.BlockSort, org.apache.tools.bzip2.CBZip2OutputStream$Data] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void finish() throws java.io.IOException {
        /*
            r2 = this;
            java.io.OutputStream r0 = r2.out
            if (r0 == 0) goto L_0x0024
            r0 = 0
            int r1 = r2.runLength     // Catch: all -> 0x001c
            if (r1 <= 0) goto L_0x000c
            r2.writeRun()     // Catch: all -> 0x001c
        L_0x000c:
            r1 = -1
            r2.currentChar = r1     // Catch: all -> 0x001c
            r2.endBlock()     // Catch: all -> 0x001c
            r2.endCompression()     // Catch: all -> 0x001c
            r2.out = r0
            r2.data = r0
            r2.blockSorter = r0
            goto L_0x0024
        L_0x001c:
            r1 = move-exception
            r2.out = r0
            r2.data = r0
            r2.blockSorter = r0
            throw r1
        L_0x0024:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2OutputStream.finish():void");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            finish();
            outputStream.close();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    private void init() throws IOException {
        this.data = new Data(this.blockSize100k);
        this.blockSorter = new BlockSort(this.data);
        bsPutUByte(104);
        bsPutUByte(this.blockSize100k + 48);
        this.combinedCRC = 0;
        initBlock();
    }

    private void initBlock() {
        this.crc.initialiseCRC();
        this.last = -1;
        boolean[] zArr = this.data.inUse;
        int i = 256;
        while (true) {
            i--;
            if (i >= 0) {
                zArr[i] = false;
            } else {
                return;
            }
        }
    }

    private void endBlock() throws IOException {
        this.blockCRC = this.crc.getFinalCRC();
        int i = this.combinedCRC;
        this.combinedCRC = (i >>> 31) | (i << 1);
        this.combinedCRC ^= this.blockCRC;
        if (this.last != -1) {
            blockSort();
            bsPutUByte(49);
            bsPutUByte(65);
            bsPutUByte(89);
            bsPutUByte(38);
            bsPutUByte(83);
            bsPutUByte(89);
            bsPutInt(this.blockCRC);
            bsW(1, 0);
            moveToFrontCodeAndSend();
        }
    }

    private void endCompression() throws IOException {
        bsPutUByte(23);
        bsPutUByte(114);
        bsPutUByte(69);
        bsPutUByte(56);
        bsPutUByte(80);
        bsPutUByte(TbsListener.ErrorCode.NEEDDOWNLOAD_5);
        bsPutInt(this.combinedCRC);
        bsFinishedWithStream();
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > buf.length(" + bArr.length + ").");
            } else if (this.out != null) {
                while (i < i3) {
                    i++;
                    write0(bArr[i]);
                }
            } else {
                throw new IOException("stream closed");
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void write0(int i) throws IOException {
        int i2 = this.currentChar;
        if (i2 != -1) {
            int i3 = i & 255;
            if (i2 == i3) {
                int i4 = this.runLength + 1;
                this.runLength = i4;
                if (i4 > 254) {
                    writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                    return;
                }
                return;
            }
            writeRun();
            this.runLength = 1;
            this.currentChar = i3;
            return;
        }
        this.currentChar = i & 255;
        this.runLength++;
    }

    private static void hbAssignCodes(int[] iArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            for (int i5 = 0; i5 < i3; i5++) {
                if ((bArr[i5] & 255) == i) {
                    iArr[i5] = i4;
                    i4++;
                }
            }
            i4 <<= 1;
            i++;
        }
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            this.out.write(this.bsBuff >> 24);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    private void bsW(int i, int i2) throws IOException {
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        while (i3 >= 8) {
            outputStream.write(i4 >> 24);
            i4 <<= 8;
            i3 -= 8;
        }
        this.bsBuff = (i2 << ((32 - i3) - i)) | i4;
        this.bsLive = i3 + i;
    }

    private void bsPutUByte(int i) throws IOException {
        bsW(8, i);
    }

    private void bsPutInt(int i) throws IOException {
        bsW(8, (i >> 24) & 255);
        bsW(8, (i >> 16) & 255);
        bsW(8, (i >> 8) & 255);
        bsW(8, i & 255);
    }

    private void sendMTFValues() throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        int i = 2;
        int i2 = this.nInUse + 2;
        int i3 = 6;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    bArr2[i4] = 15;
                }
            }
        }
        int i5 = this.nMTF;
        if (i5 >= 200) {
            i = i5 < 600 ? 3 : i5 < 1200 ? 4 : i5 < 2400 ? 5 : 6;
        }
        sendMTFValues0(i, i2);
        int sendMTFValues1 = sendMTFValues1(i, i2);
        sendMTFValues2(i, sendMTFValues1);
        sendMTFValues3(i, i2);
        sendMTFValues4();
        sendMTFValues5(i, sendMTFValues1);
        sendMTFValues6(i, i2);
        sendMTFValues7();
    }

    private void sendMTFValues0(int i, int i2) {
        int i3;
        byte[][] bArr = this.data.sendMTFValues_len;
        int[] iArr = this.data.mtfFreq;
        int i4 = this.nMTF;
        int i5 = 0;
        for (int i6 = i; i6 > 0; i6--) {
            int i7 = i4 / i6;
            int i8 = i5 - 1;
            int i9 = i2 - 1;
            int i10 = 0;
            while (i10 < i7 && i8 < i9) {
                i8++;
                i10 += iArr[i8];
            }
            if (i8 <= i5 || i6 == i || i6 == 1 || (1 & (i - i6)) == 0) {
                i3 = i8;
            } else {
                i3 = i8 - 1;
                i10 -= iArr[i8];
            }
            byte[] bArr2 = bArr[i6 - 1];
            int i11 = i2;
            while (true) {
                i11--;
                if (i11 >= 0) {
                    if (i11 < i5 || i11 > i3) {
                        bArr2[i11] = 15;
                    } else {
                        bArr2[i11] = 0;
                    }
                }
            }
            i5 = i3 + 1;
            i4 -= i10;
        }
    }

    private int sendMTFValues1(int i, int i2) {
        CBZip2OutputStream cBZip2OutputStream = this;
        Data data = cBZip2OutputStream.data;
        int[][] iArr = data.sendMTFValues_rfreq;
        int[] iArr2 = data.sendMTFValues_fave;
        short[] sArr = data.sendMTFValues_cost;
        char[] cArr = data.sfmap;
        byte[] bArr = data.selector;
        byte[][] bArr2 = data.sendMTFValues_len;
        int i3 = 0;
        byte[] bArr3 = bArr2[0];
        byte[] bArr4 = bArr2[1];
        byte[] bArr5 = bArr2[2];
        byte[] bArr6 = bArr2[3];
        byte[] bArr7 = bArr2[4];
        byte[] bArr8 = bArr2[5];
        int i4 = cBZip2OutputStream.nMTF;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 4; i5 < i7; i7 = 4) {
            int i8 = i;
            while (true) {
                i8--;
                if (i8 < 0) {
                    break;
                }
                iArr2[i8] = i3;
                int[] iArr3 = iArr[i8];
                int i9 = i2;
                while (true) {
                    i9--;
                    if (i9 >= 0) {
                        iArr3[i9] = i3;
                    }
                }
            }
            int i10 = 0;
            i6 = 0;
            while (i10 < cBZip2OutputStream.nMTF) {
                int min = Math.min((i10 + 50) - 1, i4 - 1);
                if (i == 6) {
                    int i11 = i10;
                    short s = 0;
                    short s2 = 0;
                    short s3 = 0;
                    short s4 = 0;
                    short s5 = 0;
                    short s6 = 0;
                    while (i11 <= min) {
                        char c = cArr[i11];
                        s = (short) (s + (bArr3[c] & 255));
                        s2 = (short) (s2 + (bArr4[c] & 255));
                        s3 = (short) (s3 + (bArr5[c] & 255));
                        s4 = (short) (s4 + (bArr6[c] & 255));
                        s5 = (short) (s5 + (bArr7[c] & 255));
                        s6 = (short) (s6 + (bArr8[c] & 255));
                        i11++;
                        bArr3 = bArr3;
                        i4 = i4;
                    }
                    bArr3 = bArr3;
                    i4 = i4;
                    sArr[0] = s;
                    sArr[1] = s2;
                    sArr[2] = s3;
                    sArr[3] = s4;
                    sArr[4] = s5;
                    sArr[5] = s6;
                } else {
                    bArr3 = bArr3;
                    i4 = i4;
                    int i12 = i;
                    while (true) {
                        i12--;
                        if (i12 < 0) {
                            break;
                        }
                        sArr[i12] = 0;
                    }
                    for (int i13 = i10; i13 <= min; i13++) {
                        char c2 = cArr[i13];
                        int i14 = i;
                        while (true) {
                            i14--;
                            if (i14 >= 0) {
                                sArr[i14] = (short) (sArr[i14] + (bArr2[i14][c2] & 255));
                            }
                        }
                    }
                }
                int i15 = i;
                int i16 = -1;
                short s7 = 999999999;
                while (true) {
                    i15--;
                    if (i15 < 0) {
                        break;
                    }
                    short s8 = sArr[i15];
                    if (s8 < s7) {
                        i16 = i15;
                        s7 = s8;
                    }
                    bArr4 = bArr4;
                }
                iArr2[i16] = iArr2[i16] + 1;
                bArr[i6] = (byte) i16;
                i6++;
                int[] iArr4 = iArr[i16];
                for (int i17 = i10; i17 <= min; i17++) {
                    char c3 = cArr[i17];
                    iArr4[c3] = iArr4[c3] + 1;
                }
                i10 = min + 1;
                bArr4 = bArr4;
            }
            int i18 = 0;
            while (i18 < i) {
                hbMakeCodeLengths(bArr2[i18], iArr[i18], cBZip2OutputStream.data, i2, 20);
                i18++;
                cBZip2OutputStream = this;
            }
            i5++;
            bArr4 = bArr4;
            i4 = i4;
            bArr3 = bArr3;
            cBZip2OutputStream = this;
            i3 = 0;
        }
        return i6;
    }

    private void sendMTFValues2(int i, int i2) {
        Data data = this.data;
        byte[] bArr = data.sendMTFValues2_pos;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            bArr[i] = (byte) i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = data.selector[i3];
            byte b2 = bArr[0];
            int i4 = 0;
            while (b != b2) {
                i4++;
                byte b3 = bArr[i4];
                bArr[i4] = b2;
                b2 = b3;
            }
            bArr[0] = b2;
            data.selectorMtf[i3] = (byte) i4;
        }
    }

    private void sendMTFValues3(int i, int i2) {
        int[][] iArr = this.data.sendMTFValues_code;
        byte[][] bArr = this.data.sendMTFValues_len;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            int i5 = 32;
            int i6 = 0;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    int i7 = bArr2[i4] & 255;
                    if (i7 > i6) {
                        i6 = i7;
                    }
                    if (i7 < i5) {
                        i5 = i7;
                    }
                }
            }
            hbAssignCodes(iArr[i3], bArr[i3], i5, i6, i2);
        }
    }

    private void sendMTFValues4() throws IOException {
        boolean[] zArr = this.data.inUse;
        boolean[] zArr2 = this.data.sentMTFValues4_inUse16;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                    }
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            bsW(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.out;
        int i5 = this.bsLive;
        int i6 = this.bsBuff;
        int i7 = i5;
        for (int i8 = 0; i8 < 16; i8++) {
            if (zArr2[i8]) {
                int i9 = i8 * 16;
                int i10 = i6;
                for (int i11 = 0; i11 < 16; i11++) {
                    while (i7 >= 8) {
                        outputStream.write(i10 >> 24);
                        i10 <<= 8;
                        i7 -= 8;
                    }
                    if (zArr[i9 + i11]) {
                        i10 |= 1 << ((32 - i7) - 1);
                    }
                    i7++;
                }
                i6 = i10;
            }
        }
        this.bsBuff = i6;
        this.bsLive = i7;
    }

    private void sendMTFValues5(int i, int i2) throws IOException {
        bsW(3, i);
        bsW(15, i2);
        OutputStream outputStream = this.out;
        byte[] bArr = this.data.selectorMtf;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        int i5 = i3;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = bArr[i6] & 255;
            int i8 = i5;
            int i9 = i4;
            for (int i10 = 0; i10 < i7; i10++) {
                while (i8 >= 8) {
                    outputStream.write(i9 >> 24);
                    i9 <<= 8;
                    i8 -= 8;
                }
                i9 |= 1 << ((32 - i8) - 1);
                i8++;
            }
            i4 = i9;
            while (i8 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i8 -= 8;
            }
            i5 = i8 + 1;
        }
        this.bsBuff = i4;
        this.bsLive = i5;
    }

    private void sendMTFValues6(int i, int i2) throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        int i5 = 0;
        while (i5 < i) {
            byte[] bArr2 = bArr[i5];
            int i6 = bArr2[0] & 255;
            while (i3 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i3 -= 8;
            }
            int i7 = i4 | (i6 << ((32 - i3) - 5));
            i3 += 5;
            int i8 = i6;
            for (int i9 = 0; i9 < i2; i9++) {
                int i10 = bArr2[i9] & 255;
                while (i8 < i10) {
                    while (i3 >= 8) {
                        outputStream.write(i7 >> 24);
                        i7 <<= 8;
                        i3 -= 8;
                    }
                    i7 |= 2 << ((32 - i3) - 2);
                    i3 += 2;
                    i8++;
                }
                while (i8 > i10) {
                    while (i3 >= 8) {
                        outputStream.write(i7 >> 24);
                        i7 <<= 8;
                        i3 -= 8;
                    }
                    i7 |= 3 << ((32 - i3) - 2);
                    i3 += 2;
                    i8--;
                }
                while (i3 >= 8) {
                    outputStream.write(i7 >> 24);
                    i7 <<= 8;
                    i3 -= 8;
                }
                i3++;
            }
            i5++;
            i4 = i7;
        }
        this.bsBuff = i4;
        this.bsLive = i3;
    }

    private void sendMTFValues7() throws IOException {
        Data data = this.data;
        byte[][] bArr = data.sendMTFValues_len;
        int[][] iArr = data.sendMTFValues_code;
        OutputStream outputStream = this.out;
        byte[] bArr2 = data.selector;
        char[] cArr = data.sfmap;
        int i = this.nMTF;
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int min = Math.min((i4 + 50) - 1, i - 1);
            int i6 = bArr2[i5] & 255;
            int[] iArr2 = iArr[i6];
            byte[] bArr3 = bArr[i6];
            while (i4 <= min) {
                char c = cArr[i4];
                while (i2 >= 8) {
                    outputStream.write(i3 >> 24);
                    i3 <<= 8;
                    i2 -= 8;
                }
                int i7 = bArr3[c] & 255;
                i3 |= iArr2[c] << ((32 - i2) - i7);
                i2 += i7;
                i4++;
            }
            i4 = min + 1;
            i5++;
        }
        this.bsBuff = i3;
        this.bsLive = i2;
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsW(24, this.data.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    private void generateMTFValues() {
        int i;
        int i2;
        int i3 = this.last;
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.block;
        int[] iArr = data.fmap;
        char[] cArr = data.sfmap;
        int[] iArr2 = data.mtfFreq;
        byte[] bArr2 = data.unseqToSeq;
        byte[] bArr3 = data.generateMTFValues_yy;
        int i4 = 0;
        for (int i5 = 0; i5 < 256; i5++) {
            if (zArr[i5]) {
                bArr2[i5] = (byte) i4;
                i4++;
            }
        }
        this.nInUse = i4;
        int i6 = i4 + 1;
        for (int i7 = i6; i7 >= 0; i7--) {
            iArr2[i7] = 0;
        }
        while (true) {
            i4--;
            if (i4 < 0) {
                break;
            }
            bArr3[i4] = (byte) i4;
        }
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 <= i3; i10++) {
            byte b = bArr2[bArr[iArr[i10]] & 255];
            byte b2 = bArr3[0];
            int i11 = 0;
            while (b != b2) {
                i11++;
                byte b3 = bArr3[i11];
                bArr3[i11] = b2;
                b2 = b3;
            }
            bArr3[0] = b2;
            if (i11 == 0) {
                i8++;
            } else {
                if (i8 > 0) {
                    int i12 = i8 - 1;
                    while (true) {
                        if ((i12 & 1) == 0) {
                            cArr[i9] = 0;
                            i9++;
                            iArr2[0] = iArr2[0] + 1;
                            i2 = 2;
                        } else {
                            cArr[i9] = 1;
                            i9++;
                            iArr2[1] = iArr2[1] + 1;
                            i2 = 2;
                        }
                        if (i12 < i2) {
                            break;
                        }
                        i12 = (i12 - 2) >> 1;
                    }
                    i8 = 0;
                }
                int i13 = i11 + 1;
                cArr[i9] = (char) i13;
                i9++;
                iArr2[i13] = iArr2[i13] + 1;
            }
        }
        if (i8 > 0) {
            int i14 = i8 - 1;
            while (true) {
                if ((i14 & 1) == 0) {
                    cArr[i9] = 0;
                    i9++;
                    iArr2[0] = iArr2[0] + 1;
                    i = 2;
                } else {
                    cArr[i9] = 1;
                    i9++;
                    iArr2[1] = iArr2[1] + 1;
                    i = 2;
                }
                if (i14 < i) {
                    break;
                }
                i14 = (i14 - 2) >> 1;
            }
        }
        cArr[i9] = (char) i6;
        iArr2[i6] = iArr2[i6] + 1;
        this.nMTF = i9 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Data {
        final byte[] block;
        final int[] fmap;
        int origPtr;
        final char[] sfmap;
        final boolean[] inUse = new boolean[256];
        final byte[] unseqToSeq = new byte[256];
        final int[] mtfFreq = new int[258];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] generateMTFValues_yy = new byte[256];
        final byte[][] sendMTFValues_len = (byte[][]) Array.newInstance(byte.class, 6, 258);
        final int[][] sendMTFValues_rfreq = (int[][]) Array.newInstance(int.class, 6, 258);
        final int[] sendMTFValues_fave = new int[6];
        final short[] sendMTFValues_cost = new short[6];
        final int[][] sendMTFValues_code = (int[][]) Array.newInstance(int.class, 6, 258);
        final byte[] sendMTFValues2_pos = new byte[6];
        final boolean[] sentMTFValues4_inUse16 = new boolean[16];
        final int[] heap = new int[IpcCommand.f8361aH];
        final int[] weight = new int[516];
        final int[] parent = new int[516];

        Data(int i) {
            int i2 = i * 100000;
            this.block = new byte[i2 + 1 + 20];
            this.fmap = new int[i2];
            this.sfmap = new char[i2 * 2];
        }
    }
}
