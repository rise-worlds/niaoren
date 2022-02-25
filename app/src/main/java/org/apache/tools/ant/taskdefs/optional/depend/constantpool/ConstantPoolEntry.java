package org.apache.tools.ant.taskdefs.optional.depend.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class ConstantPoolEntry {
    public static final int CONSTANT_CLASS = 7;
    public static final int CONSTANT_DOUBLE = 6;
    public static final int CONSTANT_FIELDREF = 9;
    public static final int CONSTANT_FLOAT = 4;
    public static final int CONSTANT_INTEGER = 3;
    public static final int CONSTANT_INTERFACEMETHODREF = 11;
    public static final int CONSTANT_INVOKEDYNAMIC = 18;
    public static final int CONSTANT_LONG = 5;
    public static final int CONSTANT_METHODHANDLE = 15;
    public static final int CONSTANT_METHODREF = 10;
    public static final int CONSTANT_METHODTYPE = 16;
    public static final int CONSTANT_NAMEANDTYPE = 12;
    public static final int CONSTANT_STRING = 8;
    public static final int CONSTANT_UTF8 = 1;
    private int numEntries;
    private boolean resolved = false;
    private int tag;

    public abstract void read(DataInputStream dataInputStream) throws IOException;

    public ConstantPoolEntry(int i, int i2) {
        this.tag = i;
        this.numEntries = i2;
    }

    public static ConstantPoolEntry readEntry(DataInputStream dataInputStream) throws IOException {
        ConstantPoolEntry constantPoolEntry;
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        switch (readUnsignedByte) {
            case 1:
                constantPoolEntry = new Utf8CPInfo();
                break;
            case 2:
            case 13:
            case 14:
            case 17:
            default:
                throw new ClassFormatError("Invalid Constant Pool entry Type " + readUnsignedByte);
            case 3:
                constantPoolEntry = new IntegerCPInfo();
                break;
            case 4:
                constantPoolEntry = new FloatCPInfo();
                break;
            case 5:
                constantPoolEntry = new LongCPInfo();
                break;
            case 6:
                constantPoolEntry = new DoubleCPInfo();
                break;
            case 7:
                constantPoolEntry = new ClassCPInfo();
                break;
            case 8:
                constantPoolEntry = new StringCPInfo();
                break;
            case 9:
                constantPoolEntry = new FieldRefCPInfo();
                break;
            case 10:
                constantPoolEntry = new MethodRefCPInfo();
                break;
            case 11:
                constantPoolEntry = new InterfaceMethodRefCPInfo();
                break;
            case 12:
                constantPoolEntry = new NameAndTypeCPInfo();
                break;
            case 15:
                constantPoolEntry = new MethodHandleCPInfo();
                break;
            case 16:
                constantPoolEntry = new MethodTypeCPInfo();
                break;
            case 18:
                constantPoolEntry = new InvokeDynamicCPInfo();
                break;
        }
        constantPoolEntry.read(dataInputStream);
        return constantPoolEntry;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public void resolve(ConstantPool constantPool) {
        this.resolved = true;
    }

    public int getTag() {
        return this.tag;
    }

    public final int getNumEntries() {
        return this.numEntries;
    }
}
