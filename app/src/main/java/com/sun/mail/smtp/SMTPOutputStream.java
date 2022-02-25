package com.sun.mail.smtp;

import com.sun.mail.util.CRLFOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class SMTPOutputStream extends CRLFOutputStream {
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public SMTPOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // com.sun.mail.util.CRLFOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if ((this.lastb == 10 || this.lastb == 13 || this.lastb == -1) && i == 46) {
            this.out.write(46);
        }
        super.write(i);
    }

    @Override // com.sun.mail.util.CRLFOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        int i4 = i;
        int i5 = this.lastb == -1 ? 10 : this.lastb;
        while (i < i3) {
            if ((i5 == 10 || i5 == 13) && bArr[i] == 46) {
                super.write(bArr, i4, i - i4);
                this.out.write(46);
                i4 = i;
            }
            byte b = bArr[i];
            i++;
            i5 = b;
        }
        int i6 = i3 - i4;
        if (i6 > 0) {
            super.write(bArr, i4, i6);
        }
    }

    public void ensureAtBOL() throws IOException {
        if (!this.atBOL) {
            super.writeln();
        }
    }
}
