package org.apache.tools.ant.taskdefs.condition;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ManifestTask;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/* loaded from: classes2.dex */
public class IsSigned extends DataType implements Condition {
    private static final int SHORT_SIG_LIMIT = 8;
    private static final String SIG_END = ".SF";
    private static final String SIG_START = "META-INF/";
    private File file;
    private String name;

    public void setFile(File file) {
        this.file = file;
    }

    public void setName(String str) {
        this.name = str;
    }

    public static boolean isSigned(File file, String str) throws IOException {
        Throwable th;
        ZipFile zipFile;
        boolean z;
        try {
            zipFile = new ZipFile(file);
            boolean z2 = true;
            try {
                if (str == null) {
                    Enumeration<ZipEntry> entries = zipFile.getEntries();
                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name.startsWith(SIG_START) && name.endsWith(SIG_END)) {
                            ZipFile.closeQuietly(zipFile);
                            return true;
                        }
                    }
                    ZipFile.closeQuietly(zipFile);
                    return false;
                }
                String replaceInvalidChars = replaceInvalidChars(str);
                StringBuilder sb = new StringBuilder();
                sb.append(SIG_START);
                sb.append(replaceInvalidChars.toUpperCase());
                sb.append(SIG_END);
                boolean z3 = zipFile.getEntry(sb.toString()) != null;
                if (replaceInvalidChars.length() > 8) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(SIG_START);
                    sb2.append(replaceInvalidChars.substring(0, 8).toUpperCase());
                    sb2.append(SIG_END);
                    z = zipFile.getEntry(sb2.toString()) != null;
                } else {
                    z = false;
                }
                if (!z3 && !z) {
                    z2 = false;
                }
                ZipFile.closeQuietly(zipFile);
                return z2;
            } catch (Throwable th2) {
                th = th2;
                ZipFile.closeQuietly(zipFile);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        File file = this.file;
        if (file != null) {
            boolean z = false;
            if (!file.exists()) {
                log("The file \"" + this.file.getAbsolutePath() + "\" does not exist.", 3);
                return false;
            }
            try {
                z = isSigned(this.file, this.name);
            } catch (IOException e) {
                log("Got IOException reading file \"" + this.file.getAbsolutePath() + "\"" + e, 1);
            }
            if (z) {
                log("File \"" + this.file.getAbsolutePath() + "\" is signed.", 3);
            }
            return z;
        }
        throw new BuildException("The file attribute must be set.");
    }

    private static String replaceInvalidChars(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (ManifestTask.VALID_ATTRIBUTE_CHARS.indexOf(charAt) < 0) {
                stringBuffer.append("_");
                z = true;
            } else {
                stringBuffer.append(charAt);
            }
        }
        return z ? stringBuffer.toString() : str;
    }
}
