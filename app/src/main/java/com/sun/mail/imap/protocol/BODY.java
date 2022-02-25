package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.ParsingException;
import java.io.ByteArrayInputStream;

/* loaded from: classes2.dex */
public class BODY implements Item {
    static final char[] name = {'B', 'O', 'D', 'Y'};
    public ByteArray data;
    public int msgno;
    public int origin;
    public String section;

    public BODY(FetchResponse fetchResponse) throws ParsingException {
        byte readByte;
        this.origin = 0;
        this.msgno = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        do {
            readByte = fetchResponse.readByte();
            if (readByte == 93) {
                if (fetchResponse.readByte() == 60) {
                    this.origin = fetchResponse.readNumber();
                    fetchResponse.skip(1);
                }
                this.data = fetchResponse.readByteArray();
                return;
            }
        } while (readByte != 0);
        throw new ParsingException("BODY parse error: missing ``]'' at section end");
    }

    public ByteArray getByteArray() {
        return this.data;
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        ByteArray byteArray = this.data;
        if (byteArray != null) {
            return byteArray.toByteArrayInputStream();
        }
        return null;
    }
}
