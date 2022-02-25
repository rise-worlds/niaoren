package org.apache.tools.ant.types.selectors;

/* loaded from: classes2.dex */
public interface SelectorScanner {
    String[] getDeselectedDirectories();

    String[] getDeselectedFiles();

    void setSelectors(FileSelector[] fileSelectorArr);
}
