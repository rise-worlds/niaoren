package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class Basename extends Task {
    private File file;
    private String property;
    private String suffix;

    public void setFile(File file) {
        this.file = file;
    }

    public void setProperty(String str) {
        this.property = str;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property != null) {
            File file = this.file;
            if (file != null) {
                String name = file.getName();
                String str = this.suffix;
                if (str != null && name.endsWith(str)) {
                    int length = name.length() - this.suffix.length();
                    if (length > 0 && this.suffix.charAt(0) != '.' && name.charAt(length - 1) == '.') {
                        length--;
                    }
                    name = name.substring(0, length);
                }
                getProject().setNewProperty(this.property, name);
                return;
            }
            throw new BuildException("file attribute required", getLocation());
        }
        throw new BuildException("property attribute required", getLocation());
    }
}
