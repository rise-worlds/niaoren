package org.apache.tools.ant.taskdefs.optional.vss;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public class MSVSSADD extends MSVSS {
    private String localPath = null;

    @Override // org.apache.tools.ant.taskdefs.optional.vss.MSVSS
    protected Commandline buildCmdLine() {
        Commandline commandline = new Commandline();
        if (getLocalpath() != null) {
            commandline.setExecutable(getSSCommand());
            commandline.createArgument().setValue(MSVSSConstants.COMMAND_ADD);
            commandline.createArgument().setValue(getLocalpath());
            commandline.createArgument().setValue(getAutoresponse());
            commandline.createArgument().setValue(getRecursive());
            commandline.createArgument().setValue(getWritable());
            commandline.createArgument().setValue(getLogin());
            commandline.createArgument().setValue(getComment());
            return commandline;
        }
        throw new BuildException("localPath attribute must be set!", getLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.optional.vss.MSVSS
    public String getLocalpath() {
        return this.localPath;
    }

    public void setRecursive(boolean z) {
        super.setInternalRecursive(z);
    }

    public final void setWritable(boolean z) {
        super.setInternalWritable(z);
    }

    public void setAutoresponse(String str) {
        super.setInternalAutoResponse(str);
    }

    public void setComment(String str) {
        super.setInternalComment(str);
    }

    public void setLocalpath(Path path) {
        this.localPath = path.toString();
    }
}
