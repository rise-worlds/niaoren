package com.sun.mail.imap.protocol;

import javax.mail.Flags;

/* loaded from: classes2.dex */
public class MailboxInfo {
    public Flags availableFlags;
    public int first;
    public int mode;
    public Flags permanentFlags;
    public int recent;
    public int total;
    public long uidnext;
    public long uidvalidity;

    /* JADX WARN: Removed duplicated region for block: B:47:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MailboxInfo(com.sun.mail.iap.Response[] r7) throws com.sun.mail.iap.ParsingException {
        /*
            r6 = this;
            r6.<init>()
            r0 = 0
            r6.availableFlags = r0
            r6.permanentFlags = r0
            r1 = -1
            r6.total = r1
            r6.recent = r1
            r6.first = r1
            r1 = -1
            r6.uidvalidity = r1
            r6.uidnext = r1
            r1 = 0
            r2 = 0
        L_0x0017:
            int r3 = r7.length
            if (r2 < r3) goto L_0x0032
            javax.mail.Flags r7 = r6.permanentFlags
            if (r7 != 0) goto L_0x0031
            javax.mail.Flags r7 = r6.availableFlags
            if (r7 == 0) goto L_0x002a
            javax.mail.Flags r0 = new javax.mail.Flags
            r0.<init>(r7)
            r6.permanentFlags = r0
            return
        L_0x002a:
            javax.mail.Flags r7 = new javax.mail.Flags
            r7.<init>()
            r6.permanentFlags = r7
        L_0x0031:
            return
        L_0x0032:
            r3 = r7[r2]
            if (r3 == 0) goto L_0x00d8
            r3 = r7[r2]
            boolean r3 = r3 instanceof com.sun.mail.imap.protocol.IMAPResponse
            if (r3 == 0) goto L_0x00d8
            r3 = r7[r2]
            com.sun.mail.imap.protocol.IMAPResponse r3 = (com.sun.mail.imap.protocol.IMAPResponse) r3
            java.lang.String r4 = "EXISTS"
            boolean r4 = r3.keyEquals(r4)
            if (r4 == 0) goto L_0x0052
            int r3 = r3.getNumber()
            r6.total = r3
            r7[r2] = r0
            goto L_0x00d8
        L_0x0052:
            java.lang.String r4 = "RECENT"
            boolean r4 = r3.keyEquals(r4)
            if (r4 == 0) goto L_0x0064
            int r3 = r3.getNumber()
            r6.recent = r3
            r7[r2] = r0
            goto L_0x00d8
        L_0x0064:
            java.lang.String r4 = "FLAGS"
            boolean r4 = r3.keyEquals(r4)
            if (r4 == 0) goto L_0x0076
            com.sun.mail.imap.protocol.FLAGS r4 = new com.sun.mail.imap.protocol.FLAGS
            r4.<init>(r3)
            r6.availableFlags = r4
            r7[r2] = r0
            goto L_0x00d8
        L_0x0076:
            boolean r4 = r3.isUnTagged()
            if (r4 == 0) goto L_0x00d8
            boolean r4 = r3.isOK()
            if (r4 == 0) goto L_0x00d8
            r3.skipSpaces()
            byte r4 = r3.readByte()
            r5 = 91
            if (r4 != r5) goto L_0x00d5
            java.lang.String r4 = r3.readAtom()
            java.lang.String r5 = "UNSEEN"
            boolean r5 = r4.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x00a0
            int r4 = r3.readNumber()
            r6.first = r4
            goto L_0x00cd
        L_0x00a0:
            java.lang.String r5 = "UIDVALIDITY"
            boolean r5 = r4.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x00af
            long r4 = r3.readLong()
            r6.uidvalidity = r4
            goto L_0x00cd
        L_0x00af:
            java.lang.String r5 = "PERMANENTFLAGS"
            boolean r5 = r4.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x00bf
            com.sun.mail.imap.protocol.FLAGS r4 = new com.sun.mail.imap.protocol.FLAGS
            r4.<init>(r3)
            r6.permanentFlags = r4
            goto L_0x00cd
        L_0x00bf:
            java.lang.String r5 = "UIDNEXT"
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x00cf
            long r4 = r3.readLong()
            r6.uidnext = r4
        L_0x00cd:
            r4 = 1
            goto L_0x00d0
        L_0x00cf:
            r4 = 0
        L_0x00d0:
            if (r4 == 0) goto L_0x00d5
            r7[r2] = r0
            goto L_0x00d8
        L_0x00d5:
            r3.reset()
        L_0x00d8:
            int r2 = r2 + 1
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.MailboxInfo.<init>(com.sun.mail.iap.Response[]):void");
    }
}
