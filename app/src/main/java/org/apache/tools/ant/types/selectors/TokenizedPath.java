package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.SymbolicLinkUtils;

/* loaded from: classes2.dex */
public class TokenizedPath {
    private final String path;
    private final String[] tokenizedPath;
    public static final TokenizedPath EMPTY_PATH = new TokenizedPath("", new String[0]);
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final SymbolicLinkUtils SYMLINK_UTILS = SymbolicLinkUtils.getSymbolicLinkUtils();
    private static final boolean[] CS_SCAN_ONLY = {true};
    private static final boolean[] CS_THEN_NON_CS = {true, false};

    public TokenizedPath(String str) {
        this(str, SelectorUtils.tokenizePathAsArray(str));
    }

    public TokenizedPath(TokenizedPath tokenizedPath, String str) {
        if (tokenizedPath.path.length() > 0) {
            String str2 = tokenizedPath.path;
            if (str2.charAt(str2.length() - 1) != File.separatorChar) {
                this.path = tokenizedPath.path + File.separatorChar + str;
                this.tokenizedPath = new String[tokenizedPath.tokenizedPath.length + 1];
                String[] strArr = tokenizedPath.tokenizedPath;
                System.arraycopy(strArr, 0, this.tokenizedPath, 0, strArr.length);
                this.tokenizedPath[tokenizedPath.tokenizedPath.length] = str;
            }
        }
        this.path = tokenizedPath.path + str;
        this.tokenizedPath = new String[tokenizedPath.tokenizedPath.length + 1];
        String[] strArr2 = tokenizedPath.tokenizedPath;
        System.arraycopy(strArr2, 0, this.tokenizedPath, 0, strArr2.length);
        this.tokenizedPath[tokenizedPath.tokenizedPath.length] = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenizedPath(String str, String[] strArr) {
        this.path = str;
        this.tokenizedPath = strArr;
    }

    public String toString() {
        return this.path;
    }

    public int depth() {
        return this.tokenizedPath.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getTokens() {
        return this.tokenizedPath;
    }

    public File findFile(File file, boolean z) {
        String[] strArr = this.tokenizedPath;
        if (FileUtils.isAbsolutePath(this.path)) {
            if (file == null) {
                String[] dissect = FILE_UTILS.dissect(this.path);
                File file2 = new File(dissect[0]);
                strArr = SelectorUtils.tokenizePathAsArray(dissect[1]);
                file = file2;
            } else {
                File normalize = FILE_UTILS.normalize(this.path);
                String removeLeadingPath = FILE_UTILS.removeLeadingPath(file, normalize);
                if (removeLeadingPath.equals(normalize.getAbsolutePath())) {
                    return null;
                }
                strArr = SelectorUtils.tokenizePathAsArray(removeLeadingPath);
            }
        }
        return findFile(file, strArr, z);
    }

    public boolean isSymlink(File file) {
        File file2 = file;
        int i = 0;
        while (true) {
            String[] strArr = this.tokenizedPath;
            if (i >= strArr.length) {
                return false;
            }
            if (file2 != null) {
                try {
                    if (SYMLINK_UTILS.isSymbolicLink(file2, strArr[i])) {
                        return true;
                    }
                } catch (IOException unused) {
                    System.err.println("IOException caught while checking for links, couldn't get canonical path!");
                }
            }
            if (file2 == null && SYMLINK_UTILS.isSymbolicLink(this.tokenizedPath[i])) {
                return true;
            }
            file2 = new File(file2, this.tokenizedPath[i]);
            i++;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof TokenizedPath) && this.path.equals(((TokenizedPath) obj).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    private static File findFile(File file, String[] strArr, boolean z) {
        File file2 = file;
        int i = 0;
        while (i < strArr.length) {
            if (!file2.isDirectory()) {
                return null;
            }
            String[] list = file2.list();
            if (list != null) {
                boolean[] zArr = z ? CS_SCAN_ONLY : CS_THEN_NON_CS;
                File file3 = file2;
                boolean z2 = false;
                int i2 = 0;
                while (!z2 && i2 < zArr.length) {
                    File file4 = file3;
                    for (int i3 = 0; !z2 && i3 < list.length; i3++) {
                        if (zArr[i2]) {
                            if (!list[i3].equals(strArr[i])) {
                            }
                            file4 = new File(file4, list[i3]);
                            z2 = true;
                        } else {
                            if (!list[i3].equalsIgnoreCase(strArr[i])) {
                            }
                            file4 = new File(file4, list[i3]);
                            z2 = true;
                        }
                    }
                    i2++;
                    file3 = file4;
                }
                if (!z2) {
                    return null;
                }
                i++;
                file2 = file3;
            } else {
                throw new BuildException("IO error scanning directory " + file2.getAbsolutePath());
            }
        }
        if (strArr.length != 0 || file2.isDirectory()) {
            return file2;
        }
        return null;
    }

    public TokenizedPattern toPattern() {
        return new TokenizedPattern(this.path, this.tokenizedPath);
    }
}
