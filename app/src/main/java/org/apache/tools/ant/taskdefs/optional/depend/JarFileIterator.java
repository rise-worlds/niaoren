package org.apache.tools.ant.taskdefs.optional.depend;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class JarFileIterator implements ClassFileIterator {
    private ZipInputStream jarStream;

    public JarFileIterator(InputStream inputStream) throws IOException {
        this.jarStream = new ZipInputStream(inputStream);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.depend.ClassFileIterator
    public ClassFile getNextClassFile() {
        try {
            ZipEntry nextEntry = this.jarStream.getNextEntry();
            ClassFile classFile = null;
            while (classFile == null && nextEntry != null) {
                String name = nextEntry.getName();
                if (nextEntry.isDirectory() || !name.endsWith(".class")) {
                    nextEntry = this.jarStream.getNextEntry();
                } else {
                    classFile = new ClassFile();
                    classFile.read(this.jarStream);
                }
            }
            return classFile;
        } catch (IOException e) {
            String message = e.getMessage();
            String name2 = e.getClass().getName();
            if (message != null) {
                name2 = name2 + ": " + message;
            }
            throw new RuntimeException("Problem reading JAR file: " + name2);
        }
    }
}
