package org.apache.commons.p105io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.TrueFileFilter */
/* loaded from: classes2.dex */
public class TrueFileFilter implements Serializable, IOFileFilter {
    public static final IOFileFilter INSTANCE;
    public static final IOFileFilter TRUE;
    private static final long serialVersionUID = 8782512160909720199L;

    @Override // org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return true;
    }

    @Override // org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return true;
    }

    static {
        TrueFileFilter trueFileFilter = new TrueFileFilter();
        TRUE = trueFileFilter;
        INSTANCE = trueFileFilter;
    }

    protected TrueFileFilter() {
    }
}
