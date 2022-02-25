package org.apache.tools.ant.taskdefs.optional.jlink;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.util.FileUtils;

/* renamed from: org.apache.tools.ant.taskdefs.optional.jlink.jlink */
/* loaded from: classes2.dex */
public class C3218jlink {
    private static final int BUFFER_SIZE = 8192;
    private static final int VECTOR_INIT_SIZE = 10;
    private String outfile = null;
    private Vector mergefiles = new Vector(10);
    private Vector addfiles = new Vector(10);
    private boolean compression = false;
    byte[] buffer = new byte[8192];

    public void setOutfile(String str) {
        if (str != null) {
            this.outfile = str;
        }
    }

    public void addMergeFile(String str) {
        if (str != null) {
            this.mergefiles.addElement(str);
        }
    }

    public void addAddFile(String str) {
        if (str != null) {
            this.addfiles.addElement(str);
        }
    }

    public void addMergeFiles(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                addMergeFile(str);
            }
        }
    }

    public void addAddFiles(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                addAddFile(str);
            }
        }
    }

    public void setCompression(boolean z) {
        this.compression = z;
    }

    public void link() throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(this.outfile));
        if (this.compression) {
            zipOutputStream.setMethod(8);
            zipOutputStream.setLevel(-1);
        } else {
            zipOutputStream.setMethod(0);
        }
        Enumeration elements = this.mergefiles.elements();
        while (elements.hasMoreElements()) {
            String str = (String) elements.nextElement();
            File file = new File(str);
            if (file.getName().endsWith(".jar") || file.getName().endsWith(".zip")) {
                mergeZipJarContents(zipOutputStream, file);
            } else {
                addAddFile(str);
            }
        }
        Enumeration elements2 = this.addfiles.elements();
        while (elements2.hasMoreElements()) {
            File file2 = new File((String) elements2.nextElement());
            if (file2.isDirectory()) {
                addDirContents(zipOutputStream, file2, file2.getName() + IOUtils.DIR_SEPARATOR_UNIX, this.compression);
            } else {
                addFile(zipOutputStream, file2, "", this.compression);
            }
        }
        FileUtils.close(zipOutputStream);
    }

    public static void main(String[] strArr) {
        if (strArr.length < 2) {
            System.out.println("usage: jlink output input1 ... inputN");
            System.exit(1);
        }
        C3218jlink jlinkVar = new C3218jlink();
        jlinkVar.setOutfile(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            jlinkVar.addMergeFile(strArr[i]);
        }
        try {
            jlinkVar.link();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    private void mergeZipJarContents(ZipOutputStream zipOutputStream, File file) throws IOException {
        if (file.exists()) {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (zipEntry.getName().indexOf("META-INF") < 0) {
                    try {
                        zipOutputStream.putNextEntry(processEntry(zipFile, zipEntry));
                        InputStream inputStream = zipFile.getInputStream(zipEntry);
                        int length = this.buffer.length;
                        while (true) {
                            int read = inputStream.read(this.buffer, 0, length);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(this.buffer, 0, read);
                        }
                        inputStream.close();
                        zipOutputStream.closeEntry();
                    } catch (ZipException e) {
                        if (e.getMessage().indexOf("duplicate") < 0) {
                            throw e;
                        }
                    }
                }
            }
            zipFile.close();
        }
    }

    private void addDirContents(ZipOutputStream zipOutputStream, File file, String str, boolean z) throws IOException {
        String[] list;
        for (String str2 : file.list()) {
            File file2 = new File(file, str2);
            if (file2.isDirectory()) {
                addDirContents(zipOutputStream, file2, str + str2 + IOUtils.DIR_SEPARATOR_UNIX, z);
            } else {
                addFile(zipOutputStream, file2, str, z);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r2 == null) goto L_0x0047;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getEntryName(java.io.File r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = r7.getName()
            java.lang.String r1 = ".class"
            boolean r1 = r0.endsWith(r1)
            if (r1 != 0) goto L_0x0047
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: all -> 0x003b, IOException -> 0x0043
            r2.<init>(r7)     // Catch: all -> 0x003b, IOException -> 0x0043
            java.lang.String r1 = org.apache.tools.ant.taskdefs.optional.jlink.ClassNameReader.getClassName(r2)     // Catch: all -> 0x0039, IOException -> 0x0044
            if (r1 == 0) goto L_0x0035
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0039, IOException -> 0x0044
            r3.<init>()     // Catch: all -> 0x0039, IOException -> 0x0044
            r4 = 46
            r5 = 47
            java.lang.String r1 = r1.replace(r4, r5)     // Catch: all -> 0x0039, IOException -> 0x0044
            r3.append(r1)     // Catch: all -> 0x0039, IOException -> 0x0044
            java.lang.String r1 = ".class"
            r3.append(r1)     // Catch: all -> 0x0039, IOException -> 0x0044
            java.lang.String r7 = r3.toString()     // Catch: all -> 0x0039, IOException -> 0x0044
            r2.close()     // Catch: IOException -> 0x0034
        L_0x0034:
            return r7
        L_0x0035:
            r2.close()     // Catch: IOException -> 0x0047
            goto L_0x0047
        L_0x0039:
            r7 = move-exception
            goto L_0x003d
        L_0x003b:
            r7 = move-exception
            r2 = r1
        L_0x003d:
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch: IOException -> 0x0042
        L_0x0042:
            throw r7
        L_0x0043:
            r2 = r1
        L_0x0044:
            if (r2 == 0) goto L_0x0047
            goto L_0x0035
        L_0x0047:
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "From "
            r2.append(r3)
            java.lang.String r7 = r7.getPath()
            r2.append(r7)
            java.lang.String r7 = " and prefix "
            r2.append(r7)
            r2.append(r8)
            java.lang.String r7 = ", creating entry "
            r2.append(r7)
            r2.append(r8)
            r2.append(r0)
            java.lang.String r7 = r2.toString()
            r1.println(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r8)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.optional.jlink.C3218jlink.getEntryName(java.io.File, java.lang.String):java.lang.String");
    }

    private void addFile(ZipOutputStream zipOutputStream, File file, String str, boolean z) throws IOException {
        if (file.exists()) {
            ZipEntry zipEntry = new ZipEntry(getEntryName(file, str));
            zipEntry.setTime(file.lastModified());
            zipEntry.setSize(file.length());
            if (!z) {
                zipEntry.setCrc(calcChecksum(file));
            }
            addToOutputStream(zipOutputStream, new FileInputStream(file), zipEntry);
        }
    }

    private void addToOutputStream(ZipOutputStream zipOutputStream, InputStream inputStream, ZipEntry zipEntry) throws IOException {
        try {
            zipOutputStream.putNextEntry(zipEntry);
            while (true) {
                int read = inputStream.read(this.buffer);
                if (read > 0) {
                    zipOutputStream.write(this.buffer, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    inputStream.close();
                    return;
                }
            }
        } catch (ZipException unused) {
            inputStream.close();
        }
    }

    private ZipEntry processEntry(ZipFile zipFile, ZipEntry zipEntry) {
        String name = zipEntry.getName();
        if (!zipEntry.isDirectory() && !name.endsWith(".class")) {
            try {
                InputStream inputStream = zipFile.getInputStream(zipFile.getEntry(name));
                String className = ClassNameReader.getClassName(inputStream);
                inputStream.close();
                if (className != null) {
                    name = className.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + ".class";
                }
            } catch (IOException unused) {
            }
        }
        ZipEntry zipEntry2 = new ZipEntry(name);
        zipEntry2.setTime(zipEntry.getTime());
        zipEntry2.setExtra(zipEntry.getExtra());
        zipEntry2.setComment(zipEntry.getComment());
        zipEntry2.setTime(zipEntry.getTime());
        if (this.compression) {
            zipEntry2.setMethod(8);
        } else {
            zipEntry2.setMethod(0);
            zipEntry2.setCrc(zipEntry.getCrc());
            zipEntry2.setSize(zipEntry.getSize());
        }
        return zipEntry2;
    }

    private long calcChecksum(File file) throws IOException {
        return calcChecksum(new BufferedInputStream(new FileInputStream(file)));
    }

    private long calcChecksum(InputStream inputStream) throws IOException {
        CRC32 crc32 = new CRC32();
        int length = this.buffer.length;
        while (true) {
            int read = inputStream.read(this.buffer, 0, length);
            if (read > 0) {
                crc32.update(this.buffer, 0, read);
            } else {
                inputStream.close();
                return crc32.getValue();
            }
        }
    }
}
