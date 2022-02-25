package org.apache.tools.ant.taskdefs.cvslib;

/* loaded from: classes2.dex */
class RCSFile {
    private String name;
    private String previousRevision;
    private String revision;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RCSFile(String str, String str2) {
        this(str, str2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RCSFile(String str, String str2, String str3) {
        this.name = str;
        this.revision = str2;
        if (!str2.equals(str3)) {
            this.previousRevision = str3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRevision() {
        return this.revision;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPreviousRevision() {
        return this.previousRevision;
    }
}
