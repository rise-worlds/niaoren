package org.apache.commons.p105io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.DirectoryFileFilter */
/* loaded from: classes2.dex */
public class DirectoryFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter DIRECTORY;
    public static final IOFileFilter INSTANCE;
    private static final long serialVersionUID = -5148237843784525732L;

    static {
        DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
        DIRECTORY = directoryFileFilter;
        INSTANCE = directoryFileFilter;
    }

    protected DirectoryFileFilter() {
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
