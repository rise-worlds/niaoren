package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;

/* loaded from: classes2.dex */
public class Status {
    static final String[] standardItems = {"MESSAGES", "RECENT", "UNSEEN", "UIDNEXT", "UIDVALIDITY"};
    public String mbox;
    public int recent;
    public int total;
    public long uidnext;
    public long uidvalidity;
    public int unseen;

    public Status(Response response) throws ParsingException {
        this.mbox = null;
        this.total = -1;
        this.recent = -1;
        this.uidnext = -1L;
        this.uidvalidity = -1L;
        this.unseen = -1;
        this.mbox = response.readAtomString();
        response.skipSpaces();
        if (response.readByte() == 40) {
            do {
                String readAtom = response.readAtom();
                if (readAtom.equalsIgnoreCase("MESSAGES")) {
                    this.total = response.readNumber();
                } else if (readAtom.equalsIgnoreCase("RECENT")) {
                    this.recent = response.readNumber();
                } else if (readAtom.equalsIgnoreCase("UIDNEXT")) {
                    this.uidnext = response.readLong();
                } else if (readAtom.equalsIgnoreCase("UIDVALIDITY")) {
                    this.uidvalidity = response.readLong();
                } else if (readAtom.equalsIgnoreCase("UNSEEN")) {
                    this.unseen = response.readNumber();
                }
            } while (response.readByte() != 41);
            return;
        }
        throw new ParsingException("parse error in STATUS");
    }

    public static void add(Status status, Status status2) {
        int i = status2.total;
        if (i != -1) {
            status.total = i;
        }
        int i2 = status2.recent;
        if (i2 != -1) {
            status.recent = i2;
        }
        long j = status2.uidnext;
        if (j != -1) {
            status.uidnext = j;
        }
        long j2 = status2.uidvalidity;
        if (j2 != -1) {
            status.uidvalidity = j2;
        }
        int i3 = status2.unseen;
        if (i3 != -1) {
            status.unseen = i3;
        }
    }
}
