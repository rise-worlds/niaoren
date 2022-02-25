package org.apache.tools.ant.taskdefs.cvslib;

/* loaded from: classes2.dex */
public class CvsTagEntry {
    private String filename;
    private String prevRevision;
    private String revision;

    public CvsTagEntry(String str) {
        this(str, null, null);
    }

    public CvsTagEntry(String str, String str2) {
        this(str, str2, null);
    }

    public CvsTagEntry(String str, String str2, String str3) {
        this.filename = str;
        this.revision = str2;
        this.prevRevision = str3;
    }

    public String getFile() {
        return this.filename;
    }

    public String getRevision() {
        return this.revision;
    }

    public String getPreviousRevision() {
        return this.prevRevision;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.filename);
        if (this.revision == null) {
            stringBuffer.append(" was removed");
            if (this.prevRevision != null) {
                stringBuffer.append("; previous revision was ");
                stringBuffer.append(this.prevRevision);
            }
        } else if (this.prevRevision == null) {
            stringBuffer.append(" is new; current revision is ");
            stringBuffer.append(this.revision);
        } else {
            stringBuffer.append(" has changed from ");
            stringBuffer.append(this.prevRevision);
            stringBuffer.append(" to ");
            stringBuffer.append(this.revision);
        }
        return stringBuffer.toString();
    }
}
