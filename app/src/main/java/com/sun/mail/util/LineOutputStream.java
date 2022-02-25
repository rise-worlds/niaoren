package com.sun.mail.util;

import com.tendcloud.tenddata.C2771ci;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import javax.mail.MessagingException;

/* loaded from: classes2.dex */
public class LineOutputStream extends FilterOutputStream {
    private static byte[] newline;

    static {
        byte[] bArr = new byte[2];
        newline = bArr;
        bArr[0] = C2771ci.f13672f;
        newline[1] = 10;
    }

    public LineOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeln(String str) throws MessagingException {
        try {
            this.out.write(ASCIIUtility.getBytes(str));
            this.out.write(newline);
        } catch (Exception e) {
            throw new MessagingException("IOException", e);
        }
    }

    public void writeln() throws MessagingException {
        try {
            this.out.write(newline);
        } catch (Exception e) {
            throw new MessagingException("IOException", e);
        }
    }
}
