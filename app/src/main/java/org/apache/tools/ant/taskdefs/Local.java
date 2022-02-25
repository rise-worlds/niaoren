package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.property.LocalProperties;

/* loaded from: classes2.dex */
public class Local extends Task {
    private String name;

    public void setName(String str) {
        this.name = str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.name != null) {
            LocalProperties.get(getProject()).addLocal(this.name);
            return;
        }
        throw new BuildException("Missing attribute name");
    }
}
