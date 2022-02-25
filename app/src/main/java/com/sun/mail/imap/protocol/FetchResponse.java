package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import java.io.IOException;
import java.util.Vector;
import org.apache.commons.p105io.FilenameUtils;

/* loaded from: classes2.dex */
public class FetchResponse extends IMAPResponse {
    private static final char[] HEADER = {FilenameUtils.EXTENSION_SEPARATOR, 'H', 'E', 'A', 'D', 'E', 'R'};
    private static final char[] TEXT = {FilenameUtils.EXTENSION_SEPARATOR, 'T', 'E', 'X', 'T'};
    private Item[] items;

    public FetchResponse(Protocol protocol) throws IOException, ProtocolException {
        super(protocol);
        parse();
    }

    public FetchResponse(IMAPResponse iMAPResponse) throws IOException, ProtocolException {
        super(iMAPResponse);
        parse();
    }

    public int getItemCount() {
        return this.items.length;
    }

    public Item getItem(int i) {
        return this.items[i];
    }

    public Item getItem(Class cls) {
        int i = 0;
        while (true) {
            Item[] itemArr = this.items;
            if (i >= itemArr.length) {
                return null;
            }
            if (cls.isInstance(itemArr[i])) {
                return this.items[i];
            }
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0039, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.sun.mail.imap.protocol.Item getItem(com.sun.mail.iap.Response[] r7, int r8, java.lang.Class r9) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            r2 = 0
        L_0x0006:
            int r3 = r7.length
            if (r2 < r3) goto L_0x000a
            return r0
        L_0x000a:
            r3 = r7[r2]
            if (r3 == 0) goto L_0x0039
            r3 = r7[r2]
            boolean r3 = r3 instanceof com.sun.mail.imap.protocol.FetchResponse
            if (r3 == 0) goto L_0x0039
            r3 = r7[r2]
            com.sun.mail.imap.protocol.FetchResponse r3 = (com.sun.mail.imap.protocol.FetchResponse) r3
            int r3 = r3.getNumber()
            if (r3 != r8) goto L_0x0039
            r3 = r7[r2]
            com.sun.mail.imap.protocol.FetchResponse r3 = (com.sun.mail.imap.protocol.FetchResponse) r3
            r4 = 0
        L_0x0023:
            com.sun.mail.imap.protocol.Item[] r5 = r3.items
            int r6 = r5.length
            if (r4 < r6) goto L_0x0029
            goto L_0x0039
        L_0x0029:
            r5 = r5[r4]
            boolean r5 = r9.isInstance(r5)
            if (r5 == 0) goto L_0x0036
            com.sun.mail.imap.protocol.Item[] r7 = r3.items
            r7 = r7[r4]
            return r7
        L_0x0036:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x0039:
            int r2 = r2 + 1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.protocol.FetchResponse.getItem(com.sun.mail.iap.Response[], int, java.lang.Class):com.sun.mail.imap.protocol.Item");
    }

    private void parse() throws ParsingException {
        skipSpaces();
        if (this.buffer[this.index] == 40) {
            Vector vector = new Vector();
            Object obj = null;
            do {
                this.index++;
                if (this.index < this.size) {
                    byte b = this.buffer[this.index];
                    if (b != 66) {
                        if (b != 73) {
                            if (b != 82) {
                                if (b != 85) {
                                    switch (b) {
                                        case 69:
                                            if (match(ENVELOPE.name)) {
                                                this.index += ENVELOPE.name.length;
                                                obj = new ENVELOPE(this);
                                                break;
                                            }
                                            break;
                                        case 70:
                                            if (match(FLAGS.name)) {
                                                this.index += FLAGS.name.length;
                                                obj = new FLAGS(this);
                                                break;
                                            }
                                            break;
                                    }
                                } else if (match(UID.name)) {
                                    this.index += UID.name.length;
                                    obj = new UID(this);
                                }
                            } else if (match(RFC822SIZE.name)) {
                                this.index += RFC822SIZE.name.length;
                                obj = new RFC822SIZE(this);
                            } else if (match(RFC822DATA.name)) {
                                this.index += RFC822DATA.name.length;
                                if (match(HEADER)) {
                                    this.index += HEADER.length;
                                } else if (match(TEXT)) {
                                    this.index += TEXT.length;
                                }
                                obj = new RFC822DATA(this);
                            }
                        } else if (match(INTERNALDATE.name)) {
                            this.index += INTERNALDATE.name.length;
                            obj = new INTERNALDATE(this);
                        }
                    } else if (match(BODY.name)) {
                        if (this.buffer[this.index + 4] == 91) {
                            this.index += BODY.name.length;
                            obj = new BODY(this);
                        } else {
                            if (match(BODYSTRUCTURE.name)) {
                                this.index += BODYSTRUCTURE.name.length;
                            } else {
                                this.index += BODY.name.length;
                            }
                            obj = new BODYSTRUCTURE(this);
                        }
                    }
                    if (obj != null) {
                        vector.addElement(obj);
                    }
                } else {
                    throw new ParsingException("error in FETCH parsing, ran off end of buffer, size " + this.size);
                }
            } while (this.buffer[this.index] != 41);
            this.index++;
            this.items = new Item[vector.size()];
            vector.copyInto(this.items);
            return;
        }
        throw new ParsingException("error in FETCH parsing, missing '(' at index " + this.index);
    }

    private boolean match(char[] cArr) {
        int length = cArr.length;
        int i = this.index;
        int i2 = 0;
        while (i2 < length) {
            i++;
            i2++;
            if (Character.toUpperCase((char) this.buffer[i]) != cArr[i2]) {
                return false;
            }
        }
        return true;
    }
}
