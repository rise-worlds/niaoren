package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;

/* loaded from: classes2.dex */
public class Chmod extends ExecuteOn {
    private FileSet defaultSet = new FileSet();
    private boolean defaultSetDefined = false;
    private boolean havePerm = false;

    public Chmod() {
        super.setExecutable("chmod");
        super.setParallel(true);
        super.setSkipEmptyFilesets(true);
    }

    @Override // org.apache.tools.ant.ProjectComponent
    public void setProject(Project project) {
        super.setProject(project);
        this.defaultSet.setProject(project);
    }

    public void setFile(File file) {
        FileSet fileSet = new FileSet();
        fileSet.setFile(file);
        addFileset(fileSet);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setDir(File file) {
        this.defaultSet.setDir(file);
    }

    public void setPerm(String str) {
        createArg().setValue(str);
        this.havePerm = true;
    }

    public PatternSet.NameEntry createInclude() {
        this.defaultSetDefined = true;
        return this.defaultSet.createInclude();
    }

    public PatternSet.NameEntry createExclude() {
        this.defaultSetDefined = true;
        return this.defaultSet.createExclude();
    }

    public PatternSet createPatternSet() {
        this.defaultSetDefined = true;
        return this.defaultSet.createPatternSet();
    }

    public void setIncludes(String str) {
        this.defaultSetDefined = true;
        this.defaultSet.setIncludes(str);
    }

    public void setExcludes(String str) {
        this.defaultSetDefined = true;
        this.defaultSet.setExcludes(str);
    }

    public void setDefaultexcludes(boolean z) {
        this.defaultSetDefined = true;
        this.defaultSet.setDefaultexcludes(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecuteOn, org.apache.tools.ant.taskdefs.ExecTask
    public void checkConfiguration() {
        if (this.havePerm) {
            if (this.defaultSetDefined && this.defaultSet.getDir(getProject()) != null) {
                addFileset(this.defaultSet);
            }
            super.checkConfiguration();
            return;
        }
        throw new BuildException("Required attribute perm not set in chmod", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask, org.apache.tools.ant.Task
    public void execute() throws BuildException {
        boolean z;
        if (this.defaultSetDefined || this.defaultSet.getDir(getProject()) == null) {
            try {
                super.execute();
                if (!z) {
                    return;
                }
            } finally {
                if (this.defaultSetDefined && this.defaultSet.getDir(getProject()) != null) {
                    this.filesets.removeElement(this.defaultSet);
                }
            }
        } else if (isValidOs()) {
            Execute prepareExec = prepareExec();
            Commandline commandline = (Commandline) this.cmdl.clone();
            commandline.createArgument().setValue(this.defaultSet.getDir(getProject()).getPath());
            try {
                try {
                    prepareExec.setCommandline(commandline.getCommandline());
                    runExecute(prepareExec);
                } catch (IOException e) {
                    throw new BuildException("Execute failed: " + e, e, getLocation());
                }
            } finally {
                logFlush();
            }
        }
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setExecutable(String str) {
        throw new BuildException(getTaskType() + " doesn't support the executable attribute", getLocation());
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
