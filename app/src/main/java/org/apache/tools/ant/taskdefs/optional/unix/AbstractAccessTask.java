package org.apache.tools.ant.taskdefs.optional.unix;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteOn;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.FileSet;

/* loaded from: classes2.dex */
public abstract class AbstractAccessTask extends ExecuteOn {
    public AbstractAccessTask() {
        super.setParallel(true);
        super.setSkipEmptyFilesets(true);
    }

    public void setFile(File file) {
        FileSet fileSet = new FileSet();
        fileSet.setFile(file);
        addFileset(fileSet);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setCommand(Commandline commandline) {
        throw new BuildException(getTaskType() + " doesn't support the command attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setSkipEmptyFilesets(boolean z) {
        throw new BuildException(getTaskType() + " doesn't support the skipemptyfileset attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setAddsourcefile(boolean z) {
        throw new BuildException(getTaskType() + " doesn't support the addsourcefile attribute", getLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public boolean isValidOs() {
        return (getOs() == null && getOsFamily() == null) ? C3209Os.isFamily(C3209Os.FAMILY_UNIX) : super.isValidOs();
    }
}
