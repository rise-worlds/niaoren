package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;
import java.util.Vector;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/* compiled from: ENVELOPE.java */
/* loaded from: classes2.dex */
class IMAPAddress extends InternetAddress {
    private static final long serialVersionUID = -3835822029483122232L;
    private boolean group;
    private InternetAddress[] grouplist;
    private String groupname;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMAPAddress(Response response) throws ParsingException {
        this.group = false;
        response.skipSpaces();
        if (response.readByte() == 40) {
            this.encodedPersonal = response.readString();
            response.readString();
            String readString = response.readString();
            String readString2 = response.readString();
            if (response.readByte() != 41) {
                throw new ParsingException("ADDRESS parse error");
            } else if (readString2 == null) {
                this.group = true;
                this.groupname = readString;
                if (this.groupname != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(this.groupname);
                    stringBuffer.append(':');
                    Vector vector = new Vector();
                    while (response.peekByte() != 41) {
                        IMAPAddress iMAPAddress = new IMAPAddress(response);
                        if (iMAPAddress.isEndOfGroup()) {
                            break;
                        }
                        if (vector.size() != 0) {
                            stringBuffer.append(',');
                        }
                        stringBuffer.append(iMAPAddress.toString());
                        vector.addElement(iMAPAddress);
                    }
                    stringBuffer.append(';');
                    this.address = stringBuffer.toString();
                    this.grouplist = new IMAPAddress[vector.size()];
                    vector.copyInto(this.grouplist);
                }
            } else if (readString == null || readString.length() == 0) {
                this.address = readString2;
            } else if (readString2.length() == 0) {
                this.address = readString;
            } else {
                this.address = String.valueOf(readString) + "@" + readString2;
            }
        } else {
            throw new ParsingException("ADDRESS parse error");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEndOfGroup() {
        return this.group && this.groupname == null;
    }

    @Override // javax.mail.internet.InternetAddress
    public boolean isGroup() {
        return this.group;
    }

    @Override // javax.mail.internet.InternetAddress
    public InternetAddress[] getGroup(boolean z) throws AddressException {
        InternetAddress[] internetAddressArr = this.grouplist;
        if (internetAddressArr == null) {
            return null;
        }
        return (InternetAddress[]) internetAddressArr.clone();
    }
}
