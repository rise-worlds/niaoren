package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class Dirname extends Task {
    private File file;
    private String property;

    public void setFile(File file) {
        this.file = file;
    }

    public void setProperty(String str) {
        this.property = str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property != null) {
            File file = this.file;
            if (file != null) {
                getProject().setNewProperty(this.property, file.getParent());
                return;
            }
            throw new BuildException("file attribute required", getLocation());
        }
        throw new BuildException("property attribute required", getLocation());
    }
}
