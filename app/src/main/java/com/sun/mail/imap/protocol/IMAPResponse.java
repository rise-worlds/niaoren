package com.sun.mail.imap.protocol;

import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.ASCIIUtility;
import java.io.IOException;
import java.util.Vector;

/* loaded from: classes2.dex */
public class IMAPResponse extends Response {
    private String key;
    private int number;

    public IMAPResponse(Protocol protocol) throws IOException, ProtocolException {
        super(protocol);
        if (isUnTagged() && !isOK() && !isNO() && !isBAD() && !isBYE()) {
            this.key = readAtom();
            try {
                this.number = Integer.parseInt(this.key);
                this.key = readAtom();
            } catch (NumberFormatException unused) {
            }
        }
    }

    public IMAPResponse(IMAPResponse iMAPResponse) {
        super(iMAPResponse);
        this.key = iMAPResponse.key;
        this.number = iMAPResponse.number;
    }

    public String[] readSimpleList() {
        skipSpaces();
        if (this.buffer[this.index] != 40) {
            return null;
        }
        this.index++;
        Vector vector = new Vector();
        int i = this.index;
        while (this.buffer[this.index] != 41) {
            if (this.buffer[this.index] == 32) {
                vector.addElement(ASCIIUtility.toString(this.buffer, i, this.index));
                i = this.index + 1;
            }
            this.index++;
        }
        if (this.index > i) {
            vector.addElement(ASCIIUtility.toString(this.buffer, i, this.index));
        }
        this.index++;
        int size = vector.size();
        if (size <= 0) {
            return null;
        }
        String[] strArr = new String[size];
        vector.copyInto(strArr);
        return strArr;
    }

    public String getKey() {
        return this.key;
    }

    public boolean keyEquals(String str) {
        String str2 = this.key;
        return str2 != null && str2.equalsIgnoreCase(str);
    }

    public int getNumber() {
        return this.number;
    }

    public static IMAPResponse readResponse(Protocol protocol) throws IOException, ProtocolException {
        IMAPResponse iMAPResponse = new IMAPResponse(protocol);
        return iMAPResponse.keyEquals("FETCH") ? new FetchResponse(iMAPResponse) : iMAPResponse;
    }
}
