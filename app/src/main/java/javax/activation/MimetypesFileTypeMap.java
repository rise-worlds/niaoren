package javax.activation;

import com.sun.activation.registries.LogSupport;
import com.sun.activation.registries.MimeTypeFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class MimetypesFileTypeMap extends FileTypeMap {
    private static final int PROG = 0;
    private static MimeTypeFile defDB = null;
    private static String defaultType = "application/octet-stream";

    /* renamed from: DB */
    private MimeTypeFile[] f14647DB;

    public MimetypesFileTypeMap() {
        Vector vector = new Vector(5);
        vector.addElement(null);
        LogSupport.log("MimetypesFileTypeMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (property != null) {
                MimeTypeFile loadFile = loadFile(String.valueOf(property) + File.separator + ".mime.types");
                if (loadFile != null) {
                    vector.addElement(loadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MimetypesFileTypeMap: load SYS");
        try {
            MimeTypeFile loadFile2 = loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "mime.types");
            if (loadFile2 != null) {
                vector.addElement(loadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MimetypesFileTypeMap: load JAR");
        loadAllResources(vector, "mime.types");
        LogSupport.log("MimetypesFileTypeMap: load DEF");
        synchronized (MimetypesFileTypeMap.class) {
            if (defDB == null) {
                defDB = loadResource("/mimetypes.default");
            }
        }
        MimeTypeFile mimeTypeFile = defDB;
        if (mimeTypeFile != null) {
            vector.addElement(mimeTypeFile);
        }
        this.f14647DB = new MimeTypeFile[vector.size()];
        vector.copyInto(this.f14647DB);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        if (r1 != null) goto L_0x0046;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0086, code lost:
        if (r1 != null) goto L_0x0046;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.sun.activation.registries.MimeTypeFile loadResource(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.Class r1 = r5.getClass()     // Catch: all -> 0x004e, SecurityException -> 0x0051, IOException -> 0x006d
            java.io.InputStream r1 = javax.activation.SecuritySupport.getResourceAsStream(r1, r6)     // Catch: all -> 0x004e, SecurityException -> 0x0051, IOException -> 0x006d
            if (r1 == 0) goto L_0x002d
            com.sun.activation.registries.MimeTypeFile r2 = new com.sun.activation.registries.MimeTypeFile     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r2.<init>(r1)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            if (r3 == 0) goto L_0x0027
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r4 = "MimetypesFileTypeMap: successfully loaded mime types file: "
            r3.<init>(r4)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r3.append(r6)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r3 = r3.toString()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r3)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch: IOException -> 0x002c
        L_0x002c:
            return r2
        L_0x002d:
            boolean r2 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            if (r2 == 0) goto L_0x0044
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r3 = "MimetypesFileTypeMap: not loading mime types file: "
            r2.<init>(r3)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r2.append(r6)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r2 = r2.toString()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r2)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
        L_0x0044:
            if (r1 == 0) goto L_0x0089
        L_0x0046:
            r1.close()     // Catch: IOException -> 0x0089
            goto L_0x0089
        L_0x004a:
            r2 = move-exception
            goto L_0x0053
        L_0x004c:
            r2 = move-exception
            goto L_0x006f
        L_0x004e:
            r6 = move-exception
            r1 = r0
            goto L_0x008b
        L_0x0051:
            r2 = move-exception
            r1 = r0
        L_0x0053:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: all -> 0x008a
            if (r3 == 0) goto L_0x006a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x008a
            java.lang.String r4 = "MimetypesFileTypeMap: can't load "
            r3.<init>(r4)     // Catch: all -> 0x008a
            r3.append(r6)     // Catch: all -> 0x008a
            java.lang.String r6 = r3.toString()     // Catch: all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r6, r2)     // Catch: all -> 0x008a
        L_0x006a:
            if (r1 == 0) goto L_0x0089
            goto L_0x0046
        L_0x006d:
            r2 = move-exception
            r1 = r0
        L_0x006f:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: all -> 0x008a
            if (r3 == 0) goto L_0x0086
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x008a
            java.lang.String r4 = "MimetypesFileTypeMap: can't load "
            r3.<init>(r4)     // Catch: all -> 0x008a
            r3.append(r6)     // Catch: all -> 0x008a
            java.lang.String r6 = r3.toString()     // Catch: all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r6, r2)     // Catch: all -> 0x008a
        L_0x0086:
            if (r1 == 0) goto L_0x0089
            goto L_0x0046
        L_0x0089:
            return r0
        L_0x008a:
            r6 = move-exception
        L_0x008b:
            if (r1 == 0) goto L_0x0090
            r1.close()     // Catch: IOException -> 0x0090
        L_0x0090:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MimetypesFileTypeMap.loadResource(java.lang.String):com.sun.activation.registries.MimeTypeFile");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadAllResources(java.util.Vector r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MimetypesFileTypeMap.loadAllResources(java.util.Vector, java.lang.String):void");
    }

    private MimeTypeFile loadFile(String str) {
        try {
            return new MimeTypeFile(str);
        } catch (IOException unused) {
            return null;
        }
    }

    public MimetypesFileTypeMap(String str) throws IOException {
        this();
        this.f14647DB[0] = new MimeTypeFile(str);
    }

    public MimetypesFileTypeMap(InputStream inputStream) {
        this();
        try {
            this.f14647DB[0] = new MimeTypeFile(inputStream);
        } catch (IOException unused) {
        }
    }

    public synchronized void addMimeTypes(String str) {
        if (this.f14647DB[0] == null) {
            this.f14647DB[0] = new MimeTypeFile();
        }
        this.f14647DB[0].appendToRegistry(str);
    }

    @Override // javax.activation.FileTypeMap
    public String getContentType(File file) {
        return getContentType(file.getName());
    }

    @Override // javax.activation.FileTypeMap
    public synchronized String getContentType(String str) {
        String mIMETypeString;
        int lastIndexOf = str.lastIndexOf(Consts.f23430h);
        if (lastIndexOf < 0) {
            return defaultType;
        }
        String substring = str.substring(lastIndexOf + 1);
        if (substring.length() == 0) {
            return defaultType;
        }
        for (int i = 0; i < this.f14647DB.length; i++) {
            if (!(this.f14647DB[i] == null || (mIMETypeString = this.f14647DB[i].getMIMETypeString(substring)) == null)) {
                return mIMETypeString;
            }
        }
        return defaultType;
    }
}
