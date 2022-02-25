package org.apache.tools.mail;

import java.io.OutputStream;
import java.io.PrintStream;

/* compiled from: MailMessage.java */
/* loaded from: classes2.dex */
class MailPrintStream extends PrintStream {
    private int lastChar;

    public MailPrintStream(OutputStream outputStream) {
        super(outputStream, true);
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) {
        if (i == 10 && this.lastChar != 13) {
            rawWrite(13);
            rawWrite(i);
        } else if (i == 46 && this.lastChar == 10) {
            rawWrite(46);
            rawWrite(i);
        } else {
            rawWrite(i);
        }
        this.lastChar = i;
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            write(bArr[i + i3]);
        }
    }

    void rawWrite(int i) {
        super.write(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void rawPrint(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            rawWrite(str.charAt(i));
        }
    }
}
