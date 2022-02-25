package org.apache.tools.ant.taskdefs.optional.windows;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteOn;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.FileSet;

/* loaded from: classes2.dex */
public class Attrib extends ExecuteOn {
    private static final String ATTR_ARCHIVE = "A";
    private static final String ATTR_HIDDEN = "H";
    private static final String ATTR_READONLY = "R";
    private static final String ATTR_SYSTEM = "S";
    private static final String SET = "+";
    private static final String UNSET = "-";
    private boolean haveAttr = false;

    private static String getSignString(boolean z) {
        return z ? "+" : UNSET;
    }

    public Attrib() {
        super.setExecutable("attrib");
        super.setParallel(false);
    }

    public void setFile(File file) {
        FileSet fileSet = new FileSet();
        fileSet.setFile(file);
        addFileset(fileSet);
    }

    public void setReadonly(boolean z) {
        addArg(z, ATTR_READONLY);
    }

    public void setArchive(boolean z) {
        addArg(z, ATTR_ARCHIVE);
    }

    public void setSystem(boolean z) {
        addArg(z, ATTR_SYSTEM);
    }

    public void setHidden(boolean z) {
        addArg(z, ATTR_HIDDEN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecuteOn, org.apache.tools.ant.taskdefs.ExecTask
    public void checkConfiguration() {
        if (haveAttr()) {
            super.checkConfiguration();
            return;
        }
        throw new BuildException("Missing attribute parameter", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public void setExecutable(String str) {
        throw new BuildException(getTaskType() + " doesn't support the executable attribute", getLocation());
    }

    public void setCommand(String str) {
        throw new BuildException(getTaskType() + " doesn't support the command attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setAddsourcefile(boolean z) {
        throw new BuildException(getTaskType() + " doesn't support the addsourcefile attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setSkipEmptyFilesets(boolean z) {
        throw new BuildException(getTaskType() + " doesn't support the skipemptyfileset attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setParallel(boolean z) {
        throw new BuildException(getTaskType() + " doesn't support the parallel attribute", getLocation());
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteOn
    public void setMaxParallel(int i) {
        throw new BuildException(getTaskType() + " doesn't support the maxparallel attribute", getLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.ExecTask
    public boolean isValidOs() {
        return (getOs() == null && getOsFamily() == null) ? C3209Os.isFamily(C3209Os.FAMILY_WINDOWS) : super.isValidOs();
    }

    private void addArg(boolean z, String str) {
        Commandline.Argument createArg = createArg();
        createArg.setValue(getSignString(z) + str);
        this.haveAttr = true;
    }

    private boolean haveAttr() {
        return this.haveAttr;
    }
}
