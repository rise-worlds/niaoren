package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* loaded from: classes2.dex */
public class LineInputStream extends FilterInputStream {
    private char[] lineBuffer = null;

    public LineInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public String readLine() throws IOException {
        int read;
        InputStream inputStream = this.in;
        char[] cArr = this.lineBuffer;
        if (cArr == null) {
            cArr = new char[128];
            this.lineBuffer = cArr;
        }
        int length = cArr.length;
        char[] cArr2 = cArr;
        int i = 0;
        while (true) {
            read = inputStream.read();
            if (read == -1 || read == 10) {
                break;
            } else if (read == 13) {
                int read2 = inputStream.read();
                if (read2 == 13) {
                    read2 = inputStream.read();
                }
                if (read2 != 10) {
                    if (!(inputStream instanceof PushbackInputStream)) {
                        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
                        this.in = pushbackInputStream;
                        inputStream = pushbackInputStream;
                    }
                    ((PushbackInputStream) inputStream).unread(read2);
                }
            } else {
                length--;
                if (length < 0) {
                    char[] cArr3 = new char[i + 128];
                    length = (cArr3.length - i) - 1;
                    System.arraycopy(this.lineBuffer, 0, cArr3, 0, i);
                    this.lineBuffer = cArr3;
                    cArr2 = cArr3;
                }
                i++;
                cArr2[i] = (char) read;
            }
        }
        if (read == -1 && i == 0) {
            return null;
        }
        return String.copyValueOf(cArr2, 0, i);
    }
}
