package org.apache.tools.ant.taskdefs.optional.depend;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.optional.depend.constantpool.ClassCPInfo;
import org.apache.tools.ant.taskdefs.optional.depend.constantpool.ConstantPool;
import org.apache.tools.ant.taskdefs.optional.depend.constantpool.ConstantPoolEntry;

/* loaded from: classes2.dex */
public class ClassFile {
    private static final int CLASS_MAGIC = -889275714;
    private String className;
    private ConstantPool constantPool;

    public void read(InputStream inputStream) throws IOException, ClassFormatError {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (dataInputStream.readInt() == CLASS_MAGIC) {
            dataInputStream.readUnsignedShort();
            dataInputStream.readUnsignedShort();
            this.constantPool = new ConstantPool();
            this.constantPool.read(dataInputStream);
            this.constantPool.resolve();
            dataInputStream.readUnsignedShort();
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            dataInputStream.readUnsignedShort();
            this.className = ((ClassCPInfo) this.constantPool.getEntry(readUnsignedShort)).getClassName();
            return;
        }
        throw new ClassFormatError("No Magic Code Found - probably not a Java class file.");
    }

    public Vector<String> getClassRefs() {
        Vector<String> vector = new Vector<>();
        int size = this.constantPool.size();
        for (int i = 0; i < size; i++) {
            ConstantPoolEntry entry = this.constantPool.getEntry(i);
            if (entry != null && entry.getTag() == 7) {
                ClassCPInfo classCPInfo = (ClassCPInfo) entry;
                if (!classCPInfo.getClassName().equals(this.className)) {
                    vector.add(ClassFileUtils.convertSlashName(classCPInfo.getClassName()));
                }
            }
        }
        return vector;
    }

    public String getFullClassName() {
        return ClassFileUtils.convertSlashName(this.className);
    }
}
