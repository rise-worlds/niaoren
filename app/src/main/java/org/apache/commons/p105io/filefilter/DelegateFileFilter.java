package org.apache.commons.p105io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.DelegateFileFilter */
/* loaded from: classes2.dex */
public class DelegateFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -8723373124984771318L;
    private final FileFilter fileFilter;
    private final FilenameFilter filenameFilter;

    public DelegateFileFilter(FilenameFilter filenameFilter) {
        if (filenameFilter != null) {
            this.filenameFilter = filenameFilter;
            this.fileFilter = null;
            return;
        }
        throw new IllegalArgumentException("The FilenameFilter must not be null");
    }

    public DelegateFileFilter(FileFilter fileFilter) {
        if (fileFilter != null) {
            this.fileFilter = fileFilter;
            this.filenameFilter = null;
            return;
        }
        throw new IllegalArgumentException("The FileFilter must not be null");
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        FileFilter fileFilter = this.fileFilter;
        if (fileFilter != null) {
            return fileFilter.accept(file);
        }
        return super.accept(file);
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        FilenameFilter filenameFilter = this.filenameFilter;
        if (filenameFilter != null) {
            return filenameFilter.accept(file, str);
        }
        return super.accept(file, str);
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter
    public String toString() {
        Object obj = this.fileFilter;
        if (obj == null) {
            obj = this.filenameFilter;
        }
        String obj2 = obj.toString();
        return super.toString() + "(" + obj2 + ")";
    }
}
