package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import java.util.Vector;

/* loaded from: classes2.dex */
public class Namespaces {
    public Namespace[] otherUsers;
    public Namespace[] personal;
    public Namespace[] shared;

    /* loaded from: classes2.dex */
    public static class Namespace {
        public char delimiter;
        public String prefix;

        public Namespace(Response response) throws ProtocolException {
            if (response.readByte() == 40) {
                this.prefix = BASE64MailboxDecoder.decode(response.readString());
                response.skipSpaces();
                if (response.peekByte() == 34) {
                    response.readByte();
                    this.delimiter = (char) response.readByte();
                    if (this.delimiter == '\\') {
                        this.delimiter = (char) response.readByte();
                    }
                    if (response.readByte() != 34) {
                        throw new ProtocolException("Missing '\"' at end of QUOTED_CHAR");
                    }
                } else {
                    String readAtom = response.readAtom();
                    if (readAtom == null) {
                        throw new ProtocolException("Expected NIL, got null");
                    } else if (readAtom.equalsIgnoreCase("NIL")) {
                        this.delimiter = (char) 0;
                    } else {
                        throw new ProtocolException("Expected NIL, got " + readAtom);
                    }
                }
                if (response.peekByte() != 41) {
                    response.skipSpaces();
                    response.readString();
                    response.skipSpaces();
                    response.readStringList();
                }
                if (response.readByte() != 41) {
                    throw new ProtocolException("Missing ')' at end of Namespace");
                }
                return;
            }
            throw new ProtocolException("Missing '(' at start of Namespace");
        }
    }

    public Namespaces(Response response) throws ProtocolException {
        this.personal = getNamespaces(response);
        this.otherUsers = getNamespaces(response);
        this.shared = getNamespaces(response);
    }

    private Namespace[] getNamespaces(Response response) throws ProtocolException {
        response.skipSpaces();
        if (response.peekByte() == 40) {
            Vector vector = new Vector();
            response.readByte();
            do {
                vector.addElement(new Namespace(response));
            } while (response.peekByte() != 41);
            response.readByte();
            Namespace[] namespaceArr = new Namespace[vector.size()];
            vector.copyInto(namespaceArr);
            return namespaceArr;
        }
        String readAtom = response.readAtom();
        if (readAtom == null) {
            throw new ProtocolException("Expected NIL, got null");
        } else if (readAtom.equalsIgnoreCase("NIL")) {
            return null;
        } else {
            throw new ProtocolException("Expected NIL, got " + readAtom);
        }
    }
}
