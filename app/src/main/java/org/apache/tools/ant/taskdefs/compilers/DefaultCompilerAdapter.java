package org.apache.tools.ant.taskdefs.compilers;

import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import java.io.File;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.StringUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public abstract class DefaultCompilerAdapter implements CompilerAdapter, CompilerAdapterExtension {
    private static final int COMMAND_LINE_LIMIT;
    private static final FileUtils FILE_UTILS;
    protected static final String lSep;
    protected Javac attributes;
    protected Path bootclasspath;
    protected Path compileClasspath;
    protected File[] compileList;
    protected Path compileSourcepath;
    protected File destDir;
    protected String encoding;
    protected Path extdirs;
    protected boolean includeAntRuntime;
    protected boolean includeJavaRuntime;
    protected Location location;
    protected String memoryInitialSize;
    protected String memoryMaximumSize;
    protected Project project;
    protected Path src;
    protected String target;
    protected boolean debug = false;
    protected boolean optimize = false;
    protected boolean deprecation = false;
    protected boolean depend = false;
    protected boolean verbose = false;

    static {
        if (C3209Os.isFamily(C3209Os.FAMILY_OS2)) {
            COMMAND_LINE_LIMIT = 1000;
        } else {
            COMMAND_LINE_LIMIT = 4096;
        }
        FILE_UTILS = FileUtils.getFileUtils();
        lSep = StringUtils.LINE_SEP;
    }

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public void setJavac(Javac javac) {
        this.attributes = javac;
        this.src = javac.getSrcdir();
        this.destDir = javac.getDestdir();
        this.encoding = javac.getEncoding();
        this.debug = javac.getDebug();
        this.optimize = javac.getOptimize();
        this.deprecation = javac.getDeprecation();
        this.depend = javac.getDepend();
        this.verbose = javac.getVerbose();
        this.target = javac.getTarget();
        this.bootclasspath = javac.getBootclasspath();
        this.extdirs = javac.getExtdirs();
        this.compileList = javac.getFileList();
        this.compileClasspath = javac.getClasspath();
        this.compileSourcepath = javac.getSourcepath();
        this.project = javac.getProject();
        this.location = javac.getLocation();
        this.includeAntRuntime = javac.getIncludeantruntime();
        this.includeJavaRuntime = javac.getIncludejavaruntime();
        this.memoryInitialSize = javac.getMemoryInitialSize();
        this.memoryMaximumSize = javac.getMemoryMaximumSize();
    }

    public Javac getJavac() {
        return this.attributes;
    }

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapterExtension
    public String[] getSupportedFileExtensions() {
        return new String[]{"java"};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Project getProject() {
        return this.project;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path getCompileClasspath() {
        Path path = new Path(this.project);
        if (this.destDir != null && getJavac().isIncludeDestClasses()) {
            path.setLocation(this.destDir);
        }
        Path path2 = this.compileClasspath;
        if (path2 == null) {
            path2 = new Path(this.project);
        }
        if (this.includeAntRuntime) {
            path.addExisting(path2.concatSystemClasspath("last"));
        } else {
            path.addExisting(path2.concatSystemClasspath(Definer.OnError.POLICY_IGNORE));
        }
        if (this.includeJavaRuntime) {
            path.addJavaRuntime();
        }
        return path;
    }

    protected Commandline setupJavacCommandlineSwitches(Commandline commandline) {
        return setupJavacCommandlineSwitches(commandline, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Commandline setupJavacCommandlineSwitches(Commandline commandline, boolean z) {
        Path compileClasspath = getCompileClasspath();
        Path path = this.compileSourcepath;
        if (path == null) {
            path = this.src;
        }
        String str = assumeJava11() ? "-J-" : "-J-X";
        if (this.memoryInitialSize != null) {
            if (!this.attributes.isForkedJavac()) {
                this.attributes.log("Since fork is false, ignoring memoryInitialSize setting.", 1);
            } else {
                Commandline.Argument createArgument = commandline.createArgument();
                createArgument.setValue(str + "ms" + this.memoryInitialSize);
            }
        }
        if (this.memoryMaximumSize != null) {
            if (!this.attributes.isForkedJavac()) {
                this.attributes.log("Since fork is false, ignoring memoryMaximumSize setting.", 1);
            } else {
                Commandline.Argument createArgument2 = commandline.createArgument();
                createArgument2.setValue(str + "mx" + this.memoryMaximumSize);
            }
        }
        if (this.attributes.getNowarn()) {
            commandline.createArgument().setValue("-nowarn");
        }
        if (this.deprecation) {
            commandline.createArgument().setValue("-deprecation");
        }
        if (this.destDir != null) {
            commandline.createArgument().setValue("-d");
            commandline.createArgument().setFile(this.destDir);
        }
        commandline.createArgument().setValue("-classpath");
        if (assumeJava11()) {
            Path path2 = new Path(this.project);
            Path bootClassPath = getBootClassPath();
            if (bootClassPath.size() > 0) {
                path2.append(bootClassPath);
            }
            Path path3 = this.extdirs;
            if (path3 != null) {
                path2.addExtdirs(path3);
            }
            path2.append(compileClasspath);
            path2.append(path);
            commandline.createArgument().setPath(path2);
        } else {
            commandline.createArgument().setPath(compileClasspath);
            if (path.size() > 0) {
                commandline.createArgument().setValue("-sourcepath");
                commandline.createArgument().setPath(path);
            }
            if (this.target != null) {
                commandline.createArgument().setValue("-target");
                commandline.createArgument().setValue(this.target);
            }
            Path bootClassPath2 = getBootClassPath();
            if (bootClassPath2.size() > 0) {
                commandline.createArgument().setValue("-bootclasspath");
                commandline.createArgument().setPath(bootClassPath2);
            }
            Path path4 = this.extdirs;
            if (path4 != null && path4.size() > 0) {
                commandline.createArgument().setValue("-extdirs");
                commandline.createArgument().setPath(this.extdirs);
            }
        }
        if (this.encoding != null) {
            commandline.createArgument().setValue("-encoding");
            commandline.createArgument().setValue(this.encoding);
        }
        if (this.debug) {
            if (!z || assumeJava11()) {
                commandline.createArgument().setValue("-g");
            } else {
                String debugLevel = this.attributes.getDebugLevel();
                if (debugLevel != null) {
                    Commandline.Argument createArgument3 = commandline.createArgument();
                    createArgument3.setValue("-g:" + debugLevel);
                } else {
                    commandline.createArgument().setValue("-g");
                }
            }
        } else if (getNoDebugArgument() != null) {
            commandline.createArgument().setValue(getNoDebugArgument());
        }
        if (this.optimize) {
            commandline.createArgument().setValue(MSVSSConstants.FLAG_OUTPUT);
        }
        if (this.depend) {
            if (assumeJava11()) {
                commandline.createArgument().setValue("-depend");
            } else if (assumeJava12()) {
                commandline.createArgument().setValue("-Xdepend");
            } else {
                this.attributes.log("depend attribute is not supported by the modern compiler", 1);
            }
        }
        if (this.verbose) {
            commandline.createArgument().setValue(SOSCmd.FLAG_VERBOSE);
        }
        addCurrentCompilerArgs(commandline);
        return commandline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Commandline setupModernJavacCommandlineSwitches(Commandline commandline) {
        setupJavacCommandlineSwitches(commandline, true);
        if (!assumeJava13()) {
            String target = this.attributes.getTarget();
            if (this.attributes.getSource() != null) {
                commandline.createArgument().setValue("-source");
                commandline.createArgument().setValue(adjustSourceValue(this.attributes.getSource()));
            } else if (target != null && mustSetSourceForTarget(target)) {
                setImplicitSourceSwitch(commandline, target, adjustSourceValue(target));
            }
        }
        return commandline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Commandline setupModernJavacCommand() {
        Commandline commandline = new Commandline();
        setupModernJavacCommandlineSwitches(commandline);
        logAndAddFilesToCompile(commandline);
        return commandline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Commandline setupJavacCommand() {
        return setupJavacCommand(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Commandline setupJavacCommand(boolean z) {
        Commandline commandline = new Commandline();
        setupJavacCommandlineSwitches(commandline, z);
        logAndAddFilesToCompile(commandline);
        return commandline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logAndAddFilesToCompile(Commandline commandline) {
        Javac javac = this.attributes;
        javac.log("Compilation " + commandline.describeArguments(), 3);
        StringBuffer stringBuffer = new StringBuffer("File");
        if (this.compileList.length != 1) {
            stringBuffer.append("s");
        }
        stringBuffer.append(" to be compiled:");
        stringBuffer.append(StringUtils.LINE_SEP);
        int i = 0;
        while (true) {
            File[] fileArr = this.compileList;
            if (i < fileArr.length) {
                String absolutePath = fileArr[i].getAbsolutePath();
                commandline.createArgument().setValue(absolutePath);
                stringBuffer.append("    ");
                stringBuffer.append(absolutePath);
                stringBuffer.append(StringUtils.LINE_SEP);
                i++;
            } else {
                this.attributes.log(stringBuffer.toString(), 3);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int executeExternalCompile(String[] strArr, int i) {
        return executeExternalCompile(strArr, i, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0111  */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int executeExternalCompile(java.lang.String[] r11, int r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.compilers.DefaultCompilerAdapter.executeExternalCompile(java.lang.String[], int, boolean):int");
    }

    @Deprecated
    protected void addExtdirsToClasspath(Path path) {
        path.addExtdirs(this.extdirs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addCurrentCompilerArgs(Commandline commandline) {
        commandline.addArguments(getJavac().getCurrentCompilerArgs());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean assumeJava11() {
        return "javac1.1".equals(this.attributes.getCompilerVersion());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean assumeJava12() {
        return "javac1.2".equals(this.attributes.getCompilerVersion());
    }

    protected boolean assumeJava13() {
        return "javac1.3".equals(this.attributes.getCompilerVersion());
    }

    protected boolean assumeJava14() {
        return assumeJavaXY("javac1.4", "1.4");
    }

    protected boolean assumeJava15() {
        return assumeJavaXY("javac1.5", JavaEnvUtils.JAVA_1_5);
    }

    protected boolean assumeJava16() {
        return assumeJavaXY("javac1.6", JavaEnvUtils.JAVA_1_6);
    }

    protected boolean assumeJava17() {
        return assumeJavaXY("javac1.7", JavaEnvUtils.JAVA_1_7);
    }

    protected boolean assumeJava18() {
        return assumeJavaXY("javac1.8", JavaEnvUtils.JAVA_1_8);
    }

    protected boolean assumeJava19() {
        return assumeJavaXY("javac1.9", JavaEnvUtils.JAVA_1_9);
    }

    private boolean assumeJavaXY(String str, String str2) {
        return str.equals(this.attributes.getCompilerVersion()) || ("classic".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(str2)) || (("modern".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(str2)) || ("extJavac".equals(this.attributes.getCompilerVersion()) && JavaEnvUtils.isJavaVersion(str2)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path getBootClassPath() {
        Path path = new Path(this.project);
        Path path2 = this.bootclasspath;
        if (path2 != null) {
            path.append(path2);
        }
        return path.concatSystemBootClasspath(Definer.OnError.POLICY_IGNORE);
    }

    protected String getNoDebugArgument() {
        if (assumeJava11()) {
            return null;
        }
        return "-g:none";
    }

    private void setImplicitSourceSwitch(Commandline commandline, String str, String str2) {
        this.attributes.log("", 1);
        this.attributes.log("          WARNING", 1);
        this.attributes.log("", 1);
        Javac javac = this.attributes;
        javac.log("The -source switch defaults to " + getDefaultSource() + Consts.f23430h, 1);
        Javac javac2 = this.attributes;
        javac2.log("If you specify -target " + str + " you now must also specify -source " + str2 + Consts.f23430h, 1);
        Javac javac3 = this.attributes;
        StringBuilder sb = new StringBuilder();
        sb.append("Ant will implicitly add -source ");
        sb.append(str2);
        sb.append(" for you.  Please change your build file.");
        javac3.log(sb.toString(), 1);
        commandline.createArgument().setValue("-source");
        commandline.createArgument().setValue(str2);
    }

    private String getDefaultSource() {
        return (assumeJava15() || assumeJava16()) ? "1.5 in JDK 1.5 and 1.6" : assumeJava17() ? "1.7 in JDK 1.7" : assumeJava18() ? "1.8 in JDK 1.8" : assumeJava19() ? "1.9 in JDK 1.9" : "";
    }

    private boolean mustSetSourceForTarget(String str) {
        if (assumeJava14()) {
            return false;
        }
        if (str.startsWith("1.")) {
            str = str.substring(2);
        }
        return str.equals("1") || str.equals("2") || str.equals(DdyConstants.APP_INSTALL_ERROR) || str.equals(DdyConstants.APP_INSTALL_DOWNLOADING) || ((str.equals(DdyConstants.APP_INSTALL_INSTALLING) || str.equals(DdyConstants.APP_INSTALL_UNINSTALL)) && !assumeJava15() && !assumeJava16()) || ((str.equals(DdyConstants.APP_INSTALL_INSTALLED) && !assumeJava17()) || ((str.equals("8") && !assumeJava18()) || (str.equals("9") && !assumeJava19())));
    }

    private String adjustSourceValue(String str) {
        return (str.equals("1.1") || str.equals("1.2")) ? "1.3" : str;
    }
}
