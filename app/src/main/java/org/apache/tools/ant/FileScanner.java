package org.apache.tools.ant;

import java.io.File;

/* loaded from: classes2.dex */
public interface FileScanner {
    void addDefaultExcludes();

    File getBasedir();

    String[] getExcludedDirectories();

    String[] getExcludedFiles();

    String[] getIncludedDirectories();

    String[] getIncludedFiles();

    String[] getNotIncludedDirectories();

    String[] getNotIncludedFiles();

    void scan() throws IllegalStateException;

    void setBasedir(File file);

    void setBasedir(String str);

    void setCaseSensitive(boolean z);

    void setExcludes(String[] strArr);

    void setIncludes(String[] strArr);
}
