package org.apache.tools.ant.taskdefs.optional.javacc;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.JavaEnvUtils;

/* loaded from: classes2.dex */
public class JavaCC extends Task {
    protected static final String[] ARCHIVE_LOCATIONS = {"JavaCC.zip", "bin/lib/JavaCC.zip", "bin/lib/javacc.jar", "javacc.jar"};
    protected static final int[] ARCHIVE_LOCATIONS_VS_MAJOR_VERSION = {1, 2, 3, 3};
    private static final String BUILD_PARSER = "BUILD_PARSER";
    private static final String BUILD_TOKEN_MANAGER = "BUILD_TOKEN_MANAGER";
    private static final String CACHE_TOKENS = "CACHE_TOKENS";
    private static final String CHOICE_AMBIGUITY_CHECK = "CHOICE_AMBIGUITY_CHECK";
    private static final String COMMON_TOKEN_ACTION = "COMMON_TOKEN_ACTION";
    protected static final String COM_JAVACC_CLASS = "javacc.Main";
    protected static final String COM_JJDOC_CLASS = "jjdoc.JJDocMain";
    protected static final String COM_JJTREE_CLASS = "jjtree.Main";
    protected static final String COM_PACKAGE = "COM.sun.labs.";
    private static final String DEBUG_LOOKAHEAD = "DEBUG_LOOKAHEAD";
    private static final String DEBUG_PARSER = "DEBUG_PARSER";
    private static final String DEBUG_TOKEN_MANAGER = "DEBUG_TOKEN_MANAGER";
    private static final String ERROR_REPORTING = "ERROR_REPORTING";
    private static final String FORCE_LA_CHECK = "FORCE_LA_CHECK";
    private static final String IGNORE_CASE = "IGNORE_CASE";
    private static final String JAVA_UNICODE_ESCAPE = "JAVA_UNICODE_ESCAPE";
    private static final String JDK_VERSION = "JDK_VERSION";
    private static final String KEEP_LINE_COLUMN = "KEEP_LINE_COLUMN";
    private static final String LOOKAHEAD = "LOOKAHEAD";
    private static final String OPTIMIZE_TOKEN_MANAGER = "OPTIMIZE_TOKEN_MANAGER";
    protected static final String ORG_JAVACC_CLASS = "parser.Main";
    protected static final String ORG_JJDOC_CLASS = "jjdoc.JJDocMain";
    protected static final String ORG_JJTREE_CLASS = "jjtree.Main";
    protected static final String ORG_PACKAGE_3_0 = "org.netbeans.javacc.";
    protected static final String ORG_PACKAGE_3_1 = "org.javacc.";
    private static final String OTHER_AMBIGUITY_CHECK = "OTHER_AMBIGUITY_CHECK";
    private static final String SANITY_CHECK = "SANITY_CHECK";
    private static final String STATIC = "STATIC";
    protected static final int TASKDEF_TYPE_JAVACC = 1;
    protected static final int TASKDEF_TYPE_JJDOC = 3;
    protected static final int TASKDEF_TYPE_JJTREE = 2;
    private static final String UNICODE_INPUT = "UNICODE_INPUT";
    private static final String USER_CHAR_STREAM = "USER_CHAR_STREAM";
    private static final String USER_TOKEN_MANAGER = "USER_TOKEN_MANAGER";
    private final Hashtable optionalAttrs = new Hashtable();
    private File outputDirectory = null;
    private File targetFile = null;
    private File javaccHome = null;
    private CommandlineJava cmdl = new CommandlineJava();
    private String maxMemory = null;

    public void setLookahead(int i) {
        this.optionalAttrs.put(LOOKAHEAD, new Integer(i));
    }

    public void setChoiceambiguitycheck(int i) {
        this.optionalAttrs.put(CHOICE_AMBIGUITY_CHECK, new Integer(i));
    }

    public void setOtherambiguityCheck(int i) {
        this.optionalAttrs.put(OTHER_AMBIGUITY_CHECK, new Integer(i));
    }

    public void setStatic(boolean z) {
        this.optionalAttrs.put(STATIC, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setDebugparser(boolean z) {
        this.optionalAttrs.put(DEBUG_PARSER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setDebuglookahead(boolean z) {
        this.optionalAttrs.put(DEBUG_LOOKAHEAD, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setDebugtokenmanager(boolean z) {
        this.optionalAttrs.put(DEBUG_TOKEN_MANAGER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setOptimizetokenmanager(boolean z) {
        this.optionalAttrs.put(OPTIMIZE_TOKEN_MANAGER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setErrorreporting(boolean z) {
        this.optionalAttrs.put(ERROR_REPORTING, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setJavaunicodeescape(boolean z) {
        this.optionalAttrs.put(JAVA_UNICODE_ESCAPE, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setUnicodeinput(boolean z) {
        this.optionalAttrs.put(UNICODE_INPUT, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setIgnorecase(boolean z) {
        this.optionalAttrs.put(IGNORE_CASE, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setCommontokenaction(boolean z) {
        this.optionalAttrs.put(COMMON_TOKEN_ACTION, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setUsertokenmanager(boolean z) {
        this.optionalAttrs.put(USER_TOKEN_MANAGER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setUsercharstream(boolean z) {
        this.optionalAttrs.put(USER_CHAR_STREAM, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setBuildparser(boolean z) {
        this.optionalAttrs.put(BUILD_PARSER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setBuildtokenmanager(boolean z) {
        this.optionalAttrs.put(BUILD_TOKEN_MANAGER, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setSanitycheck(boolean z) {
        this.optionalAttrs.put(SANITY_CHECK, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setForcelacheck(boolean z) {
        this.optionalAttrs.put(FORCE_LA_CHECK, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setCachetokens(boolean z) {
        this.optionalAttrs.put(CACHE_TOKENS, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setKeeplinecolumn(boolean z) {
        this.optionalAttrs.put(KEEP_LINE_COLUMN, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setJDKversion(String str) {
        this.optionalAttrs.put(JDK_VERSION, str);
    }

    public void setOutputdirectory(File file) {
        this.outputDirectory = file;
    }

    public void setTarget(File file) {
        this.targetFile = file;
    }

    public void setJavacchome(File file) {
        this.javaccHome = file;
    }

    public void setMaxmemory(String str) {
        this.maxMemory = str;
    }

    public JavaCC() {
        this.cmdl.setVm(JavaEnvUtils.getJreExecutable("java"));
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Enumeration keys = this.optionalAttrs.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            Object obj = this.optionalAttrs.get(str);
            Commandline.Argument createArgument = this.cmdl.createArgument();
            createArgument.setValue("-" + str + ":" + obj.toString());
        }
        File file = this.targetFile;
        if (file == null || !file.isFile()) {
            throw new BuildException("Invalid target: " + this.targetFile);
        }
        File file2 = this.outputDirectory;
        if (file2 == null) {
            this.outputDirectory = new File(this.targetFile.getParent());
        } else if (!file2.isDirectory()) {
            throw new BuildException("Outputdir not a directory.");
        }
        Commandline.Argument createArgument2 = this.cmdl.createArgument();
        createArgument2.setValue("-OUTPUT_DIRECTORY:" + this.outputDirectory.getAbsolutePath());
        File outputJavaFile = getOutputJavaFile(this.outputDirectory, this.targetFile);
        if (!outputJavaFile.exists() || this.targetFile.lastModified() >= outputJavaFile.lastModified()) {
            this.cmdl.createArgument().setValue(this.targetFile.getAbsolutePath());
            Path createClasspath = this.cmdl.createClasspath(getProject());
            createClasspath.createPathElement().setPath(getArchiveFile(this.javaccHome).getAbsolutePath());
            createClasspath.addJavaRuntime();
            this.cmdl.setClassname(getMainClass(createClasspath, 1));
            this.cmdl.setMaxmemory(this.maxMemory);
            Commandline.Argument createVmArgument = this.cmdl.createVmArgument();
            createVmArgument.setValue("-Dinstall.root=" + this.javaccHome.getAbsolutePath());
            Execute.runCommand(this, this.cmdl.getCommandline());
            return;
        }
        log("Target is already built - skipping (" + this.targetFile + ")", 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static File getArchiveFile(File file) throws BuildException {
        return new File(file, ARCHIVE_LOCATIONS[getArchiveLocationIndex(file)]);
    }

    protected static String getMainClass(File file, int i) throws BuildException {
        Path path = new Path(null);
        path.createPathElement().setLocation(getArchiveFile(file));
        path.addJavaRuntime();
        return getMainClass(path, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getMainClass(Path path, int i) throws BuildException {
        Throwable th;
        AntClassLoader antClassLoader;
        String str;
        String str2;
        String str3 = null;
        try {
            antClassLoader = AntClassLoader.newAntClassLoader(null, null, path.concatSystemClasspath(Definer.OnError.POLICY_IGNORE), true);
            try {
                if (antClassLoader.getResourceAsStream("COM.sun.labs.javacc.Main".replace((char) FilenameUtils.EXTENSION_SEPARATOR, (char) IOUtils.DIR_SEPARATOR_UNIX) + ".class") != null) {
                    str = COM_PACKAGE;
                    switch (i) {
                        case 1:
                            str3 = COM_JAVACC_CLASS;
                            break;
                        case 2:
                            str3 = "jjtree.Main";
                            break;
                        case 3:
                            str3 = "jjdoc.JJDocMain";
                            break;
                    }
                } else {
                    InputStream resourceAsStream = antClassLoader.getResourceAsStream("org.javacc.parser.Main".replace((char) FilenameUtils.EXTENSION_SEPARATOR, (char) IOUtils.DIR_SEPARATOR_UNIX) + ".class");
                    if (resourceAsStream != null) {
                        str2 = ORG_PACKAGE_3_1;
                    } else {
                        resourceAsStream = antClassLoader.getResourceAsStream("org.netbeans.javacc.parser.Main".replace((char) FilenameUtils.EXTENSION_SEPARATOR, (char) IOUtils.DIR_SEPARATOR_UNIX) + ".class");
                        str2 = resourceAsStream != null ? ORG_PACKAGE_3_0 : null;
                    }
                    if (resourceAsStream != null) {
                        switch (i) {
                            case 1:
                                str3 = ORG_JAVACC_CLASS;
                                str = str2;
                                break;
                            case 2:
                                str3 = "jjtree.Main";
                                str = str2;
                                break;
                            case 3:
                                str3 = "jjdoc.JJDocMain";
                                str = str2;
                                break;
                        }
                    }
                    str = str2;
                }
                if (str == null) {
                    throw new BuildException("failed to load JavaCC");
                } else if (str3 != null) {
                    String str4 = str + str3;
                    if (antClassLoader != null) {
                        antClassLoader.cleanup();
                    }
                    return str4;
                } else {
                    throw new BuildException("unknown task type " + i);
                }
            } catch (Throwable th2) {
                th = th2;
                if (antClassLoader != null) {
                    antClassLoader.cleanup();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            antClassLoader = null;
        }
    }

    private static int getArchiveLocationIndex(File file) throws BuildException {
        if (file == null || !file.isDirectory()) {
            throw new BuildException("JavaCC home must be a valid directory.");
        }
        int i = 0;
        while (true) {
            String[] strArr = ARCHIVE_LOCATIONS;
            if (i >= strArr.length) {
                throw new BuildException("Could not find a path to JavaCC.zip or javacc.jar from '" + file + "'.");
            } else if (new File(file, strArr[i]).exists()) {
                return i;
            } else {
                i++;
            }
        }
    }

    protected static int getMajorVersionNumber(File file) throws BuildException {
        return ARCHIVE_LOCATIONS_VS_MAJOR_VERSION[getArchiveLocationIndex(file)];
    }

    private File getOutputJavaFile(File file, File file2) {
        int lastIndexOf;
        String str;
        String path = file2.getPath();
        int lastIndexOf2 = path.lastIndexOf(File.separator);
        if (lastIndexOf2 != -1) {
            path = path.substring(lastIndexOf2 + 1);
        }
        if (path.lastIndexOf(46) != -1) {
            str = path.substring(0, lastIndexOf) + ".java";
        } else {
            str = path + ".java";
        }
        if (file != null) {
            str = file + File.separator + str;
        }
        return new File(str);
    }
}
