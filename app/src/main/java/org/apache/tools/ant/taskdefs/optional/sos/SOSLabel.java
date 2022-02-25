package org.apache.tools.ant.taskdefs.optional.sos;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class SOSLabel extends SOS {
    public void setVersion(String str) {
        super.setInternalVersion(str);
    }

    public void setLabel(String str) {
        super.setInternalLabel(str);
    }

    public void setComment(String str) {
        super.setInternalComment(str);
    }

    @Override // org.apache.tools.ant.taskdefs.optional.sos.SOS
    protected Commandline buildCmdLine() {
        this.commandLine = new Commandline();
        this.commandLine.createArgument().setValue(SOSCmd.FLAG_COMMAND);
        this.commandLine.createArgument().setValue(SOSCmd.COMMAND_LABEL);
        getRequiredAttributes();
        if (getLabel() != null) {
            this.commandLine.createArgument().setValue(SOSCmd.FLAG_LABEL);
            this.commandLine.createArgument().setValue(getLabel());
            this.commandLine.createArgument().setValue(getVerbose());
            if (getComment() != null) {
                this.commandLine.createArgument().setValue("-log");
                this.commandLine.createArgument().setValue(getComment());
            }
            return this.commandLine;
        }
        throw new BuildException("label attribute must be set!", getLocation());
    }
}
