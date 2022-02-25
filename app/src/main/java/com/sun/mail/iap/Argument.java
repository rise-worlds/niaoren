package com.sun.mail.iap;

import com.sun.mail.util.ASCIIUtility;
import com.tencent.smtt.sdk.TbsListener;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/* loaded from: classes2.dex */
public class Argument {
    protected Vector items = new Vector(1);

    public void append(Argument argument) {
        Vector vector = this.items;
        vector.ensureCapacity(vector.size() + argument.items.size());
        for (int i = 0; i < argument.items.size(); i++) {
            this.items.addElement(argument.items.elementAt(i));
        }
    }

    public void writeString(String str) {
        this.items.addElement(new AString(ASCIIUtility.getBytes(str)));
    }

    public void writeString(String str, String str2) throws UnsupportedEncodingException {
        if (str2 == null) {
            writeString(str);
        } else {
            this.items.addElement(new AString(str.getBytes(str2)));
        }
    }

    public void writeBytes(byte[] bArr) {
        this.items.addElement(bArr);
    }

    public void writeBytes(ByteArrayOutputStream byteArrayOutputStream) {
        this.items.addElement(byteArrayOutputStream);
    }

    public void writeBytes(Literal literal) {
        this.items.addElement(literal);
    }

    public void writeAtom(String str) {
        this.items.addElement(new Atom(str));
    }

    public void writeNumber(int i) {
        this.items.addElement(new Integer(i));
    }

    public void writeNumber(long j) {
        this.items.addElement(new Long(j));
    }

    public void writeArgument(Argument argument) {
        this.items.addElement(argument);
    }

    public void write(Protocol protocol) throws IOException, ProtocolException {
        Vector vector = this.items;
        int size = vector != null ? vector.size() : 0;
        DataOutputStream dataOutputStream = (DataOutputStream) protocol.getOutputStream();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                dataOutputStream.write(32);
            }
            Object elementAt = this.items.elementAt(i);
            if (elementAt instanceof Atom) {
                dataOutputStream.writeBytes(((Atom) elementAt).string);
            } else if (elementAt instanceof Number) {
                dataOutputStream.writeBytes(((Number) elementAt).toString());
            } else if (elementAt instanceof AString) {
                astring(((AString) elementAt).bytes, protocol);
            } else if (elementAt instanceof byte[]) {
                literal((byte[]) elementAt, protocol);
            } else if (elementAt instanceof ByteArrayOutputStream) {
                literal((ByteArrayOutputStream) elementAt, protocol);
            } else if (elementAt instanceof Literal) {
                literal((Literal) elementAt, protocol);
            } else if (elementAt instanceof Argument) {
                dataOutputStream.write(40);
                ((Argument) elementAt).write(protocol);
                dataOutputStream.write(41);
            }
        }
    }

    private void astring(byte[] bArr, Protocol protocol) throws IOException, ProtocolException {
        int i;
        DataOutputStream dataOutputStream = (DataOutputStream) protocol.getOutputStream();
        int length = bArr.length;
        if (length > 1024) {
            literal(bArr, protocol);
            return;
        }
        boolean z = length == 0;
        boolean z2 = false;
        for (byte b : bArr) {
            if (b == 0 || b == 13 || b == 10 || (i = b & 255) > 127) {
                literal(bArr, protocol);
                return;
            }
            if (b == 42 || b == 37 || b == 40 || b == 41 || b == 123 || b == 34 || b == 92 || i <= 32) {
                if (b == 34 || b == 92) {
                    z = true;
                    z2 = true;
                } else {
                    z = true;
                }
            }
        }
        if (z) {
            dataOutputStream.write(34);
        }
        if (z2) {
            for (byte b2 : bArr) {
                if (b2 == 34 || b2 == 92) {
                    dataOutputStream.write(92);
                }
                dataOutputStream.write(b2);
            }
        } else {
            dataOutputStream.write(bArr);
        }
        if (z) {
            dataOutputStream.write(34);
        }
    }

    private void literal(byte[] bArr, Protocol protocol) throws IOException, ProtocolException {
        startLiteral(protocol, bArr.length).write(bArr);
    }

    private void literal(ByteArrayOutputStream byteArrayOutputStream, Protocol protocol) throws IOException, ProtocolException {
        byteArrayOutputStream.writeTo(startLiteral(protocol, byteArrayOutputStream.size()));
    }

    private void literal(Literal literal, Protocol protocol) throws IOException, ProtocolException {
        literal.writeTo(startLiteral(protocol, literal.size()));
    }

    private OutputStream startLiteral(Protocol protocol, int i) throws IOException, ProtocolException {
        Response readResponse;
        DataOutputStream dataOutputStream = (DataOutputStream) protocol.getOutputStream();
        boolean supportsNonSyncLiterals = protocol.supportsNonSyncLiterals();
        dataOutputStream.write(TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
        dataOutputStream.writeBytes(Integer.toString(i));
        if (supportsNonSyncLiterals) {
            dataOutputStream.writeBytes("+}\r\n");
        } else {
            dataOutputStream.writeBytes("}\r\n");
        }
        dataOutputStream.flush();
        if (!supportsNonSyncLiterals) {
            do {
                readResponse = protocol.readResponse();
                if (!readResponse.isContinuation()) {
                }
            } while (!readResponse.isTagged());
            throw new LiteralException(readResponse);
        }
        return dataOutputStream;
    }
}
