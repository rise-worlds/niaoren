package org.apache.tools.ant;

/* loaded from: classes2.dex */
public class ExitStatusException extends BuildException {
    private static final long serialVersionUID = 7760846806886585968L;
    private int status;

    public ExitStatusException(int i) {
        this.status = i;
    }

    public ExitStatusException(String str, int i) {
        super(str);
        this.status = i;
    }

    public ExitStatusException(String str, int i, Location location) {
        super(str, location);
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }
}
