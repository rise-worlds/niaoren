package org.apache.tools.ant.taskdefs.optional.jlink;

import java.io.DataInput;
import java.io.IOException;

/* compiled from: ClassNameReader.java */
/* loaded from: classes2.dex */
class ConstantPool {
    static final byte CLASS = 7;
    static final byte DOUBLE = 6;
    static final byte FIELDREF = 9;
    static final byte FLOAT = 4;
    static final byte INTEGER = 3;
    static final byte INTERFACEMETHODREF = 11;
    static final byte LONG = 5;
    static final byte METHODREF = 10;
    static final byte NAMEANDTYPE = 12;
    static final byte STRING = 8;
    static final byte UNUSED = 2;
    static final byte UTF8 = 1;
    byte[] types;
    Object[] values;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPool(DataInput dataInput) throws IOException {
        int readUnsignedShort = dataInput.readUnsignedShort();
        this.types = new byte[readUnsignedShort];
        this.values = new Object[readUnsignedShort];
        int i = 1;
        while (i < readUnsignedShort) {
            byte readByte = dataInput.readByte();
            this.types[i] = readByte;
            switch (readByte) {
                case 1:
                    this.values[i] = dataInput.readUTF();
                    break;
                case 3:
                    this.values[i] = new Integer(dataInput.readInt());
                    break;
                case 4:
                    this.values[i] = new Float(dataInput.readFloat());
                    break;
                case 5:
                    this.values[i] = new Long(dataInput.readLong());
                    i++;
                    break;
                case 6:
                    this.values[i] = new Double(dataInput.readDouble());
                    i++;
                    break;
                case 7:
                case 8:
                    this.values[i] = new Integer(dataInput.readUnsignedShort());
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                    this.values[i] = new Integer(dataInput.readInt());
                    break;
            }
            i++;
        }
    }
}
