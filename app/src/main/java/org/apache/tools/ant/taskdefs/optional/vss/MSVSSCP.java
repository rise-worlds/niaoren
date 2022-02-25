package org.apache.tools.ant.taskdefs.optional.vss;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class MSVSSCP extends MSVSS {
    @Override // org.apache.tools.ant.taskdefs.optional.vss.MSVSS
    protected Commandline buildCmdLine() {
        Commandline commandline = new Commandline();
        if (getVsspath() != null) {
            commandline.setExecutable(getSSCommand());
            commandline.createArgument().setValue(MSVSSConstants.COMMAND_CP);
            commandline.createArgument().setValue(getVsspath());
            commandline.createArgument().setValue(getAutoresponse());
            commandline.createArgument().setValue(getLogin());
            return commandline;
        }
        throw new BuildException("vsspath attribute must be set!", getLocation());
    }

    public void setAutoresponse(String str) {
        super.setInternalAutoResponse(str);
    }
}
