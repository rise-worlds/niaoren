package com.sun.mail.util;

import com.tencent.smtt.sdk.TbsListener;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class UUDecoderStream extends FilterInputStream {
    private LineInputStream lin;
    private int mode;
    private String name;
    private int bufsize = 0;
    private int index = 0;
    private boolean gotPrefix = false;
    private boolean gotEnd = false;
    private byte[] buffer = new byte[45];

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public UUDecoderStream(InputStream inputStream) {
        super(inputStream);
        this.lin = new LineInputStream(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            readPrefix();
            if (!decode()) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read();
            if (read != -1) {
                bArr[i + i3] = (byte) read;
                i3++;
            } else if (i3 == 0) {
                return -1;
            } else {
                return i3;
            }
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return ((this.in.available() * 3) / 4) + (this.bufsize - this.index);
    }

    public String getName() throws IOException {
        readPrefix();
        return this.name;
    }

    public int getMode() throws IOException {
        readPrefix();
        return this.mode;
    }

    private void readPrefix() throws IOException {
        String readLine;
        if (!this.gotPrefix) {
            do {
                readLine = this.lin.readLine();
                if (readLine == null) {
                    throw new IOException("UUDecoder error: No Begin");
                }
            } while (!readLine.regionMatches(true, 0, "begin", 0, 5));
            try {
                this.mode = Integer.parseInt(readLine.substring(6, 9));
                this.name = readLine.substring(10);
                this.gotPrefix = true;
            } catch (NumberFormatException e) {
                throw new IOException("UUDecoder error: " + e.toString());
            }
        }
    }

    private boolean decode() throws IOException {
        String readLine;
        if (this.gotEnd) {
            return false;
        }
        this.bufsize = 0;
        do {
            readLine = this.lin.readLine();
            if (readLine == null) {
                throw new IOException("Missing End");
            } else if (readLine.regionMatches(true, 0, "end", 0, 3)) {
                this.gotEnd = true;
                return false;
            }
        } while (readLine.length() == 0);
        char charAt = readLine.charAt(0);
        if (charAt >= ' ') {
            int i = (charAt - ' ') & 63;
            if (i == 0) {
                String readLine2 = this.lin.readLine();
                if (readLine2 == null || !readLine2.regionMatches(true, 0, "end", 0, 3)) {
                    throw new IOException("Missing End");
                }
                this.gotEnd = true;
                return false;
            } else if (readLine.length() >= (((i * 8) + 5) / 6) + 1) {
                int i2 = 1;
                while (this.bufsize < i) {
                    int i3 = i2 + 1;
                    int i4 = i3 + 1;
                    byte charAt2 = (byte) ((readLine.charAt(i3) - ' ') & 63);
                    byte[] bArr = this.buffer;
                    int i5 = this.bufsize;
                    this.bufsize = i5 + 1;
                    bArr[i5] = (byte) (((((byte) ((readLine.charAt(i2) - ' ') & 63)) << 2) & 252) | ((charAt2 >>> 4) & 3));
                    if (this.bufsize < i) {
                        i2 = i4 + 1;
                        byte charAt3 = (byte) ((readLine.charAt(i4) - ' ') & 63);
                        byte[] bArr2 = this.buffer;
                        int i6 = this.bufsize;
                        this.bufsize = i6 + 1;
                        bArr2[i6] = (byte) (((charAt2 << 4) & TbsListener.ErrorCode.TPATCH_VERSION_FAILED) | ((charAt3 >>> 2) & 15));
                        charAt2 = charAt3;
                    } else {
                        i2 = i4;
                    }
                    if (this.bufsize < i) {
                        i2++;
                        byte[] bArr3 = this.buffer;
                        int i7 = this.bufsize;
                        this.bufsize = i7 + 1;
                        bArr3[i7] = (byte) ((((byte) ((readLine.charAt(i2) - ' ') & 63)) & 63) | ((charAt2 << 6) & 192));
                    }
                }
                return true;
            } else {
                throw new IOException("Short buffer error");
            }
        } else {
            throw new IOException("Buffer format error");
        }
    }
}
