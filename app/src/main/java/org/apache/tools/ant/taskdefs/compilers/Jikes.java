package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public class Jikes extends DefaultCompilerAdapter {
    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        Path path;
        this.attributes.log("Using jikes compiler", 3);
        Commandline commandline = new Commandline();
        if (this.compileSourcepath != null) {
            path = this.compileSourcepath;
        } else {
            path = this.src;
        }
        if (path.size() > 0) {
            commandline.createArgument().setValue("-sourcepath");
            commandline.createArgument().setPath(path);
        }
        Path path2 = new Path(this.project);
        if (this.bootclasspath == null || this.bootclasspath.size() == 0) {
            this.includeJavaRuntime = true;
        }
        path2.append(getCompileClasspath());
        String property = System.getProperty("jikes.class.path");
        if (property != null) {
            path2.append(new Path(this.project, property));
        }
        if (this.extdirs != null && this.extdirs.size() > 0) {
            commandline.createArgument().setValue("-extdirs");
            commandline.createArgument().setPath(this.extdirs);
        }
        String executable = getJavac().getExecutable();
        if (executable == null) {
            executable = "jikes";
        }
        commandline.setExecutable(executable);
        if (this.deprecation) {
            commandline.createArgument().setValue("-deprecation");
        }
        if (this.destDir != null) {
            commandline.createArgument().setValue("-d");
            commandline.createArgument().setFile(this.destDir);
        }
        commandline.createArgument().setValue("-classpath");
        commandline.createArgument().setPath(path2);
        if (this.encoding != null) {
            commandline.createArgument().setValue("-encoding");
            commandline.createArgument().setValue(this.encoding);
        }
        if (this.debug) {
            String debugLevel = this.attributes.getDebugLevel();
            if (debugLevel != null) {
                Commandline.Argument createArgument = commandline.createArgument();
                createArgument.setValue("-g:" + debugLevel);
            } else {
                commandline.createArgument().setValue("-g");
            }
        } else {
            commandline.createArgument().setValue("-g:none");
        }
        if (this.optimize) {
            commandline.createArgument().setValue(MSVSSConstants.FLAG_OUTPUT);
        }
        if (this.verbose) {
            commandline.createArgument().setValue(SOSCmd.FLAG_VERBOSE);
        }
        if (this.depend) {
            commandline.createArgument().setValue("-depend");
        }
        if (this.target != null) {
            commandline.createArgument().setValue("-target");
            commandline.createArgument().setValue(this.target);
        }
        addPropertyParams(commandline);
        if (this.attributes.getSource() != null) {
            commandline.createArgument().setValue("-source");
            String source = this.attributes.getSource();
            if (source.equals("1.1") || source.equals("1.2")) {
                Javac javac = this.attributes;
                javac.log("Jikes doesn't support '-source " + source + "', will use '-source 1.3' instead");
                commandline.createArgument().setValue("1.3");
            } else {
                commandline.createArgument().setValue(source);
            }
        }
        addCurrentCompilerArgs(commandline);
        int size = commandline.size();
        Path bootClassPath = getBootClassPath();
        if (bootClassPath.size() > 0) {
            commandline.createArgument().setValue("-bootclasspath");
            commandline.createArgument().setPath(bootClassPath);
        }
        logAndAddFilesToCompile(commandline);
        return executeExternalCompile(commandline.getCommandline(), size) == 0;
    }

    private void addPropertyParams(Commandline commandline) {
        String property = this.project.getProperty("build.compiler.emacs");
        if (property != null && Project.toBoolean(property)) {
            commandline.createArgument().setValue("+E");
        }
        String property2 = this.project.getProperty("build.compiler.warnings");
        if (property2 != null) {
            this.attributes.log("!! the build.compiler.warnings property is deprecated. !!", 1);
            this.attributes.log("!! Use the nowarn attribute instead. !!", 1);
            if (!Project.toBoolean(property2)) {
                commandline.createArgument().setValue("-nowarn");
            }
        }
        if (this.attributes.getNowarn()) {
            commandline.createArgument().setValue("-nowarn");
        }
        String property3 = this.project.getProperty("build.compiler.pedantic");
        if (property3 != null && Project.toBoolean(property3)) {
            commandline.createArgument().setValue("+P");
        }
        String property4 = this.project.getProperty("build.compiler.fulldepend");
        if (property4 != null && Project.toBoolean(property4)) {
            commandline.createArgument().setValue("+F");
        }
    }
}
