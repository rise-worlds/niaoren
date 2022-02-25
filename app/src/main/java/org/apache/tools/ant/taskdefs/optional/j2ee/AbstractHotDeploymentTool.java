package org.apache.tools.ant.taskdefs.optional.j2ee;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public abstract class AbstractHotDeploymentTool implements HotDeploymentTool {
    private Path classpath;
    private String password;
    private String server;
    private ServerDeploy task;
    private String userName;

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool
    public abstract void deploy() throws BuildException;

    protected abstract boolean isActionValid();

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(this.task.getProject());
        }
        return this.classpath.createPath();
    }

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool
    public void validateAttributes() throws BuildException {
        if (this.task.getAction() == null) {
            throw new BuildException("The \"action\" attribute must be set");
        } else if (!isActionValid()) {
            throw new BuildException("Invalid action \"" + this.task.getAction() + "\" passed");
        } else if (this.classpath == null) {
            throw new BuildException("The classpath attribute must be set");
        }
    }

    @Override // org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool
    public void setTask(ServerDeploy serverDeploy) {
        this.task = serverDeploy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ServerDeploy getTask() {
        return this.task;
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public void setClasspath(Path path) {
        this.classpath = path;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String str) {
        this.server = str;
    }
}
