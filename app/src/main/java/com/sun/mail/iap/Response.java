package com.sun.mail.iap;

import com.sun.mail.util.ASCIIUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

/* loaded from: classes2.dex */
public class Response {
    public static final int BAD = 12;
    public static final int BYE = 16;
    public static final int CONTINUATION = 1;

    /* renamed from: NO */
    public static final int f12652NO = 8;

    /* renamed from: OK */
    public static final int f12653OK = 4;
    public static final int SYNTHETIC = 32;
    public static final int TAGGED = 2;
    public static final int TAG_MASK = 3;
    public static final int TYPE_MASK = 28;
    public static final int UNTAGGED = 3;
    private static final int increment = 100;
    protected byte[] buffer;
    protected int index;
    protected int pindex;
    protected int size;
    protected String tag;
    protected int type;

    public Response(String str) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.buffer = ASCIIUtility.getBytes(str);
        this.size = this.buffer.length;
        parse();
    }

    public Response(Protocol protocol) throws IOException, ProtocolException {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        ByteArray readResponse = protocol.getInputStream().readResponse(protocol.getResponseBuffer());
        this.buffer = readResponse.getBytes();
        this.size = readResponse.getCount() - 2;
        parse();
    }

    public Response(Response response) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.index = response.index;
        this.size = response.size;
        this.buffer = response.buffer;
        this.type = response.type;
        this.tag = response.tag;
    }

    public static Response byeResponse(Exception exc) {
        Response response = new Response(("* BYE JavaMail Exception: " + exc.toString()).replace('\r', ' ').replace('\n', ' '));
        response.type = response.type | 32;
        return response;
    }

    private void parse() {
        this.index = 0;
        byte[] bArr = this.buffer;
        int i = this.index;
        if (bArr[i] == 43) {
            this.type |= 1;
            this.index = i + 1;
            return;
        }
        if (bArr[i] == 42) {
            this.type |= 3;
            this.index = i + 1;
        } else {
            this.type |= 2;
            this.tag = readAtom();
        }
        int i2 = this.index;
        String readAtom = readAtom();
        if (readAtom == null) {
            readAtom = "";
        }
        if (readAtom.equalsIgnoreCase("OK")) {
            this.type |= 4;
        } else if (readAtom.equalsIgnoreCase("NO")) {
            this.type |= 8;
        } else if (readAtom.equalsIgnoreCase("BAD")) {
            this.type |= 12;
        } else if (readAtom.equalsIgnoreCase("BYE")) {
            this.type |= 16;
        } else {
            this.index = i2;
        }
        this.pindex = this.index;
    }

    public void skipSpaces() {
        while (true) {
            int i = this.index;
            if (i < this.size && this.buffer[i] == 32) {
                this.index = i + 1;
            } else {
                return;
            }
        }
    }

    public void skipToken() {
        while (true) {
            int i = this.index;
            if (i < this.size && this.buffer[i] != 32) {
                this.index = i + 1;
            } else {
                return;
            }
        }
    }

    public void skip(int i) {
        this.index += i;
    }

    public byte peekByte() {
        int i = this.index;
        if (i < this.size) {
            return this.buffer[i];
        }
        return (byte) 0;
    }

    public byte readByte() {
        int i = this.index;
        if (i >= this.size) {
            return (byte) 0;
        }
        byte[] bArr = this.buffer;
        this.index = i + 1;
        return bArr[i];
    }

    public String readAtom() {
        return readAtom((char) 0);
    }

    public String readAtom(char c) {
        byte b;
        skipSpaces();
        int i = this.index;
        if (i >= this.size) {
            return null;
        }
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || (b = this.buffer[i2]) <= 32 || b == 40 || b == 41 || b == 37 || b == 42 || b == 34 || b == 92 || b == Byte.MAX_VALUE || (c != 0 && b == c)) {
                break;
            }
            this.index++;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public String readString(char c) {
        skipSpaces();
        int i = this.index;
        if (i >= this.size) {
            return null;
        }
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || this.buffer[i2] == c) {
                break;
            }
            this.index = i2 + 1;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public String[] readStringList() {
        byte[] bArr;
        int i;
        skipSpaces();
        byte[] bArr2 = this.buffer;
        int i2 = this.index;
        if (bArr2[i2] != 40) {
            return null;
        }
        this.index = i2 + 1;
        Vector vector = new Vector();
        do {
            vector.addElement(readString());
            bArr = this.buffer;
            i = this.index;
            this.index = i + 1;
        } while (bArr[i] != 41);
        int size = vector.size();
        if (size <= 0) {
            return null;
        }
        String[] strArr = new String[size];
        vector.copyInto(strArr);
        return strArr;
    }

    public int readNumber() {
        skipSpaces();
        int i = this.index;
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || !Character.isDigit((char) this.buffer[i2])) {
                break;
            }
            this.index++;
        }
        int i3 = this.index;
        if (i3 <= i) {
            return -1;
        }
        try {
            return ASCIIUtility.parseInt(this.buffer, i, i3);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public long readLong() {
        skipSpaces();
        int i = this.index;
        while (true) {
            int i2 = this.index;
            if (i2 >= this.size || !Character.isDigit((char) this.buffer[i2])) {
                break;
            }
            this.index++;
        }
        int i3 = this.index;
        if (i3 <= i) {
            return -1L;
        }
        try {
            return ASCIIUtility.parseLong(this.buffer, i, i3);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public String readString() {
        return (String) parseString(false, true);
    }

    public ByteArrayInputStream readBytes() {
        ByteArray readByteArray = readByteArray();
        if (readByteArray != null) {
            return readByteArray.toByteArrayInputStream();
        }
        return null;
    }

    public ByteArray readByteArray() {
        if (!isContinuation()) {
            return (ByteArray) parseString(false, false);
        }
        skipSpaces();
        byte[] bArr = this.buffer;
        int i = this.index;
        return new ByteArray(bArr, i, this.size - i);
    }

    public String readAtomString() {
        return (String) parseString(true, true);
    }

    private Object parseString(boolean z, boolean z2) {
        byte[] bArr;
        int i;
        byte[] bArr2;
        int i2;
        skipSpaces();
        byte[] bArr3 = this.buffer;
        int i3 = this.index;
        byte b = bArr3[i3];
        if (b == 34) {
            this.index = i3 + 1;
            int i4 = this.index;
            int i5 = i4;
            while (true) {
                bArr2 = this.buffer;
                i2 = this.index;
                byte b2 = bArr2[i2];
                if (b2 == 34) {
                    break;
                }
                if (b2 == 92) {
                    this.index = i2 + 1;
                }
                int i6 = this.index;
                if (i6 != i5) {
                    byte[] bArr4 = this.buffer;
                    bArr4[i5] = bArr4[i6];
                }
                i5++;
                this.index++;
            }
            this.index = i2 + 1;
            if (z2) {
                return ASCIIUtility.toString(bArr2, i4, i5);
            }
            return new ByteArray(bArr2, i4, i5 - i4);
        } else if (b == 123) {
            int i7 = i3 + 1;
            this.index = i7;
            while (true) {
                bArr = this.buffer;
                i = this.index;
                if (bArr[i] == 125) {
                    try {
                        break;
                    } catch (NumberFormatException unused) {
                        return null;
                    }
                } else {
                    this.index = i + 1;
                }
            }
            int parseInt = ASCIIUtility.parseInt(bArr, i7, i);
            int i8 = this.index + 3;
            int i9 = i8 + parseInt;
            this.index = i9;
            if (z2) {
                return ASCIIUtility.toString(this.buffer, i8, i9);
            }
            return new ByteArray(this.buffer, i8, parseInt);
        } else if (z) {
            return z2 ? readAtom() : new ByteArray(this.buffer, i3, this.index);
        } else if (b != 78 && b != 110) {
            return null;
        } else {
            this.index += 3;
            return null;
        }
    }

    public int getType() {
        return this.type;
    }

    public boolean isContinuation() {
        return (this.type & 3) == 1;
    }

    public boolean isTagged() {
        return (this.type & 3) == 2;
    }

    public boolean isUnTagged() {
        return (this.type & 3) == 3;
    }

    public boolean isOK() {
        return (this.type & 28) == 4;
    }

    public boolean isNO() {
        return (this.type & 28) == 8;
    }

    public boolean isBAD() {
        return (this.type & 28) == 12;
    }

    public boolean isBYE() {
        return (this.type & 28) == 16;
    }

    public boolean isSynthetic() {
        return (this.type & 32) == 32;
    }

    public String getTag() {
        return this.tag;
    }

    public String getRest() {
        skipSpaces();
        return ASCIIUtility.toString(this.buffer, this.index, this.size);
    }

    public void reset() {
        this.index = this.pindex;
    }

    public String toString() {
        return ASCIIUtility.toString(this.buffer, 0, this.size);
    }
}
