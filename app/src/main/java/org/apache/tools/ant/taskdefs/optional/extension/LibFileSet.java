package org.apache.tools.ant.taskdefs.optional.extension;

import org.apache.tools.ant.types.FileSet;

/* loaded from: classes2.dex */
public class LibFileSet extends FileSet {
    private boolean includeImpl;
    private boolean includeURL;
    private String urlBase;

    public void setIncludeUrl(boolean z) {
        this.includeURL = z;
    }

    public void setIncludeImpl(boolean z) {
        this.includeImpl = z;
    }

    public void setUrlBase(String str) {
        this.urlBase = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIncludeURL() {
        return this.includeURL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIncludeImpl() {
        return this.includeImpl;
    }

    String getUrlBase() {
        return this.urlBase;
    }
}
