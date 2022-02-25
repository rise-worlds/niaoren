package org.apache.commons.p105io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;

@Deprecated
/* renamed from: org.apache.commons.io.filefilter.WildcardFilter */
/* loaded from: classes2.dex */
public class WildcardFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -5037645902506953517L;
    private final String[] wildcards;

    public WildcardFilter(String str) {
        if (str != null) {
            this.wildcards = new String[]{str};
            return;
        }
        throw new IllegalArgumentException("The wildcard must not be null");
    }

    public WildcardFilter(String[] strArr) {
        if (strArr != null) {
            this.wildcards = new String[strArr.length];
            System.arraycopy(strArr, 0, this.wildcards, 0, strArr.length);
            return;
        }
        throw new IllegalArgumentException("The wildcard array must not be null");
    }

    public WildcardFilter(List<String> list) {
        if (list != null) {
            this.wildcards = (String[]) list.toArray(new String[list.size()]);
            return;
        }
        throw new IllegalArgumentException("The wildcard list must not be null");
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        if (file != null && new File(file, str).isDirectory()) {
            return false;
        }
        for (String str2 : this.wildcards) {
            if (FilenameUtils.wildcardMatch(str, str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.p105io.filefilter.AbstractFileFilter, org.apache.commons.p105io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return false;
        }
        for (String str : this.wildcards) {
            if (FilenameUtils.wildcardMatch(file.getName(), str)) {
                return true;
            }
        }
        return false;
    }
}
