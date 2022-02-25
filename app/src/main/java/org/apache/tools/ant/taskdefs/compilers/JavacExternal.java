package org.apache.tools.ant.taskdefs.compilers;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;

/* loaded from: classes2.dex */
public class JavacExternal extends DefaultCompilerAdapter {
    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using external javac compiler", 3);
        Commandline commandline = new Commandline();
        commandline.setExecutable(getJavac().getJavacExecutable());
        if (assumeJava11() || assumeJava12()) {
            setupJavacCommandlineSwitches(commandline, true);
        } else {
            setupModernJavacCommandlineSwitches(commandline);
        }
        int size = assumeJava11() ? -1 : commandline.size();
        logAndAddFilesToCompile(commandline);
        if (C3209Os.isFamily(C3209Os.FAMILY_VMS)) {
            return execOnVMS(commandline, size);
        }
        return executeExternalCompile(commandline.getCommandline(), size, true) == 0;
    }

    private boolean execOnVMS(Commandline commandline, int i) {
        File file = null;
        try {
            try {
                file = JavaEnvUtils.createVmsJavaOptionFile(commandline.getArguments());
                boolean z = false;
                if (executeExternalCompile(new String[]{commandline.getExecutable(), MSVSSConstants.FLAG_VERSION, file.getPath()}, i, true) == 0) {
                    z = true;
                }
                return z;
            } catch (IOException unused) {
                throw new BuildException("Failed to create a temporary file for \"-V\" switch");
            }
        } finally {
            FileUtils.delete(file);
        }
    }
}
