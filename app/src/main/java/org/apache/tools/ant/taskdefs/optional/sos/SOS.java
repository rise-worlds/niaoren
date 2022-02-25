package org.apache.tools.ant.taskdefs.optional.sos;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.LogStreamHandler;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public abstract class SOS extends Task implements SOSCmd {
    private static final int ERROR_EXIT_STATUS = 255;
    protected Commandline commandLine;
    private String sosCmdDir = null;
    private String sosUsername = null;
    private String sosPassword = "";
    private String projectPath = null;
    private String vssServerPath = null;
    private String sosServerPath = null;
    private String sosHome = null;
    private String localPath = null;
    private String version = null;
    private String label = null;
    private String comment = null;
    private String filename = null;
    private boolean noCompress = false;
    private boolean noCache = false;
    private boolean recursive = false;
    private boolean verbose = false;

    abstract Commandline buildCmdLine();

    public final void setNoCache(boolean z) {
        this.noCache = z;
    }

    public final void setNoCompress(boolean z) {
        this.noCompress = z;
    }

    public final void setSosCmd(String str) {
        this.sosCmdDir = FileUtils.translatePath(str);
    }

    public final void setUsername(String str) {
        this.sosUsername = str;
    }

    public final void setPassword(String str) {
        this.sosPassword = str;
    }

    public final void setProjectPath(String str) {
        if (str.startsWith("$")) {
            this.projectPath = str;
            return;
        }
        this.projectPath = "$" + str;
    }

    public final void setVssServerPath(String str) {
        this.vssServerPath = str;
    }

    public final void setSosHome(String str) {
        this.sosHome = str;
    }

    public final void setSosServerPath(String str) {
        this.sosServerPath = str;
    }

    public final void setLocalPath(Path path) {
        this.localPath = path.toString();
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalFilename(String str) {
        this.filename = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalRecursive(boolean z) {
        this.recursive = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalComment(String str) {
        this.comment = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalLabel(String str) {
        this.label = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInternalVersion(String str) {
        this.version = str;
    }

    protected String getSosCommand() {
        if (this.sosCmdDir == null) {
            return SOSCmd.COMMAND_SOS_EXE;
        }
        return this.sosCmdDir + File.separator + SOSCmd.COMMAND_SOS_EXE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getComment() {
        return this.comment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVersion() {
        return this.version;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLabel() {
        return this.label;
    }

    protected String getUsername() {
        return this.sosUsername;
    }

    protected String getPassword() {
        return this.sosPassword;
    }

    protected String getProjectPath() {
        return this.projectPath;
    }

    protected String getVssServerPath() {
        return this.vssServerPath;
    }

    protected String getSosHome() {
        return this.sosHome;
    }

    protected String getSosServerPath() {
        return this.sosServerPath;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getFilename() {
        return this.filename;
    }

    protected String getNoCompress() {
        return this.noCompress ? SOSCmd.FLAG_NO_COMPRESSION : "";
    }

    protected String getNoCache() {
        return this.noCache ? SOSCmd.FLAG_NO_CACHE : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getVerbose() {
        return this.verbose ? SOSCmd.FLAG_VERBOSE : "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getRecursive() {
        return this.recursive ? SOSCmd.FLAG_RECURSION : "";
    }

    protected String getLocalPath() {
        if (this.localPath == null) {
            return getProject().getBaseDir().getAbsolutePath();
        }
        File resolveFile = getProject().resolveFile(this.localPath);
        if (!resolveFile.exists()) {
            if (resolveFile.mkdirs() || resolveFile.isDirectory()) {
                Project project = getProject();
                project.log("Created dir: " + resolveFile.getAbsolutePath());
            } else {
                throw new BuildException("Directory " + this.localPath + " creation was not successful for an unknown reason", getLocation());
            }
        }
        return resolveFile.getAbsolutePath();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        buildCmdLine();
        if (run(this.commandLine) == 255) {
            throw new BuildException("Failed executing: " + this.commandLine.toString(), getLocation());
        }
    }

    protected int run(Commandline commandline) {
        try {
            Execute execute = new Execute(new LogStreamHandler((Task) this, 2, 1));
            execute.setAntRun(getProject());
            execute.setWorkingDirectory(getProject().getBaseDir());
            execute.setCommandline(commandline.getCommandline());
            execute.setVMLauncher(false);
            return execute.execute();
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getRequiredAttributes() {
        this.commandLine.setExecutable(getSosCommand());
        if (getSosServerPath() != null) {
            this.commandLine.createArgument().setValue(SOSCmd.FLAG_SOS_SERVER);
            this.commandLine.createArgument().setValue(getSosServerPath());
            if (getUsername() != null) {
                this.commandLine.createArgument().setValue(SOSCmd.FLAG_USERNAME);
                this.commandLine.createArgument().setValue(getUsername());
                this.commandLine.createArgument().setValue(SOSCmd.FLAG_PASSWORD);
                this.commandLine.createArgument().setValue(getPassword());
                if (getVssServerPath() != null) {
                    this.commandLine.createArgument().setValue(SOSCmd.FLAG_VSS_SERVER);
                    this.commandLine.createArgument().setValue(getVssServerPath());
                    if (getProjectPath() != null) {
                        this.commandLine.createArgument().setValue(SOSCmd.FLAG_PROJECT);
                        this.commandLine.createArgument().setValue(getProjectPath());
                        return;
                    }
                    throw new BuildException("projectpath attribute must be set!", getLocation());
                }
                throw new BuildException("vssserverpath attribute must be set!", getLocation());
            }
            throw new BuildException("username attribute must be set!", getLocation());
        }
        throw new BuildException("sosserverpath attribute must be set!", getLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getOptionalAttributes() {
        this.commandLine.createArgument().setValue(getVerbose());
        this.commandLine.createArgument().setValue(getNoCompress());
        if (getSosHome() == null) {
            this.commandLine.createArgument().setValue(getNoCache());
        } else {
            this.commandLine.createArgument().setValue(SOSCmd.FLAG_SOS_HOME);
            this.commandLine.createArgument().setValue(getSosHome());
        }
        if (getLocalPath() != null) {
            this.commandLine.createArgument().setValue(SOSCmd.FLAG_WORKING_DIR);
            this.commandLine.createArgument().setValue(getLocalPath());
        }
    }
}
