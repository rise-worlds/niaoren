package org.apache.tools.ant.taskdefs.optional.clearcase;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class CCCheckout extends ClearCase {
    public static final String FLAG_BRANCH = "-branch";
    public static final String FLAG_COMMENT = "-c";
    public static final String FLAG_COMMENTFILE = "-cfile";
    public static final String FLAG_NOCOMMENT = "-nc";
    public static final String FLAG_NODATA = "-ndata";
    public static final String FLAG_NOWARN = "-nwarn";
    public static final String FLAG_OUT = "-out";
    public static final String FLAG_RESERVED = "-reserved";
    public static final String FLAG_UNRESERVED = "-unreserved";
    public static final String FLAG_VERSION = "-version";
    private boolean mReserved = true;
    private String mOut = null;
    private boolean mNdata = false;
    private String mBranch = null;
    private boolean mVersion = false;
    private boolean mNwarn = false;
    private String mComment = null;
    private String mCfile = null;
    private boolean mNotco = true;

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Commandline commandline = new Commandline();
        Project project = getProject();
        if (getViewPath() == null) {
            setViewPath(project.getBaseDir().getPath());
        }
        commandline.setExecutable(getClearToolCommand());
        commandline.createArgument().setValue(ClearCase.COMMAND_CHECKOUT);
        checkOptions(commandline);
        if (getNotco() || !lsCheckout()) {
            if (!getFailOnErr()) {
                Project project2 = getProject();
                project2.log("Ignoring any errors that occur for: " + getViewPathBasename(), 3);
            }
            if (Execute.isFailure(run(commandline)) && getFailOnErr()) {
                throw new BuildException("Failed executing: " + commandline.toString(), getLocation());
            }
            return;
        }
        Project project3 = getProject();
        project3.log("Already checked out in this view: " + getViewPathBasename(), 3);
    }

    private boolean lsCheckout() {
        Commandline commandline = new Commandline();
        commandline.setExecutable(getClearToolCommand());
        commandline.createArgument().setValue(ClearCase.COMMAND_LSCO);
        commandline.createArgument().setValue("-cview");
        commandline.createArgument().setValue("-short");
        commandline.createArgument().setValue("-d");
        commandline.createArgument().setValue(getViewPath());
        String runS = runS(commandline);
        return runS != null && runS.length() > 0;
    }

    private void checkOptions(Commandline commandline) {
        if (getReserved()) {
            commandline.createArgument().setValue(FLAG_RESERVED);
        } else {
            commandline.createArgument().setValue(FLAG_UNRESERVED);
        }
        if (getOut() != null) {
            getOutCommand(commandline);
        } else if (getNoData()) {
            commandline.createArgument().setValue(FLAG_NODATA);
        }
        if (getBranch() != null) {
            getBranchCommand(commandline);
        } else if (getVersion()) {
            commandline.createArgument().setValue("-version");
        }
        if (getNoWarn()) {
            commandline.createArgument().setValue("-nwarn");
        }
        if (getComment() != null) {
            getCommentCommand(commandline);
        } else if (getCommentFile() != null) {
            getCommentFileCommand(commandline);
        } else {
            commandline.createArgument().setValue("-nc");
        }
        commandline.createArgument().setValue(getViewPath());
    }

    public void setReserved(boolean z) {
        this.mReserved = z;
    }

    public boolean getReserved() {
        return this.mReserved;
    }

    public void setNotco(boolean z) {
        this.mNotco = z;
    }

    public boolean getNotco() {
        return this.mNotco;
    }

    public void setOut(String str) {
        this.mOut = str;
    }

    public String getOut() {
        return this.mOut;
    }

    public void setNoData(boolean z) {
        this.mNdata = z;
    }

    public boolean getNoData() {
        return this.mNdata;
    }

    public void setBranch(String str) {
        this.mBranch = str;
    }

    public String getBranch() {
        return this.mBranch;
    }

    public void setVersion(boolean z) {
        this.mVersion = z;
    }

    public boolean getVersion() {
        return this.mVersion;
    }

    public void setNoWarn(boolean z) {
        this.mNwarn = z;
    }

    public boolean getNoWarn() {
        return this.mNwarn;
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

    private void getOutCommand(Commandline commandline) {
        if (getOut() != null) {
            commandline.createArgument().setValue(FLAG_OUT);
            commandline.createArgument().setValue(getOut());
        }
    }

    private void getBranchCommand(Commandline commandline) {
        if (getBranch() != null) {
            commandline.createArgument().setValue(FLAG_BRANCH);
            commandline.createArgument().setValue(getBranch());
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
}
