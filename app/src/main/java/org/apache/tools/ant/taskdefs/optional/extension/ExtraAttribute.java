package org.apache.tools.ant.taskdefs.optional.extension;

import org.apache.tools.ant.BuildException;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class ExtraAttribute {
    private String name;
    private String value;

    public void setName(String str) {
        this.name = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getValue() {
        return this.value;
    }

    public void validate() throws BuildException {
        if (this.name == null) {
            throw new BuildException("Missing name from parameter.");
        } else if (this.value == null) {
            throw new BuildException("Missing value from parameter " + this.name + Consts.f23430h);
        }
    }
}
