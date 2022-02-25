package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.launcher.CommandLauncher;

/* loaded from: classes2.dex */
public class CommandLauncherTask extends Task {
    private CommandLauncher commandLauncher;
    private boolean vmLauncher;

    public synchronized void addConfigured(CommandLauncher commandLauncher) {
        if (this.commandLauncher == null) {
            this.commandLauncher = commandLauncher;
        } else {
            throw new BuildException("Only one CommandLauncher can be installed");
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.commandLauncher == null) {
            return;
        }
        if (this.vmLauncher) {
            CommandLauncher.setVMLauncher(getProject(), this.commandLauncher);
        } else {
            CommandLauncher.setShellLauncher(getProject(), this.commandLauncher);
        }
    }

    public void setVmLauncher(boolean z) {
        this.vmLauncher = z;
    }
}
