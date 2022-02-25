package org.apache.tools.ant.taskdefs.optional.clearcase;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class CCMkattr extends ClearCase {
    public static final String FLAG_COMMENT = "-c";
    public static final String FLAG_COMMENTFILE = "-cfile";
    public static final String FLAG_NOCOMMENT = "-nc";
    public static final String FLAG_RECURSE = "-recurse";
    public static final String FLAG_REPLACE = "-replace";
    public static final String FLAG_VERSION = "-version";
    private boolean mReplace = false;
    private boolean mRecurse = false;
    private String mVersion = null;
    private String mTypeName = null;
    private String mTypeValue = null;
    private String mComment = null;
    private String mCfile = null;

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Commandline commandline = new Commandline();
        Project project = getProject();
        if (getTypeName() == null) {
            throw new BuildException("Required attribute TypeName not specified");
        } else if (getTypeValue() != null) {
            if (getViewPath() == null) {
                setViewPath(project.getBaseDir().getPath());
            }
            commandline.setExecutable(getClearToolCommand());
            commandline.createArgument().setValue(ClearCase.COMMAND_MKATTR);
            checkOptions(commandline);
            if (!getFailOnErr()) {
                Project project2 = getProject();
                project2.log("Ignoring any errors that occur for: " + getViewPathBasename(), 3);
            }
            if (Execute.isFailure(run(commandline)) && getFailOnErr()) {
                throw new BuildException("Failed executing: " + commandline.toString(), getLocation());
            }
        } else {
            throw new BuildException("Required attribute TypeValue not specified");
        }
    }

    private void checkOptions(Commandline commandline) {
        if (getReplace()) {
            commandline.createArgument().setValue("-replace");
        }
        if (getRecurse()) {
            commandline.createArgument().setValue("-recurse");
        }
        if (getVersion() != null) {
            getVersionCommand(commandline);
        }
        if (getComment() != null) {
            getCommentCommand(commandline);
        } else if (getCommentFile() != null) {
            getCommentFileCommand(commandline);
        } else {
            commandline.createArgument().setValue("-nc");
        }
        if (getTypeName() != null) {
            getTypeCommand(commandline);
        }
        if (getTypeValue() != null) {
            getTypeValueCommand(commandline);
        }
        commandline.createArgument().setValue(getViewPath());
    }

    public void setReplace(boolean z) {
        this.mReplace = z;
    }

    public boolean getReplace() {
        return this.mReplace;
    }

    public void setRecurse(boolean z) {
        this.mRecurse = z;
    }

    public boolean getRecurse() {
        return this.mRecurse;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setComment(String str) {
        this.mComment = str;
    }

    public String getComment() {
        return this.mComment;
    }

    public void setCommentFile(String str) {
        this.mCfile = str;
    }

    public String getCommentFile() {
        return this.mCfile;
    }

    public void setTypeName(String str) {
        this.mTypeName = str;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public void setTypeValue(String str) {
        this.mTypeValue = str;
    }

    public String getTypeValue() {
        return this.mTypeValue;
    }

    private void getVersionCommand(Commandline commandline) {
        if (getVersion() != null) {
            commandline.createArgument().setValue("-version");
            commandline.createArgument().setValue(getVersion());
        }
    }

    private void getCommentCommand(Commandline commandline) {
        if (getComment() != null) {
            commandline.createArgument().setValue("-c");
            commandline.createArgument().setValue(getComment());
        }
    }

    private void getCommentFileCommand(Commandline commandline) {
        if (getCommentFile() != null) {
            commandline.createArgument().setValue("-cfile");
            commandline.createArgument().setValue(getCommentFile());
        }
    }

    private void getTypeCommand(Commandline commandline) {
        String typeName = getTypeName();
        if (typeName != null) {
            commandline.createArgument().setValue(typeName);
        }
    }

    private void getTypeValueCommand(Commandline commandline) {
        String str;
        String typeValue = getTypeValue();
        if (typeValue != null) {
            if (C3209Os.isFamily(C3209Os.FAMILY_WINDOWS)) {
                str = "\\\"" + typeValue + "\\\"";
            } else {
                str = "\"" + typeValue + "\"";
            }
            commandline.createArgument().setValue(str);
        }
    }
}
