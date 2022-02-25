package org.apache.tools.ant.taskdefs.launcher;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class MacCommandLauncher extends CommandLauncherProxy {
    public MacCommandLauncher(CommandLauncher commandLauncher) {
        super(commandLauncher);
    }

    @Override // org.apache.tools.ant.taskdefs.launcher.CommandLauncher
    public Process exec(Project project, String[] strArr, String[] strArr2, File file) throws IOException {
        if (file == null) {
            return exec(project, strArr, strArr2);
        }
        System.getProperties().put("user.dir", file.getAbsolutePath());
        try {
            Process exec = exec(project, strArr, strArr2);
            System.getProperties().put("user.dir", System.getProperty("user.dir"));
            return exec;
        } catch (Throwable th) {
            System.getProperties().put("user.dir", System.getProperty("user.dir"));
            throw th;
        }
    }
}
