package javax.activation;

import java.io.File;

/* loaded from: classes2.dex */
public abstract class FileTypeMap {
    private static FileTypeMap defaultMap;

    public abstract String getContentType(File file);

    public abstract String getContentType(String str);

    public static void setDefaultFileTypeMap(FileTypeMap fileTypeMap) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                securityManager.checkSetFactory();
            } catch (SecurityException e) {
                if (FileTypeMap.class.getClassLoader() != fileTypeMap.getClass().getClassLoader()) {
                    throw e;
                }
            }
        }
        defaultMap = fileTypeMap;
    }

    public static FileTypeMap getDefaultFileTypeMap() {
        if (defaultMap == null) {
            defaultMap = new MimetypesFileTypeMap();
        }
        return defaultMap;
    }
}
