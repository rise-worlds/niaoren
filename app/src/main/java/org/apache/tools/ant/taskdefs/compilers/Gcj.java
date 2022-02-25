package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public class Gcj extends DefaultCompilerAdapter {
    private static final String[] CONFLICT_WITH_DASH_C = {"-o", "--main=", MSVSSConstants.FLAG_CODEDIFF, "-fjni", MSVSSConstants.FLAG_LABEL};

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using gcj compiler", 3);
        Commandline commandline = setupGCJCommand();
        int size = commandline.size();
        logAndAddFilesToCompile(commandline);
        return executeExternalCompile(commandline.getCommandline(), size) == 0;
    }

    protected Commandline setupGCJCommand() {
        Commandline commandline = new Commandline();
        Path path = new Path(this.project);
        Path bootClassPath = getBootClassPath();
        if (bootClassPath.size() > 0) {
            path.append(bootClassPath);
        }
        if (this.extdirs != null || this.includeJavaRuntime) {
            path.addExtdirs(this.extdirs);
        }
        path.append(getCompileClasspath());
        if (this.compileSourcepath != null) {
            path.append(this.compileSourcepath);
        } else {
            path.append(this.src);
        }
        String executable = getJavac().getExecutable();
        if (executable == null) {
            executable = "gcj";
        }
        commandline.setExecutable(executable);
        if (this.destDir != null) {
            commandline.createArgument().setValue("-d");
            commandline.createArgument().setFile(this.destDir);
            if (!this.destDir.exists() && !this.destDir.mkdirs() && !this.destDir.isDirectory()) {
                throw new BuildException("Can't make output directories. Maybe permission is wrong. ");
            }
        }
        commandline.createArgument().setValue("-classpath");
        commandline.createArgument().setPath(path);
        if (this.encoding != null) {
            Commandline.Argument createArgument = commandline.createArgument();
            createArgument.setValue("--encoding=" + this.encoding);
        }
        if (this.debug) {
            commandline.createArgument().setValue("-g1");
        }
        if (this.optimize) {
            commandline.createArgument().setValue(MSVSSConstants.FLAG_OUTPUT);
        }
        if (!isNativeBuild()) {
            commandline.createArgument().setValue(MSVSSConstants.FLAG_COMMENT);
        }
        if (this.attributes.getSource() != null) {
            String source = this.attributes.getSource();
            Commandline.Argument createArgument2 = commandline.createArgument();
            createArgument2.setValue("-fsource=" + source);
        }
        if (this.attributes.getTarget() != null) {
            String target = this.attributes.getTarget();
            Commandline.Argument createArgument3 = commandline.createArgument();
            createArgument3.setValue("-ftarget=" + target);
        }
        addCurrentCompilerArgs(commandline);
        return commandline;
    }

    public boolean isNativeBuild() {
        String[] currentCompilerArgs = getJavac().getCurrentCompilerArgs();
        boolean z = false;
        for (int i = 0; !z && i < currentCompilerArgs.length; i++) {
            int i2 = 0;
            while (!z) {
                String[] strArr = CONFLICT_WITH_DASH_C;
                if (i2 < strArr.length) {
                    z = currentCompilerArgs[i].startsWith(strArr[i2]);
                    i2++;
                }
            }
        }
        return z;
    }
}
