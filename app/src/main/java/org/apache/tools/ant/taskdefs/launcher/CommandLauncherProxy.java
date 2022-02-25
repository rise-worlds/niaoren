package org.apache.tools.ant.taskdefs.launcher;

import java.io.IOException;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class CommandLauncherProxy extends CommandLauncher {
    private final CommandLauncher myLauncher;

    /* JADX INFO: Access modifiers changed from: protected */
    public CommandLauncherProxy(CommandLauncher commandLauncher) {
        this.myLauncher = commandLauncher;
    }

    @Override // org.apache.tools.ant.taskdefs.launcher.CommandLauncher
    public Process exec(Project project, String[] strArr, String[] strArr2) throws IOException {
        return this.myLauncher.exec(project, strArr, strArr2);
    }
}
