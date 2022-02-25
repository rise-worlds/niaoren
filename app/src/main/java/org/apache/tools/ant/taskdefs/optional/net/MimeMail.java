package org.apache.tools.ant.taskdefs.optional.net;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.email.EmailTask;

/* loaded from: classes2.dex */
public class MimeMail extends EmailTask {
    @Override // org.apache.tools.ant.taskdefs.email.EmailTask, org.apache.tools.ant.Task
    public void execute() throws BuildException {
        log("DEPRECATED - The " + getTaskName() + " task is deprecated. Use the mail task instead.");
        super.execute();
    }
}
