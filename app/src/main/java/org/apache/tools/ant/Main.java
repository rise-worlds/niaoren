package org.apache.tools.ant;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.input.DefaultInputHandler;
import org.apache.tools.ant.input.InputHandler;
import org.apache.tools.ant.launch.AntMain;
import org.apache.tools.ant.listener.SilentLogger;
import org.apache.tools.ant.property.GetProperty;
import org.apache.tools.ant.property.ResolvePropertyMap;
import org.apache.tools.ant.taskdefs.optional.sos.SOSCmd;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.util.ClasspathUtils;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.ProxySetup;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class Main implements AntMain {
    public static final String DEFAULT_BUILD_FILENAME = "build.xml";
    private File buildFile;
    private static final Set<String> LAUNCH_COMMANDS = Collections.unmodifiableSet(new HashSet(Arrays.asList("-lib", "-cp", "-noclasspath", "--noclasspath", "-nouserlib", "-main")));
    private static PrintStream out = System.out;
    private static PrintStream err = System.err;
    private static boolean isLogFileUsed = false;
    private static final GetProperty NOPROPERTIES = new GetProperty() { // from class: org.apache.tools.ant.Main.1
        @Override // org.apache.tools.ant.property.GetProperty
        public Object getProperty(String str) {
            return null;
        }
    };
    private static String antVersion = null;
    private static String shortAntVersion = null;
    private int msgOutputLevel = 2;
    private final Vector<String> targets = new Vector<>();
    private final Properties definedProps = new Properties();
    private final Vector<String> listeners = new Vector<>(1);
    private final Vector<String> propertyFiles = new Vector<>(1);
    private boolean allowInput = true;
    private boolean keepGoingMode = false;
    private String loggerClassname = null;
    private String inputHandlerClassname = null;
    private boolean emacsMode = false;
    private boolean silent = false;
    private boolean readyToRun = false;
    private boolean projectHelp = false;
    private Integer threadPriority = null;
    private boolean proxy = false;
    private final Map<Class<?>, List<String>> extraArguments = new HashMap();

    private static void printMessage(Throwable th) {
        String message = th.getMessage();
        if (message != null) {
            System.err.println(message);
        }
    }

    public static void start(String[] strArr, Properties properties, ClassLoader classLoader) {
        new Main().startAnt(strArr, properties, classLoader);
    }

    public void startAnt(String[] strArr, Properties properties, ClassLoader classLoader) {
        int i = 1;
        try {
            processArgs(strArr);
            if (properties != null) {
                Enumeration keys = properties.keys();
                while (keys.hasMoreElements()) {
                    String str = (String) keys.nextElement();
                    this.definedProps.put(str, properties.getProperty(str));
                }
            }
            try {
                try {
                    try {
                        runBuild(classLoader);
                        i = 0;
                    } catch (BuildException e) {
                        if (err != System.err) {
                            printMessage(e);
                        }
                        handleLogfile();
                    }
                } catch (ExitStatusException e2) {
                    int status = e2.getStatus();
                    if (status == 0) {
                        i = status;
                    } else {
                        throw e2;
                    }
                }
                exit(i);
            } finally {
                handleLogfile();
            }
        } catch (Throwable th) {
            handleLogfile();
            printMessage(th);
            exit(1);
        }
    }

    protected void exit(int i) {
        System.exit(i);
    }

    private static void handleLogfile() {
        if (isLogFileUsed) {
            FileUtils.close(out);
            FileUtils.close(err);
        }
    }

    public static void main(String[] strArr) {
        start(strArr, null, null);
    }

    public Main() {
    }

    @Deprecated
    protected Main(String[] strArr) throws BuildException {
        processArgs(strArr);
    }

    private void processArgs(String[] strArr) {
        boolean z;
        ArgumentProcessorRegistry instance = ArgumentProcessorRegistry.getInstance();
        String str = null;
        boolean z2 = false;
        PrintStream printStream = null;
        int i = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (i < strArr.length) {
            String str2 = strArr[i];
            if (str2.equals("-help") || str2.equals("-h")) {
                z4 = true;
            } else if (str2.equals("-version")) {
                z3 = true;
            } else if (str2.equals("-diagnostics")) {
                z5 = true;
            } else if (str2.equals("-quiet") || str2.equals("-q")) {
                this.msgOutputLevel = 1;
            } else if (str2.equals(SOSCmd.FLAG_VERBOSE) || str2.equals("-v")) {
                this.msgOutputLevel = 3;
            } else if (str2.equals("-debug") || str2.equals("-d")) {
                this.msgOutputLevel = 4;
            } else if (str2.equals("-silent") || str2.equals("-S")) {
                this.silent = true;
            } else if (str2.equals("-noinput")) {
                this.allowInput = z2;
            } else if (str2.equals("-logfile") || str2.equals("-l")) {
                try {
                    i++;
                    printStream = new PrintStream(new FileOutputStream(new File(strArr[i])));
                    isLogFileUsed = true;
                } catch (IOException unused) {
                    throw new BuildException("Cannot write on the specified log file. Make sure the path exists and you have write permissions.");
                } catch (ArrayIndexOutOfBoundsException unused2) {
                    throw new BuildException("You must specify a log file when using the -log argument");
                }
            } else if (str2.equals("-buildfile") || str2.equals(SOSCmd.FLAG_FILE) || str2.equals("-f")) {
                i = handleArgBuildFile(strArr, i);
            } else if (str2.equals("-listener")) {
                i = handleArgListener(strArr, i);
            } else if (str2.startsWith(MSVSSConstants.FLAG_CODEDIFF)) {
                i = handleArgDefine(strArr, i);
            } else if (str2.equals("-logger")) {
                i = handleArgLogger(strArr, i);
            } else if (str2.equals("-inputhandler")) {
                i = handleArgInputHandler(strArr, i);
            } else if (str2.equals("-emacs") || str2.equals("-e")) {
                this.emacsMode = true;
            } else if (str2.equals("-projecthelp") || str2.equals("-p")) {
                this.projectHelp = true;
            } else if (str2.equals("-find") || str2.equals("-s")) {
                if (i < strArr.length - 1) {
                    i++;
                    str = strArr[i];
                    z6 = true;
                } else {
                    z6 = true;
                }
            } else if (str2.startsWith("-propertyfile")) {
                i = handleArgPropertyFile(strArr, i);
            } else if (str2.equals("-k") || str2.equals("-keep-going")) {
                this.keepGoingMode = true;
            } else if (str2.equals("-nice")) {
                i = handleArgNice(strArr, i);
            } else if (LAUNCH_COMMANDS.contains(str2)) {
                throw new BuildException("Ant's Main method is being handed an option " + str2 + " that is only for the launcher class.\nThis can be caused by a version mismatch between the ant script/.bat file and Ant itself.");
            } else if (str2.equals("-autoproxy")) {
                this.proxy = true;
            } else if (str2.startsWith("-")) {
                Iterator<ArgumentProcessor> it = instance.getProcessors().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i = i;
                        z = false;
                        break;
                    }
                    ArgumentProcessor next = it.next();
                    int readArguments = next.readArguments(strArr, i);
                    if (readArguments != -1) {
                        List<String> list = this.extraArguments.get(next.getClass());
                        if (list == null) {
                            list = new ArrayList<>();
                            this.extraArguments.put(next.getClass(), list);
                        }
                        while (i < readArguments && i < strArr.length) {
                            list.add(strArr[i]);
                            i++;
                        }
                        i = i;
                        z = true;
                    }
                }
                if (!z) {
                    System.err.println("Unknown argument: " + str2);
                    printUsage();
                    throw new BuildException("");
                }
            } else {
                this.targets.addElement(str2);
            }
            i++;
            z2 = false;
        }
        if (this.msgOutputLevel >= 3 || z3) {
            printVersion(this.msgOutputLevel);
        }
        if (z4 || z3 || z5) {
            if (z4) {
                printUsage();
            }
            if (z5) {
                Diagnostics.doReport(System.out, this.msgOutputLevel);
                return;
            }
            return;
        }
        if (this.buildFile == null) {
            if (!z6) {
                Iterator<ProjectHelper> helpers = ProjectHelperRepository.getInstance().getHelpers();
                do {
                    this.buildFile = new File(helpers.next().getDefaultBuildFile());
                    if (this.msgOutputLevel >= 3) {
                        System.out.println("Trying the default build file: " + this.buildFile);
                    }
                    if (this.buildFile.exists()) {
                        break;
                    }
                } while (helpers.hasNext());
            } else if (str != null) {
                this.buildFile = findBuildFile(System.getProperty("user.dir"), str);
                if (this.buildFile == null) {
                    throw new BuildException("Could not locate a build file!");
                }
            } else {
                Iterator<ProjectHelper> helpers2 = ProjectHelperRepository.getInstance().getHelpers();
                do {
                    String defaultBuildFile = helpers2.next().getDefaultBuildFile();
                    if (this.msgOutputLevel >= 3) {
                        System.out.println("Searching the default build file: " + defaultBuildFile);
                    }
                    this.buildFile = findBuildFile(System.getProperty("user.dir"), defaultBuildFile);
                    if (this.buildFile != null) {
                        break;
                    }
                } while (helpers2.hasNext());
                if (this.buildFile == null) {
                    throw new BuildException("Could not locate a build file!");
                }
            }
        }
        if (this.buildFile.exists()) {
            if (this.buildFile.isDirectory()) {
                File file = new File(this.buildFile, DEFAULT_BUILD_FILENAME);
                if (file.isFile()) {
                    this.buildFile = file;
                } else {
                    System.out.println("What? Buildfile: " + this.buildFile + " is a dir!");
                    throw new BuildException("Build failed");
                }
            }
            this.buildFile = FileUtils.getFileUtils().normalize(this.buildFile.getAbsolutePath());
            loadPropertyFiles();
            if (this.msgOutputLevel >= 2) {
                System.out.println("Buildfile: " + this.buildFile);
            }
            if (printStream != null) {
                out = printStream;
                err = printStream;
                System.setOut(out);
                System.setErr(err);
            }
            this.readyToRun = true;
            return;
        }
        System.out.println("Buildfile: " + this.buildFile + " does not exist!");
        throw new BuildException("Build failed");
    }

    private int handleArgBuildFile(String[] strArr, int i) {
        try {
            int i2 = i + 1;
            this.buildFile = new File(strArr[i2].replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar));
            return i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new BuildException("You must specify a buildfile when using the -buildfile argument");
        }
    }

    private int handleArgListener(String[] strArr, int i) {
        try {
            int i2 = i + 1;
            this.listeners.addElement(strArr[i2]);
            return i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new BuildException("You must specify a classname when using the -listener argument");
        }
    }

    private int handleArgDefine(String[] strArr, int i) {
        String str;
        String str2 = strArr[i];
        String substring = str2.substring(2, str2.length());
        int indexOf = substring.indexOf(SimpleComparison.f23609c);
        if (indexOf > 0) {
            str = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else if (i < strArr.length - 1) {
            i++;
            str = strArr[i];
        } else {
            throw new BuildException("Missing value for property " + substring);
        }
        this.definedProps.put(substring, str);
        return i;
    }

    private int handleArgLogger(String[] strArr, int i) {
        if (this.loggerClassname == null) {
            int i2 = i + 1;
            try {
                this.loggerClassname = strArr[i2];
                return i2;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new BuildException("You must specify a classname when using the -logger argument");
            }
        } else {
            throw new BuildException("Only one logger class may be specified.");
        }
    }

    private int handleArgInputHandler(String[] strArr, int i) {
        if (this.inputHandlerClassname == null) {
            int i2 = i + 1;
            try {
                this.inputHandlerClassname = strArr[i2];
                return i2;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new BuildException("You must specify a classname when using the -inputhandler argument");
            }
        } else {
            throw new BuildException("Only one input handler class may be specified.");
        }
    }

    private int handleArgPropertyFile(String[] strArr, int i) {
        try {
            int i2 = i + 1;
            this.propertyFiles.addElement(strArr[i2]);
            return i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new BuildException("You must specify a property filename when using the -propertyfile argument");
        }
    }

    private int handleArgNice(String[] strArr, int i) {
        int i2 = i + 1;
        try {
            this.threadPriority = Integer.decode(strArr[i2]);
            if (this.threadPriority.intValue() >= 1 && this.threadPriority.intValue() <= 10) {
                return i2;
            }
            throw new BuildException("Niceness value is out of the range 1-10");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new BuildException("You must supply a niceness value (1-10) after the -nice option");
        } catch (NumberFormatException unused2) {
            throw new BuildException("Unrecognized niceness value: " + strArr[i2]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadPropertyFiles() {
        /*
            r9 = this;
            java.util.Vector<java.lang.String> r0 = r9.propertyFiles
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007a
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: all -> 0x002c, IOException -> 0x002e
            r4.<init>(r1)     // Catch: all -> 0x002c, IOException -> 0x002e
            r2.load(r4)     // Catch: all -> 0x0024, IOException -> 0x0027
            org.apache.tools.ant.util.FileUtils.close(r4)
            goto L_0x0054
        L_0x0024:
            r0 = move-exception
            r3 = r4
            goto L_0x0076
        L_0x0027:
            r3 = move-exception
            r8 = r4
            r4 = r3
            r3 = r8
            goto L_0x002f
        L_0x002c:
            r0 = move-exception
            goto L_0x0076
        L_0x002e:
            r4 = move-exception
        L_0x002f:
            java.io.PrintStream r5 = java.lang.System.out     // Catch: all -> 0x002c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x002c
            r6.<init>()     // Catch: all -> 0x002c
            java.lang.String r7 = "Could not load property file "
            r6.append(r7)     // Catch: all -> 0x002c
            r6.append(r1)     // Catch: all -> 0x002c
            java.lang.String r1 = ": "
            r6.append(r1)     // Catch: all -> 0x002c
            java.lang.String r1 = r4.getMessage()     // Catch: all -> 0x002c
            r6.append(r1)     // Catch: all -> 0x002c
            java.lang.String r1 = r6.toString()     // Catch: all -> 0x002c
            r5.println(r1)     // Catch: all -> 0x002c
            org.apache.tools.ant.util.FileUtils.close(r3)
        L_0x0054:
            java.util.Enumeration r1 = r2.propertyNames()
        L_0x0058:
            boolean r3 = r1.hasMoreElements()
            if (r3 == 0) goto L_0x0006
            java.lang.Object r3 = r1.nextElement()
            java.lang.String r3 = (java.lang.String) r3
            java.util.Properties r4 = r9.definedProps
            java.lang.String r4 = r4.getProperty(r3)
            if (r4 != 0) goto L_0x0058
            java.util.Properties r4 = r9.definedProps
            java.lang.String r5 = r2.getProperty(r3)
            r4.put(r3, r5)
            goto L_0x0058
        L_0x0076:
            org.apache.tools.ant.util.FileUtils.close(r3)
            throw r0
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.Main.loadPropertyFiles():void");
    }

    @Deprecated
    private File getParentFile(File file) {
        File parentFile = file.getParentFile();
        if (parentFile != null && this.msgOutputLevel >= 3) {
            PrintStream printStream = System.out;
            printStream.println("Searching in " + parentFile.getAbsolutePath());
        }
        return parentFile;
    }

    private File findBuildFile(String str, String str2) {
        if (this.msgOutputLevel >= 2) {
            PrintStream printStream = System.out;
            printStream.println("Searching for " + str2 + " ...");
        }
        File file = new File(new File(str).getAbsolutePath());
        File file2 = new File(file, str2);
        while (!file2.exists()) {
            file = getParentFile(file);
            if (file == null) {
                return null;
            }
            file2 = new File(file, str2);
        }
        return file2;
    }

    /* JADX WARN: Finally extract failed */
    private void runBuild(ClassLoader classLoader) throws BuildException {
        BuildException buildException;
        if (this.readyToRun) {
            ArgumentProcessorRegistry instance = ArgumentProcessorRegistry.getInstance();
            for (ArgumentProcessor argumentProcessor : instance.getProcessors()) {
                List<String> list = this.extraArguments.get(argumentProcessor.getClass());
                if (list != null && argumentProcessor.handleArg(list)) {
                    return;
                }
            }
            Project project = new Project();
            project.setCoreLoader(classLoader);
            Throwable th = null;
            try {
                try {
                    try {
                        addBuildListeners(project);
                        addInputHandler(project);
                        PrintStream printStream = System.err;
                        PrintStream printStream2 = System.out;
                        InputStream inputStream = System.in;
                        SecurityManager securityManager = System.getSecurityManager();
                        try {
                            if (this.allowInput) {
                                project.setDefaultInputStream(System.in);
                            }
                            System.setIn(new DemuxInputStream(project));
                            System.setOut(new PrintStream(new DemuxOutputStream(project, false)));
                            boolean z = true;
                            System.setErr(new PrintStream(new DemuxOutputStream(project, true)));
                            if (!this.projectHelp) {
                                project.fireBuildStarted();
                            }
                            if (this.threadPriority != null) {
                                try {
                                    project.log("Setting Ant's thread priority to " + this.threadPriority, 3);
                                    Thread.currentThread().setPriority(this.threadPriority.intValue());
                                } catch (SecurityException unused) {
                                    project.log("A security manager refused to set the -nice value");
                                }
                            }
                            setProperties(project);
                            project.setKeepGoingMode(this.keepGoingMode);
                            if (this.proxy) {
                                new ProxySetup(project).enableProxies();
                            }
                            for (ArgumentProcessor argumentProcessor2 : instance.getProcessors()) {
                                List<String> list2 = this.extraArguments.get(argumentProcessor2.getClass());
                                if (list2 != null) {
                                    argumentProcessor2.prepareConfigure(project, list2);
                                }
                            }
                            ProjectHelper.configureProject(project, this.buildFile);
                            for (ArgumentProcessor argumentProcessor3 : instance.getProcessors()) {
                                List<String> list3 = this.extraArguments.get(argumentProcessor3.getClass());
                                if (list3 != null && argumentProcessor3.handleArg(project, list3)) {
                                    if (!this.projectHelp) {
                                        try {
                                            project.fireBuildFinished(null);
                                            return;
                                        } finally {
                                        }
                                    } else {
                                        return;
                                    }
                                }
                            }
                            if (this.projectHelp) {
                                printDescription(project);
                                boolean z2 = this.msgOutputLevel > 2;
                                if (this.msgOutputLevel <= 3) {
                                    z = false;
                                }
                                printTargets(project, z2, z);
                                if (securityManager != null) {
                                    System.setSecurityManager(securityManager);
                                }
                                System.setOut(printStream2);
                                System.setErr(printStream);
                                System.setIn(inputStream);
                                if (!this.projectHelp) {
                                    try {
                                        project.fireBuildFinished(null);
                                    } finally {
                                    }
                                }
                            } else {
                                if (this.targets.size() == 0 && project.getDefaultTarget() != null) {
                                    this.targets.addElement(project.getDefaultTarget());
                                }
                                project.executeTargets(this.targets);
                                if (securityManager != null) {
                                    System.setSecurityManager(securityManager);
                                }
                                System.setOut(printStream2);
                                System.setErr(printStream);
                                System.setIn(inputStream);
                                if (!this.projectHelp) {
                                    try {
                                        project.fireBuildFinished(null);
                                    } finally {
                                    }
                                }
                            }
                        } finally {
                            if (securityManager != null) {
                                System.setSecurityManager(securityManager);
                            }
                            System.setOut(printStream2);
                            System.setErr(printStream);
                            System.setIn(inputStream);
                        }
                    } catch (Throwable th2) {
                        if (!this.projectHelp) {
                            try {
                                project.fireBuildFinished(null);
                            } catch (Throwable th3) {
                                System.err.println("Caught an exception while logging the end of the build.  Exception was:");
                                th3.printStackTrace();
                                if (0 != 0) {
                                    System.err.println("There has been an error prior to that:");
                                    th.printStackTrace();
                                }
                                throw new BuildException(th3);
                            }
                        } else if (0 != 0) {
                            project.log(th.toString(), 0);
                        }
                        throw th2;
                    }
                } catch (RuntimeException e) {
                    throw e;
                }
            } catch (Error e2) {
                throw e2;
            }
        }
    }

    private void setProperties(Project project) {
        project.init();
        PropertyHelper propertyHelper = PropertyHelper.getPropertyHelper(project);
        HashMap hashMap = new HashMap(this.definedProps);
        new ResolvePropertyMap(project, NOPROPERTIES, propertyHelper.getExpanders()).resolveAllProperties(hashMap, null, false);
        for (Map.Entry entry : hashMap.entrySet()) {
            project.setUserProperty((String) entry.getKey(), String.valueOf(entry.getValue()));
        }
        project.setUserProperty(MagicNames.ANT_FILE, this.buildFile.getAbsolutePath());
        project.setUserProperty(MagicNames.ANT_FILE_TYPE, "file");
    }

    protected void addBuildListeners(Project project) {
        project.addBuildListener(createLogger());
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            BuildListener buildListener = (BuildListener) ClasspathUtils.newInstance(this.listeners.elementAt(i), Main.class.getClassLoader(), BuildListener.class);
            project.setProjectReference(buildListener);
            project.addBuildListener(buildListener);
        }
    }

    private void addInputHandler(Project project) throws BuildException {
        InputHandler inputHandler;
        String str = this.inputHandlerClassname;
        if (str == null) {
            inputHandler = new DefaultInputHandler();
        } else {
            inputHandler = (InputHandler) ClasspathUtils.newInstance(str, Main.class.getClassLoader(), InputHandler.class);
            project.setProjectReference(inputHandler);
        }
        project.setInputHandler(inputHandler);
    }

    private BuildLogger createLogger() {
        BuildLogger buildLogger;
        if (this.silent) {
            buildLogger = new SilentLogger();
            this.msgOutputLevel = 1;
            this.emacsMode = true;
        } else {
            String str = this.loggerClassname;
            if (str != null) {
                try {
                    buildLogger = (BuildLogger) ClasspathUtils.newInstance(str, Main.class.getClassLoader(), BuildLogger.class);
                } catch (BuildException e) {
                    PrintStream printStream = System.err;
                    printStream.println("The specified logger class " + this.loggerClassname + " could not be used because " + e.getMessage());
                    throw new RuntimeException();
                }
            } else {
                buildLogger = new DefaultLogger();
            }
        }
        buildLogger.setMessageOutputLevel(this.msgOutputLevel);
        buildLogger.setOutputPrintStream(out);
        buildLogger.setErrorPrintStream(err);
        buildLogger.setEmacsMode(this.emacsMode);
        return buildLogger;
    }

    private static void printUsage() {
        System.out.println("ant [options] [target [target2 [target3] ...]]");
        System.out.println("Options: ");
        System.out.println("  -help, -h              print this message and exit");
        System.out.println("  -projecthelp, -p       print project help information and exit");
        System.out.println("  -version               print the version information and exit");
        System.out.println("  -diagnostics           print information that might be helpful to");
        System.out.println("                         diagnose or report problems and exit");
        System.out.println("  -quiet, -q             be extra quiet");
        System.out.println("  -silent, -S            print nothing but task outputs and build failures");
        System.out.println("  -verbose, -v           be extra verbose");
        System.out.println("  -debug, -d             print debugging information");
        System.out.println("  -emacs, -e             produce logging information without adornments");
        System.out.println("  -lib <path>            specifies a path to search for jars and classes");
        System.out.println("  -logfile <file>        use given file for log");
        System.out.println("    -l     <file>                ''");
        System.out.println("  -logger <classname>    the class which is to perform logging");
        System.out.println("  -listener <classname>  add an instance of class as a project listener");
        System.out.println("  -noinput               do not allow interactive input");
        System.out.println("  -buildfile <file>      use given buildfile");
        System.out.println("    -file    <file>              ''");
        System.out.println("    -f       <file>              ''");
        System.out.println("  -D<property>=<value>   use value for given property");
        System.out.println("  -keep-going, -k        execute all targets that do not depend");
        System.out.println("                         on failed target(s)");
        System.out.println("  -propertyfile <name>   load all properties from file with -D");
        System.out.println("                         properties taking precedence");
        System.out.println("  -inputhandler <class>  the class which will handle input requests");
        System.out.println("  -find <file>           (s)earch for buildfile towards the root of");
        System.out.println("    -s  <file>           the filesystem and use it");
        System.out.println("  -nice  number          A niceness value for the main thread:                         1 (lowest) to 10 (highest); 5 is the default");
        System.out.println("  -nouserlib             Run ant without using the jar files from                         ${user.home}/.ant/lib");
        System.out.println("  -noclasspath           Run ant without using CLASSPATH");
        System.out.println("  -autoproxy             Java1.5+: use the OS proxy settings");
        System.out.println("  -main <class>          override Ant's normal entry point");
        for (ArgumentProcessor argumentProcessor : ArgumentProcessorRegistry.getInstance().getProcessors()) {
            argumentProcessor.printUsage(System.out);
        }
    }

    private static void printVersion(int i) throws BuildException {
        System.out.println(getAntVersion());
    }

    public static synchronized String getAntVersion() throws BuildException {
        String str;
        synchronized (Main.class) {
            if (antVersion == null) {
                try {
                    Properties properties = new Properties();
                    InputStream resourceAsStream = Main.class.getResourceAsStream("/org/apache/tools/ant/version.txt");
                    properties.load(resourceAsStream);
                    resourceAsStream.close();
                    shortAntVersion = properties.getProperty("VERSION");
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Apache Ant(TM) version ");
                    stringBuffer.append(shortAntVersion);
                    stringBuffer.append(" compiled on ");
                    stringBuffer.append(properties.getProperty("DATE"));
                    antVersion = stringBuffer.toString();
                } catch (IOException e) {
                    throw new BuildException("Could not load the version information:" + e.getMessage());
                } catch (NullPointerException unused) {
                    throw new BuildException("Could not load the version information.");
                }
            }
            str = antVersion;
        }
        return str;
    }

    public static String getShortAntVersion() throws BuildException {
        if (shortAntVersion == null) {
            getAntVersion();
        }
        return shortAntVersion;
    }

    private static void printDescription(Project project) {
        if (project.getDescription() != null) {
            project.log(project.getDescription());
        }
    }

    private static Map<String, Target> removeDuplicateTargets(Map<String, Target> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Target> entry : map.entrySet()) {
            String key = entry.getKey();
            Target value = entry.getValue();
            Target target = (Target) hashMap.get(value.getLocation());
            if (target == null || target.getName().length() > key.length()) {
                hashMap.put(value.getLocation(), value);
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Target target2 : hashMap.values()) {
            hashMap2.put(target2.getName(), target2);
        }
        return hashMap2;
    }

    private static void printTargets(Project project, boolean z, boolean z2) {
        Map<String, Target> removeDuplicateTargets = removeDuplicateTargets(project.getTargets());
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        Vector vector4 = new Vector();
        Vector vector5 = new Vector();
        int i = 0;
        for (Target target : removeDuplicateTargets.values()) {
            String name = target.getName();
            if (!name.equals("")) {
                String description = target.getDescription();
                if (description == null) {
                    int findTargetPosition = findTargetPosition(vector4, name);
                    vector4.insertElementAt(name, findTargetPosition);
                    if (z2) {
                        vector5.insertElementAt(target.getDependencies(), findTargetPosition);
                    }
                } else {
                    int findTargetPosition2 = findTargetPosition(vector, name);
                    vector.insertElementAt(name, findTargetPosition2);
                    vector2.insertElementAt(description, findTargetPosition2);
                    if (name.length() > i) {
                        i = name.length();
                    }
                    if (z2) {
                        vector3.insertElementAt(target.getDependencies(), findTargetPosition2);
                    }
                }
            }
        }
        printTargets(project, vector, vector2, vector3, "Main targets:", i);
        if (vector.size() == 0 ? true : z) {
            printTargets(project, vector4, null, vector5, "Other targets:", 0);
        }
        String defaultTarget = project.getDefaultTarget();
        if (!(defaultTarget == null || "".equals(defaultTarget))) {
            project.log("Default target: " + defaultTarget);
        }
    }

    private static int findTargetPosition(Vector<String> vector, String str) {
        int size = vector.size();
        int i = size;
        for (int i2 = 0; i2 < size && i == size; i2++) {
            if (str.compareTo(vector.elementAt(i2)) < 0) {
                i = i2;
            }
        }
        return i;
    }

    private static void printTargets(Project project, Vector<String> vector, Vector<String> vector2, Vector<Enumeration<String>> vector3, String str, int i) {
        String property = System.getProperty("line.separator");
        String str2 = "    ";
        while (str2.length() <= i) {
            str2 = str2 + str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str + property + property);
        int size = vector.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(ExpandableTextView.f6958c);
            sb.append(vector.elementAt(i2));
            if (vector2 != null) {
                sb.append(str2.substring(0, (i - vector.elementAt(i2).length()) + 2));
                sb.append(vector2.elementAt(i2));
            }
            sb.append(property);
            if (!vector3.isEmpty()) {
                Enumeration<String> elementAt = vector3.elementAt(i2);
                if (elementAt.hasMoreElements()) {
                    sb.append("   depends on: ");
                    while (elementAt.hasMoreElements()) {
                        sb.append(elementAt.nextElement());
                        if (elementAt.hasMoreElements()) {
                            sb.append(", ");
                        }
                    }
                    sb.append(property);
                }
            }
        }
        project.log(sb.toString(), 1);
    }
}
