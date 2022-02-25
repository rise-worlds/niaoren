package org.apache.commons.p105io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.EmptyFileFilter */
/* loaded from: classes2.dex */
public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter EMPTY = new EmptyFileFilter();
    public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);
    private static final long serialVersionUID = 3631422087512832211L;

    protected EmptyFileFilter() {
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (!file.isDirectory()) {
            return file.length() == 0;
        }
        File[] listFiles = file.listFiles();
        return listFiles == null || listFiles.length == 0;
    }
}
