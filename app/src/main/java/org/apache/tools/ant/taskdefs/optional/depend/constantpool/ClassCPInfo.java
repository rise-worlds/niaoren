package org.apache.tools.ant.taskdefs.optional.depend.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class ClassCPInfo extends ConstantPoolEntry {
    private String className;
    private int index;

    public ClassCPInfo() {
        super(7, 1);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.depend.constantpool.ConstantPoolEntry
    public void read(DataInputStream dataInputStream) throws IOException {
        this.index = dataInputStream.readUnsignedShort();
        this.className = "unresolved";
    }

    public String toString() {
        return "Class Constant Pool Entry for " + this.className + "[" + this.index + "]";
    }

    @Override // org.apache.tools.ant.taskdefs.optional.depend.constantpool.ConstantPoolEntry
    public void resolve(ConstantPool constantPool) {
        this.className = ((Utf8CPInfo) constantPool.getEntry(this.index)).getValue();
        super.resolve(constantPool);
    }

    public String getClassName() {
        return this.className;
    }
}
