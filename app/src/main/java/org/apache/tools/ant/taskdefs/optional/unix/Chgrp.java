package org.apache.tools.ant.taskdefs.optional.unix;

import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class Chgrp extends AbstractAccessTask {
    private boolean haveGroup = false;

    public Chgrp() {
        super.setExecutable("chgrp");
    }

    public void setGroup(String str) {
        createArg().setValue(str);
        this.haveGroup = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecuteOn, org.apache.tools.ant.taskdefs.ExecTask
    public void checkConfiguration() {
        if (this.haveGroup) {
            super.checkConfiguration();
            return;
        }
        throw new BuildException("Required attribute group not set in chgrp", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setExecutable(String str) {
        throw new BuildException(getTaskType() + " doesn't support the executable attribute", getLocation());
    }
}
