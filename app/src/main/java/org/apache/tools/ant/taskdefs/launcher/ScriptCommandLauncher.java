package org.apache.tools.ant.taskdefs.launcher;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class ScriptCommandLauncher extends CommandLauncherProxy {
    private final String myScript;

    public ScriptCommandLauncher(String str, CommandLauncher commandLauncher) {
        super(commandLauncher);
        this.myScript = str;
    }

    @Override // org.apache.tools.ant.taskdefs.launcher.CommandLauncher
    public Process exec(Project project, String[] strArr, String[] strArr2, File file) throws IOException {
        if (project != null) {
            String property = project.getProperty(MagicNames.ANT_HOME);
            if (property != null) {
                FileUtils fileUtils = FILE_UTILS;
                File baseDir = project.getBaseDir();
                String file2 = fileUtils.resolveFile(baseDir, property + File.separator + this.myScript).toString();
                if (file == null) {
                    file = project.getBaseDir();
                }
                String[] strArr3 = new String[strArr.length + 2];
                strArr3[0] = file2;
                strArr3[1] = file.getAbsolutePath();
                System.arraycopy(strArr, 0, strArr3, 2, strArr.length);
                return exec(project, strArr3, strArr2);
            }
            throw new IOException("Cannot locate antRun script: Property 'ant.home' not found");
        } else if (file == null) {
            return exec(project, strArr, strArr2);
        } else {
            throw new IOException("Cannot locate antRun script: No project provided");
        }
    }
}
